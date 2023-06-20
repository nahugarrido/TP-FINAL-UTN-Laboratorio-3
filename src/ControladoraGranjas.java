import excepciones.UsuarioYaExistenteException;
import genericas.GenericaList;
import modelos.granja.Granja;
import otros.Serializar;

import java.io.File;
import java.util.Scanner;

/**
 * Clase encargada de gestionar el archivo de granjas y todas las operaciones relacionadas con la granja
 */
public class ControladoraGranjas {

    private GenericaList<Granja> listaGranjas;

    public ControladoraGranjas() {
        this.listaGranjas = new GenericaList<>();
        leerArchivoGranjas();
    }

    /**
     * Persiste la informacion en el archivo
     */
    private void guardarArchivoGranjas() {
        Serializar serializar = new Serializar();
        serializar.serializar(listaGranjas, "granjas");
    }

    /**
     * Lee la informacion desde el archivo
     */
    private void leerArchivoGranjas() {
        Serializar serializar = new Serializar();
        GenericaList<Granja> listaGranjas;
        File archivo = new File("granjas");
        if (archivo.exists()) {
            Object aux = serializar.deserializar("granjas");
            listaGranjas = (GenericaList<Granja>) aux;
        } else {
            listaGranjas = new GenericaList<>();
        }

        this.setListaGranjas(listaGranjas);
    }


    /**
     * Permite obtener la informacion de una granja por id
     * @param idGranja id de granja a buscar
     * @return retorna la granja buscada o null
     */
    public Granja obtenerGranja(int idGranja) {
        return listaGranjas.buscarPorId(idGranja);
    }

    /**
     * Actualiza la informacion de una granja sin persistir en archivo
     * @param granja granja a actualizar
     */
    public void actualizarGranja(Granja granja) {
        listaGranjas.actualizarElemento(granja);
        this.guardarArchivoGranjas();
    }

    /**
     * muestra las granjas existentes
     * @return lista de granjas en formato string
     */
    public String mostrarGranjas() {
        return listaGranjas.listarElementos();
    }

    /**
     * Permite generar una granja nueva
     * @throws UsuarioYaExistenteException usuario ya existe en archivo
     */
    public void generarGranja() throws UsuarioYaExistenteException {
        Scanner scan = new Scanner(System.in);
        ControladoraUsuarios controladoraUsuarios = new ControladoraUsuarios();

        System.out.println("MENU CREAR GRANJA---------------");
        System.out.println("Ingrese el nombre de la granja: ");
        String nombre = scan.nextLine();
        System.out.println("Ingrese una fecha en formato (yyyy-mm-dd)");
        String fecha = scan.nextLine();

        int idUsuario = controladoraUsuarios.registrarUsuarioAdmin();
        Granja nuevaGranja = new Granja(nombre, fecha, idUsuario);
        controladoraUsuarios.actualizarIdGranja(idUsuario, nuevaGranja.getId());

        this.leerArchivoGranjas(); /// esta linea creo que puede removerse
        listaGranjas.agregarElemento(nuevaGranja);

        this.guardarArchivoGranjas();
    }

    /**
     * Eliminar una granja
     * @param granja granja a eliminar
     * @return texto exito
     */
    public String eliminarGranja(Granja granja) {
        listaGranjas.eliminarElemento(granja);
        this.guardarArchivoGranjas();

        return "Granja eliminada con exito";
    }

    /**
     * Actualizar lista de granjas
     * @param listaGranjas lista actualizada
     */
    private void setListaGranjas(GenericaList<Granja> listaGranjas) {
        this.listaGranjas = listaGranjas;
    }

}
