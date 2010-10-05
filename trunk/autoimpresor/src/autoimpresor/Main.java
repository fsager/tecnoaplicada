package autoimpresor;

import java.io.File;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.GenericJDBCException;
import org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel;

import testerGeneral.actualizaciones.GestorActualizaciones;
import testerGeneral.db.ConexionManager;
import testerGeneral.domain.Auditoria;
import testerGeneral.domain.Constantes;
import testerGeneral.exceptions.MyExceptionHandler;
import testerGeneral.persistence.backup.GestorDBBackup;
import testerGeneral.service.AuditoriaDefinition;
import actualizaciones.GestorActualizacionesUtil;
import autoimpresor.business.ContextManager;
import autoimpresor.frontend.ventanas.FrameContenedor;
import frontend.utils.Util;

public class Main {

	private static final Log log = LogFactory.getLog(Main.class);

	public static void main(String[] args) throws Exception {
		init();
		// encriptarString();
	}

	public static void init() {
		try {
			File archivoDeConexionDBRemota = new File(System.getProperty("user.dir")+ File.separator + "db_param.cfg");
			ConexionManager datasource = (ConexionManager) ContextManager.getBizObject("dataSource");
			// mostrarError("Version 1.0.6");
			try {

				datasource.getConnection();
				
			} catch (Exception e) {
				
				if(archivoDeConexionDBRemota.exists())
					archivoDeConexionDBRemota.delete();
				
				datasource.getConnection();
				JOptionPane.showMessageDialog(null,
								"<HTML><font color=\"red\">No ha sido posible conectarse a la Base de Datos remota. Se iniciará de ahora en adelante el programa conectado a la Base de Datos local.</font><HTML>",
								"Error al conectarse a la Base de Datos remota, configure nuevamente el acceso remoto",
								JOptionPane.WARNING_MESSAGE);

			}

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
					new FrameContenedor().setVisible(true);
					Thread
							.setDefaultUncaughtExceptionHandler(new MyExceptionHandler());
					System.setProperty("sun.awt.exception.handler",
							MyExceptionHandler.class.getName());
				}
			});

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

	public static void encriptarString() {
		System.out.println(GestorActualizacionesUtil
				.encriptarString("ftp.jttecnologiaaplicada.com"));
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

	public static void mostrarError(final String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}// Fin de la clase Main de Autoimpresor.