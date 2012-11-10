/*
 * PanelPercepcionReaccionAnimacion.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.psicometrico.percepcionreaccion;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.pushingpixels.substance.internal.fonts.Fonts;


import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import testerGeneral.domain.Constantes;
import testerGeneral.threads.ThreadTrama;
import examenes.psicometrico.domain.ParametrosPercepcionReacion;
import frontend.components.JOptionPaneTesterGral;
import frontend.paneles.Captura;
import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class PanelPercepcionReaccionAnimacion extends javax.swing.JPanel implements Captura, Runnable{

	private List<ParametrosPercepcionReacion> parametros;
	private int position=0;
	private Image imgTodo;
	private int altoMsj=50;
	private int separacionImgText=20;
	private boolean run = false;
	private boolean stop = false;
	private boolean aprend=false;
	private int tiempoSleep=0;
	private Image ok;
	private Image entrePulsadores;
	private Image ko;
	private Thread th=new Thread(this);
	private PanelPercepcionReaccion parent;
	private ThreadTrama thTrama;
	private Dimension dim;
	private String img;
	
	/** Creates new form PanelPercepcionReaccionAnimacion */
	public PanelPercepcionReaccionAnimacion(List<ParametrosPercepcionReacion> parametros,PanelPercepcionReaccion parent,String img)
	{
		this.parent=parent;
		th.start();
		this.img=img;
		if(parametros==null)
		{
			parametros=new ArrayList<ParametrosPercepcionReacion>();
			Image backImage = new ImageIcon(Util.class.getResource(img)).getImage();
			ParametrosPercepcionReacion parametrosPercepcionReacion=new ParametrosPercepcionReacion();
			parametrosPercepcionReacion.setImg(backImage);
			parametros.add(parametrosPercepcionReacion);
			
		}
		
		this.parametros=parametros;		
		initComponents();
	}
	
	public void setDim(Dimension dim) {
		this.dim = dim;
	}	
	
	public void init()
	{
		parametros=new ArrayList<ParametrosPercepcionReacion>();
		Image backImage = new ImageIcon(Util.class.getResource(img)).getImage();
		ParametrosPercepcionReacion parametrosPercepcionReacion=new ParametrosPercepcionReacion();
		parametrosPercepcionReacion.setImg(backImage);
		parametros.add(parametrosPercepcionReacion);
		this.setAprend(false);
		this.parametros=parametros;		
		
		position=0;
		
		this.repaint();
	}

	
	
	public int getTiempoSleep() {
		return tiempoSleep;
	}

	public void setTiempoSleep(int tiempoSleep) {
		this.tiempoSleep = tiempoSleep;
	}

	public List<ParametrosPercepcionReacion> getParametros() {
		return parametros;
	}

	public void setParametros(List<ParametrosPercepcionReacion> parametros) {
		this.parametros = parametros;
	}

	public int setPosition(int position) {
		return this.position=position;
	}
	
	
	public int getPosition() {
		return position;
	}
	

	public Image getImgTodo() {
		return imgTodo;
	}


	public void setImgTodo(Image imgTodo) {
		this.imgTodo = imgTodo;
	}
	
	

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}
	public boolean isAprend() {
		return aprend;
	}

	public void setAprend(boolean aprend) {
		this.aprend = aprend;
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
			
			try
			{
				if(!stop && run)
				{
					
					int pos=this.getPosition();
					repaint();
					
					//long tiempo=System.currentTimeMillis();
					//System.out.println("pos: "+pos+" parametros.get((pos)).getPulsador() "+parametros.get((pos)).getPulsador());
					try
					{
						th.sleep(tiempoSleep);
					}
					catch (Exception e) {
					}
					
					//System.out.println("this.getPosition(): "+this.getPosition()+" parametros.get((pos)).getPulsador() "+parametros.get((this.getPosition())).getPulsador());
					if(pos==this.getPosition())
					{
						//System.out.println("pos==this.getPosition(): "+(pos==this.getPosition()));
						//System.out.println("Tiempo: "+(System.currentTimeMillis()-tiempo));
						if(pos<parametros.size() && parametros.get((pos)).getPulsador()!=null)
						{
							thTrama.setEjecucion(thTrama.getEjecucion()+1);
							parent.validarBtnPressedExamen(parametros.get((pos)).getPulsador());
						}
					}
					
					repaint();
				}
				else if(aprend)
				{
					long timeIni=System.currentTimeMillis();
					long timeSlepped=0;
					boolean btnPressed=thTrama.getTramaValida().isAnyButtonPress();
					while(!btnPressed && timeSlepped<tiempoSleep)
					{
						try
						{
							th.sleep(10);
						}
						catch (Exception e) {
						}
						
						btnPressed=thTrama.getTramaValida().isAnyButtonPress();
						
						timeSlepped+=(System.currentTimeMillis()-timeIni);
						timeIni=System.currentTimeMillis();
					}
					int pos=this.getPosition();
					parent.validarBtnPressedAprendizaje(parametros.get((pos)).getPulsador());
					
					aprend=false;
				}
				else
				{
					try
					{
						Thread.sleep(100);
					} catch (Exception ex) {
					}
				}
			} catch (Exception ex) {
			}
		}
	}

	public ThreadTrama getThTrama() {
		return thTrama;
	}

	public void setThTrama(ThreadTrama thTrama) {
		this.thTrama = thTrama;
	}

	public void paint(Graphics g) {
		
		try
		{
			if(position<parametros.size())
			{
				super.paint(g);
				Image img=parametros.get(position).getImg();
				String sonido=parametros.get(position).getSonido();
				ImageIcon imgIcon=new ImageIcon(img);
			
				
				if(ok==null && ko==null && entrePulsadores==null)
				{
		
					if(parametros!=null && position< parametros.size())
					{
						//System.out.println("ok==null && ko==null && entrePulsadores==null: position: "+ position);
						
						imgTodo = new BufferedImage(imgIcon.getIconWidth(),imgIcon.getIconHeight()+altoMsj+separacionImgText,BufferedImage.TYPE_INT_RGB);
		
						Point point=centrarImagen(imgIcon);
						Graphics2D gPanelCaptura=(Graphics2D)imgTodo.getGraphics();
						Graphics2D gActual = (Graphics2D) g;
						
						gPanelCaptura.setColor(Color.WHITE);			
						gPanelCaptura.fillRect(0,0,imgIcon.getIconWidth(),imgIcon.getIconHeight()+altoMsj+separacionImgText);
						
			
						gActual.drawImage(img,point.x, point.y, this);
						gPanelCaptura.drawImage(img,0,0,this);
						
						if(parametros.get(position).getText()!=null)
						{	
							Font font=new Font(Fonts.SEGOE_UI_NAME,Font.BOLD,25);				
							
							float dash1[] = {10.0f};
							BasicStroke dashed = new BasicStroke(5f);
							
							gActual.setStroke(dashed);
							gActual.setColor(Color.BLACK);
							gActual.setFont(font);
							
							gActual.drawRect(point.x,point.y+img.getWidth(this)+separacionImgText,img.getHeight(this),altoMsj);
							gActual.drawString(parametros.get(position).getText(),point.x+10,point.y+imgIcon.getIconHeight()+separacionImgText+38);
							
							gPanelCaptura.setStroke(dashed);
							gPanelCaptura.setColor(Color.BLACK);
							gPanelCaptura.setFont(font);
							
							gPanelCaptura.drawRect(0,imgIcon.getIconHeight()+separacionImgText-10,imgIcon.getIconWidth(),altoMsj);
							gPanelCaptura.drawString(parametros.get(position).getText(),10,imgIcon.getIconHeight()+separacionImgText+28);					
						}
						
						if(sonido!=null)
							Util.playSound(sonido,50);
						
						gActual.dispose();
						gPanelCaptura.dispose();
					}
				}
				else
				{
					if(ok!=null && parametros!=null && position< parametros.size())
					{
						//System.out.println("OK OK OKOKOKOKOKOK: position: "+position);
						imgTodo = new BufferedImage(imgIcon.getIconWidth(),imgIcon.getIconHeight()+altoMsj+separacionImgText,BufferedImage.TYPE_INT_RGB);
		
						Point point=centrarImagen(ok);
						Graphics2D g2d2=(Graphics2D)imgTodo.getGraphics();
						Graphics2D g2d1 = (Graphics2D) g;
						
						g2d2.setColor(Color.WHITE);			
						g2d2.fillRect(0,0,imgIcon.getIconWidth(),imgIcon.getIconHeight()+altoMsj+separacionImgText);
						
						g2d1.drawImage(ok,point.x, point.y, this);
						g2d2.drawImage(ok,0,0,this);
						g2d1.dispose();
						g2d2.dispose();
					}else if(ko!=null && parametros!=null && position< parametros.size())
					{			
						//System.out.println("KO KO KO KO KOKOKO: position: "+position);
						imgTodo = new BufferedImage(imgIcon.getIconWidth(),imgIcon.getIconHeight()+altoMsj+separacionImgText,BufferedImage.TYPE_INT_RGB);
		
						Point point=centrarImagen(ko);
						Graphics2D g2d2=(Graphics2D)imgTodo.getGraphics();
						Graphics2D g2d1 = (Graphics2D) g;
						
						g2d2.setColor(Color.WHITE);			
						g2d2.fillRect(0,0,imgIcon.getIconWidth(),imgIcon.getIconHeight()+altoMsj+separacionImgText);
						
						g2d1.drawImage(ko,point.x, point.y, this);
						g2d2.drawImage(ko,0,0,this);
						g2d1.dispose();
						g2d2.dispose();
					}else if(entrePulsadores!=null && parametros!=null && position< parametros.size())
					{	
						//System.out.println("entrePulsadores entrePulsadores entrePulsadores: position: "+position);
						imgTodo = new BufferedImage(imgIcon.getIconWidth(),imgIcon.getIconHeight()+altoMsj+separacionImgText,BufferedImage.TYPE_INT_RGB);
		
						Point point=centrarImagen(entrePulsadores);
						Graphics2D g2d2=(Graphics2D)imgTodo.getGraphics();
						Graphics2D g2d1 = (Graphics2D) g;
						
						g2d2.setColor(Color.WHITE);			
						g2d2.fillRect(0,0,imgIcon.getIconWidth(),imgIcon.getIconHeight()+altoMsj+separacionImgText);
						
						g2d1.drawImage(entrePulsadores,point.x, point.y, this);
						g2d2.drawImage(entrePulsadores,0,0,this);
						g2d1.dispose();
						g2d2.dispose();
					}
				}
			}
		}
		catch(Exception e)
		{
			
		}

	}
	
	public void setOkToNull()
	{
		ok=null;
	}
	
	public void setOk()
	{
		ok=new ImageIcon(Util.class.getResource(Constantes.IMG_PUL_OK)).getImage();
		ok=ok.getScaledInstance(350,350,Image.SCALE_SMOOTH);
	}
	
	public void setKoToNull()
	{
		ko=null;
	}
	
	
	public void setEntrePulsadores()
	{
		entrePulsadores=new ImageIcon(Util.class.getResource(Constantes.IMG_ENTRE_PULSADORES)).getImage();
		//entrePulsadores=entrePulsadores.getScaledInstance(350,350,Image.SCALE_SMOOTH);
	}
	
	public void setEntrePulsadoresToNull()
	{
		entrePulsadores=null;
	}
	
	public void setKo()
	{
		ko=new ImageIcon(Util.class.getResource(Constantes.IMG_PUL_KO)).getImage();
		ko=ko.getScaledInstance(350,350,Image.SCALE_SMOOTH);
	}
	
	
	public Point centrarImagen(Image img)
	{			
		ImageIcon imgIcon=new ImageIcon(img);
		
		return centrarImagen(imgIcon);	
	}
	
	public Point centrarImagen(ImageIcon imgIcon)
	{	
		Point point=new Point(0,0);
		if(Util.gc!=null)
		{
			int x=((this.getWidth()- imgIcon.getIconWidth()) / 2);
			int y=(this.getHeight() - (imgIcon.getIconHeight()+altoMsj+separacionImgText)) / 2;
			//Point point = new Point((Util.gc.getBounds().width- imgIcon.getIconWidth()) / 2,(Util.gc.getBounds().height - (imgIcon.getIconHeight()+altoMsj+separacionImgText)) / 2);
			x=x<0 ? 0 : x;
			y=y<0 ? 0 : y;
			
			point = new Point(x,y);
		}
		
		return point;	
	}


	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300,
				Short.MAX_VALUE));
	}// </editor-fold>
	//GEN-END:initComponents

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	// End of variables declaration//GEN-END:variables

}