package excepciones;

/**
 * Excepción lanzada cuando un lote se encuentra vacío y no contiene elementos.
 */
public class LoteVacioExcepcion extends Exception {
    /**
     * Crea una nueva instancia de LoteVacioExcepcion con un mensaje de error personalizado.
     * @param message
     */
    public LoteVacioExcepcion(String message) {
        super(message);
    }
}
