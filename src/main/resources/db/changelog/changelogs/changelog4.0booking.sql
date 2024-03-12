--changeset mabere:4

CREATE TABLE booking
(
    booking_id SERIAL PRIMARY KEY,
    client VARCHAR NOT NULL REFERENCES client(username),
    movie_id INT NOT NULL,
    seats_id VARCHAR(255),
    price INTEGER


);