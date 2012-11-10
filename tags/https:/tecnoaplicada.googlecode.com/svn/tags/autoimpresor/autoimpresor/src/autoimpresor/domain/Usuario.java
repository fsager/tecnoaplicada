package autoimpresor.domain;

import java.util.HashSet;
import java.util.Set;

import testerGeneral.domain.UsuarioCommon;

/**
 * Usuario entity. @author MyEclipse Persistence Tools
 */

public class Usuario extends UsuarioCommon implements java.io.Serializable {

	// Fields

	private Long usrId;
	private String usrPuedeFirmarSn;
	private String usrAccPanelControlSn;
	private String usrAdmUsrSn;
	private String usrRestaurarBackSn;
	private String usrResetearSn;
	private String usrModificarLicSn;
	private String usrAutorizadoSn;
	private String usrControlAdmSn;
		
	private Set licenciasForUsrNombreFirma = new HashSet(0);
	private Set licenciasForUsrNombreResponsable = new HashSet(0);
	private Set licenciasForUsrConfeccionoLicencia = new HashSet(0);
	private Set licenciasForUsrNombreFirma_1 = new HashSet(0);

	// Constructors

	/** default constructor */
	public Usuario() {
	}

	public Long getUsrId() {
		return usrId;
	}

	public void setUsrId(Long usrId) {
		this.usrId = usrId;
	}

	public String getUsrPuedeFirmarSn() {
		return usrPuedeFirmarSn;
	}

	public void setUsrPuedeFirmarSn(String usrPuedeFirmarSn) {
		this.usrPuedeFirmarSn = usrPuedeFirmarSn;
	}

	public String getUsrAccPanelControlSn() {
		return usrAccPanelControlSn;
	}

	public void setUsrAccPanelControlSn(String usrAccPanelControlSn) {
		this.usrAccPanelControlSn = usrAccPanelControlSn;
	}

	public String getUsrAdmUsrSn() {
		return usrAdmUsrSn;
	}

	public void setUsrAdmUsrSn(String usrAdmUsrSn) {
		this.usrAdmUsrSn = usrAdmUsrSn;
	}

	public String getUsrRestaurarBackSn() {
		return usrRestaurarBackSn;
	}

	public void setUsrRestaurarBackSn(String usrRestaurarBackSn) {
		this.usrRestaurarBackSn = usrRestaurarBackSn;
	}

	public String getUsrResetearSn() {
		return usrResetearSn;
	}

	public void setUsrResetearSn(String usrResetearSn) {
		this.usrResetearSn = usrResetearSn;
	}

	public String getUsrModificarLicSn() {
		return usrModificarLicSn;
	}

	public void setUsrModificarLicSn(String usrModificarLicSn) {
		this.usrModificarLicSn = usrModificarLicSn;
	}

	public String getUsrAutorizadoSn() {
		return usrAutorizadoSn;
	}

	public void setUsrAutorizadoSn(String usrAutorizadoSn) {
		this.usrAutorizadoSn = usrAutorizadoSn;
	}

	public String getUsrControlAdmSn() {
		return usrControlAdmSn;
	}

	public void setUsrControlAdmSn(String usrControlAdmSn) {
		this.usrControlAdmSn = usrControlAdmSn;
	}

	public Set getLicenciasForUsrNombreFirma() {
		return licenciasForUsrNombreFirma;
	}

	public void setLicenciasForUsrNombreFirma(Set licenciasForUsrNombreFirma) {
		this.licenciasForUsrNombreFirma = licenciasForUsrNombreFirma;
	}

	public Set getLicenciasForUsrNombreResponsable() {
		return licenciasForUsrNombreResponsable;
	}

	public void setLicenciasForUsrNombreResponsable(
			Set licenciasForUsrNombreResponsable) {
		this.licenciasForUsrNombreResponsable = licenciasForUsrNombreResponsable;
	}

	public Set getLicenciasForUsrConfeccionoLicencia() {
		return licenciasForUsrConfeccionoLicencia;
	}

	public void setLicenciasForUsrConfeccionoLicencia(
			Set licenciasForUsrConfeccionoLicencia) {
		this.licenciasForUsrConfeccionoLicencia = licenciasForUsrConfeccionoLicencia;
	}

	public Set getLicenciasForUsrNombreFirma_1() {
		return licenciasForUsrNombreFirma_1;
	}

	public void setLicenciasForUsrNombreFirma_1(Set licenciasForUsrNombreFirma_1) {
		this.licenciasForUsrNombreFirma_1 = licenciasForUsrNombreFirma_1;
	}
	
	public String toString() {
		if(getUsrNombre()==null)
			return "";
		return getUsrNombre();
	}

}