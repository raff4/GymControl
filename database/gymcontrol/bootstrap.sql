--liquibase formatted sql

--changeset VitorBertazoli:workouts (dbms:mysql failOnError:true)
CREATE TABLE workout (
	id int(11) NOT NULL AUTO_INCREMENT,
	userid int(11) NOT NULL,
	name varchar(45),
	day date,
	PRIMARY KEY (id),
	FOREIGN KEY (userid) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE workout_cardio (
	id int(11) NOT NULL AUTO_INCREMENT,
	workoutid int(11) NOT NULL,
	name varchar(45),
	hours TINYINT UNSIGNED,
	minutes TINYINT UNSIGNED,
	PRIMARY KEY (id),
	INDEX workout_cardio_workoutid_idx (workoutid),
	FOREIGN KEY (workoutid) REFERENCES workout(id) ON DELETE CASCADE
);

CREATE TABLE workout_regular (
	id int(11) NOT NULL AUTO_INCREMENT,
	workoutid int(11) NOT NULL,
	name varchar(45),
	weight SMALLINT UNSIGNED,
	sets TINYINT UNSIGNED,
	repetitions TINYINT UNSIGNED,
	PRIMARY KEY (id),
	INDEX workout_regular_workoutid_idx (workoutid),
	FOREIGN KEY (workoutid) REFERENCES workout(id) ON DELETE CASCADE
);

CREATE TABLE workout_dropset (
	id int(11) NOT NULL AUTO_INCREMENT,
	workoutid int(11) NOT NULL,
	name varchar(45),
	PRIMARY KEY (id),
	INDEX workout_dropset_workoutid_idx (workoutid),
	FOREIGN KEY (workoutid) REFERENCES workout(id) ON DELETE CASCADE
);

CREATE TABLE workout_dropset_set (
	id int(11),
	dropsetid int(11) NOT NULL,
	sets TINYINT UNSIGNED,
	weight SMALLINT UNSIGNED,
	INDEX dropsetid_idx (dropsetid),
	FOREIGN KEY (dropsetid) REFERENCES workout_dropset(id) ON DELETE CASCADE
);
--rollback DROP TABLE IF EXISTS workout_dropset_set;
--rollback DROP TABLE IF EXISTS workout_dropset;
--rollback DROP TABLE IF EXISTS workout_regular;
--rollback DROP TABLE IF EXISTS workout_cardio;
--rollback DROP TABLE IF EXISTS workout;