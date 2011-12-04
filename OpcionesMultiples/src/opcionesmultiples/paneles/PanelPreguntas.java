/*
 * PanelPreguntas.java
 *
 * Created on __DATE__, __TIME__
 */

package opcionesmultiples.paneles;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import opcionesmultiples.domain.Pregunta;
import opcionesmultiples.domain.Respuesta;
import opcionesmultiples.service.PreguntaDefinition;
import opcionesmultiples.service.RespuestaDefinition;
import opcionesmultiples.tablemodel.TableModelPregunta;
import opcionesmultiples.tablemodel.TableModelRespuesta;
import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.Dominio;

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
import frontend.paneles.PanelDominio;
import frontend.tablemodel.TableModelPersona;
import frontend.utils.Util;
import frontend.ventanas.JInternalFrameTesterGral;
import frontend.ventanas.VentanaSeleccionImagen;

/**
 * 
 * @author __USER__
 */
public class PanelPreguntas extends javax.swing.JPanel {

	/** Creates new form PanelPreguntas */
	public PanelPreguntas() {
		initComponents();

		Util.ocultarSinResultados(lbSinResultados, true);
		Util.mostrarError(lbError, null, true);
		Util.cargarDominios(cbExamenBuscar,
				Constantes.DOMINIO_CLAVE_MP_CATEGORIA_EXAMEN, false);
		Util.cargarDominios(cbExamen,
				Constantes.DOMINIO_CLAVE_MP_CATEGORIA_EXAMEN, false);
		habilitar(false);
		tablePreguntas.getSelectionModel().addListSelectionListener(
				new SharedListSelectionHandler());

		setTableModelPreguntas(new ArrayList());
		setTableModelRespuestas(new ArrayList());
		
		Util.ocultarSinResultados(lbSinResultados, true);
	}

	public void setTableModelPreguntas(List lst) {
		Util.ocultarSinResultados(lbSinResultados, true);

		TableModelPregunta tableModel = new TableModelPregunta();
		tableModel.setLstPregunta(lst);
		tablePreguntas.setModel(tableModel);

		tablePreguntas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePreguntas.setAutoCreateRowSorter(false);

		TableColumn column = tablePreguntas.getColumnModel().getColumn(0);
		column.setPreferredWidth(200);
		column.setWidth(200);
		column.setMinWidth(200);

		if (lst.size() == 0)
			Util.ocultarSinResultados(lbSinResultados, false);
		else
			tablePreguntas.setAutoCreateRowSorter(true);
	}

