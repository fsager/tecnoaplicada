package testerGeneral.persistence;

import java.io.Serializable;

import testerGeneral.domain.UsuarioCommon;

/**
 * @created 30-Oct-2006 10:42:50 AM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public interface UsuarioDao {

	/**
	 * Permite eliminar un objeto del dominio
	 * 
	 * @param p_domain    Objeto de dominio que se desea eliminar
	 */
	public abstract void delete(UsuarioCommon p_domain) throws Exception;

	public UsuarioCommon getUsrName(String p_Id,Class classs) throws Exception;
	
	public UsuarioCommon get(Serializable p_Id,Class classs) throws Exception;
	

	/**
	 * Este m�todo retornar� una lista de objetos de dominio que coincidieron con las
	 * condiciones del objeto de ejemplo env�ado por par�metro.
	 * 
	 * @param p_example    Objeto de dominio que se utilizar� como ejemplo en el
	 * filtro al momento de realizar el query.
	 */
	public abstract java.util.List getAll(UsuarioCommon p_example) throws Exception;

	/**
	 * Insertar un registro de dominio.
	 * 
	 * @param p_domain    p_domain
	 */
	public abstract void insert(UsuarioCommon p_domain) throws Exception;

	/**
	 * Actualizar un registro de dominio.
	 * 
	 * @param p_domain    p_domain
	 */
	public abstract void update(UsuarioCommon p_domain) throws Exception;

}