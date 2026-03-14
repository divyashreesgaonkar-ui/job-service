package com.icodian.careervia.job.service;

import java.util.List;

import com.icodian.careervia.job.dto.JobDetailResponseDTO;
import com.icodian.careervia.job.dto.JobListResponseDTO;
import com.icodian.careervia.job.dto.JobRequestDTO;
import com.icodian.careervia.job.dto.JobResponseDTO;
import com.icodian.careervia.job.dto.UpdateJobRequestDTO;
import com.icodian.careervia.job.entity.constant.JobStatus;
import com.icodian.careervia.job.entity.constant.JobType;
import com.icodian.careervia.job.entity.constant.UserRole;

public interface JobService {
	
	JobResponseDTO createJob(JobRequestDTO request);
	
/*	List<JobListResponseDTO> getAlljobs(UserRole userRole, 
			JobStatus job_status, 
			String location, 
			String company_name, 
			JobType jobType, 
			String experience);
	
	JobDetailResponseDTO getJobById(Long job_id, UserRole userRole);
	
	JobResponseDTO updateJob(Long job_id, UpdateJobRequestDTO request);

	boolean isJobExists(Long job_id);
	
	*/

}
