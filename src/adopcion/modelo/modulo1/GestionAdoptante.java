package adopcion.modelo.modulo1;

import java.util.ArrayList;
import java.util.Scanner;
import adopcion.utilitario.Lista;
import adopcion.utilitario.Valida;
import adopcion.modelo.modulo1.Adoptante;

public class GestionAdoptante {
    private Scanner sc = new Scanner(System.in);

    // Validar cédula única
    private String validarCedulaUnica(Adoptante actual) {

        String cedula;
        Adoptante encontrado;

        do {
            cedula = Valida.leerDiezDigitos("\nIngrese cédula");
            encontrado = Lista.buscarAdoptante(cedula);

            if (encontrado != null && encontrado != actual) {
                System.out.println("\nError: Cédula ya registrada. Ingrese otra.");
            }
        } while (encontrado != null && encontrado != actual);

        return cedula;
    }

    // Registrar adoptante
    public void registrarAdoptante() {

        System.out.println("\nRegistrar Adoptante");

        String cedula =validarCedulaUnica(null);
        String nombre =Valida.leerString("\nIngresa nombre");
        String telefono =Valida.leerDiezDigitos("\nIngresa teléfono");
        String contrasena =Valida.leerString("\nIngresa contraseña");
        Adoptante.mVivienda();
        int tipoVivienda = Valida.leerIntervalo("\nIngrese su tipo de vivienda",1,3);
        double espacioInterior =Valida.leerDouble("\nIngresa espacio interior disponible (m2)");
        double espacioExterior =Valida.leerDouble("\nIngresa espacio exterior disponible (m2)");
        double horasFueraCasa =Valida.leerIntervalo("\nIngresa horas que pasa fuera de casa",0,24);
        boolean experienciaMascotas = false;
        int tipoMascota = 0;

        if (Valida.validarRespuestaSiNo("\n¿Tiene experiencia previa con mascotas? (si/no)").equalsIgnoreCase("si")) {
            experienciaMascotas = true;
            Adoptante.mMascota();
            tipoMascota = Valida.leerIntervalo("\nIngrese el tipo de mascota",1, 2);
        }

        double presupuesto = Valida.leerDouble("\nIngresa el presupuesto con el que cuenta para la futura mascota");
        int hijos = Valida.leerIntervalo("\nIngrese número de hijos (menores a 15 años)",0,10);
        String respuestaSeguridad=Valida.leerString("\n¿Sabor de helado favorito?").toLowerCase();

        // Asignar los atributos
        Adoptante nuevo = new Adoptante(
                cedula, nombre, telefono, contrasena, tipoVivienda,
                espacioInterior, espacioExterior, horasFueraCasa,
                experienciaMascotas, tipoMascota, presupuesto, hijos,
                respuestaSeguridad
        );

        Lista.getAdoptantes().add(nuevo);

        System.out.println("\nRegistro exitoso. El perfil del adoptante ha sido creado correctamente.");
    }

    // Consultar adoptante
    public void consultarAdoptante() {
        // Comprobar si existen adoptantes registradas
        if (Lista.getAdoptantes().isEmpty()) {
            System.out.println("\nNo existen mascotas registradas en el sistema.");
        }else{
            System.out.println("\n--- CONSULTAR ADOPTANTE ---");

            String cedula = Valida.leerDiezDigitos("\nIngrese cédula a consultar");
            Adoptante adoptante = Lista.buscarAdoptante(cedula);

            if (adoptante == null) {
                System.out.println("\nError: El adoptante no se encuentra registrado en el sistema.");
            } else {
                adoptante.mostrarInformacion();
            }
        }
    }

    // Actualizar adoptante
    public void actualizarAdoptante() {

        System.out.println("\n--- ACTUALIZAR PERFIL DEL ADOPTANTE ---");

        // Buscar al adoptante que se desea actualizar
        String cedulaActual = Valida.leerDiezDigitos("\nIngrese cédula actual");

        Adoptante adoptante = Lista.buscarAdoptante(cedulaActual);

        if (adoptante == null) {
            System.out.println("\nError: El adoptante no se encuentra registrado.");
            return;
        }

        // Validar que la nueva cédula no esté registrada
        String nuevaCedula = validarCedulaUnica(adoptante);

        String nombre = Valida.leerString("\nIngrese nuevo nombre");
        String telefono = Valida.leerDiezDigitos("\nIngrese nuevo teléfono");
        String contrasena = Valida.leerString("\nIngrese nueva contraseña");
        Adoptante.mVivienda();
        int tipoVivienda = Valida.leerIntervalo("\nIngrese tipo de vivienda", 1, 3);
        double espacioInterior = Valida.leerDouble("\nIngrese nuevo espacio interior (m2)");
        double espacioExterior = Valida.leerDouble("\nIngrese nuevo espacio exterior (m2)");
        int horasFueraCasa = Valida.leerIntervalo("\nIngrese nuevas horas fuera de casa", 0, 24);
        boolean experienciaMascotas = false;
        int tipoMascota = 0;
        if (Valida.validarRespuestaSiNo("\n¿Tiene experiencia previa con mascotas? (si/no)").equalsIgnoreCase("si")) {
            experienciaMascotas = true;
            Adoptante.mMascota();
            tipoMascota = Valida.leerIntervalo("\nIngrese tipo de mascota", 1, 2);
        }

        double presupuesto = Valida.leerDouble("\nIngrese nuevo presupuesto para la futura mascota");
        int hijos = Valida.leerIntervalo("\nIngrese número de hijos menores de 15 años", 0, 10);
        String respuestaSeguridad = Valida.leerString("\n¿Sabor de helado favorito?").toLowerCase();

        // Actualizar los atributos del adoptante existente
        adoptante.setCedula(nuevaCedula);
        adoptante.setNombreCompleto(nombre);
        adoptante.setTelefono(telefono);
        adoptante.setContrasena(contrasena);
        adoptante.setTipoVivienda(tipoVivienda);
        adoptante.setEspacioInterior(espacioInterior);
        adoptante.setEspacioExterior(espacioExterior);
        adoptante.setHorasFueraCasa(horasFueraCasa);
        adoptante.setExperienciaMascotas(experienciaMascotas);
        adoptante.setTipoMascotaAnterior(tipoMascota);
        adoptante.setPresupuesto(presupuesto);
        adoptante.setNumeroHijos(hijos);
        adoptante.setRespuestaSeguridad(respuestaSeguridad);

        System.out.println("\nOperación exitosa. El perfil del adoptante se ha actualizado correctamente.");
    }

