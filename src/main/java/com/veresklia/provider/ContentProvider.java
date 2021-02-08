package com.veresklia.provider;

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

    public List<String[]> generateStudents (int studentsNumber){
        List<String[]> students = new ArrayList<>();
        students.add(NAMES[generateRandomInt(numberOfNamesAvailable)], SURNAMES[generateRandomInt(numberOfSurnamesAvailable)]);
        students.
        //      students.add(new String[2] = {"ww", "dd"});


        return students;
    }

    private int generateRandomInt (int limit){
        return  ThreadLocalRandom.current().nextInt(0, numberOfNamesAvailable);
    }

}
