import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;

public class Biblioteca {
    // Atributos
    private String nombre;
    private ArrayList<Socio> socios;
    private ArrayList<Libro> libros;

    /**
     * Constructor para crear una biblioteca con un nombre y listas vacías de socios y libros.
     *
     * @param p_nombre Nombre asignado a la biblioteca.
     */
    public Biblioteca(String p_nombre) {
        this.setNombre(p_nombre);
        this.setSocios(new ArrayList<Socio>());
        this.setLibros(new ArrayList<Libro>());
    }

    /**
     * Constructor para crear una biblioteca con un nombre y listas preexistentes de socios y libros.
     *
     * @param p_nombre Nombre asignado a la biblioteca.
     * @param p_socios Colección inicial de socios.
     * @param p_libros Colección inicial de libros.
     */
    public Biblioteca(String p_nombre, ArrayList<Socio> p_socios, ArrayList<Libro> p_libros) {
        this.setNombre(p_nombre);
        this.setSocios(p_socios);
        this.setLibros(p_libros);
    }

    // Setters
    private void setNombre(String p_nombre) {
        this.nombre = p_nombre;
    }
    private void setSocios(ArrayList<Socio> p_socios) {
        this.socios = p_socios;
    }
    private void setLibros(ArrayList<Libro> p_libros) {
        this.libros = p_libros;
    }

    // Getters
    public String getNombre() {
        return this.nombre;
    }
    public ArrayList<Socio> getSocios() {
        return this.socios;
    }
    public ArrayList<Libro> getLibros() {
        return this.libros;
    }

    /**
     * Crea e incorpora un nuevo socio de tipo estudiante a la colección de socios.
     *
     * @param p_dniSocio DNI del estudiante.
     * @param p_nombre   Nombre del estudiante.
     * @param p_carrera  Carrera que cursa el estudiante.
     */
    public void nuevoSocioEstudiante(int p_dniSocio, String p_nombre, String p_carrera) {
        Estudiante nuevoSocio = new Estudiante(p_dniSocio, p_nombre, p_carrera);
        this.getSocios().add(nuevoSocio);
    }

    /**
     * Crea e incorpora un nuevo socio de tipo docente a la colección de socios.
     *
     * @param p_dniSocio DNI del docente.
     * @param p_nombre   Nombre del docente.
     * @param p_area     Área o departamento del docente.
     */
    public void nuevoSocioDocente(int p_dniSocio, String p_nombre, String p_area) {
        Docente unDocente = new Docente(p_dniSocio, p_nombre, p_area);
        this.socios.add(unDocente);
    }

    /**
     * Crea e incorpora un nuevo libro a la colección de la biblioteca.
     *
     * @param p_titulo    Título del libro.
     * @param p_edicion   Número de edición del libro.
     * @param p_editorial Editorial del libro.
     * @param p_anio      Año de publicación.
     */
    public void nuevoLibro(String p_titulo, int p_edicion, String p_editorial, int p_anio) {
        Libro unLibro = new Libro(p_titulo, p_edicion, p_editorial, p_anio);
        this.getLibros().add(unLibro);
    }

