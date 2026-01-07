**Project Overview**

The General Store is a modern E-Commerce application built using a microservices architecture. This platform demonstrates scalable backend services for an online store, with containerized deployment and API-driven design.

**Architecture & Structure**

The application follows a microservices pattern with independently deployable services:
The-General-Store/
├── product/          # Product catalog service
├── order/            # Order processing service  
├── user/             # User management service
├── docker-compose.yml # Container orchestration
└── .idea/            # IDE configuration

**Clone the repository:**

git clone https://github.com/SOFT-WHERE/The-General-Store.git
cd The-General-Store

**Start the application with Docker:**

docker-compose up -d


**API Documentation**

The project includes a Postman collection with all required API calls:
File: eCommerce_postman_collection.json
Import this file into Postman to test all available endpoints
Covers product, order, and user service APIs

Service Configuration
Product Service
Configured with MySQL container image

Runs on port: 8081 (as per your provided configuration)

Order Service
Docker container configuration included

Integrated with product service

User Service
User management functionality

Part of the microservices ecosystem

**Docker Configuration**

The docker-compose.yml file orchestrates all microservices:
Containerized deployment for each service
Service discovery and inter-service communication
Database containers for each service's data persistence

**Database**

MySQL for product service (configurable for other services)
Containerized database instances
Schema management through application configuration

**Testing**

Import the provided Postman collection
Start services using Docker Compose
Execute API calls in sequence:
User registration/authentication
Product browsing
Order creation and management


Troubleshooting
Ensure Docker is running before docker-compose up
Check port availability if services fail to start
Verify database connections in service configuration files
Use docker-compose logs [service-name] to debug specific services
