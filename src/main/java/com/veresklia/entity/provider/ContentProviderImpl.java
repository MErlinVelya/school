package com.veresklia.entity.provider;

import com.veresklia.entity.Course;
import com.veresklia.entity.Group;
import com.veresklia.entity.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ContentProviderImpl implements ContentProvider {
    private static final int ZERO = 0;
    private static final int NINE = 9;

    @Override
    public List<Group> generateGroups(int groupsNumber, int minStudents, int maxStudents) {
        List<Group> groups= new ArrayList<>();

        for (int i = 0; i < groupsNumber; i++){
            groups.add(new Group(GroupNameProvider.groupNameProvider(
                generateRandomInt(ZERO, NINE), generateRandomInt(ZERO, NINE)), generateRandomInt(minStudents, maxStudents)));
        }

        return groups;
    }

    private List<String[]> generateStudents(int studentsNumber) {
        List<String[]> students = new ArrayList<>();
        for (int i = 0; i < studentsNumber; i++) {
            students.add(StudentProvider.provideStudent(generateRandomInt(ZERO, StudentProvider.namesAvailable()),
                    generateRandomInt(ZERO, StudentProvider.surnamesAvailable())));
        }
        return students;
    }

    @Override
    public List<Student> generateStudentsAllIn(List<Group> groups, int minCourses, int maxCourses, int numberOfStudents) {

        List<Student> students = new ArrayList<>();
        List<String[]> studentsNames = generateStudents(numberOfStudents);

        for (String[] studentName : studentsNames) {
            students.add(Student.builder()
                    .withName(studentName[0])
                    .withSurname(studentName[1])
                    .withGroup(groupAssignement(groups))
                    .withCourses(coursesAssignement(minCourses, maxCourses)).
                            build());
        }

        return students;
    }

    private String groupAssignement(List<Group> groups) {
        String groupName = "";

        for (Group group : groups) {
            if (group.getStudentsquantity() > 0) {
                groupName = group.getGroupName();
                group.reduceStudentsquantity();
                break;
            }
        }
        return groupName;
    }

    private List<Course> coursesAssignement(int minCources, int maxCourses) {
        int courcesNumber = generateRandomInt(minCources, maxCourses + 1);
        List<Course> courses = new ArrayList<>();
        CoursesProvider coursesProvider= new CoursesProviderImpl();

        for (int i = 0; i < courcesNumber; i++) {
            courses.add(new Course(coursesProvider.getCourses()[generateRandomInt(ZERO, coursesProvider.coursesAvailable())],
                coursesProvider.getCourses()[generateRandomInt(ZERO, coursesProvider.coursesAvailable())]));
        }

        return courses;
    }

    private int generateRandomInt(int min, int limit) {
        return ThreadLocalRandom.current().nextInt(min, limit);
    }
}
