package com.veresklia.domain;

import java.util.Optional;

public class Student {
    public String name;
    public String surname;
    public Optional<String> group;
    public String[] courses;
    private Builder builder;

    private Student () {};

    public Student (Builder builder){
        this.name = builder.name;
        this.surname = builder.surname;
        this.group = builder.group;
        this.courses = builder.courses;
    }

    public class Builder {
        public String name;
        public String surname;
        public Optional<String> group;
        public String[] courses;

        public Builder() {
            new Builder();
        }

        Builder withName (String name){
            this.name = name;
            return this;
        }

        Builder withSurname (String surname){
            this.surname = surname;
            return this;
        }

        Builder withGroup (Optional<String> group){
            this.group = group;
            return this;
        }

        Builder withCourses (String[] courses){
            this.courses = courses;
            return this;
        }

        Student build (){
            return new Student(this);
        }

    }

}
