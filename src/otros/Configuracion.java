package otros;

/// esta clase la vamos a utilizar para poder generar los ids autoincrementales y no tener que usar UUID
public class Configuracion {
    private static int contadorGallinas = 0;
    private static int contadorGranjas = 0;
    private  static int contadorHuevos = 0;

    public Configuracion() {
    }
    public static int getContadorGallinas() {
        return contadorGallinas;
    }

    public static int getContadorGranjas() {
        return contadorGranjas;
    }

    public static int getContadorHuevos() {
        return contadorHuevos;
    }

    public static void setContadorGallinas(int contadorGallinas) {
        Configuracion.contadorGallinas = contadorGallinas;
        /// persistir informacion en archivo
    }

    public static void setContadorGranjas(int contadorGranjas) {
        Configuracion.contadorGranjas = contadorGranjas;
        /// persistir informacion en archivo

    }

    public static void setContadorHuevos(int contadorHuevos) {
        Configuracion.contadorHuevos = contadorHuevos;
        /// persistir informacion en archivo

    }
}
