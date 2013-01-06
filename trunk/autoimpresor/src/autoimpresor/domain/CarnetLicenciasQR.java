package autoimpresor.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import testerGeneral.domain.Dominio;
import testerGeneral.service.DominioDefinition;
import autoimpresor.business.ContextManager;
import frontend.utils.Util;




/**
 * CarnetLicencias entity. @author MyEclipse Persistence Tools
 */

public class CarnetLicenciasQR extends  CarnetLicenciasExtendida implements java.io.Serializable {

	private static final long serialVersionUID = -374384809011033049L;
	
    //QR
	private String formatoLicencia;
    private Integer perDomNro;
    private Integer perDomNroPiso;     
    private String perDomLetraDpt;
    private String perDomCodigoPostal;
    private String perDomBarrio;
    
    private String perDomPais;
    private String perDomProvincia;
    private String perDomDepartamento;
    private String perDomLocalidad;
    
    private String munDomPais;
    private String munDomProvincia;
    private String munDomDepartamento;
    private String munDomLocalidad;
    private Long codigoSeguridad;
    private String perSexo;
    private String perNacionalidadCodigo;
    private String perDomSoloCalle;
    
    private String datosQR;
    private byte[] qr=new byte[1];
    
        
    //QR
    
    // Constructors
    /** default constructor */
    
