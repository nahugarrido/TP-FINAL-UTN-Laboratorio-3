package genericas;

import java.util.ArrayList;

public interface Generica<T> {
    /// agregar, contar, listar, eliminar
    <T> void agregarElemento(T elemento);

    <T> void eliminarElemento(T elemento);

    int contarElementos();

    String listarElementos();
}
