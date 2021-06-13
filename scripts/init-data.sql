INSERT INTO course
(id, name, requirement)
VALUES
(1, 'Java', 'Having a basic knowledge of OOP principles. Familiar with Java and DBMS basics.'),
(2, 'Python', 'Having a basic knowledge of OOP principles. Familiar with Python and DBMS basics.'),
(3, 'Kotlin', 'Having a basic knowledge of OOP principles. Familiar with the basics of Kotlin, Java and DBMS.'),
(4, 'C', 'Having a basic knowledge of OOP principles. Familiar with C and DBMS basics.'),
(5, 'JavaScript', 'Having a basic knowledge of OOP principles. Familiar with JavaScript and DBMS basics.');


INSERT INTO user
(id, username, password, role)
VALUES
(1, 'admin', 'admin', 'ADMIN'),
(2, 'student1', 'student', 'STUDENT'),
(3, 'teacher1', 'teacher', 'TEACHER'),
(4, 'student2', 'student', 'STUDENT'),
(5, 'teacher2', 'teacher', 'TEACHER'),
(6, 'student3', 'student', 'STUDENT'),
(7, 'student4', 'student', 'STUDENT'),
(8, 'student5', 'student', 'STUDENT'),
(9, 'student6', 'student', 'STUDENT'),
(10, 'student7', 'student', 'STUDENT'),
(11, 'teacher3', 'teacher', 'TEACHER');

INSERT INTO student
(user_id, username, review)
VALUES
(2, 'student1', 'student1 is the best'),
(4, 'student2', 'student2 not bad');


INSERT INTO course_users
(course_name, username)
VALUES
('Java', 'student1'),
('Java', 'student2'),
('Python', 'student1'),
('Python', 'student2'),
('Kotlin', 'student2'),
('Kotlin', 'student1'),
('C', 'student2'),
('JavaScript', 'student1'),
('Java', 'teacher1'),
('Python', 'teacher2'),
('Kotlin', 'teacher3'),
('C', 'teacher1'),
('JavaScript', 'teacher1');


INSERT INTO task
(id, task_name, course_name, assignment)
VALUES
(1 ,'First task', 'Java', 'print \'Hello world\' to the console'),
(2, 'Second task', 'Java', 'print \'Hello world\' to the console three times'),
(3, 'First task', 'Python', 'print \'Hello world\' to the console'),
(4, 'Second task', 'Python', 'print \'Hello world\' to the console three times'),
(5, 'First task', 'Kotlin', 'print \'Hello world\' to the console'),
(6, 'Second task', 'Kotlin', 'print \'Hello world\' to the console three times'),
(7, 'First task', 'C', 'print \'Hello world\' to the console'),
(8, 'First task', 'JavaScript', 'print \'Hello world\' to the console');


INSERT INTO homework
(id, task_name, course_name, username, link, mark)
VALUES
(1, 'First task', 'Java', 'student1', 'https://github.com/student1', '0'),
(2, 'First task', 'Java', 'student2', 'https://github.com/student2', '0'),
(3, 'Second task', 'Java', 'student1', 'https://github.com/student1', '0'),
(4, 'First task', 'Python', 'student1', 'https://github.com/student1', '0'),
(5, 'First task', 'Python', 'student2', 'https://github.com/student2', '0'),
(6, 'First task', 'Kotlin', 'student2', 'https://github.com/student2', '0'),
(7, 'First task', 'Kotlin', 'student1', 'https://github.com/student1', '0'),
(8, 'Second task', 'Kotlin', 'student1', 'https://github.com/student1', '0'),
(9, 'First task', 'C', 'student2', 'https://github.com/student2', '0'),
(10, 'First task', 'JavaScript', 'student1', 'https://github.com/student1', '0');