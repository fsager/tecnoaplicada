/*
 * PanelPercepcionReaccionUsuario.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.psicometrico.percepcionreaccion;

import java.awt.Dimension;

import javax.swing.JLabel;

import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class PanelPercepcionReaccionUsuario extends javax.swing.JPanel {

	/** Creates new form PanelPercepcionReaccionUsuario */
	public PanelPercepcionReaccionUsuario(PanelPercepcionReaccionAnimacion panelPercepcionReaccionAnimacion,boolean src,String imgSrc) {
		this.imgSrc=imgSrc;
		Dimension dim=new Dimension(630,367);//1000/581
		this.src = src;
		this.panelPercepcionReaccionAnimacion = panelPercepcionReaccionAnimacion;
		panelPercepcionReaccionAnimacion.setDim(dim);
		initComponents();

		if (src) {
			jTextArea1.setFont(new java.awt.Font("Monospaced", 3, 12));
			JLabel img=new JLabel();
			Util.setIcon(img,imgSrc,dim);
			panelContenido.add(img);
			//panelCaptura = new PanelCaptura(panelPercepcionReaccionAnimacion,dim);
			//panelContenido.add(panelCaptura);
		} else {
			jTextArea1.setFont(new java.awt.Font("Monospaced", 3, 16));
			
			panelContenido.add(panelPercepcionReaccionAnimacion);
		}

		this.validate();
	}

	public void init() {
		panelContenido.removeAll();
		if (src)
		{
			//panelContenido.add(panelCaptura);
			JLabel img=new JLabel();
			Dimension dim1=new Dimension(634,370);
			Util.setIcon(img,imgSrc,dim1);
			panelContenido.add(img);
		}
		else
			panelContenido.add(panelPercepcionReaccionAnimacion);
		
		this.repaint();
	}


	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		panelContenido = new javax.swing.JPanel();

		jTextArea1.setColumns(20);
		jTextArea1.setEditable(false);
		jTextArea1.setFont(new java.awt.Font("Monospaced", 3, 12));
		jTextArea1.setForeground(new java.awt.Color(255, 0, 0));
		jTextArea1.setLineWrap(true);
		jTextArea1.setRows(1);
		jTextArea1.setText("Consigna: realizar la accion indicada.");
		jTextArea1.setWrapStyleWord(true);
		jScrollPane1.setViewportView(jTextArea1);

		panelContenido.setLayout(new java.awt.GridLayout(1, 0));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 528, Short.MAX_VALUE)
						.addGroup(
								layout
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
																				jScrollPane1,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				504,
																				Short.MAX_VALUE)
																		.addComponent(
																				panelContenido,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				504,
																				Short.MAX_VALUE))
														.addContainerGap())));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 345, Short.MAX_VALUE)
						.addGroup(
								layout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												layout
														.createSequentialGroup()
														.addContainerGap()
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																32,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(2, 2, 2)
														.addComponent(
																panelContenido,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																285,
																Short.MAX_VALUE)
														.addContainerGap())));
	}// </editor-fold>
	//GEN-END:initComponents

	public javax.swing.JScrollPane getJScrollPane1() {
		return jScrollPane1;
	}

	public void setJScrollPane1(javax.swing.JScrollPane scrollPane1) {
		jScrollPane1 = scrollPane1;
	}

	public javax.swing.JTextArea getJTextArea1() {
		return jTextArea1;
	}

	public void setJTextArea1(javax.swing.JTextArea textArea1) {
		jTextArea1 = textArea1;
	}

	public javax.swing.JPanel getPanelContenido() {
		return panelContenido;
	}

	public void setPanelContenido(javax.swing.JPanel panelContenido) {
		this.panelContenido = panelContenido;
	}

	public PanelPercepcionReaccionAnimacion getPanelPercepcionReaccionAnimacion() {
		return panelPercepcionReaccionAnimacion;
	}

	public void setPanelPercepcionReaccionAnimacion(
			PanelPercepcionReaccionAnimacion panelPercepcionReaccionAnimacion) {
		this.panelPercepcionReaccionAnimacion = panelPercepcionReaccionAnimacion;
	}

	public boolean isRun() {
		boolean isRun=false;
		/*if(panelCaptura!=null)
			isRun=panelCaptura.isRun();*/
		return isRun;
	}

	public void setRun(boolean run) {
		/*if(panelCaptura!=null)
			panelCaptura.setRun(run);*/
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JPanel panelContenido;
	// End of variables declaration//GEN-END:variables

	private PanelPercepcionReaccionAnimacion panelPercepcionReaccionAnimacion;
	private boolean src;
	private String imgSrc;
	//private PanelCaptura panelCaptura;
}