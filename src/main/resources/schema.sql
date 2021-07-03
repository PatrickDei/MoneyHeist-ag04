CREATE TABLE IF NOT EXISTS Skill(
    name VARCHAR(255) NOT NULL,
    level INT NOT NULL,
    PRIMARY KEY (name, level)
);

CREATE TABLE IF NOT EXISTS Heist_Member(
    id IDENTITY,
    name VARCHAR(255) NOT NULL,
    sex CHAR NOT NULL,
    email VARCHAR(255) NOT NULL,
    main_Skill VARCHAR(255),
    status VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Heist_Member_Skill(
    Heist_Member_Id INT,
    name VARCHAR(255),
    level INT,
      FOREIGN KEY (Heist_Member_Id) REFERENCES Heist_Member(id),
      FOREIGN KEY (name, level) REFERENCES Skill(name, level)
);