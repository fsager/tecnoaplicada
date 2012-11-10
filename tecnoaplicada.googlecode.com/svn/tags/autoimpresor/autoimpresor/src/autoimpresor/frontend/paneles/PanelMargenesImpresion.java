/*
 * PanelMargenesImpresion.java
 *
 * Created on __DATE__, __TIME__
 */

package autoimpresor.frontend.paneles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
import testerGeneral.business.ContextManager;
import testerGeneral.domain.Propiedad;
import testerGeneral.service.PropiedadDefinition;
import autoimpresor.domain.CarnetLicencias;
import autoimpresor.service.CarnetLicenciasDefinition;
import frontend.utils.Util;
import frontend.ventanas.JInternalFrameTesterGral;

/**
 *
 * @author  __USER__
 */
public class PanelMargenesImpresion extends javax.swing.JPanel {

	private List<CarnetLicencias> lst;

	/** Creates new form PanelMargenesImpresion */
	public PanelMargenesImpresion(List<CarnetLicencias> lst) {
		this.lst = lst;
		initComponents();

		String margenIzqVal = ContextManager.getProperty("LIC_MARGEN_IZQ");
		String margenDerVal = ContextManager.getProperty("LIC_MARGEN_SUP");
		String desplazamientoTraseroVal = ContextManager
				.getProperty("LIC_DESPLAZAMIENTO_TRAS");

		txtDesplazamientoTrasero.setText(desplazamientoTraseroVal);
		txtMargenIzq.setText(margenIzqVal);
		txtMargenSup.setText(margenDerVal);

		btnGuardar.setVisible(false);
		btnExportarTodo.setVisible(true);
		cbPrinter.setVisible(false);
		jLabel2.setVisible(false);
	}

	public PanelMargenesImpresion() {

		initComponents();

		String margenIzqVal = ContextManager.getProperty("LIC_MARGEN_IZQ");
		String margenDerVal = ContextManager.getProperty("LIC_MARGEN_SUP");
		String desplazamientoTraseroVal = ContextManager
				.getProperty("LIC_DESPLAZAMIENTO_TRAS");

		txtDesplazamientoTrasero.setText(desplazamientoTraseroVal);
		txtMargenIzq.setText(margenIzqVal);
		txtMargenSup.setText(margenDerVal);

		btnGuardar.setVisible(true);
		btnExportarTodo.setVisible(false);
		cbPrinter.setVisible(true);
		jLabel2.setVisible(true);
		
		cargarImpresoras();
	}
	
	public void cargarImpresoras()
	{
		String printer=ContextManager.getProperty("DEFAULT_PRINTER");
		cbPrinter.removeAllItems();
		
		PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
		for(int i = 0; i < services.length;i++){
			cbPrinter.addItem(services[i].getName());
			if(services[i].getName().equals(printer)){
				cbPrinter.setSelectedIndex(i);
			}
		}
		
		if(services.length>0 && cbPrinter.getSelectedIndex()==-1)
			cbPrinter.setSelectedIndex(0);
	}

