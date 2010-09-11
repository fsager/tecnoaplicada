
package frontend.utils;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.text.MaskFormatter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import testerGeneral.business.ContextManager;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.Dominio;
import testerGeneral.domain.UsuarioCommon;
import testerGeneral.service.DominioDefinition;
import testerGeneral.threads.ThreadTrama;
import actualizaciones.GestorActualizacionesUtil;
import frontend.components.GlassPanel;
import frontend.components.JOptionPaneTesterGral;
import frontend.paneles.PanelMenu;
import frontend.paneles.PanelOperacionesLargas;
import frontend.ventanas.FramePrincipal;
import frontend.ventanas.FrameSecundario;
import frontend.ventanas.InicioSession;
import frontend.ventanas.JInternalFrameTesterGral;

public class Util {
	
	private static final Log log = LogFactory.getLog(Util.class);
	
	public static javax.swing.JDesktopPane dp;
	public static javax.swing.JDesktopPane dpSecundario;
	public static JFrame frameContenedor;
	public static JInternalFrameTesterGral framePrincipal;
	public static JLabel lbPrimario;
	public static JLabel lbSecundario;
	public static String nombrePc;
	public static String USUARIO;
	public static UsuarioCommon usuarioCommon;
	public static JFrame frameSecundario;
	public static GraphicsConfiguration gc;
	public static JPanel panelContenido;
	public static ThreadTrama thTrama;
	public static String formatoFecha;
	public static PanelMenu panelMenu;
	public static boolean playSound = true;
	
	public static String ICON;
	public static String SMALL_ICON;
	
