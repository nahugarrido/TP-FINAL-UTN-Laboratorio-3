package modelos;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

public class ClimaAPI {
    public static String getInfo(String fecha) {
        System.out.println("Fecha dentro de API: " + fecha);

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
