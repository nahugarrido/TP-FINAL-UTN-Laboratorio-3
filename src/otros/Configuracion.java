package otros;

import java.io.Serializable;

/// Esta clase implementa el patron Singleton

/**
 * Clase creada para poder persistir informacion de ids
 */
public class Configuracion implements Serializable {
    private static Serializar serializar = new Serializar();
    private static Configuracion instancia = null;
    private int contadorGallinas = 0;
    private int contadorGranjas = 0;
    private int contadorHuevos = 0;
    private int contadorUsuarios = 0;

    private Configuracion() {
    }

    @Override
    public String toString() {
        return "otros.Configuracion{" +
                "contadorGallinas=" + contadorGallinas +
                ", contadorGranjas=" + contadorGranjas +
                ", contadorHuevos=" + contadorHuevos +
                '}';
    }

    public static Configuracion getInstance() {
        if (instancia == null) {
            instancia = new Configuracion();
        }
        return instancia;
    }

    public static Configuracion leerArchivo() {
        if (Configuracion.instancia == null) {
            Configuracion.instancia = serializar.deserializar("configuracion");
        }
        return Configuracion.instancia;
    }

    public int getContadorGallinas() {
        return this.contadorGallinas;
    }

    public int getContadorGranjas() {
        return this.contadorGranjas;
    }

    public int getContadorHuevos() {
        return this.contadorHuevos;
    }

    public int getContadorUsuarios() {
        return contadorUsuarios;
    }

    public void setContadorGallinas(int contadorGallinas) {
        this.contadorGallinas = contadorGallinas;
        Configuracion.guardarEnArchivo();
    }

    public void setContadorGranjas(int contadorGranjas) {
        this.contadorGranjas = contadorGranjas;
        Configuracion.guardarEnArchivo();
    }

    public void setContadorHuevos(int contadorHuevos) {
        this.contadorHuevos = contadorHuevos;
        Configuracion.guardarEnArchivo();
    }

    public void setContadorUsuarios(int contadorUsuarios) {
        this.contadorUsuarios = contadorUsuarios;
        Configuracion.guardarEnArchivo();
    }

    public static void guardarEnArchivo() {
        serializar.serializar(Configuracion.getInstance(), "configuracion");
    }

}
