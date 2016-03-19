drop database if EXISTS  kaze;
CREATE DATABASE kaze;

create table user (
  id   INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50)      DEFAULT NULL,
  PRIMARY KEY (id)
)

INSERT INTO user(name) in VALUES ('kaze');