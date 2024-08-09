CREATE DATABASE prueba_tecnica_b4;

CREATE USER prueba_user WITH PASSWORD 'prueba_password';

GRANT ALL PRIVILEGES ON DATABASE prueba_tecnica_b4 TO prueba_user;

CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL  -- Storing hashed passwords
);

-- Create the 'personas' table for storing excel data
CREATE TABLE personas (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    rut VARCHAR(20) NOT NULL,
    campo1 VARCHAR(100),
    campo2 VARCHAR(100)
);

-- 5. Otorgar permisos sobre las tablas al usuario
GRANT ALL PRIVILEGES ON TABLE usuarios TO prueba_user;
GRANT ALL PRIVILEGES ON TABLE personas TO prueba_user;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO prueba_user;

ALTER TABLE usuarios OWNER TO prueba_user;
ALTER TABLE personas OWNER TO prueba_user;