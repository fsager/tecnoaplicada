package testerGeneral.persistence.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Auditoria;
import testerGeneral.domain.Constantes;
import testerGeneral.domain.Propiedad;
import testerGeneral.service.AuditoriaDefinition;
import testerGeneral.service.PropiedadDefinition;

public class Util {
	private static final Log log = LogFactory.getLog(Util.class);

	public static final String ACTION_DELETE = "Eliminó Registro";
	public static final String ACTION_UPDATE = "Modificó Registro";
	public static final String ACTION_SELECT = "Consultó Registro";
	public static final String ACTION_INSERT = "Insertó Registro";
	public static final String ACTION_LOGIN = "Ingresó a la Aplicación";
	public static final String ACTION_SALIR = "Cerró Aplicación";
	public static final String ACTION_CIERRE_SESION = "Cerró Sesión";
	public static final String ACTION_CAMBIO_CLAVE = "Cambió de Clave";

	public static final String ACTION_MENU_SUB_LICENCIA = "Ingresó al Menú Licencia";

	public static final String ACTION_MENU_SUB_PERSONA = "Ingresó al Menú "
			+ Constantes.MENU_SUB_PERSONA;
	public static final String ACTION_MENU_EXAMEN_PSICOMETRICO = "Ingresó al Menú "+ Constantes.MENU_SUB_EXAMEN_PSICOMETRICO;
	public static final String ACTION_MENU_EXAMEN_VISION = "Ingresó al Menú "
			+ Constantes.MENU_SUB_EXAMEN_VISION;
	public static final String ACTION_MENU_EXAMEN_AUDIO= "Ingresó al Menú "
		+ Constantes.MENU_SUB_EXAMEN_AUDIO;	
	public static final String ACTION_MENU_EXAMEN_EQUILIBRIO = "Ingresó al Menú "
			+ Constantes.MENU_SUB_EXAMEN_EQUILIBRIO;
	public static final String ACTION_MENU_USUARIO_CAMBIAR_CLAVE = "Ingresó al Menú "
			+ Constantes.MENU_SUB_CAMBIAR_CLAVE;
	public static final String ACTION_MENU_ADM_USUARIOS = "Ingresó al Menú "
			+ Constantes.MENU_SUB_ADM_GRAL_USU;

	public static final String ACTION_MENU_PANEL_CONTROL = "Ingresó al Menú "
			+ Constantes.MENU_SUB_PANEL_CONTROL;
	public static final String ACTION_MENU_BACKUP = "Ingresó al Menú "
			+ Constantes.MENU_SUB_BACKUPS;
	public static final String ACTION_MENU_INFORMES = "Ingresó al Menú "
			+ Constantes.MENU_SUB_INFORMES;
	public static final String ACTION_MENU_EVENTOS = "Ingresó al Menú "
			+ Constantes.MENU_SUB_LOG;
	public static final String ACTION_MENU_PANEL_ADMIN_MUNICIPIO = "Ingresó al Menú "
			+ Constantes.MENU_SUB_PANEL_ADM_MINICIPIO;

	private static PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager
			.getBizObject("propiedadService");
	private static AuditoriaDefinition auditoriaService = (AuditoriaDefinition) ContextManager
			.getBizObject("auditoriaService");

	public static LinkedList<String> tablas = new LinkedList<String>();

	public static void insertAudit(String accion, Object entity, Object id) {
		String objeto = null;
		if (entity != null)
			objeto = entity.getClass().getSimpleName();
		insertAudit(accion, objeto, id);
	}

