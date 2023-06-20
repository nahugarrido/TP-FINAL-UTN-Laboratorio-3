package genericas;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase generica que utiliza un HashMap internamente
 * @param <K> Key
 * @param <T> Valor
 */
public class GenericaMap<K,T> implements Serializable {

    HashMap<K, T> mapGenerico;

    public GenericaMap() {
        this.mapGenerico = new HashMap<>();
    }

    /**
     * Agregar un elemento nuevo al map
     * @param key clave
     * @param elemento valor
     */
    public  void agregarElemento(K key, T elemento) {
        mapGenerico.put(key, elemento);
    }

    /**
     * Eliminar un elemento del map
     * @param key clave del elemento
     */
    public  void eliminarElemento(K key) {
        mapGenerico.remove(key);
    }

    /**
     * Obtener el valor asociado a una clave
     * @param key clave
     * @return
     */
    public T obtenerValor(K key) {
        return mapGenerico.get(key);
    }

    /**
     * Contar cantidad de elementos en el map
     * @return cantidad de elementos
     */
    public int contarElementos() {
        return mapGenerico.size();
    }

    /**
     * Muestra los elementos presentes en el map
     * @return texto con los elementos
     */
    public String listarElementos() {
        StringBuilder texto  = new StringBuilder();
        for(Map.Entry<K,T> elemento : mapGenerico.entrySet()) {
            texto.append(elemento.toString()).append('\'');
        }
        return texto.toString();
    }
}
