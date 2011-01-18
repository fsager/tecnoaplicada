package autoimpresor.business;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import testerGeneral.domain.Propiedad;
import testerGeneral.domain.UsuarioCommon;
import testerGeneral.service.PropiedadDefinition;
import testerGeneral.service.UsuarioDefinition;
import autoimpresor.domain.Antecedente;
import autoimpresor.domain.Caja;
import autoimpresor.domain.ClaseLicencia;
import autoimpresor.domain.Licencia;
import autoimpresor.domain.Persona;
import autoimpresor.domain.Usuario;
import autoimpresor.domain.access.Antecedentes;
import autoimpresor.domain.access.Clases;
import autoimpresor.domain.access.Licencias;
import autoimpresor.domain.access.Personas;
import autoimpresor.domain.access.Usuarios;
import autoimpresor.domain.importers.AntecedenteImp;
import autoimpresor.domain.importers.ClaseLicenciaImp;
import autoimpresor.domain.importers.LicenciaImp;
import autoimpresor.domain.importers.PersonaImp;
import autoimpresor.domain.importers.UsuarioImp;
import autoimpresor.service.AntecedenteDefinition;
import autoimpresor.service.AntecedentesAccessDefinition;
import autoimpresor.service.CajaAccessDefinition;
import autoimpresor.service.CajaDefinition;
import autoimpresor.service.ClaseLicenciaDefinition;
import autoimpresor.service.ClasesAccessDefinition;
import autoimpresor.service.LicenciaDefinition;
import autoimpresor.service.LicenciasAccessDefinition;
import autoimpresor.service.PersonaDefinition;
import autoimpresor.service.PersonasAccessDefinition;
import autoimpresor.service.UsuariosAccessDefinition;

public class DBImporter {
	
	PersonasAccessDefinition personasAccessService = (PersonasAccessDefinition) ContextManager.getBizObject("personasAccessService");
	PersonaDefinition personaService = (PersonaDefinition) ContextManager.getBizObject("personaService");

	AntecedentesAccessDefinition antecedentesAccessService = (AntecedentesAccessDefinition) ContextManager.getBizObject("antecedentesAccessService");
	AntecedenteDefinition antecedenteService = (AntecedenteDefinition) ContextManager.getBizObject("antecedenteService");
	
	ClasesAccessDefinition clasesAccessService = (ClasesAccessDefinition) ContextManager.getBizObject("clasesAccessService");
	ClaseLicenciaDefinition claseLicenciaService = (ClaseLicenciaDefinition) ContextManager.getBizObject("claseLicenciaService");
	
	UsuariosAccessDefinition usuariosAccessService = (UsuariosAccessDefinition) ContextManager.getBizObject("usuariosAccessService");
	UsuarioDefinition usuarioService = (UsuarioDefinition) ContextManager.getBizObject("usuarioService");
	
	LicenciasAccessDefinition licenciasAccessService = (LicenciasAccessDefinition) ContextManager.getBizObject("licenciasAccessService");
	LicenciaDefinition licenciaService = (LicenciaDefinition) ContextManager.getBizObject("licenciaService");
	
	CajaAccessDefinition cajaAccessService = (CajaAccessDefinition) ContextManager.getBizObject("cajaAccessService");
	CajaDefinition cajaService = (CajaDefinition) ContextManager.getBizObject("cajaService");
	
