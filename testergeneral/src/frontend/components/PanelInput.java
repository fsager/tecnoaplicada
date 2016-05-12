/*
 * PanelMensajes.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.components;

import java.awt.Dimension;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;

import testerGeneral.domain.Constantes;
import frontend.buttons.ButtonAceptarConTexto;
import frontend.buttons.ButtonCancelarConTexto;
import frontend.utils.Util;
import frontend.ventanas.JInternalFrameTesterGral;

/**
 *
 * @author  __USER__
 */
public class PanelInput extends javax.swing.JPanel implements ClipboardOwner {

	private int selOpt = -1;
	private JInternalFrameTesterGral internalframe;

	/** Creates new form PanelMensajes */
	public PanelInput(JInternalFrameTesterGral internalframe, int buttons,
			int icon, String message) {
		
		init(internalframe, buttons, icon, message,true);

		Dimension dim = new Dimension(350, 120);
		this.setMinimumSize(dim);
		this.setPreferredSize(dim);
	}
	
	

	public void init(JInternalFrameTesterGral internalframe, int buttons,
			int icon, String message,boolean truncMessage) {
		if (message.length() > 180 && truncMessage)
			message = message.substring(0, 180);
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
		
		
		lbError.setBackground(new java.awt.Color(204, 0, 0));
		lbError.setForeground(new java.awt.Color(204, 0, 0));
		lbError.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_ERROR)));
		
		Util.mostrarError(lbError, "", true);
		
		NumberFormat format = NumberFormat.getPercentInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setMaximum(1.0f);
        formatter.setMinimum(0.0f);
        formatter.setAllowsInvalid(true);
        formatter.setOverwriteMode(true);
        formatter.setCommitsOnValidEdit(false);
		
        //txtResultado= new JFormattedTextField(formatter);
	    
        
//        txtResultado.setColumns(3);
//        txtResultado.setValue(0f);
        
        SpinnerNumberModel model=new SpinnerNumberModel();
        model.setStepSize(5);
        txtResultado= new JSpinner(model);
        txtResultado.setValue(100);
	        
		jPanel1 = new javax.swing.JPanel();
		btnCancelar = new ButtonCancelarConTexto();
		btnAceptar = new ButtonAceptarConTexto();


		lbIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbIcon.setText("jLabel1");

		lbMessage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lbMessage.setText("jLabel2");
		lbMessage.setMaximumSize(new java.awt.Dimension(100, 16));

		txtResultado.setMaximumSize(new java.awt.Dimension(100, 16));
		
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
												lbError,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												165,
												javax.swing.GroupLayout.PREFERRED_SIZE)
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
																		lbError,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		20,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
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
												50, Short.MAX_VALUE)
										.addComponent(
												txtResultado,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												50, Short.MAX_VALUE)
												)
						.addComponent(jPanel1,
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
																77,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lbMessage,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																82,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																		txtResultado,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		25,
																		javax.swing.GroupLayout.PREFERRED_SIZE)

																
																)														
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)));
	}// </editor-fold>
	//GEN-END:initComponents



	private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
		selOpt = JOptionPane.NO_OPTION;
		internalframe.close();
	}

	private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {
		selOpt = JOptionPane.YES_OPTION;
		Util.mostrarError(lbError, "", true);
		
		Integer value=(Integer)txtResultado.getValue();
		if(value<0 || value>100){
			Util.mostrarError(lbError, "Resultado incorrecto.", false);
		}
		else{
			resultado=value;
			internalframe.close();
		}
		
	}
	
	public Integer getResultado() {
		return resultado;
	}
	
	public void setResultado(Integer resultado) {
		this.resultado = resultado;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnAceptar;
	private javax.swing.JButton btnCancelar;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JLabel lbIcon;
	private javax.swing.JLabel lbMessage;
	private javax.swing.JLabel lbError = new javax.swing.JLabel();
	//private javax.swing.JFormattedTextField txtResultado;
	private javax.swing.JSpinner txtResultado;
	private Integer resultado;
	
	
	


	// End of variables declaration//GEN-END:variables

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {

	}

}