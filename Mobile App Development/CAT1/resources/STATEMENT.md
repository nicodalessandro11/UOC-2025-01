# Mobile App Development Continuous assessment Test 1 – CAT1


## Problem statement

1.	API levels (Weight: 20%)

Each Android API level offer different sets of features, with more recent API levels offering more capabilities. Explain from which API level it is possible to perform the following:

(a)	Animated vector graphics.
(b)	Full-screen applications.

In order to solve the remaining exercises of the CAT, we will need to create an Android app.

- First of all, we will need to install Android Studio and then create an app (using the menu option File/New/New Project or New Project from the starting dialog) of type Empty Views Activity.

- We can use the following settings for this project:

    - Using the Device Manager, create a device of type Pixel 7, API level 33 and with Google API. Test the app you have created on this device, as you will be using it to solve the remaining exercises.


TODO: Complete this section

### 2.	Kotlin fundamentals (Weight: 20%)

In this exercise, we will look at simple errors in Kotlin code and how they can be fixed. 
Find the class `MainActivity` in the Empty Views Activity application we just created.
There, look for method `override fun onCreate(savedInstanceState: Bundle?)`. Position yourself at the end of this method.

Then, copy each of the following (incorrect) code snippets one by one. For each one, explain why they do not work and propose a fix that solves the problem. You can leave the erroneous code commented, adding your explanation within the comment. You can only add the symbol ‘?’ to the left side of the assignment statement if you need it. You must do the other modifications on the right side. In some cases, there may be more than one correct answer. You should choose the one that has the lowest CPU cost. The ”as” cast is the lowest cost operator because it does nothing at runtime. The ”as” operator only acts at compile time.

(a) 	
```var v4 : Int = 4
var v5 :Double = v4;
```

(b) 	

```
var v2 :Any = 4.4
var v3 :Double = v2
```
(c) 	

```
var v6 : Int = null
```

TODO: Complete this section

### 3.	Collections in Kotlin (Weight: 20%)

In this exercise, we will solve simple tasks using Kotlin collections (again in the context of the `MainActivity`).

- (a)	We want to access a car’s price as quickly as possible using its model name. What data structure should we use? Then, add a car with its price to the structure and query its price using the model name.
- (b)	Create a list of String called `car_name_list`. Add 5 different items to the list and then remove the string in the second position. Finally, iterate through the list and print the value for each position in the Logcat window using `Log.d("debug",v).`


TODO: Complete this section

 
### 4.	Classes in Kotlin (Weight: 30%)

In this exercise, we will create and extend Kotlin classes (in the context of the app we
created).

 
- (a)	Create a class called Car. It will include an attribute name of type string and an attribute price of type Int. Its constructor will have two parameters called pname and pprice that will initialize the previously mentioned attributes.

- (b)	Create a class called User. It will include an integer attribute called id and a string attribute called username. Its constructor will have a parameter called pid that will initialize the previously mentioned id attribute and username as null. Moreover, this class will have an attribute called cars of type HashMap<String,Car>. The HashMap key is the Car name.

- (c)	In class User, add a method with signature `fun addCar(d:Car)` that will allow adding its parameter `d` to the attribute of type `HashMap<String,Car>`.

- (d)	In class User, add a method with signature `fun removeCar(d:String)` that will allow removing the Car with name `d` from the attribute of type `HasMap cars`.

- (e)	Why has the price been declared as has the price been declared as type Int in the class Car instead of type double? Hint: What units are we storing?


TODO: Complete this section

 
### 5.	Use classes (Weight: 10%)

Going back to the `MainActivity` of our app, perform the following tasks:

- (a)	Create a User with id 18.

- (b)	Create a Car with name `Ferrari Purosangue` and price `39835000`.

- (c)	Add the Car to the information about the user.

- (d)	Remove the previous `Car` from the information about the user.


TODO: Complete this section
