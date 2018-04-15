# Call Center
Ejercicio de admisión para almundo


## Enunciado:

El objetivo de este ejercicio es conocer cómo los candidatos a entrar a
almundo.com usan herramientas básicas de Java y diseñan soluciones
orientadas a objetos.

### Forma de entrega:
      
- [x] La solución tiene que estar pusheada en un repo git. La url del mismo tiene
que ser enviada por email. Como alternativa a git, la solución puede ser
adjuntada (.tar, .zip, etc).
   * La URL del repositorio en git es: https://github.com/leonardo2rms/callcenter.git, existen 2 branchs, 
   en **master** pueden ver la solución y en **develop** pueden ver la solución y  el histórico de commits.

### Consigna:

Existe un call center donde hay 3 tipos de empleados: operador, supervisor
y director. El proceso de la atención de una llamada telefónica en primera
instancia debe ser atendida por un operador, si no hay ninguno libre debe
ser atendida por un supervisor, y de no haber tampoco supervisores libres
debe ser atendida por un director.

### Requerimientos:

- [x]  Debe existir una clase Dispatcher encargada de manejar las
llamadas, y debe contener el método dispatchCall para que las
asigne a los empleados disponibles.
El método dispatchCall puede invocarse por varios hilos al mismo
tiempo.
    * Se creo la clase `Dispatcher` con el método `dispatchCall(Llamada llamada)` que recibe un objeto de tipo `Llamada`
    y  dentro de este método se busca en el blockingqueue de empleados el primer empleado disponible, si no hay ninguno
    se bloquea el `Thread` esperando a que encuentre un empleado disponible. El Queue esta priorizado por tipo de Empleado, primero los 
    **operadores**, luego los **supervisores** y de ultimo los **directores**.

- [x] La clase Dispatcher debe tener la capacidad de poder procesar 10
llamadas al mismo tiempo (de modo concurrente).
    * Se utilizo un `ExecutorService` con `Executors.newFixedThreadPool(cantidadLlamadasSimultaneas)` para solucionar esta problemática.
- [x] Cada llamada puede durar un tiempo aleatorio entre 5 y 10
segundos.
    * En la clase `Util` se creo un método que dado un tiempo mínimo y un tiempo máximo retorna una duración aleatoria de la llamada entre
    esos dos valores.
- [x] Debe tener un test unitario donde lleguen 10 llamadas.
    * En la clase test `DispatcherTest` se encuentra este test con el nombre `shouldMakeTenSimultaneousCalls`.

### Extras/Plus:

- [x] Dar alguna solución sobre qué pasa con una llamada cuando no hay
ningún empleado libre.
    * Utilicé un `PriorityBlockingQueue<Empleado>` priorizando a los empleados por el atributo `tipoEmpleado`, siendo prioritarios
    los **operadores**, luego los **supervisores** y de ultimo los **directores**. En caso de que no exista un empleado disponible, el `Thread` se bloquea
    hasta que exista un empleado en el Queue.
- [x] Dar alguna solución sobre qué pasa con una llamada cuando entran
más de 10 llamadas concurrentes.
    * Al usar un `ExecutorService` con `Executors.newFixedThreadPool(cantidadLlamadasSimultaneas)` me aseguro de que cuando entren más de 10
    llamadas al mismo tiempo, el executor sea el encargado de ver cuando se desocupa un `Thread` para asignarle la tarea, la solución fue dejar
    la llamada en espera mientras se desocupa un `Tread`.
- [x] Agregar los tests unitarios que se crean convenientes.
    * Se agregaron los Test en el paquete: callcenter/src/test/java/com/almundo/callcenter
- [x] Agregar documentación de código.

### Tener en Cuenta:

- [x] El proyecto debe ser creado con Maven.
De ser necesario, anexar un documento con la explicación del cómo
y porqué resolvió los puntos extras, o comentarlo en las clases
donde se encuentran sus tests unitarios respectivos.