	static
	{
		try
		{
			formatoFecha = ContextManager.getProperty("FORMATO.FECHA");
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,"Se ha detectado que existe otra instancia de la aplicacion que se esta ejecutando. Por favor cierre dicha instancia antes de continuar.","Error",JOptionPane.ERROR_MESSAGE);
			log.error(ex.getMessage(), ex);
			System.exit(1);
		}
	}
	
	
	
	public static void setMonitorSecundario()
	{
		try
		{
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice[] gs = ge.getScreenDevices();
			GraphicsDevice gd = gs[1];
			gc = gd.getConfigurations()[0];
			
			frameSecundario = new FrameSecundario(gd.getDefaultConfiguration());
			
		}
		catch(java.lang.ArrayIndexOutOfBoundsException e){}
	}
	
	
	public static void centrarIframes(JInternalFrame iframe)
	{	
	  Dimension dim = iframe.getToolkit().getScreenSize();
	  iframe.setLocation((dim.width - iframe.getWidth()) / 2,(dim.height - iframe.getHeight()) / 2);
	}	

	public static void agregarIframe(JInternalFrame iframe)
	{			
		Util.dp.add(iframe,javax.swing.JLayeredPane.DEFAULT_LAYER);
		iframe.pack();
		iframe.validate();		
		Util.centrarIframes(iframe);
		
		frameContenedor.validate();
		frameContenedor.update(frameContenedor.getGraphics());
	}
	
	public static void agregarIframeMonSecundario(JDesktopPane dp,JInternalFrame iframe)
	{			
		dp.add(iframe,javax.swing.JLayeredPane.DEFAULT_LAYER);
		iframe.pack();
		iframe.validate();
		
		iframe.setLocation((int)(gc.getBounds().getWidth() - iframe.getWidth()) / 2,(int)(gc.getBounds().getHeight() - iframe.getHeight()) / 2);
	}
	
	public static void setIcon(javax.swing.JFrame frame,String ubicacion)
	{
		frame.setIconImage(new ImageIcon(Util.class.getResource(ubicacion)).getImage());		
	}
	
	public static void setIcon(JLabel label,String ubicacion)
	{
		label.setIcon(new ImageIcon(Util.class.getResource(ubicacion)));		
	}
	
	public static void setIcon(JLabel label,byte[] img)
	{
		ImageIcon imagen=new ImageIcon(img);
		label.setIcon(imagen);		
	}
	
	public static void setIcon(JButton button,String ubicacion)
	{
		button.setIcon(new ImageIcon(Util.class.getResource(ubicacion)));		
	}
	
	public static void setIcon(JToggleButton button,String ubicacion)
	{
		button.setIcon(new ImageIcon(Util.class.getResource(ubicacion)));		
	}
	
	public static void setIcon(JInternalFrame frame,String ubicacion)
	{
		frame.setFrameIcon(new javax.swing.ImageIcon(Util.class.getResource(ubicacion)));	
		
	}
	
	public static int showInternal(String message,String titulo,int optionType)
	{
		JOptionPane jOptionPane=new JOptionPane();
		
		int op =JOptionPane.showInternalConfirmDialog(Util.frameContenedor.getContentPane(),message, titulo,JOptionPane.YES_NO_OPTION,optionType);
			
		return op;		
	}
	

	
	public static void abrirInicioSesion(Class menu,UsuarioCommon usr) {		
		InicioSession ini = new InicioSession(menu,usr);
		Util.agregarIframe(ini);
		ini.setVisible(true);
	}
	
	public static void obtenerNombrePC()
	{
	    try{
	    	nombrePc=InetAddress.getLocalHost().getHostName();
	       
	      }catch (Exception e){
	    	  throw new RuntimeException(e);
	      }
	}
	
	public static void mostrarError(JLabel lbError,String error, boolean ocultar) {
		
		if (ocultar) {
			lbError.setText("");
			lbError.setIcon(null);
			lbError.validate();
			lbError.repaint();
		} else {
			lbError.setIcon(new ImageIcon(Util.class.getResource(
					Constantes.IMG_ERROR)));
			lbError.setText(error);
		}

	}
	
	public static void ocultarSinResultados(JLabel lbSinResultados,boolean ocultar) {
	
		if (ocultar) {
			lbSinResultados.setText("");
			lbSinResultados.setIcon(null);
		} else {
			lbSinResultados.setIcon(new ImageIcon(Util.class.getResource(
					Constantes.IMG_ERROR)));
			lbSinResultados.setText(Constantes.ERROR_SIN_RESULTADOS);
		}

	}
	
	public static void personaSinResultados(JLabel lbSinResultados,boolean ocultar) {
	
		if (ocultar) {
			lbSinResultados.setText("");
			lbSinResultados.setIcon(null);
		} else {
			lbSinResultados.setIcon(new ImageIcon(Util.class.getResource(
					Constantes.IMG_ERROR)));
			lbSinResultados.setText(Constantes.ERROR_SIN_PERSONA);
		}
	}
	
	public static void abrirFramePrincipal()
	{
		FramePrincipal framePrincipal = new FramePrincipal(panelMenu);
		Util.agregarIframe(framePrincipal);
		framePrincipal.setVisible(true);
	}
	
	public static void minimizar(JInternalFrame frame)
	{
		GraphicsDevice grafica = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if (grafica.isFullScreenSupported())
			grafica.setFullScreenWindow(null);
		Util.frameContenedor.setState(Frame.ICONIFIED);
		try {
			frame.setMaximum(true);
			frame.pack();
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
	}

	public static void redondearFecha(Date date)
	{
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);

		date.setTime(calendar.getTimeInMillis());
	}
	
	public static String toInitcup(String text)
	{
		String initcup=new String();
		
		String aux[]=text.toLowerCase().trim().split(" ");
		for(int i=0;i<aux.length;i++)
		{
			String token=aux[i].trim();
			if(token.length()>0)
			{
				String primeraLetra=token.substring(0,1).toUpperCase();
				String restoPalabra=new String();
				if(token.length()>1)
					restoPalabra=token.substring(1);
				initcup=initcup+primeraLetra+restoPalabra+" ";
			}
		}
		
		return initcup;		
	}
	
	public static void cargarDominios(JComboBox combo,String clave,boolean nulls)
	{
		Dominio dominioSel=null;
		if(combo.getSelectedItem() != null && combo.getSelectedItem() instanceof Dominio)
			dominioSel=(Dominio)combo.getSelectedItem();
		
		combo.removeAllItems();
		
		if(nulls)
			combo.addItem(new Dominio());
		
		List<Dominio> lst=getDominios(clave);
			
		for(int i=0;i<lst.size();i++)
		{
			combo.addItem(lst.get(i));
			if(dominioSel!=null)
			{
				if(lst.get(i).getDomCodigo()!=null && dominioSel.getDomCodigo()!=null && lst.get(i).getDomCodigo().compareTo(dominioSel.getDomCodigo())==0)
					combo.setSelectedIndex(i);
			}
			else
				combo.setSelectedIndex(0);
		}		
	}
	
	public static void selectDominios(JComboBox combo,String valor)
	{	
		for(int i=0;i<combo.getItemCount();i++)
		{
			Dominio dom=(Dominio)combo.getItemAt(i);
			if(dom!=null && dom.getDomCodigo()!=null && valor!=null && dom.getDomCodigo().compareToIgnoreCase(valor)==0)
			{
				combo.setSelectedIndex(i);
				return;
			}
		}
				
	}
	
	public static List<Dominio> getDominios(String clave)
	{	
		List<Dominio> lst=new ArrayList<Dominio>();
		try {	
			Dominio dom=new Dominio();
			dom.setDomClave(clave);
			
			DominioDefinition dominioService=(DominioDefinition)ContextManager.getBizObject("dominioService");		
			lst=dominioService.getAll(dom);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return lst;
	}
	
	public static double redondear(double importe)
	{
		
		BigDecimal bd=new BigDecimal(importe);
		bd=bd.setScale(2,RoundingMode.HALF_UP);

		return bd.doubleValue();		
	}
	
	public static double redondearTiempo(double importe)
	{
		
		BigDecimal bd=new BigDecimal(importe);
		bd=bd.setScale(0,RoundingMode.HALF_UP);

		return bd.doubleValue();		
	}
	
	public static boolean soloNumeros(String value) {
		try {
			Long.valueOf(value);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	public static boolean isLogitudCorrecta(String value, int lenght) {
		if (value.length() != lenght)
			return false;

		return true;
	}
	
	public static JFormattedTextField setFecha() {
		try {
			String mascara = formatoFecha.replaceAll("y", "#");
			mascara = mascara.replaceAll("M", "#");
			mascara = mascara.replaceAll("d", "#");
			JFormattedTextField textField = new javax.swing.JFormattedTextField(
					new MaskFormatter(mascara));
			textField.setFocusLostBehavior(JFormattedTextField.COMMIT);

			return textField;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

	}

	public static String mostrasMascara()
	{
		String mascaraMostrar = formatoFecha.replaceAll("y", "A");
		mascaraMostrar = mascaraMostrar.replaceAll("M", "M");
		mascaraMostrar = mascaraMostrar.replaceAll("d", "D");
		
		return mascaraMostrar;
	}
	
	
	public static void mostrarPanelOperacionesLargas()
	{
		final GlassPanel glass=new GlassPanel();
		glass.setOpaque(false);
		
		PanelOperacionesLargas panelOperacionesLargas=new PanelOperacionesLargas();
		Point  p=new Point(((Util.framePrincipal.getWidth() - panelOperacionesLargas.getWidth()) / 2),(Util.framePrincipal.getHeight() - panelOperacionesLargas.getHeight()) / 2);
		panelOperacionesLargas.setLocation(p.x,p.y);
		
		panelOperacionesLargas.setVisible(true);
		glass.add(panelOperacionesLargas);
		Util.framePrincipal.setGlassPane(glass);
		glass.setVisible(true);
		
		panelOperacionesLargas.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		glass.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}
	
	public static void ocultarPanelOperacionesLargas()
	{
		Util.framePrincipal.getGlassPane().setVisible(false);		
		Util.framePrincipal.getGlassPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	public static boolean validarDni(String value) {
		String soloNumeros = ContextManager
				.getProperty("PERSONA.DNI.PERMITIR.SOLO.NUMEROS");
		String caractFijos = ContextManager
				.getProperty("PERSONA.DNI.FIJAR.CANTIDAD.CARACTERES");
		Integer cantidadCaracteres = Integer.valueOf(ContextManager
				.getProperty("PERSONA.DNI.CANTIDAD.CARACTERES.FIJA"));

		if (soloNumeros.equals("S")) {
			if (!soloNumeros(value))
				return false;
		}

		if (caractFijos.equals("S")) {
			if (!isLogitudCorrecta(value, cantidadCaracteres))
				return false;
		}

		return true;
	}
	
	public static void calcularEdad(JLabel lbEdad,Date nacimiento) {
		if (nacimiento != null) {			
			int anos=calcularEdad(nacimiento);
			if (anos >= 0) {				
				lbEdad.setText(Constantes.LB_EDAD + " " + anos);
			} else {
				lbEdad.setText(Constantes.LB_EDAD);
			}
		} else {
			lbEdad.setText(Constantes.LB_EDAD);
		}
	}
	
	public static int calcularEdad(Date nacimiento) {		

			Calendar hoy = Calendar.getInstance();

			Calendar fechaNacimiento=Calendar.getInstance();
			fechaNacimiento.setTime(nacimiento);

			redondearFecha(hoy.getTime());
			redondearFecha(nacimiento);
			
			long milisegundos=hoy.getTime().getTime()-nacimiento.getTime();
			Calendar edad=Calendar.getInstance();
			edad.setTimeInMillis(milisegundos);
			int anos=edad.get(Calendar.YEAR)-1970;
			
			return anos;
	}
	
	public static void panelControlActualizaciones() {
		try
		{
				GestorActualizacionesUtil.init(
				ContextManager
						.getProperty("SISTEMA.FTP_PRINCIPAL_URL"),
				ContextManager
						.getProperty("SISTEMA.FTP_PRINCIPAL_USER"),
				ContextManager
						.getProperty("SISTEMA.FTP_PRINCIPAL_PASSWORD"),
				ContextManager
						.getProperty("SISTEMA.FTP_SECUNDARIO_URL"),
				ContextManager
						.getProperty("SISTEMA.FTP_SECUNDARIO_USER"),
				ContextManager
						.getProperty("SISTEMA.FTP_SECUNDARIO_PASSWORD"),
				ContextManager
						.getProperty("SISTEMA.CODIGO_REGION_DESTINO"),
				ContextManager
						.getProperty("SISTEMA.VERSION_PRINCIPAL"),ContextManager
						.getProperty("SISTEMA.NOMBRE.PROGRAMA"));


					File directorioActual = new File(System.getProperty("user.dir"));
					if (GestorActualizacionesUtil.hayNuevaVersionDelPrograma(directorioActual)) {
							int retorno = JOptionPaneTesterGral.showInternal("<HTML>Se ha encontrado una nueva versión del programa.<BR>\n¿Desea actualizar? (se cerrará el programa)</HTML>",
																			"Buscar actualizaciones",
																			JOptionPane.INFORMATION_MESSAGE);
							if (retorno == JOptionPane.YES_OPTION) {
								GestorActualizacionesUtil.generarArchivoDeOpcionesDeActualizacion();
	
								File gestorDeActualizaciones = new File("GestorActualizaciones.jar");
								/*if (gestorDeActualizaciones.exists()) {
									Runtime.getRuntime().exec("java -jar GestorActualizaciones.jar");
									System.exit(0);
								}
								else {*/
								
									/*
									 * El archivo del gestor de actualizaciones no existe.
									 * Lo bajamos desde el servidor FTP para comenzar la actualización.
									 */
								if(gestorDeActualizaciones.exists())
									gestorDeActualizaciones.delete();
								
								GestorActualizacionesUtil
											.obtenerArchivoComunDeTodasLasVersiones(
													"GestorActualizaciones.jar",
													directorioActual);
									File archivoGestorTempDescargado = new File(
											"GestorActualizaciones.jar.ftptemp");
	
									
									archivoGestorTempDescargado
											.renameTo(new File(
													"GestorActualizaciones.jar"));
	
									if (gestorDeActualizaciones.exists()) {
										Runtime.getRuntime().exec("java -jar GestorActualizaciones.jar");
										System.exit(0);
									}
								
	
						}
				}
				 else {
						JOptionPaneTesterGral.showInternalMessageDialog(
								"Usted está usando la última versión del programa",
								"Buscar actualizaciones",
								JOptionPane.INFORMATION_MESSAGE);
				}
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}	
	}
	
	public static synchronized void playSound(String sound,final long sleep)
	{
		if(playSound)
		{
				try {
				
					InputStream in = new FileInputStream(new File(sound).getAbsoluteFile());
					AudioStream as = new AudioStream(in);
					AudioPlayer.player.start(as);
					
					Thread t=new Thread()
					{	public void run() {
							try
							{
								playSound=false;
								Thread.sleep(sleep);	
								playSound=true;
							} catch (Exception ex) {
								throw new RuntimeException(ex);
							}	
						}
					};
					t.setPriority(Thread.MAX_PRIORITY);
					t.start();
					
				} catch (Exception ex) {
					ex.printStackTrace();
					throw new RuntimeException(ex);
				}
		}
	}

}
