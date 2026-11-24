package adopcion.modelo.modulo2;

import java.util.ArrayList;
import java.util.Scanner;
import adopcion.utilitario.Lista;
import adopcion.utilitario.Valida;
import adopcion.modelo.modulo1.Adoptante;
import adopcion.modelo.modulo1.GestionAdoptante;

public class Mascota {
    private static int contador = 1;
    private String codigom;
    private String nombre;
    private int especie;//
    private int edad;
    private int tamanoAproximado;
    private boolean presenciaEnfermedades;
    private int descripcionEnfermedad;//
    private int nivelEnergia;//
    private int nivelSociabilidad;//
    private int estado;
    private String motivoEliminacion;

    public Mascota(String nombre, int especie, int edad, int tamanoAproximado,
                   boolean presenciaEnfermedades, int descripcionEnfermedad,
                   int nivelEnergia, int nivelSociabilidad) {
        this.codigom = "M" + contador++;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.tamanoAproximado = tamanoAproximado;
        this.presenciaEnfermedades = presenciaEnfermedades;
        this.descripcionEnfermedad = descripcionEnfermedad;
        this.nivelEnergia = nivelEnergia;
        this.nivelSociabilidad = nivelSociabilidad;
        this.estado = 1;
        this.motivoEliminacion = " ";
    }

