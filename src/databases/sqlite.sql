create table category
(
    id_category INTEGER not null
        primary key autoincrement,
    name VARCHAR not null
        unique,
    active BOOLEAN not null
);

create table question
(
    id_question INTEGER not null
        primary key autoincrement,
    text TEXT(255) not null,
    category integer not null
        references category (id_category),
    active BOOLEAN not null
);

create table answer
(
    id_answer INTEGER not null
        primary key autoincrement,
    text VARCHAR not null,
    question_id INTEGER
        references question,
    is_correct BOOLEAN not null,
    active BOOLEAN not null
);

create table user
(
    id_user INTEGER not null
        primary key autoincrement,
    password CHAR(128) not null,
    email VARCHAR not null,
    name VARCHAR not null,
    score INTEGER,
    is_admin BOOLEAN not null,
    active BOOLEAN not null
);

create table round
(
    id_round INTEGER not null
        primary key autoincrement,
    category_id INTEGER
        references category,
    user_id INTEGER
        references user,
    active BOOLEAN not null
);

create table round_question
(
    id_round_question INTEGER not null
        primary key autoincrement,
    question_id INTEGER
        references question,
    round_id INTEGER
        references round,
    is_right BOOLEAN
);

