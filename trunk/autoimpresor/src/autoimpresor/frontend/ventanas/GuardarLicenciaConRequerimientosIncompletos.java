/*
 * InicioSession.java
 *
 * Created on __DATE__, __TIME__
 */

package autoimpresor.frontend.ventanas;

import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import autoimpresor.domain.Licencia;
import autoimpresor.domain.Usuario;
import autoimpresor.service.LicenciaDefinition;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.UsuarioCommon;
import testerGeneral.service.UsuarioDefinition;
import frontend.buttons.ButtonAceptar;
import frontend.buttons.ButtonCancelar;
import frontend.components.JOptionPaneTesterGral;
import frontend.paneles.PanelMenu;
import frontend.utils.Util;
import frontend.ventanas.JInternalFrameTesterGral;
import frontend.ventanas.VentanaCambiarClave;

/**
 *
 * @author  __USER__
 */
public class GuardarLicenciaConRequerimientosIncompletos extends
		JInternalFrameTesterGral {

	private static final Log log = LogFactory
			.getLog(GuardarLicenciaConRequerimientosIncompletos.class);
	private UsuarioDefinition usuarioService = (UsuarioDefinition) ContextManager
			.getBizObject("usuarioService");
	private LicenciaDefinition licenciaService = (LicenciaDefinition) ContextManager.getBizObject("licenciaService");

	private Licencia licenciaRecibida = new Licencia();
	private JInternalFrameTesterGral parent;
	
	public GuardarLicenciaConRequerimientosIncompletos(Class menu, Usuario usr,
			String requisitosIncumplidos, Licencia licencia,JInternalFrameTesterGral parent) {
		super("Requisitos Incompletos", false, false, false, false);
		this.parent=parent;
		this.licenciaRecibida = licencia;
		this.menu = menu;
		this.usr = usr;
		
		try {
			initComponents();
			cargarUsuarios();
			Util.mostrarError(lbError, null, true);
			ptxtClave.requestFocus();
			jTextAreaRequisitosIncompletos.setText(requisitosIncumplidos);
			setIconifiable(false);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextAreaRequisitosIncompletos = new javax.swing.JTextArea();
		jLabel3 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		btnCancelar = new ButtonCancelar();
		btnAceptar = new ButtonAceptar();
		ptxtClave = new javax.swing.JPasswordField();
		cbUsuarios = new javax.swing.JComboBox();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		lbError = new javax.swing.JLabel();

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

		jPanel1
				.setBorder(javax.swing.BorderFactory
						.createTitledBorder("No se cumple con los siguientes requisitos"));

		jTextAreaRequisitosIncompletos.setColumns(20);
		jTextAreaRequisitosIncompletos.setEditable(false);
		jTextAreaRequisitosIncompletos.setRows(6);
		jScrollPane1.setViewportView(jTextAreaRequisitosIncompletos);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 383,
				Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addComponent(
						jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		jLabel3
				.setText("Si desea continuar, debe ser bajo la supervisi\u00f3n de un Usuario Autorizado");

		jPanel2.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Usuario autorizado"));

		btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarActionPerformed(evt);
			}
		});

		btnAceptar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAceptarActionPerformed(evt);
			}
		});

		ptxtClave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ptxtClaveActionPerformed(evt);
			}
		});

		cbUsuarios.setMaximumSize(new java.awt.Dimension(0, 0));
		cbUsuarios.setPreferredSize(new java.awt.Dimension(0, 0));

		jLabel1.setText(Constantes.LB_USUARIO);

		jLabel2.setText(Constantes.LB_CLAVE);

		lbError.setForeground(new java.awt.Color(204, 0, 0));
		lbError.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_ERROR)));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addComponent(
																lbError,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																jPanel2Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel2)
																						.addComponent(
																								jLabel1))
																		.addGap(
																				37,
																				37,
																				37)
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								ptxtClave,
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel2Layout
																										.createSequentialGroup()
																										.addComponent(
																												btnAceptar)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												btnCancelar,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												48,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								cbUsuarios,
																								javax.swing.GroupLayout.Alignment.LEADING,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								252,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addGap(12, 12, 12)));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel2Layout
										.createSequentialGroup()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																cbUsuarios,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																22,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
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
												jPanel2Layout
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
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		((ButtonCancelar) btnCancelar).init();
		((ButtonAceptar) btnAceptar).init();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addGroup(
						layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								false).addComponent(jPanel2,
								javax.swing.GroupLayout.Alignment.LEADING, 0,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addComponent(jPanel1,
								javax.swing.GroupLayout.Alignment.LEADING,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addComponent(jLabel3,
								javax.swing.GroupLayout.Alignment.LEADING,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)).addContainerGap()));
		layout
				.setVerticalGroup(layout
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
										.addGap(18, 18, 18)
										.addComponent(
												jLabel3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												16,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel2,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addContainerGap()));

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
		this.close();
		super.close();
	}

	private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {
		
		login();
	}

	void login() {
		try {
			Util.mostrarError(lbError, null, true);

			Usuario usu = (Usuario) cbUsuarios.getSelectedItem();

			if (validarLogin(usu)) {

					licenciaRecibida.setLicRequisitosSn("N");
					licenciaRecibida.setUsuarioByUsrNombreResponsable(usu);
					licenciaService.insert(licenciaRecibida);
					
					this.close();
					
					JOptionPaneTesterGral.showInternalMessageDialog(parent,
							"Los cambios se guardaron correctamente",
							Constantes.MENSAJE_GUARDADO_TIT,
							JOptionPane.INFORMATION_MESSAGE);
					
					parent.close();
					
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean validarLogin(Usuario usu) {
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
		usr.setUsrAutorizadoSn("S");

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
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextAreaRequisitosIncompletos;
	private javax.swing.JLabel lbError;
	private javax.swing.JPasswordField ptxtClave;
	// End of variables declaration//GEN-END:variables
	private Class menu;
	private Usuario usr;
}