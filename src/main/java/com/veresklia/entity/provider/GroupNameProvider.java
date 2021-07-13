package com.veresklia.entity.provider;

import org.apache.commons.lang3.RandomStringUtils;

public interface GroupNameProvider {
    static String groupNameProvider(int firstFigure, int secondfigure) {
        return new StringBuilder()
                .append(RandomStringUtils.randomAlphabetic(GroupNameProviderImpl.NUMBER_OF_LETTERS))
                .append(GroupNameProviderImpl.SEPARATOR)
                .append(firstFigure)
                .append(secondfigure)
                .toString();
    }
}
