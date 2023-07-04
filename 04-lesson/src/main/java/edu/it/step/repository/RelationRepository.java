package edu.it.step.repository;

import edu.it.step.model.TranslatorType;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RelationRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final String INSERT_RELATION = """
            INSERT INTO en_ua(en_id, ua_id)
            VALUES (:enId, :uaId)
            """;

    public void save(int wordId, int translId, TranslatorType type) {
        switch (type) {
            case EN_UA -> saveEnUa(wordId, translId);
            case UA_EN -> saveEnUa(translId, wordId);
            default -> throw new IllegalArgumentException("unknown translator type");
        }
    }

    private void saveEnUa(int enId, int uaId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("enId", enId)
                .addValue("uaId", uaId);

        jdbcTemplate.update(INSERT_RELATION, mapSqlParameterSource);
    }
}
