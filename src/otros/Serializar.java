package otros;


import java.io.*;

/**
 * Clase generica encarga de persisitr y despersistir la informacion en los archivos
 */
public class Serializar {

    /**
     * Metodo para persistir informacion
     * @param objeto objeto a persisitr
     * @param rutaArchivo ruta del archivo
     * @param <T> tipo de dato del objeto a persisitir
     */
    public <T> void serializar(T objeto, String rutaArchivo) {
        File file = new File(rutaArchivo);

        try {
            ObjectOutputStream fileOut = new ObjectOutputStream( new FileOutputStream(file));
            fileOut.writeObject(objeto);
            fileOut.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para despersistir informacion
     * @param rutaArchivo ruta del archivo
     * @return objeto despersistido
     * @param <T> tipo de daot del objeto a despersistir
     */
    public <T> T deserializar(String rutaArchivo) {
        File file = new File(rutaArchivo);
        T retorno = null;
        try {
            ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream(file));
            Object aux = fileIn.readObject();
            retorno = (T) aux;
            fileIn.close();

            return retorno;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}


