package com.hfnu.job.controller;

import com.hfnu.job.other.Utils;
import com.hfnu.job.pojo.Employment;
import com.hfnu.job.repository.EmploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


@RestController
public class EmploymentController {
    @Autowired
    EmploymentRepository employmentRepository;

    @GetMapping("/employments")
    public Page<Employment> search(int pageIndex, int pageSize
            , @RequestParam(required = false) java.util.Date entryTime
            , @RequestParam(required = false) String jobPosition
    ) {
        Specification<Employment> specification = (Root<Employment> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (null != entryTime) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("entryTime"), entryTime));
            }
            if (null != jobPosition && !jobPosition.isEmpty()) {
                predicates.add(cb.equal(root.get("jobPosition"), jobPosition));
            }
            predicates.add(cb.notEqual(root.get("beDeleted"), true));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return employmentRepository.findAll(specification, new QPageRequest(pageIndex, pageSize));
    }

    @PostMapping("/employment")
    public Employment add(Employment employment) {
        employment.setEntryTime(Utils.addOneDay(employment.getEntryTime()));
        return employmentRepository.save(employment);
    }

    @PutMapping("/employment")
    public void update(Employment employment) {
        employmentRepository.findById(employment.getId()).ifPresent(e -> {
            employment.setEntryTime(Utils.addOneDay(employment.getEntryTime()));
            employmentRepository.save(employment);
        });
    }

    @DeleteMapping("/employment/{id}")
    public void delete(@PathVariable String id) {
        employmentRepository.deleteById(id);
        employmentRepository.findById(id).ifPresent(employment -> {
            employment.setBeDeleted(true);
            employmentRepository.save(employment);
        });
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(sdf, true, 10));
    }
}
