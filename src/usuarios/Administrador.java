package usuarios;

import usuarios.Usuario;

import java.io.Serializable;

public class Administrador extends Usuario implements Serializable {
    public Administrador(String usuario, String clave) {
        super(usuario, clave);
    }

    @Override
    public String toString() {
        return "Administrador{" + super.toString() + "}";
    }

    @Override
    public int compareTo(Usuario aComparar) {
        int retorno = 0;
        if (this.getId() < aComparar.getId()) {
            retorno = -1;
        }
        if (this.getId() > aComparar.getId()) {
            retorno = 1;
        }
        return retorno;
    }
}
