package genericas;

import excepciones.UsuarioNoValidoException;
import excepciones.UsuarioYaExistenteException;
import modelos.usuarios.Usuario;

import java.io.Serializable;

public class ListaUsuarios extends GenericaList<Usuario> implements Serializable {


    /// hay que ver si no hay que crear otra clase generica o si se puede dejar este metodo aca
    public Usuario verificarDatosAcceso(String usuario, String clave) throws UsuarioNoValidoException {
        for (Usuario elemento : listaGenerica) {
                if (elemento.getUsuario().equals(usuario) && elemento.getClave().equals(clave)) {
                    return elemento;
                }
        }
        throw new UsuarioNoValidoException("");
    }

    public void verificarSiExisteUsuario(String usuario) throws UsuarioYaExistenteException {
        for (Usuario elemento : listaGenerica) {
            if (elemento.getUsuario().equals(usuario)) {
                throw new UsuarioYaExistenteException("", usuario);
            }
        }
    }

    public void actualizarIdGranja(int idUsuario, int idGranja) {
        for (Usuario elemento : listaGenerica) {
            if (elemento.getId() == idUsuario) {
               elemento.setIdGranja(idGranja);
            }
        }
    }


}
