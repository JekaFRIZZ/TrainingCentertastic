CREATE DATABASE training_centertastic_db;

USE training_centertastic_db;

CREATE TABLE user (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      username VARCHAR(50) NOT NULL UNIQUE CHECK(username !=''),
                      password VARCHAR(50) NOT NULL CHECK(password !=''),
                      role ENUM('ADMIN', 'TEACHER', 'STUDENT') NOT NULL,
                      PRIMARY KEY (id)
);

CREATE TABLE course (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        name varchar(50),
                        requirement TEXT,
                        PRIMARY KEY (id)
);

create table course_users (
                              id BIGINT NOT NULL AUTO_INCREMENT,
                              course_name varchar(50),
                              username varchar(50),
                              primary key(id)
);

create table task (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      task_name varchar(50),
                      course_name varchar(50),
                      assignment TEXT,
                      PRIMARY KEY (id)
);

CREATE TABLE homework (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          task_name VARCHAR(50),
                          username VARCHAR(50),
                          course_name VARCHAR(50),
                          link VARCHAR(50) DEFAULT '',
                          mark INT CONSTRAINT CHECK(mark BETWEEN 0 AND 10) DEFAULT 0,
                          review TEXT,
                          PRIMARY KEY(id)
);