    public String getCodigoM() {
        return codigom;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEspecie() {
        return especie;
    }

    public void setEspecie(int especie) {
        this.especie = especie;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getTamanoAproximado() {
        return tamanoAproximado;
    }

    public void setTamanoAproximado(int tamanoAproximado) {
        this.tamanoAproximado = tamanoAproximado;
    }

    public boolean isPresenciaEnfermedades() {
        return presenciaEnfermedades;
    }

    public void setPresenciaEnfermedades(boolean presenciaEnfermedades) {
        this.presenciaEnfermedades = presenciaEnfermedades;
    }

    public int getDescripcionEnfermedad() {
        return descripcionEnfermedad;
    }

    public void setDescripcionEnfermedad(int descripcionEnfermedad) {
        this.descripcionEnfermedad = descripcionEnfermedad;
    }

    public int getNivelEnergia() {
        return nivelEnergia;
    }

    public void setNivelEnergia(int nivelEnergia) {
        this.nivelEnergia = nivelEnergia;
    }

    public int getNivelSociabilidad() {
        return nivelSociabilidad;
    }

    public void setNivelSociabilidad(int nivelSociabilidad) {
        this.nivelSociabilidad = nivelSociabilidad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getMotivoEliminacion() {
        return motivoEliminacion;
    }

    public void setMotivoEliminacion(String motivoEliminacion) {
        this.motivoEliminacion = motivoEliminacion;
    }

    public void mostrarInformacion(){
        String especiem;
        System.out.println("\nNombre: " + nombre);
        if (especie == 1) {
            especiem = "Perro";
        } else {
            especiem = "Gato";
        }
        System.out.println("\nEspecie: " + especiem);
        System.out.println("\nEdad: " + edad);
        System.out.println("\nTamaño aproximado: " + tamanoAproximado + "cm");
        String presencia = presenciaEnfermedades ? "Sí" : "No";
        System.out.println("\nPresencia de enfermedades: " + presencia);
        String descripcion = "";
        if (especie == 1) {
            switch (descripcionEnfermedad){
                case 1:
                    descripcion = "Parvovirus (gastroenteritis hemorrágica)";
                    break;
                case 2:
                    descripcion = "Moquillo";
                    break;
                case 3:
                    descripcion = "Tos de las perreras";
                    break;
                case 4:
                    descripcion = "Infección de oído";
                    break;
                case 5:
                    descripcion = "Alergia en la piel(atópica o por picadura de pulga)";
                    break;
                case 6:
                    descripcion = "Sarro e infección de encías";
                    break;
                case 7:
                    descripcion = "Lombrices o bichos estomacales";
                    break;
                case 8:
                    descripcion = "Desgaste de cadera y articulaciones";
                    break;
                case 9:
                    descripcion = "Leptospira (enfermedad por orina de rata)";
                    break;
                case 10:
                    descripcion = "Sobrepeso";
                    break;
            }
        } else {
            switch (descripcionEnfermedad){
                case 1:
                    descripcion = "Falla o insuficiencia de los riñones";
                    break;
                case 2:
                    descripcion = "Cistitis, piedras o tapón urinario.";
                    break;
                case 3:
                    descripcion = "Gripe felina";
                    break;
                case 4:
                    descripcion = "Virus de la leucemia felina";
                    break;
                case 5:
                    descripcion = "Sida felino";
                    break;
                case 6:
                    descripcion = "Moquillo felino (o parvovirus de gato)";
                    break;
                case 7:
                    descripcion = "Ácaros o sarna de las orejas.";
                    break;
                case 8:
                    descripcion = "Sarro e inflamación severa de encías.";
                    break;
                case 9:
                    descripcion = "Hígado graso";
                    break;
                case 10:
                    descripcion = "Diabetes o azúcar alta";
                    break;
            }
        }
        System.out.println("Descripcion de enfermedad: " + descripcion);
        String nievele;
        if (nivelEnergia == 1) {
            nievele = "Poca energía";
        } else if (nivelEnergia == 2) {
            nievele = "Normal";
        } else if (nivelEnergia == 3) {
            nievele = "Alta energía";
        } else {
            nievele = "Mucha Energía";
        }

        System.out.println("\nNivel de energía: " + nievele);
        String nievels;
        if (nivelSociabilidad == 1) {
            nievels = "Nada amigable";
        } else if (nivelSociabilidad == 2) {
            nievels = "Depende";
        } else if (nivelSociabilidad == 3) {
            nievels = "Amigable";
        } else {
            nievels = "Muy amigable";
        }
        System.out.println("\nNivel de sociabilidad: " + nievels);
        String estadom;
        if (estado == 1) {
            estadom = "Disponible";
        } else if (estado == 2) {
            estadom = "Adoptada";
        } else {
            estadom = "Retirada";
        }
        System.out.println("\nEstado: " + estadom);
    }

    public static void mEspecie(){
        System.out.println("\n\n--- ESPECIE DE LA MASCOTA ---");
        System.out.println("\n1.Perro");
        System.out.println("\n2.Gato");
    }

    public static void mEnfermedadesG(){
        System.out.println("\n\n--- ENFERMEDADES GATOS ---");
        System.out.println("\n1.Falla o insuficiencia de los riñones");
        System.out.println("\n2.Cistitis, piedras o tapón urinario.");
        System.out.println("\n3.Gripe felina");
        System.out.println("\n4.Virus de la leucemia felina");
        System.out.println("\n5.Sida felino");
        System.out.println("\n6.Moquillo felino (o parvovirus de gato)");
        System.out.println("\n7.Ácaros o sarna de las orejas.");
        System.out.println("\n8.Sarro e inflamación severa de encías.");
        System.out.println("\n9.Hígado graso");
        System.out.println("\n10.Diabetes o azúcar alta");
    }

    public static void mEnfermedadesP(){
        System.out.println("\n\n--- ENFERMEDADES PERROS ---");
        System.out.println("\n1.Parvovirus (gastroenteritis hemorrágica)");
        System.out.println("\n2.Moquillo");
        System.out.println("\n3.Tos de las perreras");
        System.out.println("\n4.Infección de oído");
        System.out.println("\n5.Alergia en la piel(atópica o por picadura de pulga)");
        System.out.println("\n6.Sarro e infección de encías");
        System.out.println("\n7.Lombrices o bichos estomacales");
        System.out.println("\n8.Desgaste de cadera y articulaciones");
        System.out.println("\n9.Leptospira (enfermedad por orina de rata)");
        System.out.println("\n10.Sobrepeso");
    }

    public static void mNivelEnergia() {
        System.out.println("\n\n--- NIVEL DE ENERGÍA ---");
        System.out.println("\n1. Poca energía (1 - 25)");
        System.out.println("\n2. Normal (26 - 50)");
        System.out.println("\n3. Alta energía (51 - 75)");
        System.out.println("\n3. Mucha Energía (76 - 100)");
    }

    public static void mNivelSociabilidad() {
        System.out.println("\n\n--- NIVEL DE SOCIABILIDAD ---");
        System.out.println("\nSociabilidad se refiere a que tan amigable es la mascota con personas extrañas o niños");
        System.out.println("\n1. Nada amigable (1 - 25)");
        System.out.println("\n2. Depende (26 - 50)");
        System.out.println("\n3. Amigable (51 - 75)");
        System.out.println("\n3. Muy amigable (76 - 100)");
    }

    /*public void mEstadosM() {
        System.out.println("\n\n--- ESTADO MASCOTA ---");
        System.out.println("\n1. Disponible");
        System.out.println("\n2. Adoptada");
        System.out.println("\n3. Retirada");
        }*/
}
