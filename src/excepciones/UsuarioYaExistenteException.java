package excepciones;

public class UsuarioYaExistenteException extends Exception {
    private String usuario;

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
