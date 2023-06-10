package enums;

import java.util.HashMap;


public enum Raza {
    SUSSEX, RHODE_ISLAND_RED, LEGHORN;
}

/*public enum Raza {
    RHODE_ISLAND_RED(0,0,0,0,0,0),
    SUSSEX(0,0,0,0,0,0),
    LEGHORN(0,0,0,0,0,0);

    //RHODE_ISLAND_RED, SUSSEX, LEGHORN;
    private double temperaturaMaxima;
    private double temperaturaMinima;
    private HashMap<String, Double> ratioHuevos;

    Raza(double temperaturaMinima, double temperaturaMaxima, double ratioS,double ratioM, double ratioL, double ratioXL) {
        this.temperaturaMinima = temperaturaMinima;
        this.temperaturaMaxima = temperaturaMaxima;
        ratioHuevos = new HashMap<>();
        ratioHuevos.put(TipoHuevo.S.name(), ratioS);
        ratioHuevos.put(TipoHuevo.M.name(), ratioM);
        ratioHuevos.put(TipoHuevo.L.name(), ratioL);
        ratioHuevos.put(TipoHuevo.XL.name(), ratioXL);
    }

    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public HashMap<String, Double> getRatioHuevos() {
        return ratioHuevos;
    }
}*/
