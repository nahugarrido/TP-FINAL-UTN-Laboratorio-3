package modelos.granja;

import clima.ClimaAPI;
import enums.EnumEstado;
import enums.EnumColor;
import interfaces.Entidad;
import otros.GeneradorID;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Granja implements Serializable, Entidad {
    private int id;
    private String nombre;
    private String fecha;
    private HashSet<Integer> usuariosValidos;
    private double comidaDisponible;
    private int gallinasMuertas;
    private double saldo;
    private ArrayList<Gallina> listaGallinas;

    public Granja(String nombre, String fecha, int idUsuario ) {
        this.id = GeneradorID.generarIdGranja();
        this.nombre = nombre;
        this.fecha = fecha;
        this.usuariosValidos = new HashSet<>();
        usuariosValidos.add(idUsuario);
        this.comidaDisponible = 10;
        this.gallinasMuertas =0;
        this.saldo= 50;
        this.listaGallinas= new ArrayList<>();
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


    public void alimentarGallinas() {
        double comidaDisponibleEnKilos = this.comidaDisponible;
        int totalComidaConsumida = 0;

        for (Gallina gallina : listaGallinas) {
            int comidaConsumida = gallina.comer(comidaDisponibleEnKilos);
            totalComidaConsumida += comidaConsumida;
            comidaDisponibleEnKilos -= (comidaConsumida / 1000.0); // Convertir gramos a kilos
        }

        // Actualizar la cantidad de comida disponible en la granja
        this.comidaDisponible -= totalComidaConsumida / 1000; // Convertir gramos a kilos
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


    public String recogerHuevos() {
        int huevosMedioClaro = 0;
        int huevosCrema = 0;
        int huevosBlanco = 0;

        for (Gallina gallina : listaGallinas) {
            if (gallina.getColorHuevo() == EnumColor.MEDIO_CLARO) {
                huevosMedioClaro += gallina.getCantidadHuevos();
            } else if (gallina.getColorHuevo() == EnumColor.CREMA) {
                huevosCrema += gallina.getCantidadHuevos();
            } else if (gallina.getColorHuevo() == EnumColor.BLANCO) {
                huevosBlanco += gallina.getCantidadHuevos();
            }

            //incrementamos el contador historico y reseteamos los atributos como cant de huevos que puso ese dia.
            gallina.setContadorHistoricoHuevos(gallina.getCantidadHuevos()+ gallina.getContadorHistoricoHuevos());
            gallina.resetearAtributos();
        }

        return "Huevos recogidos:\n" +
                "Medio Claro: " + huevosMedioClaro + "\n" +
                "Crema: " + huevosCrema + "\n" +
                "Blanco: " + huevosBlanco;
    }


    public String revisarVidaUtilGallinas() {
        int huevosPorVidaUtil = 100;  // Cantidad de huevos que una gallina puede poner antes de alcanzar su vida útil
        int gallinasAlcanzaronVidaUtil = 0;
        int gallinasProximasVidaUtil = 0;
        int totalGallinas = listaGallinas.size();

        for (Gallina gallina : listaGallinas) {
            int huevosPuestos = gallina.getContadorHistoricoHuevos();

            if (huevosPuestos >= huevosPorVidaUtil) {
                gallinasAlcanzaronVidaUtil++;
            } else if (huevosPuestos > (huevosPorVidaUtil - 10)) {
                gallinasProximasVidaUtil++;
            }
        }

        double promedioAlcanzaronVidaUtil = (double) gallinasAlcanzaronVidaUtil / totalGallinas * 100;
        double promedioProximasVidaUtil = (double) gallinasProximasVidaUtil / totalGallinas * 100;

        return "Promedio de gallinas que alcanzaron su vida útil: " + promedioAlcanzaronVidaUtil + "%\n" +
                "Promedio de gallinas próximas a alcanzar su vida útil: " + promedioProximasVidaUtil + "%";
    }


}
