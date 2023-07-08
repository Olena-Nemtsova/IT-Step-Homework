package edu.it.step.task3;

import edu.it.step.task3.util.AnswerManager;
import edu.it.step.task3.util.PrintManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionsGame {
    private final List<Question> questions = QuestionRepository.getQuestions();
    private int points;

    public void start() {
        points = 0;
        questions.forEach(question -> {
            PrintManager.println(question.getText());

            var answers = new ArrayList<>(question.getAnswers());
            String correct = answers.get(3);
            Collections.shuffle(answers);

            int correctAnsNum = answers.indexOf(correct) + 1;
            answers.forEach(answer ->
                    PrintManager.println((answers.indexOf(answer) + 1) + "." + answer)
            );
            if (AnswerManager.getIntAnswer(answers.size()) == correctAnsNum) {
                points++;
            }
        });

        result();
    }

    private void result() {
        PrintManager.println("Your points: " + points + "/" + questions.size());
    }
}
