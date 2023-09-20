# MarketplaceAPI 

## Description
MarketplaceAPI is a Spring Boot-based e-commerce backend application that allows you to manage products, customers, orders, and order items.

## Features
- Product Management: Easily add, update, and remove products from your catalog with detailed information, including name, price, and more.

- Customer Management: Manage customer data, including names, email addresses, and shipping addresses, to provide a personalized shopping experience.

- Order Processing: Efficiently process and track orders with support for order creation, modification, and order item management.

- Database Integration: Seamlessly integrate with a relational database for data storage, retrieval, and management.

- RESTful API: Expose a RESTful API to interact with your e-commerce platform programmatically, enabling easy integration with front-end applications.

- Sample Data: Get started quickly with preloaded sample data to showcase the system's capabilities.

## Table of Contents

- [Installation](#installation)
- [Prerequisites](#prerequisites)
- [Database Setup](#database-setup)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

### Prerequisites

Before running [Your Application Name], ensure you have the following prerequisites installed on your system:

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi) (for building the application)
- [Database Server](e.g., H2, MySQL,PostgreSQL) - Configured and running

## Database Setup
MarketplaceAPI  uses Flyway Migration for database  within the Spring Boot application. 
Follow the steps below to configure and set up the database for your e-commerce platform.

1. **Database Configuration**

   Ensure you have configured the database connection settings in your Spring Boot `application.properties` or `application.yml` file.

2. **Apply Migrations**

   The Spring Boot application will automatically apply Flyway migrations on startup. Simply start your application, and Flyway will handle database schema creation and updates.


## API Endpoints
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

## Contributing
- [Vanilson Muhongo](https://www.github.com/edsonwade)