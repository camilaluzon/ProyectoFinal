package adopcion.modelo.modulo5;

import java.util.ArrayList;

public class Vacuna {
    private String idVacuna;
    private String nombreVacuna;
    private int fechaAplicacion;
    private int fechaRefuerzo;
    private String observacion;
    private String estado;
    private String motivoEliminacion;

    public Vacuna() {
    }

    public Vacuna(String idVacuna, String nombreVacuna, int fechaAplicacion,
                  int fechaRefuerzo, String observacion, String estado) {
        this.idVacuna = idVacuna;
        this.nombreVacuna = nombreVacuna;
        this.fechaAplicacion = fechaAplicacion;
        this.fechaRefuerzo = fechaRefuerzo;
        this.observacion = observacion;
        this.estado = estado;
        this.motivoEliminacion = "";
    }

    // Getters y Setters

    public String getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(String idVacuna) {
        this.idVacuna = idVacuna;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivoEliminacion() {
        return motivoEliminacion;
    }

    public void setMotivoEliminacion(String motivoEliminacion) {
        this.motivoEliminacion = motivoEliminacion;
    }

    // Métodos UML

    public String registrarVacuna(Mascota mascota) {
        return "Vacuna registrada correctamente para la mascota "
                + m.getCodigoMascota();
    }

    public Vacuna consultarVacuna(String idVacuna) {
        if (this.idVacuna.equals(idVacuna)) {
            return this;
        }
        return null;
    }

    public boolean actualizarVacuna() {
        return true;
    }

    public boolean eliminarVacunaLogico(String motivo) {
        this.estado = "Eliminada";
        this.motivoEliminacion = motivo;
        return true;
    }

    @Override
    public String toString() {
        return "Vacuna{" +
                "idVacuna='" + idVacuna + '\'' +
                ", nombreVacuna='" + nombreVacuna + '\'' +
                ", fechaAplicacion=" + fechaAplicacion +
                ", fechaRefuerzo=" + fechaRefuerzo +
                ", observacion='" + observacion + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
