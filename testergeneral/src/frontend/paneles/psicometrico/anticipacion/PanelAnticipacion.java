/*
 * PanelAnticipacion.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.psicometrico.anticipacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Accion;
import testerGeneral.domain.Constantes;
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
import frontend.paneles.examenes.PanelExamen;
import frontend.paneles.examenes.PanelResultado;
import frontend.tablemodel.TableModelResultado;
import frontend.utils.Util;
import frontend.ventanas.FrameSecundario;
import frontend.ventanas.JInternalFrameTesterGral;
import frontend.ventanas.VtnConfigurarDb;

/**
 *
 * @author  __USER__
 */
public class PanelAnticipacion extends javax.swing.JPanel implements
		Finalisable,PanelExamen {

	/** Creates new form PanelAnticipacion */

	public PanelAnticipacion(JToggleButton btn,PersonaExamen personaExamen) {
		this.btn=btn;
		initComponents();

		try {
			ExamenDetalleDefinition examenDetalleService = (ExamenDetalleDefinition) ContextManager
					.getBizObject("examenDetalleService");
			exaDetalle = new ExamenDetalle();
			exaDetalle
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_CTR_TEMPORO);
			exaDetalle = (ExamenDetalle) examenDetalleService
					.getAll(exaDetalle).get(0);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		this.personaExamen = personaExamen;
		demo=(personaExamen.getPersona() == null);
		if (personaExamen.getPersona() == null) {
			btnExaminar.setEnabled(false);
			btnExaminarN.setEnabled(false);
			btnGuardar.setEnabled(false);
		}

		btnCancelar.setVisible(false);
		
		this.validate();
		inicializarThreads();
		mostrarSecondMonitor();
		repaintPanelesAnimacion();
					
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
	
	
	public void finalizarExamen()
	{	
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				btnGuardar.setEnabled((personaExamen.getPersona() != null) && !demo && true);
				Util.playSound(Constantes.SOUND_START,100);
				try {
					Thread.sleep(Constantes.TIEMPO_ENTRE_RESULTADO);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String columns[]={"Etapa","Resultado"};
				

				panelAnticipacionUsuarioExaminador.getPanelContenido().removeAll();
				PanelResultado panelResultado=new PanelResultado(resultados,TableModelResultado.SOLO_RESULTADO,exaDetalle);
				panelAnticipacionUsuarioExaminador.getPanelContenido().add(panelResultado);
				panelAnticipacionUsuarioExaminador.validate();
				panelAnticipacionUsuarioExaminador.repaint();
				
				String examenResultadosExaminado=ContextManager.getProperty("EXAMEN.RESULTADOS.EXAMADO"); 
				if(examenResultadosExaminado.equals("S"))
				{
					if (panelAnticipacionUsuarioExaminado != null)
					{
						panelAnticipacionUsuarioExaminado.getPanelContenido().removeAll();
						panelResultado=new PanelResultado(resultados,TableModelResultado.SOLO_RESULTADO,exaDetalle);
						panelAnticipacionUsuarioExaminado.getPanelContenido().add(panelResultado);
						panelAnticipacionUsuarioExaminado.validate();
						panelAnticipacionUsuarioExaminado.repaint();						
					}
				}
				
				Util.frameSecundario.setVisible(false);
			}
		});
		
		habilitarBotones();
	}

	public void cicloAprendizaje() {

		mtAccionHardLst = new ArrayList<Accion>();
		mtAccionSoftLst = new ArrayList<Accion>();
		
		resultados = new ArrayList<Resultado>();

		for (int i = 0; i < 2; i++) {
			Resultado res = new Resultado();
			res.setResEtapa(Long.valueOf(i));
			resultados.add(res);
		}
		
		
		//Frena cuando se aprieta el freno
		accionFrenar();
		
		accionIniciarEtapaDos();

		//Frena cuando se aprieta el freno
		accionFrenar();
		
		accionResultados();

		Util.thTrama.setMtAccionHard(mtAccionHardLst);
		Util.thTrama.setMtAccionSoft(mtAccionSoftLst);
		Util.thTrama.setEjecucion(99999);

	}

	public void cicloExamen() {

		mtAccionHardLst = new ArrayList<Accion>();
		mtAccionSoftLst = new ArrayList<Accion>();

		resultados = new ArrayList<Resultado>();

		for (int i = 0; i < ETAPAS; i++) {
			Resultado res = new Resultado();
			res.setResEtapa(Long.valueOf(i));
			resultados.add(res);
		}
		//ETAPA1
		//Frena cuando se aprieta el freno
		accionFrenar();
		
		//ETAPA2
		accionIniciarEtapaDos();

		//Frena cuando se aprieta el freno
		accionFrenar();

		
		//ETAPA3
		accionIniciarEtapaTres();

		//Frena cuando se aprieta el freno
		accionFrenar();
		
		//ETAPA4
		accionIniciarEtapaCuatro();

		//Frena cuando se aprieta el freno
		accionFrenar();
		
		//ETAPA5
		accionIniciarEtapaCinco();

		//Frena cuando se aprieta el freno
		accionFrenar();
		
		//ETAPA6
		accionIniciarEtapaSeis();

		//Frena cuando se aprieta el freno
		accionFrenar();

		Util.thTrama.setMtAccionHard(mtAccionHardLst);
		Util.thTrama.setMtAccionSoft(mtAccionSoftLst);
		Util.thTrama.setEjecucion(99999);
	}

	public void accionFrenar() {
		try {
			if (panelAnticipacionAnimacion != null) {
				Method mtAccionHard = TramaPsicologico.class.getMethod(
						"isFrenoPressed", null);
				Method mtAccionSoft = PanelAnticipacionAnimacion.class
						.getMethod("setMostrarParedFalse", null);

				mtAccionHardLst.add(new Accion(mtAccionHard, Util.thTrama.getTramaValida(),
						null, null));
				mtAccionSoftLst.add(new Accion(mtAccionSoft,
						panelAnticipacionAnimacion, null, null));

				mtAccionHard = PanelAnticipacion.class
						.getMethod("isTrue", null);
				mtAccionSoft = PanelAnticipacionAnimacion.class.getMethod(
						"frenar", null);
				
				mtAccionHardLst.add(new Accion(mtAccionHard, this, null, null));
				mtAccionSoftLst.add(new Accion(mtAccionSoft,
						panelAnticipacionAnimacion, null, null));
				
				mtAccionHard = PanelAnticipacion.class
				.getMethod("isTrue", null);
				mtAccionSoft = PanelAnticipacion.class.getMethod(
				"esperar", null);
		
				mtAccionHardLst.add(new Accion(mtAccionHard, this, null, null));
				mtAccionSoftLst.add(new Accion(mtAccionSoft,this, null, null));
			}

		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (ExceptionIsNotHadware e) {
			JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void esperar()
	{
		Util.thTrama.setEjecutar(false);
		Thread t1=new Thread()
		{
			public void run() {
				try
				{
					this.sleep(2500);//1500
					Util.thTrama.setEjecutar(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		t1.start();
		
	}

	public void accionIniciarEtapaDos() {
		try {
			Method mtAccionHard = PanelAnticipacion.class.getMethod("isTrue",
					null);
			Method mtAccionSoft = PanelAnticipacion.class.getMethod("etapaDos",
					null);

			mtAccionHardLst.add(new Accion(mtAccionHard, this, null, null));
			mtAccionSoftLst.add(new Accion(mtAccionSoft, this, null, null));

		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (ExceptionIsNotHadware e) {
			JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void accionIniciarEtapaTres() {
		try {
			Method mtAccionHard = PanelAnticipacion.class.getMethod("isTrue",
					null);
			Method mtAccionSoft = PanelAnticipacion.class.getMethod(
					"etapaTres", null);

			mtAccionHardLst.add(new Accion(mtAccionHard, this, null, null));
			mtAccionSoftLst.add(new Accion(mtAccionSoft, this, null, null));

		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (ExceptionIsNotHadware e) {
			JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void accionIniciarEtapaCuatro() {
		try {
			Method mtAccionHard = PanelAnticipacion.class.getMethod("isTrue",
					null);
			Method mtAccionSoft = PanelAnticipacion.class.getMethod(
					"etapaCuatro", null);

			mtAccionHardLst.add(new Accion(mtAccionHard, this, null, null));
			mtAccionSoftLst.add(new Accion(mtAccionSoft, this, null, null));

		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (ExceptionIsNotHadware e) {
			JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void accionIniciarEtapaCinco() {
		try {
			Method mtAccionHard = PanelAnticipacion.class.getMethod("isTrue",
					null);
			Method mtAccionSoft = PanelAnticipacion.class.getMethod(
					"etapaCinco", null);

			mtAccionHardLst.add(new Accion(mtAccionHard, this, null, null));
			mtAccionSoftLst.add(new Accion(mtAccionSoft, this, null, null));

		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (ExceptionIsNotHadware e) {
			JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void accionIniciarEtapaSeis() {
		try {
			Method mtAccionHard = PanelAnticipacion.class.getMethod("isTrue",
					null);
			Method mtAccionSoft = PanelAnticipacion.class.getMethod(
					"etapaSeis", null);

			mtAccionHardLst.add(new Accion(mtAccionHard, this, null, null));
			mtAccionSoftLst.add(new Accion(mtAccionSoft, this, null, null));

		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (ExceptionIsNotHadware e) {
			JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void accionResultados() {
		/*try {
			Method mtAccionHard = PanelAnticipacion.class.getMethod("isTrue",
					null);
			Method mtAccionSoft = PanelAnticipacion.class.getMethod(
					"etapaSeis", null);

			mtAccionHardLst.add(new Accion(mtAccionHard, this, null, null));
			mtAccionSoftLst.add(new Accion(mtAccionSoft, this, null, null));

		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (ExceptionIsNotHadware e) {
			Util.showInternalMessageDialog(e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}*/
	}

	public void inicializarThreads() {
		
		if (Util.thTrama != null && !(Util.thTrama.getTrama() instanceof TramaPsicologico))
			Util.thTrama.desconnect();

		if (Util.thTrama == null) {
			try
			{
			ThreadTrama thTrama = new ThreadTrama(new TramaPsicologico());
			Util.thTrama = thTrama;
			thTrama.setEjecucion(99999);
			thTrama.start();
			}
			catch (ExceptionIsNotHadware e) {
				JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void mostrarSecondMonitor() {

		if (Util.frameSecundario != null) {
			int width = Integer.valueOf(ContextManager
					.getProperty("EXAMEN.TAMAÑO.PANTALLA.SECUNDARIA.WIDTH"));
			int heigth = Integer.valueOf(ContextManager
					.getProperty("EXAMEN.TAMAÑO.PANTALLA.SECUNDARIA.HEIGHT"));

			panelAnticipacionAnimacion = new PanelAnticipacionAnimacion(this,
					new Dimension(width, heigth),Util.thTrama);

			panelAnticipacionUsuarioExaminado = new PanelAnticipacionUsuario(
					panelAnticipacionAnimacion, false);
			
			internalFrame=new JInternalFrameTesterGral();
			internalFrame.add(panelAnticipacionUsuarioExaminado);
			internalFrame.setVisible(true);
			
			
			Util.agregarIframeMonSecundario(((FrameSecundario)Util.frameSecundario).getDp(),internalFrame,btnCancelar.getActionListeners());
			


		} /*else {

			panelAnticipacionAnimacion = new PanelAnticipacionAnimacion(this,
					new Dimension(813, 320),Util.thTrama);
		}*/

		panelAnticipacionUsuarioExaminador = new PanelAnticipacionUsuario(
				panelAnticipacionAnimacion, (Util.frameSecundario != null));
		this.panelUsuario.add(panelAnticipacionUsuarioExaminador);

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
										.addContainerGap(114, Short.MAX_VALUE)));
		panelAccionesLayout.setVerticalGroup(panelAccionesLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						panelAccionesLayout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnAprendizaje).addComponent(
										btnExaminar).addComponent(btnExaminarN)
								.addComponent(btnGuardar).addComponent(
										btnCancelar)));

		panelUsuario.setLayout(new java.awt.GridLayout(1, 0));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				panelAcciones, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panelUsuario,
						javax.swing.GroupLayout.DEFAULT_SIZE, 695,
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
										.addComponent(
												panelUsuario,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												451, Short.MAX_VALUE)
										.addContainerGap()));
	}// </editor-fold>
	//GEN-END:initComponents

	
	private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
		
		panelAnticipacionAnimacion.ajustarTamañoAuto(false);
		panelAnticipacionAnimacion.initParamtros(false, Integer.valueOf(ContextManager
				.getProperty("EXAMEN.ANTICIPACION.APRENDIZAJE.SPEED")),true,false,null);
		
		Util.thTrama.setEjecucion(9999);
		habilitarBotones();
		Util.frameSecundario.setVisible(false);
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

			Set resultados = resultadoDetalleExamen.getResultados();
			resultados.clear();
			for (int i = 0; i < this.resultados.size(); i++) {
				this.resultados.get(i).setResultadoDetalleExamen(
						resultadoDetalleExamen);
				resultados.add(this.resultados.get(i));
			}

			Double pro[]=ExamenesUtils.calcularPromedio(resultados);
			String resultado=ExamenesUtils.detalleExamenResultado(exaDetalle,resultados);
			resultadoDetalleExamen.setRdeNota(pro[0]);
			resultadoDetalleExamen.setRdeDetalleResultado("Metros promedio: "+pro[0]+".");
			resultadoDetalleExamen.setRdeParametrosCorrecion(exaDetalle.getExadParametrosCorrecion());
			resultadoDetalleExamen.setRdeResultado(resultado);
			resultadoDetalleExamenService.update(resultadoDetalleExamen);

			btn.setForeground(Color.BLACK);
			Util.setIcon(btn,Constantes.IMG_ACEPTAR_SMALL);
			
			JOptionPaneTesterGral.showInternalMessageDialog(Constantes.MENSAJE_GUARDADO,
					Constantes.MENSAJE_GUARDADO_TIT,
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void btnExaminarNActionPerformed(java.awt.event.ActionEvent evt) {
		btnCancelarActionPerformed(null);
		initPanelesAnimacion();
		btnExam=true;
		demo=false;
		unSelectButtons(btnExaminarN);
		Util.frameSecundario.setVisible(true);
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try
				{
					Thread.currentThread().sleep(1000);
					examinar();
				}
				catch(Exception e)
				{
					throw new RuntimeException(e);
				}
			}
		});
	}

	private void btnExaminarActionPerformed(java.awt.event.ActionEvent evt) {
		btnCancelarActionPerformed(null);
		initPanelesAnimacion();
		unSelectButtons(btnExaminar);
		demo=false;
		btnExam=true;
		Util.frameSecundario.setVisible(true);
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try
				{
					Thread.currentThread().sleep(1000);
					examinar();
				}
				catch(Exception e)
				{
					throw new RuntimeException(e);
				}
			}
		});
		
		
	}

	public void examinar(){
		
		btnGuardar.setEnabled(false);
		cicloExamen();
		int speed = Integer.valueOf(ContextManager
				.getProperty("EXAMEN.ANTICIPACION.ETAPA1.SPEED"));
		
		iniciarExamen(-1 * speed, false, false, false, resultados.get(0));
		
	}

	public void etapaDos() {

		int speed = Integer.valueOf(ContextManager
				.getProperty("EXAMEN.ANTICIPACION.ETAPA2.SPEED"));

		panelAnticipacionAnimacion.initParamtros(true, speed, false, false,
				resultados.get(1));
		panelAnticipacionAnimacion.ajustarTamañoAuto(true);
		panelAnticipacionAnimacion.iniciar();
		repaintPanelesAnimacion();
	}

	public void etapaTres() {

		int speed = Integer.valueOf(ContextManager
				.getProperty("EXAMEN.ANTICIPACION.ETAPA3.SPEED"));

		panelAnticipacionAnimacion.initParamtros(false, -1 * speed, false,
				false, resultados.get(2));
		panelAnticipacionAnimacion.ajustarTamañoAuto(false);
		panelAnticipacionAnimacion.iniciar();
		repaintPanelesAnimacion();
	}

	public void etapaCuatro() {

		int speed = Integer.valueOf(ContextManager
				.getProperty("EXAMEN.ANTICIPACION.ETAPA4.SPEED"));

		panelAnticipacionAnimacion.initParamtros(true, speed, false, false,
				resultados.get(3));
		panelAnticipacionAnimacion.ajustarTamañoAuto(true);
		panelAnticipacionAnimacion.iniciar();
		repaintPanelesAnimacion();
	}

	public void etapaCinco() {

		int speed = Integer.valueOf(ContextManager
				.getProperty("EXAMEN.ANTICIPACION.ETAPA5.SPEED"));

		panelAnticipacionAnimacion.initParamtros(false, -1 * speed, false,
				false, resultados.get(4));
		panelAnticipacionAnimacion.ajustarTamañoAuto(false);
		panelAnticipacionAnimacion.iniciar();
		repaintPanelesAnimacion();
	}

	public void etapaSeis() {

		int speed = Integer.valueOf(ContextManager
				.getProperty("EXAMEN.ANTICIPACION.ETAPA6.SPEED"));

		panelAnticipacionAnimacion.initParamtros(true, speed, false, true,
				resultados.get(5));
		panelAnticipacionAnimacion.ajustarTamañoAuto(true);
		panelAnticipacionAnimacion.iniciar();
		repaintPanelesAnimacion();
	}

	public void initPanelesAnimacion()
	{
		if(panelAnticipacionUsuarioExaminador!=null)
			panelAnticipacionUsuarioExaminador.init();
			
		if(panelAnticipacionUsuarioExaminado!=null)
			panelAnticipacionUsuarioExaminado.init();
	}
	

	
	private void btnAprendizajeActionPerformed(java.awt.event.ActionEvent evt) {
		btnCancelarActionPerformed(null);
		initPanelesAnimacion();				
		demo=true;
		unSelectButtons(btnAprendizaje);
		cicloAprendizaje();
		Util.frameSecundario.setVisible(true);
		
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				btnAprendizajeAction();
			}
		});

	}
	
	public void btnAprendizajeAction()
	{
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try
				{
					Thread.currentThread().sleep(1000);
					int speed = Integer.valueOf(ContextManager
							.getProperty("EXAMEN.ANTICIPACION.APRENDIZAJE.SPEED"));
					iniciarExamen(-1 * speed, false, true, false, resultados.get(0));

				}
				catch(Exception e)
				{
					throw new RuntimeException(e);
				}
			}
		});
	}

	public void iniciarExamen(int speed, boolean izq, boolean demo,
			boolean save, Resultado res) {
		Util.playSound(Constantes.SOUND_START,100);
		btnCancelar.setEnabled(true);
		btnAprendizaje.setEnabled(false);
		btnExaminar.setEnabled(false);
		btnExaminarN.setEnabled(false);
		btnGuardar.setEnabled(false);
		
		Util.thTrama.setEjecucion(0);
		panelAnticipacionAnimacion.initParamtros(izq, speed, demo, save, res);
		panelAnticipacionAnimacion.ajustarTamañoAuto(izq);
		panelAnticipacionAnimacion.iniciar();
		repaintPanelesAnimacion();
		this.validate();
	}

	public void repaintPanelesAnimacion() {

		if (panelAnticipacionAnimacion != null)
			panelAnticipacionAnimacion.repaint();
	}

	public boolean isTrue() {
		
		if(Util.thTrama.getEjecucion()+1>=Util.thTrama.getMtAccionHard().size())
		{
			finalizarExamen();
		}
		return true;
	}
	
	public void habilitarBotones()
	{
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				btnCancelar.setEnabled(false);
				btnAprendizaje.setEnabled(true);
				btnExaminar.setEnabled((personaExamen.getPersona() != null)  && !btnExam);
				btnExaminarN.setEnabled((personaExamen.getPersona() != null) && btnExam);
			}
		});

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

		if (panelAnticipacionAnimacion != null) {
			panelAnticipacionAnimacion.setRun(false);
			panelAnticipacionAnimacion.setStop(true);
		}
		panelAnticipacionAnimacion = null;

		if (panelAnticipacionUsuarioExaminador != null)
			panelAnticipacionUsuarioExaminador.setRun(false);
		panelAnticipacionUsuarioExaminador = null;

		if (panelAnticipacionUsuarioExaminado != null)
			panelAnticipacionUsuarioExaminado.setRun(false);
		panelAnticipacionUsuarioExaminado = null;

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
	private PanelAnticipacionAnimacion panelAnticipacionAnimacion;//Thread
	private PanelAnticipacionUsuario panelAnticipacionUsuarioExaminador;//Thread
	private PanelAnticipacionUsuario panelAnticipacionUsuarioExaminado;
	private ExamenDetalle exaDetalle;
	//private TramaPsicologico tramaPsicologico = new TramaPsicologico();
	//private ThreadTrama thTrama;//Thread

	private List<Accion> mtAccionHardLst = new ArrayList<Accion>();
	private List<Accion> mtAccionSoftLst = new ArrayList<Accion>();
	private List<Resultado> resultados = new ArrayList<Resultado>();

	private final String comenzar = "Presione el acelerador para comenzar";
	private PersonaExamen personaExamen;
	private boolean btnExam=false;
	private boolean demo;

	private final int ETAPAS = 6;
	private static final Log log = LogFactory.getLog(PanelAnticipacion.class);
	private JInternalFrameTesterGral internalFrame;
	private JToggleButton btn;
	
}