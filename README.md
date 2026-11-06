# 🚆 Train Ticket Reservation System

A Java-based Train Ticket Reservation System developed using **Core Java, MySQL, and JDBC**. The application helps users search for trains, reserve tickets, manage bookings, and perform other railway reservation operations. MySQL is used to store and manage application data, while JDBC handles database connectivity.

## 📌 Introduction

The Train Ticket Reservation System is designed to simplify the process of railway ticket booking and management. It provides a convenient platform for passengers to search for available trains, select seats or berths, book tickets, and manage their reservations.

The system also provides administrative functionality to manage train details, routes, fares, and customer bookings.

## ✨ Features

### 1. Train Search
- Search trains based on departure station and destination.
- View available trains and journey details.
- Check train availability for selected travel dates.

### 2. Ticket Booking
- Book tickets for available trains.
- Select preferred travel class.
- Choose available seats or berths.
- Generate ticket details after successful booking.

### 3. Seat and Class Selection
- Select seats or berths based on availability.
- Support different travel classes.
- Calculate ticket fares according to the selected class.

### 4. Payment Management
- Calculate payment amounts based on ticket selection.
- Support payment-related booking operations.

### 5. User Authentication and Profile Management
- User registration and login.
- Manage passenger information.
- View booking history.
- Update personal details.

### 6. Admin Dashboard
- Add and manage train details.
- Manage routes and ticket fares.
- View and manage passenger bookings.
- Monitor reservation information.

### 7. Booking Reports
- Generate booking reports.
- Maintain reservation details.
- Manage cancellation-related records.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Application development |
| MySQL | Database management |
| JDBC | Java-MySQL connectivity |
| SQL | Database operations |
| IntelliJ IDEA / Eclipse | Development environment |

## 🗄️ Database

MySQL is used to store and manage the application's data.

The database handles information related to:
- Train details
- Passenger information
- Train routes
- Seat availability
- Ticket reservations
- Payment and booking records

JDBC is used to establish connectivity between the Java application and MySQL database and perform database operations.

## ⚙️ Installation and Setup

### Prerequisites

- Java JDK installed.
- MySQL Server installed.
- MySQL Connector/J JDBC driver.
- IntelliJ IDEA or Eclipse IDE.

### Step 1: Clone the Repository

```bash
git clone <repository-url>
```

### Step 2: Open the Project

Open the cloned project in IntelliJ IDEA or Eclipse.

### Step 3: Configure MySQL

1. Start your MySQL server.
2. Create the required database.
3. Execute the SQL scripts provided in the project, if available.
4. Update the database connection details in your Java configuration.

Example JDBC connection:

```java
String url = "jdbc:mysql://localhost:3306/your_database";
String username = "root";
String password = "your_password";

Connection con = DriverManager.getConnection(
    url, username, password
);
```

### Step 4: Add JDBC Driver

Add the MySQL Connector/J library to your project's dependencies.

### Step 5: Run the Application

Run the main Java class from your IDE and follow the application instructions.

## 🚀 Usage

1. Register or log in to the application.
2. Search for trains using departure and destination details.
3. Select a suitable train and travel class.
4. Check seat availability.
5. Enter passenger details.
6. Complete the booking process.
7. View and manage your reservations.

Administrators can manage train information, routes, fares, and passenger bookings.

## 📚 Concepts Demonstrated

- Core Java Programming
- Object-Oriented Programming (OOP)
- Classes and Objects
- Exception Handling
- JDBC Connectivity
- MySQL Database Operations
- SQL Queries
- CRUD Operations
- User Authentication

## 🔮 Future Enhancements

- Online payment gateway integration.
- Email and SMS booking confirmations.
- Real-time train and seat availability.
- Improved graphical user interface.
- PNR status tracking.
- Advanced booking analytics.

## 🤝 Contribution

Contributions are welcome!

1. Fork this repository.
2. Create a new branch:

```bash
git checkout -b feature-branch
```

3. Make your changes.
4. Commit your changes:

```bash
git commit -m "Add new feature"
```

5. Push your branch:

```bash
git push origin feature-branch
```

6. Create a Pull Request.

## 👩‍💻 Author

Developed as a Java and MySQL project to demonstrate database connectivity, object-oriented programming, and railway reservation management.

---

**If you find this project useful, consider giving the repository a ⭐!**
