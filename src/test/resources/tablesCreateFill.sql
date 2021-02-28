DROP TABLE IF EXISTS groups, students, courses, enrollments;
CREATE TABLE groups (group_id int, group_name varchar);
INSERT INTO groups (group_id, group_name) VALUES (1,'one-11'), (2,'two-22');

CREATE TABLE students (student_id int, group_id int, first_name varchar, last_name varchar);
INSERT INTO students (student_id, group_id, first_name, last_name) VALUES (1, 1, 'AAA', 'AAA'), (2, 2, 'BBB', 'BBB');

--CREATE TABLE courses (course_id int, course_name varchar, course_description varchar);
CREATE TABLE courses (course_id SERIAL PRIMARY KEY, course_name varchar, course_description varchar);
--INSERT INTO courses (course_id, course_name, course_description) VALUES (1, 'MATH', 'MATH'), (2, 'DANCE', 'DANCE');
INSERT INTO courses (course_name, course_description) VALUES ('MATH', 'MATH'), ('DANCE', 'DANCE');

CREATE TABLE enrollments (enrollment_id int, course_id int, student_id int);
INSERT INTO enrollments (enrollment_id, course_id, student_id) VALUES (1, 1, 1), (2, 2, 2);