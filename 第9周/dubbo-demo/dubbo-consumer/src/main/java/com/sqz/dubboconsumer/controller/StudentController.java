package com.sqz.dubboconsumer.controller;

import com.sqz.tccdemo.api.StudentService;
import com.sqz.tccdemo.entry.Student;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 **/
@RestController
public class StudentController {

    @DubboReference(version = "1.0.0")
    StudentService studentService;

    @GetMapping("/student")
    public Student student(Integer id) {
        Student student = studentService.findStudentById(id);
        return student;
    }
}
