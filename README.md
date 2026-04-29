# LSU Scheduler — Tiger Academic Planner

LSU Scheduler is a web-based academic planning tool built for LSU Computer Science students. It allows students to check course eligibility, view sections, detect schedule conflicts, track degree progress, and manage their academic schedule — all in one place.

---

## Technologies Used

- **Java 17** (Temurin JDK)
- **Spring Boot 3.5**
- **Spring Data JPA / Hibernate**
- **Thymeleaf**
- **MySQL 9.6**
- **HTML5 / CSS3 / JavaScript**
- **Git + GitHub**

---

## Prerequisites

Make sure you have the following installed before running the project:

- [Java 17 (Temurin JDK)](https://adoptium.net/)
- [MySQL 9.6](https://dev.mysql.com/downloads/)
- [VS Code](https://code.visualstudio.com/) with the following extensions:
  - Extension Pack for Java
  - Spring Boot Extension Pack
- [Git](https://git-scm.com/)

---

## Step 1: Install MySQL (if not already installed)

### On Mac (using Homebrew)

If you don't have Homebrew installed, first run:
```
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

Then install MySQL:
```
brew install mysql
```

Start MySQL:
```
brew services start mysql
```

Secure your MySQL installation and set a root password:
```
mysql_secure_installation
```

### On Windows

1. Go to [https://dev.mysql.com/downloads/installer/](https://dev.mysql.com/downloads/installer/)
2. Download **MySQL Installer for Windows**
3. Run the installer and select **MySQL Server** and **MySQL Workbench**
4. During setup, set a root password, remember this password, you will need it later
5. Complete the installation

### Verify MySQL is running

Open a terminal and run:

```
mysql -u root -p
```

Enter your password. If you see the `mysql>` prompt, MySQL is running correctly. Type `exit` to quit.

---

## Step 2: Get the Project

### Option A — Clone from GitHub
```
git clone https://github.com/Minh240103/CSC-3380.git
```

### Option B — Open from zip file
Extract the zip file and open the extracted folder in VS Code.

---

## Step 3: Set Up the Database

### 3a. Create the database

Open MySQL in your terminal:

```
mysql -u root -p
```

Enter your MySQL password, then run:

```
CREATE DATABASE lsu_scheduler;
exit
```

### 3b. Import the database dump

**Type this in your terminal:**
```
mysql -u root -p lsu_scheduler < lsu_scheduler_dump.sql
```

Enter your MySQL password when prompted.

---

## Step 4: Configure Application Properties

In VS Code, Go to:
```
src → main → resources → application.properties
```

Open it and replace `YOUR_PASSWORD_HERE` with your actual MySQL root password:

```
# Change the password below to your MySQL root password
spring.datasource.url=jdbc:mysql://localhost:3306/lsu_scheduler
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD_HERE
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

spring.thymeleaf.cache=false
server.port=8080
```

Save the file with **Ctrl + S** (Windows) or **Command + S** (Mac).

---

## Step 5: Run the Application

### Using VS Code Spring Boot Dashboard

1. Open VS Code
2. Look for the **Spring Boot Dashboard** in the left sidebar
3. Click the **Play** button next to `lsu-scheduler apps`

---

## Step 6: Open the App

Open your browser and go to:

```
http://localhost:8080
```

You will be redirected to the login page.

---

## Project Structure

```
src/
  main/
    java/com/tigers/lsuscheduler/
      controller/     — HTTP request handlers (9 controllers)
      model/          — JPA entity classes (Student, Course, Section, Enrollment, DegreePlan)
      repository/     — Spring Data JPA repositories
      service/        — Business logic (EligibilityService, ConflictService)
    resources/
      templates/      — Thymeleaf HTML templates
      static/css/     — Stylesheet (main.css)
      static/js/      — JavaScript (main.js)
```

---

## Database Tables

| Table | Description |
|-------|-------------|
| `students` | Student accounts |
| `courses` | All CS courses |
| `sections` | Course sections with time and location |
| `enrollments` | Student course enrollments |
| `degree_plan` | 8-semester plan per concentration |
| `course_prerequisites` | Prerequisite relationships between courses |
| `student_completed_courses` | Courses marked as completed by each student |