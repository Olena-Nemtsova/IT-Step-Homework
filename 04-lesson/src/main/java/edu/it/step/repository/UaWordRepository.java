package edu.it.step.repository;

import edu.it.step.model.TranslatorType;
import edu.it.step.model.WordEntry;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UaWordRepository implements WordRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final String INSERT_WORD = """
            INSERT INTO ua_word(word)
            VALUES (:word)
            """;
    private static final String FETCH_EN_TRANSLATIONS = """
            SELECT * FROM en_word
            INNER JOIN en_ua ON en_word.id=en_ua.en_id
            INNER JOIN ua_word ON en_ua.ua_id=ua_word.id
            WHERE ua_word.word ILIKE :word
            """;

    @Override
    public List<WordEntry> getTranslate(String word, TranslatorType translatorType) {
        String query;
        switch (translatorType) {
            case UA_EN -> query = FETCH_EN_TRANSLATIONS;
            default -> throw new IllegalArgumentException("unknown translator type");
        }
        return WordRepository.getTranslate(word, jdbcTemplate, query);
    }

    @Override
    public int save(WordEntry wordEntry) {
        return WordRepository.save(wordEntry, jdbcTemplate, INSERT_WORD);
    }
}
