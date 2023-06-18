package enums;

/// Los valores de vida util estan bajos para que sea mas divertido de testear

public enum EnumRazas {
    RHODE_ISLAND_RED("Rhode Island Red", EnumColor.MEDIO_CLARO,27,3,12),
    SUSSEX("Sussex", EnumColor.CREMA,28,5,10),
    FILIBAR("Filibar", EnumColor.BLANCO,26,-3,8);
    private final String nombreRaza;
    private final EnumColor colorHuevo;
    private final int temperaturaMaximaContenta;
    private final int temperaturaMinimaContenta;
    private final int vidaUtil;

    EnumRazas(String nombreRaza, EnumColor colorHuevo, int temperaturaMaximaContenta, int temperaturaMinimaContenta, int vidaUtil) {
        this.nombreRaza = nombreRaza;
        this.colorHuevo = colorHuevo;
        this.temperaturaMaximaContenta = temperaturaMaximaContenta;
        this.temperaturaMinimaContenta = temperaturaMinimaContenta;
        this.vidaUtil = vidaUtil;
    }

    @Override
    public String toString() {
        return "EnumRazas{" +
                "nombreRaza='" + nombreRaza + '\'' +
                ", colorHuevo=" + colorHuevo +
                ", temperaturaMaximaContenta=" + temperaturaMaximaContenta +
                ", temperaturaMinimaContenta=" + temperaturaMinimaContenta +
                ", vidaUtil=" + vidaUtil +
                '}';
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
        return temperaturaMinimaContenta;
    }

    public int getTemperaturaMinimaContenta() {
        return temperaturaMinimaContenta;
    }

    public int getVidaUtil() {
        return vidaUtil;
    }
}
