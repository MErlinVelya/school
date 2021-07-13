package com.veresklia.school.dao;

import com.veresklia.entity.Student;

import java.util.List;

public interface StudentDao extends CrudDao<Student, Integer> {
    List<Student> findStudentsByCourseName(String courseName);
}
