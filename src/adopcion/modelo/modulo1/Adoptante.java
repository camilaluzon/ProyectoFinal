package adopcion.modelo.modulo1;

public class Adoptante {
    // Atributos
    private String cedula;
    private String nombreCompleto;
    private String telefono;
    private String contrasena;
    private int tipoVivienda;//* */
    private double espacioInterior;
    private double espacioExterior;
    private double horasFueraCasa;
    private boolean experienciaMascotas;//* */
    private int tipoMascotaAnterior;//* */
    private double presupuesto;
    private int numeroHijos;
    private int estadoPerfil;//* */
    private String respuestaSeguridad;

    // Constructor
    public Adoptante(String cedula, String nombreCompleto, String telefono,
                     String contrasena, String tipoVivienda,
                     double espacioInterior, double espacioExterior,
                     double horasFueraCasa, boolean experienciaMascotas,
                     double presupuesto, int numeroHijos) {

        this.cedula = cedula;//get
        this.nombreCompleto = nombreCompleto;//get,set
        this.telefono = telefono;//get,set
        this.contrasena = contrasena;//get,set
        this.espacioInterior = espacioInterior;//get,set
        this.espacioExterior = espacioExterior;//get,set
        this.horasFueraCasa = horasFueraCasa;//get,set
        this.presupuesto = presupuesto;//get,set
        this.numeroHijos = numeroHijos;//get,set
        this.respuestaSeguridad = respuestaSeguridad;//get,set
    }

    public Adoptante() {
        this.estadoPerfil = 1;
    }

    // Constructor
    public Adoptante(String cedula, String nombreCompleto, String telefono,
                     String contrasena, int tipoVivienda,
                     double espacioInterior, double espacioExterior,
                     double horasFueraCasa, boolean experienciaMascotas,
                     int tipoMascotaAnterior, double presupuesto,
                     int numeroHijos, String respuestaSeguridad) {

        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.tipoVivienda = tipoVivienda;
        this.espacioInterior = espacioInterior;
        this.espacioExterior = espacioExterior;
        this.horasFueraCasa = horasFueraCasa;
        this.experienciaMascotas = experienciaMascotas;
        this.tipoMascotaAnterior = tipoMascotaAnterior;
        this.presupuesto = presupuesto;
        this.numeroHijos = numeroHijos;
        this.respuestaSeguridad = respuestaSeguridad;
        this.estadoPerfil = 1;
    }

    // Getters
    public String getCedula() {
        return cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getTipoVivienda(){
        return tipoVivienda;
    }

    public double getEspacioInterior() {
        return espacioInterior;
    }

    public double getEspacioExterior() {
        return espacioExterior;
    }

    public double getHorasFueraCasa() {
        return horasFueraCasa;
    }

    public boolean isExperienciaMascotas() {
        return experienciaMascotas;
    }

    public int getTipoMascotaAnterior() {
        return tipoMascotaAnterior;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public int getNumeroHijos() {
        return numeroHijos;
    }

    public int getEstadoPerfil() {
        return estadoPerfil;
    }

    public String getRespuestaSeguridad() {
        return respuestaSeguridad;
    }

    // Setters

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setTipoVivienda(int tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public void setEspacioInterior(double espacioInterior) {
        this.espacioInterior = espacioInterior;
    }

    public void setEspacioExterior(double espacioExterior) {
        this.espacioExterior = espacioExterior;
    }

    public void setHorasFueraCasa(double horasFueraCasa) {
        this.horasFueraCasa = horasFueraCasa;
    }

    public void setExperienciaMascotas(boolean experienciaMascotas) {
        this.experienciaMascotas = experienciaMascotas;
    }

    public void setTipoMascotaAnterior(int tipoMascotaAnterior) {
        this.tipoMascotaAnterior = tipoMascotaAnterior;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public void setNumeroHijos(int numeroHijos) {
        this.numeroHijos = numeroHijos;
    }

    public void setEstadoPerfil(int estadoPerfil) {
        this.estadoPerfil = estadoPerfil;
    }

    public void setRespuestaSeguridad(String respuestaSeguridad) {
        this.respuestaSeguridad = respuestaSeguridad;
    }

    // Método para mostrar información
    public void mostrarInformacion() {

        System.out.println("\n\n--- PERFIL DEL ADOPTANTE ---");
        System.out.println("Cédula: " + cedula);
        System.out.println("Nombre: " + nombreCompleto);
        System.out.println("Teléfono: " + telefono);

        String vivienda;
        if (tipoVivienda == 1) {
            vivienda = "Casa";
        } else if (tipoVivienda == 2) {
            vivienda = "Departamento";
        } else {
            vivienda = "Hacienda";
        }
        System.out.println("Tipo de vivienda: " + vivienda);

        System.out.println("Espacio interior: " + espacioInterior + " m2");
        System.out.println("Espacio exterior: " + espacioExterior + " m2");
        System.out.println("Horas fuera de casa: " + horasFueraCasa);

        String experiencia = experienciaMascotas ? "Sí" : "No";
        System.out.println("Experiencia con mascotas: " + experiencia);

        String tipoMascota;
        if (tipoMascotaAnterior == 1) {
            tipoMascota = "Perro";
        } else if (tipoMascotaAnterior == 2) {
            tipoMascota = "Gato";
        } else {
            tipoMascota = "Ninguna";
        }
        System.out.println("Mascota anterior: " + tipoMascota);

        System.out.println("Presupuesto: " + presupuesto);
        System.out.println("Niños menores de 15 años: " + numeroHijos);
        System.out.println("Respuesta de seguridad: " + respuestaSeguridad);

        String estado;
        if (estadoPerfil == 1) {
            estado = "Activo";
        } else if (estadoPerfil == 2) {
            estado = "Inactivo";
        } else {
            estado = "Inhabilitado";
        }
        System.out.println("Estado del perfil: " + estado);
    }

    public static void mVivienda() {

        System.out.println("\n\n--- TIPO DE VIVIENDA ---");
        System.out.println("\n1.Departamento");
        System.out.println("\n2.Casa");
        System.out.println("\n3.Hacienda");
    }

    public static void mMascota() {

        System.out.println("\n\n--- TIPO DE MASCOTA ---");
        System.out.println("\n1.Perro");
        System.out.println("\n2.Gato");
    }
}
