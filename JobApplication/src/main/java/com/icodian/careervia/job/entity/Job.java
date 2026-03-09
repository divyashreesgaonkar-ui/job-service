package com.icodian.careervia.job.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.icodian.careervia.job.entity.constant.ApprovalStatus;
import com.icodian.careervia.job.entity.constant.JobStatus;
import com.icodian.careervia.job.entity.constant.JobType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "job")
@NoArgsConstructor
@AllArgsConstructor
public class Job {

	@Id
	@Column(name = "job_id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long job_id;

	@Column(name = "job_title", nullable = false, length = 100, updatable = true)
	private String job_title;

	@Column(name = "description", nullable = false, columnDefinition = "TEXT")
	private String description;

	@Column(name = "location", nullable = false, length = 150)
	private String location;

	@Column(name = "experience", nullable = false)
	private String experience;

	@Column(name = "salary", length = 50)
	private String salary;

	@Enumerated(EnumType.STRING)
	@Column(name = "job_type", nullable = false)
	private JobType job_type = JobType.FULLTIME;

	@ElementCollection
	@CollectionTable(name = "job_skills", joinColumns = @JoinColumn(name = "job_id"))
	@Column(name = "skills", nullable = false, length = 100)
	List<String> skills = new ArrayList<>();

	@Column(name = "posted_date", nullable = false, updatable = false)
	private LocalDate posted_date;

	@Column(name = "company_id", nullable = false, updatable = false)
	private Long company_id;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private JobStatus job_status = JobStatus.OPENED;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "approvalStatus", nullable = false)
	private ApprovalStatus approvalStatus = ApprovalStatus.PENDING_APPROVAL;
	
	private String pendingChangesSummary; // Tracks what critical fields changed

	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Application> application;

}
