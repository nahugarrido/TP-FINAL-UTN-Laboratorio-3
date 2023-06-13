package clima;

/**
 * Codigos de clima de Open Meteo API
 */
public enum CodigosClima {
    CIELO_LIMPIO(0, "Cielo Limpio"),
    PRINCIPALMENTE_DESPEJADO(1, "Principalmente Despejado"),
    PARCIALMENTE_NUBLADO(2, "Parcialmente Nublado"),
    NUBLADO(3, "Nublado"),
    NIEBLA(45, "Niebla"),
    NIEBLA_ESCARCHA_DEPOSITADA(48, "Niebla de escarcha depositada"),
    LLOVIZNA_LIGERA(51, "Llovizna: Intensidad ligera"),
    LLOVIZNA_MODERADA(53, "Llovizna: Intensidad moderada"),
    LLOVIZNA_DENSA(55, "Llovizna: Intensidad densa"),
    LLOVIZNA_ENGELANTE_LIGERA(56, "Llovizna Engelante: Intensidad ligera"),
    LLOVIZNA_ENGELANTE_DENSA(57, "Llovizna Engelante: Intensidad densa"),
    LLUVIA_LEVE(61, "Lluvia: Intensidad leve"),
    LLUVIA_MODERADA(63, "Lluvia: Intensidad moderada"),
    LLUVIA_FUERTE(65, "Lluvia: Intensidad fuerte"),
    LLUVIA_HELADA_LIGERA(66, "Lluvia helada: Intensidad ligera"),
    LLUVIA_HELADA_FUERTE(67, "Lluvia helada: Intensidad fuerte"),
    CAIDA_NIEVE_LIGERA(71, "Caida de nieve: Intensidad ligera"),
    CAIDA_NIEVE_MODERADA(73, "Caida de nieve: Intensidad moderada"),
    CAIDA_NIEVE_FUERTE(75, "Caida de nieve: Intensidad fuerte"),
    GRANOS_DE_NIEVE(77, "Granos de nieve"),
    CHUBASCOS_DE_NIEVE_LEVE(85, "Chubascos de nieve: Intensidad leve"),
    CHUBASCOS_DE_NIEVE_FUERTE(86, "Chubascos de nieve: Intensidad fuerte"),
    TORMENTA(95, "Tormenta: Ligera o moderada"),
    TORMENTA_CON_GRANIZO_LEVE(96, "Tormenta con granizo: Intensidad leve"),
    TORMENTA_CON_GRANIZO_FUERTE(99, "Tormenta con granizo: Intensidad fuerte");
    private int codigo;
    private String valor;

    CodigosClima(int codigo, String valor) {
        this.codigo = codigo;
        this.valor = valor;
    }

    public static String getTraduccion(int codigo) {
        for(CodigosClima item : CodigosClima.values()) {
            if(item.getCodigo() == codigo) {
                return item.getValor();
            }
        }
        return "";
    }

    public int getCodigo() {
        return codigo;
    }

    public String getValor() {
        return valor;
    }
}
