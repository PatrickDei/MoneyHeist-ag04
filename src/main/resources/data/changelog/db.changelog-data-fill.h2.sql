INSERT INTO Skill (name, level) VALUES 
    ('Stealing', 8), 
    ('Sneaking', 3),
    ('Looting', 3),
    ('Looting', 5),
    ('Hacking', 9),
    ('Deception', 7),
    ('Stealing', 6),
    ('Looting', 6),
    ('Stealing', 8),
    ('Sneaking', 3),
    ('Looting', 3),
    ('Looting', 5),
    ('Hacking', 9),
    ('Deception', 7),
    ('Stealing', 6),
    ('Looting', 6);

INSERT INTO Requirement (number_of_members, skill_id) VALUES
    (3, 10),
    (2, 11),
    (1, 12),
    (4, 13),
    (3, 14),
    (2, 15),
    (1, 16),
    (4, 17),
    (3, 18),
    (2, 19),
    (1, 20),
    (4, 21),
    (3, 22),
    (2, 23),
    (1, 10),
    (4, 15);
    
INSERT INTO Member (name, sex, email, main_Skill, status, password, role_id) VALUES 
    ('Lewis', 'M', 'lewis@ag04.com', 'Driving', 'AVAILABLE', '$2y$12$xL6lMmtu8y5STc2woRJYDe9rQKmyboj3/7ob9xitLB6sEUvvtPg1u', 2),    
    ('Valterri', 'F', 'valterri@ag04.com', 'Sneaking', 'AVAILABLE', '$2y$12$xL6lMmtu8y5STc2woRJYDe9rQKmyboj3/7ob9xitLB6sEUvvtPg1u', 2),
    ('Max', 'M', 'max@ag04.com', 'Driving', 'AVAILABLE', '$2y$12$xL6lMmtu8y5STc2woRJYDe9rQKmyboj3/7ob9xitLB6sEUvvtPg1u', 2),
    ('Sergio', 'F', 'sergio@ag04.com', 'Stealing', 'AVAILABLE', '$2y$12$xL6lMmtu8y5STc2woRJYDe9rQKmyboj3/7ob9xitLB6sEUvvtPg1u', 2),
    ('Charles', 'M', 'charles@ag04.com', 'Strategy', 'AVAILABLE', '$2y$12$xL6lMmtu8y5STc2woRJYDe9rQKmyboj3/7ob9xitLB6sEUvvtPg1u', 2),
    ('Pierre', 'F', 'pierre@ag04.com', 'Driving', 'AVAILABLE', '$2y$12$xL6lMmtu8y5STc2woRJYDe9rQKmyboj3/7ob9xitLB6sEUvvtPg1u', 2),
    ('Romain', 'F', 'romain@ag04.com', 'Combat', 'RETIRED', '$2y$12$xL6lMmtu8y5STc2woRJYDe9rQKmyboj3/7ob9xitLB6sEUvvtPg1u', 2),
    ('Carlos', 'M', 'carlos@ag04.com', 'Driving', 'AVAILABLE', '$2y$12$xL6lMmtu8y5STc2woRJYDe9rQKmyboj3/7ob9xitLB6sEUvvtPg1u', 2);
    
INSERT INTO Member_Skill (Member_Id, skill_id) VALUES 
    (4, 10),
    (5, 11),
    (6, 12),
    (7, 13),
    (8, 14),
    (9, 15),
    (10, 16),
    (11, 17),
    (12, 18);
    
INSERT INTO Heist (name, location, start_Time, end_Time, status) VALUES
    ('British grand prix', 'Silverstone - Great Britain', NOW() + INTERVAL 20 MINUTE, NOW() + INTERVAL 2 MINUTE, 'PLANNING'),
    ('Hungarian grand prix', 'Hungary', NOW() + INTERVAL 20 MINUTE, NOW() + INTERVAL 1 DAY, 'PLANNING'),
    ('Italian grand prix', 'Monza - Italy', NOW() + INTERVAL 25 MINUTE, NOW() + INTERVAL 1 DAY, 'PLANNING'),
    ('Italian grand prix 2', 'Emilia Romagna - Italy', NOW() + INTERVAL 5 DAY, NOW() + INTERVAL 10 DAY, 'PLANNING'),
    ('USA grand prix', 'Texas - USA', NOW() + INTERVAL 25 DAY, NOW() + INTERVAL 100 DAY, 'PLANNING');
    
INSERT INTO Heist_Requirement (Heist_id, Requirement_id) VALUES
    (6, 15),
    (7, 10),
    (8, 11),
    (9, 18),
    (10, 19),
    (6, 16),
    (7, 12),
    (8, 13),
    (9, 17),
    (10, 9);