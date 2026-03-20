package com.icodian.careervia.job.service;

import java.util.List;
import java.util.Optional;

import com.icodian.careervia.job.dto.JobRequestDTO;
import com.icodian.careervia.job.dto.JobResponseDTO;

public interface JobService {
	
	JobResponseDTO createJob(JobRequestDTO request);
	
	List<JobResponseDTO> getAllJobs();

	Optional<JobResponseDTO> getJobById(Long jobId);

	JobResponseDTO updateJob(Long jobId, JobRequestDTO request);

	String deleteJob(Long jobId);
	

}
