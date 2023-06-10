package usuarios;

import excepciones.UsuarioNoValidoException;
import excepciones.UsuarioYaExistenteException;
import utiles.Serializar;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ControladoraUsuarios {
    private static Usuario activo;

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

    public static Usuario ingresarDatos() throws UsuarioNoValidoException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingresa tu usuario: ");
        String usuario = scan.nextLine();
        System.out.print("Ingresa tu clave: ");
        String clave = scan.nextLine();

        if (verificarDatosAcceso(usuario, clave)) {
            return activo;
        }

        return null;
    }

    private static boolean verificarDatosAcceso(String usuario, String clave) throws UsuarioNoValidoException {
        Serializar serializar = new Serializar();
        Object aux = serializar.deserializar("usuarios");

        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) aux;
        for (Usuario elemento : usuarios) {
            if (usuario.equals(elemento.getUsuario()) && clave.equals(elemento.getClave())) {
                activo = elemento;
                return true;
            }
        }

        throw new UsuarioNoValidoException("");
    }

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

    public static Usuario getActivo() {
        return activo;
    }

    public void setActivo(Usuario activo) {
        this.activo = activo;
    }
}
