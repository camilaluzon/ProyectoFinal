package adopcion.modelo.modulo1;

public class Administrador extends Persona {
    private String codigoAdmin;
    private String cargo;

    public Administrador(String cedula, String nombreCompleto, String telefono,
                         String contrasena, String codigoAdmin, String cargo) {
        super(cedula, nombreCompleto, telefono, contrasena);
        this.codigoAdmin = codigoAdmin;
        this.cargo = cargo;
    }

    public String getCodigoAdmin() { return codigoAdmin; }
    public void setCodigoAdmin(String codigoAdmin) { this.codigoAdmin = codigoAdmin; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}
