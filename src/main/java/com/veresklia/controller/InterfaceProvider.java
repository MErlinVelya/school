package com.veresklia.controller;

import java.util.Scanner;

public class InterfaceProvider {
    private static final String MENU = "Menu:" +
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


    public void provideInterface (){
        Scanner scanner = new Scanner(System.in);

        System.out.println(MENU);
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        while (!scanner.next().equals("exit")) {
            switch (scanner.next()){
                case ("a"): {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("Input student number to found:");
                    int number = scanner.nextInt();

                }
                scanner.nextLine();
            }


    }
}
}
