DROP TABLE IF EXISTS PERSON;

CREATE TABLE PERSON (
  ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1),
  NAME VARCHAR(50),
  PRIMARY KEY(ID)
);
