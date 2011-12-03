/*
 * PanelPersona.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.Dominio;
import testerGeneral.domain.Examen;
import testerGeneral.domain.Persona;
import testerGeneral.domain.PersonaExamen;
import testerGeneral.domain.PersonaRestricion;
import testerGeneral.domain.Usuario;
import testerGeneral.focus.MyOwnFocusTraversalPolicy;
import testerGeneral.service.ExamenDefinition;
import testerGeneral.service.PersonaDefinition;
import testerGeneral.service.PersonaRestricionDefinition;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import frontend.buttons.ButtonAgregarMini;
import frontend.buttons.ButtonBuscar;
import frontend.buttons.ButtonCancelarConTexto;
import frontend.buttons.ButtonCancelarMini;
import frontend.buttons.ButtonExaminar;
import frontend.buttons.ButtonGuardar;
import frontend.components.JOptionPaneTesterGral;
import frontend.components.PanelScroll;
import frontend.paneles.examenes.Finalisable;
import frontend.tablemodel.TableModelPersona;
import frontend.utils.Util;
import frontend.ventanas.JInternalFrameTesterGral;
import frontend.ventanas.VentanaSeleccionImagen;
import frontend.ventanas.VentanaTomarFirma;
import frontend.ventanas.VentanaVerExamenes;

/**
 * 
 * @author __USER__
 */
public class PanelPersona extends javax.swing.JPanel implements Finalisable {

	private static final Log log = LogFactory.getLog(PanelPersona.class);
	private PersonaDefinition personaService = (PersonaDefinition) ContextManager
			.getBizObject("personaService");
	private PersonaRestricionDefinition personaRestricionService = (PersonaRestricionDefinition) ContextManager
	.getBizObject("personaRestricionService");

	

	/** Creates new form PanelPersona */
	public PanelPersona(PanelMenuPrincipal menu) {

		this.menu = menu;
		initComponents();
		btnCancelarFirma.setText("Borrar");
		btnCancelarFoto.setText("Borrar");
		cargarListboxs();

		cargarPaneles();

		setTableModel(new ArrayList());
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
		order.add(cbEstadoCivil);
		order.add(cbGrupoSanguineo);
		order.add(cbLocalidad);
		order.add(txtDomicilio);
		order.add(txtTelefono);
		order.add(txtCelular);
		order.add(txtCorreo);
		order.add(txtEstudios);
		order.add(txtOtrasObs);
		order.add(panelObsOcular.getJlist());
		//order.add(panelObsAuditiva.getJlist());
		//order.add(panelObsFisica.getJlist());
		order.add(btnExamniarFirma);
		order.add(btnExaminarFoto);
		order.add(btnGuardar);
		order.add(btnCancelar);
		MyOwnFocusTraversalPolicy newPolicy = new MyOwnFocusTraversalPolicy(
				order);

		this.setFocusTraversalPolicy(newPolicy);

		lbFechaEjemplo.setText(Util.mostrasMascara());
		lbFechaEjemplo2.setText(Util.mostrasMascara());

		valorPorDefectoFecha = txtBusquedaNacimiento.getText();

		agregarEscuchas();

		habilitar(false);

		tablePersona.getSelectionModel().addListSelectionListener(
				new SharedListSelectionHandler());
	}

