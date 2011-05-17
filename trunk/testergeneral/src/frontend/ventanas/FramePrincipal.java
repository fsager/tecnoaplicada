/*
 * FramePrincipal.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.ventanas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.SkinInfo;

import tecnologia.aplicada.licence.LicenceManager;
import testerGeneral.actualizaciones.GestorActualizaciones;
import testerGeneral.domain.Constantes;
import actualizaciones.GestorActualizacionesUtil;
import frontend.components.JOptionPaneTesterGral;
import frontend.paneles.PanelMenu;
import frontend.paneles.examenes.PanelContenido;
import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class FramePrincipal extends JInternalFrameTesterGral {

	/** Creates new form FramePrincipal */
	public FramePrincipal(PanelMenu panelMenu) throws Exception{

		super(Constantes.VTN_TITLE_FRM_PRINCIPAL, false, false, false, true);

		Util.framePrincipal = this;
		this.panelMenu = panelMenu;
		initComponents();
		jButtonActualizarSistema.setVisible(GestorActualizaciones
				.getSeEncontroActualizacion());

		getSkins();

		panelMenu.setPanelContenido(panelContenido);
		Util.panelContenido = this.panelContenido;

		setBackgroundMenu();

		panelMenu.cargarPrimeraOpcion();

		this.pack();
		
		/*if(LicenceManager.isLicencedProduct())
		{
			if(LicenceManager.hayQueActualizarLicencia())
				LicenceManager.actualizarLicencia((String)null,null);			
		}
		else
		{
			//Si es la primera vez que ingresa muestra el panel de licencia. Periodo de prueba o Licenciado
			//Si está en periodo de prueba muestro los dias restantes del periodo de prueba
			LicenceManager.showLicencePanel();
		}*/
	}

	public boolean hayActualizaciones() {
		return GestorActualizaciones.getSeEncontroActualizacion();
	}

	public void getSkins() {
		Map map = SubstanceLookAndFeel.getAllSkins();
		Set set = map.entrySet();
		Iterator it = set.iterator();

		while (it.hasNext()) {
			Entry entry = (Entry) it.next();
			SkinInfo skinInfo = (SkinInfo) entry.getValue();

		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		panelContenido = new PanelContenido();
		//panelMenuPrincipal = new frontend.paneles.PanelMenuPrincipal();
		jButtonActualizarSistema = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		setIconifiable(true);
		addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
			public void internalFrameActivated(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameClosed(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameClosing(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameDeactivated(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameDeiconified(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameIconified(
					javax.swing.event.InternalFrameEvent evt) {
				formInternalFrameIconified(evt);
			}

			public void internalFrameOpened(
					javax.swing.event.InternalFrameEvent evt) {
			}
		});

		panelContenido.setMaximumSize(null);
		panelContenido.setLayout(new java.awt.GridLayout(1, 0));

		jButtonActualizarSistema.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/images/warning.gif"))); // NOI18N
		jButtonActualizarSistema
				.setText("Existe una nueva versi\u00f3n del sistema. Presione aqu\u00ed para instalarla (se  reiniciar\u00e1 el programa).");
		jButtonActualizarSistema.setBorder(null);
		jButtonActualizarSistema.setBorderPainted(false);
		jButtonActualizarSistema.setContentAreaFilled(false);
		jButtonActualizarSistema
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonActualizarSistemaActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																panelMenu,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																800,//1250 800
																Short.MAX_VALUE)
														.addComponent(
																panelContenido,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																800,//1250 800
																Short.MAX_VALUE))
										.addGap(0, 0, 0)).addGroup(
								layout.createSequentialGroup().addComponent(
										jButtonActualizarSistema)
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addComponent(
												panelMenu,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												108, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												panelContenido,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												12, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jButtonActualizarSistema,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												16,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButtonActualizarSistemaActionPerformed(
			java.awt.event.ActionEvent evt) {
		File directorioActual = new File(System.getProperty("user.dir"));

		try {
			GestorActualizacionesUtil.generarArchivoDeOpcionesDeActualizacion();

			File gestorDeActualizaciones = new File("GestorActualizaciones.jar");
			if (gestorDeActualizaciones.exists()) {
				Runtime.getRuntime()
						.exec("java -jar GestorActualizaciones.jar");
				System.exit(0);
			} else {
				/*
				 * El archivo del gestor de actualizaciones no existe.Lo bajamos
				 * desde el servidor ftp para comenzar la actualización.
				 */
				int retornoMensajeError = JOptionPaneTesterGral
						.showInternal(
								"Un archivo necesario para la actualización no fue encontrado. Se intentará descargarlo desde el servidor antes de continuar con la actualización del programa.",
								"Ha ocurrido un error...",
								JOptionPane.INFORMATION_MESSAGE);
				if (retornoMensajeError == JOptionPane.YES_OPTION) {
					try {

						GestorActualizacionesUtil.obtenerArchivoDesdeServidorFTP(
								"GestorActualizaciones.jar", null, directorioActual);
						File archivoGestorTempDescargado = new File(
								"GestorActualizaciones.jar.ftptemp");

						archivoGestorTempDescargado.renameTo(new File(
								"GestorActualizaciones.jar"));

						if (gestorDeActualizaciones.exists()) {
							Runtime.getRuntime().exec(
									"java -jar GestorActualizaciones.jar");
							System.exit(0);
						}

					} catch (FileNotFoundException exc) {
						JOptionPaneTesterGral.showInternalMessageDialog(
								"Ha ocurrido un error al actualizar.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	private void formInternalFrameIconified(
			javax.swing.event.InternalFrameEvent evt) {
		Util.minimizar(this);
	}

	public void setBackgroundMenu() {
		panelMenu.setBackground(panelContenido.getBackground()
				.darker().darker().darker().darker());
		panelMenu.getPanelSubMenu().setBackground(
				panelContenido.getBackground().darker().darker());
	}

	public javax.swing.JButton getJButtonActualizarSistema() {
		return jButtonActualizarSistema;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButtonActualizarSistema;
	private javax.swing.JPanel panelContenido;
	private frontend.paneles.PanelMenuPrincipal panelMenuPrincipal;
	// End of variables declaration//GEN-END:variables
	private javax.swing.JToggleButton btnBuscarPersonas;
	private javax.swing.JToggleButton btnExamenVision;
	private javax.swing.JToggleButton btnUsuario;
	private PanelMenu panelMenu;
}