package otros;

public class GeneradorID {
    public static int generarIdGranja() {
        int nuevoId = Configuracion.getContadorGranjas();
        Configuracion.setContadorGranjas(nuevoId+1);
        return nuevoId;
    }

    public static int generarIdGallina() {
        int nuevoId = Configuracion.getContadorGallinas();
        Configuracion.setContadorGallinas(nuevoId+1);
        return nuevoId;
    }

    public static int generarIdHuevo() {
        int nuevoId = Configuracion.getContadorHuevos();
        Configuracion.setContadorHuevos(nuevoId+1);
        return nuevoId;
    }



}
