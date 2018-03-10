CREATE OR REPLACE DATABASE mediatheque;

USE mediatheque;

DROP TABLE IF EXISTS USER CASCADE;
DROP TABLE IF EXISTS DVD CASCADE;
DROP TABLE IF EXISTS LIVRE CASCADE;
DROP TABLE IF EXISTS CD CASCADE;

CREATE TABLE USER (
	id VARCHAR(32) NOT NULL ,   
	password VARCHAR(32) NOT NULL , 
	PRIMARY KEY (id)
);


CREATE TABLE DVD(
	codeDoc VARCHAR(32) NOT NULL,
	titre VARCHAR(32) NOT NULL,
	nomAuteur VARCHAR(32) NOT NULL,
	duree int NOT NULL,
	idPers VARCHAR(32),
	PRIMARY KEY (codeDoc),
	FOREIGN KEY (idPers) REFERENCES USER(id)
);

CREATE TABLE LIVRE(
	codeDoc VARCHAR(32) NOT NULL,
	titre VARCHAR(32) NOT NULL,
	nomAuteur VARCHAR(32) NOT NULL,
	nbPages int NOT NULL,
	idPers VARCHAR(32),
	PRIMARY KEY (codeDoc),
	FOREIGN KEY (idPers) REFERENCES USER(id)
);

CREATE TABLE CD(
	codeDoc VARCHAR(32) NOT NULL,
	titre VARCHAR(32) NOT NULL,
	nomAuteur VARCHAR(32) NOT NULL,
	nbPistes int NOT NULL,
	idPers VARCHAR(32),
	PRIMARY KEY (codeDoc),
	FOREIGN KEY (idPers) REFERENCES USER(id)
);

INSERT INTO USER VALUES ('admin', 'admin');
INSERT INTO USER VALUES ('yannis', 'motdepasse');
INSERT INTO USER VALUES ('raphael','motdepasse');

INSERT INTO LIVRE (codeDoc, titre, nomAuteur, nbPages) VALUES ('L001', 'Quatre-Vingts-Treize', 'Victor Hugo', 830);
INSERT INTO LIVRE (codeDoc, titre, nomAuteur, nbPages, idPers) VALUES ('L002', 'Bilbo le Hobbit', 'Tolkien', 400, 'raphael');
INSERT INTO LIVRE (codeDoc, titre, nomAuteur, nbPages) VALUES ('L003', 'Le petit prince', 'Antone de St Exupery', 90);

INSERT INTO CD (codeDoc, titre, nomAuteur, nbPistes) VALUES ('CD001', 'Made In Japan', 'Deep Purple', 10);
INSERT INTO CD (codeDoc, titre, nomAuteur, nbPistes, idPers) VALUES ('CD002', 'J aime les filles', 'Jacques Dutronc', 1, 'yannis');
INSERT INTO CD (codeDoc, titre, nomAuteur, nbPistes) VALUES ('CD003', 'Back In Black', 'ACDC', 10);
INSERT INTO CD (codeDoc, titre, nomAuteur, nbPistes) VALUES ('CD004', 'Or Noir Deluxe', 'Kaaris', 20);

INSERT INTO DVD (codeDoc, titre, nomAuteur, duree) VALUES ('DVD001', 'Blade Runner', 'Ridley Scott', 117); 
INSERT INTO DVD (codeDoc, titre, nomAuteur, duree) VALUES ('DVD002', 'Titanic', 'James Cameron', 195);
