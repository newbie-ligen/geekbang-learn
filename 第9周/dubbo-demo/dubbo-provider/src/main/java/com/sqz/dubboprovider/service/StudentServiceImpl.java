package com.sqz.dubboprovider.service;

import com.sqz.tccdemo.api.StudentService;
import com.sqz.tccdemo.entry.Student;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0", interfaceClass = StudentService.class)
public class StudentServiceImpl implements StudentService {

    @Override
    public Student findStudentById(int id) {
        Student student = new Student();
        student.setId(1);
        student.setName("xxx");
        return student;
    }

}
