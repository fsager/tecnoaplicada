package testerGeneral.threads;

import examenes.psicometrico.domain.Trama;
import frontend.utils.Util;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.domain.Accion;
import testerGeneral.domain.Constantes;
import testerGeneral.exceptions.ExceptionIsNotHadware;



public class ThreadTrama extends Thread{
	
	public static final int ORDEN_PRENDER_LAZER=32; 
	public static final int ORDEN_APAGAR_LAZER=33;
	
	public static final int ORDEN_APAGAR_LED1=65;
	public static final int ORDEN_APAGAR_LED2=66;
	public static final int ORDEN_APAGAR_LED3=67;
	public static final int ORDEN_APAGAR_LED4=68;
	public static final int ORDEN_APAGAR_LED5=69;
	public static final int ORDEN_APAGAR_LED6=70;
	
	public static final int ORDEN_PRENDER_LED1=49;
	public static final int ORDEN_PRENDER_LED2=50;
	public static final int ORDEN_PRENDER_LED3=51;
	public static final int ORDEN_PRENDER_LED4=52;
	public static final int ORDEN_PRENDER_LED5=53;
	public static final int ORDEN_PRENDER_LED6=54;
	
	private static final Log log = LogFactory.getLog(ThreadTrama.class);
	private InputStream in;
	private OutputStream out;
	private CommPort port;
	private Trama trama;
	private Trama tramaValida;
	private boolean sync = false;
	private long timeStartToSync=-1;
	private int timeToSync=500;//1/2 segundo
	private List<Accion> mtAccionHard=new ArrayList<Accion>();
	private List<Accion>  mtAccionSoft=new ArrayList<Accion>();
	private boolean read=true;
	private int ejecucion=0;
	private boolean ejecutar=true;
	
	public ThreadTrama(Trama trama)
	{
		if(Util.thTrama!=null)
			Util.thTrama.desconnect();
		
		Util.thTrama=this;
		this.trama=trama;
		this.tramaValida=trama.getInstance();
		buscarPuerto();
	}

	public void buscarPuerto(){
		try
		{
			log.debug("ini buscarPuerto");
			
			Enumeration enu=CommPortIdentifier.getPortIdentifiers();
		 	while(enu.hasMoreElements())
		 	{	
		 		CommPortIdentifier puerto=(CommPortIdentifier)enu.nextElement();	  
		 	    if (!puerto.isCurrentlyOwned())
		        {
		            CommPort commPort;
					try {
						puerto.getCurrentOwner();
						commPort = puerto.open(this.getClass().getName(),2000);
						
			            if ( commPort instanceof SerialPort )
			            {
					 		if(puerto.getPortType()==CommPortIdentifier.PORT_SERIAL)
					 		{
					 			try
					 			{
					 				if(connect(commPort))
					 				{
					 					try {
											this.in = ((SerialPort)commPort).getInputStream();
											this.out=((SerialPort)commPort).getOutputStream();
						 			        this.port=commPort;
						 			       log.debug("fin exito buscarPuerto");
						 			       
						 					return;
										} catch (IOException e) {											
											//throw new RuntimeException(e); log.error(e.getMessage(),e);
										}
					 				}	
					 			}
					 			catch(ExceptionIsNotHadware e)
					 			{}//log.error(e.getMessage(),e);
					 		}
			            }
					} catch (PortInUseException e) {
						//Puertos en uso los descarto log.error(e.getMessage(),e);
					}
		        }
		 	}
		}
		catch (Exception e) {
			//log.error(e.getMessage(),e);
		}
		
		log.debug("fin fallido buscarPuerto");
	 	throw new ExceptionIsNotHadware(Constantes.ERROR_SIN_HARD);
	}
	
