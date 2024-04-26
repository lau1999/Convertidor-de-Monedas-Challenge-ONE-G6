package com.alura.convertidordemonedas.registro;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RegistroMarcaTiempo {

    private static final List<String> registros = new ArrayList<>();

    public static void agregarRegistro(String conversionInfo) {
        LocalDateTime timestamp = LocalDateTime.now();
        String registro = timestamp + ": " + conversionInfo;
        registros.add(registro);
    }

    public static void mostrarRegistros() {
        System.out.println("Registros con marca de tiempo:");
        for (String registro : registros) {
            System.out.println(registro);
        }
    }
}
