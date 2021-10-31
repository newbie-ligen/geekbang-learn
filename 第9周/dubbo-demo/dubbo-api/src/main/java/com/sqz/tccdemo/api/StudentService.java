package com.sqz.tccdemo.api;

import com.sqz.tccdemo.entry.Student;

/**
 *
 **/
public interface StudentService {
    //根据学生id查询学生信息
    public Student findStudentById(int id);
}
