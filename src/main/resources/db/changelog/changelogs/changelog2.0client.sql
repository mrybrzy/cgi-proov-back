--changeset mabere:2

CREATE TABLE client
(
    username  VARCHAR(30)  PRIMARY KEY,
    name      VARCHAR(30)  NOT NULL,
    password  VARCHAR(255) NOT NULL
);

INSERT INTO client(username, name, password)
VALUES ('mari@test.com', 'mari', '$2a$10$dLAzMJURDA3zL6WREwwe3.ER/SVU88HAGYclu7/TI9plxXIxkbqnq')