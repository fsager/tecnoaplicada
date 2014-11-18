/*
 * PanelLicencia.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.licence;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import tecnologia.aplicada.licence.LicenceManager;
import testerGeneral.business.ContextManager;
import testerGeneral.domain.Propiedad;
import testerGeneral.service.PropiedadDefinition;
import ar.com.tecnologiaaplicada.LicenseException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import frontend.buttons.ButtonGuardar;
import frontend.components.CopyPastePopUp;
import frontend.components.ExtensionFileFilter;
import frontend.components.JOptionPaneTesterGral;
import frontend.utils.Util;
import frontend.ventanas.JInternalFrameTesterGral;
import frontend.ventanas.VentanaExaminar;
import frontend.ventanas.VentanaSeleccionImagen;

/**
 *
 * @author  __USER__
 */
public class PanelLicencia extends javax.swing.JPanel {
	private JInternalFrameTesterGral internalframe;
	private static SimpleDateFormat sdf = new SimpleDateFormat(ContextManager
			.getProperty("FORMATO.FECHA.HORA"));
	private PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager
			.getBizObject("propiedadService");
	
	/** Creates new form PanelLicencia */
	public PanelLicencia(JInternalFrameTesterGral internalframe,
			boolean prueba, boolean actualizarLicencia) {
		
		initComponents();
		
		try {
			logoEmpresa = propiedadService
					.get("SISTEMA.IMAGENES.LOGO.EMPRESA");
		} catch (Exception e) {
				throw new RuntimeException(e);
		}	

		
		if (logoEmpresa.getPropBlob() != null && logoEmpresa.getPropBlob().length > 1) {
			ImageIcon icon = new ImageIcon(logoEmpresa.getPropBlob());
			lbFoto.setIcon(icon);
		}
		
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

		}

		txtNroLicencia.setText(ContextManager.getProperty("LICENCE.NRO"));

		JEditorPane htmlPane = new JEditorPane();
		htmlPane.setContentType("text/html");

		htmlPane.setEditable(false);

		String html = "<html><font size=\"3\"><b>Si dispone de conexión a internet en el equipo</b> utilice la actualización automática. Para ello solo requiere indicar el nro. de licencia que se le ha otorgado y presionar el botón Guardar/Actualizar."
				+ "<br><b>Si dispone de conexión a internet pero no en el equipo donde se encuentra</b> el sistema instalado presione sobre el botón generar código de activación y anote el mismo en un lugar donde luego lo pueda consultar. Una vez que se encuentre en un equipo con conexión a internet diríjase a <a href=\"http://app.jttecnologiaaplicada.com/tecnologiaAplicada/opciones/activacion_manual.zul\">http://app.jttecnologiaaplicada.com/tecnologiaAplicada/opciones/activacion_manual.zul</a> e ingrese el código generado y su número de licencia en el sitio web para generar un archivo de licencia. Una vez que se descarga el archivo de licencia, se debe ingresar el nro. de licencia e importar el archivo mediante la opción Importar Archivo de Licencia.</html>";

		htmlPane.setText(html);
		panelHelp.add(htmlPane);
		
		btnGuardar.setText("Guardar/Actualizar");
		

		CopyPastePopUp copyPastePopUp1=new CopyPastePopUp(htmlPane);
		CopyPastePopUp copyPastePopUp2=new CopyPastePopUp(txtNroLicencia);
		CopyPastePopUp copyPastePopUp3=new CopyPastePopUp(txtCodigoActivacion);
		
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

