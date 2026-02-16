package com.icodian.careervia.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icodian.careervia.job.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long>{

}
