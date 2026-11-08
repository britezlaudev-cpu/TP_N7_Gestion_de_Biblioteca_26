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

**Biblioteca:** 

**Libro:** La clase libro funciona en colaboración con la clase Biblioteca, además de tener una colección del tipo Prestamo.
Tiene dos metodos importantes:
ultimoPrestamo, que es un método que retorna el ultimo de la coleccion.
prestado, que es el método boolean que devuelve true si el ultimo esta actualmente prestado, es decir no contiene fecha de devolucion.
Además, tiene un método toString que devuelve un String con el titulo del libro.

**Prestamo:**

**Socio (clase abstracta):** A través de esta clase podemos conocer la cantidad de libros que tiene un estudiante o docente, si puede pedir o no más libros dependiendo de si la fecha de devolución esta vencida o no, y definimos una clase abstracta que será utilizada por sus clases herederas.

**Docente (clase hija de Socio):**

**Estudiante (clase hija de Socio):**

### 🛠️ Tecnologías y Herramientas

- **Lenguaje:** Java
- **Entorno de Desarrollo:** BlueJ / IDE compatible con Java
- **Conceptos de POO aplicados:**
  
        -Herencia y clases abstractas (Socio, Estudiante, Docente)
  
        -Polimorfismo y ligadura dinámica (puedePedir(), soyDeLaClase())
  
        -Encapsulamiento y diseño guiado por UML
  
        -Colecciones genéricas (ArrayList<Libro>, ArrayList<Socio>, ArrayList<Prestamo>)
  
        -Manejo de excepciones personalizadas (LibroNoPrestadoException)

### 👥Este proyecto fue realizado en equipo
**Equipo de trabajo:**
Clase Libro: Cabrera, Luz Arianna

Clase Préstamo y Socio: Luque, Valentin Nicolas

Clases heredadas Docente y Estudiante: Gomez, Alberto Gaston

Clase Biblioteca: Arce, Federico Hernan

                  Arredo, Araceli Estefany
                  
                  Britez, Lautaro Tomas


Clase GestionBiblioteca: clase desarrollada en conjunto.















