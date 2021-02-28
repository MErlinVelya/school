package com.veresklia.school.dao;

import com.veresklia.entity.Course;
import com.veresklia.entity.Group;
import com.veresklia.entity.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DatabaseFiller {
    void createTables(String script);

    void fillTables(List<Group> groups, List<Course> courses, List<Student> students) throws SQLException;
}
