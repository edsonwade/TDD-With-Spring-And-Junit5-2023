# 🛒 MarketplaceAPI 

## Description
MarketplaceAPI is a Spring Boot-based e-commerce backend application that allows you to manage products, customers, orders, and order items.

## 🚀 Features
- **Product Management**: Easily add, update, and remove products from your catalog with detailed information, including name, price, and more.

- **Customer Management**: Manage customer data, including names, email addresses, and shipping addresses, to provide a personalized shopping experience.

- **Order Processing**: Efficiently process and track orders with support for order creation, modification, and order item management.

- **Database Integration**: Seamlessly integrate with a relational database for data storage, retrieval, and management.

- **RESTful API**: Expose a RESTful API to interact with your e-commerce platform programmatically, enabling easy integration with front-end applications.

- **Sample Data**: Get started quickly with preloaded sample data to showcase the system's capabilities.

## Table of Contents

- [🛠️ Installation](#installation)
- [📋 Prerequisites](#prerequisites)
- [⚙️ Configuration](#configurations)
- [▶️ Running the Application](#running-the-application)
- [💾 Database Setup](#database-setup)
- [📡 API Endpoints](#api-endpoints)
- [📚 API Documentation](#api-documentation)
- [💻 Technologies](#technologies)
- [👥 Contributing](#contributing)
- [📝 License](#license)

### Prerequisites

Before running MarketplaceAPI, ensure you have the following prerequisites installed on your system:

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi) (for building the application)
- Database Server (H2, MySQL,PostgresSQL) - Configured and running

### ⚙️ Configuration

1. Clone the repository:

   ```
   git clone https://github.com/edsonwade/MarketplaceAPI.git
   ```
2. Navigate to the project directory:
   ````
   cd MarketplaceAPI
   ````
3. Open the application.properties file and configure the database connection properties. Replace your-database-url, your-database-username, and your-database-password with your database details:
   ````
   spring.datasource.url=jdbc:mysql://your-database-url:3306/your-database
   spring.datasource.username={your-database-username}
   spring.datasource.password={your-database-password}
   ````
4. Save the changes to the application.properties file.

## 💾 Database Setup
MarketplaceAPI  uses Flyway Migration for database  within the Spring Boot application. 
Follow the steps below to configure and set up the database for your e-commerce platform.

1. **Database Configuration**

Ensure you have configured the database connection settings in your Spring Boot `application.properties` or `application.yml` file.

2. **Apply Migrations**

The Spring Boot application will automatically apply Flyway migrations on startup. Simply start your application, and Flyway will handle database schema creation and updates.

## ▶️ Running the Application
Use the following steps to MarketplaceAPI
1. Build the application:
 ````
 mvn clean install
 ````
2. Run the application:

  ````
  java -jar target/your-application-name.jar
  ````
The application will start, and you can access it at `http://localhost:8080`.


## 📡 API Endpoints
- `/api/products`
  - [GET] Get a list of products
  - [POST] Create a new product
  - [PUT] Update a product
  - [DELETE] Delete a product

- `/api/customers`
  - [GET] Get a list of customers
  - [POST] Create a new customer
  - [PUT] Update a customer
  - [DELETE] Delete a customer

- `/api/orders`
  - [GET] Get a list of orders
  - [POST] Create a new order
  - [PUT] Update an order
  - [DELETE] Delete an order

- `/api/order-items`
  - [GET] Get a list of order items
  - [POST] Create a new order item
  - [PUT] Update an order item
  - [DELETE] Delete an order item

## 📚 API Documentation
MarketplaceAPI  exposes a RESTful API for managing e-commerce data. You can find detailed API documentation and explore available endpoints using Swagger UI.
To access Swagger UI, go to:

``` 
http://localhost:8080/swagger-ui.html
```
## 💻 Technologies
MarketplaceAPI leverages various technologies to provide a robust e-commerce solution:

Docker 🐳: Containerization technology for seamless deployment and scalability.

Grafana and Prometheus 📊: Monitoring and metrics gathering tools for performance analysis and optimization.

Mockito 🃏: A powerful Java testing framework for unit testing and mocking dependencies.

CI/CD Pipelines 🔄: Implements Continuous Integration and Continuous Deployment to automate development processes and ensure rapid deployment.

Spring Security 🔒:Provides robust authentication and authorization features to secure your application and protect sensitive data.

## Cucumber BDD Testing with TestContainers

Cucumber BDD (Behavior-Driven Development): Utilizes Cucumber for writing executable specifications to ensure that your application behaves as expected from the user's perspective.

TestContainers 🐋: Integrates TestContainers to manage Docker containers for testing purposes. TestContainers simplifies the setup and teardown of Docker containers during integration testing, ensuring a clean and isolated environment for your tests.



## 👥 Contributing
- [Vanilson Muhongo](https://www.github.com/edsonwade)

## 📝 License
MarketplaceAPI is licensed under the [MIT License.](https://choosealicense.com/licenses/mit)

