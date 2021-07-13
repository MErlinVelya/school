package com.veresklia.school;

import com.veresklia.entity.Course;
import com.veresklia.entity.Group;
import com.veresklia.entity.Student;
import com.veresklia.school.dao.CoursesDao;
import com.veresklia.school.dao.EnrollmentsDao;
import com.veresklia.school.dao.GroupDao;
import com.veresklia.school.dao.StudentDao;
import com.veresklia.school.view.ViewProvider;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class FrontControllerTest {

    @Mock
    private CoursesDao coursesDao;

    @Mock
    private EnrollmentsDao enrollmentsDao;

    @Mock
    private GroupDao groupDao;

    @Mock
    private StudentDao studentDao;

    @Mock
    private ViewProvider viewProvider;

    @InjectMocks
    private FrontController frontController;

    @Test
    void runShouldEnvokeFilterGroupsByStudentsNumber() throws SQLException {
        List<Group> expected = new ArrayList<>();
        when(viewProvider.readString()).thenReturn("a","exit");
        when(viewProvider.readInt()).thenReturn(1);

        when(groupDao.findGroupsByStudentsNumber(1)).thenReturn(anyList());
        List<Group> actual = groupDao.findGroupsByStudentsNumber(1);
        frontController.run();
        MatcherAssert.assertThat(actual, Matchers.is(expected));
        verify(viewProvider,times(2)).print(anyString());
    }

    @Test
    void runShouldEnvokeFilterStudentsByCourseName() throws SQLException {
        String expected = "[]";
        when(viewProvider.readString()).thenReturn("b","exit");
        when(studentDao.findStudentsByCourseName(anyString())).thenReturn(new LinkedList<>());
        String actual = studentDao.findStudentsByCourseName(anyString()).toString();
        frontController.run();
        MatcherAssert.assertThat(actual, Matchers.is(expected));
        verify(viewProvider,times(2)).print(anyString());
    }

    @Test
    void runShouldEnvokeInputNewStudent() throws SQLException {
        when(viewProvider.readString()).thenReturn("c","", "", "exit");
        frontController.run();
        verify(studentDao, times(1)).save(any());
        verify(viewProvider,times(3)).print(anyString());
    }

    @Test
    void runShouldEnvokeDeleteStudentById() throws SQLException {
        when(viewProvider.readString()).thenReturn("d", "exit");
        when(viewProvider.readInt()).thenReturn(1);
        frontController.run();
        verify(studentDao, times(1)).deleteById(anyInt());
        verify(viewProvider,times(2)).print(anyString());
    }

    @Test
    void runShouldEnvokeAddStudentToCourse() throws SQLException {
        List<Student> students = new ArrayList<>();
        List<Course> courses = new ArrayList<>();
        Course course = new Course("MATH", "MATH");
        Course course2 = new Course("MATH", "MATH");
        course.setId(1);
        course.setId(2);
        courses.add(course);
        courses.add(course2);
        Student student = Student
            .builder()
            .withId(1)
            .withCourses(courses)
            .withGroup("1").
            withName("AAA")
            .withSurname("AAA")
            .build();
        Student student2 = Student
            .builder()
            .withId(1)
            .withCourses(courses)
            .withGroup("1")
            .withName("AAA")
            .withSurname("AAA")
            .build();
        students.add(student);
        students.add(student2);

        when(viewProvider.readString()).thenReturn("e", "exit");
        when(coursesDao.findAll()).thenReturn(courses);
        when(studentDao.findAll()).thenReturn(students);
        frontController.run();
        verify(enrollmentsDao, times(1)).courseToStudent(anyInt(), anyInt());
        verify(viewProvider,times(8)).print(anyString());
    }

    @Test
    void runShouldEnvokeRemoveCourseFromStudent() throws SQLException {
        List<Student> students = new ArrayList<>();
        Student student = Student.builder().withId(1).withGroup("1").
            withName("AAA").withSurname("AAA").build();
        Student student2 = Student.builder().withId(1).withGroup("1").
            withName("AAA").withSurname("AAA").build();
        students.add(student);
        students.add(student2);

        when(viewProvider.readString()).thenReturn("f", "exit");
        when(studentDao.findAll()).thenReturn(students);
        when(coursesDao.findStudentsCoursesByStudentId(anyInt())).thenReturn(new ArrayList<>());
        frontController.run();
        verify(enrollmentsDao, times(1)).deleteById(anyInt());
        verify(viewProvider,times(8)).print(anyString());
    }
}
