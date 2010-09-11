/*
 * PanelMensajes.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.components;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JOptionPane;

import testerGeneral.domain.Constantes;
import frontend.buttons.ButtonAceptarConTexto;
import frontend.buttons.ButtonCancelarConTexto;
import frontend.utils.Util;
import frontend.ventanas.JInternalFrameTesterGral;

/**
 *
 * @author  __USER__
 */
public class PanelMensajes extends javax.swing.JPanel implements ClipboardOwner {

	private int selOpt = -1;
	private JInternalFrameTesterGral internalframe;

	/** Creates new form PanelMensajes */
	public PanelMensajes(JInternalFrameTesterGral internalframe, int buttons,
			int icon, String message) {

		init(internalframe, buttons, icon, message);
		panelDetalleError.setVisible(false);
		Dimension dim = new Dimension(500, 120);
		this.setMinimumSize(dim);
		this.setPreferredSize(dim);
	}

	public PanelMensajes(JInternalFrameTesterGral internalframe, int buttons,
			int icon, String message, Throwable ex) {
		init(internalframe, buttons, icon, message);
		if (ex != null) {
			panelDetalleError.setVisible(true);
			txtArea.setEditable(false);
			
			StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw);
		    ex.printStackTrace(pw);
		    txtArea.append(sw.toString());
			/*ex.printStackTrace()
			
			
			StackTraceElement[] stackTraceElements = ex.getStackTrace();
			for (int i = 0; i < stackTraceElements.length; i++) {
				
				txtArea.append(stackTraceElements[i].toString() + "\n");
			}*/
		}

	}

	public void init(JInternalFrameTesterGral internalframe, int buttons,
			int icon, String message) {
		if(message.length()>180)
			message=message.substring(0,180);
		this.internalframe = internalframe;
		initComponents();

		lbIcon.setText(null);

		if (JOptionPane.INFORMATION_MESSAGE == icon) {
			Util.setIcon(lbIcon, Constantes.IMG_DIALOG_INFO);
		} else if (JOptionPane.ERROR_MESSAGE == icon) {
			Util.setIcon(lbIcon, Constantes.IMG_DIALOG_ERROR);
		} else if (JOptionPane.QUESTION_MESSAGE == icon) {
			Util.setIcon(lbIcon, Constantes.IMG_DIALOG_QUESTION);
		}

		lbMessage.setText(message);
		if (JOptionPane.YES_NO_OPTION == buttons) {
			btnAceptar.setVisible(true);
			btnCancelar.setVisible(true);
		} else if (JOptionPane.NO_OPTION == buttons) {
			btnAceptar.setVisible(true);
			btnCancelar.setVisible(false);
		}
	}

	public int getSelOpt() {
		return selOpt;
	}

	public void setSelOpt(int selOpt) {
		this.selOpt = selOpt;
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		lbIcon = new javax.swing.JLabel();
		lbMessage = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		btnCancelar = new ButtonCancelarConTexto();
		btnAceptar = new ButtonAceptarConTexto();
		panelDetalleError = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		txtArea = new javax.swing.JTextArea();
		btnAceptar1 = new ButtonAceptarConTexto();

		lbIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbIcon.setText("jLabel1");

		lbMessage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lbMessage.setText("jLabel2");
		lbMessage.setMaximumSize(new java.awt.Dimension(1000, 16));

		btnCancelar.setText("Cancelar");
		btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarActionPerformed(evt);
			}
		});

		btnAceptar.setText("Cancelar");
		btnAceptar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAceptarActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap(649, Short.MAX_VALUE)
										.addComponent(
												btnAceptar,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												80,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btnCancelar,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												80,
												javax.swing.GroupLayout.PREFERRED_SIZE)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																btnCancelar,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnAceptar,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE))));

		((ButtonCancelarConTexto) btnCancelar).init();
		((ButtonAceptarConTexto) btnAceptar).init();

		panelDetalleError.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Detalle del error:"));

		txtArea.setColumns(20);
		txtArea.setRows(5);
		jScrollPane1.setViewportView(txtArea);

		btnAceptar1.setText("Copiar");
		btnAceptar1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAceptar1ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout panelDetalleErrorLayout = new javax.swing.GroupLayout(
				panelDetalleError);
		panelDetalleError.setLayout(panelDetalleErrorLayout);
		panelDetalleErrorLayout
				.setHorizontalGroup(panelDetalleErrorLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								panelDetalleErrorLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												btnAceptar1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												80,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(
								panelDetalleErrorLayout
										.createSequentialGroup()
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												792,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		panelDetalleErrorLayout
				.setVerticalGroup(panelDetalleErrorLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								panelDetalleErrorLayout
										.createSequentialGroup()
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												188, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btnAceptar1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												20,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		((ButtonAceptarConTexto) btnAceptar).init();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addComponent(
												lbIcon,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												76,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												lbMessage,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												728, Short.MAX_VALUE))
						.addComponent(jPanel1,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addComponent(
								panelDetalleError,
								javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE));
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
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(
												panelDetalleError,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)));
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnAceptar1ActionPerformed(java.awt.event.ActionEvent evt) {
		StringSelection stringSelection = new StringSelection(txtArea.getText());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, this);
	}

	private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
		selOpt = JOptionPane.NO_OPTION;
		internalframe.close();
	}

	private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {
		selOpt = JOptionPane.YES_OPTION;
		internalframe.close();
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnAceptar;
	private javax.swing.JButton btnAceptar1;
	private javax.swing.JButton btnCancelar;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lbIcon;
	private javax.swing.JLabel lbMessage;
	private javax.swing.JPanel panelDetalleError;
	private javax.swing.JTextArea txtArea;

	// End of variables declaration//GEN-END:variables

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {

	}

}