package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import logica.ConversorMoneda;
import logica.ConversorTemperatura;
import logica.OperacionesMatematicas;
import logica.ValidadorEntrada;

public class VentanaPrincipal extends JFrame implements ActionListener {

    // Instancias de la lógica de negocio
    private final OperacionesMatematicas operaciones;
    private final ConversorTemperatura conversorTemperatura;
    private final ConversorMoneda conversorMoneda;

    // --- Componentes Sección 1: Operaciones Matemáticas ---
    private JTextField txtNum1;
    private JTextField txtNum2;
    private JTextField txtResultadoMat;
    private JButton btnSumar;
    private JButton btnRestar;
    private JButton btnMultiplicar;
    private JButton btnDividir;

    // --- Componentes Sección 2: Conversión de Temperatura ---
    private JTextField txtTemp;
    private JTextField txtResultadoTemp;
    private JButton btnCtoF;
    private JButton btnFtoC;

    // --- Componentes Sección 3: Conversión de Moneda ---
    private JTextField txtMoneda;
    private JTextField txtResultadoMoneda;
    private JButton btnUsdToCop;
    private JButton btnCopToUsd;

    // --- Botón de Limpieza General ---
    private JButton btnLimpiar;

    public VentanaPrincipal() {
        // Inicializar lógica
        this.operaciones = new OperacionesMatematicas();
        this.conversorTemperatura = new ConversorTemperatura();
        this.conversorMoneda = new ConversorMoneda();

        // Configuración básica del JFrame
        setTitle("Taller - Operaciones Matemáticas y Conversiones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 750);
        setMinimumSize(new Dimension(650, 700));
        setLocationRelativeTo(null); // Centrar en pantalla

        // Construir la interfaz
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Panel principal con margen y disposición vertical
        JPanel panelContenedor = new JPanel();
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));
        panelContenedor.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // 1. Encabezado institucional / título
        JPanel panelTitulo = new JPanel(new BorderLayout());
        JLabel lblTitulo = new JLabel("APLICACIÓN DE OPERACIONES Y CONVERSIONES", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(25, 55, 109));
        panelTitulo.add(lblTitulo, BorderLayout.CENTER);

        JLabel lblSubtitulo = new JLabel("Construcción de Software I - Tasa Fija USD: $3.200 COP", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setForeground(Color.DARK_GRAY);
        panelTitulo.add(lblSubtitulo, BorderLayout.SOUTH);
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // 2. Construcción de paneles por sección
        JPanel panelMatematicas = crearPanelMatematicas();
        JPanel panelTemperatura = crearPanelTemperatura();
        JPanel panelMoneda = crearPanelMoneda();

        // 3. Panel de acciones globales (Botón Limpiar)
        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnLimpiar = new JButton("Limpiar Todos los Campos");
        btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLimpiar.setBackground(new Color(220, 53, 69));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLimpiar.addActionListener(this);
        panelAcciones.add(btnLimpiar);

        // Ensamblar todo en el contenedor
        panelContenedor.add(panelTitulo);
        panelContenedor.add(panelMatematicas);
        panelContenedor.add(panelTemperatura);
        panelContenedor.add(panelMoneda);
        panelContenedor.add(panelAcciones);

        // Asignar al JFrame
        getContentPane().add(panelContenedor);
    }

