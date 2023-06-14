package genericas;

import interfaces.Entidad;
import java.io.Serializable;
import java.util.ArrayList;

public class GenericaList<T extends Entidad> implements Generica<T>, Serializable {

    ArrayList<T> listaGenerica;

    public GenericaList() {
        listaGenerica = new ArrayList<>();
    }

    @Override
    public <T1> void agregarElemento(T1 elemento) {
        listaGenerica.add((T) elemento);
    }

    @Override
    public int contarElementos() {
        return listaGenerica.size();
    }

    @Override
    public <T1> void eliminarElemento(T1 elemento) {
        listaGenerica.remove(elemento);
    }

    @Override
    public String listarElementos() {
        StringBuilder texto = new StringBuilder();
        for (T elemento : listaGenerica) {
            texto.append(elemento.toString()).append("\n");
        }
        return texto.toString();
    }

    public T buscarPorId(int id) {
        for (T elemento : listaGenerica) {
            if (elemento.getId() == id) {
                return elemento;
            }
        }
        return null;
    }

    public void actualizarElemento(T actualizado) {
        for (T elemento : listaGenerica) {
            if (elemento.getId() == actualizado.getId()) {
                elemento = actualizado;
            }
        }
    }

}