	public boolean connect (CommPort commPort)
	{
		log.debug("ini connect "+commPort);
		SerialPort serialPort = (SerialPort) commPort;
	    try {
			serialPort.setSerialPortParams(19200,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
	        InputStream in = serialPort.getInputStream();
	        
			byte[] buffer = new byte[1024];
			int len = -1;
	        boolean primerLeida = true;
			try {
				while ((len = in.read(buffer)) > 0) 
				{
					if (!primerLeida) 
					{
						for (int i = 0; i < (buffer.length) && i < len; i++) 
						{
							if (!sync) 
								sync=sync(trama,buffer[i]);
							else
							{
								sync=false;
								log.debug("fin exito connect "+commPort);
								return true;
							}
						}
					}
					primerLeida = false;
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
	    
		} catch (UnsupportedCommOperationException e) {
			throw new RuntimeException(e);			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		log.debug("fin fallido connect "+commPort);
		return false;
	}
	
	public void run() {
		
		log.debug("ini run ");
		
		byte[] buffer = new byte[1024];
		int len = -1;

		boolean primerLeida = true;
		try {
			while (read) {
				if(in!=null)
				{
					while (read && (len = in.read(buffer)) > -1) 
					{
						if (!primerLeida) 
						{
							for (int i = 0; i < (buffer.length) && i < len; i++) 
							{
								if (!sync) 
									sync=sync(trama,buffer[i]);
								else 
								{
									if (trama.addCampo(buffer[i])) {
										if (trama.isValid())
										{
											setTramaValida(trama);
											ejecurarAccion();
											trama.init();
										}
									}
								}
							}
						}
						primerLeida = false;
						this.sleep(100);
					}
				}
				else
				{
					this.sleep(500);
				}
			}
		} catch (IOException e) {
			log.debug(e.getMessage(),e);
			//throw new RuntimeException(e);
		} catch (InterruptedException e) {
			log.debug(e.getMessage(),e);
			//throw new RuntimeException(e);
		}
		
		log.debug("fin run ");
	}
	
	public boolean sync(Trama trama,Byte b)
	{
		log.debug("ini sync ");
		
		if(timeStartToSync==-1)
			timeStartToSync=System.currentTimeMillis();
		else
		{
			long tiempoTranscurrido=System.currentTimeMillis()-timeStartToSync;
			if(tiempoTranscurrido>timeStartToSync)
				throw new ExceptionIsNotHadware();
		}
		
		boolean sync = trama.sync(b);
		if (sync)
		{
			timeStartToSync=-1;
			trama.init();
		}
		
		log.debug("fin sync :"+sync);
		return sync;
	}
	
	public void ejecurarAccion()
	{	if(ejecutar)
		{
			//System.out.println("ejecucion: "+ejecucion);
			if(ejecucion<mtAccionHard.size() && (Boolean)mtAccionHard.get(ejecucion).invoke())
			{
				int ac=ejecucion;
				
				ejecucion++;
				mtAccionSoft.get(ac).invoke();
			}
		}
	}

	public void sendOrden(int orden)
	{
		try {
			//System.out.println("Orden enviada: "+orden);
			log.debug("ini sendOrden: "+orden);
			out.write(orden);
			log.debug("fin sendOrden: "+orden);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setTramaValida(Trama tramaValida) {
		this.tramaValida.setTrama(tramaValida.getTrama());
	}
	
	public Trama getTramaValida() {
		return tramaValida;
	}
	
	public void desconnect(){
		log.debug("ini desconnect");
		try
		{
			Util.thTrama=null;
			this.sendOrden(this.ORDEN_APAGAR_LAZER);
			
			this.sendOrden(this.ORDEN_APAGAR_LED1);
			this.sendOrden(this.ORDEN_APAGAR_LED2);
			this.sendOrden(this.ORDEN_APAGAR_LED3);
			this.sendOrden(this.ORDEN_APAGAR_LED4);
			this.sendOrden(this.ORDEN_APAGAR_LED5);
			this.sendOrden(this.ORDEN_APAGAR_LED6);
			
			
			read=false;
			try {
				
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			port.close();
		}
		catch(Exception e)
		{
			
		}
		
		log.debug("fin desconnect");
	}

	public Trama getTrama() {
		return trama;
	}

	public void setTrama(Trama trama) {
		this.trama = trama;
	}

	public boolean isEjecutar() {
		return ejecutar;
	}

	public void setEjecutar(boolean ejecutar) {
		this.ejecutar = ejecutar;
	}

	public int getEjecucion() {
		return ejecucion;
	}

	public void setEjecucion(int ejecucion) {
		this.ejecucion = ejecucion;
	}
	
	public List<Accion> getMtAccionHard() {
		return mtAccionHard;
	}

	public void setMtAccionHard(List<Accion> mtAccionHard) {
		this.mtAccionHard = mtAccionHard;
	}

	public List<Accion> getMtAccionSoft() {
		return mtAccionSoft;
	}

	public void setMtAccionSoft(List<Accion> mtAccionSoft) {
		this.mtAccionSoft = mtAccionSoft;
	}
	
}
