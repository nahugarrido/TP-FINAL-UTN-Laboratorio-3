package modelos;

import enums.CodigosClima;
import interfaces.IToJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DatosClima implements IToJSON {
    private String ciudad;
    private int WMOCodigo;
    private String CodigoTexto;
    private int humedad;
    private double temperatura;
    private double temperaturaMaxima;
    private double temperaturaMinima;

    DatosClima() {

    }

    @Override
    public String toString() {
        return "DatosClima{" +
                "ciudad='" + ciudad + '\'' +
                ", WMOCodigo=" + WMOCodigo +
                ", CodigoTexto='" + CodigoTexto + '\'' +
                ", humedad=" + humedad +
                ", temperatura=" + temperatura +
                ", temperaturaMaxima=" + temperaturaMaxima +
                ", temperaturaMinima=" + temperaturaMinima +
                '}';
    }

    @Override
    public JSONObject toJOSN() throws JSONException {
        return null;
    }

    @Override
    public void fromJSON(JSONObject jsonObject) throws JSONException {
        System.out.println("SEBA: " +jsonObject);
        JSONObject json_hourly = jsonObject.getJSONObject("hourly");
        JSONArray json_temperature = json_hourly.getJSONArray("temperature_2m");
        JSONArray json_relative_humidity = json_hourly.getJSONArray("relativehumidity_2m");
        JSONObject json_daily = jsonObject.getJSONObject("daily");
        JSONArray json_array_weathercode = json_daily.getJSONArray("weathercode");
        JSONArray json_array_temp_max = json_daily.getJSONArray("apparent_temperature_max");
        JSONArray json_array_temp_min = json_daily.getJSONArray("apparent_temperature_min");

        /// calcular temperatura promedio
        double acumuladorTemperatura = 0;
        for(int i = 0; i < json_temperature.length(); i++) {
            acumuladorTemperatura += (double) json_temperature.get(i);
        }
        double temperaturaPromedio = acumuladorTemperatura / (int) json_temperature.length();

        int acumuladorHumedad = 0;
        for(int i = 0; i < json_relative_humidity.length(); i++) {
            acumuladorHumedad += (int) json_relative_humidity.get(i);
        }

        int humedadPromedio = (int) acumuladorHumedad / json_relative_humidity.length();

        /// Testear que la información este llegando correctamente
        /*System.out.println("JSON Temperatura: " + json_temperature);
        System.out.println("JSON Humedad: " + json_relative_humidity);
        System.out.println("JSON Daily" + json_daily);
        System.out.println("Weathercode: " + (int) json_array_weathercode.get(0));
        System.out.println("temperatura maxima: " + (double) json_array_temp_max.get(0));
        System.out.println("Temperatura minima: " + (double) json_array_temp_min.get(0));
        System.out.println("Temperatura promedio: " + temperaturaPromedio);
        System.out.println("Humedad promedio: " + humedadPromedio);*/

        /// Persistir la informacion en atributos del objeto
        this.setCiudad("Mar del Plata");
        this.setTemperaturaMaxima((double) json_array_temp_max.get(0));
        this.setTemperaturaMinima((double) json_array_temp_min.get(0));
        this.setWMOCodigo((int) json_array_weathercode.get(0));
        this.setTemperatura(temperaturaPromedio);
        this.setHumedad(humedadPromedio);
        this.setCodigoTexto(CodigosClima.getTraduccion(this.getWMOCodigo()));
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getWMOCodigo() {
        return WMOCodigo;
    }

    public void setWMOCodigo(int WMOCodigo) {
        this.WMOCodigo = WMOCodigo;
    }

    public String getCodigoTexto() {
        return CodigoTexto;
    }

    public void setCodigoTexto(String codigoTexto) {
        CodigoTexto = codigoTexto;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(double temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(double temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public int getHumedad() {
        return humedad;
    }

    public void setHumedad(int humedad) {
        this.humedad = humedad;
    }
}
