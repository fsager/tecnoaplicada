package testerGeneral.actualizaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.crypto.spec.SecretKeySpec;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.Propiedad;
import testerGeneral.seguridad.Encriptadora;
import testerGeneral.service.PropiedadDefinition;
import testerGeneral.service.impl.PropiedadService;
import actualizaciones.GestorActualizacionesUtil;
import actualizaciones.GestorFTP;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 
 * @author Chipio
 */
public class GestorActualizaciones extends Thread {

	private static boolean seEncontroActualizacion = false;

	public static void setSeEncontroActualizacion(
			boolean seEncontroActualizacion) {
		GestorActualizaciones.seEncontroActualizacion = seEncontroActualizacion;
	}

	public static boolean getSeEncontroActualizacion() {
		return seEncontroActualizacion;
	}

	public void run() {
		try {

			if (ContextManager.getProperty(
					"SISTEMA.ACTUALIZACION.AUTOMATICA.INICIO")
					.equalsIgnoreCase("S")) {

				GestorActualizacionesUtil
						.init(
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
										.getProperty("SISTEMA.VERSION_PRINCIPAL"),
								ContextManager
										.getProperty("SISTEMA.NOMBRE.PROGRAMA"));

				if (GestorActualizacionesUtil
						.hayNuevaVersionDelPrograma(new File(System
								.getProperty("user.dir")))) {
					GestorActualizaciones.setSeEncontroActualizacion(true);
				} else {
					GestorActualizaciones.setSeEncontroActualizacion(false);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			GestorActualizaciones.setSeEncontroActualizacion(false);
		}
	}

}// Fin de clase GestorActualizaciones de Tester General