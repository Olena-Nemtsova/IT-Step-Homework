package edu.it.step;

import edu.it.step.config.ApplicationConfig;
import edu.it.step.config.DatabaseConfig;
import edu.it.step.model.TranslatorType;
import edu.it.step.util.AnswerManager;
import edu.it.step.util.PrintManager;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationStarter {
    private static final String SET_TRANSLATOR_MESSAGE = """
            Select translator type:
            1.UA-EN
            2.EN-UA
            """;
    private static final String ACTIONS_MESSAGE = """
            Select action:
            1.Translate the word
            2.Add new word
            3.Exit
            """;
    private static final String TRANSLATE_MESSAGE = """
            Type the word to translate:
            """;
    private static final String TRANSLATE_NOT_FOUND_MESSAGE = """
            No translations found for this word
            """;
    private static final String ADD_WORD_MESSAGE = """
            Type new word:
            """;
    private static final String ADD_TRANSLATION_MESSAGE = """
            Type new word translation:
            """;
    private static final String ADD_ADDITIONAL_TRANSLATION_MESSAGE = """
            Would you like to add additional translation of the word?
            1.Yes
            2.No
            """;

    public static void main(String[] args) {
        var applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class, DatabaseConfig.class);

        var translationService = applicationContext.getBean(TranslationService.class);

        PrintManager.println(SET_TRANSLATOR_MESSAGE);
        TranslatorType type = TranslatorType.valueOf(AnswerManager.getIntAnswer(2));
        translationService.setTranslatorType(type);

        while (true) {
            PrintManager.println(ACTIONS_MESSAGE);
            switch (AnswerManager.getIntAnswer(3)) {
                case 1 -> {
                    PrintManager.println(TRANSLATE_MESSAGE);
                    var translations = translationService.getTranslations(AnswerManager.getAnswer());
                    if (translations.isEmpty()) {
                        PrintManager.println(TRANSLATE_NOT_FOUND_MESSAGE);
                    } else {
                        PrintManager.println("Translations:");
                        translations.forEach(PrintManager::println);
                    }
                }
                case 2 -> {
                    PrintManager.println(ADD_WORD_MESSAGE);
                    String newWord = AnswerManager.getAnswer();

                    List<String> translations = new ArrayList<>();
                    PrintManager.println(ADD_TRANSLATION_MESSAGE);
                    translations.add(AnswerManager.getAnswer());

                    while (true) {
                        PrintManager.println(ADD_ADDITIONAL_TRANSLATION_MESSAGE);
                        if (AnswerManager.getIntAnswer(2) == 2) {
                            break;
                        }
                        PrintManager.println(ADD_TRANSLATION_MESSAGE);
                        translations.add(AnswerManager.getAnswer());
                    }

                    translationService.addWord(newWord, translations);
                }
                default -> {
                    return;
                }
            }
        }
    }
}