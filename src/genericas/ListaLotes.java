package genericas;

import enums.EnumColor;
import excepciones.NoHayLotesException;
import modelos.granja.Lote;

import java.io.Serializable;

public class ListaLotes extends GenericaList<Lote> implements Serializable {

    public GenericaList<Lote> obtenerLotes(int idGranja) throws NoHayLotesException {
        GenericaList<Lote> listaAuxiliar = new GenericaList<>();
        for(Lote elemento : listaGenerica) {
            if(elemento.getIdGranja() == idGranja) {
                listaAuxiliar.agregarElemento(elemento);
            }
        }

        if(listaAuxiliar.contarElementos() == 0) {
            throw new NoHayLotesException("", listaAuxiliar.contarElementos());
        }

        return listaAuxiliar;
    }

    /// HAY QUE VER LA COTIZACION DEL DIA.
    public double venderLotes(int idGranja, double cotizacionHuevos) throws NoHayLotesException {
        double ingresosTotales = 0;

        for(Lote item : listaGenerica) {
            if(item.getIdGranja() == idGranja) {
                double gananciaBlancos = item.obtenerCantidad(EnumColor.BLANCO) * (cotizacionHuevos + 5);
                double gananciaCrema = item.obtenerCantidad(EnumColor.CREMA) * (cotizacionHuevos - 5);
                double gananciaMarrones = item.obtenerCantidad(EnumColor.MEDIO_CLARO) * (cotizacionHuevos);
                double valorLote = gananciaBlancos + gananciaCrema + gananciaMarrones;

                ingresosTotales += valorLote;
                item.setPrecioCompra(valorLote);
                item.setEstaVendido(true);
            }
        }
        if(ingresosTotales == 0) {
            throw new NoHayLotesException("", 0);
        }
        return ingresosTotales;
    }

}
