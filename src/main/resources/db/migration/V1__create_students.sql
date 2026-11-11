CREATE TABLE students (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(80) NOT NULL,
    last_name VARCHAR(80) NOT NULL,
    email VARCHAR(254) NOT NULL,
    phone VARCHAR(40),
    date_of_birth DATE,
    gender VARCHAR(30),
    address VARCHAR(500),
    course VARCHAR(120) NOT NULL,
    enrollment_date DATE NOT NULL,
    status VARCHAR(16) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_students_email UNIQUE (email)
);
