# Video Script: Car Park Management System

## Introduction (0:00 - 0:45)

**(Visual: Title slide with "Car Park Management System" and your name)**

"Hello everyone, and welcome to this demonstration of my Car Park Management System. My name is [Your Name], and this project was developed as part of the OOP1 assignment, showcasing various Java programming principles and features, including some of the exciting new additions in Java 25. The application is a command-line tool that allows you to park and unpark vehicles, check the parking status, and calculate fees."

## Project Overview & User Stories (0:45 - 1:45)

**(Visual: Briefly show the running application, then switch to a slide listing user stories)**

"The application is a command-line based tool designed to manage parking slots. It allows users to perform several key actions:"

*   "**Park a vehicle**: Users can park cars or motorbikes, and the system automatically assigns an available slot. We've implemented a check to prevent parking vehicles with duplicate registration numbers, and you can now use shortcuts like 'c' for CAR and 'mc' for MOTORBIKE."
*   "**Unpark a vehicle**: Vehicles can be unparked by specifying the slot number, and the system calculates the parking fee."
*   "**View parking status**: Users can see which slots are occupied and by whom. The status display now clearly indicates the vehicle type, for example, 'Occupied by Car (abc123)'."
*   "**Find vehicles**: The system can locate vehicles based on specific criteria, such as vehicle type. The output now includes the slot number where the vehicle is parked, and you can use vehicle type shortcuts here as well."
*   "**Handle edge cases**: The system gracefully handles scenarios like a full car park or invalid user input."

## UML Diagram Overview (1:45 - 3:15)

**(Visual: Display the UML_Diagram.puml rendered as a clear image)**

"Before we dive into the code, let's take a moment to look at the UML diagram for our Car Park Management System. This diagram provides a high-level visual representation of the system's structure, showing the classes, their attributes, methods, and the relationships between them. It's an excellent way to understand the overall design at a glance. This diagram has been updated to reflect all the recent changes and enhancements to the system."

"As you can see, we have key classes like `Vehicle`, which is an abstract class, with `Car` and `Motorbike` extending it. This clearly illustrates **inheritance** and how specific vehicle types inherit common properties and behaviors. The `Vehicle` class is also a **sealed class**, explicitly defining its permitted subclasses, which enhances type safety."

"We also have the `ParkingSlot` class, which manages individual parking spaces, and the `Ticket` record, an **immutable data carrier** that stores details about a parked vehicle's session. The `Money` class, a **custom immutable type**, is used within the `Ticket` to handle fees, ensuring consistency and preventing accidental modification."

"The `ParkingService` class acts as the central orchestrator, managing parking operations and interacting with `ParkingSlot` and `Ticket` objects. You can also observe the `Parkable` interface, which defines a contract for objects that can be parked, demonstrating the use of **interfaces** in our design. This interface includes `static`, `default`, and `private` methods, showcasing advanced interface features."

"This visual representation helps us understand how **encapsulation** is applied within each class, with private attributes and public methods. It also hints at **polymorphism**, as different `Vehicle` types will have their own implementations for methods like `calculateFee`."

## Project Structure & Running Application (3:15 - 4:00)

**(Visual: Transition to IDE, showing project structure in Eclipse, then running the application)**

"Here is the project structure in Eclipse. It's a Maven project, which helps manage dependencies and the build process. I have organized the code into packages: `com.carpark.model` for the data classes, `com.carpark.service` for the business logic, and `com.carpark.exception` for custom exceptions. The `pom.xml` file is configured to use Java 25 and includes the necessary dependencies for JUnit 5."

"Now, let's run the application. I'll right-click on `Main.java` and choose 'Run As > Java Application'. `Main.java` is now our primary entry point. The application starts and presents a menu with several options."

## Live Demo (4:00 - 7:30)

**(Visual: Live demo of the application, showing parking, unparking, status, and finding vehicles)**

"Let's start with a live demonstration of the system's functionality."

