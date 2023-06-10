package modelos;

import enums.Estado;
import enums.Raza;
import utiles.GeneradorID;

import java.time.LocalDate;

public class Polluelo {
    private int id;
    private String Nombre;
    private LocalDate fechaNacimiento;
    private Raza raza;
    private Estado estado;

    public Polluelo(String nombre, LocalDate fechaNacimiento, Raza raza, Estado estado) {
        this.id = GeneradorID.generarIdGallina();
        Nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.raza = raza;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return Nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Raza getRaza() {
        return raza;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
