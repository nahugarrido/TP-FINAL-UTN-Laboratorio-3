import excepciones.UsuarioNoValidoException;
import excepciones.UsuarioYaExistenteException;
import otros.ControladoraGranjas;
import modelos.Granja;
import otros.Configuracion;
import usuarios.ControladoraUsuarios;
import utiles.Serializar;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        cargarConfiguracion();
        menuPrincipal();


        /// Testear la logica de avanzar en los dias
/*
        Granja granja = new Granja("2023-01-01");
        DatosClima datos  = ClimaAPI.obtenerDatosClima();
        System.out.println(datos.toString());

        System.out.println(granja.toString());
        for(int i = 0; i < 31; i++) {
            granja.avanzarUnDia();
            datos  = ClimaAPI.obtenerDatosClima();
            System.out.println(datos.toString());
            System.out.println(granja.toString());
        }
*/
    }

    public static void menuPrincipal() {
        Scanner scan = new Scanner(System.in);
        String opcion = "";
        boolean continuar = true;
        do {
            System.out.println("MENU PRINCIPAL--------------------------");
            System.out.println("1) Generar granja nueva");
            System.out.println("2) Ingresar a granja");
            System.out.println("3) Ver granjas");
            System.out.println("4) Ver usuarios");
            System.out.println("5) Salir");
            System.out.println("----------------------------------------");
            System.out.print("Navegar: ");
            opcion = scan.nextLine();
            switch (opcion) {
                case "1":
                    try {
                        generarGranja();
                    } catch (UsuarioYaExistenteException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    try {
                        ControladoraUsuarios.ingresarDatos();
                        ControladoraUsuarios.getActivo();
                        System.out.println(ControladoraUsuarios.getActivo().toString());
                    } catch (UsuarioNoValidoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    System.out.println(ControladoraGranjas.mostrarGranjas());
                    break;
                case "4":
                    System.out.println(ControladoraUsuarios.mostrarUsuarios());
                    break;
                case "5":
                    continuar = false;
                    break;
                default:
                    System.out.println("Debes selecionar una opcion valida.");
                    break;
            }
        } while (continuar);
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

    public static void cargarConfiguracion() {
        /// Se crea o se lee el archivo de configuracion para generador de id
        File archivo = new File("configuracion");
        if (archivo.exists()) {
            Configuracion configuracion = Configuracion.leerArchivo();
            System.out.println(configuracion.toString());
        } else {
            Configuracion.getInstance();
        }
    }

}