/*
 * PanelRecuadroImagen.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author  __USER__
 */
public class PanelRecuadroImagen extends javax.swing.JPanel {

	int width;	
	int height;
	
	/** Creates new form PanelRecuadroImagen */
	public PanelRecuadroImagen(int width,int height) {
		this.width=width;
		this.height=height;
		
		initComponents();
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		float dash1[] = {10.0f};
		BasicStroke dashed = new BasicStroke(10.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f, dash1, 0.0f);
		g2d.setStroke(dashed);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(0,0,width,height);
	}
	
	public Dimension getDimension()
	{
		return new Dimension(width,height);
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