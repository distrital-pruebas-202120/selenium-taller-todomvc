# Taller de creación de pruebas de interfaz

Creación de pruebas de interfaz de usuario sobre la aplicación [To-Do MVC en Javascript](https://todomvc.com/examples/vanilla-es6/) usando unos Page Objects de Selenium.

## Requisitos

Para ejecutar el proyecto es necesario tener instalado
  - Java SDK versión 11
  - Maven
  - Google Chrome
  - [ChromeDriver para Selenium](https://chromedriver.chromium.org/)

## Instalación de ChromeDriver (si no se tiene)

Para instalar ChromeDriver en Windows, es posible usar [Chocolatey](https://community.chocolatey.org/packages/chromedriver)

```
choco install chromedriver
```

## Antes de ejecutar el programa por primera vez

Antes de ejecutar el programa por primera vez, es necesario modificar la clase de Pruebas `EjemploTest.java` para incluir la ruta en donde se instaló el ChromeDriver. Normalmente, el programa funciona sin problema si el ChromeDriver está en el PATH del computador. Si no funciona, es necesario definir la ruta al driver.

```
// en el archivo /src/test/java/tests/EjemploTest.java
// en el renglón 17

    // Para Windows usando ChromeDriver instalado con Chocolatey 
    private String ChromeDriverPath = "C:\\ProgramData\\chocolatey\\lib\\chromedriver\\tools\\chromedriver.exe";

    // o
    // Para WSL usando ChromeDriver instalado en Windows con Chocolatey 
    private String ChromeDriverPath = "/mnt/c/ProgramData/chocolatey/lib/chromedriver/tools/chromedriver.exe";
```

##  Ejecutando el programa

Es posible ejecutar las pruebas usando Maven

```
mvn test
```

## Detalles de los Page Objects

Los Page Objects para ejecutar las pruebas se localizan en la carpeta ``/src/test/java/pageObjects``.
Estas clases son:

| Clase     | Descripción |
|-----------|-------------|
| TodosPage | Representa la pantalla de la aplicación [To-Do MVC](https://todomvc.com/examples/vanilla-es6/) |
| FilaTarea | Representa cada una de las filas de la aplicación, las tareas definidas por el usuario         |


Estas clases se pueden usar para realizar tareas sobre la pantalla.

```
// agrega una tarea
todosPage.agregarTarea("Ejemplo");
// revisa que la tarea esté en la pantalla
Assertions.assertNotNull(todosPage.buscarTarea("Ejemplo"));

// marca como completado
todosPage.buscarTarea("Ejemplo").marcarComoCompletado();
// revisa que aparezca como completado
Assertions.assertTrue(todosPage.buscarTarea("Ejemplo").isCompletado());

// marca como activo de nuevo
todosPage.buscarTarea("Ejemplo").marcarComoActivo();
// revisa que aparezca como no completado
Assertions.assertFalse(todosPage.buscarTarea("Ejemplo").isCompletado());

// hace clic en ver tareas completadas
todosPage.seleccionarVerTareasCompletadas();
// revisa que no aparezca en pantalla
Assertions.assertNull(todosPage.buscarTarea("Ejemplo"));

// hace clic en otras partes de la pantalla
todosPage.seleccionarVerTareasActivas();
todosPage.seleccionarVerTodo();

// modifica la tarea        
todosPage.buscarTarea("Ejemplo").editarTarea("Nombre Modificado");
// revisa que la tarea se modificó
Assertions.assertNull(todosPage.buscarTarea("Ejemplo"));
Assertions.assertNotNull(todosPage.buscarTarea("Nombre Modificado"));

// marca la tarea como completada y elimina todos los completados
todosPage.buscarTarea("Nombre Modificado").marcarComoCompletado();
todosPage.borrarTareasCompletadas();
// Rebvisa que no aparezca en pantalla
Assertions.assertNull(todosPage.buscarTarea("Nombre Modificado"));
```


## Ejercicio

1. Revise la clase de Pruebas ``EjemploTest.java`` e identifique cada uno de los pasos en la prueba ``agregarUnaTarea()``.
2. Cree una nueva clase de Pruebas con el nombre ``PrimerTest.java`` copiando la clase existente ``EjemploTest.java``. Cree un nuevo conjunto de métodos que ejecuten pruebas de unidad sobre la pantalla
  - Si coloca un comentario en ``liberaRecursos()`` podrá ver el navegador al final de cada ejecución de pruebas

Implemente, al menos, 5 pruebas que hagan tareas sobre la pantalla.