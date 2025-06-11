CREATE USER 'user'@'127.0.0.1' identified by 'ces2025';

use socratesdb;

drop table users;

CREATE TABLE users (
                       id_user serial primary key,
                       name varchar(150) not null,
                       lastname varchar(180) not null,
                       birthdate date not null,
                       email varchar(150) not null,
                       is_active bool not null default false,
                       phone varchar(15) not null,
                       gender enum('male', 'female') not null,
                       created_at timestamp not null default current_timestamp,
                       updated_at timestamp not null default current_timestamp on update current_timestamp,
                       deleted_at timestamp,
                       UNIQUE(email)
);

alter table users add column password varchar(40) not null;

SELECT * FROM users;

INSERT INTO users (name, lastname, birthdate, email, gender, phone)
VALUES ('Jacobo', 'Uribe Domínguez', '2003-10-04 00:00:00', 'jacouribe2003@gmail.com', 'female', '3054332172');

update users set password = sha1('jeyadkspadja#2321Das-dsadaso312') where id_user = 1;

DROP TABLE subjects;
CREATE TABLE IF NOT EXISTS subjects (
                                        id INT(5) primary key,
    code varchar(6) not null unique,
    name varchar(300) not null,
    description text,
    credits INT(1) not null,
    faculty enum('Communication', 'Engineering', 'basicSciences', 'schoolOfSports'),
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at timestamp
    ) engine = innoDb DEFAULT CHARSET=utf8;

SELECT * FROM subjects;

ALTER TABLE enrollments ADD CONSTRAINT F

DROP TABLE IF EXISTS enrollments;
CREATE TABLE IF NOT EXISTS enrollments (
                                           id_user bigint unsigned,
                                           id_subject int(5),
    date_enrollment TIMESTAMP not null,
    status ENUM('active', 'inactive', 'canceled', 'finalized', 'scheduled') not null default 'scheduled',
    term varchar(6) not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at timestamp,
    CONSTRAINT PRIMARY KEY (id_user, id_subject),
    CONSTRAINT FOREIGN KEY (id_user) references users(id_user) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT FOREIGN KEY (id_subject) references subjects(id) ON UPDATE CASCADE ON DELETE RESTRICT
    );


SELECT * FROM users;


INSERT INTO users (name, lastname, birthdate, email, is_active, phone, gender, password)
VALUES
    ('Laura', 'González', '1995-04-10', 'laura.gonzalez@example.com', true, '3011234567', 'female', sha1('contrasena123')),
    ('Carlos', 'Ramírez', '1992-09-22', 'carlos.ramirez@example.com', true, '3107654321', 'male', sha1('contrasena321')),
    ('Ana', 'Martínez', '1998-01-15', 'ana.martinez@example.com', false, '3115551234', 'female', sha1('contrasena111'));

INSERT INTO subjects (code, name, description, credits, faculty)
VALUES
    ('CS101', 'Introduction to Programming', 'Learn basic programming concepts.', 3, 'Engineering'),
    ('CM201', 'Media Ethics', 'Study ethical challenges in communication.', 2, 'Communication'),
    ('BS301', 'Physics I', 'Classical mechanics and basic physics principles.', 4, 'basicSciences');

INSERT INTO enrollments (id_user, id_subject, date_enrollment, status, term, created_at, updated_at, deleted_at)
VALUES
    (7, 102, '2025-05-09 00:00:00', 'scheduled', '202501'),
    (7, 103, '2025-05-09 00:00:00', 'scheduled', '202501');