import java.util.Calendar;
import java.util.ArrayList;
import java.util.GregorianCalendar;
/**
 * Write a description of class Prestamo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Prestamo
{
    private Calendar fechaRetiro;
    private Calendar fechaDevolucion;
    private ArrayList <Libro> libro;
    private ArrayList <Socio> socio;
    
    public Prestamo(Calendar p_fechaR, Calendar p_fechaD, Libro p_libro, Socio p_socio){
        this.setFechaRetiro(p_fechaR);
        this.setFechaDevolucion(p_fechaD);
        this.setLibro(new ArrayList<Libro>());
        this.setSocio(new ArrayList<Socio>());
    }
    
    public Prestamo(Calendar p_fechaR, Calendar p_fechaD, ArrayList <Libro> p_libro, ArrayList <Socio> p_socio){
        this.setFechaRetiro(p_fechaR);
        this.setFechaDevolucion(p_fechaD);
        this.setLibro(p_libro);
        this.setSocio(p_socio);
    }
    
    private void setFechaRetiro(Calendar p_fechaR){
        this.fechaRetiro = p_fechaR;
    }
    
    private void setFechaDevolucion(Calendar p_fechaD){
        this.fechaDevolucion = p_fechaD;
    }
    
    private void setLibro(ArrayList <Libro> p_libro){
        this.libro = p_libro;
    }
    
    private void setSocio(ArrayList <Socio> p_socio){
        this.socio = p_socio;
    }
    
    public Calendar getFechaRetiro(){
        return this.fechaRetiro;
    }
    
    public Calendar getFechaDevolucion(){
        return this.fechaDevolucion;
    }
    
    public ArrayList <Libro> getLibro(){
        return this.libro;
    }
    
    public ArrayList <Socio> getSocio(){
        return this.socio;
    }
    
    
    public void registrarFechaDevolucion(Calendar p_fecha){
        this.setFechaDevolucion(p_fecha);
    }
    
    public boolean vencido(Calendar p_fecha){
        Calendar fechaVencimiento = new GregorianCalendar();
        
        fechaVencimiento.setTime(this.getFechaRetiro().getTime());
        
        fechaVencimiento.add(Calendar.DATE, this.getSocio().getDiasPrestamo());
    
        if (p_fecha.after(fechaVencimiento)){ 
            return true; 
        }else { 
            return false;
        }
    }
    
    public String toString(){
        return "Retiro: " + this.getFechaRetiro() + "- Devolucion: " + this.getFechaDevolucion();
        return "Libro: " + this.getLibro();
        return "Socio: " + this.getSocio();
    }
}