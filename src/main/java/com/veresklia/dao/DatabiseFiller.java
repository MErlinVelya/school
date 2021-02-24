package com.veresklia.dao;

import com.veresklia.domain.Group;
import com.veresklia.domain.Student;

import java.sql.*;
import java.util.List;

public class DatabiseFiller {

    public void createTables(Connection connection) {
        String createTables = new StringBuilder()
            .append("DROP TABLE IF EXISTS groups, students, courses, enrollments;")

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
                "WHERE course_name=?), (SELECT student_id FROM students WHERE student_id = ?))";


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
            try (PreparedStatement preparedStatement = connection.prepareStatement(fillStudents, Statement.RETURN_GENERATED_KEYS)) {
                int studentId = -1;
                preparedStatement.setString(1, student.group);
                preparedStatement.setString(2, student.name);
                preparedStatement.setString(3, student.surname);
                preparedStatement.execute();
                ResultSet studentIdSet = preparedStatement.getGeneratedKeys();
                while (studentIdSet.next()){
                    studentId = studentIdSet.getInt(1);
                }

                try (PreparedStatement preparedStatementEnrollments = connection.prepareStatement(fillEnrollments)) {
                    for (String course : student.courses) {
                        preparedStatementEnrollments.setString(1, course);
                        preparedStatementEnrollments.setInt(2, studentId);
                        preparedStatementEnrollments.execute();
                    }
                }


            }
        }
    }
}
