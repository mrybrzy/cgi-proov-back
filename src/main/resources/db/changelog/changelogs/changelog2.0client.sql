--changeset mabere:2

CREATE TABLE client
(
    username  VARCHAR(30)  PRIMARY KEY,
    name      VARCHAR(30)  NOT NULL,
    password  VARCHAR(255) NOT NULL
);

INSERT INTO client (username, name, password)
VALUES ('alice123', 'Alice', 'password123'),
       ('bob456', 'Bob', 'securePass'),
       ('charlie789', 'Charlie', 'letmein'),
       ('eva111', 'Eva', 'p@ssw0rd'),
       ('frank222', 'Frank', 'secretPass'),
       ('zara333', 'Zara', 'zaraPass');