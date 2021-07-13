package com.veresklia.entity.provider;

import com.veresklia.entity.Course;

import java.util.List;

public interface CoursesProvider {
    int coursesAvailable();

    String[] getCourses();

    List<Course> generateCoursesArray();
}
