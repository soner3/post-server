# Datenbanken im Buisiness Engineering Kontext Prüfungsabgabe

## Requirements

1. Docker muss auf dem Gerät installiert sein
   (Docker Engine + Docker Compose Plugin bei Linux und Docker Desktop bei MacOS oder Windows)
2. Das JDK (Mindestens Version 17) muss installiert sein
   (JAVA_HOME und PATH müssen in den Systemumgebungsvariablen vorhanden sein)
3. Folgende Ports dürfen NICHT besetzt sein
   (Ports können in der jewiligen properties und compose datei gändert werden):
   1. 8000 - Tomcat
   2. 3306 - MySQL
   3. 1025 - Mailpit Email-Server
   4. 8025 - Mailpit UI (Hier können die Mails angesehen werden die gesendet werden)

## Starten der Anwendung

Zum starten der Anwendung können folgende Befehle ausgeführt werden:

1. make run
2. ./mvnw clean install spring-boot:run (Bei Windows: mvnw clean install spring-boot:run)

## OpenAPI

Die OpenAPI Dokumentation findet mman unter folgendem Link:
http://localhost:8000/doc/swagger-ui/index.html

## WICHTIGE INFO

Beim ersten starten der Anwendung werden 2 User und die Rolen in die Datenbank eingetragen.
Der erste ist ein Admin der andere ist nur ein normaler User.
Die Anmeldedaten befinden sich in folgender Datei:
src/main/java/net/sonerapp/db_course_project/infrastructure/security/SecurityConfig.java

Der Admin hat kein Profil.
Der User muss aktiviert werden. Dazu wird eine Aktivierungsmail erstellt,
welche den die Url mit dem Token hat. Der Token muss herauskopiert werden und
anschließend beim Aktivierungsendpunkt gesendet werden. Dann kann man sich mit dem anmelden.
Das gilt für alle User die erstellt werden mit Ausnahme vom erstellten Admin User.

Jeder Endpunkt der mit "**/public/**" und der "/jwt/create" versehen ist kann verwendet werden, auch wenn man nicht angemeldet ist. Ansonsten muss man sich beim "/jwt/create" Endpunkt anmelden, um die
Token zu erhalten (werden als HTTP-Only-Cookies gespeichert und sind im Browser somit einsehbar).
Beim "/jwt/refresh" Endpunkt kann ein beliebiger Wert als cookie eingegeben werden, denn der wird
mit dem echten refresh Token beim senden ersetzt.

Bei den Pagination Endpunkten kann das Objekt das gesendet wird auch leer sein
("{}" eine leere Klammer im Request body haben), dann werden die default werte genommen.
(
{
"page": 0,
"size": 10,
"sort": ""
}
)
