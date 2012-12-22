/*
 * PanelPersona.java
 *
 * Created on __DATE__, __TIME__
 */

package autoimpresor.frontend.paneles;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.comparetors.DateComparator;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.Dominio;
import testerGeneral.domain.Propiedad;
import testerGeneral.focus.MyOwnFocusTraversalPolicy;
import autoimpresor.business.ContextManager;
import autoimpresor.domain.Licencia;
import autoimpresor.domain.Persona;
import autoimpresor.domain.Usuario;
import autoimpresor.frontend.tablemodels.TableModelLicencia;
import autoimpresor.frontend.tablemodels.TableModelPersona;
import autoimpresor.service.LicenciaDefinition;
import autoimpresor.service.PersonaDefinition;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import frontend.buttons.ButtonAgregarMini;
import frontend.buttons.ButtonBuscar;
import frontend.buttons.ButtonCancelarConTexto;
import frontend.buttons.ButtonCancelarMini;
import frontend.buttons.ButtonEliminar;
import frontend.buttons.ButtonExaminar;
import frontend.buttons.ButtonGuardar;
import frontend.buttons.ButtonModificar;
import frontend.buttons.ButtonNuevo;
import frontend.components.JOptionPaneTesterGral;
import frontend.components.PanelMensajes;
import frontend.paneles.examenes.Finalisable;
import frontend.utils.Util;
import frontend.ventanas.JInternalFrameTesterGral;
import frontend.ventanas.VentanaSeleccionImagen;
import frontend.ventanas.VentanaTomarFirma;

/**
 *
 * @author  __USER__
 */
public class PanelPersona extends javax.swing.JPanel implements Finalisable {

	private static final Log log = LogFactory.getLog(PanelPersona.class);
	private PersonaDefinition personaService = (PersonaDefinition) ContextManager
			.getBizObject("personaService");

	/** Creates new form PanelPersona */
	public PanelPersona(PanelMenuPrincipal menu) {

		this.menu = menu;

		initComponents();
		btnCancelarFirma.setText("Borrar");
		btnCancelarFoto.setText("Borrar");

		cargarListboxs();

		setTableModel(new ArrayList());
		setTableModelLicencias(new ArrayList());
		Util.personaSinResultados(lbSinResultados, true);
		Util.mostrarError(lbError, null, true);

		Vector<Component> order = new Vector<Component>();

		order.add(txtBusquedaDni);
		order.add(txtBusquedaApellido);
		order.add(txtBusquedaNombre);
		order.add(txtBusquedaNacimiento);
		order.add(cbBusquedaGrupoSanguineo);
		order.add(btnBuscar);
		order.add(tablePersona);
		order.add(txtApellido);
		order.add(txtNombre);
		order.add(cbTipoDoc);
		order.add(txtNroDoc);
		order.add(cbSexo);
		order.add(txtNacimiento);
		order.add(cbGrupoSanguineo);
		order.add(cbNacionalidad);
		order.add(cbDonante);
		order.add(txtDomicilio);
		order.add(txtTelefono);
		order.add(txtRestricciones);
		order.add(txtMedicacion);
		order.add(txtAlergico);
		order.add(btnExaminarFirma);
		order.add(btnExaminarFoto);
		order.add(btnGuardar);
		order.add(btnCancelar);
		MyOwnFocusTraversalPolicy newPolicy = new MyOwnFocusTraversalPolicy(
				order);

		this.setFocusTraversalPolicy(newPolicy);

		lbFechaEjemplo.setText(Util.mostrasMascara());
		//lbFechaEjemplo2.setText(Util.mostrasMascara());

		valorPorDefectoFecha = txtBusquedaNacimiento.getText();

		habilitar(false);

		tablePersona.getSelectionModel().addListSelectionListener(
				new SharedListSelectionHandler());
		//btnAgregarTipoDoc.setVisible(false);
		//btnAgregarNacionalidad.setVisible(false);
		
	}


	
	public void cargarListboxs() {

		Util.cargarDominios(cbBusquedaGrupoSanguineo,
				Constantes.DOMINIO_CLAVE_GRUPO_SAN, true);

		Util.cargarDominios(cbGrupoSanguineo,
				Constantes.DOMINIO_CLAVE_GRUPO_SAN, false);

		Util.cargarDominios(cbSexo, Constantes.DOMINIO_CLAVE_SEXO, false);

		Util
				.cargarDominios(cbTipoDoc, Constantes.DOMINIO_CLAVE_TIPO_DOC,
						false);
		Util.selectDominios(cbTipoDoc, "DNI-DNI");

		Util.cargarDominios(cbNacionalidad,
				Constantes.DOMINIO_CLAVE_NACIONALIDAD, false);
		Util.selectDominios(cbNacionalidad, "ARG");

		Util.cargarDominios(cbDonante, Constantes.DOMINIO_CLAVE_DONANTE, false);

	}

	private void calcularEdad() {
		Date nacimiento = getFechaNacimiento();
		Util.calcularEdad(lbEdad, nacimiento);
	}

	public void habilitar(boolean habilitar) {
		log.info("inicio  habilitar:" + habilitar);

		txtApellido.setEnabled(habilitar);
		txtNombre.setEnabled(habilitar);
		cbTipoDoc.setEnabled(habilitar);
		txtNroDoc.setEnabled(habilitar);
		cbSexo.setEnabled(habilitar);
		txtNacimiento.setEnabled(habilitar);
		cbGrupoSanguineo.setEnabled(habilitar);
		cbNacionalidad.setEnabled(habilitar);
		cbDonante.setEnabled(habilitar);

		txtDomicilio.setEnabled(habilitar);
		txtTelefono.setEnabled(habilitar);
		txtRestricciones.setEnabled(habilitar);
		txtRestricciones.setEnabled(habilitar);
		txtMedicacion.setEnabled(habilitar);
		txtAlergico.setEnabled(habilitar);
		txtMedicacion.setEnabled(habilitar);
		txtAlergico.setEnabled(habilitar);
		btnExaminarFirma.setEnabled(habilitar);
		btnExaminarFoto.setEnabled(habilitar);
		btnCancelarFirma.setEnabled(habilitar);
		btnCancelarFoto.setEnabled(habilitar);

		habilitarBtnNuevaLicencia(habilitar);
		btnVerLicenciasEmitidas.setEnabled(habilitar);
		btnDuplicarLicencia.setEnabled(habilitar);

		log.info("fin  habilitar: " + habilitar);
	}

