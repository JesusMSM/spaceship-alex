# 🚀 Spaceship

**Spaceship** is a backend-focused project built with **Spring Boot**. The project demonstrates CRUD operations, database integration, RESTful API creation, and robust business logic. It serves as a solid example of backend development practices.

This **project** demonstrates functionalities such as calculating the total weight of items, and when the weight exceeds the set limit, prioritizing items according to their category until the weight meets the limit. These **features** ensure that the ***spacecraft*** *fulfills the minimum requirements for launch readiness and mission success*.

---

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot**
- **Maven**
- **PostgreSQL**
- **JUnit 5**
- **Mockito**
- **Spring Boot Test**
- **Docker**
- **VS Code**

---

## 📋 Features

- Backend setup using **Spring Boot**.
- Entity and relationship management for `Item`, `Category` and `Calculator`. 
- RESTful APIs for CRUD (Create, Read, Update, Delete) operations.
- Weight calculation logic with prioritisation according to item and its category when exceeding the limits.
- Integration with **PostgreSQL** for database management.
- Dockerized application for easy deployment.
- Unit testing with **JUnit**, **Mockito**, and **Spring Boot Test**.
- Scalable and modular project structure for future enhancements.

---

## ⚙️ Project Setup

### 1. Clone the Repository 📂

Run the following commands to clone the repository and navigate to the project directory:

```
git clone https://github.com/JesusMSM/spaceship-alex.git
cd spaceship-alex
```

### 2. Configure the Database 🗄️

* Ensure PostgreSQL is installed and running. Then, configure the database as follows:

1. Create a database named `spaceship_db`.
2. Update the credentials in the `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/spaceship_db
spring.datasource.username=alext
spring.datasource.password=**********
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### 3. Run the project ▶️

#### Locally
1. Open the project in VS Code or your preferred IDE.
2. Start the application using the following command:
```
mvn spring-boot:run
```

#### With Docker 🐳
1. Build the Docker image:
```
docker build -t spaceship-backend .
```
2. Run the Docker container:
```
docker run -p 8080:8080 --name spaceship-container spaceship-backend
```
***Note***: *Make sure Docker is installed and running before executing these commands*.

### 4. Project Structure 🗂️

```
src/
├── main/
│   ├── java/com/example/spaceship/
│   │   ├── entity/
│   │   │   ├── Calculator.java            # Entity class representing the Calculator table
│   │   │   ├── Category.java              # Entity class representing the Category table
│   │   │   └── Item.java                  # Entity class representing the Item table
│   │   ├── controller/
│   │   │   ├── CalculatorController.java  # REST controller for weight calculations
│   │   │   ├── CategoryController.java    # REST controller handling API endpoints for Category operations
│   │   │   └── ItemController.java        # REST controller handling API endpoints for Item operations
│   │   ├── repository/
│   │   │   ├── CalculatorRepository.java  # JPA repository interface for Calculator operations
│   │   │   ├── CategoryRepository.java    # JPA repository interface for Category operations
│   │   │   └── ItemRepository.java        # JPA repository interface for Item operations
│   │   ├── service/
│   │   │   ├── CalculatorService.java     # Service class containing weight calculation logic
│   │   │   ├── CategoryService.java       # Service class containing Category business logic
│   │   │   └── ItemService.java           # Service class containing Item business logic
│   │   └── SpaceshipApplication.java      # Main class to bootstrap the Spring Boot application
│   └── resources/
│       └── application.properties         # Database and application configuration
└── test/
    ├── java/com/example/spaceship/
    │   ├── service/
    │   │   └── CalculatorServiceTest.java   # Unit tests for the CalculatorService logic
    │   └── SpaceshipApplicationTest.java    # Unit tests for the application startup

```

### 5. Project Steps 🛠️

Below is the **To-Do List** of tasks completed during the development of this project:

1. **Define Entities**
   - [x] Create the `Item`, `Category`, and `Calculator` classes to represent the database tables.

2. **Setup Repository**
   - [x] Build repository interfaces for database operations for each entity.

3. **Implement Service Layer**
   - [x] Encapsulate the business logic within the service classes.

4. **Design API Endpoints**
   - [x] Develop RESTful endpoints in their respective controllers.

5. **Integrate Database**
   - [x] Configure the `application.properties` file for PostgreSQL connectivity.

6. **Docker Deployment**
   - [x] Create a Dockerfile to containerize the application.
   - [x] Build the Docker image.
   - [x] Run the Docker container for deployment.

7. **Unit Testing**
   - [x] Develop comprehensive unit tests for the `CalculatorService` class using **JUnit**, **Mockito**, and **Spring Boot**:

#### 🧪 7.1 Unit Tests for `CalculatorService`

1. **Test to Verify That the Total Weight is Correct (Within the Weight Limit)**
   - Ensures the total weight calculated does not exceed the defined weight limit.

2. **Test to Verify That the Total Weight Exceeds the Weight Limit**
   - Confirms that the method correctly identifies when the total weight exceeds the allowed weight limit.

3. **Test When the Item is Not Found in the Repository**
   - Verifies that the service handles cases where an item is not found in the repository gracefully by throwing the appropriate exception.

4. **Test to Verify That Items with Lower Priority are Removed if Weight Exceeds the Limit**
   - Ensures that items with the lowest priority are correctly identified and removed to meet the weight limit while retaining high-priority items.

**Testing Tools and Frameworks**:
- **JUnit 5**: For defining and running unit tests.
- **Mockito**: For mocking dependencies such as the `ItemRepository`.
- **Spring Boot Test**: For integration with Spring Boot’s application context during tests.

---

### 6. Contributions 🙌

* Contributions are welcome! If you have ideas or improvements for this project, feel free to open an issue or submit a pull request.

---

### 7. Contact me 📧

* **Author**: Alexandro Tellez
* **Email**: alextellezyanes@gmail.com
* **LinkedIn**: [Alexandro Tellez](https://www.linkedin.com/in/alex-tellez-y/)

This project was developed by Alexandro Tellez with code review provided by [**Jesús MSM**](https://github.com/JesusMSM).

---


