package modelos.granja;

import clima.ClimaAPI;
import enums.EnumColor;
import excepciones.ComidaNoSuficienteException;
import excepciones.LoteVacioExcepcion;
import genericas.GenericaMap;
import genericas.ListaGallinas;
import interfaces.Entidad;
import otros.GeneradorID;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Scanner;

public class Granja implements Serializable, Entidad {
    private final int id;
    private String nombre;
    private String fecha;
    private HashSet<Integer> usuariosValidos;
    private double comidaDisponible;
    private int gallinasMuertas;
    private double saldo;
    private ListaGallinas listaGallinas;

    public Granja(String nombre, String fecha, int idUsuario) {
        this.id = GeneradorID.generarIdGranja();
        this.nombre = nombre;
        this.fecha = fecha;
        this.usuariosValidos = new HashSet<>();
        usuariosValidos.add(idUsuario);
        this.comidaDisponible = 10;
        this.gallinasMuertas = 0;
        this.saldo = 5000;
        this.listaGallinas = new ListaGallinas();
        ClimaAPI.actualizarDatosClima(this.getFecha());
    }

    @Override
    public String toString() {
        return "Granja{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", usuariosValidos=" + usuariosValidos +
                '}';
    }

    public String ingresarFecha() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Debes ingresar la fecha en el formato: yyyy-mm-dd");
        System.out.println("INGRESA UNA FECHA POR FAVOR:");
        String leerFecha = scan.nextLine();

        this.setFecha(leerFecha);

        return "La fecha ingresada es: " + this.getFecha();
    }

    public void avanzarUnDia() {
        LocalDate fechaDate = LocalDate.parse(this.getFecha());

        // Agregar un día a la fecha anterior
        LocalDate nuevaFecha = fechaDate.plusDays(1);

        // Formatear la nueva fecha como una cadena en el formato "yyyy-mm-dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nuevaFechaStr = nuevaFecha.format(formatter);

        // Actualizar fecha
        this.setFecha(nuevaFechaStr);

        // Actualizar datos del clima
        ClimaAPI.actualizarDatosClima(this.getFecha());

        /// LOGICA DE AVANZAR UN DIA


    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public HashSet<Integer> getUsuariosValidos() {
        return usuariosValidos;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getComidaDisponible() {
        return comidaDisponible;
    }

    public void setComidaDisponible(double comidaDisponible) {
        this.comidaDisponible = comidaDisponible;
    }

    public int getGallinasMuertas() {
        return gallinasMuertas;
    }

    public void setGallinasMuertas(int gallinasMuertas) {
        this.gallinasMuertas = gallinasMuertas;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void agregarUsuarioValido(int idUsuarioNuevo) {
        usuariosValidos.add(idUsuarioNuevo);
    }

    public void alimentarGallinas(double comidaKg) throws ComidaNoSuficienteException {
        if (comidaKg > this.getComidaDisponible()) {
            throw new ComidaNoSuficienteException("",this.getComidaDisponible(), comidaKg);
        } else {
            /// descontamos el valor a darle a las gallinas
            this.setComidaDisponible(this.getComidaDisponible() - comidaKg);
            /// realizamos la operacion
            double comidaRestante = listaGallinas.alimentarGallinas(comidaKg);
            /// actualizamos el valor de la comida en la granja en caso de que haya sobrado
            this.setComidaDisponible(this.getComidaDisponible() + comidaRestante);
        }


    }

    public String obtenerEstadoGallinas() {
        return listaGallinas.obtenerEstadoGallinas();
    }

    public String calcularPromediosEstados() {
        double[] estados = listaGallinas.calcularPromediosEstados();
       return "Promedio de gallinas felices: " + estados[0] + "%\n" +
               "Promedio de gallinas estresadas: " + estados[1] + "%";
    }


    public Lote recogerHuevos() throws LoteVacioExcepcion {
        GenericaMap<EnumColor, Integer> huevosRecogidos = listaGallinas.recogerHuevos();
        if(huevosRecogidos.obtenerValor(EnumColor.MEDIO_CLARO) == 0 && huevosRecogidos.obtenerValor(EnumColor.BLANCO) == 0 && huevosRecogidos.obtenerValor(EnumColor.CREMA) == 0) {
            throw new LoteVacioExcepcion("No hay huevos que recoger");
        }
        Lote nuevoLote = new Lote(this.getId(),this.getFecha(), huevosRecogidos);
        return nuevoLote;
    }


    public String revisarVidaUtilGallinas() {
        int totalGallinas = listaGallinas.contarElementos();
        int[] contadores = listaGallinas.revisarVidaUtilGallinas();
        double promedioAlcanzaronVidaUtil = (double) contadores[0] / totalGallinas * 100;
        double promedioProximasVidaUtil = (double) contadores[1] / totalGallinas * 100;

        return "Promedio de gallinas que alcanzaron su vida útil: " + promedioAlcanzaronVidaUtil + "%\n" +
                "Promedio de gallinas próximas a alcanzar su vida útil: " + promedioProximasVidaUtil + "%";
    }

    public String matarGallinasVidaUtil() {
        String texto = "";
        double random = Math.random();

        if(random > 0) {
            texto = "Has ido a matar a Cleta, pero has visto sus brillantes ojos y no has podido hacerlo.";
        } else if(random > 0.20) {
            texto = "Has tratado de matar a Turuleca, pero sus hijos observaban y te has arrepentido.";
        } else if(random > 0.40) {
            texto = "Has tratado de matar a Julia, pero es programadora y simio no mata simio.";
        } else if(random > 0.60) {
            texto = "Te has tropezado agarrando el cuchillo, decides que hoy no mataras a Cleta.";
        }

        int contador = listaGallinas.matarGallinasPorVidaUtil();
        this.setGallinasMuertas(this.getGallinasMuertas() + contador);

        if(contador > 0) {
            texto = "Hoy a dios has desafiado y " + contador + " vidas pollunas te has quedado.";
        }

        return texto;
    }

    public String madreNaturaleza() {
        String texto = "Hoy ha sido un dia pacifico y calmado en " + this.getNombre() + ".";
        int contador = listaGallinas.matarGallinasPorHambre();
        this.setGallinasMuertas(this.getGallinasMuertas() + contador);
        if(contador > 0) {
            texto = "Hoy la madre naturaleza ha obrado y tu sin " + contador + " gallinas te has quedado.";
        }
        return texto;
    }


}
