package com.veresklia.provider;

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
    private int numberOfNamesAvailable = 20;
    private int numberOfSurnamesAvailable = 20;

    public String[] generateCources (int courcesNumber){
        StringBuilder resultedstring = new StringBuilder();
        String[] resultedArray = new String[courcesNumber];
        Arrays.stream(resultedArray).forEach( a -> a = new String(resultedstring.append(RandomStringUtils.randomAlphabetic(2))
                .append("-")
                .append(generateRandomInt(9))
                .append(generateRandomInt(9))
                .toString()));

        return resultedArray ;
    }

    public List<String[]> generateStudents (int studentsNumber){
        List<String[]> students = new ArrayList<>();
        for (int i = 0; i < studentsNumber; i++) {
            students.add(new String[]{NAMES[generateRandomInt(numberOfNamesAvailable)],
                SURNAMES[generateRandomInt(numberOfSurnamesAvailable)]});
        }
        //      students.add(new String[2] = {"ww", "dd"});


        return students;
    }

    private int generateRandomInt (int limit){
        return  ThreadLocalRandom.current().nextInt(0, limit);
    }

}
