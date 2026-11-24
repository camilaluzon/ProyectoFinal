package adopcion.interfaz;

import java.util.ArrayList;
import java.util.List;

import adopcion.modelo.modulo1.Administrador;
import adopcion.utilitario.Lista;
import adopcion.utilitario.Valida;
import adopcion.modelo.modulo1.Adoptante;
import adopcion.modelo.modulo2.Mascota;
import adopcion.modelo.modulo1.GestionAdoptante;
import adopcion.modelo.modulo2.GestionMascota;
import adopcion.modelo.modulo4.AceptacionTermino;
import adopcion.modelo.modulo3.Devolucion;
import adopcion.modelo.modulo4.CitaSeguimiento;
import adopcion.modelo.modulo5.Vacuna;

import java.util.Scanner;

public class MainAdopcion {
    static void main(String[] args) {
        Lista.cargarDatosPrueba();

        GestionAdoptante gestionAdoptante = new GestionAdoptante();
        GestionMascota gestionMascota = new GestionMascota();
        int opcion;

        do {
            System.out.println("\n=========================================");
            System.out.println("   SISTEMA DE ADOPCIÓN DE MASCOTAS");
            System.out.println("=========================================");
            System.out.println("1. Iniciar sesión como Adoptante");
            System.out.println("2. Registrarse como Adoptante");
            System.out.println("3. Iniciar sesión como Administrador");
            System.out.println("0. Salir");
            opcion = Valida.leerIntervalo("Seleccione una opción", 0, 3);

            switch (opcion) {
                case 1:
                    Adoptante adoptanteLogueado = gestionAdoptante.iniciarSesion();
                    if (adoptanteLogueado != null) {
                        menuAdoptante(adoptanteLogueado, gestionAdoptante, gestionMascota);
                    }
                    break;
                case 2:
                    gestionAdoptante.registrarAdoptante();
                    break;
                case 3:
                    String cedulaAdmin = Valida.leerDiezDigitos("\nIngrese cédula de administrador");
                    Administrador admin = Lista.buscarAdministrador(cedulaAdmin);
                    if (admin == null) {
                        System.out.println("Error: Administrador no encontrado.");
                        break;
                    }
                    String passAdmin = Valida.leerString("Ingrese contraseña");
                    if (admin.getContrasena().equals(passAdmin)) {
                        System.out.println("\nBienvenido, " + admin.getNombreCompleto() + " (" + admin.getCargo() + ")");
                        menuAdministrador(gestionAdoptante, gestionMascota);
                    } else {
                        System.out.println("Error: Contraseña incorrecta.");
                    }
                    break;
                case 0:
                    System.out.println("\nSaliendo del sistema...");
                    break;
            }
        } while (opcion != 0);
    }

    private static void menuAdoptante(Adoptante adoptante, GestionAdoptante ga, GestionMascota gm) {
        int op;
        CitaSeguimiento citaAux = new CitaSeguimiento("", "", 3, null, "");

        do {
            System.out.println("\n=== MENÚ ADOPTANTE (" + adoptante.getNombreCompleto() + ") ===");
            System.out.println("1. Ver mi perfil");
            System.out.println("2. Actualizar mi perfil");
            System.out.println("3. Ver reporte de mascotas compatibles");
            System.out.println("4. Adoptar una mascota");
            System.out.println("5. Consultar una cita de seguimiento");
            System.out.println("6. Reprogramar mi cita de seguimiento");
            System.out.println("7. Cancelar mi cita de seguimiento");
            System.out.println("0. Cerrar sesión");
            op = Valida.leerIntervalo("Seleccione una opción", 0, 7);

            switch (op) {
                case 1:
                    adoptante.mostrarInformacion();
                    break;
                case 2:
                    ga.actualizarAdoptante();
                    break;
                case 3:
                    gm.generarReporteCompatibilidad(adoptante);
                    break;
                case 4:
                    String codM = Valida.leerString("Ingrese el código de la mascota a adoptar");
                    gm.realizarAdopcion(adoptante.getCedula(), codM);
                    break;
                case 5:
                    String codCita = Valida.leerString("Código de la mascota");
                    String fechaC = Valida.leerString("Fecha de la cita (AAAA-MM-DD)");
                    citaAux.consultarCita(Lista.getCitas(), adoptante.getCedula(), codCita, fechaC);
                    break;
                case 6:
                    String codRep = Valida.leerString("Código de la mascota");
                    String fechaRep = Valida.leerString("Fecha actual de la cita (AAAA-MM-DD)");
                    citaAux.reprogramarCita(Lista.getCitas(), adoptante.getCedula(), codRep, fechaRep);
                    break;
                case 7:
                    String codCan = Valida.leerString("Código de la mascota");
                    String fechaCan = Valida.leerString("Fecha de la cita a cancelar (AAAA-MM-DD)");
                    citaAux.eliminarCita(Lista.getCitas(), adoptante.getCedula(), codCan, fechaCan);
                    break;
            }
        } while (op != 0);
    }

