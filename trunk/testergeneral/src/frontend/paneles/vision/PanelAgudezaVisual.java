/*
 * PanelAgudezaVisual.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.vision;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import org.apache.derby.iapi.tools.run;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.Examen;
import testerGeneral.domain.ExamenDetalle;
import testerGeneral.domain.PersonaExamen;
import testerGeneral.domain.Resultado;
import testerGeneral.domain.ResultadoDetalleExamen;
import testerGeneral.exceptions.ExceptionIsNotHadware;
import testerGeneral.service.ExamenDetalleDefinition;
import testerGeneral.service.PersonaExamenDefinition;
import testerGeneral.service.ResultadoDetalleExamenDefinition;
import testerGeneral.threads.ThreadTrama;
import examenes.psicometrico.domain.TramaVision;
import frontend.components.JOptionPaneTesterGral;
import frontend.paneles.examenes.Finalisable;
import frontend.paneles.examenes.PanelDetalleExamen;
import frontend.paneles.examenes.PanelExamen;
import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class PanelAgudezaVisual extends javax.swing.JPanel implements
		Finalisable, PanelExamen {

	private JToggleButton btn;
	private PersonaExamen personaExamen;
	//private ExamenDetalle exaDetalle;
	private List<Resultado> resultadosCercana = new ArrayList<Resultado>();
	private List<Resultado> resultadosLejana = new ArrayList<Resultado>();
	private static final int LINEA_PROFECIONAL = 6;
	private static final int LINEA_PARTICULAR = 5;
	//private ThreadTrama thTrama;
	private boolean luzDerechaPrendida = true;
	private boolean luzIzquierdaPrendida = true;
	private ExamenDetalleDefinition examenDetalleService = (ExamenDetalleDefinition) ContextManager
			.getBizObject("examenDetalleService");
	private ResultadoDetalleExamenDefinition resultadoDetalleExamenService = (ResultadoDetalleExamenDefinition) ContextManager
			.getBizObject("resultadoDetalleExamenService");
	private PersonaExamenDefinition personaExamenService = (PersonaExamenDefinition) ContextManager
			.getBizObject("personaExamenService");

	/** Creates new form Eeddd */
	public PanelAgudezaVisual(JToggleButton btn, PersonaExamen personaExamen) {
		System.out.println("PanelAgudezaVisual");
		
		this.btn = btn;
		this.personaExamen = personaExamen;
		initComponents();
		cargarImagenes();
		Util.mostrarError(lbError, null, true);

		if (Util.connectToHard)
			inicializarThreads();
	}

	public void inicializarThreads() {

		try {

			if (Util.thTrama != null
					&& !(Util.thTrama.getTrama() instanceof TramaVision))
				Util.thTrama.desconnect();

			if (Util.thTrama == null) {
				ThreadTrama thTrama = new ThreadTrama(new TramaVision());
				thTrama.setEjecutar(false);
				Util.thTrama = thTrama;
				thTrama.setEjecucion(99999);
				thTrama.start();
			}

			Util.thTrama.sendOrden(0x5000);
			Thread.sleep(20);
			new Thread() {
				@Override
				public void run() {
					try {
						Util.thTrama.sendOrden(ThreadTrama.ORDEN_IR_TEST1);
						Thread.sleep(20);

						habilitarTextField();

					} catch (Exception e) {
						//throw new RuntimeException(e);
					}
				}
			}.start();

		} catch (ExceptionIsNotHadware e) {
			JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public void cargarImagenes() {
		String binocular = ContextManager
				.getProperty("EXAMEN.AGUDEZA.VISUAL.IMG.BONOCULAR");
		String monoIzq = ContextManager
				.getProperty("EXAMEN.AGUDEZA.VISUAL.IMG.MONOCULAR.IZQ");
		String monoDer = ContextManager
				.getProperty("EXAMEN.AGUDEZA.VISUAL.IMG.MONOCULAR.DER");
		Util.setIcon(lbBinocular, binocular);
		Util.setIcon(lbMonoIzq, monoIzq);
		Util.setIcon(lbMonoDer, monoDer);
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		buttonGroup2 = new javax.swing.ButtonGroup();
		lbError = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		lbBinocular = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jFormattedLineaBinocular = new javax.swing.JFormattedTextField();
		jRadioBoxBinocular = new javax.swing.JRadioButton();
		jLabel7 = new javax.swing.JLabel();
		jFormattedLineaBinocularLe = new javax.swing.JFormattedTextField();
		btnGuardar = new javax.swing.JToggleButton();
		jPanel2 = new javax.swing.JPanel();
		lbMonoIzq = new javax.swing.JLabel();
		jFormattedMonoIzq = new javax.swing.JFormattedTextField();
		jRadioBoxMonoIzq = new javax.swing.JRadioButton();
		jLabel3 = new javax.swing.JLabel();
		jFormattedLineaMonocularIzqLe = new javax.swing.JFormattedTextField();
		jLabel8 = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel();
		lbMonoDer = new javax.swing.JLabel();
		jFormattedMonoDer = new javax.swing.JFormattedTextField();
		jRadioBoxMonoDer = new javax.swing.JRadioButton();
		jLabel4 = new javax.swing.JLabel();
		jFormattedLineaMonocularDerLe = new javax.swing.JFormattedTextField();
		jLabel9 = new javax.swing.JLabel();
		jRadioExamenCercana = new javax.swing.JRadioButton();
		jRadioExamenLejana = new javax.swing.JRadioButton();

		addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				formFocusGained(evt);
			}
		});

		lbError.setForeground(new java.awt.Color(204, 0, 0));
		lbError.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_ERROR)));
		lbError.setText(Constantes.ERROR_SIN_RESULTADOS);

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

		lbBinocular.setBackground(new java.awt.Color(51, 51, 255));
		lbBinocular
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\vision\\agudeza visual\\binocular.png")); // NOI18N

		jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 14));
		jLabel2.setForeground(new java.awt.Color(0, 0, 255));
		jLabel2.setText("L\u00ednea correcta visi\u00f3n cercana:");

		jFormattedLineaBinocular
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0"))));
		jFormattedLineaBinocular.setEnabled(false);
		jFormattedLineaBinocular
				.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

		buttonGroup2.add(jRadioBoxBinocular);
		jRadioBoxBinocular.setText("Visi\u00f3n binocular");
		jRadioBoxBinocular
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioBoxBinocularActionPerformed(evt);
					}
				});

		jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 14));
		jLabel7.setForeground(new java.awt.Color(0, 0, 255));
		jLabel7.setText("L\u00ednea correcta visi\u00f3n lejana:");

		jFormattedLineaBinocularLe
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0"))));
		jFormattedLineaBinocularLe.setEnabled(false);
		jFormattedLineaBinocularLe
				.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

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
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																lbBinocular)
														.addComponent(
																jRadioBoxBinocular)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGap(
																				28,
																				28,
																				28)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addGap(
																												10,
																												10,
																												10)
																										.addComponent(
																												jLabel7)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jFormattedLineaBinocularLe,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												31,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel2)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jFormattedLineaBinocular,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												31,
																												javax.swing.GroupLayout.PREFERRED_SIZE)))))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addComponent(jRadioBoxBinocular)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												lbBinocular,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												251,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel7)
														.addComponent(
																jFormattedLineaBinocularLe,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																jFormattedLineaBinocular,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(56, Short.MAX_VALUE)));

		btnGuardar.setFont(new java.awt.Font("Segoe UI", 3, 14));
		btnGuardar.setForeground(new java.awt.Color(0, 0, 255));
		btnGuardar.setText("Guardar Resultados");
		btnGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGuardarActionPerformed(evt);
			}
		});

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

		lbMonoIzq.setBackground(new java.awt.Color(153, 0, 0));
		lbMonoIzq
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\vision\\agudeza visual\\monocular_izq.png")); // NOI18N

		jFormattedMonoIzq
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0"))));
		jFormattedMonoIzq.setEnabled(false);
		jFormattedMonoIzq
				.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

		buttonGroup2.add(jRadioBoxMonoIzq);
		jRadioBoxMonoIzq.setSelected(true);
		jRadioBoxMonoIzq.setText("Visi\u00f3n monocular izquierdo");
		jRadioBoxMonoIzq.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioBoxMonoIzqActionPerformed(evt);
			}
		});

		jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 14));
		jLabel3.setForeground(new java.awt.Color(0, 0, 255));
		jLabel3.setText("L\u00ednea correcta visi\u00f3n cercana:");

		jFormattedLineaMonocularIzqLe
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0"))));
		jFormattedLineaMonocularIzqLe
				.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

		jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 14));
		jLabel8.setForeground(new java.awt.Color(0, 0, 255));
		jLabel8.setText("L\u00ednea correcta visi\u00f3n lejana:");

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
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lbMonoIzq))
														.addComponent(
																jRadioBoxMonoIzq)
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
																												jLabel3)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jFormattedMonoIzq,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												31,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								jPanel2Layout
																										.createSequentialGroup()
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																												10,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												jLabel8)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jFormattedLineaMonocularIzqLe,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												31,
																												javax.swing.GroupLayout.PREFERRED_SIZE)))))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addComponent(jRadioBoxMonoIzq)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(lbMonoIzq)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jFormattedLineaMonocularIzqLe,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel8))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jFormattedMonoIzq,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel3))
										.addGap(58, 58, 58)));

		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

		lbMonoDer.setBackground(new java.awt.Color(0, 153, 153));
		lbMonoDer
				.setIcon(new javax.swing.ImageIcon(
						"C:\\programacion\\Workspaces3\\TesterGeneral\\images\\images\\vision\\agudeza visual\\monocular_der.png")); // NOI18N

		jFormattedMonoDer
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0"))));
		jFormattedMonoDer.setEnabled(false);
		jFormattedMonoDer
				.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

		buttonGroup2.add(jRadioBoxMonoDer);
		jRadioBoxMonoDer.setText("Visi\u00f3n monocular derecho");
		jRadioBoxMonoDer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioBoxMonoDerActionPerformed(evt);
			}
		});

		jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 14));
		jLabel4.setForeground(new java.awt.Color(0, 0, 255));
		jLabel4.setText("L\u00ednea correcta visi\u00f3n cercana:");

		jFormattedLineaMonocularDerLe
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0"))));
		jFormattedLineaMonocularDerLe.setEnabled(false);
		jFormattedLineaMonocularDerLe
				.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

		jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 14));
		jLabel9.setForeground(new java.awt.Color(0, 0, 255));
		jLabel9.setText("L\u00ednea correcta visi\u00f3n lejana:");

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
																jRadioBoxMonoDer)
														.addComponent(lbMonoDer)
														.addGroup(
																jPanel3Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(
																				jPanel3Layout
																						.createSequentialGroup()
																						.addComponent(
																								jLabel4)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								jFormattedMonoDer,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								31,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				jPanel3Layout
																						.createSequentialGroup()
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																								10,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel9)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								jFormattedLineaMonocularDerLe,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								31,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addComponent(jRadioBoxMonoDer)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(lbMonoDer)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jFormattedLineaMonocularDerLe,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel9))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jFormattedMonoDer,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel4))
										.addContainerGap(61, Short.MAX_VALUE)));

		buttonGroup1.add(jRadioExamenCercana);
		jRadioExamenCercana.setFont(new java.awt.Font("Segoe UI", 3, 14));
		jRadioExamenCercana.setText("Examen de visi\u00f3n cercana");
		jRadioExamenCercana
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioExamenCercanaActionPerformed(evt);
					}
				});

		buttonGroup1.add(jRadioExamenLejana);
		jRadioExamenLejana.setFont(new java.awt.Font("Segoe UI", 3, 14));
		jRadioExamenLejana.setSelected(true);
		jRadioExamenLejana.setText("Examen de visi\u00f3n lejana");
		jRadioExamenLejana
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jRadioExamenLejanaActionPerformed(evt);
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
																												jRadioExamenLejana)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jRadioExamenCercana,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												231,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING)
																										.addComponent(
																												btnGuardar)
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
																																javax.swing.GroupLayout.PREFERRED_SIZE)))))
														.addComponent(
																lbError,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																743,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jRadioExamenLejana)
														.addComponent(
																jRadioExamenCercana))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jPanel1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanel3,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanel2,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																406,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																btnGuardar)
														.addComponent(
																lbError,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																24,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
	}// </editor-fold>
	//GEN-END:initComponents

	private void jRadioBoxMonoDerActionPerformed(java.awt.event.ActionEvent evt) {
		habilitarTextField();
	}

	private void jRadioBoxMonoIzqActionPerformed(java.awt.event.ActionEvent evt) {
		habilitarTextField();
	}

	private void jRadioExamenLejanaActionPerformed(
			java.awt.event.ActionEvent evt) {
		habilitarTextField();
	}

	private void formFocusGained(java.awt.event.FocusEvent evt) {
		jFormattedLineaBinocularLe.requestFocus();
	}

	private void jRadioExamenCercanaActionPerformed(
			java.awt.event.ActionEvent evt) {
		habilitarTextField();
	}

	public void habilitarTextField() {
		boolean lejano = true;
		boolean binocular = true;
		boolean monoDer = true;
		boolean monoIzq = true;

		if (jRadioExamenCercana.isSelected())
			lejano = false;
		else if (jRadioExamenLejana.isSelected())

			lejano = true;

		if (jRadioBoxBinocular.isSelected()) {
			if (!luzDerechaPrendida) {
				if (Util.connectToHard)
					Util.thTrama
							.sendOrden(ThreadTrama.ORDEN_CAMBIA_ESTADO_LUZ_DER);

				luzDerechaPrendida = true;
			}

			if (!luzIzquierdaPrendida) {
				if (Util.connectToHard)
					Util.thTrama
							.sendOrden(ThreadTrama.ORDEN_CAMBIA_ESTADO_LUZ_IZQ);

				luzIzquierdaPrendida = true;
			}

			binocular = true;
			monoDer = false;
			monoIzq = false;

			if (!lejano)
				jFormattedLineaBinocular.requestFocus();
			else
				jFormattedLineaBinocularLe.requestFocus();

		} else if (jRadioBoxMonoDer.isSelected()) {
			if (!luzDerechaPrendida) {
				if (Util.connectToHard)
					Util.thTrama
							.sendOrden(ThreadTrama.ORDEN_CAMBIA_ESTADO_LUZ_DER);

				luzDerechaPrendida = true;
			}

			if (luzIzquierdaPrendida) {
				if (Util.connectToHard)
					Util.thTrama
							.sendOrden(ThreadTrama.ORDEN_CAMBIA_ESTADO_LUZ_IZQ);

				luzIzquierdaPrendida = false;
			}

			binocular = false;
			monoDer = true;
			monoIzq = false;

			if (!lejano)
				jFormattedMonoDer.requestFocus();
			else
				jFormattedLineaMonocularDerLe.requestFocus();

		} else if (jRadioBoxMonoIzq.isSelected()) {
			if (luzDerechaPrendida) {
				if (Util.connectToHard)
					Util.thTrama
							.sendOrden(ThreadTrama.ORDEN_CAMBIA_ESTADO_LUZ_DER);

				luzDerechaPrendida = false;
			}

			if (!luzIzquierdaPrendida) {
				if (Util.connectToHard)
					Util.thTrama
							.sendOrden(ThreadTrama.ORDEN_CAMBIA_ESTADO_LUZ_IZQ);

				luzIzquierdaPrendida = true;
			}

			binocular = false;
			monoDer = false;
			monoIzq = true;

			if (!lejano)
				jFormattedMonoIzq.requestFocus();
			else
				jFormattedLineaMonocularIzqLe.requestFocus();
		}

		jFormattedLineaBinocular.setEnabled(!lejano && binocular);
		jFormattedMonoDer.setEnabled(!lejano && monoDer);
		jFormattedMonoIzq.setEnabled(!lejano && monoIzq);

		jFormattedLineaBinocularLe.setEnabled(lejano && binocular);
		jFormattedLineaMonocularDerLe.setEnabled(lejano && monoDer);
		jFormattedLineaMonocularIzqLe.setEnabled(lejano && monoIzq);

	}

	private void jRadioBoxBinocularActionPerformed(
			java.awt.event.ActionEvent evt) {
		habilitarTextField();
	}

	private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
		btnGuardar.setSelected(false);

		try {

			if (personaExamen != null && personaExamen.getPexaId() == null) {
				personaExamen.setPexaEstado("INICIADO");
				personaExamen.setPexaFecha(new Date());
				personaExamenService.insert(personaExamen);
			}

			if (isExamenValid()) {
				cargarResultados();

				if (!resultadosCercana.isEmpty()) {
					ExamenDetalle exaDetalle = new ExamenDetalle();
					exaDetalle
							.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_AGUDEZA_VISUAL_CERCANA);
					exaDetalle = (ExamenDetalle) examenDetalleService.getAll(
							exaDetalle).get(0);
					String resultado = getResultadoCercana();
					grabarExamen(exaDetalle, resultadosCercana, resultado);
				}

				if (!resultadosLejana.isEmpty()) {
					ExamenDetalle exaDetalle = new ExamenDetalle();
					exaDetalle
							.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_AGUDEZA_VISUAL_LEJANA);
					exaDetalle = (ExamenDetalle) examenDetalleService.getAll(
							exaDetalle).get(0);
					String resultado = getResultadoLejana();
					grabarExamen(exaDetalle, resultadosLejana, resultado);
				}

				btn.setForeground(Color.BLACK);
				Util.setIcon(btn, Constantes.IMG_ACEPTAR_SMALL);

				((PanelDetalleExamen) btn.getParent()).nextExamen(btn);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void grabarExamen(ExamenDetalle exaDetalle,
			List<Resultado> resultados, String resultado) {
		try {
			ResultadoDetalleExamen resultadoDetalleExamen = new ResultadoDetalleExamen();
			resultadoDetalleExamen.setExamenDetalle(exaDetalle);
			resultadoDetalleExamen.setPersonaExamen(personaExamen);
			
			List lstResultados = resultadoDetalleExamenService
					.getAll(resultadoDetalleExamen);

			if (lstResultados.size() < 1) {
				resultadoDetalleExamenService.insert(resultadoDetalleExamen);
			} else if (lstResultados.size() == 1) {
				resultadoDetalleExamen = (ResultadoDetalleExamen) lstResultados
						.get(0);
			}

			String detalleResultado = "<HTML>";
			Set setResultados = resultadoDetalleExamen.getResultados();
			setResultados.clear();
			for (int i = 0; i < resultados.size(); i++) {
				resultados.get(i).setResultadoDetalleExamen(
						resultadoDetalleExamen);
				setResultados.add(resultados.get(i));
				detalleResultado = detalleResultado
						+ resultados.get(i).getResEtapaDesc() + ": "
						+ resultados.get(i).getResValor1() + ".<BR>";
			}

			resultadoDetalleExamen.setRdeResultado(resultado);
			resultadoDetalleExamen.setRdeDetalleResultado(detalleResultado
					+ "</HTML>");
			resultadoDetalleExamen.setRdeParametrosCorrecion(exaDetalle
					.getExadParametrosCorrecion());
			resultadoDetalleExamenService.update(resultadoDetalleExamen);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isExamenValid() {
		Util.mostrarError(lbError, null, true);

		if (!hizoAlgunExamen()) {
			Util.mostrarError(lbError, "Debe realizar alguno de los examenes.",
					false);
			return false;
		}

		if (!jFormattedLineaBinocular.getText().isEmpty()
				&& !isLineaValueValid(jFormattedLineaBinocular)) {
			Util
					.mostrarError(
							lbError,
							"Debe ingresar la línea correcta para binocular cercana. Valores entre 1 y 8.",
							false);
			return false;
		}

		if (!jFormattedMonoIzq.getText().isEmpty()
				&& !isLineaValueValid(jFormattedMonoIzq)) {
			Util
					.mostrarError(
							lbError,
							"Debe ingresar la línea correcta para monocular izquierda cercana. Valores entre 1 y 8.",
							false);
			return false;
		}

		if (!jFormattedMonoDer.getText().isEmpty()
				&& !isLineaValueValid(jFormattedMonoDer)) {
			Util
					.mostrarError(
							lbError,
							"Debe ingresar la línea correcta para monocular derecha cercana. Valores entre 1 y 8.",
							false);
			return false;
		}

		if (!jFormattedLineaBinocularLe.getText().isEmpty()
				&& !isLineaValueValid(jFormattedLineaBinocularLe)) {
			Util
					.mostrarError(
							lbError,
							"Debe ingresar la línea correcta para binocular lejana. Valores entre 1 y 8.",
							false);
			return false;
		}

		if (!jFormattedLineaMonocularIzqLe.getText().isEmpty()
				&& !isLineaValueValid(jFormattedLineaMonocularIzqLe)) {
			Util
					.mostrarError(
							lbError,
							"Debe ingresar la línea correcta para monocular izquierda lejana. Valores entre 1 y 8.",
							false);
			return false;
		}

		if (!jFormattedLineaMonocularDerLe.getText().isEmpty()
				&& !isLineaValueValid(jFormattedLineaMonocularDerLe)) {
			Util
					.mostrarError(
							lbError,
							"Debe ingresar la línea correcta para monocular derecha lejana. Valores entre 1 y 8.",
							false);
			return false;
		}

		return true;
	}

	public boolean hizoAlgunExamen() {
		if (!jFormattedLineaBinocular.getText().isEmpty())
			return true;

		if (!jFormattedMonoDer.getText().isEmpty())
			return true;

		if (!jFormattedMonoIzq.getText().isEmpty())
			return true;

		if (!jFormattedLineaBinocularLe.getText().isEmpty())
			return true;

		if (!jFormattedLineaMonocularDerLe.getText().isEmpty())
			return true;

		if (!jFormattedLineaMonocularIzqLe.getText().isEmpty())
			return true;

		return false;
	}

	public boolean isLineaValueValid(JFormattedTextField jFormattedLinea) {
		try {
			int value = Integer.valueOf(jFormattedLinea.getText());
			if (value == 0 || value > 8)
				return false;
		} catch (NumberFormatException ex) {
			return false;
		}

		return true;
	}

	public void cargarResultados() {
		resultadosLejana.clear();
		resultadosCercana.clear();
		Resultado res = new Resultado();

		if (!jFormattedLineaBinocular.getText().isEmpty()) {
			res = new Resultado();
			res.setResEtapa(0l);
			res.setResEtapaDesc("Visión binocular");
			res
					.setResValor1(getValorClinico(jFormattedLineaBinocular
							.getText()));
			resultadosCercana.add(res);
		}

		if (!jFormattedMonoIzq.getText().isEmpty()) {
			res = new Resultado();
			res.setResEtapa(2l);
			res.setResEtapaDesc("Visión monocular izquierdo");
			res.setResValor1(getValorClinico(jFormattedMonoIzq.getText()));
			resultadosCercana.add(res);
		}

		if (!jFormattedMonoDer.getText().isEmpty()) {
			res = new Resultado();
			res.setResEtapa(1l);
			res.setResEtapaDesc("Visión monocular derecho");
			res.setResValor1(getValorClinico(jFormattedMonoDer.getText()));
			resultadosCercana.add(res);
		}

		if (!jFormattedLineaBinocularLe.getText().isEmpty()) {
			res = new Resultado();
			res.setResEtapa(3l);
			res.setResEtapaDesc("Visión binocular");
			res.setResValor1(getValorClinico(jFormattedLineaBinocularLe
					.getText()));
			resultadosLejana.add(res);
		}

		if (!jFormattedLineaMonocularIzqLe.getText().isEmpty()) {
			res = new Resultado();
			res.setResEtapa(5l);
			res.setResEtapaDesc("Visión monocular izquierdo");
			res.setResValor1(getValorClinico(jFormattedLineaMonocularIzqLe
					.getText()));
			resultadosLejana.add(res);
		}

		if (!jFormattedLineaMonocularDerLe.getText().isEmpty()) {
			res = new Resultado();
			res.setResEtapa(4l);
			res.setResEtapaDesc("Visión monocular derecho");
			res.setResValor1(getValorClinico(jFormattedLineaMonocularDerLe
					.getText()));
			resultadosLejana.add(res);
		}
	}

	public Double getValorClinico(String valorLinea) {
		Integer valorClinico = Integer.valueOf(valorLinea);
		/*Donde tenemos línea 6 debe decir 0.8 
		Donde tenemos línea 5 debe decir 0.7 
		Donde tenemos línea 4 debe decir 0.5 
		Donde tenemos línea 3 debe decir 0.4 
		Donde tenemos línea 2 debe decir 0.3 
		Donde tenemos línea 1 debe decir 0.1*/

		switch (valorClinico) {
		case 1:
			return 0.1;
		case 2:
			return 0.2;
		case 3:
			return 0.3;
		case 4:
			return 0.4;
		case 5:
			return 0.5;
		case 6:
			return 0.7;
		case 7:
			return 0.8;
		default:
			return 1.0;
		}
	}

	public String getResultadoCercana() {

		if (!jFormattedLineaBinocular.getText().isEmpty()) {
			int lineaBinocular = Integer.valueOf(jFormattedLineaBinocular
					.getText());
			if (!isAprobed(lineaBinocular))
				return Examen.RESULTADO_FUERA_DERIVACION;
		}

		if (!jFormattedMonoIzq.getText().isEmpty()) {
			int lineaBinocular = Integer.valueOf(jFormattedMonoIzq.getText());
			if (!isAprobed(lineaBinocular))
				return Examen.RESULTADO_FUERA_DERIVACION;
		}

		if (!jFormattedMonoDer.getText().isEmpty()) {
			int lineaBinocular = Integer.valueOf(jFormattedMonoDer.getText());
			if (!isAprobed(lineaBinocular))
				return Examen.RESULTADO_FUERA_DERIVACION;
		}

		return Examen.RESULTADO_DENTRO;
	}

	public String getResultadoLejana() {

		if (!jFormattedLineaBinocularLe.getText().isEmpty()) {
			int lineaBinocular = Integer.valueOf(jFormattedLineaBinocularLe
					.getText());
			if (!isAprobed(lineaBinocular))
				return Examen.RESULTADO_FUERA_DERIVACION;
		}

		if (!jFormattedLineaMonocularIzqLe.getText().isEmpty()) {
			int lineaBinocular = Integer.valueOf(jFormattedLineaMonocularIzqLe
					.getText());
			if (!isAprobed(lineaBinocular))
				return Examen.RESULTADO_FUERA_DERIVACION;
		}

		if (!jFormattedLineaMonocularDerLe.getText().isEmpty()) {
			int lineaBinocular = Integer.valueOf(jFormattedLineaMonocularDerLe
					.getText());
			if (!isAprobed(lineaBinocular))
				return Examen.RESULTADO_FUERA_DERIVACION;
		}

		return Examen.RESULTADO_DENTRO;
	}

	public boolean isAprobed(int valor) {
		if (this.personaExamen.getPexaTipoExamen().equals(
				PersonaExamen.TIPO_EXAMEN_PROFECIONAL)
				&& valor < LINEA_PROFECIONAL)
			return false;

		if (this.personaExamen.getPexaTipoExamen().equals(
				PersonaExamen.TIPO_EXAMEN_PARTICULAR)
				&& valor < LINEA_PARTICULAR)
			return false;

		return true;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JToggleButton btnGuardar;
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.ButtonGroup buttonGroup2;
	private javax.swing.JFormattedTextField jFormattedLineaBinocular;
	private javax.swing.JFormattedTextField jFormattedLineaBinocularLe;
	private javax.swing.JFormattedTextField jFormattedLineaMonocularDerLe;
	private javax.swing.JFormattedTextField jFormattedLineaMonocularIzqLe;
	private javax.swing.JFormattedTextField jFormattedMonoDer;
	private javax.swing.JFormattedTextField jFormattedMonoIzq;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JRadioButton jRadioBoxBinocular;
	private javax.swing.JRadioButton jRadioBoxMonoDer;
	private javax.swing.JRadioButton jRadioBoxMonoIzq;
	private javax.swing.JRadioButton jRadioExamenCercana;
	private javax.swing.JRadioButton jRadioExamenLejana;
	private javax.swing.JLabel lbBinocular;
	private javax.swing.JLabel lbError;
	private javax.swing.JLabel lbMonoDer;
	private javax.swing.JLabel lbMonoIzq;

	// End of variables declaration//GEN-END:variables

	@Override
	public void finalizar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBtn(JToggleButton btn) {
		this.btn = btn;
	}

}