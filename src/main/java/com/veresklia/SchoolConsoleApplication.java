package com.veresklia;

import com.veresklia.entity.Course;
import com.veresklia.entity.Student;
import com.veresklia.entity.provider.CoursesProviderImpl;
import com.veresklia.school.FrontController;
import com.veresklia.school.dao.DatabaseFiller;
import com.veresklia.school.dao.DatabaseFillerImpl;
import com.veresklia.school.dao.EnrollmentsDao;
import com.veresklia.school.dao.EnrollmentsDaoImpl;
import com.veresklia.school.dao.StudentDao;
import com.veresklia.school.dao.StudentDaoImpl;
import com.veresklia.school.dao.GroupDao;
import com.veresklia.school.dao.GroupDaoImpl;
import com.veresklia.school.dao.CoursesDao;
import com.veresklia.school.dao.CoursesDaoImpl;
import com.veresklia.school.dao.connector.DatabaseConnectorImpl;
import com.veresklia.school.view.ViewProvider;
import com.veresklia.school.view.ViewProviderImpl;
import com.veresklia.school.dao.connector.DatabaseConnector;
import com.veresklia.entity.Group;
import com.veresklia.entity.provider.ContentProvider;
import com.veresklia.entity.provider.ContentProviderImpl;
import com.veresklia.entity.provider.CoursesProvider;

import java.sql.SQLException;
import java.util.List;

public class SchoolConsoleApplication {

    public static void main(String[] args) throws SQLException {
        ContentProvider contentProvider = new ContentProviderImpl();
        DatabaseConnector databaseConnector = new DatabaseConnectorImpl("src/main/resources/connection_mac.properties");
        ViewProvider viewProvider = new ViewProviderImpl();
        StudentDao studentDao = new StudentDaoImpl(databaseConnector);
        CoursesDao coursesDao = new CoursesDaoImpl(databaseConnector);
        GroupDao groupDao = new GroupDaoImpl(databaseConnector);
        EnrollmentsDao enrollmentsDao = new EnrollmentsDaoImpl(databaseConnector);
        List<Group> groups = contentProvider.generateGroups(10, 10, 30);
        DatabaseFiller databaseFiller = new DatabaseFillerImpl(studentDao, coursesDao, groupDao, enrollmentsDao, databaseConnector);
        FrontController frontController = new FrontController(coursesDao, enrollmentsDao, groupDao, studentDao,
                viewProvider);

        CoursesProvider coursesProvider = new CoursesProviderImpl();
        databaseFiller.createTables("tablesCreate.sql");
        databaseFiller.fillTables(groups, coursesProvider.generateCoursesArray(), contentProvider.generateStudentsAllIn(groups,
            1, 3, 200));
        frontController.run();
    }
}
