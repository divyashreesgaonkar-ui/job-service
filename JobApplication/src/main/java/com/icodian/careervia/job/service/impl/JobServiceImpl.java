package com.icodian.careervia.job.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.icodian.careervia.job.dto.JobDetailResponseDTO;
import com.icodian.careervia.job.dto.JobListResponseDTO;
import com.icodian.careervia.job.dto.JobRequestDTO;
import com.icodian.careervia.job.dto.JobResponseDTO;
import com.icodian.careervia.job.dto.UpdateJobRequestDTO;
import com.icodian.careervia.job.entity.Company;
import com.icodian.careervia.job.entity.Job;
import com.icodian.careervia.job.entity.constant.ApprovalStatus;
import com.icodian.careervia.job.entity.constant.JobStatus;
import com.icodian.careervia.job.entity.constant.JobType;
import com.icodian.careervia.job.entity.constant.UserRole;
import com.icodian.careervia.job.exceptions.InvalidJobDataException;
import com.icodian.careervia.job.exceptions.JobAlreadyClosedException;
import com.icodian.careervia.job.exceptions.JobNotFoundException;
import com.icodian.careervia.job.exceptions.JobNotUpdatableException;
import com.icodian.careervia.job.exceptions.UnauthorizedAccessException;
import com.icodian.careervia.job.repository.JobRepository;
import com.icodian.careervia.job.service.JobService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Company company;
	

	@Override
	public JobResponseDTO createJob(JobRequestDTO request) {
		// TODO Auto-generated method stub

//		log.info("Creating job with title: {} for company ID: {}", request.getJob_title(), request.getCompany_id());

//		Validate the salary if it is null and throw an exception
		if (request.getSalary() == null) {
			throw new InvalidJobDataException("Salary is required");
		}
		
//		Validate Company exists by calling Company Service
//		CompanyResponseDTO company = companyClient.getCompanyById(request.getCompany_id());
//		log.info("Company validated: {})", company.getCompanyId());
		
//		company.setCompanyId(request.getCompany_id());
		
		Company company_info = restTemplate.getForObject("http://COMPANY-MICROSERVICE/api/companies/" 
				+ request.getCompany_id() , Company.class);
		if(company_info == null) {
			throw new RuntimeException("Company not found");
			
		}

//		Creating the job entity
		Job job = new Job();
	
		job.setJob_title(request.getJob_title());
		job.setDescription(request.getDescriprtion());
		job.setLocation(request.getLocation());
		job.setExperience(request.getExperience());
		job.setSalary(request.getSalary());
		job.setJob_type(request.getJob_type());
		job.setRequired_skills(request.getRequired_skills());
		job.setPosted_date(request.getPosted_date());
		job.setCompanyId(request.getCompany_id());
		job.setJob_status(request.getJob_status());
		
		Job savedJob = jobRepository.save(job);
		log.info("Job created successfully with job ID: {} ", savedJob.getJob_id());

		return mapToResponse(savedJob);
	}

	private JobResponseDTO mapToResponse(Job job) {
		// TODO Auto-generated method stub

		JobResponseDTO response = new JobResponseDTO();
		response.setJob_id(job.getJob_id());
		response.setJob_title(job.getJob_title());
		response.setDescription(job.getDescription());
		response.setLocation(job.getLocation());
		response.setExperience(job.getExperience());
		response.setSalary(job.getSalary());
		response.setJob_type(job.getJob_type());
		response.setRequired_skills(job.getRequired_skills());
		response.setPosted_date(job.getPosted_date());
		response.setCompany_id(job.getCompanyId());
		response.setJob_status(job.getJob_status());

		return response;
	}

