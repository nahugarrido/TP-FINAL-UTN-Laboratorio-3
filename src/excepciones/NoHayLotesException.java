package excepciones;

public class NoHayLotesException extends Exception{
    private int contadorLotes = 0;

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
