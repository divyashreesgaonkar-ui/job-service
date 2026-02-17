package com.icodian.careervia.job.service.impl;

import org.springframework.stereotype.Service;

import com.icodian.careervia.job.dto.JobRequestDTO;
import com.icodian.careervia.job.dto.JobResponseDTO;
import com.icodian.careervia.job.entity.Job;
import com.icodian.careervia.job.exceptions.InvalidJobDataException;
import com.icodian.careervia.job.repository.JobRepository;
import com.icodian.careervia.job.service.JobService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobServiceImpl implements JobService {

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

}
