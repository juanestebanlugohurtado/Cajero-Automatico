import java.io.*;

public class CajeroService {

    // Registro del usuario
    public Usuario registrarUsuario(String nombre, String cedula, String pin) throws Exception {
        if (!cedula.matches("\\d+")) throw new Exception("Cédula inválida");
        if (!pin.matches("\\d{4}")) throw new Exception("PIN inválido");
        Usuario usuario = new Usuario(nombre, cedula, pin, 0);
        guardarUsuario(usuario);
        return usuario;
    }

    // Inicio de sesión
    public Usuario login(String cedula, String pin) throws Exception {
        Usuario usuario = cargarUsuario(cedula);
        if (usuario != null && usuario.getPin().equals(pin)) {
            return usuario;
        } else {
            throw new Exception("Cédula o PIN incorrectos");
        }
    }

    // Consultar saldo
    public double consultarSaldo(Usuario usuario) {
        return usuario.getSaldo();
    }

    // Retirar dinero
    public void retirar(Usuario usuario, double monto) throws Exception {
        if (monto <= 0) throw new Exception("Monto inválido");
        if (monto > usuario.getSaldo()) throw new Exception("Saldo insuficiente");
        usuario.setSaldo(usuario.getSaldo() - monto);
        usuario.agregarTransaccion(new Transaccion(Transaccion.Tipo.RETIRO, monto));
        guardarUsuario(usuario);
    }

    // Consignar dinero
    public void consignar(Usuario usuario, double monto) throws Exception {
        if (monto <= 0) throw new Exception("Monto inválido");
        usuario.setSaldo(usuario.getSaldo() + monto);
        usuario.agregarTransaccion(new Transaccion(Transaccion.Tipo.CONSIGNACION, monto));
        guardarUsuario(usuario);
    }

    // Mostrar historial
    public java.util.List<Transaccion> obtenerHistorial(Usuario usuario) {
        return usuario.getHistorial();
    }

    // Persistencia
    private void guardarUsuario(Usuario usuario) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(usuario.getCedula() + ".dat"))) {
            oos.writeObject(usuario);
        }
    }

    private Usuario cargarUsuario(String cedula) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(cedula + ".dat"))) {
            return (Usuario) ois.readObject();
        }
    }
}
