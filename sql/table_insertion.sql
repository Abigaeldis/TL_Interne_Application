

-- Insertion d'exemple pour la table "cartes"
INSERT INTO cartes (nom)
VALUES
    ('Menu Viande'),
    ('Menu végétarien'),
    ('Menu Fête'),
	('Menu Fête');


-- Insertions pour la table  restaurants
INSERT INTO restaurants
	(	nom							,	adresse													,	description																																																																					, id_carte	)
VALUES
	( 	'Le Jardin Enchanté'		,	'42 Rue Magique, Quartier Féerique, Ville Imaginaire'	,	'Plongez dans l''univers magique du Jardin Enchanté, où chaque plat est une potion culinaire. Des saveurs mystérieuses et des ingrédients fantastiques vous transporteront dans un monde où la cuisine devient une aventure extraordinaire.'												,		1	),
	( 	'La Taverne du Temps Perdu'	,	'17 Rue Éphémère, Quartier Temporel, Cité Illusoire'	,	'Voyagez à travers les époques à la Taverne du Temps Perdu. Ce restaurant vous propose une expérience gastronomique qui mêle le passé et le futur, avec des plats revisités de différentes périodes historiques et des créations avant-gardistes.'											,		2	),
	( 	'L''Oasis Épicurienne'		,	'8 Avenue Gourmande, Quartier Désertique, Ville Mirage' ,	'L''Oasis Épicurienne est un refuge gastronomique au cœur du désert culinaire. Découvrez des saveurs exotiques, des épices envoûtantes et des plats inspirés des traditions du monde entier, le tout dans un cadre somptueux digne d''une oasis gourmande.'									,		3	),
	( 	'Le Galion Gourmand'		,	'Port Savor, Île Gourmet, Océan Délicieux'				,	'Embarquez à bord du Galion Gourmand pour une aventure gastronomique en haute mer. Ce restaurant au thème maritime propose des fruits de mer frais, des plats inspirés des voyages exotiques et une ambiance maritime qui évoque le charme intemporel des grandes expéditions culinaires.'	,		4	);
  
-- Insertions pour la table "horaires"
INSERT INTO horaires (jour, heurededebut, heuredefin, creneau, id_restaurant)
VALUES
    ('Lundi', '09:00', '12:30', 'MIDI', 1),
    ('Lundi', '19:00', '23:30', 'SOIR', 1),
	('Mardi', '09:00', '10:00', 'MIDI', 1),
	('Mardi', '18:00', '23:00', 'SOIR', 1),
    ('Mercredi', '11:00', '13:30', 'MIDI', 1),
	('Mercredi', '17:00', '22:30', 'SOIR', 1),
    ('Jeudi', '9:00', '12:30', 'MIDI', 1),
	('Jeudi', '13:00', '20:00', 'SOIR', 1),
    ('Vendredi', '8:00', '12:00', 'MIDI', 1),
	('Vendredi', '13:00', '21:00', 'SOIR', 1);

 -- Insertions pour la table  utilisateurs
INSERT INTO utilisateurs 
	(nom,      prenom,      mail,                    motdepasse,   telephone,      adresse,         role,       id_restaurant)
VALUES 
	('Doe', 'John', 'john.doe@email.com', 'lion', '123456789', '123 Rue des exemples', 'client', 1),
	('Smith', 'Alice', 'alice.smith@email.com', 'lion', '987654321', '456 Avenue des tests', 'employe', 1),
	('Brown', 'Emma', 'emma.brown@email.com', 'lion', '555555555', '789 Boulevard des données', 'manager', 2),
	('Dupont', 'Alice', 'alice.dupont@email.com', 'lion', '123456789', '1 Rue de l''exemple', 'client', 1),
    ('Martin', 'Paul', 'paul.martin@email.com', 'lion', '987654321', '2 Avenue des tests', 'client', 2);

-- Insertion modifiée pour la table "messages"
INSERT INTO messages (titre, corps_message, id_restaurant, id_utilisateur)
VALUES
    ('Question sur les allergies', 'Bonjour, j''aimerais en savoir plus sur les options pour les personnes ayant des allergies alimentaires.', 1, 2),
    ('Réservation pour une table en extérieur', 'Est-il possible de réserver une table en extérieur pour ce samedi soir?', 2, 3),
    ('Menu végétarien', 'Pouvez-vous me fournir des informations sur les plats végétariens disponibles dans votre menu?', 3, 2),
    ('Disponibilité ce week-end', 'Je voudrais savoir s''il y a des réservations disponibles pour ce samedi à 20h.', 3, 1),
    ('Heures d''ouverture', 'Pouvez-vous me donner les heures d''ouverture de votre restaurant cette semaine?', 4, 1);

-- Insertions pour la table "tables"
INSERT INTO tables (num_table, capacite_table, etat, id_restaurant)
VALUES
    (1, 4, 'Libre',		1),
    (2, 6, 'Présent',	1),
    (3, 2, 'Libre',		1),
    (4, 8, 'Réservée',	1),
    (5, 4, 'Libre',		1);

-- Insertions pour la table "reservations"
INSERT INTO reservations 
	(date, statut, nb_personne, id_utilisateur, id_table, id_restaurant)