	public void setTableModelRespuestas(List lst) {
		TableModelRespuesta tableModel = new TableModelRespuesta();
		tableModel.setLstRespuesta(lst);
		tableRespuesta.setModel(tableModel);
		tableRespuesta.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableRespuesta.setAutoCreateRowSorter(false);

		if(lst.size()>0)
		{
			btnModificarRespuesta.setEnabled(true);
			btnEliminarRespuesta.setEnabled(true);			
		}
		else
		{
			btnModificarRespuesta.setEnabled(false);
			btnEliminarRespuesta.setEnabled(false);
		}

		
		
		/*TableColumn column = tableRespuesta.getColumnModel().getColumn(1);
		column.setPreferredWidth(80);
		column.setWidth(80);
		column.setMinWidth(80);*/

	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel3 = new javax.swing.JPanel();
		btnBuscar = new ButtonBuscar();
		jLabel8 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		cbExamenBuscar = new javax.swing.JComboBox();
		jScrollPane2 = new javax.swing.JScrollPane();
		txtPreguntaBuscar = new javax.swing.JTextArea();
		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tablePreguntas = new javax.swing.JTable();
		lbSinResultados = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jLabel9 = new javax.swing.JLabel();
		jScrollPane3 = new javax.swing.JScrollPane();
		txtPregunta = new javax.swing.JTextArea();
		cbExamen = new javax.swing.JComboBox();
		jLabel5 = new javax.swing.JLabel();
		lbImagen = new javax.swing.JLabel();
		btnCancelarImagen = new ButtonCancelarMini();
		btnExaminarImagen = new ButtonExaminar();
		btnNuevo = new ButtonNuevo();
		btnModificar = new ButtonModificar();
		btnEliminar = new ButtonEliminar();
		btnGuardar = new ButtonGuardar();
		btnCancelar = new ButtonCancelarConTexto();
		lbError = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		jScrollPane4 = new javax.swing.JScrollPane();
		tableRespuesta = new javax.swing.JTable();
		btnNuevaRespuesta = new ButtonNuevo();
		btnModificarRespuesta = new ButtonModificar();
		btnEliminarRespuesta = new ButtonEliminar();

		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.PANEL_FILTROS_BUSQUEDA,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel3.setFocusable(false);

		btnBuscar.setToolTipText("Buscar");
		btnBuscar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnBuscarActionPerformed(evt);
			}
		});

		jLabel8.setText("Pregunta:");

		jLabel4.setText("Ex\u00e1men:");

		cbExamenBuscar.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		txtPreguntaBuscar.setColumns(20);
		txtPreguntaBuscar.setLineWrap(true);
		txtPreguntaBuscar.setRows(3);
		txtPreguntaBuscar.setWrapStyleWord(true);
		jScrollPane2.setViewportView(txtPreguntaBuscar);

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
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jLabel8,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabel4,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																63,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																cbExamenBuscar,
																0,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane2,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																242,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btnBuscar,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												48,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(24, Short.MAX_VALUE)));
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
														.addComponent(jLabel8)
														.addComponent(
																jScrollPane2,
																0, 0,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(
																cbExamenBuscar,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap(52, Short.MAX_VALUE)
										.addComponent(
												btnBuscar,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												48,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		((ButtonBuscar) btnBuscar).init();

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.PANEL_RESULTADOS_BUSQUEDA,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel1.setFocusable(false);

		tablePreguntas.setModel(new javax.swing.table.DefaultTableModel(
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
		tablePreguntas.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tablePreguntasMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(tablePreguntas);

		lbSinResultados.setForeground(new java.awt.Color(204, 0, 0));
		lbSinResultados.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_ERROR)));
		lbSinResultados
				.setText("No se encontraron resultados con los filtros ingresados");

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
																386,
																Short.MAX_VALUE)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				lbSinResultados,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				383,
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
				"Preguntas",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel2.setFocusable(false);

		jLabel9.setText("Pregunta:");

		txtPregunta.setColumns(20);
		txtPregunta.setLineWrap(true);
		txtPregunta.setRows(3);
		txtPregunta.setWrapStyleWord(true);
		jScrollPane3.setViewportView(txtPregunta);

		cbExamen.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));

		jLabel5.setText("Ex\u00e1men:");

		lbImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbImagen.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
		lbImagen.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Imagen"));
		lbImagen.setMaximumSize(new java.awt.Dimension(246, 176));
		lbImagen.setMinimumSize(new java.awt.Dimension(246, 176));

		btnCancelarImagen.setIcon(new ImageIcon(getClass().getResource(
				"/images/agregar.png")));
		btnCancelarImagen.setToolTipText("Examinar");
		((ButtonCancelarMini) btnCancelarImagen).init();
		btnCancelarImagen
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnCancelarImagenActionPerformed(evt);
					}
				});

		btnExaminarImagen.setIcon(new ImageIcon(getClass().getResource(
				"/images/agregar.png")));
		btnExaminarImagen.setToolTipText("Examinar");
		((ButtonExaminar) btnExaminarImagen).init();
		btnExaminarImagen
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnExaminarImagenActionPerformed(evt);
					}
				});

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

		lbError.setBackground(new java.awt.Color(204, 0, 0));
		lbError.setForeground(new java.awt.Color(204, 0, 0));
		lbError.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_ERROR)));

		jPanel4.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Respuestas"));

		tableRespuesta.setModel(new javax.swing.table.DefaultTableModel(
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
		tableRespuesta.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tableRespuestaMouseClicked(evt);
			}
		});
		jScrollPane4.setViewportView(tableRespuesta);

		btnNuevaRespuesta.setText("Nuevo");
		btnNuevaRespuesta.setEnabled(false);
		btnNuevaRespuesta
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnNuevaRespuestaActionPerformed(evt);
					}
				});

		btnModificarRespuesta.setText("Modificar");
		btnModificarRespuesta.setEnabled(false);
		btnModificarRespuesta
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnModificarRespuestaActionPerformed(evt);
					}
				});

		btnEliminarRespuesta.setText("Eliminar");
		btnEliminarRespuesta.setEnabled(false);
		btnEliminarRespuesta
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnEliminarRespuestaActionPerformed(evt);
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
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																jPanel4Layout
																		.createSequentialGroup()
																		.addComponent(
																				btnNuevaRespuesta,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				80,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnModificarRespuesta,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				80,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnEliminarRespuesta,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				80,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jScrollPane4,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																676,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(26, Short.MAX_VALUE)));
		jPanel4Layout
				.setVerticalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addComponent(
												jScrollPane4,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												162,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																btnNuevaRespuesta,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnModificarRespuesta,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnEliminarRespuesta,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE))));

		((ButtonNuevo) btnNuevo).init();
		((ButtonModificar) btnModificar).init();
		((ButtonEliminar) btnEliminar).init();

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
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel2Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel9)
																										.addGap(
																												17,
																												17,
																												17)
																										.addComponent(
																												jScrollPane3,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												338,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								jPanel2Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel5,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												63,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												cbExamen,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												244,
																												javax.swing.GroupLayout.PREFERRED_SIZE)))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				lbImagen,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				215,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								btnExaminarImagen,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								70,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnCancelarImagen,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								70,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(
																				24,
																				24,
																				24))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				lbError,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				492,
																				Short.MAX_VALUE)
																		.addGap(
																				234,
																				234,
																				234))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								jPanel4,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								jPanel2Layout
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
																												javax.swing.GroupLayout.PREFERRED_SIZE)))
																		.addContainerGap()))));
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
																								jLabel9)
																						.addComponent(
																								jScrollPane3,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								100,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel5)
																						.addComponent(
																								cbExamen,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																jPanel2Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																		.addGroup(
																				jPanel2Layout
																						.createSequentialGroup()
																						.addComponent(
																								btnCancelarImagen,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								25,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								btnExaminarImagen,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								25,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addComponent(
																				lbImagen,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				135,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(18, 18, 18)
										.addComponent(
												jPanel4,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(64, 64, 64)
										.addComponent(
												lbError,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												24,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
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
										.addGap(121, 121, 121)));

		((ButtonNuevo) btnNuevo).init();
		((ButtonModificar) btnModificar).init();
		((ButtonEliminar) btnEliminar).init();
		((ButtonGuardar) btnGuardar).init();
		((ButtonCancelarConTexto) btnCancelar).init();

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
														.addComponent(
																jPanel3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jPanel1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												741,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
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
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jPanel2,
																javax.swing.GroupLayout.Alignment.LEADING,
																0, 527,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
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
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnEliminarRespuestaActionPerformed(
			java.awt.event.ActionEvent evt) {
		
		try
		{		
			
			int rows[]= tableRespuesta.getSelectedRows();
			for(int i=0;i<rows.length;i++)
			{
				int selected = tableRespuesta.convertRowIndexToModel(rows[i]);
				Respuesta respuesta = ((TableModelRespuesta) tableRespuesta.getModel())
				.getValueAt(selected);
				
				respuestaService.delete(respuesta);			

			}
			
			motrarRespuestas();
/*				JOptionPaneTesterGral.showInternalMessageDialog("Debe seleccionar una respuesta.",
						"Error", JOptionPane.ERROR_MESSAGE);*/
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	private void btnModificarRespuestaActionPerformed(
			java.awt.event.ActionEvent evt) {
		final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral(
				"Respuesta", false, false, false, false);
		
		int selected = tableRespuesta.getSelectedRow();
		if(selected>=0)
		{
			selected = tableRespuesta.convertRowIndexToModel(selected);
					
			Respuesta respuesta = ((TableModelRespuesta) tableRespuesta.getModel())
			.getValueAt(selected);
			respuesta.setPregunta(pregunta);
			PanelRespuesta panelRespuesta = new PanelRespuesta(respuesta,
					internalframe);
			internalframe.add(panelRespuesta);
			internalframe.pack();
	
			internalframe.doModal(this.getRootPane());
			internalframe.setVisible(true);

			motrarRespuestas();
		}
		else
		{
			JOptionPaneTesterGral.showInternalMessageDialog("Debe seleccionar una respuesta.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void btnNuevaRespuestaActionPerformed(java.awt.event.ActionEvent evt) {
		final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral(
				"Respuesta", false, false, false, false);
		Respuesta respuesta = new Respuesta();
		respuesta.setPregunta(pregunta);
		PanelRespuesta panelRespuesta = new PanelRespuesta(respuesta,
				internalframe);
		internalframe.add(panelRespuesta);
		internalframe.pack();

		internalframe.doModal(this.getRootPane());
		internalframe.setVisible(true);

		motrarRespuestas();

	}

	private void tableRespuestaMouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
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

	private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {

		Util.mostrarError(lbError, null, true);
		if (validarPregunta()) {
			boolean isUpdate = true;

			if (pregunta.getId() == null)
				isUpdate = false;

			Dominio dom = (Dominio) cbExamen.getSelectedItem();
			pregunta.setPreCat(dom.getDomCodigo());
			pregunta.setPreDetalle(txtPregunta.getText());
			pregunta.setPreOrden(1L);
			pregunta.setPreValoracion(1L);

			byte[] bytes = getImageToArray(lbImagen);
			if (bytes != null) {
				pregunta.setPreImagen(bytes);
			}

			try {

				if (isUpdate) {
					preguntaService.update(pregunta);
					/*testerGeneral.persistence.impl.Util.insertAudit(
							testerGeneral.persistence.impl.Util.ACTION_UPDATE,
							Pregunta.class.getSimpleName(), pregunta
									.getPreDetalle());*/
				} else {
					preguntaService.insert(pregunta);
				}

				btnNuevo.setEnabled(true);
				btnCancelar.setEnabled(false);
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnGuardar.setEnabled(false);
				btnCancelar.setEnabled(false);

				//afterButton();
				
				btnNuevaRespuesta.setEnabled(true);
				btnModificarRespuesta.setEnabled(true);
				btnEliminarRespuesta.setEnabled(true);

				txtPreguntaBuscar.setText(null);
				
				motrarPregunta(); 
				setTableModelPreguntas(new ArrayList());
				Util.ocultarSinResultados(lbSinResultados, true);
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

	public boolean validarPregunta() {

		if (txtPregunta.getText() == null || txtPregunta.getText().equals("")) {
			Util.mostrarError(lbError, "Debe especificar una pregunta", false);
			return false;
		}

		TableModelRespuesta tableModelRespuesta = (TableModelRespuesta) tableRespuesta
				.getModel();
		List<Respuesta> repuestas = tableModelRespuesta.getLstRespuesta();
		if(repuestas.size()>1)
		{
			int cantidadRespuestasCorrectas = 0;
			for (Respuesta respuesta : repuestas) {
				if(respuesta.getResCorrecta().equals("S"))
					cantidadRespuestasCorrectas++;
			}
	
			if (cantidadRespuestasCorrectas == 0) {
				Util.mostrarError(lbError,
						"Debe cargar una respuesta correcta", false);
				return false;
			}
	
			if (cantidadRespuestasCorrectas == 0
					|| cantidadRespuestasCorrectas > 1) {
				Util.mostrarError(lbError,
						"Debe cargar solo una respuesta correcta", false);
				return false;
			}
		}

		return true;
	}

	private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
		try {
		

			if (JOptionPaneTesterGral.showInternal(Constantes.MENSAJE_ELIMINAR,
					Constantes.MENSAJE_ELIMINADO_TIT,
					JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

				preguntaService.delete(pregunta);

				btnNuevo.setEnabled(true);
				btnCancelar.setEnabled(false);
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnGuardar.setEnabled(false);
				btnCancelar.setEnabled(false);
				
				btnNuevaRespuesta.setEnabled(false);
				btnModificarRespuesta.setEnabled(false);
				btnEliminarRespuesta.setEnabled(false);

				afterButton();
				JOptionPaneTesterGral.showInternalMessageDialog(
						Constantes.MENSAJE_ELIMINADO,
						Constantes.MENSAJE_ELIMINADO_TIT,
						JOptionPane.INFORMATION_MESSAGE);
				
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

		habilitar(true);
		btnNuevo.setEnabled(false);
		btnCancelar.setEnabled(true);
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		btnGuardar.setEnabled(true);
		btnCancelar.setEnabled(true);
		
		btnNuevaRespuesta.setEnabled(true);
		btnModificarRespuesta.setEnabled(true);
		btnEliminarRespuesta.setEnabled(true);
	}

	private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
		txtPregunta.requestFocus();
		btnNuevo.setEnabled(false);
		btnCancelar.setEnabled(true);
		btnGuardar.setEnabled(true);
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);

		btnNuevaRespuesta.setEnabled(false);
		btnModificarRespuesta.setEnabled(false);
		btnEliminarRespuesta.setEnabled(false);

		afterButton();
	}

	public void afterButton() {
		inicializar();
		habilitar(true);
		pregunta = new Pregunta();
		//cargarPaneles();
	}

	public void inicializar() {

		txtPregunta.setText(null);
		lbImagen.setIcon(null);
		Util.cargarDominios(cbExamen,
				Constantes.DOMINIO_CLAVE_MP_CATEGORIA_EXAMEN, false);

		setTableModelRespuestas(new ArrayList());
		btnNuevaRespuesta.setEnabled(false);
		Util.mostrarError(lbError, "", true);
	}

	/*public ArrayList<Respuesta> getRespuestasMasVacias(
			ArrayList<Respuesta> respuestas) {
		int cantRespuestas = 5;
		int cantRespuestasFaltantes = cantRespuestas - respuestas.size();
		;

		for (int i = 0; i < cantRespuestasFaltantes; i++) {
			Respuesta respuesta = new Respuesta();
			respuestas.add(respuesta);
		}

		return respuestas;
	}*/

	private void btnExaminarImagenActionPerformed(java.awt.event.ActionEvent evt) {
		buscarImagen(lbImagen);
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
		}

	}

	private void btnCancelarImagenActionPerformed(java.awt.event.ActionEvent evt) {
		lbImagen.setIcon(null);
		pregunta.setPreImagen(new byte[1]);
	}

	private void tablePreguntasMouseClicked(java.awt.event.MouseEvent evt) {
		// seleccionarPersona();
	}

	private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
		try {

			btnCancelarActionPerformed(null);
			
			Pregunta example = new Pregunta();
			example.setPreDetalle("%" + txtPreguntaBuscar.getText() + "%");

			Dominio dom = (Dominio) cbExamenBuscar.getSelectedItem();
			example.setPreCat(dom.getDomCodigo());

			List<Pregunta> preguntas = preguntaService.getAll(example);
			setTableModelPreguntas(preguntas);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private void habilitar(boolean habilitar) {
		cbExamen.setEnabled(habilitar);
		btnExaminarImagen.setEnabled(habilitar);
		btnCancelarImagen.setEnabled(habilitar);
		txtPregunta.setEnabled(habilitar);
				
	}

	class SharedListSelectionHandler implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();
			if (!lsm.isSelectionEmpty()) {
				seleccionarPregunta();
			}
		}
	}

	public void seleccionarPregunta() {
		int selected = tablePreguntas.getSelectedRow();
		selected = tablePreguntas.convertRowIndexToModel(selected);
		pregunta = ((TableModelPregunta) tablePreguntas.getModel())
				.getValueAt(selected);
		inicializar();
		motrarPregunta();
		
		habilitar(false);
		
		btnNuevo.setEnabled(false);
		btnCancelar.setEnabled(true);
		btnModificar.setEnabled(true);
		btnEliminar.setEnabled(true);
		btnGuardar.setEnabled(false);
	}

	public void motrarPregunta() {
		txtPregunta.setText(pregunta.getPreDetalle());

		if (pregunta.getPreImagen() != null
				&& pregunta.getPreImagen().length > 1) {
			ImageIcon icon = new ImageIcon(pregunta.getPreImagen());
			lbImagen.setIcon(icon);
		}
		
		btnNuevaRespuesta.setEnabled(true);
		
		Util.selectDominios(cbExamen, pregunta.getPreCat());
		motrarRespuestas();
	}

	public void motrarRespuestas() {
		try {
			Util.mostrarError(lbError,null, true);
			
			Respuesta respuesta = new Respuesta();
			respuesta.setPregunta(pregunta);
			List<Respuesta> respuestas = respuestaService.getAll(respuesta);
			setTableModelRespuestas(respuestas);
			
			int cantCorrectas=0;
			int cantErroneas=0;
			for(Respuesta respuestaAValidar:respuestas)
			{
				if(respuestaAValidar.getResCorrecta().equals("S"))
					cantCorrectas++;
				else
					cantErroneas++;
			}
			
			if(respuestas.size()>0 && cantCorrectas>1)
			{
				Util.mostrarError(lbError,"Debe cargar una sola respuesta correcta", false);
			}
			else if(respuestas.size()>0 && cantCorrectas==0)
			{
				Util.mostrarError(lbError,"Debe cargar una respuesta correcta", false);
			}
			else if(respuestas.size()>0 && cantErroneas==0)
			{
				Util.mostrarError(lbError,"Debe cargar por lo menos una respuesta errónea", false);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnBuscar;
	private javax.swing.JButton btnCancelar;
	private javax.swing.JButton btnCancelarImagen;
	private javax.swing.JButton btnEliminar;
	private javax.swing.JButton btnEliminarRespuesta;
	private javax.swing.JButton btnExaminarImagen;
	private javax.swing.JButton btnGuardar;
	private javax.swing.JButton btnModificar;
	private javax.swing.JButton btnModificarRespuesta;
	private javax.swing.JButton btnNuevaRespuesta;
	private javax.swing.JButton btnNuevo;
	private javax.swing.JComboBox cbExamen;
	private javax.swing.JComboBox cbExamenBuscar;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JLabel lbError;
	private javax.swing.JLabel lbImagen;
	private javax.swing.JLabel lbSinResultados;
	private javax.swing.JTable tablePreguntas;
	private javax.swing.JTable tableRespuesta;
	private javax.swing.JTextArea txtPregunta;
	private javax.swing.JTextArea txtPreguntaBuscar;
	// End of variables declaration//GEN-END:variables
	private Pregunta pregunta;

	private PreguntaDefinition preguntaService = (PreguntaDefinition) ContextManager
			.getBizObject("preguntaService");
	private RespuestaDefinition respuestaService = (RespuestaDefinition) ContextManager
			.getBizObject("respuestaService");
}