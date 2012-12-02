/*
 * PanelGestionDeMunicipio.java
 *
 * Created on __DATE__, __TIME__
 */

package autoimpresor.frontend.paneles;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import testerGeneral.domain.Constantes;
import testerGeneral.domain.Propiedad;
import testerGeneral.service.PropiedadDefinition;
import autoimpresor.business.ContextManager;
import autoimpresor.domain.ClaseLicencia;
import autoimpresor.domain.Licencia;
import autoimpresor.domain.Usuario;
import autoimpresor.frontend.tablemodels.TableModelClaseLicencia;
import autoimpresor.service.ClaseLicenciaDefinition;
import autoimpresor.service.LicenciaDefinition;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import frontend.buttons.ButtonCancelarMini;
import frontend.buttons.ButtonExaminar;
import frontend.components.JOptionPaneTesterGral;
import frontend.utils.Util;
import frontend.ventanas.JInternalFrameTesterGral;
import frontend.ventanas.VentanaSeleccionImagen;

/**
 * 
 * @author __USER__
 */
public class PanelGestionDeMunicipio extends javax.swing.JPanel {
	private PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager
			.getBizObject("propiedadService");
	private ClaseLicenciaDefinition claseLicenciaService = (ClaseLicenciaDefinition) ContextManager
			.getBizObject("claseLicenciaService");
	LicenciaDefinition licenciaService = (LicenciaDefinition) ContextManager
			.getBizObject("licenciaService");
	Usuario usuarioLogueado = (Usuario) Util.usuarioCommon;

