import clima.ClimaAPI;
import clima.DatosClima;
import enums.EnumColor;
import modelos.granja.Gallina;
import modelos.granja.Granja;

public class Main {
    public static void main(String[] args) {

        Gallina gallinita = new Gallina(EnumColor.BLANCO);
        for(int i = 0; i<80; i++) {
            gallinita.comer(100);
            System.out.println(gallinita.toString());
        }

        Navegacion navegacion = new Navegacion();
        Navegacion.cargarConfiguracion();
        navegacion.menuPrincipal();
    }

}


        /// Testear la logica de avanzar en los dias
        /*Granja granja = new Granja("2023-01-01");
        DatosClima datos  = ClimaAPI.obtenerDatosClima();
        System.out.println(datos.toString());

        System.out.println(granja.toString());
        for(int i = 0; i < 31; i++) {
            granja.avanzarUnDia();
            datos  = ClimaAPI.obtenerDatosClima();
            System.out.println(datos.toString());
            System.out.println(granja.toString());
        }*/