package com.veresklia.domain.provider;

import com.veresklia.domain.Group;
import com.veresklia.domain.Student;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ContentProvider {
    private static final String[] NAMES = {
        "Sasha",
        "Pasha",
        "Masha",
        "Misha",
        "Trisha",
        "Olya",
        "Kolya",
        "Yulya",
        "Bulya",
        "Oleg",

        "Aisha",
        "Kasim",
        "Karabas",
        "Petr",
        "Alina",
        "Kratos",
        "Hermes",
        "John",
        "Sara",
        "Jack"
    };
    private static final String[] SURNAMES = {
        "Ivanov",
        "Petrov",
        "Sidorov",
        "Kozebkova",
        "Masulkova",
        "Tyutev",
        "Mutev",
        "Kutev",
        "kaskin",
        "Maskin",
        "Terminator",
        "Sidorenko",
        "Ovsienko",
        "Kabarchenko",
        "Afinyan",
        "Andrievski",
        "Karapucko",
        "Barabas",
        "Cheburashko",
        "NoguCherezZaborPerekidayko"
    };

    private static final String[] COURSES = {
        "Math",
        "Biology",
        "History",
        "Geography",
        "Literature",
        "Ukrainian",
        "English",
        "IT",
        "Java",
        "Cooking"
    };
    private int numberOfNamesAvailable = 20;
    private int numberOfSurnamesAvailable = 20;

    public String[] getCourses() {
        return COURSES;
    }

    public Group[] generateGroups(int groupsNumder, int minStudents, int maxStudents) {
        Group[] resultedArray = new Group[groupsNumder];

        Arrays.setAll(resultedArray, a -> resultedArray[a] = new Group(new StringBuilder()
            .append(RandomStringUtils.randomAlphabetic(2))
            .append("-")
            .append(generateRandomInt(0, 10))
            .append(generateRandomInt(0, 10))
            .toString(), generateRandomInt(minStudents, maxStudents)));

        return resultedArray;
    }

    public List<String[]> generateStudents(int studentsNumber) {
        List<String[]> students = new ArrayList<>();
        for (int i = 0; i < studentsNumber; i++) {
            students.add(new String[]{NAMES[generateRandomInt(0, numberOfNamesAvailable)],
                SURNAMES[generateRandomInt(0, numberOfSurnamesAvailable)]});
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
            courses[i] = COURSES[generateRandomInt(0, 10)];
        }

        return courses;
    }

}
