package com.hfnu.job.repository;

import com.hfnu.job.pojo.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LogRepository extends JpaRepository<Log, String>, JpaSpecificationExecutor<Log> {
}