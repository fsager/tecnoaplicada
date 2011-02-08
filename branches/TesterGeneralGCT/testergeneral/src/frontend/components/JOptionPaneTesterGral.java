package frontend.components;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import frontend.utils.Util;
import frontend.ventanas.JInternalFrameTesterGral;

public class JOptionPaneTesterGral extends javax.swing.JInternalFrame {
	
	public static int showInternal(String message,String titulo,int optionType)
	{
		final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral(titulo, false, false, false, false);
		PanelMensajes panelM=new PanelMensajes(internalframe,JOptionPane.YES_NO_OPTION,optionType,message);
		internalframe.add(panelM);
		internalframe.pack();
		
		Util.centrarIframes(internalframe);
		internalframe.doModal(Util.framePrincipal.getRootPane());
		internalframe.setVisible(true);

		return panelM.getSelOpt();		
	}
	
	
	public static int showInternal(JPanel parent,String message,String titulo,int optionType)
	{
		final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral(titulo, false, false, false, false);
		PanelMensajes panelM=new PanelMensajes(internalframe,JOptionPane.YES_NO_OPTION,optionType,message);
		internalframe.add(panelM);
		internalframe.pack();
		
		//Util.centrarIframes(internalframe);
		internalframe.doModal(parent.getRootPane());
		internalframe.setVisible(true);

		return panelM.getSelOpt();		
	}
	
	public static int showInternalMessageDialog(String message,String titulo,int optionType)
	{	
		final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral(titulo, false, false, false, false);
		PanelMensajes panelM=new PanelMensajes(internalframe,JOptionPane.NO_OPTION,optionType,message);
		internalframe.add(panelM);
		internalframe.pack();
		
		internalframe.doModal(Util.framePrincipal.getRootPane());
		internalframe.setVisible(true);

		return panelM.getSelOpt();	
	}
	
	public static int showInternalMessageDialog(JInternalFrameTesterGral parent,String message,String titulo,int optionType)
	{	
		final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral(titulo, false, false, false, false);
		PanelMensajes panelM=new PanelMensajes(internalframe,JOptionPane.NO_OPTION,optionType,message);
		internalframe.add(panelM);
		internalframe.pack();
		
		internalframe.doModal(parent.getRootPane());
		internalframe.setVisible(true);

		return panelM.getSelOpt();	
	}
	
	public static int showInternalMessageDialog(JPanel parent,String message,String titulo,int optionType)
	{	
		final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral(titulo, false, false, false, false);
		PanelMensajes panelM=new PanelMensajes(internalframe,JOptionPane.NO_OPTION,optionType,message);
		internalframe.add(panelM);
		internalframe.pack();
		
		internalframe.doModal(parent.getRootPane());
		internalframe.setVisible(true);

		return panelM.getSelOpt();	
	}
	
	public static int showInternalError(JFrame parent,Throwable ex)
	{	
		final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral("Error", false, false, false, false);
		PanelMensajes panelM=new PanelMensajes(internalframe,JOptionPane.NO_OPTION,JOptionPane.ERROR_MESSAGE,"Se ha producido el siguiente error: "+ex.getLocalizedMessage(),ex);
		internalframe.add(panelM);
		
		internalframe.pack();
		
		internalframe.doModal(parent.getRootPane());
		internalframe.setVisible(true);

		return panelM.getSelOpt();	
	}

}
