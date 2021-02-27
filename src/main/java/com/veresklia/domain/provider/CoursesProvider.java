package com.veresklia.domain.provider;

public class CoursesProvider {
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

    public static int coursesAvailable (){
        return COURSES.length;
    }

    public static String[] getCourses() {
        return COURSES;
    }

}