*   "**Parking a Vehicle**: I'll choose option 1, enter 'c' for CAR as the vehicle type, and provide a registration number. The system confirms that the car has been parked and assigns it a slot number. If I try to park another vehicle with the same registration number, the system will prevent it. I'll also park a motorbike using the 'mc' shortcut. If I try to park another vehicle when the car park is full, the system will display a 'Parking Full' message."
*   "**Displaying Parking Status**: Option 3 allows us to see the current status of the car park. Notice how it now clearly shows the vehicle type along with the registration number for occupied slots. This feature uses a `StringBuilder` internally for efficient string manipulation."
*   "**Find vehicles by type**: Option 4 allows us to find vehicles by their type. I'll choose option 4 and enter 'CAR'. The system will then list all parked cars, including their slot numbers. This feature uses a lambda expression with pattern matching for `instanceof` to filter vehicles."
*   "**Unparking a Vehicle**: Now, let's unpark a vehicle. I'll choose option 2 and enter the slot number. The system calculates the parking fee, represented by our custom `Money` class, and displays the ticket details. Notice that the entry and exit times in the ticket are now formatted to show only up to the second. The fee is calculated based on the duration of the stay and the vehicle type. The `Ticket` is a Java `record`, demonstrating immutability."
*   "**View details of specific slots (Varargs example)**: Option 5 demonstrates the use of varargs. I can enter multiple slot numbers, and the system will display their details."

## Code Highlights (7:30 - 9:30)

**(Visual: Transition to IDE, showing code snippets as you discuss them)**

"Now, let's dive into the code and highlight some of the core object-oriented programming concepts and advanced Java features implemented in this project."

*   "**Classes and Objects**: We have classes like `Vehicle`, `Car`, `Motorbike`, `ParkingSlot`, `Ticket`, `Money`, and `ParkingService`. Each object represents a real-world entity within our car park domain."
*   "**Encapsulation**: All our model classes use private fields with public getters, ensuring data integrity and controlled access."
*   "**Inheritance and Polymorphism**: `Car` and `Motorbike` extend the `Vehicle` abstract class. This allows us to use polymorphism, for example, when calculating fees, where each vehicle type has its own `calculateFee` implementation."
*   "**Interfaces**: The `Parkable` interface defines common behavior for parkable entities, demonstrating `static`, `default`, and `private` methods within an interface."
*   "**Exceptions**: We use custom exceptions like `ParkingFullException` and standard `IllegalArgumentException` for robust error handling. The `ParkingService` now throws an `IllegalArgumentException` if a user attempts to park a vehicle with a duplicate registration number."
*   "**Enums**: `VehicleType` and `ParkingStatus` enums provide type-safe representations for fixed sets of constants."
*   "**Arrays and Java Core API**: Parking slots are managed using an array, and we extensively use `String`, `StringBuilder`, `List`/`ArrayList`, `Set`/`HashSet` (for tracking parked registration numbers), and the `java.time` package for date and time operations."
*   "**Records**: The `Ticket` class is a Java record, offering a concise and immutable way to store parking ticket information. It now includes a custom `toString()` method to format `LocalDateTime` objects to the second."

*   "**LVTI (Local-Variable Type Inference - `var`)**: We also make selective use of the `var` keyword in `Main.java` to improve code readability. For example, when declaring the `ticket` and `type` variables, `var` allows for more concise code without sacrificing clarity."
*   "**Custom Immutable Type**: Our `Money` class is a custom immutable type, ensuring that monetary values are handled safely and consistently."
*   "**Lambdas and Method References**: We utilize lambdas with `Predicate` for flexible vehicle searching. The `ParkingService.findVehicles` method now returns a `List<ParkingSlot>`, allowing us to easily retrieve both the vehicle and its slot information. Method references are used for cleaner code, such as `forEach(System.out::println)`."
*   "**Switch Expressions and Pattern Matching**: A switch expression is used in the overloaded `park` method in `ParkingService`. Pattern matching for `instanceof` is used in the `findVehicles` method in `Main.java` for more concise type checking and casting."
*   "**Sealed Classes**: The `Vehicle` class is a sealed abstract class, explicitly controlling its subclass hierarchy to `Car` and `Motorbike`, enhancing type safety."
*   "**Java 25 Specific Features**: This project also incorporates some of the exciting new features introduced in Java 25. Our `Main` class uses an **Instance Main Method (JEP 512)**, simplifying the traditional `public static void main` signature. In `Car` and `Motorbike` constructors, we demonstrate **Flexible Constructor Bodies (JEP 513)**, allowing us to perform validation logic *before* calling the `super()` constructor. This provides greater control during object initialization."
*   "I've also added comments throughout the code to explain concepts like `this()` vs `this.`, `super()` vs `super.`, call-by-value, defensive copying, and `final`/`effectively final`."

## Conclusion (9:30 - 10:00)

**(Visual: Concluding slide with "Thank You" and contact information if desired)**

"That concludes the demonstration of my Car Park Management System. This project has been a great opportunity to apply the concepts learned in the OOP1 module and to explore the new features in Java 25. Thank you for watching!"