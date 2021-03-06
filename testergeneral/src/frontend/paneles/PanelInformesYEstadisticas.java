/*
 * PanelInformesYEstadisticas.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Examen;
import testerGeneral.domain.Persona;
import testerGeneral.domain.PersonaExamen;
import testerGeneral.domain.ResultadoDetalleExamen;
import testerGeneral.domain.reportes.ReporteGaussExamenes;
import testerGeneral.domain.reportes.ReportesExamenesUtil;
import testerGeneral.service.PersonaDefinition;
import testerGeneral.service.PersonaExamenDefinition;

/**
 * 
 * @author __USER__
 */
public class PanelInformesYEstadisticas extends javax.swing.JPanel {
	private static final Log log = LogFactory.getLog(PanelPersona.class);
	private PersonaDefinition personaService = (PersonaDefinition) ContextManager
			.getBizObject("personaService");
	private PersonaExamenDefinition personaExamenService = (PersonaExamenDefinition) ContextManager
			.getBizObject("personaExamenService");
	private List<ReporteGaussExamenes> reportesGaussExamenes = new ArrayList();;

	/** Creates new form PanelInformesYEstadisticas */
	public PanelInformesYEstadisticas() {
		initComponents();
		//Util.personaSinResultados(lbSinResultados, true);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroupSexo = new javax.swing.ButtonGroup();
		buttonGroupAlcohol = new javax.swing.ButtonGroup();
		buttonGroupPsicofarmacos = new javax.swing.ButtonGroup();
		buttonGroupEstudios = new javax.swing.ButtonGroup();
		buttonGroupTipoReporte = new javax.swing.ButtonGroup();
		jPanelFiltros = new javax.swing.JPanel();
		jTextFieldEdadInicio = new javax.swing.JTextField();
		jLabelEdad2 = new javax.swing.JLabel();
		jTextFieldEdadFin = new javax.swing.JTextField();
		jLabelEdad3 = new javax.swing.JLabel();
		jRadioButtonMasculino = new javax.swing.JRadioButton();
		jRadioButtonFemenino = new javax.swing.JRadioButton();
		jCheckBoxEdad = new javax.swing.JCheckBox();
		jCheckBoxSexo = new javax.swing.JCheckBox();
		jCheckBoxEstudios = new javax.swing.JCheckBox();
		jCheckBoxTomoHoyAlcohol = new javax.swing.JCheckBox();
		jRadioButtonTomoHoyAlcoholSI = new javax.swing.JRadioButton();
		jRadioButtonTomoHoyAlcoholNO = new javax.swing.JRadioButton();
		jCheckBoxTomoHoyPsicofarmacos = new javax.swing.JCheckBox();
		jRadioButtonTomoHoyPsicofarmacosSI = new javax.swing.JRadioButton();
		jRadioButtonTomoHoyPsicofarmacosNO = new javax.swing.JRadioButton();
		jRadioButtonEstudiosSI = new javax.swing.JRadioButton();
		jRadioButtonEstudiosNO = new javax.swing.JRadioButton();
		jButton1 = new javax.swing.JButton();
		jRadioResumida = new javax.swing.JRadioButton();
		jRadioDetallada = new javax.swing.JRadioButton();

		jPanelFiltros.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Filtros",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));

		jTextFieldEdadInicio
				.addFocusListener(new java.awt.event.FocusAdapter() {
					public void focusGained(java.awt.event.FocusEvent evt) {
						jTextFieldEdadInicioFocusGained(evt);
					}
				});

		jLabelEdad2.setText("y");

		jTextFieldEdadFin.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				jTextFieldEdadFinFocusGained(evt);
			}
		});

		jLabelEdad3.setText("a\u00f1os.");

		buttonGroupSexo.add(jRadioButtonMasculino);
		jRadioButtonMasculino.setText("Masculino");
		jRadioButtonMasculino
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioButtonMasculinoActionPerformed(evt);
					}
				});

		buttonGroupSexo.add(jRadioButtonFemenino);
		jRadioButtonFemenino.setText("Femenino");
		jRadioButtonFemenino
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioButtonFemeninoActionPerformed(evt);
					}
				});

		jCheckBoxEdad.setText("Edad: entre");
		jCheckBoxEdad.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBoxEdadActionPerformed(evt);
			}
		});

		jCheckBoxSexo.setText("Sexo:");
		jCheckBoxSexo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBoxSexoActionPerformed(evt);
			}
		});

		jCheckBoxEstudios.setText("Estudios:");
		jCheckBoxEstudios
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jCheckBoxEstudiosActionPerformed(evt);
					}
				});

		jCheckBoxTomoHoyAlcohol.setText("\u00bfTom\u00f3 hoy alcohol?");
		jCheckBoxTomoHoyAlcohol
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jCheckBoxTomoHoyAlcoholActionPerformed(evt);
					}
				});

		buttonGroupAlcohol.add(jRadioButtonTomoHoyAlcoholSI);
		jRadioButtonTomoHoyAlcoholSI.setText("Si");
		jRadioButtonTomoHoyAlcoholSI
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioButtonTomoHoyAlcoholSIActionPerformed(evt);
					}
				});

		buttonGroupAlcohol.add(jRadioButtonTomoHoyAlcoholNO);
		jRadioButtonTomoHoyAlcoholNO.setText("No");
		jRadioButtonTomoHoyAlcoholNO
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioButtonTomoHoyAlcoholNOActionPerformed(evt);
					}
				});

		jCheckBoxTomoHoyPsicofarmacos
				.setText("\u00bfTom\u00f3 hoy psicof\u00e1rmacos?");
		jCheckBoxTomoHoyPsicofarmacos
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jCheckBoxTomoHoyPsicofarmacosActionPerformed(evt);
					}
				});

		buttonGroupPsicofarmacos.add(jRadioButtonTomoHoyPsicofarmacosSI);
		jRadioButtonTomoHoyPsicofarmacosSI.setText("Si");
		jRadioButtonTomoHoyPsicofarmacosSI
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioButtonTomoHoyPsicofarmacosSIActionPerformed(evt);
					}
				});

		buttonGroupPsicofarmacos.add(jRadioButtonTomoHoyPsicofarmacosNO);
		jRadioButtonTomoHoyPsicofarmacosNO.setText("No");
		jRadioButtonTomoHoyPsicofarmacosNO
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioButtonTomoHoyPsicofarmacosNOActionPerformed(evt);
					}
				});

		buttonGroupEstudios.add(jRadioButtonEstudiosSI);
		jRadioButtonEstudiosSI.setText("Si");
		jRadioButtonEstudiosSI
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioButtonEstudiosSIActionPerformed(evt);
					}
				});

		buttonGroupEstudios.add(jRadioButtonEstudiosNO);
		jRadioButtonEstudiosNO.setText("No");
		jRadioButtonEstudiosNO
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioButtonEstudiosNOActionPerformed(evt);
					}
				});

		jButton1.setText("Generar Estad\u00edsticas");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		buttonGroupTipoReporte.add(jRadioResumida);
		jRadioResumida.setSelected(true);
		jRadioResumida.setText("Informaci\u00f3n Resumida");
		jRadioResumida.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioResumidaActionPerformed(evt);
			}
		});

		buttonGroupTipoReporte.add(jRadioDetallada);
		jRadioDetallada.setText("Informaci\u00f3n Detallada");
		jRadioDetallada.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioDetalladaActionPerformed(evt);
			}
		});

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
										.addGroup(
												jPanelFiltrosLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanelFiltrosLayout
																		.createSequentialGroup()
																		.addComponent(
																				jCheckBoxSexo)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				jRadioButtonMasculino)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jRadioButtonFemenino))
														.addGroup(
																jPanelFiltrosLayout
																		.createSequentialGroup()
																		.addComponent(
																				jCheckBoxEdad)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
														.addComponent(
																jCheckBoxTomoHoyAlcohol)
														.addGroup(
																jPanelFiltrosLayout
																		.createSequentialGroup()
																		.addGroup(
																				jPanelFiltrosLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jCheckBoxTomoHoyPsicofarmacos)
																						.addComponent(
																								jCheckBoxEstudios))
																		.addGap(
																				4,
																				4,
																				4)
																		.addGroup(
																				jPanelFiltrosLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanelFiltrosLayout
																										.createSequentialGroup()
																										.addComponent(
																												jRadioButtonTomoHoyPsicofarmacosSI)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jRadioButtonTomoHoyPsicofarmacosNO))
																						.addGroup(
																								jPanelFiltrosLayout
																										.createSequentialGroup()
																										.addComponent(
																												jRadioButtonTomoHoyAlcoholSI)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jRadioButtonTomoHoyAlcoholNO))
																						.addGroup(
																								jPanelFiltrosLayout
																										.createSequentialGroup()
																										.addComponent(
																												jRadioButtonEstudiosSI)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jRadioButtonEstudiosNO)))
																		.addGap(
																				42,
																				42,
																				42)
																		.addGroup(
																				jPanelFiltrosLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jRadioResumida)
																						.addComponent(
																								jRadioDetallada)
																						.addComponent(
																								jButton1))))
										.addContainerGap(18, Short.MAX_VALUE)));
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
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanelFiltrosLayout
																		.createSequentialGroup()
																		.addGroup(
																				jPanelFiltrosLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jCheckBoxEdad)
																						.addComponent(
																								jLabelEdad2)
																						.addComponent(
																								jTextFieldEdadFin,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabelEdad3)
																						.addComponent(
																								jTextFieldEdadInicio,
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
																								jCheckBoxSexo)
																						.addComponent(
																								jRadioButtonMasculino)
																						.addComponent(
																								jRadioButtonFemenino))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanelFiltrosLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jRadioButtonEstudiosSI)
																						.addComponent(
																								jRadioButtonEstudiosNO)
																						.addComponent(
																								jCheckBoxEstudios))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addGroup(
																				jPanelFiltrosLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jCheckBoxTomoHoyPsicofarmacos)
																						.addComponent(
																								jRadioButtonTomoHoyPsicofarmacosSI)
																						.addComponent(
																								jRadioButtonTomoHoyPsicofarmacosNO))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addGroup(
																				jPanelFiltrosLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jCheckBoxTomoHoyAlcohol)
																						.addComponent(
																								jRadioButtonTomoHoyAlcoholSI)
																						.addComponent(
																								jRadioButtonTomoHoyAlcoholNO)))
														.addGroup(
																jPanelFiltrosLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jRadioResumida)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jRadioDetallada)
																		.addGap(
																				18,
																				18,
																				18)
																		.addComponent(
																				jButton1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				41,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(22, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(
						jPanelFiltros, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(340, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addComponent(jPanelFiltros,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(230, Short.MAX_VALUE)));
	}// </editor-fold>
	//GEN-END:initComponents

	private void jRadioDetalladaActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void jRadioResumidaActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		generarReporte();
	}

	private void jRadioButtonEstudiosSIActionPerformed(
			java.awt.event.ActionEvent evt) {
		jCheckBoxEstudios.setSelected(true);
	}

	private void jRadioButtonEstudiosNOActionPerformed(
			java.awt.event.ActionEvent evt) {
		jCheckBoxEstudios.setSelected(true);
	}

	private void jCheckBoxTomoHoyAlcoholActionPerformed(
			java.awt.event.ActionEvent evt) {
		if (!jCheckBoxTomoHoyAlcohol.isSelected()) {
			buttonGroupAlcohol.clearSelection();
		} else {
			jRadioButtonTomoHoyAlcoholNO.setSelected(true);
		}
	}

	private void jCheckBoxTomoHoyPsicofarmacosActionPerformed(
			java.awt.event.ActionEvent evt) {
		if (!jCheckBoxTomoHoyPsicofarmacos.isSelected()) {
			buttonGroupPsicofarmacos.clearSelection();
		} else {
			jRadioButtonTomoHoyPsicofarmacosNO.setSelected(true);
		}
	}

	private void jRadioButtonTomoHoyAlcoholNOActionPerformed(
			java.awt.event.ActionEvent evt) {
		jCheckBoxTomoHoyAlcohol.setSelected(true);
	}

	private void jRadioButtonTomoHoyAlcoholSIActionPerformed(
			java.awt.event.ActionEvent evt) {
		jCheckBoxTomoHoyAlcohol.setSelected(true);
	}

	private void jRadioButtonTomoHoyPsicofarmacosNOActionPerformed(
			java.awt.event.ActionEvent evt) {
		jCheckBoxTomoHoyPsicofarmacos.setSelected(true);
	}

	private void jRadioButtonTomoHoyPsicofarmacosSIActionPerformed(
			java.awt.event.ActionEvent evt) {
		jCheckBoxTomoHoyPsicofarmacos.setSelected(true);
	}

	private void jCheckBoxEstudiosActionPerformed(java.awt.event.ActionEvent evt) {
		if (!jCheckBoxEstudios.isSelected()) {
			buttonGroupEstudios.clearSelection();
		} else {
			jRadioButtonEstudiosNO.setSelected(true);
		}
	}

	private void jCheckBoxSexoActionPerformed(java.awt.event.ActionEvent evt) {
		if (!jCheckBoxSexo.isSelected()) {
			buttonGroupSexo.clearSelection();
		} else {
			jRadioButtonMasculino.setSelected(true);
		}
	}

	private void jRadioButtonFemeninoActionPerformed(
			java.awt.event.ActionEvent evt) {
		jCheckBoxSexo.setSelected(true);
	}

	private void jRadioButtonMasculinoActionPerformed(
			java.awt.event.ActionEvent evt) {
		jCheckBoxSexo.setSelected(true);
	}

	private void jTextFieldEdadFinFocusGained(java.awt.event.FocusEvent evt) {
		jCheckBoxEdad.setSelected(true);
	}

	private void jCheckBoxEdadActionPerformed(java.awt.event.ActionEvent evt) {
		if (jCheckBoxEdad.isSelected()) {
			jTextFieldEdadInicio.setEnabled(true);
			jTextFieldEdadFin.setEnabled(true);
		} else {
			jTextFieldEdadInicio.setEnabled(false);
			jTextFieldEdadFin.setEnabled(false);
		}
	}

	private void jTextFieldEdadInicioFocusGained(java.awt.event.FocusEvent evt) {
		jCheckBoxEdad.setSelected(true);
	}

	public void generarReporte() {
		try {
			reportesGaussExamenes.clear();
			List<Persona> personas = cargarPersonas();
			Integer cantidadExamenes = 0;
			String p_pexa_id="";
			
			for (Persona persona : personas) {
				Examen examen = new Examen();
				examen.setExaId(new Long(3));

				PersonaExamen perExamenExamenple = new PersonaExamen();
				perExamenExamenple.setPersona(persona);
				perExamenExamenple.setExamen(examen);

				List<PersonaExamen> perExamenes = personaExamenService
						.getAll(perExamenExamenple);
				cantidadExamenes += perExamenes.size();

				if (jRadioResumida.isSelected()) {
					for (PersonaExamen perExamen : perExamenes) {
	
						Set<ResultadoDetalleExamen> resultadosDetalleExamen = perExamen
								.getResultadoDetalleExamens();
	
						for (ResultadoDetalleExamen resultadoDetalleExamen : resultadosDetalleExamen) {
							agregarResultadoDetalleExamen(resultadoDetalleExamen);
						}
					}
				}
				else
				{
					for (PersonaExamen perExamen : perExamenes) {
						p_pexa_id+=perExamen.getPexaId()+",";
					}
					
										
				}
			}

			if(p_pexa_id.equals(""))
				p_pexa_id="(-1)";
			else
			{
				p_pexa_id=p_pexa_id.substring(0,p_pexa_id.length()-1);
				p_pexa_id="("+p_pexa_id+")";
			}
			
			final byte[] buf;
			HashMap parameterMap = new HashMap();
			String extencion="pdf";
			if (jRadioResumida.isSelected()) {
				
				parameterMap.put("p_cantidad_examinados", personas.size() + "");
				parameterMap.put("p_cantidad_examenes", cantidadExamenes + "");
				parameterMap.put("SUBREPORT_DIR", new File("./reportes")
						.getCanonicalPath()
						+ File.separator);

				parameterMap.put("reportesGaussExamenes", reportesGaussExamenes);

				ArrayList dummy = new ArrayList(1);
				dummy.add(1);
				
				JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(
						dummy);
				buf = JasperRunManager.runReportToPdf(
						"reportes/estadisticaExamenes.jasper", parameterMap, ds);

			}
			else
			{
				parameterMap.put("p_cantidad_examinados", personas.size() + "");
				parameterMap.put("p_cantidad_examenes", cantidadExamenes + "");
				parameterMap.put("p_pexa_id", p_pexa_id);
				parameterMap.put("SUBREPORT_DIR", new File("./reportes").getCanonicalPath()+ File.separator);

				java.sql.Connection conn=ContextManager.getConnection();
				JasperPrint jasperPrint=JasperFillManager.fillReport("reportes/estadisticaExamenesDetallado.jasper", parameterMap,conn);
				ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
				
				JRExporter exporter =new JRXlsExporter();

				exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, byteArray);
				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
				exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
				exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
				exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
				exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
				extencion="xls";
				exporter.exportReport();
				buf=byteArray.toByteArray();
			}
			
			String file = System.getProperty("java.io.tmpdir")+ System.currentTimeMillis() + "."+extencion;
			testerGeneral.persistence.impl.Util.toFile(file, buf);

			Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void agregarResultadoDetalleExamen(
			ResultadoDetalleExamen resultadoDetalleExamen) {
		boolean categoriaEncontrado = false;
		boolean errorEncontrado = false;

		for (ReporteGaussExamenes reporteGaussExamene : reportesGaussExamenes) {
			String serie = resultadoDetalleExamen.getExamenDetalle()
					.getExadDetalle();
			String categoria = null;
			String categoriaError = null;

			if (resultadoDetalleExamen.getRdeNota() != null)
				categoria = ReportesExamenesUtil.getCategoria(
						resultadoDetalleExamen.getExamenDetalle()
								.getExadCodigo(), resultadoDetalleExamen
								.getRdeNota());
			else
				categoriaEncontrado = true;
			if (resultadoDetalleExamen.getRdeNota2() != null) {
				categoriaError = ""
						+ ReportesExamenesUtil
								.getCategoriaErrores(resultadoDetalleExamen
										.getRdeNota2());
			} else
				errorEncontrado = true;

			if (categoria != null
					&& !categoriaEncontrado
					&& reporteGaussExamene.getTipo().equals("C")
					&& reporteGaussExamene.getSerie().equalsIgnoreCase(serie)
					&& reporteGaussExamene.getCategoria().equalsIgnoreCase(
							categoria)) {
				reporteGaussExamene.addValue();
				categoriaEncontrado = true;

			}

			if (categoriaError != null
					&& !errorEncontrado
					&& reporteGaussExamene.getTipo().equals("E")
					&& reporteGaussExamene.getSerie().equalsIgnoreCase(serie)
					&& reporteGaussExamene.getCategoria().equalsIgnoreCase(
							categoriaError)) {
				reporteGaussExamene.addValue();
				errorEncontrado = true;

			}

			if (errorEncontrado && categoriaEncontrado)
				return;
		}

		ReporteGaussExamenes.addExamen(reportesGaussExamenes,
				resultadoDetalleExamen.getExamenDetalle());
		agregarResultadoDetalleExamen(resultadoDetalleExamen);
	}

	@SuppressWarnings("unchecked")
	public List<Persona> cargarPersonas() {
		log.info("cargarPersonas");

		boolean error = false;
		//setTableModel(new ArrayList());
		Calendar fechaInicio = Calendar.getInstance();
		Calendar fechaFin = Calendar.getInstance();

		try {
			//Util.personaSinResultados(lbSinResultados, true);
			Persona per = new Persona();

			/*
			 * Filtro de edad.
			 */
			if (jCheckBoxEdad.isSelected()) {

				if (!jTextFieldEdadInicio.getText().isEmpty()
						&& !jTextFieldEdadFin.getText().isEmpty()) {

					Calendar hoy = Calendar.getInstance();
					hoy.add(Calendar.YEAR, -Integer
							.valueOf(jTextFieldEdadInicio.getText()));
					fechaInicio = hoy;

					hoy = Calendar.getInstance();
					hoy.add(Calendar.YEAR, -Integer.valueOf(jTextFieldEdadFin
							.getText()) - 1);

					fechaFin = hoy;

					System.out.println(fechaInicio);
					System.out.println(fechaFin);

				} else {
					jCheckBoxEdad.setSelected(false);
				}
			}

			/*
			 * Filtro de estudios.
			 */
			if (jCheckBoxEstudios.isSelected()) {
				if (jRadioButtonEstudiosSI.isSelected()) {
					per.setPerEstudios("SI");
				}
				if (jRadioButtonEstudiosNO.isSelected()) {
					per.setPerEstudios("NO");
				}

			}

			/*
			 * Filtro de sexo.
			 */
			if (jCheckBoxSexo.isSelected()) {

				if (jRadioButtonMasculino.isSelected()) {
					per.setPerSexo("MASCULINO");
				}
				if (jRadioButtonFemenino.isSelected()) {
					per.setPerSexo("FEMENINO");
				}

			}

			/*
			 * Filtro de Tom� hoy alcohol.
			 */
			if (jCheckBoxTomoHoyAlcohol.isSelected()) {

				if (jRadioButtonTomoHoyAlcoholSI.isSelected()) {
					per.setPerTomoHoyAlcohol("1");
				}
				if (jRadioButtonTomoHoyAlcoholNO.isSelected()) {
					per.setPerTomoHoyAlcohol("2");
				}

			}

			/*
			 * Filtro de Tom� hoy psicof�rmacos.
			 */
			if (jCheckBoxTomoHoyPsicofarmacos.isSelected()) {

				if (jRadioButtonTomoHoyPsicofarmacosSI.isSelected()) {
					per.setPerTomoHoyPsicofarmacosSN("1");
				}
				if (jRadioButtonTomoHoyPsicofarmacosNO.isSelected()) {
					per.setPerTomoHoyPsicofarmacosSN("2");
				}

			}

			List<Persona> personas;
			if (jCheckBoxEdad.isSelected()) {
				personas = personaService.getAll(per, fechaFin.getTime(),
						fechaInicio.getTime());
				return personas;
			} else {
				personas = personaService.getAll(per);
				return personas;
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*public void setTableModel(List lst) {
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
	}*/

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.ButtonGroup buttonGroupAlcohol;
	private javax.swing.ButtonGroup buttonGroupEstudios;
	private javax.swing.ButtonGroup buttonGroupPsicofarmacos;
	private javax.swing.ButtonGroup buttonGroupSexo;
	private javax.swing.ButtonGroup buttonGroupTipoReporte;
	private javax.swing.JButton jButton1;
	private javax.swing.JCheckBox jCheckBoxEdad;
	private javax.swing.JCheckBox jCheckBoxEstudios;
	private javax.swing.JCheckBox jCheckBoxSexo;
	private javax.swing.JCheckBox jCheckBoxTomoHoyAlcohol;
	private javax.swing.JCheckBox jCheckBoxTomoHoyPsicofarmacos;
	private javax.swing.JLabel jLabelEdad2;
	private javax.swing.JLabel jLabelEdad3;
	private javax.swing.JPanel jPanelFiltros;
	private javax.swing.JRadioButton jRadioButtonEstudiosNO;
	private javax.swing.JRadioButton jRadioButtonEstudiosSI;
	private javax.swing.JRadioButton jRadioButtonFemenino;
	private javax.swing.JRadioButton jRadioButtonMasculino;
	private javax.swing.JRadioButton jRadioButtonTomoHoyAlcoholNO;
	private javax.swing.JRadioButton jRadioButtonTomoHoyAlcoholSI;
	private javax.swing.JRadioButton jRadioButtonTomoHoyPsicofarmacosNO;
	private javax.swing.JRadioButton jRadioButtonTomoHoyPsicofarmacosSI;
	private javax.swing.JRadioButton jRadioDetallada;
	private javax.swing.JRadioButton jRadioResumida;
	private javax.swing.JTextField jTextFieldEdadFin;
	private javax.swing.JTextField jTextFieldEdadInicio;
	// End of variables declaration//GEN-END:variables

}