package com.icodian.careervia.job.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.icodian.careervia.job.entity.constant.JobStatus;
import com.icodian.careervia.job.entity.constant.JobType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobListResponseDTO {
	
	private Long job_id;
	private String job_title;
	private String location;
	private String experience;
	private String salary;
	private JobType job_type;
	List<String> required_skills = new ArrayList<>();
	private LocalDate posted_date;
	private Long company_id;
	private JobStatus job_status;


}
