package com.veresklia.school.view;

public interface ViewProvider {

    void print (String message);

    String getMenu();

    int readInt();

    String readString();
}
