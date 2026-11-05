/**
 * Excepción personalizada que representa la falla al intentar realizar
 * una operación de devolución o consulta sobre un libro que no figura
 * como prestado en la biblioteca.
 */
public class LibroNoPrestadoException extends Exception {
    /**
     * Construye una nueva excepción de libro no prestado especificando
     * el mensaje detallado del error.
     * @param message
     */
    public LibroNoPrestadoException(String message) {
        super(message);
    }
}