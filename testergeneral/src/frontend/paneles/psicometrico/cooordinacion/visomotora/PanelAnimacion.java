/*
 * PanelAnimacion.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.paneles.psicometrico.cooordinacion.visomotora;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

import org.pushingpixels.substance.internal.fonts.Fonts;


/**
 *
 * @author  __USER__
 */
public class PanelAnimacion extends javax.swing.JPanel {
	
	private int pulsadorAPresionar=0;
	private int errores=0;
	private int aciertosUno=0;
	private int aciertosDos=0;
	private int aciertosTres=0;
	private int aciertosCuatro=0;
	
	/** Creates new form PanelAnimacion */
	public PanelAnimacion() {
		initComponents();
	}

	public void setPulsadorAPresionar(int pulsadorAPresionar) {
		this.pulsadorAPresionar = pulsadorAPresionar;
	}
	
	public int getErrores() {
		return errores;
	}

	public void setErrores(int errores) {
		this.errores = errores;
	}

	public int getAciertosUno() {
		return aciertosUno;
	}

	public void setAciertosUno(int aciertosUno) {
		this.aciertosUno = aciertosUno;
	}

	public int getAciertosDos() {
		return aciertosDos;
	}

	public void setAciertosDos(int aciertosDos) {
		this.aciertosDos = aciertosDos;
	}

	public int getAciertosTres() {
		return aciertosTres;
	}

	public void setAciertosTres(int aciertosTres) {
		this.aciertosTres = aciertosTres;
	}

	public int getAciertosCuatro() {
		return aciertosCuatro;
	}

	public void setAciertosCuatro(int aciertosCuatro) {
		this.aciertosCuatro = aciertosCuatro;
	}

	public int getPulsadorAPresionar() {
		return pulsadorAPresionar;
	}


	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		Font font=new Font(Fonts.SEGOE_UI_NAME,Font.BOLD,18);
		g.setFont(font);
		
		int hOvalPulsador=70;
		int wOvalPulsador=70;
		int hOvalLuz=40;
		int wOvalLuz=40;
				
		int h=((this.getHeight()-hOvalPulsador)/2);
		int x=((this.getWidth())-wOvalPulsador)/2;
		int separacionIzqX=40;
		int separacionTopY=40;
		int separacionTexto=30;		
		int separacionXPulzadorLuz=20;
		
		
		//g.drawString("Tiempo total de permanecía en punteo: "+tiempoDentro,0,separacionTopY);
		
		font=new Font(Fonts.SEGOE_UI_NAME,Font.BOLD,25);
		g.setFont(font);
		
		g.drawOval(x,separacionTopY,wOvalPulsador,hOvalPulsador);//2
		g.drawString("1",x-separacionTexto,separacionTopY);//2
		if(pulsadorAPresionar==1)
		{
			g.setColor(Color.yellow);
			g.fillOval(x,separacionTopY,wOvalPulsador,hOvalPulsador);
			g.setColor(Color.black);
		}
		g.drawString(""+aciertosUno,x+(wOvalPulsador/2)-7,separacionTopY+(hOvalPulsador/2)+10);//2
		
		g.drawOval(this.getWidth()-separacionIzqX-wOvalPulsador,h,wOvalPulsador,hOvalPulsador);//3
		g.drawString("2",this.getWidth()-separacionIzqX-wOvalPulsador-separacionTexto,h);//3
		if(pulsadorAPresionar==2)
		{
			g.setColor(Color.yellow);
			g.fillOval(this.getWidth()-separacionIzqX-wOvalPulsador,h,wOvalPulsador,hOvalPulsador);
			g.setColor(Color.black);
		}
		g.drawString(""+aciertosDos,this.getWidth()-separacionIzqX-wOvalPulsador+(wOvalPulsador/2)-7,h+(hOvalPulsador/2)+10);//2
		
		g.drawOval(x,this.getHeight()-separacionTopY-hOvalPulsador,wOvalPulsador,hOvalPulsador);//4
		g.drawString("3",x-separacionIzqX+10,this.getHeight()-separacionTopY-hOvalPulsador);//4
		if(pulsadorAPresionar==3)
		{
			g.setColor(Color.yellow);
			g.fillOval(x,this.getHeight()-separacionTopY-hOvalPulsador,wOvalPulsador,hOvalPulsador);
			g.setColor(Color.black);
		}
		g.drawString(""+aciertosTres,x+(wOvalPulsador/2)-7,this.getHeight()-separacionTopY-hOvalPulsador+(hOvalPulsador/2)+10);//2
		
		g.drawOval(separacionIzqX,h,wOvalPulsador,hOvalPulsador);//1
		g.drawString("4",separacionIzqX-separacionTexto,h);//1
		if(pulsadorAPresionar==4)
		{
			g.setColor(Color.yellow);
			g.fillOval(separacionIzqX,h,wOvalPulsador,hOvalPulsador);
			g.setColor(Color.black);
		}
		g.drawString(""+aciertosCuatro,separacionIzqX+(wOvalPulsador/2)-7,h+(hOvalPulsador/2)+10);//1

		
		//Error
		g.drawRect(x-30,h,wOvalPulsador+60,hOvalPulsador);
		g.drawString("Errores: "+errores,x+(wOvalPulsador/2)-64,h+(hOvalPulsador/2)+10);
		
		

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