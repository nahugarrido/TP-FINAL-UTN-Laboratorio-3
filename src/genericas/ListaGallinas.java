package genericas;

import enums.EnumColor;
import modelos.granja.Gallina;
import modelos.usuarios.Usuario;

import java.io.Serializable;

public class ListaGallinas extends GenericaList<Gallina> {


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
            if (gallina.getColorHuevo() == EnumColor.MEDIO_CLARO) {
                huevos.agregarElemento(EnumColor.MEDIO_CLARO,huevos.obtenerValor(EnumColor.MEDIO_CLARO)+ gallina.getCantidadHuevos());
            } else if (gallina.getColorHuevo() == EnumColor.CREMA) {
                huevos.agregarElemento(EnumColor.CREMA,huevos.obtenerValor(EnumColor.CREMA)+ gallina.getCantidadHuevos());
            } else if (gallina.getColorHuevo() == EnumColor.BLANCO) {
                huevos.agregarElemento(EnumColor.BLANCO,huevos.obtenerValor(EnumColor.BLANCO)+ gallina.getCantidadHuevos());
            }
            //incrementamos el contador historico y reseteamos los atributos como cant de huevos que puso ese dia.
            gallina.setContadorHistoricoHuevos(gallina.getContadorHistoricoHuevos() + gallina.getCantidadHuevos());
            gallina.resetearAtributos();
        }

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
            } else if (huevosPuestos > (gallina.getRaza().getVidaUtil() - (gallina.getRaza().getVidaUtil() * (2/3)))) {
                gallinasProximasVidaUtil++;
            }
        }

        contadores[0] = gallinasAlcanzaronVidaUtil;
        contadores[1] = gallinasProximasVidaUtil;

        return contadores;
    }


}
