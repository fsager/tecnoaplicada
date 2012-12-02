/*
 * PanelLicenciasHistorico.java
 *
 * Created on __DATE__, __TIME__
 */

package autoimpresor.frontend.paneles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import testerGeneral.comparetors.DateComparator;
import testerGeneral.domain.Constantes;
import autoimpresor.business.ContextManager;
import autoimpresor.domain.CarnetLicencias;
import autoimpresor.domain.CarnetLicenciasQR;
import autoimpresor.domain.Licencia;
import autoimpresor.domain.Persona;
import autoimpresor.frontend.tablemodels.TableModelLicenciaFull;
import autoimpresor.service.LicenciaDefinition;
import frontend.buttons.ButtonBuscar;
import frontend.utils.Util;
import frontend.ventanas.JInternalFrameTesterGral;
import frontend.ventanas.VentanaReportes;

/**
 *
 * @author  __USER__
 */
public class PanelLicenciasHistorico extends javax.swing.JPanel {

	private Date fechaDesde = null;
	private Date fechaHasta = null;
	
	/** Creates new form PanelLicenciasHistorico */
	public PanelLicenciasHistorico() {
		lic = new Licencia();
		lic.setLicEstado("H");
		initComponents();
		btnMoverPendiente.setVisible(false);
		((PanelMenuPrincipal) Util.panelMenu).calcularLicenciasPorEstado();
		lbFechaEjemplo.setText(Util.mostrasMascara());
		valorPorDefectoFecha = txtOtorgadaDesde.getText();
		setTableModelLicencias(new ArrayList());
		Util.personaSinResultados(lbSinResultados, true);

		if (ContextManager.getProperty(
				"SISTEMA.MUNICIPIO.ES_CENTRO_IMPRESOR_S_N").equals("S")) {
			btnReimprimir.setVisible(true);
		} else {
			btnReimprimir.setVisible(false);
		}

	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		btnMoverPendiente = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		txtBusquedaApellido = new javax.swing.JTextField();
		btnBuscar = new ButtonBuscar();
		txtBusquedaNombre = new javax.swing.JTextField();
		jLabel19 = new javax.swing.JLabel();
		jLabel20 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		txtBusquedaDni = new javax.swing.JFormattedTextField();
		txtOtorgadaDesde = Util.setFecha();
		lbFechaEjemplo = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		txtOtorgadaHasta = Util.setFecha();
		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tableLicencias = new javax.swing.JTable();
		lbSinResultados = new javax.swing.JLabel();
		jLabel21 = new javax.swing.JLabel();
		txtImporteTotal = new javax.swing.JFormattedTextField();
		btnReimprimir = new javax.swing.JButton();
		btnImprimirResultados = new javax.swing.JButton();

		btnMoverPendiente.setText("Mover a pendiente licencias seleccionadas");
		btnMoverPendiente.setEnabled(false);
		btnMoverPendiente
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnMoverPendienteActionPerformed(evt);
					}
				});

		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.PANEL_FILTROS_BUSQUEDA,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel3.setFocusable(false);

		jLabel3.setText(Constantes.LB_APELLIDO);

		txtBusquedaApellido.setMaximumSize(new java.awt.Dimension(273, 22));
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

		txtBusquedaNombre.setMaximumSize(new java.awt.Dimension(273, 22));
		txtBusquedaNombre
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						txtBusquedaNombreActionPerformed(evt);
					}
				});

		jLabel19.setText(Constantes.LB_NOMBRE);

		jLabel20.setText("Otorgada:");

		jLabel8.setText(Constantes.LB_NRO_DOCUMENTO);

		txtBusquedaDni.setMaximumSize(new java.awt.Dimension(273, 22));
		txtBusquedaDni.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtBusquedaDniActionPerformed(evt);
			}
		});

		lbFechaEjemplo.setFont(new java.awt.Font("Segoe UI", 0, 11));
		lbFechaEjemplo.setText("jLabel17");

		jLabel6.setText("y");

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
																								txtBusquedaApellido,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								300,
																								Short.MAX_VALUE)
																						.addComponent(
																								txtBusquedaNombre,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								300,
																								Short.MAX_VALUE)
																						.addGroup(
																								jPanel3Layout
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
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												90,
																												Short.MAX_VALUE))))
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel8,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				112,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				txtBusquedaDni,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				301,
																				Short.MAX_VALUE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
																								txtBusquedaNombre,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel19))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel3Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel20)
																						.addComponent(
																								txtOtorgadaDesde,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel6)
																						.addComponent(
																								txtOtorgadaHasta,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lbFechaEjemplo)))
														.addComponent(
																btnBuscar,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																48,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));

		((ButtonBuscar) btnBuscar).init();

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				Constantes.PANEL_RESULTADOS_BUSQUEDA,
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));
		jPanel1.setFocusable(false);

		jScrollPane1.setMinimumSize(new java.awt.Dimension(640, 402));
		jScrollPane1.setPreferredSize(new java.awt.Dimension(640, 402));
		jScrollPane1.setRequestFocusEnabled(false);

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
		tableLicencias.setMinimumSize(null);
		tableLicencias.setPreferredSize(null);
		tableLicencias.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tableLicenciasMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(tableLicencias);

		lbSinResultados.setForeground(new java.awt.Color(204, 0, 0));
		lbSinResultados.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_ERROR)));
		lbSinResultados.setText(Constantes.ERROR_SIN_RESULTADOS);

		jLabel21.setText("Importe Total:");

		txtImporteTotal.setEditable(false);
		txtImporteTotal
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0.00"))));

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
																699,
																Short.MAX_VALUE)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				lbSinResultados,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				355,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				144,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel21)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				txtImporteTotal,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				120,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												335,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																lbSinResultados,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																24,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel21)
														.addComponent(
																txtImporteTotal,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))));

		btnReimprimir.setText("Imprimir licencias seleccionadas");
		btnReimprimir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnReimprimirActionPerformed(evt);
			}
		});

		btnImprimirResultados.setText("Imprimir Resultados");
		btnImprimirResultados
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnImprimirResultadosActionPerformed(evt);
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
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jPanel1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																layout
																		.createSequentialGroup()
																		.addComponent(
																				btnImprimirResultados)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnReimprimir)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				131,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnMoverPendiente))
														.addComponent(
																jPanel3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
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
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																btnMoverPendiente,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnImprimirResultados,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnReimprimir,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnImprimirResultadosActionPerformed(
			java.awt.event.ActionEvent evt) {
		
		Util.ocultarSinResultados(lbSinResultados, true);
		String srcString = "reportes/carnetsImpresosImportes.jasper";
		TableModelLicenciaFull tableModel = (TableModelLicenciaFull) tableLicencias
				.getModel();

		if(fechaDesde==null || fechaHasta==null){
			Util.mostrarError(lbSinResultados,"Ingrese un rango de fechas.", false);
		}
		if(tableModel.getLst().size()>0)
		{
			HashMap parameterMap = new HashMap();

			parameterMap.put("fecha_desde", sdf.format(fechaDesde));
			parameterMap.put("fecha_hasta", sdf.format(fechaHasta));
			parameterMap.put("total", "" + tableModel.getLst().size());
			parameterMap.put("importeTotal",txtImporteTotal.getValue());

			VentanaReportes ventanaReportes = new VentanaReportes(this,
					parameterMap, srcString, tableModel.getLst());			
		}

	}

	private void btnReimprimirActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			String nombreMunicipio = ContextManager
					.getProperty("SISTEMA.NOMBRE.MUNICIPIO");
			String codigoMunicipio = ContextManager
					.getProperty("SISTEMA.CODIGO.MUNICIPIO");
			byte[] escudoMunicipio = ContextManager.getPropertyObj(
					"SISTEMA.FOTO.MUNICIPIO").getPropBlob();

			List<CarnetLicencias> carnets = new ArrayList<CarnetLicencias>();
			int[] rows = tableLicencias.getSelectedRows();
			for (int i = 0; i < rows.length; i++) {
				int sel = tableLicencias.convertRowIndexToModel(rows[i]);
				Licencia lic = ((TableModelLicenciaFull) tableLicencias
						.getModel()).getValueAt(sel);

				CarnetLicencias carnet = new CarnetLicenciasQR(lic,
						nombreMunicipio, codigoMunicipio, escudoMunicipio);
				carnets.add(carnet);
			}

			final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral(
					"Imprimir", false, true, false, false);
			PanelMargenesImpresion panel = new PanelMargenesImpresion(carnets);
			internalframe.add(panel);
			internalframe.pack();

			Util.centrarIframes(internalframe);

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

			internalframe.doModal(Util.framePrincipal.getRootPane());
			internalframe.setVisible(true);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void txtBusquedaDniActionPerformed(java.awt.event.ActionEvent evt) {
		buscarLicencias();
	}

	private void txtBusquedaNombreActionPerformed(java.awt.event.ActionEvent evt) {
		buscarLicencias();
	}

	private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
		buscarLicencias();
	}

	private void txtBusquedaApellidoActionPerformed(
			java.awt.event.ActionEvent evt) {
		buscarLicencias();
	}

	private void btnMoverPendienteActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			int[] rows = tableLicencias.getSelectedRows();
			for (int i = 0; i < rows.length; i++) {
				int sel = tableLicencias.convertRowIndexToModel(rows[i]);
				Licencia lic = ((TableModelLicenciaFull) tableLicencias
						.getModel()).getValueAt(sel);
				lic.setLicEstado("P");

				licenciaService.update(lic);
			}
			((PanelMenuPrincipal) Util.panelMenu).calcularLicenciasPorEstado();
			buscarLicencias();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void tableLicenciasMouseClicked(java.awt.event.MouseEvent evt) {
		//seleccionarPersona();
	}

	public void setTableModelLicencias(List lst) {
		TableModelLicenciaFull tableModel = new TableModelLicenciaFull();
		tableModel.setLst(lst);
		tableLicencias.setModel(tableModel);
		tableLicencias
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableLicencias.setRowSorter(null);

		TableColumn column = tableLicencias.getColumnModel().getColumn(1);
		column.setPreferredWidth(90);
		column.setWidth(90);
		column.setMinWidth(90);

		column = tableLicencias.getColumnModel().getColumn(4);
		column.setPreferredWidth(55);
		column.setWidth(55);
		column.setMinWidth(55);

		column = tableLicencias.getColumnModel().getColumn(5);
		column.setPreferredWidth(60);
		column.setWidth(60);
		column.setMinWidth(60);

		column = tableLicencias.getColumnModel().getColumn(6);
		column.setPreferredWidth(35);
		column.setWidth(35);
		column.setMinWidth(35);

		column = tableLicencias.getColumnModel().getColumn(7);
		column.setPreferredWidth(35);
		column.setWidth(35);
		column.setMinWidth(35);

		//tableLicencias.setAutoCreateRowSorter(false);

		if (lst.size() > 0) {
			//tableLicencias.setAutoCreateRowSorter(true);
			TableRowSorter sorter = new TableRowSorter(tableLicencias
					.getModel());
			sorter.setComparator(3, new DateComparator(0));
			sorter.setComparator(4, new DateComparator(0));
			tableLicencias.setRowSorter(sorter);
			btnMoverPendiente.setEnabled(true);
		} else {
			btnMoverPendiente.setEnabled(false);
			Util.ocultarSinResultados(lbSinResultados, false);
		}
	}

	public void buscarLicencias() {
		LicenciaDefinition licenciaService = (LicenciaDefinition) ContextManager
				.getBizObject("licenciaService");
		fechaDesde = null;
		fechaHasta = null;
		boolean error = false;
		setTableModelLicencias(new ArrayList());
		txtImporteTotal.setText("");
		try {
			Util.ocultarSinResultados(lbSinResultados, true);
			Persona per = new Persona();
			lic.setPersona(per);
			lic.getPersona()
					.setPerApellido(txtBusquedaApellido.getText() + "%");
			lic.getPersona().setPerNombre(txtBusquedaNombre.getText() + "%");

			if (!txtBusquedaDni.getText().equals("")
					&& !Util.validarDni(txtBusquedaDni.getText())) {
				lic.getPersona().setPerNumeroDoc(null);
				Util.mostrarError(lbSinResultados,
						Constantes.ERROR_PER_DNI_SINFORMATO, false);
				error = true;

			} else if (!txtBusquedaDni.getText().equals("")
					&& Util.validarDni(txtBusquedaDni.getText())) {
				lic.getPersona().setPerNumeroDoc(txtBusquedaDni.getText());
			}

			if (valorPorDefectoFecha.compareTo(txtOtorgadaDesde.getText()) != 0) {
				fechaDesde = validarFecha(txtOtorgadaDesde);
				if (fechaDesde != null)
					fechaHasta = validarFecha(txtOtorgadaHasta);

				if (fechaHasta == null)
					error = true;

			}

			List<Licencia> liciencias = null;
			if (!error) {
				if (fechaDesde != null && fechaHasta != null) {
					Util.redondearFecha(fechaDesde);
					Util.redondearFecha(fechaHasta);
					liciencias = licenciaService.getAll(lic, fechaDesde,
							fechaHasta);
				} else
					liciencias = licenciaService.getAll(lic);
			}

			if (!error)
				setTableModelLicencias(liciencias);

			setCantidadResultados(0);
			if (!error && liciencias != null && liciencias.size() > 0) {
				tableLicencias.setRowSelectionInterval(0, 0);
				setCantidadResultados(liciencias.size());
				//set importe total
				double importeTotal = 0;
				for (Licencia licencia : liciencias) {
					if (licencia.getLicImporte() != null)
						importeTotal += licencia.getLicImporte();
				}

				txtImporteTotal.setValue(importeTotal);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setCantidadResultados(int cant) {

		((TitledBorder) jPanel1.getBorder())
				.setTitle(Constantes.PANEL_RESULTADOS_BUSQUEDA + ": " + cant);
		jPanel1.validate();
		jPanel1.repaint();

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
	private javax.swing.JButton btnBuscar;
	private javax.swing.JButton btnImprimirResultados;
	private javax.swing.JButton btnMoverPendiente;
	private javax.swing.JButton btnReimprimir;
	private javax.swing.JLabel jLabel19;
	private javax.swing.JLabel jLabel20;
	private javax.swing.JLabel jLabel21;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lbFechaEjemplo;
	private javax.swing.JLabel lbSinResultados;
	private javax.swing.JTable tableLicencias;
	private javax.swing.JTextField txtBusquedaApellido;
	private javax.swing.JFormattedTextField txtBusquedaDni;
	private javax.swing.JTextField txtBusquedaNombre;
	private javax.swing.JFormattedTextField txtImporteTotal;
	private javax.swing.JFormattedTextField txtOtorgadaDesde;
	private javax.swing.JFormattedTextField txtOtorgadaHasta;
	// End of variables declaration//GEN-END:variables
	private Licencia lic;
	private SimpleDateFormat sdf = new SimpleDateFormat(Util.formatoFecha);
	private String valorPorDefectoFecha;
	private DateComparator com = new DateComparator();
	LicenciaDefinition licenciaService = (LicenciaDefinition) ContextManager
			.getBizObject("licenciaService");
}