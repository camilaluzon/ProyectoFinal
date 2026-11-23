package adopcion.modelo.modulo2;

/*
 * Variables utilizadas en esta clase:
 * mascotas, adoptantes, contador, sc, codigo, m, cedula, a, txt, e, actual,
 * nombre, especie, edadTxt, tamTxt, enfResp, descEnf, enerTxt, socTxt,
 * estadoTxt, edad, limiteEdad, tamano, tieneEnf, energia, sociabilidad,
 * nueva, nuevoEstado, op, esp, hay, resp, motivo, pts, seleccionada,
 * lista, porc, p, i, j, auxP, auxM
 */
import java.util.ArrayList;
import java.util.Scanner;

import adopcion.modelo.modulo2.Mascota;
import adopcion.modelo.modulo1.Adoptante;
import adopcion.utilitario.Valida;
import adopcion.utilitario.Lista;

public class GestionMascota {
    private ArrayList<Mascota> mascotas = new ArrayList<>();
    private ArrayList<Adoptante> adoptantes = new ArrayList<>();
    private int contador = 1;
    private Scanner sc = new Scanner(System.in);

    private Mascota buscarMascota(String codigo) {
        for (Mascota m : mascotas) {
            if (m.getCodigo().equalsIgnoreCase(codigo)) {
                return m;
            }
        }
        return null;
    }

    private Adoptante buscarAdoptante(String cedula) {
        for (Adoptante a : adoptantes) {
            if (a.getCedula().equals(cedula)) {
                return a;
            }
        }
        return null;
    }

    private int aEntero(String txt) {
        try {
            return Integer.parseInt(txt);
        } catch (Exception e) {
            return -1;
        }
    }

    private double aDouble(String txt) {
        try {
            return Double.parseDouble(txt);
        } catch (Exception e) {
            return -1;
        }
    }

    private void registrarMascota(Mascota actual) {

        System.out.println("\n--- DATOS DE LA MASCOTA ---");

        String nombre = Valida.leerString("\nIngrese nombre de la mascota");

        Mascota.mEspecie();
        int especie = Valida.leerIntervalo("\nSeleccione la especie", 1, 2);

        int edad;
        if (actual == null) {
            edad = Valida.leerEntero("\nIngrese edad de la mascota");
        } else {
            int limiteEdad = 20;
            edad = Valida.leerIntervalo("\nIngrese edad de la mascota", 0, limiteEdad);
        }

        double tamanoAproximado = 0;

        do {
            tamanoAproximado = Valida.leerDouble("\nIngrese tamaño aproximado del perro (cm)");

            if (tamanoAproximado > 150) {
                System.out.println("Error: El tamaño máximo permitido es de 150 cm.");
            }
        } while (tamanoAproximado > 150);
    }

    // Presencia de enfermedades
    boolean presenciaEnfermedades = false;
    int descripcionEnfermedad = 0;

            if (Valida.validarRespuestaSiNo("\n¿La mascota presenta enfermedades? (si/no)").equalsIgnoreCase("si")) {

        presenciaEnfermedades = true;

        if (especie == 1) {
            Mascota.mEnfermedadesP();
        } else {
            Mascota.mEnfermedadesG();
        }

        descripcionEnfermedad = Valida.leerIntervalo("\nSeleccione la enfermedad correspondiente", 1, 10);
    }

    // Nivel de energía
            Mascota.mNivelEnergia();
    int nivelEnergia = Valida.leerIntervalo("\nSeleccione el nivel de energía", 1, 4);

    // Nivel de sociabilidad
            Mascota.mNivelSociabilidad();
    int nivelSociabilidad = Valida.leerIntervalo("\nSeleccione el nivel de sociabilidad", 1, 4);

    // Registrar mascota

    Mascota nueva = new Mascota(
            nombre,
            especie,
            edad,
            tamanoAproximado,
            presenciaEnfermedades,
            descripcionEnfermedad,
            nivelEnergia,
            nivelSociabilidad,
            codigo,
            );

            Lista.getMascotas().add(nueva);

