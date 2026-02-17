package com.icodian.careervia.job.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.icodian.careervia.job.entity.Job;
import com.icodian.careervia.job.entity.constant.JobStatus;

public interface JobRepository extends JpaRepository<Job, Long>{

	@Query("SELECT j FROM Job j WHERE"+
	"(:job_status IS NULL OR j.job_status = :job_status)")
	List<Job> findJobsByFilters(@Param("job_status") JobStatus jobStatus);
	
	Optional<Job> findById(Long job_id);

}
