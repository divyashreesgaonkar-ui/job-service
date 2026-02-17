package com.icodian.careervia.job.service;

import com.icodian.careervia.job.dto.JobRequestDTO;
import com.icodian.careervia.job.dto.JobResponseDTO;

public interface JobService {
	
	JobResponseDTO createJob(JobRequestDTO request);

}