            System.out.println("\nRegistro exitoso. La mascota ha sido registrada correctamente.");
            System.out.println("\nCódigo generado: " + nueva.getCodigoM());
}

// Consultar mascota
public void consultarMascota() {

    System.out.println("\n--- CONSULTAR MASCOTAS ---");

    // Comprobar si existen mascotas registradas
    if (Lista.getMascotas().isEmpty()) {
        System.out.println("\nNo existen mascotas registradas en el sistema.");
        return;
    }

    // Mostrar todas las mascotas
    System.out.println("\n--- MASCOTAS REGISTRADAS ---");

    for (Mascota mascota : Lista.getMascotas()) {

        String especie;

        if (mascota.getEspecie() == 1) {
            especie = "Perro";
        } else {
            especie = "Gato";
        }

        System.out.println("\nNombre: " + m.getNombre());
        System.out.println("\nEspecie: " + especie);
        System.out.println("\nCódigo: " + m.getCodigoM());
    }

    // Solicitar el código de la mascota que desea consultar
    String codigo = Valida.leerString("\nIngrese el código de la mascota que desea consultar");

    Mascota mascota = Lista.buscarMascota(codigo);

    if (mascota == null) {
        System.out.println("\nError: No se encontró una mascota con ese código.");
        return;
    }

    // Mostrar información completa
    mascota.mostrarInformacion();
}

public void consultarYActualizarMascota() {
    System.out.println("\n--- CONSULTAR Y ACTUALIZAR ---");
    System.out.print("Buscar por (1) Código o (2) Especie: ");
    String op = sc.nextLine().trim();
    String codigo = "";

    if (op.equals("1")) {
        System.out.print("Código de mascota: ");
        codigo = sc.nextLine().trim();
    } else if (op.equals("2")) {
        System.out.print("Especie (perro/gato): ");
        String esp = sc.nextLine().trim().toLowerCase();
        if (!esp.equals("perro") && !esp.equals("gato")) {
            System.out.println("Error: La especie debe ser perro o gato.");
            return;
        }
        boolean hay = false;
        for (Mascota m : mascotas) {
            if (m.getEspecie().equalsIgnoreCase(esp)) {
                System.out.println("-----\n" + m.obtenerResumen(true));
                hay = true;
            }
        }
        if (!hay) {
            System.out.println("Error: La mascota no se encuentra registrada en el sistema.");
            return;
        }
        System.out.print("Ingrese el código de la mascota a seleccionar: ");
        codigo = sc.nextLine().trim();
    } else {
        System.out.println("Opción no válida.");
        return;
    }

    Mascota m = buscarMascota(codigo);
    if (m == null) {
        System.out.println("Error: La mascota no se encuentra registrada en el sistema.");
        return;
    }

    System.out.println("\n" + m.obtenerResumen(true));
    System.out.print("¿Desea actualizar esta mascota? (Si/No): ");
    String resp = sc.nextLine().trim().toLowerCase();
    if (resp.equals("si") || resp.equals("sí")) {
        guardarDatosMascota(m);
    } else {
        System.out.println("Operación exitosa. La información de la mascota se ha consultado o actualizado correctamente.");
    }
}

public void eliminarPerfilMascota() {
    System.out.println("\n--- M3: ELIMINAR PERFIL ---");
    System.out.print("Código de la mascota: ");
    Mascota m = buscarMascota(sc.nextLine().trim());

    if (m == null) {
        System.out.println("Error: La mascota ingresada no está registrada");
        return;
    }
    if (m.getEstado().equalsIgnoreCase("Adoptada")) {
        System.out.println("Error: No se puede retirar una mascota que ya fue adoptada");
        return;
    }

    System.out.print("Motivo de retiro (máx 150 caracteres): ");
    String motivo = sc.nextLine().trim();
    if (motivo.isEmpty()) {
        System.out.println("Error: Debe especificar el motivo del retiro");
        return;
    }
    if (motivo.length() > 150) {
        System.out.println("Error: El motivo supera la longitud máxima permitida");
        return;
    }

    m.setEstado("Inactivo");
    m.setMotivoRetiro(motivo);
    System.out.println("La mascota ha sido retirada correctamente del catálogo");
}

