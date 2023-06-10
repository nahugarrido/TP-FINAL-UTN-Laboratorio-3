package modelos;

import clima.ClimaAPI;
import utiles.GeneradorID;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Scanner;

public class Granja implements Serializable {
    private int id;
    private String nombre;
    private String fecha;
    private HashSet<Integer> usuariosValidos;

    public Granja(String nombre, String fecha, int idUsuario) {
        this.id = GeneradorID.generarIdGranja();
        this.nombre = nombre;
        this.fecha = fecha;
        this.usuariosValidos = new HashSet<>();
        usuariosValidos.add(idUsuario);
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

}
