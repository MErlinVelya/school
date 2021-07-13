package com.veresklia.school;

import com.veresklia.school.dao.CoursesDao;
import com.veresklia.school.dao.EnrollmentsDao;
import com.veresklia.school.dao.GroupDao;
import com.veresklia.school.dao.StudentDao;
import com.veresklia.school.view.ViewProvider;
import com.veresklia.entity.Course;
import com.veresklia.entity.Student;

import java.sql.SQLException;
import java.util.List;

public class FrontController {
    private static final String A_OPTION = "a";
    private static final String B_OPTION = "b";
    private static final String C_OPTION = "c";
    private static final String D_OPTION = "d";
    private static final String E_OPTION = "e";
    private static final String F_OPTION = "f";
    private static final String EXIT_OPTION = "exit";

    private final CoursesDao coursesDao;
    private final EnrollmentsDao enrollmentsDao;
    private final GroupDao groupDao;
    private final StudentDao studentDao;
    private final ViewProvider viewProvider;

    public FrontController(CoursesDao coursesDao, EnrollmentsDao enrollmentsDao, GroupDao groupDao, StudentDao studentDao,
                           ViewProvider viewProvider) {
        this.coursesDao = coursesDao;
        this.enrollmentsDao = enrollmentsDao;
        this.groupDao = groupDao;
        this.studentDao = studentDao;
        this.viewProvider = viewProvider;
    }

    public void run() throws SQLException {
        boolean isWork = true;
        while (isWork) {
            viewProvider.print(viewProvider.getMenu());
            String choose = viewProvider.readString();

            switch (choose) {
                case (A_OPTION): {
                    viewProvider.print(filterGroupsByStudentsNumber());

                    break;
                }
                case (B_OPTION): {
                    viewProvider.print(filterStudentsByCourseName());

                    break;
                }

                case (C_OPTION): {
                    viewProvider.print(inputNewStudent());
                    break;
                }

                case (D_OPTION): {
                    viewProvider.print(deleteStudentById());

                    break;
                }

                case (E_OPTION): {
                    viewProvider.print(addStudentToCourse());

                    break;
                }

                case (F_OPTION): {
                    viewProvider.print((removeCourseFromStudent()));

                    break;
                }

                case (EXIT_OPTION): {
                    isWork = false;

                    break;
                }

                default: {
                    viewProvider.print("Smth goes wrong");
                }
            }
        }
    }

    private String filterGroupsByStudentsNumber () throws SQLException {
        viewProvider.print("Input student quantity to find:");
        return groupDao.findGroupsByStudentsNumber(viewProvider.readInt()).toString();
    }

    private String filterStudentsByCourseName () throws SQLException {
        viewProvider.print("Input course name:");
        String courseName = viewProvider.readString();
        String result = studentDao.findStudentsByCourseName(courseName).toString();
        return  result;
    }

    private String inputNewStudent (){
        viewProvider.print("Input student Name:");
        String studentName = viewProvider.readString();
        viewProvider.print("Input student last name:");
        String studentLastName = viewProvider.readString();
        Student student = Student.builder().withName(studentName).withSurname(studentLastName).build();
        studentDao.save(student);

        return ("Hopefully Ok");
    }

    private String deleteStudentById (){
        viewProvider.print("Input student ID:");
        int studentId = viewProvider.readInt();
        studentDao.deleteById(studentId);

        return ("Possibly deleted");
    }

    private String addStudentToCourse (){
        List<Course> courses = coursesDao.findAll();
        for (Course course : courses) {
            viewProvider.print(course.toString());
        }
        viewProvider.print("Choose course by ID");
        int courseId = viewProvider.readInt();

        viewProvider.print("Now choose student to whom course would be applied");
        List<Student> students = studentDao.findAll();
        for (Student student : students) {
            viewProvider.print(student.toString());
        }
        viewProvider.print("Choose student by ID");
        int studentId = viewProvider.readInt();

        enrollmentsDao.courseToStudent(studentId, courseId);

        return ("Hopefully Ok");
    }

    private String removeCourseFromStudent (){
        viewProvider.print("Choose student from which you want to remove courses");
        List<Student> students = studentDao.findAll();
        for (Student student : students) {
            viewProvider.print(student.toString());
        }
        viewProvider.print("Choose student by ID");
        int studentId = viewProvider.readInt();

        viewProvider.print("List of courses which this student have");
        viewProvider.print(coursesDao.findStudentsCoursesByStudentId(studentId).toString());
        viewProvider.print("Choose course by ID");
        int enrollmentId = viewProvider.readInt();

        enrollmentsDao.deleteById(enrollmentId);

        return ("Hopefully Ok");
    }
}
