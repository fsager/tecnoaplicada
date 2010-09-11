/*
 * FrameContenedor.java
 *
 * Created on __DATE__, __TIME__
 */

package autoimpresor.frontend.ventanas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.SplashScreen;

import testerGeneral.domain.Constantes;
import testerGeneral.domain.Propiedad;
import testerGeneral.service.PropiedadDefinition;
import autoimpresor.business.ContextManager;
import autoimpresor.domain.Usuario;
import autoimpresor.frontend.paneles.PanelMenuPrincipal;
import frontend.utils.Util;

public class FrameContenedor extends javax.swing.JFrame {
	private PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager
			.getBizObject("propiedadService");

	public FrameContenedor() {

		try {

			Util.setIcon(this, Constantes.IMG_ICON_AUTOIMPRESOR);

			initComponents();
			getSplashScreen();

			setSizeFullScreen();

			Propiedad prop = ContextManager.getPropertyObj("SISTEMA.IMAGEN.PRIMARIA");
			
			if (prop.getPropValor()!=null && !prop.getPropValor().isEmpty()) {
				this.getContentPane().setBackground(new Color(Integer.valueOf(prop.getPropValor())));
			} 	
			
			Util.dp = dp;
			Util.frameContenedor = this;
			Usuario usr=new Usuario();
			Util.abrirInicioSesion(PanelMenuPrincipal.class,usr);

			setVisible(true);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setFullscreen() {
		GraphicsDevice grafica = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if (grafica.isFullScreenSupported())
			grafica.setFullScreenWindow(this);
	}

	public void setSizeFullScreen() {
		GraphicsDevice grafica = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		Rectangle bounds = grafica.getDefaultConfiguration().getBounds();
		this.setBounds(0, 0, bounds.width, bounds.height);

	}

	public void getSplashScreen() {
		SplashScreen splash = SplashScreen.getSplashScreen();
		Graphics2D g = splash.createGraphics();
		splash.close();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		dp = new javax.swing.JDesktopPane();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}

			public void windowDeiconified(java.awt.event.WindowEvent evt) {
				formWindowDeiconified(evt);
			}

			public void windowIconified(java.awt.event.WindowEvent evt) {
				formWindowIconified(evt);
			}
		});

		dp.setBackground(new java.awt.Color(51, 51, 51));
		dp.setOpaque(false);
		dp.setPreferredSize(null);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 524,
				Short.MAX_VALUE).addGroup(
				layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						layout.createSequentialGroup().addGap(0, 0, 0)
								.addComponent(dp,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										0, Short.MAX_VALUE).addGap(0, 0, 0))));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 313,
				Short.MAX_VALUE).addGroup(
				layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						layout.createSequentialGroup().addGap(0, 0, 0)
								.addComponent(dp,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										0, Short.MAX_VALUE).addGap(0, 0, 0))));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void formWindowClosing(java.awt.event.WindowEvent evt) {
		System.exit(0);
	}

	private void formWindowIconified(java.awt.event.WindowEvent evt) {

	}

	private void formWindowDeiconified(java.awt.event.WindowEvent evt) {
		setSizeFullScreen();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FrameContenedor().setVisible(true);

			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JDesktopPane dp;
	// End of variables declaration//GEN-END:variables
	private javax.swing.JLabel lbFondo = new javax.swing.JLabel();

}