	public void guardarValores() {
		try {
			PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager
					.getBizObject("propiedadService");
			Propiedad pro = ContextManager.getPropertyObj("LIC_MARGEN_IZQ");
			pro.setPropValor(txtMargenIzq.getText());
			pro.setPropBlob(new byte[1]);
			propiedadService.update(pro);

			pro = ContextManager.getPropertyObj("LIC_MARGEN_SUP");
			pro.setPropValor(txtMargenSup.getText());
			pro.setPropBlob(new byte[1]);
			propiedadService.update(pro);

			pro = ContextManager.getPropertyObj("LIC_DESPLAZAMIENTO_TRAS");
			pro.setPropValor(txtDesplazamientoTrasero.getText());
			pro.setPropBlob(new byte[1]);
			propiedadService.update(pro);
			
			if(cbPrinter.getSelectedIndex()!=-1)
			{
				pro = ContextManager.getPropertyObj("DEFAULT_PRINTER");
				pro.setPropValor(cbPrinter.getSelectedItem().toString());
				pro.setPropBlob(new byte[1]);
				propiedadService.update(pro);
			}
			

			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		btnGuardar = new javax.swing.JButton();
		btnExportarTodo = new javax.swing.JButton();
		jLabel5 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		txtMargenIzq = new javax.swing.JFormattedTextField();
		txtDesplazamientoTrasero = new javax.swing.JFormattedTextField();
		txtMargenSup = new javax.swing.JFormattedTextField();
		cbPrinter = new javax.swing.JComboBox();
		jLabel2 = new javax.swing.JLabel();

		btnGuardar.setText("Guardar");
		btnGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGuardarActionPerformed(evt);
			}
		});

		btnExportarTodo.setText("Vista Previa");
		btnExportarTodo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExportarTodoActionPerformed(evt);
			}
		});

		jLabel5.setText("Desplazamiento Trasero (mm):");

		jLabel1.setText("Margen Superior (mm):");

		jLabel4.setText("Margen Izquierdo (mm):");

		txtMargenIzq
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0"))));
		txtMargenIzq.setText("1");
		txtMargenIzq.setMaximumSize(new java.awt.Dimension(60, 22));

		txtDesplazamientoTrasero
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0"))));
		txtDesplazamientoTrasero.setText("0");
		txtDesplazamientoTrasero.setMaximumSize(new java.awt.Dimension(60, 22));
		txtDesplazamientoTrasero
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						txtDesplazamientoTraseroActionPerformed(evt);
					}
				});

		txtMargenSup
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0"))));
		txtMargenSup.setText("2");
		txtMargenSup.setMaximumSize(new java.awt.Dimension(60, 22));

		cbPrinter.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));

		jLabel2.setText("Impresora:");

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
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel2)
																		.addGap(
																				26,
																				26,
																				26)
																		.addComponent(
																				cbPrinter,
																				0,
																				169,
																				Short.MAX_VALUE))
														.addComponent(
																btnGuardar,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																251,
																Short.MAX_VALUE)
														.addComponent(
																btnExportarTodo,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																251,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel1Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel5)
																						.addComponent(
																								jLabel1,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								181,
																								Short.MAX_VALUE)
																						.addComponent(
																								jLabel4,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								181,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								txtMargenIzq,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								65,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								txtDesplazamientoTrasero,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								65,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								txtMargenSup,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								65,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																cbPrinter,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																txtMargenSup,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(
																txtMargenIzq,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel5)
														.addComponent(
																txtDesplazamientoTrasero,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btnExportarTodo,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btnGuardar,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(37, 37, 37)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173,
				javax.swing.GroupLayout.PREFERRED_SIZE));
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
		guardarValores();
		
		try
		{
			autoimpresor.util.Util.compileReport();
		}
		catch(Exception e)
		{
			throw new RuntimeException();
		}
	}

	private void txtDesplazamientoTraseroActionPerformed(
			java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btnExportarTodoActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			guardarValores();
			Map param = new HashMap();
			/*String ids = "";
			for (int i = 0; i < lst.size(); i++) {
				ids += "" + lst.get(i).getCliId() + ",";
			}
			ids = ids.substring(0, ids.length() - 1);
			param.put("p_sql",
					"select *   from APP.CARNET_LICENCIAS  where cli_id in ("
							+ ids + ")");*/

			JasperReport report = autoimpresor.util.Util.compileReport();

			this.abrirVentanaReportes(Util.frameContenedor.getRootPane(),
					(HashMap) param, report, lst);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void abrirVentanaReportes(JRootPane root, HashMap parameterMap,
			JasperReport report, List lis) {
		try {
			final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral(
					"Reporte", false, true, false, false);

			if (lis == null)
				initReport(parameterMap, report, internalframe);
			else
				initReport(parameterMap, report, internalframe, lis);

			internalframe.doModal(root);
			internalframe.setVisible(true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void initReport(HashMap parameterMap, JasperReport report,
			final JInternalFrameTesterGral internalframe) {
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(report,
					parameterMap, ContextManager.getCurrentConnection());
			JRViewer viewer = new JRViewer(jasperPrint);
			((JPanel) viewer.getComponent(0)).remove(0);

			((JButton) ((JPanel) viewer.getComponent(0)).getComponent(0))
					.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							setImprimio(true);
						}
					});

			/*JRSaveContributor[] jRSaveContributor = viewer
					.getSaveContributors();
			JRSaveContributor[] jRSaveContributorDes = new JRSaveContributor[1];
			System.arraycopy(jRSaveContributor, 1, jRSaveContributorDes, 0, 1);
			viewer.setSaveContributors(jRSaveContributorDes);*/

			internalframe.add(viewer);
			internalframe.pack();
			internalframe.setSize(900, 650);

			Util.centrarIframes(internalframe);

			internalframe.addInternalFrameListener(new InternalFrameListener() {
				@Override
				public void internalFrameActivated(InternalFrameEvent e) {
				}

				@Override
				public void internalFrameClosed(InternalFrameEvent e) {
					internalframe.close();
				}

				@Override
				public void internalFrameClosing(InternalFrameEvent e) {
					internalframe.close();
				}

				@Override
				public void internalFrameDeactivated(InternalFrameEvent e) {
				}

				@Override
				public void internalFrameDeiconified(InternalFrameEvent e) {
				}

				@Override
				public void internalFrameIconified(InternalFrameEvent e) {
				}

				@Override
				public void internalFrameOpened(InternalFrameEvent e) {
				}
			});

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void initReport(HashMap parameterMap, JasperReport report,
			final JInternalFrameTesterGral internalframe, List list) {
		try {

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(list);

			JasperPrint jasperPrint = JasperFillManager.fillReport(report,
					parameterMap, ds);
			JRViewer viewer = new JRViewer(jasperPrint);
			((JPanel) viewer.getComponent(0)).remove(0);

			((JButton) ((JPanel) viewer.getComponent(0)).getComponent(0))
					.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							setImprimio(true);
						}
					});

			internalframe.add(viewer);
			internalframe.pack();
			internalframe.setSize(900, 650);

			Util.centrarIframes(internalframe);

			internalframe.addInternalFrameListener(new InternalFrameListener() {
				@Override
				public void internalFrameActivated(InternalFrameEvent e) {
				}

				@Override
				public void internalFrameClosed(InternalFrameEvent e) {
					internalframe.close();
				}

				@Override
				public void internalFrameClosing(InternalFrameEvent e) {
					internalframe.close();
				}

				@Override
				public void internalFrameDeactivated(InternalFrameEvent e) {
				}

				@Override
				public void internalFrameDeiconified(InternalFrameEvent e) {
				}

				@Override
				public void internalFrameIconified(InternalFrameEvent e) {
				}

				@Override
				public void internalFrameOpened(InternalFrameEvent e) {
				}
			});

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isImprimio() {
		return imprimio;
	}

	public void setImprimio(boolean imprimio) {
		this.imprimio = imprimio;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnExportarTodo;
	private javax.swing.JButton btnGuardar;
	private javax.swing.JComboBox cbPrinter;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JFormattedTextField txtDesplazamientoTrasero;
	private javax.swing.JFormattedTextField txtMargenIzq;
	private javax.swing.JFormattedTextField txtMargenSup;
	// End of variables declaration//GEN-END:variables
	CarnetLicenciasDefinition carnetLicenciasService = (CarnetLicenciasDefinition) ContextManager
			.getBizObject("carnetLicenciasService");
	private boolean imprimio = false;
}