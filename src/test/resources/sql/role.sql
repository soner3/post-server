INSERT INTO role (uuid, rolename) VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'ROLE_ADMIN');
INSERT INTO role (uuid, rolename) VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'ROLE_USER');
