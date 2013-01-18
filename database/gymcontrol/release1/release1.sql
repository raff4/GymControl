--liquibase formatted sql

--changeset VitorBertazoli:release1 (dbms:mysql failOnError:true)
SELECT 1;
--rollback SELECT 1;