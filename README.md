# 🍽️ Restaurant Management System

## 📌 Project Overview

The **Restaurant Management System** is a desktop-based application developed using **Core Java** with **AWT & Swings**, and connected to a **MySQL** database.  
It helps streamline restaurant operations such as table management, order placement, billing, and customer details.  
This project simulates a basic point-of-sale (POS) system tailored for restaurants, allowing staff to efficiently manage day-to-day activities.

---

## 🎯 Objectives

- To provide an easy-to-use interface for restaurant staff.
- To automate manual order and billing processes.
- To implement CRUD operations for customer and menu data.
- To integrate a database with Java for real-time data handling.

---

## 🛠️ Technologies Used

- **Programming Language**: Core Java  
- **GUI Toolkit**: AWT and Swing  
- **Database**: MySQL  
- **Database Connectivity**: JDBC  
- **IDE**: IntelliJ IDEA / Eclipse / NetBeans  
- **Version Control**: Git & GitHub  

---
## 🧪 How to Run the Project

1. **Setup MySQL Database**
   - Import `restaurant_db.sql` into your MySQL server.
   - Note the database username and password.

2. **Configure JDBC Connection**
   - In your Java code (usually in a config or utility file), update:
     ```java
     String url = "jdbc:mysql://localhost:3306/restaurant";
     String user = "root";
     String password = "your_password";
     ```

3. **Compile and Run**
   - Use any Java IDE or terminal to compile and run `Main.java`.

---

## ✨ Key Features

- 🔐 **Login System**: Simple login interface to restrict access.
- 📋 **Order Management**: Take new orders and store them in the database.
- 🍔 **Menu Management**: Add, update, or remove food items.
- 🧾 **Billing System**: Generate a final bill based on order.
- 🧑‍💼 **Customer Records**: Save and update customer details.
- 🗃️ **Database Integration**: All data stored persistently in MySQL.
- 🖼️ **User-Friendly GUI**: Built using AWT and Swing components.

---

