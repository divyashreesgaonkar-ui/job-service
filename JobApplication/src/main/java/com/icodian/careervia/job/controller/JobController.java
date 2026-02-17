package com.icodian.careervia.job.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icodian.careervia.job.dto.JobDetailResponseDTO;
import com.icodian.careervia.job.dto.JobListResponseDTO;
import com.icodian.careervia.job.dto.JobRequestDTO;
import com.icodian.careervia.job.dto.JobResponseDTO;
import com.icodian.careervia.job.entity.constant.JobStatus;
import com.icodian.careervia.job.entity.constant.UserRole;
import com.icodian.careervia.job.service.JobService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
@Slf4j
public class JobController {
	
	private final JobService jobService;
	
	@PostMapping
	public ResponseEntity<JobResponseDTO> createJob(@Valid @RequestBody JobRequestDTO request){
		
		log.info("Received request to create job: {}", request.getJob_title());
		
		JobResponseDTO response = jobService.createJob(request);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public ResponseEntity<List<JobListResponseDTO>> getAllJobs(
			@RequestHeader(value = "X-User-Role", defaultValue = "JOB_SEEKER") String userRoleHeader,
			@RequestParam(required = false) JobStatus status){
		
		log.info("Received request to fetch jobs with role: {}, status: {}", userRoleHeader, status);
		
		UserRole userRole;
		
		try {
			userRole = UserRole.valueOf(userRoleHeader.toUpperCase());
		}catch(IllegalArgumentException e){
			userRole = UserRole.JOB_SEEKER;
		}
		
		List<JobListResponseDTO> jobs = jobService.getAlljobs(userRole, status);
		return ResponseEntity.ok(jobs);
		
	}
	
	@GetMapping("/{job_id}")
	public ResponseEntity<JobDetailResponseDTO> getJobById(
			@PathVariable Long job_id,
	        @RequestHeader(value = "X-User-Role", defaultValue = "JOB_SEEKER") String userRoleHeader){
		
		
		log.info("Received request to fetch job details for Job ID: {}", job_id);
		
		UserRole userRole;
		
		try {
	        userRole = UserRole.valueOf(userRoleHeader.toUpperCase());
	    } catch (IllegalArgumentException e) {
	        userRole = UserRole.JOB_SEEKER; 
	    }
		
		JobDetailResponseDTO response = jobService.getJobById(job_id, userRole);
		return ResponseEntity.ok(response);
	}

}
