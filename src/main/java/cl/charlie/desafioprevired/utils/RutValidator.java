/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.charlie.desafioprevired.utils;

/**
 * Clase para validar rut chileno
 *
 * @author cabra
 * @version 27122024
 */
public class RutValidator {
    public static boolean validarRut(String rut) {
       
        try {
            String[] parts = rut.split("-");
            String number = parts[0];
            char verifier = parts[1].toUpperCase().charAt(0);

            int rutNumber = Integer.parseInt(number);
            int m = 0, s = 1;
            for (; rutNumber != 0; rutNumber /= 10) {
                s = (s + rutNumber % 10 * (9 - m++ % 6)) % 11;
            }
            char expectedVerifier = (char) (s != 0 ? s + 47 : 75);

            return expectedVerifier == verifier;
        } catch (Exception e) {
            return false;
        }
    }
    
}
