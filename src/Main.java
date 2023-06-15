import clima.ClimaAPI;
import clima.DatosClima;
import modelos.granja.Granja;

public class Main {
    public static void main(String[] args) {
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