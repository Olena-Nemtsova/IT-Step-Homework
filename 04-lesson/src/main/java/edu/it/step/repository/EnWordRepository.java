package edu.it.step.repository;

import edu.it.step.model.TranslatorType;
import edu.it.step.model.WordEntry;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EnWordRepository implements WordRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final String INSERT_WORD = """
            INSERT INTO en_word(word)
            VALUES (:word)
            """;
    private static final String FETCH_UA_TRANSLATIONS = """
            SELECT * FROM ua_word
            INNER JOIN en_ua ON ua_word.id=en_ua.ua_id
            INNER JOIN en_word ON en_ua.en_id=en_word.id
            WHERE en_word.word ILIKE :word
            """;

    @Override
    public List<WordEntry> getTranslate(String word, TranslatorType translatorType) {
        String query;
        switch (translatorType) {
            case EN_UA -> query = FETCH_UA_TRANSLATIONS;
            default -> throw new IllegalArgumentException("unknown translator type");
        }
        return WordRepository.getTranslate(word, jdbcTemplate, query);
    }

    @Override
    public int save(WordEntry wordEntry) {
        return WordRepository.save(wordEntry, jdbcTemplate, INSERT_WORD);
    }
}
