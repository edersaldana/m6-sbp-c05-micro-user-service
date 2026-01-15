# User Service (Identity & Auth)

Este microservicio es el corazón de la seguridad del ecosistema. Gestiona la identidad de los usuarios, la autenticación y el control de acceso basado en roles.

## Especificaciones
* **Puerto:** `8081`
* **Base de Datos:** `user_db` (PostgreSQL)
* **Seguridad:** JWT (JSON Web Tokens)

## Funcionalidades
- **Registro:** Creación de nuevos usuarios con contraseñas encriptadas.
- **Autenticación:** Validación de credenciales y generación de Token JWT.
- **Autorización:** Asignación de roles (`USER`, `ADMIN`).

## Endpoints Principales
| Método | Endpoint         | Descripción                | Acceso |
| :---   | :---             | :---                       | :---   |
| `POST` | `/auth/register` | Registro de nuevo usuario  | Público |
| `POST` | `/auth/login`    | Login y obtención de Token | Público |

##  Instalación
1. Crear base de datos `user_db` en PostgreSQL.
2. Configuraciones credenciales en `src/main/resources/application.yml`, `src/main/resources/application-dev.yml`, `src/main/resources/application-local.yml`.
3. Ejecutar: `mvn spring-boot:run`.
