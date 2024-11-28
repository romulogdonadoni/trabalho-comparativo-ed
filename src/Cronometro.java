public class Cronometro {
    private long inicio;
    private long fim;

    public void iniciar() {
        inicio = System.nanoTime();
    }

    public void parar() {
        fim = System.nanoTime();
    }

    public double getTempoDecorrido() {
        return (fim - inicio) / 1_000_000.0;
    }
} 