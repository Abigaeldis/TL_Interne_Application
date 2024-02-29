DELETE FROM platscommandes;
DELETE FROM plats;
DELETE FROM commandes;
DELETE FROM reservations;
DELETE FROM tables;
DELETE FROM messages;
DELETE FROM utilisateurs;
DELETE FROM horaires;
DELETE FROM restaurants;
DELETE FROM cartes;

-- Insertion d'exemple pour la table "cartes"
INSERT INTO cartes (id, nom)
VALUES
    (1, 'Menu Viande'),
    (2, 'Menu v�g�tarien'),
    (3, 'Menu F�te'),
	(4, 'Menu F�te');
 
 
-- Insertions pour la table restaurants
INSERT INTO restaurants
	(	id, nom						,	adresse													,	description																																																																					, id_carte	)
VALUES
	( 	1, 'Le Jardin Enchant�'		,	'42 Rue Magique, Quartier F�erique, Ville Imaginaire'	,	'Plongez dans l''univers magique du Jardin Enchant�, o� chaque plat est une potion culinaire. Des saveurs myst�rieuses et des ingr�dients fantastiques vous transporteront dans un monde o� la cuisine devient une aventure extraordinaire.'												,		1	),
	( 	2, 'La Taverne du Temps Perdu'	,	'17 Rue �ph�m�re, Quartier Temporel, Cit� Illusoire'	,	'Voyagez � travers les �poques � la Taverne du Temps Perdu. Ce restaurant vous propose une exp�rience gastronomique qui m�le le pass� et le futur, avec des plats revisit�s de diff�rentes p�riodes historiques et des cr�ations avant-gardistes.'											,		2	),
	( 	3, 'L''Oasis �picurienne'		,	'8 Avenue Gourmande, Quartier D�sertique, Ville Mirage' ,	'L''Oasis �picurienne est un refuge gastronomique au c�ur du d�sert culinaire. D�couvrez des saveurs exotiques, des �pices envo�tantes et des plats inspir�s des traditions du monde entier, le tout dans un cadre somptueux digne d''une oasis gourmande.'									,		3	),
	( 	4, 'Le Galion Gourmand'		,	'Port Savor, �le Gourmet, Oc�an D�licieux'				,	'Embarquez � bord du Galion Gourmand pour une aventure gastronomique en haute mer. Ce restaurant au th�me maritime propose des fruits de mer frais, des plats inspir�s des voyages exotiques et une ambiance maritime qui �voque le charme intemporel des grandes exp�ditions culinaires.'	,		4	);
-- Insertions pour la table "horaires"
INSERT INTO horaires (id, jour, heurededebut, heuredefin, creneau, id_restaurant)
VALUES
    (1, 'Lundi', '09:00', '12:30', 'MIDI', 1),
    (2, 'Lundi', '19:00', '23:30', 'SOIR', 1),
	(3, 'Mardi', '09:00', '10:00', 'MIDI', 1),
	(4, 'Mardi', '18:00', '23:00', 'SOIR', 1),
    (5, 'Mercredi', '11:00', '13:30', 'MIDI', 1),
	(6, 'Mercredi', '17:00', '22:30', 'SOIR', 1),
    (7, 'Jeudi', '9:00', '12:30', 'MIDI', 1),
	(8, 'Jeudi', '13:00', '20:00', 'SOIR', 1),
    (9, 'Vendredi', '8:00', '12:00', 'MIDI', 1),
	(10, 'Vendredi', '13:00', '21:00', 'SOIR', 1);
 
-- Insertions pour la table "utilisateurs"
INSERT INTO utilisateurs 
    (id, nom, prenom, mail, motdepasse, telephone, adresse, role, token, expiration_time, id_restaurant)
