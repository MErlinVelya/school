package com.veresklia.domain.provider;

import com.veresklia.domain.Group;
import com.veresklia.domain.Student;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ContentProvider {
    public static final int ZERO = 0;
    public static final int NINE = 9;
    private int numberOfNamesAvailable = 20;
    private int numberOfSurnamesAvailable = 20;


    public Group[] generateGroups(int groupsNumder, int minStudents, int maxStudents) {
        Group[] resultedArray = new Group[groupsNumder];

        Arrays.setAll(resultedArray, a -> resultedArray[a] = new Group(GroupNameProvider.groupNameProvider(
            generateRandomInt(ZERO, NINE), generateRandomInt(ZERO, NINE)), generateRandomInt(minStudents, maxStudents)));

        return resultedArray;
    }

    private List<String[]> generateStudents(int studentsNumber) {
        List<String[]> students = new ArrayList<>();
        for (int i = 0; i < studentsNumber; i++) {
            students.add(StudentProvider.provideStudent(generateRandomInt(ZERO, StudentProvider.namesAvailable()),
                generateRandomInt(ZERO, StudentProvider.surnamesAvailable())));
        }
        return students;
    }

    private int generateRandomInt(int min, int limit) {
        return ThreadLocalRandom.current().nextInt(min, limit);
    }

    public List<Student> generateStudentsAllIn(Group[] groups, int minCourses, int maxCourses, int numberOfStudents) {

        List<Student> students = new ArrayList<>();
        List<String[]> studentsNames = generateStudents(200);

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


    private String groupAssignement(Group[] groups) {
        String group = "";

        for (int i = 0; i < groups.length; i++) {
            if (groups[i].studentsquantity > 0) {
                group = groups[i].group;
                groups[i].studentsquantity--;
                break;
            }
        }
        return group;
    }

    private String[] coursesAssignement(int minCources, int maxCourses) {
        int courcesNumber = generateRandomInt(minCources, maxCourses + 1);
        String[] courses = new String[courcesNumber];

        for (int i = 0; i < courcesNumber; i++) {
            courses[i] = CoursesProvider.getCourses()[generateRandomInt(ZERO, CoursesProvider.coursesAvailable())];
        }

        return courses;
    }

}
