package com.veresklia.school.dao;

import com.veresklia.entity.Course;
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
class CrudDaoImplExceptionsTest {
    @Mock
    DatabaseConnector databaseConnector;

    @InjectMocks
    CoursesDaoImpl coursesDaoImpl;

    @Test
    void saveShouldReturnWithExceptionWhenExceptionInConnection () throws SQLException {
        when(databaseConnector.connect()).thenThrow(new SQLException());
        Course course = new Course("BBB", "BBB");

        assertThrows(RuntimeException.class, () -> coursesDaoImpl.save(course));
    }

    @Test
    void findByIdShouldReturnWithExceptionWhenExceptionInConnection() throws SQLException {
        when(databaseConnector.connect()).thenThrow(new SQLException());

        assertThrows(RuntimeException.class, () -> coursesDaoImpl.findById(1));
    }

    @Test
    void deleteByIdShouldReturnWithExceptionWhenExceptionInConnection() throws SQLException {
        when(databaseConnector.connect()).thenThrow(new SQLException());

        assertThrows(RuntimeException.class, () -> coursesDaoImpl.deleteById(1));
    }

    @Test
    void findAllShouldReturnWithExceptionWhenExceptionInConnection() throws SQLException {
        when(databaseConnector.connect()).thenThrow(new SQLException());

        assertThrows(RuntimeException.class, () -> coursesDaoImpl.findAll());
    }

    @Test
    void findAllWithIntegerParametersSShouldReturnWithExceptionWhenExceptionInConnection() throws SQLException {
        when(databaseConnector.connect()).thenThrow(new SQLException());

        assertThrows(RuntimeException.class, () -> coursesDaoImpl.findAll(1,1));
    }

    @Test
    void updateShouldReturnWithExceptionWhenExceptionInConnection() throws SQLException {
        when(databaseConnector.connect()).thenThrow(new SQLException());
        Course course = new Course("BBB", "BBB");

        assertThrows(RuntimeException.class, () -> coursesDaoImpl.update(course));
    }
}
