--changeset mabere:1

CREATE TABLE movies
(
    movie_id       SERIAL PRIMARY KEY,
    movie_name     VARCHAR(50)  NOT NULL,
    genre          VARCHAR(50),
    age_limit      BIGINT,
    language       VARCHAR(3)   NOT NULL,
    start_time     VARCHAR(8),
    run_time       VARCHAR(8),
    price          INT DEFAULT 7,
    image          VARCHAR      NOT NULL,
    description    VARCHAR(255) NOT NULL,
    recommendation INTEGER DEFAULT 0,
    rating         FLOAT(2) DEFAULT 0
);

-- action comedy drama thriller romance fantasy adventure horror

--ENG
INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Barbie', 'Comedy, Romance, Action, Fantasy, Drama', 12, 'ENG', '11:00:00', '2:40:00',
        'https://i.ebayimg.com/images/g/Cd0AAOSwocdkoDER/s-l1600.jpg',
        'Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. When they get a chance to go to the real world, they soon discover the joys and perils of living among humans.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Mean Girls', 'Comedy, Horror, Action, Romance, Drama', 12, 'ENG', '15:30:00', '2:30:00',
        'https://resizing.flixster.com/HtZiEfthbBdM_Idd_OB4ZsyfgMk=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzI2MTVlZjAyLTc1NTAtNGE2Zi1iNzk0LTQxN2ZmMTIyYzRiMC5qcGc=',
        'A naïve teenager who transfers to an American high school after years of homeschooling in Africa. Heron quickly befriends two outcasts, with the trio forming a plan to exact revenge on Regina George.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Alice in Wonderland', 'Action, Fantasy', 12, 'ENG', '16:00:00', '2:14:00',
        'https://m.media-amazon.com/images/I/81FMfUBCE8L._AC_UF894,1000_QL80_.jpg',
        'The story of a young girl named Alice who falls through a rabbit hole into a fantasy world of anthropomorphic creatures.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Enola Holmes', 'Action, Horror, Adventure, Comedy, Drama', 12, 'ENG', '12:00:00', '2:55:00',
        'https://m.media-amazon.com/images/M/MV5BMTcwM2VlMWUtYTViOS00MDJiLWE3NjItY2FmN2UzOTFmMWJkXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg',
        'Enola Holmes is the youngest sibling in the Holmes family. She is extremely intelligent, observant, and insightful, defying the social norms for women of the time.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Oppenheimer', 'Horror, Thriller', 15, 'ENG', '12:30:00', '2:00:00',
        'https://m.media-amazon.com/images/M/MV5BMDBmYTZjNjUtN2M1MS00MTQ2LTk2ODgtNzc2M2QyZGE5NTVjXkEyXkFqcGdeQXVyNzAwMjU2MTY@._V1_.jpg',
        'During World War II, Lt. Gen. Leslie Groves Jr. appoints physicist J. Robert Oppenheimer to work on the top-secret Manhattan Project. Oppenheimer and a tear of scientists spend years developing and designing the atomic bomb.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Intern', 'Comedy, Action, Romance, Drama', 15, 'ENG', '19:00:00', '2:10:00',
        'https://m.media-amazon.com/images/M/MV5BMTUyNjE5NjI5OF5BMl5BanBnXkFtZTgwNzYzMzU3NjE@._V1_UY1200_CR90,0,630,1200_AL_.jpg',
        'The plot follows a 70-year-old widower who becomes a senior intern at a fashion website, where he forms an unlikely friendship with the company''s workaholic CEO. The film was released on September 25, 2015, by Warner Bros.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Little Women', 'Drama, Comedy', 15, 'ENG', '20:00:00', '1:50:00',
        'https://m.media-amazon.com/images/M/MV5BY2QzYTQyYzItMzAwYi00YjZlLThjNTUtNzMyMDdkYzJiNWM4XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg',
        'Meg, Jo, Beth, and Amy. The girls struggle and grow as a family while their father is serving in the war and learn the value of hard work, self-sacrifice, and love.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Shrek', 'Comedy, Action, Adventure, Romance, Fantasy', 15, 'ENG', '22:30:00', '2:30:00',
        'https://m.media-amazon.com/images/I/51m1MxSiVUL._AC_UF894,1000_QL80_.jpg',
        'Shrek is an anti-social and highly territorial ogre who loves the solitude of his swamp, and enjoys fending off mobs and intruders.');


INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('House of Gucci', 'Drama, Romance, Horror, Comedy, Thriller', 18, 'ENG', '14:45:00', '2:47:00',
        'https://m.media-amazon.com/images/M/MV5BYzdlMTMyZWQtZWNmMC00MTJiLWIyMWMtM2ZlZDdlYzZhNTc0XkEyXkFqcGdeQXVyMTMzNDE5NDM2._V1_FMjpg_UX1000_.jpg',
        'When Patrizia Reggiani, an outsider from humble beginnings, marries into the Gucci family, her unbridled ambition begins to unravel the family legacy and triggers a reckless spiral of betrayal.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Saltburn', 'Thriller, Comedy', 18, 'ENG', '15:15:00', '2:38:00',
        'https://resizing.flixster.com/vw4fqr7FfM7s72DaiYj-YSc4RW4=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzLzk4Mjk4OTA5LTk1ZTgtNDViNS04YTdkLWM2OTJmOGViZDkyMi5qcGc=',
        'Struggling to find his place at Oxford University Oliver Quick finds himself drawn into the world of the charming and aristocratic Felix Catton, who invites him to Saltburn.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Hangover', 'Action, Drama, Comedy', 18, 'ENG', '11:30:00', '1:30:00',
        'https://m.media-amazon.com/images/M/MV5BNGQwZjg5YmYtY2VkNC00NzliLTljYTctNzI5NmU3MjE2ODQzXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg',
        'The story was about three friends who lose the groom at his Las Vegas bachelor party and then must retrace their steps to figure out what happened.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Napoleon', 'Drama, Action', 18, 'ENG', '18:00:00', '1:55:00',
        'https://upload.wikimedia.org/wikipedia/en/2/2e/Napoleon_Film_poster.jpg',
        'An epic that details the chequered rise and fall of French Emperor Napoleon Bonaparte and his relentless journey to power through the prism of his addictive, volatile relationship with his wife, Josephine.');


