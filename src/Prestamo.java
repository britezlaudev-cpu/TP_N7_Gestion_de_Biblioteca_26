import java.util.Calendar;

public class Prestamo {
    // Atributos
    private Calendar fechaRetiro;
    private Calendar fechaDevolucion;
    private Libro libro;
    private Socio socio;

    /**
     * Constructor para crear un nuevo registro de préstamo.
     * Inicializa la fecha de retiro, asocia el libro y el socio,
     * y deja la fecha de devolución en null hasta que se efectúe la entrega.
     *
     * @param p_fechaR Fecha en la que se efectúa el retiro del libro.
     * @param p_libro  Libro que es objeto del préstamo.
     * @param p_socio  Socio que solicita el préstamo.
     */
    public Prestamo(Calendar p_fechaR, Libro p_libro, Socio p_socio){
        this.setFechaRetiro(p_fechaR);
        this.setFechaDevolucion((Calendar) null);
        this.setLibro(p_libro);
        this.setSocio(p_socio);
    }

    // Setters
    private void setFechaRetiro(Calendar p_fechaR){
        this.fechaRetiro = p_fechaR;
    }
    private void setFechaDevolucion(Calendar p_fechaD){
        this.fechaDevolucion = p_fechaD;
    }
    private void setLibro(Libro p_libro){
        this.libro = p_libro;
    }
    private void setSocio(Socio p_socio){
        this.socio = p_socio;
    }

    // Getters
    public Calendar getFechaRetiro(){
        return this.fechaRetiro;
    }
    public Calendar getFechaDevolucion(){
        return this.fechaDevolucion;
    }
    public Libro getLibro(){
        return this.libro;
    }
    public Socio getSocio(){
        return this.socio;
    }

    /**
     * Registra la fecha en la que se devuelve el libro del préstamo.
     *
     * @param p_fecha Fecha en la que se efectúa la devolución.
     */
    public void registrarFechaDevolucion(Calendar p_fecha){
        this.setFechaDevolucion(p_fecha);
    }

    /**
     * Evalúa si el préstamo se encuentra vencido en función de una fecha dada
     * y de los días de préstamo permitidos según el tipo de socio.
     *
     * @param p_fecha Fecha contra la cual se desea comprobar el vencimiento (por ejemplo, la fecha actual).
     * @return true si la fecha pasada excede el vencimiento calculado, false en caso contrario.
     */
    public boolean vencido(Calendar p_fecha) {
        Calendar fechaVencimiento = (Calendar) this.getFechaRetiro().clone();

        int dias = this.getSocio().getDiasPrestamo();
        fechaVencimiento.add(Calendar.DATE, dias);

        if (p_fecha.after(fechaVencimiento)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retorna una representación en formato de cadena de texto con los detalles del préstamo,
     * incluyendo las fechas, el libro y el socio asociado.
     *
     * @return Una cadena formateada con la información del préstamo.
     */
    public String toString(){
        return "Retiro: " + this.getFechaRetiro() + "- Devolucion: " + this.getFechaDevolucion() + "\nLibro: " + this.getLibro() + "\nSocio: " + this.getSocio();
    }
}
