import excepciones.NoHayLotesException;
import genericas.GenericaList;
import genericas.ListaLotes;
import modelos.granja.Lote;
import otros.Serializar;

import java.io.File;

public class ControladoraLotes {
    private ListaLotes listaLotes;

    public ControladoraLotes() {
        this.listaLotes = new ListaLotes();
        leerArchivoLotes();
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

    public GenericaList<Lote> obtenerLotes(int idGranja) throws NoHayLotesException {
        return listaLotes.obtenerLotes(idGranja);
    }

    public double venderLotes(int idGranja, double cotizacionHuevos) throws NoHayLotesException {
        double beneficios = listaLotes.venderLotes(idGranja, cotizacionHuevos);
        this.guardarArchivoLotes();
        return beneficios;
    }

    public void agregarLoteNuevo(Lote loteNuevo) {
        listaLotes.agregarElemento(loteNuevo);
        this.guardarArchivoLotes();
    }

    public void actualizarLote(Lote lote) {
        listaLotes.actualizarElemento(lote);
        this.guardarArchivoLotes();
    }
    public String mostrarLotes() {
        return listaLotes.listarElementos();
    }

    private void setListaLotes(ListaLotes listaLotes) {
        this.listaLotes = listaLotes;
    }
}
