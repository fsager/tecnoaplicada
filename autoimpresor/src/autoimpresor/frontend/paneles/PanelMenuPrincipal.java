/*
 * PanelMenuPrincipal.java
 *
 * Created on __DATE__, __TIME__
 */

package autoimpresor.frontend.paneles;

import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.actualizaciones.GestorActualizaciones;
import testerGeneral.db.ConexionManagerTesterGeneral;
import testerGeneral.domain.Constantes;
import autoimpresor.business.ContextManager;
import autoimpresor.domain.Licencia;
import autoimpresor.domain.Usuario;
import autoimpresor.service.LicenciaDefinition;
import frontend.components.JOptionPaneTesterGral;
import frontend.paneles.PanelCambiarClave;
import frontend.paneles.PanelConfiguracionDB;
import frontend.paneles.PanelLogEventos;
import frontend.paneles.PanelMenu;
import frontend.paneles.licence.PanelLicencia;
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
		if (((Usuario) Util.usuarioCommon).getUsrAccPanelControlSn()
				.equals("N")) {
			btnAdmGral.setVisible(false);
			jSeparator4.setVisible(false);
		}
		agregarEscuchas();
		//panelSubMenu.setLayout(null);

	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		toolbarPrincipal = new javax.swing.JToolBar();
		btnLicencias = new javax.swing.JToggleButton();
		jSeparator2 = new javax.swing.JToolBar.Separator();
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

		setMaximumSize(new java.awt.Dimension(500, 730));

		toolbarPrincipal.setFloatable(false);
		toolbarPrincipal.setRollover(true);
		toolbarPrincipal.setMaximumSize(new java.awt.Dimension(0, 0));
		toolbarPrincipal.setOpaque(false);
		toolbarPrincipal
				.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
					public void propertyChange(
							java.beans.PropertyChangeEvent evt) {
						toolbarPrincipalPropertyChange(evt);
					}
				});

		btnLicencias.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_MENU_LICENCIA)));
		btnLicencias.setMnemonic('P');
		btnLicencias.setText("Licencias");
		btnLicencias.setFocusable(false);
		btnLicencias
				.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		btnLicencias.setMargin(null);
		btnLicencias.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLicenciasActionPerformed(evt);
			}
		});
		toolbarPrincipal.add(btnLicencias);
		toolbarPrincipal.add(jSeparator2);

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
		btnCerrarSession.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCerrarSessionActionPerformed(evt);
			}
		});
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

		panelSubMenu.setMaximumSize(new java.awt.Dimension(500, 730));

		toolbarSubNivel.setFloatable(false);
		toolbarSubNivel.setRollover(true);
		toolbarSubNivel.setMaximumSize(new java.awt.Dimension(500, 730));
		toolbarSubNivel.setOpaque(false);

		javax.swing.GroupLayout panelSubMenuLayout = new javax.swing.GroupLayout(
				panelSubMenu);
		panelSubMenu.setLayout(panelSubMenuLayout);
		panelSubMenuLayout
				.setHorizontalGroup(panelSubMenuLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 682, Short.MAX_VALUE)
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
																682,
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
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addComponent(
												toolbarPrincipal,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												369, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jLabel1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												308,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jSeparator6,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																682,
																Short.MAX_VALUE)
														.addComponent(
																panelSubMenu,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jSeparator5,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																682,
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
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGap(0, 0, 0)
										.addComponent(
												jSeparator6,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)));
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnCerrarSessionActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void toolbarPrincipalPropertyChange(
			java.beans.PropertyChangeEvent evt) {
		// TODO add your handling code here:
	}

	private void btnLicenciasActionPerformed(java.awt.event.ActionEvent evt) {
		cargarSubMenuLicencia();
		seleccionarDatosPersona();
	}

	private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
	}

	private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {
	}

	public void doBeforLoadMenu(JToolBar toolbar, JToggleButton button) {
		((FramePrincipal) Util.framePrincipal).getJButtonActualizarSistema()
				.setVisible(GestorActualizaciones.getSeEncontroActualizacion());

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

		btnDatosPersona.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				seleccionarDatosPersona();
			}
		});

		btnLicPendientes.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				seleccionarLicPendientes();
			}
		});

		btnLicHistotorial
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						seleccionarLicHistorial();
					}
				});

		btnLicImportadas.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				seleccionarLicImportadas();
			}
		});

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

		btnPanelAdminMunicipio
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						seleccionarAdministracionMunicipio();
					}
				});

		btnConfigurarDB
		.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				seleccionarConfigurarDB();
			}
		});

		btnConfigurarLicencia.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				seleccionarConfigurarLicencia();
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

	public void setLicencias() {
		btnLicHistotorial.setText("Historial (" + getEnHistorico() + ")");
		btnLicPendientes.setText("Pendientes (" + getEnPendiente() + ")");
		btnLicImportadas.setText("Importadas (" + getImportadas() + ")");

	}

	public void cargarSubMenuLicencia() {
		doBeforLoadMenu(toolbarPrincipal, btnLicencias);

		btnDatosPersona.setVisible(true);
		toolbarSubNivel.add(btnDatosPersona);

		btnLicPendientes.setVisible(true);
		toolbarSubNivel.add(btnLicPendientes);

		btnLicHistotorial.setVisible(true);
		toolbarSubNivel.add(btnLicHistotorial);

		if (ContextManager.getProperty(
				"SISTEMA.MUNICIPIO.ES_CENTRO_IMPRESOR_S_N").equals("S")) {
			btnLicImportadas.setVisible(true);
			toolbarSubNivel.add(btnLicImportadas);
		}

		calcularLicenciasPorEstado();

		doAfterLoadMenu();
	}

	public void calcularLicenciasPorEstado() {
		try {

			/*String sql1 = "select count(*) from app.licencia where lic_estado = 'H'";
			String sql2 = "select count(*) from app.licencia where lic_estado = 'P'";
			String sql3 = "select count(*) from app.licencia where lic_estado = 'I'";

			Connection conn = ContextManager.getCurrentConnection();
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql1);
			if (rs.next())
				this.setEnHistorico(rs.getInt(1));

			rs.close();

			rs = stm.executeQuery(sql2);
			if (rs.next())
				this.setEnPendiente(rs.getInt(1));

			rs.close();

			rs = stm.executeQuery(sql3);
			if (rs.next())
				this.setImportas(rs.getInt(1));

			rs.close();
			
			setLicencias();*/

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void seleccionarDatosPersona() {

		testerGeneral.persistence.impl.Util.insertAudit(
				testerGeneral.persistence.impl.Util.ACTION_MENU_SUB_LICENCIA,
				null, null);

		unSelectButtons(toolbarSubNivel, btnDatosPersona);
		panelContenido.removeAll();

		PanelPersona panelPersona = new PanelPersona(this);
		panelPersona.validate();

		panelPersona.setVisible(true);
		panelContenido.add(panelPersona);

		doAfterLoadMenuContenido();
	}

	public void seleccionarLicPendientes() {

		unSelectButtons(toolbarSubNivel, btnLicPendientes);
		panelContenido.removeAll();

		PanelLicenciasPendientes panelLicenciasPendientes = new PanelLicenciasPendientes();
		panelLicenciasPendientes.validate();

		panelLicenciasPendientes.setVisible(true);
		panelContenido.add(panelLicenciasPendientes);

		doAfterLoadMenuContenido();
	}

	public void seleccionarLicHistorial() {

		testerGeneral.persistence.impl.Util.insertAudit(
				testerGeneral.persistence.impl.Util.ACTION_MENU_SUB_LICENCIA,
				null, null);

		unSelectButtons(toolbarSubNivel, btnLicHistotorial);
		panelContenido.removeAll();

		PanelLicenciasHistorico panelLicenciasHistorico = new PanelLicenciasHistorico();
		panelLicenciasHistorico.validate();

		panelLicenciasHistorico.setVisible(true);
		panelContenido.add(panelLicenciasHistorico);

		doAfterLoadMenuContenido();
	}

	public void seleccionarConfigurarDB() {
		
		unSelectButtons(toolbarSubNivel, btnConfigurarDB);
		panelContenido.removeAll();

		PanelConfiguracionDB panelConfiguracionDB = new PanelConfiguracionDB(ContextManager.getProperty("SISTEMA.NOMBRE.PROGRAMA"),false);
		panelConfiguracionDB.validate();

		panelConfiguracionDB.setVisible(true);
		panelContenido.add(panelConfiguracionDB);

		doAfterLoadMenuContenido();
		
		
	}
	
	
	public void seleccionarLicImportadas() {

		testerGeneral.persistence.impl.Util.insertAudit(
				testerGeneral.persistence.impl.Util.ACTION_MENU_SUB_LICENCIA,
				null, null);

		unSelectButtons(toolbarSubNivel, btnLicImportadas);
		panelContenido.removeAll();

		PanelLicenciasImportadas panelLicenciasImportadas = new PanelLicenciasImportadas();
		panelLicenciasImportadas.validate();

		panelLicenciasImportadas.setVisible(true);
		panelContenido.add(panelLicenciasImportadas);

		doAfterLoadMenuContenido();
	}

	private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {
		cargarSubMenuUsuarios();
		seleccionarCambiarClave();
	}

	public void cargarSubMenuUsuarios() {
		doBeforLoadMenu(toolbarPrincipal, btnUsuarios);

		btnCambiarClave.setVisible(true);
		toolbarSubNivel.add(btnCambiarClave);

		if (((Usuario) Util.usuarioCommon).getUsrAdmUsrSn().equals("S")) {
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
		/*btnInformesEstadistica.setVisible(true);
		toolbarSubNivel.add(btnInformesEstadistica);*/

		btnLogEventos.setVisible(true);
		toolbarSubNivel.add(btnLogEventos);

		btnPanelAdminMunicipio.setVisible(true);
		toolbarSubNivel.add(btnPanelAdminMunicipio);

		btnConfigurarDB.setVisible(true);
		toolbarSubNivel.add(btnConfigurarDB);
		
		btnConfigurarLicencia.setVisible(true);
		toolbarSubNivel.add(btnConfigurarLicencia);

		
		/*Refresco para que se visualice correctamente*/
		doAfterLoadMenu();
	}

	public void seleccionarConfigurarLicencia() {

		unSelectButtons(toolbarSubNivel, btnConfigurarLicencia);
		panelContenido.removeAll();

		PanelLicencia panelLicencia = new PanelLicencia(null,false,true);
		panelLicencia.validate();

		panelLicencia.setVisible(true);
		panelContenido.add(panelLicencia);

		doAfterLoadMenuContenido();

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

		/*PanelBuscarPersona panelBuscarPersona = new PanelBuscarPersona();
		panelBuscarPersona.validate();
		
		panelBuscarPersona.setVisible(true);
		panelContenido.add(panelBuscarPersona);*/

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

	public void seleccionarAdministracionMunicipio() {
		testerGeneral.persistence.impl.Util
				.insertAudit(
						testerGeneral.persistence.impl.Util.ACTION_MENU_PANEL_ADMIN_MUNICIPIO,
						null, null);

		unSelectButtons(toolbarSubNivel, btnPanelAdminMunicipio);
		panelContenido.removeAll();

		PanelGestionDeMunicipio panelGestionDeMunicipio = new PanelGestionDeMunicipio();
		panelGestionDeMunicipio.setVisible(true);

		panelContenido.add(panelGestionDeMunicipio);

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

			ConexionManagerTesterGeneral datasource = (ConexionManagerTesterGeneral) ContextManager.getBizObject("dataSource");
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

	public javax.swing.JToggleButton getBtnLicencias() {
		return btnLicencias;
	}

	public void setBtnLicencias(javax.swing.JToggleButton btnLicencias) {
		this.btnLicencias = btnLicencias;
	}

	public javax.swing.JToggleButton getBtnDatosPersona() {
		return btnDatosPersona;
	}

	public void setBtnDatosPersona(javax.swing.JToggleButton btnDatosPersona) {
		this.btnDatosPersona = btnDatosPersona;
	}

	public javax.swing.JToggleButton getBtnLicPendientes() {
		return btnLicPendientes;
	}

	public void setBtnLicPendientes(javax.swing.JToggleButton btnLicPendientes) {
		this.btnLicPendientes = btnLicPendientes;
	}

	public javax.swing.JToggleButton getBtnLicHistotorial() {
		return btnLicHistotorial;
	}

	public void setBtnLicHistotorial(javax.swing.JToggleButton btnLicHistotorial) {
		this.btnLicHistotorial = btnLicHistotorial;
	}

	@Override
	public void cargarPrimeraOpcion() {
		this.cargarSubMenuLicencia();
		this.seleccionarDatosPersona();

	}

	public int getEnPendiente() {
		return enPendiente;
	}

	public void setEnPendiente(int enPendiente) {
		this.enPendiente = enPendiente;
	}

	public int getEnHistorico() {
		return enHistorico;
	}

	public void setEnHistorico(int enHistorico) {
		this.enHistorico = enHistorico;
	}

	public void setImportas(int importadas) {
		this.importadas = importadas;
	}

	public int getImportadas() {
		return importadas;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JToggleButton btnAdmGral;
	private javax.swing.JToggleButton btnCerrarSession;
	private javax.swing.JToggleButton btnLicencias;
	private javax.swing.JToggleButton btnSalir;
	private javax.swing.JToggleButton btnUsuarios;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JToolBar.Separator jSeparator2;
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

	/*private javax.swing.JToggleButton btnDatosPersona = new JToggleButton(
			"Datos de Personas");
	private javax.swing.JToggleButton btnLicPendientes = new JToggleButton(
			"Pendientes ()");
	private javax.swing.JToggleButton btnLicHistotorial = new JToggleButton(
			"Historial ()");
	private javax.swing.JToggleButton btnLicImportadas = new JToggleButton(
			"Importadas ()");*/

	private javax.swing.JToggleButton btnDatosPersona = new JToggleButton(
			"Datos de Personas");
	private javax.swing.JToggleButton btnLicPendientes = new JToggleButton(
			"Pendientes");
	private javax.swing.JToggleButton btnLicHistotorial = new JToggleButton(
			"Historial");
	private javax.swing.JToggleButton btnLicImportadas = new JToggleButton(
			"Importadas");

	/*Sub Menu Usuarios*/
	private javax.swing.JToggleButton btnAdmistracionUsuarios = new JToggleButton(
			Constantes.MENU_SUB_ADM_GRAL_USU);
	private javax.swing.JToggleButton btnCambiarClave = new JToggleButton(
			Constantes.MENU_SUB_CAMBIAR_CLAVE);

	/*Sub Menu Tareas Administrativas*/
	private javax.swing.JToggleButton btnPanelControl = new JToggleButton(
			Constantes.MENU_SUB_PANEL_CONTROL);

	private javax.swing.JToggleButton btnInformesEstadistica = new JToggleButton(
			Constantes.MENU_SUB_INFORMES);
	private javax.swing.JToggleButton btnLogEventos = new JToggleButton(
			Constantes.MENU_SUB_LOG);

	private javax.swing.JToggleButton btnPanelAdminMunicipio = new JToggleButton(
			Constantes.MENU_SUB_PANEL_ADM_MINICIPIO);
	
	private javax.swing.JToggleButton btnConfigurarDB = new JToggleButton(
			Constantes.MENU_SUB_PANEL_CONFIGURAR_DB);

	private javax.swing.JToggleButton btnConfigurarLicencia = new JToggleButton(
			Constantes.MENU_SUB_PANEL_CONFIGURAR_LICENCIA);
	
	private int enPendiente;
	private int enHistorico;
	private int importadas;
	private LicenciaDefinition licenciaService = (LicenciaDefinition) ContextManager
			.getBizObject("licenciaService");

	@Override
	public void cargarSubMenuPersona() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void seleccionarPersona() {
		// TODO Auto-generated method stub
		
	}

}