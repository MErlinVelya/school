package com.veresklia.provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabiseFiller {

   public void createTables (Connection connection){
        String createTables = new StringBuilder()
            .append("DROP TABLE IF EXISTS groups, students, cources, courses, enrollments;")
            .append("CREATE TABLE groups (group_id int SERIAL PRIMARY KEY, group_name varchar[48]);")
            .append("CREATE TABLE students (student_id int SERIAL PRIMARY KEY, group_id int, first_name varchar[48], last_name varchar[48]);")
            .append("CREATE TABLE courses (course_id int SERIAL PRIMARY KEY, course_name varchar[48], course_description varchar[48]);")
            .append("CREATE TABLE enrollments (enrollment_id int SERIAL PRIMARY KEY, course_id int, student_id int);")
            .toString();

        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(createTables);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
