# Lente Inmobiliario

> **Estado:** En desarrollo — MVP en etapa inicial.

Backend de **Lente Inmobiliario**, un proyecto orientado al desarrollo de una solución inmobiliaria. Este repositorio contiene la base inicial de una API construida con Spring Boot y preparada para trabajar con MySQL y persistencia mediante Spring Data JPA.

## Tecnologías

- **Java 17**
- **Spring Boot 4.1.1**
- **Spring Web MVC** para la capa web
- **Spring Data JPA / Hibernate** para persistencia
- **MySQL** como base de datos
- **Lombok** para reducir código repetitivo
- **Maven** para gestión de dependencias y construcción

## Estado actual

El proyecto cuenta actualmente con:

- Estructura inicial de una aplicación Spring Boot.
- Clase principal para iniciar la aplicación.
- Configuración de conexión a MySQL mediante propiedades.
- Dependencias base para construir endpoints web y persistencia con JPA.
- Maven Wrapper para ejecutar Maven sin instalarlo globalmente.

**Nota:** El repositorio está en una fase temprana. Los módulos funcionales del dominio inmobiliario, endpoints de negocio, autenticación y demás características se irán documentando a medida que se implementen. No se presentan como funcionalidades terminadas.

## Requisitos

- JDK 17 o compatible.
- MySQL en ejecución.
- Git (para clonar el repositorio).

## Configuración local

1. Clona el repositorio:

   ```bash
   git clone https://github.com/orregoc1/lente-inmobiliario.git
   cd lente-inmobiliario
   ```

2. Crea una base de datos en MySQL:

   ```sql
   CREATE DATABASE Lente_inmobiliario_db;
   ```

3. Configura la conexión en `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/Lente_inmobiliario_db?useSSL=false&serverTimezone=UTC
   spring.datasource.username=TU_USUARIO
   spring.datasource.password=TU_CONTRASEÑA
   ```

   Ajusta el usuario, la contraseña y los parámetros de conexión según tu entorno. Evita subir credenciales reales al repositorio.

4. Ejecuta la aplicación con Maven Wrapper:

   **Linux / macOS**
   ```bash
   ./mvnw spring-boot:run
   ```

   **Windows**
   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

La configuración actual utiliza `spring.jpa.hibernate.ddl-auto=update` y `spring.jpa.show-sql=true`, apropiadas para desarrollo local. Revisa estas opciones antes de desplegar en producción.

## Construcción y pruebas

Para compilar y ejecutar las pruebas:

```bash
./mvnw test
./mvnw package
```

En Windows, sustituye `./mvnw` por `.\mvnw.cmd`.

## Estructura principal

```text
lente-inmobiliario/
├── src/
│   ├── main/
│   │   ├── java/com/lente/inmobiliario/
│   │   │   └── InmobiliarioApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/lente/inmobiliario/
│           └── InmobiliarioApplicationTests.java
├── .mvn/
├── mvnw
├── mvnw.cmd
└── pom.xml
```

## Próximos pasos

El alcance funcional se definirá y documentará conforme avance el MVP. Algunas áreas que podrían incorporarse, si forman parte del producto, son la gestión de inmuebles, usuarios, búsquedas y persistencia de información inmobiliaria.

## Contribuciones

Por ahora, este repositorio corresponde al desarrollo del MVP. Las sugerencias y observaciones son bienvenidas mediante Issues.

## Autor

Desarrollado por [orregoc1](https://github.com/orregoc1).
