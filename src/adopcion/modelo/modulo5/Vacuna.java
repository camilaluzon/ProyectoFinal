package adopcion.modelo.modulo5;

import adopcion.modelo.modulo1.Adoptante;
import adopcion.modelo.modulo2.Mascota;
import adopcion.utilitario.Lista;
import adopcion.utilitario.Valida;
import java.time.Year;

public class Vacuna {
    private static int contador = 1;

    private String idVacuna;
    private String nombreVacuna;
    private int fechaAplicacion;
    private int fechaRefuerzo;
    private String observacion;
    private boolean estado;
    private String motivoEliminacion;
    private Mascota mascota;

    // Constructor vacío
    public Vacuna() {
        this.idVacuna = "VAC-" + contador++;
        this.estado = true;
        this.motivoEliminacion = "";
        this.observacion = "";
    }

    // Constructor completo
    public Vacuna(Mascota mascota, String nombreVacuna,
                  int fechaAplicacion, int fechaRefuerzo,
                  String observacion) {

        this.idVacuna = "VAC-" + contador++;
        this.mascota = mascota;
        this.nombreVacuna = nombreVacuna;
        this.fechaAplicacion = fechaAplicacion;
        this.fechaRefuerzo = fechaRefuerzo;
        this.observacion = observacion;
        this.estado = true;
        this.motivoEliminacion = "";
    }

    // Getters y Setters

    public Mascota getMascota() {
        return mascota;
    }