    /**
     * Intenta realizar el préstamo de un libro a un socio si este cumple con las condiciones reglamentarias.
     *
     * @param p_fechaRetiro Fecha en la que se efectúa el retiro.
     * @param p_socio       Socio que solicita el libro.
     * @param p_libro       Libro a ser prestado.
     * @return true si el préstamo fue exitoso, false en caso contrario.
     */
    public boolean prestarLibro(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro) {
        if (p_socio.puedePedir()) {
            Prestamo nuevoPrestamo = new Prestamo(p_fechaRetiro, p_libro, p_socio);
            p_socio.addPrestamo(nuevoPrestamo);
            p_libro.agregarPrestamo(nuevoPrestamo);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Registra la devolución de un libro que se encuentra actualmente prestado.
     *
     * @param p_libro Libro que se va a devolver.
     * @throws LibroNoPrestadoException Si el libro no figura como prestado en la biblioteca.
     */
    public void devolverLibro(Libro p_libro) throws LibroNoPrestadoException {
        if (p_libro.prestado()) {
            Prestamo ultimoPrestamo = p_libro.ultimoPrestamo();
            ultimoPrestamo.registrarFechaDevolucion(new GregorianCalendar());
        } else {
            throw new LibroNoPrestadoException("El libro " + p_libro.getTitulo() + " no se puede devolver ya que se encuentra en la biblioteca");
        }
    }

    /**
     * Cuenta la cantidad de socios según su tipo (por ejemplo, "Estudiante" o "Docente").
     *
     * @param p_objeto Tipo de socio a filtrar.
     * @return Cantidad de socios que coinciden con el tipo especificado.
     */
    public int cantidadDeSociosPorTipo(String p_objeto) {
        int cant = 0;
        for (Socio unSocio : this.getSocios()) {
            if (unSocio.soyDeLaClase().equalsIgnoreCase(p_objeto)) {
                cant++;
            }
        }
        return cant;
    }

    /**
     * Obtiene una lista con todos los préstamos que se encuentran vencidos a la fecha actual.
     *
     * @return ArrayList con los préstamos vencidos.
     */
    public ArrayList<Prestamo> prestamosVencidos(){
        ArrayList<Prestamo> listaVencidos = new ArrayList<>();
        Calendar fechaActual = Calendar.getInstance();

        for(Libro unLibro : this.getLibros()){
            Prestamo ultimoPrestamo = unLibro.ultimoPrestamo();
            if(ultimoPrestamo != null && ultimoPrestamo.vencido(fechaActual)){
                listaVencidos.add(ultimoPrestamo);
            }
        }
        return listaVencidos;
    }

    /**
     * Retorna una lista de aquellos docentes que cumplen con la condición de ser responsables.
     *
     * @return ArrayList con los docentes responsables.
     */
    public ArrayList<Docente> docentesResponsables(){
        ArrayList<Docente> listaResponsables = new ArrayList<>();

        for(Socio unSocio : this.getSocios()){
            if(unSocio instanceof Docente unDocente){
                if(unDocente.esResponsable()){
                    listaResponsables.add(unDocente);
                }
            }
        }
        return listaResponsables;
    }

    /**
     * Busca y retorna un socio según su número de DNI dentro de la colección.
     *
     * @param p_dni Número de DNI del socio a buscar.
     * @return El objeto Socio encontrado, o null si no existe.
     */
    public Socio buscarSocio(int p_dni) {
        for (Socio unSocio : this.getSocios()) {
            if (unSocio.getDniSocio() == p_dni) {
                return unSocio;
            }
        }
        return null;
    }

    /**
     * Identifica y retorna el nombre del socio que actualmente tiene prestado un determinado libro.
     *
     * @param p_libro Libro del cual se quiere conocer el responsable del préstamo.
     * @return Nombre del socio que tiene el libro.
     * @throws LibroNoPrestadoException Si el libro no se encuentra prestado actualmente.
     */
    public String quienTieneElLibro(Libro p_libro) throws LibroNoPrestadoException {
        for (Libro unLibro : this.getLibros()) {
            if (p_libro.getTitulo().equalsIgnoreCase(unLibro.getTitulo())) {
                Prestamo unPrestamo = unLibro.ultimoPrestamo();

                // Verificamos si existe un préstamo y si el libro efectivamente está prestado
                if (unPrestamo != null && unLibro.prestado()) {
                    return unPrestamo.getSocio().getNombre();
                }
            }
        }
        // Si no se encontró un préstamo activo, lanzamos la excepción esperada
        throw new LibroNoPrestadoException("El libro " + p_libro.getTitulo() + " se encuentra en la biblioteca");
    }

    /**
     * Genera y retorna un listado completo de socios y estadísticas por tipo, en formato de texto.
     *
     * @return Una cadena de texto formateada con la lista y contadores.
     */
    public String listaDeSocios() {
        StringBuilder lista = new StringBuilder("Lista de Socios:\n");
        int i = 0;
        for(Socio unSocio: this.getSocios()) {
            i++;
            lista.append(i).append(") ").append(unSocio.toString()).append("\n");
        }
        lista.append("**************************************\n");
        lista.append("Cantidad de Socios del tipo Estudiante: ").append(this.cantidadDeSociosPorTipo("Estudiante")).append("\n");
        lista.append("Cantidad de Socios del tipo Docente: ").append(this.cantidadDeSociosPorTipo("Docente")).append("\n");
        lista.append("**************************************\n");
        return lista.toString();
    }

    /**
     * Genera un listado con los títulos únicos de todos los libros de la biblioteca.
     *
     * @return Cadena de texto con los títulos de los libros sin repetir.
     */
    public String listaDeTitulos() {
        StringBuilder t = new StringBuilder("Lista de Títulos:\n");
        ArrayList<String> titulosUnicos = new ArrayList<>();
        for (Libro unLibro : this.getLibros()) {
            if (!titulosUnicos.contains(unLibro.getTitulo())) {
                titulosUnicos.add(unLibro.getTitulo());
                t.append("* ").append(unLibro.getTitulo()).append("\n");
            }
        }
        return t.toString();
    }

    /**
     * Genera un listado completo de todos los libros indicando su estado de préstamo actual.
     *
     * @return Cadena de texto formateada con la información de los libros y si están prestados.
     */
    public String listaDeLibros() {
        StringBuilder z = new StringBuilder();
        int i = 1;

        for (Libro unLibro : this.getLibros()) {
            z.append(i)
                    .append(") ")
                    .append(unLibro.toString())
                    .append(" || Prestado: (")
                    // Operador ternario: si es true devuelve "Sí", si es false devuelve "No"
                    .append(unLibro.prestado() ? "Sí" : "No")
                    .append(")\n");

            i++;
        }
        return z.toString();
    }

    /**
     * Genera un listado en formato de texto de aquellos docentes que son responsables.
     *
     * @return Cadena de texto con la lista de docentes responsables.
     */
    public String listaDeDocentesResponsables(){
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de Docentes Responsables:\n");

        for(Docente unDocente : this.docentesResponsables()){
            sb.append("* ").append(unDocente.toString()).append("\n");
        }
        return sb.toString();
    }
}
