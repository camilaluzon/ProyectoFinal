package adopcion.modelo.modulo3;

import java.time.LocalDate;
import java.util.ArrayList;
import adopcion.modelo.modulo1.Adoptante;
import adopcion.modelo.modulo2.Mascota;
import adopcion.utilitario.Lista;
import adopcion.utilitario.Valida;

public class Devolucion {
    private static int contador = 1;

    private String codigoDe;
    private LocalDate fechaDe;
    private String motivoDe;
    private int nivelGra;
    private String resultadoEva;
    private Adoptante adoptante;
    private Mascota mascota;

    public Devolucion(Adoptante adoptante, Mascota mascota, int numMotivo) {
        this.codigoDe = "DEV-" + contador++;
        this.adoptante = adoptante;
        this.mascota = mascota;
        this.fechaDe = LocalDate.now();
        this.motivoDe = obtenerMotivo(numMotivo);
        this.nivelGra = calcularGravedad(numMotivo);
        this.resultadoEva = "";
    }

    public String getCodigoDe() {
        return codigoDe;
    }

    public LocalDate getFechaDe() {
        return fechaDe;
    }

    public void setFechaDe(LocalDate fechaDe) {
        this.fechaDe = fechaDe;
    }

    public String getMotivoDe() {
        return motivoDe;
    }

    public void setMotivoDe(int numMotivo) {
        this.motivoDe = obtenerMotivo(numMotivo);
        this.nivelGra = calcularGravedad(numMotivo);
    }

    public void setResultadoEva(String resultadoEva) {
        this.resultadoEva = resultadoEva;
    }

    public int getNivelGra() {
        return nivelGra;
    }

    public String getResultadoEva() {
        return resultadoEva;
    }

    public Adoptante getAdoptante() {
        return adoptante;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public static void registrarDevolucion() {

        String cedula = Valida.leerDiezDigitos("\nIngrese la cédula del adoptante");

        Adoptante adoptante = Lista.buscarAdoptante(cedula);

        if (adoptante == null) {
            System.out.println("Error: el adoptante no se encuentra registrado.");
            return;
        }

        ArrayList<Mascota> mascotasAdoptadas = Lista.buscarMascotasAdoptadas(cedula);

        if (mascotasAdoptadas.isEmpty()) {
            System.out.println("Error: el adoptante no tiene mascotas actualmente adoptadas.");
            return;
        }

        String codigoM = Valida.leerString("\nIngrese el código de la mascota a devolver");
        Mascota mascota = Lista.buscarMascota(codigoM);

        if (mascota == null || mascota.getEstado() != 2) {
            System.out.println("Error: la mascota no se encuentra actualmente adoptada.");
            return;
        }

        System.out.println("Relación adoptante-mascota verificada.");

        mostrarMotivos();

        int numMotivo = Valida.leerIntervalo("\nSeleccione el motivo de devolución", 1, 6);

        // Crear registro de devolución
        Devolucion nuevaDevolucion = new Devolucion(
                adoptante,
                mascota,
                numMotivo
        );

        System.out.println("Motivo de devolución registrado y evaluado correctamente.");

        if (nuevaDevolucion.getNivelGra() > 2) {
            adoptante.setEstadoPerfil(3);
            nuevaDevolucion.setResultadoEva("Adoptante inhabilitado para futuras adopciones.");
        } else {
            nuevaDevolucion.setResultadoEva("Adoptante continúa habilitado para futuras adopciones.");
        }

        // Devolver mascota al catálogo
        mascota.setEstado(1);

        // Guardar devolución en el historial
        Lista.getDevolucion().add(nuevaDevolucion);

        nuevaDevolucion.mostrarInformacionDevolucion();
    }

    public void mostrarMenu() {
        System.out.println("\n\n --- MENU DEVOLUCIONES ---");
        System.out.println("\n1. Registrar devolucion");
        System.out.println("\n2. Ver historial");
        System.out.println("\n3. Salir");
    }

    public static void mostrarMotivos() {
        System.out.println("\n\n --- MOTIVOS ---");
        System.out.println("\n 1. Maltrato o negligencia (Gravedad 5)");
        System.out.println("\n 2. Abandono o falta de cuidado (Gravedad 5)");
        System.out.println("\n 3. Agresividad grave (Gravedad 4)");
        System.out.println("\n 4. Incompatibilidad de convivencia (Gravedad 2)");
        System.out.println("\n 5. Mudanza o cambio de rutina (Gravedad 2)");
        System.out.println("\n 6. Problemas economicos (Gravedad 1)");
    }

    public static String obtenerMotivo(int numMotivo) {
        if (numMotivo == 1) return "Maltrato o negligencia";
        if (numMotivo == 2) return "Abandono o falta de cuidado";
        if (numMotivo == 3) return "Agresividad grave";
        if (numMotivo == 4) return "Incompatibilidad de convivencia";
        if (numMotivo == 5) return "Mudanza o cambio de rutina";
        if (numMotivo == 6) return "Problemas economicos";
        return "";
    }

    public static int calcularGravedad(int numMotivo) {
        if (numMotivo == 1 || numMotivo == 2) return 5;
        if (numMotivo == 3) return 4;
        if (numMotivo == 4 || numMotivo == 5) return 2;
        if (numMotivo == 6) return 1;
        return -1;
    }

    public void mostrarInformacionDevolucion() {

        System.out.println("\n --- DEVOLUCIÓN REGISTRADA ---");

        System.out.println("\nCódigo de devolución: " + codigoDe);
        System.out.println("\nFecha de devolución: " + fechaDe);
        System.out.println("\nMotivo: " + motivoDe);
        System.out.println("\nNivel de gravedad: " + nivelGra);
        System.out.println("\nResultado de evaluación: " + resultadoEva);

        System.out.println("\n --- ADOPTANTE ASOCIADO --- ");

        System.out.println("\nNombre: " + adoptante.getNombreCompleto());
        System.out.println("\nCédula: " + adoptante.getCedula());
        System.out.println("\nTeléfono: " + adoptante.getTelefono());

        System.out.println("\n --- MASCOTA DEVUELTA --- ");

        System.out.println("\nCódigo: " + mascota.getCodigoM());
        System.out.println("\nNombre: " + mascota.getNombre());
        System.out.println("\nEstado: Disponible");
    }
}