    private static void menuAdministrador(GestionAdoptante ga, GestionMascota gm) {
        int op;
        Vacuna vacunaAux = new Vacuna();
        CitaSeguimiento citaAux = new CitaSeguimiento("", "", 3, null, "");

        do {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Registrar mascota");
            System.out.println("2. Consultar mascota");
            System.out.println("3. Actualizar mascota");
            System.out.println("4. Eliminar mascota");
            System.out.println("5. Consultar adoptante");
            System.out.println("6. Eliminar adoptante");
            System.out.println("7. Registrar vacuna");
            System.out.println("8. Consultar vacuna");
            System.out.println("9. Actualizar vacuna");
            System.out.println("10. Eliminar vacuna");
            System.out.println("11. Registrar cita de seguimiento");
            System.out.println("12. Actualizar observaciones de cita");
            System.out.println("13. Reprogramar cita");
            System.out.println("14. Cancelar cita");
            System.out.println("15. Registrar devolución");
            System.out.println("0. Cerrar sesión");
            op = Valida.leerIntervalo("Seleccione una opción", 0, 15);

            switch (op) {
                case 1:
                    gm.registrarMascota();
                    break;
                case 2:
                    gm.consultarMascota();
                    break;
                case 3:
                    gm.ActualizarMascota();
                    break;
                case 4:
                    gm.eliminarPerfilMascota();
                    break;
                case 5:
                    ga.consultarAdoptante();
                    break;
                case 6:
                    ga.eliminarAdoptante();
                    break;
                case 7:
                    vacunaAux.registrarVacuna();
                    break;
                case 8:
                    Vacuna.consultarVacuna();
                    break;
                case 9:
                    Vacuna.actualizarVacuna();
                    break;
                case 10:
                    Vacuna.eliminarVacuna();
                    break;
                case 11:
                    CitaSeguimiento.menuRegistrarCita(Lista.getCitas());
                    break;
                case 12:
                    String cedA = Valida.leerDiezDigitos("Cédula del adoptante");
                    String codA = Valida.leerString("Código de la mascota");
                    String fecA = Valida.leerString("Fecha de la cita (AAAA-MM-DD)");
                    citaAux.actualizarCita(Lista.getCitas(), cedA, codA, fecA);
                    break;
                case 13:
                    String cedR = Valida.leerDiezDigitos("Cédula del adoptante");
                    String codR = Valida.leerString("Código de la mascota");
                    String fecR = Valida.leerString("Fecha actual de la cita (AAAA-MM-DD)");
                    citaAux.reprogramarCita(Lista.getCitas(), cedR, codR, fecR);
                    break;
                case 14:
                    String cedE = Valida.leerDiezDigitos("Cédula del adoptante");
                    String codE = Valida.leerString("Código de la mascota");
                    String fecE = Valida.leerString("Fecha de la cita (AAAA-MM-DD)");
                    citaAux.eliminarCita(Lista.getCitas(), cedE, codE, fecE);
                    break;
                case 15:
                    Devolucion.registrarDevolucion();
                    break;
            }
        } while (op != 0);
    }
}
