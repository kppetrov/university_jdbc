DROP TABLE IF EXISTS course_group;
DROP TABLE IF EXISTS lessons;
DROP TABLE IF EXISTS periods;
DROP TABLE IF EXISTS classrooms;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS teachers;
DROP TABLE IF EXISTS groups;

CREATE TABLE classrooms (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(25)    
);

CREATE TABLE groups (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(25),
    UNIQUE (name)
);

CREATE TABLE courses (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(25)
);

CREATE TABLE periods (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(25),
    start_time TIME,
    end_time TIME
);

CREATE TABLE students (
    id SERIAL PRIMARY KEY NOT NULL,
    group_id INT,
    first_name VARCHAR(25) NOT NULL,
    last_name VARCHAR(25) NOT NULL,
    gender VARCHAR(6),
    birthdate DATE,
    FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE SET NULL
);

CREATE TABLE teachers (
    id SERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR(25) NOT NULL,
    last_name VARCHAR(25) NOT NULL,
    gender VARCHAR(6),
    birthdate DATE
);

CREATE TABLE lessons
(
    id SERIAL PRIMARY KEY NOT NULL,
    course_id INT NOT NULL,
    teacher_id INT NOT NULL,
    classroom_id INT NOT NULL,
    period_id INT NOT NULL,
    date DATE,
    FOREIGN KEY (course_id) REFERENCES courses (id) ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES teachers (id) ON DELETE CASCADE,
    FOREIGN KEY (classroom_id) REFERENCES classrooms (id) ON DELETE CASCADE,
    FOREIGN KEY (period_id) REFERENCES periods (id) ON DELETE CASCADE,
    UNIQUE (date,period_id,classroom_id),
    UNIQUE (date,period_id,teacher_id)
);

CREATE TABLE course_group
(
    course_id INT NOT NULL,
    group_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES courses (id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE CASCADE,
    UNIQUE (course_id,group_id)
);