/*	@Override
	@Transactional(readOnly = true)
	public List<JobListResponseDTO> getAlljobs(
			UserRole userRole, 
			JobStatus status, 
			String location, 
			String company_name, 
			JobType jobType, 
			String experience) {
		// TODO Auto-generated method stub
		log.info("Fetching jobs for role: {} with filters - status: {}, location: {}, company: {}", 
                userRole, status, location, company_name);
		
//		Job Seekers can only see ACTIVE jobs
		if(userRole == UserRole.JOB_SEEKER) {
			if(status != null && status != JobStatus.OPENED) {
				throw new UnauthorizedAccessException("Job seekers can only view active jobs");
			}
			status = JobStatus.OPENED;
		}
		
//		Fetch jobs based on filters
		List<Job> jobs = jobRepository.findJobsByFilters(status, location, company_name, jobType, experience);
		
		log.info("Found {} jobs matching the criteria", jobs.size());
		
		return jobs.stream()
				.map(this::mapToListResponse)
				.collect(Collectors.toList());
		
				
	}
	
	private JobListResponseDTO mapToListResponse(Job job) {
		
		JobListResponseDTO response = new JobListResponseDTO();
		
		response.setJob_id(job.getJob_id());
		response.setJob_title(job.getJob_title());
		response.setLocation(job.getLocation());
		response.setExperience(job.getExperience());
		response.setSalary(job.getSalary());
		response.setJob_type(job.getJob_type());
		response.setRequired_skills(job.getSkills());
		response.setPosted_date(job.getPosted_date());
		response.setCompany_id(job.getCompanyId());
		response.setJob_status(job.getJob_status());	
		
		return response;
	}
	
	

	@Override
	@Transactional(readOnly = true)
	public JobDetailResponseDTO getJobById(Long job_id, UserRole userRole) {
		// TODO Auto-generated method stub
		
		log.info("Fetching job details for Job ID: {} by role: {}", job_id, userRole);
		
//		Check if job exist
		Job job = jobRepository.findById(job_id)
	            .orElseThrow(() -> new JobNotFoundException(
	                "Job not found with ID: " + job_id));
		
//		Check Role based access
		if (userRole == UserRole.JOB_SEEKER &&
	            RESTRICTED_STATUSES.contains(job.getJob_status())) {
	        log.warn("Job Seeker tried to access restricted job ID: {} with status: {}",
	                 job_id, job.getJob_status());
	        throw new JobAlreadyClosedException(
	            "This job is no longer accessible. " +
	            "Job status is: " + job.getJob_status());
	    }
		
		log.info("Job details fetched successfully for Job ID: {}", job_id);
		
		
		return mapToJobDetailResponse(job);
	}
	
	private JobDetailResponseDTO mapToJobDetailResponse(Job job) {
		
		JobDetailResponseDTO response = new JobDetailResponseDTO();
		
		response.setJob_id(job.getJob_id());
		response.setJob_title(job.getJob_title());
		response.setPosted_date(job.getPosted_date());
		response.setJob_status(job.getJob_status());
		response.setDescription(job.getDescription());
		response.setRequired_skills(job.getSkills());
		response.setExperience(job.getExperience());
		response.setLocation(job.getLocation());
		response.setSalary(job.getSalary());
		response.setJob_type(job.getJob_type());
		response.setCompany_id(job.getCompanyId());
		
		return response;
		
	}

	@Override
	public JobResponseDTO updateJob(Long job_id, UpdateJobRequestDTO request) {
		// TODO Auto-generated method stub
		log.info("HR requested update for Job ID: {}", job_id);
		
//		Check if job exists
		Job job = jobRepository.findById(job_id)
	            .orElseThrow(() -> new JobNotFoundException(
	                "Job not found with ID: " + job_id));
		
//		Block update if job is CLOSED / EXPIRED / DISABLED
		if (RESTRICTED_STATUSES.contains(job.getJob_status())) {
	        log.warn("Update blocked for Job ID: {} — Status is: {}", job_id, job.getJob_status());
	        throw new JobNotUpdatableException(
	            "Job cannot be updated because its current status is: "
	            + job.getJob_status()
	            + ". Updates are only allowed for OPENED, DRAFT or ON_HOLD jobs.");
	    }
		
		if(!(request.getSalary() != null)) {
			throw new InvalidJobDataException(
		            "Salary cannot be zero.");
		}
		
//		Detect critical field changes — collect changed field names
		List<String> changedCriticalFields = new ArrayList<>();
		
		if (request.getJob_title() != null
	            && !request.getJob_title().equals(job.getJob_title())) {
	        changedCriticalFields.add(CRITICAL_FIELD_TITLE);
	    }

	    if (request.getExperience() != null
	            && !request.getExperience().equals(job.getExperience())) {
	        changedCriticalFields.add(CRITICAL_FIELD_EXPERIENCE);
	    }

	    if (request.getJob_type() != null
	            && !request.getJob_type().equals(job.getJob_type())) {
	        changedCriticalFields.add(CRITICAL_FIELD_JOB_TYPE);
	    }
	    
	    boolean salaryChanged =
	            (request.getSalary() != null
	                && !request.getSalary().equals(job.getSalary()));
	    
	    if (salaryChanged) {
	        changedCriticalFields.add(CRITICAL_FIELD_SALARY);
	    }
	    
//	    Apply non-critical field updates directly
	    
	    if (request.getDescription() != null) {
	        job.setDescription(request.getDescription());
	    }
	    
	    if (request.getLocation() != null) {
	        job.setLocation(request.getLocation());
	    }
	    
	    if (request.getRequired_skills() != null) {
	        job.setSkills(request.getRequired_skills());
	    }
	    
	    if (request.getJob_status() != null) {
	        job.setJob_status(request.getJob_status());
	    }
	    
//	    Apply critical field updates & flag for re-approval if changed
	    String responseMessage;
	    
	    if (!changedCriticalFields.isEmpty()) {
	    	
	    	// Apply the critical field changes
	        if (request.getJob_title() != null) {
	            job.setJob_title(request.getJob_title());
	        }
	        if (request.getExperience() != null) {
	            job.setExperience(request.getExperience());
	        }
	        if (request.getJob_type() != null) {
	            job.setJob_type(request.getJob_type());
	        }
	        if (request.getSalary() != null) {
	            job.setSalary(request.getSalary());
	        }
	        
//	        Flag job as PENDING_APPROVAL for Admin review
	        
	        job.setApprovalStatus(ApprovalStatus.PENDING_APPROVAL);
	        job.setPendingChangesSummary(
	            "Critical fields changed by HR: [" +
	            String.join(", ", changedCriticalFields) + "]" +
	            " — Awaiting Admin reapproval.");

	        responseMessage =
	            "Job updated successfully. However, the following critical fields " +
	            "were changed: [" + String.join(", ", changedCriticalFields) + "]. " +
	            "Admin reapproval is required before these changes take effect publicly.";

	        log.info("Job ID: {} flagged for Admin reapproval. Changed fields: {}",
	                 job_id, changedCriticalFields);

	    }else {
	    	// No critical fields changed — no re-approval needed
	        job.setApprovalStatus(ApprovalStatus.APPROVED);
	        job.setPendingChangesSummary(null);

	        responseMessage = "Job updated successfully. No reapproval required.";
	        log.info("Job ID: {} updated successfully without reapproval.", job_id);
	        	    	
	    }
	    
//	    Save updated job
	    
	    Job updatedJob = jobRepository.save(job);
	    
	    return mapToJobResponseDTO(updatedJob, responseMessage);
	    
	}
	
//	Mapper
	
	private JobResponseDTO mapToJobResponseDTO(Job job, String message) {
	    JobResponseDTO response = new JobResponseDTO();
	    response.setJob_id(job.getJob_id());
	    response.setJob_title(job.getJob_title());
	    response.setDescription(job.getDescription());
	    response.setLocation(job.getLocation());
	    response.setExperience(job.getExperience());
	    response.setSalary(job.getSalary());
	    response.setJob_type(job.getJob_type());
	    response.setRequired_skills(job.getSkills());
	    response.setJob_status(job.getJob_status());
	    response.setMessage(message);
	    return response;
	}

	@Override
	public boolean isJobExists(Long job_id) {
		// TODO Auto-generated method stub
		return jobRepository.existsById(job_id);
	}
*/
}
