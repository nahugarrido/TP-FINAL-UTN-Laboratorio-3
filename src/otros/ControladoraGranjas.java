package otros;

import excepciones.UsuarioYaExistenteException;
import modelos.Granja;
import usuarios.ControladoraUsuarios;
import utiles.Serializar;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ControladoraGranjas {
    private static Granja activa;

    public ControladoraGranjas() {

    }


    public static String mostrarGranjas() {
        Serializar serializar = new Serializar();
        StringBuilder texto = new StringBuilder();
        ArrayList<Granja> listaGranjas = null;
        File archivo = new File("granjas");
        if(archivo.exists()) {
            Object aux = serializar.deserializar("granjas");
            listaGranjas = (ArrayList<Granja>) aux;
        }


        for(Granja granja : listaGranjas) {
            texto.append(granja.toString()).append("\n");
        }

        return texto.toString();
    }

    public static void generarGranja() throws UsuarioYaExistenteException {
        Scanner scan = new Scanner(System.in);

        System.out.println("MENU CREAR GRANJA---------------");
        //System.out.println("El sistema soporta multiples granjas, este menu tiene como proposito la creacion de las mismas,\nse debe ingresar el nombre de la granja, la fecha (en formato yyyy-mm-dd) y luego \ncrear un usuario que sera el usuario inicial de la granja, \nluego a traves del menu del administrador se pueden crear multiples usuarios para esa granja en concreto.");

        System.out.println("Ingrese el nombre de la granja: ");
        String nombre = scan.nextLine();
        System.out.println("Ingrese una fecha en formato (yyyy-mm-dd)");
        String fecha = scan.nextLine();

        /// Se debe hacer en este orden porque de otra forma quedarian granjas sin usuarios en caso de ocurrir una excepcion
        int idUsuario = ControladoraUsuarios.registrarUsuarioAdmin();
        Granja nuevaGranja = new Granja(nombre, fecha, idUsuario);
        ControladoraUsuarios.actualizarIdGranja(idUsuario, nuevaGranja.getId());

        /// Se guarda la nueva granja en el archivo
        File archivo = new File("granjas");
        Serializar serializar = new Serializar();
        ArrayList<Granja> listaGranjas;
        if (archivo.exists()) {
            Object aux = serializar.deserializar("granjas");
            listaGranjas = (ArrayList<Granja>) aux;
        } else {
            listaGranjas = new ArrayList<>();
        }

        listaGranjas.add(nuevaGranja);

        serializar.serializar(listaGranjas, "granjas");
    }
}
