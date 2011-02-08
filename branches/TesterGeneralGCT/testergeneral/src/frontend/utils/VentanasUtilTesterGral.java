package frontend.utils;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

import frontend.ventanas.FramePrincipal;

import testerGeneral.domain.Constantes;
import testerGeneral.domain.Propiedad;

public class VentanasUtilTesterGral {

	public static void setFondo(JFrame frame,JDesktopPane dp,JLabel lb,Propiedad propImg)
	{
		if(propImg.getPropBlob()!=null && propImg.getPropBlob().length>1)
			Util.setIcon(lb,propImg.getPropBlob());
		else
			Util.setIcon(lb,Constantes.IMG_FONDO);

		centrar(frame,dp,lb);
	}
	
	public static void centrar(JFrame frame,JDesktopPane dp,JLabel lb)
	{
		Dimension dim = Util.gc.getBounds().getSize();
		Point  p=new Point((dim.width - lb.getIcon().getIconWidth()) / 2,(dim.height - lb.getIcon().getIconHeight()) / 2);
		if(p.x<= 0)
		{
			p.x=0;
		}
		
		if(p.y<= 0)
		{
			p.y=0;
		}
		
		
		lb.setBounds(p.x,p.y,dim.width,dim.height);

		lb.setVerticalAlignment(JLabel.TOP);
		lb.setHorizontalAlignment(JLabel.LEFT);

		if(lb.getParent()==null)
			dp.add(lb);
		
		dp.setLayer(lb, -30000);
	}
	
	public static void setAsMosaico(JFrame frame,JDesktopPane dp,JLabel lb)
	{
		int filas = filasImagen(frame,lb.getIcon().getIconHeight()) + 1;
		int col = columnasImagen(frame,lb.getIcon().getIconWidth()) + 1;
	
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < col; j++) {
				lb.setBounds(j * lb.getIcon().getIconWidth(), i
						* lb.getIcon().getIconHeight(), lb.getIcon()
						.getIconWidth(), lb.getIcon().getIconHeight());
	
				lb.setVerticalAlignment(JLabel.TOP);
				lb.setHorizontalAlignment(JLabel.LEFT);
	
				dp.add(lb);
				dp.setLayer(lb, -30000);
				lb = new JLabel();
				Util.setIcon(lb, Constantes.IMG_FONDO);
	
			}
		}
	}
	
	
	public static int filasImagen(JFrame frame,int height) {
		int filas = 1;
		Dimension dim = frame.getToolkit().getScreenSize();
		filas = (int) dim.getHeight() / height;
		return filas;
	}

	public static int columnasImagen(JFrame frame,int width) {
		int columnas = 1;
		Dimension dim = frame.getToolkit().getScreenSize();
		columnas = (int) dim.getWidth() / width;

		return columnas;
	}
}
