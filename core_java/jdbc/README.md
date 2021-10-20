# Introduction
This project includes a Java Database Connectivity (JDBC) application that connects to a Docker containerized PostgreSQL database. This application allows users to perform CRUD operations (create, read, update, delete) onto the database. The project uses the following technologies:
* Java
* JDBC
* SQL
* Maven
* Docker
* PostgreSQL

# Implementaiton
## ER Diagram
![ER Diagram](./assets/er_diagram.png)

## Design Patterns
### Data Access Object (DAO)
The Data Access Object (DAO) pattern is a design pattern isolates the application or business layer of a program from the persistence layer (a relational database). This is typically done using an abstract API. The purpose of this API is to abstract the added operations involved in performing CRUD actions to a database from an application. It permits both layers to be developed separately without knowing about each other's processes.

### Repository
The Repository pattern consists of classes or components that encapsulate the operations used to access data sources. This focuses on being able to access one table per class and performing join operations in the code rather than database. Similar to DAO patterns, it hides data access functionality, such as CRUD, providing easier maintainability. This lets you focus on the data persistence logic rather than on data access plumbing. DAO and Repository differ from each other as DAO focuses on the abstraction of data persistence, while Repository is an abstraction of a collection of objects.

# Test
The database was tested by creating tables and populating the database with sample data from SQL scripts. In order to test if these SQL statements were successful, queries were performed after any new data was added. The `JDBCExecutor` was used to run and test functionality. CRUD operations and join operations were tested by creating, updating and deleting mock data and printing to the Terminal to see if the expected matches the results.