# BookMyShow Clone

A backend implementation of a movie-ticket booking platform (à la [BookMyShow](https://in.bookmyshow.com/)), built with **Spring Boot 3** and **PostgreSQL**. The project models the core domain of cinemas, shows and seat-level booking, and implements a concurrency-safe ticket booking flow.

> **Status:** Work in progress / learning project. The domain model and the ticket-booking service are the most complete parts. Several controllers and services are stubs or still being wired up (see [Current status](#current-status)).

---

## Table of contents

- [Tech stack](#tech-stack)
- [Domain model](#domain-model)
- [Booking flow](#booking-flow)
- [Project structure](#project-structure)
- [API endpoints](#api-endpoints)
- [Getting started](#getting-started)
- [Configuration](#configuration)
- [Current status](#current-status)
- [Design notes](#design-notes)

---

## Tech stack

| Concern            | Choice                                            |
| ------------------ | ------------------------------------------------- |
| Language           | Java 17                                            |
| Framework          | Spring Boot 3.2.1 (Web, Data JPA)                 |
| Persistence        | Spring Data JPA / Hibernate                        |
| Database           | PostgreSQL (migrated from MySQL)                   |
| Security / hashing | Spring Security Crypto (BCrypt password encoding)  |
| Boilerplate        | Project Lombok                                     |
| Build              | Maven (via the bundled `mvnw` wrapper)             |
| Dev loop           | Spring Boot DevTools                               |

---

## Domain model

All entities extend `BaseModel`, which provides an auto-generated `id` and JPA-audited `createdAt` / `updatedAt` timestamps (enabled by `JpaAuditConfig`).

### Entities

| Entity        | Description                                                                 | Key relationships |
| ------------- | --------------------------------------------------------------------------- | ----------------- |
| `City`        | A city that hosts theaters.                                                 | `1 → N` `Theater` |
| `Theater`     | A physical cinema with a name and address.                                  | `1 → N` `Auditorium` |
| `Auditorium`  | A screen/hall inside a theater, with a list of seats and features.          | `1 → N` `Seat`, features |
| `Seat`        | A physical seat (seat number, row, col, seat type).                         | — |
| `Movie`       | A movie (name, description, language, features).                            | — |
| `Show`        | A screening of a movie in an auditorium for a time window.                  | `N → 1` `Movie`, `N → 1` `Auditorium`, `1 → N` `ShowSeat` |
| `ShowSeat`    | A seat *for a particular show*, with a per-show price and booking status.   | `N → 1` `Show`, `N → 1` `Seat` |
| `User`        | A registered user (name, email, BCrypt-hashed password). Table `BMS_USERS`. | `1 → N` `Ticket` |
| `Ticket`      | A booking: the user's selected show-seats for a show, amount and status.    | `N → 1` `User`, `N → 1` `Show`, `1 → 1` `Payment`, `1 → N` `ShowSeat` |
| `Payment`     | The payment for a ticket, with mode, status and transactions.               | `1 → N` `Transaction` |
| `Transaction` | An individual payment attempt (amount, mode, status, time, reference id).   | — |

### Enums (`models/constants`)

- **`ShowSeatStatus`** — `AVAILABLE`, `BOOKED`, `LOCKED`
- **`TicketStatus`** — `CONFIRMED`, `CANCELLED`, `IN_PROGRESS`
- **`PaymentStatus`** / **`FinalPaymentStatus`** — `PENDING`, `SUCCESS`, `FAILED`
- **`PaymentMode`** — `CREDIT_CARD`, `DEBIT_CARD`, `UPI`, `NET_BANKING`, `WALLET`
- **`Feature`** — `THREE_D`, `IMAX`, `DOLBY_ATMOS`, `GOLD_CLASS`, `PLATINUM_CLASS`
- **`SeatType`** — *(defined, no values yet)*

### Hierarchy at a glance

```
City → Theater → Auditorium → Seat
                     │
Movie ──→ Show ──────┘
            │
            └── ShowSeat (Seat + price + status)
                   │
User ──→ Ticket ───┘ + Payment → Transaction
```

---

## Booking flow

Ticket booking is implemented in `TicketServiceImpl.bookTicket(userId, showSeats)` and runs inside a single transaction with **`SERIALIZABLE`** isolation (`rollbackFor = Exception.class`) to prevent two users from booking the same seat concurrently:

1. **Resolve user** — look up the user; abort if not found.
2. **Validate availability** — every requested `ShowSeat` must be `AVAILABLE`, otherwise an `InvalidShowSeatSelectionException` is thrown.
3. **Lock seats** — set each seat to `LOCKED` and persist.
4. **Create ticket** — build a `Ticket` (user, seats, show, summed amount, payment) with status `IN_PROGRESS`. *Payment is currently assumed successful — a real payment integration is a TODO.*
5. **Confirm seats** — set each seat to `BOOKED`.
6. **Confirm ticket** — set status `CONFIRMED` and save.

If any step fails, the whole transaction rolls back, releasing the locks.

---

## Project structure

```
src/main/java/com/bookmyshow/demo
├── DemoApplication.java          # Spring Boot entry point
├── config/                       # JPA auditing + BCrypt PasswordEncoder beans
├── controller/                   # REST controllers (User, City, Ticket, Auditorium)
├── service/                      # Business logic (User, City, Ticket, ShowSeat, Auditorium)
├── repository/                   # Spring Data JPA repositories (one per entity)
├── models/                       # JPA entities
│   └── constants/                # Enums
├── dto/                          # Request/response DTOs + marker interfaces
├── mapper/                       # Entity <-> DTO mappers (User, Ticket)
├── utils/                        # Request validation (ValidationResult, validators)
└── exceptions/                   # Custom exceptions
```

---

## API endpoints

> Base URL: `http://localhost:8080`

### User

| Method | Path     | Body / Params                        | Description |
| ------ | -------- | ------------------------------------ | ----------- |
| `POST` | `/user`  | `CreateUserReqDTO` (name, email, password) | Validates the request, BCrypt-hashes the password, creates the user and returns a `UserResponseDTO`. |
| `GET`  | `/user`  | `?userId=<id>`                       | Returns the user as a `UserResponseDTO`, or `404` if not found. |

**User validation rules** (`UserReqValidationImpl`): email is required and must match a standard email pattern; name is required; password must be ≥ 8 chars and contain an uppercase letter, a lowercase letter, a number and a special character.

### City

| Method | Path          | Body / Params | Description |
| ------ | ------------- | ------------- | ----------- |
| `POST` | `/city`       | `City` body   | Create a city. |
| `GET`  | `/city`       | —             | List all cities. |
| `GET`  | `/city/{name}`| path          | Get city by name. |
| `GET`  | `/city/{id}`  | path          | Get city by id. |

### Ticket

`TicketController` exposes `createTicket` / `getTicketById` methods that call `TicketServiceImpl`, but the HTTP mappings are not yet wired up (see below).

---

## Getting started

### Prerequisites

- JDK 17+
- PostgreSQL running locally
- (Maven is provided via the `./mvnw` wrapper — no separate install needed)

### 1. Create the database

```sql
CREATE DATABASE bookmyshow;
```

The schema is created/updated automatically by Hibernate (`ddl-auto=update`) on startup.

### 2. Configure credentials

Edit `src/main/resources/application.properties` to match your local PostgreSQL user (see [Configuration](#configuration)).

### 3. Run

```bash
./mvnw spring-boot:run
```

The app starts on **`http://localhost:8080`**.

### Other useful commands

```bash
./mvnw clean package        # build a jar
./mvnw test                 # run tests
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

---

## Configuration

`src/main/resources/application.properties`:

```properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.show-sql=true
spring.datasource.url=jdbc:postgresql://localhost:5432/bookmyshow
spring.datasource.username=rohith
spring.datasource.password=
```

> Update `spring.datasource.username` / `spring.datasource.password` for your environment. For anything beyond local development, move credentials out of the properties file and into environment variables or a secrets manager.

---

## Current status

What works / is in place:

- Full JPA domain model with auditing timestamps.
- User registration with request validation and BCrypt password hashing.
- City CRUD-ish endpoints.
- Concurrency-safe ticket booking logic (`SERIALIZABLE` transaction, seat locking).

Known gaps / TODOs:

- **`TicketController`** methods are not annotated with request mappings yet, and `createTicket` builds the ticket but still returns an empty `200`.
- **`CityController`** declares two `addCity` methods mapped to the same `POST /city` — this is an ambiguous mapping that will need to be resolved (distinct paths or a single method).
- **`AuditoriumController`** is an empty class (not yet a REST controller); `AuditoriumServiceImpl` has no behavior yet.
- **Payment** is assumed successful inside the booking flow — no real payment/transaction processing.
- No Show/Movie/Theater/Auditorium management endpoints yet.
- No global exception handler, so custom exceptions surface as default error responses.

---

## Design notes

- **Interfaces & markers** — `IReqDTO` / `IResponseDTO` are marker interfaces, and `IRequestValidator<T>` provides a generic validation contract (`UserReqValidationImpl` is the first implementation).
- **Mappers** — plain static mappers (`UserMapper`, `TicketMapper`) keep entities and DTOs decoupled.
- **Password handling** — passwords are never stored in plaintext; `PasswordConfig` registers a `BCryptPasswordEncoder` bean used during user creation.
- **Auditing** — `@EnableJpaAuditing` + `BaseModel`'s `@CreatedDate` / `@LastModifiedDate` populate timestamps automatically.

---

*This README documents a learning project and will evolve as the remaining controllers, services and payment flow are completed.*
