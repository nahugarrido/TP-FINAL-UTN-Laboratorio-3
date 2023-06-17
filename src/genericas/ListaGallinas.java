package genericas;

import modelos.granja.Gallina;
import modelos.usuarios.Usuario;

import java.io.Serializable;

public class ListaGallinas extends GenericaList<Gallina> {


    @Override
    public int contarElementos() {
        return super.contarElementos();
    }

    @Override
    public <T1> void eliminarElemento(T1 elemento) {
        super.eliminarElemento(elemento);
    }

    @Override
    public String listarElementos() {
        return super.listarElementos();
    }

    @Override
    public Gallina buscarPorId(int id) {
        return super.buscarPorId(id);
    }

    @Override
    public void actualizarElemento(Gallina actualizado) {
        super.actualizarElemento(actualizado);
    }
}
