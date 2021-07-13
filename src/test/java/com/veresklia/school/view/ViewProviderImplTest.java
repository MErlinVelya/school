package com.veresklia.school.view;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ViewProviderImplTest {

    @Test
    void getMenuShouldReturnMenuWhenStarted() {
        String expected = "Menu:\n" +
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
        ViewProvider viewProvider = new ViewProviderImpl();
        String actual = viewProvider.getMenu();
        assertThat(actual, is(expected));
    }

    @Test
    void printShouldPrintWhenProvidedWithString() {
        ByteArrayOutputStream actual = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actual));
        String printable = "Ok";
        String expected = "Ok\n";
        ViewProvider viewProvider = new ViewProviderImpl();
        viewProvider.print(printable);
        assertThat(actual.toString(), is(expected) );
    }

    @Test
    void readIntShouldReturnIntWhenInputedInt() {
        String data = "1";
        int expected = 1;

        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        ViewProvider viewProvider = new ViewProviderImpl();
        int actual = viewProvider.readInt();
        assertThat(actual, is(expected));
    }

    @Test
    void readStringShouldReturnStringWhenInputedString() {
        String expected = "Ok";

        ByteArrayInputStream in = new ByteArrayInputStream(expected.getBytes());
        System.setIn(in);
        ViewProvider viewProvider = new ViewProviderImpl();
        String actual = viewProvider.readString();
        assertThat(actual, is(expected));
    }
}
