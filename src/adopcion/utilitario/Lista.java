package adopcion.utilitario;

import java.util.ArrayList;
import adopcion.modelo.modulo1.Adoptante;
import adopcion.modelo.modulo2.Mascota;

public class Lista {
    // Listas únicas compartidas por todo el programa
    private static ArrayList<Adoptante> adoptantes = new ArrayList<>();
    private static ArrayList<Mascota> mascotas = new ArrayList<>();
    // private ArrayList<Vacuna> vacunas = new ArrayList<>();
    // private ArrayList<AceptacionTerminos> adopciones = new ArrayList<>();
    // private ArrayList<Devoluciones> devoluciones = new ArrayList<>();

    // Getters para que cualquier módulo acceda a las listas
    public static ArrayList<Adoptante> getAdoptantes() {
        return adoptantes;
    }

    public static ArrayList<Mascota> getMascotas() {
        return mascotas;
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

    public static Mascota buscarMascota(String codigo) {
        for (Mascota m : mascotas) {
            if (m.getCodigo().equalsIgnoreCase(codigo)) {
                return m;
            }
        }
        return null;
    }

    // Cargar datos de prueba
    public static void cargarDatosPrueba() {

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
            mascotas.add(new Mascota(
                    "Max", 1, 4, 55.0,
                    false, 0, 3, 3
            ));

            mascotas.add(new Mascota(
                    "Rocky", 1, 2, 30.0,
                    true, 3, 2, 2
            ));

            // Gatos (especie 2)
            mascotas.add(new Mascota(
                    "Luna", 2, 3, 0,
                    false, 0, 2, 3
            ));

            mascotas.add(new Mascota(
                    "Michi", 2, 5, 0,
                    true, 3, 1, 2
            ));
        }

        System.out.println("Datos de prueba cargados correctamente.");
    }
}