public double calcularCompatibilidad(Adoptante a, Mascota m) {
    if (a.isTieneNinos() && m.getNivelSociabilidad() == 1) {
        return 0;
    }

    double pts = 0;

    if (m.getNivelSociabilidad() == 3) {
        pts += 35;
    } else if (m.getNivelSociabilidad() == 2) {
        pts += 25;
    } else {
        pts += 20;
    }

    if (m.getEspecie().equalsIgnoreCase("gato") || m.getTamanoAproximado() <= 40) {
        pts += 35;
    } else if (a.getEspacioDisponible().equalsIgnoreCase("Grande")) {
        pts += 35;
    } else if (a.getEspacioDisponible().equalsIgnoreCase("Mediano")) {
        pts += 25;
    } else {
        pts += 10;
    }

    if (m.getNivelEnergia() == 1 || a.getHorasFueraCasa() <= 4) {
        pts += 30;
    } else if (a.getHorasFueraCasa() <= 6) {
        pts += 20;
    } else {
        pts += 10;
    }

    return pts;
}

private Adoptante validarAdoptante() {
    System.out.print("Cédula del adoptante: ");
    Adoptante a = buscarAdoptante(sc.nextLine().trim());
    if (a == null) {
        System.out.println("Error: El adoptante ingresado no está registrado");
        return null;
    }
    if (!a.getEstado().equalsIgnoreCase("Activo/Habilitado")) {
        System.out.println("Error: El adoptante está inhabilitado para realizar una adopción");
        return null;
    }
    return a;
}

public void evaluarPorcentajeCompatibilidad() {
    System.out.println("\n--- C1: EVALUAR COMPATIBILIDAD ---");
    Adoptante a = validarAdoptante();
    if (a == null) {
        return;
    }

    System.out.print("Código de la mascota: ");
    Mascota seleccionada = buscarMascota(sc.nextLine().trim());
    if (seleccionada == null || !seleccionada.getEstado().equalsIgnoreCase("Disponible")) {
        System.out.println("Error: La mascota ingresada no se encuentra disponible");
        return;
    }

    System.out.println("Compatibilidad con " + seleccionada.getNombre() + ": " + calcularCompatibilidad(a, seleccionada) + "%");
    System.out.println("\nTodas las mascotas disponibles:");
    for (Mascota m : mascotas) {
        if (m.getEstado().equalsIgnoreCase("Disponible")) {
            System.out.println("- " + m.getNombre() + ": " + calcularCompatibilidad(a, m) + "%");
        }
    }
}

public void generarReporteCompatibles() {
    System.out.println("\n--- C2: REPORTE DE COMPATIBLES ---");
    Adoptante a = validarAdoptante();
    if (a == null) {
        return;
    }

    ArrayList<Mascota> lista = new ArrayList<>();
    ArrayList<Double> porc = new ArrayList<>();

    for (Mascota m : mascotas) {
        if (m.getEstado().equalsIgnoreCase("Disponible")) {
            double p = calcularCompatibilidad(a, m);
            if (p > 60) {
                lista.add(m);
                porc.add(p);
            }
        }
    }

    if (lista.isEmpty()) {
        System.out.println("No se encontraron mascotas con el nivel mínimo de compatibilidad requerido");
        return;
    }

    for (int i = 0; i < porc.size() - 1; i++) {
        for (int j = 0; j < porc.size() - 1 - i; j++) {
            if (porc.get(j) < porc.get(j + 1)) {
                double auxP = porc.get(j);
                porc.set(j, porc.get(j + 1));
                porc.set(j + 1, auxP);

                Mascota auxM = lista.get(j);
                lista.set(j, lista.get(j + 1));
                lista.set(j + 1, auxM);
            }
        }
    }

    for (int i = 0; i < lista.size(); i++) {
        System.out.println("-----");
        System.out.println(lista.get(i).obtenerResumen(false));
        System.out.println("Compatibilidad: " + porc.get(i) + "%");
    }
}

