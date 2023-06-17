package enums;

public enum EnumRazas {
    RHODE_ISLAND_RED("Rhode Island Red", EnumColor.MEDIO_CLARO,27,3),
    SUSSEX("Sussex", EnumColor.CREMA,28,5),
    FILIBAR("Filibar", EnumColor.BLANCO,26,-3);
    private final String nombreRaza;
    private final EnumColor colorHuevo;
    private final int temperaturaMaximaContenta;
    private final int getTemperaturaMinimaContenta;

    EnumRazas(String nombreRaza, EnumColor colorHuevo, int temperaturaMaximaContenta, int getTemperaturaMinimaContenta) {
        this.nombreRaza = nombreRaza;
        this.colorHuevo = colorHuevo;
        this.temperaturaMaximaContenta = temperaturaMaximaContenta;
        this.getTemperaturaMinimaContenta = getTemperaturaMinimaContenta;
    }

    public String getNombreRaza() {
        return nombreRaza;
    }

    public EnumColor getColorHuevo() {
        return colorHuevo;
    }

    public int getTemperaturaMaximaContenta() {
        return temperaturaMaximaContenta;
    }

    public int getGetTemperaturaMinimaContenta() {
        return getTemperaturaMinimaContenta;
    }
}
