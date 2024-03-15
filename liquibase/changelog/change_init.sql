--liquibase formatted sql
--changeset tmili:20230101000000_create_db_docker context:RELEASE_0_0_0 labels:
--comment Create Product database for docker only

IF
NOT EXISTS (SELECT SCHEMA_ID FROM sys.schemas WHERE [name] = 'matcher')

BEGIN

EXEC ('CREATE SCHEMA matcher AUTHORIZATION dbo;')

END

-- rollback empty
