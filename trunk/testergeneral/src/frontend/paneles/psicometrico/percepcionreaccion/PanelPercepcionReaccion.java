/*
 * PanelPercepcionReaccion.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.psicometrico.percepcionreaccion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.ImageIcon;
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
import testerGeneral.service.PersonaExamenDefinition;
import testerGeneral.service.ResultadoDetalleExamenDefinition;
import testerGeneral.threads.ThreadTrama;
import examenes.psicometrico.domain.ParametrosPercepcionReacion;
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
public class PanelPercepcionReaccion extends javax.swing.JPanel implements
		Finalisable {

	/** Creates new form PanelPercepcionReaccion */
	public PanelPercepcionReaccion(JToggleButton btn,PersonaExamen personaExamen,ExamenDetalle exaDetalle) {
		this.btn=btn;
		initComponents();
		this.exaDetalle =exaDetalle;
		this.personaExamen = personaExamen;

		demo=(personaExamen.getPersona() == null);
		if (personaExamen.getPersona() == null) {
			btnExaminar.setEnabled(false);
			btnExaminarN.setEnabled(false);
			btnGuardar.setEnabled(false);
		}

		this.validate();
		mostrarSecondMonitor();

		if(exaDetalle.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_PERC_REAC))
		{
			ENTRE_TIEMPO =Integer.valueOf(ContextManager.getProperty("EXAMEN.PERCEPCION.REACCION.TIEMPO.ENTRE.TIEMPO"));
			TIEMPO_SLEEP =Integer.valueOf(ContextManager.getProperty("EXAMEN.PERCEPCION.REACCION.TIEMPO.SLEEP"))+ENTRE_TIEMPO;
		}
		else if(exaDetalle.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULTIPLES_COND))
		{	
			ENTRE_TIEMPO =Integer.valueOf(ContextManager.getProperty("EXAMEN.PERCEPCION.REACCION.TIEMPO.ENTRE.TIEMPO.COND"));
			TIEMPO_SLEEP =Integer.valueOf(ContextManager.getProperty("EXAMEN.PERCEPCION.REACCION.TIEMPO.SLEEP.COND"))+ENTRE_TIEMPO;
		}		
		else if(exaDetalle.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULT_NO_COND))
		{
			ENTRE_TIEMPO =Integer.valueOf(ContextManager.getProperty("EXAMEN.PERCEPCION.REACCION.TIEMPO.ENTRE.TIEMPO.NOCOND"));
			TIEMPO_SLEEP =Integer.valueOf(ContextManager.getProperty("EXAMEN.PERCEPCION.REACCION.TIEMPO.SLEEP.NOCOND"))+ENTRE_TIEMPO;
		}
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inicializarThreads();
				} catch (ExceptionIsNotHadware e) {
					JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void definicionAprendizaje()
	{
		ArrayList<Integer> orden=new ArrayList<Integer>();
		orden.add(0);
		orden.add(1);
		orden.add(2);
		orden.add(3);
		orden.add(4);
		orden.add(5);

		parametros = new ArrayList<ParametrosPercepcionReacion>();
		for(int i=0;i<ETAPAS;i++)
		{
			parametros.add(new ParametrosPercepcionReacion());
		}
		definicion(orden,true);

		orden=new ArrayList<Integer>();

		while(orden.size()<6)
		{
			Integer ord=(int)((Math.random()*6)+6);
			if(!orden.contains(ord))
				orden.add(ord);
		}		
		
		definicion(orden,true);
	}
	
	public void definicionExamen()
	{
		ArrayList<Integer> orden=new ArrayList<Integer>();
		parametros = new ArrayList<ParametrosPercepcionReacion>();
		for(int i=0;i<ETAPAS_EXAMEN;i++)
		{
			parametros.add(new ParametrosPercepcionReacion());
		}	

		int ddd=ETAPAS_EXAMEN/6;
		for(int i=0;i<ddd;i++)
		{
			orden=new ArrayList<Integer>();
			while(orden.size()<6)
			{

				Random generator = new Random();				
				Integer ord=generator.nextInt(6)+i*6;
				if(!orden.contains(ord))
					orden.add(ord);
			}		
			
			definicion(orden,false);
		}

	}
	
	public void definicion(ArrayList<Integer> orden,boolean texto) {		
		if(exaDetalle.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_PERC_REAC))
		{
			setAccionPulsador(Constantes.IMG_PERCEPCION_PUL_BLAN,texto,"Pulsador Blanco",ParametrosPercepcionReacion.PULSADOR_1,orden.get(0),null);
			
			setAccionPulsador(Constantes.IMG_PERCEPCION_PUL_AMA,texto,"Pulsador Amarillo",ParametrosPercepcionReacion.PULSADOR_2,orden.get(1),null);
			
			setAccionPulsador(Constantes.IMG_PERCEPCION_PUL_VER,texto,"Pulsador Verde",ParametrosPercepcionReacion.PULSADOR_3,orden.get(2),null);
			
			setAccionPulsador(Constantes.IMG_PERCEPCION_PUL_ROJO,texto,"Pulsador Rojo",ParametrosPercepcionReacion.PULSADOR_4,orden.get(3),null);
			
			setAccionPulsador(Constantes.IMG_PERCEPCION_PUL_AZUL,texto,"Pulsador Azul",ParametrosPercepcionReacion.PULSADOR_5,orden.get(4),null);
			
			setAccionPulsador(Constantes.IMG_PERCEPCION_PUL_NEG,texto,"Pulsador Negro",ParametrosPercepcionReacion.PULSADOR_6,orden.get(5),null);
			
		}
		else if(exaDetalle.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULTIPLES_COND))
		{	
			setAccionPulsador(Constantes.IMG_REAC_MULT_COND_PUL_PARE,texto,"Pedal Izquierdo",ParametrosPercepcionReacion.PULSADOR_FRENO,orden.get(0),null);//PARE
			
			setAccionPulsador(Constantes.IMG_REAC_MULT_COND_SEMAFORO,texto,"Pedal Derecho",ParametrosPercepcionReacion.PULSADOR_ACELERADOR,orden.get(1),null);//Autopista
			
			setAccionPulsador(Constantes.IMG_REAC_MULT_COND_PUL_NADA,texto,"Nada",ParametrosPercepcionReacion.PULSADOR_0,orden.get(2),null);//dos flechas
			
			setAccionPulsador(Constantes.IMG_REAC_MULT_COND_PUL_GIRO_IZQ,texto,"Pulsador Izquierdo",ParametrosPercepcionReacion.PULSADOR_6,orden.get(3),null);//flecha izq
			
			setAccionPulsador(Constantes.IMG_REAC_MULT_COND_PUL_GIRO_DERECHO,texto,"Pulsador Derecho",ParametrosPercepcionReacion.PULSADOR_5,orden.get(4),null);//flecha der
			
			setAccionPulsador(Constantes.IMG_REAC_MULT_COND_PUL_E,texto,"Nada",ParametrosPercepcionReacion.PULSADOR_0,orden.get(5),null);//estacionamiento

		}		
		else if(exaDetalle.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULT_NO_COND))
		{
			setAccionPulsador(Constantes.IMG_REAC_MULT_NO_COND_PUL_SONIDO_IZQ,texto,"Pedal Izquierdo",ParametrosPercepcionReacion.PULSADOR_FRENO,orden.get(0),Constantes.SONIDO_REAC_MULT_NO_COND_PUL_FRENO);//PARE
			
			setAccionPulsador(Constantes.IMG_REAC_MULT_NO_COND_PUL_SONIDO_DER,texto,"Pedal Derecho",ParametrosPercepcionReacion.PULSADOR_ACELERADOR,orden.get(1),Constantes.SONIDO_REAC_MULT_NO_COND_PUL_ACELERADOR);//Autopista
			
			setAccionPulsador(Constantes.IMG_REAC_MULT_NO_COND_PUL_ROMBO,texto,"Nada",ParametrosPercepcionReacion.PULSADOR_0,orden.get(2),null);//dos flechas
			
			setAccionPulsador(Constantes.IMG_REAC_MULT_NO_COND_PUL_ESTRELLA,texto,"Pulsador Izquierdo",ParametrosPercepcionReacion.PULSADOR_6,orden.get(3),null);//flecha izq
			
			setAccionPulsador(Constantes.IMG_REAC_MULT_NO_COND_PUL_TRIANGULO,texto,"Pulsador Derecho",ParametrosPercepcionReacion.PULSADOR_5,orden.get(4),null);//flecha der
			
			setAccionPulsador(Constantes.IMG_REAC_MULT_NO_COND_PUL_CRUZ,texto,"Nada",ParametrosPercepcionReacion.PULSADOR_0,orden.get(5),null);//estacionamiento

		}		
	}
	
	public void setAccionPulsador(String imagen,boolean mostrarTexto,String texto,String nroPulsador,int orden,String sonido)
	{
		Image pulsador = null;
		if(Util.frameSecundario!=null)
			pulsador = new ImageIcon(Util.class.getResource(imagen)).getImage();
		else
			pulsador = new ImageIcon(Util.class.getResource(imagen)).getImage().getScaledInstance(400,400,Image.SCALE_SMOOTH);
		
		ParametrosPercepcionReacion parametrosPercepcionReacion = new ParametrosPercepcionReacion();
		parametrosPercepcionReacion.setImg(pulsador);
		if(mostrarTexto)
			parametrosPercepcionReacion.setText(texto);
		
		parametrosPercepcionReacion.setTextInfo(texto);
		parametrosPercepcionReacion.setPulsador(nroPulsador);
		parametrosPercepcionReacion.setSonido(sonido);
		parametros.set(orden,parametrosPercepcionReacion);
	}

	public void repaintPanelesAnimacion() {
		if (panelPercepcionReaccionAnimacion != null)
			panelPercepcionReaccionAnimacion.repaint();
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

		/*if (thTrama != null)
			thTrama.desconnect();*/

		if(panelPercepcionReaccionAnimacion != null)			
			panelPercepcionReaccionAnimacion.setStop(true);
		panelPercepcionReaccionAnimacion = null;

		if (panelPercepcionReaccionUsuarioExaminado != null)
			panelPercepcionReaccionUsuarioExaminado.setRun(false);
		panelPercepcionReaccionUsuarioExaminado = null;

		if (panelPercepcionReaccionUsuarioExaminador != null)
			panelPercepcionReaccionUsuarioExaminador.setRun(false);
		panelPercepcionReaccionUsuarioExaminador = null;

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
			try
			{
				thTrama = new ThreadTrama(new TramaPsicologico());
				Util.thTrama = thTrama;
				thTrama.setEjecucion(99999);
				thTrama.start();		
				panelPercepcionReaccionAnimacion.setThTrama(thTrama);
			}
			catch (NullPointerException e) {
				
			}
			catch (ExceptionIsNotHadware e) {
				JOptionPaneTesterGral.showInternalMessageDialog(e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			this.thTrama = Util.thTrama;
			/*this.thTrama.setEjecutar(false);
			this.thTrama.setEjecucion(99999);
			this.thTrama.setMtAccionHard(null);
			this.thTrama.setMtAccionSoft(null);*/
			panelPercepcionReaccionAnimacion.setThTrama(thTrama);
		}
		
	}

	public void mostrarSecondMonitor() {

		String img=null;
		
		if(exaDetalle.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_PERC_REAC))
			img=Constantes.IMG_PERCEPCION_REACCION;
		else if(exaDetalle.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULTIPLES_COND))
			img=Constantes.IMG_REAC_MULT_COND;	
		else if(exaDetalle.getExadCodigo().equals(ExamenDetalle.EXAD_CODIGO_TEST_REAC_MULT_NO_COND))
			img=Constantes.IMG_REAC_MULT_NO_COND;
		
		panelPercepcionReaccionAnimacion = new PanelPercepcionReaccionAnimacion(null,this,img);

		if (Util.frameSecundario != null) {
			
			int width = Integer.valueOf(ContextManager
					.getProperty("EXAMEN.TAMAÑO.PANTALLA.SECUNDARIA.WIDTH"));
			int heigth = Integer.valueOf(ContextManager
					.getProperty("EXAMEN.TAMAÑO.PANTALLA.SECUNDARIA.HEIGHT"));

			
			panelPercepcionReaccionUsuarioExaminado = new PanelPercepcionReaccionUsuario(
					panelPercepcionReaccionAnimacion, false);
			
			panelPercepcionReaccionUsuarioExaminado.setMinimumSize(new Dimension(width,heigth));
			panelPercepcionReaccionUsuarioExaminado.setSize(width,heigth);
			panelPercepcionReaccionUsuarioExaminado.setPreferredSize(new Dimension(width,heigth));
			
			panelPercepcionReaccionUsuarioExaminado.validate();
			internalFrame=new JInternalFrameTesterGral();
			internalFrame.add(panelPercepcionReaccionUsuarioExaminado);
			internalFrame.setVisible(true);
			
			Util.agregarIframeMonSecundario(((FrameSecundario)Util.frameSecundario).getDp(),internalFrame);

		}

		panelPercepcionReaccionUsuarioExaminador = new PanelPercepcionReaccionUsuario(panelPercepcionReaccionAnimacion,	(Util.frameSecundario != null));
		this.panelUsuario.add(panelPercepcionReaccionUsuarioExaminador);

	}

	public void cicloAprendizaje() {

		resultados = new ArrayList<Resultado>();
		for (int i = 0; i < ETAPAS; i++) {
			Resultado res = new Resultado();
			res.setResEtapa(Long.valueOf(i));
			resultados.add(res);
		}

		mtAccionHardLst = new ArrayList<Accion>();
		mtAccionSoftLst = new ArrayList<Accion>();

		for(int i=0;i<ETAPAS;i++)
		{
			actionBtnPressAprendizaje(i);	
		}
		
		thTrama.setMtAccionHard(mtAccionHardLst);
		thTrama.setMtAccionSoft(mtAccionSoftLst);
		thTrama.setEjecucion(99999);
	}
	
	public void cicloExamen() {

		resultados = new ArrayList<Resultado>();
		for (int i = 0; i < ETAPAS_EXAMEN; i++) {
			Resultado res = new Resultado();
			res.setResEtapa(Long.valueOf(i));
			resultados.add(res);
		}

		mtAccionHardLst = new ArrayList<Accion>();
		mtAccionSoftLst = new ArrayList<Accion>();

		for(int i=0;i<ETAPAS_EXAMEN;i++)
		{
			actionBtnPressExamen(i);	
		}
		
		thTrama.setMtAccionHard(mtAccionHardLst);
		thTrama.setMtAccionSoft(mtAccionSoftLst);
		thTrama.setEjecucion(99999);

	}

	public void actionBtnPressAprendizaje(int pos) {
		try {

			Class[] parametros = { String.class };

			if (panelPercepcionReaccionAnimacion != null) {
				
				Method mtAccionHard = null;
				Method mtAccionSoft = null;
				if(!this.parametros.get(pos).getPulsador().equals(ParametrosPercepcionReacion.PULSADOR_0))
				{
					mtAccionHard = TramaPsicologico.class.getMethod("isAnyButtonPress", null);
					mtAccionSoft = PanelPercepcionReaccion.class.getMethod("validarBtnPressedAprendizaje", parametros);
					
					mtAccionHardLst.add(new Accion(mtAccionHard, thTrama.getTramaValida(),null, null));
					mtAccionSoftLst.add(new Accion(mtAccionSoft, this,this.parametros.get(pos).getPulsador(), null));
				}
				else
				{
					mtAccionHard = PanelPercepcionReaccion.class.getMethod("isNotAprend", null);
					mtAccionSoft = PanelPercepcionReaccion.class.getMethod("validarBtnNoPressedAprendizaje",parametros);
					
					mtAccionHardLst.add(new Accion(mtAccionHard, this,null, null));
					mtAccionSoftLst.add(new Accion(mtAccionSoft, this,this.parametros.get(pos).getPulsador(), null));					
				}

				mtAccionHard = PanelPercepcionReaccion.class.getMethod("isTrue", null);
				mtAccionSoft = PanelPercepcionReaccion.class.getMethod("esperarAprendizaje", null);
				
				mtAccionHardLst.add(new Accion(mtAccionHard, this, null, null));
				mtAccionSoftLst.add(new Accion(mtAccionSoft,this, null, null));
				
			}

		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void actionBtnPressExamen(int pos) {
		try {

			Class[] parametros = { String.class };

			if (panelPercepcionReaccionAnimacion != null) {
				Method mtAccionHard = TramaPsicologico.class.getMethod("isAnyButtonPress", null);
				Method mtAccionSoft = PanelPercepcionReaccion.class.getMethod("validarBtnPressedExamen", parametros);

				mtAccionHardLst.add(new Accion(mtAccionHard, thTrama.getTramaValida(),null, null));
				mtAccionSoftLst.add(new Accion(mtAccionSoft, this,this.parametros.get(pos).getPulsador(), null));
				
			}

		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void esperarAprendizaje()
	{
		thTrama.setEjecutar(false);
		panelPercepcionReaccionAnimacion.setOk();
		panelPercepcionReaccionAnimacion.repaint();
		Thread t1=new Thread()
		{
			public void run() {
				try
				{
					this.sleep(1500);
					thTrama.setEjecutar(true);
					panelPercepcionReaccionAnimacion.setOkToNull();
					panelPercepcionReaccionAnimacion.repaint();
				}
				catch (Exception e) {
					
				}
			}
		};
		t1.start();		
	}
	

	public void validarBtnNoPressedAprendizaje(String pulsadorObj) throws InterruptedException
	{
		if(!panelPercepcionReaccionAnimacion.isAprend())
		{	
			thTrama.setEjecutar(false);
			panelPercepcionReaccionAnimacion.setTiempoSleep(TIEMPO_SLEEP);
			panelPercepcionReaccionAnimacion.setAprend(true);
			
		}
		
	}
	
	public boolean isNotAprend()
	{
		return !panelPercepcionReaccionAnimacion.isAprend();
	}
	
	public void validarBtnPressedAprendizaje(String pulsadorObj) {
		
		Boolean isBtn = false;
		try {
			if(!pulsadorObj.equals(ParametrosPercepcionReacion.PULSADOR_0))
				thTrama.setEjecucion(thTrama.getEjecucion() - 1);
			Method mtAccionSoft = TramaPsicologico.class.getMethod(pulsadorObj, null);
			isBtn = (Boolean) mtAccionSoft.invoke(thTrama.getTramaValida(), null);
			
			//System.out.println("isBtn: "+isBtn+" thTrama.getEjecucion(): "+thTrama.getEjecucion()+"thTrama.isEjecutar(): "+thTrama.isEjecutar());

		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}

		Resultado resultado=resultados.get(panelPercepcionReaccionAnimacion.getPosition());
		String etapaDesc=parametros.get(panelPercepcionReaccionAnimacion.getPosition()).getTextInfo();
		resultado.setResEtapaDesc(etapaDesc);
		if (isBtn) {
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					Util.playSound(Constantes.SOUND_OK,100);					
				}
			});
			
			Double res=resultados.get(panelPercepcionReaccionAnimacion.getPosition()).getResValor2();
			if(res==null)
				resultados.get(panelPercepcionReaccionAnimacion.getPosition()).setResValor2(new Double(0));
			
			resultados.get(panelPercepcionReaccionAnimacion.getPosition()).setResValor1(0d);//(double) (System.currentTimeMillis() - tiempInicioEtapa)
			
			
			//tiempInicioEtapa = System.currentTimeMillis();
			panelPercepcionReaccionAnimacion.setOk();
			panelPercepcionReaccionAnimacion.repaint();
			panelPercepcionReaccionAnimacion.setPosition(panelPercepcionReaccionAnimacion.getPosition() + 1);
			if(!pulsadorObj.equals(ParametrosPercepcionReacion.PULSADOR_0))
				thTrama.setEjecucion(thTrama.getEjecucion() + 1);
			else
				thTrama.setEjecutar(true);
			
			if(panelPercepcionReaccionAnimacion.getPosition()>=parametros.size())
				finalizarExamen();

			this.repaintPanelesAnimacion();
		} else {
			
			if(pulsadorObj.equals(ParametrosPercepcionReacion.PULSADOR_0))
				thTrama.setEjecucion(thTrama.getEjecucion() - 1);
			
			thTrama.setEjecutar(false);
			panelPercepcionReaccionAnimacion.setKo();
			panelPercepcionReaccionAnimacion.repaint();
			Thread t1=new Thread()
			{
				public void run() {
					try
					{
						this.sleep(1000);
						thTrama.setEjecutar(true);
						panelPercepcionReaccionAnimacion.setKoToNull();
						panelPercepcionReaccionAnimacion.repaint();
					}
					catch (Exception e) {
					}
				}
			};
			t1.start();	
			
			Double res=resultados.get(panelPercepcionReaccionAnimacion.getPosition()).getResValor2();
			
			if(res==null)
				resultados.get(panelPercepcionReaccionAnimacion.getPosition()).setResValor2(new Double(1));
			else
				resultados.get(panelPercepcionReaccionAnimacion.getPosition()).setResValor2(res+1);

			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					Util.playSound(Constantes.SOUND_ERROR,100);
				}
			});
		}

	}

	public void validarBtnPressedExamen(String pulsadorObj) {
		//System.out.println("validarBtnPressedExamen: "+pulsadorObj);
		thTrama.setEjecutar(false);
		panelPercepcionReaccionAnimacion.setEntrePulsadores();
		panelPercepcionReaccionAnimacion.repaint();
		Thread t1=new Thread()
		{
			public void run() {
				try{
					this.sleep(ENTRE_TIEMPO);
					panelPercepcionReaccionAnimacion.setEntrePulsadoresToNull();
					panelPercepcionReaccionAnimacion.repaint();				

					thTrama.setEjecutar(true);
				}
				catch (Exception e) {
				}
			}
		};
		t1.start();
		
		Boolean isBtn = false;
		try {
			thTrama.setEjecucion(thTrama.getEjecucion() - 1);
			Method mtAccionSoft = TramaPsicologico.class.getMethod(pulsadorObj, null);
			isBtn = (Boolean) mtAccionSoft.invoke(thTrama.getTramaValida(), null);

		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}

	
		if(panelPercepcionReaccionAnimacion.getPosition()<resultados.size())
		{
			Resultado resultado=resultados.get(panelPercepcionReaccionAnimacion.getPosition());
			String etapaDesc=parametros.get(panelPercepcionReaccionAnimacion.getPosition()).getTextInfo();
			resultado.setResEtapaDesc(etapaDesc);
			
			if (isBtn) {
				Double res=resultados.get(panelPercepcionReaccionAnimacion.getPosition()).getResValor2();
				if(res==null)
					resultados.get(panelPercepcionReaccionAnimacion.getPosition()).setResValor2(new Double(0));
				
				
				Double tiempo=(double) (System.currentTimeMillis() - tiempInicioEtapa)-ENTRE_TIEMPO-200;
				tiempo=tiempo/10;
				if(pulsadorObj.equals(ParametrosPercepcionReacion.PULSADOR_0))
					tiempo=null;
				
				//System.out.println("Tiempo: "+tiempo);
				resultados.get(panelPercepcionReaccionAnimacion.getPosition()).setResValor1(tiempo);				
	
			} else {
				
				Double res=resultados.get(panelPercepcionReaccionAnimacion.getPosition()).getResValor2();
				
				if(res==null)
					resultados.get(panelPercepcionReaccionAnimacion.getPosition()).setResValor2(new Double(1));
				else
					resultados.get(panelPercepcionReaccionAnimacion.getPosition()).setResValor2(res+1);
			}
			tiempInicioEtapa = System.currentTimeMillis();
		}
		
		//System.out.println("Res "+resultados.get(panelPercepcionReaccionAnimacion.getPosition()).getResValor1()+" Pos "+panelPercepcionReaccionAnimacion.getPosition());
		
		panelPercepcionReaccionAnimacion.setPosition(panelPercepcionReaccionAnimacion.getPosition() + 1);
		thTrama.setEjecucion(thTrama.getEjecucion() + 1);
		
		if(panelPercepcionReaccionAnimacion.getPosition()>=parametros.size())			
			finalizarExamen();

		this.repaintPanelesAnimacion();
	}
	

	

	
	public void finalizarExamen()
	{	
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				btnGuardar.setEnabled((personaExamen.getPersona() != null) && !demo && true);
				Util.playSound(Constantes.SOUND_START,0);
				try {
					Thread.sleep(Constantes.TIEMPO_ENTRE_RESULTADO);
				} catch (InterruptedException e) {
				}
				
				panelPercepcionReaccionUsuarioExaminador.getPanelContenido().removeAll();
				PanelResultado panelResultado=new PanelResultado(resultados,TableModelResultado.SOLO_ERRORES,exaDetalle);
				panelPercepcionReaccionUsuarioExaminador.getPanelContenido().add(panelResultado);
				panelPercepcionReaccionUsuarioExaminador.validate();
				panelPercepcionReaccionUsuarioExaminador.repaint();
				
				String examenResultadosExaminado=ContextManager.getProperty("EXAMEN.RESULTADOS.EXAMADO"); 
				if(examenResultadosExaminado.equals("S"))
				{
					if (panelPercepcionReaccionUsuarioExaminado != null)
					{
						panelPercepcionReaccionUsuarioExaminado.getPanelContenido().removeAll();
						panelResultado=new PanelResultado(resultados,TableModelResultado.SOLO_ERRORES,exaDetalle);
						panelPercepcionReaccionUsuarioExaminado.getPanelContenido().add(panelResultado);
						panelPercepcionReaccionUsuarioExaminado.validate();
						panelPercepcionReaccionUsuarioExaminado.repaint();						
					}
				}
				
			}
		});
		
		habilitarBotones();
	}
	
	
	public void initPanelesAnimacion()
	{
		if(panelPercepcionReaccionUsuarioExaminado!=null)
			panelPercepcionReaccionUsuarioExaminado.init();
			
		if(panelPercepcionReaccionUsuarioExaminador!=null)
			panelPercepcionReaccionUsuarioExaminador.init();
	}
	
	public void habilitarBotones()
	{	
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				btnCancelar.setEnabled(false);
				btnAprendizaje.setEnabled(true);
				btnExaminar.setEnabled((personaExamen.getPersona() != null) && !btnExam);
				btnExaminarN.setEnabled((personaExamen.getPersona() != null) &&  btnExam);
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
										.addContainerGap(12, Short.MAX_VALUE)));
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
				panelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 593,
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
		panelPercepcionReaccionAnimacion.init();
		thTrama.setEjecucion(9999);
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

			Set resultados = resultadoDetalleExamen.getResultados();
			resultados.clear();
			for (int i = 0; i < this.resultados.size(); i++) {
				this.resultados.get(i).setResultadoDetalleExamen(
						resultadoDetalleExamen);
				resultados.add(this.resultados.get(i));
			}
			
			Double pro[]=ExamenesUtils.calcularPromedio(resultados);
			String resultado=ExamenesUtils.detalleExamenResultado(exaDetalle,resultados);
			resultadoDetalleExamen.setRdeNota(pro[0]);//tiempo centecimas de segundos
			resultadoDetalleExamen.setRdeNota2(pro[1]);//errores
			resultadoDetalleExamen.setRdeDetalleResultado("Tiempo promedio: "+pro[0].intValue()+" Errores: "+pro[1].intValue());
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
		panelPercepcionReaccionAnimacion.setTiempoSleep(TIEMPO_SLEEP);
		definicionExamen();
		initPanelesAnimacion();
		demo=false;
		btnExam=true;
		examinar((JToggleButton)evt.getSource(),true);
		panelPercepcionReaccionAnimacion.setRun(true);
	}

	private void btnExaminarActionPerformed(java.awt.event.ActionEvent evt) {
		panelPercepcionReaccionAnimacion.setTiempoSleep(TIEMPO_SLEEP);
		definicionExamen();
		initPanelesAnimacion();
		demo=false;
		btnExam=true;
		examinar((JToggleButton)evt.getSource(),true);
		panelPercepcionReaccionAnimacion.setRun(true);
	}
	
	public void examinar(JToggleButton btn,boolean examen)
	{
		
		Util.playSound(Constantes.SOUND_START,0);
		try
		{
		Thread.sleep(1400);
		}
		catch(Exception e)
		{
			
		}
		unSelectButtons(btn);
		btnCancelar.setEnabled(true);
		btnAprendizaje.setEnabled(false);
		btnExaminar.setEnabled(false);
		btnExaminarN.setEnabled(false);
		btnGuardar.setEnabled(false);
		
		if(examen)
			cicloExamen();
		else
			cicloAprendizaje();
		
		panelPercepcionReaccionAnimacion.setParametros(parametros);
		panelPercepcionReaccionAnimacion.setPosition(0);
		panelPercepcionReaccionAnimacion.repaint();
		thTrama.setEjecucion(0);
		tiempInicioEtapa = System.currentTimeMillis();
	}

	private void btnAprendizajeActionPerformed(java.awt.event.ActionEvent evt) {
		panelPercepcionReaccionAnimacion.setRun(false);
		definicionAprendizaje();
		initPanelesAnimacion();
		demo=true;
		examinar((JToggleButton)evt.getSource(),false);
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
	private PanelPercepcionReaccionAnimacion panelPercepcionReaccionAnimacion;//Thread
	private PanelPercepcionReaccionUsuario panelPercepcionReaccionUsuarioExaminador;//Thread
	private PanelPercepcionReaccionUsuario panelPercepcionReaccionUsuarioExaminado;
	private ExamenDetalle exaDetalle;
	//private TramaPsicologico tramaPsicologico = new TramaPsicologico();
	private ThreadTrama thTrama;//Thread
	private ArrayList<ParametrosPercepcionReacion> parametros;

	private List<Accion> mtAccionHardLst = new ArrayList<Accion>();
	private List<Accion> mtAccionSoftLst = new ArrayList<Accion>();
	private List<Resultado> resultados = new ArrayList<Resultado>();

	private PersonaExamen personaExamen;

	private static final Log log = LogFactory.getLog(PanelPercepcionReaccion.class);
	private final int ETAPAS = Integer.valueOf(ContextManager.getProperty("EXAMEN.PERCEPCION.REACCION.ETAPAS.APRENDIZAJE"));
	private final int ETAPAS_EXAMEN =Integer.valueOf(ContextManager.getProperty("EXAMEN.PERCEPCION.REACCION.ETAPAS.EXAMEN"));
	private int ENTRE_TIEMPO=0;
	private int TIEMPO_SLEEP=0;
	
	private long tiempInicioEtapa = 0;
	private boolean demo;
	private boolean btnExam=false;
	private JInternalFrameTesterGral internalFrame;
	private JToggleButton btn;
	
}