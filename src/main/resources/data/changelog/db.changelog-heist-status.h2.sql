ALTER TABLE Heist ADD COLUMN Status VARCHAR(255) NOT NULL;

CREATE TABLE Heist_Heist_Member(
    Heist_id BIGINT NOT NULL,
    Member_id BIGINT NOT NULL,
    PRIMARY KEY (Heist_id, Member_id),
      CONSTRAINT fk_Heist_has_Heist_member_Heist1
        FOREIGN KEY (Heist_id)
        REFERENCES Heist (id),
      CONSTRAINT fk_Heist_has_Heist_member_Heist_member1
        FOREIGN KEY (Member_id)
        REFERENCES Heist_Member (id)
);

INSERT INTO Heist (name, location, start_Time, end_Time, status) VALUES
    ('First heist', 'France', NOW() + INTERVAL 1 MINUTE, NOW() + INTERVAL 2 MINUTE, 'PLANNING'),
    ('Second heist', 'Spain', NOW() + INTERVAL 5 MINUTE, NOW() + INTERVAL 1 DAY, 'PLANNING'),
    ('Third heist', 'Belgium', NOW() + INTERVAL 5 MINUTE, NOW() + INTERVAL 1 DAY, 'PLANNING'),
    ('Fourth heist', 'United Kingdom', NOW() + INTERVAL 5 MINUTE, NOW() + INTERVAL 1 DAY, 'PLANNING'),
    ('Fifth heist', 'USA', NOW() + INTERVAL 5 MINUTE, NOW() + INTERVAL 1 DAY, 'PLANNING');

INSERT INTO Heist_requirement (number_of_members, skill_id) VALUES
    (3, 1),
    (2, 2),
    (1, 3),
    (4, 3);

INSERT INTO Heist_Heist_requirement (Heist_id, Heist_requirement_id) VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 2);