/*
 * PanelAnticipacion.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.psicometrico.cooordinacion.bimanual;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.business.ContextManager;
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

/**
 *
 * @author  __USER__
 */
public class PanelCoordinacionBimanual extends javax.swing.JPanel implements Finalisable,PanelExamen {
	
	/** Creates new form PanelAnticipacion */
	public PanelCoordinacionBimanual(JToggleButton btn,PersonaExamen personaExamen) {
		this.btn=btn;
		initComponents();
		
		try {
			ExamenDetalleDefinition examenDetalleService = (ExamenDetalleDefinition) ContextManager
					.getBizObject("examenDetalleService");
			exaDetalle = new ExamenDetalle();
			exaDetalle
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_COOR_BIMANUAL);
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

		this.validate();
		inicializarThreads();
		mostrarSecondMonitor();;
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
				
				try {
					Thread.sleep(Constantes.TIEMPO_ENTRE_RESULTADO);
				} catch (InterruptedException e) {
				}
				String columns[]={"Etapa","Resultado","Errores"};
				

				panelCoordinacionBimanualUsuarioExaminador.getPanelContenido().removeAll();
				
				Resultado res=new Resultado();
				res.setResEtapa(0l);
				//res.setResEtapaDesc("Auto Derecho");
				double tiempo=panelCoordinacionBimanualAnimacion.getTiempoFueraDer()+panelCoordinacionBimanualAnimacion.getTiempoFueraIzq();
				double errores=panelCoordinacionBimanualAnimacion.getErroresDerecho()+panelCoordinacionBimanualAnimacion.getErroresIzq();
				res.setResValor1(tiempo/10);
				res.setResValor2(errores);
				resultados.add(res);
				
				/*res=new Resultado();
				res.setResEtapa(1l);
				res.setResEtapaDesc("Auto Izquierdo");
				res.setResValor2((double)panelCoordinacionBimanualAnimacion.getTiempoFueraIzq());
				res.setResValor1((double)panelCoordinacionBimanualAnimacion.getErroresIzq());
				resultados.add(res);*/
				
				PanelResultado panelResultado=new PanelResultado(resultados,TableModelResultado.ERRORES_Y_RESULTADO,exaDetalle);
				panelCoordinacionBimanualUsuarioExaminador.getPanelContenido().add(panelResultado);
				panelCoordinacionBimanualUsuarioExaminador.validate();
				panelCoordinacionBimanualUsuarioExaminador.repaint();
				
				String examenResultadosExaminado=ContextManager.getProperty("EXAMEN.RESULTADOS.EXAMADO"); 
				if(examenResultadosExaminado.equals("S"))
				{
					if (panelCoordinacionBimanualUsuarioExaminado != null)
					{
						panelCoordinacionBimanualUsuarioExaminado.getPanelContenido().removeAll();
						panelResultado=new PanelResultado(resultados,TableModelResultado.ERRORES_Y_RESULTADO,exaDetalle);
						panelCoordinacionBimanualUsuarioExaminado.getPanelContenido().add(panelResultado);
						panelCoordinacionBimanualUsuarioExaminado.validate();
						panelCoordinacionBimanualUsuarioExaminado.repaint();						
					}
				}
			}
		});
		
		habilitarBotones();
	}


	public void inicializarThreads() {

		try
		{
			thTrama = new ThreadTrama(new TramaPsicologico());
			Util.thTrama = thTrama;
			thTrama.setEjecucion(99999);
			thTrama.start();
		}
		catch (ExceptionIsNotHadware e) {
			JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void mostrarSecondMonitor() {

		if (Util.frameSecundario != null) {
			int width = Integer.valueOf(ContextManager
					.getProperty("EXAMEN.TAMAÑO.PANTALLA.SECUNDARIA.WIDTH"));
			int heigth = Integer.valueOf(ContextManager
					.getProperty("EXAMEN.TAMAÑO.PANTALLA.SECUNDARIA.HEIGHT"));

			panelCoordinacionBimanualAnimacion = new PanelCoordinacionBimanualAnimacion(this,
					new Dimension(width, heigth),thTrama,rutasAprendizaje);

			panelCoordinacionBimanualUsuarioExaminado = new PanelCoordinacionBimanualUsuario(
					panelCoordinacionBimanualAnimacion, false);
			
			internalFrame=new JInternalFrameTesterGral();
			internalFrame.add(panelCoordinacionBimanualUsuarioExaminado);
			internalFrame.setVisible(true);
			
			Util.agregarIframeMonSecundario(((FrameSecundario)Util.frameSecundario).getDp(),internalFrame);
			


		} else {

			panelCoordinacionBimanualAnimacion = new PanelCoordinacionBimanualAnimacion(this,
					new Dimension(813, 320),thTrama,rutasAprendizaje);
		}

		panelCoordinacionBimanualUsuarioExaminador = new PanelCoordinacionBimanualUsuario(
				panelCoordinacionBimanualAnimacion, (Util.frameSecundario != null));
		this.panelUsuario.add(panelCoordinacionBimanualUsuarioExaminador);

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
		initPanelesAnimacion();
		panelCoordinacionBimanualAnimacion.initPosicion();
		panelCoordinacionBimanualAnimacion.initValores();
		panelCoordinacionBimanualAnimacion.setbackImagen(rutasAprendizaje);
		panelCoordinacionBimanualAnimacion.setRun(false);		
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

			Set resultados = resultadoDetalleExamen.getResultados();
			resultados.clear();
			for (int i = 0; i < this.resultados.size(); i++) {
				this.resultados.get(i).setResultadoDetalleExamen(
						resultadoDetalleExamen);
				resultados.add(this.resultados.get(i));
			}

			Double pro[]=ExamenesUtils.calcularPromedio(resultados);
			pro[0]=this.resultados.get(0).getResValor1();
			String resultado=ExamenesUtils.detalleExamenResultado(exaDetalle,resultados);
			resultadoDetalleExamen.setRdeNota(pro[0]);
			resultadoDetalleExamen.setRdeNota2(pro[1]);
			resultadoDetalleExamen.setRdeResultado(resultado);
			resultadoDetalleExamenService.update(resultadoDetalleExamen);

			btn.setForeground(Color.BLACK);
			Util.setIcon(btn,Constantes.IMG_ACEPTAR_SMALL);
			
			JOptionPaneTesterGral.showInternalMessageDialog(Constantes.MENSAJE_GUARDADO,
					Constantes.MENSAJE_GUARDADO_TIT,
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			new RuntimeException(e);
		}
	}

	private void btnExaminarNActionPerformed(java.awt.event.ActionEvent evt) {
		unSelectButtons(btnExaminarN);
		examinar();
	}

	private void btnExaminarActionPerformed(java.awt.event.ActionEvent evt) {
		unSelectButtons(btnExaminar);
		examinar();
	}

	public void examinar() {
		
		initPanelesAnimacion();
		panelCoordinacionBimanualAnimacion.setVelocidad(velocidadExamen);
		//panelCoordinacionBimanualAnimacion.setImg(rutasExamen);
		panelCoordinacionBimanualAnimacion.setbackImagen(rutasExamen);
		
		demo=false;
		iniciarExamen();
	}

	public void initPanelesAnimacion()
	{
		if(panelCoordinacionBimanualUsuarioExaminador!=null)
			panelCoordinacionBimanualUsuarioExaminador.init();
			
		if(panelCoordinacionBimanualUsuarioExaminado!=null)
			panelCoordinacionBimanualUsuarioExaminado.init();
	}
	
	private void btnAprendizajeActionPerformed(java.awt.event.ActionEvent evt) {

		initPanelesAnimacion();
		panelCoordinacionBimanualAnimacion.setVelocidad(velocidadAprendizaje);
		panelCoordinacionBimanualAnimacion.setbackImagen(rutasAprendizaje);
		panelCoordinacionBimanualAnimacion.initPosicion();
		
		demo=true;
		unSelectButtons(btnAprendizaje);
		iniciarExamen();

	}

	public void iniciarExamen() {
		
		resultados.clear();
		if(isInInitPosition())
		{
			panelCoordinacionBimanualAnimacion.initPosicion();
			panelCoordinacionBimanualAnimacion.initValores();
			btnCancelar.setEnabled(true);
			btnAprendizaje.setEnabled(false);
			btnExaminar.setEnabled(false);
			btnExaminarN.setEnabled(false);
			btnGuardar.setEnabled(false);
			
			panelCoordinacionBimanualAnimacion.setRun(true);
			repaintPanelesAnimacion();
			this.validate();
		}
		else {
			JOptionPaneTesterGral.showInternalMessageDialog(
					"Los autos deben estar en la posición inicial",
					"Iniciar Examen", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public boolean isInInitPosition()
	{
		return panelCoordinacionBimanualAnimacion.isInInitPosition();
	}
	
	public void repaintPanelesAnimacion() {

		if (panelCoordinacionBimanualAnimacion != null)
			panelCoordinacionBimanualAnimacion.repaint();
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

		if (thTrama != null)
			thTrama.desconnect();

		if (panelCoordinacionBimanualAnimacion != null) {
			panelCoordinacionBimanualAnimacion.setRun(false);
			panelCoordinacionBimanualAnimacion.setStop(true);
			panelCoordinacionBimanualAnimacion.finalizar();
		}
		panelCoordinacionBimanualAnimacion = null;

		if (panelCoordinacionBimanualUsuarioExaminador != null)
			panelCoordinacionBimanualUsuarioExaminador.setRun(false);
		panelCoordinacionBimanualUsuarioExaminador = null;

		if (panelCoordinacionBimanualUsuarioExaminado != null)
			panelCoordinacionBimanualUsuarioExaminado.setRun(false);
		panelCoordinacionBimanualUsuarioExaminado = null;

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
	private PanelCoordinacionBimanualAnimacion panelCoordinacionBimanualAnimacion;//Thread
	private PanelCoordinacionBimanualUsuario panelCoordinacionBimanualUsuarioExaminador;//Thread
	private PanelCoordinacionBimanualUsuario panelCoordinacionBimanualUsuarioExaminado;
	private ExamenDetalle exaDetalle;
	private ThreadTrama thTrama;//Thread

	private List<Resultado> resultados = new ArrayList<Resultado>();
	private PersonaExamen personaExamen;
	private boolean btnExam=false;
	private boolean demo;

	private static final Log log = LogFactory.getLog(PanelCoordinacionBimanual.class);
	private JInternalFrameTesterGral internalFrame;
	private JToggleButton btn;
	
	private boolean run = true;
	private boolean runExamen = false;
	private String rutasExamen=ContextManager.getProperty("EXAMEN.COORDINACION.BIMANUAL.RUTAS.EXAMEN");
	private String rutasAprendizaje=ContextManager.getProperty("EXAMEN.COORDINACION.BIMANUAL.RUTAS.APRENDIZAJE");
	private int velocidadExamen=Integer.valueOf(ContextManager.getProperty("EXAMEN.COORDINACION.BIMANUAL.VELOCIDAD.EXAMEN"));
	private int velocidadAprendizaje=Integer.valueOf(ContextManager.getProperty("EXAMEN.COORDINACION.BIMANUAL.VELOCIDAD.APRENDIZAJE"));
	
}