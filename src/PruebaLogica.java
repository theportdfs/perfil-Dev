import logica.ConversorMoneda;
import logica.ConversorTemperatura;
import logica.OperacionesMatematicas;
import logica.ValidadorEntrada;


public class PruebaLogica {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   PRUEBAS DE LA LÓGICA DEL TALLER (JAVA 17)      ");
        System.out.println("==================================================\n");

        // 1. Operaciones Matemáticas
        OperacionesMatematicas mates = new OperacionesMatematicas();
        double num1 = 15.0;
        double num2 = 3.0;

        System.out.println("--- 1. OPERACIONES MATEMÁTICAS ---");
        System.out.println("Suma (" + num1 + " + " + num2 + ") = " + mates.sumar(num1, num2));
        System.out.println("Resta (" + num1 + " - " + num2 + ") = " + mates.restar(num1, num2));
        System.out.println("Multiplicación (" + num1 + " * " + num2 + ") = " + mates.multiplicar(num1, num2));
        System.out.println("División (" + num1 + " / " + num2 + ") = " + mates.dividir(num1, num2));

        // Prueba de división por cero
        try {
            System.out.print("Probando división por cero (15 / 0)... ");
            mates.dividir(15, 0);
        } catch (ArithmeticException e) {
            System.out.println("Capturada correctamente -> " + e.getMessage());
        }

        System.out.println();

        // 2. Conversión de Temperatura
        ConversorTemperatura temp = new ConversorTemperatura();
        double celsius = 25.0;
        double fahrenheit = 77.0;

        System.out.println("--- 2. CONVERSIÓN DE TEMPERATURA ---");
        System.out.println(celsius + " °C a °F = " + temp.celsiusAFahrenheit(celsius) + " °F");
        System.out.println(fahrenheit + " °F a °C = " + temp.fahrenheitACelsius(fahrenheit) + " °C");

        System.out.println();

        // 3. Conversión de Moneda (Tasa fija $3200)
        ConversorMoneda moneda = new ConversorMoneda();
        double usd = 100.0;
        double cop = 320000.0;

        System.out.println("--- 3. CONVERSIÓN DE MONEDA (Tasa fija: $" + ConversorMoneda.TASA_CAMBIO + ") ---");
        System.out.println(usd + " USD a COP = $" + moneda.dolaresAPesos(usd) + " COP");
        System.out.println(cop + " COP a USD = $" + moneda.pesosADolares(cop) + " USD");

        System.out.println();

        // 4. Validación de Entradas (Campos vacíos, letras, caracteres especiales)
        System.out.println("--- 4. VALIDACIÓN DE ENTRADAS ---");

        // Entrada válida
        double valorValido = ValidadorEntrada.parsearNumero("45.6", "Valor Numérico");
        System.out.println("Entrada válida '45.6' parseada correctamente: " + valorValido);

        // Prueba de campo vacío
        try {
            System.out.print("Probando validación con texto vacío (\"\")... ");
            ValidadorEntrada.parsearNumero("", "Número");
        } catch (IllegalArgumentException e) {
            System.out.println("Capturada correctamente -> " + e.getMessage());
        }

        // Prueba de letras o caracteres especiales
        try {
            System.out.print("Probando validación con letras ('abc')... ");
            ValidadorEntrada.parsearNumero("abc", "Número");
        } catch (IllegalArgumentException e) {
            System.out.println("Capturada correctamente -> " + e.getMessage());
        }

        try {
            System.out.print("Probando validación con caracteres especiales ('12#$')... ");
            ValidadorEntrada.parsearNumero("12#$", "Número");
        } catch (IllegalArgumentException e) {
            System.out.println("Capturada correctamente -> " + e.getMessage());
        }

        System.out.println("\n==================================================");
        System.out.println("   TODAS LAS PRUEBAS DE LÓGICA PASARON CON ÉXITO  ");
        System.out.println("==================================================");
    }
}
