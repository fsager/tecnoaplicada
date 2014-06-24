/*
 * PanelCaptura.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.examenes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageReadParam;
import javax.swing.SwingUtilities;

import frontend.paneles.Captura;

/**
 *
 * @author  __USER__
 */
public class PanelCaptura extends javax.swing.JPanel implements Runnable {

	Dimension dim=new Dimension(813,320);
	private int SCALE_AREA=Image.SCALE_AREA_AVERAGING;
	
	/** Creates new form PanelCaptura */
	public PanelCaptura(Captura panelCaptura) {
		this.panelCaptura = panelCaptura;
		SCALE_AREA=Image.SCALE_FAST;
		//SCALE_AREA=Image.SCALE_AREA_AVERAGING;
		thThis.setDaemon(true);
		thThis.setPriority(Thread.MIN_PRIORITY);
		thThis.start();
		initComponents();
	}
	
	public PanelCaptura(Captura panelCaptura,Dimension dim) {
		this.panelCaptura = panelCaptura;
		SCALE_AREA=Image.SCALE_AREA_AVERAGING;
		this.dim=dim;
		
		thThis.start();
		
		initComponents();
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
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 547,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 408,
				Short.MAX_VALUE));
	}// </editor-fold>
	//GEN-END:initComponents

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	// End of variables declaration//GEN-END:variables

	@Override
	public void run() {

		while (run) {

			if (panelCaptura != null && panelCaptura.getImgTodo() != null) {
				imgAnimacion = panelCaptura.getImgTodo();
				
				int heigth=dim.height;
				int width=dim.width;
				
				if(imgAnimacion.getWidth(this)<width)
					width=imgAnimacion.getWidth(this);
				
				if(imgAnimacion.getHeight(this)<heigth)
					heigth=imgAnimacion.getHeight(this);

				//Aca se pierde timepo!!!!!! Validar!!!
				imgAnimacion = imgAnimacion.getScaledInstance(width, heigth,SCALE_AREA);
				repaint();
			}

			try {
				Thread.sleep(628);//328

			} catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (imgAnimacion != null)
			g.drawImage(imgAnimacion,0,0, this);
	}

	
	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}


	private Thread thThis = new Thread(this);
	private Image imgAnimacion;
	private boolean run = true;
	private boolean src;
	private Captura panelCaptura;
	//private boolean capturar;

}