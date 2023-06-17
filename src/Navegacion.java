import excepciones.UsuarioNoValidoException;
import excepciones.UsuarioYaExistenteException;
import modelos.granja.Granja;
import modelos.usuarios.Administrador;
import modelos.usuarios.Empleado;
import modelos.usuarios.Usuario;
import otros.Configuracion;

import java.io.File;
import java.util.Scanner;

/**
 * Clase encargada de la navegacion dentro de la aplicacion
 */
public class Navegacion {
    private final ControladoraUsuarios controladoraUsuarios;
    private final ControladoraGranjas controladoraGranjas;
    private Usuario usuario = null;

    private Granja granja = null;

    public Navegacion() {
        this.controladoraUsuarios = new ControladoraUsuarios();
        this.controladoraGranjas = new ControladoraGranjas();


    }

    /**
     * Menu principal de la aplicacion, permite crear una granja y el acceso a los modelos.usuarios
     */
    public void menuPrincipal() {
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
                        controladoraGranjas.generarGranja();
                    } catch (UsuarioYaExistenteException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    try {
                        this.setUsuario(controladoraUsuarios.ingresarDatos());
                        int opcionSeleccionada = usuario.mostrarMenu();
                        /// switch con opciones segun tipo de usuario
                        if(this.getUsuario() instanceof Administrador) {
                            switch (opcionSeleccionada) {
                                case 1:
                                    break;
                                case 2:
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (this.getUsuario() instanceof Empleado) {
                            switch (opcionSeleccionada) {
                                case 1:
                                    granja.alimentarGallinas();
                                    break;
                                case 2:
                                    System.out.println(granja.obtenerEstadoGallinas());
                                    System.out.println(granja.calcularPromediosEstados());
                                    break;
                                case 3:
                                    System.out.println(granja.recogerHuevos());
                                    break;
                                case 4:
                                    System.out.println(granja.revisarVidaUtilGallinas());
                                    break;
                                default:
                                    System.out.println("Opción no válida");
                                    break;
                            }
                        }
                    } catch (UsuarioNoValidoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    System.out.println(controladoraGranjas.mostrarGranjas());
                    break;
                case "4":
                    System.out.println(controladoraUsuarios.mostrarUsuarios());
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

    /**
     * Carga la informacion inicial para la generacion de ids
     */
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
