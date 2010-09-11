package testerGeneral.exceptions;


import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.derby.iapi.services.io.ArrayOutputStream;

import frontend.components.JOptionPaneTesterGral;
import frontend.utils.Util;

public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {

	private static final Log log = LogFactory.getLog(MyExceptionHandler.class);
	
    public void uncaughtException(final Thread t, final Throwable e) {
        if (SwingUtilities.isEventDispatchThread()) {
            showException(t, e);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    showException(t, e);
                }
            });
        }
    }
    
    public void handle(Throwable t) {
    	uncaughtException(null,t);
    }


    private void showException(Thread t, Throwable e) {
    	log.error(e.getMessage(),e);
    	e.printStackTrace();
    	
    	if(!(e instanceof ArrayIndexOutOfBoundsException))
    	{
	    	try
	    	{
		    	if(Util.frameContenedor!=null)
		    	   	JOptionPaneTesterGral.showInternalError(Util.frameContenedor,e);
		    	else
		    		JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		    	Util.ocultarPanelOperacionesLargas();
	    	}
	    	catch (Exception ex) {
	    		JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
    	}
    }

}