# Expense Tracker API

A RESTful backend application built with Spring Boot for managing expenses and expense categories.

This project was built as a practical backend development project to practice designing and 
implementing a Spring Boot API from requirements, rather than following a step-by-step tutorial.

---

## Features

### Expense Management

- Create an expense
- Get all expenses
- Get an expense by ID
- Update an expense
- Delete an expense
- Search expenses by keyword
- Pagination
- Sorting
- Search across expense item and category name

### Category Management

- Create a category
- Get all categories
- Get a category by ID
- Update a category
- Delete a category
- Get all expenses belonging to a category

### Validation & Error Handling

- Request validation using Jakarta Bean Validation
- Custom exceptions
- Global exception handling
- Appropriate HTTP status codes for common errors

---

## Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Jakarta Validation
- Lombok
- Maven

---

## Project Architecture

The application follows a layered architecture:

```text
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
MySQL
