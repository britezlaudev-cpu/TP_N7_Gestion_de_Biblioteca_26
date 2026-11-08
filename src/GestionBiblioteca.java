import java.util.Scanner;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;

/**
 * Clase ejecutable principal para el Sistema de Gestión de Biblioteca.
 * Gestiona el menú de opciones e interactúa con el modelo del TP 7.
 */
public class GestionBiblioteca {
    public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);

                // Inicializamos la biblioteca de prueba

                Biblioteca miBiblioteca = new Biblioteca("Hemeroteca");
        miBiblioteca.nuevoLibro("Java: How to Program", 10, "Pearson", 2017);
        miBiblioteca.nuevoLibro("Cien Años de Soledad", 1, "Editorial Sudamericana", 1967);
        miBiblioteca.nuevoLibro("Clean Code", 1, "Prentice Hall", 2008);
        miBiblioteca.nuevoLibro("El Principito", 2, "Reynal & Hitchcock", 1943);
        miBiblioteca.nuevoLibro("Estructuras de Datos en Java", 4, "McGraw-Hill", 2014);
        miBiblioteca.nuevoLibro("Design Patterns", 1, "Addison-Wesley", 1994);
        miBiblioteca.nuevoLibro("El Aleph", 1, "Editorial Losada", 1949);
        miBiblioteca.nuevoLibro("Ingeniería de Software", 9, "Pearson", 2011);
        miBiblioteca.nuevoSocioEstudiante(40111222, "Juan Pérez", "Licenciatura en Sistemas");
        miBiblioteca.nuevoSocioEstudiante(41333444, "María Gómez", "Tecnicatura en Programación");
        miBiblioteca.nuevoSocioEstudiante(42555666, "Carlos Rodríguez", "Licenciatura en Sistemas");
        miBiblioteca.nuevoSocioDocente(20777888, "Dr. Roberto Martínez", "Departamento de Computación");
        miBiblioteca.nuevoSocioDocente(22999000, "Dra. Ana López", "Departamento de Matemáticas");

                int opcion = 0;

                do {
                System.out.print("""
                            ===============================================================
                                      SISTEMA DE GESTIÓN DE BIBLIOTECA
                            ===============================================================
                            ALTAS                      INFORMES Y LISTADOS
                            [1] Socio Estudiante       [6] Listado Socios (Formato 1)
                            [2] Socio Docente          [7] Listado Libros (Formato 2)
                            [3] Registrar Libro        [8] Listado de Títulos
                                                       [9] Docentes Responsables
                           TRANSACCIONES               [10] Préstamos Vencidos
                           [4] Prestar Libro
                           [5] Devolver Libro          CONSULTAS Y CONFIGURACIÓN
                                                       [11] Consultar Titular de Libro
                                                       [12] Modificar Días (Docente)
                           ---------------------------------------------------------------
                           [13] Salir
                            ===============================================================
                            Seleccione una opción (1-13):""");

                    if (scanner.hasNextInt()) {
                        opcion = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer
                    } else {
                        System.out.println("Error: Por favor, ingrese un número válido.");
                        scanner.nextLine();
                        continue;
                    }

                    switch (opcion) {
                        case 1:
                            System.out.print("Ingrese DNI del estudiante: ");
                            int dniEst = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Ingrese nombre del estudiante: ");
                            String nomEst = scanner.nextLine();
                            System.out.print("Ingrese carrera: ");
                            String carrera = scanner.nextLine();

                            miBiblioteca.nuevoSocioEstudiante(dniEst, nomEst, carrera);
                            System.out.println("¡Socio estudiante registrado con éxito!");
                            break;

                        case 2:
                            System.out.print("Ingrese DNI del docente: ");
                            int dniDoc = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Ingrese nombre del docente: ");
                            String nomDoc = scanner.nextLine();
                            System.out.print("Ingrese área: ");
                            String area = scanner.nextLine();

                            miBiblioteca.nuevoSocioDocente(dniDoc, nomDoc, area);
                            System.out.println("¡Socio docente registrado con éxito!");
                            break;

                        case 3:
                            System.out.print("Ingrese título del libro: ");
                            String titulo = scanner.nextLine();
                            System.out.print("Ingrese edición: ");
                            int edicion = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Ingrese editorial: ");
                            String editorial = scanner.nextLine();
                            System.out.print("Ingrese año de publicación: ");
                            int anio = scanner.nextInt();
                            scanner.nextLine();

                            miBiblioteca.nuevoLibro(titulo, edicion, editorial, anio);
                            System.out.println("¡Libro registrado con éxito!");
                            break;

                        case 4:
                            System.out.print("Ingrese DNI del socio que solicita el libro: ");
                            int dniSocioPrest = scanner.nextInt();
                            scanner.nextLine();
                            Socio socioEncontrado = miBiblioteca.buscarSocio(dniSocioPrest);

                            if (socioEncontrado != null) {
                                System.out.print("Ingrese título del libro a prestar: ");
                                String titLibroPrest = scanner.nextLine();
                                Libro libroEncontrado = buscarLibroEnBiblioteca(miBiblioteca, titLibroPrest);

                                if (libroEncontrado != null) {
                                    Calendar fechaRetiro = new GregorianCalendar(); // Fecha actual
                                    boolean exito = miBiblioteca.prestarLibro(fechaRetiro, socioEncontrado, libroEncontrado);
                                    if (exito) {
                                        System.out.println("¡Préstamo realizado con éxito!");
                                    } else {
                                        System.out.println("El socio no cumple con las condiciones para pedir un libro.");
                                    }
                                } else {
                                    System.out.println("Libro no encontrado.");
                                }
                            } else {
                                System.out.println("Socio no encontrado.");
                            }
                            break;

                        case 5:
                            System.out.print("Ingrese el título del libro a devolver: ");
                            String titDevolucion = scanner.nextLine();
                            Libro libroDev = buscarLibroEnBiblioteca(miBiblioteca, titDevolucion);

                            if (libroDev != null) {
                                try {
                                    miBiblioteca.devolverLibro(libroDev);
                                    System.out.println("¡Devolución registrada con éxito!");
                                } catch (LibroNoPrestadoException e) {
                                    System.out.println(e.getMessage());
                                }
                            } else {
                                System.out.println("Libro no encontrado en la biblioteca.");
                            }
                            break;

                        case 6:
                            System.out.println("\n" + miBiblioteca.listaDeSocios());
                            break;

                        case 7:
                            System.out.println("\n--- LISTA DE LIBROS ---");
                            System.out.println(miBiblioteca.listaDeLibros());
                            break;

                        case 8:
                            System.out.println(miBiblioteca.listaDeTitulos());
                            break;

                        case 9:
                            System.out.println("\n" + miBiblioteca.listaDeDocentesResponsables());

                            break;

                        case 10:
                             System.out.println("\n--- PRÉSTAMOS VENCIDOS ---");
                             ArrayList<Prestamo> vencidos = miBiblioteca.prestamosVencidos();
                             if (vencidos.isEmpty()) {
                             System.out.println("No hay préstamos vencidos actualmente.");
                             } else {
                             for (Prestamo p : vencidos) {
                             System.out.println("- Libro: " + p.getLibro().toString() + " | Socio: " + p.getSocio().toString());
                             }
                             }
                            break;

                        case 11:
                            System.out.println("=== CONSULTAR TITULAR DE LIBRO ===");
                            System.out.print("Ingrese el título del libro para consultar quién lo tiene: ");
                            String tituloConsulta = scanner.nextLine();


                            Libro libroAveriguar = buscarLibroEnBiblioteca(miBiblioteca, tituloConsulta);

                            if (libroAveriguar != null) {
                                try {

                                    String nombreSocio = miBiblioteca.quienTieneElLibro(libroAveriguar);
                                    System.out.println("El libro lo tiene actualmente: " + nombreSocio);
                                } catch (LibroNoPrestadoException e) {
                                    // 3. Capturamos la excepción si el libro no está prestado
                                    System.out.println(e.getMessage());
                                }
                            } else {
                                System.out.println("El libro ingresado no existe en la biblioteca.");
                            }
                            break;

                        case 12:
                            System.out.println("=== MODIFICAR DIAS DE PRESTAMO (DOCENTE) ===");
                            System.out.print("Ingrese el DNI del docente: ");
                            int dniDocente = Integer.parseInt(scanner.nextLine());

                            Socio socioBusqueda = miBiblioteca.buscarSocio(dniDocente);

                            if (socioBusqueda == null) {
                                System.out.println("Error: No existe ningun socio registrado con el DNI " + dniDocente);
                            } else if (socioBusqueda instanceof Docente docente) {
                                System.out.print("Ingrese la cantidad de dias adicionales a otorgar: ");
                                int diasMas = Integer.parseInt(scanner.nextLine());

                                docente.cambiarDiasDePrestamo(diasMas);
                                System.out.println("Se agregaron " + diasMas + " dias con exito!");
                                System.out.println("El docente " + docente.getNombre() + " ahora cuenta con " + docente.getDiasPrestamo() + " dias de prestamo en total.");
                            } else {
                                System.out.println("El socio ingresado (" + socioBusqueda.getNombre() + ") es de tipo " + socioBusqueda.soyDeLaClase() + ". Solo los docentes pueden recibir dias adicionales de prestamo.");
                            }
                            break;

                        case 0:
                            System.out.println("Saliendo del sistema. ¡Hasta luego!");
                            break;

                        default:
                            System.out.println("Opción inválida. Intente nuevamente.");
                    }

                } while (opcion != 0);

                scanner.close();
            }
    private static Libro buscarLibroEnBiblioteca(Biblioteca p_biblioteca, String p_titulo) {
        for (Libro unLibro : p_biblioteca.getLibros()) {
            if (unLibro.getTitulo().equalsIgnoreCase(p_titulo)) {
                return unLibro; // Retorna el libro apenas lo encuentra
            }
        }
        return null; // Retorna null si no lo encuentra en toda la lista
    }
        }
