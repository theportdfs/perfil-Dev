package logica;


public class OperacionesMatematicas {


    public double sumar(double a, double b) {
        return a + b;
    }


    public double restar(double a, double b) {
        return a - b;
    }


    public double multiplicar(double a, double b) {
        return a * b;
    }


    public double dividir(double a, double b) {
        if (b == 0.0) {
            throw new ArithmeticException("Error: No es posible dividir entre cero.");
        }
        return a / b;
    }
}
