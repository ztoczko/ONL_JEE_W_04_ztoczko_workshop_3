DROP DATABASE IF EXISTS workshop2;
CREATE DATABASE workshop2 CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci;
USE workshop2;

CREATE TABLE users (
                       id INT UNSIGNED AUTO_INCREMENT,
                       email VARCHAR (255) UNIQUE NOT NULL,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(60) NOT NULL,
                       PRIMARY KEY (id)
);

CREATE TABLE admins (
                       email VARCHAR (255) NOT NULL,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(60) NOT NULL
);

INSERT INTO admins VALUES ('admin@admin.pl', 'admin', '$2a$10$J018nhvCD9A71LSuStjmqOkXD10//.N1H7vR2lsSoYzQTY7zPXDpe');
