package otros;

import modelos.Granja;
import utiles.Serializar;

import java.io.File;
import java.util.ArrayList;

public class ControladoraGranjas {
    private static Granja activa;

    public ControladoraGranjas() {

    }
    public static String mostrarGranjas() {
        Serializar serializar = new Serializar();
        StringBuilder texto = new StringBuilder();
        ArrayList<Granja> listaGranjas = null;
        File archivo = new File("granjas");
        if(archivo.exists()) {
            Object aux = serializar.deserializar("granjas");
            listaGranjas = (ArrayList<Granja>) aux;
        }


        for(Granja granja : listaGranjas) {
            texto.append(granja.toString()).append("\n");
        }

        return texto.toString();
    }
}
