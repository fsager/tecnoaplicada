/*
 * PanelPercepcionReaccion.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.psicometrico.reaccionsimple;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Accion;
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
import examenes.util.ExamenesUtils;
import frontend.components.JOptionPaneTesterGral;
import frontend.paneles.examenes.Finalisable;
import frontend.paneles.examenes.PanelResultado;
import frontend.tablemodel.TableModelResultado;
import frontend.utils.Util;
import frontend.ventanas.FrameSecundario;
import frontend.ventanas.JInternalFrameTesterGral;

/**
 *
 * @author  __USER__
 */
public class PanelReaccionSimple extends javax.swing.JPanel implements
		Finalisable {

	/** Creates new form PanelPercepcionReaccion */
	public PanelReaccionSimple(JToggleButton btn, PersonaExamen personaExamen) {
		this.btn = btn;
		initComponents();

		try {
			ExamenDetalleDefinition examenDetalleService = (ExamenDetalleDefinition) ContextManager
					.getBizObject("examenDetalleService");
			exaDetalle = new ExamenDetalle();
			exaDetalle
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_REAC_SIMPLE);
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
		mostrarSecondMonitor();

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inicializarThreads();
				} catch (ExceptionIsNotHadware e) {
					JOptionPaneTesterGral.showInternalMessageDialog(e
							.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnCancelar.setVisible(false);
	}

	public void ciclo(int etapas) {

		etapaActual = 0;
		resultados = new ArrayList<Resultado>();

		mtAccionHardLst = new ArrayList<Accion>();
		mtAccionSoftLst = new ArrayList<Accion>();

		for (int i = 0; i < etapas; i++)
			agregarCiclo();

		Util.thTrama.setMtAccionHard(mtAccionHardLst);
		Util.thTrama.setMtAccionSoft(mtAccionSoftLst);
		Util.thTrama.setEjecucion(99999);
	}

	public void agregarCiclo() {
		Resultado res = new Resultado();
		res.setResEtapa(Long.valueOf(resultados.size()));
		resultados.add(res);

		actionArrancarSemaforo();
		actionValidarAceleracion();
		actionFrenar();
	}

	public void repaintPanelesAnimacion() {
		if (panelReaccionSimpleAnimacion != null)
			panelReaccionSimpleAnimacion.repaint();
	}

	public boolean isTrue() {
		return true;
	}

	@Override
	public void finalizar() {

		if (Util.frameSecundario != null) {
			internalFrame.dispose();
			Util.frameSecundario.repaint();
			Util.frameSecundario.validate();
		}

		/*if (Util.thTrama != null)
			Util.thTrama.desconnect();*/

		if (panelReaccionSimpleAnimacion != null)
			panelReaccionSimpleAnimacion.setStop(true);
		panelReaccionSimpleAnimacion = null;

		if (panelReaccionSimpleUsuarioExaminado != null)
			panelReaccionSimpleUsuarioExaminado.setRun(false);
		panelReaccionSimpleUsuarioExaminado = null;

		if (panelReaccionSimpleUsuarioExaminador != null)
			panelReaccionSimpleUsuarioExaminador.setRun(false);
		panelReaccionSimpleUsuarioExaminador = null;

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

	public void inicializarThreads() {
		if (Util.thTrama != null && !(Util.thTrama.getTrama() instanceof TramaPsicologico))
			Util.thTrama.desconnect();

		if (Util.thTrama == null) {
			try {
				ThreadTrama thTrama = new ThreadTrama(new TramaPsicologico());
				Util.thTrama = thTrama;
				thTrama.setEjecucion(99999);
				thTrama.start();
			} catch (ExceptionIsNotHadware e) {
				JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}		
		}
	}

	public ThreadTrama getThTrama() {
		return Util.thTrama;
	}

	public void mostrarSecondMonitor() {

		panelReaccionSimpleAnimacion = new PanelReaccionSimpleAnimacion(this);

		if (Util.frameSecundario != null) {

			int width = Integer.valueOf(ContextManager
					.getProperty("EXAMEN.TAMAÑO.PANTALLA.SECUNDARIA.WIDTH"));
			int heigth = Integer.valueOf(ContextManager
					.getProperty("EXAMEN.TAMAÑO.PANTALLA.SECUNDARIA.HEIGHT"));

			panelReaccionSimpleUsuarioExaminado = new PanelReaccionSimpleUsuario(
					panelReaccionSimpleAnimacion, false);

			panelReaccionSimpleUsuarioExaminado.setMinimumSize(new Dimension(
					width, heigth));
			panelReaccionSimpleUsuarioExaminado.setSize(width, heigth);
			panelReaccionSimpleUsuarioExaminado.setPreferredSize(new Dimension(
					width, heigth));

			panelReaccionSimpleUsuarioExaminado.validate();
			internalFrame = new JInternalFrameTesterGral();
			internalFrame.add(panelReaccionSimpleUsuarioExaminado);
			internalFrame.setVisible(true);

			Util.agregarIframeMonSecundario(((FrameSecundario)Util.frameSecundario).getDp(),internalFrame,btnCancelar.getActionListeners());

		}

		panelReaccionSimpleUsuarioExaminador = new PanelReaccionSimpleUsuario(
				panelReaccionSimpleAnimacion, (Util.frameSecundario != null));
		this.panelUsuario.add(panelReaccionSimpleUsuarioExaminador);

	}

	public void actionArrancarSemaforo() {
		try {

			if (panelReaccionSimpleAnimacion != null) {
				Method mtAccionHard = TramaPsicologico.class.getMethod(
						"isAceleradorPressed", null);
				Method mtAccionSoft = PanelReaccionSimple.class
						.getMethod("arrancarSemaforo");

				mtAccionHardLst.add(new Accion(mtAccionHard, Util.thTrama
						.getTramaValida(), null, null));
				mtAccionSoftLst.add(new Accion(mtAccionSoft, this, null, null));
			}

		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	public void actionValidarAceleracion() {
		try {

			if (panelReaccionSimpleAnimacion != null) {
				Method mtAccionHard = PanelReaccionSimple.class.getMethod(
						"isAceleretorNotPressedOrFrenoPressed", null);
				Method mtAccionSoft = PanelReaccionSimple.class
						.getMethod("setError");

				mtAccionHardLst.add(new Accion(mtAccionHard, this, null, null));
				mtAccionSoftLst.add(new Accion(mtAccionSoft, this, null, null));
			}

		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	public void actionFrenar() {
		try {

			if (panelReaccionSimpleAnimacion != null) {
				Method mtAccionHard = TramaPsicologico.class.getMethod(
						"isFrenoPressed", null);
				Method mtAccionSoft = PanelReaccionSimple.class
						.getMethod("frenar");

				mtAccionHardLst.add(new Accion(mtAccionHard, Util.thTrama
						.getTramaValida(), null, null));
				mtAccionSoftLst.add(new Accion(mtAccionSoft, this, null, null));
			}

		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	public void arrancarSemaforo() {
		setMensajes(null);
		if (panelReaccionSimpleAnimacion.getEstado().equals(
				PanelReaccionSimpleAnimacion.ESTADO_INI)
				|| panelReaccionSimpleAnimacion.getEstado().equals(
						PanelReaccionSimpleAnimacion.ESTADO_ROJO)) {
			panelReaccionSimpleAnimacion.setRun(true);
		} else {
			Util.thTrama.setEjecucion(Util.thTrama.getEjecucion() - 1);
		}
	}

	public boolean isAceleretorNotPressedOrFrenoPressed() {
		if (!Util.thTrama.getTramaValida().isAceleradorPressed()
				|| Util.thTrama.getTramaValida().isFrenoPressed())
			return true;

		return false;
	}

	public void incrementarEjecucion() {
		Util.thTrama.setEjecucion(Util.thTrama.getEjecucion() + 1);
	}

	public void frenar() throws InterruptedException {
		long tiempoActual = System.currentTimeMillis();
		double resEtapa = 0;
		if(configuracion.equals("PERU"))
		{
			resEtapa = ((tiempoActual - getInstanteSemaforoRojo()) * 0.70) / 10;
		}
		else
		{
			resEtapa = ((tiempoActual - getInstanteSemaforoRojo()) * 0.91) / 10;
		}
		
		
		//log.debug("Tiempo de Frenado: "+(tiempoActual - getInstanteSemaforoRojo())+" current: "+System.currentTimeMillis());

		//TODO comentar descomentar para pruebas edgardo
		//Util.thTrama.sendOrden(ThreadTrama.ORDEN_PRENDER_LED3);

		setMensajes(continuarAnimacion);
		Resultado res = resultados.get(etapaActual);
		res.setResValor1((double) resEtapa);
		if (res.getResValor2() == null)
			res.setResValor2(new Double(0));
		else
			res.setResValor1(0.0);

		etapaActual++;

		if (erroresCometidos >= erroresPermitidos
				|| etapaActual == resultados.size())
		{
			Util.thTrama.setEjecucion(99999);
			finalizarExamen();
		}

		panelReaccionSimpleAnimacion.init();
	}

	public void setError() throws InterruptedException {
		panelReaccionSimpleAnimacion.setRun(false);
		resultados.get(etapaActual).setResValor2(new Double(1));
		erroresCometidos++;

		Util.thTrama.setEjecutar(false);
		panelReaccionSimpleAnimacion.interrupt();
		panelReaccionSimpleAnimacion.setKo();
		panelReaccionSimpleAnimacion.repaint();
		Thread t1 = new Thread() {
			public void run() {
				try {
					this.sleep(2000);
					Util.thTrama.setEjecutar(true);
					panelReaccionSimpleAnimacion.setKoToNull();
					panelReaccionSimpleAnimacion.repaint();
				} catch (Exception e) {
				}
			}
		};
		t1.start();
		Util.playSound(Constantes.SOUND_ERROR, 100);
		frenar();

		Util.thTrama.setEjecucion(Util.thTrama.getEjecucion() + 1);

		agregarCiclo();
	}

	public long getInstanteSemaforoRojo() {
		return this.instanteSemaforoRojo;
	}

	public void setInstanteSemaforoRojo(long instanteSemaforoRojo) {
		//log.debug("Semaforo en rojo: "+instanteSemaforoRojo);
		this.instanteSemaforoRojo = instanteSemaforoRojo;
	}

	public void finalizarExamen() {
		setMensajes(null);
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				btnGuardar.setEnabled((personaExamen.getPersona() != null)
						&& !demo && true);
				Util.playSound(Constantes.SOUND_START, 0);
				try {
					Thread.sleep(Constantes.TIEMPO_ENTRE_RESULTADO);
				} catch (InterruptedException e) {

				}
				String columns[] = { "Etapa", "Resultado" };
				promediar(resultados, 3);

				panelReaccionSimpleUsuarioExaminador.getPanelContenido()
						.removeAll();
				PanelResultado panelResultado = new PanelResultado(resultados,
						TableModelResultado.ERRORES_Y_RESULTADO, exaDetalle,personaExamen.getPexaTipoExamen());
				panelReaccionSimpleUsuarioExaminador.getPanelContenido().add(
						panelResultado);
				panelReaccionSimpleUsuarioExaminador.validate();
				panelReaccionSimpleUsuarioExaminador.repaint();

				String examenResultadosExaminado = ContextManager
						.getProperty("EXAMEN.RESULTADOS.EXAMADO");
				if (examenResultadosExaminado.equals("S")) {
					if (panelReaccionSimpleUsuarioExaminado != null) {
						panelReaccionSimpleUsuarioExaminado.getPanelContenido()
								.removeAll();
						panelResultado = new PanelResultado(resultados,
								TableModelResultado.ERRORES_Y_RESULTADO,
								exaDetalle,personaExamen.getPexaTipoExamen());
						panelReaccionSimpleUsuarioExaminado.getPanelContenido()
								.add(panelResultado);
						panelReaccionSimpleUsuarioExaminado.validate();
						panelReaccionSimpleUsuarioExaminado.repaint();
					}
				}
				Util.frameSecundario.setVisible(false);
			}
		});

		habilitarBotones();
		this.validate();
	}

	public void initPanelesAnimacion() {
		panelReaccionSimpleAnimacion.init();
		if (panelReaccionSimpleUsuarioExaminado != null)
			panelReaccionSimpleUsuarioExaminado.init();

		if (panelReaccionSimpleUsuarioExaminador != null)
			panelReaccionSimpleUsuarioExaminador.init();
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
										.addComponent(btnAprendizaje)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
										.addContainerGap(44, Short.MAX_VALUE)));
		panelAccionesLayout
				.setVerticalGroup(panelAccionesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								panelAccionesLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(btnAprendizaje)
										.addComponent(btnExaminarN)
										.addComponent(btnGuardar)
										.addComponent(btnCancelar)
										.addComponent(
												btnExaminar,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												25,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		panelUsuario.setLayout(new java.awt.GridLayout(1, 0));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				panelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 625,
				Short.MAX_VALUE).addComponent(panelAcciones,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
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
										.addComponent(
												panelUsuario,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												239, Short.MAX_VALUE)));
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
		Util.frameSecundario.setVisible(false);
		erroresCometidos = 0;
		setMensajes(null);
		panelReaccionSimpleAnimacion.init();
		Util.thTrama.setEjecucion(9999);
		habilitarBotones();
	}

	private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
		unSelectButtons(null);

		ResultadoDetalleExamenDefinition resultadoDetalleExamenService = (ResultadoDetalleExamenDefinition) ContextManager
				.getBizObject("resultadoDetalleExamenService");
		PersonaExamenDefinition personaExamenService = (PersonaExamenDefinition) ContextManager
				.getBizObject("personaExamenService");

		try {

			if (personaExamen != null && personaExamen.getPexaId() == null) {
				personaExamen.setPexaEstado(Examen.ESTADO_INI);
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

			String detalleResultado = "<HTML><div width=\"200px\">";
			Set resultados = resultadoDetalleExamen.getResultados();
			resultados.clear();
			for (int i = 0; i < this.resultados.size(); i++) {
				this.resultados.get(i).setResultadoDetalleExamen(
						resultadoDetalleExamen);
				resultados.add(this.resultados.get(i));
				if(this.resultados.get(i).getResValor1()!=null)
				{
					detalleResultado = detalleResultado
					+ Util.redondear(this.resultados.get(i).getResValor1())+ "/ ";
				}
			}

			Double pro[] = ExamenesUtils.calcularPromedio(resultados);
			String resultado = ExamenesUtils.detalleExamenResultado(exaDetalle,
					resultados,configuracion,personaExamen.getPexaTipoExamen());
			resultadoDetalleExamen.setRdeNota(pro[0]);
			resultadoDetalleExamen.setRdeNota2(pro[1]);
			
			
			detalleResultado=detalleResultado+"<BR>Tiempo promedio: "
				+ pro[0].intValue()
				+ " Centésimas de segundos.<BR> Errores: "
				+ pro[1].intValue() + ".</HTML>";
			resultadoDetalleExamen
					.setRdeDetalleResultado(detalleResultado);			
			

			resultadoDetalleExamen.setRdeParametrosCorrecion(exaDetalle
					.getExadParametrosCorrecion());
			resultadoDetalleExamen.setRdeResultado(resultado);
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

	public void promediar(Collection<Resultado> resultados, int last) {
		List<Resultado> lstResultados = (List) resultados;
		List<Resultado> lstResultadosAux = new ArrayList();

		for (int i = lstResultados.size() - 1; i >= 0; i--) {
			Resultado res = lstResultados.get(i);
			if (res.getResValor1() != null
					&& res.getResValor1().intValue() != 0) {
				lstResultadosAux.add(res);
			}
		}

		Comparator<Resultado> com = new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				if (o1 == null && o2 == null)
					return 0;
				else if (o1 == null)
					return -1;
				else if (o2 == null)
					return 1;
				else {
					Resultado res1 = (Resultado) o1;
					Resultado res2 = (Resultado) o2;

					if (res1.getResValor1() == null
							|| res2.getResValor1() == null)
						return 0;

					return res1.getResValor1().compareTo(res2.getResValor1());
				}
			}
		};

		if (lstResultadosAux.size() > 0) {
			Resultado minRes = Collections.min(lstResultadosAux, com);
			System.out.println("minRes.getResValor1(): "
					+ minRes.getResValor1());
			for (int i = 0; i < last && i < lstResultadosAux.size(); i++) {
				double procentaje = 1d + ((i + 5) / 100d);
				Double newValue = minRes.getResValor1() * procentaje;
				Resultado res = Collections.max(lstResultadosAux, com);
				lstResultadosAux.remove(res);
				res.setResValor1(newValue);
				System.out.println("res.getResValor1(): " + res.getResValor1());
			}
		}
	}

	private void btnExaminarNActionPerformed(final java.awt.event.ActionEvent evt) {
		btnCancelarActionPerformed(null);
		Util.frameSecundario.setVisible(true);
		initPanelesAnimacion();
		demo = false;
		btnExam = true;
		
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try
				{
					Thread.currentThread().sleep(1000);
					examinar((JToggleButton) evt.getSource(), true);
				}
				catch(Exception e)
				{
					throw new RuntimeException(e);
				}
			}
		});
		
	}

	private void btnExaminarActionPerformed(final java.awt.event.ActionEvent evt) {
		btnCancelarActionPerformed(null);
		Util.frameSecundario.setVisible(true);
		initPanelesAnimacion();
		demo = false;
		btnExam = true;
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try
				{
					Thread.currentThread().sleep(1000);
					examinar((JToggleButton) evt.getSource(), true);
				}
				catch(Exception e)
				{
					throw new RuntimeException(e);
				}
			}
		});
		
	}

	public void examinar(JToggleButton btn, boolean examen) {
		Util.playSound(Constantes.SOUND_START, 0);
		erroresCometidos = 0;
		setMensajes(iniciarAnimacion);
		unSelectButtons(btn);
		btnCancelar.setEnabled(true);
		btnAprendizaje.setEnabled(false);
		btnExaminar.setEnabled(false);
		btnExaminarN.setEnabled(false);
		btnGuardar.setEnabled(false);

		if (examen)
			ciclo(ETAPAS_EXAMEN);
		else
			ciclo(ETAPAS);

		panelReaccionSimpleAnimacion.repaint();
		Util.thTrama.setEjecucion(0);
	}

	private void btnAprendizajeActionPerformed(final java.awt.event.ActionEvent evt) {
		btnCancelarActionPerformed(null);
		Util.frameSecundario.setVisible(true);
		
		initPanelesAnimacion();
		demo = true;
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try
				{
					Thread.currentThread().sleep(1000);
					examinar((JToggleButton) evt.getSource(), false);
				}
				catch(Exception e)
				{
					throw new RuntimeException(e);
				}
			}
		});
		
	}

	public void setMensajes(String mensaje) {
		if (panelReaccionSimpleUsuarioExaminado != null) {
			panelReaccionSimpleUsuarioExaminado.getTxtMensajes().setVisible(
					(mensaje != null));
			panelReaccionSimpleUsuarioExaminado.getTxtMensajes().setText(
					mensaje);
			panelReaccionSimpleUsuarioExaminado.validate();
		}
		if (panelReaccionSimpleUsuarioExaminador != null) {
			panelReaccionSimpleUsuarioExaminador.getTxtMensajes().setVisible(
					(mensaje != null));
			panelReaccionSimpleUsuarioExaminador.getTxtMensajes().setText(
					mensaje);
			panelReaccionSimpleUsuarioExaminador.validate();
		}
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JToggleButton btnAprendizaje;
	private javax.swing.JToggleButton btnCancelar;
	private javax.swing.JToggleButton btnExaminar;
	private javax.swing.JToggleButton btnExaminarN;
	private javax.swing.JToggleButton btnGuardar;
	private javax.swing.JPanel panelAcciones;
	private javax.swing.JPanel panelUsuario;
	// End of variables declaration//GEN-END:variables
	private PanelReaccionSimpleAnimacion panelReaccionSimpleAnimacion;//Thread
	private PanelReaccionSimpleUsuario panelReaccionSimpleUsuarioExaminador;//Thread
	private PanelReaccionSimpleUsuario panelReaccionSimpleUsuarioExaminado;
	private ExamenDetalle exaDetalle;
	//private TramaPsicologico tramaPsicologico = new TramaPsicologico();
	//private ThreadTrama thTrama;//Thread
	//private ArrayList<ParametrosPercepcionReacion> parametros;

	private List<Accion> mtAccionHardLst = new ArrayList<Accion>();
	private List<Accion> mtAccionSoftLst = new ArrayList<Accion>();
	private List<Resultado> resultados = new ArrayList<Resultado>();

	private PersonaExamen personaExamen;

	private static final Log log = LogFactory.getLog(PanelReaccionSimple.class);
	private final int ETAPAS = Integer.valueOf(ContextManager
			.getProperty("EXAMEN.REACCION.SIMPLE.ETAPAS.APRENDIZAJE"));
	private final int ETAPAS_EXAMEN = Integer.valueOf(ContextManager
			.getProperty("EXAMEN.REACCION.SIMPLE.ETAPAS.EXAMEN"));

	//private long tiempInicioEtapa = 0;
	private boolean demo;
	private boolean btnExam = false;
	private JInternalFrameTesterGral internalFrame;
	private JToggleButton btn;
	private int etapaActual = 0;
	private long instanteSemaforoRojo;
	private String iniciarAnimacion = "Presionar Acelerador para iniciar"
			.toUpperCase();
	private String continuarAnimacion = "Presionar Acelerador para continuar"
			.toUpperCase();
	private int erroresPermitidos = Integer.valueOf(ContextManager
			.getProperty("EXAMEN.REACCION.SIMPLE.ERRORES.PERMITIDOS.HASTA"));
	private int erroresCometidos = 0;
	private String configuracion=ContextManager.getProperty("PARAMETROS.CONFIGURACION");

}