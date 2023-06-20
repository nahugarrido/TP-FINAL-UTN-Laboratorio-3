package modelos.granja;

import enums.EnumColor;
import genericas.Generica;
import genericas.GenericaMap;
import interfaces.Entidad;
import otros.GeneradorID;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que recolecta los huevos producidos un dia y los agrupa en un lote.
 */
public class Lote implements Entidad, Serializable {
    private final int id;
    private int idGranja;
    private String fecha;
    private GenericaMap<EnumColor, Integer> cantidadHuevos;
    private boolean estaVendido;
    private double precioCompra;

    public Lote(int idGranja, String fecha, GenericaMap<EnumColor, Integer> huevosRecogidos) {
        this.id = GeneradorID.generarIdLote();
        this.idGranja = idGranja;
        this.fecha = fecha;
        this.estaVendido = false;
        this.precioCompra = 0;
        this.cantidadHuevos = huevosRecogidos;
    }

    @Override
    public String toString() {
        return "Lote{" +
                "id=" + id +
                ", idGranja=" + idGranja +
                ", fecha='" + fecha + '\'' +
                ", cantidadHuevos=" + cantidadHuevos.listarElementos() +
                ", estaVendido=" + estaVendido +
                ", precioCompra=" + precioCompra +
                '}';
    }

    @Override
    public boolean equals(Object aComparar) {
        if (aComparar != null && aComparar instanceof Lote) {
            return this.getId() == ((Lote) aComparar).getId() && this.getIdGranja() == ((Lote) aComparar).getIdGranja();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 5;
    }

    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public boolean isEstaVendido() {
        return estaVendido;
    }

    public void setEstaVendido(boolean estaVendido) {
        this.estaVendido = estaVendido;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getIdGranja() {
        return idGranja;
    }

    public Integer obtenerCantidad(EnumColor color) {
        return cantidadHuevos.obtenerValor(color);
    }

}
