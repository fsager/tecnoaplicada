/*
 * PanelLicenciasPendientes.java
 *
 * Created on __DATE__, __TIME__
 */

package autoimpresor.frontend.paneles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;

import testerGeneral.comparetors.DateComparator;
import testerGeneral.domain.Constantes;
import autoimpresor.business.ContextManager;
import autoimpresor.domain.CarnetLicencias;
import autoimpresor.domain.ClaseLicencia;
import autoimpresor.domain.Licencia;
import autoimpresor.frontend.tablemodels.TableModelLicenciaFull;
import autoimpresor.mails.MailSender;
import autoimpresor.mails.UeMail;
import autoimpresor.mails.UeMailAttach;
import autoimpresor.service.ClaseLicenciaDefinition;
import autoimpresor.service.LicenciaDefinition;
import frontend.utils.Util;
import frontend.ventanas.JInternalFrameTesterGral;

/**
 * 
 * @author __USER__
 */
public class PanelLicenciasPendientes extends javax.swing.JPanel {

	/** Creates new form PanelLicenciasPendientes */
	public PanelLicenciasPendientes() {
		initComponents();
		((PanelMenuPrincipal) Util.panelMenu).calcularLicenciasPorEstado();
		setTableModelLicencias(new ArrayList());
		cargarLicencias();

		if (ContextManager.getProperty(
				"SISTEMA.MUNICIPIO.ES_CENTRO_IMPRESOR_S_N").equals("S")) {
			btnExportarTodo.setVisible(false);
			btnExportarSel.setVisible(false);
			btnReimprimir.setVisible(true);
			btnReimprimirPendietes.setVisible(true);
		} else {
			btnExportarTodo.setVisible(true);
			btnExportarSel.setVisible(true);
			btnReimprimir.setVisible(false);
			btnReimprimirPendietes.setVisible(false);
		}
	}

