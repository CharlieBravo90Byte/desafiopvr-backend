/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.charlie.desafioprevired.utils;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase para generar el identificador aleatorio
 *
 * @author cabra
 * @version 27122024
 */
public class IdentificadorUnicoGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generarIdentificadorUnico() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        
        int longitudAleatoria = RANDOM.nextInt(41) + 10; 
        StringBuilder randomPart = new StringBuilder();
        
        for (int i = 0; i < longitudAleatoria; i++) {
            randomPart.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        
        return timestamp + "-" + randomPart.toString();
    }
}
