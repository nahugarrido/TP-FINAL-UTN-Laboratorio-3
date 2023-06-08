package modelos;

import enums.Raza;
import enums.TipoHuevo;

import java.time.LocalDate;
import java.util.UUID;

public class Huevo {

    private int id;
    private TipoHuevo tipo;
    private Raza raza;
    private LocalDate fechaPostura;

    public Huevo(TipoHuevo tipo, Raza raza, LocalDate fechaPostura) {
        this.id = 0; /// aca se debe usar el generador de id
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
