DELETE FROM Heist_Member_Skill;
DELETE FROM Heist_Member;
DELETE FROM Skill;

INSERT INTO Skill (name, level) VALUES ('Hacking', 1);
INSERT INTO Skill (name, level) VALUES ('Deception', 2);
INSERT INTO Skill (name, level) VALUES ('Stealing', 3);
INSERT INTO Skill (name, level) VALUES ('Looting', 3);
INSERT INTO Skill (name, level) VALUES ('Stealth', 4);


INSERT INTO Heist_Member (name, sex, email, main_Skill, status) VALUES ('Bob', 'M', 'bobby@ag04.com', 'Combat', 'AVAILABLE');
INSERT INTO Heist_Member (name, sex, email, main_Skill, status) VALUES ('Michael', 'M', 'michael@ag04.com', 'Combat', 'EXPIRED');
INSERT INTO Heist_Member (name, sex, email, main_Skill, status) VALUES ('Bono', 'M', 'bono@ag04.com', 'Stealth', 'RETIRED');
INSERT INTO Heist_Member (name, sex, email, main_Skill, status) VALUES ('Christian', 'M', 'christian@ag04.com', 'Sneak', 'INCARCERATED');

INSERT INTO Heist_Member_Skill (Heist_Member_Id, name, level) VALUES (1, 'Hacking', 1);
INSERT INTO Heist_Member_Skill (Heist_Member_Id, name, level) VALUES (1, 'Deception', 2);
INSERT INTO Heist_Member_Skill (Heist_Member_Id, name, level) VALUES (2, 'Stealing', 3);
INSERT INTO Heist_Member_Skill (Heist_Member_Id, name, level) VALUES (3, 'Looting', 3);
