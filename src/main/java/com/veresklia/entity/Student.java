package com.veresklia.entity;

import java.util.List;
import java.util.stream.Collectors;

public class Student {
    private Integer id;
    public final String name;
    public final String surname;
    public final String group;
    public final List<Course> courses;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGroup() {
        return group;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Student(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.group = builder.group;
        this.courses = builder.courses;
    }

    private Student() throws Exception {
        throw new Exception();
    }

    @Override
    public String toString() {
        String addCourses = "";
        if (courses != null) {
            addCourses += courses.stream().map(a -> a.toString()).collect(Collectors.joining());
        }

        return new StringBuilder()
            .append(" ID :" + id)
            .append(" Name: " + name)
            .append(" Suranme: " + surname)
            .append(" Group: " + group)
            .append(" Cources: " + addCourses)
            .append("\n")
            .toString();
    }

    public static class Builder {
        public Integer id;
        public String name;
        public String surname;
        public String group;
        public List<Course> courses;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder withGroup(String group) {
            this.group = group;
            return this;
        }

        public Builder withCourses(List<Course> courses) {
            this.courses = courses;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
