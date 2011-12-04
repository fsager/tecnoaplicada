package opcionesmultiples;

import java.io.File;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import opcionesmultiples.db.ConexionManagerOpcionesMultiples;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.GenericJDBCException;
import org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel;

import testerGeneral.actualizaciones.GestorActualizaciones;
import testerGeneral.business.ContextManager;
import testerGeneral.db.ConexionManagerTesterGeneral;
import testerGeneral.domain.Auditoria;
import testerGeneral.domain.Constantes;
import testerGeneral.exceptions.MyExceptionHandler;
import testerGeneral.persistence.backup.GestorDBBackup;
import testerGeneral.service.AuditoriaDefinition;
import actualizaciones.GestorActualizacionesUtil;
import frontend.utils.Util;
import opcionesmultiples.ventanas.FrameContenedor;
import frontend.ventanas.VtnConfigurarDb;

public class Main {

	private static final Log log = LogFactory.getLog(Main.class);

	public static void main(String[] args) throws Exception {
		init();
		//encriptarString();
		//desencriptarString();
	}

	public static void init() {
		try {
			try {
				Constantes.VTN_TITLE_FRM_PRINCIPAL = "Opciones Múltiples";
				Util.SMALL_ICON = Constantes.IMG_ICON_SMALL_MC;
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
				
			} catch (Exception e) {
				
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

}// Fin de la clase Main de Autoimpresor.