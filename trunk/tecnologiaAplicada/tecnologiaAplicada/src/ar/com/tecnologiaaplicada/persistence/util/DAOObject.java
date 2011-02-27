package ar.com.tecnologiaaplicada.persistence.util;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class DAOObject extends HibernateDaoSupport {
	
	public Object getObject(Class clazz, Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}
	
	public List getObjects(Class clazz) {
		return getHibernateTemplate().loadAll(clazz);
	}
	
	public List getObjectsByQuery(String query, Object[] parms) {
		if (parms == null) {
			return getHibernateTemplate().find(query);
		} else {
			return getHibernateTemplate().find(query, parms);
		}
	}
	
	public void saveObject(Object object) {
		getHibernateTemplate().save(object);
	}
	
	public void updateObject (Object object) {
		getHibernateTemplate().update(object);
	}
	
	public void deleteObject(Object object) {
		getHibernateTemplate().delete(object);
	}

	public void deleteObject(Class clazz, Serializable id) {
		getHibernateTemplate().delete(getObject(clazz, id));
	}
	
}
