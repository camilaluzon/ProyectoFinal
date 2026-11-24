package adopcion.utilitario;

import java.time.LocalDate;
import java.util.ArrayList;

import adopcion.modelo.modulo1.Administrador;
import adopcion.modelo.modulo1.Adoptante;
import adopcion.modelo.modulo2.Mascota;
import adopcion.modelo.modulo3.Devolucion;
import adopcion.modelo.modulo4.AceptacionTermino;
import adopcion.modelo.modulo4.CitaSeguimiento;
import adopcion.modelo.modulo5.Vacuna;

public class Lista {
    // Listas únicas compartidas por todo el programa
    private static ArrayList<Adoptante> adoptantes = new ArrayList<>();
    private static ArrayList<Administrador> administradores = new ArrayList<>();
    private static ArrayList<Mascota> mascotas = new ArrayList<>();
    private static ArrayList<Vacuna> vacunas = new ArrayList<>();
    private static ArrayList<AceptacionTermino> adopciones = new ArrayList<>();
    private static ArrayList<Devolucion> devoluciones = new ArrayList<>();
    private static ArrayList<CitaSeguimiento> citas = new ArrayList<>();

    // Getters para que cualquier módulo acceda a las listas
    public static ArrayList<Adoptante> getAdoptantes() {
        return adoptantes;
    }

    public static ArrayList<Administrador> getAdministradores() {
        return administradores;
    }

    public static ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public static ArrayList<AceptacionTermino> getAdopcion() {
        return adopciones;
    }

    public static ArrayList<Vacuna> getVacunas() {
        return vacunas;
    }

    public static ArrayList<Devolucion> getDevolucion() {
        return devoluciones;
    }

    public static ArrayList<CitaSeguimiento> getCitas() {
        return citas;
    }

    // Métodos de búsqueda centralizados (así no los repiten en cada clase)
    public static Adoptante buscarAdoptante(String cedula) {
        for (Adoptante a : adoptantes) {
            if (a.getCedula().equals(cedula)) {
                return a;
            }
        }
        return null;
    }

    public static Administrador buscarAdministrador(String cedula) {
        for (Administrador admin : administradores) {
            if (admin.getCedula().equals(cedula)) {
                return admin;
            }
        }
        return null;
    }

    public static Mascota buscarMascota(String codigo) {
        for (Mascota m : mascotas) {
            if (m.getCodigoM().equalsIgnoreCase(codigo)) {
                return m;
            }
        }
        return null;
    }

    public static Vacuna buscarVacuna(Mascota mascota, String nombreVacuna, int fechaAplicacion) {

        for (Vacuna v : vacunas) {

            if (v.getMascota().getCodigoM().equalsIgnoreCase(mascota.getCodigoM()) &&
                    v.getNombreVacuna().equalsIgnoreCase(nombreVacuna) &&
                    v.getFechaAplicacion() == fechaAplicacion) {

                return v;
            }
        }
        return null;
    }

    public static Vacuna buscarVacunaPorId(String idVacuna) {

        for (Vacuna v : vacunas) {
            if (v.getIdVacuna().equalsIgnoreCase(idVacuna)) {
                return v;
            }
        }

        return null;
    }

    public static Adoptante buscarAdoptanteMascota(String codigoMascota) {

        for (AceptacionTermino a : adopciones) {

            if (a.getCodigoMascota().equalsIgnoreCase(codigoMascota)) {
                return buscarAdoptante(a.getCedulaAdoptante());
            }
        }

        return null;
    }

    public static ArrayList<Mascota> buscarMascotasAdoptadas(String cedula) {

        ArrayList<Mascota> mascotasAdoptadas = new ArrayList<>();

        for (AceptacionTermino a : adopciones) {

            if (a.getCedulaAdoptante().equalsIgnoreCase(cedula)) {

                Mascota mascota = buscarMascota(a.getCodigoMascota());

                if (mascota != null &&
                        mascota.getEstado() == 2 &&
                        !mascotasAdoptadas.contains(mascota)) {

                    mascotasAdoptadas.add(mascota);
                }
            }
        }

        return mascotasAdoptadas;
    }

    // Cargar datos de prueba
    public static void cargarDatosPrueba() {
        // ADMINISTRADOR DE PRUEBA
        if (administradores.isEmpty()) {
            administradores.add(new Administrador(
                    "1700000001", "Admin Principal", "0990000000",
                    "admin123", "ADM-01", "Coordinador"
            ));
        }

        // ADOPTANTES DE PRUEBA
        if (adoptantes.isEmpty()) {
            adoptantes.add(new Adoptante(
                    "1711111111", "Carlos Perez", "0991111111",
                    "Carlos123", 2, 80, 30, 4,
                    true, 1, 150, 2, "vainilla"
            ));

            adoptantes.add(new Adoptante(
                    "1722222222", "Maria Gomez", "0992222222",
                    "Maria123", 1, 60, 15, 6,
                    false, 0, 100, 0, "chocolate"
            ));

            adoptantes.add(new Adoptante(
                    "1733333333", "Luis Torres", "0993333333",
                    "Luis123", 3, 150, 80, 2,
                    true, 2, 250, 1, "frutilla"
            ));
        }

        // MASCOTAS DE PRUEBA
        if (mascotas.isEmpty()) {
            // Perros (especie 1)
            mascotas.add(new Mascota("Max", 1, 4, 55, false, 0, 3, 3));
            mascotas.add(new Mascota("Rocky", 1, 2, 30, true, 3, 2, 2));

            // Gatos (especie 2)
            mascotas.add(new Mascota("Luna", 2, 3, 0, false, 0, 2, 3));
            mascotas.add(new Mascota("Michi", 2, 5, 0, true, 3, 1, 2));
        }

        // VACUNA DE PRUEBA
        if (vacunas.isEmpty() && !mascotas.isEmpty()) {
            Vacuna v1 = new Vacuna(mascotas.get(0), "Rabia", 2026, 2027, "Sin novedades");
            v1.setFechaProximaDosis(LocalDate.now().plusMonths(3));
            vacunas.add(v1);
        }

        // CITA DE SEGUIMIENTO DE PRUEBA
        if (citas.isEmpty()) {
            CitaSeguimiento c1 = new CitaSeguimiento(
                    "1711111111", "M1", 3, LocalDate.of(2026, 12, 1), "Primera visita"
            );
            c1.setEstadoCita("Programada");
            citas.add(c1);
        }

        System.out.println("Datos de prueba cargados correctamente.");
    }
}

