package com.veresklia.domain.provider;

public class StudentProvider {
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

    public static int namesAvailable(){
        return NAMES.length;
    }

    public static int surnamesAvailable(){
        return SURNAMES.length;
    }

    public static String[] provideStudent (int nameNumber, int surnameNumber){
        return new String[] {NAMES[nameNumber], SURNAMES[surnameNumber]};
    }

}
