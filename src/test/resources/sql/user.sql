INSERT INTO `user` (uuid, email, firstname, lastname, password, username, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, role_fk, created_at, updated_at) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'admin2@example.com', 'Admin', 'User', 'password', 'admin2', 1, 1, 1, 1, 1, NOW(), NOW());

INSERT INTO `user` (uuid, email, firstname, lastname, password, username, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, role_fk, created_at, updated_at) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'userdoihhj@example.com', 'Regular', 'Test1', 'password2', 'user20', 1, 1, 1, 1, 2, NOW(), NOW());

INSERT INTO `user` (uuid, email, firstname, lastname, password, username, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, role_fk, created_at, updated_at) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'moderator@dfhdf.com', 'Mod', 'Test2', 'password3', 'moderator', 1, 1, 1, 1, 2, NOW(), NOW());

INSERT INTO `user` (uuid, email, firstname, lastname, password, username, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, role_fk, created_at, updated_at) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'guest@jrjbrth.com', 'Guest', 'Test3', 'password4', 'guest', 1, 1, 1, 1, 2, NOW(), NOW());

INSERT INTO `user` (uuid, email, firstname, lastname, password, username, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, role_fk, created_at, updated_at) 
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'superadmin@drtj.com', 'Super', 'Test4', 'password5', 'superadmin', 1, 1, 1, 1, 2, NOW(), NOW());
