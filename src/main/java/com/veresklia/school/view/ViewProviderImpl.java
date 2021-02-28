package com.veresklia.school.view;

import java.util.Scanner;

public class ViewProviderImpl implements ViewProvider {
    private static final String MENU = "Menu:\n" +
            "\ta. Find all groups with less or equals student count\n" +
            "\n" +
            "\tb. Find all students related to course with given name\n" +
            "\n" +
            "\tc. Add new student\n" +
            "\n" +
            "\td. Delete student by STUDENT_ID\n" +
            "\n" +
            "\te. Add a student to the course (from a list)\n" +
            "\n" +
            "\tf. Remove the student from one of his or her courses\n\n" +
            "\texit. for exit";

    @Override
    public String getMenu(){
        return MENU;
    }

    @Override
    public void print (String message){
        System.out.println(message);
    }

    @Override
    public int readInt(){
        return new Scanner(System.in).nextInt();
    }

    @Override
    public String readString(){
        return new Scanner(System.in).nextLine();
    }
}
