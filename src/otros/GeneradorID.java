package otros;

/**
 * Clase encargada de generar nuevos ids
 */
public class GeneradorID {
    private static Configuracion configuracion = Configuracion.getInstance();

    /**
     * Generar id de granja
     * @return id nuevo
     */
    public static int generarIdGranja() {
        int nuevoId = configuracion.getContadorGranjas() + 1;
        configuracion.setContadorGranjas(nuevoId);
        return nuevoId;
    }

    /**
     * Generar id de gallina
     * @return id nuevo
     */
    public static int generarIdGallina() {
        int nuevoId = configuracion.getContadorGallinas() + 1;
        configuracion.setContadorGallinas(nuevoId);
        return nuevoId;
    }

    /**
     * Generar id de usuario
     * @return id nuevo
     */
    public static int generarIdUsuario() {
        int nuevoId = configuracion.getContadorUsuarios() + 1;
        configuracion.setContadorUsuarios(nuevoId);
        return nuevoId;
    }

    /**
     * Generar id de lote
     * @return id nuevo
     */
    public static int generarIdLote() {
        int nuevoId = configuracion.getContadorLotes() + 1;
        configuracion.setContadorLotes(nuevoId);
        return nuevoId;
    }
}
