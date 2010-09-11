/*
 * PanelAnticipacionAnimacion.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.psicometrico.anticipacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.Resultado;
import testerGeneral.threads.ThreadTrama;
import examenes.psicometrico.domain.ParametrosAnticipacion;
import examenes.psicometrico.domain.TramaPsicologico;
import frontend.paneles.Captura;
import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class PanelAnticipacionAnimacion extends JPanel  implements Runnable, Captura{

	private Image dbImage;
	private Graphics dbg;
	private Image imgTodo;
	private Graphics2D graphicsCaptura;
	private Image backImage;
	private Image imgAuto;
	private Image imgPared;
	private Image imgPare;
	private ParametrosAnticipacion parametros=new ParametrosAnticipacion();
	private boolean run = false;
	//private TramaPsicologico tramaPsicologico = new TramaPsicologico();
	private Dimension imgDimension;
	private double metrosTotal=Integer.valueOf(ContextManager.getProperty("EXAMEN.ANTICIPACION.APRENDIZAJE.METROS.TOTAL"));
	
	private Thread thThis=new Thread(this);
	private boolean demo=false;
	private boolean save=false;
	private boolean izq=false;
	
	private boolean mostrarPared=true;
	
	private double avancePx=5;
	
	private boolean stop=false;
	
	private double nanoSeg=1;
	
	private Resultado res;
	private PanelAnticipacion panelAnticipacion;
	private ThreadTrama thTrama;


	
	/** Creates new form PanelAnticipacionAnimacion */
	public PanelAnticipacionAnimacion(PanelAnticipacion panelAnticipacion ,Dimension imgDimension,ThreadTrama thTrama){
	
		this.panelAnticipacion=panelAnticipacion;
		this.thTrama=thTrama;
		initComponents();

		this.imgDimension=imgDimension;
		ajustarTamañosImagenes();

		initParamtros(false, Integer.valueOf(ContextManager
				.getProperty("EXAMEN.ANTICIPACION.APRENDIZAJE.SPEED")),true,false,null);
		thThis.setPriority(Thread.MAX_PRIORITY);
		thThis.start();
		
		imgTodo = new BufferedImage(imgDimension.width,imgDimension.height,BufferedImage.TYPE_INT_RGB);		

	}


	
	public void initParamtros(boolean izq,int speed,boolean demo,boolean save,Resultado res)
	{
		ImageIcon imgauto=new ImageIcon(imgAuto);
		ImageIcon imgback=new ImageIcon(backImage);
		ImageIcon imgpared=new ImageIcon(imgPared);
		ImageIcon imgpare=new ImageIcon(imgPare);

		parametros.setWidth(imgback.getIconWidth());
		parametros.setWidthObjeto(imgauto.getIconWidth());
		parametros.setY_pos(imgback.getIconHeight()-imgauto.getIconHeight());			
		parametros.setY_posPared(imgback.getIconHeight()-imgpared.getIconHeight());
		parametros.setY_posPare(imgback.getIconHeight()-imgpare.getIconHeight()-imgpared.getIconHeight()-20);
		
		if(izq)
		{
			parametros.setX_pos(10);
			parametros.setX_posPared(imgback.getIconWidth()-(4*imgauto.getIconWidth()));
			parametros.setX_posPare(imgback.getIconWidth()-(2*imgauto.getIconWidth()));
			parametros.setPosicionObjetivo(parametros.getX_posPare()+(imgpare.getIconWidth()/2));
		}
		else
		{
			parametros.setX_pos(imgback.getIconWidth()-imgauto.getIconWidth()-10);			
			parametros.setX_posPared(1*imgauto.getIconWidth());
			parametros.setX_posPare((int)(1.5*imgauto.getIconWidth()));
			parametros.setPosicionObjetivo(parametros.getX_posPare()+(imgpare.getIconWidth()/2));
		}
		
		run=false;
		mostrarPared=true;
		this.save=save;
		this.demo=demo;
		this.res=res;
		this.izq=izq;
		
		imgauto=null;
		imgback=null;
		imgpared=null;
		imgpare=null;
		
		parametros.setX_speed(speed);
		nanoSeg=calcTiempo(parametros.getWidth());	
		
		repaint();
	}
	
	public ParametrosAnticipacion getParametros() {
		return parametros;
	}


	public void setParametros(ParametrosAnticipacion parametros) {
		this.parametros = parametros;
	}


	public void ajustarTamañosImagenes()
	{
		backImage = new ImageIcon(Util.class.getResource(Constantes.IMG_FONDO_CIUDAD)).getImage();
		backImage=backImage.getScaledInstance(imgDimension.width,imgDimension.height,Image.SCALE_DEFAULT);
    	setMinimumSize(imgDimension);
		setSize(imgDimension);
		setPreferredSize(imgDimension);

		ajustarTamañoAuto(false);		
		ajustarTamañoPared();		
		ajustarTamañoPare();
		
		this.validate();
	}
	
	public void ajustarTamañoAuto(boolean izq)
	{
		//tamaño del auto deberia tres metros de largo
		if(izq)
			imgAuto = new ImageIcon(Util.class.getResource(Constantes.IMG_AUTO_IZQUIERDO)).getImage();
		else
			imgAuto = new ImageIcon(Util.class.getResource(Constantes.IMG_AUTO_DERECHO)).getImage();
		
		float tamañoMetros=34;
		ImageIcon imgIcon=new ImageIcon(backImage);
		
		int withback=imgIcon.getIconWidth();
		
		float withAuto=((3f*withback)/tamañoMetros);
		float proporcion=withAuto/imgAuto.getWidth(this);
		
		imgAuto=imgAuto.getScaledInstance((int)withAuto,(int)(imgAuto.getHeight(this)*proporcion),Image.SCALE_DEFAULT);
		
		imgIcon=null;
	}
	
	public void ajustarTamañoPared()
	{
		imgPared = new ImageIcon(Util.class.getResource(Constantes.IMG_PARED)).getImage();		
		float tamañoMetros=30;
		ImageIcon imgIcon=new ImageIcon(backImage);
		
		int withback=imgIcon.getIconWidth();
		
		float withPared=((10f*withback)/tamañoMetros);
		float proporcion=withPared/imgPared.getWidth(this);
		
		imgPared=imgPared.getScaledInstance((int)withPared,(int)(imgPared.getHeight(this)*proporcion),Image.SCALE_DEFAULT);
		
		imgIcon=null;				
	}
	
	public void ajustarTamañoPare()
	{
		imgPare = new ImageIcon(Util.class.getResource(Constantes.IMG_PARE)).getImage();		
		float tamañoMetros=30;
		ImageIcon imgIcon=new ImageIcon(backImage);
		
		int withback=imgIcon.getIconWidth();
		
		float withPare=((3f*withback)/tamañoMetros);
		float proporcion=withPare/imgPared.getWidth(this);
		
		imgPare=imgPare.getScaledInstance((int)withPare,(int)(imgPare.getHeight(this)*proporcion),Image.SCALE_DEFAULT);	
		
		
		imgIcon=null;
	}
	
	@Override
	public Dimension getSize() {
		return imgDimension;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	@Override
	public void update(Graphics g) {
		
		graphicsCaptura=(Graphics2D)imgTodo.getGraphics();
		
		//g.drawImage(backImage, 0, 0, this);
		graphicsCaptura.drawImage(backImage, 0, 0, this);
		
		//g.drawImage(imgAuto,parametros.getX_pos(), parametros.getY_pos(),this);
		graphicsCaptura.drawImage(imgAuto,parametros.getX_pos(), parametros.getY_pos(),this);
		
		graphicsCaptura.setColor(Color.RED);
		if(izq)
			graphicsCaptura.fillRect(parametros.getPosicionObjetivo(),backImage.getHeight(this)-imgPared.getHeight(this),15,imgPared.getHeight(this));
		else
			graphicsCaptura.fillRect(parametros.getPosicionObjetivo()-15,backImage.getHeight(this)-imgPared.getHeight(this),15,imgPared.getHeight(this));
		
		if(mostrarPared)
		{
			//g.drawImage(imgPared,parametros.getX_posPared(), parametros.getY_posPared(),this);
			graphicsCaptura.drawImage(imgPared,parametros.getX_posPared(), parametros.getY_posPared(),this);
		}
		

		//g.drawImage(imgPare,parametros.getX_posPare() , parametros.getY_posPare(),this);
		graphicsCaptura.drawImage(imgPare,parametros.getX_posPare() , parametros.getY_posPare(),this);
				
		g.drawImage(imgTodo,0,0,this);
		
		g.dispose();
		graphicsCaptura.dispose();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		update(g);		
	}
	
	public Image getImgTodo() {
		return imgTodo;
	}

	public void setImgTodo(Image imgTodo) {
		this.imgTodo = imgTodo;
	}



	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

	}// </editor-fold>
	//GEN-END:initComponents

	public void iniciar() {	
		//habilitarBotones(false);
		run=true;
	}
		
	public void frenar()
	{	
		if(res!=null)
			res.setResValor1(calcularResultado());//Metros al objetivo
		run=false;
		
	}
	
	
	public int calcularPosicionDeFrenado()
	{
		int posFrenado;
		if(izq)
			posFrenado=parametros.getX_pos()+imgAuto.getWidth(this);
		else
			posFrenado=parametros.getX_pos();		
		return posFrenado;
	}
	
	public Double calcularResultado()
	{
		
		Double metrosObjetivo;
		float posFrenado=calcularPosicionDeFrenado();
		float posObt=parametros.getPosicionObjetivo();
		float diferencia;
		
		if(izq)
			diferencia=posObt-posFrenado;		
		else
			diferencia=posFrenado-posObt;
		
		metrosObjetivo=(diferencia*metrosTotal)/(float)backImage.getWidth(this);
		
		/*System.out.println("posFrenado: "+posFrenado);
		System.out.println("posObt: "+posObt);
		System.out.println("diferencia: "+diferencia);
		System.out.println("metrosObjetivo: "+metrosObjetivo);*/
		return metrosObjetivo;
	}
	
	public void arrancar()
	{	
		run=true;
	}
	
	public double calcTiempo(double width)
	{
		double tiempoSleep=0;
		double x_speedMetros=Math.abs(parametros.getX_speed())*1000;
		double mileSegHora=60*60*1000;
			
		double recorrerEnTiempo = (metrosTotal * mileSegHora)/(double)x_speedMetros;
		
		double recorrerEnTiempoNano=recorrerEnTiempo*1000000;
		tiempoSleep=recorrerEnTiempoNano/(width/avancePx);	
				
		return tiempoSleep;		
	}
	
	public boolean isMostrarPared() {
		return mostrarPared;
	}

	public void setMostrarParedTrue() {
		
		this.mostrarPared = true;
		this.repaint();
	}
	
	public void setMostrarParedFalse() {
		this.mostrarPared = false;
		this.repaint();
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	
	@Override
	public void run() {
		
		while (!stop) {
			
			if(!stop && run)
			{
				if ((parametros.getX_pos() > parametros.getWidth()) || (parametros.getX_pos()+imgAuto.getWidth(this))<0) {				
					//this.frenar();
					TramaPsicologico trama=(TramaPsicologico)thTrama.getTrama();
					trama.setFrenoPressed();
				}
				else
				{
					try {
						
						int milisegundos=(int)(nanoSeg/1000000f);					
						
						if(parametros.getX_speed()<0)
							parametros.setX_pos((int)(parametros.getX_pos() - avancePx));
						else
							parametros.setX_pos((int)(parametros.getX_pos() + avancePx));						
			
						PanelAnticipacionAnimacion.this.repaint();
						
						long timeIni=System.currentTimeMillis();
						Thread.sleep(milisegundos);						
						long timeFin=System.currentTimeMillis()-timeIni;
						
					
						/*if(timeFin>milisegundos)
							System.out.println("Tiempo 2: "+timeFin);*/
						
					} catch (Exception ex) {
						throw new RuntimeException(ex);
					}
				}
				
			}
			else
			{
				try
				{
					Thread.sleep(100);
				} catch (Exception ex) {
				}
			}
		}
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	// End of variables declaration//GEN-END:variables

}