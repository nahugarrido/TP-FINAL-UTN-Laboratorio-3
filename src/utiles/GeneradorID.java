package utiles;

import otros.Configuracion;
import utiles.Serializar;

public class GeneradorID {
    private static Configuracion configuracion = Configuracion.getInstance();
    public static int generarIdGranja() {
        int nuevoId = configuracion.getContadorGranjas() + 1;
        configuracion.setContadorGranjas(nuevoId);
        return nuevoId;
    }

    public static int generarIdGallina() {
        int nuevoId = configuracion.getContadorGallinas() + 1;
        configuracion.setContadorGallinas(nuevoId);
        return nuevoId;
    }

    public static int generarIdHuevo() {
        int nuevoId = configuracion.getContadorHuevos() + 1;
        configuracion.setContadorHuevos(nuevoId);
        return nuevoId;
    }

    public static int generarIdUsuario() {
        int nuevoId = configuracion.getContadorUsuarios() + 1;
        configuracion.setContadorUsuarios(nuevoId);
        return nuevoId;
    }



}
