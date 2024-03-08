# Hotel Management System

The Hotel Management System is an Object-Oriented Programming (OOP) project developed in Java. It demonstrates the implementation of various OOP concepts, including inheritance, polymorphism, encapsulation, and abstraction. The system is designed to manage employees, guests, and room reservations in a hotel.

## Project Overview

The project consists of several classes that represent different entities and their relationships within the hotel management system. The main classes are:

1. `HotelManager`: This class manages the hotel's operations, such as adding and updating guests, employees, and room reservations.
2. `Employee`: This is an abstract class that represents an employee in the hotel. It has two concrete subclasses: `Manager` and `Receptionist`.
3. `Guest`: This class represents a guest staying at the hotel. It stores information about the guest, including their check-in and check-out dates, as well as the room they are assigned to.
4. `Room`: This class represents a room in the hotel. It keeps track of room reservations and availability.
5. `Reservation`: This class represents a reservation made for a room. It stores the check-in and check-out dates.
6. `User`: This is an interface that defines a contract for displaying user details.

## Features

The Hotel Management System provides the following features:

- **Add Users**: The system allows adding new employees (managers and receptionists) and guests.
- **Display Data**: The system can display the details of all employees and guests currently registered in the hotel.
- **Update User**: The system enables updating guest information, such as name, check-in/check-out dates, and room assignment.
- **Delete User**: The system allows deleting a guest from the system, effectively canceling their reservation.
- **Room Availability**: The system can check and display the availability of rooms based on specified check-in and check-out dates.
- **Room Reservation**: When adding a guest, the system assigns them a room based on availability during their stay.
- **Graphical User Interface (GUI)**: The project includes a GUI implementation using Java Swing, allowing users to interact with the system through a user-friendly interface.

## Getting Started

To run the Hotel Management System. Follow these steps:

1. Clone the repository or download the project files.
2. Locate the `HotelManagementSystem` class, which contains the `main` method.
3. Run the `main` method to start the application.

For the GUI version, locate the `HotelManagementSystemGUI` class and run the `main` method.
