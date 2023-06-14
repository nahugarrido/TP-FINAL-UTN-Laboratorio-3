package modelos.granja;

import enums.Raza;
import enums.TipoHuevo;
import otros.GeneradorID;

import java.time.LocalDate;

public class Huevo {

    private int id;
    private TipoHuevo tipo;
    private Raza raza;
    private LocalDate fechaPostura;

    public Huevo(TipoHuevo tipo, Raza raza, LocalDate fechaPostura) {
        this.id = GeneradorID.generarIdHuevo();
        this.tipo = tipo;
        this.raza = raza;
        this.fechaPostura = fechaPostura;
    }

    public int getId() {
        return id;
    }

    public TipoHuevo getTipo() {
        return tipo;
    }

    public Raza getRaza() {
        return raza;
    }

    public LocalDate getFechaPostura() {
        return fechaPostura;
    }
}
