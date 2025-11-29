# Video Screencast Script - Car Park Management System
## OOP1 Assignment 2025/26

**Total Duration: ~8-9 minutes**
**Key Points**: Clear audio, visual annotations, demonstrate ALL user stories and language features

---

## INTRO (30 seconds)

**[Show: Title slide with your name and student ID]**

> "Hello, my name is [YOUR NAME], and this is my OOP1 assignment - a Car Park Management System built in Java 25.
>
> In this video, I'll demonstrate all user stories and explain the Java language features I've implemented, including both fundamental and advanced concepts, plus Java 25 preview features."

---

## SECTION 1: PROJECT OVERVIEW (1 minute)

**[Show: IDE with project structure open]**

> "Let me start with an overview of the project structure."

**[Annotate/highlight each package as you mention it]**

> "The project is organized into packages:
> - `com.carpark.model` - Contains our domain classes: Vehicle, Car, Motorbike, ParkingSlot, Ticket, Money, and enums
> - `com.carpark.service` - Contains ParkingService which handles all business logic
> - `com.carpark.exception` - Contains our custom checked exception
> - The Main class at the root handles user interaction"

**[Show: UML diagram briefly]**

> "Here's the UML class diagram showing the relationships between classes."

---

## SECTION 2: FUNDAMENTAL FEATURES (3 minutes)

### 2.1 Classes, Inheritance & Sealed Classes (45 sec)

**[Open: Vehicle.java]**

> "Vehicle is a **sealed abstract class** - a feature from Java 17. The `sealed` keyword means only explicitly permitted classes can extend it - in this case, Car and Motorbike."

**[Highlight: `sealed` and `permits` keywords]**

> "This provides better type safety than regular inheritance."

**[Open: Car.java]**

> "Car is a **final class** that extends Vehicle. The `final` keyword means no class can extend Car."

### 2.2 this() vs this. (30 sec)

**[Open: Vehicle.java, show both constructors]**

> "Here I demonstrate the difference between `this()` and `this.`:
> - `this.registrationNumber` uses `this.` to access the instance variable
> - `this(registrationNumber, VehicleType.CAR)` uses `this()` to chain to another constructor"

**[Annotate both usages on screen]**

### 2.3 super() vs super. (30 sec)

**[Open: Car.java]**

> "Similarly, `super()` and `super.` serve different purposes:
> - In the constructor, `super(registrationNumber, VehicleType.CAR)` calls the parent constructor
> - In the toString method, `super.getRegistrationNumber()` calls the parent's method"

**[Highlight lines 16 and 31-32]**

### 2.4 Interfaces with Private/Default/Static Methods (30 sec)

**[Open: Parkable.java]**

> "The Parkable interface demonstrates three types of interface methods:
> - `static` method: `getParkingInfo()` - can be called without an instance
> - `default` method: `printRegistration()` - provides default implementation
> - `private` methods: `printVin()` and `generateVin()` - helper methods only visible within the interface"

### 2.5 Other Fundamentals (45 sec)

**[Open: ParkingService.java]**

> "ParkingService demonstrates several features:
> - **Method overloading**: Two `park()` methods - one takes a Vehicle, one takes String and VehicleType
> - **Varargs**: `printSlotDetails(int... slotNumbers)` accepts variable number of arguments
> - **Arrays**: Parking slots are stored in a `ParkingSlot[]` array
> - **Java Core API**: We use ArrayList, HashSet, StringBuilder, and LocalDateTime"

**[Show: Enums VehicleType.java and ParkingStatus.java briefly]**

> "Enums provide type-safe constants for VehicleType and ParkingStatus."

---

## SECTION 3: ADVANCED FEATURES (2 minutes)

### 3.1 Records & Custom Immutable Type (30 sec)

**[Open: Ticket.java]**

> "Ticket is implemented as a Java **record** - a concise way to create immutable data carriers. Records automatically generate constructor, getters, equals, hashCode, and toString."

**[Open: Money.java]**

> "Money is a **custom immutable type**. It's a final class with final fields, no setters, and methods return new instances rather than modifying state."

### 3.2 Lambdas, Predicates & Method References (30 sec)

**[Open: ParkingService.java, findVehicles method]**

> "The `findVehicles` method accepts a `Predicate<Vehicle>` - a functional interface. This allows callers to pass lambda expressions to filter vehicles."

**[Open: Main.java, show the lambda usage]**

> "Here in Main, I pass a lambda: `v -> v instanceof Car car`. This uses **pattern matching** - a Java 21 feature where instanceof both checks the type AND creates a variable."

**[Show line 110]**

> "I also use a **method reference**: `System.out::println` - a shorthand for lambda expressions."

### 3.3 Switch Expressions (20 sec)

**[Open: ParkingService.java, line 105-108]**

> "Switch expressions return values directly. Here, I create either a Car or Motorbike based on the vehicle type enum."

