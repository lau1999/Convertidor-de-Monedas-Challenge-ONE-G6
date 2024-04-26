package com.alura.convertidordemonedas.historial;

import com.google.gson.Gson;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistorialConversiones {

    private static final String HISTORIAL_JSON_FILE = "src/com.alura.convertidordemonedas/informacion/historial.json";
    private static final Gson gson = new Gson();
    private static final List<String> historial = new ArrayList<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void cargarHistorial() {
        try (Reader reader = new FileReader(HISTORIAL_JSON_FILE)) {
            String[] historialCargado = gson.fromJson(reader, String[].class);
            if (historialCargado != null) {
                for (String conversion : historialCargado) {
                    historial.add(conversion);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de historial no encontrado. Se creará uno nuevo.");
        } catch (IOException e) {
            System.out.println("Error al cargar el historial: " + e.getMessage());
        }
    }

    public static void guardarHistorial() {
        try (FileWriter writer = new FileWriter(HISTORIAL_JSON_FILE)) {
            gson.toJson(historial.toArray(new String[0]), writer);
        } catch (IOException e) {
            System.out.println("Error al guardar el historial: " + e.getMessage());
        }
    }

    public static void agregarConversion(String conversion) {
        String registro = obtenerMarcaTiempo() + ": " + conversion;
        historial.add(registro);
        guardarHistorial(); // Guarda la información inmediatamente después de agregar una conversión
    }

    public static void mostrarHistorial() {
        System.out.println("Contenido del historial:");
        for (String conversion : historial) {
            System.out.println(conversion);
        }
    }

    public static void eliminarHistorial() {
        historial.clear();
        guardarHistorial(); // Guarda el historial vacío
    }

    private static String obtenerMarcaTiempo() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }
}
