package excepciones;

/**
 * Excepción lanzada cuando ocurre un error al recolectar huevos.
 */
public class RecolectarHuevosException extends Exception {
    /**
     *Crea una nueva instancia de RecolectarHuevosException con un mensaje de error personalizado.
     * @param message
     */
    public RecolectarHuevosException(String message) {
        super(message);
    }
}
