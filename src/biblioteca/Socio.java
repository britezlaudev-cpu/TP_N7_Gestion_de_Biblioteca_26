package biblioteca;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public abstract class Socio {
    // Atributos
    private int dniSocio;
    private String nombre;
    private int diasPrestamo;
    private ArrayList <Prestamo> prestamo;

    /**
     * Constructor para crear un nuevo socio con su DNI, nombre, días de préstamo
     * permitidos e inicializando una lista de préstamos vacía.
     *
     * @param p_dniSocio Número de DNI del socio.
     * @param p_nombre   Nombre y apellido del socio.
     * @param p_diasP    Cantidad de días permitidos para los préstamos.
     */
    public Socio(int p_dniSocio, String p_nombre, int p_diasP){
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasP);
        this.setPrestamo(new ArrayList<Prestamo>());
    }

    /**
     * Constructor para crear un socio incluyendo una colección preexistente de préstamos.
     *
     * @param p_dniSocio   Número de DNI del socio.
     * @param p_nombre     Nombre y apellido del socio.
     * @param p_diasP      Cantidad de días permitidos para los préstamos.
     * @param p_prestamo   Lista inicial de préstamos asociados al socio.
     */
    public Socio(int p_dniSocio, String p_nombre, int p_diasP, ArrayList <Prestamo> p_prestamo){
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasP);
        this.setPrestamo(p_prestamo);
    }

    // Setters
    private void setDniSocio(int p_dniSocio){
        this.dniSocio = p_dniSocio;
    }
    private void setNombre(String p_nombre){
        this.nombre = p_nombre;
    }
    protected void setDiasPrestamo(int p_diasP){
        this.diasPrestamo = p_diasP;
    }
    private void setPrestamo(ArrayList <Prestamo> p_prestamo){
        this.prestamo = p_prestamo;
    }

    // Getters
    public int getDniSocio(){
        return this.dniSocio;
    }
    public String getNombre(){
        return this.nombre;
    }
    public int getDiasPrestamo(){
        return this.diasPrestamo;
    }
    public ArrayList <Prestamo> getPrestamo(){
        return this.prestamo;
    }

    /**
     * Agrega un nuevo préstamo a la colección de préstamos del socio.
     *
     * @param p_prestamo Objeto Prestamo que se desea incorporar.
     * @return true si el préstamo se agregó correctamente, false en caso contrario.
     */
    public boolean addPrestamo(Prestamo p_prestamo){
        return this.getPrestamo().add(p_prestamo);
    }
    /**
     * Cuenta la cantidad de libros que el socio tiene actualmente asociados a través de sus préstamos.
     *
     * @return Cantidad de libros prestados.
     */
    public int cantLibrosPrestados(){
        int libros = 0;
        for(Prestamo p : prestamo){
            if(p.getLibro() != null){
                libros++;
            }
        }
        return libros;
    }
    /**
     * Retorna una representación en formato de texto con los datos principales del socio,
     * incluyendo su DNI, nombre, tipo de clase y la cantidad de libros prestados.
     *
     * @return Una cadena formateada con la información del socio.
     */
    public String toString(){
        return "DNI: " + this.getDniSocio() + " || Nombre y Apellido: " + this.getNombre() + "(" + this.soyDeLaClase() + ") ||"
                + " Libros Prestados: " + this.cantLibrosPrestados();
    }
    /**
     * Evalúa si el socio está habilitado para realizar nuevos pedidos de préstamos.
     *
     * @return true si cumple con las condiciones para pedir, false en caso contrario.
     */
    public boolean puedePedir(){
        for(Prestamo p : this.getPrestamo()){
            if(p.vencido(new GregorianCalendar())){
                return true;
            }
        }
        return false;
    }
    /**
     * Método abstracto para obtener el tipo o subtipo de socio en formato de texto.
     *
     * @return Una cadena de texto indicando la clase del socio (por ejemplo, "Estudiante" o "Docente").
     */
    public abstract String soyDeLaClase();
}