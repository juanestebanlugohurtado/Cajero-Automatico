public class PruebaCajero {
    public static void main(String[] args) throws Exception {
        CajeroService cajero = new CajeroService();

        // Registrar usuario
        Usuario usuario = cajero.registrarUsuario("Juan Lugo", "1111545023", "0420");

        // Iniciar sesión
        Usuario usuarioSesion = cajero.login("1111545023", "0420");

        // Consignar dinero
        cajero.consignar(usuarioSesion, 1000);

        // Retirar dinero
        cajero.retirar(usuarioSesion, 200);

        // Consultar saldo
        double saldo = cajero.consultarSaldo(usuarioSesion);
        System.out.println("Saldo actual: $" + saldo);

        // Ver historial
        for (Transaccion t : cajero.obtenerHistorial(usuarioSesion)) {
            System.out.println(t.getTipo() + " | $" + t.getMonto() + " | " + t.getFecha());
        }
    }
}
