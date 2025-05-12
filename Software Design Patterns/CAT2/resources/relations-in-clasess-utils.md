# Classes Utils

## Tipos de Relaciones en Diagramas de Clases UML

### 1. Asociación

- **Qué es**: Una relación simple entre dos clases donde una clase "conoce" a otra.
- **Representación**: Se dibuja como una línea sólida entre dos clases.
- **Ejemplo**: Una clase `Profesor` tiene una asociación con la clase `Estudiante` porque un profesor conoce a sus estudiantes.
- **En código**: Se implementa generalmente como un atributo de una clase que referencia a la otra.

```plaintext
Profesor ------- Estudiante
```

### 2. Generalización (Herencia)

- **Qué es**: Una relación donde una clase (subclase) hereda propiedades y comportamientos de otra clase (superclase).
- **Representación**: Se dibuja como una línea con una flecha triangular vacía apuntando a la superclase.
- **Ejemplo**: Una clase `Gato` es una generalización de la clase `Animal` porque un gato es un tipo de animal.
- **En código**: Se implementa con la palabra clave `extends` en Java o herencia en otros lenguajes.

```plaintext
      Animal
        ▲
        |
       Gato
```

### 3. Realización (Implementación)

- **Qué es**: Una relación donde una clase implementa los métodos definidos en una interfaz.
- **Representación**: Se dibuja como una línea punteada con una flecha triangular vacía hacia la interfaz.
- **Ejemplo**: Una clase `Pájaro` realiza la interfaz `Volador` porque implementa el método `volar()`.
- **En código**: Se implementa con la palabra clave `implements` en Java o similar en otros lenguajes.

```plaintext
     Volador
       ▲
       :
     Pájaro
```

### 4. Dependencia

- **Qué es**: Una relación donde una clase usa otra momentáneamente pero no la mantiene.
- **Representación**: Se dibuja como una línea punteada con una flecha simple.
- **Ejemplo**: Un `Constructor` depende de `Material` porque lo usa para construir algo.
- **En código**: Generalmente aparece como parámetro en métodos, variables locales, o llamadas a métodos estáticos.

```plaintext
Constructor - - -> Material
```

### 5. Agregación

- **Qué es**: Una relación "tiene un" donde un objeto puede existir independientemente del contenedor.
- **Representación**: Se dibuja como una línea con un diamante vacío en el lado del contenedor.
- **Ejemplo**: Un `Equipo` tiene `Jugadores`, pero los jugadores pueden existir sin el equipo.
- **En código**: Similar a la asociación, generalmente como un atributo que es una colección.

```plaintext
Equipo ◇───── Jugadores
```

### 6. Composición

- **Qué es**: Una relación "contiene" más fuerte donde la parte no puede existir sin el todo.
- **Representación**: Se dibuja como una línea con un diamante relleno en el lado del contenedor.
- **Representación**: Se dibuja como una línea con un diamante negro (lleno) en el lado del contenedor.
- **Ejemplo**: Un `Coche` contiene un `Motor` y si el coche se destruye, el motor también.
- **En código**: El objeto contenedor es responsable de crear y destruir las partes.

```plaintext
Coche ◆───── Motor
```

### Multiplicidades en las relaciones

Además de los tipos de relaciones, es importante entender las multiplicidades:

- **1**: Exactamente uno
- **0..1**: Cero o uno
- **\***: Muchos (cero o más)
- **1..\***: Uno o más
- **n..m**: De n a m (por ejemplo, 2..4 significa de dos a cuatro)

Las multiplicidades se colocan en los extremos de las líneas de relación para indicar cuántas instancias de una clase están relacionadas con una instancia de la otra clase.

```plaintext
Profesor 1───────* Estudiante
(Un profesor tiene muchos estudiantes)
```
