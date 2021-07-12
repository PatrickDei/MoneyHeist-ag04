ALTER TABLE Member ADD COLUMN Password VARCHAR(255);
ALTER TABLE Member ADD COLUMN Role_id INT;

CREATE TABLE Role(
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO Role (name) VALUES ('ROLE_ORGANISER');
INSERT INTO Role (name) VALUES ('ROLE_MEMBER');

UPDATE Member SET Role_id = 1;
UPDATE Member SET password = '$2y$12$xL6lMmtu8y5STc2woRJYDe9rQKmyboj3/7ob9xitLB6sEUvvtPg1u';