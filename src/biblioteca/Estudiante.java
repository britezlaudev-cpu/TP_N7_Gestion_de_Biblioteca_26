package biblioteca;

import java.util.Calendar;

public class Estudiante extends Socio{
    // Atributos
    private String carrera;

    /**
     * Constructor para crear un nuevo socio Estudiante con su DNI, nombre, carrera
     * y un límite predeterminado de días de préstamo (fijado en 20 en la superclase).
     *
     * @param p_dniSocio DNI del estudiante.
     * @param p_nombre   Nombre del estudiante.
     * @param p_carrera  Carrera que cursa el estudiante.
     */
    public Estudiante(int p_dniSocio, String p_nombre, String p_carrera) {
        super(p_dniSocio, p_nombre, 20);
        this.setCarrera(p_carrera);
    }

    // Setters
    private void setCarrera(String p_carrera) {
        this.carrera = p_carrera;
    }
    // Getters
    public String getCarrera() {
        return this.carrera;
    }

    /**
     * Evalúa si el estudiante está habilitado para solicitar un nuevo préstamo,
     * verificando que no posea 3 o más libros prestados actualmente y que no tenga
     * ningún préstamo activo que se encuentre vencido a la fecha de hoy.
     *
     * @return true si puede realizar el pedido, false si alcanzó el límite de libros o tiene vencidos pendientes.
     */
    public boolean puedePedir() {
        if (this.cantLibrosPrestados() >= 3) {
            return false;
        } else {
            Calendar hoy = Calendar.getInstance();

            for(Prestamo p : this.getPrestamo()) {
                if (p.getFechaDevolucion() == null && p.vencido(hoy)) {
                    return false;
                }
            }
            return true;
        }
    }
    /**
     * Retorna el nombre de la clase o el tipo de socio.
     *
     * @return Una cadena de texto indicando que el socio es del tipo "Estudiante".
     */
    public String soyDeLaClase() {
        return "Estudiante";
    }
}