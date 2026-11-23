package adopcion.interfaz;

public class MainAdopcion {
    static void main() {
        //public class MainAdoptante {
        GestionAdoptante gestion = new GestionAdoptante();
        gestion.mostrarMenu();
    }

    //mascota
    Scanner scanner = new Scanner(System.in);
    GestionMascotasCompatibilidad sistema = new GestionMascotasCompatibilidad();
    String opcion = "";

        while (!opcion.equals("0")) {
        System.out.println("\n=== MÓDULO 2: MASCOTAS Y COMPATIBILIDAD ===");
        System.out.println("1. M1 - Registrar mascota");
        System.out.println("2. M2 - Consultar y actualizar perfil de mascota");
        System.out.println("3. M3 - Eliminar perfil de mascota");
        System.out.println("4. C1 - Evaluar porcentaje de compatibilidad");
        System.out.println("5. C2 - Generar reporte de mascotas compatibles");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        opcion = scanner.nextLine().trim();

        switch (opcion) {
            case "1":
                sistema.registrarMascota();
                break;
            case "2":
                sistema.consultarYActualizarMascota();
                break;
            case "3":
                sistema.eliminarPerfilMascota();
                break;
            case "4":
                sistema.evaluarPorcentajeCompatibilidad();
                break;
            case "5":
                sistema.generarReporteCompatibles();
                break;
            case "0":
                System.out.println("Saliendo del módulo...");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
}
