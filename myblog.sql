DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS tag;
DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS user_to_comment;

CREATE TABLE article
(
    idArticle SERIAL8,
    title     VARCHAR(100)   NOT NULL,
    time      TIMESTAMP DEFAULT current_timestamp,
    comment   INT       DEFAULT 0,
    content   VARCHAR(10000) NOT NULL,
    PRIMARY KEY (idArticle)
);

CREATE TABLE user_to_comment
(
    idUser SERIAL8,
    name   VARCHAR(100) DEFAULT NULL,
    email  VARCHAR(100) NOT NULL,
    PRIMARY KEY (idUser)
);

CREATE TABLE comment
(
    idComment         SERIAL8,
    content           VARCHAR(100) NOT NULL,
    time              TIMESTAMP DEFAULT current_timestamp,
    article_idArticle INT       DEFAULT NULL,
    user_idUser       INT       DEFAULT NULL,
    PRIMARY KEY (idComment),
--     PRIMARY KEY (article_idArticle),
    FOREIGN KEY (article_idArticle) REFERENCES article (idArticle) ON DELETE CASCADE ON UPDATE NO ACTION,
    FOREIGN KEY (user_idUser) REFERENCES user_to_comment (idUser)
);

CREATE TABLE tag
(
    idTag             SERIAL8,
    tag               VARCHAR(100) DEFAULT NULL,
    article_idArticle INT NOT NULL,
    PRIMARY KEY (idTag),
    FOREIGN KEY (article_idArticle) REFERENCES article (idArticle) ON DELETE CASCADE ON UPDATE NO ACTION
);

ALTER TABLE article
    ADD UNIQUE (title);
ALTER TABLE tag
    ADD UNIQUE (tag);
ALTER TABLE user_to_comment
    ADD UNIQUE (email);

-- ALTER TABLE article
--     ADD CHECK ((current_date - article.time) >= 0);
ALTER TABLE user_to_comment
    ADD CHECK (
            octet_length(email) BETWEEN 6 AND 320 -- https://en.wikipedia.org/wiki/Email_address
            AND user_to_comment.email LIKE '_%@_%.__%' -- rough, but quick check email syntax
        );

