package com.veresklia.school.dao;

import com.veresklia.entity.exception.DaoLevelException;
import com.veresklia.school.dao.connector.DatabaseConnector;
import com.veresklia.school.dao.connector.DatabaseConnectorImpl;
import com.veresklia.entity.Course;
import com.veresklia.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnrollmentsDaoImpl extends CrudDaoImpl<Student, Integer> implements EnrollmentsDao {
    private static final String SAVE_QUERY = "INSERT INTO enrollments (course_id, student_id) VALUES ((SELECT course_id FROM courses " +
        "WHERE course_name = ?), ?)";
    private static final String FIND_BY_ID_QUERY = "select * from enrollments where enrollment_id = ?";
    private static final String FIND_ALL_QUERY = "select students.* from enrollments RIGHT JOIN students ON" +
        " enrollments.student_id = students.student_id";
    private static final String UPDATE_QUERY = "unsupported";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM enrollments WHERE enrollment_id = ?";
    private static final String SAVE_COURSE_TO_STUDENT = "INSERT INTO enrollments (course_id, student_id) VALUES (?, ?)";
    private static final String DELETE_COURSE_FROM_STUDENT = "DELETE from enrollments WHERE course_id = ? AND student_id = ?";

    public EnrollmentsDaoImpl(DatabaseConnector databaseConnector) {
        super(SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY, databaseConnector);
    }

    @Override
    public Integer save(Student entity) {
        try (Connection connection = databaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_QUERY)){
            connection.setAutoCommit(false);
            for (Course course : entity.courses) {
                preparedStatement.setString(1, course.getCourseName());
                preparedStatement.setInt(2, entity.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoLevelException("Insertion is failed", e);
        }
        return null;
    }

    public void courseToStudent(Integer student, Integer course) {
        try (Connection connection = databaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_COURSE_TO_STUDENT)) {
                preparedStatement.setInt(1, course);
                preparedStatement.setInt(2, student);
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoLevelException("Insertion is failed", e);
        }
    }

    public void courseFromStudent(Integer student, Integer course) {
        try (Connection connection = databaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COURSE_FROM_STUDENT)) {
            preparedStatement.setInt(1, course);
            preparedStatement.setInt(2, student);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoLevelException("Insertion is failed", e);
        }
    }

    @Override
    protected Student mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        System.out.println(resultSet.getString(1));
        System.out.println(resultSet.getString(2));
        System.out.println(resultSet.getString(3));
        System.out.println(resultSet.getString(4));
        return Student.builder()
            .withId(resultSet.getInt(1))
            .withName(resultSet.getString(3))
            .withSurname(resultSet.getString(4))
            .withGroup(resultSet.getString(2))
            .withCourses(new ArrayList<>())
            .build();
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Student entity){
        throw new DaoLevelException("Not used in current context", new SQLException());
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, Student entity){
        throw new DaoLevelException("Not used in current context", new SQLException());
    }
}
