package modelos;

import enums.Estado;
import enums.Raza;

import java.time.LocalDate;
import java.util.HashMap;

public class Sussex extends Gallina {
    private static double tempMaximaSoportada;
    private static double tempMinimaSoportada;
    private static HashMap<String, Double> ratioHuevos;

    static {
        tempMaximaSoportada = 0;
        tempMinimaSoportada = 0;
        ratioHuevos = new HashMap<>();
        ratioHuevos.put("S",0.25);
        ratioHuevos.put("M",0.25);
        ratioHuevos.put("L",0.25);
        ratioHuevos.put("XL",0.25);
    }

    public Sussex(String nombre, LocalDate fechaNacimiento, Raza raza, Estado estado) {
        super(nombre, fechaNacimiento, raza, estado);
    }

    @Override
    public double comer() {
        return 0;
    }

    @Override
    public void cancelarComida() {

    }

    @Override
    public void modificarEstado() {

    }

    public static double getTempMaximaSoportada() {
        return tempMaximaSoportada;
    }

    public static double getTempMinimaSoportada() {
        return tempMinimaSoportada;
    }

}
