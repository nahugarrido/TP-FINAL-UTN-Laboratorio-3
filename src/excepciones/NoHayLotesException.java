package excepciones;

/**
 * Excepción lanzada cuando no hay lotes en el historial.
 */
public class NoHayLotesException extends Exception{
    private int contadorLotes = 0;

    /**
     * Crea una nueva instancia de NoHayLotesException con un mensaje de error personalizado y un contador de lotes.
     * @param message
     * @param contadorLotes
     */
    public NoHayLotesException(String message, int contadorLotes) {
        super(message);
        this.contadorLotes = contadorLotes;
    }

    @Override
    public String getMessage() {
        return "No hay lotes en el historial, contadorLotes: " + this.getContadorLotes();
    }

    public int getContadorLotes() {
        return contadorLotes;
    }
}
