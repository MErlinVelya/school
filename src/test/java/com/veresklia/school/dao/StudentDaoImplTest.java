package com.veresklia.school.dao;

import com.ibatis.common.jdbc.ScriptRunner;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentDaoImplTest {
    private final DatabaseConnector databaseConnector =
        new DatabaseConnectorImpl("src/test/resources/connection_mac_test.properties");

    @BeforeEach
    private void init(){
        UtilityBeforeEach.beforeEach(databaseConnector);
    }

    @Test
    void findStudentsByCourseNameReturnWithListWhenProvidedInt () throws SQLException {
        List<Student> expected = new ArrayList<>();
        Student student = Student
            .builder()
            .withId(1)
            .withGroup("1")
            .withName("AAA")
            .withSurname("AAA")
            .build();
        expected.add(student);
        System.out.println(student);
        StudentDao studentDao = new StudentDaoImpl(databaseConnector);
        List<Student> actual = studentDao.findStudentsByCourseName("MATH");
        assertThat(actual.toString(), is(expected.toString()));
    }

    @Test
    void updateValueShouldThrowExceptionWhenCalled() throws SQLException {
        StudentDaoImpl studentDaoImpl = new StudentDaoImpl(databaseConnector);
        Student student1 = Student
            .builder()
            .withId(1)
            .withName("AAA")
            .withSurname("AAA")
            .build();
        PreparedStatement preparedStatement = databaseConnector.connect().prepareStatement("Select * from students");
        Exception exception = assertThrows(DaoLevelException.class, () ->
            studentDaoImpl.updateValues(preparedStatement, student1));
        Throwable expected = new DaoLevelException("Not used in current context", new SQLException());

        assertThat(exception.toString(), is(expected.toString()));
    }

    @Test
    void insertShouldAddDataToPreparedStatementWhenSaveCalled() throws SQLException {
        final StudentDaoImpl studentDaoIml = new StudentDaoImpl(databaseConnector);
        Student student1 = Student
            .builder()
            .withName("CCC")
            .withSurname("CCC")
            .build();
        Optional<Student> expected= Optional.of(student1);

        studentDaoIml.save(student1);

        List<Student> students = studentDaoIml.findAll();
        Student actual = students.get(2);

        assertThat(actual.getName(), is(expected.get().getName()));
    }
}
