# Solutions in Professor's Style

## Exercise 3: Collections in Kotlin

### (a) Data structure for car prices
```kotlin
// For quick access to a car's price using its model name, we should use a HashMap

// Creating the HashMap to store car models and their prices
val carPrices = HashMap<String, Int>()

// Adding a car with its price
carPrices["Ferrari Purosangue"] = 39835000

// Querying the price using the model name
val ferrariPrice = carPrices["Ferrari Purosangue"]
Log.d("CarPrice", "The Ferrari Purosangue costs $ferrariPrice")
```

### (b) List operations
```kotlin
// Creating a list of car names using ArrayList
val lucky_numbers_list = ArrayList<String>()

// Adding 5 different items to the list
lucky_numbers_list.add("Ferrari")
lucky_numbers_list.add("Lamborghini")
lucky_numbers_list.add("Porsche")
lucky_numbers_list.add("Bugatti")
lucky_numbers_list.add("McLaren")

// Removing the string in the second position (index 1)
lucky_numbers_list.removeAt(1)

// Iterating through the list and printing each value
for ( v in lucky_numbers_list ) {
    Log.d("debug", v)
}
```

## Exercise 4: Classes in Kotlin

### (a) Car class
```kotlin
class Car(pname: String, pprice: Int) {
    var name: String = pname
    var price: Int = pprice
}
```

### (b) User class
```kotlin
class User(pid: Int) {
    var id: Int = pid
    var username: String? = null
    var cars: HashMap <String, Car> = HashMap <String, Car>()
}
```

### (c) Adding a car method
```kotlin
class User(pid: Int) {
    var id: Int = pid
    var username: String? = null
    var cars: HashMap <String, Car> = HashMap <String, Car>()
    
    fun addCar(d: Car){
        cars[d.name] = d
    }
}
```

### (d) Removing a car method
```kotlin
class User(pid: Int) {
    var id: Int = pid
    var username: String? = null
    var cars: HashMap <String, Car> = HashMap <String, Car>()
    
    fun addCar(d: Car){
        cars[d.name] = d
    }
    
    fun removeCar(pname: String){
        cars.remove(pname)
    }
}
```

### (e) Why Int instead of Double for price
We are storing the smallest unit: pennies or cents. This amount no longer needs decimals, so we use Int. We don't use Double because it is a floating-point type, and uncontrolled precision problems can occur.

## Exercise 5: Use classes

```kotlin
var user : User = User ( 18 )
var car : Car = Car ( "Ferrari Purosangue" , 39835000 )
user.addCar ( car )
user.removeCar("Ferrari Purosangue")
``` 