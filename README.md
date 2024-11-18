# Smatech POS API

**Smatech POS API** is a robust and scalable RESTful API developed using **Spring Boot**. It powers point-of-sale systems by handling essential operations such as managing products, orders.

## Features
- **Product Management**: CRUD operations for products.
- **Order Processing**: Support for creating and managing orders.
- **Swagger Integration**: API documentation for easy testing and reference.

---

## Technologies Used
- **Java 17**: For compatibility with modern Java features.
- **Spring Boot**: Provides the framework for rapid development.
- **Spring Data JPA**: Manages database operations seamlessly.
- **Swagger UI**: Interactive API documentation.
- **PostgeSQL**: A powerful, open-source relational database system for handling SQL operations efficiently.
- **Maven**: Dependency and project management.
- **Lombok**: Simplifies boilerplate code.

---

## Prerequisites
Ensure you have the following installed:
- Java Development Kit (JDK) 17 or higher
- Apache Maven
- PostreSQL
- Postman (optional, for API testing)

---

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/ishe19/Smatech_POS_API.git
cd smatech_POS_API
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

The API will start at `http://localhost:9078`.

---

## Configuration

### Database
By default, the API uses the PostgreSQL. You can configure a different database by updating the `application.properties` file:

```properties
# Example for MySQL
spring.datasource.url=jdbc:mysql://localhost:9078/smatech_pos_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

---

## API Endpoints


### Products
| Method | Endpoint              | Description               |
|--------|------------------------|---------------------------|
| GET    | `/smatech_api/products`       | Retrieve all products     |
| POST   | `/smatech_api/products`       | Add a new product         |
| GET    | `/smatech_api/products/{productSku}/image` | Get Product Image |


### Orders
| Method | Endpoint              | Description               |
|--------|------------------------|---------------------------|
| GET    | `/smatech_api/orders`         | Retrieve all orders       |
| POST   | `/smatech_api/orders`         | Create a new order        |

For a complete list of endpoints and their details, refer to the **Swagger Documentation**.

---

## Swagger Documentation
The API provides comprehensive documentation through Swagger. Access it at:
```
http://localhost:9078/swagger-ui/index.html
```

---

## Deployment
To deploy the API:
1. Package it as a JAR file:
   ```bash
   mvn package
   ```
2. Run the JAR file:
   ```bash
   java -jar target/smatech-pos-api-0.0.1-SNAPSHOT.jar
   ```

This README should give assessors and collaborators clear insights into your project. You can adjust specifics like endpoints or features to match your implementation.
