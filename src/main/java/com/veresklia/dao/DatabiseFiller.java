package com.veresklia.dao;

import com.veresklia.domain.Group;
import com.veresklia.domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabiseFiller {

    public void createTables(Connection connection) {
        String createTables = new StringBuilder()
            .append("DROP TABLE IF EXISTS groups, cources, students, courses, enrollments;")

            .append("CREATE TABLE groups (group_id SERIAL PRIMARY KEY, group_name varchar);")

            .append("CREATE TABLE students (student_id SERIAL PRIMARY KEY, group_id int, first_name varchar, last_name varchar, ")
            .append("FOREIGN KEY (group_id) REFERENCES groups(group_id) ON DELETE SET NULL);")

            .append("CREATE TABLE courses (course_id SERIAL PRIMARY KEY, course_name varchar, course_description varchar);")

            .append("CREATE TABLE enrollments (enrollment_id SERIAL PRIMARY KEY, course_id int NOT NULL, student_id int NOT NULL, ")
            .append("FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE, ")
            .append("FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE);")
            .toString();

        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(createTables);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void fillTables(Connection connection, Group[] groups, String[] courses, List<Student> students) throws SQLException {
        String fillGroups = "INSERT INTO groups (group_name) VALUES(?)";
        String fillCourses = "INSERT INTO courses (course_name, course_description) VALUES (?, ?)";
        String fillStudents = "INSERT INTO students (group_id, first_name, last_name) VALUES ((SELECT group_id FROM groups " +
            "WHERE group_name=?), ?, ?)";
        String fillEnrollments = "INSERT INTO enrollments (course_id, student_id) VALUES ((SELECT course_id FROM courses " +
            "WHERE course_name=?), (SELECT student_id FROM students WHERE first_name=? AND last_name=? LIMIT 1))";


        for (Group group : groups) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(fillGroups)) {
                preparedStatement.setString(1, group.group);
                preparedStatement.execute();
            }
        }

        for (String course : courses) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(fillCourses)) {
                preparedStatement.setString(1, course);
                preparedStatement.setString(2, course);
                preparedStatement.execute();
            }
        }

        for (Student student : students) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(fillStudents)) {
                preparedStatement.setString(1, student.group);
                preparedStatement.setString(2, student.name);
                preparedStatement.setString(3, student.surname);
                preparedStatement.execute();
            }
        }

        for (Student student : students) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(fillEnrollments)) {
                for (String course : student.courses) {
                    preparedStatement.setString(1, course);
                    preparedStatement.setString(2, student.name);
                    preparedStatement.setString(3, student.surname);
                    preparedStatement.execute();
                }
            }
        }
    }
}
