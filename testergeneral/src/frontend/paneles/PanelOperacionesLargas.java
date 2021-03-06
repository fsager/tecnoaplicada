/*
 * PanelOperacionesLargas.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;

import testerGeneral.domain.Constantes;
import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class PanelOperacionesLargas extends javax.swing.JPanel {

	/** Creates new form PanelOperacionesLargas */
	public PanelOperacionesLargas() {
		initComponents();

		lbIcon.setText(null);
		Util.setIcon(lbIcon, Constantes.IMG_DIALOG_INFO);
		lbMessage
				.setText("La operación puede demorar algunos segundos. Por favor aguarde un instante.");
		Dimension dim = new Dimension(600, 70);
		this.setSize(dim);
		this.setMinimumSize(dim);
		this.setPreferredSize(dim);

	}

	public PanelOperacionesLargas(String mensaje) {
		initComponents();

		lbIcon.setText(null);
		Util.setIcon(lbIcon, Constantes.IMG_DIALOG_INFO);
		lbMessage.setText(mensaje);
		Dimension dim = new Dimension(600, 70);
		this.setSize(dim);
		this.setMinimumSize(dim);
		this.setPreferredSize(dim);

	}

	public PanelOperacionesLargas(String mensaje, JButton btn) {
		initComponents();

		lbIcon.setText(null);
		Util.setIcon(lbIcon, Constantes.IMG_DIALOG_INFO);
		lbMessage.setText(mensaje);
		panelCancel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		panelCancel.add(btn);

		Dimension dim = new Dimension(600, 70);
		this.setSize(dim);
		this.setMinimumSize(dim);
		this.setPreferredSize(dim);

	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		lbIcon = new javax.swing.JLabel();
		lbMessage = new javax.swing.JLabel();
		panelCancel = new javax.swing.JPanel();

		lbIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbIcon.setText("jLabel1");

		lbMessage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lbMessage.setText("jLabel2");

		panelCancel.setLayout(new java.awt.GridLayout());

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
												lbIcon,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												76,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												lbMessage,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												254, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												panelCancel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												113,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(
												lbIcon,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												74,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(
												lbMessage,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												65,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(
								layout.createSequentialGroup().addGap(21, 21,
										21).addComponent(panelCancel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										31,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
	}// </editor-fold>
	//GEN-END:initComponents

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JLabel lbIcon;
	private javax.swing.JLabel lbMessage;
	private javax.swing.JPanel panelCancel;
	// End of variables declaration//GEN-END:variables

}