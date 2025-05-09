Inspection Management System

A simple web-based application for managing inspection workflows. The system is built using Java Servlets, JSP, and MySQL, and it allows inspectors or administrators to create, view, update, and manage inspection records efficiently.

Features

* Create new inspection records
* View all inspections
* Edit existing inspection data
* Delete inspections
* MySQL database integration for persistent storage

Technologies Used

* Backend: Java Servlets, JDBC
* Frontend: JSP, HTML, CSS
* Database: MySQL
* Build Tool: Apache Ant (with optional NetBeans support)
* Server: Apache Tomcat

Setup Instructions

Prerequisites

* Java JDK 8 or higher
* Apache Ant
* MySQL
* Apache Tomcat
* (Optional) NetBeans IDE

Database Setup

Create a MySQL database named inspectiondb.

Then run the following SQL to create the required table:

```sql
CREATE TABLE inspections (
    id INT AUTO_INCREMENT PRIMARY KEY,
    inspector_name VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL,
    inspection_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL
);
```

Update the connection details in the DB connection file (likely found in src/db/DBConnector.java):

* URL
* USER
* PASSWORD

### 🧱 Build and Deploy

Using Ant:

```bash
ant clean
ant build
```

This will generate the compiled files into the dist/ directory.

Deploy the generated WAR file (if applicable) or the project folder to Apache Tomcat.

Access the Application

Open your browser and go to:

[http://localhost:8080/InspectionSystem](http://localhost:8080/InspectionSystem)

(Replace InspectionSystem with the actual context path if it's different.)

Project Structure

* src/controller: Servlets to handle HTTP requests
* src/dao: Data access logic for inspections
* src/dto or model: Entity classes for transferring data
* src/db: Utility class for establishing database connections
* web/: JSPs, HTML, CSS, and other UI resources
* build.xml: Apache Ant build script
* dist/: Generated build output
Future Improvements

* Add input validation and error handling
* Implement user authentication and session management
* Add search and filtering capabilities
* Improve UI with JavaScript and responsive design

