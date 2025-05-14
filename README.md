# 🧩 Escape Room Virtual - Java Project

Welcome to the official repository of the Escape Room Virtual project! This application simulates a virtual escape room experience with customizable rooms, clues, and decorative items.

## 👥 Team

Developed collaboratively by:

* [@MaraMarchello](https://github.com/MaraMarchello)
* [@Pabs-Git](https://github.com/Pabs-Git)
* [@acocinas](https://github.com/acocinas)

## 🛠️ Technologies Used

* Java 21
* Maven
* MySQL & Workbench
* IntelliJ IDEA
* JUnit 5 for Testing

---

## 🎯 Project Goals

This project is part of a collaborative team exercise to practice:

* Git & GitHub collaboration using **Git Flow**
* UML and Database design
* Java design patterns (Abstract Factory, DAO, Observer)
* Agile task management via **GitHub Kanban Board**
* Writing and running **unit/integration tests**

---

## 🚪 Escape Room Features

### Core Entities:

* **Room**: Associated with a difficulty level and a price
* **Clue**: Thematic hints tied to puzzles and rooms
* **DecorationItem**: Objects that create immersive atmospheres using different materials
* **Player**: Interacts with rooms, can receive certificates and purchase tickets
* **Certificate**: Generated for players upon successful room completion
* **TicketSale**: Tracks room sales and revenue

### Main Functionalities:

* 🧱 Create new Escape Room setups with difficulty-based factory pattern
* ➕ Add rooms, clues, and decorations
* 📦 View inventory by difficulty, theme, and material
* 💶 Calculate total inventory value in euros
* ❌ Remove rooms, clues, and items from inventory
* 🎟️ Sell tickets and track player-room interactions
* 📈 Calculate total revenue
* 🧾 Issue certificates of completion to players
* 🔔 Receive notifications for escape room events

---

## 🧪 Testing

Test coverage includes:

* DAO operations for persistence (integration tests)
* Domain logic (certificate assignment, inventory calculations, etc.)

Tests are located under:

```
src/test/java/com
```

---

## 🗂️ Project Structure

```bash
src
├── main
│   ├── java/com/...        # Main Java codebase
│   └── resources           # SQL schema, data seeding, properties
└── test
    └── java/com/...        # JUnit tests
```

---

## 📚 Database Setup

* `Schema_escape_room.sql`: defines the database schema
* `init.sql`: loads initial data
* `database.properties`: contains database connection info

Ensure MySQL is running and the `escape_room` database is created before running the app.

---

## 🚀 Getting Started

1. Clone the repo:

```bash
git clone https://github.com/S303DevelopersTeam/escape-room-virtual.git
```

2. Configure `src/main/resources/database.properties` with your DB credentials.

3. Run the app via `EscapeRoomApplication.java`

---

## 🧠 Design Patterns Applied

* **Abstract Factory**: Room creation by difficulty
* **DAO Pattern**: Decoupled persistence logic
* **Observer Pattern**: Event notification system

---

## 📅 Agile Workflow

* Daily standups
* Kanban board used for task assignment
* PRs required for merging into `main`
* Regular checkpoints with mentor

---

## 💡 Educational Context

This project was part of a collaborative educational challenge focused on improving teamwork, code quality, and Git collaboration. Thanks to all participants and reviewers!

**Happy Coding!** 🚀

---

## 📄 License

This project is for educational purposes only. No commercial license is applied.
