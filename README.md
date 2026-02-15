# Incident Tracker — Full Stack Project

## Overview

This repository contains my Backend and Frontend for the **Incident Tracker Full Stack Project**...

The application is a lightweight incident management system that allows engineers to:

* Create production incidents with validation
* Browse incidents with server-side pagination
* Filter incidents by service, severity, and status
* View incident details
* Update incident status with lifecycle rules

The backend focuses on clean architecture, validation, and efficient querying, while the frontend provides a simple UI to demonstrate functionality.

> The frontend UI was developed with the assistance of an AI coding agent to accelerate prototyping while keeping the primary focus on backend design and API correctness.

---

# Tech Stack

## Backend

* Java 21
* Spring Boot
* Spring Data JPA
* MySQL
* Maven

## Frontend

* HTML
* CSS
* JavaScript

---

# Project Structure

```
incident-tracker
 ┣ backend        → Spring Boot API
 ┗ frontend       → UI application
```

---

# Setup & Run Instructions

## 1️⃣ Clone Repository

```bash
git clone https://github.com/demoralizersde/incident-tracker-engine.git
cd incident-tracker
```

---

## 2️⃣ Backend Setup

### Create Database

```sql
CREATE DATABASE incidents;
```

### Create Table Query

```sql
CREATE TABLE `incidents` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) DEFAULT NULL,
  `service` VARCHAR(100) DEFAULT NULL,
  `severity` VARCHAR(50) DEFAULT NULL,
  `status` VARCHAR(50) DEFAULT NULL,
  `owner` VARCHAR(100) DEFAULT NULL,
  `summary` TEXT,
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP 
        ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),

  INDEX idx_service_severity_status (`service`, `severity`, `status`),
  INDEX idx_severity_status (`severity`, `status`),
  INDEX idx_status (`status`)
);
```

### Configure application.properties

```
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/incidents
spring.datasource.username=root
spring.datasource.password=<your-password>

spring.jpa.hibernate.ddl-auto=update
```

---

### Run Backend

```bash
cd backend
./mvnw spring-boot:run
```

Backend runs at:

```
http://localhost:8080
```

---

## 3️⃣ Frontend Setup

Run index.html using chrome or any other browser

---

# Database Schema

The `incidents` table stores all incident records and metadata.

---

## Indexing Strategy

Indexes were designed based on query patterns used by the filtering API.

### Composite Index

`(service, severity, status)`
Optimizes queries filtering on all three parameters simultaneously.

### Partial Composite Index

`(severity, status)`
Improves performance when service filter is not provided.

### Single Column Index

`(status)`
Used for quick retrieval of incidents by status.

This approach reduces full table scans and improves pagination performance.

---

# API Overview

## Create Incident

POST `/api/incidents`

Creates a new incident with validation.

---

## Fetch Incidents (Pagination + Filters)

GET `/api/incidents`

Query Params:

* service
* severity
* status
* page
* size

---

## Get Incident by ID

GET `/api/incidents/{id}`

---

## Update Incident Status

PATCH `/api/incidents/{id}/status?status=RESOLVED`

Supported transitions:

* OPEN → MITIGATED
* OPEN → RESOLVED
* MITIGATED → RESOLVED

---

# Consistent API Response Design

A generic `ApiResponseDTO` wrapper was implemented to maintain a consistent response format across all endpoints.

## Response Structure

```json
{
  "status": true,
  "statusCode": 200,
  "msg": "Request successful",
  "data": {}
}
```

## Benefits

* Predictable API contract
* Simplifies frontend handling
* Consistent success and error messaging
* Easier debugging

---

# Global Exception Handling

A centralized `GlobalExceptionHandler` was implemented using `@RestControllerAdvice`.

## Responsibilities

* Handles validation errors
* Handles custom exceptions (InvalidRequestException, ResourceNotFoundException)
* Returns structured error responses

## Example Error Response

```json
{
  "status": false,
  "statusCode": 400,
  "msg": "Service is required",
  "data": null
}
```

## Benefits

* Keeps controllers clean
* Ensures consistent error formatting
* Improves maintainability

---

# Design Decisions & Tradeoffs

## RESTful API Design

Endpoints follow REST principles for clarity and maintainability.

## Server-Side Pagination

Implemented using Spring Data `Pageable` to handle large datasets efficiently.

## JPQL Query Filtering

Chosen over Specifications for readability and simplicity.

Tradeoff:
Specifications provide more flexibility but add complexity for this scope.

## Status Lifecycle Management

Implemented explicit transition rules to maintain realistic incident workflows.

---

# Improvements With More Time

If given additional time, the following enhancements would be implemented to make the system production-ready:

### Notifications & Alerting

* Trigger notifications (email or messaging integrations) when an incident is created, especially for high-severity incidents (e.g., SEV1 / SEV2).
* Notify stakeholders when incident status changes (for example, when an incident is mitigated or resolved).

### Scheduled Monitoring

* Introduce a scheduler to periodically check the status of open incidents.
* Automatically send reminders or reports to the assigned owner if incidents remain unresolved beyond a defined threshold.
* Generate daily summary reports for operational visibility.

### Security & Access Control

* Add authentication and role-based authorization (Admin / Engineer roles).

### Observability & Audit

* Implement audit logging to track status changes and updates.
* Add monitoring and metrics integration.

### Testing & Deployment

* Add unit and integration tests.
* Dockerize the application for easier deployment.

### UI Enhancements

* Improve frontend with a component-based framework.
* Add better UX elements such as search, sorting, and notifications.

---

# Key Highlights

* Clean layered backend architecture
* Efficient filtering with indexed columns
* Consistent API response contract
* Centralized error handling
* Realistic incident lifecycle management
* Lightweight UI for demonstration

---

# Author

**Aman Kumar**

---
