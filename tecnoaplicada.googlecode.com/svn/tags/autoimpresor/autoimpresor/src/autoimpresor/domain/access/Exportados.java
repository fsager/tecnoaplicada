package autoimpresor.domain.access;

import java.sql.Timestamp;

/**
 * Exportados entity. @author MyEclipse Persistence Tools
 */

public class Exportados implements java.io.Serializable {

	// Fields

	private Integer codigoExportado;
	private Timestamp fecha;
	private String nombre;
	private String dni;
	private Integer licencia;
	private Short dia;
	private Short mes;
	private Short año;

	// Constructors

	/** default constructor */
	public Exportados() {
	}

	/** full constructor */
	public Exportados(Timestamp fecha, String nombre, String dni,
			Integer licencia, Short dia, Short mes, Short año) {
		this.fecha = fecha;
		this.nombre = nombre;
		this.dni = dni;
		this.licencia = licencia;
		this.dia = dia;
		this.mes = mes;
		this.año = año;
	}

	// Property accessors

	public Integer getCodigoExportado() {
		return this.codigoExportado;
	}

	public void setCodigoExportado(Integer codigoExportado) {
		this.codigoExportado = codigoExportado;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Integer getLicencia() {
		return this.licencia;
	}

	public void setLicencia(Integer licencia) {
		this.licencia = licencia;
	}

	public Short getDia() {
		return this.dia;
	}

	public void setDia(Short dia) {
		this.dia = dia;
	}

	public Short getMes() {
		return this.mes;
	}

	public void setMes(Short mes) {
		this.mes = mes;
	}

	public Short getAño() {
		return this.año;
	}

	public void setAño(Short año) {
		this.año = año;
	}

}