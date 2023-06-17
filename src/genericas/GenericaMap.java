package genericas;

import java.util.HashMap;
import java.util.Map;

public class GenericaMap<K,T> {

    HashMap<K, T> mapGenerico;

    public GenericaMap() {
        this.mapGenerico = new HashMap<>();
    }

    public  void agregarElemento(K key, T elemento) {
        mapGenerico.put(key, elemento);
    }


    public  void eliminarElemento(K key) {
        mapGenerico.remove(key);
    }

    public T obtenerValor(K key) {
        return mapGenerico.get(key);
    }

    public int contarElementos() {
        return mapGenerico.size();
    }

    public String listarElementos() {
        StringBuilder texto  = new StringBuilder();
        for(Map.Entry<K,T> elemento : mapGenerico.entrySet()) {
            texto.append(elemento.toString()).append("\n");
        }
        return texto.toString();
    }
}
