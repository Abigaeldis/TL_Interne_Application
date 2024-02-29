DROP TABLE IF EXISTS platscommandes, commandes, reservations, tables, plats, horaires,  messages, utilisateurs, restaurants, cartes ; 


-- Table "cartes"
CREATE TABLE cartes (
    id INT PRIMARY KEY,
    nom VARCHAR(30) NULL,
);

-- Table "restaurants"
CREATE TABLE restaurants (
    id INT PRIMARY KEY,
    nom VARCHAR(30) NOT NULL,
    adresse VARCHAR(150) NOT NULL,
    description VARCHAR(300),
	status VARCHAR(15) DEFAULT 'OUVERT',
	id_carte INT FOREIGN KEY REFERENCES cartes(id) NOT NULL,
);

-- Table "utilisateurs"
CREATE TABLE utilisateurs (
    id INT PRIMARY KEY,
    nom VARCHAR(30) NOT NULL,
    prenom VARCHAR(30) NOT NULL,
    mail VARCHAR(30) NOT NULL,
    motdepasse VARCHAR(30) NOT NULL,
    telephone VARCHAR(15),
    adresse VARCHAR(150),
    role VARCHAR(50),
	token VARCHAR(100) NULL,
	expiration_time DATETIME NULL,
    id_restaurant INT FOREIGN KEY REFERENCES restaurants(id) ON DELETE CASCADE
);

-- Table "messages"
CREATE TABLE messages (
    id INT PRIMARY KEY,
    titre VARCHAR(50) NULL,
    corps_message VARCHAR(300) NULL,
	id_restaurant INT FOREIGN KEY REFERENCES restaurants(id)  NOT NULL,
	id_utilisateur INT FOREIGN KEY REFERENCES utilisateurs(id) ON DELETE CASCADE NOT NULL
);



-- Table "horaires"
CREATE TABLE horaires (

    id INT PRIMARY KEY,

    jour VARCHAR(10)  NOT NULL,
    heurededebut TIME  NOT NULL,
    heuredefin TIME  NOT NULL,
	creneau VARCHAR(4) NOT NULL,
    id_restaurant INT FOREIGN KEY REFERENCES restaurants(id)  NOT NULL
);

-- Table "plats"
CREATE TABLE plats (
    id INT PRIMARY KEY   NOT NULL,
    nom VARCHAR(30)  NOT NULL,
    description VARCHAR(150) NULL,
    prix NUMERIC(5, 2)  NOT NULL,
    type VARCHAR(15) NULL,
    id_carte INT FOREIGN KEY REFERENCES cartes(id)  NOT NULL
);



-- Table "tables"
CREATE TABLE tables (

    id INT PRIMARY KEY,

    num_table INT  NOT NULL,
    capacite_table INT  NOT NULL,
    etat VARCHAR(12) NULL,
    id_restaurant INT FOREIGN KEY REFERENCES restaurants(id)  NOT NULL
);



-- Table "reservations"
CREATE TABLE reservations (

    id INT PRIMARY KEY,

    date DATETIME NOT NULL,
    statut VARCHAR(12)  NOT NULL,
    nb_personne INT  NOT NULL,
    id_utilisateur INT FOREIGN KEY REFERENCES utilisateurs(id) ON DELETE CASCADE NOT NULL,
    id_table INT FOREIGN KEY REFERENCES tables(id),
    id_restaurant INT FOREIGN KEY REFERENCES restaurants(id)  NOT NULL
);



-- Table "commandes"
CREATE TABLE commandes (
    id INT PRIMARY KEY ,
    statut VARCHAR(12)  NOT NULL,
    date DATETIME DEFAULT GETDATE()  NOT NULL,
    id_table INT FOREIGN KEY REFERENCES tables(id)  NOT NULL,
    id_utilisateur INT FOREIGN KEY REFERENCES utilisateurs(id) ON DELETE CASCADE,
);

-- Table "platscommandes"
CREATE TABLE platscommandes (
    id INT PRIMARY KEY ,
    nb_plat INT DEFAULT 1  NOT NULL,
    id_plat INT FOREIGN KEY REFERENCES plats(id)  NOT NULL,
    id_commande INT FOREIGN KEY REFERENCES commandes(id)  ON DELETE CASCADE NOT NULL 
);

SELECT * FROM cartes;
SELECT * FROM plats WHERE id_carte = 1;

SELECT u.nom, u.prenom, r.nom, u.role, nb_personne, statut, date FROM restaurants r
	INNER JOIN utilisateurs u ON r.id = u.id_restaurant
	INNER JOIN reservations res ON u.id = res.id_utilisateur; 