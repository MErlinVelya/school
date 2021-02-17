package com.veresklia.provider;

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

    private static final String[] COURCES = {
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

    public String[] getCourses ()
    {
        return COURCES;
    }

    public Group[] generateGroups (int groupsNumder){
        Group[] resultedArray = new Group [groupsNumder];

        Arrays.setAll(resultedArray, a ->  resultedArray[a] = new Group(new StringBuilder()
            .append(RandomStringUtils.randomAlphabetic(2))
            .append("-")
            .append(generateRandomInt(0, 9))
            .append(generateRandomInt(0, 9))
            .toString(), generateRandomInt(10, 30) ));

        return resultedArray ;
    }

    public List<String[]> generateStudents (int studentsNumber){
        List<String[]> students = new ArrayList<>();
        for (int i = 0; i < studentsNumber; i++) {
            students.add(new String[]{NAMES[generateRandomInt(0, numberOfNamesAvailable)],
                SURNAMES[generateRandomInt(0, numberOfSurnamesAvailable)]});
        }
        //      students.add(new String[2] = {"ww", "dd"});


        return students;
    }

    private int generateRandomInt (int min, int limit){
        return  ThreadLocalRandom.current().nextInt(min, limit);
    }

    public List<Student> generateStudentsAllIn (int minInGroup, int maxInGroup, int minCourses, int maxCourses, int numberOfStudents){
        List<String[]> names = generateStudents(numberOfStudents);
        // = new String [groupsNumder];

        List<Student> students = new ArrayList<>();

       // students.add(new Student())

    }

}
