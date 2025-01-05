# project_modules
EcomProduct Service, User Service, Notification Service, Payment Service
This project is a scalable and robust e-commerce platform built using modern technologies such as Spring Boot, Apache Kafka, PostgreSQL, and Redis. It supports features like product catalog management, order processing, user authentication, and a seamless product searching experience.
Tech Stack
Backend:
Spring Boot: To build RESTful APIs and implement business logic.
Apache Kafka: For real-time event streaming and asynchronous communication for Notification service
PostgreSQL: To store transactional data like orders, users, and products.
Redis: For storing product details fetched from third party api and caching frequently accessed data.
Installation
Prerequisites:
Java 17, SpringBoot 3.1.5.
PostgreSQL installed and configured as per application.properties
Redis installed and configured using docker.
docker run --name redis -p 6379:6379 -d redis
docker exec -it redis redis-cli
Postman to check the proper working of API.