    public CarnetLicenciasQR(Licencia lic,String nombreMunicipio,String codigoMunicipio,byte[] escudoMunicipio,String formato) {
    	super(lic,nombreMunicipio,codigoMunicipio,escudoMunicipio);
		try
		{			
			actualizarFechas();
	        this.munDomPais=ContextManager.getProperty("SISTEMA.PAIZ.MUNICIPIO");
	        this.munDomProvincia=ContextManager.getProperty("SISTEMA.PROVINCIA.MUNICIPIO");
	        this.munDomDepartamento=ContextManager.getProperty("SISTEMA.DEPARTAMENTO.MUNICIPIO");
	        this.munDomLocalidad=ContextManager.getProperty("SISTEMA.LOCALIDAD.MUNICIPIO");
	        
    		if(formato==null)
    			this.formatoLicencia=ContextManager.getProperty("LICENCIA.FORMATO");
    		else
    			this.formatoLicencia=formato;
    		
	    	this.perDomNro=lic.getPersona().getPerDomNro();
	    	this.perDomNroPiso=lic.getPersona().getPerDomNroPiso();
	    	this.perDomLetraDpt=lic.getPersona().getPerDomLetraDpt();
	    	this.perDomCodigoPostal=lic.getPersona().getPerDomCodigoPostal();
	    	this.perDomBarrio=lic.getPersona().getPerDomBarrio();
	    	this.perDomPais=lic.getPersona().getPerDomPais();
	    	this.perDomProvincia=lic.getPersona().getPerDomProvincia();
	    	this.perDomDepartamento=lic.getPersona().getPerDomDepartamento();
	    	this.perDomLocalidad=lic.getPersona().getPerDomLocalidad();
	    	this.codigoSeguridad=lic.getLicNumero();
	    	this.perSexo=lic.getPersona().getPerSexo();
	    	
	    	this.perNacionalidadCodigo=getPerNacionalidad();
	    	this.perDomSoloCalle=getPerDomicilio();
	    		    	
	    	datosQR=getQRData();
	    	qr=autoimpresor.util.Util.getQRBytes(datosQR);
	    	
	    	//Luego modifico los valores para mostrarlos en el reportes. NO SE DEBE ALTERAR ESTE ORDEN.
	    	perDomLocalidad=deCodigoaValorMostras("Localidad",perDomLocalidad);
	    	perDomDepartamento=deCodigoaValorMostras("Departamento",perDomDepartamento);
	    	perDomProvincia=deCodigoaValorMostras("Provincia",perDomProvincia);
	    	perDomPais=deCodigoaValorMostras("Nacionalidad",perDomPais);
	    	setPerNacionalidad(deCodigoaValorMostras("Nacionalidad",getPerNacionalidad()));
	    	
	    	setPerDomicilio(lic.getPersona().getDomicilioCompleto());
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
    }
    
    public String deCodigoaValorMostras(String dominio,String codigo)
    {
		try
		{
			Dominio example=new Dominio();
			example.setDomClave(dominio);
			example.setDomCodigo(codigo);
			
			DominioDefinition dominioService=(DominioDefinition)ContextManager.getBizObject("dominioService");		
			List<Dominio> dominios=dominioService.getAll(example);
			
			if(dominios.size()>0)
				return dominios.get(0).getDomValorMostrar();
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		return null;
    }
    
    public void actualizarFechas() 
    {
    	try
    	{
			SimpleDateFormat sdf=new SimpleDateFormat(Util.formatoFecha); 
			this.setLicFechaOtorgada(new Timestamp(sdf.parse(this.getLicFechaOtorgadaTxt()).getTime()));
			this.setLicFechaVencimiento(new Timestamp(sdf.parse(this.getLicFechaVencimientoTxt()).getTime()));
			this.setPerFechaNacimiento(new Timestamp(sdf.parse(this.getPerFechaNacimientoTxt()).getTime()));
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
    }
    public String completaryTruncar(String dato, int largo)
    {
    	String nuevoDato=dato;
    	if(nuevoDato!=null && nuevoDato.length()<largo)
    	{
    		int tamañoActual=nuevoDato.length();
    		//Completo con espacios hasta el largo
    		for(int i=0;i<(largo-tamañoActual);i++)
    		{
    			nuevoDato+=" ";
    		}
    	}
    	else if(nuevoDato!=null && nuevoDato.length()>largo)
    	{
    		//Trunco hasta el largo
    		nuevoDato=nuevoDato.substring(0,largo);
    		
    	}
    	else if(nuevoDato==null)
    	{
    		nuevoDato=new String();
    		//Completo todo con espacios
    		for(int i=0;i<largo;i++)
    		{
    			nuevoDato+=" ";
    		}    		
    	}
    	
    	return nuevoDato;
    }
    
    public String getQRData()
    {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
    	String qrData=new String();
    	//1	DATO_001	Código de seguridad de la Licencia de Conducir	10
    	if(codigoSeguridad!=null)
    		qrData+=completaryTruncar(codigoSeguridad.toString(),10);
    	else
    		qrData+=completaryTruncar(null,10);
    	
    	//2	DATO_002	Fecha de Emisión Licencia	8
    	qrData+=sdf.format(this.getLicFechaOtorgada());
    	//3	DATO_003	Fecha con formato  YYYYMMDD (AñoMesDia)	8
    	qrData+=sdf.format(this.getLicFechaVencimiento());
    	//4	DATO_004	Clase de la Licencia de Conducir	3
    	qrData+=completaryTruncar(this.getLicClase(),3);
    	//5	DATO_005	Código del País de la Licencia	3
    	qrData+=completaryTruncar(this.getMunDomPais(),3);
    	//6	DATO_006	Código de la Provincia de la Licencia	2
    	qrData+=completaryTruncar(this.getMunDomProvincia(),2);
    	//7	DATO_007	Código del Departamento de la Licencia	6
    	qrData+=completaryTruncar(this.getMunDomDepartamento(),6);
    	//8	DATO_008	Código de la Localidad de la Licencia	6
    	qrData+=completaryTruncar(this.getMunDomLocalidad(),6);    	
    	//9	DATO_009	Código de Municipio	4
    	qrData+=completaryTruncar(this.getMncCodigo(),4);
    	//10	DATO_010	Código de Nacionalidad de la Persona	6
    	qrData+=completaryTruncar(perNacionalidadCodigo,6);
    	//11	DATO_011	Nro. de Documento de la Persona	12
    	qrData+=completaryTruncar(this.getPerNumeroDoc(),12);
    	//12	DATO_012	Tipo de Documento de la Persona	4
    	String tipoDoc=this.getPerTipoDoc().split("-")[0];
    	qrData+=completaryTruncar(tipoDoc,4);
    	//13	DATO_013	Código del Sexo de la Persona	1
    	qrData+=completaryTruncar(perSexo,1);
    	//14	DATO_014	Fecha de nacimiento YYYYMMDD (AñoMesDia)	8
    	qrData+=sdf.format(this.getPerFechaNacimiento());
    	//15	DATO_015	Apellido de la Persona	25
    	qrData+=completaryTruncar(this.getPerNombre(),25);
    	//16	DATO_016	Nombre e de la Persona	30
    	qrData+=completaryTruncar(this.getPerApellido(),30);
    	//17	DATO_017	Calle o Domicilio de la Persona	30
    	qrData+=completaryTruncar(perDomSoloCalle,30);
    	//18	DATO_018	El nro. que identifica a la ubicación del domicilio	4
    	if(this.getPerDomNro()!=null)
    		qrData+=completaryTruncar(this.getPerDomNro().toString(),4);
    	else
    		qrData+=completaryTruncar(null,4);    	
    	//19	DATO_019	El nro. del Piso del Edificio (si corresponde, de lo contrario se debe poner un valor vacio)	2
    	if(this.getPerDomNroPiso()!=null)
    		qrData+=completaryTruncar(this.getPerDomNroPiso().toString(),2);
    	else
    		qrData+=completaryTruncar(null,2);    	
    	//20	DATO_020	La Letra que identifica al Departamento del Domicilio de la Persona(vacio si no corresponde)	2
    	if(this.getPerDomLetraDpt()!=null)
    		qrData+=completaryTruncar(this.getPerDomLetraDpt().toString(),2);
    	else
    		qrData+=completaryTruncar(null,2);
    	//21	DATO_021	El Código postal que corresponde al Domicilio de la Persona	10
    	qrData+=completaryTruncar(this.getPerDomCodigoPostal(),10);
    	//22	DATO_022	Nombre del Barrio de la Persona	20
    	qrData+=completaryTruncar(this.getPerDomBarrio(),20);
    	//23	DATO_023	Código del País del domicilio de la  persona	3
    	qrData+=completaryTruncar(this.getPerDomPais(),3);
    	//24	DATO_024	Código de la Provincia del Domicilio de la persona	2
    	qrData+=completaryTruncar(this.getPerDomProvincia(),2);
    	//25	DATO_025	Código de Departamento	6
    	qrData+=completaryTruncar(this.getPerDomDepartamento(),6);
    	//26	DATO_026	Código Localidad	6
    	qrData+=completaryTruncar(this.getPerDomLocalidad(),6);
    	//27	DATO_027	Restricciones texto	10
    	qrData+=completaryTruncar(this.getPerRestricciones(),10);
    	//28	DATO_028	Alergia/s texto	1
    	String alergiaSN="S";
    	if(this.getPerAlergia()==null || this.getPerAlergia().equals("NO"))
    		alergiaSN="N";    			
    	qrData+=alergiaSN;
    	//29	DATO_029	Medicación texto	1
    	String medicacionSN="S";
    	if(this.getPerMedicacion()==null || this.getPerMedicacion().equals("NO"))
    		medicacionSN="N";    			
    	qrData+=medicacionSN;
    	//30	DATO_030	Donante	1
    	qrData+=this.getPerDonante();
    	//31	DATO_031	Grupo Sanguíneo Texto	1
    	qrData+=completaryTruncar(this.getPerGrupoSanguineo(),1);
    	//32	DATO_032	Teléfono Emergencia	10
    	qrData+=completaryTruncar(this.getPerTelefono(),10);
    	
    	return qrData;
    }
    
    public CarnetLicenciasQR() {
    }

	public Integer getPerDomNro() {
		return perDomNro;
	}

	public void setPerDomNro(Integer perDomNro) {
		this.perDomNro = perDomNro;
	}

	public Integer getPerDomNroPiso() {
		return perDomNroPiso;
	}

	public void setPerDomNroPiso(Integer perDomNroPiso) {
		this.perDomNroPiso = perDomNroPiso;
	}

	public String getPerDomLetraDpt() {
		return perDomLetraDpt;
	}

	public void setPerDomLetraDpt(String perDomLetraDpt) {
		this.perDomLetraDpt = perDomLetraDpt;
	}

	public String getPerDomCodigoPostal() {
		return perDomCodigoPostal;
	}

	public void setPerDomCodigoPostal(String perDomCodigoPostal) {
		this.perDomCodigoPostal = perDomCodigoPostal;
	}

	public String getPerDomBarrio() {
		return perDomBarrio;
	}

	public void setPerDomBarrio(String perDomBarrio) {
		this.perDomBarrio = perDomBarrio;
	}

	public String getPerDomPais() {
		return perDomPais;
	}

	public void setPerDomPais(String perDomPais) {
		this.perDomPais = perDomPais;
	}

	public String getPerDomProvincia() {
		return perDomProvincia;
	}

	public void setPerDomProvincia(String perDomProvincia) {
		this.perDomProvincia = perDomProvincia;
	}

	public String getPerDomDepartamento() {
		return perDomDepartamento;
	}

	public void setPerDomDepartamento(String perDomDepartamento) {
		this.perDomDepartamento = perDomDepartamento;
	}

	public String getPerDomLocalidad() {
		return perDomLocalidad;
	}

	public void setPerDomLocalidad(String perDomLocalidad) {
		this.perDomLocalidad = perDomLocalidad;
	}

	public String getMunDomPais() {
		return munDomPais;
	}

	public void setMunDomPais(String munDomPais) {
		this.munDomPais = munDomPais;
	}

	public String getMunDomProvincia() {
		return munDomProvincia;
	}

	public void setMunDomProvincia(String munDomProvincia) {
		this.munDomProvincia = munDomProvincia;
	}

	public String getMunDomDepartamento() {
		return munDomDepartamento;
	}

	public void setMunDomDepartamento(String munDomDepartamento) {
		this.munDomDepartamento = munDomDepartamento;
	}

	public String getMunDomLocalidad() {
		return munDomLocalidad;
	}

	public void setMunDomLocalidad(String munDomLocalidad) {
		this.munDomLocalidad = munDomLocalidad;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public String getFormatoLicencia() {
		return formatoLicencia;
	}
	
	public void setFormatoLicencia(String formatoLicencia) {
		this.formatoLicencia = formatoLicencia;
	}    
 
	public String getDatosQR() {
		return datosQR;
	}
	
	public void setDatosQR(String datosQR) {
		this.datosQR = datosQR;
	}
	
	public byte[] getQr() {
		return qr;
	}
	
	public void setQr(byte[] qr) {
		this.qr = qr;
	}

	public Long getCodigoSeguridad() {
		return codigoSeguridad;
	}

	public void setCodigoSeguridad(Long codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

	public String getPerSexo() {
		return perSexo;
	}

	public void setPerSexo(String perSexo) {
		this.perSexo = perSexo;
	}

	

}