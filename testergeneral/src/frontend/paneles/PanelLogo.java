/*
 * PanelLicencia.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles;

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
public class PanelLogo extends javax.swing.JPanel {
	private JInternalFrameTesterGral internalframe;
	private static SimpleDateFormat sdf = new SimpleDateFormat(ContextManager
			.getProperty("FORMATO.FECHA.HORA"));
	private PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager
			.getBizObject("propiedadService");
	
	/** Creates new form PanelLicencia */
	public PanelLogo() {
		
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
		
		//this.internalframe = internalframe;


		
		
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
	
	private void initComponents() {

		//Panel logo 
		jPanelLogo = new javax.swing.JPanel();
		jPanelLogo.setSize(700,700);
		
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
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		//addComponent(Component component, int min, int pref, int max)


		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(jPanelLogo,400,400,400));
		layout.setVerticalGroup((layout.createParallelGroup()
				.addComponent(jPanelLogo,500,500,500)));

	}// </editor-fold>
	


	private javax.swing.JButton jButtonBuscarLogo;
	private javax.swing.JPanel jPanelLogo;
	private JButton jButtonBorrarLogo;
	private JLabel lbFoto;
	private Propiedad logoEmpresa;

}