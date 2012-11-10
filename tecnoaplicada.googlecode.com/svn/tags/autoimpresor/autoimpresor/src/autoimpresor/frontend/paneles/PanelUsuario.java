/*
 * PanelUsuario.java
 *
 * Created on __DATE__, __TIME__
 */

package autoimpresor.frontend.paneles;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.focus.MyOwnFocusTraversalPolicy;
import testerGeneral.service.UsuarioDefinition;
import autoimpresor.domain.Usuario;
import autoimpresor.frontend.tablemodels.TableModelUsuario;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import frontend.buttons.ButtonBuscar;
import frontend.buttons.ButtonCancelarConTexto;
import frontend.buttons.ButtonCancelarMini;
import frontend.buttons.ButtonEliminar;
import frontend.buttons.ButtonExaminar;
import frontend.buttons.ButtonGuardar;
import frontend.buttons.ButtonModificar;
import frontend.buttons.ButtonNuevo;
import frontend.components.JOptionPaneTesterGral;
import frontend.utils.Util;
import frontend.ventanas.VentanaSeleccionImagen;
import frontend.ventanas.VentanaTomarFirma;

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
		Util.ocultarSinResultados(lbSinResultados, true);
		Util.mostrarError(lbError, null, true);
		btnCancelarFirma.setText("Borrar");

		btnExaminarFirma.setText("Agregar desde Pad");
		btnExaminarFoto.setText("Agregar desde Imagen");

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
		order.add(txtCargo);//6		
		order.add(checkHabilitado);//7		
		order.add(checkPuedeFirmar);//7
		order.add(checkPuedeCrearLic);//7
		order.add(checkPuedeIngresarPC);//7
		order.add(checkPuedeAdmUsr);//7		
		order.add(checkPuedeLicInc);//7		
		order.add(btnNuevo);//10
		order.add(btnModificar);//11
		order.add(btnEliminar);//12
		order.add(btnGuardar);//13
		order.add(btnCancelar);//14
		MyOwnFocusTraversalPolicy newPolicy = new MyOwnFocusTraversalPolicy(
				order);

		this.setFocusTraversalPolicy(newPolicy);

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
		checkPuedeLicInc = new javax.swing.JCheckBox();
		checkPuedeAdmUsr = new javax.swing.JCheckBox();
		checkPuedeIngresarPC = new javax.swing.JCheckBox();
		checkPuedeCrearLic = new javax.swing.JCheckBox();
		checkPuedeFirmar = new javax.swing.JCheckBox();
		lbError = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		txtCargo = new javax.swing.JTextField();
		lbFirma = new javax.swing.JLabel();
		btnCancelarFirma = new ButtonCancelarMini();
		btnExaminarFirma = new ButtonExaminar();
		btnExaminarFoto = new ButtonExaminar();
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

		txtBusquedaNombre.setMaximumSize(new java.awt.Dimension(186, 22));
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
												26, Short.MAX_VALUE)
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
																				352,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel1Layout
										.createSequentialGroup()
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												394,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(
												lbSinResultados,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												24,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.PANEL_DATOS_USUARIO,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel2.setFocusable(false);

		jLabel1.setText(Constantes.LB_USUARIO);

		txtUsuario.setMaximumSize(new java.awt.Dimension(174, 22));
		txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtUsuarioFocusLost(evt);
			}
		});

		jLabel2.setText(Constantes.LB_CLAVE);

		txtClave.setMaximumSize(new java.awt.Dimension(174, 22));

		jLabel5.setText(Constantes.LB_CLAVE_NUEVA_RE);

		txtClave2.setMaximumSize(new java.awt.Dimension(174, 22));

		checkHabilitado.setText(Constantes.LB_HABILITADO + "");

		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.PANEL_PERMISOS,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 3, 11)));

		checkPuedeLicInc
				.setText("Puede emitir licencias con requisitos incompletos");

		checkPuedeAdmUsr.setText("Puede admistrar usuarios y permisos");

		checkPuedeIngresarPC
				.setText("Puede ingresar a las tareas administrativas");

		checkPuedeCrearLic.setText("Puede crear licencias");

		checkPuedeFirmar.setText("Puede firmar licencias");
		checkPuedeFirmar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				checkPuedeFirmarActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(
				jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout
				.setHorizontalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																checkPuedeFirmar)
														.addComponent(
																checkPuedeCrearLic)
														.addComponent(
																checkPuedeIngresarPC)
														.addComponent(
																checkPuedeAdmUsr)
														.addComponent(
																checkPuedeLicInc))
										.addContainerGap(97, Short.MAX_VALUE)));
		jPanel4Layout
				.setVerticalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addComponent(checkPuedeFirmar)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(checkPuedeCrearLic)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(checkPuedeIngresarPC)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(checkPuedeAdmUsr)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(checkPuedeLicInc)));

		lbError.setBackground(new java.awt.Color(204, 0, 0));
		lbError.setForeground(new java.awt.Color(204, 0, 0));
		lbError.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_ERROR)));

		jLabel6.setText("Cargo:");

		txtCargo.setMaximumSize(new java.awt.Dimension(174, 22));
		txtCargo.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtCargoFocusLost(evt);
			}
		});

		lbFirma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbFirma.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
		lbFirma.setBorder(javax.swing.BorderFactory
				.createTitledBorder(Constantes.LB_FIRMA));
		lbFirma.setMaximumSize(new java.awt.Dimension(246, 99));
		lbFirma.setMinimumSize(new java.awt.Dimension(246, 99));

		btnCancelarFirma.setIcon(new ImageIcon(getClass().getResource(
				"/images/agregar.png")));
		btnCancelarFirma.setToolTipText("Examinar");
		btnCancelarFirma.setEnabled(false);
		((ButtonCancelarMini) btnCancelarFirma).init();
		btnCancelarFirma.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarFirmaActionPerformed(evt);
			}
		});

		btnExaminarFirma.setIcon(new ImageIcon(getClass().getResource(
				"/images/agregar.png")));
		btnExaminarFirma.setToolTipText("Examinar");
		btnExaminarFirma.setEnabled(false);
		((ButtonExaminar) btnExaminarFirma).init();
		btnExaminarFirma.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExaminarFirmaActionPerformed(evt);
			}
		});

		btnExaminarFoto.setIcon(new ImageIcon(getClass().getResource(
				"/images/agregar.png")));
		btnExaminarFoto.setToolTipText("Examinar");
		btnExaminarFoto.setEnabled(false);
		((ButtonExaminar) btnExaminarFoto).init();
		btnExaminarFoto.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExaminarFotoActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel2Layout
										.createSequentialGroup()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
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
																																jLabel5)
																														.addComponent(
																																jLabel6))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addGroup(
																												jPanel2Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING)
																														.addGroup(
																																jPanel2Layout
																																		.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING,
																																				false)
																																		.addComponent(
																																				txtClave2,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE)
																																		.addComponent(
																																				txtClave,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE)
																																		.addComponent(
																																				txtUsuario,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				174,
																																				Short.MAX_VALUE))
																														.addComponent(
																																txtCargo,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																174,
																																Short.MAX_VALUE)))
																						.addComponent(
																								checkHabilitado))
																		.addGap(
																				380,
																				380,
																				380))
														.addComponent(
																lbError,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																512,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap())
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												lbFirma,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												287, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																btnExaminarFoto,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																103,
																Short.MAX_VALUE)
														.addComponent(
																btnExaminarFirma,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																btnCancelarFirma,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGap(247, 247, 247))
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jPanel4,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(238, Short.MAX_VALUE)));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
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
														.addComponent(jLabel1)
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
														.addComponent(jLabel2)
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
														.addComponent(jLabel5)
														.addComponent(
																txtClave2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel6)
														.addComponent(
																txtCargo,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(checkHabilitado)
										.addGap(7, 7, 7)
										.addComponent(
												jPanel4,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				btnCancelarFirma,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				25,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnExaminarFirma,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				25,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnExaminarFoto,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				25,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																lbFirma,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																178,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(0, 0, 0)
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
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addGroup(
																layout
																		.createSequentialGroup()
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
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																layout
																		.createSequentialGroup()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								jPanel3,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jPanel1,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								0,
																								380,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jPanel2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				438,
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
																javax.swing.GroupLayout.Alignment.LEADING)
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
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
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
										.addContainerGap()));

		((ButtonNuevo) btnNuevo).init();
		((ButtonModificar) btnModificar).init();
		((ButtonEliminar) btnEliminar).init();
		((ButtonGuardar) btnGuardar).init();
		((ButtonCancelarConTexto) btnCancelar).init();
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnExaminarFotoActionPerformed(java.awt.event.ActionEvent evt) {
		buscarImagen(lbFirma);
	}

	public void buscarImagen(final JLabel label) {

		final VentanaSeleccionImagen internalframe = new VentanaSeleccionImagen(
				new Dimension(200, 110), true);
		internalframe.pack();
		Util.agregarIframe(internalframe);

		internalframe.doModal(Util.framePrincipal.getRootPane());
		internalframe.setVisible(true);
		if (internalframe.getImg() != null) {
			ImageIcon imgIcon = new ImageIcon(internalframe.getImg());
			label.setIcon(imgIcon);
		}

	}

	private void checkPuedeFirmarActionPerformed(java.awt.event.ActionEvent evt) {

		if (checkPuedeFirmar.isSelected()) {
			btnExaminarFirma.setEnabled(true);
			btnCancelarFirma.setEnabled(true);
			btnExaminarFoto.setEnabled(true);
		}
	}

	private void btnExaminarFirmaActionPerformed(java.awt.event.ActionEvent evt) {
		final VentanaTomarFirma internalframe = new VentanaTomarFirma();
		internalframe.pack();
		Util.agregarIframe(internalframe);

		internalframe.doModal(Util.frameContenedor.getRootPane());
		internalframe.setVisible(true);
		if (internalframe.getImg() != null) {
			ImageIcon imgIcon = new ImageIcon(internalframe.getImg());
			lbFirma.setIcon(imgIcon);
		}
	}

	private void btnCancelarFirmaActionPerformed(java.awt.event.ActionEvent evt) {
		lbFirma.setIcon(null);
		usuario.setUsrFirma(null);
	}

	private void txtCargoFocusLost(java.awt.event.FocusEvent evt) {

	}

	private void txtUsuarioFocusLost(java.awt.event.FocusEvent evt) {
		//txtUsuario.setText(txtUsuario.getText().toLowerCase());
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
				JOptionPaneTesterGral.showInternalMessageDialog(
						Constantes.MENSAJE_ELIMINADO,
						Constantes.MENSAJE_ELIMINADO_TIT,
						JOptionPane.INFORMATION_MESSAGE);

				txtBusquedaNombre.setText(null);
				habilitar(false);
				cargarUsuarios();

			}
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			JOptionPaneTesterGral.showInternalError(Util.frameContenedor,
					new Throwable(Constantes.MENSAJE_ERROR_ELIMINAR));
		} catch (Exception e) {
			throw new RuntimeException(e);
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
		//seleccionarUsuario();
	}

	public void seleccionarUsuario() {
		log.info("seleccionarUsuario");

		int selected = tableUsuarios.getSelectedRow();
		selected = tableUsuarios.convertRowIndexToModel(selected);
		usuario = ((TableModelUsuario) tableUsuarios.getModel())
				.getValueAt(selected);

		//table.convertRowIndexToModel(selectedRow)

		inicializar();

		txtUsuario.setText(usuario.getUsrNombre());
		txtClave.setText(usuario.getUsrClave());
		txtClave2.setText(usuario.getUsrClave());
		txtCargo.setText(usuario.getUsrCargo());

		if (usuario.getUsrFirma() != null && usuario.getUsrFirma().length > 1) {
			ImageIcon icon = new ImageIcon(usuario.getUsrFirma());
			lbFirma.setIcon(icon);
		}

		if (usuario.getUsrHabilitadoSn().compareTo("S") == 0)
			checkHabilitado.setSelected(true);

		if (usuario.getUsrPuedeFirmarSn().compareTo("S") == 0) {
			checkPuedeFirmar.setSelected(true);
		}

		if (usuario.getUsrModificarLicSn().compareTo("S") == 0)
			checkPuedeCrearLic.setSelected(true);

		if (usuario.getUsrAccPanelControlSn().compareTo("S") == 0)
			checkPuedeIngresarPC.setSelected(true);

		if (usuario.getUsrAdmUsrSn().compareTo("S") == 0)
			checkPuedeAdmUsr.setSelected(true);

		if (usuario.getUsrAutorizadoSn().compareTo("S") == 0)
			checkPuedeLicInc.setSelected(true);

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

		Util.mostrarError(lbError, null, true);
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
			usuario.setUsrCargo(txtCargo.getText());
			usuario.setDeletedSn("S");

			String usrHabilitadoSn = new String();
			if (checkHabilitado.isSelected())
				usrHabilitadoSn = "S";
			else
				usrHabilitadoSn = "N";

			usuario.setUsrHabilitadoSn(usrHabilitadoSn);
			usuario.setUsrPuedeFirmarSn(checkPuedeFirmar.isSelected() ? "S"
					: "N");
			usuario.setUsrModificarLicSn(checkPuedeCrearLic.isSelected() ? "S"
					: "N");
			usuario
					.setUsrAccPanelControlSn(checkPuedeIngresarPC.isSelected() ? "S"
							: "N");
			usuario.setUsrAdmUsrSn(checkPuedeAdmUsr.isSelected() ? "S" : "N");
			usuario.setUsrAutorizadoSn(checkPuedeLicInc.isSelected() ? "S"
					: "N");

			usuario.setUsrRestaurarBackSn("N");
			usuario.setUsrResetearSn("N");
			usuario.setUsrControlAdmSn("N");

			byte[] bytes = getImageToArray(lbFirma);
			if (bytes != null) {
				usuario.setUsrFirma(bytes);
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
				JOptionPaneTesterGral.showInternalMessageDialog(
						Constantes.MENSAJE_GUARDADO,
						Constantes.MENSAJE_GUARDADO_TIT,
						JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}

	public boolean validarUsuario() {
		log.info("validarUsuario");

		if (txtUsuario.getText() == null || txtUsuario.getText().equals("")) {
			Util.mostrarError(lbError, Constantes.ERROR_USR_NOMBRE, false);
			return false;
		}

		if (usuario.getUsrNombre() == null
				&& existeUsuario(txtUsuario.getText())) {
			Util.mostrarError(lbError, Constantes.ERROR_USR_EXISTE, false);
			return false;
		}

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

	public boolean existeUsuario(String usr_nombre) {
		try {
			log.info("existeUsuario");

			Usuario usu = (Usuario) usuarioService.getUsrName(usr_nombre,
					usuario.getClass());

			if (usu != null)
				return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return false;
	}

	public void afterButton() {
		log.info("afterButton");

		inicializar();
		habilitar(true);
		usuario = new Usuario();
	}

	public void inicializar() {
		log.info("inicializar");

		txtUsuario.setText(null);
		txtClave.setText(null);
		txtClave2.setText(null);
		txtCargo.setText(null);
		checkHabilitado.setSelected(false);

		checkPuedeFirmar.setSelected(false);
		checkPuedeCrearLic.setSelected(false);
		checkPuedeIngresarPC.setSelected(false);
		checkPuedeAdmUsr.setSelected(false);
		checkPuedeLicInc.setSelected(false);
		lbFirma.setIcon(null);

		Util.mostrarError(lbError, "", true);
	}

	public void habilitar(boolean habilitar) {
		log.info("habilitar");

		txtUsuario.setEnabled(habilitar);
		txtClave.setEnabled(habilitar);
		txtClave2.setEnabled(habilitar);
		txtCargo.setEnabled(habilitar);
		checkHabilitado.setEnabled(habilitar);

		checkPuedeFirmar.setEnabled(habilitar);

		if (checkPuedeFirmar.isSelected()) {
			btnCancelarFirma.setEnabled(habilitar);
			btnExaminarFirma.setEnabled(habilitar);
			btnExaminarFoto.setEnabled(habilitar);
		} else {
			btnCancelarFirma.setEnabled(false);
			btnExaminarFirma.setEnabled(false);
			btnExaminarFoto.setEnabled(false);
		}

		checkPuedeCrearLic.setEnabled(habilitar);
		checkPuedeIngresarPC.setEnabled(habilitar);
		checkPuedeAdmUsr.setEnabled(habilitar);
		checkPuedeLicInc.setEnabled(habilitar);
	}

	public void cargarUsuarios() {
		try {
			log.info("cargarUsuarios");

			Util.ocultarSinResultados(lbSinResultados, true);
			Usuario usu = new Usuario();
			usu.setDeletedSn(null);
			usu.setUsrNombre(txtBusquedaNombre.getText());

			List<Usuario> usuarios = usuarioService.getAll(usu);
			TableModelUsuario tableModel = new TableModelUsuario();
			tableModel.setLstUsuario(usuarios);
			tableUsuarios.setModel(tableModel);
			tableUsuarios.setAutoCreateRowSorter(false);

			if (usuarios.size() == 0)
				Util.ocultarSinResultados(lbSinResultados, false);
			else
				tableUsuarios.setAutoCreateRowSorter(true);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	class SharedListSelectionHandler implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();
			if (!lsm.isSelectionEmpty()) {
				seleccionarUsuario();
				lsm.clearSelection();
			}
		}
	}

	public byte[] getImageToArray(JLabel label) {
		byte[] byteArray = null;
		if (label.getIcon() != null) {
			ImageIcon imageIcon = (ImageIcon) label.getIcon();
			Image img = imageIcon.getImage();
			BufferedImage bi = new BufferedImage(imageIcon.getIconWidth(),
					imageIcon.getIconHeight(), BufferedImage.TYPE_INT_RGB);

			Graphics2D big = bi.createGraphics();
			big.drawImage(img, 0, 0, this);

			ByteArrayOutputStream os = new ByteArrayOutputStream();

			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
			try {
				encoder.encode(bi);
				byteArray = os.toByteArray();
			} catch (Exception e) {
				JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}

		}

		return byteArray;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnBuscar;
	private javax.swing.JButton btnCancelar;
	private javax.swing.JButton btnCancelarFirma;
	private javax.swing.JButton btnEliminar;
	private javax.swing.JButton btnExaminarFirma;
	private javax.swing.JButton btnExaminarFoto;
	private javax.swing.JButton btnGuardar;
	private javax.swing.JButton btnModificar;
	private javax.swing.JButton btnNuevo;
	private javax.swing.JCheckBox checkHabilitado;
	private javax.swing.JCheckBox checkPuedeAdmUsr;
	private javax.swing.JCheckBox checkPuedeCrearLic;
	private javax.swing.JCheckBox checkPuedeFirmar;
	private javax.swing.JCheckBox checkPuedeIngresarPC;
	private javax.swing.JCheckBox checkPuedeLicInc;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lbError;
	private javax.swing.JLabel lbFirma;
	private javax.swing.JLabel lbSinResultados;
	private javax.swing.JTable tableUsuarios;
	private javax.swing.JTextField txtBusquedaNombre;
	private javax.swing.JTextField txtCargo;
	private javax.swing.JPasswordField txtClave;
	private javax.swing.JPasswordField txtClave2;
	private javax.swing.JTextField txtUsuario;
	// End of variables declaration//GEN-END:variables
	//private List<Permiso> permisos;
	private UsuarioDefinition usuarioService = (UsuarioDefinition) ContextManager
			.getBizObject("usuarioService");
	private Usuario usuario = new Usuario();

}