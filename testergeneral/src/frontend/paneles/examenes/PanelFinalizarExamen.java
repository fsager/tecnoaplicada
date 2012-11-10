/*
 * PanelFinalizarExamen.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.examenes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.Examen;
import testerGeneral.domain.ExamenDetalle;
import testerGeneral.domain.PersonaExamen;
import testerGeneral.domain.ResultadoDetalleExamen;
import testerGeneral.service.ExamenDetalleDefinition;
import testerGeneral.service.PersonaExamenDefinition;
import testerGeneral.service.PersonaRestricionDefinition;
import testerGeneral.service.ResultadoDetalleExamenDefinition;
import frontend.buttons.ButtonGuardar;
import frontend.components.JOptionPaneTesterGral;
import frontend.paneles.PanelMenuPrincipal;
import frontend.tablemodel.RowRenderer;
import frontend.tablemodel.TableModelResultadoExamen;
import frontend.utils.Util;
import frontend.ventanas.DialogoTomarExamen;
import frontend.ventanas.VentanaReportes;

/**
 *
 * @author  __USER__
 */
public class PanelFinalizarExamen extends javax.swing.JPanel {

	/** Creates new form PanelFinalizarExamen */
	public PanelFinalizarExamen(PersonaExamen perExamen) {
		System.out.println("PanelFinalizarExamen");
		
		this.perExamen = perExamen;
		initComponents();
		txtObservaciones.setLineWrap(true);
		txtObservaciones.setWrapStyleWord(true);
		txtObservaciones.setRows(4);
		jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		Util.cargarDominios(cbEstado, Constantes.ESTADO_EXAMEN, false);
		detalleExamen();
		setTableModel(lstResultadoDetalleExamen);
	}

