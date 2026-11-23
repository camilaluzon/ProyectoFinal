package adopcion.modelo.modulo4;

import java.util.ArrayList;

public class CitaSeguimiento {
    private String cedulaAdoptante;
    private String codigoMascota;
    private String tipoCita;
    private String fechaCita;
    private String observaciones;
    private String estadoCita = "Sin programar";

    public CitaSeguimiento(String cedulaAdoptante, String codigoMascota, String tipoCita, String fechaCita, String observaciones) {
        this.cedulaAdoptante = cedulaAdoptante;
        this.codigoMascota = codigoMascota;
        this.tipoCita = tipoCita;
        this.fechaCita = fechaCita;
        this.observaciones = observaciones;
    }

    public String getCedulaAdoptante() {
        return cedulaAdoptante;
    }

    public void setCedulaAdoptante(String cedulaAdoptante) {
        this.cedulaAdoptante = cedulaAdoptante;
    }

    public String getCodigoMascota() {
        return codigoMascota;
    }

    public void setCodigoMascota(String codigoMascota) {
        this.codigoMascota = codigoMascota;
    }

    public String getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(String estadoCita) {
        this.estadoCita = estadoCita;
    }

    public boolean registrarCita(ArrayList<CitaSeguimiento> citas) {
        for (CitaSeguimiento cita : citas) {
            if (cita.getCedulaAdoptante().equals(cedulaAdoptante) && cita.getFechaCita().equals(fechaCita)) {
                System.out.println("Error: El adoptante ya tiene una cita registrada en esa fecha.");
                return false;
            }
        }
        estadoCita = "Programada";
        citas.add(this);
        System.out.println("Cita de seguimiento registrada correctamente.");
        return true;
    }

    public CitaSeguimiento consultarCita(ArrayList<CitaSeguimiento> citas, String cedulaAdoptante, String codigoMascota, String fechaCita) {
        for (CitaSeguimiento cita : citas) {
            if (cita.getCedulaAdoptante().equals(cedulaAdoptante) && cita.getCodigoMascota().equals(codigoMascota) && cita.getFechaCita().equals(fechaCita)) {
                System.out.println("Cita encontrada");
                System.out.println("Fecha: " + cita.getFechaCita());
                System.out.println("Estado: " + cita.getEstadoCita());
                System.out.println("Observaciones: " + cita.getObservaciones());
                return cita;
            }
        }
        System.out.println("Error: La cita especificada no existe en el sistema.");
        return null;
    }

    public boolean actualizarCita(ArrayList<CitaSeguimiento> citas, String cedulaAdoptante, String codigoMascota, String fechaCita, boolean cancelada, boolean cumplida) {
        for (CitaSeguimiento cita : citas) {
            if (cita.getCedulaAdoptante().equals(cedulaAdoptante) && cita.getCodigoMascota().equals(codigoMascota) && cita.getFechaCita().equals(fechaCita)) {
                if (cita.getEstadoCita().equals("Realizada") || cita.getEstadoCita().equals("Cancelada")) {
                    System.out.println("Error: No se puede cancelar una cita que ya fue atendida o cancelada");
                    return false;
                }
                if (cancelada == true) {

                    cita.setEstadoCita("Cancelada");
                    System.out.println("La cita de seguimiento ha sido cancelada correctamente");
                } else if (cumplida == true) {

                    cita.setEstadoCita("Realizada");
                } else {
                    cita.setEstadoCita("Programada");
                    System.out.println("La cita continúa pendiente.");
                    return true;
                }

                System.out.println("Estado de la cita actualizado correctamente: " + cita.getEstadoCita());
                return true;
            }
        }

        System.out.println("Error: No se encontró la cita.");
        return false;
    }

    public boolean reprogramarCita(ArrayList<CitaSeguimiento> citas, String cedulaAdoptante, String codigoMascota, String fechaAnterior, String nuevaFecha) {
        for (CitaSeguimiento cita : citas) {
            if (cita.getCedulaAdoptante().equals(cedulaAdoptante) && cita.getCodigoMascota().equals(codigoMascota) && cita.getFechaCita().equals(fechaAnterior)) {
                if (cita.getEstadoCita().equals("Realizada") || cita.getEstadoCita().equals("Cancelada")){
                    System.out.println("Error: No se puede reprogramar esta cita.");
                    return false;
                }

                for (CitaSeguimiento otraCita : citas) {
                    if (otraCita.getCedulaAdoptante().equals(cedulaAdoptante) && otraCita.getCodigoMascota().equals(codigoMascota) && otraCita.getFechaCita().equals(nuevaFecha) && !otraCita.getEstadoCita().equals("Cancelada") && !otraCita.getEstadoCita().equals("Reprogramada")) {
                        System.out.println("Error: Ya existe una cita para esa fecha.");
                        return false;
                    }
                }

                CitaSeguimiento nuevaCita = new CitaSeguimiento(
                        cedulaAdoptante,
                        codigoMascota,
                        cita.getTipoCita(),
                        nuevaFecha,
                        cita.getObservaciones()
                );

                cita.setEstadoCita("Reprogramada");
                nuevaCita.setEstadoCita("Programada");
                citas.add(nuevaCita);

                System.out.println("Cita reprogramada correctamente.");
                return true;
            }
        }
        System.out.println("Error: No se encontró la cita.");
        return false;
    }
}
