package usuarios;

import usuarios.Usuario;

import java.io.Serializable;

public class Empleado extends Usuario implements Serializable {
    public Empleado(String usuario, String clave) {
        super(usuario, clave);
    }

    @Override
    public String toString() {
        return "Empleado{" + super.toString() + "}";
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
