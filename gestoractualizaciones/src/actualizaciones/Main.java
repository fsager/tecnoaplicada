package actualizaciones;

public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				// File directorioAListar = new File
				// (System.getProperty("user.dir"));
				// GestorActualizaciones.listarDirectorio(directorioAListar,
				// "");
				JDialogActualizar dialog = new JDialogActualizar(
						new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}
}