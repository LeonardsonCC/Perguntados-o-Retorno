CREATE TABLE user
(
    id_user  INTEGER PRIMARY KEY AUTOINCREMENT
                       NOT NULL,
    password CHAR(128) NOT NULL,
    email    VARCHAR   NOT NULL,
    name     VARCHAR   NOT NULL,
    score    INTEGER,
    is_admin BOOLEAN   NOT NULL,
    active   BOOLEAN   NOT NULL
);


CREATE TABLE category
(
    id_category INTEGER PRIMARY KEY AUTOINCREMENT
                        NOT NULL,
    name        VARCHAR NOT NULL
        UNIQUE,
    active      BOOLEAN NOT NULL
);


CREATE TABLE question
(
    id_question INTEGER PRIMARY KEY AUTOINCREMENT
                          NOT NULL,
    title       VARCHAR   NOT NULL,
    text        TEXT(255) NOT NULL,
    category              NOT NULL REFERENCES category (id_category),
    active      BOOLEAN   NOT NULL
);


CREATE TABLE answer
(
    id_answer   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    text        VARCHAR                           NOT NULL,
    question_id INTEGER REFERENCES question (id_question),
    is_correct  BOOLEAN                           NOT NULL,
    active      BOOLEAN                           NOT NULL
);

CREATE TABLE round
(
    id_round    INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    category_id INTEGER REFERENCES category (id_category),
    user_id     INTEGER REFERENCES user (id_user),
    active      BOOLEAN                           NOT NULL
);

CREATE TABLE round_question
(
    id_round_question INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    question_id       INTEGER REFERENCES question (id_question),
    round_id          INTEGER REFERENCES round (id_round),
    is_right          BOOLEAN
)
