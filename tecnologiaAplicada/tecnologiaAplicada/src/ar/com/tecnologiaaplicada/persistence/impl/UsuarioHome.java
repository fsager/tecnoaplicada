package ar.com.tecnologiaaplicada.persistence.impl;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ar.com.tecnologiaaplicada.domain.Usuario;
import ar.com.tecnologiaaplicada.persistence.UsuarioDao;
import ar.com.tecnologiaaplicada.persistence.util.DAOObject;


/**
 * Home object for domain model class Usuario.
 * @see testerGeneral.persistence.impl.Usuario
 * @author Hibernate Tools
 */
public class UsuarioHome extends DAOObject implements UsuarioDao {

    private static final Log log = LogFactory.getLog(UsuarioHome.class);
    
    public void insert(Usuario transientInstance) throws Exception {
        log.debug("persisting Usuario instance");
        try {
            saveObject(transientInstance);            
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Usuario transientInstance) throws Exception {
        log.debug("persisting Usuario instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Usuario persistentInstance) throws Exception {
        log.debug("deleting Usuario instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
        	//log.error("delete failed", re);
            throw re;
        }
    }
    
    public Usuario get(Serializable p_Id) throws Exception {
        log.debug("getting Persona instance with id: " + p_Id);
        try {
        	Usuario instance = (Usuario) getHibernateTemplate().get(Usuario.class,p_Id);
            if (instance==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
        	//log.error("get failed", re);
            throw re;
        }
    }

	
    public Usuario getUsrName(String p_Id,Class classs) throws Exception {
        log.debug("getting Usuario instance with id: " + p_Id);
        try {
        	Usuario usr=(Usuario)classs.newInstance();
        	usr.setUsrNombre(p_Id);
            Criteria cri = getSession().createCriteria(usr.getClass());
        	cri.add(Example.create(usr).enableLike().ignoreCase());

            usr = (Usuario)cri.uniqueResult();
            
            return usr;
        }
        catch (RuntimeException re) {
        	//log.error("get failed", re);
            throw re;
        }
    }
    
    public List getAll(Usuario p_example) throws Exception {
        log.debug("finding Usuario instance by example");
        try {
            Criteria cri = getSession().createCriteria(p_example.getClass());
            if(p_example.getUsrNombre()!=null)
            {
            	cri.add(Restrictions.ilike("usrNombre", p_example.getUsrNombre().toLowerCase(),MatchMode.START));
            	//cri.add(Restrictions.sqlRestriction("usr_nombre like '"+p_example.getUsrNombre().toLowerCase()+"%'"));
            }
            
            p_example.setUsrNombre(null);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            
            cri.addOrder(Order.asc("usrNombre"));
            List results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
        	//log.error("find by example failed", re);
            throw re;
        }
    }
}