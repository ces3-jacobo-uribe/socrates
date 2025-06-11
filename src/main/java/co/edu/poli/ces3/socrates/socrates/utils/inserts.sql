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