    // Eliminar perfil del adoptante
    public void eliminarAdoptante() {

        System.out.println("\n--- ELIMINAR PERFIL ---");
        String cedula = Valida.leerDiezDigitos("\nIngrese cédula");
        Adoptante adoptante = Lista.buscarAdoptante(cedula);

        if (adoptante == null) {
            System.out.println("\nError: El adoptante no se encuentra registrado en el sistema.");
            return;
        }

        if (adoptante.getEstadoPerfil() == 2) {
            System.out.println("\nError: El perfil ya se encuentra inactivo.");
            return;
        }

        if (adoptante.getEstadoPerfil() == 3) {
            System.out.println("\nError: El perfil se encuentra inhabilitado.");
            return;
        }

        String confirmacion = Valida.validarRespuestaSiNo("\n¿Está seguro de eliminar su perfil? (si/no)");


        if (confirmacion.equalsIgnoreCase("si")) {
            adoptante.setEstadoPerfil(2);
            System.out.println("\nOperación exitosa. El perfil del adoptante ha sido desactivado correctamente.");
        } else {
            System.out.println("\nOperación cancelada. No se realizaron cambios.");
        }

    }

    public void recuperarContrasena(Adoptante adoptante) {
        String confirmacion = Valida.validarRespuestaSiNo("\n¿Desea cambiar su contraseña? (si/no)");

        if (confirmacion.equalsIgnoreCase("si")) {
            String respuesta = Valida.leerString("\n¿Sabor de helado favorito?").toLowerCase().trim();

            if (adoptante.getRespuestaSeguridad().equalsIgnoreCase(respuesta)) {
                String nuevaContrasena = Valida.leerString("\nIngrese nueva contraseña");
                adoptante.setContrasena(nuevaContrasena);
                System.out.println("\nContraseña actualizada correctamente. Inicie sesión con su nueva clave.");
            } else {
                System.out.println("\nError: La respuesta de seguridad es incorrecta, la contraseña no se cambiará.");
            }
        } else {
            System.out.println("\nOperación cancelada. La contraseña no se cambiará.");
        }
    }

    // Iniciar sesión
    public void iniciarSesion() {

        System.out.println("\n--- INICIO DE SESIÓN ---");

        String cedula = Valida.leerDiezDigitos("\nIngrese su cédula");

        Adoptante adoptante = Lista.buscarAdoptante(cedula);

        if (adoptante == null) {
            System.out.println("\nNo estás registrado. Por favor, registrar usuario.");
            return;
        }

        // Verificar primero si el perfil está activo
        if (adoptante.getEstadoPerfil() != 1) {
            System.out.println("\nError: Tu perfil se encuentra inactivo o inhabilitado. No puedes ingresar al sistema.");
            return;
        }

        String contrasena;

        do {
            contrasena = Valida.leerString("\nIngrese su contraseña");

            if (!adoptante.getContrasena().equals(contrasena)) {
                System.out.println("\nError: La contraseña es incorrecta.");
                recuperarContrasena(adoptante);
            }
        } while (!adoptante.getContrasena().equals(contrasena));

        System.out.println("\nInicio de sesión exitoso. Bienvenido al sistema.");
    }

    // Menú principal
    public void logicaMenu() {
        int opcion = 0;
        System.out.println("\n\nGESTIÓN DE ADOPTANTES");
        System.out.println("\n1. Registrar adoptante");
        System.out.println("\n2. Iniciar sesión");
        System.out.println("\n3. Actualizar perfil");
        System.out.println("\n4. Eliminar perfil");
        System.out.println("\n5. Consultar Adoptante");
        System.out.println("\n6. Salir");

        opcion = Valida.leerIntervalo("Seleccione una opción",1,6);

        switch (opcion){
            case 1:
                registrarAdoptante();
                break;
            case 2:
                iniciarSesion();
                break;
            case 3:
                actualizarAdoptante();
                break;
            case 4:
                eliminarAdoptante();
                break;
            case 5:
                consultarAdoptante();
                break;
            case 6:
                System.out.println("Saliendo del sistema... ");
                break;
        }
    }
}
