/*
 * InicioSession.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.ventanas;

import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.UsuarioCommon;
import testerGeneral.service.UsuarioDefinition;
import frontend.buttons.ButtonAceptar;
import frontend.buttons.ButtonCancelar;
import frontend.paneles.PanelMenu;
import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class InicioSession extends JInternalFrameTesterGral {

	private static final Log log = LogFactory.getLog(InicioSession.class);
	private UsuarioDefinition usuarioService = (UsuarioDefinition) ContextManager
			.getBizObject("usuarioService");

	public InicioSession(Class menu,UsuarioCommon usr) {
		super(Constantes.VTN_TITLE_INICIO_SESION, false, false, false, false);
		this.menu=menu;
		this.usr=usr;
		
		try {
			initComponents();
			cargarUsuarios();
			Util.mostrarError(lbError, null, true);
			ptxtClave.requestFocus();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		ptxtClave = new javax.swing.JPasswordField();
		btnAceptar = new ButtonAceptar();
		btnCancelar = new ButtonCancelar();
		lbError = new javax.swing.JLabel();
		cbUsuarios = new javax.swing.JComboBox();

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		setIconifiable(true);
		addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
			public void internalFrameActivated(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameClosed(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameClosing(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameDeactivated(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameDeiconified(
					javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameIconified(
					javax.swing.event.InternalFrameEvent evt) {
				formInternalFrameIconified(evt);
			}

			public void internalFrameOpened(
					javax.swing.event.InternalFrameEvent evt) {
			}
		});

		jLabel1.setText(Constantes.LB_USUARIO);

		jLabel2.setText(Constantes.LB_CLAVE);

		ptxtClave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ptxtClaveActionPerformed(evt);
			}
		});

		btnAceptar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAceptarActionPerformed(evt);
			}
		});

		btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarActionPerformed(evt);
			}
		});

		lbError.setForeground(new java.awt.Color(204, 0, 0));
		lbError.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_ERROR)));

		cbUsuarios.setMaximumSize(new java.awt.Dimension(0, 0));
		cbUsuarios.setPreferredSize(new java.awt.Dimension(0, 0));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
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
														.addComponent(jLabel2)
														.addComponent(jLabel1))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																ptxtClave,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																136,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																layout
																		.createSequentialGroup()
																		.addComponent(
																				btnAceptar,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				48,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnCancelar,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				48,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																cbUsuarios, 0,
																136,
																Short.MAX_VALUE))
										.addContainerGap()).addComponent(
								lbError, javax.swing.GroupLayout.DEFAULT_SIZE,
								235, Short.MAX_VALUE));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																cbUsuarios,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																22,
																Short.MAX_VALUE)
														.addComponent(jLabel1))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																ptxtClave,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																btnCancelar,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																48,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnAceptar,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																48,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												lbError,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												24,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		((ButtonAceptar) btnAceptar).init();
		((ButtonCancelar) btnCancelar).init();

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void formInternalFrameIconified(
			javax.swing.event.InternalFrameEvent evt) {
		Util.minimizar(this);
	}

	private void ptxtClaveActionPerformed(java.awt.event.ActionEvent evt) {
		login();
	}

	private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
		testerGeneral.persistence.impl.Util.insertAudit(testerGeneral.persistence.impl.Util.ACTION_SALIR,null,null);
		System.exit(0);
	}

	private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {
		login();
	}

	void login() {
		try {
			Util.mostrarError(lbError, null, true);

			UsuarioCommon usu = (UsuarioCommon) cbUsuarios.getSelectedItem();

			if (validarLogin(usu)) {

				Util.USUARIO = usu.getUsrNombre();
				Util.usuarioCommon=usu;

				testerGeneral.persistence.impl.Util.insertAudit(
						testerGeneral.persistence.impl.Util.ACTION_LOGIN,
						null, null);
				Util.panelMenu=(PanelMenu)menu.newInstance();
				if (usu.getUsrUltimoAcceso() == null)
					cambiarClave();
				else {
					usu.setUsrUltimoAcceso(new Date());
					usuarioService.update(usu);
					this.dispose();
					Util.abrirFramePrincipal();
				}
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean validarLogin(UsuarioCommon usu) {
		if (usu == null) {
			Util.mostrarError(lbError, Constantes.ERROR_USR_LOGIN, false);
			return false;
		} else if (usu.getUsrClave().compareTo(
				new String(ptxtClave.getPassword())) != 0) {
			Util.mostrarError(lbError, Constantes.ERROR_USR_LOGIN, false);
			return false;
		} else if (usu.getUsrHabilitadoSn().equals("N")) {
			Util.mostrarError(lbError,
					Constantes.ERROR_USR_LOGIN_NO_HABILITADO, false);
			return false;
		}

		return true;
	}

	private void cambiarClave() {
		this.dispose();

		VentanaCambiarClave ventanaCambiarClave = new VentanaCambiarClave(usr);
		Util.agregarIframe(ventanaCambiarClave);
		ventanaCambiarClave.setVisible(true);
	}

	private void cargarUsuarios() throws Exception {
		
		usr.setDeletedSn(null);
		usr.setUsrHabilitadoSn("S");

		List<UsuarioCommon> usuarios = usuarioService.getAll(usr);

		for (int i = 0; i < usuarios.size(); i++) {
			cbUsuarios.addItem(usuarios.get(i));
		}
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnAceptar;
	private javax.swing.JButton btnCancelar;
	private javax.swing.JComboBox cbUsuarios;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel lbError;
	private javax.swing.JPasswordField ptxtClave;
	// End of variables declaration//GEN-END:variables
	private Class menu;
	private UsuarioCommon usr;
}