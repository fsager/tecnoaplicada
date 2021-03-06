package ar.com.tecnologiaaplicada.domain;

// Generated 15-ene-2011 15:36:58 by Hibernate Tools 3.4.0.Beta1

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import ar.com.tecnologiaaplicada.LicenseException;

/**
 * Activacion generated by hbm2java
 */
@Entity
@Table(name = "ACTIVACION")
public class Activacion implements java.io.Serializable {
	
	private Long actId;
	private Licencia licencia;
	private Date actFecha;
	private String actCodigo;
	private String actVersionApp;
	
	public Activacion() {
	}

	public Activacion(Licencia licencia, Date actFecha, String actCodigo) {
		this.licencia = licencia;
		this.actFecha = actFecha;
		this.actCodigo = actCodigo;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ACT_ID", unique = true, nullable = false)
	public Long getActId() {
		return this.actId;
	}

	public void setActId(Long actId) {
		this.actId = actId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIC_ID", nullable = false)
	public Licencia getLicencia() {
		return this.licencia;
	}

	public void setLicencia(Licencia licencia) {
		this.licencia = licencia;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACT_FECHA", nullable = false, length = 19)
	public Date getActFecha() {
		return this.actFecha;
	}

	public void setActFecha(Date actFecha) {
		this.actFecha = actFecha;
	}

	@Column(name = "ACT_CODIGO", nullable = false, length = 200)
	public String getActCodigo() {
		return this.actCodigo;
	}

	public void setActCodigo(String actCodigo) {
		this.actCodigo = actCodigo;
	}


	@Column(name = "ACT_VERSION_APP", length = 200)
	public String getActVersionApp() {
		return actVersionApp;
	}
	
	public void setActVersionApp(String actVersionApp) {
		this.actVersionApp = actVersionApp;
	}
	

	@Transient
	public String getActInfo() throws LicenseException
	{
		String codigoActivacionFormat="yyMMddHHmmss";
		SimpleDateFormat formatCodigo=new SimpleDateFormat(codigoActivacionFormat);
		
		Long longCodigoActivacion=null;
		Date fechaActivacion=null;
		String info=null;
		
		try
		{
			longCodigoActivacion=Long.parseLong(this.getActCodigo(),16);
			String a="123456789"; a.substring(a.length()-1,a.length());
			String codigo=String.valueOf(longCodigoActivacion);
			//Elimino la informacion de la fecha y el digito verificador.
			info=codigo.substring(codigoActivacionFormat.length(),codigo.length()-1);
			

		}
		catch(Exception e)
		{
			throw new LicenseException("El codigo de activaci�n es err�neo: "+this.getActCodigo());
		}
		
		return info;
	}
	
	@Transient
	public Date getActFechaCodigo() throws LicenseException
	{
		String codigoActivacionFormat="yyMMddHHmmss";
		SimpleDateFormat formatCodigo=new SimpleDateFormat(codigoActivacionFormat);
		
		Long longCodigoActivacion=null;
		Date fechaActivacion=null;
		String info=null;
		
		try
		{
			longCodigoActivacion=Long.parseLong(this.getActCodigo(),16);
			String fecha=String.valueOf(longCodigoActivacion).substring(0,codigoActivacionFormat.length());
			info=String.valueOf(longCodigoActivacion).substring(codigoActivacionFormat.length());
			fechaActivacion=formatCodigo.parse(fecha);

		}
		catch(Exception e)
		{
			throw new LicenseException("El codigo de activaci�n es err�neo: "+this.getActCodigo());
		}
		
		
		return fechaActivacion;
	}

}
