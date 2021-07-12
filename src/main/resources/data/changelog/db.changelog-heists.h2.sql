CREATE TABLE IF NOT EXISTS Heist (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  location VARCHAR(255) NOT NULL,
  start_Time DATETIME NOT NULL,
  end_Time DATETIME NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX name_UNIQUE (name)
);


CREATE TABLE IF NOT EXISTS Requirement (
  id BIGINT NOT NULL AUTO_INCREMENT,
  number_of_members INT NOT NULL,
  skill_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_Requirement_Skill1
    FOREIGN KEY (skill_id)
    REFERENCES Skill (id)
);


CREATE TABLE IF NOT EXISTS Heist_Requirement (
  Heist_id BIGINT NOT NULL,
  Requirement_id BIGINT NOT NULL,
  PRIMARY KEY (Heist_id, Requirement_id),
  CONSTRAINT fk_Heist_has_Requirement_Heist1
    FOREIGN KEY (Heist_id)
    REFERENCES Heist (id),
  CONSTRAINT fk_Heist_has_Requirement_Requirement1
    FOREIGN KEY (Requirement_id)
    REFERENCES Requirement (id)
);
