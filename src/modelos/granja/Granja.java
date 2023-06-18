package modelos.granja;

import clima.ClimaAPI;
import enums.EnumColor;
import enums.EnumRazas;
import genericas.GenericaMap;
import genericas.ListaGallinas;
import genericas.ListaLotes;
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
        return listaGallinas.obtenerEstadoGallinas();
    }

    public String calcularPromediosEstados() {
        double[] estados = listaGallinas.calcularPromediosEstados();
       return "Promedio de gallinas felices: " + estados[0] + "%\n" +
               "Promedio de gallinas estresadas: " + estados[1] + "%";
    }


    public GenericaMap<EnumColor, Integer> recogerHuevos() {
        GenericaMap<EnumColor, Integer> huevosRecogidos = listaGallinas.recogerHuevos();
        return huevosRecogidos;
    }


    public String revisarVidaUtilGallinas() {
        int totalGallinas = listaGallinas.contarElementos();
        int[] contadores = listaGallinas.revisarVidaUtilGallinas();
        double promedioAlcanzaronVidaUtil = (double) contadores[0] / totalGallinas * 100;
        double promedioProximasVidaUtil = (double) contadores[1] / totalGallinas * 100;

        return "Promedio de gallinas que alcanzaron su vida útil: " + promedioAlcanzaronVidaUtil + "%\n" +
                "Promedio de gallinas próximas a alcanzar su vida útil: " + promedioProximasVidaUtil + "%";
    }


    public int comprarGallinas(EnumRazas raza, int cantidad) {
        double precioPorGallina = 0.0;

        switch (raza) {
            case RHODE_ISLAND_RED:
                precioPorGallina = 10.0;
                break;
            case SUSSEX:
                precioPorGallina = 12.0;
                break;
            case FILIBAR:
                precioPorGallina = 8.0;
                break;
        }

        double costoTotal = precioPorGallina * cantidad;

        if (costoTotal <= this.getSaldo()){ // Verificar si el saldo es suficiente para realizar la compra
            for (int i = 0; i < cantidad; i++) { // Realizar la compra de las gallinas y actualizar el saldo
                Gallina gallina = new Gallina(raza);
                listaGallinas.agregarElemento(gallina);
            }
            this.setSaldo(this.getSaldo() -costoTotal);
        }

        else {
            int cantidadAsequible = (int) (this.getSaldo() / precioPorGallina);
            // Realizar la compra de las gallinas asequibles y actualizar el saldo
            for (int i = 0; i < cantidadAsequible; i++) {
                Gallina gallina = new Gallina(raza);
                listaGallinas.agregarElemento(gallina);
            }
            this.setSaldo(this.getSaldo()- (cantidadAsequible* precioPorGallina));
        }

        return cantidad;
    }


    public double comprarAlimento(double cantidad) {
        double precioPorKilo = 10.0; // Precio del alimento por kilo
        double maxCantidad = this.getSaldo() / precioPorKilo; // Cantidad máxima que se puede comprar con el saldo disponible

        if (cantidad <= maxCantidad) {
            double costoTotal = precioPorKilo * cantidad;
            this.setComidaDisponible(this.getComidaDisponible() + cantidad);
            this.setSaldo(this.getSaldo() - costoTotal);
            return cantidad; // Se compra la cantidad solicitada
        } else if (maxCantidad > 0) {
            double costoTotal = precioPorKilo * maxCantidad;
            this.setComidaDisponible(this.getComidaDisponible() + cantidad);
            this.setSaldo(this.getSaldo() - costoTotal);
            return maxCantidad; // Se compra la cantidad máxima posible
        } else {
            return 0; // No se puede comprar ninguna cantidad
        }

    }




}
