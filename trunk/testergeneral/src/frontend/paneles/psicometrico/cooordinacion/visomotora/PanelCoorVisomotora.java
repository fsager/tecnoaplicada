/*

 * PanelAnticipacion.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.psicometrico.cooordinacion.visomotora;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
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
import testerGeneral.domain.ResultadoDetalleExamen;
import testerGeneral.exceptions.ExceptionIsNotHadware;
import testerGeneral.service.ExamenDetalleDefinition;
import testerGeneral.service.PersonaExamenDefinition;
import testerGeneral.service.ResultadoDetalleExamenDefinition;
import testerGeneral.threads.ThreadTrama;
import examenes.psicometrico.domain.TramaPsicologico;
import frontend.components.JOptionPaneTesterGral;
import frontend.paneles.PanelPersona;
import frontend.paneles.examenes.Finalisable;
import frontend.paneles.examenes.PanelExamen;
import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class PanelCoorVisomotora extends javax.swing.JPanel implements
		Finalisable, PanelExamen, Runnable {

	/** Creates new form PanelAnticipacion */

	public PanelCoorVisomotora(JToggleButton btn, PersonaExamen personaExamen) {
		this.btn = btn;
		initComponents();

		this.panelAnimacion.add(panelAnimacionDibujar);

		th.start();
		jProgress.setStringPainted(true);
		lbResultados.setVisible(false);

		try {
			ExamenDetalleDefinition examenDetalleService = (ExamenDetalleDefinition) ContextManager
					.getBizObject("examenDetalleService");
			exaDetalle = new ExamenDetalle();
			exaDetalle
					.setExadCodigo(ExamenDetalle.EXAD_CODIGO_TEST_COOR_VISOMOTORA);
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
		if(personaExamen.getPexaTipoExamen().equals(PersonaExamen.TIPO_EXAMEN_PROFECIONAL))
		{
			tiempoEntreLuz=Integer.valueOf(ContextManager.getProperty("EXAMEN.COORDINACION.VISOMOTORA.ENTRE.TIEMPO.PROFECIONAL"));
			tiempoLuz = Integer.valueOf(ContextManager.getProperty("EXAMEN.COORDINACION.VISOMOTORA.TIEMPO.LUZ.PROFECIONAL"));
		}
		else if(personaExamen.getPexaTipoExamen().equals(PersonaExamen.TIPO_EXAMEN_PARTICULAR))
		{
			tiempoEntreLuz=Integer.valueOf(ContextManager.getProperty("EXAMEN.COORDINACION.VISOMOTORA.ENTRE.TIEMPO.PARTICULAR"));
			tiempoLuz = Integer.valueOf(ContextManager.getProperty("EXAMEN.COORDINACION.VISOMOTORA.TIEMPO.LUZ.PARTICULAR"));
		}
		
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

		runExamen = false;
		Util.playSound(Constantes.SOUND_START,100);
		mostrarResultados();
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				btnGuardar.setEnabled((personaExamen.getPersona() != null)
						&& !demo && true);
			}
		});

		habilitarBotones();
	}

	public void run() {
		Random rnd = new Random();
		while (run) {
			try {

				if (run && runExamen) {
					//System.out.println("tiempoLuz: "+tiempoLuz+" tiempoEntreLuz: "+tiempoEntreLuz);
					cantidadTotal++;
					if (pulsadorAPresionar == 0)
						pulsadorAPresionar = rnd.nextInt(4) + 1;
					else {
						int rndAux = rnd.nextInt(4) + 1;
						while (rndAux == pulsadorAPresionar)
							rndAux = rnd.nextInt(4) + 1;

						pulsadorAPresionar = rndAux;
					}

					panelAnimacionDibujar
							.setPulsadorAPresionar(pulsadorAPresionar);
					panelAnimacionDibujar.repaint();

					prenderLed(pulsadorAPresionar);
					
					long timeIni = System.currentTimeMillis();
					long timeSlepped = 0;
					while (!isAnyButtonPress() && timeSlepped < tiempoLuz) {
						Thread.sleep(10);
						timeSlepped += (System.currentTimeMillis() - timeIni);
						timeIni = System.currentTimeMillis();
					}
					
					if(cantidadTotal>5)
						validarPulsacion(pulsadorAPresionar);

					long itempoASeguirDurmiendo = tiempoLuz - timeSlepped;
					if (itempoASeguirDurmiendo > 0)
						Thread.sleep(itempoASeguirDurmiendo);

					apagarLed();

					panelAnimacionDibujar.setPulsadorAPresionar(0);
					panelAnimacionDibujar.repaint();
					
					Thread.sleep(tiempoEntreLuz);
					
					if(cantidadTotal==5)
						Util.playSound(Constantes.SOUND_START,100);
				}
				else
				{
					Thread.sleep(10);
				}
			} catch (InterruptedException e) {
				apagarLed();
				panelAnimacionDibujar.setPulsadorAPresionar(0);
				panelAnimacionDibujar.repaint();
			}
		}
	}

	public void validarPulsacion(final int pulsador) {
		log.debug("validarPulsacion: "+pulsador);
		switch (pulsador) {
			case 1: {
				if (!thTrama.getTramaValida().isButton1Press()) {
					addError();
				} else
				{
					if(demo)
						Util.playSound(Constantes.SOUND_OK,100);
					aciertosUno++;
				}
			}
				break;
			case 2: {
				if (!thTrama.getTramaValida().isButton2Press()) {
					addError();
				} else
				{
					if(demo)
						Util.playSound(Constantes.SOUND_OK,100);
					
					aciertosDos++;
				}
			}
				break;
			case 3: {
				if (!thTrama.getTramaValida().isButton3Press()) {
					addError();
				} else
				{
					if(demo)
						Util.playSound(Constantes.SOUND_OK,100);
	
					aciertosTres++;
				}
			}
				break;
			case 4: {
				if (!thTrama.getTramaValida().isButton4Press()) {
					addError();
				} else
				{
					if(demo)
						Util.playSound(Constantes.SOUND_OK,100);
	
					aciertosCuatro++;
				}
			}
				break;
		}
		setAciertos();
		log.debug("errores: "+errores);
		/*SwingUtilities.invokeLater(new Runnable() {
			public void run() {	
				long tiempoEntroEnPiscion=System.currentTimeMillis();
				int pruebaTiempoLocal=0;
				while(isAnyButtonPress())
				{
					try
					{
						long tiempoActual=System.currentTimeMillis();
						long tiempo=(tiempoActual-tiempoEntroEnPiscion);
						tiempoAcumuladoEnPosicion+=tiempo;
						pruebaTiempoLocal+=tiempo;
						tiempoEntroEnPiscion=System.currentTimeMillis();
						Thread.sleep(8);
					} catch (InterruptedException e) {
					}
				}
				
				log.debug("pruebaTiempoLocal: "+pruebaTiempoLocal);
				pruebaTiempoLocal=0;
			}});*/

	}
	
	public boolean isPulsacionValid(final int pulsador)
	{
		switch (pulsador) {
			case 1: {
				if (!thTrama.getTramaValida().isButton1Press()) {
					return false;
				} 
			}
				break;
			case 2: {
				if (!thTrama.getTramaValida().isButton2Press()) {
					return false;
				} 
			}
				break;
			case 3: {
				if (!thTrama.getTramaValida().isButton3Press()) {
					return false;
				}
			}
				break;
			case 4: {
				if (!thTrama.getTramaValida().isButton4Press()) {
					return false;
				} 
			}
				break;
		}
		
		return true;
	}

	/*public void playOkSound()
	{
		String file = Util.class
		.getResource(Constantes.SOUND_OK).getFile();

			try {
			
				InputStream in = new FileInputStream(file.substring(1));
				AudioStream as = new AudioStream(in);
				AudioPlayer.player.start(as);
			
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
	}*/
	
	public void setAciertos()
	{
		panelAnimacionDibujar.setErrores(errores);
		panelAnimacionDibujar.setAciertosUno(aciertosUno);
		panelAnimacionDibujar.setAciertosDos(aciertosDos);
		panelAnimacionDibujar.setAciertosTres(aciertosTres);
		panelAnimacionDibujar.setAciertosCuatro(aciertosCuatro);
		//panelAnimacionDibujar.setTiempoDentro(tiempoAcumuladoEnPosicion/10);
		panelAnimacionDibujar.repaint();
	}
	public void prenderLed(int pulsador) {
		switch (pulsador) {
		case 1:
			thTrama.sendOrden(ThreadTrama.ORDEN_PRENDER_LED1);
			break;
		case 2:
			thTrama.sendOrden(ThreadTrama.ORDEN_PRENDER_LED2);
			break;
		case 3:
			thTrama.sendOrden(ThreadTrama.ORDEN_PRENDER_LED3);
			break;
		case 4:
			thTrama.sendOrden(ThreadTrama.ORDEN_PRENDER_LED4);
			break;
		}
	}

	public void apagarLed() {		
		/*switch (pulsador) {
		case 1:
			thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED1);
			break;
		case 2:
			thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED2);
			break;
		case 3:
			thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED3);
			break;
		case 4:
			thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED4);
			break;
		}*/
		
		thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED1);
		thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED2);
		thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED3);
		thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED4);		
		thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED5);
		thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LED6);
	}

	public boolean isAnyButtonPress() {
		
		log.debug("thTrama.getTramaValida: "+thTrama.getTramaValida().getByte(6));
		if (thTrama.getTramaValida().isButton1Press())
			return true;
		if (thTrama.getTramaValida().isButton2Press())
			return true;
		if (thTrama.getTramaValida().isButton3Press())
			return true;
		if (thTrama.getTramaValida().isButton4Press())
			return true;

		return false;
	}


	public void addError() {

		errores++;
		panelAnimacionDibujar.setErrores(errores);
		panelAnimacionDibujar.repaint();
		if(demo)
			Util.playSound(Constantes.SOUND_ERROR,100);

	}

	public void inicializarThreads() {

		try {
			thTrama = new ThreadTrama(new TramaPsicologico());
			Util.thTrama = thTrama;
			thTrama.setEjecucion(99999);
			thTrama.start();
		} catch (ExceptionIsNotHadware e) {
			JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		panelAcciones = new javax.swing.JPanel();
		btnAprendizaje = new javax.swing.JToggleButton();
		btnExaminar = new javax.swing.JToggleButton();
		btnExaminarN = new javax.swing.JToggleButton();
		btnGuardar = new javax.swing.JToggleButton();
		btnCancelar = new javax.swing.JToggleButton();
		jProgress = new javax.swing.JProgressBar();
		lbResultados = new javax.swing.JLabel();
		panelAnimacion = new javax.swing.JPanel();

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
										.addContainerGap(121, Short.MAX_VALUE)));
		panelAccionesLayout.setVerticalGroup(panelAccionesLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						panelAccionesLayout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnExaminar).addComponent(
										btnExaminarN).addComponent(btnGuardar)
								.addComponent(btnCancelar).addComponent(
										btnAprendizaje)));

		lbResultados.setFont(new java.awt.Font("Segoe UI", 1, 17));
		lbResultados.setForeground(new java.awt.Color(255, 0, 0));
		lbResultados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbResultados.setText("Resultado");

		panelAnimacion.setLayout(new java.awt.GridLayout());

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addGroup(
						layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jProgress,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										719, Short.MAX_VALUE).addComponent(
										panelAcciones,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)).addContainerGap())
				.addComponent(panelAnimacion,
						javax.swing.GroupLayout.DEFAULT_SIZE, 731,
						Short.MAX_VALUE).addComponent(lbResultados,
						javax.swing.GroupLayout.DEFAULT_SIZE, 731,
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
												jProgress,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												30,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(lbResultados)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												panelAnimacion,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												415, Short.MAX_VALUE)));
	}// </editor-fold>
	//GEN-END:initComponents

	private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {

		inicializar();
		habilitarBotones();
		repaint();
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
			List lstResultados = resultadoDetalleExamenService.getAll(resultadoDetalleExamen);

			if (lstResultados.size() < 1) {
				resultadoDetalleExamenService.insert(resultadoDetalleExamen);
			} else if (lstResultados.size() == 1) {
				resultadoDetalleExamen = (ResultadoDetalleExamen) lstResultados.get(0);
			}
			resultadoDetalleExamen.setRdeNota2(new Double(errores));
			//resultadoDetalleExamen.setRdeNota(new Double(tiempoAcumuladoEnPosicion));
			resultadoDetalleExamen.setRdeDetalleResultado("Errores: "+errores+".");
			resultadoDetalleExamen.setRdeParametrosCorrecion(exaDetalle.getExadParametrosCorrecion());
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
		lbResultados.setVisible(true);

		lbResultados.setText(getResultado());

	}

	public String getResultado() {
		lbResultados.setVisible(true);
		Double errores = Double.valueOf(ContextManager.getProperty("EXAMEN.COORDINACION.VISOMOTORA.ERRORES.PERMITIDOS.HASTA"));
		//Double tiempoMinimo = Double.valueOf(ContextManager.getProperty("EXAMEN.COORDINACION.VISOMOTORA.TIEMPO.DENTRO.MINIMO.PERMITIDO"));

		if (this.errores <= errores)
			return Examen.RESULTADO_DENTRO;
		else
			return Examen.RESULTADO_FUERA;
	}

	public void inicializar() {
		apagarLed();
		cantidadTotal=0;
		runExamen = false;
		if (task != null)
			task.cancel(true);
		task=null;
		
		jProgress.setValue(0);
		
		lbResultados.setVisible(false);

		errores = 0;
		aciertosUno = 0;
		aciertosDos = 0;
		aciertosTres = 0;
		aciertosCuatro = 0;
		tiempoTotal = 0;
		pulsadorAPresionar = 0;

		setAciertos();
	}

	private void iniciarExamen() {
		inicializar();

		if (demo)
			tiempoTotal = tiempoAprend;
		else
			tiempoTotal = tiempoExamen;

		btnCancelar.setEnabled(true);
		btnAprendizaje.setEnabled(false);
		btnExaminar.setEnabled(false);
		btnExaminarN.setEnabled(false);
		btnGuardar.setEnabled(false);

		unSelectButtons(btnAprendizaje);

		iniciarProgressBar();

		this.validate();

		runExamen = true;
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

		try {
			th.interrupt();
		} catch (Exception e) {
			// TODO: handle exception
		}
		run = false;

		if (task != null)
			task.cancel(true);

		if (thTrama != null) {
			thTrama.sendOrden(ThreadTrama.ORDEN_APAGAR_LAZER);
			apagarLed();
			thTrama.desconnect();
		}

	}

	class Task extends SwingWorker {
		/*
		 * Main task. Executed in background thread.
		 */

		@Override
		public Void doInBackground() {

			int tiempoDormir = tiempoTotal / 100;

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
			
			runExamen=false;
			th.interrupt();

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
	private javax.swing.JProgressBar jProgress;
	private javax.swing.JLabel lbResultados;
	private javax.swing.JPanel panelAcciones;
	private javax.swing.JPanel panelAnimacion;
	// End of variables declaration//GEN-END:variables

	private ExamenDetalle exaDetalle;
	private ThreadTrama thTrama;//Thread

	//private List<Resultado> resultados = new ArrayList<Resultado>();
	private PersonaExamen personaExamen;
	private boolean btnExam = false;
	private boolean demo;
	private boolean run = true;
	//private boolean onError = false;
	private boolean runExamen = false;

	private static final Log log = LogFactory.getLog(PanelCoorVisomotora.class);

	//private TramaPsicologico tramaPsicologico = new TramaPsicologico();
	private JToggleButton btn;
	private Task task;
	//private List<Integer> puntosActivados = new LinkedList<Integer>();
	private int cantidadTotal = 0;
	private int errores = 0;
	private int aciertosUno = 0;
	private int aciertosDos = 0;
	private int aciertosTres = 0;
	private int aciertosCuatro = 0;
	//private long tiempoIniError = 0;
	//private boolean finOK = false;
	private Thread th = new Thread(this);
	private int tiempoTotal = 0;
	private int tiempoLuz = -1;
	private int tiempoAprend = Integer
			.valueOf(ContextManager
					.getProperty("EXAMEN.COORDINACION.VISOMOTORA.TIEMPO.APRENDEZAJE.DURACION"));
	private int tiempoExamen = Integer
			.valueOf(ContextManager
					.getProperty("EXAMEN.COORDINACION.VISOMOTORA.TIEMPO.EXAMEN.DURACION"));
	private int pulsadorAPresionar = 0;
	private PanelAnimacion panelAnimacionDibujar = new PanelAnimacion();
	private long tiempoEntreLuz=-1;
	
	//private long tiempoAcumuladoEnPosicion=0;
	//private long tiempoEntroEnPiscion=0;

}