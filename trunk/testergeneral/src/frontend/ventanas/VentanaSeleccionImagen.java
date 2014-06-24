/*
 * VentanaSeleccionImagen.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.ventanas;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.ImageFilter;
import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.OrigenFotosEnum;
import frontend.components.PanelImagenFinal;
import frontend.paneles.ImagePreviewPanel;
import frontend.paneles.PanelImagen;
import frontend.paneles.PanelWebCam;
import frontend.utils.Util;

/**
 * 
 * @author __USER__
 */
public class VentanaSeleccionImagen extends JInternalFrameTesterGral {

	private static final Log log = LogFactory
			.getLog(VentanaSeleccionImagen.class);
	private Image img;
	private File archivoSeleccionado;
	private Dimension dim;

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public File getArchivoSeleccionado () {
		return archivoSeleccionado;
	}
	
	/**
	 * Creates new form VentanaSeleccionImagen
	 * */
	public VentanaSeleccionImagen() {
		super("Selección de imagen", false, true, false, false);
		initComponents();
		this.dim=new Dimension(113, 113);
		primerPaso();
	}

	/**
	 * Creates new form VentanaSeleccionImagen
	 * 
	 * @param true abre la ventana de selección de imagen desde el Sistema de
	 *        archivos.
	 *        
	 * @param false abre la ventana de selección de imagen desde la Webcam.
	 * 
	 * */
	public VentanaSeleccionImagen(Dimension dim,boolean trueDesdeSistDeArchivos_falseDesdeWebCam) {
		
		super("Selección de imagen", false, true, false, false);
		initComponents();
		this.dim=dim;
		if(this.dim==null)
			this.dim=new Dimension(113, 113);
		if (trueDesdeSistDeArchivos_falseDesdeWebCam == true) {
			abrirFileSystem();
		} else {
			abrirCamara();
		}

	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		panelContenido = new javax.swing.JPanel();
		btnAceptar = new javax.swing.JButton();

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

		panelContenido.setLayout(new java.awt.GridLayout(1, 0));

		btnAceptar.setText(Constantes.BTN_ACEPTAR);
		btnAceptar.setEnabled(false);
		btnAceptar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAceptarActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(300,
						Short.MAX_VALUE).addComponent(btnAceptar))
				.addComponent(panelContenido,
						javax.swing.GroupLayout.DEFAULT_SIZE, 403,
						Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addComponent(panelContenido,
						javax.swing.GroupLayout.DEFAULT_SIZE, 147,
						Short.MAX_VALUE).addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(btnAceptar)));

		pack();
	}// </editor-fold>

	// GEN-END:initComponents

	private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {
		img = ((PanelImagenFinal) panelImage.getPanelImgFinal()).getImg();
		panelContenido = null;
		panelImage = null;
		panelWebCam = null;
		close();
		this.dispose();
	}

	private void formInternalFrameClosing(
			javax.swing.event.InternalFrameEvent evt) {
		cerrar();
	}

	public void cerrar() {
		if (panelWebCam != null)
			panelWebCam.frenar();
		close();
		this.dispose();
	}

	public void primerPaso() {

		if (ContextManager.getProperty("SISTEMA.ORIGEN.FOTOS").compareTo(
				OrigenFotosEnum.DISCO.getValue()) == 0) {
			abrirFileSystem();
		} else {
			abrirCamara();
		}
	}

	public void segundoPaso(ImageIcon img) {

		abriPanelImagen(img);
	}

	public void abrirCamara() {
		btnAceptar.setEnabled(false);
		btnAceptar.setVisible(false);

		panelContenido.removeAll();
		panelWebCam = new PanelWebCam();
		panelContenido.add(panelWebCam);
		this.panelContenido.setMinimumSize(panelWebCam.getPreferredSize());
		this.panelContenido.setMaximumSize(panelWebCam.getPreferredSize());
		this.panelContenido.setSize(panelWebCam.getPreferredSize());
		this.panelContenido.setPreferredSize(panelWebCam.getPreferredSize());
		this.panelContenido.validate();

		this.pack();
		this.repaint();
		Util.centrarIframes(this);

		if(panelWebCam.getBtnAceptar()!=null){
			panelWebCam.getBtnAceptar().addActionListener(new AbstractAction() {
				public void actionPerformed(ActionEvent evt) {
					ImageIcon imageIcon = new ImageIcon(panelWebCam.getImg());
					abriPanelImagen(imageIcon);
				}
			});
		}
		
		if(panelWebCam.getBtnCancelar()!=null){
			panelWebCam.getBtnCancelar().addActionListener(new AbstractAction() {
				public void actionPerformed(ActionEvent evt) {
					cerrar();
				}
			});
		}


	}

	public void abrirFileSystem() {
		btnAceptar.setEnabled(false);
		btnAceptar.setVisible(false);

		panelContenido.removeAll();
		String currentDirectory = ContextManager
				.getProperty("DIRECTORIO.IMAGENES");
		/*
		 * final VentanaSeleccionImagen internalframe = new
		 * VentanaSeleccionImagen(); internalframe.pack();
		 * Util.agregarIframe(internalframe);
		 * 
		 * internalframe.doModal(this.getRootPane());
		 * internalframe.setVisible(true); if (internalframe.getImg() != null) {
		 * ImageIcon imgIcon = new ImageIcon(internalframe.getImg());
		 * label.setIcon(imgIcon); }
		 */

		JFileChooser chooser = new JFileChooser(currentDirectory);
		chooser.setApproveButtonText("Seleccionar");
		panelContenido.add(chooser);

		// chooser.s

		chooser.addChoosableFileFilter(new ImageFilter());
		chooser.setMultiSelectionEnabled(false);
		ImagePreviewPanel preview = new ImagePreviewPanel();
		chooser.setAccessory(preview);
		chooser.addPropertyChangeListener(preview);
		chooser.setVisible(true);
		this.panelContenido.setMinimumSize(chooser.getPreferredSize());
		this.panelContenido.setMaximumSize(chooser.getPreferredSize());
		this.panelContenido.setSize(chooser.getPreferredSize());
		this.panelContenido.setPreferredSize(chooser.getPreferredSize());
		this.panelContenido.validate();
		this.pack();
		Util.centrarIframes(this);
		chooser.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent evt) {
				JFileChooser chooser = (JFileChooser) evt.getSource();
				if (JFileChooser.APPROVE_SELECTION.equals(evt
						.getActionCommand())) {
					archivoSeleccionado = chooser.getSelectedFile();
					if(archivoSeleccionado.exists())
					{
						ImageIcon img = new ImageIcon(archivoSeleccionado.getAbsolutePath());
						segundoPaso(img);
					}

				} else if (JFileChooser.CANCEL_SELECTION.equals(evt
						.getActionCommand())) {

					goBackToSelectionMode();
				}
			}
		});
	}

	public void abriPanelImagen(ImageIcon img) {

		btnAceptar.setEnabled(true);
		btnAceptar.setVisible(true);

		panelContenido.removeAll();
		panelImage = new PanelImagen(img, dim,"SISTEMA.IMAGENES.PORCENTAJE.REDUCCION");
		panelContenido.add(panelImage);
		this.panelContenido.setMinimumSize(panelImage.getPreferredSize());
		this.panelContenido.setMaximumSize(panelImage.getPreferredSize());
		this.panelContenido.setSize(panelImage.getPreferredSize());
		this.panelContenido.setPreferredSize(panelImage.getPreferredSize());
		this.panelContenido.validate();
		this.pack();
		this.repaint();
		/*Util.centrarIframes(this);*/
		
		Point  p=new Point(((Util.framePrincipal.getWidth() - this.getWidth()) / 2),(Util.framePrincipal.getHeight() - this.getHeight()) / 2);
		this.setLocation(p.x,p.y);
		
	}

	public void goBackToSelectionMode() {
		cerrar();
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnAceptar;
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JPanel panelContenido;
	// End of variables declaration//GEN-END:variables

	private PanelImagen panelImage;
	private PanelWebCam panelWebCam;

}