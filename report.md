# OOP1 Assignment Report: Car Park Management System

## 1. Introduction

This report details the design and implementation of a Car Park Management System, developed as part of the OOP1 assignment. The application is a command-line based tool that allows users to park and unpark vehicles, view the parking status, and calculate parking fees.

The primary goal of this project is to demonstrate a comprehensive understanding of Java programming principles, from fundamental concepts to advanced features, including new features in Java 25.

## 2. User Stories Completed

The application implements the following user stories, which correspond to the features listed in the assignment brief:

*   **Park a vehicle**: Users can park a vehicle by specifying its type (Car or Motorbike) and registration number. The system assigns the vehicle to the first available parking slot.
*   **Unpark a vehicle**: Users can unpark a vehicle by providing the slot number. The system calculates the parking fee based on the duration of the stay and the vehicle type.
*   **Parking status**: Users can view the current status of all parking slots, including which slots are occupied and by which vehicles.
*   **Find vehicles**: Users can find parked vehicles by their type.
*   **Handle full car park**: If a user tries to park a vehicle when the car park is full, the system gracefully handles the situation by displaying a "Parking Full" message.
*   **Handle invalid input**: The application validates user input and provides appropriate error messages for invalid slot numbers or vehicle types.

## 3. Evaluation

### 3.1. Adherence to the Project Brief

The project successfully implements all the fundamental and advanced Java features specified in the assignment brief. This includes:

*   **Fundamentals**: Classes, objects, encapsulation, inheritance, polymorphism, exceptions, enums, arrays, and the Java Core API (String, StringBuilder, List/ArrayList, Date API).
*   **Advanced**: Call-by-value, defensive copying, records, sealed classes, lambdas, method references, switch expressions, and pattern matching.

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