--EST
INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Avengers', 'Action, Fantasy, Drama', 12, 'EST', '10:00:00', '2:40:00',
        'https://m.media-amazon.com/images/I/81Q3-wGudPL._AC_UF894,1000_QL80_.jpg',
        'A group of superheroes team up to save the world.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Inception', 'Action, Thriller, Fantasy, Adventure', 12, 'EST', '10:30:00', '2:30:00',
        'https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p7825626_p_v8_af.jpg',
        'A mind-bending heist thriller directed by Christopher Nolan, where skilled thieves enter people''s dreams to steal their deepest secrets. The film explores the layers of reality and challenges the boundaries of the subconscious mind.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Dark Knight', 'Action, Drama, Thriller', 12, 'EST', '16:00:00', '2:14:00',
        'https://images.moviesanywhere.com/bd47f9b7d090170d79b3085804075d41/c6140695-a35f-46e2-adb7-45ed829fc0c0.jpg',
        'Christopher Nolan''s gripping Batman sequel features Christian Bale as the caped crusader, facing off against the iconic Joker, portrayed by Heath Ledger. The film delves into the moral complexities of heroism and villainy.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Pulp Fiction', 'Comedy, Drama, Thriller', 12, 'EST', '11:00:00', '2:55:00',
        'https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p15684_p_v10_ag.jpg',
        'Directed by Quentin Tarantino, "Pulp Fiction" is a nonlinear narrative that weaves together multiple interconnected stories involving crime, redemption, and dark humor. The film''s unconventional structure and memorable dialogue became iconic in the 1990s.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Matrix', 'Action, Thriller, Fantasy, Adventure', 15, 'EST', '12:30:00', '2:00:00',
        'https://m.media-amazon.com/images/I/613ypTLZHsL._AC_UF894,1000_QL80_.jpg',
        'A groundbreaking sci-fi action film directed by the Wachowskis, "The Matrix" follows the journey of Neo (Keanu Reeves) as he discovers the truth about reality and battles against sentient machines that control human minds.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Forrest Gump', 'Comedy, Drama, Romance', 15, 'EST', '19:00:00', '2:10:00',
        'https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p15829_v_v13_aa.jpg',
        'Directed by Robert Zemeckis, this heartwarming film stars Tom Hanks as Forrest Gump, a man with a low IQ who inadvertently influences several defining moments in American history. The story explores themes of love, destiny, and the power of simplicity.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Godfather', 'Drama', 15, 'EST', '20:00:00', '1:50:00',
        'https://static0.srcdn.com/wordpress/wp-content/uploads/2023/05/the-godfather-poster.jpeg',
        'Directed by Francis Ford Coppola, "The Godfather" is a cinematic masterpiece that follows the Corleone family''s patriarch, Vito Corleone (Marlon Brando), and his son Michael (Al Pacino) as they navigate the treacherous world of organized crime.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Silence of the Lambs', 'Thriller, Horror', 15, 'EST', '22:30:00', '2:30:00',
        'https://s3.amazonaws.com/criterion-production/films/46014912ca0c20592c472f5bbe33e4f8/L9AQ95Y7z2vOIUf1ntwvpZsZx3kp8B_large.jpg',
        'A psychological thriller directed by Jonathan Demme, "The Silence of the Lambs" follows FBI trainee Clarice Starling as she seeks the help of imprisoned cannibalistic serial killer Hannibal Lecter (Anthony Hopkins) to catch another killer on the loose.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Titanic', 'Drama, Romance', 18, 'EST', '14:45:00', '2:47:00',
        'https://m.media-amazon.com/images/M/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_FMjpg_UX1000_.jpg',
        'Directed by James Cameron, "Titanic" is an epic romance and disaster film that follows the ill-fated love story between Jack (Leonardo DiCaprio) and Rose (Kate Winslet) aboard the luxurious but doomed ship.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Fight Club', 'Action, Drama', 18, 'EST', '15:15:00', '2:38:00',
        'https://m.media-amazon.com/images/M/MV5BMmEzNTkxYjQtZTc0MC00YTVjLTg5ZTEtZWMwOWVlYzY0NWIwXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg',
        'Directed by David Fincher, "Fight Club" is a dark and provocative film that delves into the psyche of an unreliable narrator (Edward Norton) who forms an underground fight club with the charismatic and anarchistic Tyler Durden (Brad Pitt)');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('Inglorious Bastards', 'Action, Drama', 18, 'EST', '11:30:00', '1:30:00',
        'https://m.media-amazon.com/images/M/MV5BOTJiNDEzOWYtMTVjOC00ZjlmLWE0NGMtZmE1OWVmZDQ2OWJhXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_FMjpg_UX1000_.jpg',
        'Quentin Tarantino''s alternate history war film is a unique blend of drama and dark comedy. Set in Nazi-occupied France, it follows a group of Jewish-American soldiers known as the Basterds as they plan to assassinate high-ranking Nazi officials.');

INSERT INTO movies (movie_name, genre, age_limit, language, start_time, run_time, image, description)
VALUES ('The Social Network', 'Drama', 18, 'EST', '18:00:00', '1:55:00',
        'https://upload.wikimedia.org/wikipedia/en/8/8c/The_Social_Network_film_poster.png',
        'Directed by David Fincher, this biographical drama chronicles the founding and rise of Facebook, focusing on the relationships and conflicts among its co-founders, including Mark Zuckerberg (Jesse Eisenberg) and Eduardo Saverin (Andrew Garfield).');
