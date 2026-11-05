//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Calendar;
public class TestMain {
    public static void main(String[] args) {
        Biblioteca miBiblioteca = new Biblioteca("Biblioteca Central UNNE");
        Libro libro1 = new Libro("JAVA. Cómo Programar", 101, "Pearson", 2021);
        Libro libro2 = new Libro("Estructuras de Datos y Algoritmos", 102, "McGraw-Hill", 2019);
        Libro libro3 = new Libro("Patrones de Diseño", 103, "Addison-Wesley", 2020);
        miBiblioteca.nuevoSocioEstudiante(40123456, "María López", "Licenciatura en Sistemas");
        miBiblioteca.nuevoSocioEstudiante(41987654, "Carlos Gómez", "Ingeniería en Agrimensura");
        miBiblioteca.nuevoSocioEstudiante(25111222, "Roberto Martínez", "Programación");

        System.out.println("-&gt; Socios dados de alta correctamente.");
        System.out.println("\\n==================================================");
        System.out.println("2. PRUEBA DE: buscarSocio(dni)");
        System.out.println("==================================================");

        Socio socioEncontrado = miBiblioteca.buscarSocio(40123456);
        if (socioEncontrado != null) {
            System.out.println("-&gt; Socio encontrado: " + socioEncontrado.toString());
        } else {
            System.out.println("-&gt; Socio no encontrado.");
        }
        Socio socioInexistente = miBiblioteca.buscarSocio(99999999);
        System.out.println("-&gt; Búsqueda de DNI inexistente (debe dar null): " + socioInexistente);

        System.out.println("n==================================================");
        System.out.println("3. PRUEBA DE: prestarLibro(...)");
        System.out.println("==================================================");
        Calendar fechaHoy = Calendar.getInstance();
        boolean ok1 = miBiblioteca.prestarLibro(fechaHoy, socioEncontrado, libro1);
        System.out.println("-&gt; Préstamo de '" + libro1.getTitulo() + "' a María López: " + (ok1 ? "EXITOSO" : "RECHAZADO"));
        Socio docente = miBiblioteca.buscarSocio(25111222);
        boolean ok2 = miBiblioteca.prestarLibro(fechaHoy, docente, libro2);
        System.out.println("-&gt; Préstamo de '" + libro2.getTitulo() + "' al Dr. Martínez: " + (ok2 ? "EXITOSO" : "RECHAZADO"));
        System.out.println("n==================================================");
        System.out.println("4. PRUEBA DE: listaDeSocios() [Incluye cantidadDeSociosPorTipo]");
        System.out.println("=================================================="); // Imprime el informe generado en String
        String informe = miBiblioteca.listaDeSocios();
        System.out.println(informe);
        System.out.println("==================================================");
        System.out.println("5. PRUEBA DE: devolverLibro(...) Y MANEJO DE EXCEPCIONES");
        System.out.println("==================================================");
        System.out.println("--- Caso A: Devolver un libro que SÍ está prestado ---");
        try {
            miBiblioteca.devolverLibro(libro1);
            System.out.println("-&gt; ¡Éxito! El libro '" + libro1.getTitulo() + "' fue devuelto a la biblioteca.");
        } catch (LibroNoPrestadoException e) {
            System.out.println("-&gt; Error inesperado: " + e.getMessage());
        }
        System.out.println("n--- Caso B: Intentar devolver un libro que NO está prestado ---");
        try {
            miBiblioteca.devolverLibro(libro3); // Debería saltar al catch
            System.out.println("-&gt; El libro fue devuelto.");
        } catch (LibroNoPrestadoException e) {
            System.out.println("-&gt; Excepción capturada correctamente en el bloque catch:");
            System.out.println(" Mensaje: + e.getMessage() + ");
        }
    }
}