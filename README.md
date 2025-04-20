# Databases in the Business Engineering Context – Exam Submission

---

## ✅ Requirements

Before running the application, ensure the following prerequisites are met:

1. **Docker** must be installed  
   - Linux: Docker Engine + Docker Compose plugin  
   - macOS / Windows: Docker Desktop

2. **Java Development Kit (JDK)** – version 17 or higher  
   - `JAVA_HOME` and `PATH` must be properly set in your system environment variables

3. The following **ports must be free** (can be changed in `application.properties` and `docker-compose.yml` if necessary):
   - `8000` – Tomcat
   - `3306` – MySQL
   - `1025` – Mailpit SMTP server
   - `8025` – Mailpit web UI (for viewing sent emails)

---

## 🚀 Starting the Application

You can start the application with one of the following commands:

```bash
make run
```

Or if you prefer to run it manually:

```bash
./mvnw clean install spring-boot:run
```

> On Windows, use: `mvnw clean install spring-boot:run`

---

## 📘 OpenAPI Documentation

The OpenAPI documentation is available at:

[http://localhost:8000/doc/swagger-ui/index.html](http://localhost:8000/doc/swagger-ui/index.html)

---

## ⚠️ IMPORTANT INFORMATION

On the first application startup, two users and their corresponding roles will be inserted into the database:

- One **Admin user** (without a profile)
- One **regular User**

You can find the login credentials in the following file:  
`src/main/java/net/sonerapp/db_course_project/infrastructure/security/SecurityConfig.java`

### User Activation

- The regular user must be **activated** via email.
- An activation email with a token will be sent via Mailpit.
- Copy the token and send it to the activation endpoint manually.
- Only then can the user log in.

> ⚠️ This activation flow applies to all users created via the system — **except the pre-created admin**, who is already activated.

---

## 🔓 Public & Authenticated Endpoints

- Any endpoints containing `**/public/**` or `/jwt/create` are **accessible without authentication**.
- To access protected endpoints:
  1. First call `/jwt/create` with valid credentials to receive your tokens.
  2. Tokens are stored as **HTTP-only cookies** and accessible via the browser.

### Refresh Token Behavior

- The `/jwt/refresh` endpoint accepts **any value** for the refresh token cookie.
- That cookie will be automatically replaced with a valid token upon the next request if available.

---

## 📄 Pagination Endpoints

You can send an empty JSON object `{}` as the request body when using paginated endpoints.  
If you do so, the default values will be applied:

```json
{
  "page": 0,
  "size": 10,
  "sort": ""
}
```
