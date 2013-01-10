/*
 * PanelAudio.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.audio;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

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
import examenes.psicometrico.domain.TramaAudio;
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
public class PanelAudio extends javax.swing.JPanel implements Finalisable,
		PanelExamen {

	private JToggleButton btn;
	private PersonaExamen personaExamen;
	//private ThreadTrama thTrama;
	private ExamenDetalleDefinition examenDetalleService = (ExamenDetalleDefinition) ContextManager
			.getBizObject("examenDetalleService");
	private ResultadoDetalleExamenDefinition resultadoDetalleExamenService = (ResultadoDetalleExamenDefinition) ContextManager
			.getBizObject("resultadoDetalleExamenService");
	private PersonaExamenDefinition personaExamenService = (PersonaExamenDefinition) ContextManager
			.getBizObject("personaExamenService");
	private String operacionLargaMensaje = "Aguarde la respuesta del paciente";
	private ExamenDetalle exaDetalle;

	/** Creates new form Eeddd */
	public PanelAudio(JToggleButton btn, PersonaExamen personaExamen) {
		this.btn = btn;
		this.personaExamen = personaExamen;
		initComponents();

		try {
			ExamenDetalleDefinition examenDetalleService = (ExamenDetalleDefinition) ContextManager
					.getBizObject("examenDetalleService");
			exaDetalle = new ExamenDetalle();
			exaDetalle.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_AUDIO);
			exaDetalle = (ExamenDetalle) examenDetalleService
					.getAll(exaDetalle).get(0);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		inicializarResultados();
		btnCancelar = new javax.swing.JButton();
		btnCancelar.setFont(new java.awt.Font("Segoe UI", 3, 14));
		btnCancelar.setText("Cancelar");
		btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarActionPerformed2(evt);
			}
		});

		Util.mostrarError(lbError, null, true);

		inicializarThreads();
		agragrGrafico();
		agregarAlGrafico();
	}

	public void agregarAlGrafico() {
		dataset.clear();
		for (int i = 0; i < resultados.size(); i++) {
			Resultado resultado = resultados.get(i);
			if (resultado.getResValor1() != null) {
				dataset.addValue(resultado.getResValor1().intValue(),
						seriesSubida, "" + resultado.getResEtapa().intValue());
				dataset.addValue(resultado.getResValor2().intValue(),
						seriesBajada, "" + resultado.getResEtapa().intValue());
			} else {
				dataset.addValue(85, seriesSubida, ""
						+ resultado.getResEtapa().intValue());
				dataset.addValue(85, seriesBajada, ""
						+ resultado.getResEtapa().intValue());
			}

		}
		chart.fireChartChanged();
	}

	public void agragrGrafico() {
		dataset = new DefaultCategoryDataset();
		chart = ChartFactory.createLineChart(null, // chart title
				"Hz", // x axis label
				"Db", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
				);
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		LineAndShapeRenderer renderer = new LineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, true);
		renderer.setSeriesShapesVisible(0, true);
		renderer.setSeriesLinesVisible(1, true);
		renderer.setSeriesShapesVisible(1, true);
		plot.setRenderer(renderer);

		final ChartPanel chartPanel = new ChartPanel(chart);
		panelGrafico.add(chartPanel);
	}

	public void agregarResultado(int etapa, int subida, int bajada) {
		System.out.println("agregarResultado: " + etapa + " subida: " + subida);
		for (Resultado res : resultados) {
			if (res.getResEtapa().intValue() == etapa) {
				res.setResValor1(new Double(subida));
				res.setResValor2(new Double(bajada));
				double promedio = (new Double(subida) + new Double(bajada)) / 2d;
				promedio = Util.redondear(promedio);
				res.setResEtapaDesc(etapa + "Hz: " + promedio + " Db");
				agregarAlGrafico();
				return;
			}
		}
	}

	public void inicializarResultados() {
		Resultado res = new Resultado();
		res.setResEtapa(250l);
		resultados.add(res);

		res = new Resultado();
		res.setResEtapa(500l);
		resultados.add(res);

		res = new Resultado();
		res.setResEtapa(1000l);
		resultados.add(res);

		res = new Resultado();
		res.setResEtapa(2000l);
		resultados.add(res);

		res = new Resultado();
		res.setResEtapa(3000l);
		resultados.add(res);

		res = new Resultado();
		res.setResEtapa(4000l);
		resultados.add(res);

		res = new Resultado();
		res.setResEtapa(6000l);
		resultados.add(res);

		res = new Resultado();
		res.setResEtapa(8000l);
		resultados.add(res);

	}

	public void inicializarThreads() {

		try {
			
			if (Util.thTrama != null && !(Util.thTrama.getTrama() instanceof TramaAudio))
				Util.thTrama.desconnect();
			
			if (Util.thTrama == null)
			{
				ThreadTrama thTrama = new ThreadTrama(new TramaAudio());
				thTrama.setEjecutar(false);
				Util.thTrama = thTrama;
				thTrama.setEjecucion(99999);
				thTrama.start();
			}
			


			/*thTrama.sendOrden(ThreadTrama.ORDEN_START_AUTOMATICO_STEREO);
			Thread.sleep(50);
			thTrama.sendOrden(ThreadTrama.ORDEN_STOP_AUTOMATICO);*/

		} catch (ExceptionIsNotHadware e) {
			JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public void escuchar(int frecuencia, final JProgressBar progresSubida,
			final JProgressBar progresBajada) {
		long sleep = 50;
		final TramaAudio tAudio = (TramaAudio) Util.thTrama.getTramaValida();
		//System.out.println("tAudio.isTestNotRunning()1: "+tAudio.isTestNotRunning());

		try {

			//Hasta que termine el examen
			while (!tAudio.isTestNotRunning()) {
				//System.out.println("tAudio.isTestNotRunning()3: "+tAudio.isTestNotRunning());
				Thread.sleep(sleep);
			}

			Thread.sleep(sleep);
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					if (!cancel) {
						progresBajada.setValue(tAudio.getDbBajanda());
						progresBajada.setString(tAudio.getDbBajanda() + " Db");

						progresSubida.setValue(tAudio.getDbSubida());
						progresSubida.setString(tAudio.getDbSubida() + " Db");
					} else {
						progresBajada.setValue(80);
						progresBajada.setString("80 Db");

						progresSubida.setValue(80);
						progresSubida.setString("80 Db");
					}
				}
			});

			if (!cancel)
				agregarResultado(getRealFrecuencia(frecuencia), tAudio
						.getDbSubida(), tAudio.getDbBajanda());

		} catch (InterruptedException e) {

		}
	}

	public void escucharTodasLasFreccuencias() {
		long sleep = 50;
		final TramaAudio tAudio = (TramaAudio) Util.thTrama.getTramaValida();

		try {

			for (int i = 1; i <= 8 && !cancel; i++) {
				final int frecuencia = i;
				sendOrdes(frecuencia);
				Thread.sleep(100);
				//Hasta que termine el examen
				while (!tAudio.isTestNotRunning()) {
					Thread.sleep(sleep);
				}

				Thread.sleep(sleep);

				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						if (!cancel) {
							getProgressBarBajadaPorFrecuencia(frecuencia)
									.setValue(tAudio.getDbBajanda());
							getProgressBarBajadaPorFrecuencia(frecuencia)
									.setString(tAudio.getDbBajanda() + " Db");

							getProgressBarSubidaPorFrecuencia(frecuencia)
									.setValue(tAudio.getDbSubida());
							getProgressBarSubidaPorFrecuencia(frecuencia)
									.setString(tAudio.getDbSubida() + " Db");
						} else {
							getProgressBarBajadaPorFrecuencia(frecuencia)
									.setValue(80);
							getProgressBarBajadaPorFrecuencia(frecuencia)
									.setString("80 Db");

							getProgressBarSubidaPorFrecuencia(frecuencia)
									.setValue(80);
							getProgressBarSubidaPorFrecuencia(frecuencia)
									.setString("80 Db");
						}
					}
				});

				if (!cancel)
					agregarResultado(getRealFrecuencia(i),
							tAudio.getDbSubida(), tAudio.getDbBajanda());
			}

		} catch (InterruptedException e) {

		}
	}

	public JProgressBar getProgressBarSubidaPorFrecuencia(int frecuencia) {
		switch (frecuencia) {
		case 1:
			return jProgress250S;
		case 2:
			return jProgress500S;
		case 3:
			return jProgress1000S;
		case 4:
			return jProgress2000S;
		case 5:
			return jProgress3000S;
		case 6:
			return jProgress4000S;
		case 7:
			return jProgress6000S;
		case 8:
			return jProgress8000S;
		default:
			return null;
		}
	}

	public JProgressBar getProgressBarBajadaPorFrecuencia(int frecuencia) {
		switch (frecuencia) {
		case 1:
			return jProgress250B;
		case 2:
			return jProgress500B;
		case 3:
			return jProgress1000B;
		case 4:
			return jProgress2000B;
		case 5:
			return jProgress3000B;
		case 6:
			return jProgress4000B;
		case 7:
			return jProgress6000B;
		case 8:
			return jProgress8000B;
		default:
			return null;
		}
	}

	public int getRealFrecuencia(int frecuencia) {
		switch (frecuencia) {
		case 1:
			return 250;
		case 2:
			return 500;
		case 3:
			return 1000;
		case 4:
			return 2000;
		case 5:
			return 3000;
		case 6:
			return 4000;
		case 7:
			return 6000;
		case 8:
			return 8000;
		default:
			throw new RuntimeException("Frecuencia no soportada.: "
					+ frecuencia);
		}
	}

	public void ejecutarIndividual(final int frecuencia,
			final JProgressBar progresSubida, final JProgressBar progresBajada) {
		cancel = false;
		try {
			//Thread.sleep(5000);
			sendOrdes(frecuencia);
			Util.mostrarPanelOperacionesLargasConCancelar(
					operacionLargaMensaje, btnCancelar);
			Thread.sleep(100);
			Thread timer = new Thread() {
				public void run() {

					escuchar(frecuencia, progresSubida, progresBajada);
					Util.ocultarPanelOperacionesLargas();
				}
			};
			timer.start();

		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	public int getOrdenOido() {
		if (radioAmbos.isSelected())
			return ThreadTrama.ORDEN_SONIDO_OIDO_AMBOS;
		else if (radioDerecho.isSelected())
			return ThreadTrama.ORDEN_SONIDO_OIDO_DERECHO;
		else if (radioIzquierdo.isSelected())
			return ThreadTrama.ORDEN_SONIDO_OIDO_IZQ;

		throw new RuntimeException("getOrdenOido error");
	}

	public void sendOrdes(int frecuencia) {
		System.out.println("sendOrdes: " + frecuencia);
		try {
			switch (frecuencia) {
			case 1:
				Util.thTrama.sendOrden(ThreadTrama.ORDEN_TONO250);
				break;
			case 2:
				Util.thTrama.sendOrden(ThreadTrama.ORDEN_TONO500);
				break;
			case 3:
				Util.thTrama.sendOrden(ThreadTrama.ORDEN_TONO1000);
				break;
			case 4:
				Util.thTrama.sendOrden(ThreadTrama.ORDEN_TONO2000);
				break;
			case 5:
				Util.thTrama.sendOrden(ThreadTrama.ORDEN_TONO3000);
				break;
			case 6:
				Util.thTrama.sendOrden(ThreadTrama.ORDEN_TONO4000);
				break;
			case 7:
				Util.thTrama.sendOrden(ThreadTrama.ORDEN_TONO6000);
				break;
			case 8:
				Util.thTrama.sendOrden(ThreadTrama.ORDEN_TONO8000);
				break;
			}
			Thread.sleep(50);
			Util.thTrama.sendOrden(getOrdenOido());

		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		lbError = new javax.swing.JLabel();
		btnGuardar = new javax.swing.JToggleButton();
		jPanel1 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		btnComenzar250 = new javax.swing.JButton();
		jProgress250B = new javax.swing.JProgressBar();
		jProgress250S = new javax.swing.JProgressBar();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jPanel11 = new javax.swing.JPanel();
		radioDerecho = new javax.swing.JRadioButton();
		radioIzquierdo = new javax.swing.JRadioButton();
		radioAmbos = new javax.swing.JRadioButton();
		btnComenzarAutomatico = new javax.swing.JButton();
		jPanel4 = new javax.swing.JPanel();
		btnComenzar500 = new javax.swing.JButton();
		jProgress500B = new javax.swing.JProgressBar();
		jProgress500S = new javax.swing.JProgressBar();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jPanel5 = new javax.swing.JPanel();
		btnComenzar1000 = new javax.swing.JButton();
		jProgress1000B = new javax.swing.JProgressBar();
		jProgress1000S = new javax.swing.JProgressBar();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jPanel6 = new javax.swing.JPanel();
		btnComenzar2000 = new javax.swing.JButton();
		jProgress2000B = new javax.swing.JProgressBar();
		jProgress2000S = new javax.swing.JProgressBar();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jPanel8 = new javax.swing.JPanel();
		btnComenzar6000 = new javax.swing.JButton();
		jProgress6000B = new javax.swing.JProgressBar();
		jProgress6000S = new javax.swing.JProgressBar();
		jLabel13 = new javax.swing.JLabel();
		jLabel14 = new javax.swing.JLabel();
		jPanel7 = new javax.swing.JPanel();
		btnComenzar8000 = new javax.swing.JButton();
		jProgress8000B = new javax.swing.JProgressBar();
		jProgress8000S = new javax.swing.JProgressBar();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jPanel9 = new javax.swing.JPanel();
		btnComenzar4000 = new javax.swing.JButton();
		jProgress4000B = new javax.swing.JProgressBar();
		jProgress4000S = new javax.swing.JProgressBar();
		jLabel15 = new javax.swing.JLabel();
		jLabel16 = new javax.swing.JLabel();
		jPanel10 = new javax.swing.JPanel();
		btnComenzar3000 = new javax.swing.JButton();
		jProgress3000B = new javax.swing.JProgressBar();
		jProgress3000S = new javax.swing.JProgressBar();
		jLabel17 = new javax.swing.JLabel();
		jLabel18 = new javax.swing.JLabel();
		panelGrafico = new javax.swing.JPanel();

		lbError.setForeground(new java.awt.Color(204, 0, 0));
		lbError.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_ERROR)));
		lbError.setText(Constantes.ERROR_SIN_RESULTADOS);

		btnGuardar.setFont(new java.awt.Font("Segoe UI", 3, 14));
		btnGuardar.setForeground(new java.awt.Color(0, 0, 255));
		btnGuardar.setText("Guardar Resultados");
		btnGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGuardarActionPerformed(evt);
			}
		});

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"250 Hz",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 1, 14)));

		btnComenzar250.setText("Comenzar");
		btnComenzar250.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnComenzar250ActionPerformed(evt);
			}
		});

		jProgress250B.setBackground(new java.awt.Color(255, 255, 204));
		jProgress250B.setForeground(new java.awt.Color(255, 51, 51));
		jProgress250B.setMaximum(80);
		jProgress250B.setOrientation(1);
		jProgress250B.setValue(80);
		jProgress250B.setOpaque(true);
		jProgress250B.setString("80 Db");
		jProgress250B.setStringPainted(true);

		jProgress250S.setMaximum(80);
		jProgress250S.setOrientation(1);
		jProgress250S.setValue(80);
		jProgress250S.setString("80 Db");
		jProgress250S.setStringPainted(true);

		jLabel3.setText("0 Db");

		jLabel4.setText("80 Db");

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel3Layout
										.createSequentialGroup()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addContainerGap(
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel3)
																		.addGap(
																				11,
																				11,
																				11))
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jLabel4)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
										.addComponent(
												jProgress250S,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jProgress250B,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(67, 67, 67)).addComponent(
								btnComenzar250,
								javax.swing.GroupLayout.PREFERRED_SIZE, 100,
								javax.swing.GroupLayout.PREFERRED_SIZE));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout
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
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel3Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel4)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				81,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel3))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel3Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(
																				jProgress250B,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				113,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				jProgress250S,
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				113,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnComenzar250)
										.addContainerGap()));

		jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"O\u00eddo",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 1, 14)));

		buttonGroup1.add(radioDerecho);
		radioDerecho.setText("Derecho");

		buttonGroup1.add(radioIzquierdo);
		radioIzquierdo.setText("Izquierdo");

		buttonGroup1.add(radioAmbos);
		radioAmbos.setSelected(true);
		radioAmbos.setText("Ambos");

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
																radioAmbos)
														.addComponent(
																radioDerecho)
														.addComponent(
																radioIzquierdo))
										.addContainerGap(100, Short.MAX_VALUE)));
		jPanel11Layout
				.setVerticalGroup(jPanel11Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel11Layout
										.createSequentialGroup()
										.addComponent(radioAmbos)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(radioDerecho)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(radioIzquierdo)));

		btnComenzarAutomatico.setFont(new java.awt.Font("Segoe UI", 3, 14));
		btnComenzarAutomatico.setText("Comenzar Test Completo");
		btnComenzarAutomatico
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						btnComenzarAutomaticoActionPerformed(evt);
					}
				});

		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"500 Hz",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 1, 14)));

		btnComenzar500.setText("Comenzar");
		btnComenzar500.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnComenzar500ActionPerformed(evt);
			}
		});

		jProgress500B.setBackground(new java.awt.Color(255, 255, 204));
		jProgress500B.setForeground(new java.awt.Color(255, 51, 51));
		jProgress500B.setMaximum(80);
		jProgress500B.setOrientation(1);
		jProgress500B.setValue(80);
		jProgress500B.setOpaque(true);
		jProgress500B.setString("80 Db");
		jProgress500B.setStringPainted(true);

		jProgress500S.setMaximum(80);
		jProgress500S.setOrientation(1);
		jProgress500S.setValue(80);
		jProgress500S.setString("80 Db");
		jProgress500S.setStringPainted(true);

		jLabel5.setText("0 Db");

		jLabel6.setText("80 Db");

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(
				jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout
				.setHorizontalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel4Layout
										.createSequentialGroup()
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel4Layout
																		.createSequentialGroup()
																		.addContainerGap(
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel5)
																		.addGap(
																				11,
																				11,
																				11))
														.addGroup(
																jPanel4Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jLabel6)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
										.addComponent(
												jProgress500S,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jProgress500B,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(67, 67, 67)).addComponent(
								btnComenzar500,
								javax.swing.GroupLayout.PREFERRED_SIZE, 100,
								javax.swing.GroupLayout.PREFERRED_SIZE));
		jPanel4Layout
				.setVerticalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel4Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel6)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				81,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel5))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel4Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(
																				jProgress500B,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				113,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				jProgress500S,
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				113,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnComenzar500)
										.addContainerGap()));

		jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"1000 Hz",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 1, 14)));

		btnComenzar1000.setText("Comenzar");
		btnComenzar1000.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnComenzar1000ActionPerformed(evt);
			}
		});

		jProgress1000B.setBackground(new java.awt.Color(255, 255, 204));
		jProgress1000B.setForeground(new java.awt.Color(255, 51, 51));
		jProgress1000B.setMaximum(80);
		jProgress1000B.setOrientation(1);
		jProgress1000B.setValue(80);
		jProgress1000B.setOpaque(true);
		jProgress1000B.setString("80 Db");
		jProgress1000B.setStringPainted(true);

		jProgress1000S.setMaximum(80);
		jProgress1000S.setOrientation(1);
		jProgress1000S.setValue(80);
		jProgress1000S.setString("80 Db");
		jProgress1000S.setStringPainted(true);

		jLabel7.setText("0 Db");

		jLabel8.setText("80 Db");

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(
				jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout
				.setHorizontalGroup(jPanel5Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel5Layout
										.createSequentialGroup()
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel5Layout
																		.createSequentialGroup()
																		.addContainerGap(
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel7)
																		.addGap(
																				11,
																				11,
																				11))
														.addGroup(
																jPanel5Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jLabel8)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
										.addComponent(
												jProgress1000S,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jProgress1000B,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(67, 67, 67)).addComponent(
								btnComenzar1000,
								javax.swing.GroupLayout.PREFERRED_SIZE, 100,
								javax.swing.GroupLayout.PREFERRED_SIZE));
		jPanel5Layout
				.setVerticalGroup(jPanel5Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel5Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel5Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel8)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				81,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel7))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel5Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(
																				jProgress1000B,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				113,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				jProgress1000S,
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				113,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnComenzar1000)
										.addContainerGap()));

		jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"2000 Hz",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 1, 14)));

		btnComenzar2000.setText("Comenzar");
		btnComenzar2000.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnComenzar2000ActionPerformed(evt);
			}
		});

		jProgress2000B.setBackground(new java.awt.Color(255, 255, 204));
		jProgress2000B.setForeground(new java.awt.Color(255, 51, 51));
		jProgress2000B.setMaximum(80);
		jProgress2000B.setOrientation(1);
		jProgress2000B.setValue(80);
		jProgress2000B.setOpaque(true);
		jProgress2000B.setString("80 Db");
		jProgress2000B.setStringPainted(true);

		jProgress2000S.setMaximum(80);
		jProgress2000S.setOrientation(1);
		jProgress2000S.setValue(80);
		jProgress2000S.setString("80 Db");
		jProgress2000S.setStringPainted(true);

		jLabel9.setText("0 Db");

		jLabel10.setText("80 Db");

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(
				jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout
				.setHorizontalGroup(jPanel6Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel6Layout
										.createSequentialGroup()
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel6Layout
																		.createSequentialGroup()
																		.addContainerGap(
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel9)
																		.addGap(
																				11,
																				11,
																				11))
														.addGroup(
																jPanel6Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jLabel10)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
										.addComponent(
												jProgress2000S,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jProgress2000B,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(67, 67, 67)).addComponent(
								btnComenzar2000,
								javax.swing.GroupLayout.PREFERRED_SIZE, 100,
								javax.swing.GroupLayout.PREFERRED_SIZE));
		jPanel6Layout
				.setVerticalGroup(jPanel6Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel6Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel6Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel10)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				81,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel9))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel6Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(
																				jProgress2000B,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				113,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				jProgress2000S,
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				113,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnComenzar2000)
										.addContainerGap()));

		jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"6000 Hz",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 1, 14)));

		btnComenzar6000.setText("Comenzar");
		btnComenzar6000.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnComenzar6000ActionPerformed(evt);
			}
		});

		jProgress6000B.setBackground(new java.awt.Color(255, 255, 204));
		jProgress6000B.setForeground(new java.awt.Color(255, 51, 51));
		jProgress6000B.setMaximum(80);
		jProgress6000B.setOrientation(1);
		jProgress6000B.setValue(80);
		jProgress6000B.setOpaque(true);
		jProgress6000B.setString("80 Db");
		jProgress6000B.setStringPainted(true);

		jProgress6000S.setMaximum(80);
		jProgress6000S.setOrientation(1);
		jProgress6000S.setValue(80);
		jProgress6000S.setString("80 Db");
		jProgress6000S.setStringPainted(true);

		jLabel13.setText("0 Db");

		jLabel14.setText("80 Db");

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
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addContainerGap(
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel13)
																		.addGap(
																				11,
																				11,
																				11))
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jLabel14)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
										.addComponent(
												jProgress6000S,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jProgress6000B,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(67, 67, 67)).addComponent(
								btnComenzar6000,
								javax.swing.GroupLayout.PREFERRED_SIZE, 100,
								javax.swing.GroupLayout.PREFERRED_SIZE));
		jPanel8Layout
				.setVerticalGroup(jPanel8Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel8Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel8Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel14)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				81,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel13))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel8Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(
																				jProgress6000B,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				113,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				jProgress6000S,
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				113,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnComenzar6000)
										.addContainerGap()));

		jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"8000 Hz",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 1, 14)));

		btnComenzar8000.setText("Comenzar");
		btnComenzar8000.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnComenzar8000ActionPerformed(evt);
			}
		});

		jProgress8000B.setBackground(new java.awt.Color(255, 255, 204));
		jProgress8000B.setForeground(new java.awt.Color(255, 51, 51));
		jProgress8000B.setMaximum(80);
		jProgress8000B.setOrientation(1);
		jProgress8000B.setValue(80);
		jProgress8000B.setOpaque(true);
		jProgress8000B.setString("80 Db");
		jProgress8000B.setStringPainted(true);

		jProgress8000S.setMaximum(80);
		jProgress8000S.setOrientation(1);
		jProgress8000S.setValue(80);
		jProgress8000S.setString("80 Db");
		jProgress8000S.setStringPainted(true);

		jLabel11.setText("0 Db");

		jLabel12.setText("80 Db");

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
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel7Layout
																		.createSequentialGroup()
																		.addContainerGap(
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel11)
																		.addGap(
																				11,
																				11,
																				11))
														.addGroup(
																jPanel7Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jLabel12)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
										.addComponent(
												jProgress8000S,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jProgress8000B,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(67, 67, 67)).addComponent(
								btnComenzar8000,
								javax.swing.GroupLayout.PREFERRED_SIZE, 100,
								javax.swing.GroupLayout.PREFERRED_SIZE));
		jPanel7Layout
				.setVerticalGroup(jPanel7Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel7Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel7Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel12)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				81,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel11))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel7Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(
																				jProgress8000B,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				113,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				jProgress8000S,
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				113,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnComenzar8000)
										.addContainerGap()));

		jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"4000 Hz",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 1, 14)));

		btnComenzar4000.setText("Comenzar");
		btnComenzar4000.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnComenzar4000ActionPerformed(evt);
			}
		});

		jProgress4000B.setBackground(new java.awt.Color(255, 255, 204));
		jProgress4000B.setForeground(new java.awt.Color(255, 51, 51));
		jProgress4000B.setMaximum(80);
		jProgress4000B.setOrientation(1);
		jProgress4000B.setValue(80);
		jProgress4000B.setOpaque(true);
		jProgress4000B.setString("80 Db");
		jProgress4000B.setStringPainted(true);

		jProgress4000S.setMaximum(80);
		jProgress4000S.setOrientation(1);
		jProgress4000S.setValue(80);
		jProgress4000S.setString("80 Db");
		jProgress4000S.setStringPainted(true);

		jLabel15.setText("0 Db");

		jLabel16.setText("80 Db");

		javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(
				jPanel9);
		jPanel9.setLayout(jPanel9Layout);
		jPanel9Layout
				.setHorizontalGroup(jPanel9Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel9Layout
										.createSequentialGroup()
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel9Layout
																		.createSequentialGroup()
																		.addContainerGap(
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel15)
																		.addGap(
																				11,
																				11,
																				11))
														.addGroup(
																jPanel9Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jLabel16)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
										.addComponent(
												jProgress4000S,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jProgress4000B,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(67, 67, 67)).addComponent(
								btnComenzar4000,
								javax.swing.GroupLayout.PREFERRED_SIZE, 100,
								javax.swing.GroupLayout.PREFERRED_SIZE));
		jPanel9Layout
				.setVerticalGroup(jPanel9Layout
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
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel9Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel16)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				81,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel15))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel9Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(
																				jProgress4000B,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				113,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				jProgress4000S,
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				113,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnComenzar4000)
										.addContainerGap()));

		jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"3000 Hz",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 1, 14)));

		btnComenzar3000.setText("Comenzar");
		btnComenzar3000.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnComenzar3000ActionPerformed(evt);
			}
		});

		jProgress3000B.setBackground(new java.awt.Color(255, 255, 204));
		jProgress3000B.setForeground(new java.awt.Color(255, 51, 51));
		jProgress3000B.setMaximum(80);
		jProgress3000B.setOrientation(1);
		jProgress3000B.setValue(80);
		jProgress3000B.setOpaque(true);
		jProgress3000B.setString("80 Db");
		jProgress3000B.setStringPainted(true);

		jProgress3000S.setMaximum(80);
		jProgress3000S.setOrientation(1);
		jProgress3000S.setValue(80);
		jProgress3000S.setString("80 Db");
		jProgress3000S.setStringPainted(true);

		jLabel17.setText("0 Db");

		jLabel18.setText("80 Db");

		javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(
				jPanel10);
		jPanel10.setLayout(jPanel10Layout);
		jPanel10Layout
				.setHorizontalGroup(jPanel10Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel10Layout
										.createSequentialGroup()
										.addGroup(
												jPanel10Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel10Layout
																		.createSequentialGroup()
																		.addContainerGap(
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel17)
																		.addGap(
																				11,
																				11,
																				11))
														.addGroup(
																jPanel10Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jLabel18)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
										.addComponent(
												jProgress3000S,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jProgress3000B,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(67, 67, 67)).addComponent(
								btnComenzar3000,
								javax.swing.GroupLayout.PREFERRED_SIZE, 100,
								javax.swing.GroupLayout.PREFERRED_SIZE));
		jPanel10Layout
				.setVerticalGroup(jPanel10Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel10Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel10Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel10Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel18)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				81,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel17))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel10Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(
																				jProgress3000B,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				113,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				jProgress3000S,
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				113,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnComenzar3000)
										.addContainerGap()));

		panelGrafico.setLayout(new java.awt.GridLayout(1, 0));

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
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jPanel11,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																btnComenzarAutomatico,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGap(18, 18, 18)
										.addComponent(
												panelGrafico,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												765, Short.MAX_VALUE)
										.addContainerGap())
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addComponent(
												jPanel3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												117,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel4,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												117,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel5,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												117,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel6,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												117,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel10,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												117,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(
												jPanel9,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												117,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel8,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												117,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel7,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												117,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(5, 5, 5)));

		jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
				new java.awt.Component[] { jPanel10, jPanel3, jPanel4, jPanel5,
						jPanel6, jPanel7, jPanel8, jPanel9 });

		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
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
																jPanel4,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																188,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jPanel5,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																188,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jPanel3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																188,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jPanel6,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																188,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jPanel8,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																188,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jPanel10,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																188,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jPanel7,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																188,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jPanel9,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																188,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGap(
																				7,
																				7,
																				7)
																		.addComponent(
																				jPanel11,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnComenzarAutomatico))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				panelGrafico,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				176,
																				Short.MAX_VALUE)))
										.addContainerGap()));

		jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL,
				new java.awt.Component[] { jPanel10, jPanel3, jPanel4, jPanel5,
						jPanel6, jPanel7, jPanel8, jPanel9 });

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addComponent(
												lbError,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												610,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												211, Short.MAX_VALUE)
										.addComponent(btnGuardar).addGap(22,
												22, 22)).addComponent(jPanel1,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE));
		layout
				.setVerticalGroup(layout
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
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																lbError,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																24,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnGuardar))
										.addContainerGap(14, Short.MAX_VALUE)));
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnComenzar8000ActionPerformed(java.awt.event.ActionEvent evt) {
		ejecutarIndividual(8, jProgress8000S, jProgress8000B);
	}

	private void btnComenzar6000ActionPerformed(java.awt.event.ActionEvent evt) {
		ejecutarIndividual(7, jProgress6000S, jProgress6000B);
	}

	private void btnComenzar4000ActionPerformed(java.awt.event.ActionEvent evt) {
		ejecutarIndividual(6, jProgress4000S, jProgress4000B);
	}

	private void btnComenzar3000ActionPerformed(java.awt.event.ActionEvent evt) {
		ejecutarIndividual(5, jProgress3000S, jProgress3000B);
	}

	private void btnComenzar2000ActionPerformed(java.awt.event.ActionEvent evt) {
		ejecutarIndividual(4, jProgress2000S, jProgress2000B);
	}

	private void btnComenzar1000ActionPerformed(java.awt.event.ActionEvent evt) {
		ejecutarIndividual(3, jProgress1000S, jProgress1000B);
	}

	private void btnComenzar500ActionPerformed(java.awt.event.ActionEvent evt) {
		ejecutarIndividual(2, jProgress500S, jProgress500B);
	}

	private void btnComenzar250ActionPerformed(java.awt.event.ActionEvent evt) {
		ejecutarIndividual(1, jProgress250S, jProgress250B);
	}

	private void btnCancelarActionPerformed2(java.awt.event.ActionEvent evt) {
		Util.thTrama.sendOrden(ThreadTrama.ORDEN_STOP_AUTOMATICO);
		cancel = true;
	}

	private void btnComenzarAutomaticoActionPerformed(
			java.awt.event.ActionEvent evt) {
		cancel = false;
		Util.mostrarPanelOperacionesLargasConCancelar(operacionLargaMensaje,
				btnCancelar);
		Thread timer = new Thread() {
			public void run() {
				escucharTodasLasFreccuencias();
				Util.ocultarPanelOperacionesLargas();
			}
		};
		timer.start();
	}

	public boolean isExamenValid() {
		Util.mostrarError(lbError, null, true);
		for (Resultado res : resultados) {
			if (res.getResValor1() != null) {
				return true;
			}
		}

		Util.mostrarError(lbError, "Debe realizar alguno de los ex�menes.",
				false);
		return false;
	}

	public void cargarResultados() {
		for (int i = resultados.size() - 1; i >= 0; i--) {
			if (resultados.get(i).getResValor1() == null)
				resultados.remove(i);
		}
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

				ResultadoDetalleExamen resultadoDetalleExamen = new ResultadoDetalleExamen();
				resultadoDetalleExamen.setExamenDetalle(exaDetalle);
				resultadoDetalleExamen.setPersonaExamen(personaExamen);
				List lstResultados = resultadoDetalleExamenService
						.getAll(resultadoDetalleExamen);

				if (lstResultados.size() < 1) {
					resultadoDetalleExamenService
							.insert(resultadoDetalleExamen);
				} else if (lstResultados.size() == 1) {
					resultadoDetalleExamen = (ResultadoDetalleExamen) lstResultados
							.get(0);
				}

				String detalleResultado = "<HTML>";
				Set setResultados = resultadoDetalleExamen.getResultados();
				setResultados.clear();
				for (int i = 0; i < this.resultados.size(); i++) {
					this.resultados.get(i).setResultadoDetalleExamen(
							resultadoDetalleExamen);
					setResultados.add(this.resultados.get(i));
					detalleResultado = detalleResultado
							+ this.resultados.get(i).getResEtapaDesc() + "/ ";
					int resto = i % 2;
					if (resto != 0)
						detalleResultado += "<BR>";

				}

				String resultado = getResultado();

				resultadoDetalleExamen.setRdeResultado(resultado);
				resultadoDetalleExamen.setRdeDetalleResultado(detalleResultado
						+ "</HTML>");
				resultadoDetalleExamen.setRdeParametrosCorrecion(exaDetalle
						.getExadParametrosCorrecion());

				resultadoDetalleExamenService.update(resultadoDetalleExamen);

				btn.setForeground(Color.BLACK);
				Util.setIcon(btn, Constantes.IMG_ACEPTAR_SMALL);

				((PanelDetalleExamen) btn.getParent()).nextExamen(btn);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String getResultado() {
		for (Resultado res : resultados) {

			double diferencia = Math.abs(res.getResValor1()
					- res.getResValor2());
			if (diferencia > 10)
				return Examen.RESULTADO_FUERA;

			double promedio = (res.getResValor1() + res.getResValor2()) / 2d;
			promedio = Util.redondear(promedio);

			if (this.personaExamen.getPexaTipoExamen().equals(PersonaExamen.TIPO_EXAMEN_PROFECIONAL)
					&& promedio > 50)
				return Examen.RESULTADO_FUERA;
			else if (this.personaExamen.getPexaTipoExamen()
					.equals(PersonaExamen.TIPO_EXAMEN_PARTICULAR)
					&& promedio > 60)
				return Examen.RESULTADO_FUERA;
		}

		return Examen.RESULTADO_DENTRO;
	}

	@Override
	public void finalizar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBtn(JToggleButton btn) {
		// TODO Auto-generated method stub

	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnComenzar1000;
	private javax.swing.JButton btnComenzar2000;
	private javax.swing.JButton btnComenzar250;
	private javax.swing.JButton btnComenzar3000;
	private javax.swing.JButton btnComenzar4000;
	private javax.swing.JButton btnComenzar500;
	private javax.swing.JButton btnComenzar6000;
	private javax.swing.JButton btnComenzar8000;
	private javax.swing.JButton btnComenzarAutomatico;
	private javax.swing.JToggleButton btnGuardar;
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel10;
	private javax.swing.JPanel jPanel11;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JPanel jPanel9;
	private javax.swing.JProgressBar jProgress1000B;
	private javax.swing.JProgressBar jProgress1000S;
	private javax.swing.JProgressBar jProgress2000B;
	private javax.swing.JProgressBar jProgress2000S;
	private javax.swing.JProgressBar jProgress250B;
	private javax.swing.JProgressBar jProgress250S;
	private javax.swing.JProgressBar jProgress3000B;
	private javax.swing.JProgressBar jProgress3000S;
	private javax.swing.JProgressBar jProgress4000B;
	private javax.swing.JProgressBar jProgress4000S;
	private javax.swing.JProgressBar jProgress500B;
	private javax.swing.JProgressBar jProgress500S;
	private javax.swing.JProgressBar jProgress6000B;
	private javax.swing.JProgressBar jProgress6000S;
	private javax.swing.JProgressBar jProgress8000B;
	private javax.swing.JProgressBar jProgress8000S;
	private javax.swing.JLabel lbError;
	private javax.swing.JPanel panelGrafico;
	private javax.swing.JRadioButton radioAmbos;
	private javax.swing.JRadioButton radioDerecho;
	private javax.swing.JRadioButton radioIzquierdo;
	// End of variables declaration//GEN-END:variables
	private javax.swing.JButton btnCancelar;
	private boolean cancel = false;
	private List<Resultado> resultados = new ArrayList();
	private String seriesSubida = "Subida";
	private String seriesBajada = "Bajada";
	private JFreeChart chart;
	private DefaultCategoryDataset dataset;

}