	public void apagarAuditoria(boolean apagar)
	{
		try
		{
			PropiedadDefinition propiedadService = (PropiedadDefinition) ContextManager.getBizObject("propiedadService");
			Propiedad propiedadGuardarLogDeEventos = new Propiedad();
			propiedadGuardarLogDeEventos.setPropClave("SISTEMA.GUARDAR.LOG.EVENTOS");
			if(apagar)
				propiedadGuardarLogDeEventos.setPropValor("N");
			else
				propiedadGuardarLogDeEventos.setPropValor("S");
			
			propiedadService.update(propiedadGuardarLogDeEventos);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void importar(String file)
	{
		try
		{
			apagarAuditoria(true);
			
			revertImport();
			
			File f=null;
			
		    if(file==null)
		    	f=new File("fine.mdb");
		    else
		    	f=new File(file);
		    	
			String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+f.getAbsolutePath();
			DriverManagerDataSource driverManagerDataSource=(DriverManagerDataSource)ContextManager.getBizObject("dataSourceAccess");
			driverManagerDataSource.setUrl(url);
			
				
	
			
			List<Personas> lst=personasAccessService.getAll(new Personas());
			for(int i=0;i<lst.size();i++)
			{
				Personas perAccess=lst.get(i);
				Persona per=personaAccessToPersona(perAccess);
				personaService.insert(per);
		
			}
			
	
			
			List<Antecedentes> lstA=antecedentesAccessService.getAll(new Antecedentes());
			for(int i=0;i<lstA.size();i++)
			{
				Antecedente ant=antecedenteAccessToAntecedente(lstA.get(i));
				if(ant.getAntId().intValue()==1)
				{
					ant.setAntDescripcion("No disponibles");
					ant.setAntApruebaSn("S");
				}
				antecedenteService.insert(ant);
			}
	
	
			
			List<Clases> lstClases=clasesAccessService.getAll(new Clases());
			for(int i=0;i<lstClases.size();i++)
			{
				ClaseLicencia usr=claseLicAccessToClaseLic(lstClases.get(i));
				claseLicenciaService.insert(usr);
			}
			
	
			
			List<Usuarios> lstUsr=usuariosAccessService.getAll(new Usuarios());
			for(int i=0;i<lstUsr.size();i++)
			{
				Usuario usr=usrAccessToUsr(lstUsr.get(i));
				usuarioService.insert(usr);
			}
			
	
			
			List<Licencias> lstLic=licenciasAccessService.getAll(new Licencias());
			for(int i=0;i<lstLic.size();i++)
			{
				Licencia lic=licenciaAccessToLicencia(lstLic.get(i));
				licenciaService.insert(lic);
			}
			
			
			List<autoimpresor.domain.access.Caja> lstCaja=cajaAccessService.getAll(new autoimpresor.domain.access.Caja());
			for(int i=0;i<lstCaja.size();i++)
			{
				autoimpresor.domain.Caja caj=cajaAccessToCaja(lstCaja.get(i));
				if(caj!=null)
					cajaService.insert(caj);
			}
			
	
			
			apagarAuditoria(false);
		}
		catch(Exception ex)
		{
			revertImport();
			throw new RuntimeException(ex);
		}
	}
	
	public LicenciaImp licenciaAccessToLicencia(Licencias licAcces)
	{
		try
		{
			LicenciaImp lic=new LicenciaImp();
			
			Persona per=personaService.get(licAcces.getPersona().longValue());
			UsuarioCommon usrConfeccionoLicencia=usuarioService.get(licAcces.getConfecciono().longValue(),Usuario.class);
			UsuarioCommon usrNombreResponsable=usuarioService.get(licAcces.getResponsable().longValue(),Usuario.class);
			UsuarioCommon usrNombreFirma=usuarioService.get(licAcces.getFirma().longValue(),Usuario.class);
			Antecedente ant=antecedenteService.get(licAcces.getAntecedentes().longValue());
			
			lic.setLicId(licAcces.getCodigoLicencia().longValue());
			lic.setPersona(per);
			lic.setUsuarioByUsrConfeccionoLicencia(usrConfeccionoLicencia);
			lic.setUsuarioByUsrNombreResponsable(usrNombreResponsable);
			lic.setUsuarioByUsrNombreFirma(usrNombreFirma);
			lic.setLicObservaciones(licAcces.getObservaciones());
			lic.setLicClase(licAcces.getClase());
			lic.setLicTramite(licAcces.getTramite());
			lic.setLicFechaOtorgada(licAcces.getFechaOtor());
			lic.setLicFechaVencimiento(licAcces.getFechaVen());
			lic.setLicEstado(licAcces.getEstado());
			lic.setLicExamenTeorico(licAcces.getTeorico());
			lic.setLicExamenPractico(licAcces.getPractico());
			
			lic.setAntecedente(ant);
			lic.setLicDeudaSn(licAcces.getDeuda() ? "S" : "N");
			lic.setLicFechaReal(licAcces.getFechaReal());
			lic.setLicRequisitosSn(licAcces.getRequisitos()? "S" : "N");
			lic.setLicNumero(licAcces.getNumero().longValue());
			lic.setLicCodLicencia(licAcces.getNroLicencia());
			lic.setLicExamenMedico(licAcces.getMedico());
			lic.setLicExamenOftalmologico(licAcces.getOftalmologico());
			lic.setLicExamenPsicofisico(licAcces.getPsicofisico());
	
			
			return lic;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public UsuarioImp usrAccessToUsr(Usuarios usrAcces)
	{
		UsuarioImp usr=new UsuarioImp();
		
		usr.setUsrId(usrAcces.getCodigoUsuario().longValue());
		usr.setUsrNombre(usrAcces.getNombreUsuario());
		usr.setUsrCargo(usrAcces.getCargo());
		usr.setUsrClave(usrAcces.getPassword());
		usr.setUsrUltimoAcceso(null);
		usr.setDeletedSn("S");

		usr.setUsrHabilitadoSn(usrAcces.getEliminado().equals("S") ? "N" : "S");
		usr.setUsrPuedeFirmarSn(usrAcces.getFirma() ? "S" : "N");
		usr.setUsrAccPanelControlSn(usrAcces.getPanel() ? "S" : "N");
		usr.setUsrAdmUsrSn(usrAcces.getAdministrar()? "S" : "N");
		usr.setUsrRestaurarBackSn(usrAcces.getRestaurar()? "S" : "N");
		usr.setUsrResetearSn(usrAcces.getResetear()? "S" : "N");
		usr.setUsrModificarLicSn(usrAcces.getModificar()? "S" : "N");
		usr.setUsrAutorizadoSn(usrAcces.getAutorizado()? "S" : "N");
		usr.setUsrControlAdmSn(usrAcces.getCadministrativo()? "S" : "N");

		
		return usr;
	}
	
	public PersonaImp personaAccessToPersona(Personas perAccess)
	{
		PersonaImp per=new PersonaImp();

		per.setPerId(perAccess.getCodigoPersona().longValue());
		per.setPerApellido(perAccess.getApellido());
		
		String nombreCompleto=perAccess.getNombre1();
		
		if(perAccess.getNombre2()!=null && !perAccess.getNombre2().equals("") && !perAccess.getNombre2().trim().equals(""))
			nombreCompleto+=" "+perAccess.getNombre2();
		
		per.setPerNombre(nombreCompleto);
		per.setPerTipoDoc(perAccess.getTipoDni());
		
		if(perAccess.getDni()!=null)
			per.setPerNumeroDoc(perAccess.getDni().replaceAll("\\.",""));
		
		per.setPerNacionalidad(perAccess.getNacionalidad());
		per.setPerSexo(perAccess.getSexo());
		per.setPerFechaNacimiento(perAccess.getFechaNac());
		per.setPerGrupoSanguineo(perAccess.getFactorSangre());
		per.setPerTelefono(perAccess.getTelefono());
		per.setPerDonante(perAccess.getDonante());
		per.setPerAlergia(perAccess.getAlergia());
		per.setPerRestricciones(perAccess.getRestricciones());
		per.setPerMedicacion(perAccess.getMedicacion());
		per.setPerDomicilio(perAccess.getDomicilio());
		
		return per;
	}
	
	public AntecedenteImp antecedenteAccessToAntecedente(Antecedentes antAccess)
	{
		AntecedenteImp ant=new AntecedenteImp();

		ant.setAntId(antAccess.getCodigoAntecedente().longValue());
		ant.setAntDescripcion(antAccess.getDescripcionAntecedente());
		ant.setAntApruebaSn(antAccess.getAprueba());
		
		return ant;
	}
	
	public Caja cajaAccessToCaja(autoimpresor.domain.access.Caja cajAccess)
	{
		try {
			Caja caj=new Caja();
	
			Licencia lic=licenciaService.get(cajAccess.getLicencia().longValue());
			
			if(lic!=null)
			{
				caj.setLicencia(lic);
				
				if(cajAccess.getImporte()!=null)
					caj.setCajImporte(cajAccess.getImporte().doubleValue());
				
				caj.setCajObservaciones(cajAccess.getObservaciones());
				
				if(cajAccess.getRecibo()!=null)
				caj.setCajRecibo(cajAccess.getRecibo().longValue());
				
				caj.setCajFecha(cajAccess.getFecha());
			}
			else
				return null;
			
			return caj;
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public ClaseLicenciaImp claseLicAccessToClaseLic(Clases claAccess)
	{
		try {
			ClaseLicenciaImp claLic=new ClaseLicenciaImp();

			claLic.setCllId(claAccess.getCodigoClase().longValue());
			claLic.setCllNombreClase(claAccess.getDescripcionClase());
			
			if(claAccess.getDuracion()!=null)
				claLic.setCllVigenciaPredeterminada(claAccess.getDuracion().longValue());
			
			if(claAccess.getMinima()!=null)
				claLic.setCllEdadMinima(claAccess.getMinima().longValue());
			
			if(claAccess.getMaxima()!=null)
				claLic.setCllEdadMaxima(claAccess.getMaxima().longValue());
			
			if(claAccess.getConcepto()!=null && claAccess.getConcepto().length()<47)
				claLic.setCllDescripcionCorta(claAccess.getConcepto().substring(0,claAccess.getConcepto().length()-4)+"...");
			else if(claAccess.getConcepto()!=null)
				claLic.setCllDescripcionCorta(claAccess.getConcepto().substring(0,47)+"...");
			
			claLic.setCllDescripcion(claAccess.getConcepto());
			
			if(claAccess.getI6()!=null)
				claLic.setCllImportex6meses(claAccess.getI6().doubleValue());
			
			if(claAccess.getI12()!=null)
				claLic.setCllImportex12meses(claAccess.getI12().doubleValue());

			if(claAccess.getI24()!=null)
				claLic.setCllImportex24meses(claAccess.getI24().doubleValue());
			
			if(claAccess.getI36()!=null)
				claLic.setCllImportex36meses(claAccess.getI36().doubleValue());
			
			if(claAccess.getI48()!=null)
				claLic.setCllImportex48meses(claAccess.getI48().doubleValue());
			
			if(claAccess.getI60()!=null)
				claLic.setCllImportex60meses(claAccess.getI60().doubleValue());
			
			return claLic;
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void revertImport() 
	{	
		try {
			
			String sql1="delete from app.caja";
			String sql2="delete from app.licencia";
			String sql3="delete from app.antecedente";
			String sql4="delete from app.clase_licencia";
			String sql5="delete from app.persona";			
			String sql6="delete from app.usuario where usr_id<>-1";
			
			
			Connection conn=ContextManager.getCurrentConnection();
			Statement stm=conn.createStatement();
			stm.execute(sql1);
			stm.execute(sql2);
			stm.execute(sql3);
			stm.execute(sql4);
			stm.execute(sql5);
			stm.execute(sql6);
			
			conn.commit();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
