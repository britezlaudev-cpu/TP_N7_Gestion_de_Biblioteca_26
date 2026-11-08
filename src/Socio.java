import java.util.ArrayList;
import java.util.GregorianCalendar;
public abstract class Socio
{
    private int dniSocio;
    private String nombre;
    private int diasPrestamo;
    private ArrayList <Prestamo> prestamo;

    public Socio(int p_dniSocio, String p_nombre, int p_diasP){
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasP);
        this.setPrestamo(new ArrayList<Prestamo>());
    }

    public Socio(int p_dniSocio, String p_nombre, int p_diasP, ArrayList <Prestamo> p_prestamo){
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasP);
        this.setPrestamo(p_prestamo);
    }

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

    /**Getters*/
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


    /**Metodos*/
    public boolean addPrestamo(Prestamo p_prestamo){
        return this.getPrestamo().add(p_prestamo);
    }

    public int cantLibrosPrestados(){
        int libros = 0;

        for(Prestamo p : prestamo){
            if(p.getLibro() != null){
                libros++;
            }
        }
        return libros;
    }

    public String toString(){
        return "DNI: " + this.getDniSocio() + "|| Nombre y Apellido: " + this.getNombre() + "(" + this.soyDeLaClase() + ") ||"
                + "Libros Prestados: " + this.cantLibrosPrestados();
    }

    public boolean puedePedir(){
        for(Prestamo p : this.getPrestamo()){
            if(p.vencido(new GregorianCalendar())){
                return true;
            }
        }
        return false;
    }

    public abstract String soyDeLaClase();
}}
