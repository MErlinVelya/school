package com.veresklia.school.dao;

import com.veresklia.entity.exception.DaoLevelException;
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
class CoursesDaoImplExceptionsTest {
    @Mock
    DatabaseConnector databaseConnector;

    @InjectMocks
    CoursesDaoImpl coursesDaoImpl;

    @Test
    void findStudentsCoursesByStudentIdShouldReturnWithExceptionWhenExceptionInConnection () throws SQLException {
        when(databaseConnector.connect()).thenThrow(new SQLException());

        assertThrows(DaoLevelException.class, () -> coursesDaoImpl.findStudentsCoursesByStudentId(1));
    }
}
