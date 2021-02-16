package com.veresklia.provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabiseFiller {

   public void createTables (Connection connection){
        String createTables = new StringBuilder()
            .append("DROP TABLE IF EXISTS groups, students, courses, enrollments;")
            .append("CREATE TABLE groups (group_id SERIAL PRIMARY KEY, group_name varchar[48]);")
            .append("CREATE TABLE students (student_id SERIAL PRIMARY KEY, group_id int, first_name varchar[48], last_name varchar[48]);")
            .append("CREATE TABLE courses (course_id SERIAL PRIMARY KEY, course_name varchar[48], course_description varchar[48]);")
            .append("CREATE TABLE enrollments (enrollment_id SERIAL PRIMARY KEY, course_id int, student_id int);")
            .toString();

        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(createTables);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void fillTables (Connection connection, String[] groups, String[] courses,  List<String[]> students) throws SQLException {
       String fillGroups = "INSERT INTO groups (group_name) VALUES(?)";

            for (String group : groups){
                try (PreparedStatement preparedStatement = connection.prepareStatement(fillGroups)) {
                    preparedStatement.setString(1, group);
                    preparedStatement.execute();
                }
            }


    }
}
