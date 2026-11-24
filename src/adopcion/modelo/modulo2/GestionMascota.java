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
import java.util.List;
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
            String motivo=Valida.leerObservaciones("\nIngrese el motivo de retirada");
            m.setEstado(3);
            m.setMotivoEliminacion(motivo);
            System.out.println("\nLa mascota ha sido retirada correctamente del catálogo");
            return;
        }
        System.out.println("\nOperación cancelada. No se realizaron cambios.");

    }

    //Compatibilidad
    public static int calcularCompatibilidad(Adoptante adoptante, Mascota mascota) {

        // Validaciones criticas y minimas que retornan 0
        if (mascota.getEstado()!=1 || adoptante.getEstadoPerfil()!=1) {
            return 0;}

        if (adoptante.getNumeroHijos()>0 && mascota.getNivelSociabilidad()==1) {
            return 0;}

        if (mascota.isPresenciaEnfermedades() && adoptante.getPresupuesto()<45) {
            return 0;}

        if (mascota.getEdad() > 10 && adoptante.getPresupuesto()<50) {
            return 0;}

        if (mascota.getNivelEnergia()>3 && adoptante.getHorasFueraCasa()>16 && adoptante.getEspacioExterior() < 4) {
            return 0;}

        int puntos = 0;

        //  Vivienda, Especie y Tamaño
        int vivienda = adoptante.getTipoVivienda();
        int especie = mascota.getEspecie();
        double tamano = mascota.getTamanoAproximado();

        if (vivienda == 1) { // Casa
            if (especie == 2) { puntos += 20;      // Gato
            }else { puntos += 15;} // Perro
        } else if (vivienda == 2) { // Departamento
            if (especie == 2) {
                puntos += 20; // Gato
            } else if (especie == 1) {
                puntos += 10; // Perro
                if (tamano <= 40){ puntos += 10;
                }else if (tamano <= 50) {puntos += 5;}
                // mayor a 51 suma 0
            }
        } else { // Hacienda
            puntos += 20; // Perro o Gato
        }

        // Espacio interior
        double espInterior = adoptante.getEspacioInterior();
        if (espInterior <= 34) {
            puntos -= 5;
        } else if (espInterior <= 45) {
            if (tamano > 50) puntos -= 5;
            // 0 a 50 suma 0
        } else { // >= 46
            puntos += 5;
        }

        // Espacio exterior
        if (adoptante.getEspacioExterior() < 4) {
            puntos -= 5;
        }

        // Experiencia previa
        if (adoptante.isExperienciaMascotas()) {
            if (especie == 2 && puntos > 15){ puntos += 5;// Gato
            }else if (especie == 1 && puntos > 5) {puntos += 5;}// Perro
        }

        // Presupuesto
        double presupuesto = adoptante.getPresupuesto();
        if (presupuesto < 45) {
            puntos -= 5;
        } else if (presupuesto > 55) {
            puntos += 5;
            if (mascota.getEdad() > 10) puntos += 10;
            if (tamano > 50) puntos += 10;
            if (presupuesto > 65 && mascota.isPresenciaEnfermedades()) puntos += 10;
        }

        // Niños y Sociabilidad
        int ninos = adoptante.getNumeroHijos();
        int sociabilidad = mascota.getNivelSociabilidad();

        if (ninos == 1) {
            if (sociabilidad >= 3){ puntos += 5;
            }else if (sociabilidad == 1) {puntos -= 5;}
        } else if (ninos > 1) {
            if (sociabilidad >= 3) {puntos += 10;
            }else if (sociabilidad == 2){ puntos -= 5;
            }else {puntos -= 10;}
        }

        int horas = adoptante.getHorasFueraCasa();
        int energia = mascota.getNivelEnergia();

        if (horas > 16) {
            puntos -= 5;
        } else if (horas >= 12 && horas <= 16) {
            // Suma 0 por horas, pero si energía (3 o 4) resta 5
            if (energia >= 3) {
                puntos -= 5;
            }
        } else if (horas >= 10 && horas <= 11) {
            puntos += 5;
        } else if (horas >= 0 && horas <= 9) {
            puntos += 10;
            // Si si energía (3 o 4) suma 5
            if (energia >= 3) {
                puntos += 5;
            }
        }

        return puntos;
    }

    public void generarReporteCompatibilidad (Adoptante adoptante){

        if (adoptante.getEstadoPerfil()==3) {
            System.out.println("\nEl adoptante esta ihabilitado y no puede adoptar");
            return;}

        List<Mascota> compatibles = new ArrayList<>();

        for (Mascota m : Lista.getMascotas()) {
            if (m.getEstado()==1 && calcularCompatibilidad(adoptante, m) >= 20){
                compatibles.add(m);
            }
        }

        if (compatibles.isEmpty()) {
            System.out.println("\nNo se encontraron mascotas compatibles para este adoptante.");
            return;
        }

        //Ordenar de mayor a menor
        compatibles.sort((m1, m2) -> Integer.compare(
                calcularCompatibilidad(adoptante, m2),
                calcularCompatibilidad(adoptante, m1)
        ));

        // Mostrar mascotas
        System.out.println("\n--- REPORTE DE COMPATIBILIDAD ---");

        for (Mascota m : compatibles) {
            int puntos = calcularCompatibilidad(adoptante, m);
            String nivel;
            if (puntos>=35) {
                nivel="\ncompatibilidad Alta (Ideal)";
            }else{
                nivel="\ncompatibilidad Media (Apta) ";
            }
            System.out.println("\nPuntaje: " + puntos + " puntos con una " + nivel);
            m.mostrarInformacion();
        }
    }

    public void realizarAdopcion(String cedulaAdoptante, String codigoMascota){
        System.out.println("\n--- PROCESO DE ADOPCIÓN ---");

        // Buscar adoptante
        Adoptante adoptante = Lista.buscarAdoptante(cedulaAdoptante);

        if (adoptante == null) {
            System.out.println("\nError: El adoptante no se encuentra registrado.");
            return;
        }

        // Verificar que el adoptante esté activo
        if (adoptante.getEstadoPerfil() != 1) {
            System.out.println("\nError: El adoptante no está habilitado para realizar adopciones.");
            return;
        }

        // Buscar mascota
        Mascota mascota = Lista.buscarMascota(codigoMascota);

        if (mascota == null) {
            System.out.println("\nError: La mascota no se encuentra registrada.");
            return;
        }

        // Verificar disponibilidad de la mascota
        if (mascota.getEstado() != 1) {
            System.out.println("\nError: La mascota no se encuentra disponible para adopción.");
            return;
        }

        // Verificar que no exista una adopción anterior entre ambos
        for (AceptacionTermino adopcion : Lista.getAdopcion()) {

            if (adopcion.getCedulaAdoptante().equals(cedulaAdoptante) && adopcion.getCodigoMascota().equalsIgnoreCase(codigoMascota)) {
                System.out.println("\nError: Ya existe una aceptación registrada para esta adopción.");
                return;
            }
        }

        // Mostrar términos y condiciones
        System.out.println("\n--- TÉRMINOS Y CONDICIONES DE ADOPCIÓN ---");
        System.out.println("\nDeclaro que he leído y acepto el compromiso de tenencia responsable.");
        System.out.println("\nMe comprometo a brindar los cuidados necesarios a la mascota.");
        System.out.println("\nAcepto las condiciones legales establecidas por el refugio.");

        // Solicitar aceptación
        String respuesta = Valida.validarRespuestaSiNo("\n¿Acepta los términos y condiciones? (si/no)");

        // Crear registro de aceptación
        AceptacionTermino aceptacion = new AceptacionTermino(
                cedulaAdoptante,
                codigoMascota
        );

        // Registrar respuesta
        aceptacion.registrarAceptacion(respuesta);

        // Comprobar aceptación
        if (!aceptacion.validarTerminos()) {System.out.println("\nLa adopción no se ha realizado.");
            return;
        }

        // Guardar aceptación en la lista compartida
        Lista.getAdopcion().add(aceptacion);

        // Cambiar estado de mascota a Adoptada
        mascota.setEstado(2);

        System.out.println("\n¡Adopción realizada exitosamente!");
        System.out.println("\nAdoptante: " + adoptante.getNombreCompleto());
        System.out.println("\nMascota: " + mascota.getNombre());
        System.out.println("\nCódigo de mascota: " + mascota.getCodigoM());
        System.out.println("\nFecha de aceptación: " + aceptacion.getFechaAceptacion());
    }
}
