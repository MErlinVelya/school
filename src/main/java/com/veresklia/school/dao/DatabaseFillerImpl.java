package com.veresklia.school.dao;

import com.ibatis.common.jdbc.ScriptRunner;
import com.veresklia.entity.Course;
import com.veresklia.entity.Group;
import com.veresklia.entity.Student;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;
import java.io.Reader;

import java.util.List;

import com.veresklia.school.dao.connector.DatabaseConnector;
import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseFillerImpl implements DatabaseFiller {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseFillerImpl.class);

    private final StudentDao studentDao;
    private final CoursesDao coursesDao;
    private final GroupDao groupDao;
    private final EnrollmentsDao enrollmentsDao;
    private final DatabaseConnector databaseConnector;

    public DatabaseFillerImpl (StudentDao studentDao, CoursesDao coursesDao, GroupDao groupDao,
                               EnrollmentsDao enrollmentsDao, DatabaseConnector databaseConnector){
        this.studentDao = studentDao;
        this.coursesDao = coursesDao;
        this.groupDao = groupDao;
        this.enrollmentsDao = enrollmentsDao;
        this.databaseConnector = databaseConnector;
    }

    @Override
    public void createTables(String script) {
        try {
            Reader reader = Resources.getResourceAsReader(script);
            ScriptRunner runner = new ScriptRunner(databaseConnector.connect(), false, true);
            runner.runScript(reader);
        } catch (IOException | SQLException e) {
            LOGGER.error("Error ", e);
        }
    }

    @Override
    public void fillTables(List<Group> groups, List<Course> courses, List<Student> students) throws SQLException {

        for (Group group : groups) {
           groupDao.save(group);
        }

        for (Course course : courses) {
            coursesDao.save(course);
        }

        for (Student student : students) {
            student.setId(studentDao.save(student));
        }

        for (Student student : students){
            enrollmentsDao.save(student);
        }
    }
}
