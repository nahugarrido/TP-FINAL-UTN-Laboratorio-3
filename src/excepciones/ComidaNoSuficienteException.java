package excepciones;

public class ComidaNoSuficienteException extends Exception {
    private double comidaDisponible;
    private double comidaAlimentar;

    public ComidaNoSuficienteException(String message, double comidaDisponible, double comidaAlimentar) {
        super(message);
        this.comidaDisponible = comidaDisponible;
        this.comidaAlimentar = comidaAlimentar;
    }
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
