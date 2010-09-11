/*
 * PanelUsuario.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.Examen;
import testerGeneral.domain.Permiso;
import testerGeneral.domain.Usuario;
import testerGeneral.domain.UsuarioExamen;
import testerGeneral.domain.UsuarioPermiso;
import testerGeneral.focus.MyOwnFocusTraversalPolicy;
import testerGeneral.service.PermisoDefinition;
import testerGeneral.service.UsuarioDefinition;
import examenes.util.ExamenesUtils;
import frontend.buttons.ButtonBuscar;
import frontend.buttons.ButtonCancelarConTexto;
import frontend.buttons.ButtonEliminar;
import frontend.buttons.ButtonGuardar;
import frontend.buttons.ButtonModificar;
import frontend.buttons.ButtonNuevo;
import frontend.components.JOptionPaneTesterGral;
import frontend.tablemodel.TableModelUsuario;
import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class PanelUsuario extends javax.swing.JPanel {

	private static final Log log = LogFactory.getLog(PanelUsuario.class);

	/** Creates new form PanelUsuario 
	 * @throws Exception */
	public PanelUsuario() {
		initComponents();
		Util.ocultarSinResultados(lbSinResultados,true);
		Util.mostrarError(lbError,null, true);

		TableModelUsuario tableModel = new TableModelUsuario();
		tableModel.setLstUsuario(new ArrayList());
		tableUsuarios.setModel(tableModel);
		tableUsuarios.getSelectionModel().addListSelectionListener(
				new SharedListSelectionHandler());
		tableUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		Vector<Component> order = new Vector<Component>();
		order.add(txtBusquedaNombre);//1
		order.add(btnBuscar);//2
		order.add(tableUsuarios);//3
		order.add(txtUsuario);//4
		order.add(txtClave);//5
		order.add(txtClave2);//6
		order.add(checkHabilitado);//7
		order.add(panelPermisos.getJlist());//8
		order.add(panelExamenes.getJlist());//9
		order.add(btnNuevo);//10
		order.add(btnModificar);//11
		order.add(btnEliminar);//12
		order.add(btnGuardar);//13
		order.add(btnCancelar);//14
		MyOwnFocusTraversalPolicy newPolicy = new MyOwnFocusTraversalPolicy(
				order);

		this.setFocusTraversalPolicy(newPolicy);

		cargarPaneles();
		habilitar(false);
	}



	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel3 = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		txtBusquedaNombre = new javax.swing.JTextField();
		btnBuscar = new ButtonBuscar();
		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tableUsuarios = new javax.swing.JTable();
		lbSinResultados = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		txtUsuario = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		txtClave = new javax.swing.JPasswordField();
		jLabel5 = new javax.swing.JLabel();
		txtClave2 = new javax.swing.JPasswordField();
		checkHabilitado = new javax.swing.JCheckBox();
		jPanel4 = new javax.swing.JPanel();
		panelPermisos = new frontend.components.PanelScroll();
		jPanel5 = new javax.swing.JPanel();
		panelExamenes = new frontend.components.PanelScroll();
		lbError = new javax.swing.JLabel();
		btnNuevo = new ButtonNuevo();
		btnModificar = new ButtonModificar();
		btnEliminar = new ButtonEliminar();
		btnGuardar = new ButtonGuardar();
		btnCancelar = new ButtonCancelarConTexto();

		setFocusCycleRoot(true);

		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.PANEL_FILTROS_BUSQUEDA,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel3.setFocusable(false);

		jLabel3.setText(Constantes.LB_USUARIO);

		txtBusquedaNombre
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						txtBusquedaNombreActionPerformed(evt);
					}
				});

		btnBuscar.setToolTipText("Buscar");
		btnBuscar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnBuscarActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jLabel3)
										.addGap(14, 14, 14)
										.addComponent(
												txtBusquedaNombre,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												186,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												29, Short.MAX_VALUE)
										.addComponent(
												btnBuscar,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												48,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																btnBuscar,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																48,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				jPanel3Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel3)
																						.addComponent(
																								txtBusquedaNombre,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		((ButtonBuscar) btnBuscar).init();

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.PANEL_RESULTADOS_BUSQUEDA,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel1.setFocusable(false);

		tableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { "fsager", "No" }, { "vpaolini", "Si" },
						{ "jtesta", "Si" }, { null, null } }, new String[] {
						"Nombre usuario", "Habilitado" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class };
			boolean[] canEdit = new boolean[] { false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		tableUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tableUsuariosMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(tableUsuarios);

		lbSinResultados.setForeground(new java.awt.Color(204, 0, 0));
		lbSinResultados.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_ERROR)));
		lbSinResultados.setText(Constantes.ERROR_SIN_RESULTADOS);

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
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jScrollPane1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				360,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGap(
																				12,
																				12,
																				12)
																		.addComponent(
																				lbSinResultados,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				349,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel1Layout.createSequentialGroup().addComponent(
						jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE,
						363, Short.MAX_VALUE).addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(lbSinResultados,
								javax.swing.GroupLayout.PREFERRED_SIZE, 24,
								javax.swing.GroupLayout.PREFERRED_SIZE)));

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.PANEL_DATOS_USUARIO,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel2.setFocusable(false);

		jLabel1.setText(Constantes.LB_USUARIO);

		txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtUsuarioFocusLost(evt);
			}
		});

		jLabel2.setText(Constantes.LB_CLAVE);

		jLabel5.setText(Constantes.LB_CLAVE_NUEVA_RE);

		checkHabilitado.setText(Constantes.LB_HABILITADO + "");

		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.PANEL_PERMISOS,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 3, 11)));

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(
				jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel4Layout.createSequentialGroup().addComponent(
						panelPermisos, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel4Layout.createSequentialGroup().addComponent(
						panelPermisos, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(34, Short.MAX_VALUE)));

		jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.PANEL_EXAMENES,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 3, 11)));

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(
				jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel5Layout.createSequentialGroup().addComponent(
						panelExamenes, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel5Layout.createSequentialGroup().addComponent(
						panelExamenes, javax.swing.GroupLayout.DEFAULT_SIZE,
						412, Short.MAX_VALUE).addContainerGap()));

		lbError.setBackground(new java.awt.Color(204, 0, 0));
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
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addGap(
																				25,
																				25,
																				25)
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel2Layout
																										.createSequentialGroup()
																										.addGroup(
																												jPanel2Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jLabel1)
																														.addComponent(
																																jLabel2)
																														.addComponent(
																																jLabel5))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addGroup(
																												jPanel2Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING,
																																false)
																														.addComponent(
																																txtClave2)
																														.addComponent(
																																txtClave)
																														.addComponent(
																																txtUsuario,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																174,
																																Short.MAX_VALUE)))
																						.addComponent(
																								checkHabilitado)))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jPanel4,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel5,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addContainerGap())
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addComponent(
												lbError,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												685, Short.MAX_VALUE).addGap(
												24, 24, 24)));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel1)
																						.addComponent(
																								txtUsuario,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel2)
																						.addComponent(
																								txtClave,
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
																								jLabel5)
																						.addComponent(
																								txtClave2,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				checkHabilitado)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jPanel4,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(
																				7,
																				7,
																				7))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jPanel5,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
										.addComponent(
												lbError,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												24,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		btnNuevo.setText("Nuevo");
		btnNuevo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnNuevoActionPerformed(evt);
			}
		});

		btnModificar.setText("Modificar");
		btnModificar.setEnabled(false);
		btnModificar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnModificarActionPerformed(evt);
			}
		});

		btnEliminar.setText("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnEliminarActionPerformed(evt);
			}
		});

		btnGuardar.setText("Guardar");
		btnGuardar.setEnabled(false);
		btnGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGuardarActionPerformed(evt);
			}
		});

		btnCancelar.setText("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarActionPerformed(evt);
			}
		});

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
																layout
																		.createSequentialGroup()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jPanel3,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jPanel1,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								0,
																								383,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jPanel2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																layout
																		.createSequentialGroup()
																		.addContainerGap(
																				683,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnNuevo,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				80,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnModificar,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				80,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnEliminar,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				80,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnGuardar,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				80,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnCancelar,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				80,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
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
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jPanel3,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				82,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jPanel1,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
														.addComponent(
																jPanel2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																btnNuevo,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnModificar,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnEliminar,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnGuardar,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnCancelar,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(18, Short.MAX_VALUE)));

		((ButtonNuevo) btnNuevo).init();
		((ButtonModificar) btnModificar).init();
		((ButtonEliminar) btnEliminar).init();
		((ButtonGuardar) btnGuardar).init();
		((ButtonCancelarConTexto) btnCancelar).init();
	}// </editor-fold>
	//GEN-END:initComponents

	private void txtUsuarioFocusLost(java.awt.event.FocusEvent evt) {
		txtUsuario.setText(txtUsuario.getText().toLowerCase());
	}

	private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
		txtUsuario.requestFocus();
		btnNuevo.setEnabled(false);
		btnCancelar.setEnabled(true);
		btnGuardar.setEnabled(true);
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		
		afterButton();
	}

	private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			log.info("btnEliminarActionPerformed");

			if (JOptionPaneTesterGral.showInternal(Constantes.MENSAJE_ELIMINAR,
					Constantes.MENSAJE_ELIMINADO_TIT,
					JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

				usuarioService.delete(usuario);

				btnNuevo.setEnabled(true);
				btnCancelar.setEnabled(false);
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnGuardar.setEnabled(false);
				btnCancelar.setEnabled(false);

				afterButton();
				JOptionPaneTesterGral.showInternalMessageDialog(Constantes.MENSAJE_ELIMINADO,
						Constantes.MENSAJE_ELIMINADO_TIT,
						JOptionPane.INFORMATION_MESSAGE);

				txtBusquedaNombre.setText(null);
				habilitar(false);
				cargarUsuarios();
				
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
		log.info("btnModificarActionPerformed");

		habilitar(true);

		txtUsuario.setEnabled(false);

		btnNuevo.setEnabled(false);
		btnCancelar.setEnabled(true);
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnGuardar.setEnabled(true);
		btnCancelar.setEnabled(true);
	}

	private void tableUsuariosMouseClicked(java.awt.event.MouseEvent evt) {
		seleccionarUsuario();
	}

	public void seleccionarUsuario() {
		log.info("seleccionarUsuario");

		int selected = tableUsuarios.getSelectedRow();
		usuario = ((TableModelUsuario) tableUsuarios.getModel())
				.getValueAt(selected);

		inicializar();

		txtUsuario.setText(usuario.getUsrNombre());
		txtClave.setText(usuario.getUsrClave());
		txtClave2.setText(usuario.getUsrClave());

		if (usuario.getUsrHabilitadoSn().compareTo("S") == 0)
			checkHabilitado.setSelected(true);

		cargarPaneles();
		panelExamenes.setSeleccionados(usuario.getUsuarioExamens());
		panelPermisos.setSeleccionados(usuario.getUsuarioPermisos());

		btnGuardar.setEnabled(false);
		btnCancelar.setEnabled(false);
		btnNuevo.setEnabled(true);

		if (usuario.getDeletedSn().equals("S")) {
			btnEliminar.setEnabled(true);
			btnModificar.setEnabled(true);
		} else {
			btnEliminar.setEnabled(false);
			btnModificar.setEnabled(false);
		}

		habilitar(false);

	}

	private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {

		btnNuevo.setEnabled(true);
		btnCancelar.setEnabled(false);
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnGuardar.setEnabled(false);
		btnCancelar.setEnabled(false);		
		afterButton();
		habilitar(false);
	}

	private void txtBusquedaNombreActionPerformed(java.awt.event.ActionEvent evt) {
		cargarUsuarios();
	}

	private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
		cargarUsuarios();
		inicializar();
		habilitar(false);
	}

	private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
		log.info("btnGuardarActionPerformed");

		Util.mostrarError(lbError,null, true);
		if (validarUsuario()) {
			boolean isUpdate = true;
			if (usuario.getUsrClave() == null)
				isUpdate = false;

			if (usuario.getUsrClave() != null
					&& usuario.getUsrClave().compareTo(
							new String(txtClave.getPassword())) != 0)
				usuario.setUsrUltimoAcceso(null);

			usuario.setUsrNombre(txtUsuario.getText());
			usuario.setUsrClave(new String(txtClave.getPassword()));
			usuario.setDeletedSn("S");

			String usrHabilitadoSn = new String();
			if (checkHabilitado.isSelected())
				usrHabilitadoSn = "S";
			else
				usrHabilitadoSn = "N";

			usuario.setUsrHabilitadoSn(usrHabilitadoSn);

			Set usuarioPermisos = usuario.getUsuarioPermisos();
			usuarioPermisos.clear();
			for (int i = 0; i < panelPermisos.getSeleccionados().size(); i++) {
				usuarioPermisos.add(panelPermisos.getSeleccionados().get(i));
			}

			Set usuarioExamens = usuario.getUsuarioExamens();
			usuarioExamens.clear();
			for (int i = 0; i < panelExamenes.getSeleccionados().size(); i++) {
				usuarioExamens.add(panelExamenes.getSeleccionados().get(i));
			}

			try {

				if (isUpdate) {
					usuarioService.update(usuario);
					testerGeneral.persistence.impl.Util.insertAudit(
							testerGeneral.persistence.impl.Util.ACTION_UPDATE,
							Usuario.class.getSimpleName(), usuario
									.getUsrNombre());
				} else {
					usuarioService.insert(usuario);
				}

				btnNuevo.setEnabled(true);
				btnCancelar.setEnabled(false);
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnGuardar.setEnabled(false);
				btnCancelar.setEnabled(false);

				afterButton();
				txtBusquedaNombre.setText(null);
				cargarUsuarios();
				habilitar(false);
				JOptionPaneTesterGral.showInternalMessageDialog(Constantes.MENSAJE_GUARDADO,
						Constantes.MENSAJE_GUARDADO_TIT,
						JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			cargarPaneles();
		}
	}

	public boolean validarUsuario() {
		log.info("validarUsuario");

		if (txtUsuario.getText() == null || txtUsuario.getText().equals("")) {
			Util.mostrarError(lbError,Constantes.ERROR_USR_NOMBRE, false);
			return false;
		}
		
		if (usuario.getUsrNombre() == null
				&& existeUsuario(txtUsuario.getText())) {
			Util.mostrarError(lbError,Constantes.ERROR_USR_EXISTE, false);
			return false;
		}

		String clave = new String(txtClave.getPassword());
		String clave2 = new String(txtClave2.getPassword());
		if (clave == null || clave.equals("")) {
			Util.mostrarError(lbError,Constantes.ERROR_USR_CLAVE, false);
			return false;
		}

		if (clave.compareTo(clave2) != 0) {
			Util.mostrarError(lbError,Constantes.ERROR_USR_CLAVE2, false);
			return false;
		}



		return true;
	}

	public boolean existeUsuario(String usr_nombre) {
		try {
			log.info("existeUsuario");

			Usuario usu =(Usuario)usuarioService.get(usr_nombre,usuario.getClass());

			if (usu != null)
				return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		return false;
	}

	public void afterButton() {
		log.info("afterButton");

		inicializar();
		habilitar(true);
		usuario = new Usuario();
		cargarPaneles();
	}

	public void inicializar() {
		log.info("inicializar");

		txtUsuario.setText(null);
		txtClave.setText(null);
		txtClave2.setText(null);
		checkHabilitado.setSelected(false);
		panelExamenes.inicializar();
		panelPermisos.inicializar();
		Util.mostrarError(lbError,"", true);
	}

	public void habilitar(boolean habilitar) {
		log.info("habilitar");

		txtUsuario.setEnabled(habilitar);
		txtClave.setEnabled(habilitar);
		txtClave2.setEnabled(habilitar);
		checkHabilitado.setEnabled(habilitar);

		panelExamenes.setEnabled(habilitar);
		panelPermisos.setEnabled(habilitar);
	}

	public void cargarUsuarios() {
		try {
			log.info("cargarUsuarios");

			Util.ocultarSinResultados(lbSinResultados,true);
			Usuario usu = new Usuario();
			usu.setDeletedSn(null);
			usu.setUsrNombre(txtBusquedaNombre.getText());

			List<Usuario> usuarios = usuarioService.getAll(usu);
			TableModelUsuario tableModel = new TableModelUsuario();
			tableModel.setLstUsuario(usuarios);
			tableUsuarios.setModel(tableModel);
			tableUsuarios.setAutoCreateRowSorter(false);

			if (usuarios.size() == 0)
				Util.ocultarSinResultados(lbSinResultados,false);
			else
				tableUsuarios.setAutoCreateRowSorter(true);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void cargarPaneles() {
		try {
			log.info("cargarPermisos");

			PermisoDefinition permisoService = (PermisoDefinition) ContextManager
					.getBizObject("permisoService");
			permisos = permisoService.getAll(new Permiso());
			List usuarioPermisos = new ArrayList();

			for (int i = 0; i < permisos.size(); i++) {
				UsuarioPermiso usuarioPermiso = new UsuarioPermiso();
				usuarioPermiso.setPermiso(permisos.get(i));
				usuarioPermiso.setUsuario(usuario);
				usuarioPermisos.add(usuarioPermiso);
			}

			panelPermisos.setDatos(usuarioPermisos);
			panelPermisos.cargarList();

			List<Examen> examenes = ExamenesUtils.obtenerExamenes(true);
			List usuarioExamenes = new ArrayList();

			for (int i = 0; i < examenes.size(); i++) {
				UsuarioExamen usuarioExamen = new UsuarioExamen();
				usuarioExamen.setExamen(examenes.get(i));
				usuarioExamen.setUsuario(usuario);
				usuarioExamenes.add(usuarioExamen);
			}

			panelExamenes.setDatos(usuarioExamenes);
			panelExamenes.cargarList();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	class SharedListSelectionHandler implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();
			if (!lsm.isSelectionEmpty()) {
				seleccionarUsuario();
			}
		}
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnBuscar;
	private javax.swing.JButton btnCancelar;
	private javax.swing.JButton btnEliminar;
	private javax.swing.JButton btnGuardar;
	private javax.swing.JButton btnModificar;
	private javax.swing.JButton btnNuevo;
	private javax.swing.JCheckBox checkHabilitado;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lbError;
	private javax.swing.JLabel lbSinResultados;
	private frontend.components.PanelScroll panelExamenes;
	private frontend.components.PanelScroll panelPermisos;
	private javax.swing.JTable tableUsuarios;
	private javax.swing.JTextField txtBusquedaNombre;
	private javax.swing.JPasswordField txtClave;
	private javax.swing.JPasswordField txtClave2;
	private javax.swing.JTextField txtUsuario;
	// End of variables declaration//GEN-END:variables
	private List<Permiso> permisos;
	private UsuarioDefinition usuarioService = (UsuarioDefinition) ContextManager
			.getBizObject("usuarioService");
	private Usuario usuario = new Usuario();

}