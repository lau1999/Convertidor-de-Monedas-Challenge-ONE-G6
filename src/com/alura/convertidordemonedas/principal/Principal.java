package com.alura.convertidordemonedas.principal;

import com.alura.convertidordemonedas.historial.HistorialConversiones;
import com.alura.convertidordemonedas.registro.RegistroMarcaTiempo;
import com.alura.convertidordemonedas.modelosapi.Conversion;
import java.util.Scanner;

public class Principal {

    public static final String RESET = "\033[0m";
    public static final String PURPLE = "\033[0;35m";
    public static final String YELLOW = "\033[0;33m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String MENU = """
            *************************************************
            BIENVENIDOS AL CONVERSOR DE MONEDAS!
            **************************************************
            1-Convertir de USD (Dólares) => a EUR (Euros).
            2-Convertir de EUR (Euros) => a USD (Dólares).
            3-Convertir de USD (Dólares) => a ARS (Peso Argentino).
            4-Convertir de ARS (Peso Argentino) => a USD (Dólares).
            5-Convertir de USD (Dólares) => a BOB (Peso Boliviano).
            6-Convertir de BOB (Peso Boliviano) => a USD (Dólares).
            7-Convertir de USD (Dólares) => a COP (Peso Colombiano).
            8-Convertir de COP (Peso Colombiano) => a USD (Dólares).
            9-Convertir de USD (Dólares) => a DOP (Peso Dominicano).
            10-Convertir de DOP (Peso Dominicano) => a USD (Dólares).
            11-Mostrar historial.
            12-Eliminar historial.
            13-Salir
            """;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        HistorialConversiones.cargarHistorial();

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            procesarOpcion(opcion);
        } while (opcion != 13);

        HistorialConversiones.guardarHistorial();

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println(PURPLE + MENU + RESET);
        System.out.println("Elija las Monedas que desea Convertir del Menú: ");
    }

    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                convertirMoneda("USD", "EUR");
                break;
            case 2:
                convertirMoneda("EUR", "USD");
                break;
            case 3:
                convertirMoneda("USD", "ARS");
                break;
            case 4:
                convertirMoneda("ARS", "USD");
                break;
            case 5:
                convertirMoneda("USD", "BOB");
                break;
            case 6:
                convertirMoneda("BOB", "USD");
                break;
            case 7:
                convertirMoneda("USD", "COP");
                break;
            case 8:
                convertirMoneda("COP", "USD");
                break;
            case 9:
                convertirMoneda("USD", "DOP");
                break;
            case 10:
                convertirMoneda("DOP", "USD");
                break;
            case 11:
                HistorialConversiones.mostrarHistorial();
                break;
            case 12:
                HistorialConversiones.eliminarHistorial();
                System.out.println("**********************************************************");
                System.out.println(" ");
                System.out.println(YELLOW + "¡Historial eliminado exitosamente!" + RESET);
                System.out.println(" ");
                System.out.println("***********************************************************");
                break;
            case 13:
                System.out.println("**********************************************************");
                System.out.println(" ");
                System.out.println(YELLOW + "GRACIAS POR USAR NUESTRO CONVERSOR DE MONEDAS..." + RESET);
                System.out.println(" ");
                System.out.println("***********************************************************");
                break;
            default:
                System.out.println(RED + "Opción no válida. Por favor, elija una opción del menú." + RESET);
        }
    }

    private static void convertirMoneda(String baseCode, String targetCode) {
        System.out.println("Ingrese el monto total a convertir: ");
        double amount = scanner.nextDouble();
        Conversion conversion = new Conversion(baseCode, targetCode, amount);
        try {
            double result = conversion.convert();
            String conversionInfo = amount + " " + baseCode + " => " + result + " " + targetCode;
            HistorialConversiones.agregarConversion(conversionInfo);
            RegistroMarcaTiempo.agregarRegistro(conversionInfo);
            System.out.println("********************************************************************");
            System.out.println(GREEN + amount + " " + baseCode + " son " + result + " " + targetCode + RESET);
            System.out.println("********************************************************************");
        } catch (Exception e) {
            System.out.println(RED + "Error al realizar la conversión: " + e.getMessage() + RESET);
        }
    }
}
