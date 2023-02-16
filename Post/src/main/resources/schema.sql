CREATE TABLE if NOT EXISTS posts
(
    id
    BIGSERIAL
    PRIMARY
    KEY,
    author_id
    BIGINT
    NOT
    NULL,
    text
    TEXT
    NOT
    NULL,
    topic
    TEXT
    NOT
    NULL,
    posted_at
    TIMESTAMP
    NOT
    NULL
);
CREATE SEQUENCE if not EXISTS post_seq START 7;


