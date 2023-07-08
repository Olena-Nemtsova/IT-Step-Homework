package edu.it.step.task3;

import edu.it.step.task3.util.PrintManager;
import edu.it.step.task3.util.PrintType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionRepository {
    private static final List<Question> questions = new ArrayList<>();

    private QuestionRepository() {
    }

    static {
        pullQuestions();
    }

    private static void pullQuestions() {
        try (FileReader fr = new FileReader("06-lesson/src/main/resources/questions.txt");
             BufferedReader reader = new BufferedReader(fr)) {
            String line = reader.readLine();

            while (line != null) {
                questions.add(getQuestionFromString(line));
                line = reader.readLine();
            }
        } catch (IOException e) {
            PrintManager.println(e.getMessage(), PrintType.ERROR);
        }
    }

    private static Question getQuestionFromString(String line) {
        String[] parts = line.split(";");
        return new Question(parts[0], Arrays.stream(parts).skip(1).toList());
    }

    public static List<Question> getQuestions() {
        return questions;
    }
}
