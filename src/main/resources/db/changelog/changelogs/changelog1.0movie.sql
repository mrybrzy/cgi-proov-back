--changeset mabere:1

CREATE TABLE movies
(
    movie_id     SERIAL PRIMARY KEY,
    movie_name   VARCHAR(50)  NOT NULL,
    genre        VARCHAR(50),
    age_limit    BIGINT,
    language     VARCHAR(3) NOT NULL,
    start_time   TIME,
    run_time     TIME,
    price        INT DEFAULT 7,
    image        VARCHAR      NOT NULL,
    description  VARCHAR(255) NOT NULL
);

-- action comedy drama thriller romance fantasy adventure

--ENG
INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Avengers', 'Action, Fantasy, Drama', 12, 'ENG', '11:00:00', '2:40:00', 'https://m.media-amazon.com/images/I/81Q3-wGudPL._AC_UF894,1000_QL80_.jpg', 'A group of superheroes team up to save the world.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Inception', 'Action, Thriller, Fantasy, Adventure', 12, 'ENG', '15:30:00', '2:30:00', 'https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p7825626_p_v8_af.jpg', 'A mind-bending heist thriller directed by Christopher Nolan, where skilled thieves enter people''s dreams to steal their deepest secrets. The film explores the layers of reality and challenges the boundaries of the subconscious mind.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Dark Knight', 'Action, Drama, Thriller', 12, 'ENG', '16:00:00', '2:14:00', 'https://images.moviesanywhere.com/bd47f9b7d090170d79b3085804075d41/c6140695-a35f-46e2-adb7-45ed829fc0c0.jpg', 'Christopher Nolan''s gripping Batman sequel features Christian Bale as the caped crusader, facing off against the iconic Joker, portrayed by Heath Ledger. The film delves into the moral complexities of heroism and villainy.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Pulp Fiction', 'Comedy, Drama, Thriller', 12, 'ENG', '12:00:00', '2:55:00', 'https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p15684_p_v10_ag.jpg', 'Directed by Quentin Tarantino, "Pulp Fiction" is a nonlinear narrative that weaves together multiple interconnected stories involving crime, redemption, and dark humor. The film''s unconventional structure and memorable dialogue became iconic in the 1990s.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Matrix', 'Action, Thriller, Fantasy, Adventure', 15, 'ENG', '12:30:00', '2:00:00', 'https://m.media-amazon.com/images/I/613ypTLZHsL._AC_UF894,1000_QL80_.jpg', 'A groundbreaking sci-fi action film directed by the Wachowskis, "The Matrix" follows the journey of Neo (Keanu Reeves) as he discovers the truth about reality and battles against sentient machines that control human minds.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Forrest Gump', 'Comedy, Drama, Romance', 15, 'ENG', '19:00:00', '2:10:00', 'https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p15829_v_v13_aa.jpg', 'Directed by Robert Zemeckis, this heartwarming film stars Tom Hanks as Forrest Gump, a man with a low IQ who inadvertently influences several defining moments in American history. The story explores themes of love, destiny, and the power of simplicity.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Godfather', 'Drama', 15, 'ENG', '20:00:00', '1:50:00', 'https://static0.srcdn.com/wordpress/wp-content/uploads/2023/05/the-godfather-poster.jpeg', 'Directed by Francis Ford Coppola, "The Godfather" is a cinematic masterpiece that follows the Corleone family''s patriarch, Vito Corleone (Marlon Brando), and his son Michael (Al Pacino) as they navigate the treacherous world of organized crime.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Silence of the Lambs', 'Horror', 15, 'ENG', '22:30:00', '2:30:00', 'https://s3.amazonaws.com/criterion-production/films/46014912ca0c20592c472f5bbe33e4f8/L9AQ95Y7z2vOIUf1ntwvpZsZx3kp8B_large.jpg', 'A psychological thriller directed by Jonathan Demme, "The Silence of the Lambs" follows FBI trainee Clarice Starling as she seeks the help of imprisoned cannibalistic serial killer Hannibal Lecter (Anthony Hopkins) to catch another killer on the loose.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Titanic', 'Drama, Romance', 18, 'ENG', '14:45:00', '2:47:00', 'https://m.media-amazon.com/images/M/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_FMjpg_UX1000_.jpg', 'Directed by James Cameron, "Titanic" is an epic romance and disaster film that follows the ill-fated love story between Jack (Leonardo DiCaprio) and Rose (Kate Winslet) aboard the luxurious but doomed ship.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Fight Club', 'Action, Drama', 18, 'ENG', '15:15:00', '2:38:00', 'https://m.media-amazon.com/images/M/MV5BMmEzNTkxYjQtZTc0MC00YTVjLTg5ZTEtZWMwOWVlYzY0NWIwXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg', 'Directed by David Fincher, "Fight Club" is a dark and provocative film that delves into the psyche of an unreliable narrator (Edward Norton) who forms an underground fight club with the charismatic and anarchistic Tyler Durden (Brad Pitt)');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Inglorious Bastards', 'Action, Drama', 18, 'ENG', '11:30:00', '1:30:00', 'https://m.media-amazon.com/images/M/MV5BOTJiNDEzOWYtMTVjOC00ZjlmLWE0NGMtZmE1OWVmZDQ2OWJhXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_FMjpg_UX1000_.jpg', 'Quentin Tarantino''s alternate history war film is a unique blend of drama and dark comedy. Set in Nazi-occupied France, it follows a group of Jewish-American soldiers known as the Basterds as they plan to assassinate high-ranking Nazi officials.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Social Network', 'Drama', 18, 'ENG', '18:00:00', '1:55:00', 'https://upload.wikimedia.org/wikipedia/en/8/8c/The_Social_Network_film_poster.png', 'Directed by David Fincher, this biographical drama chronicles the founding and rise of Facebook, focusing on the relationships and conflicts among its co-founders, including Mark Zuckerberg (Jesse Eisenberg) and Eduardo Saverin (Andrew Garfield).');


