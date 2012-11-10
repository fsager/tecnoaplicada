package autoimpresor.domain.access;


/**
 * Personas entity. @author MyEclipse Persistence Tools
 */

public class Personas implements java.io.Serializable {

	// Fields

	private Integer codigoPersona;
	private String apellido;
	private String nombre1;
	private String nombre2;
	private String tipoDni;
	private String dni;
	private String nacionalidad;
	private java.util.Date fechaNac;
	private String domicilio;
	private String telefono;
	private String factorSangre;
	private String donante;
	private String restricciones;
	private String alergia;
	private String medicacion;
	private String sexo;

	// Constructors

	/** default constructor */
	public Personas() {
	}

	/** full constructor */
	public Personas(String apellido, String nombre1, String nombre2,
			String tipoDni, String dni, String nacionalidad,
			java.util.Date fechaNac, String domicilio, String telefono,
			String factorSangre, String donante, String restricciones,
			String alergia, String medicacion, String sexo) {
		this.apellido = apellido;
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.tipoDni = tipoDni;
		this.dni = dni;
		this.nacionalidad = nacionalidad;
		this.fechaNac = fechaNac;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.factorSangre = factorSangre;
		this.donante = donante;
		this.restricciones = restricciones;
		this.alergia = alergia;
		this.medicacion = medicacion;
		this.sexo = sexo;
	}

	// Property accessors

	public Integer getCodigoPersona() {
		return this.codigoPersona;
	}

	public void setCodigoPersona(Integer codigoPersona) {
		this.codigoPersona = codigoPersona;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre1() {
		return this.nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return this.nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getTipoDni() {
		return this.tipoDni;
	}

	public void setTipoDni(String tipoDni) {
		this.tipoDni = tipoDni;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public java.util.Date getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(java.util.Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFactorSangre() {
		return this.factorSangre;
	}

	public void setFactorSangre(String factorSangre) {
		this.factorSangre = factorSangre;
	}

	public String getDonante() {
		return this.donante;
	}

	public void setDonante(String donante) {
		this.donante = donante;
	}

	public String getRestricciones() {
		return this.restricciones;
	}

	public void setRestricciones(String restricciones) {
		this.restricciones = restricciones;
	}

	public String getAlergia() {
		return this.alergia;
	}

	public void setAlergia(String alergia) {
		this.alergia = alergia;
	}

	public String getMedicacion() {
		return this.medicacion;
	}

	public void setMedicacion(String medicacion) {
		this.medicacion = medicacion;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}