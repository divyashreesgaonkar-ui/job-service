package com.icodian.careervia.job.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import com.icodian.careervia.job.dto.UpdateJobRequestDTO;
import com.icodian.careervia.job.entity.constant.JobStatus;
import com.icodian.careervia.job.entity.constant.JobType;
import com.icodian.careervia.job.entity.constant.UserRole;
import com.icodian.careervia.job.service.JobService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/jobs")
@Slf4j
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@PostMapping
	public ResponseEntity<JobResponseDTO> createJob(@RequestBody JobRequestDTO request){
		
		log.info("Received request to create job: {}", request.getJob_title());
		
		JobResponseDTO response = jobService.createJob(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
	
	
/*	@GetMapping
	public ResponseEntity<List<JobListResponseDTO>> getAllJobs(
			@RequestHeader(value = "X-User-Role", defaultValue = "JOB_SEEKER") String userRoleHeader,
			@RequestParam(required = false) JobStatus status,
			@RequestParam String location,
			@RequestParam String company_name,
			@RequestParam JobType jobType,
			@RequestParam String experience){
		
		log.info("Received request to fetch jobs with role: {}, status: {}", userRoleHeader, status);
		
		UserRole userRole;
		
		try {
			userRole = UserRole.valueOf(userRoleHeader.toUpperCase());
		}catch(IllegalArgumentException e){
			userRole = UserRole.JOB_SEEKER;
		}
		
		List<JobListResponseDTO> jobs = jobService.getAlljobs(userRole, status, location, company_name, jobType, experience);
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
	
	@PatchMapping("/{jobId}")
	public ResponseEntity<JobResponseDTO> updateJob(
	        @PathVariable Long jobId,
	        @Valid @RequestBody UpdateJobRequestDTO request) {

	    log.info("HR requested update for Job ID: {}", jobId);
	    JobResponseDTO response = jobService.updateJob(jobId, request);
	    return ResponseEntity.ok(response);
	}
	*/

}