--EST
INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Avengers', 'Action, Fantasy, Drama', 12, 'EST', '10:00:00', '2:40:00', 'https://m.media-amazon.com/images/I/81Q3-wGudPL._AC_UF894,1000_QL80_.jpg', 'A group of superheroes team up to save the world.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Inception', 'Action, Thriller, Fantasy, Adventure', 12, 'EST', '9:30:00', '2:30:00', 'https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p7825626_p_v8_af.jpg', 'A mind-bending heist thriller directed by Christopher Nolan, where skilled thieves enter people''s dreams to steal their deepest secrets. The film explores the layers of reality and challenges the boundaries of the subconscious mind.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Dark Knight', 'Action, Drama, Thriller', 12, 'EST', '16:00:00', '2:14:00', 'https://images.moviesanywhere.com/bd47f9b7d090170d79b3085804075d41/c6140695-a35f-46e2-adb7-45ed829fc0c0.jpg', 'Christopher Nolan''s gripping Batman sequel features Christian Bale as the caped crusader, facing off against the iconic Joker, portrayed by Heath Ledger. The film delves into the moral complexities of heroism and villainy.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Pulp Fiction', 'Comedy, Drama, Thriller', 12, 'EST', '11:00:00', '2:55:00', 'https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p15684_p_v10_ag.jpg', 'Directed by Quentin Tarantino, "Pulp Fiction" is a nonlinear narrative that weaves together multiple interconnected stories involving crime, redemption, and dark humor. The film''s unconventional structure and memorable dialogue became iconic in the 1990s.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Matrix', 'Action, Thriller, Fantasy, Adventure', 15, 'EST', '12:30:00', '2:00:00', 'https://m.media-amazon.com/images/I/613ypTLZHsL._AC_UF894,1000_QL80_.jpg', 'A groundbreaking sci-fi action film directed by the Wachowskis, "The Matrix" follows the journey of Neo (Keanu Reeves) as he discovers the truth about reality and battles against sentient machines that control human minds.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Forrest Gump', 'Comedy, Drama, Romance', 15, 'EST', '19:00:00', '2:10:00', 'https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p15829_v_v13_aa.jpg', 'Directed by Robert Zemeckis, this heartwarming film stars Tom Hanks as Forrest Gump, a man with a low IQ who inadvertently influences several defining moments in American history. The story explores themes of love, destiny, and the power of simplicity.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Godfather', 'Drama', 15, 'EST', '20:00:00', '1:50:00', 'https://static0.srcdn.com/wordpress/wp-content/uploads/2023/05/the-godfather-poster.jpeg', 'Directed by Francis Ford Coppola, "The Godfather" is a cinematic masterpiece that follows the Corleone family''s patriarch, Vito Corleone (Marlon Brando), and his son Michael (Al Pacino) as they navigate the treacherous world of organized crime.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Silence of the Lambs', 'Horror', 15, 'EST', '22:30:00', '2:30:00', 'https://s3.amazonaws.com/criterion-production/films/46014912ca0c20592c472f5bbe33e4f8/L9AQ95Y7z2vOIUf1ntwvpZsZx3kp8B_large.jpg', 'A psychological thriller directed by Jonathan Demme, "The Silence of the Lambs" follows FBI trainee Clarice Starling as she seeks the help of imprisoned cannibalistic serial killer Hannibal Lecter (Anthony Hopkins) to catch another killer on the loose.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Titanic', 'Drama, Romance', 18, 'EST', '14:45:00', '2:47:00', 'https://m.media-amazon.com/images/M/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_FMjpg_UX1000_.jpg', 'Directed by James Cameron, "Titanic" is an epic romance and disaster film that follows the ill-fated love story between Jack (Leonardo DiCaprio) and Rose (Kate Winslet) aboard the luxurious but doomed ship.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Fight Club', 'Action, Drama', 18, 'EST', '15:15:00', '2:38:00', 'https://m.media-amazon.com/images/M/MV5BMmEzNTkxYjQtZTc0MC00YTVjLTg5ZTEtZWMwOWVlYzY0NWIwXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg', 'Directed by David Fincher, "Fight Club" is a dark and provocative film that delves into the psyche of an unreliable narrator (Edward Norton) who forms an underground fight club with the charismatic and anarchistic Tyler Durden (Brad Pitt)');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Inglorious Bastards', 'Action, Drama', 18, 'EST', '11:30:00', '1:30:00', 'https://m.media-amazon.com/images/M/MV5BOTJiNDEzOWYtMTVjOC00ZjlmLWE0NGMtZmE1OWVmZDQ2OWJhXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_FMjpg_UX1000_.jpg', 'Quentin Tarantino''s alternate history war film is a unique blend of drama and dark comedy. Set in Nazi-occupied France, it follows a group of Jewish-American soldiers known as the Basterds as they plan to assassinate high-ranking Nazi officials.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Social Network', 'Drama', 18, 'EST', '18:00:00', '1:55:00', 'https://upload.wikimedia.org/wikipedia/en/8/8c/The_Social_Network_film_poster.png', 'Directed by David Fincher, this biographical drama chronicles the founding and rise of Facebook, focusing on the relationships and conflicts among its co-founders, including Mark Zuckerberg (Jesse Eisenberg) and Eduardo Saverin (Andrew Garfield).');



