# Student Management System backend

Spring Boot REST API using Java 21, Gradle, Spring Data JPA, validation, and Flyway migrations.

## Run locally

Install Java 21 and run the MySQL service. Create the schema once (this does not drop or reset existing data):

```sql
CREATE DATABASE student_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

Set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` in the process environment as needed. Defaults target `jdbc:mysql://localhost:3306/student_management` and user `root`; the password default is `root` for the requested local setup. Do not commit real credentials. Then run:

```powershell
.
gradlew.bat bootRun
```

The API listens on `http://localhost:8080`. Set `CORS_ALLOWED_ORIGIN` when the frontend uses a different origin; the default is `http://localhost:5173`.

## API

- `GET /api/students?page=0&size=20&search=&status=ACTIVE&course=&sortBy=enrollmentDate&sortDirection=desc`
- `GET /api/students/{id}`
- `POST /api/students`
- `PUT /api/students/{id}`
- `DELETE /api/students/{id}`
- `GET /api/students/statistics`

Student JSON requires `firstName`, `lastName`, `email`, `course`, `enrollmentDate` (ISO date), and `status` (`ACTIVE` or `INACTIVE`). Optional fields: `phone`, `dateOfBirth`, `gender`, `address`. List responses use Spring's zero-based `Page` format. Flyway creates the table on first startup; Hibernate validates the resulting schema.

## Current environment notes

The React project directory and the separate `D:\Tech Works\Student Management System` directory described in the supplied brief were not present in this environment, so this checkout contains only the backend. MySQL at `localhost:3306` was also unreachable during inspection. Start/configure MySQL and provide the frontend directory to complete live integration. The database migration is additive and does not reset existing data.
