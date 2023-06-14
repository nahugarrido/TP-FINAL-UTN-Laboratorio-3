package modelos.granja;

import enums.Estado;
import enums.Raza;
import modelos.granja.Polluelo;

import java.time.LocalDate;

public abstract class Gallina  extends Polluelo {

    private boolean haComido;
    private double cantidadComida;

    public Gallina(String nombre, LocalDate fechaNacimiento, Raza raza, Estado estado) {
        super(nombre, fechaNacimiento, raza, estado);
    }
    public abstract double comer();
    public abstract void cancelarComida();
    public abstract void modificarEstado();



}
