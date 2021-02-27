package com.veresklia.domain.provider;

import org.apache.commons.lang3.RandomStringUtils;

public class GroupNameProvider {
    private static final int NUMBER_OF_LETTERS = 2;
    private static final String SEPARATOR = "-";


    public static String groupNameProvider(int firstFigure, int secondfigure){
        return new StringBuilder()
            .append(RandomStringUtils.randomAlphabetic(NUMBER_OF_LETTERS))
            .append(SEPARATOR)
            .append(firstFigure)
            .append(secondfigure)
            .toString();
    }
}
