package excepciones;

/**
 * Excepción lanzada cuando el usuario o la contraseña no son válidos.
 */
public class UsuarioNoValidoException extends Exception {
    /**
     * Crea una nueva instancia de UsuarioNoValidoException con un mensaje de error personalizado.
     * @param message
     */
    public UsuarioNoValidoException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "El usuario o la contraseña no es valida.";
    }
}
