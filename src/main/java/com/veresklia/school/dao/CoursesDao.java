package com.veresklia.school.dao;

import com.veresklia.entity.Course;

import java.util.List;

public interface CoursesDao extends CrudDao<Course, Integer> {
    List<Course> findStudentsCoursesByStudentId(int id);
}
