# 🍽️ Restaurant Management System

A desktop-based Restaurant Management application built using **Java Swing (AWT & Swing GUI)** and **Oracle Database (JDBC)** for efficient restaurant order handling, billing, and payment management.

---

## 📋 Overview

This project simulates a **complete restaurant management workflow**:

- **Admin Panel** – Add or manage menu items.
- **Customer Portal** – Place orders and view available dishes.
- **Billing & Payment** – Auto-calculate bills, accept different payment methods, and generate printable receipts.

It connects Java Swing UI to an **Oracle 21c XE database** using **JDBC (ojdbc8.jar)**.

---

## 🧠 Features

### 👨‍💼 Admin
- Secure login using predefined credentials.
- Add new food items with prices for single/double/mandi packs.
- Automatic data insertion into Oracle database.

### 👨‍👩‍👧‍👦 Customer
- Simple login with name and phone number.
- View available menu items from the database.
- Place orders by selecting:
  - Item ID
  - Pack type (SC / DC / MC)
  - Quantity

### 💳 Billing & Payment
- Auto bill generation.
- Choose payment method (Cash / Card / UPI).
- Calculates total, amount paid, and change.
- Stores payment record in `PAYMENT` table.
- Printable receipt window (Ctrl + P support).

---

## 🧰 Tech Stack

| Layer | Technology |
|-------|-------------|
| **Frontend (GUI)** | Java Swing (AWT & Swing components) |
| **Backend** | Core Java (JDBC, OOP) |
| **Database** | Oracle Database 21c XE |
| **Driver** | ojdbc8.jar |
| **IDE (Recommended)** | IntelliJ IDEA / VS Code / Eclipse |
| **Language** | Java 17+ |

---

## 🗄️ Database Schema

### 🧾 Tables:

#### 1️⃣ RESTAURANTMANAGEMENT (Menu)
| Column | Type | Description |
|---------|------|-------------|
| IID | NUMBER | Item ID (Primary Key) |
| INAME | VARCHAR2(40) | Item Name |
| SC | NUMBER | Single Cost |
| DC | NUMBER | Double Cost |
| MC | NUMBER | Mandi Cost |

#### 2️⃣ CUSTOMER
| Column | Type | Description |
|---------|------|-------------|
| CNAME | VARCHAR2(40) | Customer Name |
| CPHONE | VARCHAR2(40) | Phone Number |
| CBILL | NUMBER | Bill Amount |

#### 3️⃣ PAYMENT
| Column | Type | Description |
|---------|------|-------------|
| PAY_ID | NUMBER (Auto) | Payment ID |
| CNAME | VARCHAR2(40) | Customer Name |
| CPHONE | VARCHAR2(40) | Phone Number |
| ITEM_ID | NUMBER | Ordered Item |
| PACK | VARCHAR2(3) | SC / DC / MC |
| QTY | NUMBER | Quantity |
| UNIT_PRICE | NUMBER | Price per unit |
| TOTAL | NUMBER | Total Bill |
| METHOD | VARCHAR2(20) | Payment Mode |
| PAID | NUMBER | Amount Paid |
| CHANGE | NUMBER | Balance Returned |
| STATUS | VARCHAR2(20) | PAID / PENDING |
| PAID_AT | TIMESTAMP | Auto timestamp |

