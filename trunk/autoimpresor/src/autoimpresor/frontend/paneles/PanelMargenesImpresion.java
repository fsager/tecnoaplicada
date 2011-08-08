/*
 * PanelMargenesImpresion.java
 *
 * Created on __DATE__, __TIME__
 */

package autoimpresor.frontend.paneles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.JRViewer;
import testerGeneral.business.ContextManager;
import autoimpresor.domain.CarnetLicencias;
import autoimpresor.frontend.tablemodels.TableModelCarnet;
import autoimpresor.service.CarnetLicenciasDefinition;
import frontend.utils.Util;
import frontend.ventanas.JInternalFrameTesterGral;
import frontend.ventanas.VentanaReportes;

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
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		txtMargenSup = new javax.swing.JFormattedTextField();
		txtMargenIzq = new javax.swing.JFormattedTextField();
		jLabel4 = new javax.swing.JLabel();
		btnExportarTodo = new javax.swing.JButton();
		jLabel5 = new javax.swing.JLabel();
		txtDesplazamientoTrasero = new javax.swing.JFormattedTextField();

		jLabel1.setText("Margen Superior (mm):");

		txtMargenSup
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0"))));
		txtMargenSup.setText("2");
		txtMargenSup.setMaximumSize(new java.awt.Dimension(60, 22));

		txtMargenIzq
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0"))));
		txtMargenIzq.setText("1");
		txtMargenIzq.setMaximumSize(new java.awt.Dimension(60, 22));

		jLabel4.setText("Margen Izquierdo (mm):");

		btnExportarTodo.setText("Vista Previa");
		btnExportarTodo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExportarTodoActionPerformed(evt);
			}
		});

		jLabel5.setText("Desplazamiento Trasero (mm):");

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
																btnExportarTodo,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																215,
																Short.MAX_VALUE)
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
																												jLabel5)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED))
																						.addGroup(
																								layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING)
																										.addComponent(
																												jLabel1,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												163,
																												Short.MAX_VALUE)
																										.addComponent(
																												jLabel4,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												163,
																												Short.MAX_VALUE)))
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								txtMargenIzq,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								52,
																								Short.MAX_VALUE)
																						.addComponent(
																								txtDesplazamientoTrasero,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								52,
																								Short.MAX_VALUE)
																						.addComponent(
																								txtMargenSup,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								52,
																								Short.MAX_VALUE))))
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
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(
																txtMargenIzq,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel5)
														.addComponent(
																txtDesplazamientoTrasero,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												btnExportarTodo,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												20,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
	}// </editor-fold>
	//GEN-END:initComponents

	private void txtDesplazamientoTraseroActionPerformed(
			java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btnExportarTodoActionPerformed(java.awt.event.ActionEvent evt) {
		try {

			String srcString = "reportes/carnetsSrc.jrxml";
			File srcFile = new File(srcString);
			String srcReport = srcFile.getParent() + File.separator
					+ "carnets.jrxml";

			FileInputStream fis = new FileInputStream(srcFile);

			int cant = fis.available();
			byte byt[] = new byte[cant];
			fis.read(byt);
			fis.close();
			String template = new String(byt);

			Double pixel = 1.3;

			Integer margenIzq = (int) (Integer.valueOf(txtMargenIzq.getText()) * pixel);
			//Integer margenDer = (int) (Integer.valueOf(txtMargenDer.getText()) * pixel);
			Integer margenSup = (int) (Integer.valueOf(txtMargenSup.getText()) * pixel);
			//Integer margenInf = (int) (Integer.valueOf(txtMargenInf.getText()) * pixel);

			int with = 237 + margenIzq;
			int colWith = with - margenIzq;
			int height = 156 + margenSup;

			template = template.replaceAll("#!MARGEN_IZQ!#", "" + margenIzq);
			//template = template.replaceAll("#!MARGEN_DER!#", "" + margenDer);
			template = template.replaceAll("#!MARGEN_TOP!#", "" + margenSup);
			//template = template.replaceAll("#!MARGEN_BOTTON!#", "" + margenInf);
			template = template.replaceAll("#!COLUMN_WITH!#", "" + colWith);
			template = template.replaceAll("#!PAGE_WITH!#", "" + with);
			template = template.replaceAll("#!PAGE_HEIGHT!#", "" + height);

			int desplazamientoTrasero=(int)(pixel*Integer.valueOf(txtDesplazamientoTrasero.getText()));
			template = template.replaceAll("#!OBSERVACIONES_Y!#", (72+desplazamientoTrasero+""));
			template = template.replaceAll("#!RESTRICCIONES_Y!#", (40+desplazamientoTrasero+""));
			template = template.replaceAll("#!ALERGIAS_Y!#", (56+desplazamientoTrasero+""));
			template = template.replaceAll("#!GRUPO_SANGINEO_Y!#", (24+desplazamientoTrasero+""));
			template = template.replaceAll("#!TELEFONO_Y!#", (24+desplazamientoTrasero+""));
			template = template.replaceAll("#!DOMICILIO_Y!#", (8+desplazamientoTrasero+""));
			template = template.replaceAll("#!DONANTE_Y!#", (24+desplazamientoTrasero+""));
			template = template.replaceAll("#!MEDICACION_Y!#", (55+desplazamientoTrasero+""));
			template = template.replaceAll("#!CARGO_Y!#", (126+desplazamientoTrasero+""));
			template = template.replaceAll("#!NOMBRE_Y!#", (120+desplazamientoTrasero+""));
			template = template.replaceAll("#!FIRMA_Y!#", (92+desplazamientoTrasero+""));

			FileOutputStream fos = new FileOutputStream(srcReport);
			fos.write(template.getBytes());
			fos.close();

			Map param = new HashMap();
			String ids = "";
			for (int i = 0; i < lst.size(); i++) {
				ids += "" + lst.get(i).getCliId() + ",";
			}
			ids = ids.substring(0, ids.length() - 1);
			/*param.put("p_sql",
					"select *   from APP.CARNET_LICENCIAS  where cli_id in ("
							+ ids + ")");*/

			JasperReport report = JasperCompileManager.compileReport(srcReport);

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

			JRSaveContributor[] jRSaveContributor = viewer
					.getSaveContributors();
			JRSaveContributor[] jRSaveContributorDes = new JRSaveContributor[1];
			System.arraycopy(jRSaveContributor, 1, jRSaveContributorDes, 0, 1);
			viewer.setSaveContributors(jRSaveContributorDes);

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

			JRSaveContributor[] jRSaveContributor = viewer
					.getSaveContributors();
			JRSaveContributor[] jRSaveContributorDes = new JRSaveContributor[1];
			System.arraycopy(jRSaveContributor, 1, jRSaveContributorDes, 0, 1);
			viewer.setSaveContributors(jRSaveContributorDes);

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
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JFormattedTextField txtDesplazamientoTrasero;
	private javax.swing.JFormattedTextField txtMargenIzq;
	private javax.swing.JFormattedTextField txtMargenSup;
	// End of variables declaration//GEN-END:variables
	CarnetLicenciasDefinition carnetLicenciasService = (CarnetLicenciasDefinition) ContextManager
			.getBizObject("carnetLicenciasService");
	private boolean imprimio = false;
}