# Contact Management API

This project is a Contact Management system built with Spring Boot. It provides APIs for user management, contact management, and address management. The system includes features such as user registration, login, and CRUD operations for contacts and their associated addresses.

## Table of Contents

- [Features](#features)
- [API Documentation](#api-documentation)
  - [User API](#user-api)
  - [Contact API](#contact-api)
  - [Address API](#address-api)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Usage](#usage)

## Features

- User registration and authentication
- CRUD operations for managing contacts
- CRUD operations for managing addresses associated with contacts
- Token-based authentication for securing APIs

## API Documentation

### User API

#### Register User

Endpoint : POST /api/users

Request Body :

```json
{
  "username" : "anton",
  "password" : "rahasia",
  "name" : "Anton Roycar Nababan"
}
```

Response Body (Success) :
```json
{
  "data" : "OK"
}
```

Response Body (Failed) :
```json
{
  "errors" : "Username can't black, ???"
}
```

#### Login User
Endpoint : POST /api/auth/login

Request Body :

```json
{
  "username" : "anton",
  "password" : "rahasia"
}
```

Response Body (Success) :
```json
{
  "data" : {
    "token" : "TOKEN",
    "expiredAt" : 1234567890 //millisecond
  }
}
```

Response Body (Failed) :
```json
{
  "errors" : "Username or password wrong, ???"
}
```

#### Get User

Endpoint : GET /api/users/current

Request Header :
- X -API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data" : {
    "username" : "anton",
    "name" : "Anton Roycar Nababan"
  }
}
```

Response Body (Failed, 401) :
```json
{
  "errors" : "Unauthorized"
}
```

#### Update User


Endpoint : PATCH /api/users/current

Request Header :
- X -API-TOKEN : Token (Mandatory)

Request Body :
```json
{
  "name" : "Anton Roycar Nababan", //put if only want to update name
  "password" : "new password" //put if only want to update password
}
```


Response Body (Success) :
```json
{
  "data" : {
    "username" : "anton",
    "name" : "Anton Roycar Nababan"
  }
}
```

Response Body (Failed, 401) :
```json
{
  "errors" : "Unauthorized"
}
```

#### Logout User


Endpoint : DELETE /api/users/logout

Request Header :
- X -API-TOKEN : Token (Mandatory)
  Response Body (Success) :

```json
{
  "data" : "OK"
}
```


### Contact API

#### Create Contact

Enpoint : POST /api/contacts

Request Header :
- X -API-TOKEN : Token (Mandatory)

Request Body :
```json
{
  "firstName" : "Anton Roycar",
  "lastName"  : "Nababan",
  "email" : "anton@example.com",
  "phone" : "089988776611"
}
```

Response Body (Success) :
```json
{
  "data" : {
    "id" : "random-string",
    "firstName" : "Anton Roycar",
    "lastName"  : "Nababan",
    "email" : "anton@example.com",
    "phone" : "089988776611"
  }
}
```

Response Body (Failed) :
```json
{
  "errors" : "Email format invalid, phone format invalid, ..."
}
```

#### Update Contact

Enpoint : PUT /api/contacts/{idContact}

Request Header :
- X -API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "firstName" : "Anton Roycar",
  "lastName"  : "Nababan",
  "email" : "anton@example.com",
  "phone" : "089988776611"
}
```

Response Body (Success) :
```json
{
  "id" : "random-string",
  "firstName" : "Anton Roycar",
  "lastName"  : "Nababan",
  "email" : "anton@example.com",
  "phone" : "089988776611"
}
```

Response Body (Failed) :
```json
{
  "errors" : "Email format invalid, phone format invalid, ..."
}
```

#### Get Contact

Enpoint : GET /api/contacts/{idContact}

Request Header :
- X -API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "id" : "random-string",
  "firstName" : "Anton Roycar",
  "lastName"  : "Nababan",
  "email" : "anton@example.com",
  "phone" : "089988776611"
}
```

Response Body (Failed, 404) :
```json
{
  "errors" : "Contact is not found"
}
```

#### Search Contact

Enpoint : GET /api/contacts

Query Param :
- name : String, contact first name or last name, using like query, optional
- phone : String, contact phone, using like query, optional
- email : String, contact email, using like query, optional
- page : Integer, start from 0, default 0
- size : Integer, default 10

Request Header :
- X -API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data" : [
    {
    "id" : "random-string",
    "firstName" : "Anton Roycar",
    "lastName"  : "Nababan",
    "email" : "anton@example.com",
    "phone" : "089988776611"
    }
  ],
  "paging" : {
    "currentPage" : 0,
    "totalPage" : 10,
    "size" : 10
  }
}
```

Response Body (Failed) :
```json
{
  "errors" : "Unauthorized"
}
```
#### Remove Contact

Enpoint : DELETE /api/contacts/{idContact}

Request Header :
- X -API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data" : "OK"
}
```

Response Body (Failed) :
```json
{
  "errors" : "Contact is not found"
}
```

### Address API

#### Create Address
Endpoint : POST /api/contacts/{idContact}/addresses

Request Header : 
- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "street": "Jalan apa",
  "city": "kota",
  "province": "provinsi",
  "country": "negara",
  "postalCode": "123456"
}
```

Response Body (Success) :
```json
{
  "data" : {
    "id" : "random-string",
    "street": "Jalan apa",
    "city": "kota",
    "province": "provinsi",
    "country": "negara",
    "postalCode": "123456"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "contact is not found" 
}
```

#### Update Address
Endpoint : PUT /api/contacts/{idContacts}/addresses/{idAddress}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "street": "Jalan apa",
  "city": "kota",
  "province": "provinsi",
  "country": "negara",
  "postalCode": "123456"
}
```

Response Body (Success) :
```json
{
  "data" : {
    "id" : "random-string",
    "street": "Jalan apa",
    "city": "kota",
    "province": "provinsi",
    "country": "negara",
    "postalCode": "123456"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Address is not found" 
}
```

#### Get Address
Endpoint : GET /api/contacts/{idContact}/addresses/{idAddress}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data" : {
    "id" : "random-string",
    "street": "Jalan apa",
    "city": "kota",
    "province": "provinsi",
    "country": "negara",
    "postalCode": "123456"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "contact is not found" 
}
```

#### Remove Address
Endpoint : DELETE /api/contacts/{idContact}/addresses/{idAddress}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data" : "OK"
}
```

Response Body (Failed) :

```json
{
  "errors" : "contact is not found" 
}
```

#### List Address
Endpoint : GET /api/contact/{idContact}/addresses

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data" : [
    {
      "id" : "random-string",
      "street": "Jalan apa",
      "city": "kota",
      "province": "provinsi",
      "country": "negara",
      "postalCode": "123456"
    }
  ]
}
```

Response Body (Failed) :

```json
{
  "errors" : "contact is not found" 
}
```

## Getting Started

These instructions will help you set up and run the Contact Management API on your local machine for development and testing purposes.

### Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 8 or higher installed
- Apache Maven installed
- A MySQL database or any other supported database for storing data
- An IDE such as IntelliJ IDEA or Eclipse (optional, but recommended)

### Installation

1. Clone the repository from GitHub:

   ```bash
   git clone https://github.com/antonroycar/spring-contactmanagement.git
   ```
   
2. Navigate to the project directory:
   
   ```bash
   cd spring-contactmanagement

   ```
   
3. Set up the database by creating a new schema in MySQL and updating the `application.properties` file with your database credentials:
   
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/spring-contactmanagement
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

4. Build the project using Maven:

    ```bash
   mvn clean install
   ```

### Running the Application

1. Run the application using Maven:
   
    ```bash
   mvn spring-boot:run
   ```

2. The API will be accessible at `http://localhost:8080`.

## Usage
Once the application is running, you can use tools like [Postman](https://www.postman.com/) or [cURL](https://curl.se/) to interact with the API. Refer to the [API Documentation](#api-documentation) section for details on available endpoints and their usage.