VALUES 
    (1, 'Doe', 'John', 'john.doe@email.com', 'lion', '123456789', '123 Rue des exemples', 'client', NULL, NULL, 1),
    (2, 'Smith', 'Alice', 'alice.smith@email.com', 'tiger', '987654321', '456 Avenue des tests', 'employe', NULL, NULL, 1),
    (3, 'Brown', 'Emma', 'emma.brown@email.com', 'elephant', '555555555', '789 Boulevard des donn�es', 'manager', NULL, NULL, 2),
    (4, 'Dupont', 'Alice', 'alice.dupont@email.com', 'giraffe', '123456789', '1 Rue de l''exemple', 'client', NULL, NULL, 1),
    (5, 'Martin', 'Paul', 'paul.martin@email.com', 'zebra', '987654321', '2 Avenue des tests', 'client', NULL, NULL, 2);
 
 
-- Insertion modifi�e pour la table "messages"
INSERT INTO messages (id, titre, corps_message, id_restaurant, id_utilisateur)
VALUES
    (1, 'Question sur les allergies', 'Bonjour, j''aimerais en savoir plus sur les options pour les personnes ayant des allergies alimentaires.', 1, 2),
    (2, 'R�servation pour une table en ext�rieur', 'Est-il possible de r�server une table en ext�rieur pour ce samedi soir?', 2, 3),
    (3, 'Menu v�g�tarien', 'Pouvez-vous me fournir des informations sur les plats v�g�tariens disponibles dans votre menu?', 3, 2),
    (4, 'Disponibilit� ce week-end', 'Je voudrais savoir s''il y a des r�servations disponibles pour ce samedi � 20h.', 3, 1),
    (5, 'Heures d''ouverture', 'Pouvez-vous me donner les heures d''ouverture de votre restaurant cette semaine?', 4, 1);
 
-- Insertions pour la table "tables"
INSERT INTO tables (id, num_table, capacite_table, etat, id_restaurant)
VALUES
    (1, 1, 4, 'Libre',		1),
    (2, 2, 6, 'Occup�e',	1),
    (3, 3, 2, 'Libre',		1),
    (4, 4, 8, 'R�serv�e',	1),
    (5, 5, 4, 'Libre',		1);
 
-- Insertions pour la table "reservations"
INSERT INTO reservations 
	(id, date, statut, nb_personne, id_utilisateur, id_table, id_restaurant)
VALUES
    (1, CONVERT(DATETIME,'2023-01-15 18:00:00',120), 'Confirm�e',	2, 1, 2, 1),
    (2, CONVERT(DATETIME,'2023-01-20 19:30:00',120), 'En attente',	4, 3, 3, 1),
    (3, CONVERT(DATETIME,'2023-02-05 20:00:00',120), 'Confirm�e',	6, 2, 1, 1),
    (4, CONVERT(DATETIME,'2023-02-10 21:00:00',120), 'Annul�e',	8, 4, 4, 1),
    (5, CONVERT(DATETIME,'2023-02-15 18:30:00',120), 'Confirm�e',	3, 5, 5, 1);
 
-- Insertions pour la table "commandes"
INSERT INTO commandes 
	(id, statut, id_table, id_utilisateur)
VALUES
    (1, 'En cours',	1, NULL),
    (2, 'Termin�e',	2, 3),
    (3, 'En attente',	3, NULL),
    (4, 'Annul�e',		4, 4),
    (5, 'En cours',	5, 5);
 
 
INSERT INTO plats
	(id, nom									,	description														,	prix	,	type		,		id_carte	)
