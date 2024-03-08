--changeset mabere:3

CREATE TABLE seats (
    seat_id SERIAL PRIMARY KEY,
    movie_id INT NOT NULL REFERENCES movies (movie_id),
    seat_number INT,
    is_booked BOOLEAN DEFAULT FALSE,
    client VARCHAR(30)

);

INSERT INTO seats (movie_id, seat_number, is_booked, client)
SELECT
    movie_id,
    seat_number,
    is_booked,
    CASE WHEN is_booked THEN (SELECT username FROM client ORDER BY random() LIMIT 1) ELSE NULL END AS client
FROM
    (SELECT
         movie_id,
         seat_number,
         CASE WHEN random() BETWEEN 0.1 AND 0.6 THEN TRUE ELSE FALSE END AS is_booked
     FROM
         generate_series(1, 24) AS movie_id
             CROSS JOIN
         generate_series(1, 70) AS seat_number) AS subquery




