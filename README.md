# Prueba Técnica B4 - API REST con Spring Boot

Este proyecto es una implementación de una API REST utilizando Spring Boot, desarrollada como parte de la Prueba Técnica B4 para [nombre de la empresa].

## Descripción

Esta API proporciona funcionalidades para:
- Autenticación de usuarios (registro y login) utilizando JWT.
- Carga y procesamiento de archivos Excel con información de personas.
- Almacenamiento de datos en una base de datos PostgreSQL.

## Requisitos

- Java 17 o superior
- Maven 3.6.3 o superior
- PostgreSQL

## Configuración

1. Clonar el repositorio:
   ```
   git clone [URL del repositorio]
   ```

2. Configurar la base de datos PostgreSQL:
   - Crear una base de datos llamada `prueba_tecnica_b4`
   - Actualizar las credenciales de la base de datos en `src/main/resources/application.properties`:

     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/prueba_tecnica_b4
     spring.datasource.username=prueba_user
     spring.datasource.password=prueba_password
     spring.jpa.hibernate.ddl-auto=update
     ```

   Asegúrate de reemplazar `prueba_user` y `prueba_password` con tus credenciales reales de PostgreSQL.

3. Compilar el proyecto:
   ```
   mvn clean install
   ```
## Ejecución

Para ejecutar la aplicación:

```
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080`

## Endpoints principales

- POST `/api/auth/register`: Registro de usuarios
- POST `/api/auth/login`: Login de usuarios
- POST `/api/excel/upload`: Carga de archivo Excel
- GET `/api/excel/persons`: Obtener todas las personas registradas

## Pruebas

### Preparación para pruebas manuales

Antes de realizar pruebas manuales o de integración, es necesario registrar un usuario a través de la API REST. Puedes hacer esto utilizando una herramienta como Postman o curl:

1. Registrar un nuevo usuario:
   ```
   POST http://localhost:8080/api/auth/register
   Content-Type: application/json

   {
     "username": "testuser",
     "password": "testpassword"
   }
   ```

2. Una vez registrado, puedes usar este usuario para probar el endpoint de login y obtener un token JWT:
   ```
   POST http://localhost:8080/api/auth/login
   Content-Type: application/json

   {
     "username": "testuser",
     "password": "testpassword"
   }
   ```

### Pruebas unitarias

Este proyecto incluye tests unitarios utilizando JUnit 4 y Mockito. Para ejecutar las pruebas unitarias:

```
mvn test
```

Los tests unitarios cubren componentes clave del backend, incluyendo el servicio de autenticación.

## Tecnologías utilizadas

- Spring Boot
- Spring Security
- JWT para autenticación
- JPA/Hibernate
- PostgreSQL
- Apache POI para procesamiento de Excel

## Docker

Este proyecto incluye configuración para Docker y Docker Compose, lo que facilita la ejecución de la aplicación junto con su base de datos PostgreSQL.

### Requisitos

- Docker
- Docker Compose

### Ejecutar la aplicación con Docker Compose

1. Asegúrate de estar en el directorio raíz del proyecto donde se encuentra el archivo `docker-compose.yml`.

2. Ejecuta el siguiente comando para construir y iniciar los contenedores:

   ```
   docker-compose up --build
   ```

   Este comando construirá la imagen de la aplicación, descargará la imagen de PostgreSQL si es necesario, y iniciará ambos servicios.

3. La aplicación estará disponible en `http://localhost:8080` y la base de datos PostgreSQL en `localhost:5432`.

4. Para detener los contenedores, puedes usar:

   ```
   docker-compose down
   ```

### Notas importantes

- La base de datos se inicializa automáticamente con el nombre `prueba_tecnica_b4`, el usuario `prueba_user` y la contraseña `prueba_password`.
- Los datos de la base de datos se persisten en un volumen Docker llamado `postgres_data`.
- La aplicación espera a que la base de datos esté lista antes de intentar conectarse.
- 
## Requisitos específicos de la prueba técnica

Este proyecto cumple con los siguientes requisitos de la Prueba Técnica B4:

- Implementación de microservicio con Spring Boot
- Procesamiento de archivo Excel con al menos 5 registros
- Almacenamiento en base de datos PostgreSQL
- Implementación de servicio de autenticación
- Uso de Java 17 y Maven 3.6.3 o superior
- Creación de tests unitarios con JUnit 4 y Mockito
- Implementación de JWT para el servicio de login

## Pruebas

Este proyecto incluye tests unitarios utilizando JUnit 4 y Mockito. Para ejecutar las pruebas:

```
mvn test
```

Los tests unitarios cubren componentes clave del backend, incluyendo el servicio de autenticación.