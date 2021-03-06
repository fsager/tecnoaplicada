/*
 * PanelPercepcionReaccionUsuario.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.psicometrico.reaccionsimple;

import javax.swing.JLabel;

import testerGeneral.domain.Constantes;
import frontend.paneles.examenes.PanelCaptura;
import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class PanelReaccionSimpleUsuario extends javax.swing.JPanel {

	/** Creates new form PanelPercepcionReaccionUsuario */
	public PanelReaccionSimpleUsuario(
			PanelReaccionSimpleAnimacion panelReaccionSimpleAnimacion,
			boolean src) {
		this.src = src;
		this.panelReaccionSimpleAnimacion = panelReaccionSimpleAnimacion;
		initComponents();
		this.getTxtMensajes().setText("a");
		this.getTxtMensajes().setVisible(false);

		if (src) {
			jTextArea1.setFont(new java.awt.Font("Monospaced", 3, 12));
			txtMensajes.setFont(new java.awt.Font("Monospaced", 3, 12));
			//panelCaptura = new PanelCaptura(panelReaccionSimpleAnimacion);
			//panelContenido.add(panelCaptura);
			JLabel img=new JLabel();
			Util.setIcon(img,Constantes.IMG_FONDO_REACION_SIMPLE_COMPLETO);
			panelContenido.add(img);

		} else {
			jTextArea1.setFont(new java.awt.Font("Monospaced", 3, 16));
			txtMensajes.setFont(new java.awt.Font("Monospaced", 3, 16));
			panelContenido.add(panelReaccionSimpleAnimacion);
		}

		this.validate();
	}

	public javax.swing.JTextArea getTxtMensajes() {
		return txtMensajes;
	}

	public void setTxtMensajes(javax.swing.JTextArea txtMensajes) {
		this.txtMensajes = txtMensajes;
	}

	public void init() {
		panelContenido.removeAll();
		if (src)
		{
			//panelContenido.add(panelCaptura);
			JLabel img=new JLabel();
			Util.setIcon(img,Constantes.IMG_FONDO_REACION_SIMPLE_COMPLETO);
			panelContenido.add(img);
		}
		else			
			panelContenido.add(panelReaccionSimpleAnimacion);
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
		jScrollPane2 = new javax.swing.JScrollPane();
		txtMensajes = new javax.swing.JTextArea();

		jScrollPane1
				.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane1
				.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		jTextArea1.setColumns(20);
		jTextArea1.setEditable(false);
		jTextArea1.setFont(new java.awt.Font("Monospaced", 3, 12));
		jTextArea1.setForeground(new java.awt.Color(255, 0, 0));
		jTextArea1.setLineWrap(true);
		jTextArea1.setRows(2);
		jTextArea1
				.setText("Consigna: acelerar mientras la luz est\u00e1 en verde, con luz roja frenar lo m\u00e1s r\u00e1pido posible con el mismo pie.");
		jTextArea1.setWrapStyleWord(true);
		jScrollPane1.setViewportView(jTextArea1);

		panelContenido.setLayout(new java.awt.GridLayout(1, 0));

		jScrollPane2
				.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane2
				.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		txtMensajes.setColumns(20);
		txtMensajes.setEditable(false);
		txtMensajes.setFont(new java.awt.Font("Monospaced", 3, 12));
		txtMensajes.setForeground(new java.awt.Color(255, 0, 0));
		txtMensajes.setLineWrap(true);
		txtMensajes.setRows(1);
		txtMensajes.setText("Presionar el acelerados para iniciar.");
		txtMensajes.setWrapStyleWord(true);
		jScrollPane2.setViewportView(txtMensajes);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jScrollPane2,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												504, Short.MAX_VALUE)
										.addContainerGap())
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
																				panelContenido,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				504,
																				Short.MAX_VALUE)
																		.addComponent(
																				jScrollPane1,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				504,
																				Short.MAX_VALUE))
														.addContainerGap())));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(396,
						Short.MAX_VALUE).addComponent(jScrollPane2,
						javax.swing.GroupLayout.PREFERRED_SIZE, 31,
						javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(
				layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						layout.createSequentialGroup().addContainerGap()
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										45,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(3, 3, 3).addComponent(panelContenido,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										334, Short.MAX_VALUE)
								.addGap(32, 32, 32))));
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

	public PanelReaccionSimpleAnimacion getPanelPercepcionReaccionAnimacion() {
		return panelReaccionSimpleAnimacion;
	}

	public void setPanelPercepcionReaccionAnimacion(
			PanelReaccionSimpleAnimacion panelPercepcionReaccionAnimacion) {
		this.panelReaccionSimpleAnimacion = panelPercepcionReaccionAnimacion;
	}

	public boolean isRun() {
		boolean isRun = false;
		/*if (panelCaptura != null)
			isRun = panelCaptura.isRun();*/
		return isRun;
	}

	public void setRun(boolean run) {
		/*if (panelCaptura != null)
			panelCaptura.setRun(run);*/
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JPanel panelContenido;
	private javax.swing.JTextArea txtMensajes;
	// End of variables declaration//GEN-END:variables

	private PanelReaccionSimpleAnimacion panelReaccionSimpleAnimacion;
	private boolean src;
	//private PanelCaptura panelCaptura;
}