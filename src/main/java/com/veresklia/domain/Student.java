package com.veresklia.domain;

import java.util.Optional;

public class Student {
    public String name;
    public String surname;
    public String group;
    public String[] courses;

    public static Builder builder() {
        return new Builder();
    }

    private Student () {};

    public Student (Builder builder){
        this.name = builder.name;
        this.surname = builder.surname;
        this.group = builder.group;
        this.courses = builder.courses;
    }

    public static class Builder {
        public String name;
        public String surname;
        public String group;
        public String[] courses;

        private Builder() { }

        public Builder withName (String name){
            this.name = name;
            return this;
        }

        public Builder withSurname (String surname){
            this.surname = surname;
            return this;
        }

        public Builder withGroup (String group){
            this.group = group;
            return this;
        }

        public Builder withCourses (String[] courses){
            this.courses = courses;
            return this;
        }

        public Student build (){
            return new Student(this);
        }

    }

}
