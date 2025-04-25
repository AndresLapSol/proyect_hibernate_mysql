# Hibernate + MySQL Project

This project is a Java application demonstrating the use of Hibernate as an ORM tool to interact with a MySQL database.

## Features
- Connection to MySQL via Hibernate
- CRUD operations for defined entities (e.g., Hotel, Flight, Reservation)
- Hibernate configuration via XML and annotations
- Session and transaction management
- Modular project structure

## Technologies
- Java 8+
- Hibernate ORM 5.x
- MySQL Server 5.7+
- Maven
- IDE: IntelliJ IDEA / Eclipse

## Prerequisites
- JDK 8 or higher installed
- MySQL installed and running
- Maven installed
- (Optional) IDE like IntelliJ IDEA or Eclipse

## Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/AndresLapSol/proyect_hibernate_mysql.git
cd proyect_hibernate_mysql
```

### 2. Configure the database
- Create a MySQL database, e.g., `hibernate_db`.
- Update `src/main/resources/hibernate.cfg.xml` with your DB credentials:
```xml
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/hibernate_db</property>
<property name="hibernate.connection.username">your_username</property>
<property name="hibernate.connection.password">your_password</property>
```

### 3. Build the project
```bash
mvn clean install
```

### 4. Run the application
- Execute the main class: `com.example.MainApp` (or similar)
- Observe console logs for database operations

## Project Structure
```
proyect_hibernate_mysql/
├── src/
│   ├── main/
│   │   ├── java/com/example/
│   │   │   ├── entity/    # Hibernate entity classes
│   │   │   ├── dao/       # Data Access Objects
│   │   │   └── util/      # Hibernate utility classes
│   │   └── resources/
│   │       └── hibernate.cfg.xml
│   └── test/
│       └── java/com/example/ # Test cases
├── pom.xml
└── README.md
```

## Configuration
- `hibernate.cfg.xml` for Hibernate settings
- `pom.xml` for dependencies and build

## Usage
- The project currently runs simple CRUD demos in the `MainApp`.
- Extend by adding new entities, DAOs, and service layers.

## Contributing
Contributions are welcome! Please fork the repo and submit pull requests.

## License
This project is licensed under the MIT License. See `LICENSE` for details.

## Contact
Developed by Andrés LapSol.  
Email: andres@example.com
