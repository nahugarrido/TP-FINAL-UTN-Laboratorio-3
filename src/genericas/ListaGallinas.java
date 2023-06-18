package genericas;

import enums.EnumEstado;
import modelos.granja.Gallina;
import modelos.usuarios.Usuario;

import java.io.Serializable;

public class ListaGallinas extends GenericaList<Gallina> {



    public String obtenerEstadoGallinas() {
        StringBuilder texto = new StringBuilder();
        for (Gallina gallina : listaGenerica) {
            texto.append("ID: ").append(gallina.getId()).append(", Estado: ").append(gallina.getEstado().listarElementos()).append("\n");
        }
        return texto.toString();
    }

    public double[] calcularPromediosEstados() {
        double [] contadores = new double[2];
        int totalGallinas = listaGenerica.size();
        int countFelices = 0;
        int countEstresadas = 0;
        for (Gallina gallina : listaGenerica) {
            if (gallina.getEstado().buscarElemento(EnumEstado.FELIZ)) {
                countFelices++;
            } else if (gallina.getEstado().buscarElemento(EnumEstado.ESTRESADA)) {
                countEstresadas++;
            }
        }
        double promedioFelices = (double) countFelices / totalGallinas * 100;
        double promedioEstresadas = (double) countEstresadas / totalGallinas * 100;
        contadores[0] = promedioFelices;
        contadores[1] = promedioEstresadas;

        return contadores;
    }

}