	public void agregarEscuchas() {

		actVerExamen = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnVerExamen();
			}
		};
		menu.getBtnVerExamenPersona().addActionListener(actVerExamen);

		actTomarExamen = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btnTomarExamen();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		};

		menu.getBtnRealizarExamenPersona().addActionListener(actTomarExamen);

		nuevo = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnNuevoActionPerformed1(evt);
			}
		};
		menu.getBtnNuevaPersona().addActionListener(nuevo);

		modificar = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnModificarActionPerformed1(evt);
			}
		};
		menu.getBtnModificarPersona().addActionListener(modificar);

		eliminar = new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnEliminarActionPerformed1(evt);
			}
		};
		menu.getBtnEliminarPersona().addActionListener(eliminar);
	}

	public void btnTomarExamen() throws Exception {
		menu.unSelectButtons(menu.getToolbarSubNivel(), menu
				.getBtnBuscarPersona());
		/*final DialogoTomarExamen dialogoTomarExamen = new DialogoTomarExamen(
				persona, menu);
		dialogoTomarExamen.pack();
		Util.agregarIframe(dialogoTomarExamen);

		dialogoTomarExamen.doModal(this.getRootPane());
		dialogoTomarExamen.setVisible(true);*/

		ExamenDefinition examenService = (ExamenDefinition) ContextManager
				.getBizObject("examenService");
		Examen exa = new Examen();
		exa.setExaCodigo(Examen.EXA_CODIGO_VISION);
		exa = (Examen) examenService.getAll(exa).get(0);

		testerGeneral.persistence.impl.Util.insertAudit(
				testerGeneral.persistence.impl.Util.ACTION_MENU_EXAMEN_VISION,
				null, null);

		PersonaExamen personaExamen = new PersonaExamen();
		personaExamen.setPexaTipoExamen(PersonaExamen.TIPO_EXAMEN_PROFECIONAL);
		personaExamen.setPersona(persona);
		personaExamen.setExamen(exa);

		javax.swing.JToggleButton btnExamenVision = new JToggleButton(
				Constantes.MENU_SUB_EXAMEN_VISION);
		btnExamenVision.setEnabled(false);
		menu.cargarSubMenuExamenes(btnExamenVision);
		menu.seleccionarExamenVision(personaExamen);
	}

	public void btnVerExamen() {
		if (menu != null) {
			menu.unSelectButtons(menu.getToolbarSubNivel(), menu
					.getBtnBuscarPersona());

			final VentanaVerExamenes ventanaVerExamenes = new VentanaVerExamenes(
					persona);
			ventanaVerExamenes.pack();
			Util.agregarIframe(ventanaVerExamenes);

			ventanaVerExamenes.doModal(this.getRootPane());
			ventanaVerExamenes.setVisible(true);
		}
	}

	public void cargarListboxs() {
		Util.cargarDominios(cbEstadoCivil,
				Constantes.DOMINIO_CLAVE_ESTADO_CIVIL, false);
		Util.cargarDominios(cbBusquedaGrupoSanguineo,
				Constantes.DOMINIO_CLAVE_GRUPO_SAN, true);
		Util.cargarDominios(cbGrupoSanguineo,
				Constantes.DOMINIO_CLAVE_GRUPO_SAN, false);
		Util.cargarDominios(cbSexo, Constantes.DOMINIO_CLAVE_SEXO, false);
		Util
				.cargarDominios(cbTipoDoc, Constantes.DOMINIO_CLAVE_TIPO_DOC,
						false);
		Util.cargarDominios(cbLocalidad, Constantes.DOMINIO_CLAVE_LOCALIDAD,
				false);
		Util.selectDominios(cbTipoDoc, "DNI");
	}

	private void calcularEdad() {
		Date nacimiento = getFechaNacimiento();
		Util.calcularEdad(lbEdad, nacimiento);
	}

	public void habilitar(boolean habilitar) {
		log.info("habilitar");

		txtApellido.setEnabled(habilitar);
		txtNombre.setEnabled(habilitar);
		cbTipoDoc.setEnabled(habilitar);
		txtNroDoc.setEnabled(habilitar);
		cbSexo.setEnabled(habilitar);
		txtNacimiento.setEnabled(habilitar);
		cbEstadoCivil.setEnabled(habilitar);
		cbGrupoSanguineo.setEnabled(habilitar);
		cbLocalidad.setEnabled(habilitar);
		txtDomicilio.setEnabled(habilitar);
		txtTelefono.setEnabled(habilitar);
		txtCelular.setEnabled(habilitar);
		txtCorreo.setEnabled(habilitar);
		txtEstudios.setEnabled(habilitar);
		txtOtrasObs.setEnabled(habilitar);
		panelObsOcular.setEnabled(habilitar);
		//panelObsAuditiva.setEnabled(habilitar);
		//panelObsFisica.setEnabled(habilitar);
		btnExamniarFirma.setEnabled(habilitar);
		btnExaminarFoto.setEnabled(habilitar);
		btnCancelarFirma.setEnabled(habilitar);
		btnCancelarFoto.setEnabled(habilitar);
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroupEducacion = new javax.swing.ButtonGroup();
		buttonGroupLicenciaDeConducir = new javax.swing.ButtonGroup();
		buttonGroupTiempoQueConduce = new javax.swing.ButtonGroup();
		buttonGroupTomaMedicamentosSN = new javax.swing.ButtonGroup();
		buttonGroupTomoHoyPsicofarmacosSN = new javax.swing.ButtonGroup();
		buttonGroupTomoHoyAlcoholSN = new javax.swing.ButtonGroup();
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
		jPanel7 = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		panelObsOcular = new frontend.components.PanelScroll();
		jPanel9 = new javax.swing.JPanel();
		lbFoto = new javax.swing.JLabel();
		btnExaminarFoto = new ButtonExaminar();
		lbFirma = new javax.swing.JLabel();
		btnExamniarFirma = new ButtonExaminar();
		btnCancelarFirma = new ButtonCancelarMini();
		btnCancelarFoto = new ButtonCancelarMini();
		btnAgregarOcular = new ButtonAgregarMini();
		jPanel8 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		txtApellido = new javax.swing.JTextField();
		txtNombre = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		cbTipoDoc = new javax.swing.JComboBox();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		cbSexo = new javax.swing.JComboBox();
		jLabel7 = new javax.swing.JLabel();
		lbEdad = new javax.swing.JLabel();
		cbEstadoCivil = new javax.swing.JComboBox();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		cbGrupoSanguineo = new javax.swing.JComboBox();
		jScrollPane2 = new javax.swing.JScrollPane();
		txtDomicilio = new javax.swing.JTextArea();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		txtTelefono = new javax.swing.JTextField();
		jLabel13 = new javax.swing.JLabel();
		txtCelular = new javax.swing.JTextField();
		jLabel14 = new javax.swing.JLabel();
		txtCorreo = new javax.swing.JTextField();
		jLabel15 = new javax.swing.JLabel();
		txtEstudios = new javax.swing.JTextField();
		jLabel16 = new javax.swing.JLabel();
		jScrollPane3 = new javax.swing.JScrollPane();
		txtOtrasObs = new javax.swing.JTextArea();
		txtNroDoc = new javax.swing.JFormattedTextField();
		btnAgregarTipoDoc = new ButtonAgregarMini();
		btnAgregarEstadoCivil = new ButtonAgregarMini();
		txtNacimiento = Util.setFecha();
		lbFechaEjemplo2 = new javax.swing.JLabel();
		jLabel18 = new javax.swing.JLabel();
		cbLocalidad = new javax.swing.JComboBox();
		btnAgregarLocalidad = new ButtonAgregarMini();
		jPanel11 = new javax.swing.JPanel();
		jLabel23 = new javax.swing.JLabel();
		jRadioButtonEducacionPrimaria = new javax.swing.JRadioButton();
		jRadioButtonEducacionSecundaria = new javax.swing.JRadioButton();
		jRadioButtonEducacionUniversitaria = new javax.swing.JRadioButton();
		jRadioButtonEducacionTecnica = new javax.swing.JRadioButton();
		jLabel24 = new javax.swing.JLabel();
		jRadioButtonLicenciaParticular = new javax.swing.JRadioButton();
		jRadioButtonLicenciaProfesional = new javax.swing.JRadioButton();
		jLabel25 = new javax.swing.JLabel();
		jRadioButtonTiempoConduceOpcion1 = new javax.swing.JRadioButton();
		jRadioButtonTiempoConduceOpcion3 = new javax.swing.JRadioButton();
		jRadioButtonTiempoConduceOpcion2 = new javax.swing.JRadioButton();
		jLabel26 = new javax.swing.JLabel();
		jRadioButtonTomaMedicamentosRegularmente_SI = new javax.swing.JRadioButton();
		jRadioButtonTomaMedicamentosRegularmente_NO = new javax.swing.JRadioButton();
		jRadioButtonTomoHoyPsicofarmacosNO = new javax.swing.JRadioButton();
		jRadioButtonTomoHoyPsicofarmacosSI = new javax.swing.JRadioButton();
		jLabel27 = new javax.swing.JLabel();
		jRadioButtonTomoHoyAlcohol_NO = new javax.swing.JRadioButton();
		jRadioButtonTomoHoyAlcohol_SI = new javax.swing.JRadioButton();
		jLabel28 = new javax.swing.JLabel();
		lbError = new javax.swing.JLabel();
		btnGuardar = new ButtonGuardar();
		btnCancelar = new ButtonCancelarConTexto();

		setFocusCycleRoot(true);

		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.PANEL_FILTROS_BUSQUEDA,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel3.setFocusable(false);

		jLabel3.setText(Constantes.LB_APELLIDO);

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

		txtBusquedaDni.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtBusquedaDniActionPerformed(evt);
			}
		});

		txtBusquedaNacimiento
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						txtBusquedaNacimientoActionPerformed(evt);
					}
				});

		lbFechaEjemplo.setFont(new java.awt.Font("Segoe UI", 0, 7));
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
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
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
																								jLabel20)
																						.addComponent(
																								jLabel21))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel3Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addGroup(
																								jPanel3Layout
																										.createSequentialGroup()
																										.addComponent(
																												txtBusquedaNacimiento,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												75,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												lbFechaEjemplo,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE))
																						.addComponent(
																								txtBusquedaNombre)
																						.addComponent(
																								txtBusquedaDni)
																						.addComponent(
																								txtBusquedaApellido,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								125,
																								Short.MAX_VALUE)
																						.addComponent(
																								cbBusquedaGrupoSanguineo,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								128,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnBuscar,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				48,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jLabel8,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																112,
																javax.swing.GroupLayout.PREFERRED_SIZE))));
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
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel3Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel8)
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
																						.addComponent(
																								jLabel3)
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
																						.addComponent(
																								jLabel19)
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
																						.addComponent(
																								jLabel20)
																						.addComponent(
																								lbFechaEjemplo)
																						.addComponent(
																								txtBusquedaNacimiento,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel3Layout
																		.createSequentialGroup()
																		.addContainerGap(
																				86,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnBuscar,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				48,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));

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
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																301,
																Short.MAX_VALUE)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				lbSinResultados,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				298,
																				Short.MAX_VALUE)
																		.addGap(
																				3,
																				3,
																				3)))));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addComponent(
						jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
						307, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18,
						18, 18).addComponent(lbSinResultados,
						javax.swing.GroupLayout.PREFERRED_SIZE, 24,
						javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.PANEL_DATOS_PERSONA,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel2.setFocusable(false);

		jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.LB_OBSERVACIONES_OCULAR,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 3, 11)));

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(
				jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel5Layout.createSequentialGroup().addComponent(
						panelObsOcular, javax.swing.GroupLayout.DEFAULT_SIZE,
						249, Short.MAX_VALUE).addContainerGap()));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				panelObsOcular, javax.swing.GroupLayout.DEFAULT_SIZE, 242,
				Short.MAX_VALUE));

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

		btnExamniarFirma.setIcon(new ImageIcon(getClass().getResource(
				"/images/agregar.png")));
		btnExamniarFirma.setToolTipText("Examinar");
		((ButtonExaminar) btnExamniarFirma).init();
		btnExamniarFirma.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExamniarFirmaActionPerformed(evt);
			}
		});

		btnCancelarFirma.setIcon(new ImageIcon(getClass().getResource(
				"/images/agregar.png")));
		btnCancelarFirma.setToolTipText("Examinar");
		((ButtonCancelarMini) btnCancelarFirma).init();
		btnCancelarFirma.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarFirmaActionPerformed(evt);
			}
		});

		btnCancelarFoto.setIcon(new ImageIcon(getClass().getResource(
				"/images/agregar.png")));
		btnCancelarFoto.setToolTipText("Examinar");
		((ButtonCancelarMini) btnCancelarFoto).init();
		btnCancelarFoto.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarFotoActionPerformed(evt);
			}
		});

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
										.addContainerGap()
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																lbFirma,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																215,
																Short.MAX_VALUE)
														.addComponent(
																lbFoto,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																215,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																btnExaminarFoto,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																70,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnCancelarFoto,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																70,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnExamniarFirma,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																70,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnCancelarFirma,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																70,
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
																135,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																lbFirma,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																117,
																javax.swing.GroupLayout.PREFERRED_SIZE)
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
																				btnExamniarFirma,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				25,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))));

		btnAgregarOcular.setIcon(new ImageIcon(getClass().getResource(
				"/images/agregar.png")));
		btnAgregarOcular.setToolTipText("Examinar");
		((ButtonAgregarMini) btnAgregarOcular).init();
		btnAgregarOcular.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAgregarOcularActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(
				jPanel7);
		jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout
				.setHorizontalGroup(jPanel7Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel7Layout
										.createSequentialGroup()
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jPanel9,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																jPanel7Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jPanel5,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnAgregarOcular,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				22,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
										.addContainerGap()));
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
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																btnAgregarOcular,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																22,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jPanel5,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel9,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		jLabel1.setText(Constantes.LB_APELLIDO);

		txtApellido.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtApellidoFocusLost(evt);
			}
		});

		txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtNombreFocusLost(evt);
			}
		});

		jLabel2.setText(Constantes.LB_NOMBRE);

		jLabel4.setText(Constantes.LB_TIPO_DNI);

		cbTipoDoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));

		jLabel5.setText(Constantes.LB_NRO_DOCUMENTO);

		jLabel6.setText(Constantes.LB_SEXO);

		cbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));

		jLabel7.setText(Constantes.LB_FECHA_NACIMIENTO);

		lbEdad.setFont(new java.awt.Font("Segoe UI", 0, 11));
		lbEdad.setText(Constantes.LB_EDAD);

		cbEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		jLabel9.setText(Constantes.LB_ESTADO_CIVIL);

		jLabel10.setText(Constantes.LB_GRUPO_SANGUIENEO);

		cbGrupoSanguineo.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		txtDomicilio.setColumns(20);
		txtDomicilio.setRows(2);
		jScrollPane2.setViewportView(txtDomicilio);

		jLabel11.setText(Constantes.LB_DOMICILIO);

		jLabel12.setText(Constantes.LB_TELEFONO);

		jLabel13.setText(Constantes.LB_TELEFONO_CEL);

		jLabel14.setText(Constantes.LB_CORREO);

		jLabel15.setText(Constantes.LB_ESTUDIOS);

		jLabel16.setText(Constantes.LB_OTRAS_OBSERVACIONES);

		txtOtrasObs.setColumns(20);
		txtOtrasObs.setRows(3);
		jScrollPane3.setViewportView(txtOtrasObs);

		btnAgregarTipoDoc.setIcon(new ImageIcon(getClass().getResource(
				"/images/agregar.png")));
		btnAgregarTipoDoc.setToolTipText("Examinar");
		((ButtonAgregarMini) btnAgregarTipoDoc).init();
		btnAgregarTipoDoc
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnAgregarTipoDocActionPerformed(evt);
					}
				});

		btnAgregarEstadoCivil.setIcon(new ImageIcon(getClass().getResource(
				"/images/agregar.png")));
		btnAgregarEstadoCivil.setToolTipText("Examinar");
		((ButtonAgregarMini) btnAgregarEstadoCivil).init();
		btnAgregarEstadoCivil
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnAgregarEstadoCivilActionPerformed(evt);
					}
				});

		txtNacimiento.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent evt) {
				txtNacimientoFocusLost(evt);
			}
		});

		lbFechaEjemplo2.setFont(new java.awt.Font("Segoe UI", 0, 7));
		lbFechaEjemplo2.setText("jLabel17");

		jLabel18.setText(Constantes.LB_LOCALIDAD);

		cbLocalidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));

		btnAgregarLocalidad.setIcon(new ImageIcon(getClass().getResource(
				"/images/agregar.png")));
		btnAgregarLocalidad.setToolTipText("Examinar");
		((ButtonAgregarMini) btnAgregarLocalidad).init();
		btnAgregarLocalidad
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnAgregarLocalidadActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(
				jPanel8);
		jPanel8.setLayout(jPanel8Layout);
		jPanel8Layout
				.setHorizontalGroup(jPanel8Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel8Layout
										.createSequentialGroup()
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																jPanel8Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel7,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				117,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				txtNacimiento,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				78,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				lbFechaEjemplo2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				55,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				lbEdad,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				59,
																				Short.MAX_VALUE))
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
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
																												jScrollPane3,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												175,
																												Short.MAX_VALUE))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
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
																												txtEstudios,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												175,
																												Short.MAX_VALUE))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jPanel8Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel14,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												117,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												txtCorreo,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												175,
																												Short.MAX_VALUE))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jPanel8Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel13,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												117,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												txtCelular,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												175,
																												Short.MAX_VALUE))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jPanel8Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel12,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												117,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												txtTelefono,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												175,
																												Short.MAX_VALUE))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jPanel8Layout
																										.createSequentialGroup()
																										.addGroup(
																												jPanel8Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING,
																																false)
																														.addComponent(
																																jLabel18,
																																javax.swing.GroupLayout.Alignment.LEADING,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)
																														.addComponent(
																																jLabel10,
																																javax.swing.GroupLayout.Alignment.LEADING,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																117,
																																Short.MAX_VALUE)
																														.addComponent(
																																jLabel11,
																																javax.swing.GroupLayout.Alignment.LEADING,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																117,
																																Short.MAX_VALUE))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addGroup(
																												jPanel8Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jScrollPane2,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																cbLocalidad,
																																0,
																																175,
																																Short.MAX_VALUE)
																														.addComponent(
																																cbGrupoSanguineo,
																																0,
																																175,
																																Short.MAX_VALUE)))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jPanel8Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel9,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												117,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												cbEstadoCivil,
																												0,
																												175,
																												Short.MAX_VALUE))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jPanel8Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel1,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												117,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												txtApellido,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												175,
																												Short.MAX_VALUE))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jPanel8Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel2,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												117,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												txtNombre,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												175,
																												Short.MAX_VALUE))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jPanel8Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel4,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												117,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												cbTipoDoc,
																												0,
																												175,
																												Short.MAX_VALUE))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jPanel8Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel5,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												117,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												txtNroDoc,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												175,
																												Short.MAX_VALUE))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jPanel8Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel6,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												117,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												cbSexo,
																												0,
																												175,
																												Short.MAX_VALUE)))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								btnAgregarEstadoCivil,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								22,
																								Short.MAX_VALUE)
																						.addComponent(
																								btnAgregarLocalidad,
																								0,
																								22,
																								Short.MAX_VALUE)
																						.addComponent(
																								btnAgregarTipoDoc,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								22,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addContainerGap()));
		jPanel8Layout
				.setVerticalGroup(jPanel8Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel8Layout
										.createSequentialGroup()
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel1)
																						.addComponent(
																								txtApellido,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel2)
																						.addComponent(
																								txtNombre,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel4)
																						.addComponent(
																								cbTipoDoc,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel5)
																						.addComponent(
																								txtNroDoc,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel6)
																						.addComponent(
																								cbSexo,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addGap(
																				58,
																				58,
																				58)
																		.addComponent(
																				btnAgregarTipoDoc,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				22,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																jPanel8Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				jLabel7)
																		.addComponent(
																				txtNacimiento,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel8Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				lbEdad)
																		.addComponent(
																				lbFechaEjemplo2)))
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel9)
																						.addComponent(
																								cbEstadoCivil,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel10)
																						.addComponent(
																								cbGrupoSanguineo,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel18)
																						.addComponent(
																								cbLocalidad,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(
																				8,
																				8,
																				8)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel11)
																						.addComponent(
																								jScrollPane2,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								58,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel12)
																						.addComponent(
																								txtTelefono,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel13)
																						.addComponent(
																								txtCelular,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel14)
																						.addComponent(
																								txtCorreo,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel15)
																						.addComponent(
																								txtEstudios,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jScrollPane3,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel16)))
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addGap(
																				8,
																				8,
																				8)
																		.addComponent(
																				btnAgregarEstadoCivil,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				22,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(
																				38,
																				38,
																				38)
																		.addComponent(
																				btnAgregarLocalidad,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				22,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))));

		jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Informaci\u00f3n adicional",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 3, 11)));

		jLabel23.setText("Educaci\u00f3n:");

		buttonGroupEducacion.add(jRadioButtonEducacionPrimaria);
		jRadioButtonEducacionPrimaria.setText("Primaria");

		buttonGroupEducacion.add(jRadioButtonEducacionSecundaria);
		jRadioButtonEducacionSecundaria.setText("Secundaria");

		buttonGroupEducacion.add(jRadioButtonEducacionUniversitaria);
		jRadioButtonEducacionUniversitaria.setText("Universitaria");

		buttonGroupEducacion.add(jRadioButtonEducacionTecnica);
		jRadioButtonEducacionTecnica.setText("T\u00e9cnica");

		jLabel24.setText("Licencia de conducir:");

		buttonGroupLicenciaDeConducir.add(jRadioButtonLicenciaParticular);
		jRadioButtonLicenciaParticular.setText("Particular");

		buttonGroupLicenciaDeConducir.add(jRadioButtonLicenciaProfesional);
		jRadioButtonLicenciaProfesional.setText("Profesional");

		jLabel25.setText("Tiempo que conduce:");

		buttonGroupTiempoQueConduce.add(jRadioButtonTiempoConduceOpcion1);
		jRadioButtonTiempoConduceOpcion1.setText("Menos de un a\u00f1o");

		buttonGroupTiempoQueConduce.add(jRadioButtonTiempoConduceOpcion3);
		jRadioButtonTiempoConduceOpcion3.setText("Entre 1 y 5 a\u00f1os");

		buttonGroupTiempoQueConduce.add(jRadioButtonTiempoConduceOpcion2);
		jRadioButtonTiempoConduceOpcion2.setText("M\u00e1s de 5 a\u00f1os");

		jLabel26.setText("Toma medicamentos regularmente:");

		buttonGroupTomaMedicamentosSN
				.add(jRadioButtonTomaMedicamentosRegularmente_SI);
		jRadioButtonTomaMedicamentosRegularmente_SI.setText("Si");

		buttonGroupTomaMedicamentosSN
				.add(jRadioButtonTomaMedicamentosRegularmente_NO);
		jRadioButtonTomaMedicamentosRegularmente_NO.setText("No");

		buttonGroupTomoHoyPsicofarmacosSN
				.add(jRadioButtonTomoHoyPsicofarmacosNO);
		jRadioButtonTomoHoyPsicofarmacosNO.setText("No");

		buttonGroupTomoHoyPsicofarmacosSN
				.add(jRadioButtonTomoHoyPsicofarmacosSI);
		jRadioButtonTomoHoyPsicofarmacosSI.setText("Si");

		jLabel27.setText("Tom\u00f3 hoy psicof\u00e1rmacos:");

		buttonGroupTomoHoyAlcoholSN.add(jRadioButtonTomoHoyAlcohol_NO);
		jRadioButtonTomoHoyAlcohol_NO.setText("No");

		buttonGroupTomoHoyAlcoholSN.add(jRadioButtonTomoHoyAlcohol_SI);
		jRadioButtonTomoHoyAlcohol_SI.setText("Si");

		jLabel28.setText("Tom\u00f3 hoy alcohol:");

		javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(
				jPanel11);
		jPanel11.setLayout(jPanel11Layout);
		jPanel11Layout
				.setHorizontalGroup(jPanel11Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel11Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel11Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jRadioButtonTiempoConduceOpcion2)
														.addComponent(
																jRadioButtonTiempoConduceOpcion3)
														.addComponent(jLabel25)
														.addComponent(
																jRadioButtonTiempoConduceOpcion1)
														.addGroup(
																jPanel11Layout
																		.createSequentialGroup()
																		.addComponent(
																				jRadioButtonTomoHoyAlcohol_SI)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jRadioButtonTomoHoyAlcohol_NO))
														.addComponent(jLabel28)
														.addComponent(jLabel23)
														.addGroup(
																jPanel11Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel11Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jRadioButtonEducacionPrimaria)
																						.addComponent(
																								jRadioButtonEducacionTecnica))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel11Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jRadioButtonEducacionUniversitaria)
																						.addComponent(
																								jRadioButtonEducacionSecundaria)))
														.addComponent(
																jRadioButtonLicenciaProfesional)
														.addComponent(
																jRadioButtonLicenciaParticular)
														.addComponent(jLabel24)
														.addGroup(
																jPanel11Layout
																		.createSequentialGroup()
																		.addComponent(
																				jRadioButtonTomaMedicamentosRegularmente_SI)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jRadioButtonTomaMedicamentosRegularmente_NO,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				41,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(jLabel26)
														.addGroup(
																jPanel11Layout
																		.createSequentialGroup()
																		.addComponent(
																				jRadioButtonTomoHoyPsicofarmacosSI)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jRadioButtonTomoHoyPsicofarmacosNO))
														.addComponent(jLabel27))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel11Layout
				.setVerticalGroup(jPanel11Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel11Layout
										.createSequentialGroup()
										.addComponent(jLabel23)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel11Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jRadioButtonEducacionPrimaria)
														.addComponent(
																jRadioButtonEducacionSecundaria))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel11Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jRadioButtonEducacionTecnica)
														.addComponent(
																jRadioButtonEducacionUniversitaria))
										.addGap(18, 18, 18)
										.addComponent(jLabel24)
										.addComponent(
												jRadioButtonLicenciaParticular)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jRadioButtonLicenciaProfesional)
										.addGap(18, 18, 18)
										.addComponent(jLabel25)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jRadioButtonTiempoConduceOpcion1)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jRadioButtonTiempoConduceOpcion3)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jRadioButtonTiempoConduceOpcion2)
										.addGap(18, 18, 18)
										.addComponent(jLabel26)
										.addGap(2, 2, 2)
										.addGroup(
												jPanel11Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jRadioButtonTomaMedicamentosRegularmente_SI)
														.addComponent(
																jRadioButtonTomaMedicamentosRegularmente_NO))
										.addGap(18, 18, 18)
										.addComponent(jLabel27)
										.addGap(1, 1, 1)
										.addGroup(
												jPanel11Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jRadioButtonTomoHoyPsicofarmacosNO)
														.addComponent(
																jRadioButtonTomoHoyPsicofarmacosSI))
										.addGap(18, 18, 18)
										.addComponent(jLabel28)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel11Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jRadioButtonTomoHoyAlcohol_NO)
														.addComponent(
																jRadioButtonTomoHoyAlcohol_SI))
										.addContainerGap(24, Short.MAX_VALUE)));

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
										.addContainerGap()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jPanel8,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jPanel11,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																lbError,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																587,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel7,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
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
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jPanel8,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								509,
																								Short.MAX_VALUE)
																						.addComponent(
																								jPanel11,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				lbError,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				25,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jPanel7,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

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
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				809,
																				Short.MAX_VALUE)
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
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addContainerGap())
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jPanel2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addContainerGap()))));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addComponent(
												jPanel3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(
								layout
										.createSequentialGroup()
										.addComponent(
												jPanel2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
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
																javax.swing.GroupLayout.PREFERRED_SIZE))));

		((ButtonGuardar) btnGuardar).init();
		((ButtonCancelarConTexto) btnCancelar).init();
	}// </editor-fold>
	//GEN-END:initComponents

	private void txtNacimiento1FocusLost(java.awt.event.FocusEvent evt) {
		// TODO add your handling code here:
	}

	private void txtBusquedaNacimientoActionPerformed(
			java.awt.event.ActionEvent evt) {
		btnCancelarActionPerformed(null);
		cargarPersonas();
	}

	private void btnAgregarLocalidadActionPerformed(
			java.awt.event.ActionEvent evt) {
		Dominio dom = new Dominio();
		dom.setDomClave(Constantes.DOMINIO_CLAVE_LOCALIDAD);
		dom.setDomTipo(Constantes.DOMINIO_TIPO_LOCALIDAD);
		mostrarVentanaDominios(dom);
	}

	private void btnAgregarEstadoCivil1ActionPerformed(
			java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void txtNacimientoFocusLost(java.awt.event.FocusEvent evt) {
		calcularEdad();
	}

	private void btnAgregarOcularActionPerformed(java.awt.event.ActionEvent evt) {
		Dominio dom = new Dominio();
		dom.setDomClave(Constantes.DOMINIO_CLAVE_RES_VISUAL);
		dom.setDomTipo(Constantes.DOMINIO_TIPO_RES_VISUAL);
		mostrarVentanaDominios(dom);
	}

	private void btnAgregarEstadoCivilActionPerformed(
			java.awt.event.ActionEvent evt) {
		Dominio dom = new Dominio();
		dom.setDomClave(Constantes.DOMINIO_CLAVE_ESTADO_CIVIL);
		dom.setDomTipo(Constantes.DOMINIO_TIPO_ESTADO_CIVIL);
		mostrarVentanaDominios(dom);

	}

	private void btnAgregarTipoDocActionPerformed(java.awt.event.ActionEvent evt) {
		Dominio dom = new Dominio();
		dom.setDomClave(Constantes.DOMINIO_CLAVE_TIPO_DOC);
		dom.setDomTipo(Constantes.DOMINIO_TIPO_TIPO_DOC);
		mostrarVentanaDominios(dom);
	}

	public void mostrarVentanaDominios(Dominio dom) {
		final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral(
				dom.getDomClave(), false, false, false, false);
		PanelDominio panelDominios = new PanelDominio(dom, internalframe);
		internalframe.add(panelDominios);
		internalframe.pack();

		internalframe.doModal(this.getRootPane());
		internalframe.setVisible(true);
		cargarListboxs();
		cargarPaneles();
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

	private void btnExamniarFirmaActionPerformed(java.awt.event.ActionEvent evt) {
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

	}

	private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {
		String initcup = Util.toInitcup(txtNombre.getText());
		txtNombre.setText(initcup);
	}

	private void txtApellidoFocusLost(java.awt.event.FocusEvent evt) {
		txtApellido.setText(txtApellido.getText().toUpperCase());
	}

	private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {

		menu.unSelectButtons(menu.getToolbarSubNivel(), menu
				.getBtnBuscarPersona());
		menu.getBtnModificarPersona().setEnabled(false);
		menu.getBtnEliminarPersona().setEnabled(false);
		menu.getBtnRealizarExamenPersona().setEnabled(false);
		menu.getBtnVerExamenPersona().setEnabled(false);
		menu.getBtnNuevaPersona().setEnabled(false);

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
		cargarPaneles();
	}

	public void inicializar() {
		log.info("inicializar");

		txtApellido.setText("");
		txtNombre.setText("");
		cbTipoDoc.setSelectedIndex(0);
		Util.selectDominios(cbTipoDoc, "DNI");
		txtNroDoc.setText(null);
		cbSexo.setSelectedIndex(0);
		txtNacimiento.setText(null);
		cbEstadoCivil.setSelectedIndex(0);
		cbLocalidad.setSelectedIndex(0);
		cbGrupoSanguineo.setSelectedIndex(0);
		txtDomicilio.setText("");
		txtTelefono.setText("");
		txtCelular.setText("");
		txtCorreo.setText("");
		txtEstudios.setText("");
		txtOtrasObs.setText("");
		panelObsOcular.inicializar();
		//panelObsAuditiva.inicializar();
		//panelObsFisica.inicializar();

		lbEdad.setText(Constantes.LB_EDAD);
		lbFirma.setIcon(null);
		lbFoto.setIcon(null);

		buttonGroupEducacion.clearSelection();
		buttonGroupLicenciaDeConducir.clearSelection();
		buttonGroupTiempoQueConduce.clearSelection();
		buttonGroupTomaMedicamentosSN.clearSelection();
		buttonGroupTomoHoyAlcoholSN.clearSelection();
		buttonGroupTomoHoyPsicofarmacosSN.clearSelection();

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
			dom = (Dominio) cbEstadoCivil.getSelectedItem();
			persona.setPerEstadoCivil(dom.getDomCodigo());
			dom = (Dominio) cbGrupoSanguineo.getSelectedItem();
			persona.setPerGrupoSanguineo(dom.getDomCodigo());
			dom = (Dominio) cbLocalidad.getSelectedItem();
			persona.setPerLocalidad(dom.getDomCodigo());
			persona.setPerDomicilio(txtDomicilio.getText());
			persona.setPerTelefono(txtTelefono.getText());
			persona.setPerCelular(txtCelular.getText());
			persona.setPerCorreo(txtCorreo.getText());

			if (!txtEstudios.getText().equals("")) {
				persona.setPerEstudios(txtEstudios.getText());
			}
			persona.setPerObservaciones(txtOtrasObs.getText());

			persona.setPerEducacion("0");
			if (jRadioButtonEducacionPrimaria.isSelected()) {
				persona.setPerEducacion("1");
			}
			if (jRadioButtonEducacionSecundaria.isSelected()) {
				persona.setPerEducacion("2");
			}
			if (jRadioButtonEducacionTecnica.isSelected()) {
				persona.setPerEducacion("3");
			}
			if (jRadioButtonEducacionUniversitaria.isSelected()) {
				persona.setPerEducacion("4");
			}

			persona.setPerLicenciaDeConducir("0");
			if (jRadioButtonLicenciaParticular.isSelected()) {
				persona.setPerLicenciaDeConducir("1");
			}
			if (jRadioButtonLicenciaProfesional.isSelected()) {
				persona.setPerLicenciaDeConducir("2");
			}

			persona.setPerTiempoQueLlevaConduciendo("0");
			if (jRadioButtonTiempoConduceOpcion1.isSelected()) {
				persona.setPerTiempoQueLlevaConduciendo("1");
			}
			if (jRadioButtonTiempoConduceOpcion2.isSelected()) {
				persona.setPerTiempoQueLlevaConduciendo("2");
			}
			if (jRadioButtonTiempoConduceOpcion3.isSelected()) {
				persona.setPerTiempoQueLlevaConduciendo("3");
			}

			persona.setPerTomaMedicamentosRegularmenteSN("0");
			if (jRadioButtonTomaMedicamentosRegularmente_SI.isSelected()) {
				persona.setPerTomaMedicamentosRegularmenteSN("1");
			}
			if (jRadioButtonTomaMedicamentosRegularmente_NO.isSelected()) {
				persona.setPerTomaMedicamentosRegularmenteSN("2");
			}

			persona.setPerTomoHoyPsicofarmacosSN("0");
			if (jRadioButtonTomoHoyPsicofarmacosSI.isSelected()) {
				persona.setPerTomoHoyPsicofarmacosSN("1");
			}
			if (jRadioButtonTomoHoyPsicofarmacosNO.isSelected()) {
				persona.setPerTomoHoyPsicofarmacosSN("2");
			}

			persona.setPerTomoHoyAlcohol("0");
			if (jRadioButtonTomoHoyAlcohol_SI.isSelected()) {
				persona.setPerTomoHoyAlcohol("1");
			}
			if (jRadioButtonTomoHoyAlcohol_NO.isSelected()) {
				persona.setPerTomoHoyAlcohol("2");
			}

			byte[] bytes = getImageToArray(lbFoto);
			if (bytes != null) {
				persona.setPerFoto(bytes);
			}

			bytes = getImageToArray(lbFirma);
			if (bytes != null) {
				persona.setPerFirma(bytes);
			}

			/*Set personaRestricciones = persona.getPersonaRestricions();
			personaRestricciones.clear();
			for (int i = 0; i < panelObsOcular.getSeleccionados().size(); i++) {
				personaRestricciones.add(panelObsOcular.getSeleccionados().get(
						i));
			}*/

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
				
				//Borro todas las restricciones y las inserto nuevamente con lo que seleccionó
				List<PersonaRestricion> restricciones=(List)Util.getPersonaRestriciones(persona);
				
				for(PersonaRestricion personaRestricion:restricciones)
				{
					PersonaRestricionDefinition personaRestricionService = (PersonaRestricionDefinition) ContextManager.getBizObject("personaRestricionService");
					personaRestricionService.delete(personaRestricion);
				}
				
				for (int i = 0; i < panelObsOcular.getSeleccionados().size(); i++) {
					PersonaRestricion personaRestricionSel=(PersonaRestricion)panelObsOcular.getSeleccionados().get(i);
					personaRestricionService.insert(personaRestricionSel);
					
				}

				// afterButton();
				limpiarFiltrosBusqueda();
				cargarPersonas();
				habilitar(false);
				mostrarPersona(persona);
				JOptionPaneTesterGral.showInternalMessageDialog(
						Constantes.MENSAJE_GUARDADO,
						Constantes.MENSAJE_GUARDADO_TIT,
						JOptionPane.INFORMATION_MESSAGE);
				menu.unSelectButtons(menu.getToolbarSubNivel(), menu
						.getBtnBuscarPersona());

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			// cargarPaneles();
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

		/*if (getFechaNacimiento() == null) {
			return false;
		}
		if (txtDomicilio.getText() == null || txtDomicilio.getText().equals("")) {
			Util.mostrarError(lbError, Constantes.ERROR_PER_DOMICILIO, false);
			return false;
		}*/
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

	private void btnEliminarActionPerformed1(java.awt.event.ActionEvent evt) {
		menu.unSelectButtons(menu.getToolbarSubNivel(), menu
				.getBtnEliminarPersona());
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
			menu.unSelectButtons(menu.getToolbarSubNivel(), menu
					.getBtnBuscarPersona());

		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			JOptionPaneTesterGral.showInternalError(Util.frameContenedor,
					new Throwable(Constantes.MENSAJE_ERROR_ELIMINAR));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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

			if (!error)
			{
				
				Dominio dom = (Dominio) cbBusquedaGrupoSanguineo.getSelectedItem();
				per.setPerGrupoSanguineo(dom.getDomCodigo());
	
				List<Persona> personas = personaService.getAll(per);
	
				menu.getBtnRealizarExamenPersona().setEnabled(false);
				menu.getBtnVerExamenPersona().setEnabled(false);
	
				btnGuardar.setEnabled(false);
				btnCancelar.setEnabled(false);
	
				menu.getBtnEliminarPersona().setEnabled(false);
				menu.getBtnModificarPersona().setEnabled(false);
	
				if (personas.size() <= 0) {
					menu.getBtnNuevaPersona().setEnabled(
							true && usr.hasAmPersonaPermition());
				} else {
					menu.getBtnNuevaPersona().setEnabled(false);
				}
				
				setTableModel(personas);
			}
			/*else			
				setTableModel(new ArrayList());*/

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

	private void btnModificarActionPerformed1(java.awt.event.ActionEvent evt) {
		log.info("btnModificarActionPerformed");
		menu.unSelectButtons(menu.getToolbarSubNivel(), menu
				.getBtnModificarPersona());
		habilitar(true);

		menu.getBtnRealizarExamenPersona().setEnabled(false);
		menu.getBtnVerExamenPersona().setEnabled(false);
		menu.getBtnNuevaPersona().setEnabled(false);
		menu.getBtnModificarPersona().setEnabled(false);
		menu.getBtnEliminarPersona().setEnabled(false);
		btnGuardar.setEnabled(true);
		btnCancelar.setEnabled(true);
	}

	private void btnNuevoActionPerformed1(java.awt.event.ActionEvent evt) {
		menu.unSelectButtons(menu.getToolbarSubNivel(), menu
				.getBtnNuevaPersona());
		menu.getBtnRealizarExamenPersona().setEnabled(false);
		menu.getBtnVerExamenPersona().setEnabled(false);
		menu.getBtnNuevaPersona().setEnabled(false);
		menu.getBtnModificarPersona().setEnabled(false);
		menu.getBtnEliminarPersona().setEnabled(false);

		txtApellido.requestFocus();

		btnCancelar.setEnabled(true);
		btnGuardar.setEnabled(true);

		afterButton();

		if (Util.validarDni(txtBusquedaDni.getText()))
			txtNroDoc.setText(txtBusquedaDni.getText());
	}

	void tablePersonaMouseClicked(java.awt.event.MouseEvent evt) {
		// seleccionarPersona();
	}

	private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
		btnCancelarActionPerformed(null);
		cargarPersonas();

	}

	private void txtBusquedaNombreActionPerformed(java.awt.event.ActionEvent evt) {
		btnCancelarActionPerformed(null);
		cargarPersonas();
	}

	public void cargarPaneles() {

		try {
			log.info("cargarPaneles");

			List restriccionVision = Util
					.getDominios(Constantes.DOMINIO_CLAVE_RES_VISUAL);
			List restriccionFisica = Util
					.getDominios(Constantes.DOMINIO_CLAVE_RES_FISICA);
			List restriccionAuditiva = Util
					.getDominios(Constantes.DOMINIO_CLAVE_RES_AUD);

			//cargarPanel(panelObsAuditiva, restriccionAuditiva);
			//cargarPanel(panelObsFisica, restriccionFisica);
			cargarPanel(panelObsOcular, restriccionVision);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public void cargarPanel(PanelScroll panelScroll, List domains) {

		try {
			log.info("cargarPanel");
			List personaRestriccion = new ArrayList();
			for (int i = 0; i < domains.size(); i++) {
				PersonaRestricion personaRestricion = new PersonaRestricion();
				personaRestricion.setPersona(persona);
				Dominio dom = (Dominio) domains.get(i);
				personaRestricion.setDescripcion(dom.getDomValorMostrar());
				personaRestricion.setDomId(dom.getDomId());
				personaRestriccion.add(personaRestricion);
			}

			panelScroll.setDatos(personaRestriccion);
			panelScroll.cargarList();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
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

	@SuppressWarnings("unchecked")
	private void mostrarPersona(Persona persona){
		
		this.persona = persona;
		
		inicializar();

		txtApellido.setText(persona.getPerApellido());
		txtNombre.setText(persona.getPerNombre());
		Util.selectDominios(cbTipoDoc, persona.getPerTipoDoc());
		txtNroDoc.setText(persona.getPerNumeroDoc());
		Util.selectDominios(cbSexo, persona.getPerSexo());

		if (persona.getPerFechaNacimiento() != null)
			txtNacimiento.setText(sdf.format(persona.getPerFechaNacimiento()));

		Util.selectDominios(cbEstadoCivil, persona.getPerEstadoCivil());
		Util.selectDominios(cbGrupoSanguineo, persona.getPerGrupoSanguineo());
		Util.selectDominios(cbLocalidad, persona.getPerLocalidad());

		if (persona.getPerDomicilio() != null)
			txtDomicilio.setText(persona.getPerDomicilio());
		txtTelefono.setText(persona.getPerTelefono());
		txtCelular.setText(persona.getPerCelular());
		txtCorreo.setText(persona.getPerCorreo());
		txtEstudios.setText(persona.getPerEstudios());
		txtOtrasObs.setText(persona.getPerObservaciones());

		if (persona.getPerFoto() != null && persona.getPerFoto().length > 1) {
			ImageIcon icon = new ImageIcon(persona.getPerFoto());
			lbFoto.setIcon(icon);
		}

		if (persona.getPerFirma() != null && persona.getPerFirma().length > 1) {
			ImageIcon icon = new ImageIcon(persona.getPerFirma());
			lbFirma.setIcon(icon);
		}
		try {

			if (persona.getPerEducacion().equals("1")) {
				jRadioButtonEducacionPrimaria.setSelected(true);
			}
			if (persona.getPerEducacion().equals("2")) {
				jRadioButtonEducacionSecundaria.setSelected(true);
			}
			if (persona.getPerEducacion().equals("3")) {
				jRadioButtonEducacionTecnica.setSelected(true);
			}
			if (persona.getPerEducacion().equals("4")) {
				jRadioButtonEducacionUniversitaria.setSelected(true);
			}

			if (persona.getPerLicenciaDeConducir().equals("1")) {
				jRadioButtonLicenciaParticular.setSelected(true);
			}
			if (persona.getPerLicenciaDeConducir().equals("2")) {
				jRadioButtonLicenciaProfesional.setSelected(true);
			}

			if (persona.getPerTiempoQueLlevaConduciendo().equals("1")) {
				jRadioButtonTiempoConduceOpcion1.setSelected(true);
			}
			if (persona.getPerTiempoQueLlevaConduciendo().equals("2")) {
				jRadioButtonTiempoConduceOpcion2.setSelected(true);
			}
			if (persona.getPerTiempoQueLlevaConduciendo().equals("3")) {
				jRadioButtonTiempoConduceOpcion3.setSelected(true);
			}

			if (persona.getPerTomaMedicamentosRegularmenteSN().equals("1")) {
				jRadioButtonTomaMedicamentosRegularmente_SI.setSelected(true);
			}
			if (persona.getPerTomaMedicamentosRegularmenteSN().equals("2")) {
				jRadioButtonTomaMedicamentosRegularmente_NO.setSelected(true);
			}

			if (persona.getPerTomoHoyPsicofarmacosSN().equals("1")) {
				jRadioButtonTomoHoyPsicofarmacosSI.setSelected(true);
			}
			if (persona.getPerTomoHoyPsicofarmacosSN().equals("2")) {
				jRadioButtonTomoHoyPsicofarmacosNO.setSelected(true);
			}

			if (persona.getPerTomoHoyAlcohol().equals("1")) {
				jRadioButtonTomoHoyAlcohol_SI.setSelected(true);
			}
			if (persona.getPerTomoHoyAlcohol().equals("2")) {
				jRadioButtonTomoHoyAlcohol_NO.setSelected(true);
			}

		} catch (NullPointerException ex) {

		}
		cargarPaneles();

		//panelObsFisica.setSeleccionados(persona.getPersonaRestricions());
		List personaRestriciones=(List)Util.getPersonaRestriciones(persona);
		panelObsOcular.setSeleccionados(personaRestriciones);
		//panelObsAuditiva.setSeleccionados(persona.getPersonaRestricions());

		calcularEdad();

		menu.getBtnRealizarExamenPersona().setEnabled(true);
		menu.getBtnVerExamenPersona().setEnabled(true);

		btnGuardar.setEnabled(false);
		btnCancelar.setEnabled(true);

		menu.getBtnNuevaPersona().setEnabled(false);
		menu.getBtnEliminarPersona().setEnabled(
				true && usr.hasBPersonaPermition());
		menu.getBtnModificarPersona().setEnabled(
				true && usr.hasAmPersonaPermition());

		habilitar(false);
	}

	public Date getFechaNacimiento() {
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
			//Util.mostrarError(lbError, Constantes.ERROR_PER_FECHA_NAC, false);
		}

		return null;
	}

	@Override
	public void finalizar() {
		menu.getBtnEliminarPersona().removeActionListener(eliminar);
		menu.getBtnRealizarExamenPersona().removeActionListener(actTomarExamen);
		menu.getBtnNuevaPersona().removeActionListener(nuevo);
		menu.getBtnModificarPersona().removeActionListener(modificar);
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
	private javax.swing.JButton btnAgregarEstadoCivil;
	private javax.swing.JButton btnAgregarLocalidad;
	private javax.swing.JButton btnAgregarOcular;
	private javax.swing.JButton btnAgregarTipoDoc;
	private javax.swing.JButton btnBuscar;
	private javax.swing.JButton btnCancelar;
	private javax.swing.JButton btnCancelarFirma;
	private javax.swing.JButton btnCancelarFoto;
	private javax.swing.JButton btnExaminarFoto;
	private javax.swing.JButton btnExamniarFirma;
	private javax.swing.JButton btnGuardar;
	private javax.swing.ButtonGroup buttonGroupEducacion;
	private javax.swing.ButtonGroup buttonGroupLicenciaDeConducir;
	private javax.swing.ButtonGroup buttonGroupTiempoQueConduce;
	private javax.swing.ButtonGroup buttonGroupTomaMedicamentosSN;
	private javax.swing.ButtonGroup buttonGroupTomoHoyAlcoholSN;
	private javax.swing.ButtonGroup buttonGroupTomoHoyPsicofarmacosSN;
	private javax.swing.JComboBox cbBusquedaGrupoSanguineo;
	private javax.swing.JComboBox cbEstadoCivil;
	private javax.swing.JComboBox cbGrupoSanguineo;
	private javax.swing.JComboBox cbLocalidad;
	private javax.swing.JComboBox cbSexo;
	private javax.swing.JComboBox cbTipoDoc;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel19;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel20;
	private javax.swing.JLabel jLabel21;
	private javax.swing.JLabel jLabel23;
	private javax.swing.JLabel jLabel24;
	private javax.swing.JLabel jLabel25;
	private javax.swing.JLabel jLabel26;
	private javax.swing.JLabel jLabel27;
	private javax.swing.JLabel jLabel28;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel11;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JPanel jPanel9;
	private javax.swing.JRadioButton jRadioButtonEducacionPrimaria;
	private javax.swing.JRadioButton jRadioButtonEducacionSecundaria;
	private javax.swing.JRadioButton jRadioButtonEducacionTecnica;
	private javax.swing.JRadioButton jRadioButtonEducacionUniversitaria;
	private javax.swing.JRadioButton jRadioButtonLicenciaParticular;
	private javax.swing.JRadioButton jRadioButtonLicenciaProfesional;
	private javax.swing.JRadioButton jRadioButtonTiempoConduceOpcion1;
	private javax.swing.JRadioButton jRadioButtonTiempoConduceOpcion2;
	private javax.swing.JRadioButton jRadioButtonTiempoConduceOpcion3;
	private javax.swing.JRadioButton jRadioButtonTomaMedicamentosRegularmente_NO;
	private javax.swing.JRadioButton jRadioButtonTomaMedicamentosRegularmente_SI;
	private javax.swing.JRadioButton jRadioButtonTomoHoyAlcohol_NO;
	private javax.swing.JRadioButton jRadioButtonTomoHoyAlcohol_SI;
	private javax.swing.JRadioButton jRadioButtonTomoHoyPsicofarmacosNO;
	private javax.swing.JRadioButton jRadioButtonTomoHoyPsicofarmacosSI;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JLabel lbEdad;
	private javax.swing.JLabel lbError;
	private javax.swing.JLabel lbFechaEjemplo;
	private javax.swing.JLabel lbFechaEjemplo2;
	private javax.swing.JLabel lbFirma;
	private javax.swing.JLabel lbFoto;
	private javax.swing.JLabel lbSinResultados;
	private frontend.components.PanelScroll panelObsOcular;
	private javax.swing.JTable tablePersona;
	private javax.swing.JTextField txtApellido;
	private javax.swing.JTextField txtBusquedaApellido;
	private javax.swing.JFormattedTextField txtBusquedaDni;
	private javax.swing.JFormattedTextField txtBusquedaNacimiento;
	private javax.swing.JTextField txtBusquedaNombre;
	private javax.swing.JTextField txtCelular;
	private javax.swing.JTextField txtCorreo;
	private javax.swing.JTextArea txtDomicilio;
	private javax.swing.JTextField txtEstudios;
	private javax.swing.JFormattedTextField txtNacimiento;
	private javax.swing.JTextField txtNombre;
	private javax.swing.JFormattedTextField txtNroDoc;
	private javax.swing.JTextArea txtOtrasObs;
	private javax.swing.JTextField txtTelefono;
	// End of variables declaration//GEN-END:variables

	private Persona persona = new Persona();
	private String validarFirma = ContextManager
			.getProperty("PERSONA.FIRMA.REQUERIDA");
	private String validarFoto = ContextManager
			.getProperty("PERSONA.FOTO.REQUERIDA");
	private String formatoFecha = ContextManager.getProperty("FORMATO.FECHA");
	// private String valorPorDefectoDni;
	private String valorPorDefectoFecha;
	private SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
	private PanelMenuPrincipal menu;
	private ActionListener eliminar;
	private ActionListener modificar;
	private ActionListener nuevo;
	private ActionListener actTomarExamen;
	private ActionListener actVerExamenes;
	private ActionListener actVerExamen;
	private Usuario usr = ((Usuario) Util.usuarioCommon);
	// private SharedListSelectionHandler sharedListSelectionHandler=new
	// SharedListSelectionHandler();
}