package com.icodian.careervia.job.entity;

import java.time.LocalDate;

import com.icodian.careervia.job.entity.constant.ApplicationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "application")
public class Application {

	@Id
	@Column(name = "appication_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long application_id;

	@Column(name = "user_id")
	private Long user_id;

	@Column(name = "job_id")
	private Long job_id;

	@Column(name = "resume_id", length = 200)
	private Long resume_id;

	@Column(name = "applied_date")
	private LocalDate applied_date;

	@Enumerated(EnumType.STRING)
	@Column(name = "application_status")
	private ApplicationStatus application_status = ApplicationStatus.APPLIED;

	@Column(name = "remarks", columnDefinition = "TEXT")
	private String remarks;

	@ManyToOne(fetch = FetchType.LAZY)
	private Job job;

}
