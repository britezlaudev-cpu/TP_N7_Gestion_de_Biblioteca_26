import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;

public class Biblioteca {
    private String nombre;
    private ArrayList<Socio> socios;
    private ArrayList<Libro> libros;

    public Biblioteca(String p_nombre) {
        this.setNombre(p_nombre);
        this.setSocios(new ArrayList<Socio>());
        this.setLibros(new ArrayList<Libro>());
    }

    public Biblioteca(String p_nombre, ArrayList<Socio> p_socios, ArrayList<Libro> p_libros) {
        this.setNombre(p_nombre);
        this.setSocios(p_socios);
        this.setLibros(p_libros);
    }

    private void setNombre(String p_nombre) {
        this.nombre = p_nombre;
    }

    private void setSocios(ArrayList<Socio> p_socios) {
        this.socios = p_socios;
    }

    private void setLibros(ArrayList<Libro> p_libros) {
        this.libros = p_libros;
    }

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
     * @param p_dniSocio DNI del estudiante
     * @param p_nombre   nombre del estudiante
     * @param p_carrera  carrera que cursa el estudiante
     */
    public void nuevoSocioEstudiante(int p_dniSocio, String p_nombre, String p_carrera) {
        Estudiante nuevoSocio = new Estudiante(p_dniSocio, p_nombre, p_carrera);
        this.getSocios().add(nuevoSocio);
    }

    public void nuevoSocioDocente(int p_dniSocio, String p_nombre, String p_area) {
        Docente unDocente = new Docente(p_dniSocio, p_nombre, p_area);
        this.socios.add(unDocente);
    }

    public void nuevoLibro(String p_titulo, int p_edicion, String p_editorial, int p_anio) {
        Libro unLibro = new Libro(p_titulo, p_edicion, p_editorial, p_anio);
        this.getLibros().add(unLibro);
    }

    /**
     * Intenta realizar el préstamo de un libro a un socio si este cumple con las condiciones reglamentarias.
     *
     * @param p_fechaRetiro fecha en la que se efectúa el retiro
     * @param p_socio       socio que solicita el libro
     * @param p_libro       libro a ser prestado
     * @return true si el préstamo fue exitoso, false en caso contrario
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
     * @param p_libro libro que se va a devolver
     * @throws LibroNoPrestadoException si el libro no figura como prestado en la biblioteca
     */
    public void devolverLibro(Libro p_libro) throws LibroNoPrestadoException {
        if (p_libro.prestado()) {
            Prestamo ultimoPrestamo = p_libro.ultimoPrestamo();
            ultimoPrestamo.registrarFechaDevolucion(new GregorianCalendar());
        } else {
            throw new LibroNoPrestadoException("El libro " + p_libro.getTitulo() + " no se puede devolver ya que se encuentra en la biblioteca");
        }
    }

    public int cantidadDeSociosPorTipo(String p_objeto) {
        int cant = 0;
        for (Socio unSocio : this.getSocios()) {
            if (unSocio.soyDeLaClase().equalsIgnoreCase(p_objeto)) {
                cant++;
            }
        }
        return cant;
    }

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
     * @param p_dni número de DNI del socio a buscar
     * @return el objeto Socio encontrado, o null si no existe
     */
    public Socio buscarSocio(int p_dni) {
        for (Socio unSocio : this.getSocios()) {
            if (unSocio.getDniSocio() == p_dni) {
                return unSocio;
            }
        }
        return null;
    }



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
     * Genera y retorna un listado completo de socios y estadísticas por tipo, en formato de texto .
     *
     * @return una cadena de texto formateada con la lista y contadores
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

    public String listaDeDocentesResponsables(){
        //ArrayList<Docente> responsables = this.docentesResponsables();
        //String listado = "Lista de Docentes Responsables:\n";
        //for (Docente unDocente : responsables) {
        //    listado += "* " + unDocente.toString() + "\n";
        //}
        //return listado; ya bastaaaa geminiiii

        StringBuilder sb = new StringBuilder();
        sb.append("Lista de Docentes Responsables:\n");

        for(Docente unDocente : this.docentesResponsables()){
            sb.append("* ").append(unDocente.toString()).append("\n");
        }
        return sb.toString();
    }
}
