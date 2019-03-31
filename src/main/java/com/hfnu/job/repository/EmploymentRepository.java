package com.hfnu.job.repository;

import com.hfnu.job.pojo.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmploymentRepository extends JpaRepository<Employment, String>, JpaSpecificationExecutor<Employment> {
}