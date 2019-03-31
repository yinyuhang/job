package com.hfnu.job.controller;

import com.hfnu.job.pojo.Student;
import com.hfnu.job.repository.StudentRepository;
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
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/students")
    public Page<Student> search(int pageIndex, int pageSize
            , @RequestParam(required = false) Date admissionTime
            , @RequestParam(required = false) java.lang.String major
            , @RequestParam(required = false) java.lang.String name
    ) {
        Specification<Student> specification = (Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (null != admissionTime) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("admissionTime"), admissionTime));
            }
            if (null != major && !major.isEmpty()) {
                predicates.add(cb.equal(root.get("major"), major));
            }
            if (null != name && !name.isEmpty()) {
                predicates.add(cb.like(root.get("name"), "%" + name + "%"));
            }
            predicates.add(cb.notEqual(root.get("beDeleted"), true));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return studentRepository.findAll(specification, new QPageRequest(pageIndex, pageSize));
    }

    @PostMapping("/student")
    public Student add(Student student) {
        student.setBeDeleted(false);
        addOneDay(student);
        return studentRepository.save(student);
    }

    @PutMapping("/student")
    public void update(Student student) {
        studentRepository.findById(student.getId()).ifPresent(stu -> {
            addOneDay(student);
            studentRepository.save(student);
        });
    }

    private void addOneDay(Student student) {
        if (student == null) return;
        Date admissionTime = student.getAdmissionTime();
        long time;
        if (admissionTime != null) {
            time = admissionTime.getTime();
            time += 24 * 60 * 60 * 1000;
            admissionTime.setTime(time);
            student.setAdmissionTime(admissionTime);
        }
        Date birthday = student.getBirthday();
        if (birthday != null) {
            time = birthday.getTime();
            time += 24 * 60 * 60 * 1000;
            birthday.setTime(time);
            student.setBirthday(birthday);
        }
    }
    @DeleteMapping("/student/{id}")
    public void delete(@PathVariable String id) {
        studentRepository.findById(id).ifPresent(student -> {
            student.setBeDeleted(true);
            studentRepository.save(student);
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
