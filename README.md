Backend API - Gestión de Empresas y Trabajadores
```markdown
# Sistema de Gestión de Empresas y Trabajadores
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.1-brightgreen)
![Swagger](https://img.shields.io/badge/Swagger-3.0-85EA2D)
![License](https://img.shields.io/badge/license-MIT-blue)


## Descripción
API REST desarrollada con Spring Boot para la gestión de empresas y trabajadores, incluyendo validación de RUT chileno y documentación Swagger.

## Stack Tecnológico

### Backend
- Java 17
- Spring Boot 3.2.1
- Spring Data JPA
- PostgreSQL (Base de datos)
- Maven (Gestión de dependencias)

### Documentación
- Swagger / OpenAPI 3.0
- Spring Doc

## Pre-requisitos
- Git
- JDK 17+
- Maven 3.8+
- PostgreSQL 12+

## Instalación

1. Clonar el repositorio:
```bash
git clone https://github.com/CharlieBravo90Byte/desafiopvr-backend.git
cd desafiopvr-backend
```

2. Configurar base de datos PostgreSQL en `application.properties`:

spring.datasource.url=jdbc:postgresql://localhost:5432/db_desafioprevired
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

3. Compilar el proyecto:
```bash
mvn clean install
```

4. Ejecutar la aplicación:
```bash
mvn spring-boot:run
```

## Configuración del Proyecto

### Dependencias Maven (`pom.xml`)
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.2.1</version>
</parent>

<dependencies>
    <!-- Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- PostgreSQL -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
    </dependency>

    <!-- Swagger -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.1.0</version>
    </dependency>
</dependencies>
```

## Estructura de Directorios
```
src/
├── main/
│   ├── java/cl/charlie/desafioprevired
│   │   ├── config/
│   │   ├── controller/
│   │   ├── model/
│   │   ├── repository/
│   │   ├── service/
│   │   └── util/
│   └── resources/
│       └── application.properties
```

## API REST Endpoints

### Empresas
```
GET    /api/empresas       # Listar empresas
POST   /api/empresas       # Crear empresa
GET    /api/empresas/{id}  # Obtener empresa
PUT    /api/empresas/{id}  # Actualizar empresa
DELETE /api/empresas/{id}  # Eliminar empresa
```

### Trabajadores
```
GET    /api/empresas/{empresaId}/trabajadores  # Listar trabajadores
POST   /api/empresas/{empresaId}/trabajadores  # Crear trabajador
GET    /api/trabajadores/{id}                  # Obtener trabajador
PUT    /api/trabajadores/{id}                  # Actualizar trabajador
DELETE /api/trabajadores/{id}                  # Eliminar trabajador
```

## Documentación
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- API Docs: http://localhost:8080/v3/api-docs

## Características Principales

### Validación de RUT
- Implementa algoritmo de validación de RUT chileno
- Verifica unicidad en la base de datos
- Formato: XX.XXX.XXX-X

### Gestión de Empresas
- CRUD completo
- Validación de datos
- Asociación con trabajadores

### Gestión de Trabajadores
- CRUD completo
- Validación de RUT
- Asociación con empresa

## Pruebas
Ejecutar tests unitarios:
```bash
mvn test
```

## Despliegue
1. Generar JAR:
```bash
mvn package
```

2. Ejecutar JAR:
```bash
java -jar target/desafiopvr-backend-1.0.0.jar
```


## Issues y Sugerencias
Para reportar problemas o sugerir mejoras:
https://github.com/CharlieBravo90Byte/desafiopvr-backend/issues

## Licencia
Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para detalles.

## Autor
CharlieBravo90Byte
- GitHub: [@CharlieBravo90Byte](https://github.com/CharlieBravo90Byte)
