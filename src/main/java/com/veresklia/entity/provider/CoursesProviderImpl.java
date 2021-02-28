package com.veresklia.entity.provider;

import com.veresklia.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CoursesProviderImpl implements CoursesProvider {
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

    @Override
    public int coursesAvailable() {

        return CoursesProviderImpl.COURSES.length;
    }

    @Override
    public String[] getCourses() {
        return CoursesProviderImpl.COURSES;
    }

    @Override
    public List<Course> generateCoursesArray(){
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < coursesAvailable(); i++){
            courses.add(new Course(COURSES[i] , COURSES[i]));
        }
        return courses;
    }
}
