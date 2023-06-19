import clima.ClimaAPI;
import clima.DatosClima;
import excepciones.*;
import enums.EnumRazas;
import modelos.granja.Granja;
import modelos.granja.Lote;
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
    private final ControladoraLotes controladoraLotes;

    private Usuario usuario = null;

    private Granja granja = null;

    public Navegacion() {
        this.controladoraUsuarios = new ControladoraUsuarios();
        this.controladoraGranjas = new ControladoraGranjas();
        this.controladoraLotes = new ControladoraLotes();
    }

    /**
     * Menu principal de la aplicacion, permite crear una granja y el acceso a los modelos.usuarios
     */
    public void menuPrincipal() {
        Scanner scan = new Scanner(System.in);
        String opcion;
        boolean continuar = true;
        do {
            System.out.println("MENU PRINCIPAL--------------------------");
            System.out.println("1) Generar granja nueva");
            System.out.println("2) Ingresar a granja");
            System.out.println("3) Ver granjas");
            System.out.println("4) Ver usuarios");
            System.out.println("5) Ver historial lotes");
            System.out.println("*) Modificar una granja *** AGREGAR");
            System.out.println("*) Modificar un usuario *** AGREGAR");
            System.out.println("*) Eliminar una granja *** AGREGAR");
            System.out.println("*) Eliminar un usuario *** AGREGAR");
            System.out.println("6) Salir");
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
                        boolean continuarUsuario = true;
                        /// Se obtiene el usuario
                        this.setUsuario(controladoraUsuarios.ingresarDatos());
                        /// Se selecciona la granja
                        this.setGranja(controladoraGranjas.obtenerGranja(usuario.getIdGranja()));

                        if(granja.getSaldo() < 0) {
                            try {
                                throw new SaldoNegativoException("",granja.getSaldo());
                            } catch (SaldoNegativoException e) {
                                System.out.println(e.getMessage());
                                break;
                            }
                        }

                        /// Se actualiza datos de clima
                        ClimaAPI.actualizarDatosClima(granja.getFecha());

                        do {
                            int opcionSeleccionada = usuario.mostrarMenu();
                            /// switch con opciones segun tipo de usuario
                            if (this.getUsuario() instanceof Administrador) {
                                switch (opcionSeleccionada) {
                                    case 1:
                                        DatosClima datos = ClimaAPI.obtenerDatosClima();
                                        System.out.println("Ciudad: " + datos.getCiudad() + " ,Clima: " + datos.getCodigoTexto() + ", min: " + datos.getTemperaturaMinima() + ", max: " + datos.getTemperaturaMaxima());
                                        break;
                                    case 2:
                                        System.out.println(granja.calcularPromediosEstados());
                                        break;
                                    case 3:
                                        System.out.println("La empresa dispone de: $" + granja.getSaldo() + " pesos para gastar");
                                        break;
                                    case 4:
                                        System.out.println("La empresa dispone de: " + granja.getComidaDisponible() + " kilos de comida.");
                                        break;
                                    case 5:
                                        System.out.println("¿Qué gallina deseas comprar?");
                                        System.out.println("1. Rhode Island Red");
                                        System.out.println("2. Sussex");
                                        System.out.println("3. Filibar");

                                        int opcionGallina = scan.nextInt();
                                        System.out.println("Ingrese la cantidad de gallinas que desea comprar:");
                                        int cantidad = scan.nextInt();

                                        EnumRazas raza = null;

                                        switch (opcionGallina) {
                                            case 1:
                                                raza = EnumRazas.RHODE_ISLAND_RED;
                                                break;
                                            case 2:
                                                raza = EnumRazas.SUSSEX;
                                                break;
                                            case 3:
                                                raza = EnumRazas.FILIBAR;
                                                break;
                                            default:
                                                System.out.println("Opción inválida");
                                                break;
                                        }

                                        if (raza != null) {
                                            // Llamar al método comprarGallinas de la granja pasando la raza y la cantidad
                                            int contador = granja.comprarGallinas(raza, cantidad);
                                            controladoraGranjas.actualizarGranja(granja);
                                            System.out.println("Con el dinero disponible se compraron " + contador + " galllinas");
                                        }
                                        break;
                                    case 6:
                                        System.out.println("Comida disponible: " + granja.getComidaDisponible());
                                        System.out.println("Ingrese cantidad de kilos de alimento que desea comprar: ");
                                        double cant = scan.nextDouble();
                                        System.out.println("Con el dinero disponible se compro un total de " + granja.comprarAlimento(cant) + " kilos de alimento");
                                        break;
                                    case 7:
                                        System.out.println(controladoraLotes.obtenerLotes(granja.getId()).listarElementos());
                                        break;

                                    //// IMPLEMENTAR CASE 8 VENDER LOTES DISPONIBLES

                                    case 9:
                                        try {
                                            int idUsuarioNuevo = controladoraUsuarios.registrarUsuarioEmpleado();
                                            controladoraUsuarios.actualizarIdGranja(idUsuarioNuevo, granja.getId());
                                            granja.agregarUsuarioValido(idUsuarioNuevo);
                                            System.out.println("Usuario creado con exito.");
                                        } catch (UsuarioYaExistenteException e) {
                                            System.out.println(e.getMessage());
                                        }
                                        break;
                                    case 10:
                                        continuarUsuario = false;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (this.getUsuario() instanceof Empleado) {
                                switch (opcionSeleccionada) {
                                    case 1:
                                        DatosClima datos = ClimaAPI.obtenerDatosClima();
                                        System.out.println("Ciudad: " + datos.getCiudad() + " ,Clima: " + datos.getCodigoTexto() + ", min: " + datos.getTemperaturaMinima() + ", max: " + datos.getTemperaturaMaxima());                                        break;
                                    case 2:
                                        System.out.println("Ingrese cantidad de comida en kilogramos: (" + granja.getComidaDisponible() + ")");
                                        double comida = Double.parseDouble(scan.nextLine());
                                        try {
                                            granja.alimentarGallinas(comida);

                                        } catch (ComidaNoSuficienteException | HanComidoException e) {
                                            System.out.println(e.getMessage());
                                        }
                                        break;
                                    case 3:
                                        System.out.println(granja.matarGallinasVidaUtil());
                                        break;
                                    case 4:
                                        System.out.println(granja.obtenerEstadoGallinas());
                                        System.out.println(granja.calcularPromediosEstados());
                                        break;
                                    case 5:

                                        try {
                                            Lote nuevoLote = granja.recogerHuevos();
                                            controladoraLotes.agregarLoteNuevo(nuevoLote);
                                            controladoraGranjas.actualizarGranja(granja);
                                            System.out.println("Has generado un nuevo lote de huevos");
                                            System.out.println(nuevoLote.toString());
                                        } catch (LoteVacioExcepcion | RecolectarHuevosException e) {
                                            System.out.println(e.getMessage());
                                        }
                                        break;
                                    case 6:
                                        System.out.println(granja.revisarVidaUtilGallinas());
                                        break;
                                    case 7:
                                        try {
                                            granja.avanzarUnDia();
                                            System.out.println(granja.madreNaturaleza());
                                            System.out.println(granja.costosFijos());

                                            /// se encuentra aca porque debemos printear el texto de costos fijos antes de tirarla
                                            if (granja.getSaldo() < 0) {
                                                throw new SaldoNegativoException("", granja.getSaldo());
                                            }

                                        }
                                         catch (RecolectarHuevosException e) {
                                            System.out.println(e.getMessage());
                                        } catch (SaldoNegativoException e) {
                                            System.out.println(e.getMessage());
                                            continuarUsuario = false;
                                        }


                                        break;
                                    case 8:
                                        controladoraGranjas.actualizarGranja(granja);
                                        continuarUsuario = false;
                                        break;
                                    default:
                                        System.out.println("Opción no válida");
                                        break;
                                }
                            }
                        } while (continuarUsuario);
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
                    System.out.println(controladoraLotes.mostrarLotes());
                    break;
                case "6":
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

    public Granja getGranja() {
        return granja;
    }

    public void setGranja(Granja granja) {
        this.granja = granja;
    }
}
