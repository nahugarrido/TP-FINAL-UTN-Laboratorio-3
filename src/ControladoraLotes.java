import genericas.GenericaList;
import genericas.ListaLotes;
import modelos.granja.Lote;
import otros.Serializar;

import java.io.File;

public class ControladoraLotes {
    private ListaLotes listaLotes;

    public ControladoraLotes() {
        this.listaLotes = new ListaLotes();
    }

    private void guardarArchivoLotes() {
        Serializar serializar = new Serializar();
        serializar.serializar(listaLotes, "lotes");
    }

    /**
     * Lee la informacion desde el archivo
     * @return retorna la informacion en formato GenericaList<Lote>
     */
    private GenericaList<Lote> leerArchivoLotes() {
        Serializar serializar = new Serializar();
        ListaLotes listaLotes;
        File archivo = new File("lotes");
        if (archivo.exists()) {
            Object aux = serializar.deserializar("lotes");
            listaLotes = (ListaLotes) aux;
        } else {
            listaLotes = new ListaLotes();
        }

        this.setListaLotes(listaLotes);
        return listaLotes;
    }

    public Lote obtenerLote(int idLote) {
        return listaLotes.buscarPorId(idLote);
    }

    public GenericaList<Lote> obtenerLotes(int idGranja) {
        return listaLotes.obtenerLotes(idGranja);
    }

    public void agregarLoteNuevo(Lote loteNuevo) {
        listaLotes.agregarElemento(loteNuevo);
    }

    public void actualizarLote(Lote lote) {
        listaLotes.actualizarElemento(lote);
    }
    public String mostrarLotes() {
        return listaLotes.listarElementos();
    }

    private void setListaLotes(ListaLotes listaLotes) {
        this.listaLotes = listaLotes;
    }
}
