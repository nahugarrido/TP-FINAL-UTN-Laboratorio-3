package genericas;

import genericas.Generica;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

public class GenericaSet<T> implements Generica<T>, Serializable {

    HashSet<T> setGenerico;

    public GenericaSet() {
        this.setGenerico = new HashSet<>();
    }

    public boolean buscarElemento(T elemento) {
        return setGenerico.contains(elemento);
    }

    @Override
    public <T1> void agregarElemento(T1 elemento) {
        setGenerico.add((T) elemento);
    }

    @Override
    public <T1> void eliminarElemento(T1 elemento) {
        setGenerico.remove(elemento);
    }

    @Override
    public int contarElementos() {
        return setGenerico.size();
    }

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
