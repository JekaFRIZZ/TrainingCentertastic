CREATE DATABASE training_centertastic_db;

USE training_centertastic_db;

CREATE TABLE user (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      username VARCHAR(50) NOT NULL UNIQUE CHECK(username !=''),
                      password VARCHAR(50) NOT NULL CHECK(password !=''),
                      role ENUM('ADMIN', 'TEACHER', 'STUDENT') NOT NULL,
                      PRIMARY KEY (id)
);

CREATE TABLE student (
                         user_id BIGINT NOT NULL AUTO_INCREMENT,
                         username varchar(50),
                         review TEXT,
                         PRIMARY KEY (user_id),
                         foreign key (user_id) references user(id),
                         foreign key (username) references user(username)
);

CREATE TABLE course (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        name varchar(50),
                        requirement TEXT,
                        PRIMARY KEY (id)
);