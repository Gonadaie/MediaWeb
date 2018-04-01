CREATE OR REPLACE DATABASE mediatheque;

USE mediatheque;

DROP TABLE IF EXISTS USER CASCADE;
DROP TABLE IF EXISTS DOCUMENT CASCADE;

CREATE TABLE USER (
	id INT NOT NULL UNIQUE AUTO_INCREMENT,
	name VARCHAR(32) NOT NULL,   
	password VARCHAR(32) NOT NULL, 
	type INT NOT NULL DEFAULT 3,
	PRIMARY KEY (id)
);

CREATE TABLE DOCUMENT (
	id INT NOT NULL UNIQUE AUTO_INCREMENT,
	titre VARCHAR(32) NOT NULL,
	nomAuteur VARCHAR(32) NOT NULL,
	type VARCHAR(32) NOT NULL,
	nbPages INT,
	duree INT,
	genre VARCHAR(32),
	PRIMARY KEY (id)
);

CREATE TABLE EMPRUNT (
	idDoc INT NOT NULL UNIQUE,
	idUser INT NOT NULL,
	FOREIGN KEY (idDoc) REFERENCES DOCUMENT(id),
	FOREIGN KEY (idUser) REFERENCES USER(id)
);

INSERT INTO USER (name, password, type) VALUES ('admin', 'admin', 1);
INSERT INTO USER (name, password, type) VALUES ('bibliothecaire', 'motdepasse', 2);
INSERT INTO USER (name, password, type) VALUES ('yannis', 'motdepasse', 3);
INSERT INTO USER (name, password, type) VALUES ('raphael','motdepasse', 3);

INSERT INTO DOCUMENT (type, titre, nomAuteur) VALUES (1, 'Quatre-Vingts-Treize', 'Victor Hugo');
INSERT INTO DOCUMENT (type, titre, nomAuteur, nbPages) VALUES (1, 'Bilbo le Hobbit', 'Tolkien', 450);
INSERT INTO DOCUMENT (type, titre, nomAuteur) VALUES (1, 'Le petit prince', 'Antone de St Exupery');

INSERT INTO DOCUMENT (type, titre, nomAuteur, duree) VALUES (2, 'Made In Japan', 'Deep Purple', 60);
INSERT INTO DOCUMENT (type, titre, nomAuteur) VALUES (2, 'J aime les filles', 'Jacques Dutronc');
INSERT INTO DOCUMENT (type, titre, nomAuteur) VALUES (2, 'Back In Black', 'AC/DC');
INSERT INTO DOCUMENT (type, titre, nomAuteur) VALUES (2, 'Or Noir Deluxe', 'Kaaris');

INSERT INTO DOCUMENT (type, titre, nomAuteur, duree) VALUES (3, 'Blade Runner', 'Ridley Scott', 120); 
INSERT INTO DOCUMENT (type, titre, nomAuteur) VALUES (3, 'Titanic', 'James Cameron');

INSERT INTO EMPRUNT (idDoc, idUser) VALUES (2, 4);
INSERT INTO EMPRUNT (idDoc, idUser) VALUES (5, 3);
