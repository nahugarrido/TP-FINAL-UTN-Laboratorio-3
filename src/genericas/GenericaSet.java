package genericas;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Clase generica que utiliza internamente un HashSet
 * @param <T> tipo  de dato a utilizar
 */
public class GenericaSet<T> implements Generica<T>, Serializable {

    HashSet<T> setGenerico;

    public GenericaSet() {
        this.setGenerico = new HashSet<>();
    }

    /**
     * Buscar un elemento en el Set
     * @param elemento elemento a buscar
     * @return true si lo encuentra, false si no lo encuentra
     */
    public boolean buscarElemento(T elemento) {
        return setGenerico.contains(elemento);
    }

    /**
     * Agregar un elemento al Set
     * @param elemento elemento a agregar
     * @param <T1> tipo de dato
     */
    @Override
    public <T1> void agregarElemento(T1 elemento) {
        setGenerico.add((T) elemento);
    }

    /**
     * Eliminar un elemento del Set
     * @param elemento elemento a eliminar
     * @param <T1> tipo de dato
     */
    @Override
    public <T1> void eliminarElemento(T1 elemento) {
        setGenerico.remove(elemento);
    }

    /**
     * Contar elementos presentes en el Set
     * @return cantidad de elementos en el Set
     */
    @Override
    public int contarElementos() {
        return setGenerico.size();
    }

    /**
     * Muestra los elementos presentes en el Set
     * @return texto con elementos en el Set
     */
    @Override
    public String listarElementos() {
        StringBuilder texto = new StringBuilder();
        Iterator<T> iterator = setGenerico.iterator();
        while (iterator.hasNext()) {
            T aux = iterator.next();
            texto.append(aux.toString()).append("");
        }
        return texto.toString();
    }
}
