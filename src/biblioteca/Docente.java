package biblioteca;

import java.util.Calendar;

public class Docente extends Socio{
    // Atriutos
    private String area;

    /**
     * Constructor para crear un nuevo socio Docente con su DNI, nombre, área
     * y un límite predeterminado de días de préstamo (fijado en 5 en la superclase).
     *
     * @param p_dniSocio DNI del docente.
     * @param p_nombre   Nombre del docente.
     * @param p_area     Área o departamento a la que pertenece el docente.
     */
    public Docente(int p_dniSocio, String p_nombre, String p_area) {
        super(p_dniSocio, p_nombre, 5);
        this.setArea(p_area);
    }

    // Setters
    private void setArea(String p_area) {
        this.area = p_area;
    }
    // Getters
    public String getArea() {
        return this.area;
    }

    /**
     * Evalúa si el docente es considerado un socio responsable, verificando
     * que no posea préstamos vencidos históricos o pendientes de devolución.
     *
     * @return true si es responsable (no tiene préstamos vencidos), false en caso contrario.
     */
    public boolean esResponsable() {
        for(Prestamo p : this.getPrestamo()) {
            if (p.vencido(p.getFechaDevolucion() != null ? p.getFechaDevolucion() : Calendar.getInstance()) && p.getFechaDevolucion() != null && p.vencido(p.getFechaDevolucion())) {
                return false;
            }
        }
        return true;
    }
    /**
     * Modifica los días de préstamo permitidos sumando o restando una cantidad específica.
     *
     * @param p_dias Cantidad de días a sumar (o restar si es negativo) a los días de préstamo actuales.
     */
    public void cambiarDiasDePrestamo(int p_dias) {
        this.setDiasPrestamo(this.getDiasPrestamo() + p_dias);
    }

    /**
     * Verifica si el docente está habilitado para solicitar un nuevo préstamo,
     * comprobando que no tenga libros actualmente prestados y vencidos a la fecha de hoy.
     *
     * @return true si puede realizar un nuevo pedido, false si tiene algún préstamo vencido sin devolver.
     */
    public boolean puedePedir() {
        Calendar hoy = Calendar.getInstance();

        for(Prestamo p : this.getPrestamo()) {
            if (p.getFechaDevolucion() == null && p.vencido(hoy)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Retorna el nombre de la clase o el tipo de socio.
     *
     * @return Una cadena de texto indicando que el socio es del tipo "Docente".
     */
    public String soyDeLaClase() {
        return "Docente";
    }
}