CREATE TABLE seance (
	id SERIAL PRIMARY KEY,
	film VARCHAR(100) NOT NULL,
	date_seance DATE NOT NULL,
	heure TIME NOT NULL,
	places_disponibles INT NOT NULL
);

select * from seance;

CREATE TABLE reservation (
	id SERIAL PRIMARY KEY,
	nom_client VARCHAR(100) NOT NULL,
	seance_id INT,
	nombre_places INT,
	FOREIGN KEY (seance_id) REFERENCES seance(id)
);

SELECT * FROM reservation;

INSERT INTO seance (film, date_seance, heure, places_disponibles) VALUES
('Inception', '2025-04-20', '19:30:00', 50),
('Titanic', '2025-04-20', '21:00:00', 40),
('Avatar 2', '2025-04-21', '18:00:00', 60);

