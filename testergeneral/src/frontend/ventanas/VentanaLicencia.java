/*
 * ventanaLicencia.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.ventanas;

import javax.swing.JFileChooser;

import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class VentanaLicencia extends javax.swing.JDialog {

	/** Creates new form ventanaLicencia */
	public VentanaLicencia(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanelGenerarLicencia = new javax.swing.JPanel();
		jLabelSeleccionarGenerarArchivoLicencia = new javax.swing.JLabel();
		jTextFieldRutaGenerarLicencia = new javax.swing.JTextField();
		jButtonExaminarGenerarLicencia = new javax.swing.JButton();
		jButtonGenerarLicencia = new javax.swing.JButton();
		jPanelCargarLicencia = new javax.swing.JPanel();
		jLabelSeleccionarArchivoLicencia = new javax.swing.JLabel();
		jTextFieldRutaCargarLicencia = new javax.swing.JTextField();
		jButtonExaminarCargarLicencia = new javax.swing.JButton();
		jButtonCargarLicencia = new javax.swing.JButton();
		jPanelDatosLicenciaActual = new javax.swing.JPanel();
		jButtonCerrar = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jPanelGenerarLicencia.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Generar licencia"));

		jLabelSeleccionarGenerarArchivoLicencia
				.setText("Seleccionar archivo de licencia: ");

		jTextFieldRutaGenerarLicencia.setEditable(false);

		jButtonExaminarGenerarLicencia.setText("Examinar");
		jButtonExaminarGenerarLicencia
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonExaminarGenerarLicenciaActionPerformed(evt);
					}
				});

		jButtonGenerarLicencia.setText("Generar licencia");

		javax.swing.GroupLayout jPanelGenerarLicenciaLayout = new javax.swing.GroupLayout(
				jPanelGenerarLicencia);
		jPanelGenerarLicencia.setLayout(jPanelGenerarLicenciaLayout);
		jPanelGenerarLicenciaLayout
				.setHorizontalGroup(jPanelGenerarLicenciaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelGenerarLicenciaLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanelGenerarLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanelGenerarLicenciaLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabelSeleccionarGenerarArchivoLicencia)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jTextFieldRutaGenerarLicencia,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				335,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButtonExaminarGenerarLicencia))
														.addComponent(
																jButtonGenerarLicencia,
																javax.swing.GroupLayout.Alignment.TRAILING))));
		jPanelGenerarLicenciaLayout
				.setVerticalGroup(jPanelGenerarLicenciaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelGenerarLicenciaLayout
										.createSequentialGroup()
										.addGroup(
												jPanelGenerarLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jButtonExaminarGenerarLicencia)
														.addComponent(
																jTextFieldRutaGenerarLicencia,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jLabelSeleccionarGenerarArchivoLicencia))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE).addComponent(
												jButtonGenerarLicencia)));

		jPanelCargarLicencia.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Cargar licencia"));

		jLabelSeleccionarArchivoLicencia
				.setText("Seleccionar archivo de licencia: ");

		jTextFieldRutaCargarLicencia.setEditable(false);

		jButtonExaminarCargarLicencia.setText("Examinar");
		jButtonExaminarCargarLicencia
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonExaminarCargarLicenciaActionPerformed(evt);
					}
				});

		jButtonCargarLicencia.setText("Cargar licencia");

		javax.swing.GroupLayout jPanelCargarLicenciaLayout = new javax.swing.GroupLayout(
				jPanelCargarLicencia);
		jPanelCargarLicencia.setLayout(jPanelCargarLicenciaLayout);
		jPanelCargarLicenciaLayout
				.setHorizontalGroup(jPanelCargarLicenciaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelCargarLicenciaLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanelCargarLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanelCargarLicenciaLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabelSeleccionarArchivoLicencia)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jTextFieldRutaCargarLicencia,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				335,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButtonExaminarCargarLicencia))
														.addComponent(
																jButtonCargarLicencia,
																javax.swing.GroupLayout.Alignment.TRAILING))));
		jPanelCargarLicenciaLayout
				.setVerticalGroup(jPanelCargarLicenciaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelCargarLicenciaLayout
										.createSequentialGroup()
										.addGroup(
												jPanelCargarLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jButtonExaminarCargarLicencia)
														.addComponent(
																jTextFieldRutaCargarLicencia,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jLabelSeleccionarArchivoLicencia))
										.addGap(18, 18, 18)
										.addComponent(jButtonCargarLicencia)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jPanelDatosLicenciaActual.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Datos de la licencia actual"));

		javax.swing.GroupLayout jPanelDatosLicenciaActualLayout = new javax.swing.GroupLayout(
				jPanelDatosLicenciaActual);
		jPanelDatosLicenciaActual.setLayout(jPanelDatosLicenciaActualLayout);
		jPanelDatosLicenciaActualLayout
				.setHorizontalGroup(jPanelDatosLicenciaActualLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 607, Short.MAX_VALUE));
		jPanelDatosLicenciaActualLayout
				.setVerticalGroup(jPanelDatosLicenciaActualLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 133, Short.MAX_VALUE));

		jButtonCerrar.setText("Cerrar");
		jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonCerrarActionPerformed(evt);
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
																jPanelDatosLicenciaActual,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanelCargarLicencia,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanelGenerarLicencia,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addContainerGap(
																				554,
																				Short.MAX_VALUE)
																		.addComponent(
																				jButtonCerrar)))
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addComponent(
												jPanelGenerarLicencia,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanelCargarLicencia,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanelDatosLicenciaActual,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18).addComponent(
												jButtonCerrar)
										.addContainerGap()));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	private void jButtonExaminarCargarLicenciaActionPerformed(
			java.awt.event.ActionEvent evt) {
		final VentanaExaminar ventanaExaminar = new VentanaExaminar(
				JFileChooser.DIRECTORIES_ONLY,JFileChooser.OPEN_DIALOG);
		ventanaExaminar.pack();
		Util.agregarIframe(ventanaExaminar);
		ventanaExaminar.doModal(this.getRootPane());
		ventanaExaminar.setVisible(true);

		if (ventanaExaminar.getRutaSeleccionada() != null) {
			jTextFieldRutaCargarLicencia.setText(ventanaExaminar
					.getRutaSeleccionada());
		}
	}

	private void jButtonExaminarGenerarLicenciaActionPerformed(
			java.awt.event.ActionEvent evt) {
		final VentanaExaminar ventanaExaminar = new VentanaExaminar(
				JFileChooser.DIRECTORIES_ONLY,JFileChooser.OPEN_DIALOG);
		ventanaExaminar.pack();
		Util.agregarIframe(ventanaExaminar);
		ventanaExaminar.doModal(this.getRootPane());
		ventanaExaminar.setVisible(true);

		if (ventanaExaminar.getRutaSeleccionada() != null) {
			jTextFieldRutaGenerarLicencia.setText(ventanaExaminar
					.getRutaSeleccionada());
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				VentanaLicencia dialog = new VentanaLicencia(
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

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButtonCargarLicencia;
	private javax.swing.JButton jButtonCerrar;
	private javax.swing.JButton jButtonExaminarCargarLicencia;
	private javax.swing.JButton jButtonExaminarGenerarLicencia;
	private javax.swing.JButton jButtonGenerarLicencia;
	private javax.swing.JLabel jLabelSeleccionarArchivoLicencia;
	private javax.swing.JLabel jLabelSeleccionarGenerarArchivoLicencia;
	private javax.swing.JPanel jPanelCargarLicencia;
	private javax.swing.JPanel jPanelDatosLicenciaActual;
	private javax.swing.JPanel jPanelGenerarLicencia;
	private javax.swing.JTextField jTextFieldRutaCargarLicencia;
	private javax.swing.JTextField jTextFieldRutaGenerarLicencia;
	// End of variables declaration//GEN-END:variables

}