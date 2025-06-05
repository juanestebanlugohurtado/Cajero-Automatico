import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    private String nombre;
    private String cedula;
    private String pin;
    private double saldo;
    private List<Transaccion> historial;

    public Usuario(String nombre, String cedula, String pin, double saldoInicial) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.pin = pin;
        this.saldo = saldoInicial;
        this.historial = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
    public String getPin() { return pin; }
    public double getSaldo() { return saldo; }
    public List<Transaccion> getHistorial() { return historial; }

    public void setSaldo(double saldo) { this.saldo = saldo; }
    public void agregarTransaccion(Transaccion t) { historial.add(t); }
}
