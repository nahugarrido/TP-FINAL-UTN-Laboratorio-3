package genericas;

import excepciones.UsuarioNoValidoException;
import excepciones.UsuarioYaExistenteException;
import modelos.usuarios.Usuario;

import java.io.Serializable;

/**
 * Esta clase representa una lista genérica de usuarios.
 */
public class ListaUsuarios extends GenericaList<Usuario> implements Serializable {

    /**
     * Verifica los datos de acceso de un usuario.
     *
     * @param usuario Nombre de usuario.
     * @param clave   Clave de acceso.
     * @return El usuario si los datos son válidos.
     * @throws UsuarioNoValidoException Si los datos de acceso no son válidos.
     */

    /// hay que ver si no hay que crear otra clase generica o si se puede dejar este metodo aca
    public Usuario verificarDatosAcceso(String usuario, String clave) throws UsuarioNoValidoException {
        for (Usuario elemento : listaGenerica) {
                if (elemento.getUsuario().equals(usuario) && elemento.getClave().equals(clave)) {
                    return elemento;
                }
        }
        throw new UsuarioNoValidoException("");
    }


    /**
     * Verifica si un usuario ya existe en la lista.
     *
     * @param usuario Nombre de usuario a verificar.
     * @throws UsuarioYaExistenteException Si el usuario ya existe en la lista.
     */
    public void verificarSiExisteUsuario(String usuario) throws UsuarioYaExistenteException {
        for (Usuario elemento : listaGenerica) {
            if (elemento.getUsuario().equals(usuario)) {
                throw new UsuarioYaExistenteException("", usuario);
            }
        }
    }

    /**
     * Actualiza el ID de granja de un usuario.
     *
     * @param idUsuario ID del usuario.
     * @param idGranja  Nuevo ID de granja.
     */
    public void actualizarIdGranja(int idUsuario, int idGranja) {
        for (Usuario elemento : listaGenerica) {
            if (elemento.getId() == idUsuario) {
               elemento.setIdGranja(idGranja);
            }
        }
    }


}
