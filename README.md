# Back-End API - sales website

## Introduction

This README provides an overview and documentation for the Back-End (BE) of an API created for a company's e-commerce website. The API is designed to manage and handle various entities such as employees, products, customers, orders, and related details.

## Table of Contents

-   [Back-End API - sales website](#back-end-api---sales-website)
    -   [Introduction](#introduction)
    -   [Table of Contents](#table-of-contents)
    -   [Entity Information](#entity-information)
    -   [API Endpoints](#api-endpoints)
        -   [CRUD Operations](#crud-operations)
        -   [Order Statistics](#order-statistics)
    -   [Technologies Used](#technologies-used)

## Entity Information

The API manages the following entities and their respective attributes:

-   **Employee** (emp_id, full_name, dob, email, phone, address, status)
-   **Product** (product_id, name, description, unit, manufacturer_name, status)
-   **Customer** (cust_id, cust_name, email, phone, address)
-   **Product Image** (product_id, image_id, path, alternative)
-   **Orders** (order_id, order_date, emp_id, cust_id)
-   **Order Detail** (order_id, product_id, quantity, price, note)
-   **Product Price** (product_id, price_date_time, price, note)

For detailed information on these entities, refer to the initial request.

## API Endpoints

### CRUD Operations

The API provides endpoints for basic CRUD operations on the mentioned entities using Jakarta EE and JPA (Java Persistence API).

-   **Employee**

    -   Read: `GET /api/employees`
    -   Read by employee id: `GET /api/employees/{id}`
    -   Create: `POST /api/employees`
    -   Update: `PUT /api/employees`
    -   Delete: `DELETE /api/employees/{id}`

-   **Product**

    -   Read: `GET /api/products`
    -   Read by product id: `GET /api/products/{id}`
    -   Read images by product id: `GET /api/products/{id}/images`
    -   Create: `POST /api/products`
    -   Update: `PUT /api/products`
    -   Delete: `DELETE /api/products/{id}`

-   **Customer**

    -   Read: `GET /api/customers`
    -   Read by customer id: `GET /api/customers/{id}`
    -   Create: `POST /api/customers`
    -   Update: `PUT /api/customers`
    -   ~~Delete: `DELETE /api/customers/{id}`~~

-   **Product Image**

    -   Read: `GET /api/product-images`
    -   Read by product image id: `GET /api/product-images/{id}`
    -   Create: `POST /api/product-images`
    -   Update: `PUT /api/product-images`
    -   ~~Delete: `DELETE /api/product-images/{product_id}/{image_id}`~~

-   **Order**

    -   Read: `GET /api/orders`
    -   Read by order id: `GET /api/orders/{id}`
    -   Create: `POST /api/orders`
    -   Update: `PUT /api/orders/{id}`
    -   ~~Delete: `DELETE /api/orders/{id}`~~

-   **Order Detail**

    ~~- Create: `POST /api/order-details`~~
    ~~- Read: `GET /api/order-details/{order_id}/{product_id}`~~
    ~~- Update: `PUT /api/order-details/{order_id}/{product_id}`~~
    ~~- Delete: `DELETE /api/order-details/{order_id}/{product_id}`~~

-   **Product Price**

    -   Read: `GET /api/product-prices`
    -   Read new price of product: `GET /api/product-prices/{id}`
    -   Create: `POST /api/product-prices`
    -   Update: `PUT /api/product-prices`
    -   ~~Delete: `DELETE /api/product-prices/{product_id}/{price_date_time}`~~

### Order Statistics

The API provides endpoints for generating order statistics:

-   **Statistics by Date**
    -   ~~Get order statistics for a specific date: `GET /api/statistics/by-date/{date}`~~
-   **Statistics by Time Range**
    -   ~~Get order statistics for a specific time range: `GET /api/statistics/by-time-range/{start_date}/{end_date}`~~
-   **Statistics by Employee in Time Range**
    -   ~~Get order statistics by employee for a specific time range: `GET /api/statistics/by-employee/{emp_id}/{start_date}/{end_date}`~~

## Technologies Used

The Back-End API is developed using the following technologies:

-   Jakarta EE: A set of specifications that extend the Java SE platform for enterprise applications.
-   JPA (Java Persistence API): A standard for accessing and managing relational databases in Java applications.
-   Other relevant libraries and frameworks as needed for the specific implementation.

For more detailed implementation and usage, refer to the source code and relevant documentation.

Feel free to reach out for further assistance or clarifications regarding the API implementation.
