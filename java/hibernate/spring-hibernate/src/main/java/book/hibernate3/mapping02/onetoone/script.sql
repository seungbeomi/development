DROP TABLE IF EXISTS BOARD_ARTICLE;

CREATE TABLE BOARD_ARTICLE (
  ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1),
  TITLE VARCHAR(50),
  PRIMARY KEY(ID)
);

DROP TABLE IF EXISTS BOARD_ARTICLE_DETAIL;

CREATE TABLE BOARD_ARTICLE_DETAIL (
  BOARD_ARTICLEID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1),
  CONTENT TEXT,
  PRIMARY KEY(BOARD_ARTICLEID),
  FOREIGN KEY (BOARD_ARTICLEID) REFERENCES BOARD_ARTICLE(ID)
);
