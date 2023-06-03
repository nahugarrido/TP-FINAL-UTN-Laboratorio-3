package modelos;

public class GeneradorID {
    private static int ultimoID = 0;

    public static int generarID() {
        ultimoID++;
        // this.guardarUltimoID();
        return ultimoID;
    }

    public static void guardarUltimoID() {
        // persiste la informacion en la granja del ultimo id
    }
}
