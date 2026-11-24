package adopcion.modelo.modulo4;

import adopcion.utilitario.Valida;

import java.util.ArrayList;
import java.time.LocalDate;

public class CitaSeguimiento  {
    private String cedulaAdoptante;
    private String codigoMascota;
    private int tipoCita;
    private LocalDate fechaproximaCita;
    private LocalDate fechaCita;
    private String observaciones = "";
    private String estadoCita = "Sin programar";

    public CitaSeguimiento(String cedulaAdoptante, String codigoMascota, int tipoCita, LocalDate fechaCita, String observaciones) {
        this.cedulaAdoptante = cedulaAdoptante;
        this.codigoMascota = codigoMascota;
        this.tipoCita = tipoCita;
        this.fechaCita = fechaCita;
        this.observaciones = observaciones;
        calcularProximaCita();
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

    public int getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(int tipoCita) {
        this.tipoCita = tipoCita;
        calcularProximaCita();
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
        calcularProximaCita();
    }

    public LocalDate getFechaproximaCita() {
        return fechaproximaCita;
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
                System.out.println("\nError: El adoptante ya tiene una cita registrada en esa fecha.");
                return false;
            }
        }
        estadoCita = "Programada";
        citas.add(this);
        System.out.println("\nCita de seguimiento registrada correctamente. Próxima cita: " + fechaproximaCita);
        return true;
    }

    public CitaSeguimiento consultarCita(ArrayList<CitaSeguimiento> citas, String cedulaAdoptante, String codigoMascota, String fechaCita) {
        LocalDate fechaBuscada = LocalDate.parse(fechaCita);

        for (CitaSeguimiento cita : citas) {
            if (cita.getCedulaAdoptante().equals(cedulaAdoptante)
                    && cita.getCodigoMascota().equals(codigoMascota)
                    && cita.getFechaCita().equals(fechaBuscada)) {
                System.out.println("\nCita encontrada");
                System.out.println("\nFecha: " + cita.getFechaCita());
                System.out.println("\nPróxima cita: " + cita.getFechaproximaCita());
                System.out.println("\nEstado: " + cita.getEstadoCita());
                System.out.println("\nObservaciones: " + cita.getObservaciones());
                return cita;
            }
        }
        System.out.println("\nError: La cita especificada no existe en el sistema.");
        return null;
    }

    public void actualizarCita(ArrayList<CitaSeguimiento> citas, String cedulaAdoptante, String codigoMascota, String fechaCita) {
        LocalDate fechaBuscada = LocalDate.parse(fechaCita);

        for (CitaSeguimiento cita : citas) {
            if (cita.getCedulaAdoptante().equals(cedulaAdoptante)
                    && cita.getCodigoMascota().equals(codigoMascota)
                    && cita.getFechaCita().equals(fechaBuscada)) {

                if (cita.getEstadoCita().equals("Realizada") || cita.getEstadoCita().equals("Cancelada")) {
                    System.out.println("Error: No se puede modificar una cita que ya fue realizada o cancelada.");
                    return;
                }

                // Actualizar observaciones
                String nuevasObs = Valida.leerObservaciones("Ingrese las nuevas observaciones");
                cita.setObservaciones(nuevasObs);

                System.out.println("\nCita actualizada correctamente. Estado actual: " + cita.getEstadoCita());
                return;
            }
        }

        System.out.println("Error: No se encontró la cita.");
    }

    public void reprogramarCita(ArrayList<CitaSeguimiento> citas, String cedulaAdoptante, String codigoMascota, String fechaActual) {
        LocalDate fechaBuscada = LocalDate.parse(fechaActual);

        for (CitaSeguimiento cita : citas) {
            if (cita.getCedulaAdoptante().equals(cedulaAdoptante)
                    && cita.getCodigoMascota().equals(codigoMascota)
                    && cita.getFechaCita().equals(fechaBuscada)) {

                if (cita.getEstadoCita().equals("Realizada") || cita.getEstadoCita().equals("Cancelada")) {
                    System.out.println("Error: No se puede reprogramar una cita que ya fue realizada o cancelada.");
                    return;
                }

                String nuevaFechaTexto = Valida.leerString("Ingrese la nueva fecha de la cita (AAAA-MM-DD)");
                LocalDate nuevaFecha = LocalDate.parse(nuevaFechaTexto);

                // Al usar setFechaCita, también se recalcula fechaproximaCita automáticamente
                cita.setFechaCita(nuevaFecha);
                cita.setEstadoCita("Reprogramada");

                System.out.println("\nCita reprogramada correctamente para: " + cita.getFechaCita());
                System.out.println("Nueva fecha de próxima cita: " + cita.getFechaproximaCita());
                return;
            }
        }

        System.out.println("Error: No se encontró la cita.");
    }

    public void eliminarCita(ArrayList<CitaSeguimiento> citas, String cedulaAdoptante, String codigoMascota, String fechaCita) {
        LocalDate fechaBuscada = LocalDate.parse(fechaCita);

        for (CitaSeguimiento cita : citas) {
            if (cita.getCedulaAdoptante().equals(cedulaAdoptante)
                    && cita.getCodigoMascota().equals(codigoMascota)
                    && cita.getFechaCita().equals(fechaBuscada)) {

                if (cita.getEstadoCita().equals("Realizada") || cita.getEstadoCita().equals("Cancelada")) {
                    System.out.println("Error: La cita ya fue realizada o cancelada previamente.");
                    return;
                }

                cita.setEstadoCita("Cancelada");
                System.out.println("\nLa cita ha sido eliminada (estado cambiado a Cancelada).");
                return;
            }
        }

        System.out.println("Error: No se encontró la cita.");
    }

    public void calcularProximaCita() {
        if (this.fechaCita != null) {
            if (this.tipoCita == 3) {
                this.fechaproximaCita = this.fechaCita.plusMonths(3);
            } else if (this.tipoCita == 6) {
                this.fechaproximaCita = this.fechaCita.plusMonths(6);
            } else if (this.tipoCita == 12) {
                this.fechaproximaCita = this.fechaCita.plusMonths(12);
            }
        }
    }

    public static void menuRegistrarCita(ArrayList<CitaSeguimiento> citas) {
        String cedula = Valida.leerString("Ingrese la cédula del adoptante");
        String codigoM = Valida.leerString("Ingrese el código de la mascota");

        System.out.println("\nTipo de cita:");
        System.out.println("1. Seguimiento a los 3 meses");
        System.out.println("2. Seguimiento a los 6 meses");
        System.out.println("3. Seguimiento al año (12 meses)");
        int opcionTipo = Valida.leerIntervalo("Seleccione el tipo", 1, 3);

        int tipoCita;
        if (opcionTipo == 1) {
            tipoCita = 3;
        } else if (opcionTipo == 2) {
            tipoCita = 6;
        } else {
            tipoCita = 12;
        }

        String fechaTexto = Valida.leerString("Ingrese la fecha de la cita (AAAA-MM-DD)");
        LocalDate fecha = LocalDate.parse(fechaTexto);

        String observaciones = Valida.leerObservaciones("Ingrese observaciones iniciales");

        CitaSeguimiento nuevaCita = new CitaSeguimiento(cedula, codigoM, tipoCita, fecha, observaciones);
        nuevaCita.registrarCita(citas);
    }
}
