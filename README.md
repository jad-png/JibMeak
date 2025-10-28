# JibMeak - Logistics Management System 🚚

## Project Overview

JibMeak is a **Spring Boot-based logistics management system** designed for taxi transport companies. It efficiently handles delivery management, route optimization, and resource allocation, including vehicles, warehouses, and deliveries.

The system implements intelligent **tour optimization** using the **Nearest Neighbor algorithm** to create efficient delivery routes while respecting vehicle constraints and delivery time windows.

---

## ✨ Features

* **Warehouse Management**
    * CRUD operations for warehouses.
    * Location tracking with coordinates.
    * Operating hours management.
* **Vehicle Fleet Management**
    * Support for multiple vehicle types (**BIKE**, **VAN**, **TRUCK**).
    * Capacity constraints (weight, volume, delivery count).
    * Vehicle allocation for tours.
* **Delivery Management**
    * Creation and tracking of deliveries.
    * Time window preferences.
    * Status tracking (**PENDING**, **IN\_TRANSIT**, **DELIVERED**, **FAILED**).
* **Tour Optimization**
    * Automated route planning using the **Nearest Neighbor algorithm**.
    * Vehicle capacity constraints validation.
    * Distance calculation and optimization.
    * Multi-stop delivery planning.

---

## 💻 Tech Stack

| Category | Technology | Version / Notes |
| :--- | :--- | :--- |
| **Backend** | Java | 17 |
| **Framework** | Spring Boot | 3.5.6 |
| **Persistence** | Spring Data JPA/Hibernate | |
| **Database** | H2 Database | Embedded |
| **Mapping** | MapStruct | 1.5.5 |
| **Utilities** | Lombok | Boilerplate reduction |
| **Testing** | JUnit 5, Mockito | |
| **Documentation** | SpringDoc OpenAPI | Swagger UI |
| **Build Tool** | Maven | |

---

## 📂 Project Structure

```shell
src/main/java/com/taxist/JibMeak/ 

├── controller/ # REST API endpoints 
├── service/
│ ├── interfaces/ # Service definitions 
│ └── Impl/ # Service implementations
├── repository/ # JPA repositories 
├── model/ # Entity classes 
├── dto/ # Data Transfer Objects
├── mapper/ # MapStruct mappers 
├── algo/ # Optimization algorithms (NearestNeighborOptimizer) 
└── utils/ # Utility classes (DistanceCalculator, VehicleConstraints)
```

---

## 🛠️ Installation Guide

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/yourusername/JibMeak.git](https://github.com/yourusername/JibMeak.git)
    cd JibMeak
    ```

2.  **Configure:**
    * Ensure you have Java 17 installed.
    * The project is configured to use an in-memory **H2 Database** by default. No external database setup is needed.

3.  **Build and Run the project:**
    ```bash
    ./mvnw spring-boot:run
    ```

### Access Points

The application will be running on `http://localhost:8080`.

| Feature | URL |
| :--- | :--- |
| **Main Application** | `http://localhost:8080` |
| **H2 Console** | `http://localhost:8080/h2-console` |
| **Swagger UI (API Docs)** | `http://localhost:8080/swagger-ui.html` |

---

## 🗺️ API Endpoints

| Controller | Base Path | Endpoints |
| :--- | :--- | :--- |
| **Warehouse** | `/api/warehouses` | `GET /`, `GET /{id}`, `POST /`, `PUT /{id}`, `DELETE /{id}` |
| **Vehicle** | `/api/vehicles` | `GET /`, `GET /{id}`, `POST /`, `PUT /{id}`, `DELETE /{id}` |
| **Delivery** | `/api/deliveries` | `GET /`, `GET /{id}`, `POST /`, `DELETE /{id}` |
| **Tour** | `/api/tours` | `GET /`, `GET /{id}`, `POST /`, **`POST /optimize`**, `DELETE /{id}` |

---

## ✅ Testing

The project includes comprehensive unit tests using **JUnit 5** and **Mockito** for the service layer, controllers, and optimization logic.

Run all tests using the Maven wrapper:

```bash
./mvnw test
```


## Key Dependencies 

The following key dependencies power the applicatoin : 

```XML

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
    
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
            <version>1.5.5.Final</version>
        </dependency>
    
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
    
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.2.0</version>
        </dependency>
    
    </dependencies>
```
