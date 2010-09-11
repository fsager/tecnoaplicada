/*
 * PanelWebCam.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles;

import java.awt.Component;
import java.awt.Image;

import javax.media.Buffer;
import javax.media.CaptureDeviceInfo;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.cdm.CaptureDeviceManager;
import javax.media.control.FormatControl;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.protocol.CaptureDevice;
import javax.media.protocol.DataSource;
import javax.media.util.BufferToImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.business.ContextManager;
import frontend.components.JOptionPaneTesterGral;

/**
 *
 * @author  __USER__
 */
public class PanelWebCam extends javax.swing.JPanel {

	private CaptureDeviceInfo dev;
	private Format[] cfmts;
	private MediaLocator loc;
	private DataSource ds;
	private Player player;
	private static final Log log = LogFactory.getLog(PanelWebCam.class);
	private Image img;
	private boolean frenado = false;

	/** Creates new form PanelWebCam */
	public PanelWebCam() {
		initComponents();
		iniciarCamara();
	}

	private void iniciarCamara() {
		try {
			String camara = ContextManager
					.getProperty("SISTEMA.DEVICES.CAMARA");
			dev = CaptureDeviceManager.getDevice(camara);			
			loc = dev.getLocator();
			
			startPlayer();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	public boolean requestCaptureFormat(Format requested_format, DataSource ds) {
		 
        if (ds instanceof CaptureDevice) {
            FormatControl[] fcs = ((CaptureDevice) ds).getFormatControls();
            for (FormatControl fc : fcs) {
                Format[] formats = ((FormatControl) fc).getSupportedFormats();
                for (Format format : formats) {
                    if (requested_format.matches(format)) {
                        ((FormatControl) fc).setFormat(format);
                        return true;
                    }
                }
            }
        }
        return false;
    }

	private void startPlayer() {
		try {
			frenado = false;
			ds = Manager.createDataSource(loc);			
			requestCaptureFormat(dev.getFormats()[0],ds);
			
			player = Manager.createRealizedPlayer(ds);
			
			player.start();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			Component comp = null;
			if ((comp = player.getVisualComponent()) != null)
			{	panelWeb.removeAll();
				panelWeb.add(comp);
			}
			
			this.validate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		panelWeb = new javax.swing.JPanel();
		btnCapturar = new javax.swing.JButton();
		btnCancelar = new javax.swing.JButton();
		btnInicarCamara = new javax.swing.JButton();
		btnAceptar = new javax.swing.JButton();

		panelWeb.setLayout(new java.awt.GridLayout(1, 0));

		btnCapturar.setText("Capturar");
		btnCapturar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCapturarActionPerformed(evt);
			}
		});

		btnCancelar.setText("Cancelar");
		btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarActionPerformed(evt);
			}
		});

		btnInicarCamara.setText("Iniciar Camara");
		btnInicarCamara.setEnabled(false);
		btnInicarCamara.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnInicarCamaraActionPerformed(evt);
			}
		});

		btnAceptar.setText("Aceptar");
		btnAceptar.setEnabled(false);
		btnAceptar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAceptarActionPerformed(evt);
			}
		});

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
										.addComponent(btnInicarCamara)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												310, Short.MAX_VALUE)
										.addComponent(btnCapturar)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnAceptar)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnCancelar))
						.addGroup(
								layout.createSequentialGroup().addGap(12, 12,
										12).addComponent(panelWeb,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										640, Short.MAX_VALUE).addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addComponent(
												panelWeb,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												480, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																btnCancelar)
														.addComponent(
																btnInicarCamara)
														.addComponent(
																btnAceptar)
														.addComponent(
																btnCapturar))));
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btnInicarCamaraActionPerformed(java.awt.event.ActionEvent evt) {

		btnCapturar.setEnabled(true);
		btnInicarCamara.setEnabled(false);
		btnAceptar.setEnabled(false);

		startPlayer();
	}

	public javax.swing.JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(javax.swing.JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	private void btnCapturarActionPerformed(java.awt.event.ActionEvent evt) {

		FrameGrabbingControl fgc = (FrameGrabbingControl) player
				.getControl("javax.media.control.FrameGrabbingControl");
		Buffer buf = fgc.grabFrame();
		BufferToImage btoi = new BufferToImage((VideoFormat) buf.getFormat());
		JLabel lb = new JLabel();
		img = btoi.createImage(buf);

		btnCapturar.setEnabled(false);
		btnInicarCamara.setEnabled(true);
		btnAceptar.setEnabled(true);

		frenar();

		ImageIcon imageIcon = new ImageIcon(img);
		lb.setIcon(imageIcon);
		panelWeb.removeAll();
		panelWeb.add(lb);
		this.repaint();
		this.validate();
	}

	public void frenar() {
		try {
			if (!frenado) {
				player.stop();
				player.deallocate();
				player.close();
				frenado = true;
			}
		} catch (Exception ex) {
			
		}
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnAceptar;
	private javax.swing.JButton btnCancelar;
	private javax.swing.JButton btnCapturar;
	private javax.swing.JButton btnInicarCamara;
	private javax.swing.JPanel panelWeb;

	// End of variables declaration//GEN-END:variables

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public javax.swing.JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(javax.swing.JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

}