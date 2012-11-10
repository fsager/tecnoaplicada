/*
 * PanelCambiarClave.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles;

import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.UsuarioCommon;
import testerGeneral.service.UsuarioDefinition;
import frontend.buttons.ButtonAceptar;
import frontend.components.JOptionPaneTesterGral;
import frontend.utils.Util;
import frontend.utils.VentanasUtilTesterGral;

/**
 *
 * @author  __USER__
 */
public class PanelCambiarClave extends javax.swing.JPanel {

	private static final Log log = LogFactory.getLog(PanelCambiarClave.class);

	/** Creates new form PanelCambiarClave */
	public PanelCambiarClave(UsuarioCommon usr) {
		initComponents();
		this.usr = usr;
		Util.mostrarError(lbError, null, true);
		txtClaveAnt.requestFocus();
		lbMensaje.setVisible(false);
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		txtClave2 = new javax.swing.JPasswordField();
		txtClave = new javax.swing.JPasswordField();
		btnAceptar = new ButtonAceptar();
		txtClaveAnt = new javax.swing.JPasswordField();
		lbError = new javax.swing.JLabel();
		lbMensaje = new javax.swing.JLabel();

		setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.PANEL_TITLE_CAMBIAR_CLAVE,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));

		jLabel1.setText(Constantes.LB_CLAVE_ANTERIOR);

		jLabel2.setText(Constantes.LB_CLAVE_NUEVA);

		jLabel5.setText(Constantes.LB_CLAVE_NUEVA_RE);

		btnAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnAceptarMouseClicked(evt);
			}
		});
		btnAceptar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAceptarActionPerformed(evt);
			}
		});

		lbError.setForeground(new java.awt.Color(204, 0, 0));
		lbError.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_ERROR)));

		lbMensaje.setForeground(new java.awt.Color(204, 0, 0));
		lbMensaje.setText(Constantes.PANEL_CAMBIAR_CLAVE_MSG);

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
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																lbMensaje,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																357,
																Short.MAX_VALUE)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								layout
																										.createSequentialGroup()
																										.addComponent(
																												lbError,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												183,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(
																												45,
																												45,
																												45)
																										.addComponent(
																												btnAceptar,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												48,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								layout
																										.createSequentialGroup()
																										.addGroup(
																												layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jLabel1)
																														.addComponent(
																																jLabel2)
																														.addComponent(
																																jLabel5))
																										.addGap(
																												15,
																												15,
																												15)
																										.addGroup(
																												layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING,
																																false)
																														.addComponent(
																																txtClaveAnt)
																														.addComponent(
																																txtClave2)
																														.addComponent(
																																txtClave,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																191,
																																javax.swing.GroupLayout.PREFERRED_SIZE))))
																		.addContainerGap(
																				81,
																				Short.MAX_VALUE)))));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addComponent(
												lbMensaje,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												90,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																txtClaveAnt,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																txtClave,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel5)
														.addComponent(
																txtClave2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																btnAceptar,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																48,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lbError,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																24,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		((ButtonAceptar) btnAceptar).init();
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {
		aceptar();
	}

	private void btnAceptarMouseClicked(java.awt.event.MouseEvent evt) {
		//aceptar();
	}

	public void aceptar() {
		try {

			log.info("btnAceptarActionPerformed");
			Util.mostrarError(lbError, null, true);

			UsuarioDefinition usuarioService = (UsuarioDefinition) ContextManager
					.getBizObject("usuarioService");
			UsuarioCommon usu = usuarioService.getUsrName(Util.USUARIO, usr
					.getClass());

			if (usu != null
					&& usu.getUsrHabilitadoSn().equals("S")
					&& usu.getUsrClave().compareTo(
							new String(txtClaveAnt.getPassword())) == 0) {
				if (validarUsuario()) {
					usu.setUsrClave(new String(txtClave.getPassword()));
					usu.setUsrUltimoAcceso(new Date());
					usuarioService.update(usu);

					testerGeneral.persistence.impl.Util
							.insertAudit(
									testerGeneral.persistence.impl.Util.ACTION_CAMBIO_CLAVE,
									null, null);

					if (padre != null) {
						padre.dispose();
						Util.abrirFramePrincipal();
					} else {
						txtClaveAnt.setText("");
						txtClave.setText("");
						txtClave2.setText("");
						Util.mostrarError(lbError, null, true);
						JOptionPaneTesterGral.showInternalMessageDialog(
								Constantes.MENSAJE_GUARDADO,
								Constantes.MENSAJE_GUARDADO_TIT,
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			} else {
				Util.mostrarError(lbError, Constantes.ERROR_USR_LOGIN, false);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean validarUsuario() {
		log.info("validarUsuario");
		
		Util.mostrarError(lbError,null, true);
		
		String clave = new String(txtClave.getPassword());
		String clave2 = new String(txtClave2.getPassword());
		if (clave == null || clave.equals("")) {
			Util.mostrarError(lbError, Constantes.ERROR_USR_CLAVE, false);
			return false;
		}
		if (clave.compareTo(clave2) != 0) {
			Util.mostrarError(lbError, Constantes.ERROR_USR_CLAVE2, false);
			return false;
		}

		return true;
	}

	public javax.swing.JInternalFrame getPadre() {
		return padre;
	}

	public void setPadre(javax.swing.JInternalFrame padre) {
		lbMensaje.setVisible(true);
		this.padre = padre;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnAceptar;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel lbError;
	private javax.swing.JLabel lbMensaje;
	private javax.swing.JPasswordField txtClave;
	private javax.swing.JPasswordField txtClave2;
	private javax.swing.JPasswordField txtClaveAnt;
	// End of variables declaration//GEN-END:variables
	private javax.swing.JInternalFrame padre;
	private UsuarioCommon usr;
}