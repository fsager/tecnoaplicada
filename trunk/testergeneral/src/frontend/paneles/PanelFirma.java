/*
 * PanelFirma.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import jpen.PButtonEvent;
import jpen.PKindEvent;
import jpen.PLevel;
import jpen.PLevelEvent;
import jpen.PScrollEvent;
import jpen.PenManager;

/**
 *
 * @author  __USER__
 */
public class PanelFirma extends javax.swing.JPanel implements
		jpen.event.PenListener {

	private Image imgTodo;
	private Graphics2D g2d;
	private float brushSize;
	private BasicStroke stroke;
	private Point2D.Float prevLoc = new Point2D.Float();
	private Point2D.Float loc = new Point2D.Float();

	/** Creates new form PanelFirma */
	public PanelFirma() {
		//initComponents();

		this.setBackground(Color.WHITE);
		Dimension dim = this.getToolkit().getScreenSize();

		int width = (int) (dim.width - (dim.width * 0.2));
		int height = (int) (dim.height - (dim.height * 0.2));

		this.setPreferredSize(new Dimension(width, height));
		this.setSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));

		PenManager pm = new PenManager(this);
		pm.pen.addListener(this);
		pm.pen.levelEmulator.setPressureTriggerForLeftCursorButton(4f);

		this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

		imgTodo = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g2d = (Graphics2D) imgTodo.getGraphics();
		
		clearPantalla();
		
		
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_PURE);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

	}
	
	public Image getImgTodo() {
		return imgTodo;
	}
	
	public void clearPantalla(){
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0,0, imgTodo.getWidth(this), imgTodo.getHeight(this));
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(imgTodo!=null)
			g.drawImage(imgTodo,0,0,this);
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
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 776,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 542,
				Short.MAX_VALUE));
	}// </editor-fold>
	//GEN-END:initComponents

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	// End of variables declaration//GEN-END:variables
	@Override
	public void penButtonEvent(PButtonEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void penKindEvent(PKindEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void penLevelEvent(PLevelEvent ev) {

		// if this event was not a movement, do nothing
		if (!ev.isMovement())
			return;

		// set the brush's size, and opacity relative to the pressure
		float pressure = ev.pen.getLevelValue(PLevel.Type.PRESSURE);
		brushSize = pressure * 20;
		// get the current cursor location
		loc.x = ev.pen.getLevelValue(PLevel.Type.X);
		loc.y = ev.pen.getLevelValue(PLevel.Type.Y);

		if (brushSize > 0) {
				g2d.setColor(Color.BLACK);
				stroke = new BasicStroke(brushSize, BasicStroke.CAP_ROUND, // round line endings
						BasicStroke.JOIN_MITER);

			g2d.setStroke(stroke);
			g2d.draw(new Line2D.Float(prevLoc, loc));
			//g2d2.draw(new Line2D.Float(prevLoc, loc));
		}

		// set the current position to the previous position
		prevLoc.setLocation(loc);
		this.repaint();
	}

	@Override
	public void penScrollEvent(PScrollEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void penTock(long arg0) {
		// TODO Auto-generated method stub

	}
}