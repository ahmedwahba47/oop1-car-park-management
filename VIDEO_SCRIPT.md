# Video Screencast Script - Car Park Management System
## OOP1 Assignment 2025/26 - Ahmed Wahba

---

## TIME BREAKDOWN

| Section | Duration | Cumulative |
|---------|----------|------------|
| Introduction | 0:30 | 0:30 |
| Live Demo (User Stories) | 2:30 | 3:00 |
| Fundamental Features | 3:00 | 6:00 |
| Advanced Features | 1:30 | 7:30 |
| Java 25 Features | 1:00 | 8:30 |
| Conclusion | 0:30 | 9:00 |

**Total: ~9 minutes** (within 10 min limit)

---

## PART 1: INTRODUCTION (0:30)

**[SCREEN: Title slide or IDE with project open]**

> "Hello, my name is Ahmed Wahba. This is my OOP1 assignment - a Car Park Management System built in Java 25.
>
> I'll start by demonstrating the application, then walk through the Java language features I've implemented - covering fundamentals, advanced concepts, and Java 25 preview features."

---

## PART 2: LIVE DEMO - USER STORIES (2:30)

**[SCREEN: Run the application in terminal]**

> "Let me run the application and demonstrate all user stories."

```bash
java --enable-preview --source 25 src/main/java/Main.java
```

### Demo 1: Park Vehicles (0:30)

> "First, I'll park a car. I select option 1, enter 'c' for car, and registration 'ABC123'."

**[Type: 1 → c → ABC123]**

> "Car parked at slot 1. Now a motorbike - option 1, 'mc', registration 'XYZ789'."

**[Type: 1 → mc → XYZ789]**

> "Motorbike parked at slot 2."

### Demo 2: View Parking Status (0:20)

> "Option 3 displays all parking slots."

**[Type: 3]**

> "We see slot 1 has a Car with ABC123, slot 2 has a Motorbike with XYZ789, and slots 3-10 are available."

### Demo 3: Find Vehicles by Type (0:20)

> "Option 4 finds vehicles by type. Let me search for cars."

**[Type: 4 → car]**

> "It finds the car at slot 1 with its registration number. This uses lambdas and pattern matching internally."

### Demo 4: View Specific Slots - Varargs (0:20)

> "Option 5 demonstrates varargs - viewing multiple slots at once."

**[Type: 5 → 1 2 3]**

> "I entered three slot numbers separated by spaces. The method `printSlotDetails(int... slotNumbers)` accepts any number of arguments."

### Demo 5: Unpark Vehicle (0:25)

> "Option 2 unparks a vehicle. Let me unpark slot 1."

**[Type: 2 → 1]**

> "The system calculates the parking fee based on duration and vehicle type - cars cost 2.50 per hour. It returns a Ticket with entry time, exit time, and fee."

### Demo 6: Handle Duplicate Registration (0:15)

> "Let me show error handling. I'll try to park with the same registration as the motorbike."

**[Type: 1 → c → XYZ789]**

> "The system rejects it - 'Vehicle already parked'. This is an unchecked IllegalArgumentException."

### Demo 7: Handle Invalid Input (0:20)

> "If I try to unpark an empty slot..."

**[Type: 2 → 1]**

> "'Slot is already empty' - proper validation with meaningful error messages."

---

## PART 3: FUNDAMENTAL FEATURES (3:00)

**[SCREEN: Open IDE with source files]**

### 3.1 Classes & Inheritance (0:30)

**[Open: Vehicle.java]**

> "The application has several classes. Vehicle is an abstract class - you cannot instantiate it directly."

**[Open: Car.java]**

> "Car extends Vehicle. This is inheritance - Car inherits fields and methods from Vehicle, but provides its own implementation of `calculateFee()`."

**[Open: Motorbike.java briefly]**

> "Motorbike also extends Vehicle with a different fee calculation. This is polymorphism - same method name, different behavior."

### 3.2 this() vs this. (0:25)

**[Open: Vehicle.java - show both constructors]**

> "Here's the difference between `this()` and `this.`:
> - Line 17: `this.registrationNumber` - the dot accesses the instance variable
> - Line 27: `this(registrationNumber, VehicleType.CAR)` - parentheses call another constructor in the same class. This is constructor chaining."

### 3.3 super() vs super. (0:25)

**[Open: Car.java]**

> "Similarly for `super()` and `super.`:
> - Line 16: `super(registrationNumber, VehicleType.CAR)` calls the parent's constructor
> - Line 32 in toString: `super.getRegistrationNumber()` - the dot calls the parent's method"

### 3.4 Encapsulation (0:20)

**[Open: ParkingSlot.java]**

> "Encapsulation means private fields with public getters. Here, `slotNumber`, `vehicle`, `status` are all private. External code accesses them through getter methods like `getSlotNumber()`. State changes only through controlled methods like `park()` and `unpark()`."

### 3.5 Interfaces - Private/Default/Static Methods (0:25)

**[Open: Parkable.java]**

> "The Parkable interface demonstrates three types of interface methods:
> - Line 5: `static` method `getParkingInfo()` - called without an instance
> - Line 10: `default` method `printRegistration()` - provides implementation that classes can use or override
> - Lines 16 and 23: `private` methods - helper methods only visible inside the interface"

### 3.6 Method Overloading & Varargs (0:20)

**[Open: ParkingService.java]**

