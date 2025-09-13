# eKart — Spring Cloud Microservices E-Commerce

[![Java](https://img.shields.io/badge/Java-24%2B-blue)]()
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)]()
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-202x--x-success)]()
[![Maven](https://img.shields.io/badge/Maven-3.x-orange)]()

A modular, cloud-native **e-commerce backend** built with **Spring Boot** and **Spring Cloud**. It demonstrates production-style patterns—**API Gateway**, **Service Discovery** (Eureka), **declarative inter-service calls** (OpenFeign) and **validation**—with clean separation of concerns.

---

## ✨ Core Functionality

- **Products**
  - CRUD for products (create, list, get by id, update, delete)
- **Users**
  - CRUD for users (DTO validation with Jakarta Validation)
- **API Gateway**
  - Single entry point for clients (`/api/**`)
  - Route mapping, request filtering
- **Service Discovery**
  - All services auto-register to **Eureka**
- **Inter-Service Communication**
  - **OpenFeign** clients (e.g., Order → Product, User)


```
eKart/
├─ api-gateway/           
├─ eureka-server/         
├─ product-service/               
└─ user-service/ 
```

---

## 🧰 Tech Stack

- **Language:** Java 17+
- **Build:** Maven
- **Frameworks:** Spring Boot 3.x, Spring Web, Spring Data JPA
- **Cloud:** Spring Cloud (Gateway, Eureka, OpenFeign)
- **DB:** PostgreSQL (prod)
- **Validation:** Jakarta Bean Validation

---

## 🏗️ Architecture

```mermaid
## 🏗️ Architecture

```mermaid
flowchart LR
  Client[(Client)] -->|HTTPS/REST| APIGW[API Gateway]

  subgraph Discovery
    ES[Eureka Server (8761)]
  end

  APIGW -->|/api/products/**| P[Product Service]
  APIGW -->|/api/users/**| U[User Service]

  P <-->|OpenFeign| U

  P <--> DB1[(DB: PostgreSQL/H2)]
  U <--> DB2[(DB: PostgreSQL/H2)]

  APIGW --- ES
  P --- ES
  U --- ES
```

---

## 🚀 Quick Start (Local)

1) **Eureka Server** (default **8761**)
```bash
cd eureka-server
./mvnw spring-boot:run
```

2) **Product Service** (e.g., **9001**)
```bash
cd product-service
./mvnw spring-boot:run
```

3) **User Service** (e.g., **9000**)
```bash
cd user-service
./mvnw spring-boot:run
```

4) **API Gateway** (e.g., **8080**)
```bash
cd api-gateway
./mvnw spring-boot:run
```

- Eureka dashboard: `http://localhost:8761`
- Gateway entry: `http://localhost:8080`

---

## 🧪 API Samples

### Users
```bash
curl -X POST http://localhost:8080/api/users   -H "Content-Type: application/json"   -d '{"username":"john","email":"john@example.com","password":"Secret@123"}'
```

### Products
```bash
curl -X POST http://localhost:8080/api/{userId}/products   -H "Content-Type: application/json"   -d '{"name":"iphone17","category":"phone","description":"new technology","price":59000}'

```

---

## ✅ Validation Example (DTO)

```java
public record UserDTO(
  @NotBlank @Size(min=6) String username,
  @Email @NotBlank String email,
  @NotBlank @Size(min=8) String password
) {}
```

---