	/** Creates new form PanelGestionDeMunicipio */
	public PanelGestionDeMunicipio() {
		initComponents();
		checkHabilitarCaja.setEnabled(true);
		checkImprimirRecibo.setVisible(false);
		Util.mostrarError(lbSinResultados, "", true);
		buttonGroupCentroImpresorSiNo
				.add(jRadioButtonEsCentroImpresorDeLicencias);
		buttonGroupCentroImpresorSiNo
				.add(jRadioButtonNoEsCentroImpresorDeLicencias);
		cargarValoresDePropiedades();
		btnCancelarFoto.setText("Borrar");

		if (usuarioLogueado.getUsrId() == -1) {
			jRadioButtonEsCentroImpresorDeLicencias.setEnabled(true);
			jRadioButtonNoEsCentroImpresorDeLicencias.setEnabled(true);
			/*txtRangoDesde.setEnabled(true);
			txtRangoHasta.setEnabled(true);*/

		} else {
			jRadioButtonEsCentroImpresorDeLicencias.setEnabled(false);
			jRadioButtonNoEsCentroImpresorDeLicencias.setEnabled(false);
			/*txtRangoDesde.setEnabled(false);
			txtRangoHasta.setEnabled(false);*/
		}

		txtRangoDesde.setEnabled(true);
		txtRangoHasta.setEnabled(true);
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroupCentroImpresorSiNo = new javax.swing.ButtonGroup();
		jPanelBotones = new javax.swing.JPanel();
		jButtonGuardar = new javax.swing.JButton();
		jPanelDatosMunicipio = new javax.swing.JPanel();
		jLabelNombreMunicipio = new javax.swing.JLabel();
		jTextFieldNombreMunicipio = new javax.swing.JTextField();
		jLabelCodigoMunicipio = new javax.swing.JLabel();
		jTextFieldCodigoMunicipio = new javax.swing.JTextField();
		jPanelEsCentroImpresor = new javax.swing.JPanel();
		jRadioButtonEsCentroImpresorDeLicencias = new javax.swing.JRadioButton();
		jRadioButtonNoEsCentroImpresorDeLicencias = new javax.swing.JRadioButton();
		jPanelEsCentroImpresor1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		txtRangoDesde = new javax.swing.JTextField();
		txtRangoHasta = new javax.swing.JTextField();
		jPanel1 = new javax.swing.JPanel();
		checkFormatoLic = new javax.swing.JCheckBox();
		jPanelClasesDeLicencia = new javax.swing.JPanel();
		jScrollPaneClasesDeLicencia = new javax.swing.JScrollPane();
		jTableClasesDeLicencia = new javax.swing.JTable();
		jButtonNuevaClaseLicencia = new javax.swing.JButton();
		jButtonEditarClaseLicencia = new javax.swing.JButton();
		checkHabilitarCaja = new javax.swing.JCheckBox();
		jLabelCodigoMunicipio1 = new javax.swing.JLabel();
		jFormattedTextFieldEdadMinima = new javax.swing.JFormattedTextField();
		jButtonEliminarCL = new javax.swing.JButton();
		checkImprimirRecibo = new javax.swing.JCheckBox();
		btnExaminarFoto = new ButtonExaminar();
		jLabelLogoMunicipio = new javax.swing.JLabel();
		btnCancelarFoto = new ButtonCancelarMini();
		lbSinResultados = new javax.swing.JLabel();

		jButtonGuardar.setMnemonic('G');
		jButtonGuardar.setText("Guardar");
		jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonGuardarActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(
				jPanelBotones);
		jPanelBotones.setLayout(jPanelBotonesLayout);
		jPanelBotonesLayout.setHorizontalGroup(jPanelBotonesLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanelBotonesLayout.createSequentialGroup()
								.addContainerGap(18, Short.MAX_VALUE)
								.addComponent(jButtonGuardar)));
		jPanelBotonesLayout.setVerticalGroup(jPanelBotonesLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jButtonGuardar));

		jPanelDatosMunicipio.setBorder(javax.swing.BorderFactory
				.createTitledBorder(null, "Datos de Municipio",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION,
						new java.awt.Font("Segoe UI", 3, 12)));

		jLabelNombreMunicipio.setText("Municipio:");

		jTextFieldNombreMunicipio
				.setMaximumSize(new java.awt.Dimension(424, 22));

		jLabelCodigoMunicipio.setText("C\u00f3digo de municipio:");

		jTextFieldCodigoMunicipio
				.setMaximumSize(new java.awt.Dimension(164, 22));

		jPanelEsCentroImpresor
				.setBorder(javax.swing.BorderFactory
						.createTitledBorder("\u00bfEs el municipio un centro impresor de licencias?"));

		jRadioButtonEsCentroImpresorDeLicencias.setText("Si");
		jRadioButtonEsCentroImpresorDeLicencias
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioButtonEsCentroImpresorDeLicenciasActionPerformed(evt);
					}
				});

		jRadioButtonNoEsCentroImpresorDeLicencias.setText("No");

		javax.swing.GroupLayout jPanelEsCentroImpresorLayout = new javax.swing.GroupLayout(
				jPanelEsCentroImpresor);
		jPanelEsCentroImpresor.setLayout(jPanelEsCentroImpresorLayout);
		jPanelEsCentroImpresorLayout
				.setHorizontalGroup(jPanelEsCentroImpresorLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelEsCentroImpresorLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jRadioButtonEsCentroImpresorDeLicencias)
										.addGap(18, 18, 18)
										.addComponent(
												jRadioButtonNoEsCentroImpresorDeLicencias)
										.addContainerGap(166, Short.MAX_VALUE)));
		jPanelEsCentroImpresorLayout
				.setVerticalGroup(jPanelEsCentroImpresorLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelEsCentroImpresorLayout
										.createSequentialGroup()
										.addGroup(
												jPanelEsCentroImpresorLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jRadioButtonEsCentroImpresorDeLicencias)
														.addComponent(
																jRadioButtonNoEsCentroImpresorDeLicencias))
										.addContainerGap(10, Short.MAX_VALUE)));

		jPanelEsCentroImpresor1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Rango de licencias asignado"));

		jLabel1.setText("Desde:");

		jLabel2.setText("Hasta:");

		txtRangoDesde.setEnabled(false);

		txtRangoHasta.setEnabled(false);

		javax.swing.GroupLayout jPanelEsCentroImpresor1Layout = new javax.swing.GroupLayout(
				jPanelEsCentroImpresor1);
		jPanelEsCentroImpresor1.setLayout(jPanelEsCentroImpresor1Layout);
		jPanelEsCentroImpresor1Layout
				.setHorizontalGroup(jPanelEsCentroImpresor1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelEsCentroImpresor1Layout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												jPanelEsCentroImpresor1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(jLabel2)
														.addComponent(jLabel1))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jPanelEsCentroImpresor1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																txtRangoHasta)
														.addComponent(
																txtRangoDesde,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																121,
																Short.MAX_VALUE))));
		jPanelEsCentroImpresor1Layout
				.setVerticalGroup(jPanelEsCentroImpresor1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelEsCentroImpresor1Layout
										.createSequentialGroup()
										.addGroup(
												jPanelEsCentroImpresor1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																txtRangoDesde,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelEsCentroImpresor1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																txtRangoHasta,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jPanel1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Formato Licencia"));

		checkFormatoLic.setText("Con QR");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				checkFormatoLic, javax.swing.GroupLayout.DEFAULT_SIZE, 98,
				Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addComponent(
						checkFormatoLic).addContainerGap(10, Short.MAX_VALUE)));

		javax.swing.GroupLayout jPanelDatosMunicipioLayout = new javax.swing.GroupLayout(
				jPanelDatosMunicipio);
		jPanelDatosMunicipio.setLayout(jPanelDatosMunicipioLayout);
		jPanelDatosMunicipioLayout
				.setHorizontalGroup(jPanelDatosMunicipioLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelDatosMunicipioLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanelDatosMunicipioLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelCodigoMunicipio)
														.addComponent(
																jLabelNombreMunicipio))
										.addGap(12, 12, 12)
										.addGroup(
												jPanelDatosMunicipioLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jTextFieldCodigoMunicipio,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																164,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jTextFieldNombreMunicipio,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																474,
																Short.MAX_VALUE)))
						.addGroup(
								jPanelDatosMunicipioLayout
										.createSequentialGroup()
										.addComponent(
												jPanelEsCentroImpresor,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jPanelEsCentroImpresor1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		jPanelDatosMunicipioLayout
				.setVerticalGroup(jPanelDatosMunicipioLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanelDatosMunicipioLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanelDatosMunicipioLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelNombreMunicipio)
														.addComponent(
																jTextFieldNombreMunicipio,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelDatosMunicipioLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																jPanelDatosMunicipioLayout
																		.createSequentialGroup()
																		.addGroup(
																				jPanelDatosMunicipioLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabelCodigoMunicipio)
																						.addComponent(
																								jTextFieldCodigoMunicipio,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanelDatosMunicipioLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jPanel1,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jPanelEsCentroImpresor,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)))
														.addComponent(
																jPanelEsCentroImpresor1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap()));

		jPanelClasesDeLicencia.setBorder(javax.swing.BorderFactory
				.createTitledBorder(null, "Clases de licencia",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION,
						new java.awt.Font("Segoe UI", 3, 12)));

		jTableClasesDeLicencia
				.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][] {
								{ null, null, null, null, null, null },
								{ null, null, null, null, null, null },
								{ null, null, null, null, null, null },
								{ null, null, null, null, null, null },
								{ null, null, null, null, null, null },
								{ null, null, null, null, null, null } },
						new String[] { "Clase", "Descripción corta",
								"Descripción", "Vigencia predeterminada",
								"Edad mínima", "Edad máxima" }) {
					boolean[] canEdit = new boolean[] { false, false, false,
							false, false, false };

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});
		jTableClasesDeLicencia
				.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						jTableClasesDeLicenciaMouseClicked(evt);
					}
				});
		jScrollPaneClasesDeLicencia.setViewportView(jTableClasesDeLicencia);

		jButtonNuevaClaseLicencia.setText("Nueva clase");
		jButtonNuevaClaseLicencia
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonNuevaClaseLicenciaActionPerformed(evt);
					}
				});

		jButtonEditarClaseLicencia.setText("Editar clase");
		jButtonEditarClaseLicencia.setEnabled(false);
		jButtonEditarClaseLicencia
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonEditarClaseLicenciaActionPerformed(evt);
					}
				});

		checkHabilitarCaja.setText("Habilitar caja");
		checkHabilitarCaja
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						checkHabilitarCajaActionPerformed(evt);
					}
				});

		jLabelCodigoMunicipio1.setText("Importe de los duplicado:");

		jFormattedTextFieldEdadMinima
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0.00"))));
		jFormattedTextFieldEdadMinima.setMaximumSize(new java.awt.Dimension(86,
				22));

		jButtonEliminarCL.setText("Eliminar clase");
		jButtonEliminarCL.setEnabled(false);
		jButtonEliminarCL
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonEliminarCLActionPerformed(evt);
					}
				});

		checkImprimirRecibo
				.setText("Imprimir automaticamente recibo al generar licencia. ");
		checkImprimirRecibo
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						checkImprimirReciboActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout jPanelClasesDeLicenciaLayout = new javax.swing.GroupLayout(
				jPanelClasesDeLicencia);
		jPanelClasesDeLicencia.setLayout(jPanelClasesDeLicenciaLayout);
		jPanelClasesDeLicenciaLayout
				.setHorizontalGroup(jPanelClasesDeLicenciaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelClasesDeLicenciaLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanelClasesDeLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPaneClasesDeLicencia,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																854,
																Short.MAX_VALUE)
														.addGroup(
																jPanelClasesDeLicenciaLayout
																		.createSequentialGroup()
																		.addComponent(
																				jButtonNuevaClaseLicencia)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jButtonEditarClaseLicencia)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButtonEliminarCL)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				344,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabelCodigoMunicipio1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jFormattedTextFieldEdadMinima,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				60,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanelClasesDeLicenciaLayout
																		.createSequentialGroup()
																		.addComponent(
																				checkHabilitarCaja,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				113,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				checkImprimirRecibo,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				377,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		jPanelClasesDeLicenciaLayout
				.setVerticalGroup(jPanelClasesDeLicenciaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanelClasesDeLicenciaLayout
										.createSequentialGroup()
										.addGroup(
												jPanelClasesDeLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																checkHabilitarCaja)
														.addComponent(
																checkImprimirRecibo))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPaneClasesDeLicencia,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												199, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelClasesDeLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jButtonNuevaClaseLicencia)
														.addComponent(
																jButtonEditarClaseLicencia)
														.addComponent(
																jFormattedTextFieldEdadMinima,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jLabelCodigoMunicipio1)
														.addComponent(
																jButtonEliminarCL))));

		btnExaminarFoto.setIcon(new ImageIcon(getClass().getResource(
				"/images/agregar.png")));
		btnExaminarFoto.setToolTipText("Examinar");
		((ButtonExaminar) btnExaminarFoto).init();
		btnExaminarFoto.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExaminarFotoActionPerformed(evt);
			}
		});

		jLabelLogoMunicipio
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelLogoMunicipio
				.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
		jLabelLogoMunicipio.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Logo"));
		jLabelLogoMunicipio.setMaximumSize(new java.awt.Dimension(246, 176));
		jLabelLogoMunicipio.setMinimumSize(new java.awt.Dimension(246, 176));

		btnCancelarFoto.setIcon(new ImageIcon(getClass().getResource(
				"/images/agregar.png")));
		btnCancelarFoto.setToolTipText("Examinar");
		((ButtonCancelarMini) btnCancelarFoto).init();
		btnCancelarFoto.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarFotoActionPerformed(evt);
			}
		});

		lbSinResultados.setForeground(new java.awt.Color(204, 0, 0));
		lbSinResultados.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_ERROR)));
		lbSinResultados.setText(Constantes.ERROR_SIN_RESULTADOS);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jPanelClasesDeLicencia,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jPanelDatosMunicipio,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jLabelLogoMunicipio,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				185,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								btnExaminarFoto,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								70,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnCancelarFoto,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								70,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				lbSinResultados,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				518,
																				Short.MAX_VALUE)
																		.addGap(
																				279,
																				279,
																				279)
																		.addComponent(
																				jPanelBotones,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
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
																javax.swing.GroupLayout.Alignment.TRAILING,
																layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				btnCancelarFoto,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				25,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnExaminarFoto,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				25,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				false)
																		.addComponent(
																				jLabelLogoMunicipio,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jPanelDatosMunicipio,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				176,
																				Short.MAX_VALUE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanelClasesDeLicencia,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jPanelBotones,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lbSinResultados,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																24,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
	}// </editor-fold>
	//GEN-END:initComponents

	private void checkImprimirReciboActionPerformed(
			java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void checkHabilitarCajaActionPerformed(
			java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jButtonEliminarCLActionPerformed(java.awt.event.ActionEvent evt) {

		try {
			if (JOptionPaneTesterGral.showInternal(Constantes.MENSAJE_ELIMINAR,
					Constantes.MENSAJE_ELIMINADO_TIT,
					JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

				int indiceClaseLicenciaSeleccionada = jTableClasesDeLicencia
						.getSelectedRow();
				indiceClaseLicenciaSeleccionada = jTableClasesDeLicencia
						.convertRowIndexToModel(indiceClaseLicenciaSeleccionada);

				TableModelClaseLicencia tableModelClaseLicencia = (TableModelClaseLicencia) jTableClasesDeLicencia
						.getModel();
				ClaseLicencia claseLicenciaSeleccionada = tableModelClaseLicencia
						.getValueAt(indiceClaseLicenciaSeleccionada);

				Licencia exampleLic = new Licencia();
				exampleLic.setLicClase(claseLicenciaSeleccionada
						.getCllNombreClase());
				List licencias = licenciaService.getAll(exampleLic);

				if (licencias.size() <= 0) {

					claseLicenciaService.delete(claseLicenciaSeleccionada);
					cargarValoresPanelClasesDeLicencia();
					JOptionPaneTesterGral.showInternalMessageDialog(
							Constantes.MENSAJE_ELIMINADO,
							Constantes.MENSAJE_ELIMINADO_TIT,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPaneTesterGral.showInternalError(
							Util.frameContenedor, new Throwable(
									Constantes.MENSAJE_ERROR_ELIMINAR));

				}

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void btnCancelarFotoActionPerformed(java.awt.event.ActionEvent evt) {
		jLabelLogoMunicipio.setIcon(null);
	}

	private void btnExaminarFotoActionPerformed(java.awt.event.ActionEvent evt) {
		buscarImagen(jLabelLogoMunicipio);
	}

	private void jRadioButtonEsCentroImpresorDeLicenciasActionPerformed(
			java.awt.event.ActionEvent evt) {

		if (usuarioLogueado.getUsrId() != -1) {
			jRadioButtonNoEsCentroImpresorDeLicencias.setSelected(true);
			JOptionPaneTesterGral
					.showInternalMessageDialog(
							"Esta opción sólo puede ser modificada por el Administrador del Sistema",
							"Seguridad", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private void jTableClasesDeLicenciaMouseClicked(
			java.awt.event.MouseEvent evt) {
		jButtonEditarClaseLicencia.setEnabled(true);
		jButtonEliminarCL.setEnabled(true);
	}

	private void jButtonEditarClaseLicenciaActionPerformed(
			java.awt.event.ActionEvent evt) {
		jButtonEditarClaseLicencia.setEnabled(false);
		jButtonEliminarCL.setEnabled(false);
		final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral(
				"Editar Clase de licencia", false, true, false, false);

		int indiceClaseLicenciaSeleccionada = jTableClasesDeLicencia
				.getSelectedRow();
		indiceClaseLicenciaSeleccionada = jTableClasesDeLicencia
				.convertRowIndexToModel(indiceClaseLicenciaSeleccionada);

		TableModelClaseLicencia tableModelClaseLicencia = (TableModelClaseLicencia) jTableClasesDeLicencia
				.getModel();

		ClaseLicencia claseLicenciaSeleccionada = tableModelClaseLicencia
				.getValueAt(indiceClaseLicenciaSeleccionada);
		PanelEdicionClaseLicencia panelEditarClaseLicencia = new PanelEdicionClaseLicencia(
				claseLicenciaSeleccionada, internalframe);

		internalframe.add(panelEditarClaseLicencia);

		internalframe
				.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
					public void internalFrameActivated(
							javax.swing.event.InternalFrameEvent evt) {
					}

					public void internalFrameClosed(
							javax.swing.event.InternalFrameEvent evt) {
					}

					public void internalFrameClosing(
							javax.swing.event.InternalFrameEvent evt) {
						internalframe.close();
					}

					public void internalFrameDeactivated(
							javax.swing.event.InternalFrameEvent evt) {
					}

					public void internalFrameDeiconified(
							javax.swing.event.InternalFrameEvent evt) {
					}

					public void internalFrameIconified(
							javax.swing.event.InternalFrameEvent evt) {
					}

					public void internalFrameOpened(
							javax.swing.event.InternalFrameEvent evt) {
					}
				});

		internalframe.pack();

		internalframe.doModal(Util.framePrincipal.getRootPane());
		internalframe.setVisible(true);

		cargarValoresPanelClasesDeLicencia();
	}

	private void jButtonNuevaClaseLicenciaActionPerformed(
			java.awt.event.ActionEvent evt) {
		final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral(
				"Clase de licencia", false, true, false, false);

		/*
		 * Se crea una nueva clase de licencia.
		 */
		PanelEdicionClaseLicencia panelNuevaClaseLicencia = new PanelEdicionClaseLicencia(
				internalframe);

		internalframe.add(panelNuevaClaseLicencia);

		internalframe
				.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
					public void internalFrameActivated(
							javax.swing.event.InternalFrameEvent evt) {
					}

					public void internalFrameClosed(
							javax.swing.event.InternalFrameEvent evt) {
					}

					public void internalFrameClosing(
							javax.swing.event.InternalFrameEvent evt) {
						internalframe.close();
					}

					public void internalFrameDeactivated(
							javax.swing.event.InternalFrameEvent evt) {
					}

					public void internalFrameDeiconified(
							javax.swing.event.InternalFrameEvent evt) {
					}

					public void internalFrameIconified(
							javax.swing.event.InternalFrameEvent evt) {
					}

					public void internalFrameOpened(
							javax.swing.event.InternalFrameEvent evt) {
					}
				});

		internalframe.pack();

		internalframe.doModal(Util.framePrincipal.getRootPane());
		internalframe.setVisible(true);

		cargarValoresPanelClasesDeLicencia();

	}

	private void cargarValoresDePropiedades() {
		cargarValoresPanelDatosMunicipio();
		cargarValoresPanelClasesDeLicencia();
		String utilizarCaja = ContextManager.getProperty("UTILIZAR_CAJA_SN");
		if (utilizarCaja.equals("S"))
			checkHabilitarCaja.setSelected(true);
		else
			checkHabilitarCaja.setSelected(false);

		String reciboAutomatico = ContextManager
				.getProperty("IMPRIMIR_RECIBO_AUTOMATICAMENTE");
		if (reciboAutomatico.equals("S"))
			checkImprimirRecibo.setSelected(true);
		else
			checkImprimirRecibo.setSelected(false);

		String licenciaFormato = ContextManager.getProperty("LICENCIA.FORMATO");
		if (licenciaFormato.equals("QR"))
			checkFormatoLic.setSelected(true);
		else
			checkFormatoLic.setSelected(false);

		String cajaImporteDuplicado = ContextManager
				.getProperty("CAJA_IMPORTE_DUPLICADO");
		if (!cajaImporteDuplicado.equals("")) {
			cajaImporteDuplicado = cajaImporteDuplicado.replace(".", "#");
			cajaImporteDuplicado = cajaImporteDuplicado.replace(",", ".");
			cajaImporteDuplicado = cajaImporteDuplicado.replace("#", ",");
			jFormattedTextFieldEdadMinima.setValue(Double
					.valueOf(cajaImporteDuplicado));
		}

	}

	private void cargarValoresPanelDatosMunicipio() {

		try {

			Propiedad propiedadRangoDesde = ContextManager
					.getPropertyObj("MUNICIPIO.RANGO.LICENCIAS.DESDE");
			Propiedad propiedadRangoHasta = ContextManager
					.getPropertyObj("MUNICIPIO.RANGO.LICENCIAS.HASTA");

			Propiedad propiedadNombreMunicipio = propiedadService
					.get("SISTEMA.NOMBRE.MUNICIPIO");
			Propiedad propiedadCodigoMunicipio = propiedadService
					.get("SISTEMA.CODIGO.MUNICIPIO");
			Propiedad propiedadFotoMunicipio = propiedadService
					.get("SISTEMA.FOTO.MUNICIPIO");
			Propiedad propiedadEsCentroImpresorSN = propiedadService
					.get("SISTEMA.MUNICIPIO.ES_CENTRO_IMPRESOR_S_N");

			String valorPropiedadNombreMunicipio = propiedadNombreMunicipio
					.getPropValor();
			String valorPropiedadCodigoMunicipio = propiedadCodigoMunicipio
					.getPropValor();
			byte[] valorBytesPropiedadFotoMunicipio = propiedadFotoMunicipio
					.getPropBlob();
			String valorPropiedadEsCentroImpresorSN = propiedadEsCentroImpresorSN
					.getPropValor();

			txtRangoDesde.setText(propiedadRangoDesde.getPropValor());
			txtRangoHasta.setText(propiedadRangoHasta.getPropValor());
			jTextFieldCodigoMunicipio.setText(valorPropiedadCodigoMunicipio);
			jTextFieldNombreMunicipio.setText(valorPropiedadNombreMunicipio);

			if (valorBytesPropiedadFotoMunicipio != null
					&& valorBytesPropiedadFotoMunicipio.length > 1) {
				ImageIcon icon = new ImageIcon(valorBytesPropiedadFotoMunicipio);
				jLabelLogoMunicipio.setIcon(icon);
			}

			if (valorPropiedadEsCentroImpresorSN.equalsIgnoreCase("S")) {
				jRadioButtonEsCentroImpresorDeLicencias.setSelected(true);

			} else {
				jRadioButtonNoEsCentroImpresorDeLicencias.setSelected(true);
			}

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}

	private void cargarValoresPanelClasesDeLicencia() {

		try {
			List<ClaseLicencia> listaDeClasesDeLicencia = claseLicenciaService
					.getAll(new ClaseLicencia());

			setTableModel(listaDeClasesDeLicencia);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public void setTableModel(List<ClaseLicencia> lst) {
		TableModelClaseLicencia tableModel = new TableModelClaseLicencia();
		tableModel.setLst(lst);
		jTableClasesDeLicencia.setModel(tableModel);
		jTableClasesDeLicencia
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTableClasesDeLicencia.setAutoCreateRowSorter(false);
		jTableClasesDeLicencia.setAutoCreateRowSorter(true);
	}

	private void guardarValoresPanelDatosMunicipio() {

		Integer desde = Integer.valueOf(txtRangoDesde.getText());
		Integer hasta = Integer.valueOf(txtRangoHasta.getText());

		Propiedad propiedadRangoDesde = ContextManager
				.getPropertyObj("MUNICIPIO.RANGO.LICENCIAS.DESDE");
		propiedadRangoDesde.setPropValor(txtRangoDesde.getText());
		propiedadRangoDesde.setPropBlob(new byte[1]);

		Propiedad propiedadRangoHasta = ContextManager
				.getPropertyObj("MUNICIPIO.RANGO.LICENCIAS.HASTA");
		propiedadRangoHasta.setPropValor(txtRangoHasta.getText());
		propiedadRangoHasta.setPropBlob(new byte[1]);

		Propiedad propiedadNombreMunicipio = new Propiedad();
		propiedadNombreMunicipio.setPropClave("SISTEMA.NOMBRE.MUNICIPIO");
		propiedadNombreMunicipio.setPropValor(jTextFieldNombreMunicipio
				.getText());

		Propiedad propiedadCodigoMunicipio = new Propiedad();
		propiedadCodigoMunicipio.setPropClave("SISTEMA.CODIGO.MUNICIPIO");
		propiedadCodigoMunicipio.setPropValor(jTextFieldCodigoMunicipio
				.getText());

		Propiedad propiedadFotoMunicipio = new Propiedad();
		propiedadFotoMunicipio.setPropClave("SISTEMA.FOTO.MUNICIPIO");

		Propiedad propiedadEsCentroImpresorSN = new Propiedad();
		propiedadEsCentroImpresorSN
				.setPropClave("SISTEMA.MUNICIPIO.ES_CENTRO_IMPRESOR_S_N");

		if (jRadioButtonEsCentroImpresorDeLicencias.isSelected()) {
			propiedadEsCentroImpresorSN.setPropValor("S");
		} else {
			propiedadEsCentroImpresorSN.setPropValor("N");
		}

		byte[] bytesFotoMunicipio = getImageToArray(jLabelLogoMunicipio);

		if (bytesFotoMunicipio == null) {
			bytesFotoMunicipio = new byte[1];
		}

		propiedadFotoMunicipio.setPropBlob(bytesFotoMunicipio);
		propiedadFotoMunicipio.setPropValor("Foto logo del municipio");

		Propiedad utilizarCaja = ContextManager
				.getPropertyObj("UTILIZAR_CAJA_SN");
		utilizarCaja.setPropBlob(new byte[1]);
		if (checkHabilitarCaja.isSelected())
			utilizarCaja.setPropValor("S");
		else
			utilizarCaja.setPropValor("N");

		Propiedad reciboAutomatico = ContextManager
				.getPropertyObj("IMPRIMIR_RECIBO_AUTOMATICAMENTE");
		reciboAutomatico.setPropBlob(new byte[1]);
		if (checkImprimirRecibo.isSelected())
			reciboAutomatico.setPropValor("S");
		else
			reciboAutomatico.setPropValor("N");

		Propiedad cajaImporteDuplicado = ContextManager
				.getPropertyObj("CAJA_IMPORTE_DUPLICADO");
		cajaImporteDuplicado.setPropValor(jFormattedTextFieldEdadMinima
				.getText());
		cajaImporteDuplicado.setPropBlob(new byte[1]);
		
		Propiedad licenciaFormato = ContextManager.getPropertyObj("LICENCIA.FORMATO");
		licenciaFormato.setPropBlob(new byte[1]);
		if (checkFormatoLic.isSelected())
			licenciaFormato.setPropValor("QR");
		else
			licenciaFormato.setPropValor("SinQR");

		try {

			if (hasta > desde) {
				propiedadService.update(propiedadNombreMunicipio);
				propiedadService.update(propiedadCodigoMunicipio);
				propiedadService.update(propiedadFotoMunicipio);
				propiedadService.update(propiedadRangoDesde);
				propiedadService.update(propiedadRangoHasta);
				propiedadService.update(utilizarCaja);
				propiedadService.update(reciboAutomatico);
				propiedadService.update(cajaImporteDuplicado);
				propiedadService.update(licenciaFormato);

				if (usuarioLogueado.getUsrId() == -1) {
					propiedadService.update(propiedadEsCentroImpresorSN);
				}
				Util.mostrarError(lbSinResultados, "", true);
				JOptionPaneTesterGral.showInternalMessageDialog(
						"Los cambios se guardaron correctamente",
						Constantes.MENSAJE_GUARDADO_TIT,
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				Util.mostrarError(lbSinResultados,
						"El rango de licencias es incorrecto.", false);
			}
			
			

	
		
			

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}

	private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {
		guardarValoresPanelDatosMunicipio();

	}

	public void buscarImagen(final JLabel label) {

		final VentanaSeleccionImagen internalframe = new VentanaSeleccionImagen(
				null, true);
		internalframe.pack();
		Util.agregarIframe(internalframe);

		internalframe.doModal(Util.framePrincipal.getRootPane());
		internalframe.setVisible(true);
		if (internalframe.getImg() != null) {
			ImageIcon imgIcon = new ImageIcon(internalframe.getImg());
			label.setIcon(imgIcon);
		}

	}

	public byte[] getImageToArray(JLabel label) {
		byte[] byteArray = null;
		if (label.getIcon() != null) {
			ImageIcon imageIcon = (ImageIcon) label.getIcon();
			Image img = imageIcon.getImage();
			BufferedImage bi = new BufferedImage(imageIcon.getIconWidth(),
					imageIcon.getIconHeight(), BufferedImage.TYPE_INT_RGB);

			Graphics2D big = bi.createGraphics();
			big.drawImage(img, 0, 0, this);

			ByteArrayOutputStream os = new ByteArrayOutputStream();

			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
			try {
				encoder.encode(bi);
				byteArray = os.toByteArray();
			} catch (Exception e) {
				JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}

		}

		return byteArray;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnCancelarFoto;
	private javax.swing.JButton btnExaminarFoto;
	private javax.swing.ButtonGroup buttonGroupCentroImpresorSiNo;
	private javax.swing.JCheckBox checkFormatoLic;
	private javax.swing.JCheckBox checkHabilitarCaja;
	private javax.swing.JCheckBox checkImprimirRecibo;
	private javax.swing.JButton jButtonEditarClaseLicencia;
	private javax.swing.JButton jButtonEliminarCL;
	private javax.swing.JButton jButtonGuardar;
	private javax.swing.JButton jButtonNuevaClaseLicencia;
	private javax.swing.JFormattedTextField jFormattedTextFieldEdadMinima;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabelCodigoMunicipio;
	private javax.swing.JLabel jLabelCodigoMunicipio1;
	private javax.swing.JLabel jLabelLogoMunicipio;
	private javax.swing.JLabel jLabelNombreMunicipio;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanelBotones;
	private javax.swing.JPanel jPanelClasesDeLicencia;
	private javax.swing.JPanel jPanelDatosMunicipio;
	private javax.swing.JPanel jPanelEsCentroImpresor;
	private javax.swing.JPanel jPanelEsCentroImpresor1;
	private javax.swing.JRadioButton jRadioButtonEsCentroImpresorDeLicencias;
	private javax.swing.JRadioButton jRadioButtonNoEsCentroImpresorDeLicencias;
	private javax.swing.JScrollPane jScrollPaneClasesDeLicencia;
	private javax.swing.JTable jTableClasesDeLicencia;
	private javax.swing.JTextField jTextFieldCodigoMunicipio;
	private javax.swing.JTextField jTextFieldNombreMunicipio;
	private javax.swing.JLabel lbSinResultados;
	private javax.swing.JTextField txtRangoDesde;
	private javax.swing.JTextField txtRangoHasta;
	// End of variables declaration//GEN-END:variables

}