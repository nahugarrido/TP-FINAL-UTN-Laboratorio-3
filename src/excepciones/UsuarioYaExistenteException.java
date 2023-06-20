package excepciones;

/**
 *Excepción lanzada cuando se intenta registrar un usuario que ya existe.
 */
public class UsuarioYaExistenteException extends Exception {
    private String usuario;

    /**
     * Crea una nueva instancia de UsuarioYaExistenteException
     * con un mensaje de error personalizado y el nombre de usuario existente.
     * @param message
     * @param usuario
     */
    public UsuarioYaExistenteException(String message, String usuario) {
        super(message);
        this.usuario = usuario;
    }

    @Override
    public String getMessage() {
        return "El usuario " + this.getUsuario() + " ya esta registrado, por favor vuelve a intentarlo con otro usuario..";
    }

    public String getUsuario() {
        return usuario;
    }
}
