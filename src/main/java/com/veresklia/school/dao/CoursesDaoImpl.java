package com.veresklia.school.dao;

import com.veresklia.entity.Course;
import com.veresklia.entity.exception.DaoLevelException;
import com.veresklia.school.dao.connector.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoursesDaoImpl extends CrudDaoImpl<Course, Integer> implements CoursesDao {
    private static final String SAVE_QUERY = "INSERT INTO courses (course_name, course_description) VALUES (?, ?)";
    private static final String FIND_BY_ID_QUERY = "select course_id, course_name, course_description from courses where course_id = ?";
    private static final String FIND_ALL_QUERY = "select * from courses";
    private static final String UPDATE_QUERY = "UPDATE COURSES SET course_name = ?, course_description = ? WHERE course_id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM courses WHERE course_id = ?";
    private static final String FIND_STUDENTS_COURSES_BY_STUDENT_ID = "select enrollments.enrollment_id, course_name, course_description from enrollments inner" +
        " join courses on enrollments.course_id = courses.course_id where enrollments.student_id = ?";

    public CoursesDaoImpl (DatabaseConnector databaseConnector) {
    super(SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY,
        DELETE_BY_ID_QUERY, databaseConnector);
    }

    @Override
    protected Course mapResultSetToEntity(ResultSet resultSet) throws SQLException {

        Course course = new Course(resultSet.getString(2),resultSet.getString(3));
        course.setId(resultSet.getInt(1));

        return course;
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Course entity) throws SQLException {
        preparedStatement.setString(1, entity.getCourseName());
        preparedStatement.setString(2, entity.getCourseDescription());
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, Course entity) throws SQLException {
        preparedStatement.setString(1, entity.getCourseName());
        preparedStatement.setString(2, entity.getCourseDescription());
        preparedStatement.setInt(3, entity.getId());
    }

    @Override
    public List<Course> findStudentsCoursesByStudentId(int id){
        List coursesList = new ArrayList<Course>();
        try (Connection connection = databaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_STUDENTS_COURSES_BY_STUDENT_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet coursesNames = preparedStatement.executeQuery();
            while (coursesNames.next()) {
                coursesList.add(mapResultSetToEntity(coursesNames));
            }
        } catch (SQLException sqlException) {
            throw new DaoLevelException("found Students problem", sqlException);
        }

        return coursesList;
    }
}
