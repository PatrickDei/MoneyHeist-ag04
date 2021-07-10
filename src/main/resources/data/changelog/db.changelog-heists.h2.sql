CREATE TABLE IF NOT EXISTS Heist (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  location VARCHAR(255) NOT NULL,
  start_Time DATETIME NOT NULL,
  end_Time DATETIME NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX name_UNIQUE (name)
);


CREATE TABLE IF NOT EXISTS Heist_requirement (
  id BIGINT NOT NULL AUTO_INCREMENT,
  number_of_members INT NOT NULL,
  skill_id INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_Heist_requirement_Skill1
    FOREIGN KEY (skill_id)
    REFERENCES Skill (id)
);


CREATE TABLE IF NOT EXISTS Heist_Heist_requirement (
  Heist_id BIGINT NOT NULL,
  Heist_requirement_id BIGINT NOT NULL,
  PRIMARY KEY (Heist_id, Heist_requirement_id),
  CONSTRAINT fk_Heist_has_Heist_requirement_Heist1
    FOREIGN KEY (Heist_id)
    REFERENCES Heist (id),
  CONSTRAINT fk_Heist_has_Heist_requirement_Heist_requirement1
    FOREIGN KEY (Heist_requirement_id)
    REFERENCES Heist_requirement (id)
);
