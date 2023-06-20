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
    private int contadorUsuarios = 0;
    private int contadorLotes = 0;

    /*private int contadorHuevos = 0;*/
    private Configuracion() {
    }

    @Override
    public String toString() {
        return "Configuracion{" +
                "contadorGallinas=" + contadorGallinas +
                ", contadorGranjas=" + contadorGranjas +
                ", contadorUsuarios=" + contadorUsuarios +
                ", contadorLotes=" + contadorLotes +
                '}';
    }

    /**
     * Obtener instancia de configuracion
     * @return instancia de configuracion
     */
    public static Configuracion getInstance() {
        if (instancia == null) {
            instancia = new Configuracion();
        }
        return instancia;
    }

    /**
     * Leer archivo con una instancia previa de configuracion
     * @return instancia de configuracion
     */
    public static Configuracion leerArchivo() {
        if (Configuracion.instancia == null) {
            Configuracion.instancia = serializar.deserializar("configuracion");
        }
        return Configuracion.instancia;
    }

    /**
     * obtener contador de gallinas
     * @return contador de gallinas
     */
    public int getContadorGallinas() {
        return this.contadorGallinas;
    }

    /**
     * obtener contador de granjas
     * @return contador de granjas
     */
    public int getContadorGranjas() {
        return this.contadorGranjas;
    }

    /**
     * obtener contador de usuarios
     * @return contador de usuarios
     */
    public int getContadorUsuarios() {
        return contadorUsuarios;
    }

    /**
     * obtener contador de lotes
     * @return contador de lotes
     */
    public int getContadorLotes() {
        return contadorLotes;
    }

    /**
     * Actualizar contador de gallinas
     * @param contadorGallinas nuevo valor
     */
    public void setContadorGallinas(int contadorGallinas) {
        this.contadorGallinas = contadorGallinas;
        Configuracion.guardarEnArchivo();
    }

    /**
     * Actualizar contador de granjas
     * @param contadorGranjas nuevo valor
     */
    public void setContadorGranjas(int contadorGranjas) {
        this.contadorGranjas = contadorGranjas;
        Configuracion.guardarEnArchivo();
    }

    /**
     * Actualizar contador de usuarios
     * @param contadorUsuarios nuevo valor
     */
    public void setContadorUsuarios(int contadorUsuarios) {
        this.contadorUsuarios = contadorUsuarios;
        Configuracion.guardarEnArchivo();
    }

    /**
     * Actualizar contador de lotes
     * @param contadorLotes nuevo valor
     */
    public void setContadorLotes(int contadorLotes) {
        this.contadorLotes = contadorLotes;
        Configuracion.guardarEnArchivo();
    }

    public static void guardarEnArchivo() {
        serializar.serializar(Configuracion.getInstance(), "configuracion");
    }
}
