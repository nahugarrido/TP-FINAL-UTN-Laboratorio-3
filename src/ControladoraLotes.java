import excepciones.NoHayLotesException;
import genericas.GenericaList;
import genericas.ListaLotes;
import modelos.granja.Lote;
import otros.Serializar;

import java.io.File;

/**
 * Clase encargada de gestionar el archivo de lotes y todas las operaciones relacionadas con los lotes
 */
public class ControladoraLotes {
    private ListaLotes listaLotes;

    public ControladoraLotes() {
        this.listaLotes = new ListaLotes();
        leerArchivoLotes();
    }

    /**
     * Guardar informacion en archivo de lotes
     */
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

    /**
     * Obtener un lote por id
     * @param idLote id del lote a obtener
     * @return lote
     */
    public Lote obtenerLote(int idLote) {
        return listaLotes.buscarPorId(idLote);
    }

    /**
     * Obtener lotes por un id de granja
     * @param idGranja id granja
     * @return Generico de lotes
     * @throws NoHayLotesException No hay lotes a retornar
     */
    public GenericaList<Lote> obtenerLotes(int idGranja) throws NoHayLotesException {
        return listaLotes.obtenerLotes(idGranja);
    }

    /**
     * Vender lotes segun un id de granja
     * @param idGranja id de granja
     * @param cotizacionHuevos cotizacion de huevos
     * @return valor total de la venta
     * @throws NoHayLotesException No hay lotes relacionados con ese idGranja
     */
    public double venderLotes(int idGranja, double cotizacionHuevos) throws NoHayLotesException {
        double beneficios = listaLotes.venderLotes(idGranja, cotizacionHuevos);
        this.guardarArchivoLotes();
        return beneficios;
    }

    /**
     * Agregar un lote nuevo al archivo
     * @param loteNuevo lote a agregar
     */
    public void agregarLoteNuevo(Lote loteNuevo) {
        listaLotes.agregarElemento(loteNuevo);
        this.guardarArchivoLotes();
    }

    /**
     * Actualizar un lote
     * @param lote lote a actualizar
     */
    public void actualizarLote(Lote lote) {
        listaLotes.actualizarElemento(lote);
        this.guardarArchivoLotes();
    }

    /**
     * Listar los lotes del archivo
     * @return String con lista de lotes
     */
    public String mostrarLotes() {
        return listaLotes.listarElementos();
    }

    /**
     * Actualizar el valor de la lista de lotes
     * @param listaLotes lista de lotes nueva
     */
    private void setListaLotes(ListaLotes listaLotes) {
        this.listaLotes = listaLotes;
    }
}
