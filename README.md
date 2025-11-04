# Car Park Management System

## Project Overview

This project implements a command-line based Car Park Management System in Java. Developed as part of the OOP1 assignment, it showcases a wide range of Java programming principles, from fundamental object-oriented concepts to advanced features introduced in Java 25. The system allows users to manage parking slots, park and unpark vehicles, view parking status, and calculate fees.

## Features

The application provides the following core functionalities:

*   **Park a Vehicle**: Users can park cars or motorbikes, and the system automatically assigns an available slot.
*   **Unpark a Vehicle**: Vehicles can be unparked by specifying the slot number. The system calculates the parking fee based on the duration of the stay and the vehicle type.
*   **View Parking Status**: Displays the current occupancy of all parking slots, indicating which slots are available and which are occupied by specific vehicles.
*   **Find Vehicles**: Allows users to search for parked vehicles based on criteria such as vehicle type.
*   **Handle Edge Cases**: Gracefully manages scenarios like a full car park (`ParkingFullException`) and invalid user input.
*   **Demonstrates Java Features**: Implements various fundamental and advanced Java features as required by the OOP1 assignment, including Java 25 specific features like Instance Main Methods (JEP 512) and Flexible Constructor Bodies (JEP 513).

## Technologies Used

*   **Java 25**: The core programming language, utilizing its latest features.
*   **Maven**: For project management, dependency management, and build automation.
*   **JUnit 5**: For unit testing.

## Project Structure

The project follows a standard Maven directory structure:

```
oop1/
└── car-park-management/
    ├── pom.xml
    ├── src/
    │   ├── main/
    │   │   └── java/com/carpark/
    │   │       ├── Main.java             # Application entry point
    │   │       ├── App.java              # Main application logic
    │   │       ├── exception/            # Custom exception classes
    │   │       │   └── ParkingFullException.java
    │   │       ├── model/                # Data model classes (Vehicle, Car, Motorbike, ParkingSlot, Ticket, Money, etc.)
    │   │       ├── service/              # Business logic (ParkingService)
    │   │       │   └── ParkingService.java
    │   └── test/
    │       └── java/com/carpark/
    │           └── service/
    │               └── ParkingServiceTest.java # Unit tests for ParkingService
    ├── UML_Diagram.puml    # PlantUML diagram of the system
    ├── report.md           # Detailed project report
    └── video_script.md     # Script for the video demonstration
```

## Setup and Installation

To set up and run this project, you will need:

*   **JDK 25**: Ensure you have a Java Development Kit version 25 installed and configured.
*   **Maven**: Apache Maven should be installed and accessible from your command line.

### Command Line Setup

1.  **Clone the repository**:
    ```bash
    git clone https://github.com/ahmedwahba47/oop1-car-park-management.git
    cd oop1-car-park-management
    ```
2.  **Compile the project**:
    ```bash
    mvn clean install
    ```
3.  **Run the application**:
    ```bash
    java --enable-preview --source 25 src/main/java/com/carpark/App.java
    ```
    *(Note: `App.java` is the main class for the application logic, `Main.java` is the entry point for the instance main method demonstration.)*

4.  **Run tests**:
    ```bash
    mvn test
    ```

### Eclipse Setup

1.  **Install JDK 25**: Make sure you have a JDK 25 build installed on your system.
2.  **Configure Eclipse**:
    *   Go to `Window > Preferences > Java > Installed JREs`.
    *   Click `Add...`, select `Standard VM`, and navigate to your JDK 25 installation directory.
    *   Set this JDK as the default.
3.  **Import the project**:
    *   Go to `File > Import...`.
    *   Select `Maven > Existing Maven Projects`.
    *   Browse to the `car-park-management` directory.
    *   Click `Finish`.
4.  **Run the application**:
    *   Right-click on the `Main.java` file.
    *   Select `Run As > Java Application`.
5.  **Run the tests**:
    *   Right-click on the `ParkingServiceTest.java` file.
    *   Select `Run As > JUnit Test`.

## Usage

Upon running the application, you will be presented with a menu of options to interact with the Car Park Management System. Follow the on-screen prompts to park vehicles, unpark them, view the parking status, or find specific vehicles.

## UML Diagram

A `UML_Diagram.puml` file is included in the repository, providing a visual representation of the system's class structure, relationships, and key components. This diagram helps in understanding the overall design and how different parts of the system interact.

## Code Highlights

The project demonstrates a comprehensive application of Java features:

*   **Object-Oriented Programming (OOP)**: Extensive use of classes (`Vehicle`, `Car`, `Motorbike`, `ParkingSlot`, `Ticket`, `Money`, `ParkingService`), encapsulation, inheritance, and polymorphism.
*   **Interfaces**: The `Parkable` interface showcases `static`, `default`, and `private` methods.
*   **Exceptions**: Robust error handling using custom checked exceptions (`ParkingFullException`) and unchecked exceptions.
*   **Enums**: `VehicleType` and `ParkingStatus` for type-safe constant representation.
*   **Java Core API**: Effective use of `String`, `StringBuilder`, `List`/`ArrayList`, and `java.time` package.
*   **Records**: `Ticket` class implemented as an immutable Java record.
*   **Custom Immutable Type**: The `Money` class ensures precise and immutable handling of monetary values.
*   **Lambdas & Method References**: Used for functional programming constructs, such as predicates for filtering.
*   **Switch Expressions & Pattern Matching**: Modern control flow and type-checking mechanisms.
*   **Sealed Classes**: `Vehicle` is a sealed abstract class, explicitly controlling its subclass hierarchy.
*   **Java 25 Features**:
    *   **Instance Main Methods (JEP 512)**: Simplifies the application's entry point.
    *   **Flexible Constructor Bodies (JEP 513)**: Allows for validation logic before `super()` calls in constructors.

## Contributing

Feel free to fork the repository, make improvements, and submit pull requests.

## License

This project is open-source and available under the MIT License.
