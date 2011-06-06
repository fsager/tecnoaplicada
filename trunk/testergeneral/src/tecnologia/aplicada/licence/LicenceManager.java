package tecnologia.aplicada.licence;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.security.GeneralSecurityException;
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
import ar.com.tecnologiaaplicada.LicenseException;
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

	public static void showLicencePanel(){
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral(
						"Datos de licencia", false, true, false, true);
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
					internalframe.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
						public void internalFrameActivated(
								javax.swing.event.InternalFrameEvent evt) {
						}

						public void internalFrameClosed(
								javax.swing.event.InternalFrameEvent evt) {
						}

						public void internalFrameClosing(
								javax.swing.event.InternalFrameEvent evt) {
							System.exit(0);
						}

						public void internalFrameDeactivated(
								javax.swing.event.InternalFrameEvent evt) {
						}

						public void internalFrameDeiconified(
								javax.swing.event.InternalFrameEvent evt) {
						}

						public void internalFrameIconified(
								javax.swing.event.InternalFrameEvent evt) {
							Util.minimizar(internalframe);
						}

						public void internalFrameOpened(
								javax.swing.event.InternalFrameEvent evt) {
						}
					});
					internalframe.setVisible(true);
				}
			}
		});
	}

	public static void showLicencePanelActivacion(){
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				final JInternalFrameTesterGral internalframe = new JInternalFrameTesterGral(
						"Datos de licencia", false, false, false, false);
				PanelLicencia panelLicencia = new PanelLicencia(internalframe, false,false);
				internalframe.add(panelLicencia);
				internalframe.pack();
				Util.centrarIframes(internalframe);
				internalframe.doModal(Util.framePrincipal.getRootPane());
				internalframe.setVisible(true);
			}
		});
	}
	
	public static Date getLastDateActivation()
	{
		try
		{
			String lastDateActivated = ContextManager.getProperty("LICENCE.LAST.DATE.ACTIVATED");
	
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
	
	
	public static Date getfechaUltimoIntentoActivation()
	{
		try
		{
			String date = ContextManager.getProperty("LICENCIA.FECHA.ULTIMO.INTENTO");
			if (!date.equals("")) {
				return sdf.parse(date);
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
			codigo=codigo+getDigitoVerificador(codigo);			
			Long condigoInHexa = Long.valueOf(codigo);
			codigo = Long.toHexString(condigoInHexa);

			return codigo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getDigitoVerificador(String codigoActivacion)
	{
		int[] secuencia={1,3,5,7,9,3,5,7,9,3,5,7,9,3,5,7,9,3,5,7,9,3,5,7,9,3,5,7,9,3,5,7,9,3,5,7,9,3,5,7,9};//,3,5
		int prod=0;
		
		for(int i=0;i<secuencia.length && i<codigoActivacion.length();i++)
		{
			
			int res=secuencia[i]*Integer.valueOf(""+codigoActivacion.charAt(i));
			prod+=res;
		}
		
		int result=prod/2;
		
		String unidad=String.valueOf(result);
		
		return unidad.substring(unidad.length()-1);
	}

	public static void actualizarLicencia(String nroLicencia,String codigoActivacion) throws LicenseException {
		final Integer cantidadIntentosPermitidos=Integer.valueOf(ContextManager.getProperty("LICENCIA.CANTIDAD.INTENTOS.PERMITIDOS"));
		Integer cantidadIntentos=Integer.valueOf(ContextManager.getProperty("LICENCIA.CANTIDAD.INTENTOS"));
		Date fechaUltimoIntentoActivacion=LicenceManager.getfechaUltimoIntentoActivation();
		int todayDayOfYear=Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
		
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
		} catch (org.springframework.remoting.RemoteAccessException e) {

			Calendar calendarUltimoIntentoActivacion=Calendar.getInstance();
			Integer intentoDayOfYear=null;
			if(fechaUltimoIntentoActivacion!=null)
			{
				calendarUltimoIntentoActivacion.setTime(fechaUltimoIntentoActivacion);
				intentoDayOfYear=calendarUltimoIntentoActivacion.get(Calendar.DAY_OF_YEAR);;
			}
			
			if(intentoDayOfYear==null || todayDayOfYear!=intentoDayOfYear){
				
				Propiedad propiedad = new Propiedad();
				propiedad.setPropClave("LICENCIA.CANTIDAD.INTENTOS");
				propiedad.setPropValor(""+(++cantidadIntentos));
				ContextManager.updatePropiedad(propiedad);
				
				propiedad = new Propiedad();
				propiedad.setPropClave("LICENCIA.FECHA.ULTIMO.INTENTO");
				propiedad.setPropValor(sdf.format(new Date()));
				ContextManager.updatePropiedad(propiedad);
			}
			
			if(cantidadIntentos>=cantidadIntentosPermitidos)
			{
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						JOptionPaneTesterGral.showInternal(
								"<HTML>No dispone de más intentos para activar el producto, debe activarlo para continuar.</HTML>",
								"Activación del producto",
								JOptionPane.INFORMATION_MESSAGE,false);
						showLicencePanelActivacion();
					}});
				
			}
			else
			{
				final int cantidadIntentosMostrar=cantidadIntentos;
				//Informo que no se pudo realizar la actualizacion y que le quedan X intentos
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						JOptionPaneTesterGral.showInternal(
								"<HTML>No se pudo realizar la activación requerida del producto.<BR>Controle su conexión a Internet. Restan "+(cantidadIntentosPermitidos-cantidadIntentosMostrar)+" intento/s.</HTML>",
								"Activación del producto",
								JOptionPane.INFORMATION_MESSAGE,false);					
					}});
			}
		
			
		} 
		 catch (Exception e) {
			 if(e instanceof LicenseException)
			 {
				 throw (LicenseException)e; 
			 }
			 else
				throw new RuntimeException(e);
		}

	}
	
	public static void actualizarLicencia(byte[] licencia,String nroLicencia)throws GeneralSecurityException {
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

			//Licencia Vitalicia, no hay que validarla más.
			String vitalicia="N";
			if(licenciaObjecto.getLicTipo()==1)
				vitalicia="S";

			propiedad = new Propiedad();
			propiedad.setPropClave("LICENCIA_VITALICIA");
			propiedad.setPropValor(vitalicia);
			ContextManager.updatePropiedad(propiedad);	
			
			propiedad = new Propiedad();
			propiedad.setPropClave("LICENCIA.CANTIDAD.INTENTOS");
			propiedad.setPropValor("0");
			ContextManager.updatePropiedad(propiedad);
			
			propiedad = new Propiedad();
			propiedad.setPropClave("LICENCIA.FECHA.ULTIMO.INTENTO");
			propiedad.setPropValor("");
			ContextManager.updatePropiedad(propiedad);

		}
		catch(GeneralSecurityException e)
		{
			throw e;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static Licencia getLicenciaFromBytes(byte[] licencia, String nroLicencia) throws GeneralSecurityException{
		String clave = nroLicencia;
		// completo la longitud de la clave
		while (clave.length() < 16) {
			clave += 1;
		}

		SecretKeySpec clavePrivada =null;
		Encriptadora encriptador=null;
		byte[] bytes =null;
		
		try
		{
			clavePrivada = new SecretKeySpec(clave.getBytes(), "AES");
			encriptador = new Encriptadora("AES", clavePrivada);
			bytes = encriptador.desencriptar(licencia);
		}
		catch(Exception e)
		{
			throw new GeneralSecurityException(e);
		}

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
