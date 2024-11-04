INSERT INTO profile (uuid, user_fk, street, street_number, city, zip_code, country, gender, created_at, updated_at) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 1, 'Main St', 123, 'New York', 10001, 'USA', 'MALE', NOW(), NOW());

INSERT INTO profile (uuid, user_fk, street, street_number, city, zip_code, country, gender, created_at, updated_at) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 2, 'Second St', 456, 'Los Angeles', 90001, 'USA', 'FEMALE', NOW(), NOW());

INSERT INTO profile (uuid, user_fk, street, street_number, city, zip_code, country, gender, created_at, updated_at) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 3, 'Third St', 789, 'Chicago', 60601, 'USA', 'DIVERSE', NOW(), NOW());

INSERT INTO profile (uuid, user_fk, street, street_number, city, zip_code, country, gender, created_at, updated_at) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 4, 'Fourth St', 101, 'Houston', 77001, 'USA', 'MALE', NOW(), NOW());

INSERT INTO profile (uuid, user_fk, street, street_number, city, zip_code, country, gender, created_at, updated_at) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 5, 'Fifth St', 202, 'Phoenix', 85001, 'USA', 'FEMALE', NOW(), NOW());
