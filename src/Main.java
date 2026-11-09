import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import vista.VentanaPrincipal;

/**
 * Punto de entrada principal para ejecutar la aplicación gráfica.
 */
public class Main {
    public static void main(String[] args) {
        // Establecer apariencia nativa del sistema operativo
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        // Iniciar la interfaz en el hilo de despacho de eventos de Swing (EDT)
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
