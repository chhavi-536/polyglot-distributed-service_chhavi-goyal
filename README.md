Distributed SMS Processing System
Overview

This project implements a distributed SMS processing system using:

Java Spring Boot (SMS Sender Service)
GoLang (SMS Store Service)
Kafka for asynchronous communication
Redis for blocked-user validation
MongoDB for persistent storage
Docker for infrastructure services

The system accepts SMS requests, validates blocked users using Redis, simulates a third-party SMS vendor call, publishes SMS events to Kafka, and stores SMS records in MongoDB through a GoLang consumer service.


Architecture
Components
1. SMS Sender Service (Spring Boot)
Responsibilities:
Exposes SMS sending API
Validates blocked phone numbers using Redis
Simulates third-party SMS vendor
Publishes SMS events to Kafka

2. SMS Store Service (GoLang)
Responsibilities:
Consumes Kafka events
Stores SMS data into MongoDB
Provides logs for successful persistence

3. Infrastructure
Kafka → Message broker
Zookeeper → Kafka dependency
Redis → Block list storage
MongoDB → Persistent database



Run the Project
1. Start Docker Services
docker-compose up -d

2. Run GoLang SMS Store Service
cd sms-store
go run main.go

3. Run Spring Boot SMS Sender Service
cd sms-sender
mvn spring-boot:run

4. Add Blocked User in Redis
docker exec -it redis redis-cli
SET 9876543210 blocked

5. Send SMS Request
Endpoint
POST http://localhost:8080/v1/sms/send
Request Body
{
  "phoneNumber": "9999999999",
  "message": "Hello from SMS system"
}
