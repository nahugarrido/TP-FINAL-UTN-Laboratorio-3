package genericas;

import interfaces.Entidad;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase generica que implementa un ArrayList dentro
 * @param <T> tipo de dato de variable en ArrayList
 */
public class GenericaList<T extends Entidad> implements Generica<T>, Serializable {

    ArrayList<T> listaGenerica;

    public GenericaList() {
        listaGenerica = new ArrayList<>();
    }

    /**
     * Agregar un elemento nuevo a la lista
     * @param elemento elemento a agregar
     * @param <T1> tipo de dato de elemento
     */
    @Override
    public <T1> void agregarElemento(T1 elemento) {
        listaGenerica.add((T) elemento);
    }

    /**
     * Cuenta los elementos que hay en la lista
     * @return cantidad de elementos
     */
    @Override
    public int contarElementos() {
        return listaGenerica.size();
    }

    /**
     * Elimina un elemento de la lista
     * @param elemento elemento a eliminar
     * @param <T1> tipo de dato de elemento
     */
    @Override
    public <T1> void eliminarElemento(T1 elemento) {
        listaGenerica.remove(elemento);
    }

    /**
     * Muestra los elementos de la lista
     * @return texto con elementos de la lista
     */
    @Override
    public String listarElementos() {
        StringBuilder texto = new StringBuilder();
        for (T elemento : listaGenerica) {
            texto.append(elemento.toString()).append("\n");
        }
        return texto.toString();
    }

    /**
     * Busca un elemento a traves de un id
     * @param id id elemento a buscar
     * @return elemento buscado
     */
    public T buscarPorId(int id) {
        for (T elemento : listaGenerica) {
            if (elemento.getId() == id) {
                return elemento;
            }
        }
        return null;
    }

    /**
     * actualiza el valor de uno de sus elementos
     * @param actualizado elemento actualizado
     */
    public void actualizarElemento(T actualizado) {
        for (T elemento : listaGenerica) {
            if (elemento.getId() == actualizado.getId()) {
                elemento = actualizado;
            }
        }
    }

}
