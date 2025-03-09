-- Active: 1741459211514@@192.168.1.139@5432@supervivencia_preguntas


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
    fallo3 TEXT NOT NULL,
    ban BOOLEAN NOT NULL
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


-- DATOS:
INSERT INTO pregunta (id_jugador, pregunta, respuesta, fallo1, fallo2, fallo3, ban) VALUES
(1, 'En qué año nació San Ignacio de Loyola', '1491', '1521', '1456', '1505', FALSE),
(1, 'Cómo se llamaba San Ignacio de Loyola antes de convertirse en religioso', 'Íñigo López de Loyola', 'Juan de Borja', 'Francisco de Jaso', 'Pedro Fabro', FALSE),
(1, 'Qué hecho marcó la conversión de San Ignacio de Loyola', 'Una herida en la pierna durante la batalla de Pamplona', 'Un naufragio en el mar Mediterráneo', 'La muerte de su hermano en combate', 'Una visión en un monasterio de Francia', FALSE),
(1, 'Cuál es el nombre de la orden religiosa fundada por San Ignacio de Loyola', 'Compañía de Jesús', 'Orden de los Cartujos', 'Hermanos Franciscanos', 'Congregación de los Agustinos', FALSE),
(1, 'Dónde murió San Ignacio de Loyola', 'Roma', 'París', 'Loyola', 'Jerusalén', FALSE);
