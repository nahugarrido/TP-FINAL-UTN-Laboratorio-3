package genericas;

import enums.EnumColor;
import enums.EnumEstado;
import modelos.granja.Gallina;

import java.io.Serializable;
import java.util.Iterator;

/**
 *  Esta clase representa una lista genérica de gallinas.
 */
public class ListaGallinas extends GenericaList<Gallina> implements Serializable {

    /**
     * Alimenta a todas las gallinas en la lista con la cantidad de comida disponible.
     * Devuelve la cantidad de comida restante después de alimentar a las gallinas.
     * @param comidaDisponible comida disponible
     * @return cantidad restante
     */
    public double alimentarGallinas(double comidaDisponible) {
        double comidaTotal = comidaDisponible;
        int comidaConsumida = 0;
        for (Gallina gallina : listaGenerica) {
            comidaConsumida = gallina.comer(comidaTotal);
            if (comidaTotal > 0) {
                comidaTotal -= ( (double) comidaConsumida / 1000); // Convertir gramos a kilos
            }

            /// se alterna el estado de la gallina
            gallina.alterarEstado();
        }

        return comidaTotal;
    }

    /**
     * Recoge los huevos de todas las gallinas en la lista.
     * @return mapa con la cantidad de huevos por color
     */
    public GenericaMap<EnumColor, Integer> recogerHuevos() {

        GenericaMap<EnumColor, Integer> huevos = new GenericaMap<>();
        huevos.agregarElemento(EnumColor.MEDIO_CLARO, 0);
        huevos.agregarElemento(EnumColor.CREMA, 0);
        huevos.agregarElemento(EnumColor.BLANCO, 0);

        for (Gallina gallina : listaGenerica) {
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

        return huevos;
    }

    /**
     * Revisa la vida útil de todas las gallinas en la lista
     * @return  devuelve un arreglo con el conteo de gallinas que han alcanzado la vida útil y las que están próximas a alcanzarla.
     */
    public int[] revisarVidaUtilGallinas() {
        int[] contadores = new int[2];
        int gallinasAlcanzaronVidaUtil = 0;
        int gallinasProximasVidaUtil = 0;

        for (Gallina gallina : listaGenerica) {
            int huevosPuestos = gallina.getContadorHistoricoHuevos();
            if (huevosPuestos >= gallina.getRaza().getVidaUtil()) {
                gallinasAlcanzaronVidaUtil++;
            }
            if(huevosPuestos < gallina.getRaza().getVidaUtil() && huevosPuestos >= (gallina.getRaza().getVidaUtil() - 3)) {
                gallinasProximasVidaUtil++;
            }
        }

        contadores[0] = gallinasAlcanzaronVidaUtil;
        contadores[1] = gallinasProximasVidaUtil;

        return contadores;
    }


    /**
     * Obtiene el estado de todas las gallinas en la lista
     * @return devuelve un texto con el ID de cada gallina y su estado.
     */
    public String obtenerEstadoGallinas() {
        StringBuilder texto = new StringBuilder();
        for (Gallina gallina : listaGenerica) {
            texto.append("ID: ").append(gallina.getId()).append(", Estado: ").append(gallina.getEstado().listarElementos()).append("\n");
        }
        return texto.toString();
    }

    /**
     * Calcula los promedios de los estados "feliz" y "estresada" de todas las gallinas en la lista
     * @return devuelve un arreglo con los valores.
     */
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

    /**
     * Mata a las gallinas que han alcanzado la vida útil y las elimina de la lista.
     * @return contador de gallinas eliminadas
     */
    public int matarGallinasPorVidaUtil() {
        int contador = 0;
        Iterator<Gallina> iterator = listaGenerica.iterator();
        while (iterator.hasNext()) {
            Gallina gallina = iterator.next();
            if (gallina.getContadorHistoricoHuevos() >= gallina.getRaza().getVidaUtil()) {
                contador++;
                iterator.remove();
            }
        }
        return contador;
    }

    /**
     * Mata a las gallinas que llevan más de 3 días sin comer y las elimina de la lista.
     * @return contador de gallinas eliminadas
     */
    public int matarGallinasPorHambre() {
        int contador = 0;
        Iterator<Gallina> iterator = listaGenerica.iterator();
        while (iterator.hasNext()) {
            Gallina gallina = iterator.next();
            if (gallina.getDiasSinComer() >= 3) {
                contador++;
                iterator.remove();
            }
        }
        return contador;
    }

    /**
     * Altera el estado de todas las gallinas en la lista según las condiciones de su entorno.
     */
    public void alterarEstadoGallinas() {
        /// se somete a la gallina a las condiciones de su entorno
        for(Gallina gallina : listaGenerica) {
            gallina.alterarEstado();
        }
    }

    /**
     *Aumenta el contador(en uno) de días sin comer de todas las gallinas en la lista.
     */
    public void aumentarContadorDiasSinComer() {
        for(Gallina gallina : listaGenerica) {
            gallina.setDiasSinComer(gallina.getDiasSinComer()+1);
        }
    }

}
