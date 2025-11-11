# OOP1 Assignment Report: Car Park Management System

## 1. Introduction

This report details the design and implementation of a Car Park Management System, developed as part of the OOP1 assignment. The application is a command-line based tool that allows users to park and unpark vehicles, view the parking status, and calculate parking fees.

The primary goal of this project is to demonstrate a comprehensive understanding of Java programming principles, from fundamental concepts to advanced features, including new features in Java 25.

## 2. User Stories Completed

The application implements the following user stories, which correspond to the features listed in the assignment brief, along with several enhancements:

*   **Park a vehicle**: Users can park a vehicle by specifying its type (Car or Motorbike) and registration number. The system assigns the vehicle to the first available parking slot. **New**: The system now prevents parking vehicles with duplicate registration numbers. Vehicle type input also accepts shortcuts (e.g., 'c' for CAR, 'mc' for MOTORBIKE).
*   **Unpark a vehicle**: Users can unpark a vehicle by providing the slot number. The system calculates the parking fee based on the duration of the stay and the vehicle type.
*   **Parking status**: Users can view the current status of all parking slots, including which slots are occupied and by which vehicles. **Enhanced**: The status now clearly indicates the vehicle type (e.g., "Occupied by Car (abc123)").
*   **Find vehicles**: Users can find parked vehicles by their type. **Enhanced**: The output now includes the slot number where the vehicle is parked. Vehicle type input also accepts shortcuts.
*   **View details of specific slots**: Users can view details of multiple parking slots by providing their numbers, demonstrating the use of varargs.
*   **Handle full car park**: If a user tries to park a vehicle when the car park is full, the system gracefully handles the situation by displaying a "Parking Full" message.
*   **Handle invalid input**: The application validates user input and provides appropriate error messages for invalid slot numbers or vehicle types.

## 3. Evaluation

### 3.1. Adherence to the Project Brief

The project successfully implements all the fundamental and advanced Java features specified in the assignment brief. This includes:

*   **Classes**: The system is built around several classes such as `Vehicle`, `Car`, `Motorbike`, `ParkingSlot`, `Ticket`, `ParkingService`, and `Money`.
*   **`this()` and `this.`**: Demonstrated in constructors (e.g., `Vehicle` class) to distinguish instance variables from parameters.
*   **Method Overloading**: The `ParkingService` class includes overloaded `park` methods, allowing vehicles to be parked using different parameters.
*   **Varargs**: The `ParkingService.printSlotDetails` method demonstrates the use of variable-arity arguments, allowing users to view details of multiple slots.
*   **LVTI (Local-Variable Type Inference - `var`)**: Used selectively in `Main.java` to improve code clarity in specific instances, such as when retrieving a `Ticket` object or determining the vehicle type from user input.
*   **Encapsulation**: Achieved through private fields and public getter methods across all model classes.
*   **Interfaces**: The `Parkable` interface is introduced, which the `Vehicle` class implements. This interface demonstrates `static`, `default`, and `private` methods within an interface.
*   **Inheritance**: `Car` and `Motorbike` classes extend the `Vehicle` abstract class, showcasing hierarchical relationships.
*   **Overriding and Polymorphism**: The `calculateFee` method is overridden in `Car` and `Motorbike` subclasses, demonstrating polymorphic behavior.
*   **`super()` and `super.`**: `super()` is explicitly used in subclass constructors (`Car`, `Motorbike`) to invoke the parent class constructor.
*   **Exceptions**: Both checked (`ParkingFullException`) and unchecked (`IllegalArgumentException`) exceptions are used for robust error handling, including a new check for duplicate registration numbers in `ParkingService`.
*   **Enums**: `VehicleType` and `ParkingStatus` enums are used for type-safe representation of fixed sets of constants.
*   **Arrays**: Parking slots are managed using an array in the `ParkingService`.
*   **Java Core API**: Extensive use of `String`, `StringBuilder` (for efficient string manipulation in `displayParkingStatus`), `List`/`ArrayList` (for `ticketHistory`), `Set`/`HashSet` (for tracking parked registration numbers), and `java.time` package (for `LocalDateTime` in `ParkingSlot` and `Ticket`).
*   **Call-by-value and Defensive Copying**: Primitive types are passed by value. Immutable objects like `Ticket` (a record) and `LocalDateTime` inherently prevent modification, thus eliminating the need for defensive copying. The custom `Money` class is also immutable.
*   **Records**: The `Ticket` class is implemented as a Java record, providing a concise way to declare immutable data carriers. It now includes a custom `toString()` method to format `LocalDateTime` objects to the second.
*   **Custom Immutable Type**: The `Money` class serves as a custom immutable type for handling monetary values.
*   **Lambdas (Predicate)**: Used in the `findVehicles` method to filter vehicles based on dynamic criteria. The `ParkingService.findVehicles` method now returns a `List<ParkingSlot>` to provide slot information.
*   **`final` or `effectively final`**: Discussed in comments where lambdas capture variables from their enclosing scope.
*   **Method References**: Used in `Main.java` (e.g., `foundVehicles.forEach(System.out::println)`).
*   **Switch Expressions and Pattern Matching**: A switch expression is used in the overloaded `park` method in `ParkingService`. Pattern matching for `instanceof` is used in the `findVehicles` method in `Main.java` for more concise type checking and casting.
*   **Sealed Classes and Interfaces**: The `Vehicle` class is a `sealed` abstract class, explicitly permitting only `Car` and `Motorbike` as its direct subclasses, enhancing type safety and design.

### 3.2. Java 25 Features

The project makes use of the following Java 25 features:

*   **Instance Main Methods (JEP 512)**: The application's entry point is an instance `main` method in the `Main` class. This simplifies the traditional `public static void main(String[] args)` method, making the code more concise. To compile and run this feature, the `--enable-preview` flag is required.
*   **Flexible Constructor Bodies (JEP 513)**: The `Car` and `Motorbike` classes use flexible constructor bodies to validate the registration number before calling the `super()` constructor. This demonstrates the ability to perform operations before the superclass constructor is invoked, enhancing constructor flexibility. This also requires the `--enable-preview` flag.

### 3.3. Problems Encountered

The main challenge was setting up the environment to work with Java 25 features. Since Java 25 is a new release, it required careful configuration of the `pom.xml` file to use the correct compiler version and enable preview features.

Another issue was the initial test setup. The default Maven archetype generated a test file that used an older version of JUnit, which was incompatible with the JUnit 5 dependency I added. This was resolved by removing the old test file and creating a new one with JUnit 5 annotations.

### 3.4. How to Get Java 25 Working

To compile and run this project, you need to have a JDK 25 build installed and configured in your environment. The `pom.xml` file is configured to use Java 25.

The key configurations in `pom.xml` are:

*   Setting the Java version:
    ```xml
    <properties>
        <maven.compiler.source>25</maven.compiler.source>
        <maven.compiler.target>25</maven.compiler.target>
    </properties>
    ```
*   Enabling preview features:
    ```xml
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.11.0</version>
        <configuration>
            <compilerArgs>
                <arg>--enable-preview</arg>
            </compilerArgs>
        </configuration>
    </plugin>
    ```

To run the application from the command line, navigate to the project's root directory and execute the following command:

```bash
java --enable-preview --source 25 src/main/java/Main.java
```

To run the tests, use the following Maven command:

```bash
mvn test
```

## 4. How to Open in Eclipse

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