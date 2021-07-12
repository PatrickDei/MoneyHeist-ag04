ALTER TABLE Heist ADD COLUMN Status VARCHAR(255) NOT NULL;

CREATE TABLE Heist_Member(
    Heist_id BIGINT NOT NULL,
    Member_id BIGINT NOT NULL,
    PRIMARY KEY (Heist_id, Member_id),
      CONSTRAINT fk_Heist_has_Heist_member_Heist1
        FOREIGN KEY (Heist_id)
        REFERENCES Heist (id),
      CONSTRAINT fk_Heist_has_Heist_member_Heist_member1
        FOREIGN KEY (Member_id)
        REFERENCES Member (id)
);

INSERT INTO Heist (name, location, start_Time, end_Time, status) VALUES
    ('First heist', 'France', NOW() + INTERVAL 1 MINUTE, NOW() + INTERVAL 2 MINUTE, 'PLANNING'),
    ('Second heist', 'Spain', NOW() + INTERVAL 5 MINUTE, NOW() + INTERVAL 1 DAY, 'PLANNING'),
    ('Third heist', 'Belgium', NOW() + INTERVAL 5 MINUTE, NOW() + INTERVAL 1 DAY, 'PLANNING'),
    ('Fourth heist', 'United Kingdom', NOW() + INTERVAL 5 MINUTE, NOW() + INTERVAL 1 DAY, 'PLANNING'),
    ('Fifth heist', 'USA', NOW() + INTERVAL 5 MINUTE, NOW() + INTERVAL 1 DAY, 'PLANNING');

INSERT INTO Skill (name, level) VALUES ('Hacking', 1);
INSERT INTO Skill (name, level) VALUES ('Deception', 2);
INSERT INTO Skill (name, level) VALUES ('Stealing', 3);
INSERT INTO Skill (name, level) VALUES ('Looting', 3);

INSERT INTO Requirement (number_of_members, skill_id) VALUES
    (3, 6),
    (2, 7),
    (1, 8),
    (4, 9);

INSERT INTO Heist_Requirement (Heist_id, Requirement_id) VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 2);