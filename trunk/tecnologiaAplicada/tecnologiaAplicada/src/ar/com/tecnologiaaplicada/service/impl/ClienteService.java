package ar.com.tecnologiaaplicada.service.impl;

import java.util.List;

import ar.com.tecnologiaaplicada.domain.Cliente;
import ar.com.tecnologiaaplicada.service.ClienteDefinition;

/**
 * @created 30-Oct-2006 12:19:43 PM
 * @author Juan Pablo Nicotra
 * @version 1.0
 */
public class ClienteService implements ClienteDefinition {

	protected ClienteDefinition biz;
	
	public ClienteService(){

	}
	
	public void setBusinessObject (ClienteDefinition p_biz)
	{
		biz=p_biz;
	}

	public void delete(Cliente p_domain) throws Exception {
		biz.delete(p_domain);
	}

	public Cliente get(java.io.Serializable p_Id) throws Exception {
		return biz.get(p_Id);
	}

	public List getAll(Cliente p_example) throws Exception {
		return biz.getAll(p_example);
	}

	public void insert(Cliente p_domain) throws Exception {
		biz.insert(p_domain);
	}

	public void update(Cliente p_domain) throws Exception {
		biz.update(p_domain);
	}
}