> "Method overloading: two `park()` methods - line 27 takes a Vehicle object, line 110 takes a String and VehicleType. Same name, different parameters.
>
> Line 121: `printSlotDetails(int... slotNumbers)` - varargs lets us pass any number of integers."

### 3.7 Enums & Arrays (0:15)

**[Open: VehicleType.java, then ParkingStatus.java briefly]**

> "Enums provide type-safe constants - VehicleType has CAR and MOTORBIKE, ParkingStatus has OCCUPIED and AVAILABLE."

**[Show: ParkingService.java line 16]**

> "Line 16: `ParkingSlot[] slots` - an array to manage parking slots."

### 3.8 Exceptions - Checked vs Unchecked (0:20)

**[Open: ParkingFullException.java]**

> "ParkingFullException extends Exception - this is a checked exception. The compiler forces you to handle it with try-catch or throws."

**[Open: ParkingService.java - show IllegalArgumentException]**

> "IllegalArgumentException is unchecked - extends RuntimeException. Used for invalid slot numbers. No compile-time handling required."

### 3.9 Java Core API (0:20)

**[Open: ParkingService.java]**

> "Java Core API usage:
> - Line 17: `ArrayList` for ticket history
> - Line 18: `HashSet` to track parked registrations
> - Line 76: `StringBuilder` for efficient string building
> - Lines 57-58: `LocalDateTime` from the Date API for entry and exit times"

---

## PART 4: ADVANCED FEATURES (1:30)

### 4.1 Sealed Classes (0:20)

**[Open: Vehicle.java - line 8]**

> "Vehicle is a `sealed` class - it `permits` only Car and Motorbike to extend it. No other class can inherit from Vehicle. This provides better type safety and exhaustive pattern matching."

### 4.2 Lambdas with Predicate (0:25)

**[Open: ParkingService.java - findVehicles method]**

> "The `findVehicles` method takes a `Predicate<Vehicle>` parameter - a functional interface. Callers pass a lambda expression to define the filter condition."

**[Open: Main.java - show lambda usage around line 116]**

> "Here I pass the lambda `v -> v instanceof Car car`. This filters for vehicles that are Cars."

### 4.3 Pattern Matching (0:15)

**[Point to: Main.java line 116]**

> "This lambda uses pattern matching: `v instanceof Car car`. It checks if v is a Car AND creates a variable `car` of type Car - all in one expression. No explicit cast needed."

### 4.4 Switch Expressions (0:15)

**[Open: ParkingService.java - lines 111-114]**

> "Switch expressions return values directly. Here I create either a Car or Motorbike based on the enum. Arrow syntax, no break statements, no fall-through."

**[Open: Main.java - lines 160-164]**

> "Another switch expression handles user input shortcuts - 'c' maps to 'CAR', 'mc' to 'MOTORBIKE'."

### 4.5 Final vs Effectively Final (0:15)

**[Open: ParkingService.java - show comment around line 89-94]**

> "Lambdas can only capture variables that are `final` or effectively final - meaning never reassigned after initialization. The variables `slot` and `vehicle` in my loop are effectively final because I never reassign them."

---

## PART 5: JAVA 25 FEATURES (1:00)

### 5.1 Instance Main Methods - JEP 512 (0:30)

**[Open: Main.java - line 21]**

> "Java 25 introduces instance main methods - JEP 512. Instead of the traditional `public static void main(String[] args)`, I simply write `void main()`.
>
> This is simpler and allows me to call instance methods directly without creating an object. All the helper methods in Main are instance methods.
>
> This requires the `--enable-preview` flag when compiling and running."

### 5.2 Flexible Constructor Bodies - JEP 513 (0:30)

**[Open: Car.java - constructor lines 12-17]**

> "Java 25 also introduces flexible constructor bodies - JEP 513. Previously, `super()` had to be the very first statement in a constructor.
>
> Now look at lines 13-15: I validate that the registration number is not null or blank BEFORE calling `super()` on line 16. If validation fails, I throw an exception before the parent constructor ever runs.
>
> This was impossible in earlier Java versions and makes constructors much more flexible."

---

## PART 6: CONCLUSION (0:30)

**[SCREEN: IDE or summary view]**

> "To summarize, this Car Park Management System demonstrates:
>
> All fundamental features - classes, inheritance, encapsulation, interfaces with private/default/static methods, method overloading, varargs, enums, arrays, exceptions, and Java Core API.
>
> Advanced features - sealed classes, lambdas with Predicate, pattern matching, switch expressions, and final versus effectively final.
>
> And for extra marks - Java 25 features: instance main methods and flexible constructor bodies.
>
> Thank you for watching."

---

## RECORDING CHECKLIST

Before recording:
- [ ] IDE font size increased (16-18pt)
- [ ] Terminal font size increased
- [ ] All files open and ready in tabs
- [ ] Application compiles and runs
- [ ] Microphone tested for clear audio
- [ ] Screen recording software ready
- [ ] Script printed or on second monitor

During recording:
- [ ] Speak clearly and at moderate pace
- [ ] Pause briefly between sections
- [ ] Highlight/annotate code as you explain
- [ ] Keep eye on time

File order to open:
1. Terminal (for demo)
2. Vehicle.java
3. Car.java
4. Motorbike.java
5. ParkingSlot.java
6. Parkable.java
7. ParkingService.java
8. VehicleType.java
9. ParkingStatus.java
10. ParkingFullException.java
11. Main.java
