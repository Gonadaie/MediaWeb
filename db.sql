CREATE DATABASE mediatheque;


CREATE TABLE USER (
	id VARCHAR NOT NULL ,   
	password VARCHAR NOT NULL , 
	PRIMARY KEY (id);

)


CREATE TABLE DVD(
	codeDoc VARCHAR NOT NULL,
	titre VARCHAR NOT NULL,
	nomAuteur VARCHAR NOT NULL,
	duree int NOT NULL,
	idPers VARCHAR,
	PRIMARY KEY (codeDoc),
	FOREIGN KEY (idPers) REFERENCES USER(id);
)

CREATE TABLE LIVRE(
	codeDoc VARCHAR NOT NULL,
	titre VARCHAR NOT NULL,
	nomAuteur VARCHAR NOT NULL,
	nbPages int NOT NULL,
	idPers VARCHAR,
	PRIMARY KEY (codeDoc),
	FOREIGN KEY (idPers) REFERENCES USER(id);

CREATE TABLE CD(
	codeDoc VARCHAR NOT NULL,
	titre VARCHAR NOT NULL,
	nomAuteur VARCHAR NOT NULL,
	nbPistes int NOT NULL,
	idPers VARCHAR,
	PRIMARY KEY (codeDoc),
	FOREIGN KEY (idPers) REFERENCES USER(id);
)

INSERT INTO USER VALUES ('admin', 'admin');
INSERT INTO USER VALUES ('yannis', 'motdepasse');
INSERT INTO USER VALUES ('raphael','motdepasse');

INSERT INTO LIVRE ('codeDoc','titre','nomAuteur','nbPages')VALUES ('L001', 'Quatre-Vingts-Treize','Victor Hugo','830');
INSERT INTO LIVRE ('codeDoc','titre','nomAuteur','nbPages','idPers')VALUES ('L002', 'Bilbo le Hobbit','Tolkien','400','raphael');
INSERT INTO LIVRE ('codeDoc','titre','nomAuteur','nbPages')VALUES ('L003', 'Le petit prince','Antone de St Exupery','90');

INSERT INTO CD ('codeDoc','titre','nomAuteur','nbPistes')VALUES ('CD001', 'Made In Japan','Deep Purple','10');
INSERT INTO CD ('codeDoc','titre','nomAuteur','nbPistes','idPers')VALUES ('CD001', 'J aime les filles','Jacques Dutronc','1','yannis');
INSERT INTO CD ('codeDoc','titre','nomAuteur','nbPistes')VALUES ('CD001', 'Back In Black','ACDC','10');
INSERT INTO CD ('codeDoc','titre','nomAuteur','nbPistes')VALUES ('CD004', 'Or Noir Deluxe','Kaaris','20');

INSERT INTO DVD ('codeDoc','titre','nomAuteur','duree')VALUES ('DVD001', 'Blade Runner','Ridley Scott','117');
INSERT INTO DVD ('codeDoc','titre','nomAuteur','duree')VALUES ('DVD002', 'Titanic','James Cameron','195');