package autoimpresor.domain.access;

/**
 * Usuarios entity. @author MyEclipse Persistence Tools
 */

public class Usuarios implements java.io.Serializable {

	// Fields

	private Integer codigoUsuario;
	private String nombreUsuario;
	private String cargo;
	private String password;
	private Boolean firma;
	private Boolean panel;
	private Boolean administrar;
	private Boolean restaurar;
	private Boolean resetear;
	private Boolean modificar;
	private Boolean autorizado;
	private Boolean cadministrativo;
	private String eliminado;

	// Constructors

	/** default constructor */
	public Usuarios() {
	}

	/** full constructor */
	public Usuarios(String nombreUsuario, String cargo, String password,
			Boolean firma, Boolean panel, Boolean administrar,
			Boolean restaurar, Boolean resetear, Boolean modificar,
			Boolean autorizado, Boolean cadministrativo, String eliminado) {
		this.nombreUsuario = nombreUsuario;
		this.cargo = cargo;
		this.password = password;
		this.firma = firma;
		this.panel = panel;
		this.administrar = administrar;
		this.restaurar = restaurar;
		this.resetear = resetear;
		this.modificar = modificar;
		this.autorizado = autorizado;
		this.cadministrativo = cadministrativo;
		this.eliminado = eliminado;
	}

	// Property accessors

	public Integer getCodigoUsuario() {
		return this.codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getFirma() {
		return this.firma;
	}

	public void setFirma(Boolean firma) {
		this.firma = firma;
	}

	public Boolean getPanel() {
		return this.panel;
	}

	public void setPanel(Boolean panel) {
		this.panel = panel;
	}

	public Boolean getAdministrar() {
		return this.administrar;
	}

	public void setAdministrar(Boolean administrar) {
		this.administrar = administrar;
	}

	public Boolean getRestaurar() {
		return this.restaurar;
	}

	public void setRestaurar(Boolean restaurar) {
		this.restaurar = restaurar;
	}

	public Boolean getResetear() {
		return this.resetear;
	}

	public void setResetear(Boolean resetear) {
		this.resetear = resetear;
	}

	public Boolean getModificar() {
		return this.modificar;
	}

	public void setModificar(Boolean modificar) {
		this.modificar = modificar;
	}

	public Boolean getAutorizado() {
		return this.autorizado;
	}

	public void setAutorizado(Boolean autorizado) {
		this.autorizado = autorizado;
	}

	public Boolean getCadministrativo() {
		return this.cadministrativo;
	}

	public void setCadministrativo(Boolean cadministrativo) {
		this.cadministrativo = cadministrativo;
	}

	public String getEliminado() {
		return this.eliminado;
	}

	public void setEliminado(String eliminado) {
		this.eliminado = eliminado;
	}

}