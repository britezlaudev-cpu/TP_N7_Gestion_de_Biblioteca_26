import java.util.ArrayList;

public class Libro {
    // Atributos
    private String titulo;
    private int edicion;
    private String editorial;
    private int anio;
    private ArrayList<Prestamo> prestamos;

    /**
     * Constructor para crear un nuevo libro con su información básica y una lista de préstamos vacía.
     *
     * @param p_titulo    Título del libro.
     * @param p_edicion   Número de edición del libro.
     * @param p_editorial Editorial a cargo de la publicación.
     * @param p_anio      Año de publicación del libro.
     */
    public Libro(String p_titulo, int p_edicion, String p_editorial, int p_anio)
    {
        this.setTitulo(p_titulo);
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamo(new ArrayList<Prestamo>());
    }

    /**
     * Constructor para crear un libro incluyendo una colección preexistente de préstamos.
     *
     * @param p_titulo     Título del libro.
     * @param p_edicion    Número de edición del libro.
     * @param p_editorial  Editorial a cargo de la publicación.
     * @param p_anio       Año de publicación del libro.
     * @param p_prestamos  Lista inicial de préstamos asociados al libro.
     */
    public Libro(String p_titulo, int p_edicion, String p_editorial, int p_anio, ArrayList<Prestamo> p_prestamos)
    {
        this.setTitulo(p_titulo);
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamo(p_prestamos);
    }

    //Setters
    private void setTitulo(String p_titulo){
        this.titulo = p_titulo;
    }
    private void setEdicion(int p_edicion){
        this.edicion = p_edicion;
    }
    private void setEditorial(String p_editorial){
        this.editorial = p_editorial;
    }
    private void setAnio(int p_anio){
        this.anio = p_anio;
    }
    private void setPrestamo(ArrayList<Prestamo> p_prestamo){
        this.prestamos = p_prestamo;
    }

    // Getters
    public String getTitulo(){
        return this.titulo;
    }
    public int getEdicion(){
        return this.edicion;
    }
    public String getEditorial(){
        return this.editorial;
    }
    public int getAnio(){
        return this.anio;
    }
    public ArrayList<Prestamo> getPrestamo(){
        return this.prestamos;
    }

    /**
     * Agrega un nuevo registro de préstamo a la colección histórica del libro.
     *
     * @param p_prestamo Objeto Prestamo a ser incorporado.
     * @return true si el préstamo fue agregado exitosamente, false en caso contrario.
     */
    public boolean agregarPrestamo(Prestamo p_prestamo){
        return this.getPrestamo().add(p_prestamo);
    }

    /**
     * Remueve un registro de préstamo de la colección del libro.
     *
     * @param p_prestamo Objeto Prestamo que se desea quitar.
     * @return true si el préstamo fue removido, false si no se encontraba en la lista.
     */
    public boolean quitarPrestamo(Prestamo p_prestamo){
        return this.getPrestamo().remove(p_prestamo);
    }

    /**
     * Determina si el libro se encuentra actualmente prestado (es decir, si el último préstamo registrado no tiene fecha de devolución).
     *
     * @return true si el libro está prestado, false si se encuentra en la biblioteca.
     */
    public boolean prestado(){
        Prestamo ultimo = this.ultimoPrestamo();
        return ultimo != null && ultimo.getFechaDevolucion() == null;
    }

    /**
     * Obtiene el último préstamo registrado en el historial del libro.
     *
     * @return El objeto Prestamo más reciente, o null si la lista de préstamos está vacía.
     */
    public Prestamo ultimoPrestamo(){
        if(this.getPrestamo().isEmpty()){
                return null;
        } else 
            return this.getPrestamo().get(this.getPrestamo().size() - 1);
    }

    /**
     * Retorna una representación en formato de cadena de texto del libro, mostrando su título.
     *
     * @return Una cadena con el formato "Titulo: [nombre del libro]".
     */
    public String toString(){            
        return "Titulo: " +this.getTitulo();
    } 
}
