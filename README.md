# Order Management Personal Project
## Description
This is a personal project that I recently started with the main goal of applying some of the knowledge that I'm learning.

The project is not finished, but I think that it contains some interesting things that I've learned while working in my job and in my spare time.

## What technologies I am using?

In this project I am applying the following technologies:
- **Spring Boot** 
- **Spring Data**
- **Spring Security** for JWT authentication and authorization
- **JPA**
- **JUnit for unit testing** (in the future I will add integration tests)
- **Swagger for API documentation**
- **Mapstruct**
- **H2** to create an in-memory database
- **Javadoc** for code documentation

Notes:
- **Lombok**: I quit Lombok, because I decided to switch my IDE (I went from using eclipse to IntelliJ) and it gave me some problems with the new IDE. In the future I will solve this issue.


## What you will find?

Currently, it contains two APIs with the following endpoints:

- **Customer API V1**: an API to work with Customers. It has the following endpoints:
  - **GET /customer/v1**: a custom **paginated get method for searching that uses Specifications**. It uses internally Specifications to make this endpoint more versatile. Thanks to the use of Specfications I can apply multiple filters for the different fields of the entity to search data. Thanks to it, I can avoid creating more specific endpoints for each of the filters I want to search for, making the code also more maintainable.
  - **PUT /customer/v1/batch**: a custom **endpoint that updates massively customers in a very efficient way**. It uses internally batching (defined in properties file) to improve performance of the request. Without batching it would send multiple request to the database (a request per item updated), while using batching with a single request it can update multiple customers with a single query which is much faster.
  - **POST /customer/v1**: a generic endpoint that creates a customer.
  - **PUT /customer/v1**: a generic endpoint that updates a customer.

- **Users Auth API V1**: an API used to authenticate the application. It has the following endpoints:
  - **POST /auth/v1/addNewUser**: endpoint used to create a new user and define his role. The role will be used to authenticate with the endpoints of Customer API V1.
  - **POST /auth/v1/generateToken**: endpoint used to generate a JWT Token. This token will be used to access Customer API V1 endpoints.

## How to start the project in your local computer?

To make the project work in your local computer you will need to follow this steps:

1. Git clone your project in your local computer.
2. Run the mvn **clean install** command.
3. Run the the project: **mvn spring-boot:run -D spring-boot.run.profiles=localdev**

Notes: the localdev profile contains specific configuration to run the project in your local machine.

## How can I call Customer API V1 endpoints?

The endpoints are secured, so in order to call Customer API V1 endpoints you will first need to create a user and generate a token.

Steps to follow:

1. **Create new user** using POST (auth/v1/addNewUser) endpoint.
2. **Generate a token** for this user using POST (auth/v1/generateToken) endpoint.
3. **Introduce the generated token in your request.** In swagger you can do it easily by clicking the lock that appears at the right of the screen and introducing the JWT token that was generated in step 2.

Notes: the current available roles you can use when adding a new user are: ROLE_ADMIN and ROLE_USER