VALUES
	( 1, 'Carpaccio de Saumon et Avocat'		,	'Saumon frais, avocat, vinaigrette citron-huile d''olive'		,	7		,	'entr�e'	,		1	),
	( 2, 'Bruschetta Tomate-Mozzarella'		,	'Tomates, mozzarella, basilic, huile d''olive sur pain grill�.'	,	8		,	'entr�e'	,		1	),
	( 3, 'Velout� Champignons '				,	'Soupe cr�meuse aux champignons, parfum�e � l''ail.'			,	9		,	'entr�e'	,		2	),
	( 4, 'Salade Quinoa-L�gumes R�tis'		,	'Quinoa, l�gumes r�tis, feta, vinaigrette citron.'					,	8		,	'entr�e'	,		2	),
	( 5, 'Croustillants Ch�vre-Miel'			,	'Ch�vre fondant, miel, noix dans une enveloppe croustillante.'	,	9.5		,	'entr�e'	,		3	),
	( 6, 'Tartare Thon-Avocat-Mangue'		,	'Thon cru, avocat, mangue, sauce soja.'								,	7.5		,	'entr�e'	,		3	),
	( 7, 'Filet Mignon Morilles '			,	'Filet mignon, sauce morilles, pommes de terre r�ties.'				,	16		,	'plat'		,		1	),
	( 8, 'Risotto Champignons Sauvages'		,	'Risotto cr�meux, champignons sauvages, parmesan.'				,	15		,	'plat'		,		1	),
	( 9, 'Poulet Teriyaki'					,	'Cuisses de poulet glac�es teriyaki, servi avec du riz.'			,	17		,	'plat'		,		2	),
	( 10, 'P�tes Fra�ches Pesto Basilic'		,	'P�tes, pesto basilic, pignons, parmesan, huile d''olive.'		,	18		,	'plat'		,		2	),
	( 11, 'Saumon Grill� Citronnelle'		,	'Saumon grill�, marin� citronnelle, servi avec une salade.'		,	17.5	,	'plat'		,		3	),
	( 12, 'Curry V�g�tarien Coco'			,	'Curry l�gumes, lait de coco, servi avec riz basmati.'			,	18.5	,	'plat'		,		3	),
	( 13, 'Mojito � la Menthe'				,	'Menthe, citron vert, sucre, glace pil�e, rhum blanc.'				,	5		,	'boisson'	,		1	),
	( 14, 'Smoothie Tropical'				,	'Ananas, mangue, banane, yaourt nature.'						,	6		,	'boisson'	,		1	),
	( 15, 'Caf� Glac� Vanille'				,	'Caf� fort, glace vanille, sirop de vanille.'						,	5.5		,	'boisson'	,		2	),
	( 16, 'Th� Vert Jasmin'					,	'Th� vert parfum� aux fleurs de jasmin.'							,	6.5		,	'boisson'	,		2	),
	( 17, 'Limonade Fraise-Basilic'			,	'Limonade, fraises, basilic.'										,	7		,	'boisson'	,		3	),
	( 18, 'Cocktail sans Alcool Past�que'	,	'Jus de past�que, menthe, eau p�tillante.'						,	7.5		,	'boisson'	,		3	),
	( 19, 'Tiramisu Classique'				,	'Biscuits caf�, mascarpone, cacao.'									,	10		,	'dessert'	,		1	),
	( 20, 'Fondant Chocolat-Framboises'		,	'G�teau chocolat, framboises.'										,	11		,	'dessert'	,		1	),
	( 21, 'Panna Cotta Mangue'				,	'Cr�me dessert mangue.'												,	11.5	,	'dessert'	,		2	),
	( 22, 'Tarte Pommes Caram�lis�es'		,	'Pommes caram�lis�es, cro�te croustillante.'					,	13		,	'dessert'	,		2	),
	( 23, 'Cr�me Br�l�e Vanille'			,	'Cr�me vanille, surface caram�lis�e.'							,	12		,	'dessert'	,		3	),
	( 24, 'Parfait Citron-Baies'			,	'Yaourt citron, biscuits, compote de baies.'					,	10.5	,	'dessert'	,		3	);
 
-- Insertions pour la table "platscommandes"
INSERT INTO platscommandes 
	(id, nb_plat, id_plat, id_commande)
VALUES
    (1, 2, 1, 1),
    (2, 1, 3, 1),
    (3, 3, 2, 2),
    (4, 1, 4, 3),
    (5, 2, 5, 4);