DROP TABLE IF EXISTS groups, students, courses, enrollments;
CREATE TABLE groups (group_id SERIAL PRIMARY KEY, group_name varchar);
CREATE TABLE students (student_id SERIAL PRIMARY KEY, group_id int, first_name varchar, last_name varchar, FOREIGN KEY
(group_id) REFERENCES groups(group_id) ON DELETE SET NULL);
CREATE TABLE courses (course_id SERIAL PRIMARY KEY, course_name varchar, course_description varchar);
CREATE TABLE enrollments (enrollment_id SERIAL PRIMARY KEY, course_id int NOT NULL, student_id int NOT NULL,
FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE,
FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE);