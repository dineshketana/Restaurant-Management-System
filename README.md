# 🍽️ Restaurant Management System

## 📌 Project Overview

The **Restaurant Management System** is a desktop-based application developed using **Core Java** with **AWT & Swings**, and connected to a **Oracle SQL** database.  
It helps streamline restaurant operations such as table management, order placement, billing, and customer details.  
This project simulates a basic point-of-sale (POS) system tailored for restaurants, allowing staff to efficiently manage day-to-day activities.

---

## 🎯 Objectives

- To provide an easy-to-use interface for restaurant staff.
- To automate manual order and billing processes.
- To implement CRUD operations for customer and menu data.
- Integrate Java with an OracleSQL database for reliable, real-time data management.

---

## 🛠️ Technologies Used

- **Programming Language**: Core Java  
- **GUI Toolkit**: AWT and Swing  
- **Database**: Oracle SQL  
- **Database Connectivity**: JDBC (with Oracle JDBC Driver)  
- **IDE**: IntelliJ IDEA / Eclipse / NetBeans  
- **Version Control**: Git & GitHub  

---

## 🧪 How to Run the Project

### Step 1: Set up the OracleSQL Database

1. Install and set up **Oracle Database Express Edition (XE)** or another Oracle Database version.
2. Execute the `restaurant_db.sql` script located in the `db/` directory to create the necessary schema and tables.
3. Note the database connection details such as username, password, and connection URL.

### Step 2: Configure JDBC Connection

- In your Java code (typically in a configuration or database utility file), update the Oracle JDBC connection details:
  ```java
  String url = "jdbc:oracle:thin:@localhost:1521:XE"; // Adjust based on your Oracle setup
  String user = "your_username";
  String password = "your_password";

Step 3: Compile and Run
Open the project in your preferred Java IDE.

Compile the source code.

Run Main.java to launch the application.
---

## ✨ Key Features

- 🔐 **Login System**: Secure login interface to restrict access.
- 📋 **Order Management**: Efficient order entry and storage in the database.
- 🍔 **Menu Management**: Capability to add, update, or remove food items.
- 🧾 **Billing System**: Generation of final bills based on orders.
- 🧑‍💼 **Customer Records**: Management of customer details and history.
- 🗃️ **Database Integration**: All data persistently stored in an OracleSQL database.
- 🖼️ **User-Friendly GUI**: Intuitive interface built with AWT and Swing.

---
