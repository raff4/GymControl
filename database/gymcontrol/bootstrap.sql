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
	userid int(11) NOT NULL,
	name varchar(45),
	hours TINYINT UNSIGNED,
	minutes TINYINT UNSIGNED,
	PRIMARY KEY (id),
	INDEX workout_cardio_userid_idx (userid),
	FOREIGN KEY (userid) REFERENCES user(id) ON DELETE CASCADE
) ENGINE=INNODB;

CREATE TABLE workout_regular (
	id int(11) NOT NULL AUTO_INCREMENT,
	userid int(11) NOT NULL,
	name varchar(45),
	weight SMALLINT UNSIGNED,
	sets TINYINT UNSIGNED,
	repetitions TINYINT UNSIGNED,
	PRIMARY KEY (id),
	INDEX workout_regular_userid_idx (userid),
	FOREIGN KEY (userid) REFERENCES user(id) ON DELETE CASCADE
) ENGINE=INNODB;

CREATE TABLE workout_dropset (
	id int(11) NOT NULL AUTO_INCREMENT,
	userid int(11) NOT NULL,
	name varchar(45),
	PRIMARY KEY (id),
	INDEX workout_dropset_userid_idx (userid),
	FOREIGN KEY (userid) REFERENCES user(id) ON DELETE CASCADE
) ENGINE=INNODB;

CREATE TABLE workout_dropset_set (
	id int(11),
	dropsetid int(11) NOT NULL,
	sets TINYINT UNSIGNED,
	weight SMALLINT UNSIGNED,
	INDEX dropsetid_idx (dropsetid),
	FOREIGN KEY (dropsetid) REFERENCES workout_dropset(id) ON DELETE CASCADE
) ENGINE=INNODB;
--rollback DROP TABLE IF EXISTS workout_cardio;
--rollback DROP TABLE IF EXISTS workout_regular;
--rollback DROP TABLE IF EXISTS workout_dropset_set;
--rollback DROP TABLE IF EXISTS workout_dropset;