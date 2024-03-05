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
    image        VARCHAR      NOT NULL,
    description  VARCHAR(255) NOT NULL
);

-- action comedy drama thriller romance fantasy mystery adventure

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Avengers', 'Action, Fantasy, Drama', 12, 'ENG', '18:00:00', '2:40:00', 'avengers.jpg', 'A group of superheroes team up to save the world.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Inception', 'Action, Thriller, Fantasy, Adventure', 12, 'ENG', '15:30:00', '2:30:00', 'avengers.jpg', 'A mind-bending heist thriller directed by Christopher Nolan, where skilled thieves enter people''s dreams to steal their deepest secrets. The film explores the layers of reality and challenges the boundaries of the subconscious mind.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Dark Knight', 'Action, Drama, Thriller', 12, 'ENG', '16:00:00', '2:14:00', 'avengers.jpg', 'Christopher Nolan''s gripping Batman sequel features Christian Bale as the caped crusader, facing off against the iconic Joker, portrayed by Heath Ledger. The film delves into the moral complexities of heroism and villainy.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Pulp Fiction', 'Comedy, Drama, Thriller', 12, 'ENG', '12:00:00', '2:55:00', 'avengers.jpg', 'Directed by Quentin Tarantino, "Pulp Fiction" is a nonlinear narrative that weaves together multiple interconnected stories involving crime, redemption, and dark humor. The film''s unconventional structure and memorable dialogue became iconic in the 1990s.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Matrix', 'Action, Thriller, Fantasy, Adventure', 12, 'ENG', '12:30:00', '2:00:00', 'avengers.jpg', 'A groundbreaking sci-fi action film directed by the Wachowskis, "The Matrix" follows the journey of Neo (Keanu Reeves) as he discovers the truth about reality and battles against sentient machines that control human minds.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Forrest Gump', 'Comedy, Drama, Romance', 12, 'ENG', '19:00:00', '2:10:00', 'avengers.jpg', 'Directed by Robert Zemeckis, this heartwarming film stars Tom Hanks as Forrest Gump, a man with a low IQ who inadvertently influences several defining moments in American history. The story explores themes of love, destiny, and the power of simplicity.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Godfather', 'Drama', 12, 'ENG', '20:00:00', '1:50:00', 'avengers.jpg', 'Directed by Francis Ford Coppola, "The Godfather" is a cinematic masterpiece that follows the Corleone family''s patriarch, Vito Corleone (Marlon Brando), and his son Michael (Al Pacino) as they navigate the treacherous world of organized crime.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Silence of the Lambs', 'Horror', 12, 'ENG', '22:30:00', '2:30:00', 'avengers.jpg', 'A psychological thriller directed by Jonathan Demme, "The Silence of the Lambs" follows FBI trainee Clarice Starling as she seeks the help of imprisoned cannibalistic serial killer Hannibal Lecter (Anthony Hopkins) to catch another killer on the loose.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Titanic', 'Drama, Romance', 12, 'ENG', '14:45:00', '2:47:00', 'avengers.jpg', 'Directed by James Cameron, "Titanic" is an epic romance and disaster film that follows the ill-fated love story between Jack (Leonardo DiCaprio) and Rose (Kate Winslet) aboard the luxurious but doomed ship.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Fight Club', 'Action, Drama', 12, 'ENG', '15:15:00', '2:38:00', 'avengers.jpg', 'Directed by David Fincher, "Fight Club" is a dark and provocative film that delves into the psyche of an unreliable narrator (Edward Norton) who forms an underground fight club with the charismatic and anarchistic Tyler Durden (Brad Pitt)');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Inglorious Bastards', 'Action, Drama', 12, 'ENG', '11:30:00', '1:30:00', 'avengers.jpg', 'Quentin Tarantino''s alternate history war film is a unique blend of drama and dark comedy. Set in Nazi-occupied France, it follows a group of Jewish-American soldiers known as the Basterds as they plan to assassinate high-ranking Nazi officials.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Social Network', 'Drama', 12, 'ENG', '18:00:00', '1:55:00', 'avengers.jpg', 'Directed by David Fincher, this biographical drama chronicles the founding and rise of Facebook, focusing on the relationships and conflicts among its co-founders, including Mark Zuckerberg (Jesse Eisenberg) and Eduardo Saverin (Andrew Garfield).');




