package com.icodian.careervia.job.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.icodian.careervia.job.dto.JobRequestDTO;
import com.icodian.careervia.job.dto.JobResponseDTO;
import com.icodian.careervia.job.entity.Company;
import com.icodian.careervia.job.entity.Job;
import com.icodian.careervia.job.exceptions.InvalidJobDataException;
import com.icodian.careervia.job.exceptions.JobNotFoundException;
import com.icodian.careervia.job.repository.JobRepository;
import com.icodian.careervia.job.service.JobService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private RestTemplate restTemplate;

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

		Company company_info = restTemplate
				.getForObject("http://COMPANY-MICROSERVICE/api/companies/" + request.getCompany_id(), Company.class);
		if (company_info == null) {
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

	@Override
	public List<JobResponseDTO> getAllJobs() {
		// TODO Auto-generated method stub
		return jobRepository.findAll()
				.stream()
				.map(this::mapToListResponse)
				.collect(Collectors.toList());
	}
	
	private JobResponseDTO mapToListResponse(Job job) {
		 
		  JobResponseDTO response = new JobResponseDTO();
		 
		 response.setJob_id(job.getJob_id());
		 response.setJob_title(job.getJob_title());
		 response.setDescription(job.getDescription());
		 response.setLocation(job.getLocation());
		 response.setExperience(job.getExperience());
		 response.setSalary(job.getSalary()); response.setJob_type(job.getJob_type());
		 response.setRequired_skills(job.getRequired_skills());
		 response.setPosted_date(job.getPosted_date());
		 response.setCompany_id(job.getCompanyId());
		 response.setJob_status(job.getJob_status());
		 
		 return response; 
		 }

	@Override
	public Optional<JobResponseDTO> getJobById(Long jobId) {
		// TODO Auto-generated method stub
		
		Job job = jobRepository.findById(jobId)
				.orElseThrow(()-> new JobNotFoundException("Job not found with id: "+ jobId));
		
		JobResponseDTO dto = new JobResponseDTO();
		
		dto.setJob_id(job.getJob_id());
		dto.setJob_title(job.getJob_title());
		dto.setDescription(job.getDescription());
		dto.setLocation(job.getLocation());
		dto.setExperience(job.getExperience());
		dto.setSalary(job.getSalary());
		
		dto.setRequired_skills(job.getRequired_skills());
		dto.setPosted_date(job.getPosted_date());
		dto.setCompany_id(job.getCompanyId());
		dto.setJob_status(job.getJob_status());
		
		return Optional.ofNullable(dto);
		
	}

	@Override
	public JobResponseDTO updateJob(Long jobId, JobRequestDTO request) {
		// TODO Auto-generated method stub
		
		if(request == null) {
			throw new JobNotFoundException("Job is not present.");
		}
		
		Job job = jobRepository.findById(jobId)
				.orElseThrow(()-> new JobNotFoundException("Job not found with jobId : "+jobId));
		
		if(request.getJob_title() != null) {
			job.setJob_title(request.getJob_title());
		}
		
		if(request.getDescriprtion() != null) {
			job.setDescription(request.getDescriprtion());
		}
		
		if(request.getLocation() != null) {
			job.setLocation(request.getLocation());
			}
		
		if(request.getExperience() != null) {
			job.setExperience(request.getExperience());	
			}

		if(request.getSalary() != null) {
			job.setSalary(request.getSalary());
			}
		
		if(request.getJob_type() != null) {
			job.setJob_type(request.getJob_type());	
			}
		
		if(request.getRequired_skills() != null) {
			job.setRequired_skills(request.getRequired_skills());
			}
		
		if(request.getJob_status() != null) {
			job.setJob_status(request.getJob_status());
		}
		
		Job updateJob = jobRepository.save(job);
		
		return mapToUpdateResponse(updateJob);
	}

	private JobResponseDTO mapToUpdateResponse(Job updateJob) {
		// TODO Auto-generated method stub
		
		JobResponseDTO dto = new JobResponseDTO();
		
		dto.setJob_id(updateJob.getJob_id());
		dto.setJob_title(updateJob.getJob_title());
		dto.setDescription(updateJob.getDescription());
		dto.setLocation(updateJob.getLocation());
		dto.setExperience(updateJob.getExperience());
		dto.setSalary(updateJob.getSalary());
		dto.setJob_type(updateJob.getJob_type());
		dto.setRequired_skills(updateJob.getRequired_skills());
		dto.setJob_status(updateJob.getJob_status());		
		
		return dto;
	}

	@Override
	public String deleteJob(Long job_id) {
		// TODO Auto-generated method stub
		
		if(job_id == null) {
			throw new InvalidJobDataException("Please add your job ID. Job Id is empty.");
		}
		
		jobRepository.deleteById(job_id);
		
		return "Job has been deleted";
	}
	
	


}
