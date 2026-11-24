package adopcion.modelo.modulo4;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class AceptacionTermino {
    private String cedulaAdoptante;
    private String codigoMascota;
    private boolean aceptacionTerminos;
    private String fechaAceptacion;

    public AceptacionTerminos(String cedulaAdoptante, String codigoMascota, String versionTerminos) {
        this.cedulaAdoptante = cedulaAdoptante;
        this.codigoMascota = codigoMascota;
        this.fechaAceptacion = LocalDateTime.now().toString();
        this.aceptacionTerminos = false;
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

    public boolean isAceptacionTerminos() {
        return aceptacionTerminos;
    }

    public void setAceptacionTerminos(boolean aceptacionTerminos) {
        this.aceptacionTerminos = aceptacionTerminos;
    }

    public String getFechaAceptacion() {
        return fechaAceptacion;
    }

    public void registrarAceptacion(String respuesta) {
        if (respuesta.equalsIgnoreCase("si")) {
            aceptacionTerminos = true;
        } else {
            aceptacionTerminos = false;
        }
    }

    public boolean validarTerminos() {
        if (aceptacionTerminos == false) {
            System.out.println("Error: Debe aceptar los Términos y Condiciones para continuar con la adopción.");
            return false;
        }
        System.out.println("Aceptación de Términos y Condiciones registrada correctamente.");
        return true;
    }
}
