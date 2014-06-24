/*
 * PanelPercepcionReaccionAnimacion.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.psicometrico.reaccionsimple;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.threads.ThreadTrama;
import frontend.paneles.Captura;
import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class PanelReaccionSimpleAnimacion extends javax.swing.JPanel implements Captura, Runnable{

	public final static String ESTADO_INI="INICIALIZADO";
	public final static String ESTADO_VERDE="VERDE";
	public final static String ESTADO_ROJO="ROJO";
	
	private Image imgTodo;
	private boolean run = false;
	private boolean stop = false;
	private Thread th=new Thread(this);
	private PanelReaccionSimple parent;
	private Image backImage;
	private final int TIEMPO_DESDE =Integer.valueOf(ContextManager.getProperty("EXAMEN.REACCION.SIMPLE.TIEMPO.VERDE.DESDE"));
	private final int TIEMPO_HASTA =Integer.valueOf(ContextManager.getProperty("EXAMEN.REACCION.SIMPLE.TIEMPO.VERDE.HASTA"));
	private String estado=ESTADO_INI;
	private Image ko;


	
	/** Creates new form PanelPercepcionReaccionAnimacion */
	public PanelReaccionSimpleAnimacion(PanelReaccionSimple parent)
	{
		initComponents();
		
		this.parent=parent;
		th.start();		
		backImage = new ImageIcon(Util.class.getResource(Constantes.IMG_TEST_REACCION_SIMPLE_SEM)).getImage();
		
		imgTodo = new BufferedImage(backImage.getWidth(this),backImage.getHeight(this),BufferedImage.TYPE_INT_RGB);
	}
	
	public void init()
	{
		backImage = new ImageIcon(Util.class.getResource(Constantes.IMG_TEST_REACCION_SIMPLE_SEM)).getImage();
		run=false;
		estado=ESTADO_INI;
		this.repaint();
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
				backImage = new ImageIcon(Util.class.getResource(Constantes.IMG_TEST_REACCION_SIMPLE_SEM_VERDE)).getImage();
				this.repaint();
				estado=ESTADO_VERDE;
				try
				{
					Random generator = new Random();
					int tiempoSleep =(generator.nextInt(TIEMPO_HASTA-TIEMPO_DESDE)+TIEMPO_DESDE)+1000;
					th.sleep(tiempoSleep);
					
					if(run)
					{
						backImage = new ImageIcon(Util.class.getResource(Constantes.IMG_TEST_REACCION_SIMPLE_SEM_ROJO)).getImage();
						this.repaint();
						estado=ESTADO_ROJO;
						run=false;
						parent.setInstanteSemaforoRojo(System.currentTimeMillis());
						parent.incrementarEjecucion();
						
					}
				}
				catch (Exception e) {
						//e.printStackTrace();
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
	
	public void interrupt()
	{
		th.interrupt();
	}

	public void setKo()
	{	
		ko=new ImageIcon(Util.class.getResource(Constantes.IMG_PUL_KO)).getImage();
		ko=ko.getScaledInstance(350,350,Image.SCALE_SMOOTH);
	}	
	
	public void setKoToNull()
	{	
		ko=null;
	}	

	
	public void paint(Graphics g) {
		
		super.paint(g);
	
		if(ko==null && backImage!=null)
		{
				imgTodo.flush();

				Point point=centrarImagen(backImage);
				Graphics2D g2d2=(Graphics2D)imgTodo.getGraphics();
				Graphics2D g2d1 = (Graphics2D) g;
				
				g2d2.setColor(Color.WHITE);
				g2d2.fillRect(0,0,imgTodo.getWidth(this),imgTodo.getHeight(this));
				
				g2d1.drawImage(backImage,point.x, point.y, this);
				g2d2.drawImage(backImage,0, 0, this);
				
				g2d1.dispose();
				g2d2.dispose();
		}
		else if(ko!=null)
		{
			imgTodo.flush();

			Point point=centrarImagen(ko);
			Graphics2D g2d2=(Graphics2D)imgTodo.getGraphics();
			Graphics2D g2d1 = (Graphics2D) g;
			
			g2d2.setColor(Color.WHITE);
			g2d2.fillRect(0,0,imgTodo.getWidth(this),imgTodo.getHeight(this));
			
			g2d1.drawImage(ko,point.x, point.y, this);
			g2d2.drawImage(ko,0, 0, this);
			
			g2d1.dispose();
			g2d2.dispose();
		}
	}
	
	public Point centrarImagen(Image img)
	{	
		if(Util.gc!=null)
		{
			Point point = new Point((this.getWidth()- img.getWidth(this)) / 2,(this.getHeight()- img.getHeight(this)) / 2);
			
			return point;
		}
		else
			return new Point(0,0);
		
	}
	

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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