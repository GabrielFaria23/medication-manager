CREATE TABLE IF NOT EXISTS manufacturer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(200),
    activated BOOLEAN DEFAULT TRUE,
    deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT NULL,
    last_update TIMESTAMP DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS medication (
    id SERIAL PRIMARY KEY,
    anvisa_registration_number VARCHAR(17) NOT NULL,
    name VARCHAR(150),
    expiration_date TIMESTAMP DEFAULT NULL,
    telephone_sac VARCHAR(15),
    price FLOAT,
    quantity_pills INTEGER,
    activated BOOLEAN DEFAULT TRUE,
    deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT NULL,
    last_update TIMESTAMP DEFAULT NULL,
    id_manufacturer SERIAL,
    FOREIGN KEY (id_manufacturer) REFERENCES manufacturer(id)
);

CREATE TABLE IF NOT EXISTS adverse_reactions (
    id SERIAL PRIMARY KEY,
    description VARCHAR(200),
    activated BOOLEAN DEFAULT TRUE,
    deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT NULL,
    last_update TIMESTAMP DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS medication_adverse_reactions (
    id_medication INTEGER NOT NULL,
    id_adverse_reactions INTEGER NOT NULL,
    CONSTRAINT fk_medication FOREIGN KEY (id_medication) REFERENCES medication(id),
    CONSTRAINT fk_adverse_reactions FOREIGN KEY (id_adverse_reactions) REFERENCES adverse_reactions(id)
);

CREATE SEQUENCE sq_medication
INCREMENT 1
START 1;

CREATE SEQUENCE sq_manufacturer
INCREMENT 1
START 1;

CREATE SEQUENCE sq_adverse_reactions
INCREMENT 1
START 1;