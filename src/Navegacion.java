import clima.ClimaAPI;
import clima.DatosClima;
import excepciones.*;
import enums.EnumRazas;
import genericas.GenericaList;
import modelos.granja.Granja;
import modelos.granja.Lote;
import modelos.usuarios.Administrador;
import modelos.usuarios.Empleado;
import modelos.usuarios.Usuario;
import otros.Configuracion;

import java.io.File;
import java.util.Random;
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
        Random random = new Random();
        boolean continuar = true;
        do {
            System.out.println("MENU PRINCIPAL--------------------------");
            System.out.println("1) Generar granja nueva");
            System.out.println("2) Ingresar a granja");
            System.out.println("3) Ver granjas");
            System.out.println("4) Ver usuarios");
            System.out.println("5) Ver historial lotes");
            System.out.println("6) Modificar una granja");
            System.out.println("7) Modificar un usuario");
            System.out.println("8) Eliminar una granja");
            System.out.println("9) Eliminar un usuario");
            System.out.println("10) Salir");
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

                        if (granja.getSaldo() < 0) {
                            try {
                                throw new SaldoNegativoException("", granja.getSaldo());
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
                                        System.out.println("Ciudad: " + datos.getCiudad() + ", Clima: " + datos.getCodigoTexto() + ", min: " + datos.getTemperaturaMinima() + ", max: " + datos.getTemperaturaMaxima());
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
                                        int contador = granja.comprarGallinas();
                                        controladoraGranjas.actualizarGranja(granja);
                                        System.out.println("Se han comprado  " + contador + " galllinas, tu nuevo saldo es $" + granja.getSaldo());
                                        break;
                                    case 6:
                                        System.out.println("COMIDA DISPONIBLE: " + granja.getComidaDisponible());
                                        System.out.println("PRECIO POR KILO: $180");
                                        System.out.println("Ingrese kilos de comida a comprar: ");
                                        double cant = scan.nextDouble();
                                        System.out.println("Con el dinero disponible se compro un total de " + granja.comprarAlimento(cant) + " kilos de alimento");
                                        break;
                                    case 7:
                                        try {
                                            GenericaList<Lote> lotes = controladoraLotes.obtenerLotes(granja.getId());
                                            System.out.println(lotes.listarElementos());
                                        } catch (NoHayLotesException e) {
                                            System.out.println(e.getMessage());
                                        }
                                        break;
                                    case 8:
                                        double auxRandom = random.nextDouble() * 100;
                                        double cotizacionHuevos = 0;
                                        if (auxRandom < 40) {
                                            cotizacionHuevos = 20;
                                        } else if (auxRandom < 80) {
                                            cotizacionHuevos = 30;
                                        } else {
                                            cotizacionHuevos = 50;
                                        }

                                        System.out.println("COTIZACIONES DE HUEVOS DEL DIA------------");
                                        System.out.println("1) HUEVO BLANCO $" + (cotizacionHuevos + 5));
                                        System.out.println("2) HUEVO CREMA $" + (cotizacionHuevos - 5));
                                        System.out.println("3) HUEVO MEDIO CLARO $" + cotizacionHuevos);
                                        System.out.println("DESEAS LIQUIDAR TUS LOTES? (y/n):");
                                        String opcionCotizaciones = scan.nextLine();
                                        if (opcionCotizaciones.equals("y")) {
                                            try {
                                                double beneficios = controladoraLotes.venderLotes(granja.getId(), cotizacionHuevos);
                                                granja.setSaldo(granja.getSaldo() + beneficios);
                                                System.out.println("Has vendido tus lotes disponibles y has obtenido $" + beneficios);

                                            } catch (NoHayLotesException e) {
                                                System.out.println(e.getMessage());
                                            }
                                        } else {
                                            System.out.println("Puedes vender tus lotes cuando quieras!");
                                        }
                                        break;
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
                                        System.out.println("Ciudad: " + datos.getCiudad() + ", Clima: " + datos.getCodigoTexto() + ", min: " + datos.getTemperaturaMinima() + ", max: " + datos.getTemperaturaMaxima());
                                        break;
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

                                        } catch (RecolectarHuevosException e) {
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
                    String continuarModificar = "";
                    System.out.println("INGRESE EL ID DE LA GRANJA A MODIFICAR:");

                    int idModificar;

                    try {
                        idModificar = Integer.parseInt(scan.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("No es un id valido.");
                        break;
                    }
                    Granja granjaModificar = controladoraGranjas.obtenerGranja(idModificar);

                    System.out.println(granjaModificar.toString());
                    System.out.println("Deseas cambiar el nombre de la granja?(y/n)");
                    continuarModificar = scan.nextLine();
                    if (continuarModificar.equals("y")) {
                        System.out.println("INGRESE EL NUEVO NOMBRE: ");
                        String nuevoNombre = scan.nextLine();
                        granjaModificar.setNombre(nuevoNombre);
                    }

                    System.out.println("Deseas cambiar el saldo de la granja?(y/n)");
                    continuarModificar = scan.nextLine();
                    if (continuarModificar.equals("y")) {
                        System.out.println("INGRESE EL NUEVO SALDO: ");
                        String nuevoSaldo = scan.nextLine();
                        granjaModificar.setSaldo(Double.parseDouble(nuevoSaldo));
                    }


                    System.out.println("Deseas cambiar la fecha de la granja?(y/n)");
                    continuarModificar = scan.nextLine();
                    if (continuarModificar.equals("y")) {
                        System.out.println("INGRESE LA NUEVA FECHA: formato (yyyy-mm-dd)");
                        String nuevaFecha = scan.nextLine();
                        granjaModificar.setFecha(nuevaFecha);
                    }

                    System.out.println(granjaModificar.toString());
                    controladoraGranjas.actualizarGranja(granjaModificar);
                    System.out.println("Datos de la granja actualizados con exito.");

                    break;
                case "7":
                    continuarModificar = "";
                    System.out.println("INGRESE EL ID DEL USUARIO A MODIFICAR:");

                    int idModificarUsuario;

                    try {
                        idModificarUsuario = Integer.parseInt(scan.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("No es un id valido.");
                        break;
                    }

                    Usuario usuarioModificar = controladoraUsuarios.obtenerUsuario(idModificarUsuario);

                    System.out.println("Deseas cambiar la clave del usuario?(y/n)");
                    continuarModificar = scan.nextLine();
                    if (continuarModificar.equals("y")) {
                        System.out.println("INGRESE LA NUEVA CLAVE: ");
                        String nuevaClave = scan.nextLine();
                        usuarioModificar.setClave(nuevaClave);
                    }

                    System.out.println(usuarioModificar.toString());
                    controladoraUsuarios.actualizarUsuario(usuarioModificar);
                    System.out.println("Datos del Usuario actualizados con exito.");
                    break;
                case "8":
                    continuarModificar = "";
                    System.out.println("INGRESE EL ID DE LA GRANJA A BORRAR:");
                    int idBorrar;
                    try {
                        idBorrar = Integer.parseInt(scan.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("No es un id valido.");
                        break;
                    }
                    Granja granjaBorrar = controladoraGranjas.obtenerGranja(idBorrar);

                    System.out.println(granjaBorrar.toString());
                    System.out.println("Estas seguro de que deseas eliminar esta granja?(y/n)");
                    continuarModificar = scan.nextLine();
                    if (continuarModificar.equals("y")) {
                        System.out.println(controladoraGranjas.eliminarGranja(granjaBorrar));

                    }
                    break;
                case "9":
                    continuarModificar = "";
                    System.out.println("INGRESE EL ID DE LA GRANJA A BORRAR:");
                    int idBorrarUsuario;
                    try {
                        idBorrarUsuario = Integer.parseInt(scan.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("No es un id valido.");
                        break;
                    }

                    Usuario usuarioBorrar = controladoraUsuarios.obtenerUsuario(idBorrarUsuario);

                    System.out.println(usuarioBorrar.toString());
                    System.out.println("Estas seguro de que deseas eliminar este usuario?(y/n)");
                    continuarModificar = scan.nextLine();
                    if (continuarModificar.equals("y")) {
                        System.out.println(controladoraUsuarios.eliminarUsuario(usuarioBorrar));
                    }
                    break;
                case "10":
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
