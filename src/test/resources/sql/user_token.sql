INSERT INTO user_token (user_fk, token, token_type, used, expiry_date) 
VALUES (1, 'token1isedururhg', 'PASSWORD_RESET_TOKEN', 0, DATE_ADD(NOW(), INTERVAL 1 DAY));

INSERT INTO user_token (user_fk, token, token_type, used, expiry_date) 
VALUES (2, 'liisrrthgbboiusorthn', 'USER_ACTIVATION_TOKEN', 0, DATE_ADD(NOW(), INTERVAL 1 DAY));

INSERT INTO user_token (user_fk, token, token_type, used, expiry_date) 
VALUES (3, 'ioiuuserbhg9ious', 'PASSWORD_RESET_TOKEN', 0, DATE_ADD(NOW(), INTERVAL 1 DAY));

INSERT INTO user_token (user_fk, token, token_type, used, expiry_date) 
VALUES (4, 'piuiuahergiuhet', 'USER_ACTIVATION_TOKEN', 0, DATE_ADD(NOW(), INTERVAL 1 DAY));

INSERT INTO user_token (user_fk, token, token_type, used, expiry_date) 
VALUES (5, 'ioaiubrgupiouiuo', 'PASSWORD_RESET_TOKEN', 0, DATE_ADD(NOW(), INTERVAL 1 DAY));
