package com.hfnu.job.controller;

import com.hfnu.job.pojo.Company;
import com.hfnu.job.pojo.Student;
import com.hfnu.job.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;

    @GetMapping("/companys")
    public Page<Company> search(int pageIndex, int pageSize
            , @RequestParam(required = false) String name
            , @RequestParam(required = false) String location
            , @RequestParam(required = false) String industry
    ) {
        Specification<Company> specification = (Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (null != name && !name.isEmpty()) {
                predicates.add(cb.like(root.get("name"), "%" + name + "%"));
            }
            if (null != location && !location.isEmpty()) {
                predicates.add(cb.like(root.get("location"), location));
            }
            if (null != industry && !industry.isEmpty()) {
                predicates.add(cb.equal(root.get("industry"), industry));
            }
            predicates.add(cb.notEqual(root.get("beDeleted"), true));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return companyRepository.findAll(specification, new QPageRequest(pageIndex, pageSize));
    }

    @PostMapping("/company")
    public Company add(Company company) {
        return companyRepository.save(company);
    }

    @PutMapping("/company")
    public void update(Company company) {
        companyRepository.findById(company.getId()).ifPresent(entity -> {
            companyRepository.save(company);
        });
    }

    @DeleteMapping("/company/{id}")
    public void delete(@PathVariable String id) {
        companyRepository.deleteById(id);
        companyRepository.findById(id).ifPresent(company -> {
            company.setBeDeleted(true);
            companyRepository.save(company);
        });
    }

}
