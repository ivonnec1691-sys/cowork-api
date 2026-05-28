# CoworkLima API

## Datos:
- **Nombre:** Ivonne Coaquira Sarmiento
- **Curso:** Desarrollo Backend con Java/Spring Boot
- **Repositorio:** https://github.com/ivonnec1691-sys/cowork-api

---

## Cómo ejecutar el proyecto

```bash
./mvnw spring-boot:run
```

La app arranca en: http://localhost:9090

---

## Endpoints disponibles

### Información general
| Método | Ruta      | Descripción               |
|--------|-----------|---------------------------|
| GET    | /api/info | Info de la app y versión  |

### Salas
| Método | Ruta            | Status | Descripción                |
|--------|-----------------|--------|----------------------------|
| GET    | /api/salas      | 200    | Listar todas las salas     |
| GET    | /api/salas/{id} | 200    | Obtener sala por ID        |
| POST   | /api/salas      | 201    | Crear nueva sala           |
| PUT    | /api/salas/{id} | 200    | Actualizar sala existente  |
| DELETE | /api/salas/{id} | 204    | Eliminar sala              |

### Reservas
| Método | Ruta                           | Status | Descripción                        |
|--------|--------------------------------|--------|------------------------------------|
| POST   | /api/reservas                  | 201    | Crear reserva                      |
| GET    | /api/reservas/{id}             | 200    | Obtener reserva por ID             |
| GET    | /api/reservas                  | 200    | Listar con filtros opcionales      |
| GET    | /api/reservas/sala/{salaId}    | 200    | Listar reservas de una sala        |
| PUT    | /api/reservas/{id}/estado      | 200    | Cambiar estado                     |
| DELETE | /api/reservas/{id}             | 204    | Eliminar reserva                   |
| POST   | /api/reservas/{id}/comprobante | 200    | Subir PDF comprobante              |

---

## Arquitectura en capas

**Controller** — Recibe las peticiones HTTP y delega al Service. Nunca accede al Repository directamente, responde solicitudes.

**Service** — Contiene las reglas de negocio y coordina Repository y Mapper.

**Repository** — Almacena datos en memoria con List<> y AtomicLong para IDs, también podemos acceder a la base de datos.

**DTO** - Los DTOs son Java Records, contrato de entrada y salidas, clases simples para mapear.
 
** Mapper** — El Mapper es el traductor entre model y DTO. En el ejercicio el campo passwordInterno nunca aparece en las respuestas.