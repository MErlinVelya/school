package com.veresklia.school.dao;

import com.ibatis.common.jdbc.ScriptRunner;
import com.veresklia.entity.Course;
import com.veresklia.entity.Student;
import com.veresklia.entity.exception.DaoLevelException;
import com.veresklia.school.dao.connector.DatabaseConnector;
import com.veresklia.school.dao.connector.DatabaseConnectorImpl;
import org.apache.ibatis.io.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnrollmentsDaoImplTest {
    private final DatabaseConnector databaseConnector =
        new DatabaseConnectorImpl("src/test/resources/connection_mac_test.properties");

        @BeforeEach
        private void init(){
            UtilityBeforeEach.beforeEach(databaseConnector);
        }

        @Test
        void courseToStudentAddRecordToDatabaseWhenProvidedInt () throws SQLException {
            EnrollmentsDao enrollmentsDao = new EnrollmentsDaoImpl(databaseConnector);
            int expected = 44;
            enrollmentsDao.courseToStudent(expected,expected);
            int actual = 0;

            try(Connection connection = databaseConnector.connect();
                PreparedStatement preparedStatement = connection.prepareStatement("Select student_id, course_id " +
                    "FROM Enrollments WHERE student_id = 44")){
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    actual = rs.getInt(1);
                }
            }
            assertThat(actual, is(expected));
        }

    @Test
    void courseFromStudentRemoveRecordFromDatabaseWhenProvidedInt () throws SQLException {
        EnrollmentsDao enrollmentsDao = new EnrollmentsDaoImpl(databaseConnector);
        int actual = 0;
        int expected = 0;
        int testingValue = 44;

        try(Connection connection = databaseConnector.connect();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Enrollments (student_id, course_id) " +
                "VALUES (" + testingValue+ ", " + testingValue + ")")){
            preparedStatement.executeUpdate();
        }

        enrollmentsDao.courseFromStudent(testingValue,testingValue);

        try(Connection connection = databaseConnector.connect();
            PreparedStatement preparedStatement = connection.prepareStatement("Select student_id from enrollments " +
                "where student_id = " + testingValue +" ")){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                actual = rs.getInt(1);
            }
        }
        assertThat(actual, is(expected));
    }

    @Test
    void saveAddRecordToDatabaseAndReturnNullWhenProvidedStudent () throws SQLException {
        EnrollmentsDao enrollmentsDao = new EnrollmentsDaoImpl(databaseConnector);
        Integer expected = null;
        List<Course> courses= new ArrayList<>();
        Course course = new Course("AA","AA");
        courses.add(course);
        Student student = Student.builder().withCourses(courses).withId(1).build();
        Integer actual = 3;

        actual = enrollmentsDao.save(student);

        assertThat(actual, is(expected));
    }

    @Test
    void findAllShoulReturnListOfStudentsWhenCalled(){
        EnrollmentsDao enrollmentsDao = new EnrollmentsDaoImpl(databaseConnector);
        List<Student> expected = new ArrayList<>();
        Student student1 = Student.builder()
            .withId(1)
            .withName("AAA")
            .withSurname("AAA")
            .build();
        expected.add(student1);

        List<Student> actual = enrollmentsDao.findAll();
        assertThat(actual.get(0).getId(), is(expected.get(0).getId()));
    }

    @Test
    void insertShouldThrowExceptionWhenCalled() throws SQLException {
        EnrollmentsDaoImpl enrollmentsDaoImpl = new EnrollmentsDaoImpl(databaseConnector);
        Student student1 = Student.builder()
            .withId(1)
            .withName("AAA")
            .withSurname("AAA")
            .build();
        PreparedStatement preparedStatement = databaseConnector.connect().prepareStatement("Select * from students");
        Exception exception = assertThrows(DaoLevelException.class, () ->
            enrollmentsDaoImpl.insert(preparedStatement, student1));
        Throwable expected = new DaoLevelException("Not used in current context", new SQLException());

        assertThat(exception.toString(), is(expected.toString()));
    }

    @Test
    void updateValueThrowExceptionWhenCalled() throws SQLException {
        EnrollmentsDaoImpl enrollmentsDaoImpl = new EnrollmentsDaoImpl(databaseConnector);
        Student student1 = Student.builder()
            .withId(1)
            .withName("AAA")
            .withSurname("AAA")
            .build();
        PreparedStatement preparedStatement = databaseConnector.connect().prepareStatement("Select * from students");
        Exception exception = assertThrows(DaoLevelException.class, () ->
            enrollmentsDaoImpl.updateValues(preparedStatement, student1));
        Throwable expected = new DaoLevelException("Not used in current context", new SQLException());

        assertThat(exception.toString(), is(expected.toString()));
    }
}
