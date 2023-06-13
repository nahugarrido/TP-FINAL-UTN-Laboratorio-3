package usuarios;

import excepciones.UsuarioNoValidoException;
import excepciones.UsuarioYaExistenteException;
import utiles.Serializar;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase encargada de gestionar los usuarios
 */
public class ControladoraUsuarios {
    private Usuario activo;

    public ControladoraUsuarios() {
        activo = null;
    }

    public static void actualizarIdGranja(int idUsuario, int idGranja) {
        Serializar serializar = new Serializar();
        Object aux = serializar.deserializar("usuarios");
        ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) aux;
        for(Usuario item : listaUsuarios) {
            if(item.getId() == idUsuario) {
                item.setIdGranja(idGranja);
                break;
            }
        }

        serializar.serializar(listaUsuarios, "usuarios");
    }

    /**
     * Registrar un usuario Administrador
     * @return id del usuario
     * @throws UsuarioYaExistenteException usuario ya existe
     */
    public static int registrarUsuarioAdmin() throws UsuarioYaExistenteException {
        Serializar serializar = new Serializar();
        Scanner scan = new Scanner(System.in);
        ArrayList<Usuario> listaUsuarios;
        /// obtener datos del usuario a registrar
        System.out.println("Ingrese un nombre de usuario (administrador): ");
        String usuario = scan.nextLine();
        System.out.println("Ingrese la clave del usuario: ");
        String clave = scan.nextLine();
        /// revisar si existe el archivo, en caso contrario crearlo
        File archivo = new File("usuarios");
        if (archivo.exists()) {
            Object aux = serializar.deserializar("usuarios");
            listaUsuarios = (ArrayList<Usuario>) aux;
        } else {
            listaUsuarios = new ArrayList<>();
        }


        /// revisar si existe el usuario en el archivo de usuarios
        for (Usuario elemento : listaUsuarios) {
            if (elemento.getUsuario().equals(usuario)) {
                throw new UsuarioYaExistenteException("", usuario);
            }
        }

        Usuario nuevoUsuario = new Administrador(usuario, clave);
        listaUsuarios.add(nuevoUsuario);

        serializar.serializar(listaUsuarios, "usuarios");

        return nuevoUsuario.getId();
    }

    /**
     * Registrar un usuario Empleado
     * @return id del usuario
     * @throws UsuarioYaExistenteException usuario ya existe
     */
    public static int registrarUsuarioEmpleado() throws UsuarioYaExistenteException {
        Serializar serializar = new Serializar();
        Scanner scan = new Scanner(System.in);
        ArrayList<Usuario> listaUsuarios;

        /// obtener datos del usuario a registrar
        System.out.println("Ingrese un nombre de usuario (empleado): ");
        String usuario = scan.nextLine();
        System.out.println("Ingrese la clave del usuario: ");
        String clave = scan.nextLine();

        /// revisar si existe el archivo, en caso contrario crearlo
        File archivo = new File("usuarios");
        if (archivo.exists()) {
            Object aux = serializar.deserializar("usuarios");
            listaUsuarios = (ArrayList<Usuario>) aux;
        } else {
            listaUsuarios = new ArrayList<>();
        }


        /// revisar si existe el usuario en el archivo de usuarios
        for (Usuario elemento : listaUsuarios) {
            if (elemento.getUsuario().equals(usuario)) {
                throw new UsuarioYaExistenteException("", usuario);
            }
        }

        Usuario nuevoUsuario = new Empleado(usuario, clave);
        listaUsuarios.add(nuevoUsuario);

        serializar.serializar(listaUsuarios, "usuarios");

        return nuevoUsuario.getId();
    }

    /**
     * Acceder a la aplicacion
     * @return Usuario activo
     * @throws UsuarioNoValidoException usuario no valido
     */
    public Usuario ingresarDatos() throws UsuarioNoValidoException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingresa tu usuario: ");
        String usuario = scan.nextLine();
        System.out.print("Ingresa tu clave: ");
        String clave = scan.nextLine();

        if (verificarDatosAcceso(usuario, clave)) {
            return this.getActivo();
        }

        return null;
    }

    /**
     * Verificar datos de acceso de un usuario
     * @param usuario Nombre de acceso
     * @param clave Clave de acceso
     * @return Booleano true: valido , false: invalido
     * @throws UsuarioNoValidoException usuario no valido
     */
    private boolean verificarDatosAcceso(String usuario, String clave) throws UsuarioNoValidoException {
        Serializar serializar = new Serializar();
        Object aux = serializar.deserializar("usuarios");

        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) aux;
        for (Usuario elemento : usuarios) {
            if (usuario.equals(elemento.getUsuario()) && clave.equals(elemento.getClave())) {
                this.setActivo(elemento);
                return true;
            }
        }

        throw new UsuarioNoValidoException("");
    }

    /**
     * Metodo para mostrar todos los usuarios del archivo "usuarios.bin"
     * @return retorna un string con todos los usuarios
     */
    public static String mostrarUsuarios() {
        Serializar serializar = new Serializar();
        StringBuilder texto = new StringBuilder();
        ArrayList<Usuario> listaUsuarios = null;
        File archivo = new File("usuarios");
        if(archivo.exists()) {
            Object aux = serializar.deserializar("usuarios");
            listaUsuarios = (ArrayList<Usuario>) aux;
        }


        for(Usuario usuario : listaUsuarios) {
            texto.append(usuario.toString()).append("\n");
        }

        return texto.toString();
    }

    /**
     * Metodo para el usuario activo en la aplicacion
     * @return Usuario activo
     */
    public  Usuario getActivo() {
        return activo;
    }

    public void setActivo(Usuario activo) {
        this.activo = activo;
    }
}