	public static void insertAudit(String accion, String entity, Object id) {
		try {
			Propiedad propiedadGuardarLogDeEventos;

			propiedadGuardarLogDeEventos = propiedadService
					.get("SISTEMA.GUARDAR.LOG.EVENTOS");

			String valorPropiedadGuardarLogDeEventos = propiedadGuardarLogDeEventos
					.getPropValor();

			if (valorPropiedadGuardarLogDeEventos.equals("S")) {// Si el valor
				// de la
				// propiedad
				// está
				// activado, se
				// inserta la
				// auditoría

				String computername = new String();

				Auditoria auditoria = new Auditoria();
				auditoria.setAudAccion(accion);
				auditoria.setAudFecha(new Date());
				auditoria.setAudObjeto(entity);

				String user = new String();
				if (frontend.utils.Util.USUARIO != null)
					user = frontend.utils.Util.USUARIO;
				auditoria.setAudUsuario(user);
				auditoria.setAudEstacion(frontend.utils.Util.nombrePc);
				if (id != null) {
					auditoria.setAudFk(id.toString());
				}

				auditoriaService.insert(auditoria);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Borra las auditorías anteriores anteriores en X días a la fecha actual. X
	 * está definido por la propiedad
	 * "SISTEMA.CONSERVAR.DATOS.LOG.EVENTOS.XDIAS" siempre y cuando la propiedad
	 * "SISTEMA.GUARDAR.LOG.EVENTOS" valga "S".
	 */
	public static void borrarAuditoriasAnterioresAXDias() {
		try {
			Propiedad propiedadGuardarLogDeEventos = propiedadService
					.get("SISTEMA.GUARDAR.LOG.EVENTOS");

			Propiedad propiedadDiasAGuardarLogDeEventos = propiedadService
					.get("SISTEMA.CONSERVAR.DATOS.LOG.EVENTOS.XDIAS");

			String valorPropiedadGuardarLogDeEventos = propiedadGuardarLogDeEventos
					.getPropValor();

			String valorPropiedadDiasAGuardarLogDeEventos = propiedadDiasAGuardarLogDeEventos
					.getPropValor();

			if (valorPropiedadGuardarLogDeEventos.equals("S")) {

				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_YEAR, Integer
						.valueOf(valorPropiedadDiasAGuardarLogDeEventos)
						* (-1));

				Date borrarHaciaAtrasDeEstaFecha = calendar.getTime();

				Auditoria audit = new Auditoria();
				audit.setAudFecha(borrarHaciaAtrasDeEstaFecha);

				auditoriaService.deleteAll(audit);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Devuelve las tablas de la DB que pertenecen a la aplicación.
	 */
	public static LinkedList<String> getTablas() {
		try {
			ResultSet res = ContextManager.getConnection().getMetaData()
					.getTables(null, "APP", null, null);

			while (res.next()) {
				tablas.add(res.getString("TABLE_NAME"));
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return tablas;
	}

	public static byte[] getBytesFromFile(String file) {
		try {
			File archivoSeleccionado = new File(file);

			String fileName = archivoSeleccionado.getName();
			byte[] bytes = new byte[(int) archivoSeleccionado.length()];
			FileInputStream fin;
			fin = new FileInputStream(archivoSeleccionado);
			fin.read(bytes);
			fin.close();

			return bytes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void toFile(String file, byte[] bytes) {
		try {
			File f = new File(file);
			FileOutputStream fout = new FileOutputStream(f, false);
			fout.write(bytes);
			fout.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void zipper(File file, Object obj) throws Exception {
		FileOutputStream fos = new FileOutputStream(file);
		GZIPOutputStream gz = new GZIPOutputStream(fos);
		ObjectOutputStream oos = new ObjectOutputStream(gz);
		oos.writeObject(obj);

		oos.flush();
		oos.close();
		fos.close();
	}

	
	// Deletes all files and subdirectories under dir.
	// Returns true if all deletions were successful.
	// If a deletion fails, the method stops attempting to delete and returns false.
	public static boolean deleteDir(File dir) {
	    if (dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i=0; i<children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]));
	            if (!success) {
	                return false;
	            }
	        }
	    }

	    // The directory is now empty so delete it
	    return dir.delete();
	}

	
	public static void zipDir(String zipFileName, String dir){
		try {
			
			File dirObj = new File(dir);
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		
			String parent=dirObj.getParent();
			if(dirObj.getParentFile()!=null && dirObj.getParentFile().getParentFile()!=null)
				 parent=dirObj.getParent()+File.separator;
			
			addDir(dirObj, out,parent);
			out.close();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	public static void addDir(File dirObj, ZipOutputStream out,String parent) throws IOException {
		File[] files = dirObj.listFiles();
		byte[] tmpBuf = new byte[1024];

		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				addDir(files[i], out,parent);
				continue;
			}
			FileInputStream in = new FileInputStream(files[i].getAbsolutePath());
			
			String fileName=files[i].getAbsolutePath();
			
			fileName=fileName.replace(parent,"");
			
			ZipEntry entry=new ZipEntry(fileName);
			out.putNextEntry(entry);
			int len;
			while ((len = in.read(tmpBuf)) > 0) {
				out.write(tmpBuf, 0, len);
			}
			out.closeEntry();
			in.close();
		}
	}

}