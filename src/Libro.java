import java.util.ArrayList;
/**
 * En esta clase modelamos la clase Libro para usarla en la gestion de la clase Biblioteca.
 * 
 * @author () 
 * @version (2026)
 */
public class Libro
{
    //Declaracion de atributos
    private String titulo;
    private int edicion;
    private String editorial;
    private int anio;
    private ArrayList<Prestamo> prestamos;
    
    /**
     * Constructor principal de Libro (inicializa)
     */
    public Libro(String p_titulo, int p_edicion, String p_editorial, int p_anio)
    {
        this.setTitulo(p_titulo);
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamo(new ArrayList<Prestamo>());
    }
    
    public Libro(String p_titulo, int p_edicion, String p_editorial, int p_anio, ArrayList<Prestamo> p_prestamos)
    {
        this.setTitulo(p_titulo);
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamo(p_prestamos);
    }

    //Setters y Getters
    private void setTitulo(String p_titulo){
        this.titulo = p_titulo;
    }
    
    public String getTitulo(){
        return this.titulo;
    }
    
    private void setEdicion(int p_edicion){
        this.edicion = p_edicion;
    }
    
    public int getEdicion(){
        return this.edicion;
    }
    
    private void setEditorial(String p_editorial){
        this.editorial = p_editorial;
    }
    
    public String getEditorial(){
        return this.editorial;
    }
    
    private void setAnio(int p_anio){
        this.anio = p_anio;
    }
    
    public int getAnio(){
        return this.anio;
    }
    
    private void setPrestamo(ArrayList<Prestamo> p_prestamo){
        this.prestamos = p_prestamo;
    }
    
    public ArrayList<Prestamo> getPrestamo(){
        return this.prestamos;
    }
    
    /**
     * Metodo que agrega un prestamo pasado por parametro al ArrayList 
     */
    public boolean agregarPrestamo(Prestamo p_prestamo){
        return this.getPrestamo().add(p_prestamo);
    }
    
    /**
     * Metodo que elimina un prestamo pasado por parametro al ArrayList 
     */
    public boolean quitarPrestamo(Prestamo p_prestamo){
        return this.getPrestamo().remove(p_prestamo);
    }
    
    /**
     * @return este metodo retorna true si el libro fue prestado y si no tiene una fecha de devolucion
     */
    public boolean prestado(){
        Prestamo ultimo = this.ultimoPrestamo();
        return ultimo != null && ultimo.getFechaDevolucion() == null;
    }
    
    /**
     * @return este metodo retorna el ultimo prestamo de la coleccion,
     * si la lista esta vacia retorna null
     */
    public Prestamo ultimoPrestamo(){
        if(this.getPrestamo().isEmpty()){
                return null;
        } else 
            return this.getPrestamo().get(this.getPrestamo().size() - 1);
    }
    
    /**
     * @return toString() imprime el titulo del libro
     */
    public String toString(){            
        return "Titulo: " +this.getTitulo();
    } 
}
