import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaccion implements Serializable {
    public enum Tipo { RETIRO, CONSIGNACION }
    private Tipo tipo;
    private double monto;
    private LocalDateTime fecha;

    public Transaccion(Tipo tipo, double monto) {
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
    }

    public Tipo getTipo() { return tipo; }
    public double getMonto() { return monto; }
    public LocalDateTime getFecha() { return fecha; }
}
