# Distributed SMS Processing System

## Overview

This project implements a distributed SMS processing system using:

- Java Spring Boot (SMS Sender Service)
- GoLang (SMS Store Service)
- Kafka for asynchronous communication
- Redis for blocked-user validation
- MongoDB for persistent storage
- Docker for infrastructure services

---

## Architecture

### Components

### 1. SMS Sender Service (Spring Boot)

Responsibilities:
- Exposes SMS sending API
- Validates blocked phone numbers using Redis
- Simulates third-party SMS vendor
- Publishes SMS events to Kafka

### 2. SMS Store Service (GoLang)

Responsibilities:
- Consumes Kafka events
- Stores SMS data into MongoDB
- Provides logs for successful persistence

---

## Run the Project

### 1. Start Docker Services

```bash
docker-compose up -d
```

### 2. Run GoLang SMS Store Service

```bash
cd sms-store
go run main.go
```

### 3. Run Spring Boot SMS Sender Service

```bash
cd sms-sender
mvn spring-boot:run
```

---

## Redis Setup

```bash
docker exec -it redis redis-cli
SET 9876543210 blocked
```

---

## API Endpoint

### Send SMS

```http
POST http://localhost:8080/v1/sms/send
```

### Request Body

```json
{
  "phoneNumber": "9999999999",
  "message": "Hello from SMS system"
}
```

---

## Technologies Used

| Technology | Purpose |
|------------|---------|
| Spring Boot | SMS Sender Service |
| GoLang | Kafka Consumer |
| Kafka | Message Broker |
| Redis | Blocked User Validation |
| MongoDB | Persistent Storage |
| Docker | Infrastructure |
