CREATE OR REPLACE DATABASE mediatheque;

USE mediatheque;

DROP TABLE IF EXISTS USER CASCADE;
DROP TABLE IF EXISTS DVD CASCADE;
DROP TABLE IF EXISTS LIVRE CASCADE;
DROP TABLE IF EXISTS CD CASCADE;

CREATE TABLE USER (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(32) NOT NULL ,   
	password VARCHAR(32) NOT NULL , 
	PRIMARY KEY (id)
);

CREATE TABLE EMPRUNT (
	codeDoc VARCHAR(32) NOT NULL,
	idUser INT NOT NULL,
	isDocReturned BOOLEAN DEFAULT FALSE
);

CREATE TABLE DVD(
	codeDoc VARCHAR(32) NOT NULL,
	titre VARCHAR(32) NOT NULL,
	nomAuteur VARCHAR(32) NOT NULL,
	duree INT NOT NULL,
	PRIMARY KEY (codeDoc)
);

CREATE TABLE LIVRE(
	codeDoc VARCHAR(32) NOT NULL,
	titre VARCHAR(32) NOT NULL,
	nomAuteur VARCHAR(32) NOT NULL,
	nbPages INT NOT NULL,
	PRIMARY KEY (codeDoc)
);

CREATE TABLE CD(
	codeDoc VARCHAR(32) NOT NULL,
	titre VARCHAR(32) NOT NULL,
	nomAuteur VARCHAR(32) NOT NULL,
	nbPistes INT NOT NULL,
	PRIMARY KEY (codeDoc)
);

INSERT INTO USER (name, password) VALUES ('admin', 'admin');
INSERT INTO USER (name, password) VALUES ('yannis', 'motdepasse');
INSERT INTO USER (name, password) VALUES ('raphael','motdepasse');

INSERT INTO LIVRE (codeDoc, titre, nomAuteur, nbPages) VALUES ('L001', 'Quatre-Vingts-Treize', 'Victor Hugo', 830);
INSERT INTO LIVRE (codeDoc, titre, nomAuteur, nbPages) VALUES ('L002', 'Bilbo le Hobbit', 'Tolkien', 400);
INSERT INTO LIVRE (codeDoc, titre, nomAuteur, nbPages) VALUES ('L003', 'Le petit prince', 'Antone de St Exupery', 90);

INSERT INTO CD (codeDoc, titre, nomAuteur, nbPistes) VALUES ('CD001', 'Made In Japan', 'Deep Purple', 10);
INSERT INTO CD (codeDoc, titre, nomAuteur, nbPistes) VALUES ('CD002', 'J aime les filles', 'Jacques Dutronc', 1);
INSERT INTO CD (codeDoc, titre, nomAuteur, nbPistes) VALUES ('CD003', 'Back In Black', 'AC/DC', 10);
INSERT INTO CD (codeDoc, titre, nomAuteur, nbPistes) VALUES ('CD004', 'Or Noir Deluxe', 'Kaaris', 20);

INSERT INTO DVD (codeDoc, titre, nomAuteur, duree) VALUES ('DVD001', 'Blade Runner', 'Ridley Scott', 117); 
INSERT INTO DVD (codeDoc, titre, nomAuteur, duree) VALUES ('DVD002', 'Titanic', 'James Cameron', 195);

INSERT INTO EMPRUNT (codeDoc, idUser) VALUES ('L002', 3);
INSERT INTO EMPRUNT (codeDoc, idUser) VALUES ('CD002', 2);
