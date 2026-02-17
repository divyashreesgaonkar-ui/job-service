package com.icodian.careervia.job.service;

import java.util.List;

import com.icodian.careervia.job.dto.JobDetailResponseDTO;
import com.icodian.careervia.job.dto.JobListResponseDTO;
import com.icodian.careervia.job.dto.JobRequestDTO;
import com.icodian.careervia.job.dto.JobResponseDTO;
import com.icodian.careervia.job.entity.constant.JobStatus;
import com.icodian.careervia.job.entity.constant.UserRole;

public interface JobService {
	
	JobResponseDTO createJob(JobRequestDTO request);
	
	List<JobListResponseDTO> getAlljobs(UserRole userRole, JobStatus job_status);
	
	JobDetailResponseDTO getJobById(Long job_id, UserRole userRole);

}
