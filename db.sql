CREATE OR REPLACE DATABASE mediatheque;

USE mediatheque;

DROP TABLE IF EXISTS USER CASCADE;
DROP TABLE IF EXISTS DOCUMENT CASCADE;

CREATE TABLE USER (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(32) NOT NULL,   
	password VARCHAR(32) NOT NULL, 
	type INT NOT NULL DEFAULT 3,
	PRIMARY KEY (id)
);

CREATE TABLE EMPRUNT (
	idDoc INT NOT NULL,
	idUser INT NOT NULL,
	isDocReturned BOOLEAN DEFAULT FALSE
);

CREATE TABLE DOCUMENT (
	id INT NOT NULL AUTO_INCREMENT,
	titre VARCHAR(32) NOT NULL,
	nomAuteur VARCHAR(32) NOT NULL,
	type VARCHAR(32),
	nbPages INT,
	genre VARCHAR(32),
	PRIMARY KEY (id)
);

INSERT INTO USER (name, password, type) VALUES ('admin', 'admin', 1);
INSERT INTO USER (name, password, type) VALUES ('bibliothecaire', 'motdepasse', 2);
INSERT INTO USER (name, password, type) VALUES ('yannis', 'motdepasse', 3);
INSERT INTO USER (name, password, type) VALUES ('raphael','motdepasse', 3);

INSERT INTO DOCUMENT (titre, nomAuteur) VALUES ('Quatre-Vingts-Treize', 'Victor Hugo');
INSERT INTO DOCUMENT (titre, nomAuteur) VALUES ('Bilbo le Hobbit', 'Tolkien');
INSERT INTO DOCUMENT (titre, nomAuteur) VALUES ('Le petit prince', 'Antone de St Exupery');

INSERT INTO DOCUMENT (titre, nomAuteur) VALUES ('Made In Japan', 'Deep Purple');
INSERT INTO DOCUMENT (titre, nomAuteur) VALUES ('J aime les filles', 'Jacques Dutronc');
INSERT INTO DOCUMENT (titre, nomAuteur) VALUES ('Back In Black', 'AC/DC');
INSERT INTO DOCUMENT (titre, nomAuteur) VALUES ('Or Noir Deluxe', 'Kaaris');

INSERT INTO DOCUMENT (titre, nomAuteur) VALUES ('Blade Runner', 'Ridley Scott'); 
INSERT INTO DOCUMENT (titre, nomAuteur) VALUES ('Titanic', 'James Cameron');

INSERT INTO EMPRUNT (idDoc, idUser) VALUES (2, 4);
INSERT INTO EMPRUNT (idDoc, idUser) VALUES (5, 3);
