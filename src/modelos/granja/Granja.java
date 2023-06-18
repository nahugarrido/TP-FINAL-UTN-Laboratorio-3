package modelos.granja;

import clima.ClimaAPI;
import enums.EnumEstado;
import enums.EnumColor;
import genericas.Generica;
import genericas.GenericaMap;
import genericas.ListaGallinas;
import interfaces.Entidad;
import otros.GeneradorID;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        this.saldo = 50;
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

    public void alimentarGallinas(double comidaKg) {
        if (comidaKg > this.getComidaDisponible()) {
            //throw new Exception()...
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
        StringBuilder texto = new StringBuilder();
        for (Gallina gallina : listaGallinas) {
            texto.append("ID: ").append(gallina.getId()).append(", Estado: ").append(gallina.getEstado().listarElementos()).append("\n");
        }
        return texto.toString();
    }

    public String calcularPromediosEstados() {
        int totalGallinas = listaGallinas.size();
        int countFelices = 0;
        int countEstresadas = 0;

        for (Gallina gallina : listaGallinas) {
            if (gallina.getEstado().buscarElemento(EnumEstado.FELIZ)) {
                countFelices++;
            } else if (gallina.getEstado().buscarElemento(EnumEstado.ESTRESADA)) {
                countEstresadas++;
            }
        }

        double promedioFelices = (double) countFelices / totalGallinas * 100;
        double promedioEstresadas = (double) countEstresadas / totalGallinas * 100;

        return "Promedio de gallinas felices: " + promedioFelices + "%\n" +
                "Promedio de gallinas estresadas: " + promedioEstresadas + "%";
    }


    public GenericaMap<EnumColor, Integer> recogerHuevos() {
        GenericaMap<EnumColor, Integer> huevosRecogidos = listaGallinas.recogerHuevos();
        return
    }


    public String revisarVidaUtilGallinas() {
        int totalGallinas = listaGallinas.contarElementos();
        int[] contadores = listaGallinas.revisarVidaUtilGallinas();
        double promedioAlcanzaronVidaUtil = (double) contadores[0] / totalGallinas * 100;
        double promedioProximasVidaUtil = (double) contadores[1] / totalGallinas * 100;

        return "Promedio de gallinas que alcanzaron su vida útil: " + promedioAlcanzaronVidaUtil + "%\n" +
                "Promedio de gallinas próximas a alcanzar su vida útil: " + promedioProximasVidaUtil + "%";
    }


}
