package genericas;

import enums.EnumColor;
import excepciones.NoHayLotesException;
import modelos.granja.Lote;

import java.io.Serializable;

/**
 * Esta clase representa una lista genérica de lotes de huevos.
 */
public class ListaLotes extends GenericaList<Lote> implements Serializable {

    /**
     * Obtiene una lista de lotes pertenecientes a una granja específica
     *
     * @param idGranja ID de la granja
     * @return Lista de lotes pertenecientes a la granja.
     * @throws NoHayLotesException no hay lotes disponibles para la granja.
     */
    public GenericaList<Lote> obtenerLotes(int idGranja) throws NoHayLotesException {
        GenericaList<Lote> listaAuxiliar = new GenericaList<>();
        for (Lote elemento : listaGenerica) {
            if (elemento.getIdGranja() == idGranja) {
                listaAuxiliar.agregarElemento(elemento);
            }
        }

        if (listaAuxiliar.contarElementos() == 0) {
            throw new NoHayLotesException("", listaAuxiliar.contarElementos());
        }

        return listaAuxiliar;
    }


    /**
     * Vende los lotes de huevos de una granja y devuelve los ingresos totales.
     *
     * @param idGranja  ID de la granja.
     * @param cotizacionHuevos Cotización actual de los huevos.
     * @return Ingresos totales por la venta de los lotes de huevos.
     * @throws NoHayLotesException Si no hay lotes disponibles para la granja.
     */
    /// HAY QUE VER LA COTIZACION DEL DIA.
    public double venderLotes(int idGranja, double cotizacionHuevos) throws NoHayLotesException {
        double ingresosTotales = 0;

        for (Lote item : listaGenerica) {
            if (item.getIdGranja() == idGranja && !item.isEstaVendido()) {
                double gananciaBlancos = item.obtenerCantidad(EnumColor.BLANCO) * (cotizacionHuevos + 5);
                double gananciaCrema = item.obtenerCantidad(EnumColor.CREMA) * (cotizacionHuevos - 5);
                double gananciaMarrones = item.obtenerCantidad(EnumColor.MEDIO_CLARO) * (cotizacionHuevos);
                double valorLote = gananciaBlancos + gananciaCrema + gananciaMarrones;

                ingresosTotales += valorLote;
                item.setPrecioCompra(valorLote);
                item.setEstaVendido(true);
            }
        }
        if (ingresosTotales == 0) {
            throw new NoHayLotesException("", 0);
        }
        return ingresosTotales;
    }

}
