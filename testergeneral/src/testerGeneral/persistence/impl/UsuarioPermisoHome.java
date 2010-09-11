package testerGeneral.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import testerGeneral.domain.UsuarioPermiso;
import testerGeneral.persistence.DAOObject;
import testerGeneral.persistence.UsuarioPermisoDao;


/**
 * Home object for domain model class UsuarioPermiso.
 * @see testerGeneral.persistence.impl.UsuarioPermiso
 * @author Hibernate Tools
 */
public class UsuarioPermisoHome extends DAOObject implements UsuarioPermisoDao {

    private static final Log log = LogFactory.getLog(UsuarioPermisoHome.class);
    
    public void insert(UsuarioPermiso transientInstance) throws Exception {
        log.debug("persisting UsuarioPermiso instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(UsuarioPermiso transientInstance) throws Exception {
        log.debug("persisting UsuarioPermiso instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(UsuarioPermiso persistentInstance) throws Exception {
        log.debug("deleting UsuarioPermiso instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
        	//log.error("delete failed", re);
           throw re;
        }
    }
    
    public UsuarioPermiso get(Long p_Id) throws Exception {
        log.debug("getting UsuarioPermiso instance with id: " + p_Id);
        try {
            UsuarioPermiso instance = (UsuarioPermiso) getHibernateTemplate()
                    .get(UsuarioPermiso.class, p_Id);
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
    
    
    public List getAll(UsuarioPermiso p_example) throws Exception {
        log.debug("finding UsuarioPermiso instance by example");
        try {
            Criteria cri = getSession().createCriteria(UsuarioPermiso.class);
            
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
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