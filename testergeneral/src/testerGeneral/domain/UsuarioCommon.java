package testerGeneral.domain;

import java.util.Date;
import java.util.Set;

public class UsuarioCommon {
	
	private String usrNombre;
	private String usrCargo;
	private String usrClave;
	private java.util.Date usrUltimoAcceso;
	private String deletedSn;
	private String usrHabilitadoSn;
	private byte[] usrFirma=new byte[1];
	
	public String getUsrNombre() {
		return this.usrNombre;
	}

	public void setUsrNombre(String usrNombre) {
		this.usrNombre = usrNombre;
	}

	public String getUsrCargo() {
		return this.usrCargo;
	}

	public void setUsrCargo(String usrCargo) {
		this.usrCargo = usrCargo;
	}

	public String getUsrClave() {
		return this.usrClave;
	}

	public void setUsrClave(String usrClave) {
		this.usrClave = usrClave;
	}

	public java.util.Date getUsrUltimoAcceso() {
		return this.usrUltimoAcceso;
	}

	public void setUsrUltimoAcceso(java.util.Date usrUltimoAcceso) {
		this.usrUltimoAcceso = usrUltimoAcceso;
	}

	public String getUsrHabilitadoSn() {
		return this.usrHabilitadoSn;
	}

	public void setUsrHabilitadoSn(String usrHabilitadoSn) {
		this.usrHabilitadoSn = usrHabilitadoSn;
	}
	
	public String getDeletedSn() {
		return deletedSn;
	}

	public void setDeletedSn(String deletedSn) {
		this.deletedSn = deletedSn;
	}
	
	public byte[] getUsrFirma() {
		return usrFirma;
	}
	
	public void setUsrFirma(byte[] usrFirma) {
		this.usrFirma = usrFirma;
	}
	
	@Override
	public String toString() {
		return usrNombre;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((usrNombre == null) ? 0 : usrNombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		UsuarioCommon other = (UsuarioCommon) obj;
		if (usrNombre == null) {
			if (other.usrNombre != null)
				return false;
		} else if (!usrNombre.equals(other.usrNombre))
			return false;
		return true;
	}

}
