CREATE DATABASE IF NOT EXISTS cidades;

USE cidades;

CREATE TABLE IF NOT EXISTS municipios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    estado VARCHAR(100),
    codigo_estado INT,
    codigo_municipio INT,
    nome_municipio VARCHAR(200),
    capital BOOLEAN,
    populacao INT
);
