INSERT INTO Skill (name, level) VALUES ('Hacking', 1);
INSERT INTO Skill (name, level) VALUES ('Deception', 2);
INSERT INTO Skill (name, level) VALUES ('Stealing', 3);
INSERT INTO Skill (name, level) VALUES ('Looting', 3);
INSERT INTO Skill (name, level) VALUES ('Stealth', 4);


INSERT INTO Member (name, sex, email, main_Skill, status) VALUES ('Bob', 'M', 'bobby@ag04.com', 'Combat', 'AVAILABLE');
INSERT INTO Member (name, sex, email, main_Skill, status) VALUES ('Michael', 'M', 'michael@ag04.com', 'Combat', 'EXPIRED');
INSERT INTO Member (name, sex, email, main_Skill, status) VALUES ('Bono', 'M', 'bono@ag04.com', 'Stealth', 'RETIRED');
INSERT INTO Member (name, sex, email, main_Skill, status) VALUES ('Christian', 'M', 'christian@ag04.com', 'Sneak', 'INCARCERATED');

INSERT INTO Member_Skill (Member_Id, skill_id) VALUES (1, 1);
INSERT INTO Member_Skill (Member_Id, skill_id) VALUES (1, 2);
INSERT INTO Member_Skill (Member_Id, skill_id) VALUES (2, 3);
INSERT INTO Member_Skill (Member_Id, skill_id) VALUES (3, 3);