	public void cargarLicencias() {
		try {
			System.out
					.println("cargarLicencias cargarLicencias cargarLicencias");

			Licencia lic = new Licencia();
			lic.setLicEstado("P");
			List lst = licenciaService.getAll(lic);
			setTableModelLicencias(lst);

			if (lst.size() > 0) {
				tableLicencias.setRowSelectionInterval(0, 0);
				btnExportarTodo.setEnabled(true);
				btnExportarSel.setEnabled(true);
				btnReimprimir.setEnabled(true);
				btnReimprimirPendietes.setEnabled(true);
				btnEliminarLicenciaSeleccionada.setEnabled(true);
				btnPlantillaConf.setEnabled(true);
			} else {
				btnExportarTodo.setEnabled(false);
				btnExportarSel.setEnabled(false);
				btnReimprimir.setEnabled(false);
				btnReimprimirPendietes.setEnabled(false);
				btnEliminarLicenciaSeleccionada.setEnabled(false);
				btnPlantillaConf.setEnabled(false);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setTableModelLicencias(List lst) {
		if (tableLicencias.getModel() instanceof TableModelLicenciaFull)
			((TableModelLicenciaFull) tableLicencias.getModel()).removeAll();

		TableModelLicenciaFull tableModel = new TableModelLicenciaFull();
		tableModel.setLst(lst);
		tableLicencias.setModel(tableModel);
		tableLicencias
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableLicencias.setAutoCreateRowSorter(false);

		if (lst.size() > 0) {
			TableRowSorter sorter = new TableRowSorter(tableLicencias
					.getModel());
			sorter.setComparator(3, new DateComparator(0));
			sorter.setComparator(4, new DateComparator(0));
			tableLicencias.setRowSorter(sorter);
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		tableLicencias = new javax.swing.JTable();
		btnExportarTodo = new javax.swing.JButton();
		btnExportarSel = new javax.swing.JButton();
		btnReimprimirPendietes = new javax.swing.JButton();
		btnReimprimir = new javax.swing.JButton();
		btnEliminarLicenciaSeleccionada = new javax.swing.JButton();
		btnPlantillaConf = new javax.swing.JButton();

		jScrollPane1.setMinimumSize(new java.awt.Dimension(828, 458));

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

		btnExportarTodo.setText("Exportar y Enviar Todas las Licencias");
		btnExportarTodo.setEnabled(false);
		btnExportarTodo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExportarTodoActionPerformed(evt);
			}
		});

		btnExportarSel.setText("Exportar y Enviar Licencia Seleccionadas");
		btnExportarSel.setEnabled(false);
		btnExportarSel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExportarSelActionPerformed(evt);
			}
		});

		btnReimprimirPendietes.setText("Imprimir Pendientes");
		btnReimprimirPendietes.setEnabled(false);
		btnReimprimirPendietes
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnReimprimirPendietesActionPerformed(evt);
					}
				});

		btnReimprimir.setText("Imprimir licencias seleccionadas");
		btnReimprimir.setEnabled(false);
		btnReimprimir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnReimprimirActionPerformed(evt);
			}
		});

		btnEliminarLicenciaSeleccionada
				.setText("Eliminar licencia seleccionada");
		btnEliminarLicenciaSeleccionada.setEnabled(false);
		btnEliminarLicenciaSeleccionada
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnEliminarLicenciaSeleccionadaActionPerformed(evt);
					}
				});

		btnPlantillaConf.setForeground(new java.awt.Color(0, 0, 255));
		btnPlantillaConf.setText("Imprimir plantilla de conformidad");
		btnPlantillaConf.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPlantillaConfActionPerformed(evt);
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
																jScrollPane1,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																1242,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																layout
																		.createSequentialGroup()
																		.addComponent(
																				btnEliminarLicenciaSeleccionada)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				30,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnPlantillaConf)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnReimprimirPendietes)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnReimprimir)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnExportarTodo,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				210,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnExportarSel,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				232,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
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
												jScrollPane1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												464, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE,
																false)
														.addComponent(
																btnExportarSel,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnExportarTodo,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnReimprimir,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnReimprimirPendietes,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnEliminarLicenciaSeleccionada,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																20,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnPlantillaConf,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																19,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnPlantillaConfActionPerformed(java.awt.event.ActionEvent evt) {
		int row = tableLicencias.getSelectedRow();
		int sel = tableLicencias.convertRowIndexToModel(row);

		PanelNuevaLicenciaDeUsuario
				.imprimirPlantillaConformida(((TableModelLicenciaFull) tableLicencias
						.getModel()).getValueAt(sel));
	}

	private void btnEliminarLicenciaSeleccionadaActionPerformed(
			java.awt.event.ActionEvent evt) {
		List<Licencia> licencias = new ArrayList();
		int[] rows = tableLicencias.getSelectedRows();
		for (int i = 0; i < rows.length; i++) {
			int sel = tableLicencias.convertRowIndexToModel(rows[i]);

			licencias.add(((TableModelLicenciaFull) tableLicencias.getModel())
					.getValueAt(sel));
		}

		eliminarLicencias(licencias);
	}

	private void btnReimprimirActionPerformed(java.awt.event.ActionEvent evt) {
		try {

			List<Licencia> licencias = new ArrayList();
			int[] rows = tableLicencias.getSelectedRows();
			for (int i = 0; i < rows.length; i++) {
				int sel = tableLicencias.convertRowIndexToModel(rows[i]);
				licencias.add(((TableModelLicenciaFull) tableLicencias
						.getModel()).getValueAt(sel));
			}

			List<CarnetLicencias> carnetLicencias = listaLicentasToListaCarnets(licencias);
			abrirVentanaMargenes(carnetLicencias, licencias);
			cargarLicencias();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void abrirVentanaMargenes(List<CarnetLicencias> carnets,
			final List<Licencia> licencias) {
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

		if (panel.isImprimio())
			updateLicencias(licencias);
	}

	private void btnReimprimirPendietesActionPerformed(
			java.awt.event.ActionEvent evt) {
		try {
			List<Licencia> licencias = ((TableModelLicenciaFull) tableLicencias
					.getModel()).getLst();
			List<CarnetLicencias> carnetLicencias = listaLicentasToListaCarnets(licencias);
			abrirVentanaMargenes(carnetLicencias, licencias);
			cargarLicencias();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void btnExportarSelActionPerformed(java.awt.event.ActionEvent evt) {
		List<Licencia> licencias = new ArrayList();
		int[] rows = tableLicencias.getSelectedRows();
		for (int i = 0; i < rows.length; i++) {
			int sel = tableLicencias.convertRowIndexToModel(rows[i]);
			licencias.add(((TableModelLicenciaFull) tableLicencias.getModel())
					.getValueAt(sel));
		}

		exportar(licencias);
	}

	private void btnExportarTodoActionPerformed(java.awt.event.ActionEvent evt) {
		List<Licencia> licencias = ((TableModelLicenciaFull) tableLicencias
				.getModel()).getLst();
		exportar(licencias);
	}

	private void tableLicenciasMouseClicked(java.awt.event.MouseEvent evt) {
		// seleccionarPersona();
	}

	public void exportar(final List<Licencia> licencias) {
		Util.mostrarPanelOperacionesLargas();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {

					List<CarnetLicencias> carnetLicencias = listaLicentasToListaCarnets(licencias);
					sendMail(carnetLicencias);
					updateLicencias(licencias);
					cargarLicencias();
				} catch (Exception e) {

					throw new RuntimeException(e);
				} finally {
					Util.ocultarPanelOperacionesLargas();
				}
			}
		});
	}

	/**
	 * Elimina de la base de datos las licencias que recibe como parámetro
	 * 
	 * @param licencias
	 */
	public void eliminarLicencias(final List<Licencia> licencias) {

		int op = JOptionPane.showConfirmDialog(getParent(),
				"¿Desea eliminar las licencias seleccionadas?", "Confirmación",
				JOptionPane.YES_NO_OPTION);
		if (op == JOptionPane.YES_OPTION) {

			Util.mostrarPanelOperacionesLargas();

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {

					try {
						for (int i = 0; i < licencias.size(); i++) {
							Licencia lic = licencias.get(i);
							licenciaService.delete(lic);
						}

						cargarLicencias();
					} catch (Exception e) {
						throw new RuntimeException(e);
					} finally {
						Util.ocultarPanelOperacionesLargas();
					}
				}

			});
		}
	}

	public List<CarnetLicencias> listaLicentasToListaCarnets(
			List<Licencia> licencias) {
		List<CarnetLicencias> carnetLicencias = new ArrayList();
		for (int i = 0; i < licencias.size(); i++) {
			Licencia lic = licencias.get(i);
			CarnetLicencias car = new CarnetLicencias(lic, nombreMunicipio,
					codigoMunicipio, escudoMunicipio);
			carnetLicencias.add(car);
		}

		return carnetLicencias;
	}

	public void sendMail(List<CarnetLicencias> carnetLicencias) {
		try {
			String fileName = fecha + "(" + nombreMunicipio + ").aut";
			File f = new File(fileName);

			testerGeneral.persistence.impl.Util.zipper(f, carnetLicencias);

			FileInputStream fin = new FileInputStream(f);
			byte adjunto[] = new byte[(int) f.length()];
			fin.read(adjunto);
			fin.close();
			f.delete();

			UeMail mail = new UeMail();
			mail.setUserFrom(ContextManager
					.getProperty("SISTEMA.EMAIL.CENTRO.IMPRESION.LICENCIAS"));
			mail.setReplyTo(ContextManager
					.getProperty("SISTEMA.EMAIL.CENTRO.IMPRESION.LICENCIAS"));
			mail.setSubject("Licencias del " + fecha);
			mail.setTexto("Municipalidad: " + nombreMunicipio
					+ ". Envío automático desde: "
					+ ContextManager.getProperty("SISTEMA.NOMBRE.PROGRAMA")
					+ " " + fecha + ". Enviadas " + carnetLicencias.size()
					+ " licencias");
			mail.setPrioridad(0L);
			mail.setUserTo(ContextManager
					.getProperty("SISTEMA.EMAIL.CENTRO.IMPRESION.LICENCIAS"));

			UeMailAttach ueMailAttach = new UeMailAttach();
			ueMailAttach.setUeMailPool(mail);
			ueMailAttach.setFileInfo(adjunto);
			ueMailAttach.setFileName(fileName);
			ueMailAttach.setMimeType("application/x-zip-compressed");

			ArrayList<UeMailAttach> lis = new ArrayList();
			lis.add(ueMailAttach);

			MailSender.sendMail(mail, lis);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void updateLicencias(List<Licencia> licencias) {
		try {
			for (int i = 0; i < licencias.size(); i++) {
				Licencia lic = licencias.get(i);
				lic.setLicEstado("H");
				licenciaService.update(lic);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnEliminarLicenciaSeleccionada;
	private javax.swing.JButton btnExportarSel;
	private javax.swing.JButton btnExportarTodo;
	private javax.swing.JButton btnPlantillaConf;
	private javax.swing.JButton btnReimprimir;
	private javax.swing.JButton btnReimprimirPendietes;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable tableLicencias;
	// End of variables declaration//GEN-END:variables
	LicenciaDefinition licenciaService = (LicenciaDefinition) ContextManager
			.getBizObject("licenciaService");
	ClaseLicenciaDefinition claseLicenciaService = (ClaseLicenciaDefinition) ContextManager
			.getBizObject("claseLicenciaService");

	private SimpleDateFormat sdf = new SimpleDateFormat(Util.formatoFecha
			+ " hh-mm");
	private String fecha = sdf.format(new Date());
	private String nombreMunicipio = ContextManager
			.getProperty("SISTEMA.NOMBRE.MUNICIPIO");
	private String codigoMunicipio = ContextManager
			.getProperty("SISTEMA.CODIGO.MUNICIPIO");
	private byte[] escudoMunicipio = ContextManager.getPropertyObj(
			"SISTEMA.FOTO.MUNICIPIO").getPropBlob();
}