	public void detalleExamen() {
		try {
			ExamenDetalle detallesExamenen = new ExamenDetalle();
			detallesExamenen.setExamen(perExamen.getExamen());
			ExamenDetalleDefinition examenDetalleService = (ExamenDetalleDefinition) ContextManager
					.getBizObject("examenDetalleService");

			List<ExamenDetalle> detallesExamenes = examenDetalleService
					.getAll(detallesExamenen);

			for (int i = 0; i < detallesExamenes.size(); i++) {

				ResultadoDetalleExamen example = new ResultadoDetalleExamen();
				example.setExamenDetalle(detallesExamenes.get(i));
				example.setPersonaExamen(perExamen);
				List<ResultadoDetalleExamen> lstResultados = resultadoDetalleExamenService
						.getAll(example);

				if (lstResultados.size() < 1) {
					ResultadoDetalleExamen resultadoDetalleExamen = new ResultadoDetalleExamen();
					resultadoDetalleExamen.setExamenDetalle(detallesExamenes
							.get(i));
					lstResultadoDetalleExamen.add(resultadoDetalleExamen);
				} else {
					lstResultadoDetalleExamen.add(lstResultados.get(0));
				}
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setTableModel(List lst) {
		TableModelResultadoExamen tableModel = new TableModelResultadoExamen(
				perExamen.getExamen().getExaCodigo());

		tableModel.setLst(lst);
		tableDetalleExamen.setModel(tableModel);
		tableDetalleExamen
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDetalleExamen.setAutoCreateRowSorter(true);
		for (int j = 0; j < tableModel.getColumnCount(); j++)
			tableDetalleExamen.getColumnModel().getColumn(j).setCellRenderer(
					new RowRenderer());

		for (int i = 0; i < tableModel.getLstUsuario().size(); i++) {
			tableDetalleExamen.setRowHeight(i, 100);
		}

		if (perExamen.getExamen().getExaCodigo().equals(
				Examen.EXA_CODIGO_VISION)) {
			tableDetalleExamen.setRowHeight(0, 50);

			/*DefaultTableCellRenderer renderer =new DefaultTableCellRenderer();
			renderer.setToolTipText(ParametrosCorreccion.parametro);
			tableDetalleExamen.getColumnModel().getColumn(2).setCellRenderer(renderer);*/

			//tableModel.get
			if (tableDetalleExamen.getRowCount() > 1)
				tableDetalleExamen.setRowHeight(1, 50);

			if (tableDetalleExamen.getRowCount() > 3)
				tableDetalleExamen.setRowHeight(3, 60);

			if (tableDetalleExamen.getRowCount() > 4)
				tableDetalleExamen.setRowHeight(4, 50);

			if (tableDetalleExamen.getRowCount() > 8)
				tableDetalleExamen.setRowHeight(8, 80);

			if (tableDetalleExamen.getRowCount() > 9)
				tableDetalleExamen.setRowHeight(9, 120);
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tableDetalleExamen = new javax.swing.JTable();
		cbEstado = new javax.swing.JComboBox();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		btnGuardar = new ButtonGuardar();
		jScrollPane2 = new javax.swing.JScrollPane();
		txtObservaciones = new javax.swing.JTextArea();

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Detalle Exámen",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel1.setFocusable(false);

		tableDetalleExamen.setModel(new javax.swing.table.DefaultTableModel(
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
		jScrollPane1.setViewportView(tableDetalleExamen);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane1,
								javax.swing.GroupLayout.DEFAULT_SIZE, 617,
								Short.MAX_VALUE).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289,
				javax.swing.GroupLayout.PREFERRED_SIZE));

		cbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
		jLabel1.setText("Resultado General del Examen:");

		jLabel2.setText("Comentario:");

		btnGuardar.setText("Guardar");
		btnGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGuardarActionPerformed(evt);
			}
		});

		txtObservaciones.setColumns(20);
		txtObservaciones.setRows(5);
		txtObservaciones.setWrapStyleWord(true);
		jScrollPane2.setViewportView(txtObservaciones);

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
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jPanel1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				false)
																		.addGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				layout
																						.createSequentialGroup()
																						.addComponent(
																								jLabel1)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								cbEstado,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								140,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								btnGuardar,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								80,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				layout
																						.createSequentialGroup()
																						.addGap(
																								13,
																								13,
																								13)
																						.addComponent(
																								jLabel2)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								jScrollPane2,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								490,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jPanel1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jScrollPane2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				75,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																layout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel2)
																		.addGap(
																				30,
																				30,
																				30)))
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																cbEstado,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnGuardar,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		((ButtonGuardar) btnGuardar).init();
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnExaminarActionPerformed(java.awt.event.ActionEvent evt) {

	}

	public void cancalar() {
		//lbAdjunto.setText(null);
		perExamen.setPexaAdj(new byte[1]);
		perExamen.setPexaNombreAdjunto(null);
	}

	public void examinar() {
		try {
			String dirDoc = "tmpDocs";
			String dirArchivo = System.getProperty("user.dir") + File.separator
					+ dirDoc + File.separator;

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String fileName = System.currentTimeMillis() + ".doc";
			String file = dirArchivo;
			File f = new File(file);
			f.mkdir();
			file = dirArchivo + fileName;
			f = new File(file);
			f.createNewFile();

			//C:\\Program Files (x86)\\Microsoft Office\\Office12\\WINWORD.EXE
			String application = "WINWORD.EXE ";
			Process p = Runtime.getRuntime().exec(
					application + "\"" + file + "\"");
			Util.minimizar(Util.framePrincipal);
			p.waitFor();

			Thread.sleep(1000);

			File renameTo = new File(dirArchivo + System.currentTimeMillis()
					+ ".doc");
			while (!f.renameTo(renameTo)) {
				Thread.sleep(100);
			}

			byte[] bytes = testerGeneral.persistence.impl.Util
					.getBytesFromFile(renameTo.getAbsolutePath());
			perExamen.setPexaNombreAdjunto(perExamen.getPersona()
					.getPerNumeroDoc()
					+ " " + sdf.format(new Date()) + ".doc");
			perExamen.setPexaAdj(bytes);
			//lbAdjunto.setText(perExamen.getPexaNombreAdjunto());
			renameTo.delete();
			JOptionPaneTesterGral.showInternalMessageDialog(
					"Documentación Adjuntada Correctamente", "Adjuntar",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {

		try {
			perExamen.setPexaEstado(Examen.ESTADO_FIN);
			perExamen.setPexaResultadoMedico(cbEstado.getSelectedItem()
					.toString());
			perExamen.setPexaObs(txtObservaciones.getText());
			personaExamenService.update(perExamen);

			imprimirResultado();

			int op = JOptionPaneTesterGral.showInternal("<HTML>"
					+ Constantes.MENSAJE_GUARDADO
					+ "<BR>¿Desea continuar tomando exámenes?</HTML>",
					Constantes.MENSAJE_GUARDADO_TIT,
					JOptionPane.QUESTION_MESSAGE);

			if (op == JOptionPane.YES_OPTION) {

				final DialogoTomarExamen dialogoTomarExamen = new DialogoTomarExamen(
						perExamen.getPersona(),
						(PanelMenuPrincipal) Util.panelMenu);
				dialogoTomarExamen.pack();
				Util.agregarIframe(dialogoTomarExamen);

				dialogoTomarExamen.doModal(this.getRootPane());
				dialogoTomarExamen.setVisible(true);
			} else {
				Util.panelMenu.cargarSubMenuPersona();
				Util.panelMenu.seleccionarPersona();
			}

			//ExamenesUtils.mostrarPanelExamen(perExamen, Util.panelContenido);

		}
		catch (InvalidDataAccessApiUsageException idau) {
			JOptionPaneTesterGral.showInternal(
					"Debe realizar por lo menos una evaluación", "Error",
					JOptionPane.ERROR_MESSAGE);
			
		}
		catch (Exception e) {
			throw new RuntimeException("Error desconocido al finalizar examen",e);
		}
	}

	public void imprimirResultado() throws Exception{

		PersonaRestricionDefinition personaRestriccionService=(PersonaRestricionDefinition)ContextManager.getBizObject("personaRestricionService");
		HashMap parameterMap = new HashMap();
		parameterMap.put("p_pexa_id", perExamen.getPexaId());
		parameterMap.put("otrasAflicciones",personaRestriccionService.getOtrasAflicciones(perExamen.getPersona()));
		
		
		parameterMap.put("SUBREPORT_DIR",new File("./reportes").getCanonicalPath()+File.separator);		
		new VentanaReportes(this, parameterMap, Constantes.RPT_PERSONA_EXAMEN);

	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnGuardar;
	private javax.swing.JComboBox cbEstado;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTable tableDetalleExamen;
	private javax.swing.JTextArea txtObservaciones;
	// End of variables declaration//GEN-END:variables
	private PersonaExamen perExamen;
	private ResultadoDetalleExamenDefinition resultadoDetalleExamenService = (ResultadoDetalleExamenDefinition) ContextManager
			.getBizObject("resultadoDetalleExamenService");
	private LinkedList<ResultadoDetalleExamen> lstResultadoDetalleExamen = new LinkedList<ResultadoDetalleExamen>();
	private PersonaExamenDefinition personaExamenService = (PersonaExamenDefinition) ContextManager
			.getBizObject("personaExamenService");
	private static final Log log = LogFactory.getLog(PanelFinalizarExamen.class);
}
