package tecnologia.aplicada.licence;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import testerGeneral.business.ContextManager;
import testerGeneral.domain.ExamenDetalle;
import testerGeneral.domain.Propiedad;
import testerGeneral.service.ExamenDefinition;
import testerGeneral.service.ExamenDetalleDefinition;
import testerGeneral.service.PersonaExamenDefinition;
import ar.com.tecnologiaaplicada.Encriptadora;
import ar.com.tecnologiaaplicada.domain.DetalleLicencia;
import ar.com.tecnologiaaplicada.domain.Licencia;
import frontend.components.JOptionPaneTesterGral;
import frontend.paneles.licence.PanelLicencia;
import frontend.utils.Util;
import frontend.ventanas.JInternalFrameTesterGral;

public class LicenceManager {

	private static SimpleDateFormat sdf = new SimpleDateFormat(ContextManager.getProperty("FORMATO.FECHA.HORA"));
	private static final long ONE_HOUR = 60 * 60 * 1000L;
	private static ExamenDetalleDefinition examenDetalleServiceLocal=(ExamenDetalleDefinition)ContextManager.getBizObject("examenDetalleService"); 
	private static ExamenDefinition examenServiceLocal=(ExamenDefinition)ContextManager.getBizObject("examenService");

	public static void showLicencePanel() throws Exception {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral(
						"Datos de licencia", false, false, false, false);
				PanelLicencia panelLicencia = null;

				if (!isLicencedProduct()) {
					Long testDays = LicenceManager.getTestDays();

					if (testDays == null) {
						// Mostras panel donde debe seleccionar si quiere cargar
						// una liciencia o usar el periodo de prueba.
						panelLicencia = new PanelLicencia(internalframe, true,
								false);
					} else if (testDays < 0) {
						// Informar finalizacion del periodo de prueba o Ingresa
						// una licencia o no sigue más
						panelLicencia = new PanelLicencia(internalframe, false,
								false);
					} else if (testDays > 0) {
						// Mostras días restantes del periodo de prueba.
						JOptionPaneTesterGral.showInternalMessageDialog(
								"El periodo de prueba finalizará en: "
										+ testDays + " día/s",
								"Periodo de prueba",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}

					internalframe.add(panelLicencia);
					internalframe.pack();
					Util.centrarIframes(internalframe);
					internalframe.doModal(Util.framePrincipal.getRootPane());
					internalframe.setVisible(true);
				}
			}
		});
	}

	public static Date getLastDateActivation()
	{
		try
		{
			String lastDateActivated = ContextManager.getProperty("LICENCE.LAST.DATE.ACTIVATED");
	
			String lastMonthActivated = "";
			if (!lastDateActivated.equals("")) {
				return sdf.parse(lastDateActivated);
			}
			else 
				return null;
		}catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	public static boolean hayQueActualizarLicencia() {
		if (isLicencedProduct()) {
			Calendar hoy = Calendar.getInstance();
			String actualMonth = hoy.get(Calendar.MONTH) + "";
			
			Date lastDateActivated=getLastDateActivation();

			String lastMonthActivated = "";
			if (lastDateActivated!=null) {
				Calendar fechaActualizacion = Calendar.getInstance();
				try {
					fechaActualizacion.setTime(lastDateActivated);
					lastMonthActivated = fechaActualizacion.get(Calendar.MONTH)+ "";
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

			if (lastMonthActivated.equals("")
					|| !actualMonth.equals(lastMonthActivated)) {
				return true;
			}
		}

		return false;
	}

	public static String getCodigoActivacion() {
		try {
			Date lastDateActivated = getLastDateActivation();
			
			String codigoActivacionFormat = "yyMMddHHmmss";
			SimpleDateFormat formatCodigo = new SimpleDateFormat(
					codigoActivacionFormat);

			String lastMonthActivated = "";
			Calendar fechaActualizacion = null;
			Date dateActualizacion = null;
			if (lastDateActivated!=null) {
				fechaActualizacion = Calendar.getInstance();
				fechaActualizacion.setTime(lastDateActivated);
				dateActualizacion = fechaActualizacion.getTime();
			}

			PersonaExamenDefinition personaExamenService = (PersonaExamenDefinition) ContextManager
					.getBizObject("personaExamenService");
			Long cantidad = personaExamenService
					.getCantidadExamenes(dateActualizacion);

			String codigo = formatCodigo.format(new Date()) + cantidad;
			Long condigoInHexa = Long.valueOf(codigo);
			codigo = Long.toHexString(condigoInHexa);

			return codigo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void actualizarLicencia(String nroLicencia,
			String codigoActivacion) {
		try {
			String producto = ContextManager
					.getProperty("SISTEMA.NOMBRE.PROGRAMA");
			if (nroLicencia == null)
				nroLicencia = ContextManager.getProperty("LICENCE.NRO");

			if (codigoActivacion == null)
				codigoActivacion = getCodigoActivacion();

			ar.com.tecnologiaaplicada.service.ExamenDetalleDefinition examenDetalleService = (ar.com.tecnologiaaplicada.service.ExamenDetalleDefinition) ContextManager
					.getBizObject("licenseRemoteService");
			byte[] licencia = examenDetalleService
					.getDetalleLicenciaPorCliente(nroLicencia, producto,
							codigoActivacion);

			actualizarLicencia(licencia,nroLicencia);
			/*
			Licencia licenciaObjecto=getLicenciaFromBytes(licencia,nroLicencia);
			if(getLastDateActivation()!=null && !licenciaObjecto.getLicFechaActualizacion().after(getLastDateActivation()))
				throw new RuntimeException("La licencia ya ha sido utilizada, por favor solicite una nueva.");
			
			Collection<DetalleLicencia> detalleLicencias=licenciaObjecto.getDetalleLicencias();
			for(DetalleLicencia detalleLicencia:detalleLicencias)
			{
				//System.out.println("DetalleExamen: "+detalleLicencia.getExamenDetalle().getExadCodigo()+" Activo: "+detalleLicencia.getDlicActivaSn());
				
				//Actualizar la informaicon de licencia
				ExamenDetalle examenDetalle=new ExamenDetalle();
				examenDetalle.setExadCodigo(detalleLicencia.getExamenDetalle().getExadCodigo());
				
				List<ExamenDetalle> detalleExamenes=examenDetalleServiceLocal.getAll(examenDetalle);
				if(detalleExamenes.size()==1)
				{
					examenDetalle=detalleExamenes.get(0);
					examenDetalle.setExadLicencedSn(detalleLicencia.getDlicActivaSn());
					examenDetalleServiceLocal.update(examenDetalle);
				}
				else if(detalleExamenes.size()>1)
					throw new RuntimeException("Código de examen duplicado: "+detalleLicencia.getExamenDetalle().getExadCodigo());
				
				
			}
			
			Propiedad propiedad = new Propiedad();
			propiedad.setPropClave("LICENCED");
			propiedad.setPropValor("S");
			ContextManager.updatePropiedad(propiedad);

			propiedad = new Propiedad();
			propiedad.setPropClave("LICENCE.NRO");
			propiedad.setPropValor(nroLicencia);
			ContextManager.updatePropiedad(propiedad);

			propiedad = new Propiedad();
			propiedad.setPropClave("LICENCE.LAST.DATE.ACTIVATED");
			propiedad.setPropValor(sdf.format(licenciaObjecto.getLicFechaActualizacion()));
			ContextManager.updatePropiedad(propiedad);*/

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	public static void actualizarLicencia(byte[] licencia,String nroLicencia) {
		try {

			Licencia licenciaObjecto=getLicenciaFromBytes(licencia,nroLicencia);
			
			String licFechaActualizacion=sdf.format(licenciaObjecto.getLicFechaActualizacion());
			Date licFechaActualizacionDate=sdf.parse(licFechaActualizacion);
			
			if(getLastDateActivation()!=null && !licFechaActualizacionDate.after(getLastDateActivation()))
				throw new RuntimeException("La licencia ya ha sido utilizada, por favor solicite una nueva.");
			
			Collection<DetalleLicencia> detalleLicencias=licenciaObjecto.getDetalleLicencias();
			for(DetalleLicencia detalleLicencia:detalleLicencias)
			{
				//System.out.println("DetalleExamen: "+detalleLicencia.getExamenDetalle().getExadCodigo()+" Activo: "+detalleLicencia.getDlicActivaSn());
				
				//Actualizar la informaicon de licencia
				ExamenDetalle examenDetalle=new ExamenDetalle();
				examenDetalle.setExadCodigo(detalleLicencia.getExamenDetalle().getExadCodigo());
				
				List<ExamenDetalle> detalleExamenes=examenDetalleServiceLocal.getAll(examenDetalle);
				if(detalleExamenes.size()==1)
				{
					examenDetalle=detalleExamenes.get(0);
					examenDetalle.setExadLicencedSn(detalleLicencia.getDlicActivaSn());
					examenDetalleServiceLocal.update(examenDetalle);
				}
				else if(detalleExamenes.size()>1)
					throw new RuntimeException("Código de examen duplicado: "+detalleLicencia.getExamenDetalle().getExadCodigo());
				
				
			}
			
			Propiedad propiedad = new Propiedad();
			propiedad.setPropClave("LICENCED");
			propiedad.setPropValor("S");
			ContextManager.updatePropiedad(propiedad);

			propiedad = new Propiedad();
			propiedad.setPropClave("LICENCE.NRO");
			propiedad.setPropValor(nroLicencia);
			ContextManager.updatePropiedad(propiedad);

			propiedad = new Propiedad();
			propiedad.setPropClave("LICENCE.LAST.DATE.ACTIVATED");
			propiedad.setPropValor(licFechaActualizacion);
			ContextManager.updatePropiedad(propiedad);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static Licencia getLicenciaFromBytes(byte[] licencia, String nroLicencia) {
		String clave = nroLicencia;
		// completo la longitud de la clave
		while (clave.length() < 16) {
			clave += 1;
		}
		
		SecretKeySpec clavePrivada = new SecretKeySpec(clave.getBytes(), "AES");
		Encriptadora encriptador = new Encriptadora("AES", clavePrivada);
		byte[] bytes = encriptador.desencriptar(licencia);

		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		try {

			ObjectInputStream ois = new ObjectInputStream(bis);
			Licencia licenciaObject = (Licencia) ois.readObject();
			ois.close();
			bis.close();
			return licenciaObject;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static Long getTestDays() {
		try {
			String testProduct = ContextManager
					.getProperty("PERIODO.PRUEBA.FECHA.INICIO");
			String testDuration = ContextManager
					.getProperty("PERIODO.PRUEBA.DURACION");
			if (testProduct.equals(""))
				return null;
			else {

				Calendar fechaInicio = Calendar.getInstance();
				fechaInicio.setTime(sdf.parse(testProduct));

				Calendar fechaFin = Calendar.getInstance();
				fechaFin.setTime(sdf.parse(testProduct));
				fechaFin.add(Calendar.DAY_OF_YEAR, Integer
						.valueOf(testDuration));

				Long daysBetween = daysBetween(new Date(), fechaFin.getTime());

				return daysBetween;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean isLicencedProduct() {
		String licenced = ContextManager.getProperty("LICENCED");
		if (licenced.equals("S"))
			return true;
		else
			return false;
	}

	public static Long daysBetween(Date d1, Date d2) {
		return ((d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24));
	}

}
