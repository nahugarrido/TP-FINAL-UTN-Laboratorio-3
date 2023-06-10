package excepciones;

public class UsuarioNoValidoException extends Exception {
    public UsuarioNoValidoException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "El usuario o la contraseña no es valida.";
    }
}
