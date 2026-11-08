package logica;


public class ConversorMoneda {
    public static final double TASA_CAMBIO = 3200.0;

    /**
     */
    public double dolaresAPesos(double dolares) {
        if (dolares < 0) {
            throw new IllegalArgumentException("Error: La cantidad de dinero no puede ser negativa.");
        }
        return dolares * TASA_CAMBIO;
    }


    public double pesosADolares(double pesos) {
        if (pesos < 0) {
            throw new IllegalArgumentException("Error: La cantidad de dinero no puede ser negativa.");
        }
        return pesos / TASA_CAMBIO;
    }
}
