-- Annahme: Profile mit IDs 1 bis 5 sind bereits vorhanden

INSERT INTO post (uuid, message, profile_fk)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Das ist die erste Nachricht.', 1);

INSERT INTO post (uuid, message, profile_fk)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Dies ist eine zweite Beispielnachricht.', 2);

INSERT INTO post (uuid, message, profile_fk)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Hier ist eine dritte Nachricht für das Profil.', 3);

INSERT INTO post (uuid, message, profile_fk)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Vierte Nachricht als Beispielinhalt.', 4);

INSERT INTO post (uuid, message, profile_fk)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Fünfte Nachricht für das letzte Profil.', 5);
