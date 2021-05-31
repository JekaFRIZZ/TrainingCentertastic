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
                          task_name varchar(50),
                          username varchar(50),
                          course_name varchar(50),
                          link varchar(50),
                          mark int constraint check(mark between 0 and 10),
                          review TEXT,
                          primary key(id)
);