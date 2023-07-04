package edu.it.step.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WordEntry {
    private int id;
    private String word;

    public WordEntry(String word) {
        this(-1, word);
    }
}
