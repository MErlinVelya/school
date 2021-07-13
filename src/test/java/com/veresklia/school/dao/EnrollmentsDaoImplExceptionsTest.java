package com.veresklia.school.dao;

import com.veresklia.entity.Student;
import com.veresklia.school.dao.connector.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnrollmentsDaoImplExceptionsTest {
    @Mock
    DatabaseConnector databaseConnector;

    @InjectMocks
    EnrollmentsDaoImpl enrollmentsDaoImpl;

    @Test
    void courseToStudentShouldReturnWithExceptionWhenExceptionInConnection () throws SQLException {
            when(databaseConnector.connect()).thenThrow(new SQLException());

            assertThrows(RuntimeException.class, () -> enrollmentsDaoImpl.courseToStudent(1,1));
    }

    @Test
    void courseFromStudentShouldReturnWithExceptionWhenExceptionInConnection () throws SQLException {
        when(databaseConnector.connect()).thenThrow(new SQLException());

        assertThrows(RuntimeException.class, () -> enrollmentsDaoImpl.courseFromStudent(1,1));
    }

    @Test
    void saveShouldReturnWithExceptionWhenExceptionInConnection () throws SQLException {
        when(databaseConnector.connect()).thenThrow(new SQLException());
        Student student = Student.builder().withId(1).build();

        assertThrows(RuntimeException.class, () -> enrollmentsDaoImpl.save(student));
    }
}
