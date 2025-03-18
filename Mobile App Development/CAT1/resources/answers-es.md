# Respuestas del Examen CAT1 de Desarrollo Android

## Ejercicio 1: Niveles de API

### (a) Gráficos vectoriales animados
Los gráficos vectoriales animados están disponibles desde el **nivel de API 21** (Android 5.0 Lollipop). Fue cuando Android añadió la clase AnimatedVectorDrawable que permite a los desarrolladores crear gráficos animados que se escalan sin perder calidad.

### (b) Aplicaciones a pantalla completa
El modo de pantalla completa con la capacidad de ocultar las barras del sistema se introdujo en el **nivel de API 19** (Android 4.4 KitKat). Esta función se llama "modo inmersivo" y permite a las aplicaciones ocultar tanto la barra de navegación como la barra de estado para una mejor experiencia visual.

## Ejercicio 2: Fundamentos de Kotlin

### (a) 
```kotlin
// var v4 : Int = 4
// var v5 :Double = v4;
// Esto no funciona porque no podemos asignar directamente un Int a una variable Double.
// La solución es convertir el Int a Double:

var v4 : Int = 4
var v5 :Double = v4.toDouble()
```

### (b) 
```kotlin
// var v2 :Any = 4.4
// var v3 :Double = v2
// Esto no funciona porque v2 es de tipo Any, que es un tipo general.
// Aunque v2 contiene un valor Double, el compilador solo lo ve como Any.
// La solución es convertir v2 a Double:

var v2 :Any = 4.4
var v3 :Double = v2 as Double
```

### (c) 
```kotlin
// var v6 : Int = null
// Esto no funciona porque Int es un tipo no nulable en Kotlin.
// La solución es hacer que la variable sea nulable añadiendo un signo de interrogación:

var v6 : Int? = null
``` 

## Ejercicio 3: Colecciones en Kotlin

### (a) Estructura de datos para precios de coches
```kotlin
// Para acceder rápidamente al precio de un coche usando su nombre de modelo, debería usar un HashMap
// HashMap proporciona búsquedas rápidas usando claves (complejidad temporal O(1))

// Creando el HashMap para almacenar modelos de coches y sus precios
val preciosCoches = HashMap<String, Int>()

// Añadiendo un coche con su precio
preciosCoches["Toyota Corolla"] = 2500000

// Consultando el precio usando el nombre del modelo
val precioCorolla = preciosCoches["Toyota Corolla"]
Log.d("PrecioCoche", "El Toyota Corolla cuesta $precioCorolla")
```

### (b) Operaciones con listas
```kotlin
// Creando una lista de nombres de coches
val lista_nombres_coches = mutableListOf<String>()

// Añadiendo 5 elementos diferentes a la lista
lista_nombres_coches.add("Toyota")
lista_nombres_coches.add("Honda")
lista_nombres_coches.add("Ford")
lista_nombres_coches.add("BMW")
lista_nombres_coches.add("Mercedes")

// Eliminando la cadena en la segunda posición (índice 1)
lista_nombres_coches.removeAt(1)

// Recorriendo la lista e imprimiendo cada valor
for (nombreCoche in lista_nombres_coches) {
    Log.d("debug", nombreCoche)
}
```

## Ejercicio 4: Clases en Kotlin

### (a) Clase Coche
```kotlin
// Creando una clase Coche con atributos nombre y precio
class Coche(val nombre: String, val precio: Int) {
    // Los parámetros del constructor automáticamente se convierten en propiedades
    // porque usamos 'val' en el constructor
}
```

### (b) Clase Usuario
```kotlin
// Creando una clase Usuario con atributos id, nombreUsuario y coches
class Usuario(val id: Int) {
    // nombreUsuario es nulable e inicializado como null
    var nombreUsuario: String? = null
    
    // HashMap para almacenar coches, con el nombre del coche como clave
    val coches = HashMap<String, Coche>()
}
```

### (c) Método para añadir un coche
```kotlin
// Añadiendo el método añadirCoche a la clase Usuario
class Usuario(val id: Int) {
    var nombreUsuario: String? = null
    val coches = HashMap<String, Coche>()
    
    // Método para añadir un coche al HashMap
    fun añadirCoche(d: Coche) {
        coches[d.nombre] = d
    }
}
```

### (d) Método para eliminar un coche
```kotlin
// Añadiendo el método eliminarCoche a la clase Usuario
class Usuario(val id: Int) {
    var nombreUsuario: String? = null
    val coches = HashMap<String, Coche>()
    
    fun añadirCoche(d: Coche) {
        coches[d.nombre] = d
    }
    
    // Método para eliminar un coche del HashMap usando su nombre
    fun eliminarCoche(d: String) {
        coches.remove(d)
    }
}
```

### (e) Por qué Int en lugar de Double para el precio
El precio se declara como Int en lugar de Double porque probablemente estamos almacenando el precio en céntimos o la unidad monetaria más pequeña. Esto evita problemas de precisión de punto flotante que pueden ocurrir con Double cuando se trata de dinero. Por ejemplo, un precio de $398,350.00 se almacenaría como 39835000 (en céntimos), lo que es más preciso para cálculos que usar un Double.