VALUES
    (CONVERT(DATETIME,'2024-04-02 18:00:00',120), 'Confirmée',	2, 1, 2, 1),
    (CONVERT(DATETIME,'2024-04-03 19:30:00',120), 'En attente',	4, 3, 3, 1),
    (CONVERT(DATETIME,'2024-04-03 20:00:00',120), 'Confirmée',	6, 2, 1, 1),
    (CONVERT(DATETIME,'2024-04-02 21:00:00',120), 'Annulée',	8, 4, 4, 1),
    (CONVERT(DATETIME,'2024-04-04 18:30:00',120), 'Confirmée',	3, 5, 5, 1);

-- Insertions pour la table "commandes"
INSERT INTO commandes 
	(statut, id_table, id_utilisateur)
VALUES
    ('En cours',	1, NULL),
    ('Terminée',	2, 3),
    ('En attente',	3, NULL),
    ('Annulée',		4, 4),
    ('En cours',	5, 5);


INSERT INTO plats
	(nom									,	description														,	prix	,	type		,		id_carte	)
VALUES
	( 'Carpaccio de Saumon et Avocat'		,	'Saumon frais, avocat, vinaigrette citron-huile d''olive'		,	7		,	'entrée'	,		1	),
	( 'Bruschetta Tomate-Mozzarella'		,	'Tomates, mozzarella, basilic, huile d''olive sur pain grillé.'	,	8		,	'entrée'	,		1	),
	( 'Velouté Champignons '				,	'Soupe crémeuse aux champignons, parfumée à l''ail.'			,	9		,	'entrée'	,		2	),
	( 'Salade Quinoa-Légumes Rôtis'		,	'Quinoa, légumes rôtis, feta, vinaigrette citron.'					,	8		,	'entrée'	,		2	),
	( 'Croustillants Chèvre-Miel'			,	'Chèvre fondant, miel, noix dans une enveloppe croustillante.'	,	9.5		,	'entrée'	,		3	),
	( 'Tartare Thon-Avocat-Mangue'		,	'Thon cru, avocat, mangue, sauce soja.'								,	7.5		,	'entrée'	,		3	),
	( 'Filet Mignon Morilles '			,	'Filet mignon, sauce morilles, pommes de terre rôties.'				,	16		,	'plat'		,		1	),
	( 'Risotto Champignons Sauvages'		,	'Risotto crémeux, champignons sauvages, parmesan.'				,	15		,	'plat'		,		1	),
	( 'Poulet Teriyaki'					,	'Cuisses de poulet glacées teriyaki, servi avec du riz.'			,	17		,	'plat'		,		2	),
	( 'Pâtes Fraîches Pesto Basilic'		,	'Pâtes, pesto basilic, pignons, parmesan, huile d''olive.'		,	18		,	'plat'		,		2	),
	( 'Saumon Grillé Citronnelle'			,	'Saumon grillé, mariné citronnelle, servi avec une salade.'		,	17.5	,	'plat'		,		3	),
	( 'Curry Végétarien Coco'				,	'Curry légumes, lait de coco, servi avec riz basmati.'			,	18.5	,	'plat'		,		3	),
	( 'Mojito à la Menthe'				,	'Menthe, citron vert, sucre, glace pilée, rhum blanc.'				,	5		,	'boisson'	,		1	),
	( 'Smoothie Tropical'					,	'Ananas, mangue, banane, yaourt nature.'						,	6		,	'boisson'	,		1	),
	( 'Café Glacé Vanille'				,	'Café fort, glace vanille, sirop de vanille.'						,	5.5		,	'boisson'	,		2	),
	( 'Thé Vert Jasmin'					,	'Thé vert parfumé aux fleurs de jasmin.'							,	6.5		,	'boisson'	,		2	),
	( 'Limonade Fraise-Basilic'			,	'Limonade, fraises, basilic.'										,	7		,	'boisson'	,		3	),
	( 'Cocktail sans Alcool Pastèque'		,	'Jus de pastèque, menthe, eau pétillante.'						,	7.5		,	'boisson'	,		3	),
	( 'Tiramisu Classique'				,	'Biscuits café, mascarpone, cacao.'									,	10		,	'dessert'	,		1	),
	( 'Fondant Chocolat-Framboises'		,	'Gâteau chocolat, framboises.'										,	11		,	'dessert'	,		1	),
	( 'Panna Cotta Mangue'				,	'Crème dessert mangue.'												,	11.5	,	'dessert'	,		2	),
	( 'Tarte Pommes Caramélisées'			,	'Pommes caramélisées, croûte croustillante.'					,	13		,	'dessert'	,		2	),
	( 'Crème Brûlée Vanille'				,	'Crème vanille, surface caramélisée.'							,	12		,	'dessert'	,		3	),
	( 'Parfait Citron-Baies'				,	'Yaourt citron, biscuits, compote de baies.'					,	10.5	,	'dessert'	,		3	);

-- Insertions pour la table "platscommandes"
INSERT INTO platscommandes 
	(nb_plat, id_plat, id_commande)
VALUES
    (2, 1, 1),
    (1, 3, 1),
    (3, 2, 2),
    (1, 4, 3),
    (2, 5, 4);

