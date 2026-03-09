package com.icodian.careervia.job.dto;

import java.util.ArrayList;
import java.util.List;

import com.icodian.careervia.job.entity.constant.JobStatus;
import com.icodian.careervia.job.entity.constant.JobType;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateJobRequestDTO {
	
	private Long job_id;
	
	@Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
	private String job_title;
	
	@Size(min = 10, message = "Description must be at least 10 characters")
	private String description;
	
	private String location;
	
	List<String> required_skills = new ArrayList<>();
	
	private String salary;
	
	private String experience;
	
	private JobType job_type;
	
	private JobStatus job_status;	

}
