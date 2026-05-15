Distributed SMS Processing System

A distributed microservices-based SMS processing platform built using Spring Boot, GoLang, Kafka, Redis, and MongoDB. The system validates blocked users, processes SMS requests asynchronously through Kafka, and stores SMS records reliably in MongoDB.

Project Structure
distributed-sms-system/
├── sms-sender/                 # Spring Boot SMS Sender Service (Port 8080)
│   ├── src/main/java/
│   ├── src/main/resources/
│   └── pom.xml
│
├── sms-store/                  # GoLang SMS Store Service
│   ├── main.go
│   ├── consumer/
│   └── go.mod
│
├── docker-compose.yml          # Kafka, Zookeeper, Redis, MongoDB setup
├── docs/                       # Documentation files
│   └── architecture.md
└── README.md
System Architecture
Components
1. SMS Sender Service (Spring Boot)

Responsibilities:

Exposes REST API for sending SMS
Validates blocked users using Redis
Simulates third-party SMS vendor call
Publishes SMS events to Kafka
2. SMS Store Service (GoLang)

Responsibilities:

Consumes Kafka SMS events
Stores SMS data into MongoDB
Logs successful persistence events
3. Infrastructure Services
Kafka → Asynchronous message broker
Zookeeper → Kafka dependency
Redis → Blocked-user validation
MongoDB → Persistent SMS storage
Docker → Containerized infrastructure
Quick Start
Start Docker Infrastructure
docker-compose up -d

This starts:

Kafka
Zookeeper
Redis
MongoDB
Run Services
Start GoLang SMS Store Service
cd sms-store
go run main.go
Start Spring Boot SMS Sender Service
cd sms-sender
mvn spring-boot:run
Redis Blocked User Setup

Open Redis CLI:

docker exec -it redis redis-cli

Add a blocked phone number:

SET 9876543210 blocked
API Endpoint
Send SMS

Endpoint

POST http://localhost:8080/v1/sms/send
Request Body
{
  "phoneNumber": "9999999999",
  "message": "Hello from SMS system"
}
Workflow
User sends SMS request to Spring Boot service
Service checks blocked users in Redis
If valid:
Simulates external SMS vendor API call
Publishes SMS event to Kafka
GoLang consumer reads Kafka event
SMS data gets stored in MongoDB
Features
Distributed Microservices Architecture
Asynchronous Kafka Communication
Redis-based User Blocking
MongoDB Persistent Storage
Dockerized Infrastructure
Scalable Event-driven Design
Fault Isolation Between Services
