/*
 * PanelInformesYEstadisticas.java
 *
 * Created on __DATE__, __TIME__
 */

package autoimpresor.frontend.paneles;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import testerGeneral.domain.Constantes;
import testerGeneral.domain.Dominio;
import testerGeneral.persistence.GestorExportarDB;
import testerGeneral.service.DominioDefinition;
import autoimpresor.business.ContextManager;
import autoimpresor.domain.ClaseLicencia;
import autoimpresor.domain.Licencia;
import autoimpresor.domain.Persona;
import autoimpresor.frontend.tablemodels.TableModelEstadistica;
import autoimpresor.service.ClaseLicenciaDefinition;
import autoimpresor.service.LicenciaDefinition;
import autoimpresor.service.PersonaDefinition;
import frontend.components.JOptionPaneTesterGral;
import frontend.utils.ExcelGenerator;
import frontend.utils.Util;
import frontend.ventanas.VentanaExaminar;

/**
 * 
 * @author __USER__
 */
public class PanelInformesYEstadisticas extends javax.swing.JPanel {
	private static final Log log = LogFactory.getLog(PanelPersona.class);
	private PersonaDefinition personaService = (PersonaDefinition) ContextManager
			.getBizObject("personaService");
	/*private PersonaExamenDefinition personaExamenService = (PersonaExamenDefinition) ContextManager
			.getBizObject("personaExamenService");
	private List<ReporteGaussExamenes> reportesGaussExamenes = new ArrayList();*/
	private ClaseLicenciaDefinition claseLicenciaService = (ClaseLicenciaDefinition) ContextManager
			.getBizObject("claseLicenciaService");
	LicenciaDefinition licenciaService = (LicenciaDefinition) ContextManager
			.getBizObject("licenciaService");

	/** Creates new form PanelInformesYEstadisticas */
	public PanelInformesYEstadisticas() {
		initComponents();

		lbFechaEjemplo.setText(Util.mostrasMascara());
		llenarComboBoxClasesLicencia();
		llenarComboBoxTipoTramite();
		valorPorDefectoFecha = txtOtorgadaDesde.getText();
		tituloTabla = "Sexo";
		domainToConvert = Constantes.DOMINIO_CLAVE_SEXO;
		setTableModel(new ArrayList());
		Util.personaSinResultados(lbSinResultados, true);
	}

	public void setTableModel(List lst) {
		TableModelEstadistica tableModel = new TableModelEstadistica();
		tableModel.setLst(lst);
		tableModel.setPrimerColumna(tituloTabla);
		tableModel.setDomainToConvert(domainToConvert);
		tableEstadisticas.setModel(tableModel);
		tableEstadisticas
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableEstadisticas.setRowSorter(null);
		tableEstadisticas.setAutoCreateRowSorter(false);

		if (lst.size() > 0) {
			tableEstadisticas.setAutoCreateRowSorter(true);

		} else {
			Util.ocultarSinResultados(lbSinResultados, false);
		}
	}

	private void llenarComboBoxTipoTramite() {
		List<Dominio> listaDominios = Util.getDominios(new String(
				"TIPO_TRAMITE"));
		Util.cargarDominios(jComboBoxTipoTramite, new String(new String(
				"TIPO_TRAMITE")), true);
		if (jComboBoxTipoTramite.getComponentCount() != 0) {
			jComboBoxTipoTramite.setSelectedIndex(0);
		}
	}

