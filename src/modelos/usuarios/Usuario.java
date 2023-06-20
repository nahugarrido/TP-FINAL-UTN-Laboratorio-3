package modelos.usuarios;
import interfaces.Entidad;
import otros.GeneradorID;
import java.io.Serializable;

/**
 * Clase usuario, se utiliza para acceder a una granja en concreto
 */
public abstract class Usuario implements Comparable<Usuario>, Serializable, Entidad {
    private final int id;
    private int idGranja;
    private String usuario;
    private String clave;

    /// Hay que cambiar el constructor para el id auto incremental
    public Usuario(String usuario, String clave) {
        this.id = GeneradorID.generarIdUsuario();
        this.usuario = usuario;
        this.clave = clave;
    }

    public abstract int mostrarMenu();

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", idGranja=" + idGranja +
                ", usuario='" + usuario + '\'' +
                ", clave='" + clave + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object aComparar) {
        if(aComparar != null && aComparar instanceof Usuario) {
            return this.getId() == ((Usuario) aComparar).getId() && this.getUsuario().equals(((Usuario) aComparar).getUsuario())  && this.getClave().equals(((Usuario) aComparar).getClave());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 100;
    }

    @Override
    public int compareTo(Usuario aComparar) {
        int retorno = 0;
        if(this.getId() < aComparar.getId()) {
            retorno = -1;
        }
        if(this.getId() > aComparar.getId()) {
            retorno = 1;
        }
        return retorno;
    }

    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getIdGranja() {
        return idGranja;
    }

    public void setIdGranja(int idGranja) {
        this.idGranja = idGranja;
    }
}
