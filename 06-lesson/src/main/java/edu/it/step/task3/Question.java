package edu.it.step.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Question {
    private String text;
    private List<String> answers;
}
