package autoimpresor;

import java.io.File;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.GenericJDBCException;
import org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel;

import testerGeneral.actualizaciones.GestorActualizaciones;
import testerGeneral.db.ConexionManagerTesterGeneral;
import testerGeneral.domain.Auditoria;
import testerGeneral.domain.Constantes;
import testerGeneral.exceptions.MyExceptionHandler;
import testerGeneral.persistence.backup.GestorDBBackup;
import testerGeneral.service.AuditoriaDefinition;
import actualizaciones.GestorActualizacionesUtil;
import autoimpresor.business.ContextManager;
import autoimpresor.frontend.ventanas.FrameContenedor;
import autoimpresor.service.PersonaDefinition;
import autoimpresor.service.impl.PersonaService;
import frontend.utils.Util;
import frontend.ventanas.VtnConfigurarDb;

public class Main {

	private static final Log log = LogFactory.getLog(Main.class);

	public static void main(String[] args) throws Exception {
		init();
		//autoimpresor.util.Util.printReport(null, null, null);
		//encriptarString();
		//desencriptarString();
	}

	public static void init() {
		try {
			File archivoDeConexionDBRemota = new File(System.getProperty("user.dir")+ File.separator + "db_param.cfg");
			ConexionManagerTesterGeneral datasource = (ConexionManagerTesterGeneral) ContextManager.getBizObject("dataSource");
			// mostrarError("Version 1.0.6");
			try {
				datasource.getConnection();
				Constantes.VTN_TITLE_FRM_PRINCIPAL = "Autoimpresor";
				Util.SMALL_ICON = Constantes.IMG_ICON_SMALL_AUTOIMPRESOR;
				Util.obtenerNombrePC();
				AuditoriaDefinition auditoriaService = (AuditoriaDefinition) ContextManager
						.getBizObject("auditoriaService");
				Auditoria auditoria = new Auditoria();
				auditoria.setAudFecha(new Date());
				auditoriaService.getAll(auditoria);
				// Se borra el log anterior a los días especificados en el panel de
				// control.
				testerGeneral.persistence.impl.Util
						.borrarAuditoriasAnterioresAXDias();

				/*
				 * Si la actualización anterior incluía ejecutar un archivo de
				 * script (actualizar_db.sql), se actualiza con ese archivo la DB
				 * del programa.
				 */
				GestorDBBackup.ejecutarSentenciaSQL();
				
				

				try {
					/*
					 * Si se bajó una nueva versión del GestorActualizaciones.jar,
					 * se la activa.
					 */
					// GestorActualizacionesUtil.actualizarGestorActualizaciones();
					/* Se revisa si hay una nueva versión del sistema al iniciar. */

					GestorActualizaciones gestorActualizaciones = new GestorActualizaciones();
					gestorActualizaciones.start();

				} catch (Exception ex) {

				}

				// Hilo de backup automático al iniciar
				GestorDBBackup gestorBackup = new GestorDBBackup();
				Thread hiloBackupAutomatico = new Thread(gestorBackup);
				hiloBackupAutomatico.start();

				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UIManager
									.setLookAndFeel(new SubstanceCremeLookAndFeel());

						} catch (Exception e) {
							throw new RuntimeException(e);
						}
						JFrame frame=new FrameContenedor();
						frame.setVisible(true);
						
						migrarDatos(frame);
						
						
						Thread
								.setDefaultUncaughtExceptionHandler(new MyExceptionHandler());
						System.setProperty("sun.awt.exception.handler",
								MyExceptionHandler.class.getName());
					}
				});
				
			} catch (Exception e) {
				
				if(archivoDeConexionDBRemota.exists())
					mostrarFrameJDBCRemoto();
				else
					throw new GenericJDBCException(null,null);
					
			}
		}
		catch (GenericJDBCException ex) {
			mostrarError("Se ha detectado que existe otra instancia de la aplicacion que se esta ejecutando. Por favor cierre dicha instancia antes de continuar.");
			log.error(ex.getMessage(), ex);
			System.exit(1);
		} catch (Exception exc) {
			log.error(exc.getMessage(), exc);
			throw new RuntimeException(exc);
		}
	}

	public static void migrarDatos(JFrame frame)
	{
		try{
			if(seRequiereMigracion())
			{
				int op=JOptionPane.showConfirmDialog(frame,"<HTML>Se requiere una migración de los datos. <B>Antes de continuar se recomienda realizar una copia de seguridad de la aplicación.</B> <BR>¿Desea continuar?</HTML>","Migración de datos",JOptionPane.YES_NO_OPTION);
				if (op == JOptionPane.YES_OPTION) {
					GestorDBBackup.ejecutarMigracionSQL();
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);				
		}
		
	}
	
	public static boolean seRequiereMigracion() throws Exception
	{
		PersonaDefinition personaService = (PersonaDefinition) ContextManager.getBizObject("personaService");
		return personaService.migracionNecesaria();
		
	}
	
	public static void encriptarString() {

		System.out.println(GestorActualizacionesUtil
				.encriptarString("jtftp@jttecnologiaaplicada.com"));
		System.out.println(GestorActualizacionesUtil.encriptarString("jtftp"));
		System.out.println(GestorActualizacionesUtil
				.encriptarString("tecnoapli"));

		System.out.println(GestorActualizacionesUtil
				.encriptarString("ftp3.Jabry.com"));
		System.out.println(GestorActualizacionesUtil
				.encriptarString("chipiochipio"));
		System.out.println(GestorActualizacionesUtil
				.encriptarString("lero2lero"));
	}
	
	public static void desencriptarString() {

		
		System.out.println("FTP_PRINCIPAL_URL: "+GestorActualizacionesUtil.desencriptarString("DXTEn5m2NtfIb0JrQ88tOwkSptl3lH87AJZPfePBh80="));
		System.out.println("FTP_PRINCIPAL_USER: "+GestorActualizacionesUtil.desencriptarString("K8l004OOY1J6Uup98Cp/pQ=="));
		System.out.println("FTP_PRINCIPAL_PASSWORD: "+GestorActualizacionesUtil.desencriptarString("uLBg40t0YiQ9/9dBV8UgSg=="));

		System.out.println("FTP_SECUNDARIO_URL: "+GestorActualizacionesUtil.desencriptarString("1yT7xdyXFOC1p1EecQ0V3Q==="));
		System.out.println("FTP_SECUNDARIO_USER: "+GestorActualizacionesUtil.desencriptarString("sva1t1RMRadkMMoiRlpKWA=="));
		System.out.println("FTP_SECUNDARIO_PASSWORD: "+GestorActualizacionesUtil.desencriptarString("WXr7F2JirExUiUzOnSAeyA=="));
	}

	public static void mostrarError(final String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
	
	public static void mostrarFrameJDBCRemoto()
	{
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel(new SubstanceCremeLookAndFeel());

				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				VtnConfigurarDb VtnConfigurarDb=new VtnConfigurarDb("/images/autoimpresor.png","Autoimpresor");
			}
		});
	}
}// Fin de la clase Main de Autoimpresor.