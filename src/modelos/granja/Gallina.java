package modelos.granja;

import clima.ClimaAPI;
import clima.DatosClima;
import enums.EnumColor;
import enums.EnumEstado;
import enums.EnumRazas;
import genericas.GenericaSet;
import interfaces.Entidad;
import otros.GeneradorID;

import java.io.Serializable;
import java.util.Objects;

/**
 * La clase Gallina modela una gallina en un sistema, permite gestionar su alimentación,
 * controlar la puesta de huevos y modificar su estado en función de diferentes condiciones.
 */
public class Gallina implements Entidad, Serializable {
    private final int id;
    private int diasSinComer;
    private int contadorHistoricoHuevos;
    private int cantidadHuevos;
    private int cantidadComida;
    private EnumRazas raza;
    private EnumColor colorHuevo;
    private GenericaSet<EnumEstado> estado;

    public Gallina(EnumRazas raza) {
        this.id = GeneradorID.generarIdGallina();
        this.diasSinComer = 0;
        this.cantidadHuevos = 0;
        this.cantidadComida = 0;
        this.contadorHistoricoHuevos = 0;
        this.raza = raza;
        this.colorHuevo = raza.getColorHuevo();
        this.estado = new GenericaSet<>();
        estado.agregarElemento(EnumEstado.FELIZ);
    }
    @Override
    public String toString() {
        return "Gallina: " + raza.getNombreRaza() + ", id: " + id +
                ", dias sin comer:" + diasSinComer +
                ", huevos colocados historico: " + contadorHistoricoHuevos +
                ", huevos colocados hoy: " + cantidadHuevos +
                ", cantidad comida hoy: " + cantidadComida +
                ", color de huevos: " + colorHuevo +
                ", estado: " + estado.listarElementos();
    }

    @Override
    public boolean equals(Object aComparar) {
        if(aComparar != null && aComparar instanceof Gallina) {
            return this.getId() == ((Gallina) aComparar).getId() && this.getRaza() == ((Gallina) aComparar).getRaza();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 5;
    }

    /**
     * Se gestiona la alimentacion de la gallina
     * @param comidaDisponible
     * @return cantidad comida por la gallina
     */
    public int comer(double comidaDisponible) {
        /// se convierte la comida en kg a gramos
        int comidaEnGramos = (int) (comidaDisponible * 1000);
        /// se obtiene el random de lo que come la gallina
        int comida = (int) Math.round(Math.random() * 1.2 * 100);

        /// se verifica que no supere el maximo de comida disponible
        if (comida < comidaEnGramos) {
            this.setCantidadComida(comida);
        } else {
            comida = (int) Math.floor(comidaDisponible);
            this.setCantidadComida(comida);
        }

        /// Se aumenta o reinicia la cantidad de dias sin comer
        /// si la gallina comio menos de 10 gramos consideramos que no ha comido
        if(this.getCantidadComida() < 10) {
            this.setDiasSinComer(this.getDiasSinComer() + 1);
        } else {
            this.setDiasSinComer(0);
        }


        return comida;
    }

    /**
     *Controlar la puesta de huevos de una gallina.
     */
    public void ponerHuevos() {
        /// pequeño buffo a la comida para que la gallina tenga un plus al estar feliz
        if (estado.buscarElemento(EnumEstado.FELIZ)) {
            this.setCantidadComida(this.getCantidadComida()+20);
        }

        /// si la gallina comio mas de 60 gramos pone un huevo y si esta dentro de su vida util
        if (this.getCantidadComida() > 60 && this.getContadorHistoricoHuevos() < this.getRaza().getVidaUtil()) {
            this.setCantidadHuevos(this.getCantidadHuevos() + 1);
        }

        /// si la gallina esta estresada no puede poner huevos
        if (estado.buscarElemento(EnumEstado.ESTRESADA)) {
            this.setCantidadHuevos(this.getCantidadHuevos() - 1);
        }

        /// se revisa que no haya un valor negativo en el contador al finalizar el proceso
        if(this.getCantidadHuevos() < 0) {
            this.setCantidadHuevos(0);
        }

        /// se actualiza contador historico de huevos
        this.setContadorHistoricoHuevos(this.getContadorHistoricoHuevos() + this.getCantidadHuevos());
    }

    /**
     * El método se encarga de modificar el estado de una gallina en función
     * de diferentes condiciones.
     */
    public void alterarEstado() {
        DatosClima datos = ClimaAPI.obtenerDatosClima();
        /// Si la temperatura no le gusta a la gallina o no ha comido o el dia no le gusta, la gallina se estresa.
        if(datos.getTemperaturaMaxima() > raza.getTemperaturaMaximaContenta() || datos.getTemperaturaMinima() < raza.getGetTemperaturaMinimaContenta() || this.getDiasSinComer() > 0 || datos.getWMOCodigo() > 60) {
            estado.agregarElemento(EnumEstado.ESTRESADA);
            estado.eliminarElemento(EnumEstado.FELIZ);
        } else {
            /// En caso de que no este estresada por las razones de arriba....
            /// Si la gallina comio 40 gramos de alimento, el cielo esta despejado o parcialmente despejado la gallina esta feliz
            if(this.getCantidadComida() > 40 || datos.getWMOCodigo() == 0 || datos.getWMOCodigo() == 1) {
                estado.agregarElemento(EnumEstado.FELIZ);
                estado.eliminarElemento(EnumEstado.ESTRESADA);
            }
        }
    }

    /**
     * Reseatar atributos cantidadComida y CantidadHuevos
     */
    public void resetearAtributos() {
        this.setCantidadComida(0);
        this.setCantidadHuevos(0);
    }

    @Override
    public int getId() {
        return id;
    }

    public int getDiasSinComer() {
        return diasSinComer;
    }

    public int getCantidadHuevos() {
        return cantidadHuevos;
    }

    public int getCantidadComida() {
        return cantidadComida;
    }

    public GenericaSet<EnumEstado> getEstado() {
        return estado;
    }

    public EnumColor getColorHuevo() {
        return colorHuevo;
    }

    public void setDiasSinComer(int diasSinComer) {
        this.diasSinComer = diasSinComer;
    }

    public void setCantidadHuevos(int cantidadHuevos) {
        this.cantidadHuevos = cantidadHuevos;
    }

    public void setCantidadComida(int cantidadComida) {
        this.cantidadComida = cantidadComida;
    }

    public int getContadorHistoricoHuevos() {
        return contadorHistoricoHuevos;
    }

    public void setContadorHistoricoHuevos(int contadorHistoricoHuevos) {
        this.contadorHistoricoHuevos = contadorHistoricoHuevos;
    }

    public EnumRazas getRaza() {
        return raza;
    }
}
