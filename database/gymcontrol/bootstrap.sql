--liquibase formatted sql

--changeset VitorBertazoli:initial (dbms:mysql failOnError:true)
CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  firstname varchar(45),
  lastname varchar(45),
  username varchar(45),
  email varchar(50),
  password varchar(100),
  salt varchar(50),
  dob timestamp,
  PRIMARY KEY (id),
  UNIQUE KEY email_UNIQUE (email)
);
--rollback drop table user