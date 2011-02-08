/*
 * PanelMenuPrincipal.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.actualizaciones.GestorActualizaciones;
import testerGeneral.business.ContextManager;
import testerGeneral.db.ConexionManagerTesterGeneral;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.PersonaExamen;
import testerGeneral.domain.Usuario;
import examenes.util.ExamenesUtils;
import frontend.components.JOptionPaneTesterGral;
import frontend.utils.Util;
import frontend.ventanas.FramePrincipal;

/**
 *
 * @author  __USER__
 */
public class PanelMenuPrincipal extends PanelMenu {

	private static final Log log = LogFactory.getLog(PanelMenuPrincipal.class);

	/** Creates new form PanelMenuPrincipal */
	public PanelMenuPrincipal() {
		initComponents();
		if (!((Usuario) Util.usuarioCommon).hasAccesoTareasAdmPermition()) {
			btnAdmGral.setVisible(false);
			jSeparator4.setVisible(false);
		}
		agregarEscuchas();

	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		toolbarPrincipal = new javax.swing.JToolBar();
		btnPersonas = new javax.swing.JToggleButton();
		jSeparator1 = new javax.swing.JToolBar.Separator();
		btnUsuarios = new javax.swing.JToggleButton();
		jSeparator3 = new javax.swing.JToolBar.Separator();
		btnAdmGral = new javax.swing.JToggleButton();
		jSeparator4 = new javax.swing.JToolBar.Separator();
		btnCerrarSession = new javax.swing.JToggleButton();
		jSeparator7 = new javax.swing.JToolBar.Separator();
		btnSalir = new javax.swing.JToggleButton();
		jSeparator5 = new javax.swing.JSeparator();
		panelSubMenu = new javax.swing.JPanel();
		toolbarSubNivel = new javax.swing.JToolBar();
		jSeparator6 = new javax.swing.JSeparator();
		jLabel1 = new javax.swing.JLabel();

		toolbarPrincipal.setFloatable(false);
		toolbarPrincipal.setRollover(true);
		toolbarPrincipal.setMaximumSize(new java.awt.Dimension(0, 0));
		toolbarPrincipal.setOpaque(false);

		btnPersonas.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_MENU_PERSONAS)));
		btnPersonas.setMnemonic('P');
		btnPersonas.setText(Constantes.MENU_PERSONAS);
		btnPersonas.setFocusable(false);
		btnPersonas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		btnPersonas.setMargin(null);
		btnPersonas.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPersonasActionPerformed(evt);
			}
		});
		toolbarPrincipal.add(btnPersonas);
		toolbarPrincipal.add(jSeparator1);

		btnUsuarios.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_MENU_USUARIOS)));
		btnUsuarios.setMnemonic('U');
		btnUsuarios.setText(Constantes.MENU_USUARIOS);
		btnUsuarios.setFocusable(false);
		btnUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		btnUsuarios.setMargin(null);
		btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnUsuariosActionPerformed(evt);
			}
		});
		toolbarPrincipal.add(btnUsuarios);
		toolbarPrincipal.add(jSeparator3);

		btnAdmGral.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_MENU_TAREAS_ADMISTATIVAS)));
		btnAdmGral.setMnemonic('T');
		btnAdmGral.setText(Constantes.MENU_TAREAS_ADMISTATIVAS);
		btnAdmGral.setFocusable(false);
		btnAdmGral.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		btnAdmGral.setMargin(null);
		btnAdmGral.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAdmGralActionPerformed(evt);
			}
		});
		toolbarPrincipal.add(btnAdmGral);
		toolbarPrincipal.add(jSeparator4);

		btnCerrarSession.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_MENU_CERRAR_SESSION)));
		btnCerrarSession.setMnemonic('C');
		btnCerrarSession.setText(Constantes.MENU_SUB_CERRAR_SESION);
		btnCerrarSession.setFocusable(false);
		btnCerrarSession
				.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		btnCerrarSession.setMargin(null);
		toolbarPrincipal.add(btnCerrarSession);
		toolbarPrincipal.add(jSeparator7);

		btnSalir.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_MENU_SALIR)));
		btnSalir.setMnemonic('S');
		btnSalir.setText(Constantes.MENU_SALIR);
		btnSalir.setFocusable(false);
		btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		btnSalir.setMargin(null);
		btnSalir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSalirActionPerformed(evt);
			}
		});
		toolbarPrincipal.add(btnSalir);

		toolbarSubNivel.setFloatable(false);
		toolbarSubNivel.setRollover(true);
		toolbarSubNivel.setOpaque(false);

		javax.swing.GroupLayout panelSubMenuLayout = new javax.swing.GroupLayout(
				panelSubMenu);
		panelSubMenu.setLayout(panelSubMenuLayout);
		panelSubMenuLayout
				.setHorizontalGroup(panelSubMenuLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 1006, Short.MAX_VALUE)
						.addGroup(
								panelSubMenuLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												panelSubMenuLayout
														.createSequentialGroup()
														.addGap(0, 0, 0)
														.addComponent(
																toolbarSubNivel,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																1006,
																Short.MAX_VALUE)
														.addGap(0, 0, 0))));
		panelSubMenuLayout
				.setVerticalGroup(panelSubMenuLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 33, Short.MAX_VALUE)
						.addGroup(
								panelSubMenuLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												panelSubMenuLayout
														.createSequentialGroup()
														.addGap(0, 0, 0)
														.addComponent(
																toolbarSubNivel,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																33,
																Short.MAX_VALUE)
														.addGap(0, 0, 0))));

		jSeparator6.setMinimumSize(null);
		jSeparator6.setPreferredSize(null);

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel1.setText(Constantes.LB_USUARIO + " " + Util.USUARIO + "   ");
		jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
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
																javax.swing.GroupLayout.Alignment.TRAILING,
																layout
																		.createSequentialGroup()
																		.addComponent(
																				toolbarPrincipal,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				391,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jLabel1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				603,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jSeparator6,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																1006,
																Short.MAX_VALUE)
														.addComponent(
																panelSubMenu,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jSeparator5,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																1006,
																Short.MAX_VALUE))
										.addGap(0, 0, 0)));
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
														.addComponent(
																jLabel1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																25,
																Short.MAX_VALUE)
														.addComponent(
																toolbarPrincipal,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGap(0, 0, 0)
										.addComponent(
												jSeparator5,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												4,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, 0)
										.addComponent(
												panelSubMenu,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, 0)
										.addComponent(
												jSeparator6,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)));
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnPersonasActionPerformed(java.awt.event.ActionEvent evt) {
		cargarSubMenuPersona();
		seleccionarPersona();
	}

	private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	public void doBeforLoadMenu(JToolBar toolbar, JToggleButton button) {

		((FramePrincipal)Util.framePrincipal).getJButtonActualizarSistema().setVisible(GestorActualizaciones.getSeEncontroActualizacion());

		/*Selecciona del menu principal SOLO el btnPersonas*/
		unSelectButtons(toolbar, button);

		/*Elimina todas las opciones del sub menu*/
		toolbarSubNivel.removeAll();
	}

	public void doAfterLoadMenu() {
		this.validate();
		this.update(getGraphics());
	}

	public void doAfterLoadMenuContenido() {
		panelContenido.validate();
		Util.framePrincipal.pack();
		this.validate();
		Util.centrarIframes(Util.framePrincipal);
	}

	public void unSelectButtons(JToolBar toolbar, JToggleButton btnSource) {
		Component[] cmps = toolbar.getComponents();
		for (int i = 0; i < cmps.length; i++) {
			if (cmps[i].getClass().equals(JToggleButton.class)) {
				JToggleButton toggleBtn = (JToggleButton) cmps[i];
				if (!btnSource.equals(toggleBtn))
					toggleBtn.setSelected(false);
				else
					toggleBtn.setSelected(true);
			}
		}
	}

	public javax.swing.JToolBar getToolbarSubNivel() {
		return toolbarSubNivel;
	}

	public void setToolbarSubNivel(javax.swing.JToolBar toolbarSubNivel) {
		this.toolbarSubNivel = toolbarSubNivel;
	}

	/*****************************/
	public void agregarEscuchas() {

		log.debug("ini agregarEscuchas");
		/*btnRegistrarPersonas
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						seleccionarRegistrarPersona();
					}
				});

		btnPersonas.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				seleccionarPersona();
			}
		});*/

		btnCambiarClave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				seleccionarCambiarClave();
			}
		});

		btnAdmistracionUsuarios
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						seleccionarUsuario();
					}
				});

		/*btnExamenVision.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				seleccionarExamenVision();
			}
		});

		btnExamenEquilibrio
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						seleccionarExamenEquilibrio();
					}
				});*/

		btnPanelControl.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				seleccionarPanelControl();
			}
		});

		btnInformesEstadistica
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						seleccionarInformesEstadisticas();
					}
				});

		btnLogEventos.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				seleccionarLogEventos();
			}
		});

		btnConfigurarDB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				seleccionarConfigurarDB();
			}
		});

		btnCerrarSession.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				seleccionarCerrarSesion();
				doAfterCerrarySalir(btnCerrarSession);
			}
		});

		btnSalir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				cerrar();
				doAfterCerrarySalir(btnSalir);
			}
		});

		log.debug("fin agregarEscuchas");
	}

	private void doAfterCerrarySalir(JToggleButton boton) {
		boton.setSelected(false);
		boton.setRolloverEnabled(false);
	}

	public void cargarSubMenuPersona() {
		doBeforLoadMenu(toolbarPrincipal, btnPersonas);

		/*Muestro y agrego al menu*/
		btnBuscarPersona.setVisible(true);
		toolbarSubNivel.add(btnBuscarPersona);

		btnNuevaPersona.setVisible(true);
		btnNuevaPersona.setEnabled(false);
		toolbarSubNivel.add(btnNuevaPersona);

		btnModificarPersona.setVisible(true);
		btnModificarPersona.setEnabled(false);
		toolbarSubNivel.add(btnModificarPersona);

		btnEliminarPersona.setVisible(true);
		btnEliminarPersona.setEnabled(false);
		toolbarSubNivel.add(btnEliminarPersona);

		btnRealizarExamenPersona.setVisible(true);
		btnRealizarExamenPersona.setEnabled(false);
		toolbarSubNivel.add(btnRealizarExamenPersona);

		btnVerExamenPersona.setVisible(true);
		btnVerExamenPersona.setEnabled(false);
		toolbarSubNivel.add(btnVerExamenPersona);

		/*Refresco para que se visualice correctamente*/
		doAfterLoadMenu();
	}

	public void seleccionarPersona() {

		testerGeneral.persistence.impl.Util.insertAudit(
				testerGeneral.persistence.impl.Util.ACTION_MENU_SUB_PERSONA,
				null, null);

		unSelectButtons(toolbarSubNivel, btnBuscarPersona);
		panelContenido.removeAll();

		PanelPersona panelPersona = new PanelPersona(this);
		panelPersona.validate();

		panelPersona.setVisible(true);
		panelContenido.add(panelPersona);

		doAfterLoadMenuContenido();
	}

	/*public void seleccionarRegistrarPersona() {

		testerGeneral.persistence.impl.Util.insertAudit(testerGeneral.persistence.impl.Util.ACTION_MENU_REGISTRA_PERSONA,null,null);
		
		unSelectButtons(toolbarSubNivel, btnRegistrarPersonas);
		panelContenido.removeAll();

		PanelRegistrarPersona panelRegistrarPersona = new PanelRegistrarPersona();
		panelRegistrarPersona.validate();

		panelRegistrarPersona.setVisible(true);
		panelContenido.add(panelRegistrarPersona);

		doAfterLoadMenuContenido();
	}*/

	/*****************************/

	public javax.swing.JToggleButton getBtnBuscarPersona() {
		return btnBuscarPersona;
	}

	public void setBtnBuscarPersona(javax.swing.JToggleButton btnBuscarPersona) {
		this.btnBuscarPersona = btnBuscarPersona;
	}

	public javax.swing.JToggleButton getBtnNuevaPersona() {
		return btnNuevaPersona;
	}

	public void setBtnNuevaPersona(javax.swing.JToggleButton btnNuevaPersona) {
		this.btnNuevaPersona = btnNuevaPersona;
	}

	public javax.swing.JToggleButton getBtnModificarPersona() {
		return btnModificarPersona;
	}

	public void setBtnModificarPersona(
			javax.swing.JToggleButton btnModificarPersona) {
		this.btnModificarPersona = btnModificarPersona;
	}

	public javax.swing.JToggleButton getBtnEliminarPersona() {
		return btnEliminarPersona;
	}

	public void setBtnEliminarPersona(
			javax.swing.JToggleButton btnEliminarPersona) {
		this.btnEliminarPersona = btnEliminarPersona;
	}

	public javax.swing.JToggleButton getBtnRealizarExamenPersona() {
		return btnRealizarExamenPersona;
	}

	public void setBtnRealizarExamenPersona(
			javax.swing.JToggleButton btnRealizarExamenPersona) {
		this.btnRealizarExamenPersona = btnRealizarExamenPersona;
	}

	public javax.swing.JToggleButton getBtnVerExamenPersona() {
		return btnVerExamenPersona;
	}

	public void setBtnVerExamenPersona(
			javax.swing.JToggleButton btnVerExamenPersona) {
		this.btnVerExamenPersona = btnVerExamenPersona;
	}

	/*****************************/
	private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {
		cargarSubMenuUsuarios();
		seleccionarCambiarClave();
	}

	public void cargarSubMenuUsuarios() {
		doBeforLoadMenu(toolbarPrincipal, btnUsuarios);

		btnCambiarClave.setVisible(true);
		toolbarSubNivel.add(btnCambiarClave);

		if (((Usuario) Util.usuarioCommon).hasAbmUsuarioPermition()) {
			btnAdmistracionUsuarios.setVisible(true);
			toolbarSubNivel.add(btnAdmistracionUsuarios);
		}
		

		doAfterLoadMenu();
	}

	public void seleccionarCambiarClave() {
		testerGeneral.persistence.impl.Util
				.insertAudit(
						testerGeneral.persistence.impl.Util.ACTION_MENU_USUARIO_CAMBIAR_CLAVE,
						null, null);

		unSelectButtons(toolbarSubNivel, btnCambiarClave);
		panelContenido.removeAll();

		PanelCambiarClave panelCambiarClave = new PanelCambiarClave(
				new Usuario());
		panelContenido.add(panelCambiarClave);

		doAfterLoadMenuContenido();
	}

	public void seleccionarUsuario() {
		testerGeneral.persistence.impl.Util.insertAudit(
				testerGeneral.persistence.impl.Util.ACTION_MENU_ADM_USUARIOS,
				null, null);

		unSelectButtons(toolbarSubNivel, btnAdmistracionUsuarios);
		panelContenido.removeAll();

		PanelUsuario panelUsuario = new PanelUsuario();
		panelContenido.add(panelUsuario);

		doAfterLoadMenuContenido();
	}

	public void cargarSubMenuExamenes(javax.swing.JToggleButton button) {
		toolbarSubNivel.removeAll();
		
		button.setVisible(true);
		toolbarSubNivel.add(button);

		/*btnExamenVision.setVisible(true);
		toolbarSubNivel.add(btnExamenVision);

		btnExamenEquilibrio.setVisible(true);
		toolbarSubNivel.add(btnExamenEquilibrio);*/

		doAfterLoadMenu();
	}

	public void seleccionarExamenPsicometrico(PersonaExamen personaExamen) {
		panelContenido.removeAll();
		ExamenesUtils.mostrarPanelExamen(personaExamen, panelContenido);
		doAfterLoadMenuContenido();
	}

	public void seleccionarExamenVision(PersonaExamen personaExamen) {
		panelContenido.removeAll();
		ExamenesUtils.mostrarPanelExamen(personaExamen, panelContenido);
		doAfterLoadMenuContenido();
	}

	/*public void seleccionarExamenVision() {
		testerGeneral.persistence.impl.Util.insertAudit(
				testerGeneral.persistence.impl.Util.ACTION_MENU_EXAMEN_VISION,
				null, null);

		unSelectButtons(toolbarSubNivel, btnExamenVision);

		panelContenido.removeAll();

		doAfterLoadMenuContenido();
	}*/

	public void seleccionarExamenEquilibrio() {
		/*testerGeneral.persistence.impl.Util
				.insertAudit(
						testerGeneral.persistence.impl.Util.ACTION_MENU_EXAMEN_EQUILIBRIO,
						null, null);

		unSelectButtons(toolbarSubNivel, btnExamenEquilibrio);
		panelContenido.removeAll();

		doAfterLoadMenuContenido();*/
	}

	/*****************************/

	/*****************************/
	void btnAdmGralActionPerformed(java.awt.event.ActionEvent evt) {
		cargarSubAdmGral();
		seleccionarPanelControl();
	}

	public void cargarSubAdmGral() {
		doBeforLoadMenu(toolbarPrincipal, btnAdmGral);

		/*Muestro y agrego al menu*/
		btnPanelControl.setVisible(true);
		toolbarSubNivel.add(btnPanelControl);

		/*Muestro y agrego al menu*/
		btnInformesEstadistica.setVisible(true);
		toolbarSubNivel.add(btnInformesEstadistica);

		btnLogEventos.setVisible(true);
		toolbarSubNivel.add(btnLogEventos);

		btnConfigurarDB.setVisible(true);
		toolbarSubNivel.add(btnConfigurarDB);

		/*Refresco para que se visualice correctamente*/
		doAfterLoadMenu();
	}

	public void seleccionarPanelControl() {

		testerGeneral.persistence.impl.Util.insertAudit(
				testerGeneral.persistence.impl.Util.ACTION_MENU_PANEL_CONTROL,
				null, null);

		unSelectButtons(toolbarSubNivel, btnPanelControl);
		panelContenido.removeAll();

		PanelPanelDeControl panelPanelDeControl = new PanelPanelDeControl();
		panelContenido.add(panelPanelDeControl);

		doAfterLoadMenuContenido();
	}

	public void seleccionarInformesEstadisticas() {

		testerGeneral.persistence.impl.Util.insertAudit(
				testerGeneral.persistence.impl.Util.ACTION_MENU_INFORMES, null,
				null);

		unSelectButtons(toolbarSubNivel, btnInformesEstadistica);
		panelContenido.removeAll();

		PanelInformesYEstadisticas panelInformesYEstadisticas = new PanelInformesYEstadisticas();
		panelInformesYEstadisticas.validate();

		panelInformesYEstadisticas.setVisible(true);
		panelContenido.add(panelInformesYEstadisticas);

		doAfterLoadMenuContenido();
	}

	public void seleccionarLogEventos() {

		testerGeneral.persistence.impl.Util.insertAudit(
				testerGeneral.persistence.impl.Util.ACTION_MENU_EVENTOS, null,
				null);

		unSelectButtons(toolbarSubNivel, btnLogEventos);
		panelContenido.removeAll();

		PanelLogEventos panelLogEventos = new PanelLogEventos(new Usuario());
		panelLogEventos.setVisible(true);

		panelContenido.add(panelLogEventos);

		doAfterLoadMenuContenido();
	}

	public void seleccionarConfigurarDB() {

		unSelectButtons(toolbarSubNivel, btnConfigurarDB);
		panelContenido.removeAll();

		PanelConfiguracionDB panelConfiguracionDB = new PanelConfiguracionDB(
				ContextManager.getProperty("SISTEMA.NOMBRE.PROGRAMA"), false);
		panelConfiguracionDB.validate();

		panelConfiguracionDB.setVisible(true);
		panelContenido.add(panelConfiguracionDB);

		doAfterLoadMenuContenido();

	}

	public void seleccionarCerrarSesion() {

		int op = JOptionPaneTesterGral.showInternal(
				Constantes.MENSAJE_CERRAR_SESSION,
				Constantes.MENSAJE_CERRAR_SESSION_TIT,
				JOptionPane.QUESTION_MESSAGE);

		if (op == JOptionPane.YES_OPTION) {
			testerGeneral.persistence.impl.Util.insertAudit(
					testerGeneral.persistence.impl.Util.ACTION_CIERRE_SESION,
					null, null);
			Util.framePrincipal.dispose();
			if (Util.thTrama != null)
				Util.thTrama.desconnect();
			Util.abrirInicioSesion(this.getClass(), new Usuario());
		}
	}

	/*****************************/

	public void cerrar() {
		int op = JOptionPaneTesterGral.showInternal(Constantes.MENSAJE_SALIR,
				Constantes.MENSAJE_SALIR_TIT, JOptionPane.QUESTION_MESSAGE);

		if (op == JOptionPane.YES_OPTION) {
			testerGeneral.persistence.impl.Util.insertAudit(
					testerGeneral.persistence.impl.Util.ACTION_SALIR, null,
					null);
			Util.frameContenedor.dispose();
			if (Util.thTrama != null)
				Util.thTrama.desconnect();

			ConexionManagerTesterGeneral datasource = (ConexionManagerTesterGeneral) ContextManager
					.getBizObject("dataSource");
			datasource.destroy();

			System.exit(0);
		}
	}

	public javax.swing.JPanel getPanelContenido() {
		return panelContenido;
	}

	public void setPanelContenido(javax.swing.JPanel panelContenido) {
		this.panelContenido = panelContenido;
	}

	public javax.swing.JPanel getPanelSubMenu() {
		return panelSubMenu;
	}

	public void setPanelSubMenu(javax.swing.JPanel panelSubMenu) {
		this.panelSubMenu = panelSubMenu;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JToggleButton btnAdmGral;
	private javax.swing.JToggleButton btnCerrarSession;
	private javax.swing.JToggleButton btnPersonas;
	private javax.swing.JToggleButton btnSalir;
	private javax.swing.JToggleButton btnUsuarios;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JToolBar.Separator jSeparator1;
	private javax.swing.JToolBar.Separator jSeparator3;
	private javax.swing.JToolBar.Separator jSeparator4;
	private javax.swing.JSeparator jSeparator5;
	private javax.swing.JSeparator jSeparator6;
	private javax.swing.JToolBar.Separator jSeparator7;
	private javax.swing.JPanel panelSubMenu;
	private javax.swing.JToolBar toolbarPrincipal;
	private javax.swing.JToolBar toolbarSubNivel;
	// End of variables declaration//GEN-END:variables
	private JPanel panelContenido;

	/*Sub Menu Persona*/

	private javax.swing.JToggleButton btnBuscarPersona = new JToggleButton(
			Constantes.BTN_BUSCAR);
	private javax.swing.JToggleButton btnNuevaPersona = new JToggleButton(
			Constantes.BTN_NUEVO);
	private javax.swing.JToggleButton btnModificarPersona = new JToggleButton(
			Constantes.BTN_MODIFICAR);
	private javax.swing.JToggleButton btnEliminarPersona = new JToggleButton(
			Constantes.BTN_ELIMINAR);
	private javax.swing.JToggleButton btnRealizarExamenPersona = new JToggleButton(
			Constantes.BTN_REALIZAR_EXAMEN);
	private javax.swing.JToggleButton btnVerExamenPersona = new JToggleButton(
			Constantes.BTN_VER_EXAMENES);

	/*Sub Menu Examenes*/

	/*Sub Menu Usuarios*/
	private javax.swing.JToggleButton btnAdmistracionUsuarios = new JToggleButton(
			Constantes.MENU_SUB_ADM_GRAL_USU);
	private javax.swing.JToggleButton btnCambiarClave = new JToggleButton(
			Constantes.MENU_SUB_CAMBIAR_CLAVE);

	/*Sub Menu Tareas Admisnitrativas*/
	private javax.swing.JToggleButton btnPanelControl = new JToggleButton(
			Constantes.MENU_SUB_PANEL_CONTROL);

	private javax.swing.JToggleButton btnInformesEstadistica = new JToggleButton(
			Constantes.MENU_SUB_INFORMES);
	private javax.swing.JToggleButton btnLogEventos = new JToggleButton(
			Constantes.MENU_SUB_LOG);
	private javax.swing.JToggleButton btnConfigurarDB = new JToggleButton(
			Constantes.MENU_SUB_PANEL_CONFIGURAR_DB);

	@Override
	public void cargarPrimeraOpcion() {
		this.cargarSubMenuPersona();
		this.seleccionarPersona();

	}

}