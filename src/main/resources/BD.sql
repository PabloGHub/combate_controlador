-- Active: 1730730652288@@127.0.0.1@5432@supervivencia_preguntas


CREATE DATABASE supervivencia_preguntas;

--\connect supervivencia_preguntas;

CREATE SCHEMA IF NOT EXISTS supervivencia_preguntas;
SET search_path TO supervivencia_preguntas;

/*
    -- Tabla: jugador -> id_jugador:numero, nombre:texto, cantidad_preguntas:numero
    -- Tabla: pregunta -> id_pregunta:numero, id_jugador:numero, pregunta:texto, respuesta:texto, fallo1:texto, fallo2:texto, fallo3:texto, aciertos:numero, fallos:numero
*/

-- Tabla: jugador
CREATE TABLE IF NOT EXISTS jugador
(
    id_jugador SERIAL PRIMARY KEY,
    nombre TEXT NOT NULL
);

-- Tabla: pregunta
CREATE TABLE IF NOT EXISTS pregunta
(
    id_pregunta SERIAL PRIMARY KEY,
    id_jugador INT REFERENCES jugador(id_jugador),
    pregunta TEXT NOT NULL,
    respuesta TEXT NOT NULL,
    fallo1 TEXT NOT NULL,
    fallo2 TEXT NOT NULL,
    fallo3 TEXT NOT NULL
);

-- Tabla: respuesta
CREATE TABLE IF NOT EXISTS respuesta
(
    id_respuesta SERIAL PRIMARY KEY,
    id_jugador INT REFERENCES jugador(id_jugador),
    id_pregunta INT REFERENCES pregunta(id_pregunta),
    acertada BOOLEAN NOT NULL
);

-- DROP SCHEMA IF EXISTS supervivencia_preguntas CASCADE;