package modelos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Granja {
    private String fecha;
    private DatosClima datosClima;
    public Granja(String fecha) {
        this.fecha = fecha;
        try {
            DatosClima datos = new DatosClima();
            datos.fromJSON(obtenerDatos(this.fecha));
            this.datosClima = datos;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Granja{" +
                "fecha='" + fecha + '\'' +
                ", " + datosClima.toString() +
                '}';
    }

    public String ingresarFecha() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Debes ingresar la fecha en el formato: yyyy-mm-dd");
        System.out.println("INGRESA UNA FECHA POR FAVOR:" );
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

        /// Obtener datos del clima de la fecha nueva
        try {
           JSONObject json =  this.obtenerDatos(this.getFecha());
           DatosClima datos = new DatosClima();
           datos.fromJSON(json);
           this.setDatosClima(datos);
        } catch (JSONException e) {
            System.out.println(e);
        }
    }

    private JSONObject obtenerDatos(String fecha) throws JSONException {
        JSONObject datos = new JSONObject(ClimaAPI.getInfo(fecha));
        return datos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public DatosClima getDatosClima() {
        return datosClima;
    }

    public void setDatosClima(DatosClima datosClima) {
        this.datosClima = datosClima;
    }
}
