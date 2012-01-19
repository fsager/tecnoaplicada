package actualizaciones;

import java.io.File;
import java.io.RandomAccessFile;

import javax.crypto.spec.SecretKeySpec;

import seguridad.Encriptadora;

public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				
			
				JDialogActualizar dialog = new JDialogActualizar(
						new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
		
		/*try
		{
			File appIni=new File("C:\\programacion\\Workspaces3\\GestorActualizaciones\\Autoimpresor.ini");
			long filePointer=-1;
			
			RandomAccessFile randomAccessFile=new RandomAccessFile(appIni,"rw");
			try
			{
				String strLine=null;
				while ((strLine = randomAccessFile.readLine()) != null){
						if(strLine.trim().startsWith("Class Path="))
					  {
						 
						  filePointer+=strLine.trim().getBytes().length;
						  break;
					  }
					else
					{
						filePointer=randomAccessFile.getFilePointer();
					}
				}
			}
			finally
			{
				if(randomAccessFile!=null)
					randomAccessFile.close();
			}
			
			
			try
			{
				randomAccessFile=new RandomAccessFile(appIni,"rw");
				randomAccessFile.seek(filePointer);
				randomAccessFile.write("fede".getBytes());
			}
			finally
			{
				if(randomAccessFile!=null)
					randomAccessFile.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
	}
}