/*
 * PanelLicencia.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.licence;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;

import tecnologia.aplicada.licence.LicenceManager;
import testerGeneral.business.ContextManager;
import testerGeneral.domain.Propiedad;
import frontend.buttons.ButtonGuardar;
import frontend.components.ExtensionFileFilter;
import frontend.utils.Util;
import frontend.ventanas.JInternalFrameTesterGral;
import frontend.ventanas.VentanaExaminar;

/**
 *
 * @author  __USER__
 */
public class PanelLicencia extends javax.swing.JPanel {
	private JInternalFrameTesterGral internalframe;
	private static SimpleDateFormat sdf = new SimpleDateFormat(
			Util.formatoFecha);

	/** Creates new form PanelLicencia */
	public PanelLicencia(JInternalFrameTesterGral internalframe,
			boolean prueba, boolean actualizarLicencia) {
		initComponents();
		this.internalframe = internalframe;
		if (!prueba) {
			jRadioPrueba.setVisible(false);
			jLabelLicenciaPrueba.setVisible(false);
		} else {
			jLabelLicencia.setVisible(false);
		}

		if (actualizarLicencia) {
			jRadioPrueba.setVisible(false);
			jLabelLicenciaPrueba.setVisible(false);
			jLabelLicencia.setVisible(false);
			btnGuardar.setVisible(false);
		}

		txtNroLicencia.setText(ContextManager.getProperty("LICENCE.NRO"));

	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		jLabel1 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		txtNroLicencia = new javax.swing.JTextField();
		jLabelLicenciaPrueba = new javax.swing.JLabel();
		jLabelLicencia = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		btnCodigoActivacion = new javax.swing.JButton();
		txtCodigoActivacion = new javax.swing.JTextField();
		btnImportarArchivoLic = new ButtonGuardar();
		jRadioLicencia = new javax.swing.JRadioButton();
		jRadioPrueba = new javax.swing.JRadioButton();
		btnGuardar = new ButtonGuardar();
		jLabelLicenciaPrueba1 = new javax.swing.JLabel();

		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12));
		jLabel1.setText("Nro de Licencia:");

		jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12));
		jButton1.setText("Actualizar datos de licencia");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		txtNroLicencia.setFont(new java.awt.Font("Segoe UI", 1, 12));

		jLabelLicenciaPrueba.setFont(new java.awt.Font("Segoe UI", 2, 12));
		jLabelLicenciaPrueba.setForeground(new java.awt.Color(255, 0, 0));
		jLabelLicenciaPrueba
				.setText("Para continuar debe cargar un n\u00famero de licencia o seleccionar el periodo de prueba.");

		jLabelLicencia.setFont(new java.awt.Font("Segoe UI", 2, 12));
		jLabelLicencia.setForeground(new java.awt.Color(255, 0, 0));
		jLabelLicencia
				.setText("Para continuar debe cargar un n\u00famero de licencia, el periodo de prueba se ha vencido.");

		jPanel1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("S\u00f3lo para activaci\u00f3n manual"));

		btnCodigoActivacion.setFont(new java.awt.Font("Segoe UI", 0, 10));
		btnCodigoActivacion.setText("Generar C\u00f3digo de Activaci\u00f3n");
		btnCodigoActivacion
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnCodigoActivacionActionPerformed(evt);
					}
				});

		txtCodigoActivacion.setEditable(false);

		btnImportarArchivoLic.setText("Importar Archivo de Licencia");
		btnImportarArchivoLic
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnImportarArchivoLicActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addComponent(
						btnCodigoActivacion).addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtCodigoActivacion,
								javax.swing.GroupLayout.DEFAULT_SIZE, 261,
								Short.MAX_VALUE).addContainerGap())
				.addComponent(btnImportarArchivoLic,
						javax.swing.GroupLayout.DEFAULT_SIZE, 443,
						Short.MAX_VALUE));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																btnCodigoActivacion)
														.addComponent(
																txtCodigoActivacion,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												44, Short.MAX_VALUE)
										.addComponent(
												btnImportarArchivoLic,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		((ButtonGuardar) btnGuardar).init();

		buttonGroup1.add(jRadioLicencia);
		jRadioLicencia.setSelected(true);
		jRadioLicencia.setText("Licencia");

		buttonGroup1.add(jRadioPrueba);
		jRadioPrueba.setText("Periodo de prueba");

		btnGuardar.setText("Guardar");
		btnGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGuardarActionPerformed(evt);
			}
		});

		jLabelLicenciaPrueba1.setFont(new java.awt.Font("Segoe UI", 2, 48));
		jLabelLicenciaPrueba1.setForeground(new java.awt.Color(255, 0, 0));
		jLabelLicenciaPrueba1.setText("                               ");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jRadioLicencia)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jRadioPrueba))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addGroup(
																								layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel1)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												txtNroLicencia,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												174,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jButton1,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE))
																						.addComponent(
																								jPanel1,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGap(
																				185,
																				185,
																				185)
																		.addComponent(
																				btnGuardar,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				80,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jLabelLicenciaPrueba,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabelLicencia))
										.addContainerGap(14, Short.MAX_VALUE))
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jLabelLicenciaPrueba1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												453, Short.MAX_VALUE).addGap(
												14, 14, 14)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jRadioLicencia)
														.addComponent(
																jRadioPrueba))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																txtNroLicencia,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jButton1))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jLabelLicenciaPrueba)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jLabelLicencia)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jLabelLicenciaPrueba1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												btnGuardar,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		((ButtonGuardar) btnGuardar).init();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		LicenceManager.actualizarLicencia(txtNroLicencia.getText(), null);
	}

	private void btnImportarArchivoLicActionPerformed(
			java.awt.event.ActionEvent evt) {
		String extencion[] = { "jtt" };
		ExtensionFileFilter fileFilter = new ExtensionFileFilter("Licencias",
				extencion);

		final VentanaExaminar ventanaExaminar = new VentanaExaminar(
				JFileChooser.FILES_ONLY, JFileChooser.OPEN_DIALOG, fileFilter);
		ventanaExaminar.pack();
		Util.agregarIframe(ventanaExaminar);
		ventanaExaminar.doModal(this.getRootPane());
		ventanaExaminar.setVisible(true);

		if (ventanaExaminar.getRutaSeleccionada() != null) {
			byte[] bytes = testerGeneral.persistence.impl.Util
					.getBytesFromFile(ventanaExaminar.getRutaSeleccionada());
			LicenceManager.actualizarLicencia(bytes, txtNroLicencia.getText());
		}
	}

	private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
		if (jRadioLicencia.isSelected()) {
			LicenceManager.actualizarLicencia(txtNroLicencia.getText(), null);

			return;
		} else if (jRadioPrueba.isSelected()) {
			Propiedad propiedad = new Propiedad();
			propiedad.setPropClave("LICENCED");
			propiedad.setPropValor("N");
			ContextManager.updatePropiedad(propiedad);

			propiedad = new Propiedad();
			propiedad.setPropClave("PERIODO.PRUEBA.FECHA.INICIO");
			propiedad.setPropValor(sdf.format(new Date()));
			ContextManager.updatePropiedad(propiedad);
		}

		if (internalframe != null)
			internalframe.close();
	}

	private void btnCodigoActivacionActionPerformed(
			java.awt.event.ActionEvent evt) {
		String codigoActivacion = LicenceManager.getCodigoActivacion();
		txtCodigoActivacion.setText(codigoActivacion);
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnCodigoActivacion;
	private javax.swing.JButton btnGuardar;
	private javax.swing.JButton btnImportarArchivoLic;
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabelLicencia;
	private javax.swing.JLabel jLabelLicenciaPrueba;
	private javax.swing.JLabel jLabelLicenciaPrueba1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JRadioButton jRadioLicencia;
	private javax.swing.JRadioButton jRadioPrueba;
	private javax.swing.JTextField txtCodigoActivacion;
	private javax.swing.JTextField txtNroLicencia;
	// End of variables declaration//GEN-END:variables

}