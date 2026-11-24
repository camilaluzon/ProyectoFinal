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
import adopcion.utilitario.Lista;
import adopcion.utilitario.Valida;
import adopcion.modelo.modulo1.Adoptante;
import adopcion.modelo.modulo1.GestionAdoptante;
import adopcion.modelo.modulo4.AceptacionTermino;

public class GestionMascota {
    private Scanner sc = new Scanner(System.in);

    private void registrarMascota(Mascota actual) {
        System.out.println("\n --- REGISTRAR MASCOTA ---");

        String nombre = Valida.leerString("\nIngrese nombre de la mascota");
        Mascota.mEspecie();
        int especie = Valida.leerIntervalo("\nSeleccione la especie", 1, 2);
        int edad=Valida.leerIntervalo("\nIngrese edad de la mascota", 0, 25);

        int tamanoAproximado = Valida.leerIntervalo("\nIngrese tamaño (altura) aproximado del perro (cm)",5 , 150);

        // Presencia de enfermedades
        boolean presenciaEnfermedades = false;
        int descripcionEnfermedad = 0;

        if (Valida.validarRespuestaSiNo("\n¿La mascota presenta enfermedades? (si/no)").equalsIgnoreCase("si")) { //en registrar
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
                nombre,especie,edad,tamanoAproximado,presenciaEnfermedades,
                descripcionEnfermedad,nivelEnergia,nivelSociabilidad
        );

        Lista.getMascotas().add(nueva);

        System.out.println("\nCódigo generado: " + nueva.getCodigoM());
        System.out.println("\nRegistro exitoso. La mascota ha sido registrada correctamente.");
    }
    // Consultar mascota
    public void consultarMascota() {

        // Comprobar si existen mascotas registradas
        if (Lista.getMascotas().isEmpty()) {
            System.out.println("\nNo existen mascotas registradas en el sistema.");
        }else{
            System.out.println("\n--- CONSULTAR MASCOTAS ---");
            String codigo = Valida.leerString("\nIngrese el código de la mascota que desea consultar");
            Mascota mascota = Lista.buscarMascota(codigo);

            if (mascota == null) {
                System.out.println("\nError: No se encontró una mascota con ese código.");
            } else {
                mascota.mostrarInformacion();
            }
        }
    }

    public void ActualizarMascota() {

        if (Lista.getMascotas().isEmpty()) {
            System.out.println("\nNo existen mascotas registradas en el sistema.");
            return;
        }
        System.out.println("\n --- ACTUALIZAR MASCOTA ---");
        String codigo = Valida.leerString("\nIngrese el código de la mascota que desea consultar");
        Mascota mascota = Lista.buscarMascota(codigo);

        if (mascota == null) {
            System.out.println("\nError: No se encontró una mascota con ese código.");
            return;
        }

        String nombre = Valida.leerString("\nIngrese nombre de la mascota");
        Mascota.mEspecie();
        int especie = Valida.leerIntervalo("\nSeleccione la especie", 1, 2);
        int edad=Valida.leerIntervalo("\nIngrese edad de la mascota", 0, 25);

        int tamanoAproximado = Valida.leerIntervalo("\nIngrese tamaño (altura) aproximado del perro (cm)",5 , 150);

        // Presencia de enfermedades
        boolean presenciaEnfermedades = false;
        int descripcionEnfermedad = 0;

        if (Valida.validarRespuestaSiNo("\n¿La mascota presenta enfermedades? (si/no)").equalsIgnoreCase("si")) { //en registrar
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

        mascota.setNombre(nombre);
        mascota.setEspecie(especie);
        mascota.setEdad(edad);
        mascota.setTamanoAproximado(tamanoAproximado);
        mascota.setPresenciaEnfermedades(presenciaEnfermedades);
        mascota.setDescripcionEnfermedad(descripcionEnfermedad);
        mascota.setNivelEnergia(nivelEnergia);
        mascota.setNivelSociabilidad(nivelSociabilidad);

        System.out.println("\nCódigo generado: " + mascota.getCodigoM());
        System.out.println("\nRegistro exitoso. La mascota ha sido registrada correctamente.");
    }

    public void eliminarPerfilMascota() {

        if (Lista.getMascotas().isEmpty()) {
            System.out.println("\nNo existen mascotas registradas en el sistema.");
            return;
        }

        System.out.println("\n--- ELIMINAR PERFIL DE MASCOTA ---");

        String codigo = Valida.leerString("\nIngrese código de la mascota");
        Mascota m = Lista.buscarMascota(codigo);

        if (m == null) {
            System.out.println("\nError: La mascota no se encuentra registrada.");
            return;
        }

        if (m.getEstado() == 2){
            System.out.println("\nError: No se puede retirar una mascota que ya fue adoptada");
            return;
        } else if (m.getEstado() == 3) {
            System.out.println("\nError: La mascota ya se encuentra retirada.");
            return;
        }

        if (Valida.validarRespuestaSiNo("\n¿Está seguro de eliminar su perfil? (si/no)").equalsIgnoreCase("si")) {
            String motivo=Valida.leerObservaciones("\nIngrese el motivo de retirada")
            m.setEstado(3);
            m.setMotivoEliminacion(motivo);
            System.out.println("\nLa mascota ha sido retirada correctamente del catálogo");
            return;
        }
        System.out.println("\nOperación cancelada. No se realizaron cambios.");

    }

    //Compatibilidad
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
}
