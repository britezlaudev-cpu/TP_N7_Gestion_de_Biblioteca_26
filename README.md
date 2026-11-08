## 🏫📚 Sistema de gestión de Biblioteca
### - Proyecto integrador desarrollado en grupo en JAVA -

Universidad Nacional del Nordeste (UNNE) — FaCENA

Programación Orientada a Objetos — Proyecto Integrador 2026

### 📝 Descripción del Proyecto
**Nuestro proyecto gestiona la administración de prestamos de libros en una Biblioteca.**
Cada libro tiene asociada una lista de prestamos, ademas tambien a cada prestamo se le asocia un Socio de la Biblioteca, que es quien realiza el prestamo.
Los Socios pueden ser de dos tipos: Docente o Estudiante, cada uno con sus condiciones indicadas al momento de adquirir un libro prestado.

### 📄 Informes necesarios para la administración de la Biblioteca
- Cantidad de socios de un determinado tipo.
- Libros prestados, cuyos plazos de devolucion estan actualmente vencidos.
- Que socio tiene en su poder un libro.
- Títulos de la biblioteca.
- Que docentes han devuelto libros antes de que se venza el prestamo (o ese mismo dia de vencimiento).

### 👩‍💻 Desarrollo del Proyecto
**El desarrollo del proyecto se realiza en BlueJ.**

Lo desarrollamos en 6 clases distintas:

**Biblioteca:** La clase Biblioteca tiene dos colecciones de tipo ArrayList, para Socios y Libros.
Los metodos nuevoSocio de Docente y Estudiante crea e incorpora un nuevo socio dependiendo de su tipo a la colección de socios.

Asimismo el metodo nuevoLibro tiene la misma funcionalidad pero con los objetos de tipo Libro.

El metodo prestarLibro es boolean y usa un if else para retornar true si las condiciones para realizar el prestamo se cumplen, y false en caso contrario. Si la devolucion es true, crea un nuevo prestamo y lo agrega a la coleccion.

devolverLibro registra la devolución de un libro que se encuentra actualmente prestado. Ademas, tiene una excepcion que devuelve un mensaje en caso de que el libro no este prestado actualmente.

Existe un contador que cuenta la cantidad de Socios segun su tipo.

prestamosVencidos devuelve un ArrayList con todos los prestamos vencidos actualmente.

docentesResponsables tambien devuelve un ArrayList, pero con los docentes que son considerados como responsables (metodo esResponsable en Docente).

Esta clase ademas tiene dos metodos importantes y complejos que se encargan de buscar el Socio, con su numero de DNI como referencia, y ademas quien tiene un libro especifico, utilizando el titulo del mismo como referencia; tambien utiliza una excepcion en caso de que ese libro no este prestado actualmente.

Por ultimo, la clase Biblioteca se encarga de listar lo siguiente:
 
Libros que se encuentran en la biblioteca. (Y si estan actualmente prestados o no)

Titulos de los libros.

Socios.

Docentes responsables.


**Libro:** La clase libro funciona en colaboración con la clase Biblioteca, además de tener una colección del tipo Prestamo.

Tiene dos metodos importantes:

ultimoPrestamo, que es un método que retorna el ultimo de la coleccion.

prestado, que es el método boolean que devuelve true si el ultimo esta actualmente prestado, es decir no contiene fecha de devolucion.

Además, tiene un método toString que devuelve un String con el titulo del libro.


**Prestamo:** Prestamo es una clase que tiene atriutos de tipo Socio y Libro, y registra las fecha en la que de devuelve el libro adquirido en un prestamo. 

Tiene un metodo llamado vencido, que evalúa si el préstamo se encuentra vencido en función de una fecha dada y de los días de préstamo permitidos según el tipo de socio. Devuelve true si la fecha pasada excede el vencimiento calculado, false en caso contrario.

Su metodo toString imprime las fechas de retiro, de devolucion, el nombre del libro y el socio que lo tiene.


**Socio (clase abstracta):** La clase Socio es abstracta y sirve para moldear la estructura que necesitan los objetos Docente y Estudiante.

Contiene una coleccion de tipo ArrayList donde se guardan los prestamos.

Tiene un metodo cantLibrosPrestados que funciona como un contador de los libros prestados de la Bibioteca.

Ademas, un metodo boolean que devuelve true cuando el socio cumple con las condiciones para realizar un prestamo, y false en el caso contrario.

Su metodo abstracto soyDeLaClase sirve para obtener el tipo de socio en formato String.


**Docente (clase hija de Socio):** Para realizar un prestamo, los docentes deben considerarse "responsables", es decir no deben tener ningun prestamo vencido o pendiente de devolucion.

esResponsable evalua esa condicion devolviendo true o false segun sea.

El metodo puedePedir true si puede realizar un nuevo pedido, false si tiene algún préstamo vencido sin devolver.

El metodo soyDeLaClase devuelve el tipo de socio, es decir devuelve la cadena de texto "Docente".

Ademas, los docentes tienen la posibilidad de cambiar los dias de prestamos, sumando mas dias a su prestamo actual, de esto se encarga el metodo cambiarDiasDePrestamo.


**Estudiante (clase hija de Socio):** Para realizar un prestamo, los estudiantes puede pedir solamente 3 prestamos y ademas, no debe tener ningun prestamo vencido.

El metodo puedePedir de Estudiante se encarga de evaluar estas condiciones devolviendo true si puede realizar un prestamo y false en caso contrario.

El metodo soyDeLaClase devuelve el tipo de socio, es decir devuelve la cadena de texto "Estudiante".

### 🛠️ Tecnologías y Herramientas

- **Lenguaje:** Java
- **Entorno de Desarrollo:** BlueJ
- **Conceptos de POO aplicados:**
  
  -Herencia y clases abstractas (Socio, Estudiante, Docente)
  
  -Polimorfismo y ligadura dinámica (puedePedir(), soyDeLaClase())
  
  -Encapsulamiento y diseño guiado por UML
  
  -Colecciones genéricas (ArrayList)
  
  -Manejo de excepciones personalizadas (LibroNoPrestadoException)

### 👥Este proyecto fue realizado en equipo
**Equipo de trabajo:**

Clase Libro: Cabrera, Luz Arianna

Clase Préstamo y Socio: Luque, Valentin Nicolas

Clases heredadas Docente y Estudiante: Gomez, Alberto Gaston

Clase Biblioteca: Arce, Federico Hernan - 
                  Arredo, Araceli Estefany - 
                  Britez, Lautaro Tomas


Clase GestionBiblioteca: clase desarrollada en conjunto.















