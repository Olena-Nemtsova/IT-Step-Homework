package edu.it.step.model;

import java.util.Arrays;

public enum TranslatorType {
    UA_EN(1),
    EN_UA(2);

    private final int id;

    TranslatorType(int id) {
        this.id = id;
    }

    public static TranslatorType valueOf(int id) {
        return Arrays.stream(TranslatorType.values())
                .filter(e -> e.id == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown translator type id"));
    }
}
