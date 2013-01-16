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

--changeset VitorBertazoli:workouts (dbms:mysql failOnError:true)
CREATE TABLE workout_cardio (
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(45),
	hours TINYINT UNSIGNED,
	minutes TINYINT UNSIGNED,
	PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE workout_regular (
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(45),
	weight SMALLINT UNSIGNED,
	sets TINYINT UNSIGNED,
	repetitions TINYINT UNSIGNED,
	PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE workout_dropset (
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(45),
	PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE workout_dropset_set (
	id int(11),
	dropset_id int(11) NOT NULL,
	sets TINYINT UNSIGNED,
	weight SMALLINT UNSIGNED,
	INDEX dropset_id_idx (dropset_id),
	FOREIGN KEY (dropset_id) REFERENCES workout_dropset(id) ON DELETE CASCADE
) ENGINE=INNODB;
--rollback DROP TABLE IF EXISTS workout_cardio;
--rollback DROP TABLE IF EXISTS workout_regular;
--rollback DROP TABLE IF EXISTS workout_dropset_set;
--rollback DROP TABLE IF EXISTS workout_dropset;