    /**
     * Sección 1: Operaciones Matemáticas (Suma, Resta, Multiplicación, División)
     */
    private JPanel crearPanelMatematicas() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(60, 100, 160), 1),
                " 1. Operaciones Matemáticas (Ingrese dos números) ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13),
                new Color(30, 60, 120)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: Etiquetas
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.5;
        panel.add(new JLabel("Primer número:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.5;
        panel.add(new JLabel("Segundo número:"), gbc);

        // Fila 1: Campos de texto
        txtNum1 = new JTextField(10);
        txtNum1.setToolTipText("Ingrese el primer número");
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(txtNum1, gbc);

        txtNum2 = new JTextField(10);
        txtNum2.setToolTipText("Ingrese el segundo número");
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtNum2, gbc);

        // Fila 2: Botones de Operación
        JPanel panelBotones = new JPanel(new GridLayout(1, 4, 10, 0));
        btnSumar = crearBotonOperacion("Sumar");
        btnRestar = crearBotonOperacion("Restar");
        btnMultiplicar = crearBotonOperacion("Multiplicar");
        btnDividir = crearBotonOperacion("Dividir");

        panelBotones.add(btnSumar);
        panelBotones.add(btnRestar);
        panelBotones.add(btnMultiplicar);
        panelBotones.add(btnDividir);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        // Fila 3: Resultado
        JPanel panelResultado = new JPanel(new BorderLayout(8, 0));
        JLabel lblRes = new JLabel("Resultado Matemático:");
        lblRes.setFont(new Font("Segoe UI", Font.BOLD, 12));
        txtResultadoMat = new JTextField();
        txtResultadoMat.setEditable(false);
        txtResultadoMat.setFont(new Font("Segoe UI", Font.BOLD, 13));
        txtResultadoMat.setBackground(new Color(245, 245, 245));
        txtResultadoMat.setForeground(new Color(0, 102, 0));

        panelResultado.add(lblRes, BorderLayout.WEST);
        panelResultado.add(txtResultadoMat, BorderLayout.CENTER);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(panelResultado, gbc);

        return panel;
    }

    /**
     * Sección 2: Conversión de Temperatura (°C a °F y °F a °C)
     */
    private JPanel crearPanelTemperatura() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 120, 50), 1),
                " 2. Conversión de Temperatura (Ingrese el valor en grados) ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13),
                new Color(180, 90, 20)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: Etiqueta y campo de entrada
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        panel.add(new JLabel("Temperatura a convertir:"), gbc);

        txtTemp = new JTextField(12);
        txtTemp.setToolTipText("Ejemplo: 25 para Celsius o 77 para Fahrenheit");
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.7;
        panel.add(txtTemp, gbc);

        // Fila 1: Botones
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 12, 0));
        btnCtoF = crearBotonOperacion("°C a °F");
        btnFtoC = crearBotonOperacion("°F a °C");

        panelBotones.add(btnCtoF);
        panelBotones.add(btnFtoC);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        // Fila 2: Resultado
        JPanel panelResultado = new JPanel(new BorderLayout(8, 0));
        JLabel lblRes = new JLabel("Resultado Temperatura:");
        lblRes.setFont(new Font("Segoe UI", Font.BOLD, 12));
        txtResultadoTemp = new JTextField();
        txtResultadoTemp.setEditable(false);
        txtResultadoTemp.setFont(new Font("Segoe UI", Font.BOLD, 13));
        txtResultadoTemp.setBackground(new Color(245, 245, 245));
        txtResultadoTemp.setForeground(new Color(180, 80, 0));

        panelResultado.add(lblRes, BorderLayout.WEST);
        panelResultado.add(txtResultadoTemp, BorderLayout.CENTER);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(panelResultado, gbc);

        return panel;
    }

    /**
     * Sección 3: Conversión de Moneda (USD a COP y COP a USD)
     */
    private JPanel crearPanelMoneda() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(40, 140, 90), 1),
                " 3. Conversión de Moneda (Tasa fija: 1 USD = $3.200 COP) ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13),
                new Color(20, 110, 60)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: Etiqueta y campo de entrada
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        panel.add(new JLabel("Monto a convertir:"), gbc);

        txtMoneda = new JTextField(12);
        txtMoneda.setToolTipText("Ejemplo: 100 si va a convertir de USD a COP o 320000 si es de COP a USD");
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.7;
        panel.add(txtMoneda, gbc);

        // Fila 1: Botones
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 12, 0));
        btnUsdToCop = crearBotonOperacion("USD a COP");
        btnCopToUsd = crearBotonOperacion("COP a USD");

        panelBotones.add(btnUsdToCop);
        panelBotones.add(btnCopToUsd);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        // Fila 2: Resultado
        JPanel panelResultado = new JPanel(new BorderLayout(8, 0));
        JLabel lblRes = new JLabel("Resultado Moneda:");
        lblRes.setFont(new Font("Segoe UI", Font.BOLD, 12));
        txtResultadoMoneda = new JTextField();
        txtResultadoMoneda.setEditable(false);
        txtResultadoMoneda.setFont(new Font("Segoe UI", Font.BOLD, 13));
        txtResultadoMoneda.setBackground(new Color(245, 245, 245));
        txtResultadoMoneda.setForeground(new Color(0, 102, 51));

        panelResultado.add(lblRes, BorderLayout.WEST);
        panelResultado.add(txtResultadoMoneda, BorderLayout.CENTER);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(panelResultado, gbc);

        return panel;
    }

    /**
     * Utilidad para crear botones con estilo coherente y registrar el listener.
     */
    private JButton crearBotonOperacion(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addActionListener(this);
        return boton;
    }

    /**
     * Manejador de eventos de ActionListener.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object origen = e.getSource();

        // 1. Operaciones Matemáticas
        if (origen == btnSumar) {
            procesarOperacionMatematica("sumar");
        } else if (origen == btnRestar) {
            procesarOperacionMatematica("restar");
        } else if (origen == btnMultiplicar) {
            procesarOperacionMatematica("multiplicar");
        } else if (origen == btnDividir) {
            procesarOperacionMatematica("dividir");

        // 2. Conversión de Temperatura
        } else if (origen == btnCtoF) {
            procesarConversionTemperatura("CtoF");
        } else if (origen == btnFtoC) {
            procesarConversionTemperatura("FtoC");

        // 3. Conversión de Moneda
        } else if (origen == btnUsdToCop) {
            procesarConversionMoneda("USDtoCOP");
        } else if (origen == btnCopToUsd) {
            procesarConversionMoneda("COPtoUSD");

        // 4. Limpiar campos
        } else if (origen == btnLimpiar) {
            limpiarCampos();
        }
    }

    /**
     * Procesa la suma, resta, multiplicación o división con sus respectivas validaciones.
     */
    private void procesarOperacionMatematica(String tipo) {
        try {
            // Validar campos vacíos y formato numérico usando ValidadorEntrada
            double num1 = ValidadorEntrada.parsearNumero(txtNum1.getText(), "Primer número");
            double num2 = ValidadorEntrada.parsearNumero(txtNum2.getText(), "Segundo número");

            double resultado = 0.0;
            String simbolo = "";

            switch (tipo) {
                case "sumar":
                    resultado = operaciones.sumar(num1, num2);
                    simbolo = "+";
                    break;
                case "restar":
                    resultado = operaciones.restar(num1, num2);
                    simbolo = "-";
                    break;
                case "multiplicar":
                    resultado = operaciones.multiplicar(num1, num2);
                    simbolo = "×";
                    break;
                case "dividir":
                    // Si num2 == 0, la clase OperacionesMatematicas lanza ArithmeticException
                    resultado = operaciones.dividir(num1, num2);
                    simbolo = "÷";
                    break;
            }

            // Mostrar el resultado formateado en el campo de texto
            txtResultadoMat.setText(formatearNumero(num1) + " " + simbolo + " " + formatearNumero(num2) + " = " + formatearNumero(resultado));

        } catch (IllegalArgumentException ex) {
            // Error de campo vacío o entrada con letras/caracteres especiales
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error en Datos de Entrada", JOptionPane.WARNING_MESSAGE);
        } catch (ArithmeticException ex) {
            // Error de división por cero
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error Aritmético", JOptionPane.ERROR_MESSAGE);
            txtResultadoMat.setText("Error: División entre cero");
        }
    }

    /**
     * Procesa la conversión de temperatura entre Celsius y Fahrenheit.
     */
    private void procesarConversionTemperatura(String tipo) {
        try {
            double temp = ValidadorEntrada.parsearNumero(txtTemp.getText(), "Temperatura");
            double resultado;

            if ("CtoF".equals(tipo)) {
                resultado = conversorTemperatura.celsiusAFahrenheit(temp);
                txtResultadoTemp.setText(formatearNumero(temp) + " °C = " + formatearNumero(resultado) + " °F");
            } else {
                resultado = conversorTemperatura.fahrenheitACelsius(temp);
                txtResultadoTemp.setText(formatearNumero(temp) + " °F = " + formatearNumero(resultado) + " °C");
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error en Temperatura", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Procesa la conversión de moneda (USD a COP y COP a USD).
     */
    private void procesarConversionMoneda(String tipo) {
        try {
            double monto = ValidadorEntrada.parsearNumero(txtMoneda.getText(), "Monto a convertir");
            double resultado;

            if ("USDtoCOP".equals(tipo)) {
                resultado = conversorMoneda.dolaresAPesos(monto);
                txtResultadoMoneda.setText(formatearMoneda(monto, "USD") + " = " + formatearMoneda(resultado, "COP"));
            } else {
                resultado = conversorMoneda.pesosADolares(monto);
                txtResultadoMoneda.setText(formatearMoneda(monto, "COP") + " = " + formatearMoneda(resultado, "USD"));
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error en Moneda", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Limpia todas las cajas de texto de la aplicación.
     */
    private void limpiarCampos() {
        txtNum1.setText("");
        txtNum2.setText("");
        txtResultadoMat.setText("");

        txtTemp.setText("");
        txtResultadoTemp.setText("");

        txtMoneda.setText("");
        txtResultadoMoneda.setText("");

        txtNum1.requestFocus();
    }

    /**
     * Formatea un número double para que no muestre ceros decimales innecesarios (ej. 5 en lugar de 5.0).
     */
    private String formatearNumero(double valor) {
        if (valor == (long) valor) {
            return String.valueOf((long) valor);
        } else {
            return String.format(Locale.US, "%.2f", valor);
        }
    }

    /**
     * Formatea un monto de dinero con separadores de miles adecuados.
     */
    private String formatearMoneda(double valor, String codigoMoneda) {
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("es", "CO"));
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(0);
        return "$" + nf.format(valor) + " " + codigoMoneda;
    }
}
