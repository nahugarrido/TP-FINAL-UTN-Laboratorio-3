package excepciones;

/**
 * Excepción que se lanza cuando la cantidad de comida disponible no es suficiente.
 * Proporciona información detallada sobre el error.
 */
public class ComidaNoSuficienteException extends Exception {
    private double comidaDisponible;
    private double comidaAlimentar;

    /**
     * Se crea una instancia de la excepción con información específica sobre la falta de comida disponible.
     * @param message
     * @param comidaDisponible
     * @param comidaAlimentar
     */
    public ComidaNoSuficienteException(String message, double comidaDisponible, double comidaAlimentar) {
        super(message);
        this.comidaDisponible = comidaDisponible;
        this.comidaAlimentar = comidaAlimentar;
    }

    /**
     * Proporciona un mensaje personalizado que indica la cantidad de comida disponible
     * y el valor ingresado para alimentar.
     * @return String mensaje
     */
    @Override
    public String getMessage() {
        return "La comida disponible es de " + this.getComidaDisponible() + " kilos, valor ingresado: (" + this.getComidaAlimentar() + ")";
    }

    public double getComidaDisponible() {
        return comidaDisponible;
    }

    public void setComidaDisponible(double comidaDisponible) {
        this.comidaDisponible = comidaDisponible;
    }

    public double getComidaAlimentar() {
        return comidaAlimentar;
    }

    public void setComidaAlimentar(double comidaAlimentar) {
        this.comidaAlimentar = comidaAlimentar;
    }
}