**[Open: Main.java, line 131-135]**

> "Another switch expression handles user input shortcuts."

### 3.4 Final vs Effectively Final (20 sec)

**[Open: ParkingService.java, show the comment at findVehicles]**

> "Lambdas can only capture variables that are `final` or `effectively final`. Effectively final means the variable is never reassigned after initialization. This is documented in my code comments."

### 3.5 Exceptions (20 sec)

**[Open: ParkingFullException.java]**

> "`ParkingFullException` is a **checked exception** - it extends Exception. The compiler forces callers to handle it."

**[Open: ParkingService.java, show IllegalArgumentException usage]**

> "`IllegalArgumentException` is an **unchecked exception** - it extends RuntimeException. Used for invalid slot numbers or duplicate registrations."

---

## SECTION 4: JAVA 25 FEATURES (1 minute)

### 4.1 Instance Main Methods - JEP 512 (30 sec)

**[Open: Main.java]**

> "Java 25 introduces **instance main methods** - JEP 512. Instead of the traditional `public static void main(String[] args)`, I can simply write `void main()`. This simplifies the entry point significantly."

**[Highlight line 12]**

> "To use this feature, we need the `--enable-preview` flag when compiling and running."

### 4.2 Flexible Constructor Bodies - JEP 513 (30 sec)

**[Open: Car.java, constructor]**

> "Java 25 also introduces **flexible constructor bodies** - JEP 513. Previously, `super()` had to be the first statement. Now, I can execute validation logic BEFORE calling the parent constructor."

**[Highlight lines 13-16]**

> "Here, I validate that the registration number is not null or blank before calling `super()`. This was impossible in earlier Java versions."

---

## SECTION 5: LIVE DEMO - ALL USER STORIES (2 minutes)

**[Run the application]**

> "Now let me demonstrate all user stories."

### 5.1 Park a Vehicle (20 sec)

> "Option 1 - Park a vehicle. I'll enter 'c' for Car and registration 'ABC123'."

**[Type: 1, c, ABC123]**

> "The car is parked at slot 1. Let me park a motorbike - 'mc' for Motorbike, 'XYZ789'."

**[Type: 1, mc, XYZ789]**

### 5.2 Duplicate Registration (15 sec)

> "If I try to park a vehicle with the same registration..."

**[Type: 1, c, ABC123]**

> "The system rejects it with an error message - preventing duplicates."

### 5.3 Display Parking Status (15 sec)

> "Option 3 shows all parking slots."

**[Type: 3]**

> "We can see slots 1 and 2 are occupied, showing vehicle type and registration."

### 5.4 Find Vehicles by Type (20 sec)

> "Option 4 finds vehicles by type. Let me search for cars."

**[Type: 4, car]**

> "It shows all cars with their slot numbers."

### 5.5 View Specific Slots - Varargs (15 sec)

> "Option 5 demonstrates varargs. I can view multiple slots at once."

**[Type: 5, 1 2 3]**

> "This calls `printSlotDetails(1, 2, 3)` using varargs."

### 5.6 Unpark a Vehicle (20 sec)

> "Option 2 unparks a vehicle and calculates the fee."

**[Type: 2, 1]**

> "The system shows the ticket with entry time, exit time, and calculated fee. The fee is based on duration and vehicle type - Car is 2.50 per hour, Motorbike is 1.50."

### 5.7 Handle Full Car Park (15 sec)

> "If I fill all 10 slots and try to park another vehicle, the system throws ParkingFullException and displays 'Car park is full.'"

**[Show the error message - can simulate or explain]**

---

## CONCLUSION (30 seconds)

**[Show: Summary slide or IDE]**

> "To summarize, this Car Park Management System demonstrates:
> - All fundamental features: classes, inheritance, interfaces, encapsulation, enums, arrays, exceptions
> - All advanced features: records, sealed classes, lambdas, switch expressions, pattern matching
> - Java 25 features: instance main methods and flexible constructor bodies
>
> The code compiles and runs with Java 25 using the `--enable-preview` flag.
>
> Thank you for watching."

---

## TIPS FOR RECORDING

1. **Audio**: Use a good microphone, speak clearly, minimize background noise
2. **Annotations**: Use screen drawing tools to highlight code as you explain
3. **Pace**: Don't rush - pause briefly between sections
4. **IDE**: Use a large font size (16-18pt) so code is readable
5. **Terminal**: Make terminal text large and clear
6. **Practice**: Do a dry run before recording
7. **Time**: Keep under 9 minutes (10 with grace period)

---

## CHECKLIST BEFORE RECORDING

- [ ] IDE open with all files ready
- [ ] Terminal ready to run application
- [ ] Screen recording software set up
- [ ] Microphone tested
- [ ] Font sizes increased for readability
- [ ] All code compiles and runs
- [ ] UML diagram ready to show
- [ ] This script printed/available for reference
