package excepciones;

public class SaldoNegativoException extends Exception {

    private final double saldo;

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

