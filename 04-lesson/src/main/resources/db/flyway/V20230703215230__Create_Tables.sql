CREATE TABLE IF NOT EXISTS en_word (
    id SERIAL PRIMARY KEY,
    word VARCHAR UNIQUE
);

CREATE TABLE IF NOT EXISTS ua_word (
    id SERIAL PRIMARY KEY,
    word VARCHAR UNIQUE
);

CREATE TABLE IF NOT EXISTS en_ua (
    id SERIAL PRIMARY KEY,
    en_id INT NOT NULL,
    ua_id INT NOT NULL,
    FOREIGN KEY (en_id) REFERENCES en_word(id) ON DELETE CASCADE,
    FOREIGN KEY (ua_id) REFERENCES ua_word(id) ON DELETE CASCADE
);

INSERT INTO en_word (word) VALUES
                               ('sun'),
                               ('sky'),
                               ('cloud'),
                               ('rain');

INSERT INTO ua_word (word) VALUES
                               ('сонце'),
                               ('небо'),
                               ('небеса'),
                               ('хмара'),
                               ('дощ');

INSERT INTO en_ua (en_id, ua_id) VALUES
                                     (1, 1),
                                     (2, 2),
                                     (2, 3),
                                     (3, 4),
                                     (4, 5);
