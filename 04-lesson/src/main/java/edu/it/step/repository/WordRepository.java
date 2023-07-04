package edu.it.step.repository;

import edu.it.step.model.TranslatorType;
import edu.it.step.model.WordEntry;
import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

public interface WordRepository {
    List<WordEntry> getTranslate(String word, TranslatorType translatorType);

    int save(WordEntry wordEntry);

    static int save(WordEntry wordEntry, NamedParameterJdbcTemplate jdbcTemplate, String query){
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("word", wordEntry.getWord());

        jdbcTemplate.update(query, mapSqlParameterSource, generatedKeyHolder);
        var keys = generatedKeyHolder.getKeys();
        if (keys != null) {
            return (int) keys.get("id");
        }
        return -1;
    }

    static List<WordEntry>getTranslate(String word, NamedParameterJdbcTemplate jdbcTemplate, String query){
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("word", word);

        return jdbcTemplate.query(query, mapSqlParameterSource,
                (rs, rowNum) -> new WordEntry(
                        rs.getInt("id"),
                        rs.getString("word")
                ));
    }
}