	public void habilitarBtnNuevaLicencia(boolean habilitar) {
		if (((Usuario) Util.usuarioCommon).getUsrModificarLicSn().equals("S"))
			btnNuevaLicencia
					.setEnabled(habilitar && persona.getPerId() != null);
		else
			btnNuevaLicencia.setEnabled(false);
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel6 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		txtBusquedaApellido = new javax.swing.JTextField();
		btnBuscar = new ButtonBuscar();
		txtBusquedaNombre = new javax.swing.JTextField();
		jLabel19 = new javax.swing.JLabel();
		jLabel20 = new javax.swing.JLabel();
		jLabel21 = new javax.swing.JLabel();
		cbBusquedaGrupoSanguineo = new javax.swing.JComboBox();
		jLabel8 = new javax.swing.JLabel();
		txtBusquedaDni = new javax.swing.JFormattedTextField();
		txtBusquedaNacimiento = Util.setFecha();
		lbFechaEjemplo = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tablePersona = new javax.swing.JTable();
		lbSinResultados = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jPanel8 = new javax.swing.JPanel();
		jLabel12 = new javax.swing.JLabel();
		txtTelefono = new javax.swing.JTextField();
		jLabel14 = new javax.swing.JLabel();
		txtRestricciones = new javax.swing.JTextField();
		jLabel15 = new javax.swing.JLabel();
		txtMedicacion = new javax.swing.JTextField();
		jLabel16 = new javax.swing.JLabel();
		txtAlergico = new javax.swing.JTextField();
		jPanel5 = new javax.swing.JPanel();
		jLabel11 = new javax.swing.JLabel();
		txtDomicilio = new javax.swing.JTextField();
		jFormattedTextField1 = new javax.swing.JFormattedTextField();
		jLabel9 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		jFormattedTextField2 = new javax.swing.JFormattedTextField();
		txtRestricciones2 = new javax.swing.JTextField();
		jLabel17 = new javax.swing.JLabel();
		jLabel23 = new javax.swing.JLabel();
		txtRestricciones3 = new javax.swing.JTextField();
		jLabel24 = new javax.swing.JLabel();
		txtRestricciones4 = new javax.swing.JTextField();
		txtRestricciones5 = new javax.swing.JTextField();
		jLabel25 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jPanel7 = new javax.swing.JPanel();
		txtNombre = new javax.swing.JTextField();
		txtNroDoc = new javax.swing.JFormattedTextField();
		cbTipoDoc = new javax.swing.JComboBox();
		jLabel2 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		cbGrupoSanguineo = new javax.swing.JComboBox();
		jLabel1 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		cbSexo = new javax.swing.JComboBox();
		jLabel6 = new javax.swing.JLabel();
		lbEdad = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		txtApellido = new javax.swing.JTextField();
		txtNacimiento = Util.setFecha();
		jLabel18 = new javax.swing.JLabel();
		cbNacionalidad = new javax.swing.JComboBox();
		cbDonante = new javax.swing.JComboBox();
		jLabel22 = new javax.swing.JLabel();
		jPanel9 = new javax.swing.JPanel();
		lbFoto = new javax.swing.JLabel();
		btnExaminarFoto = new ButtonExaminar();
		lbFirma = new javax.swing.JLabel();
		btnExaminarFirma = new ButtonExaminar();
		btnCancelarFirma = new ButtonCancelarMini();
		btnCancelarFoto = new ButtonCancelarMini();
		jPanel4 = new javax.swing.JPanel();
		jScrollPane3 = new javax.swing.JScrollPane();
		tableLicencias = new javax.swing.JTable();
		btnVerLicenciasEmitidas = new ButtonNuevo();
		btnNuevaLicencia = new ButtonNuevo();
		btnDuplicarLicencia = new ButtonNuevo();
		lbError = new javax.swing.JLabel();
		btnGuardar = new ButtonGuardar();
		btnCancelar = new ButtonCancelarConTexto();
		btnNuevo = new ButtonNuevo();
		btnModificar = new ButtonModificar();
		btnEliminar = new ButtonEliminar();

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(
				jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 100,
				Short.MAX_VALUE));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 100,
				Short.MAX_VALUE));

		setFocusCycleRoot(true);

		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.PANEL_FILTROS_BUSQUEDA,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel3.setFocusable(false);

		jLabel3.setText(Constantes.LB_APELLIDO);

		txtBusquedaApellido.setMaximumSize(new java.awt.Dimension(153, 22));
		txtBusquedaApellido
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						txtBusquedaApellidoActionPerformed(evt);
					}
				});

		btnBuscar.setToolTipText("Buscar");
		btnBuscar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnBuscarActionPerformed(evt);
			}
		});

		txtBusquedaNombre.setMaximumSize(new java.awt.Dimension(153, 22));
		txtBusquedaNombre
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						txtBusquedaNombreActionPerformed(evt);
					}
				});

		jLabel19.setText(Constantes.LB_NOMBRE);

		jLabel20.setText(Constantes.LB_FECHA_NACIMIENTO);

		jLabel21.setText(Constantes.LB_GRUPO_SANGUIENEO);

		cbBusquedaGrupoSanguineo.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		jLabel8.setText(Constantes.LB_NRO_DOCUMENTO);

		txtBusquedaDni.setMaximumSize(new java.awt.Dimension(153, 22));
		txtBusquedaDni.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtBusquedaDniActionPerformed(evt);
			}
		});

		txtBusquedaNacimiento.setMaximumSize(new java.awt.Dimension(88, 22));
		txtBusquedaNacimiento
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						txtBusquedaNacimientoActionPerformed(evt);
					}
				});

		lbFechaEjemplo.setFont(new java.awt.Font("Segoe UI", 0, 8));
		lbFechaEjemplo.setText("jLabel17");

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabel8,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																112,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel3Layout
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
																																jLabel19,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																113,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																jLabel3,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																112,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																jLabel20))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addGroup(
																												jPanel3Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																txtBusquedaNombre,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																130,
																																Short.MAX_VALUE)
																														.addComponent(
																																txtBusquedaNacimiento,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																130,
																																Short.MAX_VALUE)
																														.addComponent(
																																txtBusquedaDni,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																130,
																																Short.MAX_VALUE)
																														.addGroup(
																																jPanel3Layout
																																		.createSequentialGroup()
																																		.addGroup(
																																				jPanel3Layout
																																						.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.TRAILING,
																																								false)
																																						.addComponent(
																																								cbBusquedaGrupoSanguineo,
																																								javax.swing.GroupLayout.Alignment.LEADING,
																																								0,
																																								javax.swing.GroupLayout.DEFAULT_SIZE,
																																								Short.MAX_VALUE)
																																						.addComponent(
																																								lbFechaEjemplo,
																																								javax.swing.GroupLayout.Alignment.LEADING,
																																								javax.swing.GroupLayout.DEFAULT_SIZE,
																																								75,
																																								Short.MAX_VALUE))
																																		.addPreferredGap(
																																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																		.addComponent(
																																				btnBuscar,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				48,
																																				javax.swing.GroupLayout.PREFERRED_SIZE))
																														.addComponent(
																																txtBusquedaApellido,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																130,
																																Short.MAX_VALUE))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED))
																						.addGroup(
																								jPanel3Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel21)
																										.addGap(
																												130,
																												130,
																												130)))
																		.addGap(
																				38,
																				38,
																				38)))));
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
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel8)
														.addComponent(
																txtBusquedaDni,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3)
														.addComponent(
																txtBusquedaApellido,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel19)
														.addComponent(
																txtBusquedaNombre,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel20)
														.addComponent(
																txtBusquedaNacimiento,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addGap(
																				2,
																				2,
																				2)
																		.addComponent(
																				lbFechaEjemplo)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addGroup(
																				jPanel3Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel21,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								22,
																								Short.MAX_VALUE)
																						.addComponent(
																								cbBusquedaGrupoSanguineo,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(
																				17,
																				17,
																				17))
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnBuscar,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				48,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addContainerGap()))));

		((ButtonBuscar) btnBuscar).init();

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.PANEL_RESULTADOS_BUSQUEDA,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel1.setFocusable(false);

		tablePersona.setModel(new javax.swing.table.DefaultTableModel(
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
		tablePersona.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tablePersonaMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(tablePersona);

		lbSinResultados.setFont(new java.awt.Font("Segoe UI", 0, 10));
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
																javax.swing.GroupLayout.Alignment.LEADING,
																jPanel1Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jScrollPane1,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				246,
																				Short.MAX_VALUE))
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
																				246,
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
												265,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												9, Short.MAX_VALUE)
										.addComponent(
												lbSinResultados,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												24,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.PANEL_DATOS_PERSONA,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel2.setFocusable(false);

		jLabel12.setText("Tel\u00e9fono Emergencia:");

		txtTelefono.setMaximumSize(new java.awt.Dimension(150, 22));
		txtTelefono.setMinimumSize(new java.awt.Dimension(150, 22));

		jLabel14.setText("Restricciones:");

		txtRestricciones.setMaximumSize(new java.awt.Dimension(150, 22));
		txtRestricciones.setMinimumSize(new java.awt.Dimension(150, 22));

		jLabel15.setText("Medicamentos:");

		txtMedicacion.setMaximumSize(new java.awt.Dimension(150, 22));
		txtMedicacion.setMinimumSize(new java.awt.Dimension(150, 22));

		jLabel16.setText("Al\u00e9rgico a:");

		txtAlergico.setMaximumSize(new java.awt.Dimension(150, 22));
		txtAlergico.setMinimumSize(new java.awt.Dimension(150, 22));

		jPanel5.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Domicilio"));

		jLabel11.setText("Calle:");

		txtDomicilio.setMaximumSize(new java.awt.Dimension(150, 22));
		txtDomicilio.setMinimumSize(new java.awt.Dimension(150, 22));

		jLabel9.setText("Nro:");

		jLabel13.setText("Piso:");

		txtRestricciones2.setMaximumSize(new java.awt.Dimension(150, 22));
		txtRestricciones2.setMinimumSize(new java.awt.Dimension(150, 22));

		jLabel17.setText("Dpt:");

		jLabel23.setText("Barrio:");

		txtRestricciones3.setMaximumSize(new java.awt.Dimension(150, 22));
		txtRestricciones3.setMinimumSize(new java.awt.Dimension(150, 22));

		jLabel24.setText("C/P:");

		txtRestricciones4.setMaximumSize(new java.awt.Dimension(150, 22));
		txtRestricciones4.setMinimumSize(new java.awt.Dimension(150, 22));

		txtRestricciones5.setMaximumSize(new java.awt.Dimension(150, 22));
		txtRestricciones5.setMinimumSize(new java.awt.Dimension(150, 22));

		jLabel25.setText("Localidad:");

		jButton1.setText("Buscar");

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(
				jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout
				.setHorizontalGroup(jPanel5Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel5Layout
										.createSequentialGroup()
										.addContainerGap(17, Short.MAX_VALUE)
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel25)
														.addComponent(jLabel23)
														.addComponent(jLabel13)
														.addComponent(jLabel11))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel5Layout
																		.createSequentialGroup()
																		.addComponent(
																				txtRestricciones5,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				132,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton1))
														.addGroup(
																jPanel5Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				false)
																		.addGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				jPanel5Layout
																						.createSequentialGroup()
																						.addComponent(
																								txtDomicilio,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								120,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								jLabel9)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																						.addComponent(
																								jFormattedTextField1))
																		.addGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				jPanel5Layout
																						.createSequentialGroup()
																						.addComponent(
																								jFormattedTextField2,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								36,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								jLabel17)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								txtRestricciones2,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								36,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addGap(
																								13,
																								13,
																								13)
																						.addComponent(
																								jLabel24)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								txtRestricciones4,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								57,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addComponent(
																				txtRestricciones3,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))));
		jPanel5Layout
				.setVerticalGroup(jPanel5Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel5Layout
										.createSequentialGroup()
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel11)
														.addComponent(
																txtDomicilio,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																22,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel9)
														.addComponent(
																jFormattedTextField1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jFormattedTextField2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel17)
														.addComponent(
																txtRestricciones2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																22,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel24)
														.addComponent(jLabel13)
														.addComponent(
																txtRestricciones4,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																22,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel23)
														.addComponent(
																txtRestricciones3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																22,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel5Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				txtRestricciones5,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				22,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				jButton1))
														.addComponent(jLabel25))));

		txtNombre.setMaximumSize(new java.awt.Dimension(150, 22));
		txtNombre.setMinimumSize(new java.awt.Dimension(150, 22));
		txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtNombreFocusLost(evt);
			}
		});

		txtNroDoc.setMaximumSize(new java.awt.Dimension(150, 22));
		txtNroDoc.setMinimumSize(new java.awt.Dimension(150, 22));

		cbTipoDoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));
		cbTipoDoc.setMaximumSize(new java.awt.Dimension(150, 22));
		cbTipoDoc.setMinimumSize(new java.awt.Dimension(150, 22));

		jLabel2.setText(Constantes.LB_NOMBRE);

		jLabel10.setText(Constantes.LB_GRUPO_SANGUIENEO);

		cbGrupoSanguineo.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		cbGrupoSanguineo.setMaximumSize(new java.awt.Dimension(150, 22));
		cbGrupoSanguineo.setMinimumSize(new java.awt.Dimension(150, 22));

		jLabel1.setText(Constantes.LB_APELLIDO);

		jLabel4.setText("Tipo de Doc:");

		cbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));
		cbSexo.setMaximumSize(new java.awt.Dimension(150, 22));
		cbSexo.setMinimumSize(new java.awt.Dimension(150, 22));

		jLabel6.setText(Constantes.LB_SEXO);

		lbEdad.setFont(new java.awt.Font("Segoe UI", 0, 11));
		lbEdad.setText(Constantes.LB_EDAD);
		lbEdad.setPreferredSize(new java.awt.Dimension(69, 22));

		jLabel5.setText(Constantes.LB_NRO_DOCUMENTO);

		jLabel7.setText(Constantes.LB_FECHA_NACIMIENTO);

		txtApellido.setMaximumSize(new java.awt.Dimension(150, 22));
		txtApellido.setMinimumSize(new java.awt.Dimension(150, 22));
		txtApellido.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtApellidoFocusLost(evt);
			}
		});

		txtNacimiento.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtNacimientoFocusLost(evt);
			}
		});

		jLabel18.setText("Pa\u00edz:");

		cbNacionalidad.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		cbNacionalidad.setMaximumSize(new java.awt.Dimension(150, 22));
		cbNacionalidad.setMinimumSize(new java.awt.Dimension(150, 22));

		cbDonante.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));
		cbDonante.setMaximumSize(new java.awt.Dimension(150, 22));
		cbDonante.setMinimumSize(new java.awt.Dimension(150, 22));

		jLabel22.setText("Donante:");

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(
				jPanel7);
		jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout
				.setHorizontalGroup(jPanel7Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel7Layout
										.createSequentialGroup()
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jLabel10,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel18,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel7,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel6,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel5,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel2,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel4,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																69,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																jPanel7Layout
																		.createSequentialGroup()
																		.addComponent(
																				cbGrupoSanguineo,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				57,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jLabel22)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				cbDonante,
																				0,
																				53,
																				Short.MAX_VALUE))
														.addComponent(cbSexo,
																0, 200,
																Short.MAX_VALUE)
														.addComponent(
																txtNroDoc,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																200,
																Short.MAX_VALUE)
														.addComponent(
																cbNacionalidad,
																0, 200,
																Short.MAX_VALUE)
														.addComponent(
																txtNombre,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																200,
																Short.MAX_VALUE)
														.addComponent(
																cbTipoDoc, 0,
																200,
																Short.MAX_VALUE)
														.addGroup(
																jPanel7Layout
																		.createSequentialGroup()
																		.addComponent(
																				txtNacimiento,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				78,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				lbEdad,
																				0,
																				0,
																				Short.MAX_VALUE))
														.addComponent(
																txtApellido,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																200,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(19, Short.MAX_VALUE)));

		jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
				new java.awt.Component[] { cbSexo, cbTipoDoc, txtNombre,
						txtNroDoc });

		jPanel7Layout
				.setVerticalGroup(jPanel7Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel7Layout
										.createSequentialGroup()
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																txtApellido,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																txtNombre,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																cbTipoDoc,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel4))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel5)
														.addComponent(
																txtNroDoc,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel6)
														.addComponent(
																cbSexo,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																txtNacimiento,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel7)
														.addComponent(
																lbEdad,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel10)
														.addComponent(
																cbGrupoSanguineo,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel22)
														.addComponent(
																cbDonante,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel18)
														.addComponent(
																cbNacionalidad,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(
				jPanel8);
		jPanel8.setLayout(jPanel8Layout);
		jPanel8Layout
				.setHorizontalGroup(jPanel8Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel8Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jPanel5,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel12,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				125,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				txtTelefono,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				150,
																				Short.MAX_VALUE))
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel14)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				txtRestricciones,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				150,
																				Short.MAX_VALUE))
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel15,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				117,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				txtMedicacion,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				150,
																				Short.MAX_VALUE))
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel16,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				117,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				txtAlergico,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				150,
																				Short.MAX_VALUE))
														.addComponent(
																jPanel7,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
				new java.awt.Component[] { jLabel12, jLabel14, jLabel15,
						jLabel16 });

		jPanel8Layout
				.setVerticalGroup(jPanel8Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel8Layout
										.createSequentialGroup()
										.addComponent(
												jPanel7,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, 0)
										.addComponent(
												jPanel5,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, 0)
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel12)
														.addComponent(
																txtTelefono,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																22,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel14)
														.addComponent(
																txtRestricciones,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																22,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel15)
														.addComponent(
																txtMedicacion,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																22,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel16)
														.addComponent(
																txtAlergico,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																22,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jPanel9.setOpaque(false);

		lbFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbFoto.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
		lbFoto.setBorder(javax.swing.BorderFactory
				.createTitledBorder(Constantes.LB_FOTO));
		lbFoto.setMaximumSize(new java.awt.Dimension(246, 176));
		lbFoto.setMinimumSize(new java.awt.Dimension(246, 176));

		btnExaminarFoto.setIcon(new ImageIcon(getClass().getResource(
				"/images/agregar.png")));
		btnExaminarFoto.setToolTipText("Examinar");
		((ButtonExaminar) btnExaminarFoto).init();
		btnExaminarFoto.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExaminarFotoActionPerformed(evt);
			}
		});

		lbFirma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbFirma.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
		lbFirma.setBorder(javax.swing.BorderFactory
				.createTitledBorder(Constantes.LB_FIRMA));
		lbFirma.setMaximumSize(new java.awt.Dimension(246, 99));
		lbFirma.setMinimumSize(new java.awt.Dimension(246, 99));

		btnExaminarFirma.setIcon(new ImageIcon(getClass().getResource(
				"/images/agregar.png")));
		btnExaminarFirma.setToolTipText("Examinar");
		((ButtonExaminar) btnExaminarFirma).init();
		btnExaminarFirma.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExaminarFirmaActionPerformed(evt);
			}
		});

		btnCancelarFirma.setIcon(new ImageIcon(getClass().getResource(
				"/images/agregar.png")));
		btnCancelarFirma.setText("Borrar");
		btnCancelarFirma.setToolTipText("Examinar");
		((ButtonCancelarMini) btnCancelarFirma).init();
		btnCancelarFirma.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarFirmaActionPerformed(evt);
			}
		});

		btnCancelarFoto.setIcon(new ImageIcon(getClass().getResource(
				"/images/agregar.png")));
		btnCancelarFoto.setText("Borrar");
		btnCancelarFoto.setToolTipText("Examinar");
		((ButtonCancelarMini) btnCancelarFoto).init();
		btnCancelarFoto.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarFotoActionPerformed(evt);
			}
		});

		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Licencias Emitidas",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel4.setFocusable(false);

		tableLicencias.setModel(new javax.swing.table.DefaultTableModel(
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
		tableLicencias.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tableLicenciasMouseClicked(evt);
			}
		});
		jScrollPane3.setViewportView(tableLicencias);

		btnVerLicenciasEmitidas.setText("Ver");
		btnVerLicenciasEmitidas
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnVerLicenciasEmitidasActionPerformed(evt);
					}
				});

		btnNuevaLicencia.setText("Generar Nueva Licencia");
		btnNuevaLicencia.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnNuevaLicenciaActionPerformed(evt);
			}
		});

		btnDuplicarLicencia.setText("Emitir Duplicado");
		btnDuplicarLicencia
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnDuplicarLicenciaActionPerformed(evt);
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
										.addComponent(
												btnNuevaLicencia,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												146,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btnDuplicarLicencia,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												110,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btnVerLicenciasEmitidas,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												57, Short.MAX_VALUE)
										.addContainerGap()).addComponent(
								jScrollPane3,
								javax.swing.GroupLayout.DEFAULT_SIZE, 339,
								Short.MAX_VALUE));
		jPanel4Layout
				.setVerticalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel4Layout
										.createSequentialGroup()
										.addComponent(
												jScrollPane3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												153,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																btnNuevaLicencia,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnDuplicarLicencia,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnVerLicenciasEmitidas,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));

		((ButtonNuevo) btnNuevo).init();
		((ButtonNuevo) btnNuevo).init();
		((ButtonNuevo) btnNuevo).init();

		javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(
				jPanel9);
		jPanel9.setLayout(jPanel9Layout);
		jPanel9Layout
				.setHorizontalGroup(jPanel9Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel9Layout
										.createSequentialGroup()
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel9Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel9Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								lbFirma,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								299,
																								Short.MAX_VALUE)
																						.addComponent(
																								lbFoto,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								287,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(
																				0,
																				0,
																				0)
																		.addGroup(
																				jPanel9Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel9Layout
																										.createSequentialGroup()
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												btnCancelarFoto,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												78,
																												Short.MAX_VALUE))
																						.addComponent(
																								btnCancelarFirma,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								78,
																								Short.MAX_VALUE)
																						.addComponent(
																								btnExaminarFirma,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								78,
																								Short.MAX_VALUE)
																						.addComponent(
																								btnExaminarFoto,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								78,
																								Short.MAX_VALUE)))
														.addComponent(
																jPanel4,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		jPanel9Layout
				.setVerticalGroup(jPanel9Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel9Layout
										.createSequentialGroup()
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																jPanel9Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				btnCancelarFoto,
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
																lbFoto,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																137,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																jPanel9Layout
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
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																lbFirma,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																130,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel4,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												202,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		lbError.setBackground(new java.awt.Color(204, 0, 0));
		lbError.setFont(new java.awt.Font("Segoe UI", 0, 10));
		lbError.setForeground(new java.awt.Color(204, 0, 0));
		lbError.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_ERROR)));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout.createSequentialGroup().addGap(0, 0, 0)
						.addComponent(jPanel8,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE).addGap(
								0, 0, 0).addComponent(jPanel9,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addContainerGap()).addGroup(
				jPanel2Layout.createSequentialGroup().addContainerGap()
						.addComponent(lbError,
								javax.swing.GroupLayout.DEFAULT_SIZE, 698,
								Short.MAX_VALUE).addGap(9, 9, 9)));
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
														.addComponent(
																jPanel8,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jPanel9,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												14, Short.MAX_VALUE)
										.addComponent(
												lbError,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												24,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

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

		btnNuevo.setText("Nuevo");
		btnNuevo.setEnabled(false);
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
																javax.swing.GroupLayout.Alignment.TRAILING)
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
																layout
																		.createSequentialGroup()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								jPanel1,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jPanel3,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								282,
																								Short.MAX_VALUE))
																		.addGap(
																				0,
																				0,
																				0)
																		.addComponent(
																				jPanel2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
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
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jPanel3,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jPanel1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jPanel2,
																0,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																btnCancelar,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnGuardar,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
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
																javax.swing.GroupLayout.PREFERRED_SIZE))));

		((ButtonGuardar) btnGuardar).init();
		((ButtonCancelarConTexto) btnCancelar).init();
		((ButtonNuevo) btnNuevo).init();
		((ButtonModificar) btnModificar).init();
		((ButtonEliminar) btnEliminar).init();
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnDuplicarLicenciaActionPerformed(
			java.awt.event.ActionEvent evt) {
		int op = JOptionPaneTesterGral
				.showInternal(
						"¿Está seguro que desea emitir un duplicado de la licencia seleccionada?",
						"Emitir Duplicado", JOptionPane.QUESTION_MESSAGE);

		if (op == JOptionPane.YES_OPTION) {
			try {
				
				if(!(persona.getPerDomNro()==null))
				{
					int licSel = tableLicencias.getSelectedRow();
					licSel = tableLicencias.convertRowIndexToModel(licSel);
					TableModelLicencia tableModelLicencia = (TableModelLicencia) tableLicencias
							.getModel();

					Licencia lic = tableModelLicencia.getValueAt(licSel);

					Date hoy = new Date();
					if (lic.getLicFechaVencimiento().before(hoy)) {
						JOptionPaneTesterGral
								.showInternal(
										"La Licencia se encuentra vencida, no se puede emitir un duplicado.",
										"Emitir Duplicado",
										JOptionPane.ERROR_MESSAGE);
					} else {
						boolean conError = false;
						lic.setLicId(null);
						lic.setLicFechaOtorgada(new Date());
						lic
								.setLicTramite(Constantes.DOMINIO_TIPO_TRAMITE_RENOVACION);
						lic.setLicEstado("P");

						String utilizarCaja = ContextManager
								.getProperty("UTILIZAR_CAJA_SN");
						if (utilizarCaja.equals("S")) {
							String cajaImporteDuplicado = ContextManager
									.getProperty("CAJA_IMPORTE_DUPLICADO");
							if (!cajaImporteDuplicado.equals("")) {
								cajaImporteDuplicado = cajaImporteDuplicado
										.replace(".", "#");
								cajaImporteDuplicado = cajaImporteDuplicado
										.replace(",", ".");
								cajaImporteDuplicado = cajaImporteDuplicado
										.replace("#", ",");
								lic.setLicImporte(Double
										.valueOf(cajaImporteDuplicado));
							} else {
								JOptionPaneTesterGral
										.showInternal(
												"No se encuentra definido el importe para el duplicado de los carnets.",
												"Emitir Duplicado",
												JOptionPane.INFORMATION_MESSAGE);
								conError = true;
							}
						}
						LicenciaDefinition licenciaService = (LicenciaDefinition) ContextManager
								.getBizObject("licenciaService");

						if (!conError) {
							licenciaService.insert(lic);
							cargarLicencias();
						}
					}
				}
				else
				{
					JOptionPaneTesterGral
					.showInternal(
							"Debe completar los datos del domicilio.",
							"Datos domicilio",
							JOptionPane.ERROR_MESSAGE);
				}
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

	}

	private void txtBusquedaNacimientoActionPerformed(
			java.awt.event.ActionEvent evt) {
		btnCancelarActionPerformed(null);
		cargarPersonas();
	}

	private void btnNuevaLicenciaActionPerformed(java.awt.event.ActionEvent evt) {
		
		//TODO descomentar!!!!!!!!!!!!!!!!!!!!!!!!!1
		/*if(!(persona.getPerDomNro()==null))
		{*/
			abrirVentanaLicencia(null);
			((PanelMenuPrincipal) Util.panelMenu).calcularLicenciasPorEstado();
			cargarLicencias();
		/*}
		else
		{
			JOptionPaneTesterGral
			.showInternal(
					"Debe completar los datos del domicilio.",
					"Datos domicilio",
					JOptionPane.ERROR_MESSAGE);
		}*/
		
	}

	private void btnVerLicenciasEmitidasActionPerformed(
			java.awt.event.ActionEvent evt) {

		int licSel = tableLicencias.getSelectedRow();
		licSel = tableLicencias.convertRowIndexToModel(licSel);
		TableModelLicencia tableModelLicencia = (TableModelLicencia) tableLicencias
				.getModel();

		Licencia lic = tableModelLicencia.getValueAt(licSel);
		abrirVentanaLicencia(lic);
		cargarLicencias();
	}

	private void abrirVentanaLicencia(Licencia lic) {
		final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral(
				"Licencia", false, true, false, false);

		//TODO ver esto
		PanelNuevaLicenciaDeUsuario panelNuevaLicenciaDeUsuario = new PanelNuevaLicenciaDeUsuario(
				persona, lic, internalframe);

		internalframe.add(panelNuevaLicenciaDeUsuario);

		internalframe
				.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
					public void internalFrameActivated(
							javax.swing.event.InternalFrameEvent evt) {
					}

					public void internalFrameClosed(
							javax.swing.event.InternalFrameEvent evt) {
					}

					public void internalFrameClosing(
							javax.swing.event.InternalFrameEvent evt) {
						internalframe.close();
					}

					public void internalFrameDeactivated(
							javax.swing.event.InternalFrameEvent evt) {
					}

					public void internalFrameDeiconified(
							javax.swing.event.InternalFrameEvent evt) {
					}

					public void internalFrameIconified(
							javax.swing.event.InternalFrameEvent evt) {
					}

					public void internalFrameOpened(
							javax.swing.event.InternalFrameEvent evt) {
					}
				});

		internalframe.pack();

		internalframe.doModal(Util.framePrincipal.getRootPane());
		internalframe.setVisible(true);
	}

	private void tableLicenciasMouseClicked(java.awt.event.MouseEvent evt) {
	}

	private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {

		try {
			log.info("btnEliminarActionPerformed");

			if (JOptionPaneTesterGral.showInternal(Constantes.MENSAJE_ELIMINAR,
					Constantes.MENSAJE_ELIMINADO_TIT,
					JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

				personaService.delete(persona);

				btnCancelarActionPerformed(null);

				JOptionPaneTesterGral.showInternalMessageDialog(
						Constantes.MENSAJE_ELIMINADO,
						Constantes.MENSAJE_ELIMINADO_TIT,
						JOptionPane.INFORMATION_MESSAGE);

				limpiarFiltrosBusqueda();
				cargarPersonas();
				habilitar(false);
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

		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnNuevo.setEnabled(false);

		btnGuardar.setEnabled(true);
		btnCancelar.setEnabled(true);

		btnGuardar.setEnabled(true);
		btnCancelar.setEnabled(true);

		btnDuplicarLicencia.setEnabled(false);
		btnNuevaLicencia.setEnabled(false);
	}

	private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {

		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnNuevo.setEnabled(false);

		txtApellido.requestFocus();

		btnCancelar.setEnabled(true);
		btnGuardar.setEnabled(true);

		afterButton();

		btnVerLicenciasEmitidas.setEnabled(false);
		btnDuplicarLicencia.setEnabled(false);
		if (Util.validarDni(txtBusquedaDni.getText()))
			txtNroDoc.setText(txtBusquedaDni.getText());
	}

	private void btnAgregarNacionalidadActionPerformed(
			java.awt.event.ActionEvent evt) {
		Dominio dom = new Dominio();
		dom.setDomClave(Constantes.DOMINIO_CLAVE_NACIONALIDAD);
		dom.setDomTipo(Constantes.DOMINIO_TIPO_NACIONALIDAD);
		mostrarVentanaDominios(dom);
	}

	private void txtNacimientoFocusLost(java.awt.event.FocusEvent evt) {
		calcularEdad();
	}

	public void mostrarVentanaDominios(Dominio dom) {
		final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral(
				dom.getDomClave(), false, false, false, false);
		PanelDominioImpresor panelDominios = new PanelDominioImpresor(dom,
				internalframe);
		internalframe.add(panelDominios);
		internalframe.pack();

		internalframe.doModal(this.getRootPane());
		internalframe.setVisible(true);
		cargarListboxs();
	}

	private void txtBusquedaDniActionPerformed(java.awt.event.ActionEvent evt) {
		btnCancelarActionPerformed(null);
		cargarPersonas();
	}

	private void txtBusquedaApellidoActionPerformed(
			java.awt.event.ActionEvent evt) {
		btnCancelarActionPerformed(null);
		cargarPersonas();
	}

	private void btnCancelarFotoActionPerformed(java.awt.event.ActionEvent evt) {
		lbFoto.setIcon(null);
		persona.setPerFoto(new byte[1]);
	}

	void btnCancelarFirmaActionPerformed(java.awt.event.ActionEvent evt) {
		lbFirma.setIcon(null);
		persona.setPerFirma(new byte[1]);
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
		System.gc();
	}

	private void btnExaminarFotoActionPerformed(java.awt.event.ActionEvent evt) {
		buscarImagen(lbFoto);
	}

	public void buscarImagen(final JLabel label) {

		final VentanaSeleccionImagen internalframe = new VentanaSeleccionImagen();
		internalframe.pack();

		Util.agregarIframe(internalframe);
		internalframe.doModal(this.getRootPane());

		internalframe.setVisible(true);
		if (internalframe.getImg() != null) {
			ImageIcon imgIcon = new ImageIcon(internalframe.getImg());
			label.setIcon(imgIcon);
			if (internalframe.getArchivoSeleccionado() != null)
				internalframe.getArchivoSeleccionado().delete();
		}

		System.gc();

	}

	private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {
		String initcup = Util.toInitcup(txtNombre.getText());
		txtNombre.setText(initcup);
	}

	private void txtApellidoFocusLost(java.awt.event.FocusEvent evt) {
		txtApellido.setText(txtApellido.getText().toUpperCase());
	}

	private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {

		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnNuevo.setEnabled(false);
		btnCancelar.setEnabled(false);
		btnGuardar.setEnabled(false);
		btnCancelar.setEnabled(false);

		afterButton();
		habilitar(false);
	}

	public void afterButton() {
		log.info("afterButton");

		inicializar();
		habilitar(true);
		persona = new Persona();
	}

	public void inicializar() {
		log.info("inicializar");

		txtApellido.setText("");
		txtNombre.setText("");

		//TODO
		Util.selectDominios(cbTipoDoc, "DNI");
		txtNroDoc.setText(null);
		cbSexo.setSelectedIndex(0);
		txtNacimiento.setText(null);
		cbDonante.setSelectedIndex(0);
		cbNacionalidad.setSelectedIndex(0);
		cbGrupoSanguineo.setSelectedIndex(0);
		txtDomicilio.setText("");
		txtTelefono.setText("");
		txtMedicacion.setText("");
		txtRestricciones.setText("");
		txtMedicacion.setText("");
		txtAlergico.setText("");

		lbEdad.setText(Constantes.LB_EDAD);
		lbFirma.setIcon(null);
		lbFoto.setIcon(null);

		setTableModelLicencias(new ArrayList());
		Util.mostrarError(lbError, "", true);
	}

	private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
		log.info("btnGuardarActionPerformed");

		Util.mostrarError(lbError, null, true);
		if (validarPersona()) {
			boolean isUpdate = true;
			if (persona.getPerId() == null)
				isUpdate = false;

			persona.setPerApellido(txtApellido.getText());
			persona.setPerNombre(txtNombre.getText());
			Dominio dom = (Dominio) cbTipoDoc.getSelectedItem();
			persona.setPerTipoDoc(dom.getDomCodigo());
			persona.setPerNumeroDoc(txtNroDoc.getText());

			dom = (Dominio) cbSexo.getSelectedItem();
			persona.setPerSexo(dom.getDomCodigo());
			persona.setPerFechaNacimiento(getFechaNacimiento());
			dom = (Dominio) cbDonante.getSelectedItem();
			persona.setPerDonante(dom.getDomCodigo());
			dom = (Dominio) cbGrupoSanguineo.getSelectedItem();
			persona.setPerGrupoSanguineo(dom.getDomCodigo());
			dom = (Dominio) cbNacionalidad.getSelectedItem();
			persona.setPerNacionalidad(dom.getDomCodigo());

			persona.setPerDomicilio(txtDomicilio.getText());
			persona.setPerTelefono(txtTelefono.getText());

			//TODO puedo hacer un SUBSTRING del NO o SI
			if (txtMedicacion.getText() == null
					|| txtMedicacion.getText().equals(""))
				persona.setPerMedicacion("NO");
			else
				persona.setPerMedicacion(txtMedicacion.getText());
			//TODO puedo hacer un SUBSTRING del NO o SI
			if (txtRestricciones.getText() == null
					|| txtRestricciones.getText().equals(""))
				persona.setPerRestricciones("NO");
			else
				persona.setPerRestricciones(txtRestricciones.getText());
			//TODO puedo hacer un SUBSTRING del NO o SI
			if (txtAlergico.getText() == null
					|| txtAlergico.getText().equals(""))
				persona.setPerAlergia("NO");
			else
				persona.setPerAlergia(txtAlergico.getText());

			byte[] bytes = getImageToArray(lbFoto);
			if (bytes != null) {
				persona.setPerFoto(bytes);
			}

			bytes = getImageToArray(lbFirma);
			if (bytes != null) {
				persona.setPerFirma(bytes);
			}

			try {

				if (isUpdate) {
					personaService.update(persona);
					testerGeneral.persistence.impl.Util.insertAudit(
							testerGeneral.persistence.impl.Util.ACTION_UPDATE,
							Persona.class.getSimpleName(), persona
									.getPerNumeroDoc()
									+ " - " + persona.getPerNombreCompleto());
				} else {
					personaService.insert(persona);
				}

				//afterButton();
				limpiarFiltrosBusqueda();
				cargarPersonas();
				habilitar(false);
				mostrarPersona(persona);
				JOptionPaneTesterGral.showInternalMessageDialog(
						Constantes.MENSAJE_GUARDADO,
						Constantes.MENSAJE_GUARDADO_TIT,
						JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}

	public boolean validarPersona() {

		if (txtApellido.getText() == null || txtApellido.getText().equals("")) {
			Util.mostrarError(lbError, Constantes.ERROR_PER_APELLIDO, false);
			return false;
		}
		if (txtNombre.getText() == null || txtNombre.getText().equals("")) {
			Util.mostrarError(lbError, Constantes.ERROR_PER_NOMBRE, false);
			return false;
		}

		if (txtNroDoc.getText() == null || txtNroDoc.getText().equals("")) {
			Util.mostrarError(lbError, Constantes.ERROR_PER_NRO_DOC, false);
			return false;
		}

		if (!Util.validarDni(txtNroDoc.getText())) {
			Util.mostrarError(lbError, Constantes.ERROR_PER_DNI_SINFORMATO,
					false);
			return false;
		}

		if (existePersona()) {
			Util.mostrarError(lbError, Constantes.ERROR_PER_EXISTE, false);
			return false;
		}

		if (getFechaNacimiento() == null) {
			return false;
		}
		if (txtDomicilio.getText() == null || txtDomicilio.getText().equals("")) {
			Util.mostrarError(lbError, Constantes.ERROR_PER_DOMICILIO, false);
			return false;
		}
		if (validarFoto.equals("S") && lbFoto.getIcon() == null) {
			Util.mostrarError(lbError, Constantes.ERROR_PER_FOTO, false);
			return false;
		}
		if (validarFirma.equals("S") && lbFirma.getIcon() == null) {
			Util.mostrarError(lbError, Constantes.ERROR_PER_FIRMA, false);
			return false;
		}

		return true;
	}

	public boolean existePersona() {
		Persona per = new Persona();
		Dominio dom = (Dominio) cbTipoDoc.getSelectedItem();
		per.setPerNumeroDoc(txtNroDoc.getText());
		per.setPerTipoDoc(dom.getDomCodigo());

		List<Persona> personas = new ArrayList();

		try {
			personas = personaService.getAll(per);

			if (personas.size() > 0) {
				per = personas.get(0);
				if (!per.equals(persona))
					return true;
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	public void limpiarFiltrosBusqueda() {
		txtBusquedaDni.setText(null);
		txtBusquedaApellido.setText(null);
		txtBusquedaNacimiento.setValue(null);
		txtBusquedaNombre.setText(null);
		cbBusquedaGrupoSanguineo.setSelectedIndex(0);
	}

	@SuppressWarnings("unchecked")
	public void cargarPersonas() {
		log.info("cargarPersonas");

		boolean error = false;
		setTableModel(new ArrayList());
		try {
			Util.personaSinResultados(lbSinResultados, true);
			Persona per = new Persona();
			per.setPerApellido(txtBusquedaApellido.getText() + "%");
			per.setPerNombre(txtBusquedaNombre.getText() + "%");

			if (!txtBusquedaDni.getText().equals("")
					&& !Util.validarDni(txtBusquedaDni.getText())) {
				per.setPerNumeroDoc(null);
				Util.mostrarError(lbSinResultados,
						Constantes.ERROR_PER_DNI_SINFORMATO, false);
				error = true;

			} else if (!txtBusquedaDni.getText().equals("")
					&& Util.validarDni(txtBusquedaDni.getText())) {
				per.setPerNumeroDoc(txtBusquedaDni.getText());
			}

			if (valorPorDefectoFecha.compareTo(txtBusquedaNacimiento.getText()) != 0) {
				AbstractFormatter formatter = txtBusquedaNacimiento
						.getFormatter();
				if (formatter != null) {
					String text = txtBusquedaNacimiento.getText();
					try {
						formatter.stringToValue(text);
						SimpleDateFormat sdf = new SimpleDateFormat(
								formatoFecha);
						sdf.setLenient(false);
						per.setPerFechaNacimiento(sdf
								.parse(txtBusquedaNacimiento.getText()));
					} catch (ParseException pe) {
						per.setPerNumeroDoc(null);
						Util.mostrarError(lbSinResultados,
								Constantes.ERROR_PER_FECHA_SINFORMATO, false);
						error = true;
					}
				}
			}

			Dominio dom = (Dominio) cbBusquedaGrupoSanguineo.getSelectedItem();
			per.setPerGrupoSanguineo(dom.getDomCodigo());

			List<Persona> personas = personaService.getAll(per);

			btnGuardar.setEnabled(false);
			btnCancelar.setEnabled(false);

			btnEliminar.setEnabled(false);
			btnModificar.setEnabled(false);

			if (personas.size() <= 0) {
				btnNuevo.setEnabled(true);
			} else {
				btnNuevo.setEnabled(false);
			}

			if (!error)
				setTableModel(personas);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setTableModel(List lst) {
		TableModelPersona tableModel = new TableModelPersona();
		tableModel.setLst(lst);
		tablePersona.setModel(tableModel);
		tablePersona.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePersona.setAutoCreateRowSorter(false);

		TableColumn column = tablePersona.getColumnModel().getColumn(1);
		column.setPreferredWidth(20);
		column.setWidth(20);
		column.setMinWidth(20);

		if (lst.size() == 0)
			Util.personaSinResultados(lbSinResultados, false);
		else
			tablePersona.setAutoCreateRowSorter(true);
	}

	public void setTableModelLicencias(List lst) {
		TableModelLicencia tableModel = new TableModelLicencia();
		tableModel.setLst(lst);
		tableLicencias.setModel(tableModel);
		tableLicencias.setRowSorter(null);

		TableColumn column = tableLicencias.getColumnModel().getColumn(1);
		column.setPreferredWidth(20);
		column.setWidth(20);
		column.setMinWidth(20);

		column = tableLicencias.getColumnModel().getColumn(2);
		column.setPreferredWidth(45);
		column.setWidth(45);
		column.setMinWidth(45);

		column = tableLicencias.getColumnModel().getColumn(3);
		column.setPreferredWidth(50);
		column.setWidth(50);
		column.setMinWidth(50);

		column = tableLicencias.getColumnModel().getColumn(4);
		column.setPreferredWidth(50);
		column.setWidth(50);
		column.setMinWidth(50);

		tableLicencias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableLicencias.setAutoCreateRowSorter(false);

		if (lst.size() > 0) {
			tableLicencias.setAutoCreateRowSorter(true);
			TableRowSorter sorter = new TableRowSorter(tableModel);
			sorter.setComparator(2, new DateComparator(0));
			sorter.setComparator(3, new DateComparator(0));
			tableLicencias.setRowSorter(sorter);
		}
	}

	void tablePersonaMouseClicked(java.awt.event.MouseEvent evt) {
		//seleccionarPersona();
	}

	private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
		btnCancelarActionPerformed(null);
		cargarPersonas();
	}

	private void txtBusquedaNombreActionPerformed(java.awt.event.ActionEvent evt) {
		btnCancelarActionPerformed(null);
		cargarPersonas();
	}

	class SharedListSelectionHandler implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();
			if (!lsm.isSelectionEmpty()) {
				seleccionarPersona();
				lsm.clearSelection();
			}
		}
	}

	public void seleccionarPersona() {

		log.info("seleccionarPersona");

		int selected = tablePersona.getSelectedRow();
		selected = tablePersona.convertRowIndexToModel(selected);
		persona = ((TableModelPersona) tablePersona.getModel())
				.getValueAt(selected);

		mostrarPersona(persona);

	}

	private void mostrarPersona(Persona persona) {
		this.persona = persona;
		inicializar();

		txtApellido.setText(persona.getPerApellido());
		txtNombre.setText(persona.getPerNombre());
		Util.selectDominios(cbTipoDoc, persona.getPerTipoDoc());
		txtNroDoc.setText(persona.getPerNumeroDoc());

		Util.selectDominios(cbSexo, persona.getPerSexo());
		txtNacimiento.setText(sdf.format(persona.getPerFechaNacimiento()));

		Util.selectDominios(cbNacionalidad, persona.getPerNacionalidad());

		Util.selectDominios(cbGrupoSanguineo, persona.getPerGrupoSanguineo());

		Util.selectDominios(cbDonante, persona.getPerDonante());

		txtDomicilio.setText(persona.getPerDomicilio());
		txtTelefono.setText(persona.getPerTelefono());
		txtRestricciones.setText(persona.getPerRestricciones());
		txtMedicacion.setText(persona.getPerMedicacion());
		txtAlergico.setText(persona.getPerAlergia());

		if (persona.getPerFoto() != null && persona.getPerFoto().length > 1) {
			ImageIcon icon = new ImageIcon(persona.getPerFoto());
			lbFoto.setIcon(icon);
		}

		if (persona.getPerFirma() != null && persona.getPerFirma().length > 1) {
			ImageIcon icon = new ImageIcon(persona.getPerFirma());
			lbFirma.setIcon(icon);
		}

		calcularEdad();

		btnGuardar.setEnabled(false);
		btnCancelar.setEnabled(true);

		btnNuevo.setEnabled(false);
		btnEliminar.setEnabled(true);
		btnModificar.setEnabled(true);

		habilitar(false);

		cargarLicencias();
		habilitarBtnNuevaLicencia(true);
	}

	public void cargarLicencias() {
		try {
			LicenciaDefinition licenciaService = (LicenciaDefinition) ContextManager
					.getBizObject("licenciaService");
			Licencia lic = new Licencia();
			lic.setPersona(persona);
			List lst = licenciaService.getAll(lic);
			setTableModelLicencias(lst);

			if (lst.size() > 0) {
				tableLicencias.setRowSelectionInterval(0, 0);
				btnVerLicenciasEmitidas.setEnabled(true);
				btnDuplicarLicencia.setEnabled(true);
			} else {
				btnVerLicenciasEmitidas.setEnabled(false);
				btnDuplicarLicencia.setEnabled(false);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Date getFechaNacimiento() {
		Util.mostrarError(lbError, null, true);

		if (valorPorDefectoFecha.compareTo(txtNacimiento.getText()) != 0) {
			AbstractFormatter formatter = txtNacimiento.getFormatter();
			if (formatter != null) {
				String text = txtNacimiento.getText();
				try {
					formatter.stringToValue(text);

					sdf.setLenient(false);

					Date hoy = new Date();
					Util.redondearFecha(hoy);

					Date fecha = sdf.parse(txtNacimiento.getText());

					if (!fecha.before(hoy)) {
						Util.mostrarError(lbError,
								Constantes.ERROR_PER_FECHA_FUTURO, false);
						return null;
					}
					return fecha;
				} catch (ParseException pe) {
					Util.mostrarError(lbError,
							Constantes.ERROR_PER_FECHA_SINFORMATO, false);
				}
			}
		} else {
			Util.mostrarError(lbError, Constantes.ERROR_PER_FECHA_NAC, false);
		}

		return null;
	}

	@Override
	public void finalizar() {
		menu = null;
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
				throw new RuntimeException(e);
			}

		}

		return byteArray;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnBuscar;
	private javax.swing.JButton btnCancelar;
	private javax.swing.JButton btnCancelarFirma;
	private javax.swing.JButton btnCancelarFoto;
	private javax.swing.JButton btnDuplicarLicencia;
	private javax.swing.JButton btnEliminar;
	private javax.swing.JButton btnExaminarFirma;
	private javax.swing.JButton btnExaminarFoto;
	private javax.swing.JButton btnGuardar;
	private javax.swing.JButton btnModificar;
	private javax.swing.JButton btnNuevaLicencia;
	private javax.swing.JButton btnNuevo;
	private javax.swing.JButton btnVerLicenciasEmitidas;
	private javax.swing.JComboBox cbBusquedaGrupoSanguineo;
	private javax.swing.JComboBox cbDonante;
	private javax.swing.JComboBox cbGrupoSanguineo;
	private javax.swing.JComboBox cbNacionalidad;
	private javax.swing.JComboBox cbSexo;
	private javax.swing.JComboBox cbTipoDoc;
	private javax.swing.JButton jButton1;
	private javax.swing.JFormattedTextField jFormattedTextField1;
	private javax.swing.JFormattedTextField jFormattedTextField2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel19;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel20;
	private javax.swing.JLabel jLabel21;
	private javax.swing.JLabel jLabel22;
	private javax.swing.JLabel jLabel23;
	private javax.swing.JLabel jLabel24;
	private javax.swing.JLabel jLabel25;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JPanel jPanel9;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JLabel lbEdad;
	private javax.swing.JLabel lbError;
	private javax.swing.JLabel lbFechaEjemplo;
	private javax.swing.JLabel lbFirma;
	private javax.swing.JLabel lbFoto;
	private javax.swing.JLabel lbSinResultados;
	private javax.swing.JTable tableLicencias;
	private javax.swing.JTable tablePersona;
	private javax.swing.JTextField txtAlergico;
	private javax.swing.JTextField txtApellido;
	private javax.swing.JTextField txtBusquedaApellido;
	private javax.swing.JFormattedTextField txtBusquedaDni;
	private javax.swing.JFormattedTextField txtBusquedaNacimiento;
	private javax.swing.JTextField txtBusquedaNombre;
	private javax.swing.JTextField txtDomicilio;
	private javax.swing.JTextField txtMedicacion;
	private javax.swing.JFormattedTextField txtNacimiento;
	private javax.swing.JTextField txtNombre;
	private javax.swing.JFormattedTextField txtNroDoc;
	private javax.swing.JTextField txtRestricciones;
	private javax.swing.JTextField txtRestricciones2;
	private javax.swing.JTextField txtRestricciones3;
	private javax.swing.JTextField txtRestricciones4;
	private javax.swing.JTextField txtRestricciones5;
	private javax.swing.JTextField txtTelefono;
	// End of variables declaration//GEN-END:variables
	private Persona persona = new Persona();
	private String validarFirma = ContextManager
			.getProperty("PERSONA.FIRMA.REQUERIDA");
	private String validarFoto = ContextManager
			.getProperty("PERSONA.FOTO.REQUERIDA");
	private String formatoFecha = ContextManager.getProperty("FORMATO.FECHA");
	//private String valorPorDefectoDni;
	private String valorPorDefectoFecha;
	private SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
	private PanelMenuPrincipal menu;
	private ActionListener eliminar;
	private ActionListener modificar;
	private ActionListener nuevo;
	//private ActionListener actVerExamen;
	//private SharedListSelectionHandler sharedListSelectionHandler=new SharedListSelectionHandler();
}