package com.icodian.careervia.job.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icodian.careervia.job.dto.JobDetailResponseDTO;
import com.icodian.careervia.job.dto.JobListResponseDTO;
import com.icodian.careervia.job.dto.JobRequestDTO;
import com.icodian.careervia.job.dto.JobResponseDTO;
import com.icodian.careervia.job.entity.Job;
import com.icodian.careervia.job.entity.constant.JobStatus;
import com.icodian.careervia.job.entity.constant.UserRole;
import com.icodian.careervia.job.exceptions.InvalidJobDataException;
import com.icodian.careervia.job.exceptions.JobAlreadyClosedException;
import com.icodian.careervia.job.exceptions.JobNotFoundException;
import com.icodian.careervia.job.exceptions.UnauthorizedAccessException;
import com.icodian.careervia.job.repository.JobRepository;
import com.icodian.careervia.job.service.JobService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobServiceImpl implements JobService {

	@Autowired
	private final JobRepository jobRepository;

	@Override
	public JobResponseDTO createJob(JobRequestDTO request) {
		// TODO Auto-generated method stub

		log.info("Creating job with title: {}", request.getJob_title());

//		Validate the salary if it is null and throw an exception
		if (request.getSalary() == null) {
			throw new InvalidJobDataException("Salary is required");
		}

//		Creating the job entity
		Job job = new Job();
		job.setJob_title(request.getJob_title());
		job.setDescription(request.getDescriprtion());
		job.setLocation(request.getLocation());
		job.setExperience(request.getExperience());
		job.setSalary(request.getSalary());
		job.setJob_type(request.getJob_type());
		job.setSkills(request.getRequired_skills());
		job.setPosted_date(request.getPosted_date());
		job.setCompany_id(request.getCompany_id());
		job.setJob_status(request.getJob_status());

		Job savedJob = jobRepository.save(job);
		log.info("Job created successfully with job ID", savedJob.getJob_id());

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
		response.setRequired_skills(job.getSkills());
		response.setPosted_date(job.getPosted_date());
		response.setCompany_id(job.getCompany_id());
		response.setJob_status(job.getJob_status());

		return response;
	}

	@Override
	public List<JobListResponseDTO> getAlljobs(UserRole userRole, JobStatus status) {
		// TODO Auto-generated method stub
		log.info("Fetching jobs for role: {} with filters - status: {}", 
                userRole, status);
		
//		Job Seekers can only see ACTIVE jobs
		if(userRole == UserRole.JOB_SEEKER) {
			if(status != null && status != JobStatus.OPENED) {
				throw new UnauthorizedAccessException("Job seekers can only view active jobs");
			}
			status = JobStatus.OPENED;
		}
		
//		Fetch jobs based on filters
		List<Job> jobs = jobRepository.findJobsByFilters(status);
		
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
		response.setCompany_id(job.getCompany_id());
		response.setJob_status(job.getJob_status());	
		
		return response;
	}
	
	// Statuses restricted for Job Seekers
	private static final List<JobStatus> RESTRICTED_STATUSES = List.of(
		    JobStatus.CLOSED,
		    JobStatus.DISABLED,
		    JobStatus.EXPIRED
		);

	@Override
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
		response.setCompany_id(job.getCompany_id());
		
		return response;
		
	}

}
