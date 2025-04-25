# proyect_hibernate_mysql

A simple **Java 21** console application demonstrating basic **CRUD** operations on three tables—**Empresa**, **Departamento** and **Empleado**—using **Hibernate ORM** with a **MySQL** database. The user interacts via a text‐based menu to create, read, update and delete entities in the database.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Database Setup](#database-setup)
- [Running the Application](#running-the-application)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Dependencies](#dependencies)
- [Technologies](#technologies)
- [License](#license)

## Prerequisites

- **Java 21 SDK** installed and `JAVA_HOME` configured.
- **Apache Maven** (for building the project).
- **MySQL** server running locally (or reachable remotely).

## Installation

1. **Clone** the repository:
   ```bash
   git clone https://github.com/AndresLapSol/proyect_hibernate_mysql.git
2. **Navigate** into the project directory:
   ```bash
   cd proyect_hibernate_mysql

## Configuration
Edit the Hibernate configuration file at src/main/resources/hibernate.cfg.xml to adjust your database connection settings (URL, username, password, dialect, etc.). By default it uses:
