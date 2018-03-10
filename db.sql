CREATE OR REPLACE DATABASE mediatheque;

USE mediatheque;

DROP TABLE IF EXISTS USER CASCADE;
DROP TABLE IF EXISTS DOCUMENT CASCADE;

CREATE TABLE USER (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(32) NOT NULL ,   
	password VARCHAR(32) NOT NULL , 
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
	PRIMARY KEY (id)
);

INSERT INTO USER (name, password) VALUES ('admin', 'admin');
INSERT INTO USER (name, password) VALUES ('yannis', 'motdepasse');
INSERT INTO USER (name, password) VALUES ('raphael','motdepasse');

INSERT INTO DOCUMENT (titre, nomAuteur) VALUES ('Quatre-Vingts-Treize', 'Victor Hugo');
INSERT INTO DOCUMENT (titre, nomAuteur) VALUES ('Bilbo le Hobbit', 'Tolkien');
INSERT INTO DOCUMENT (titre, nomAuteur) VALUES ('Le petit prince', 'Antone de St Exupery');

INSERT INTO DOCUMENT (titre, nomAuteur) VALUES ('Made In Japan', 'Deep Purple');
INSERT INTO DOCUMENT (titre, nomAuteur) VALUES ('J aime les filles', 'Jacques Dutronc');
INSERT INTO DOCUMENT (titre, nomAuteur) VALUES ('Back In Black', 'AC/DC');
INSERT INTO DOCUMENT (titre, nomAuteur) VALUES ('Or Noir Deluxe', 'Kaaris');

INSERT INTO DOCUMENT (titre, nomAuteur) VALUES ('Blade Runner', 'Ridley Scott'); 
INSERT INTO DOCUMENT (titre, nomAuteur) VALUES ('Titanic', 'James Cameron');

INSERT INTO EMPRUNT (idDoc, idUser) VALUES (2, 3);
INSERT INTO EMPRUNT (idDoc, idUser) VALUES (5, 2);
