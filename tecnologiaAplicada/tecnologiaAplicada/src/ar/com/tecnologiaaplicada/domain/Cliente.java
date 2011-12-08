package ar.com.tecnologiaaplicada.domain;

// Generated 15-ene-2011 15:36:58 by Hibernate Tools 3.4.0.Beta1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Cliente generated by hbm2java
 */
@Entity
@Table(name = "CLIENTE")
public class Cliente implements java.io.Serializable {

	private Long cliId;
	private String cliRazonSocial;
	private String cliCuitCuil;
	private String cliTelefono;
	private String cliCelular;
	private String cliCorreo;
	private String cliDomicilio;
	private String cliObservaciones;
	/*private Set<Licencia> licencias = new HashSet<Licencia>(0);*/

	public Cliente() {
	}

	public Cliente(String cliRazonSocial) {
		this.cliRazonSocial = cliRazonSocial;
	}

	/*public Cliente(String cliRazonSocial, String cliCuitCuil,
			String cliTelefono, String cliCelular, String cliCorreo,
			String cliDomicilio, String cliObservaciones,
			Set<Licencia> licencias) {
		this.cliRazonSocial = cliRazonSocial;
		this.cliCuitCuil = cliCuitCuil;
		this.cliTelefono = cliTelefono;
		this.cliCelular = cliCelular;
		this.cliCorreo = cliCorreo;
		this.cliDomicilio = cliDomicilio;
		this.cliObservaciones = cliObservaciones;
		this.licencias = licencias;
	}*/

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CLI_ID", unique = true, nullable = false)
	public Long getCliId() {
		return this.cliId;
	}

	public void setCliId(Long cliId) {
		this.cliId = cliId;
	}

	@Column(name = "CLI_RAZON_SOCIAL", nullable = false, length = 200)
	public String getCliRazonSocial() {
		return this.cliRazonSocial;
	}

	public void setCliRazonSocial(String cliRazonSocial) {
		this.cliRazonSocial = cliRazonSocial;
	}

	@Column(name = "CLI_CUIT_CUIL", length = 200)
	public String getCliCuitCuil() {
		return this.cliCuitCuil;
	}

	public void setCliCuitCuil(String cliCuitCuil) {
		this.cliCuitCuil = cliCuitCuil;
	}

	@Column(name = "CLI_TELEFONO", length = 40)
	public String getCliTelefono() {
		return this.cliTelefono;
	}

	public void setCliTelefono(String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}

	@Column(name = "CLI_CELULAR", length = 40)
	public String getCliCelular() {
		return this.cliCelular;
	}

	public void setCliCelular(String cliCelular) {
		this.cliCelular = cliCelular;
	}

	@Column(name = "CLI_CORREO", length = 60)
	public String getCliCorreo() {
		return this.cliCorreo;
	}

	public void setCliCorreo(String cliCorreo) {
		this.cliCorreo = cliCorreo;
	}

	@Column(name = "CLI_DOMICILIO", length = 200)
	public String getCliDomicilio() {
		return this.cliDomicilio;
	}

	public void setCliDomicilio(String cliDomicilio) {
		this.cliDomicilio = cliDomicilio;
	}

	@Column(name = "CLI_OBSERVACIONES", length = 200)
	public String getCliObservaciones() {
		return this.cliObservaciones;
	}

	public void setCliObservaciones(String cliObservaciones) {
		this.cliObservaciones = cliObservaciones;
	}

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	public Set<Licencia> getLicencias() {
		return this.licencias;
	}

	public void setLicencias(Set<Licencia> licencias) {
		this.licencias = licencias;
	}*/

}
