package excepciones;

/**
 * Excepción personalizada lanzada cuando una gallina ya ha comido.
 */
public class HanComidoException extends Exception {
    /**
     * Crea una nueva instancia de HanComidoException con un mensaje de error personalizado.
     * @param message
     */
    public HanComidoException(String message) {
        super(message);
    }
}
