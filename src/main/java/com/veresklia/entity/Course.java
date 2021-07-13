package com.veresklia.entity;

import java.util.Objects;

public class Course  {
    private Integer id;
    private final String courseName;
    private final String courseDescription;

   public Course (String course, String courseDescription) {
       courseName = course;
       this.courseDescription = courseDescription;
   }

   public void setId (Integer id){
       this.id = id;
   }

    public Integer getId (){
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    @Override
    public String toString () {
        return ("Id: " + id + " CorseName: " + courseName + ", Course descr: " + courseDescription);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Course course = (Course) o;
        return id.equals(course.id) &&
            courseName.equals(course.courseName) &&
            courseDescription.equals(course.courseDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, courseDescription);
    }
}
