package com.icodian.careervia.job.service;

import java.util.List;

import com.icodian.careervia.job.dto.ApplicationRequestDTO;
import com.icodian.careervia.job.dto.ApplicationResponseDTO;
import com.icodian.careervia.job.dto.ApplicationStatusResponseDTO;
import com.icodian.careervia.job.dto.ApplicationUpdateRequestDTO;
import com.icodian.careervia.job.dto.ApplicationUpdateResponseDTO;
import com.icodian.careervia.job.dto.JobApplicationResponseDTO;
import com.icodian.careervia.job.dto.UserApplicationResponseDTO;

public interface ApplicationService {

	ApplicationResponseDTO createApplication(ApplicationRequestDTO request);

	List<JobApplicationResponseDTO> getApplicationByJobId(Long jobId);
	
	List<UserApplicationResponseDTO> getApplicationByUserId(Long userId);

	ApplicationUpdateResponseDTO updateApplication(Long applicationId, ApplicationUpdateRequestDTO request);

	ApplicationStatusResponseDTO withdrawApplication(Long applicationId, Long userId);

}
