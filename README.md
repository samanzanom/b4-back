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

Este proyecto incluye un Dockerfile para facilitar su despliegue en contenedores.

### Construir la imagen Docker

1. Asegúrate de tener Docker instalado en tu sistema.
2. Navega hasta el directorio raíz del proyecto donde se encuentra el Dockerfile.
3. Ejecuta el siguiente comando para construir la imagen Docker:

```
docker build -t prueba-tecnica-b4 .
```

### Ejecutar el contenedor

Una vez que la imagen se ha construido, puedes ejecutar el contenedor con el siguiente comando:

```
docker run -p 8080:8080 prueba-tecnica-b4
```

Este comando iniciará el contenedor y mapeará el puerto 8080 del contenedor al puerto 8080 de tu máquina host.

### Notas sobre Docker

- Asegúrate de que la aplicación esté configurada para conectarse a la base de datos PostgreSQL correctamente cuando se ejecute en un contenedor. Puedes necesitar ajustar la configuración de la base de datos en `application.properties` o usar variables de entorno.
- Si necesitas pasar variables de entorno al contenedor, puedes hacerlo usando la opción `-e` en el comando `docker run`.


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