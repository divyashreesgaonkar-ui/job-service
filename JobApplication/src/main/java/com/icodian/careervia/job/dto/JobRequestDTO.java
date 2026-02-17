package com.icodian.careervia.job.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.icodian.careervia.job.entity.constant.JobStatus;
import com.icodian.careervia.job.entity.constant.JobType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRequestDTO {

	@NotBlank(message = "Job title is required")
	@Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
	private String job_title;

	@NotBlank(message = "Job description is required")
	@Size(min = 10, message = "Description must be at least 10 characters")
	private String descriprtion;

	@NotBlank(message = "Location is required")
	private String location;

	@NotNull(message = "Experience level is required")
	private String experience;

	@NotNull(message = "Salary is required")
	private String salary;

	@NotNull(message = "Job type is required")
	private JobType job_type;

	List<String> required_skills = new ArrayList<>();

	private LocalDate posted_date;

	@NotBlank(message = "Company ID is requitred")
	private Long company_id;

	private JobStatus job_status;

}
