# Android Development CAT1 Answers

## Exercise 1: API Levels

### (a) Animated vector graphics
Animated vector graphics became available in **API level 21** (Android 5.0 Lollipop). This is when Android added the AnimatedVectorDrawable class that lets developers create animated graphics that scale without losing quality.

### (b) Full-screen applications
Full-screen mode with the ability to hide system bars was introduced in **API level 19** (Android 4.4 KitKat). This feature is called "immersive mode" and it lets apps hide both the navigation and status bars for a better viewing experience.

## Exercise 2: Kotlin Fundamentals

### (a) 
```kotlin
// var v4 : Int = 4
// var v5 :Double = v4;
// This doesn't work because we can't directly assign an Int to a Double variable.
// The fix is to convert the Int to Double:

var v4 : Int = 4
var v5 :Double = v4.toDouble()
```

### (b) 
```kotlin
// var v2 :Any = 4.4
// var v3 :Double = v2
// This doesn't work because v2 is of type Any, which is a general type.
// Even though v2 contains a Double value, the compiler only sees it as Any.
// The fix is to cast v2 to Double:

var v2 :Any = 4.4
var v3 :Double = v2 as Double
```

### (c) 
```kotlin
// var v6 : Int = null
// This doesn't work because Int is a non-nullable type in Kotlin.
// The fix is to make the variable nullable by adding a question mark:

var v6 : Int? = null
``` 

## Exercise 3: Collections in Kotlin

### (a) Data structure for car prices
```kotlin
// For quick access to a car's price using its model name, I should use a HashMap
// HashMap gives fast lookups using keys (O(1) time complexity)

// Creating the HashMap to store car models and their prices
val carPrices = HashMap<String, Int>()

// Adding a car with its price
carPrices["Toyota Corolla"] = 2500000

// Querying the price using the model name
val corollaPrice = carPrices["Toyota Corolla"]
Log.d("CarPrice", "The Toyota Corolla costs $corollaPrice")
```

### (b) List operations
```kotlin
// Creating a list of car names
val car_name_list = mutableListOf<String>()

// Adding 5 different items to the list
car_name_list.add("Toyota")
car_name_list.add("Honda")
car_name_list.add("Ford")
car_name_list.add("BMW")
car_name_list.add("Mercedes")

// Removing the string in the second position (index 1)
car_name_list.removeAt(1)

// Iterating through the list and printing each value
for (carName in car_name_list) {
    Log.d("debug", carName)
}
```

## Exercise 4: Classes in Kotlin

### (a) Car class
```kotlin
// Creating a Car class with name and price attributes
class Car(val name: String, val price: Int) {
    // The constructor parameters automatically become properties
    // because we used 'val' in the constructor
}
```

### (b) User class
```kotlin
// Creating a User class with id, username, and cars attributes
class User(val id: Int) {
    // Username is nullable and initialized as null
    var username: String? = null
    
    // HashMap to store cars, with car name as the key
    val cars = HashMap<String, Car>()
}
```

### (c) Adding a car method
```kotlin
// Adding the addCar method to the User class
class User(val id: Int) {
    var username: String? = null
    val cars = HashMap<String, Car>()
    
    // Method to add a car to the HashMap
    fun addCar(d: Car) {
        cars[d.name] = d
    }
}
```

### (d) Removing a car method
```kotlin
// Adding the removeCar method to the User class
class User(val id: Int) {
    var username: String? = null
    val cars = HashMap<String, Car>()
    
    fun addCar(d: Car) {
        cars[d.name] = d
    }
    
    // Method to remove a car from the HashMap using its name
    fun removeCar(d: String) {
        cars.remove(d)
    }
}
```

### (e) Why Int instead of Double for price
The price is declared as Int instead of Double because we're likely storing the price in cents or the smallest currency unit (like centavos). This avoids floating-point precision issues that can happen with Double when dealing with money. For example, a price of $398,350.00 would be stored as 39835000 (in cents), which is more accurate for calculations than using a Double. 