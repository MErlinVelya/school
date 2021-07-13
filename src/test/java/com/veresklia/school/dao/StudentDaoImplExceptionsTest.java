package com.veresklia.school.dao;

import com.veresklia.entity.exception.DaoLevelException;
import com.veresklia.school.dao.connector.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentDaoImplExceptionsTest {

    @Mock
    DatabaseConnector databaseConnector;

    @Test
    void findStudentsByCourseNameShouldReturnWithExceptionWhenExceptionInConnection () throws SQLException {
        StudentDao studentDao = new StudentDaoImpl(databaseConnector);
        when(databaseConnector.connect()).thenThrow(new SQLException());

        assertThrows(DaoLevelException.class, () -> studentDao.findStudentsByCourseName("Course"));
    }
}
