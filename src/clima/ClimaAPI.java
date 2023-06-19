package clima;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Clase encargada de gestionar toda la informacion relacionada con el clima
 */
public class ClimaAPI {
    private static DatosClima datosClima;

    static {
        datosClima = new DatosClima();
    }

    /**
     * Retorna instancia de DatosClima
     * @return
     */
    public static DatosClima obtenerDatosClima() {
        return datosClima;
    }

    /**
     * Actualiza el atributo datosClima de la clase con la informacion nueva de la API
     * @param fecha (yyyy-mm-dd)
     */
    public static void actualizarDatosClima(String fecha) {
        try {
            JSONObject json_datos = new JSONObject(ClimaAPI.getInfo(fecha));
            datosClima.fromJSON(json_datos);
        } catch (JSONException e) {
            /// llamada recursiva a funcion
            actualizarDatosClima(fecha);
        }
    }

    /**
     * Realiza peticion a la API de Clima "Open Meteo"
     * @param fecha (yyyy-mm-dd)
     * @return JSON en formato String
     */
    private static String getInfo(String fecha) {
        System.out.println("Realizando conexion a API... ");

        String apiURL = "https://archive-api.open-meteo.com/v1/archive?latitude=-38.00&longitude=-57.56" + "&start_date=" + fecha + "&end_date=" + fecha + "&hourly=temperature_2m,relativehumidity_2m,apparent_temperature,precipitation,rain,weathercode&daily=weathercode,apparent_temperature_max,apparent_temperature_min,apparent_temperature_mean&timezone=America%2FSao_Paulo";

        try {
            URL url = new URL(apiURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Codigo de error: " + responseCode);
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    stringBuilder.append(scanner.nextLine());
                }
                scanner.close();

                return stringBuilder.toString();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

}
