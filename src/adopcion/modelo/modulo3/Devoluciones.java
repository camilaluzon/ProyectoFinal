package adopcion.modelo.modulo3;

import java.time.LocalDate;
import java.util.Scanner;
import adopcion.modelo.modulo1.Adoptante;
import adopcion.modelo.modulo2.Mascota;

public class Devoluciones {
    private String  codigoDe;
    private LocalDate fechaDe;
    private String motivoDe;
    private int nivelGra;
    private String resultadoEva;
    private Adoptante adoptante;
    private Mascota mascota;

    public Devoluciones() {
        this.adoptante = adoptante;
        this.mascota = mascota;
        this.fechaDe = fechaDe;
        this.motivoDe = motivoDe;
        this.nivelGra = nivelGra;
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

    public void setMotivoDe(String motivoDe) {
        this.motivoDe = motivoDe;
        this.nivelGra = nivelGra;
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

    public void mostrarMenu(Scanner sc) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\nMwnu Devoluciones");
            System.out.println("1. Registrar devolucion");
            System.out.println("2. Ver historial");
            System.out.println("0. Salir");
            System.out.print("Elija una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

        }
    }

    public void mostrarMotivos() {
        System.out.println("Motivos permitidos:");
        System.out.println("1. Maltrato o negligencia (Gravedad 5)");
        System.out.println("2. Abandono o falta de cuidado (Gravedad 5)");
        System.out.println("3. Agresividad grave (Gravedad 4)");
        System.out.println("4. Incompatibilidad de convivencia (Gravedad 2)");
        System.out.println("5. Mudanza o cambio de rutina (Gravedad 2)");
        System.out.println("6. Problemas economicos (Gravedad 1)");
    }

    public String obtenerMotivo(int numMotivo) {
        if (numMotivo == 1) return "Maltrato o negligencia";
        if (numMotivo == 2) return "Abandono o falta de cuidado";
        if (numMotivo == 3) return "Agresividad grave";
        if (numMotivo == 4) return "Incompatibilidad de convivencia";
        if (numMotivo == 5) return "Mudanza o cambio de rutina";
        if (numMotivo == 6) return "Problemas economicos";
        return "";
    }

    public int calcularGravedad(int numMotivo) {
        if (numMotivo == 1 || numMotivo == 2) return 5;
        if (numMotivo == 3) return 4;
        if (numMotivo == 4 || numMotivo == 5) return 2;
        if (numMotivo == 6) return 1;
        return -1;
    }
}
