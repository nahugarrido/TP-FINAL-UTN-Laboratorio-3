package genericas;

import modelos.granja.Lote;

import java.io.Serializable;

public class ListaLotes extends GenericaList<Lote> implements Serializable {

    public GenericaList<Lote> obtenerLotes(int idGranja) {
        GenericaList<Lote> listaAuxiliar = new GenericaList<>();
        for(Lote elemento : listaGenerica) {
            if(elemento.getIdGranja() == idGranja) {
                listaAuxiliar.agregarElemento(elemento);
            }
        }
        return listaAuxiliar;
    }
}
