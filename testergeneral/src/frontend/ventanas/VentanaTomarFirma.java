/*
 * VentanaTomarFirma.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.ventanas;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.domain.Constantes;
import frontend.components.PanelImagenFinal;
import frontend.paneles.PanelFirma;
import frontend.paneles.PanelImagen;

/**
 *
 * @author  __USER__
 */
public class VentanaTomarFirma extends JInternalFrameTesterGral {

	private static final Log log = LogFactory.getLog(VentanaTomarFirma.class);
	private Image img;

	/** Creates new form VentanaTomarFirma */
	public VentanaTomarFirma() {
		super("Adquirir de firma", false, true, false, false);
		initComponents();
		initPanelFirmas();
	}

	public void initPanelFirmas() {

		panelFirma = new PanelFirma();
		this.panelContenido.add(panelFirma);
		this.panelContenido.setSize(panelFirma.getSize());
		this.panelContenido.setPreferredSize(panelFirma.getSize());
		this.panelContenido.setMinimumSize(panelFirma.getSize());

		this.pack();
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public void cerrar() {
		panelContenido = null;
		panelImage = null;
		panelFirma = null;
		System.gc();
		close();
		this.dispose();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		btnAceptar = new javax.swing.JButton();
		panelContenido = new javax.swing.JPanel();
		btnLimpiar = new javax.swing.JButton();

		addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
			public void internalFrameActivated(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameClosed(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameClosing(
					javax.swing.event.InternalFrameEvent evt) {
				formInternalFrameClosing(evt);
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

		btnAceptar.setText(Constantes.BTN_ACEPTAR);
		btnAceptar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAceptarActionPerformed(evt);
			}
		});

		panelContenido.setLayout(new java.awt.GridLayout());

		btnLimpiar.setText("Limpiar");
		btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLimpiarActionPerformed(evt);
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
										.addContainerGap(211, Short.MAX_VALUE)
										.addComponent(btnLimpiar)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnAceptar))
						.addComponent(panelContenido,
								javax.swing.GroupLayout.DEFAULT_SIZE, 394,
								Short.MAX_VALUE));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addComponent(
												panelContenido,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												246, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																btnAceptar)
														.addComponent(
																btnLimpiar))));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
		panelFirma.clearPantalla();
		panelFirma.repaint();
	}

	private void formInternalFrameClosing(
			javax.swing.event.InternalFrameEvent evt) {
		cerrar();
	}

	private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {
		if (panelImage != null) {
			img = ((PanelImagenFinal) panelImage.getPanelImgFinal()).getImg();
			cerrar();
		} else {
			btnLimpiar.setVisible(false);
			ImageIcon icon = new ImageIcon(panelFirma.getImgTodo());
			abriPanelImagen(icon);
		}

	}

	public void abriPanelImagen(ImageIcon img) {

		btnAceptar.setEnabled(true);
		btnAceptar.setVisible(true);

		panelContenido.removeAll();
		panelImage = new PanelImagen(img,new Dimension(200,110),"SISTEMA.FIRMAS.PORCENTAJE.REDUCCION");
		panelContenido.add(panelImage);
		this.panelContenido.setMinimumSize(panelImage.getPreferredSize());
		this.panelContenido.setMaximumSize(panelImage.getPreferredSize());
		this.panelContenido.setSize(panelImage.getPreferredSize());
		this.panelContenido.setPreferredSize(panelImage.getPreferredSize());
		this.panelContenido.validate();

		this.pack();
		this.repaint();

	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnAceptar;
	private javax.swing.JButton btnLimpiar;
	private javax.swing.JPanel panelContenido;
	// End of variables declaration//GEN-END:variables
	private PanelImagen panelImage;
	private PanelFirma panelFirma;

}