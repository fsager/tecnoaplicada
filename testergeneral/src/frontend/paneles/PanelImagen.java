/*
 * PanelImagen.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import testerGeneral.business.ContextManager;
import frontend.components.PanelImagenFinal;
import frontend.components.ScrollImage;

/**
 *
 * @author  __USER__
 */
public class PanelImagen extends javax.swing.JPanel {

	/** Creates new form PanelImagen */

	ScrollImage canvas;

	private Image img;
	private Dimension dim;

	public PanelImagen(ImageIcon image,Dimension dim,String propiedad) {
		super();
		this.dim=dim;
		initComponents();
		canvas = new ScrollImage(dim, image, (PanelImagenFinal) panelImgFinal);
		this.setOpaque(true);
		canvas.setBounds(0, 0, 572, 487);
		panelImagen.add(canvas, new Integer(0));

		panelImagen.add(createColoredLabel(),new Integer(1));

		Integer tamaño = Integer.valueOf(ContextManager
				.getProperty(propiedad));
		txtTamaño.setValue(tamaño);
		ajustarTamano();
		panelImagen.setOpaque(true);
		this.repaint();
	}

	private JPanel createColoredLabel() {
		PanelRecuadroImagen jPanel = new PanelRecuadroImagen(dim.width,
				dim.height);
		jPanel.setOpaque(false);

		Point p = new Point();

		p.x = (int) ((572 / 2f) - ((float) dim.width / 2f));
		p.y = (int) ((487 / 2f) - ((float) dim.height / 2f));

		jPanel.setBounds(p.x, p.y, dim.width, dim.height);
		return jPanel;
	}

	public javax.swing.JPanel getPanelImgFinal() {
		return panelImgFinal;
	}

	public void setPanelImgFinal(javax.swing.JPanel panelImgFinal) {
		this.panelImgFinal = panelImgFinal;
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenu2 = new javax.swing.JMenu();
		jPanel1 = new javax.swing.JPanel();
		panelImgFinal = new PanelImagenFinal();
		txtTamaño = new javax.swing.JSpinner();
		jLabel1 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		panelImagen = new javax.swing.JLayeredPane();

		jMenu1.setText("File");
		jMenuBar1.add(jMenu1);

		jMenu2.setText("Edit");
		jMenuBar1.add(jMenu2);

		setMinimumSize(new java.awt.Dimension(500, 500));

		jPanel1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Imagen Final"));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				panelImgFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 220,
				Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addComponent(
						panelImgFinal, javax.swing.GroupLayout.PREFERRED_SIZE,
						113, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		txtTamaño.setModel(new javax.swing.SpinnerNumberModel(100, 1, 100, 1));
		txtTamaño.setValue(100);
		txtTamaño.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				txtTamañoStateChanged(evt);
			}
		});

		jLabel1.setText("Tama\u00f1o(%): ");

		jButton1.setText("Ajustar Tama\u00f1o");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
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
										.addComponent(
												panelImagen,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												588, Short.MAX_VALUE)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGap(
																				12,
																				12,
																				12)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jButton1,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								215,
																								Short.MAX_VALUE)
																						.addGroup(
																								layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel1,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												73,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																												95,
																												Short.MAX_VALUE)
																										.addComponent(
																												txtTamaño,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)))
																		.addContainerGap())
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jPanel1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))));
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
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jPanel1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(
																				13,
																				13,
																				13)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel1)
																						.addComponent(
																								txtTamaño,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jButton1))
														.addComponent(
																panelImagen,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																487,
																Short.MAX_VALUE))
										.addContainerGap()));
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		ajustarTamano();
	}

	private void txtTamañoStateChanged(javax.swing.event.ChangeEvent evt) {
		ajustarTamano();
	}

	public void ajustarTamano() {
		float proporcion = ((Integer) txtTamaño.getValue()) / 100f;
		canvas.resetImageSize();
		canvas.reSize(proporcion);
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JLayeredPane panelImagen;
	private javax.swing.JPanel panelImgFinal;
	private javax.swing.JSpinner txtTamaño;
	// End of variables declaration//GEN-END:variables
	private Image imgFinal;

}