# OOP1 Assignment Video Script

**(0:00-0:30) Introduction**

"Hello, my name is [Your Name], and this is my submission for the OOP1 assignment. I have developed a Car Park Management System in Java."

"The application is a command-line tool that allows you to park and unpark vehicles, check the parking status, and calculate fees. It demonstrates various Java features, including new features from Java 25."

**(0:30-1:15) Project Structure in Eclipse**

"Here is the project structure in Eclipse. It's a Maven project, which helps manage dependencies and the build process."

"I have organized the code into packages: `com.carpark.model` for the data classes, `com.carpark.service` for the business logic, and `com.carpark.exception` for custom exceptions."

"The `pom.xml` file is configured to use Java 25 and includes the necessary dependencies for JUnit 5."

**(1:15-2:00) Running the Application**

"Now, let's run the application. I'll right-click on `Main.java` and choose 'Run As > Java Application'."

"The application starts and presents a menu with several options."

**(2:00-3:30) Demo: Parking a Vehicle**

"Let's park a car. I'll choose option 1, enter 'CAR' as the vehicle type, and provide a registration number. Notice that the input validation is now improved, so it won't ask for a registration number if the vehicle type is invalid."

"The system confirms that the car has been parked and assigns it a slot number."

"Now, let's park a motorbike."

"If I try to park another vehicle when the car park is full, the system will display a 'Parking Full' message."

**(3:30-4:30) Demo: Displaying Parking Status**

"Option 3 allows us to see the current status of the car park. It shows which slots are occupied and by which vehicles. This feature uses a `StringBuilder` internally for efficient string manipulation."

**(4:30-5:15) Demo: Find vehicles by type**

"Option 4 allows us to find vehicles by their type. I'll choose option 4 and enter 'CAR'. The system will then list all parked cars. This feature uses a lambda expression with pattern matching for `instanceof` to filter vehicles."

**(5:15-6:30) Demo: Unparking a Vehicle**

"Now, let's unpark the car we parked earlier. I'll choose option 2 and enter the slot number."

"The system calculates the parking fee, represented by our custom `Money` class, and displays the ticket details. The fee is calculated based on the duration of the stay and the vehicle type. The `Ticket` is a Java `record`, demonstrating immutability."

**(6:30-7:00) Demo: View details of specific slots (Varargs example)**

"Option 5 demonstrates the use of varargs. I can enter multiple slot numbers, and the system will display their details."

**(7:00-9:30) Code Highlights**

"I'd like to highlight a few code features."

"The `Vehicle` class is a `sealed` class, which restricts its subclasses to `Car` and `Motorbike`. It also implements the `Parkable` interface, which demonstrates `static`, `default`, and `private` methods within an interface."

"The `Main` class uses an instance `main` method, a new feature in Java 25. This simplifies the entry point of the application."

"The `Car` and `Motorbike` constructors use flexible constructor bodies, another Java 25 feature, to validate the registration number before calling the super constructor."

"In the `findVehicles` method, I've used pattern matching for `instanceof` to concisely check the type of a vehicle and cast it simultaneously."

"The `Money` class serves as a custom immutable type for handling monetary values, ensuring precision and immutability."

"I've also added comments throughout the code to explain concepts like `this()` vs `this.`, `super()` vs `super.`, call-by-value, defensive copying, and `final`/`effectively final`."

## Project Overview & User Stories (0:30 - 1:30)

**(Visual: Briefly show the running application, then switch to a slide listing user stories)**

"The application is a command-line based tool designed to manage parking slots. It allows users to perform several key actions:"

*   "**Park a vehicle**: Users can park cars or motorbikes, and the system automatically assigns an available slot."
*   "**Unpark a vehicle**: Vehicles can be unparked by specifying the slot number, and the system calculates the parking fee."
*   "**View parking status**: Users can see which slots are occupied and by whom."
*   "**Find vehicles**: The system can locate vehicles based on specific criteria, such as vehicle type."
*   "**Handle edge cases**: The system gracefully handles scenarios like a full car park or invalid user input."

### 2.1. UML Diagram Overview (1:30 - 2:30)

**(Visual: Display the UML_Diagram.puml rendered as a clear image)**

"Before we dive into the code, let's take a moment to look at the UML diagram for our Car Park Management System. This diagram provides a high-level visual representation of the system's structure, showing the classes, their attributes, methods, and the relationships between them. It's an excellent way to understand the overall design at a glance."

"As you can see, we have key classes like `Vehicle`, which is an abstract class, with `Car` and `Motorbike` extending it. This clearly illustrates **inheritance** and how specific vehicle types inherit common properties and behaviors. The `Vehicle` class is also a **sealed class**, explicitly defining its permitted subclasses, which enhances type safety."

"We also have the `ParkingSlot` class, which manages individual parking spaces, and the `Ticket` record, an **immutable data carrier** that stores details about a parked vehicle's session. The `Money` class, a **custom immutable type**, is used within the `Ticket` to handle fees, ensuring consistency and preventing accidental modification."

"The `ParkingService` class acts as the central orchestrator, managing parking operations and interacting with `ParkingSlot` and `Ticket` objects. You can also observe the `Parkable` interface, which defines a contract for objects that can be parked, demonstrating the use of **interfaces** in our design. This interface includes `static`, `default`, and `private` methods, showcasing advanced interface features."

"This visual representation helps us understand how **encapsulation** is applied within each class, with private attributes and public methods. It also hints at **polymorphism**, as different `Vehicle` types will have their own implementations for methods like `calculateFee`."

## Code Walkthrough - Key Features (2:30 - 7:00)

**(9:30-10:00) Conclusion**

"This project has been a great opportunity to apply the concepts learned in the OOP1 module and to explore the new features in Java 25."

"Thank you for watching."
