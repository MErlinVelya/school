package com.veresklia.school.dao;

import com.veresklia.entity.Student;
import com.veresklia.entity.exception.DaoLevelException;
import com.veresklia.school.dao.connector.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class StudentDaoImpl extends CrudDaoImpl<Student, Integer> implements StudentDao {
    private static final String SAVE_QUERY = "INSERT INTO students (group_id, first_name, last_name) VALUES ((SELECT group_id FROM groups " +
        "WHERE group_name=?), ?, ?)";
    private static final String FIND_BY_ID_QUERY = "select student_id, first_name, last_name from students where student_id = ?";
    private static final String FIND_ALL_QUERY = "select * from students";
    private static final String UPDATE_QUERY = "unsupported";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM students WHERE student_id = ?";
    private static final String FIND_STUDENTS_BY_COURSE_NAME = "SELECT students.student_id, students.group_id, students.first_name, students.last_name FROM " +
            "((students INNER JOIN enrollments ON students.student_id = enrollments.student_id) INNER JOIN courses ON " +
            "enrollments.course_id = courses.course_id) WHERE courses.course_name = ?";

    public StudentDaoImpl(DatabaseConnector databaseConnector) {
        super(SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY, databaseConnector);
    }

    @Override
    protected Student mapResultSetToEntity(ResultSet resultSet) throws SQLException {
            return Student.builder()
                    .withId(resultSet.getInt(1))
                    .withName(resultSet.getString(3))
                    .withSurname(resultSet.getString(4))
                    .withGroup(resultSet.getString(2))
                    .withCourses(new ArrayList<>())
                    .build();
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, Student entity) throws SQLException {
        throw new DaoLevelException("Not used in current context", new SQLException());
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Student entity) throws SQLException {
        preparedStatement.setString(1, entity.getGroup());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setString(3, entity.getSurname());
    }

    @Override
    public List<Student> findStudentsByCourseName(String courseName){
        List<Student> students = new ArrayList<>();
        try (Connection connection = databaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_STUDENTS_BY_COURSE_NAME)) {
            preparedStatement.setString(1, courseName);
            ResultSet studentNames = preparedStatement.executeQuery();
            while (studentNames.next()) {
                students.add(mapResultSetToEntity(studentNames));
            }

        } catch (SQLException sqlException) {
            throw new DaoLevelException("FindStudents" , sqlException);
        }

        return students;
    }
}
