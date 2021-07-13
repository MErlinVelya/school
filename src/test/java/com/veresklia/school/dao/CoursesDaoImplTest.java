package com.veresklia.school.dao;

import com.ibatis.common.jdbc.ScriptRunner;
import com.veresklia.entity.Course;
import com.veresklia.school.dao.connector.DatabaseConnector;
import com.veresklia.school.dao.connector.DatabaseConnectorImpl;
import org.apache.ibatis.io.Resources;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CoursesDaoImplTest {
    private final DatabaseConnector databaseConnector =
        new DatabaseConnectorImpl("src/test/resources/connection_mac_test.properties");

    @BeforeEach
    private void init(){
        UtilityBeforeEach.beforeEach(databaseConnector);
    }

    @Test
    void findStudentsCoursesByStudentIdReturnWithListWhenProvidedInt () throws SQLException {
        List<Course> expected = new ArrayList<>();
        Course course = new Course("MATH", "MATH");
        course.setId(1);
        expected.add(course);
        CoursesDao coursesDao = new CoursesDaoImpl(databaseConnector);
        List<Course> actual = coursesDao.findStudentsCoursesByStudentId(1);
        assertThat(actual, is(expected));
    }
}
