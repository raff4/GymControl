--liquibase formatted sql

--changeset VitorBertazoli:initial (dbms:mysql failOnError:true)
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
);
--rollback drop table user