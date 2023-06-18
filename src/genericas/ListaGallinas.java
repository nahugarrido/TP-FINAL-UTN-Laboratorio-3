package genericas;

import enums.EnumColor;
import enums.EnumEstado;
import modelos.granja.Gallina;

import java.io.Serializable;

public class ListaGallinas extends GenericaList<Gallina> implements Serializable {

    public double alimentarGallinas(double comidaDisponible) {
        double comidaTotal = comidaDisponible;
        int comidaConsumida = 0;
        for (Gallina gallina : listaGenerica) {
            comidaConsumida = gallina.comer(comidaTotal);
            if (comidaTotal > 0) {
                comidaTotal -= (comidaConsumida / 1000); // Convertir gramos a kilos
            } else {
                break;
            }
        }
        return comidaTotal;
    }

    public GenericaMap<EnumColor, Integer> recogerHuevos() {

        GenericaMap<EnumColor, Integer> huevos = new GenericaMap<>();
        huevos.agregarElemento(EnumColor.MEDIO_CLARO, 0);
        huevos.agregarElemento(EnumColor.CREMA, 0);
        huevos.agregarElemento(EnumColor.BLANCO, 0);

        for (Gallina gallina : listaGenerica) {
            /// se somete a la gallina a las condiciones de su entorno
            gallina.alterarEstado();
            /// la gallina pone los huevos
            gallina.ponerHuevos();
            if (gallina.getColorHuevo() == EnumColor.MEDIO_CLARO) {
                huevos.agregarElemento(EnumColor.MEDIO_CLARO, huevos.obtenerValor(EnumColor.MEDIO_CLARO) + gallina.getCantidadHuevos());
            } else if (gallina.getColorHuevo() == EnumColor.CREMA) {
                huevos.agregarElemento(EnumColor.CREMA, huevos.obtenerValor(EnumColor.CREMA) + gallina.getCantidadHuevos());
            } else if (gallina.getColorHuevo() == EnumColor.BLANCO) {
                huevos.agregarElemento(EnumColor.BLANCO, huevos.obtenerValor(EnumColor.BLANCO) + gallina.getCantidadHuevos());
            }
            //incrementamos el contador historico y reseteamos los atributos como cant de huevos que puso ese dia.
            gallina.setContadorHistoricoHuevos(gallina.getContadorHistoricoHuevos() + gallina.getCantidadHuevos());
            /// se resetean los atributos de la gallina
            gallina.resetearAtributos();
        }
        System.out.println("PRUEBA: " + huevos.listarElementos());
        return huevos;
    }

    public int[] revisarVidaUtilGallinas() {
        int[] contadores = new int[2];
        int gallinasAlcanzaronVidaUtil = 0;
        int gallinasProximasVidaUtil = 0;

        for (Gallina gallina : listaGenerica) {
            int huevosPuestos = gallina.getContadorHistoricoHuevos();
            if (huevosPuestos >= gallina.getRaza().getVidaUtil()) {
                gallinasAlcanzaronVidaUtil++;
            } else if (huevosPuestos > (gallina.getRaza().getVidaUtil() - (gallina.getRaza().getVidaUtil() * (2 / 3)))) {
                gallinasProximasVidaUtil++;
            }
        }

        contadores[0] = gallinasAlcanzaronVidaUtil;
        contadores[1] = gallinasProximasVidaUtil;

        return contadores;
    }


    public String obtenerEstadoGallinas() {
        StringBuilder texto = new StringBuilder();
        for (Gallina gallina : listaGenerica) {
            texto.append("ID: ").append(gallina.getId()).append(", Estado: ").append(gallina.getEstado().listarElementos()).append("\n");
        }
        return texto.toString();
    }

    public double[] calcularPromediosEstados() {
        double[] contadores = new double[2];
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

    public int matarGallinasPorVidaUtil() {
        int contador = 0;
        for (Gallina gallina : listaGenerica) {
            if (gallina.getContadorHistoricoHuevos() >= gallina.getRaza().getVidaUtil()) {
                contador++;
                listaGenerica.remove(gallina);
            }
        }
        return contador;
    }

    public int matarGallinasPorHambre() {
        int contador = 0;
        for (Gallina gallina : listaGenerica) {
            if (gallina.getDiasSinComer() >= 3) {
                contador++;
                listaGenerica.remove(gallina);
            }
        }
        return contador;
    }

}
