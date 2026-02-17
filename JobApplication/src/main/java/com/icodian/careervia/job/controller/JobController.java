package com.icodian.careervia.job.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icodian.careervia.job.dto.JobRequestDTO;
import com.icodian.careervia.job.dto.JobResponseDTO;
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

}
