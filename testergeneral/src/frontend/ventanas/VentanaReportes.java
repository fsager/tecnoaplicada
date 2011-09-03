/*
 * VentanaReportes.java
 *
 * Created on __DATE__, __TIME__
 */

package frontend.ventanas;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.JRViewer;
import testerGeneral.business.ContextManager;
import frontend.utils.Util;

/**
 *
 * @author  __USER__
 */
public class VentanaReportes extends JInternalFrameTesterGral {

	/** Creates new form VentanaReportes */
	public VentanaReportes(JPanel parent,HashMap parameterMap,String reporte) {
		try
		{
			final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral("Reporte", false, true, false, false);
			initReport(parameterMap,reporte,internalframe);			
			internalframe.doModal(parent.getRootPane());			
			internalframe.setVisible(true);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public VentanaReportes(JPanel parent,HashMap parameterMap,JasperReport report) {
		try
		{
			final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral("Reporte", false, true, false, false);
			initReport(parameterMap,report,internalframe);			
			internalframe.doModal(parent.getRootPane());			
			internalframe.setVisible(true);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public VentanaReportes(JRootPane root,HashMap parameterMap,JasperReport report) {
		try
		{
			final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral("Reporte", false, true, false, false);
			
			initReport(parameterMap,report,internalframe);			
			internalframe.doModal(root);			
			internalframe.setVisible(true);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}	
	public VentanaReportes(JInternalFrameTesterGral parent,HashMap parameterMap,String reporte) {
		try
		{
			final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral("Reporte", false, true, false, false);
			initReport(parameterMap,reporte,internalframe);			
			internalframe.doModal(parent.getRootPane());			
			internalframe.setVisible(true);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public VentanaReportes(JPanel parent,HashMap parameterMap,String reporte,List dataSource) {
		try
		{
			final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral("Reporte", false, true, false, false);
			initReport(parameterMap,reporte,internalframe,dataSource);			
			internalframe.doModal(parent.getRootPane());			
			internalframe.setVisible(true);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void initReport(HashMap parameterMap,String reporte,final JInternalFrameTesterGral internalframe)
	{
		try
		{
			String ubicacion=new File(reporte).getAbsolutePath();
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(ubicacion, parameterMap,ContextManager.getCurrentConnection()); 
			JRViewer viewer = new JRViewer(jasperPrint);
			
			
			/*JRSaveContributor[] jRSaveContributor=viewer.getSaveContributors();
			JRSaveContributor[] jRSaveContributorDes=new JRSaveContributor[1];
			System.arraycopy(jRSaveContributor,1,jRSaveContributorDes,0,1);
			viewer.setSaveContributors(jRSaveContributorDes);*/
			
			
			agregarEscucha(internalframe,viewer);
			

		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void initReport(HashMap parameterMap,String reporte,final JInternalFrameTesterGral internalframe,List dataSouce)
	{
		try
		{
			String ubicacion=new File(reporte).getAbsolutePath();
			
			JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(dataSouce);
			JasperPrint jasperPrint = JasperFillManager.fillReport(ubicacion, parameterMap,ds); 
			JRViewer viewer = new JRViewer(jasperPrint);
			
			 
			/*JRSaveContributor[] jRSaveContributor=viewer.getSaveContributors();
			JRSaveContributor[] jRSaveContributorDes=new JRSaveContributor[1];
			System.arraycopy(jRSaveContributor,1,jRSaveContributorDes,0,1);
			viewer.setSaveContributors(jRSaveContributorDes);*/
			
			agregarEscucha(internalframe,viewer);
		}
		
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void agregarEscucha(final JInternalFrameTesterGral internalframe,JRViewer viewer)
	{
		internalframe.add(viewer);
		internalframe.pack();
		internalframe.setSize(900,650);
		
		Util.centrarIframes(internalframe);
		
		internalframe.addInternalFrameListener(new InternalFrameListener(){
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
			}
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				internalframe.close();
			}
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
					internalframe.close();
			}
			@Override
			public void internalFrameDeactivated(InternalFrameEvent e) {
			}
			@Override
			public void internalFrameDeiconified(InternalFrameEvent e) {
			}
			@Override
			public void internalFrameIconified(InternalFrameEvent e) {
			}
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {	
			}				
		});
	}
	
	public void initReport(HashMap parameterMap,JasperReport report,final JInternalFrameTesterGral internalframe)
	{
		try
		{
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameterMap,ContextManager.getCurrentConnection()); 
			JRViewer viewer = new JRViewer(jasperPrint);
			
			/*JRSaveContributor[] jRSaveContributor=viewer.getSaveContributors();
			JRSaveContributor[] jRSaveContributorDes=new JRSaveContributor[1];
			System.arraycopy(jRSaveContributor,1,jRSaveContributorDes,0,1);
			viewer.setSaveContributors(jRSaveContributorDes);*/
			
			
			internalframe.add(viewer);
			internalframe.pack();
			internalframe.setSize(900,650);
			
			Util.centrarIframes(internalframe);
			
			internalframe.addInternalFrameListener(new InternalFrameListener(){
				@Override
				public void internalFrameActivated(InternalFrameEvent e) {
				}
				@Override
				public void internalFrameClosed(InternalFrameEvent e) {
					internalframe.close();
				}
				@Override
				public void internalFrameClosing(InternalFrameEvent e) {
						internalframe.close();
				}
				@Override
				public void internalFrameDeactivated(InternalFrameEvent e) {
				}
				@Override
				public void internalFrameDeiconified(InternalFrameEvent e) {
				}
				@Override
				public void internalFrameIconified(InternalFrameEvent e) {
				}
				@Override
				public void internalFrameOpened(InternalFrameEvent e) {	
				}				
			});
			

		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 394,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 278,
				Short.MAX_VALUE));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	// End of variables declaration//GEN-END:variables

}