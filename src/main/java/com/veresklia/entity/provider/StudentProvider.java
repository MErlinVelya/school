package com.veresklia.entity.provider;

public interface StudentProvider {
    static int namesAvailable() {
        return StudentProviderImpl.getNames().length;
    }

    static int surnamesAvailable() {
        return StudentProviderImpl.getSurnames().length;
    }

    static String[] provideStudent(int nameNumber, int surnameNumber) {
        return new String[]{StudentProviderImpl.getNames()[nameNumber], StudentProviderImpl.getSurnames()[surnameNumber]};
    }
}
