package logica;


public class ValidadorEntrada {


    public static void validarNoVacio(String texto, String nombreCampo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo \"" + nombreCampo + "\" no puede estar vacío.");
        }
    }

    
    public static double parsearNumero(String texto, String nombreCampo) {
        validarNoVacio(texto, nombreCampo);

        String textoLimpio = texto.trim().replace(',', '.');

        if (!textoLimpio.matches("^-?\\d+(\\.\\d+)?$")) {
            throw new IllegalArgumentException("El campo \"" + nombreCampo + "\" solo debe contener caracteres numéricos válidos. No se permiten letras ni símbolos especiales.");
        }

        try {
            return Double.parseDouble(textoLimpio);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El formato del número en \"" + nombreCampo + "\" no es válido.");
        }
    }
}
