/*
 * GlassPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.components;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author  __USER__
 */
public class GlassPanel extends javax.swing.JPanel {
	private float tran= 0.8f;
	
	/** Creates new form GlassPanel */
	public GlassPanel() {
		this.setLayout(null);
		//initComponents();
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        //Color ppColor =Color.BLUE.brighter().brighter();
		Color ppColor =this.getParent().getBackground().darker().darker().darker().darker();
        ppColor=new Color(ppColor.getRed(),ppColor.getGreen(),ppColor.getBlue(),90);
        g.setColor(ppColor);
        g.fillRect(0,0,this.getWidth(),this.getHeight()); //x,y,width,height
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
