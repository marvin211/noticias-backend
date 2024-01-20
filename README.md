# API de Noticias

Esta API proporciona un conjunto de endpoints REST para acceder a noticias. Los endpoints están protegidos por autenticación JWT.

## Requisitos

- Java 17 o superior
- Maven
- PostgreSQL

## Instalación

1. Clonar el repositorio

```bash
git clone https://github.com/marvin211/noticias-backend.git
```

2. Crear una base de datos en PostgreSQL

3. Modifica el archivo application.properties para especificar las credenciales de la base de datos:
    
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/noticias_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```
4. Ejecuta el siguiente comando para compilar y ejecutar la aplicación:

```bash
mvn clean install
mvn spring-boot:run
```

## Endpoints

La API proporciona los siguientes endpoints:

- /auth/registrar

Registra un nuevo usuario.

- /auth/login

Inicia sesión en un usuario existente.

- /noticias

Devuelve una lista de noticias.

- /noticias/{id}

Devuelve los detalles de una noticia.

- /noticias/recomendadas/{id}

Devuelve una lista de noticias recomendadas para una noticia dada.

## Documentación

La documentación de la API está disponible en la siguiente URL:

http://localhost:8080/swagger-ui/index.html#/

### Ejemplos

Registrar un usuario

``` 
curl -X POST -H "Content-Type: application/json" -d '{
"nombre": "Marvin",
"username": "test1@test.com",
"password": "12345"
}' http://localhost:8080/auth/registrar
```

Iniciar sesión

```
curl -X POST -H "Content-Type: application/json" -d '{
"username": "test1@test.com",
"password": "12345"
}' http://localhost:8080/auth/login
```

Obtener una lista de noticias
```
curl -X GET -H "Authorization: Bearer [token]" http://localhost:8080/noticias
```

Obtener los detalles de una noticia

```
curl -X GET -H "Authorization: Bearer [token]" http://localhost:8080/noticias/1
```

Obtener noticias recomendadas

```
curl -X GET -H "Authorization: Bearer [token]" http://localhost:8080/noticias/recomendadas/1
```

### Autenticación

Para acceder a los endpoints protegidos, debes obtener un token JWT. Puedes hacerlo utilizando el endpoint /auth/login.

**Nota:** El token JWT generado por el endpoint /auth/login es válido durante 1 hora.


