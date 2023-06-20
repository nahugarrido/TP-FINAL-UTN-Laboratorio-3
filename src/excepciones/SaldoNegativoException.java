package excepciones;

/**
 * Excepción lanzada cuando el saldo de la granja es negativo.
 */
public class SaldoNegativoException extends Exception {

    private final double saldo;

    /**
     * Crea una nueva instancia de SaldoNegativoException con un mensaje de error personalizado y el saldo negativo.
     * @param message
     * @param saldo
     */
    public SaldoNegativoException(String message, double saldo) {
        super(message);
        this.saldo = saldo;
    }

    @Override
    public String getMessage() {
        return "Tu granja tiene un saldo negativo de " + this.getSaldo() + " por lo que tu partida ha terminado.";
    }

    public double getSaldo() {
        return saldo;
    }
}

