package otros;


import java.io.*;

public class Serializar {

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


