import java.util.Calendar;

public class Prestamo
{
    private Calendar fechaRetiro;
    private Calendar fechaDevolucion;
    private Libro libro;
    private Socio socio;

    public Prestamo(Calendar p_fechaR, Libro p_libro, Socio p_socio){
        this.setFechaRetiro(p_fechaR);
        this.setFechaDevolucion((Calendar) null);
        this.setLibro(p_libro);
        this.setSocio(p_socio);
    }

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


    public void registrarFechaDevolucion(Calendar p_fecha){
        this.setFechaDevolucion(p_fecha);
    }

    public boolean vencido(Calendar p_fecha) {
        // 1. Clonamos la fecha de retiro para no modificar la original
        Calendar fechaVencimiento = (Calendar) this.getFechaRetiro().clone();

        // 2. Sumamos los días de préstamo que tiene permitidos el socio de este préstamo
        // (Asumiendo que el socio tiene un método getDiasPrestamo() o según corresponda a tu diseño)
        int dias = this.getSocio().getDiasPrestamo(); // O si prefieres consultar el tipo de socio,
        // pero el UML indica que Socio o sus hijas manejan los plazos.
        fechaVencimiento.add(Calendar.DATE, dias);

        // 3. Comparamos: si la fecha pasada por parámetro es posterior (mayor) a la fecha de vencimiento, retorna true.
        if (p_fecha.after(fechaVencimiento)) {
            return true;
        } else {
            return false;
        }
    }
    public String toString(){
        return "Retiro: " + this.getFechaRetiro() + "- Devolucion: " + this.getFechaDevolucion() + "\nLibro: " + this.getLibro() + "\nSocio: " + this.getSocio();
    }
}
