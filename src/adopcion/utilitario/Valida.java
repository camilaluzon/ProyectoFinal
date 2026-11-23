package adopcion.utilitario;

import java.util.Scanner;

public class Valida {
    private static Scanner sc = new Scanner(System.in);

    public static String leerString(String mensaje) {

        String valor;

        do {
            System.out.print(mensaje + ": ");
            valor = sc.nextLine();

            if (valor == null || valor.trim().isEmpty()) {
                System.out.println("Error. El dato no puede estar vacio.");
            }

        } while (valor == null || valor.trim().isEmpty());

        return valor;
    }

    public static int leerEntero(String mensaje) {

        int valor;

        do {

            System.out.print(mensaje + ": ");

            while (!sc.hasNextInt()) {
                System.out.println("Ingrese un numero entero valido.");
                sc.next();
                System.out.print(mensaje + ": ");
            }

            valor = sc.nextInt();
            sc.nextLine();

            if (valor <= 0) {
                System.out.println("El numero debe ser mayor que cero.");
            }

        } while (valor <= 0);

        return valor;
    }

    public static double leerDouble(String mensaje) {

        double valor;

        do {

            System.out.print(mensaje + ": ");

            while (!sc.hasNextDouble()) {
                System.out.println("Ingrese un numero valido.");
                sc.next();
                System.out.print(mensaje + ": ");
            }

            valor = sc.nextDouble();
            sc.nextLine();

            if (valor <= 0) {
                System.out.println("El numero debe ser mayor que cero.");
            }

        } while (valor <= 0);

        return valor;
    }

    public static String validarRespuestaSiNo(String mensaje) {
        String respuesta;

        do {
            System.out.println(mensaje);
            respuesta = leerString(mensaje);

            if (!respuesta.equalsIgnoreCase("si") && !respuesta.equalsIgnoreCase("no")) {
                System.out.println("Ingrese lo solicitado (si/no).");
            }

        } while (!respuesta.equalsIgnoreCase("si") && !respuesta.equalsIgnoreCase("no"));

        return respuesta;
    }

    public static String leerDiezDigitos(String mensaje) {
        String valor;

        do {
            System.out.print(mensaje + ": ");
            valor = sc.nextLine();

            if (valor == null || valor.trim().isEmpty()) {
                System.out.println("Error: El dato no puede estar vacío.");

            } else if (!valor.matches("[0-9]{10}")) {
                System.out.println("Error: Debe ingresar exactamente 10 dígitos numéricos.");
            }

        } while (valor == null || valor.trim().isEmpty() || !valor.matches("[0-9]{10}"));

        return valor;
    }

    public static int leerIntervalo(String mensaje, int minimo, int maximo) {
        int valor;

        do {
            System.out.print(mensaje + ": ");

            while (!sc.hasNextInt()) {
                System.out.println("Error: Ingrese un número entero válido.");
                sc.next();
                System.out.print(mensaje + ": ");
            }

            valor = sc.nextInt();
            sc.nextLine();

            if (valor < minimo || valor > maximo) {
                System.out.println("Error: El número debe estar entre " + minimo + " y " + maximo + ".");
            }

        } while (valor < minimo || valor > maximo);

        return valor;
    }

    public static String leerObservaciones(String mensaje) {
        String valor;

        do {
            System.out.print(mensaje + ": ");
            valor = sc.nextLine();

            if (valor == null || valor.trim().isEmpty()) {
                System.out.println("Error: La observación no puede estar vacía.");

            } else {

                if (valor.length() > 150) {
                    System.out.println("Error: La observación supera el límite de 150 caracteres.");
                }
            }

        } while (valor == null || valor.trim().isEmpty() || valor.length() > 150);

        return valor;
    }
}
