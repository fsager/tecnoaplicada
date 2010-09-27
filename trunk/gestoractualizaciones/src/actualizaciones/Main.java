package actualizaciones;

import javax.crypto.spec.SecretKeySpec;

import seguridad.Encriptadora;

public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				/**SecretKeySpec clavePrivada = new SecretKeySpec(new String(
				"czbmrndoritlekaz").getBytes(), "AES");
				Encriptadora encriptador = new Encriptadora("AES", clavePrivada);
				System.out.println(encriptador.encriptar("chipio@jttecnologiaaplicada.com"));
				*/
				
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