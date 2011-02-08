package testerGeneral.business;

import java.io.Serializable;
import java.util.List;

import testerGeneral.domain.UsuarioCommon;
import testerGeneral.persistence.UsuarioDao;
import testerGeneral.service.UsuarioDefinition;

public class UsuarioBiz implements UsuarioDefinition {
	UsuarioDao dao;
	
	public void setDao (UsuarioDao p_dao)
	{
		dao=p_dao;
	}
	
	public void delete(UsuarioCommon p_domain) throws Exception {
		dao.delete(p_domain);
	}

	public UsuarioCommon getUsrName(String p_Id,Class classs) throws Exception {
		return dao.getUsrName(p_Id,classs);
	}
	
	public UsuarioCommon get(Serializable p_Id,Class classs) throws Exception {
		return dao.get(p_Id,classs);
	}
	

	public List getAll(UsuarioCommon p_example) throws Exception {
		return dao.getAll(p_example);
	}

	public void insert(UsuarioCommon p_domain) throws Exception {
		dao.insert(p_domain);
	}

	public void update(UsuarioCommon p_domain) throws Exception {
		dao.update(p_domain);
	}

}