	private void llenarComboBoxClasesLicencia() {
		try {
			List<ClaseLicencia> listaDeClasesDeLicencia = claseLicenciaService
					.getAll(new ClaseLicencia());
			jComboBoxClaseLicencia.removeAllItems();

			ClaseLicencia claseInsercion = new ClaseLicencia();
			jComboBoxClaseLicencia.addItem(claseInsercion);

			for (int i = 0; i < listaDeClasesDeLicencia.size(); i++) {
				claseInsercion = listaDeClasesDeLicencia.get(i);
				jComboBoxClaseLicencia.addItem(claseInsercion);
			}
			/*claseLicenciaSeleccionada = (ClaseLicencia) jComboBoxClaseLicencia
					.getSelectedItem();*/
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroupSexo = new javax.swing.ButtonGroup();
		buttonGroupVencido = new javax.swing.ButtonGroup();
		buttonGroupAgrupados = new javax.swing.ButtonGroup();
		jPanelFiltros = new javax.swing.JPanel();
		jTextFieldEdadInicio = new javax.swing.JTextField();
		jLabelEdad2 = new javax.swing.JLabel();
		jTextFieldEdadFin = new javax.swing.JTextField();
		jLabelEdad3 = new javax.swing.JLabel();
		jLabel20 = new javax.swing.JLabel();
		txtOtorgadaDesde = Util.setFecha();
		jLabel6 = new javax.swing.JLabel();
		txtOtorgadaHasta = Util.setFecha();
		lbFechaEjemplo = new javax.swing.JLabel();
		jRadioButtonMasculino = new javax.swing.JRadioButton();
		jRadioButtonFemenino = new javax.swing.JRadioButton();
		jLabel21 = new javax.swing.JLabel();
		jLabel22 = new javax.swing.JLabel();
		jLabelTipoTramite = new javax.swing.JLabel();
		jComboBoxTipoTramite = new javax.swing.JComboBox();
		jComboBoxClaseLicencia = new javax.swing.JComboBox();
		jLabelClaseDeLicencia = new javax.swing.JLabel();
		jRadioButtonSexoAmbos = new javax.swing.JRadioButton();
		jPanel1 = new javax.swing.JPanel();
		jRadioSexo = new javax.swing.JRadioButton();
		jRadioGS = new javax.swing.JRadioButton();
		jRadioClaseLicencia = new javax.swing.JRadioButton();
		jRadioTipoTramite = new javax.swing.JRadioButton();
		btnGenerar = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tableEstadisticas = new javax.swing.JTable();
		btnExportar = new javax.swing.JButton();
		lbSinResultados = new javax.swing.JLabel();
		jPanelGrafico = new javax.swing.JPanel();

		jPanelFiltros.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Filtros",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));

		jLabelEdad2.setText("y");

		jLabelEdad3.setText("a\u00f1os.");

		jLabel20.setText("Otorgada:");

		jLabel6.setText("y");

		lbFechaEjemplo.setFont(new java.awt.Font("Segoe UI", 0, 11));
		lbFechaEjemplo.setText("jLabel17");

		buttonGroupSexo.add(jRadioButtonMasculino);
		jRadioButtonMasculino.setText("Masculino");

		buttonGroupSexo.add(jRadioButtonFemenino);
		jRadioButtonFemenino.setText("Femenino");

		jLabel21.setText("Sexo:");

		jLabel22.setText("Edad: entre");

		jLabelTipoTramite.setText("Tipo de tr\u00e1mite:");

		jComboBoxTipoTramite.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2" }));

		jComboBoxClaseLicencia
				.setModel(new javax.swing.DefaultComboBoxModel(
						new String[] { "A-1: Ciclomotores c/ cilindrada menor a 50cc" }));

		jLabelClaseDeLicencia.setText("Clase de licencia:");

		buttonGroupSexo.add(jRadioButtonSexoAmbos);
		jRadioButtonSexoAmbos.setSelected(true);
		jRadioButtonSexoAmbos.setText("Ambos");

		javax.swing.GroupLayout jPanelFiltrosLayout = new javax.swing.GroupLayout(
				jPanelFiltros);
		jPanelFiltros.setLayout(jPanelFiltrosLayout);
		jPanelFiltrosLayout
				.setHorizontalGroup(jPanelFiltrosLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelFiltrosLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanelFiltrosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanelFiltrosLayout
																		.createSequentialGroup()
																		.addGroup(
																				jPanelFiltrosLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel21)
																						.addComponent(
																								jLabel20)
																						.addComponent(
																								jLabel22))
																		.addGap(
																				36,
																				36,
																				36)
																		.addGroup(
																				jPanelFiltrosLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanelFiltrosLayout
																										.createSequentialGroup()
																										.addComponent(
																												jTextFieldEdadInicio,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												30,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jLabelEdad2)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jTextFieldEdadFin,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												33,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jLabelEdad3))
																						.addGroup(
																								jPanelFiltrosLayout
																										.createSequentialGroup()
																										.addComponent(
																												txtOtorgadaDesde,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												88,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jLabel6)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												txtOtorgadaHasta,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												88,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(
																												18,
																												18,
																												18)
																										.addComponent(
																												lbFechaEjemplo,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												103,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								jPanelFiltrosLayout
																										.createSequentialGroup()
																										.addComponent(
																												jRadioButtonMasculino)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jRadioButtonFemenino)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jRadioButtonSexoAmbos))))
														.addGroup(
																jPanelFiltrosLayout
																		.createSequentialGroup()
																		.addGroup(
																				jPanelFiltrosLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabelClaseDeLicencia)
																						.addComponent(
																								jLabelTipoTramite))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanelFiltrosLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								jComboBoxTipoTramite,
																								0,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jComboBoxClaseLicencia,
																								0,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))))
										.addContainerGap(72, Short.MAX_VALUE)));
		jPanelFiltrosLayout
				.setVerticalGroup(jPanelFiltrosLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanelFiltrosLayout
										.createSequentialGroup()
										.addGroup(
												jPanelFiltrosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel20)
														.addComponent(
																txtOtorgadaDesde,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel6)
														.addComponent(
																txtOtorgadaHasta,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lbFechaEjemplo))
										.addGap(1, 1, 1)
										.addGroup(
												jPanelFiltrosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel21)
														.addComponent(
																jRadioButtonMasculino)
														.addComponent(
																jRadioButtonFemenino)
														.addComponent(
																jRadioButtonSexoAmbos))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelFiltrosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel22)
														.addComponent(
																jLabelEdad2)
														.addComponent(
																jTextFieldEdadFin,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jTextFieldEdadInicio,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jLabelEdad3))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelFiltrosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelTipoTramite)
														.addComponent(
																jComboBoxTipoTramite,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanelFiltrosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelClaseDeLicencia)
														.addComponent(
																jComboBoxClaseLicencia,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jPanel1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Agrupar resultados por"));

		buttonGroupAgrupados.add(jRadioSexo);
		jRadioSexo.setSelected(true);
		jRadioSexo.setText("Sexo");
		jRadioSexo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioSexoActionPerformed(evt);
			}
		});

		buttonGroupAgrupados.add(jRadioGS);
		jRadioGS.setText("Grupo Sangu\u00edneo");

		buttonGroupAgrupados.add(jRadioClaseLicencia);
		jRadioClaseLicencia.setText("Clase de Licencia");
		jRadioClaseLicencia
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioClaseLicenciaActionPerformed(evt);
					}
				});

		buttonGroupAgrupados.add(jRadioTipoTramite);
		jRadioTipoTramite.setText("Tipo Tr\u00e1mite");
		jRadioTipoTramite
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioTipoTramiteActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addGroup(
						jPanel1Layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jRadioSexo)
								.addComponent(jRadioGS).addComponent(
										jRadioClaseLicencia).addComponent(
										jRadioTipoTramite)).addContainerGap(18,
						Short.MAX_VALUE)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addComponent(jRadioSexo)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jRadioGS)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jRadioClaseLicencia)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jRadioTipoTramite)
										.addContainerGap(42, Short.MAX_VALUE)));

		btnGenerar.setText("Generar Estad\u00edsticas");
		btnGenerar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGenerarActionPerformed(evt);
			}
		});

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.PANEL_RESULTADOS_BUSQUEDA,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel2.setFocusable(false);

		jScrollPane1.setMinimumSize(new java.awt.Dimension(640, 402));
		jScrollPane1.setPreferredSize(new java.awt.Dimension(640, 402));
		jScrollPane1.setRequestFocusEnabled(false);

		tableEstadisticas.setModel(new javax.swing.table.DefaultTableModel(
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
		tableEstadisticas.setMinimumSize(null);
		tableEstadisticas.setPreferredSize(null);
		tableEstadisticas.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tableEstadisticasMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(tableEstadisticas);

		btnExportar.setText("Exportar a excel");
		btnExportar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExportarActionPerformed(evt);
			}
		});

		lbSinResultados.setForeground(new java.awt.Color(204, 0, 0));
		lbSinResultados.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_ERROR)));
		lbSinResultados.setText(Constantes.ERROR_SIN_RESULTADOS);

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
										.addGap(2, 2, 2)
										.addComponent(
												lbSinResultados,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												317,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btnExportar,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												168, Short.MAX_VALUE))
						.addComponent(jScrollPane1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 492,
								Short.MAX_VALUE));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												240,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																btnExportar)
														.addComponent(
																lbSinResultados,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																24,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));

		jPanelGrafico.setLayout(null);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
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
														.addComponent(
																jPanelFiltros,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
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
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jPanel1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnGenerar))
														.addComponent(
																jPanelGrafico,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																398,
																javax.swing.GroupLayout.PREFERRED_SIZE))
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
														.addGroup(
																layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				false)
																		.addComponent(
																				jPanel1,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jPanelFiltros,
																				javax.swing.GroupLayout.Alignment.LEADING,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
														.addComponent(
																btnGenerar))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jPanelGrafico,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanel2,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																303,
																Short.MAX_VALUE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			final VentanaExaminar ventanaExaminar = new VentanaExaminar(
					JFileChooser.DIRECTORIES_ONLY, JFileChooser.SAVE_DIALOG);
			ventanaExaminar.pack();
			Util.agregarIframe(ventanaExaminar);
			ventanaExaminar.doModal(this.getRootPane());
			ventanaExaminar.setVisible(true);

			if (ventanaExaminar.getRutaSeleccionada() != null) {

				List<Object[]> datos = ((TableModelEstadistica) tableEstadisticas
						.getModel()).getLst();
				List heads = new ArrayList();
				heads.add(tituloTabla);
				heads.add("Cantidad");
				java.io.ByteArrayOutputStream bao = ExcelGenerator
						.generateExcelFromList("Valores estadísticos", datos,
								heads);

				File archivoSeleccionado = new File(ventanaExaminar
						.getRutaSeleccionada());
				String fileDestino = archivoSeleccionado.getAbsolutePath()+File.separator+"valoresEstadísticos.xls";
				testerGeneral.persistence.impl.Util.toFile(fileDestino, bao
						.toByteArray());
				Process p = Runtime.getRuntime().exec(
						"rundll32 url.dll,FileProtocolHandler " + fileDestino);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void tableEstadisticasMouseClicked(java.awt.event.MouseEvent evt) {
		//seleccionarPersona();
	}

	private void jRadioTipoTramiteActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jRadioClaseLicenciaActionPerformed(
			java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jRadioSexoActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jRadioDetalladaActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jRadioResumidaActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {
		generarReporte();
	}

	public void generarReporte() {
		try {
			List<Object[]> datos = filtrar();
			setTableModel(datos);
			PieDataset dataset = createDataset(datos);
			crearGrafico(dataset);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private PieDataset createDataset(List<Object[]> datos) {
		DefaultPieDataset dataset = new DefaultPieDataset();

		for (Object[] dato : datos) {
			if (domainToConvert != null && dato[0] != null) {
				try {
					String codigo = (String) dato[0];
					Dominio dominio = new Dominio();
					dominio.setDomClave(domainToConvert);
					dominio.setDomCodigo(codigo);
					DominioDefinition dominioService = (DominioDefinition) ContextManager
							.getBizObject("dominioService");
					List<Dominio> lst = dominioService.getAll(dominio);

					if (lst.size() > 0) {
						dataset.setValue(lst.get(0).getDomValorMostrar(),
								(java.lang.Number) dato[1]);
					} else
						dataset.setValue((String) dato[0],
								(java.lang.Number) dato[1]);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			} else
				dataset.setValue((String) dato[0], (java.lang.Number) dato[1]);
		}
		return dataset;
	}

	public void crearGrafico(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart3D("", dataset, true,
				true, false);
		//JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, legend, tooltips, urls)

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setNoDataMessage("No data available");
		plot.setCircular(true);
		//plot.setLabelGap(0.02);
		//plot.setLabelLinksVisible(false);
		plot.setLabelGenerator(null);
		//plot.setLabel

		ChartPanel panelChart = new ChartPanel(chart);
		panelChart.setBounds(0, 0,420,310);
		jPanelGrafico.removeAll();
		jPanelGrafico.setSize(420,310);
		jPanelGrafico.add(panelChart);
		jPanelGrafico.repaint();
		jPanelGrafico.validate();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> filtrar() {
		log.info("filtrar");
		List<Object[]> datos = new ArrayList();

		Date fechaInicioEdad = null;
		Date fechaFinEdad = null;
		Date fechaDesdeOtor = null;
		Date fechaHastaOtor = null;

		try {
			Util.personaSinResultados(lbSinResultados, true);
			Persona per = new Persona();
			Licencia lic = new Licencia();
			lic.setPersona(per);

			//FECHA OTOR
			if (valorPorDefectoFecha.compareTo(txtOtorgadaDesde.getText()) != 0) {
				fechaDesdeOtor = validarFecha(txtOtorgadaDesde);
				if (fechaDesdeOtor != null)
					Util.redondearFecha(fechaDesdeOtor);
			}

			if (valorPorDefectoFecha.compareTo(txtOtorgadaHasta.getText()) != 0) {
				fechaHastaOtor = validarFecha(txtOtorgadaHasta);
				if (fechaHastaOtor != null)
					Util.redondearFecha(fechaHastaOtor);
			}

			//EDAD
			if (!jTextFieldEdadInicio.getText().isEmpty()) {

				Calendar hoy = Calendar.getInstance();
				hoy.add(Calendar.YEAR, -Integer.valueOf(jTextFieldEdadInicio
						.getText()));
				fechaInicioEdad = hoy.getTime();
			}

			if (!jTextFieldEdadFin.getText().isEmpty()) {

				Calendar hoy = Calendar.getInstance();
				hoy.add(Calendar.YEAR, -Integer.valueOf(jTextFieldEdadFin
						.getText()) - 1);
				fechaFinEdad = hoy.getTime();
			}

			//SEXO
			if (jRadioButtonMasculino.isSelected()) {
				per.setPerSexo("H");
			}
			if (jRadioButtonFemenino.isSelected()) {
				per.setPerSexo("M");
			}
			if (jRadioButtonSexoAmbos.isSelected()) {
				per.setPerSexo(null);
			}

			Dominio dom = (Dominio) jComboBoxTipoTramite.getSelectedItem();
			if (dom.getDomClave() != null)
				lic.setLicTramite(dom.getDomCodigo());

			ClaseLicencia clase = (ClaseLicencia) jComboBoxClaseLicencia
					.getSelectedItem();
			if (clase.getCllId() != null)
				lic.setLicClase(clase.getCllNombreClase());

			if (lbSinResultados.getIcon() == null) {
				String agrupador = null;

				if (jRadioSexo.isSelected()) {
					tituloTabla = "Sexo";
					agrupador = "persona.perSexo";
					domainToConvert = Constantes.DOMINIO_CLAVE_SEXO;
				} else if (jRadioGS.isSelected()) {
					tituloTabla = "Grupo Sanguíneo";
					agrupador = "persona.perGrupoSanguineo";
					domainToConvert = null;
				} else if (jRadioClaseLicencia.isSelected()) {
					tituloTabla = "Clase Licencia";
					agrupador = "licClase";
					domainToConvert = null;
				} else if (jRadioClaseLicencia.isSelected()) {
					tituloTabla = "Clase Licencia";
					agrupador = "licClase";
					domainToConvert = null;
				} else if (jRadioTipoTramite.isSelected()) {
					tituloTabla = "Tipo Trámite";
					agrupador = "licTramite";
					domainToConvert = null;
				}

				datos = licenciaService.getAllEstadisticas(lic, fechaDesdeOtor,
						fechaHastaOtor, fechaInicioEdad, fechaFinEdad,
						agrupador);
			}

			return datos;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public Date validarFecha(JFormattedTextField txtDate) {
		AbstractFormatter formatter = txtDate.getFormatter();
		if (formatter != null) {
			String text = txtDate.getText();
			try {
				formatter.stringToValue(text);
				SimpleDateFormat sdf = new SimpleDateFormat(Util.formatoFecha);
				sdf.setLenient(false);
				//lic.setLicFechaOtorgada(sdf.parse(txtDate.getText()));
				return sdf.parse(txtDate.getText());
			} catch (ParseException pe) {
				//lic.setLicFechaOtorgada(null);
				Util.mostrarError(lbSinResultados,
						Constantes.ERROR_PER_FECHA_SINFORMATO, false);
				return null;
			}
		}
		return null;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnExportar;
	private javax.swing.JButton btnGenerar;
	private javax.swing.ButtonGroup buttonGroupAgrupados;
	private javax.swing.ButtonGroup buttonGroupSexo;
	private javax.swing.ButtonGroup buttonGroupVencido;
	private javax.swing.JComboBox jComboBoxClaseLicencia;
	private javax.swing.JComboBox jComboBoxTipoTramite;
	private javax.swing.JLabel jLabel20;
	private javax.swing.JLabel jLabel21;
	private javax.swing.JLabel jLabel22;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabelClaseDeLicencia;
	private javax.swing.JLabel jLabelEdad2;
	private javax.swing.JLabel jLabelEdad3;
	private javax.swing.JLabel jLabelTipoTramite;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanelFiltros;
	private javax.swing.JPanel jPanelGrafico;
	private javax.swing.JRadioButton jRadioButtonFemenino;
	private javax.swing.JRadioButton jRadioButtonMasculino;
	private javax.swing.JRadioButton jRadioButtonSexoAmbos;
	private javax.swing.JRadioButton jRadioClaseLicencia;
	private javax.swing.JRadioButton jRadioGS;
	private javax.swing.JRadioButton jRadioSexo;
	private javax.swing.JRadioButton jRadioTipoTramite;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextField jTextFieldEdadFin;
	private javax.swing.JTextField jTextFieldEdadInicio;
	private javax.swing.JLabel lbFechaEjemplo;
	private javax.swing.JLabel lbSinResultados;
	private javax.swing.JTable tableEstadisticas;
	private javax.swing.JFormattedTextField txtOtorgadaDesde;
	private javax.swing.JFormattedTextField txtOtorgadaHasta;
	// End of variables declaration//GEN-END:variables
	private String valorPorDefectoFecha;
	private String tituloTabla;
	private String domainToConvert;
}