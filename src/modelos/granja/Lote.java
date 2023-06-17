package modelos.granja;

import enums.EnumColor;
import genericas.GenericaMap;
import interfaces.Entidad;
import otros.GeneradorID;

public class Lote implements Entidad {
    private final int id;
    private int idGranja;
    private String fecha;
    private GenericaMap<EnumColor, Integer> cantidadHuevos;
    private boolean estaVendido;
    private double precioCompra;

    public Lote(int idGranja, String fecha, int huevosMarrones, int huevosBlanco, int huevosCrema) {
        this.id = GeneradorID.generarIdLote();
        this.idGranja = idGranja;
        this.fecha = fecha;
        this.estaVendido = false;
        this.precioCompra = 0;
        this.cantidadHuevos = new GenericaMap<>();
        this.cantidadHuevos.agregarElemento(EnumColor.MEDIO_CLARO, huevosMarrones);
        this.cantidadHuevos.agregarElemento(EnumColor.BLANCO, huevosBlanco);
        this.cantidadHuevos.agregarElemento(EnumColor.CREMA, huevosCrema);
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

    public String verLote() {
        return cantidadHuevos.listarElementos();
    }
}
