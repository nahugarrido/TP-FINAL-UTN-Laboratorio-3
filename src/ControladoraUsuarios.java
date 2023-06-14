import excepciones.UsuarioNoValidoException;
import excepciones.UsuarioYaExistenteException;
import genericas.GenericaList;
import genericas.ListaUsuarios;
import modelos.granja.Granja;
import modelos.usuarios.Administrador;
import modelos.usuarios.Empleado;
import modelos.usuarios.Usuario;
import otros.Serializar;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase encargada de gestionar los modelos.usuarios
 */
public class ControladoraUsuarios {

    private ListaUsuarios<Usuario> listaUsuarios;

    public ControladoraUsuarios() {
        listaUsuarios = new ListaUsuarios<>();
    }

    /**
     * Persiste la informacion en el archivo
     */
    private void guardarArchivoUsuarios() {
        Serializar serializar = new Serializar();
        serializar.serializar(listaUsuarios, "usuarios");
    }

    /**
     * Lee la informacion desde el archivo
     *
     * @return retorna la informacion en formato GenericaList<Usuario>
     */
    private GenericaList<Usuario> leerArchivoUsuarios() {
        Serializar serializar = new Serializar();
        ListaUsuarios<Usuario> listaUsuarios = null;
        File archivo = new File("usuarios");
        if (archivo.exists()) {
            Object aux = serializar.deserializar("usuarios");
            listaUsuarios = (ListaUsuarios<Usuario>) aux;
        } else {
            listaUsuarios = new ListaUsuarios<>();
        }

        this.setListaUsuarios(listaUsuarios);
        return listaUsuarios;
    }

    /**
     * actualizar el id de granja asociado al usuario
     *
     * @param idUsuario usuario que se desea actualizar
     * @param idGranja  granja con la que se quiere asociar
     */
    public void actualizarIdGranja(int idUsuario, int idGranja) {
        this.leerArchivoUsuarios();
        listaUsuarios.actualizarIdGranja(idUsuario, idGranja);
        this.guardarArchivoUsuarios();
    }

    /**
     * Registrar un usuario Administrador
     *
     * @return id del usuario
     * @throws UsuarioYaExistenteException usuario ya existe
     */
    public int registrarUsuarioAdmin() throws UsuarioYaExistenteException {
        Scanner scan = new Scanner(System.in);

        /// obtener datos del usuario a registrar
        System.out.println("Ingrese un nombre de usuario (administrador): ");
        String usuario = scan.nextLine();
        System.out.println("Ingrese la clave del usuario: ");
        String clave = scan.nextLine();

        this.leerArchivoUsuarios();

        listaUsuarios.verificarSiExisteUsuario(usuario);
        Usuario nuevoUsuario = new Administrador(usuario, clave);
        listaUsuarios.agregarElemento(nuevoUsuario);

        this.guardarArchivoUsuarios();

        return nuevoUsuario.getId();
    }

    /**
     * Registrar un usuario Empleado
     *
     * @return id del usuario
     * @throws UsuarioYaExistenteException usuario ya existe
     */
    public int registrarUsuarioEmpleado() throws UsuarioYaExistenteException {
        Scanner scan = new Scanner(System.in);

        /// obtener datos del usuario a registrar
        System.out.println("Ingrese un nombre de usuario (empleado): ");
        String usuario = scan.nextLine();
        System.out.println("Ingrese la clave del usuario: ");
        String clave = scan.nextLine();

        this.leerArchivoUsuarios();

        listaUsuarios.verificarSiExisteUsuario(usuario);
        Usuario nuevoUsuario = new Empleado(usuario, clave);
        listaUsuarios.agregarElemento(nuevoUsuario);

        this.guardarArchivoUsuarios();

        return nuevoUsuario.getId();
    }

    /**
     * Acceder a la aplicacion
     *
     * @return Usuario activo
     * @throws UsuarioNoValidoException usuario no valido
     */
    public Usuario ingresarDatos() throws UsuarioNoValidoException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingresa tu usuario: ");
        String usuario = scan.nextLine();
        System.out.print("Ingresa tu clave: ");
        String clave = scan.nextLine();

        return verificarDatosAcceso(usuario, clave);
    }

    /**
     * Verificar datos de acceso de un usuario
     *
     * @param usuario Nombre de acceso
     * @param clave   Clave de acceso
     * @return Booleano retorna usuario
     * @throws UsuarioNoValidoException usuario no valido
     */
    private Usuario verificarDatosAcceso(String usuario, String clave) throws UsuarioNoValidoException {
        this.leerArchivoUsuarios();
        Usuario aux = listaUsuarios.verificarDatosAcceso(usuario, clave);
        return aux;
    }

    /**
     * Metodo para mostrar todos los modelos.usuarios del archivo "modelos.usuarios.bin"
     *
     * @return retorna un string con todos los modelos.usuarios
     */
    public String mostrarUsuarios() {
        this.leerArchivoUsuarios();
        return listaUsuarios.listarElementos();
    }

    private void setListaUsuarios(ListaUsuarios<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
}
