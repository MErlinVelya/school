package com.veresklia.school.dao;

import com.ibatis.common.jdbc.ScriptRunner;
import com.veresklia.entity.Course;
import com.veresklia.school.dao.connector.DatabaseConnector;
import com.veresklia.school.dao.connector.DatabaseConnectorImpl;
import org.apache.ibatis.io.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CrudDaoImplTest {
    private final DatabaseConnector databaseConnector =
        new DatabaseConnectorImpl("src/test/resources/connection_mac_test.properties");
    final CoursesDao coursesDao = new CoursesDaoImpl(databaseConnector);

    @BeforeEach
    private void init(){
        UtilityBeforeEach.beforeEach(databaseConnector);
    }

    @Test
    void saveShouldReturnIntegerWhenProvidedEntity (){
        Course course = new Course("BBB", "BBB");
        Integer expected = 3;

        Integer actual = coursesDao.save(course);
        assertThat(actual, is(expected));
    }

    @Test
    void findByIdShouldReturnEntityWhenProvidedInteger(){
        String expectedCourseName = "MATH";
        final CoursesDao coursesDao = new CoursesDaoImpl(databaseConnector);
        Optional<Course> actualCourse = coursesDao.findById(1);

        assertThat(actualCourse.get().getCourseName(), is(expectedCourseName));
    }

    @Test
    void deleteByIdShouldDeleteRecordWhenProvidedId(){
        Integer toDelete = 1;
        Optional<Course> expected = Optional.empty();
        coursesDao.deleteById(toDelete);

        Optional<Course> actualCourse = coursesDao.findById(toDelete);
        assertThat(actualCourse, is(expected));
    }

    @Test
    void findAllShouldReturnListOfEntitiesWhenCalled(){
        List<Course> expected= new ArrayList<>();
        Course course1 = new Course("MATH", "MATH");
        course1.setId(1);
        Course course2 = new Course("DANCE", "DANCE");
        course2.setId(2);
        expected.add(course1);
        expected.add(course2);

        List<Course> coursesActual = coursesDao.findAll();
        assertThat(coursesActual, is(expected));
    }

    @Test
    void findAllShouldReturnListOfEntitiesWhenCalledWithIntegerParameters(){
        List<Course> expected= new ArrayList<>();
        Course course1 = new Course("MATH", "MATH");
        course1.setId(1);
        Course course2 = new Course("DANCE", "DANCE");
        course2.setId(2);
        expected.add(course1);
        expected.add(course2);

        List<Course> coursesActual = coursesDao.findAll(1,2);
        assertThat(coursesActual, is(expected));
    }

    @Test
    void updateUpdatingDatabaseWhenProvidedEntity(){
        Course course = new Course("CCC", "CCC");
        course.setId(1);
        Optional<Course> expected = Optional.of(course);

        coursesDao.update(course);

        Optional<Course> actual = coursesDao.findById(1);

        assertThat(actual, is(expected));
    }
}
