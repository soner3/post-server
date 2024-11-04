INSERT INTO likes (uuid, post_fk, profile_fk) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 1, 1);

INSERT INTO likes (uuid, post_fk, profile_fk) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 1, 2);

INSERT INTO likes (uuid, post_fk, profile_fk) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 2, 3);

INSERT INTO likes (uuid, post_fk, profile_fk) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 2, 4);

INSERT INTO likes (uuid, post_fk, profile_fk) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 3, 5);
