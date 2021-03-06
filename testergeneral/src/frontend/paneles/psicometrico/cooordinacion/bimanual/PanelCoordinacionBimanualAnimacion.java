/*
 * PanelAnticipacionAnimacion.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.psicometrico.cooordinacion.bimanual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.derby.tools.sysinfo;
import org.jfree.chart.plot.ThermometerPlot;
import org.python.modules.synchronize;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.PersonaExamen;
import testerGeneral.threads.ThreadTrama;
import frontend.components.JOptionPaneTesterGral;
import frontend.paneles.Captura;
import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class PanelCoordinacionBimanualAnimacion extends JPanel  implements Runnable, Captura{

	private boolean run = false;
	
	private Graphics2D graphicsCaptura;
	private Image imgTodo;
	private Image imgCaptura;
	private BufferedImage bi;
	private BufferedImage biSecond;
	private Dimension imgDimension;
	private int yDesdeInit;
	private int yDesde;
	private boolean stop=false;
	private int velocidad=0;
	private ThreadTrama thTrama;
	private int xAutoDer;
	private int xAutoIzq;
	//private ImageReader reader;
	private int extraHeigth=1000;
	private int width;
	private int heightReader;
	private int heightPantalla;
	private int widthAuto=20;
	private int heightAuto=20;
	private int erroresDerecho=0;
	private int erroresIzq=0;
	private long tiempoFueraIzq=0;
	private long tiempoFueraDer=0;
	private long tiempoDesdeFueraIzq=0;
	private long tiempoDesdeFueraDer=0;
	
	private boolean isDerFuera=false;
	private boolean isIzqFuera=false;
	
	private Thread thThis=new Thread(this);
	private static final Log log = LogFactory.getLog(PanelCoordinacionBimanualAnimacion.class);
	private PanelCoordinacionBimanual panelCoordinacionBimanual;
	private PersonaExamen personaExamen;
	private String img;
	
	/** Creates new form PanelAnticipacionAnimacion */
	public PanelCoordinacionBimanualAnimacion(PanelCoordinacionBimanual panelCoordinacionBimanual ,Dimension imgDimension,ThreadTrama thTrama,String img,PersonaExamen personaExamen){
		this.panelCoordinacionBimanual=panelCoordinacionBimanual;
		this.personaExamen=personaExamen;		
		this.thTrama=thTrama;
		this.imgDimension=imgDimension;
		this.setName("PanelCoordinacionBimanualAnimacion");
		setMinimumSize(imgDimension);
		setSize(imgDimension);
		setPreferredSize(imgDimension);
		imgTodo = new BufferedImage(imgDimension.width,imgDimension.height,BufferedImage.TYPE_INT_RGB);
		
		setbackImagen(img);
		dibujarAutos();
		
		thThis.setPriority(Thread.MAX_PRIORITY);
		thThis.start();
		thThis.setName("PanelCoordinacionBimanualAnimacion");
		initValores();
	}
	
	public Point centrarImagen(ImageIcon imgIcon)
	{	
		Point point=new Point(0,0);
		if(Util.gc!=null)
		{
			int x=((this.getWidth()- imgIcon.getIconWidth()) / 2);
			int y=((this.getHeight()- imgIcon.getIconHeight()) / 2);
			x=x<0 ? 0 : x;
			y=y<0 ? 0 : y;
			
			point = new Point(x,y);
		}
		
		return point;	
	}
	
	public void inicializarBuffuer()
	{
		/*try
		{
			if(reader!=null)
			{
				if(reader.getInput()!=null)
					((ImageInputStream)reader.getInput()).close();

				reader.dispose();
			}		
		}
		catch(Exception e)
		{}
		finally
		{
			System.gc();
		}*/
	}
	
	public String getImg()
	{
		return img;
	}
	
	public void setImg(String img)
	{
		this.img=img;
	}

	
	public synchronized void  setbackImagen(String img)
	{
		try {
			//inicializarBuffuer();
			setImg(img);
			Iterator readers = ImageIO.getImageReadersByFormatName("jpg");
			ImageReader reader= (ImageReader)readers.next();
		    
			//File f=new File(Util.class.getResource(img).getFile());
		    File f=new File(img).getAbsoluteFile();
			
		    ImageInputStream iis= ImageIO.createImageInputStream(f);
			reader.setInput(iis, true);
			
			width=reader.getWidth(0);
			heightReader=reader.getHeight(0);
			
		    initPosicion();
			
			heightPantalla=imgDimension.height;
			ImageReadParam param = reader.getDefaultReadParam();

		    Rectangle rect = new Rectangle(0,yDesdeInit-extraHeigth,width,(heightPantalla+extraHeigth)); 
		    param.setSourceRegion(rect);
		    
		    bi = reader.read(0, param);
		    yDesde=bi.getHeight()-heightPantalla;

		    rect = new Rectangle(0,yDesdeInit-(extraHeigth*2),width,(heightPantalla+extraHeigth)); 
		    param.setSourceRegion(rect);
		    
		    biSecond = reader.read(0, param);
		    
		    //nuevo
		    iis.close();
		    reader.dispose();
		    
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void setVelocidad(int velocidad)
	{
		this.velocidad=velocidad;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	@Override
	public void update(Graphics g) {
		long tiempoEntr=System.currentTimeMillis();

		if(imgTodo!=null)
		{
			try
			{
				
				if(yDesde<=0)
					yDesde=0;
				
				int anchoImagen=this.bi.getWidth(this);
				int posxImagen=((this.getWidth()- anchoImagen) / 2);
				
				graphicsCaptura=(Graphics2D)imgTodo.getGraphics();
			    
				ImageIcon img=new ImageIcon(bi.getSubimage(0,yDesde,width,heightPantalla));
				
				Point p=centrarImagen(img);
				graphicsCaptura.setColor(Color.WHITE);
				g.setColor(Color.WHITE);
		
				
				graphicsCaptura.fillRect(0, 0, imgDimension.width, imgDimension.height);
				g.fillRect(0,0, imgDimension.width, imgDimension.height);
				
				graphicsCaptura.drawImage(img.getImage(),0,0,this);
				g.drawImage(imgTodo,p.x,p.y,this);
				
				graphicsCaptura.setColor(Color.RED);
				graphicsCaptura.fillOval(xAutoDer-posxImagen, imgDimension.height-100, widthAuto,heightAuto);
				
				g.setColor(Color.RED);
				g.fillOval(xAutoDer, imgDimension.height-100, widthAuto,heightAuto);
	
				graphicsCaptura.setColor(Color.BLUE);
				graphicsCaptura.fillOval(xAutoIzq-posxImagen, imgDimension.height-100, widthAuto,heightAuto);
				g.setColor(Color.BLUE);
				g.fillOval(xAutoIzq, imgDimension.height-100, widthAuto,heightAuto);
				
				
				/*for(int i=0;i<=360;i++)
				{
					int radio=widthAuto/2;
					//int radio=20;
					double angulo=Math.toDegrees(i);
					int coorX=(int)(radio*Math.cos(angulo)-1); 
					int coorY=(int)(radio*Math.sin(angulo)-1); 
					
					int posAutoDer = thTrama.getTramaValida().getPotenciometroDerecho();
					int posAutoIzq= thTrama.getTramaValida().getPotenciometroIzquierdo();
					
					xAutoDer=getPosInPixel(posAutoDer,true);
					xAutoIzq=getPosInPixel(posAutoIzq,false);
					
					int finalCoorX=xAutoDer+(coorX+radio);
					int finalCoorY=yDesde+heightPantalla-100+(coorY)+(radio);
					
					finalCoorY=finalCoorY-1000;
					
					System.out.println("finalCoorX: "+finalCoorX+" finalCoorY: "+finalCoorY);
					g.setColor(Color.GREEN);
					g.drawLine(finalCoorX, finalCoorY, finalCoorX, finalCoorY);
					//g.drawRect(finalCoorX, finalCoorY-heightPantalla-500, 20, 20);					
					//g.fillOval(xAutoIzq, imgDimension.height-100, widthAuto,heightAuto);
				}*/
				
				g.dispose();
				graphicsCaptura.dispose();
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		long tiempo=(System.currentTimeMillis() - tiempoEntr);
		if(tiempo>12)
			log.debug("Tiempo Paint: "+(System.currentTimeMillis() - tiempoEntr));
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

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	
	@Override
	public void run() {
		int i=2;
		boolean exit=false;
		int tiempoSleep=37;//48
		
		if(personaExamen.getPexaTipoExamen().equals(PersonaExamen.TIPO_EXAMEN_PROFECIONAL))
		{
			tiempoSleep=tiempoSleep-((int)(tiempoSleep*0.09));
		}
		
		while (!stop) {
			dibujarAutos();
			if(!stop && run)
			{	
				long timeIni=System.currentTimeMillis();
				try
				{	
					if(this.yDesde>0)
					{						
						this.yDesde-=velocidad;
						repaint();
					}
					else
					{
						if(!exit)
						{
							bi=biSecond.getSubimage(0,0,biSecond.getWidth(),biSecond.getHeight());
							
							i++;
							int y=yDesdeInit-(extraHeigth*i);
							int h=(heightPantalla+extraHeigth);
							
							if(y<0)
							{
								h=h+y;
								y=0;
								exit=true;
							}
							
							final int yRun=y;
							final int hRun=h;
							final int iRun=i;
							
							
							final boolean read=true;
							
							
							Thread t=new Thread()
									{	public void run() {
											try
											{
												Iterator readers = ImageIO.getImageReadersByFormatName("jpg");
												ImageReader reader= (ImageReader)readers.next();
											    File f=new File(img).getAbsoluteFile();
												
											    ImageInputStream iis= ImageIO.createImageInputStream(f);
												reader.setInput(iis, true);
												
												ImageReadParam param = reader.getDefaultReadParam();
											    Rectangle rect = new Rectangle(0,yRun,width,hRun); 
											    param.setSourceRegion(rect);
										    	biSecond = reader.read(0,param);	
											    
										    	iis.close();
										    	reader.dispose();
										    	
											} catch (Exception ex) {
												throw new RuntimeException(ex);
											}	
										}
									};
							t.setName("Read biSecond buffer");			
							t.start();

						    yDesde=bi.getHeight()-heightPantalla;
						}
						else
						{
							panelCoordinacionBimanual.finalizarExamen();
							run=false;
						}
					}
					Thread.sleep(tiempoSleep);					
					
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
				
				long timepo=System.currentTimeMillis() - timeIni;
				
			}
			else
			{
				try
				{					
					initPosicion();
					repaint();
					Thread.sleep(100);//300
					i=2;
					exit=false;
				} catch (Exception ex) {
				}
			}			
		}
	}

	
	public void initPosicion()
	{
		try
		{
			this.yDesdeInit=(int)(heightReader-imgDimension.getHeight());
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
		
	public void initValores()
	{
		isDerFuera=false;
		isIzqFuera=false;
		erroresDerecho=0;
		erroresIzq=0;
		tiempoFueraIzq=0;
		tiempoFueraDer=0;
	}

	//posPotenciometro 0..127
	//posPotenciometro -127..0
	
	public int getPosInPixel(int posPotenciometro,boolean der)
	{
		//if(!der)
			//System.out.println("posPotenciometro: "+posPotenciometro);
		
		int anchoImagen=this.bi.getWidth(this);
		int medio=anchoImagen/2;
		int posxImagen=((this.getWidth()- anchoImagen) / 2);
		
		int rangoValores=medio-posxImagen;
		
		float pixelPorPotenciometro=(float)rangoValores/255f;
		
		if(posPotenciometro>0)
			posPotenciometro=255-posPotenciometro;
		else
			posPotenciometro=Math.abs(posPotenciometro);
				
		float valorPotenciometroEnPixel=posPotenciometro*pixelPorPotenciometro;
		

		if(der)
			return (int)(posxImagen+medio+valorPotenciometroEnPixel);
		else
			return (int)(posxImagen+valorPotenciometroEnPixel);
	}
	
	public void dibujarAutos()
	{
		int posAutoDer = thTrama.getTramaValida().getPotenciometroDerecho();
		int posAutoIzq= thTrama.getTramaValida().getPotenciometroIzquierdo();
		
		xAutoDer=getPosInPixel(posAutoDer,true);
		xAutoIzq=getPosInPixel(posAutoIzq,false);
		
		if(run)
		{
			isOutOfTheRoadDerecha();
			isOutOfTheRoadIzquierda();
		}
	}
	
	public boolean isInInitPosition()
	{
		return !isOutOfTheRoadDerecha() && !isOutOfTheRoadIzquierda();
	}
	
	public boolean isOutOfTheRoadDerecha()
	{
		boolean isOutOfTheRoad=isOutOfTheRoad(xAutoDer);
		if(isOutOfTheRoad && !isDerFuera)
		{
			Util.playSound(Constantes.SOUND_ERROR,10);
			erroresDerecho++;
			isDerFuera=true;
			tiempoDesdeFueraDer=System.currentTimeMillis();
		}
		else if(!isOutOfTheRoad && isDerFuera)
		{
			isDerFuera=false;
			tiempoFueraDer+=(System.currentTimeMillis()-tiempoDesdeFueraDer);
		}
		
		return isOutOfTheRoad;
	}
	
	public boolean isOutOfTheRoadIzquierda()
	{
		boolean isOutOfTheRoad=isOutOfTheRoad(xAutoIzq);
		if(isOutOfTheRoad && !isIzqFuera)
		{
			Util.playSound(Constantes.SOUND_ERROR,10);
			erroresIzq++;
			isIzqFuera=true;
			tiempoDesdeFueraIzq=System.currentTimeMillis();
		}
		else if(!isOutOfTheRoad && isIzqFuera)
		{
			isIzqFuera=false;
			tiempoFueraIzq+=(System.currentTimeMillis()-tiempoDesdeFueraIzq);
		}
		
		return isOutOfTheRoad;
	}
	
	public boolean isOutOfTheRoad(int x)
	{
		int anchoImagen=this.bi.getWidth(this);
		int posxImagen=((this.getWidth()- anchoImagen) / 2);
		
		for(int i=0;i<=360;i++)
		{
			int radio=widthAuto/2;
			//int radio=20;
			double angulo=Math.toDegrees(i);
			int coorX=(int)(radio*Math.cos(angulo)-1); 
			int coorY=(int)(radio*Math.sin(angulo)-1); 
			
			int finalCoorX=x-posxImagen+(coorX+radio);
			int finalCoorY=yDesde+heightPantalla-100+(coorY)+(radio);
			int pixel=bi.getRGB(finalCoorX,finalCoorY);
			
		
			if(pixel==-16777216)
					return true;
			
		}
		return false;
	}
	
	public void finalizar()
	{
		try
		{
			stop=true;
			run=false;
			graphicsCaptura.dispose();
			graphicsCaptura=null;
			imgTodo=null;
			imgCaptura=null;
			bi.flush();
			bi=null;
			biSecond.flush();
			biSecond=null;
			imgDimension=null;
			System.gc();
		}
		catch(Exception e)
		{}
	}
	

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	// End of variables declaration//GEN-END:variables


	public Graphics2D getGraphicsCaptura() {
		return graphicsCaptura;
	}

	public void setGraphicsCaptura(Graphics2D graphicsCaptura) {
		this.graphicsCaptura = graphicsCaptura;
	}

	public Image getImgCaptura() {
		return imgCaptura;
	}

	public void setImgCaptura(Image imgCaptura) {
		this.imgCaptura = imgCaptura;
	}

	public BufferedImage getBi() {
		return bi;
	}

	public void setBi(BufferedImage bi) {
		this.bi = bi;
	}

	public BufferedImage getBiSecond() {
		return biSecond;
	}

	public void setBiSecond(BufferedImage biSecond) {
		this.biSecond = biSecond;
	}

	public Dimension getImgDimension() {
		return imgDimension;
	}

	public void setImgDimension(Dimension imgDimension) {
		this.imgDimension = imgDimension;
	}

	public int getYDesdeInit() {
		return yDesdeInit;
	}

	public void setYDesdeInit(int desdeInit) {
		yDesdeInit = desdeInit;
	}

	public int getYDesde() {
		return yDesde;
	}

	public void setYDesde(int desde) {
		yDesde = desde;
	}

	public ThreadTrama getThTrama() {
		return thTrama;
	}

	public void setThTrama(ThreadTrama thTrama) {
		this.thTrama = thTrama;
	}

	public int getXAutoDer() {
		return xAutoDer;
	}

	public void setXAutoDer(int autoDer) {
		xAutoDer = autoDer;
	}

	public int getXAutoIzq() {
		return xAutoIzq;
	}

	public void setXAutoIzq(int autoIzq) {
		xAutoIzq = autoIzq;
	}

	/*public ImageReader getReader() {
		return reader;
	}

	public void setReader(ImageReader reader) {
		this.reader = reader;
	}*/

	public int getExtraHeigth() {
		return extraHeigth;
	}

	public void setExtraHeigth(int extraHeigth) {
		this.extraHeigth = extraHeigth;
	}

	public int getHeightPantalla() {
		return heightPantalla;
	}

	public void setHeightPantalla(int heightPantalla) {
		this.heightPantalla = heightPantalla;
	}

	public int getWidthAuto() {
		return widthAuto;
	}

	public void setWidthAuto(int widthAuto) {
		this.widthAuto = widthAuto;
	}

	public int getHeightAuto() {
		return heightAuto;
	}

	public void setHeightAuto(int heightAuto) {
		this.heightAuto = heightAuto;
	}

	public int getErroresDerecho() {
		return erroresDerecho;
	}

	public void setErroresDerecho(int erroresDerecho) {
		this.erroresDerecho = erroresDerecho;
	}

	public int getErroresIzq() {
		return erroresIzq;
	}

	public void setErroresIzq(int erroresIzq) {
		this.erroresIzq = erroresIzq;
	}

	public long getTiempoFueraIzq() {
		return tiempoFueraIzq;
	}

	public void setTiempoFueraIzq(long tiempoFueraIzq) {
		this.tiempoFueraIzq = tiempoFueraIzq;
	}

	public long getTiempoFueraDer() {
		return tiempoFueraDer;
	}

	public void setTiempoFueraDer(long tiempoFueraDer) {
		this.tiempoFueraDer = tiempoFueraDer;
	}

	public long getTiempoDesdeFueraIzq() {
		return tiempoDesdeFueraIzq;
	}

	public void setTiempoDesdeFueraIzq(long tiempoDesdeFueraIzq) {
		this.tiempoDesdeFueraIzq = tiempoDesdeFueraIzq;
	}

	public long getTiempoDesdeFueraDer() {
		return tiempoDesdeFueraDer;
	}

	public void setTiempoDesdeFueraDer(long tiempoDesdeFueraDer) {
		this.tiempoDesdeFueraDer = tiempoDesdeFueraDer;
	}

	public boolean isDerFuera() {
		return isDerFuera;
	}

	public void setDerFuera(boolean isDerFuera) {
		this.isDerFuera = isDerFuera;
	}

	public boolean isIzqFuera() {
		return isIzqFuera;
	}

	public void setIzqFuera(boolean isIzqFuera) {
		this.isIzqFuera = isIzqFuera;
	}

	public Thread getThThis() {
		return thThis;
	}

	public void setThThis(Thread thThis) {
		this.thThis = thThis;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public static Log getLog() {
		return log;
	}	
}