				throw new RuntimeException(e);
			}

		}

		return byteArray;
	}
	
	void btnCancelarFotoActionPerformed(java.awt.event.ActionEvent evt){
		lbFoto.setIcon(null);
		logoEmpresa.setPropBlob(new byte[1]);
		try {
			propiedadService.update(logoEmpresa);
		} catch (Exception e) {
				throw new RuntimeException(e);
		}
	}
	
	private void btnExaminarFotoActionPerformed(java.awt.event.ActionEvent evt) {
		buscarImagen(lbFoto);
		
		byte[] bytes = getImageToArray(lbFoto);
		if (bytes != null) {
			logoEmpresa.setPropBlob(bytes);			
			try {
				propiedadService.update(logoEmpresa);
			} catch (Exception e) {
					throw new RuntimeException(e);
			}
		}

	}

	public void buscarImagen(final JLabel label) {

		final VentanaSeleccionImagen internalframe = new VentanaSeleccionImagen();
		internalframe.pack();
		Util.agregarIframe(internalframe);

		internalframe.doModal(this.getRootPane());
		internalframe.setVisible(true);
		if (internalframe.getImg() != null) {
			ImageIcon imgIcon = new ImageIcon(internalframe.getImg());
			label.setIcon(imgIcon);

			System.gc();
		}

	}
	
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
	
		jLabel1 = new javax.swing.JLabel();
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
		panelHelp = new javax.swing.JPanel();

		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12));
		jLabel1.setText("Nro de Licencia:");

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

		//Panel logo 
		jPanelLogo = new javax.swing.JPanel();
		
		jButtonBuscarLogo = new javax.swing.JButton();
		jButtonBuscarLogo.setText("Agregar");
		
		jButtonBuscarLogo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExaminarFotoActionPerformed(evt);
			}
		});
		
		jButtonBorrarLogo = new javax.swing.JButton();
		jButtonBorrarLogo.setText("Borrar");
		jButtonBorrarLogo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarFotoActionPerformed(evt);
			}
		});
		
		lbFoto=new JLabel();
		lbFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbFoto.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
		lbFoto.setBorder(javax.swing.BorderFactory.createTitledBorder("Logo"));
		lbFoto.setMaximumSize(new java.awt.Dimension(246, 150));
		lbFoto.setMinimumSize(new java.awt.Dimension(246, 150));	
		
		javax.swing.GroupLayout groupLayout=new javax.swing.GroupLayout(jPanelLogo);
		jPanelLogo.setLayout(groupLayout);
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
										.addComponent(lbFoto)
										.addGroup(groupLayout.createParallelGroup()
												.addComponent(jButtonBorrarLogo)
												.addComponent(jButtonBuscarLogo)));
		
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
				.addComponent(lbFoto)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(jButtonBorrarLogo)
						.addComponent(jButtonBuscarLogo)));
		
		
		
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addComponent(
						btnCodigoActivacion).addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtCodigoActivacion,
								javax.swing.GroupLayout.DEFAULT_SIZE, 161,
								Short.MAX_VALUE).addContainerGap())
				.addComponent(btnImportarArchivoLic,
						javax.swing.GroupLayout.DEFAULT_SIZE, 343,
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

		btnGuardar.setText("Guardar/Actualizar");
		btnGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGuardarActionPerformed(evt);
			}
		});

		panelHelp.setLayout(new java.awt.GridLayout());

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
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								layout
																										.createSequentialGroup()
																										.addComponent(
																												jRadioLicencia)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jRadioPrueba))
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
																												200,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jPanel1,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								panelHelp,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								655,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(jPanelLogo,
																									javax.swing.GroupLayout.PREFERRED_SIZE,
																									655,
																									javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabelLicenciaPrueba,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								655,
																								Short.MAX_VALUE)
																						.addComponent(
																								jLabelLicencia,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								655,
																								Short.MAX_VALUE)))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGap(
																				244,
																				244,
																				244)
																		.addComponent(
																				btnGuardar,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				183,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
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
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												panelHelp,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												159,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(
												jPanelLogo,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												159,
												javax.swing.GroupLayout.PREFERRED_SIZE)

										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				jPanel1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGap(
																				71,
																				71,
																				71)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel1)
																						.addComponent(
																								txtNroLicencia,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jLabelLicenciaPrueba)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jLabelLicencia)
										.addGap(18, 18, 18)
										.addComponent(
												btnGuardar,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		((ButtonGuardar) btnGuardar).init();
	}// </editor-fold>
	//GEN-END:initComponents

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
			try {
				LicenceManager.actualizarLicencia(bytes, txtNroLicencia.getText());
				JOptionPaneTesterGral.showInternalMessageDialog("La licencia se actualizó correctamente.", "Licencia",JOptionPane.INFORMATION_MESSAGE);
				if (internalframe != null)
					internalframe.close();
			} catch (java.security.GeneralSecurityException e) {
				JOptionPaneTesterGral
						.showInternalMessageDialog(
								this,
								"El nro. de licencia ingresado, no se corresponde con la licencia importada.",
								"Activación del producto",
								JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPaneTesterGral.showInternalMessageDialog(this, "<HTML>"
						+ e.getMessage() + "</HTML>",
						"Activación del producto",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			if (jRadioLicencia.isSelected()) {
				LicenceManager.actualizarLicencia(txtNroLicencia.getText(),
						null);
				JOptionPaneTesterGral.showInternalMessageDialog(
						"La licencia se actualizó correctamente.", "Licencia",
						JOptionPane.INFORMATION_MESSAGE);

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

		} catch (LicenseException e) {

			JOptionPaneTesterGral.showInternal("<HTML>" + e.getMessage()
					+ "</HTML>", "Activación del producto",
					JOptionPane.INFORMATION_MESSAGE, false);
			LicenceManager.showLicencePanel();
		}
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
	private javax.swing.JButton jButtonBuscarLogo;
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabelLicencia;
	private javax.swing.JLabel jLabelLicenciaPrueba;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanelLogo;
	private javax.swing.JRadioButton jRadioLicencia;
	private javax.swing.JRadioButton jRadioPrueba;
	private javax.swing.JPanel panelHelp;
	private javax.swing.JTextField txtCodigoActivacion;
	private javax.swing.JTextField txtNroLicencia;
	private JButton jButtonBorrarLogo;
	private JLabel lbFoto;
	private Propiedad logoEmpresa;
	// End of variables declaration//GEN-END:variables

}