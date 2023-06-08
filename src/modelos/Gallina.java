package modelos;

import enums.Estado;
import enums.Raza;

import java.time.LocalDate;
import java.util.HashMap;

public class Gallina  extends Polluelo {

    public Gallina(String nombre, LocalDate fechaNacimiento, Raza raza, Estado estado) {
        super(nombre, fechaNacimiento, raza, estado);
    }
}
