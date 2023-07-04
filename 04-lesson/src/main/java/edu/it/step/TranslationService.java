package edu.it.step;

import edu.it.step.model.TranslatorType;
import edu.it.step.model.WordEntry;
import edu.it.step.repository.EnWordRepository;
import edu.it.step.repository.RelationRepository;
import edu.it.step.repository.UaWordRepository;
import edu.it.step.repository.WordRepository;
import edu.it.step.util.PrintManager;
import edu.it.step.util.PrintType;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranslationService {
    private WordRepository from;
    private WordRepository to;
    private TranslatorType translatorType;
    private final ApplicationContext context;
    private final RelationRepository relationRepository;

    public void setTranslatorType(TranslatorType type) {
        translatorType = type;
        switch (type) {
            case EN_UA -> {
                from = context.getBean(EnWordRepository.class);
                to = context.getBean(UaWordRepository.class);
            }
            case UA_EN -> {
                from = context.getBean(UaWordRepository.class);
                to = context.getBean(EnWordRepository.class);
            }
            default -> throw new IllegalArgumentException("unknown translator type");
        }
    }

    public List<String> getTranslations(String word) {
        PrintManager.println(translatorType.name());
        return from.getTranslate(word, translatorType)
                .stream()
                .map(WordEntry::getWord)
                .toList();
    }

    public void addWord(String word, List<String> translations) {
        try {
            int newWordId = from.save(new WordEntry(word));

            List<Integer> newTranslationsId = new ArrayList<>();
            translations.forEach(it -> newTranslationsId.add(to.save(new WordEntry(it))));

            newTranslationsId.forEach(it -> relationRepository.save(newWordId, it, translatorType));
        } catch (DataAccessException ex) {
            PrintManager.println(ex.getMessage(), PrintType.ERROR);
        }
    }
}
