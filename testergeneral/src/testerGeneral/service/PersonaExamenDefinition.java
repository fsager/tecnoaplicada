package testerGeneral.service;
import java.util.Date;
import java.util.List;

import testerGeneral.domain.PersonaExamen;

/**
 * @created 30-Oct-2006 10:42:50 AM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public interface PersonaExamenDefinition {

	/**
	 * Permite eliminar un objeto del dominio
	 * 
	 * @param p_domain    Objeto de dominio que se desea eliminar
	 */
	public abstract void delete(PersonaExamen p_domain) throws Exception;

	/**
	 * Permite obtener un objeto de dominio por su clave primaria.
	 * 
	 * @param p_Id    Identificador �nico del objeto que se desea obtener
	 */
	public abstract PersonaExamen get(Long p_Id) throws Exception;

	/**
	 * Este m�todo retornar� una lista de objetos de dominio que coincidieron con las
	 * condiciones del objeto de ejemplo env�ado por par�metro.
	 * 
	 * @param p_example    Objeto de dominio que se utilizar� como ejemplo en el
	 * filtro al momento de realizar el query.
	 */
	public abstract java.util.List getAll(PersonaExamen p_example) throws Exception;

	/**
	 * Insertar un registro de dominio.
	 * 
	 * @param p_domain    p_domain
	 */
	public abstract void insert(PersonaExamen p_domain) throws Exception;

	/**
	 * Actualizar un registro de dominio.
	 * 
	 * @param p_domain    p_domain
	 */
	public abstract void update(PersonaExamen p_domain) throws Exception;
	
	public Long getCantidadExamenes(Date lastDate) throws Exception;

    public List getAll(PersonaExamen p_example,Date desde, Date hasta) throws Exception ;
}