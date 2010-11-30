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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

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
public class PanelCampimetria extends javax.swing.JPanel implements
		Finalisable, PanelExamen {

	private JToggleButton btn;
	private PersonaExamen personaExamen;
	private ExamenDetalle exaDetalle;
	private List<Resultado> resultados = new ArrayList<Resultado>();
	private static final int LINEA_PROFECIONAL = 6;
	private static final int LINEA_PARTICULAR = 5;
	private ThreadTrama thTrama;
	private int esperarRespuesta = 4000;
	private int tiempoEspera = 100;
	private Thread timer;
	private String operacionLargaMensaje = "Aguarde la respuesta del paciente";

	/** Creates new form PanelAgudezaVisual */
	public PanelCampimetria(JToggleButton btn, PersonaExamen personaExamen) {
		this.btn = btn;
		this.personaExamen = personaExamen;
		initComponents();
		Util.mostrarError(lbError, null, true);

		try {
			ExamenDetalleDefinition examenDetalleService = (ExamenDetalleDefinition) ContextManager
					.getBizObject("examenDetalleService");
			exaDetalle = new ExamenDetalle();
			exaDetalle
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_CAMPIMETRIA);
			exaDetalle = (ExamenDetalle) examenDetalleService
					.getAll(exaDetalle).get(0);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		inicializarThreads();
	}

	public void inicializarThreads() {
		try {

			thTrama = Util.thTrama;
			//thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_TEST_LAMINAS);
			//thTrama.sendOrden(ThreadTrama.ORDEN_CAMBIA_ESTADO_LUZ_DER);
			//thTrama.sendOrden(ThreadTrama.ORDEN_CAMBIA_ESTADO_LUZ_IZQ);
			//thTrama.sendOrden(ThreadTrama.ORDEN_IR_TEST8);

		} catch (ExceptionIsNotHadware e) {
			JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		btnGuardar = new javax.swing.JToggleButton();
		lbError = new javax.swing.JLabel();
		jLayeredPane1 = new javax.swing.JLayeredPane();
		jPanel1 = new javax.swing.JPanel();
		jRadioNasalIzq = new javax.swing.JRadioButton();
		jRadioNasalDer = new javax.swing.JRadioButton();
		lbRespuestaNasalSup = new javax.swing.JLabel();
		lbRespuestaNasalInf = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jRadioDer85 = new javax.swing.JRadioButton();
		jRadioDer70 = new javax.swing.JRadioButton();
		jRadioDer55 = new javax.swing.JRadioButton();
		lbRespuesta85Der = new javax.swing.JLabel();
		lbRespuesta70Der = new javax.swing.JLabel();
		lbRespuesta55Der = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		jRadioIzq85 = new javax.swing.JRadioButton();
		jRadioIzq70 = new javax.swing.JRadioButton();
		jRadioIzq55 = new javax.swing.JRadioButton();
		lbRespuesta85Izq = new javax.swing.JLabel();
		lbRespuesta70Izq = new javax.swing.JLabel();
		lbRespuesta55Izq = new javax.swing.JLabel();

		setBorder(javax.swing.BorderFactory.createTitledBorder(""));

		btnGuardar.setFont(new java.awt.Font("Segoe UI", 3, 14));
		btnGuardar.setForeground(new java.awt.Color(0, 0, 255));
		btnGuardar.setText("Guardar Resultados");
		btnGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGuardarActionPerformed(evt);
			}
		});

		lbError.setForeground(new java.awt.Color(204, 0, 0));
		lbError.setIcon(new ImageIcon(getClass().getResource(
				Constantes.IMG_ERROR)));
		lbError.setText(Constantes.ERROR_SIN_RESULTADOS);

		jLayeredPane1.setBorder(javax.swing.BorderFactory
				.createTitledBorder(""));

		jPanel1
				.setBorder(javax.swing.BorderFactory
						.createTitledBorder("Nasal"));

		jRadioNasalIzq.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioNasalIzqActionPerformed(evt);
			}
		});

		jRadioNasalDer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioNasalDerActionPerformed(evt);
			}
		});

		lbRespuestaNasalSup.setFont(new java.awt.Font("Segoe UI", 3, 18));
		lbRespuestaNasalSup.setForeground(new java.awt.Color(0, 0, 255));
		lbRespuestaNasalSup.setBorder(javax.swing.BorderFactory
				.createTitledBorder(""));

		lbRespuestaNasalInf.setFont(new java.awt.Font("Segoe UI", 3, 18));
		lbRespuestaNasalInf.setForeground(new java.awt.Color(0, 0, 255));
		lbRespuestaNasalInf.setBorder(javax.swing.BorderFactory
				.createTitledBorder(""));

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
										.addComponent(jRadioNasalIzq)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												lbRespuestaNasalSup,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												24, Short.MAX_VALUE)
										.addComponent(jRadioNasalDer)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												lbRespuestaNasalInf,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGap(55, 55, 55)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																lbRespuestaNasalSup,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																30,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jRadioNasalIzq)
														.addComponent(
																jRadioNasalDer)
														.addComponent(
																lbRespuestaNasalInf,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																30,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(66, Short.MAX_VALUE)));

		jPanel1.setBounds(220, 40, 180, 180);
		jLayeredPane1.add(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jPanel2.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Ojo derecho"));

		jRadioDer85.setText("85\u00b0");
		jRadioDer85.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioDer85ActionPerformed(evt);
			}
		});

		jRadioDer70.setText("70\u00b0");
		jRadioDer70.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioDer70ActionPerformed(evt);
			}
		});

		jRadioDer55.setText("55\u00b0");
		jRadioDer55.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioDer55ActionPerformed(evt);
			}
		});

		lbRespuesta85Der.setFont(new java.awt.Font("Segoe UI", 3, 18));
		lbRespuesta85Der.setForeground(new java.awt.Color(0, 0, 255));
		lbRespuesta85Der.setBorder(javax.swing.BorderFactory
				.createTitledBorder(""));

		lbRespuesta70Der.setFont(new java.awt.Font("Segoe UI", 3, 18));
		lbRespuesta70Der.setForeground(new java.awt.Color(0, 0, 255));
		lbRespuesta70Der.setBorder(javax.swing.BorderFactory
				.createTitledBorder(""));

		lbRespuesta55Der.setFont(new java.awt.Font("Segoe UI", 3, 18));
		lbRespuesta55Der.setForeground(new java.awt.Color(0, 0, 255));
		lbRespuesta55Der.setBorder(javax.swing.BorderFactory
				.createTitledBorder(""));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																lbRespuesta85Der,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																40,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				lbRespuesta55Der,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				40,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jRadioDer55)
																						.addGroup(
																								jPanel2Layout
																										.createSequentialGroup()
																										.addComponent(
																												lbRespuesta70Der,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												40,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												jRadioDer70)))))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jRadioDer85)
										.addContainerGap()));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
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
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								jRadioDer85)
																						.addComponent(
																								lbRespuesta85Der,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								30,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(
																				18,
																				18,
																				18)
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								jRadioDer70)
																						.addComponent(
																								lbRespuesta70Der,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								30,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				35,
																				Short.MAX_VALUE)
																		.addComponent(
																				jRadioDer55))
														.addComponent(
																lbRespuesta55Der,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																30,
																javax.swing.GroupLayout.PREFERRED_SIZE))));

		jPanel2.setBounds(400, 40, 200, 180);
		jLayeredPane1.add(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jPanel4.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Ojo izquierdo"));

		jRadioIzq85.setText("85\u00b0");
		jRadioIzq85.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioIzq85ActionPerformed(evt);
			}
		});

		jRadioIzq70.setText("70\u00b0");
		jRadioIzq70.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioIzq70ActionPerformed(evt);
			}
		});

		jRadioIzq55.setText("55\u00b0");
		jRadioIzq55.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioIzq55ActionPerformed(evt);
			}
		});

		lbRespuesta85Izq.setFont(new java.awt.Font("Segoe UI", 3, 18));
		lbRespuesta85Izq.setForeground(new java.awt.Color(0, 0, 255));
		lbRespuesta85Izq.setBorder(javax.swing.BorderFactory
				.createTitledBorder(""));

		lbRespuesta70Izq.setFont(new java.awt.Font("Segoe UI", 3, 18));
		lbRespuesta70Izq.setForeground(new java.awt.Color(0, 0, 255));
		lbRespuesta70Izq.setBorder(javax.swing.BorderFactory
				.createTitledBorder(""));

		lbRespuesta55Izq.setFont(new java.awt.Font("Segoe UI", 3, 18));
		lbRespuesta55Izq.setForeground(new java.awt.Color(0, 0, 255));
		lbRespuesta55Izq.setBorder(javax.swing.BorderFactory
				.createTitledBorder(""));

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
										.addContainerGap()
										.addComponent(jRadioIzq85)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												lbRespuesta85Izq,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(96, Short.MAX_VALUE))
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addGap(58, 58, 58)
										.addComponent(jRadioIzq70)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												lbRespuesta70Izq,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(46, Short.MAX_VALUE))
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel4Layout
										.createSequentialGroup()
										.addContainerGap(101, Short.MAX_VALUE)
										.addComponent(jRadioIzq55)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												lbRespuesta55Izq,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(3, 3, 3)));
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
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																jPanel4Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel4Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								jRadioIzq85)
																						.addComponent(
																								lbRespuesta85Izq,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								30,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(
																				26,
																				26,
																				26)
																		.addComponent(
																				jRadioIzq70))
														.addComponent(
																lbRespuesta70Izq,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																30,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(27, 27, 27)
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																lbRespuesta55Izq,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																30,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jRadioIzq55))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jPanel4.setBounds(20, 40, 200, 180);
		jLayeredPane1.add(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																layout
																		.createSequentialGroup()
																		.addComponent(
																				lbError,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				304,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnGuardar))
														.addComponent(
																jLayeredPane1,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																624,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addComponent(jLayeredPane1,
						javax.swing.GroupLayout.PREFERRED_SIZE, 234,
						javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18,
						18).addGroup(
						layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lbError,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										24,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnGuardar))
						.addGap(147, 147, 147)));
	}// </editor-fold>
	//GEN-END:initComponents

	private void jRadioNasalDerActionPerformed(java.awt.event.ActionEvent evt) {
		thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_DER);
		try {
			Thread.sleep(tiempoEspera);
		} catch (Exception exception) {
		}

		thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_NASAL);

		Util.mostrarPanelOperacionesLargas(operacionLargaMensaje);
		Thread timer = new Thread() {
			public void run() {
				Boolean ok = escuchar(lbRespuestaNasalInf, null);
				Util.ocultarPanelOperacionesLargas();
			}
		};
		timer.start();
	}

	private void jRadioNasalIzqActionPerformed(java.awt.event.ActionEvent evt) {
		thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_IZQ);
		try {
			Thread.sleep(tiempoEspera);
		} catch (Exception exception) {
		}

		thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_NASAL);

		Util.mostrarPanelOperacionesLargas(operacionLargaMensaje);
		Thread timer = new Thread() {
			public void run() {
				Boolean ok = escuchar(lbRespuestaNasalSup, null);
				Util.ocultarPanelOperacionesLargas();
			}
		};
		timer.start();
	}

	private void jRadioDer55ActionPerformed(java.awt.event.ActionEvent evt) {
		thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_DER);
		try {
			Thread.sleep(tiempoEspera);
		} catch (Exception exception) {
		}
		thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_55);
		//thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_55_DER);

		Util.mostrarPanelOperacionesLargas(operacionLargaMensaje);
		Thread timer = new Thread() {
			public void run() {
				Boolean ok = escuchar(lbRespuesta55Der, true);
				Util.ocultarPanelOperacionesLargas();
			}
		};
		timer.start();
	}

	private void jRadioDer70ActionPerformed(java.awt.event.ActionEvent evt) {
		thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_DER);
		try {
			Thread.sleep(tiempoEspera);
		} catch (Exception exception) {
		}
		thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_70);
		//thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_70_DER);

		Util.mostrarPanelOperacionesLargas(operacionLargaMensaje);
		Thread timer = new Thread() {
			public void run() {
				Boolean ok = escuchar(lbRespuesta70Der, true);
				Util.ocultarPanelOperacionesLargas();
			}
		};
		timer.start();
	}

	private void jRadioDer85ActionPerformed(java.awt.event.ActionEvent evt) {
		thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_DER);
		try {
			Thread.sleep(tiempoEspera);
		} catch (Exception exception) {
		}
		thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_85);
		//thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_85_DER);

		Util.mostrarPanelOperacionesLargas(operacionLargaMensaje);
		Thread timer = new Thread() {
			public void run() {
				Boolean ok = escuchar(lbRespuesta85Der, true);
				Util.ocultarPanelOperacionesLargas();
			}
		};
		timer.start();
	}

	private void jRadioIzq55ActionPerformed(java.awt.event.ActionEvent evt) {
		thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_IZQ);
		try {
			Thread.sleep(tiempoEspera);
		} catch (Exception exception) {
		}
		thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_55);
		//thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_55_IZQ);

		Util.mostrarPanelOperacionesLargas(operacionLargaMensaje);
		Thread timer = new Thread() {
			public void run() {
				Boolean ok = escuchar(lbRespuesta55Izq, false);
				Util.ocultarPanelOperacionesLargas();
			}
		};
		timer.start();
	}

	private void jRadioIzq70ActionPerformed(java.awt.event.ActionEvent evt) {
		thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_IZQ);
		try {
			Thread.sleep(tiempoEspera);
		} catch (Exception exception) {
		}
		thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_70);
		//thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_70_IZQ);

		Util.mostrarPanelOperacionesLargas(operacionLargaMensaje);
		Thread timer = new Thread() {
			public void run() {
				Boolean ok = escuchar(lbRespuesta70Izq, false);
				Util.ocultarPanelOperacionesLargas();
			}
		};
		timer.start();
	}

	private void jRadioIzq85ActionPerformed(java.awt.event.ActionEvent evt) {
		thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_IZQ);
		try {
			Thread.sleep(tiempoEspera);
		} catch (Exception exception) {
		}
		thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_85);
		//thTrama.sendOrden(ThreadTrama.ORDEN_ENCIENDE_PER_85_IZQ);

		Util.mostrarPanelOperacionesLargas(operacionLargaMensaje);
		Thread timer = new Thread() {
			public void run() {
				Boolean ok = escuchar(lbRespuesta85Izq, false);
				Util.ocultarPanelOperacionesLargas();
			}
		};
		timer.start();
	}

	public boolean escuchar(JLabel label, Boolean der) {
		label.setIcon(null);

		int tiempo = 0;
		long sleep = 30;

		try {
			while (tiempo < esperarRespuesta) {
				Thread.sleep(sleep);
				tiempo += sleep;

				TramaVision tv = (TramaVision) thTrama.getTramaValida();
				if (der == null) {
					if (tv.isDerButtonPress() || tv.isIzqButtonPress()) {
						Util.setIcon(label, Constantes.IMG_ACEPTAR_SMALL);
						label.setName("Si");
						thTrama
								.sendOrden(ThreadTrama.ORDEN_APAGAR_TEST_PERIMETRIA);
						return true;
					}
				} else if (der) {
					if (tv.isDerButtonPress()) {
						Util.setIcon(label, Constantes.IMG_ACEPTAR_SMALL);
						label.setName("Si");
						thTrama
								.sendOrden(ThreadTrama.ORDEN_APAGAR_TEST_PERIMETRIA);
						return true;
					} else if (tv.isIzqButtonPress()) {
						Util.setIcon(label, Constantes.IMG_CANCEL_SMALL);
						label.setName("No");
						thTrama
								.sendOrden(ThreadTrama.ORDEN_APAGAR_TEST_PERIMETRIA);
						return false;
					}
				} else if (!der) {
					if (tv.isDerButtonPress()) {
						Util.setIcon(label, Constantes.IMG_CANCEL_SMALL);
						label.setName("No");
						thTrama
								.sendOrden(ThreadTrama.ORDEN_APAGAR_TEST_PERIMETRIA);
						return false;
					} else if (tv.isIzqButtonPress()) {
						Util.setIcon(label, Constantes.IMG_ACEPTAR_SMALL);
						label.setName("Si");
						thTrama
								.sendOrden(ThreadTrama.ORDEN_APAGAR_TEST_PERIMETRIA);
						return true;
					}
				}

			}

			Util.setIcon(label, Constantes.IMG_CANCEL_SMALL);
			label.setName("No");
			thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_TEST_PERIMETRIA);

		} catch (InterruptedException e) {
			return false;
		}

		return false;
	}

	public boolean isExamenValid() {
		Util.mostrarError(lbError, null, true);

		if (resultados.isEmpty()) {
			Util.mostrarError(lbError, "Debe realizar alguno de los exámenes.",
					false);
			return false;
		}

		int cantDe70Gradados = 0;
		for (Resultado res : resultados) {
			if (res.getResEtapa() == 15) {
				cantDe70Gradados++;
			}
		}

		if (cantDe70Gradados < 2) {
			Util.mostrarError(lbError,
					"Debe realizar los exámenes de 70 grados.", false);
			return false;
		}

		return true;
	}

	public void cargarResultados() {
		resultados.clear();

		agregarResultado(lbRespuesta85Izq, 30, "Campimetría 85° izquierda");
		agregarResultado(lbRespuesta70Izq, 15, "Campimetría 70° izquierda");
		agregarResultado(lbRespuesta55Izq, 10, "Campimetría 55° izquierda");

		agregarResultado(lbRespuesta85Der, 30, "Campimetría 85° derecha");
		agregarResultado(lbRespuesta70Der, 15, "Campimetría 70° derecha");
		agregarResultado(lbRespuesta55Der, 10, "Campimetría 55° derecha");

		agregarResultado(lbRespuestaNasalSup, 25, "Campimetría nasal superior");
		agregarResultado(lbRespuestaNasalInf, 20, "Campimetría nasal inferior");
	}

	public void agregarResultado(JLabel lb, long etapa, String desc) {
		if (lb.getName() != null && lb.getName().equals("Si")) {
			Resultado res = new Resultado();
			res.setResEtapa(etapa);
			res.setResEtapaDesc(desc + ": Si");
			res.setResValor1(1d);
			resultados.add(res);
		} else if (lb.getName() != null && lb.getName().equals("No")) {
			Resultado res = new Resultado();
			res.setResEtapa(etapa);
			res.setResEtapaDesc(desc + ": No");
			res.setResValor1(0d);
			resultados.add(res);
		}
	}

	public String getResultado() {
		for (Resultado res : resultados) {
			if (res.getResEtapa() < 30) {
				if (res.getResValor1().intValue() == 0)
					return Examen.RESULTADO_FUERA;
			}
		}

		return Examen.RESULTADO_DENTRO;
	}

	private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
		btnGuardar.setSelected(false);
		ResultadoDetalleExamenDefinition resultadoDetalleExamenService = (ResultadoDetalleExamenDefinition) ContextManager
				.getBizObject("resultadoDetalleExamenService");
		PersonaExamenDefinition personaExamenService = (PersonaExamenDefinition) ContextManager
				.getBizObject("personaExamenService");

		try {

			if (personaExamen != null && personaExamen.getPexaId() == null) {
				personaExamen.setPexaEstado("INICIADO");
				personaExamen.setPexaFecha(new Date());
				personaExamenService.insert(personaExamen);
			}

			cargarResultados();
			if (isExamenValid()) {
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
							+ this.resultados.get(i).getResEtapaDesc()
							+ ". <BR>";
				}

				String resultado = getResultado();

				resultadoDetalleExamen.setRdeResultado(resultado);
				resultadoDetalleExamen.setRdeDetalleResultado(detalleResultado
						+ "</HTML>");

				resultadoDetalleExamenService.update(resultadoDetalleExamen);

				btn.setForeground(Color.BLACK);
				Util.setIcon(btn, Constantes.IMG_ACEPTAR_SMALL);

				((PanelDetalleExamen) btn.getParent()).nextExamen(btn);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void finalizar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBtn(JToggleButton btn) {
		this.btn = btn;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JToggleButton btnGuardar;
	private javax.swing.JLayeredPane jLayeredPane1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JRadioButton jRadioDer55;
	private javax.swing.JRadioButton jRadioDer70;
	private javax.swing.JRadioButton jRadioDer85;
	private javax.swing.JRadioButton jRadioIzq55;
	private javax.swing.JRadioButton jRadioIzq70;
	private javax.swing.JRadioButton jRadioIzq85;
	private javax.swing.JRadioButton jRadioNasalDer;
	private javax.swing.JRadioButton jRadioNasalIzq;
	private javax.swing.JLabel lbError;
	private javax.swing.JLabel lbRespuesta55Der;
	private javax.swing.JLabel lbRespuesta55Izq;
	private javax.swing.JLabel lbRespuesta70Der;
	private javax.swing.JLabel lbRespuesta70Izq;
	private javax.swing.JLabel lbRespuesta85Der;
	private javax.swing.JLabel lbRespuesta85Izq;
	private javax.swing.JLabel lbRespuestaNasalInf;
	private javax.swing.JLabel lbRespuestaNasalSup;
	// End of variables declaration//GEN-END:variables

}