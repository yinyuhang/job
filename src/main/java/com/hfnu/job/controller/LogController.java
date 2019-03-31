package com.hfnu.job.controller;

import com.hfnu.job.pojo.Log;
import com.hfnu.job.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@RestController
public class LogController {
    @Autowired
    LogRepository logRepository;

    @GetMapping("/logs")
    Page<Log> search(int pageIndex, int pageSize
    ) {
        Specification<Log> specification = (Root<Log> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return logRepository.findAll(specification, new QPageRequest(pageIndex, pageSize));
    }
}