    public String getIdVacuna() {
        return idVacuna;
    }

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }

    public int getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(int fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public int getFechaRefuerzo() {
        return fechaRefuerzo;
    }

    public void setFechaRefuerzo(int fechaRefuerzo) {
        this.fechaRefuerzo = fechaRefuerzo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMotivoEliminacion() {
        return motivoEliminacion;
    }

    public void setMotivoEliminacion(String motivoEliminacion) {
        this.motivoEliminacion = motivoEliminacion;
    }

    // Métodos UML

    public void registrarVacuna() {

        String codigoMascota = Valida.leerString("\nIngrese el código de la mascota:");

        Mascota mascota = Lista.buscarMascota(codigoMascota);

        if (mascota == null) {
            System.out.println("\nError: no se encontró un registro asociado al código ingresado.");
            return;
        }

        // Solicitar nombre de la vacuna
        String nombreVacuna = Valida.leerString("\nIngrese el nombre de la vacuna");

        int fechaAplicacion = Valida.leerIntervalo("\nIngrese el año de aplicación:",2001,Year.now().getValue());

        int fechaRefuerzo = Valida.leerIntervalo("\nIngrese el año de refuerzo:",fechaAplicacion,fechaAplicacion+1);

        String observacion = Valida.leerObservaciones("\nIngrese una observación");

        // Buscar registros duplicados
        Vacuna vacunaEncontrada = Lista.buscarVacuna(
                mascota,
                nombreVacuna,
                fechaAplicacion
        );

        if (vacunaEncontrada != null) {
            System.out.println("\nError: el registro ingresado ya existe en el sistema.");
            return;
        }

        // Crear nueva vacuna
        Vacuna nuevaVacuna = new Vacuna(
                mascota,
                nombreVacuna,
                fechaAplicacion,
                fechaRefuerzo,
                observacion
        );

        Lista.getVacunas().add(nuevaVacuna);

        System.out.println("\nLa vacuna fue registrada correctamente.");
        System.out.println("El historial médico se actualizó correctamente.");
    }

    public static void consultarVacuna() {

        String idVacuna = Valida.leerString("\nIngrese el ID de la vacuna:");

        Vacuna vacuna = Lista.buscarVacunaPorId(idVacuna);

        if (vacuna == null) {
            System.out.println("\nError: no se encontró el registro de la vacuna.");
            return;
        }

        System.out.println("\nRegistro encontrado correctamente.");
        vacuna.mostrarInformacionVacunas();
    }

    public static void actualizarVacuna() {

        String codigoMascota = Valida.leerString("\nIngrese el código de la mascota");

        Mascota mascota = Lista.buscarMascota(codigoMascota);

        if (mascota == null) {
            System.out.println("\nError: no se encontró un registro asociado al código ingresado.");
            return;
        }

        String idVacuna = Valida.leerString("\nIngrese el ID de la vacuna que desea actualizar");

        Vacuna vacunaEncontrada = Lista.buscarVacunaPorId(idVacuna);

        if (vacunaEncontrada == null ||!vacunaEncontrada.getMascota().getCodigoM().equalsIgnoreCase(codigoMascota)) {
            System.out.println("\nError: el registro solicitado no se encuentra en el sistema.");
            return;
        }

        if (!vacunaEncontrada.estado) {
            System.out.println("Error: no se puede actualizar una vacuna inactiva.");
            return;
        }

        String nombreVacuna = Valida.leerString("\nIngrese el nuevo nombre de la vacuna");

        int fechaAplicacion = Valida.leerIntervalo("\nIngrese el nuevo año de aplicación:",2001,Year.now().getValue());

        int fechaRefuerzo = Valida.leerIntervalo("\nIngrese el nuevo año de refuerzo:",fechaAplicacion,fechaAplicacion+1);

        String observacion = Valida.leerObservaciones("\nIngrese la nueva observación (opcional):");

        Vacuna duplicada = Lista.buscarVacuna(
                mascota,
                nombreVacuna,
                fechaAplicacion
        );

        if (duplicada != null && duplicada != vacunaEncontrada) {
            System.out.println("\nError: ya existe un registro de esta vacuna para la mascota en la fecha ingresada.");
            return;
        }

        if (vacunaEncontrada.actualizarVacuna(
                nombreVacuna,
                fechaAplicacion,
                fechaRefuerzo,
                observacion)) {

            System.out.println("\nActualización exitosa. La información de la vacuna ha sido modificada correctamente.");
            System.out.println("\nEl historial médico se actualizó correctamente.");
        } else {
            System.out.println("Error: no se pudo actualizar el registro.");
        }
    }

    public boolean actualizarVacuna(String nombreVacuna, int fechaAplicacion, int fechaRefuerzo, String observacion) {

        if (!estado) {
            return false;
        }

        this.nombreVacuna = nombreVacuna;
        this.fechaAplicacion = fechaAplicacion;
        this.fechaRefuerzo = fechaRefuerzo;
        this.observacion = observacion;

        return true;
    }

    public static void eliminarVacuna() {

        String codigoMascota = Valida.leerString("\nIngrese el código de la mascota");

        Mascota mascota = Lista.buscarMascota(codigoMascota);

        if (mascota == null) {
            System.out.println("\nError: no se encontró un registro asociado al código ingresado.");
            return;
        }

        String idVacuna = Valida.leerString("\nIngrese el ID de la vacuna que desea eliminar");

        Vacuna vacunaEncontrada = Lista.buscarVacunaPorId(idVacuna);

        if (vacunaEncontrada == null ||!vacunaEncontrada.getMascota().getCodigoM().equalsIgnoreCase(mascota.getCodigoM())) {
            System.out.println("\nError: el registro solicitado no se encuentra en el sistema.");
            return;
        }

        // Comprobar que la vacuna esté activa
        if (!vacunaEncontrada.isEstado()) {
            System.out.println("\nError: el registro seleccionado ya se encuentra inactivo.");
            return;
        }

        String confirmacion = Valida.validarRespuestaSiNo("\n¿Está seguro de que desea eliminar este registro? (SI/NO)");

        if (!confirmacion.equalsIgnoreCase("Si")) {
            System.out.println("Operación cancelada. No se realizaron modificaciones en el registro.");
            return;
        }

        String motivo = Valida.leerObservaciones("\nIngrese el motivo de eliminación");

    }

    public void mostrarInformacionVacunas() {

        Adoptante adoptante = Lista.buscarAdoptanteMascota(mascota.getCodigoM());

        System.out.println("\n --- VACUNA REGISTRADA ---");
        System.out.println("ID de vacuna: " + idVacuna);
        System.out.println("Código de mascota: " + mascota.getCodigoM());
        System.out.println("Nombre de vacuna: " + nombreVacuna);
        System.out.println("Año de aplicación: " + fechaAplicacion);
        System.out.println("Año de refuerzo: " + fechaRefuerzo);
        System.out.println("Observación: " + observacion);

        System.out.println("\n--- ADOPTANTE ASOCIADO ---");

        if (adoptante != null) {
            System.out.println("Nombre del adoptante: " + adoptante.getNombreCompleto());
            System.out.println("Cédula del adoptante: " + adoptante.getCedula());
            System.out.println("Teléfono: " + adoptante.getTelefono());
        } else {
            System.out.println("La mascota todavía no tiene un adoptante asociado.");
        }

        System.out.println("Próxima dosis programada para: " + fechaRefuerzo);
    }
}
