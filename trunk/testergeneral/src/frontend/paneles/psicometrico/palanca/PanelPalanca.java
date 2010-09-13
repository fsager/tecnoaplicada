/*
 * PanelAnticipacion.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.psicometrico.palanca;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.SwingWorker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
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
import examenes.psicometrico.domain.TramaPsicologico;
import frontend.components.JOptionPaneTesterGral;
import frontend.paneles.examenes.Finalisable;
import frontend.paneles.examenes.PanelExamen;
import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class PanelPalanca extends javax.swing.JPanel implements Finalisable,
		PanelExamen, Runnable {

	/** Creates new form PanelAnticipacion */

	private ArrayList<Point> coordenadasPuntos = new ArrayList();

	public PanelPalanca(JToggleButton btn, PersonaExamen personaExamen) {
		inicializarCoordenadas();
		this.btn = btn;
		initComponents();
		th.start();
		jProgress.setStringPainted(true);
		txtErrores.setEditable(false);
		txtTiempo.setEditable(false);
		jLabel3.setVisible(false);

		try {
			ExamenDetalleDefinition examenDetalleService = (ExamenDetalleDefinition) ContextManager
					.getBizObject("examenDetalleService");
			exaDetalle = new ExamenDetalle();
			exaDetalle
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_COOR_BIMANUAL_FINA);
			exaDetalle = (ExamenDetalle) examenDetalleService
					.getAll(exaDetalle).get(0);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		this.personaExamen = personaExamen;
		demo = (personaExamen.getPersona() == null);
		if (personaExamen.getPersona() == null) {
			btnExaminar.setEnabled(false);
			btnExaminarN.setEnabled(false);
			btnGuardar.setEnabled(false);
		}

		this.validate();
		g2d.rotate(Math.toRadians(-90), 315, 230);
		img= buffImg.getScaledInstance(370 - (int) (370 * 0.32),630 - (int) (630 * 0.32), Image.SCALE_SMOOTH);
		inicializarThreads();
	}

	public void unSelectButtons(JToggleButton btnSource) {
		Component[] cmps = panelAcciones.getComponents();
		for (int i = 0; i < cmps.length; i++) {
			if (cmps[i].getClass().equals(JToggleButton.class)) {
				JToggleButton toggleBtn = (JToggleButton) cmps[i];
				if (btnSource != null && btnSource.equals(toggleBtn))
					toggleBtn.setSelected(true);
				else
					toggleBtn.setSelected(false);
			}
		}
	}

	public void finalizarExamen() {

		if (finOK) {
			runExamen = false;
			mostrarResultados();
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					btnGuardar.setEnabled((personaExamen.getPersona() != null)
							&& !demo && true);
				}
			});
		}

		habilitarBotones();
	}

	public void run() {

		while (run) {
			try {

				if (runExamen) {
					int punto = thTrama.getTramaValida().getByte(8);
					//System.out.println("punto: " + punto);

					if (punto == 0) {
						if (!onError)
							addError();
					} else {
						if (onError)
							addErrorTiempo();

						if (!puntosActivados.contains(punto)) {
							puntosActivados.add(punto);
							this.repaint();

							if (punto == puntos) {
								finOK = true;
								task.cancel(true);
								finalizarExamen();
							}
						}
					}
				}
				img= buffImg.getScaledInstance(370 - (int) (370 * 0.32),630 - (int) (630 * 0.32), Image.SCALE_SMOOTH);
				Thread.sleep(100);

			} catch (InterruptedException e) {

			}
		}

	}



	public void addError() {

		errores++;
		txtErrores.setText("" + errores);
		Util.playSound(Constantes.SOUND_ERROR,100);
		onError = true;
		//System.out.println("inicia la cuenta del tiempo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1".toUpperCase());
		tiempoIniError = System.currentTimeMillis();

	}

	public void addErrorTiempo() {

		long tiempoDeError = (System.currentTimeMillis() - tiempoIniError) / 10;
		Util.redondearTiempo(tiempoDeError);
		//System.out.println("detiene la cuenta del tiempo:   "+tiempoDeError);
		Resultado res = new Resultado();

		res.setResValor1(new Double(tiempoDeError));

		tiempoTotal += tiempoDeError;

		resultados.add(res);

		txtTiempo.setText("" + tiempoTotal);
		onError = false;
	}

	public void inicializarCoordenadas() {
		coordenadasPuntos.add(new Point(30, 130));
		coordenadasPuntos.add(new Point(55, 135));
		coordenadasPuntos.add(new Point(73, 135));
		coordenadasPuntos.add(new Point(91, 135));
		coordenadasPuntos.add(new Point(109, 135));
		coordenadasPuntos.add(new Point(127, 135));
		coordenadasPuntos.add(new Point(143, 135));
		coordenadasPuntos.add(new Point(148, 151));
		coordenadasPuntos.add(new Point(156, 167));
		coordenadasPuntos.add(new Point(164, 183));
		coordenadasPuntos.add(new Point(172, 199));
		coordenadasPuntos.add(new Point(180, 215));
		coordenadasPuntos.add(new Point(188, 231));
		coordenadasPuntos.add(new Point(196, 247));
		coordenadasPuntos.add(new Point(178, 247));
		coordenadasPuntos.add(new Point(160, 247));
		coordenadasPuntos.add(new Point(142, 247));
		coordenadasPuntos.add(new Point(124, 247));
		coordenadasPuntos.add(new Point(106, 247));
		coordenadasPuntos.add(new Point(88, 247));
		coordenadasPuntos.add(new Point(70, 247));
		coordenadasPuntos.add(new Point(80, 264));
		coordenadasPuntos.add(new Point(94, 276));
		coordenadasPuntos.add(new Point(108, 286));
		coordenadasPuntos.add(new Point(122, 296));
		coordenadasPuntos.add(new Point(137, 304));
		coordenadasPuntos.add(new Point(152, 312));
		coordenadasPuntos.add(new Point(167, 319));
		coordenadasPuntos.add(new Point(183, 326));
		coordenadasPuntos.add(new Point(199, 332));
		coordenadasPuntos.add(new Point(217, 337));
		coordenadasPuntos.add(new Point(235, 342));
		coordenadasPuntos.add(new Point(253, 344));
		coordenadasPuntos.add(new Point(271, 342));
		coordenadasPuntos.add(new Point(289, 337));
		coordenadasPuntos.add(new Point(307, 332));
		coordenadasPuntos.add(new Point(323, 326));
		coordenadasPuntos.add(new Point(339, 319));
		coordenadasPuntos.add(new Point(354, 312));
		coordenadasPuntos.add(new Point(369, 304));
		coordenadasPuntos.add(new Point(384, 296));
		coordenadasPuntos.add(new Point(388, 281));
		coordenadasPuntos.add(new Point(388, 265));
		coordenadasPuntos.add(new Point(388, 249));
		coordenadasPuntos.add(new Point(388, 233));
		coordenadasPuntos.add(new Point(388, 217));
		coordenadasPuntos.add(new Point(388, 201));
		coordenadasPuntos.add(new Point(388, 185));
		coordenadasPuntos.add(new Point(378, 174));
		coordenadasPuntos.add(new Point(363, 166));
		coordenadasPuntos.add(new Point(348, 158));
		coordenadasPuntos.add(new Point(336, 149));
		coordenadasPuntos.add(new Point(324, 140));
		coordenadasPuntos.add(new Point(314, 131));
		coordenadasPuntos.add(new Point(305, 120));
		coordenadasPuntos.add(new Point(297, 108));
		coordenadasPuntos.add(new Point(292, 95));
		coordenadasPuntos.add(new Point(297, 82));
		coordenadasPuntos.add(new Point(305, 70));
		coordenadasPuntos.add(new Point(314, 59));
		coordenadasPuntos.add(new Point(324, 50));
		coordenadasPuntos.add(new Point(336, 41));
		coordenadasPuntos.add(new Point(348, 32));
		coordenadasPuntos.add(new Point(363, 24));
		coordenadasPuntos.add(new Point(378, 16));
		coordenadasPuntos.add(new Point(396, 16));
		coordenadasPuntos.add(new Point(413, 26));
		coordenadasPuntos.add(new Point(428, 36));
		coordenadasPuntos.add(new Point(441, 46));
		coordenadasPuntos.add(new Point(454, 56));
		coordenadasPuntos.add(new Point(465, 68));
		coordenadasPuntos.add(new Point(476, 80));
		coordenadasPuntos.add(new Point(485, 92));
		coordenadasPuntos.add(new Point(494, 105));
		coordenadasPuntos.add(new Point(501, 118));
		coordenadasPuntos.add(new Point(508, 131));
		coordenadasPuntos.add(new Point(513, 144));
		coordenadasPuntos.add(new Point(518, 157));
		coordenadasPuntos.add(new Point(521, 170));
		coordenadasPuntos.add(new Point(524, 183));
		coordenadasPuntos.add(new Point(514, 202));

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		pintarPuntos(g2d);

		for (int i = 0; i < puntosActivados.size(); i++) {
			int punto = puntosActivados.get(i);

			Point p = coordenadasPuntos.get(punto - 1);
			g2d.setColor(Color.green);

			if (punto == 1) {
				g2d.fillOval(p.x, p.y, 21, 21);
			} else if (punto == puntos) {
				g2d.fillOval(p.x, p.y, 21, 21);
			} else {
				g2d.fillOval(p.x, p.y, 15, 13);
			}
		}

		g.drawImage(img, 150, 125, this);
	}

	public void pintarPuntos(Graphics g) {
		for (int i = 1; i <= puntos && i <= coordenadasPuntos.size(); i++) {

			Point p = coordenadasPuntos.get(i - 1);

			/* AffineTransform origXform = g2d.getTransform();
			 AffineTransform newXform = (AffineTransform)(origXform.clone());
			 //center of rotation is center of the panel
			 int xRot = this.getWidth()/2;
			 int yRot = this.getHeight()/2;
			 newXform.rotate(Math.toRadians(currentAngle), xRot, yRot);*/

			g.setColor(Color.red);
			if (i == 1) {
				g.fillOval(p.x, p.y, 21, 21);
			} else if (i == puntos) {
				g.fillOval(p.x, p.y, 21, 21);
			} else {
				g.fillOval(p.x, p.y, 15, 13);
			}
		}
	}

	public void inicializarThreads() {

		try {
			thTrama = new ThreadTrama(tramaPsicologico);
			Util.thTrama = thTrama;
			thTrama.setEjecucion(99999);
			thTrama.start();
			thTrama.sendOrden(ThreadTrama.ORDEN_PRENDER_LAZER);
		} catch (ExceptionIsNotHadware e) {
			JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		panelAcciones = new javax.swing.JPanel();
		btnAprendizaje = new javax.swing.JToggleButton();
		btnExaminar = new javax.swing.JToggleButton();
		btnExaminarN = new javax.swing.JToggleButton();
		btnGuardar = new javax.swing.JToggleButton();
		btnCancelar = new javax.swing.JToggleButton();
		panelUsuario = new javax.swing.JPanel();
		jProgress = new javax.swing.JProgressBar();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		txtErrores = new javax.swing.JTextField();
		txtTiempo = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();

		panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "Acciones",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 3, 12)));

		btnAprendizaje.setText("Aprendizaje");
		btnAprendizaje.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAprendizajeActionPerformed(evt);
			}
		});

		btnExaminar.setText("Examinar");
		btnExaminar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExaminarActionPerformed(evt);
			}
		});

		btnExaminarN.setText("Examinar Nuevamente");
		btnExaminarN.setEnabled(false);
		btnExaminarN.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnExaminarNActionPerformed(evt);
			}
		});

		btnGuardar.setText("Guardar Resultados");
		btnGuardar.setEnabled(false);
		btnGuardar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGuardarActionPerformed(evt);
			}
		});

		btnCancelar.setText("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout panelAccionesLayout = new javax.swing.GroupLayout(
				panelAcciones);
		panelAcciones.setLayout(panelAccionesLayout);
		panelAccionesLayout
				.setHorizontalGroup(panelAccionesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								panelAccionesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(btnAprendizaje)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(btnExaminar)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnExaminarN)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnGuardar)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnCancelar)
										.addContainerGap(50, Short.MAX_VALUE)));
		panelAccionesLayout.setVerticalGroup(panelAccionesLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						panelAccionesLayout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnExaminar).addComponent(
										btnExaminarN).addComponent(btnGuardar)
								.addComponent(btnCancelar).addComponent(
										btnAprendizaje)));

		panelUsuario.setLayout(new java.awt.GridLayout(1, 0));

		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12));
		jLabel1.setText("Errores:");

		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12));
		jLabel2
				.setText("Tiempo fuera del circuito (cent\u00e9simas de segundos):");

		txtErrores.setFont(new java.awt.Font("Segoe UI", 1, 12));
		txtErrores.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtErroresActionPerformed(evt);
			}
		});

		txtTiempo.setFont(new java.awt.Font("Segoe UI", 1, 12));
		txtTiempo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtTiempoActionPerformed(evt);
			}
		});

		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 17));
		jLabel3.setForeground(new java.awt.Color(255, 0, 0));
		jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel3.setText("Resultado");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(panelAcciones,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jProgress,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jLabel2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																298,
																javax.swing.GroupLayout.PREFERRED_SIZE))
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
																				txtTiempo,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				59,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				162,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				txtErrores,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				74,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jLabel3,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																343,
																Short.MAX_VALUE)))
						.addComponent(panelUsuario,
								javax.swing.GroupLayout.DEFAULT_SIZE, 648,
								Short.MAX_VALUE));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addComponent(
												panelAcciones,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabel3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																18,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jProgress,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																24,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																txtErrores)
														.addComponent(
																jLabel2,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(txtTiempo)
														.addComponent(
																jLabel1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												panelUsuario,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												472, Short.MAX_VALUE)));
	}// </editor-fold>
	//GEN-END:initComponents

	private void txtTiempoActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void txtErroresActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {

		inicializar();
		habilitarBotones();
	}

	public JToggleButton getBtn() {
		return btn;
	}

	public void setBtn(JToggleButton btn) {
		this.btn = btn;
	}

	private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
		unSelectButtons(null);

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

			/*Set resultados = resultadoDetalleExamen.getResultados();
			resultados.clear();
			for (int i = 0; i < this.resultados.size(); i++) {
				this.resultados.get(i).setResultadoDetalleExamen(
						resultadoDetalleExamen);
				resultados.add(this.resultados.get(i));
			}*/

			//resultadoDetalleExamen.setRdeNota(new Double(tiempoTotal));
			resultadoDetalleExamen.setRdeNota2(new Double(errores));
			resultadoDetalleExamen.setRdeResultado(getResultado());
			resultadoDetalleExamenService.update(resultadoDetalleExamen);

			btn.setForeground(Color.BLACK);
			Util.setIcon(btn, Constantes.IMG_ACEPTAR_SMALL);

			JOptionPaneTesterGral.showInternalMessageDialog(
					Constantes.MENSAJE_GUARDADO,
					Constantes.MENSAJE_GUARDADO_TIT,
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void btnExaminarNActionPerformed(java.awt.event.ActionEvent evt) {

		btnExam = true;
		demo = false;
		unSelectButtons(btnExaminarN);
		iniciarExamen();
	}

	private void btnExaminarActionPerformed(java.awt.event.ActionEvent evt) {
		unSelectButtons(btnExaminar);
		demo = false;
		btnExam = true;
		iniciarExamen();
	}

	private void btnAprendizajeActionPerformed(java.awt.event.ActionEvent evt) {
		demo = true;
		iniciarExamen();
	}

	public void mostrarResultados() {
		jLabel3.setVisible(true);

		jLabel3.setText(getResultado());

	}

	public String getResultado() {
		jLabel3.setVisible(true);
		Double errores = Double.valueOf(ContextManager
				.getProperty("EXAMEN.PALANCA.ERRORES.PERMITIDOS.HASTA"));
		Double erroresTiempo = Double.valueOf(ContextManager
				.getProperty("EXAMEN.PALANCA.ERRORES.TIEMPO")) / 10;
		Double erroresPuntosSinActiva = Double
				.valueOf(ContextManager
						.getProperty("EXAMEN.PALANCA.ERRORES.PUNTOS.SIN.ACTIVAR.HASTA"));

		int puntosSinActivar = puntos - this.puntosActivados.size();

		if (this.errores > errores)
			return Examen.RESULTADO_FUERA;
		else if (this.tiempoTotal > erroresTiempo)
			return Examen.RESULTADO_FUERA;
		else if (puntosSinActivar > erroresPuntosSinActiva)
			return Examen.RESULTADO_FUERA;
		else
			return Examen.RESULTADO_DENTRO;
	}

	public void inicializar() {
		jLabel3.setVisible(false);

		finOK = false;
		runExamen = false;
		if (task != null)
			task.cancel(true);
		jProgress.setValue(0);
		errores = 0;
		resultados.clear();
		puntosActivados.clear();
		onError = false;
		tiempoTotal = 0;

		txtErrores.setText("0");
		txtTiempo.setText("0");

		this.repaint();
	}

	private void iniciarExamen() {
		if (thTrama.getTramaValida().isPalancaInInicio()) {

			inicializar();

			btnCancelar.setEnabled(true);
			btnAprendizaje.setEnabled(false);
			btnExaminar.setEnabled(false);
			btnExaminarN.setEnabled(false);
			btnGuardar.setEnabled(false);

			unSelectButtons(btnAprendizaje);
			runExamen = true;
			iniciarProgressBar();

			this.validate();
		} else {
			JOptionPaneTesterGral.showInternalMessageDialog(
					"La palanca debe estar en la posición inicial",
					"Iniciar Examen", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void iniciarProgressBar() {
		if (task != null)
			task.cancel(true);

		task = new Task();
		task.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if ("progress" == evt.getPropertyName()) {

					int progress = (Integer) evt.getNewValue();
					jProgress.setValue(progress);
				}
			}
		});
		task.execute();
	}

	public void habilitarBotones() {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				btnCancelar.setEnabled(false);
				btnAprendizaje.setEnabled(true);
				btnExaminar.setEnabled((personaExamen.getPersona() != null)
						&& !btnExam);
				btnExaminarN.setEnabled((personaExamen.getPersona() != null)
						&& btnExam);
			}
		});

	}

	@Override
	public void finalizar() {

		run = false;

		if (task != null)
			task.cancel(true);

		if (thTrama != null) {
			thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LAZER);
			thTrama.desconnect();
		}

	}

	class Task extends SwingWorker {
		/*
		 * Main task. Executed in background thread.
		 */

		@Override
		public Void doInBackground() {

			int tiempoDormir = tiempo / 100;

			int progress = 0;
			setProgress(0);

			while (!this.isDone() && progress < 100) {
				//Sleep for up to one second.
				try {
					Thread.sleep(tiempoDormir);
					progress++;
				} catch (InterruptedException ignore) {
				}

				setProgress(Math.min(progress, 100));
			}

			if (progress >= 100)
				finOK = true;
			return null;
		}

		/*
		 * Executed in event dispatching thread
		 */
		@Override
		public void done() {

			finalizarExamen();
		}
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JToggleButton btnAprendizaje;
	private javax.swing.JToggleButton btnCancelar;
	private javax.swing.JToggleButton btnExaminar;
	private javax.swing.JToggleButton btnExaminarN;
	private javax.swing.JToggleButton btnGuardar;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JProgressBar jProgress;
	private javax.swing.JPanel panelAcciones;
	private javax.swing.JPanel panelUsuario;
	private javax.swing.JTextField txtErrores;
	private javax.swing.JTextField txtTiempo;
	// End of variables declaration//GEN-END:variables

	private ExamenDetalle exaDetalle;
	private ThreadTrama thTrama;//Thread

	private List<Resultado> resultados = new ArrayList<Resultado>();
	private PersonaExamen personaExamen;
	private boolean btnExam = false;
	private boolean demo;
	private boolean run = true;
	private boolean onError = false;
	private boolean runExamen = false;

	private static final Log log = LogFactory.getLog(PanelPalanca.class);
	//private JInternalFrameTesterGral internalFrame;
	private TramaPsicologico tramaPsicologico = new TramaPsicologico();
	private JToggleButton btn;
	private Task task;
	private List<Integer> puntosActivados = new LinkedList<Integer>();
	private int errores = 0;
	private long tiempoIniError = 0;
	private boolean finOK = false;
	private Thread th = new Thread(this);
	private long tiempoTotal = 0;
	private int puntos = Integer.valueOf(ContextManager
			.getProperty("EXAMEN.PALANCA.PUNTOS"));
	private int tiempo = Integer.valueOf(ContextManager
			.getProperty("EXAMEN.PALANCA.TIEMPO.DURACION.HASTA"));
	private BufferedImage buffImg = new BufferedImage(450, 630,BufferedImage.TYPE_INT_ARGB);
	private Graphics2D g2d = (Graphics2D) buffImg.getGraphics();
	private Image img;
	
}