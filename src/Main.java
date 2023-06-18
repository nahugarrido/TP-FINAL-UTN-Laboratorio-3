import clima.ClimaAPI;
import clima.DatosClima;
import enums.EnumColor;
import enums.EnumRazas;
import modelos.granja.Gallina;
import modelos.granja.Granja;

public class Main {
    public static void main(String[] args) {
        Navegacion navegacion = new Navegacion();
        Navegacion.cargarConfiguracion();
        navegacion.menuPrincipal();
    }

}
