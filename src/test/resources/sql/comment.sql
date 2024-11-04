INSERT INTO comment (uuid, post_fk, profile_fk, content) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 1, 1, 'Great post!');

INSERT INTO comment (uuid, post_fk, profile_fk, content) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 1, 2, 'Interesting read.');

INSERT INTO comment (uuid, post_fk, profile_fk, content) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 2, 1, 'Thanks for sharing!');

INSERT INTO comment (uuid, post_fk, profile_fk, content) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 2, 3, 'I totally agree!');

INSERT INTO comment (uuid, post_fk, profile_fk, content) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 3, 4, 'Looking forward to more posts like this!');
