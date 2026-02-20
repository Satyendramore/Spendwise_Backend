# 🚀 SpendWise - Backend

SpendWise is a  expense management application designed to help users track, manage, and analyze their personal spending.

This repository contains the **Spring Boot backend** that powers authentication, expense management, and analytics.

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- MySQL
- JPA / Hibernate
- Maven

---

## 🔐 Features

### 🔑 Authentication & Authorization
- User Signup
- User Login
- JWT-based authentication
- Role-based access control
- Protected APIs using Spring Security
- 403 handling for unauthorized access

### 💰 Expense Management (CRUD)
- Add new expense
- Update existing expense
- Delete expense
- Get all expenses for authenticated user
- Category-based expenses
- User-specific data isolation

### 📊 Analytics
- Monthly expense aggregation (year-wise)
- Yearly expense aggregation
- Optimized queries using JPQL
- Secure analytics endpoints (role protected)

---

## 📡 API Endpoints

### 🔐 Auth APIs

| Method | Endpoint | Description |
|--------|----------|------------|
| POST   | /public/signup | Register new user |
| POST   | /public/login  | Authenticate user & return JWT |

---

### 💰 Expense APIs (Protected)

| Method | Endpoint | Description |
|--------|----------|------------|
| POST   | /expense | Create expense |
| GET    | /expense | Get all expenses of logged-in user |
| PUT    | /expense/{id} | Update expense |
| DELETE | /expense/{id} | Delete expense |

---

### 📊 Analytics APIs (Protected)

| Method | Endpoint | Description |
|--------|----------|------------|
| GET | /expense/analytics/year?year=YYYY | Monthly expense summary for given year |
| GET | /expense/analytics/years | Year-wise expense summary |

---

## 🔐 Security Flow

1. User logs in.
2. Backend validates credentials.
3. JWT token is generated and returned.
4. Frontend stores token.
5. Every protected request includes:
