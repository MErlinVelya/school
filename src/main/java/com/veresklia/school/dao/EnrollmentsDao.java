package com.veresklia.school.dao;

import com.veresklia.entity.Student;

public interface EnrollmentsDao extends CrudDao<Student, Integer> {

    void courseToStudent(Integer student, Integer course);

    void courseFromStudent(Integer student, Integer course);

}
