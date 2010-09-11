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
import autoimpresor.domain.Usuario;
import autoimpresor.frontend.tablemodels.TableModelClaseLicencia;
import autoimpresor.service.ClaseLicenciaDefinition;

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
	Usuario usuarioLogueado = (Usuario) Util.usuarioCommon;

	/** Creates new form PanelGestionDeMunicipio */
	public PanelGestionDeMunicipio() {
		initComponents();
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
		jPanelClasesDeLicencia = new javax.swing.JPanel();
		jScrollPaneClasesDeLicencia = new javax.swing.JScrollPane();
		jTableClasesDeLicencia = new javax.swing.JTable();
		jButtonNuevaClaseLicencia = new javax.swing.JButton();
		jButtonEditarClaseLicencia = new javax.swing.JButton();
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
										.addContainerGap(205, Short.MAX_VALUE)));
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
										.addContainerGap()
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
																Short.MAX_VALUE))
										.addContainerGap(48, Short.MAX_VALUE)));
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
														.addGroup(
																jPanelDatosMunicipioLayout
																		.createSequentialGroup()
																		.addComponent(
																				jTextFieldCodigoMunicipio,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				164,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(
																				194,
																				194,
																				194))
														.addComponent(
																jTextFieldNombreMunicipio,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																424,
																Short.MAX_VALUE)))
						.addGroup(
								jPanelDatosMunicipioLayout
										.createSequentialGroup()
										.addComponent(
												jPanelEsCentroImpresor,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGap(4, 4, 4)
										.addComponent(
												jPanelEsCentroImpresor1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanelDatosMunicipioLayout
				.setVerticalGroup(jPanelDatosMunicipioLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
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
																javax.swing.GroupLayout.Alignment.LEADING,
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
																		.addComponent(
																				jPanelEsCentroImpresor,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
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
																804,
																Short.MAX_VALUE)
														.addGroup(
																jPanelClasesDeLicenciaLayout
																		.createSequentialGroup()
																		.addComponent(
																				jButtonNuevaClaseLicencia)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jButtonEditarClaseLicencia)))
										.addContainerGap()));
		jPanelClasesDeLicenciaLayout
				.setVerticalGroup(jPanelClasesDeLicenciaLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanelClasesDeLicenciaLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jScrollPaneClasesDeLicencia,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												196, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelClasesDeLicenciaLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jButtonNuevaClaseLicencia)
														.addComponent(
																jButtonEditarClaseLicencia))));

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
																				468,
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
	}

	private void jButtonEditarClaseLicenciaActionPerformed(
			java.awt.event.ActionEvent evt) {
		jButtonEditarClaseLicencia.setEnabled(false);
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
		try {

			if (hasta > desde) {
				propiedadService.update(propiedadNombreMunicipio);
				propiedadService.update(propiedadCodigoMunicipio);
				propiedadService.update(propiedadFotoMunicipio);
				propiedadService.update(propiedadRangoDesde);
				propiedadService.update(propiedadRangoHasta);

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

		final VentanaSeleccionImagen internalframe = new VentanaSeleccionImagen(null,true);
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
	private javax.swing.JButton jButtonEditarClaseLicencia;
	private javax.swing.JButton jButtonGuardar;
	private javax.swing.JButton jButtonNuevaClaseLicencia;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabelCodigoMunicipio;
	private javax.swing.JLabel jLabelLogoMunicipio;
	private javax.swing.JLabel jLabelNombreMunicipio;
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