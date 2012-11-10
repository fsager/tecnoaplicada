package testerGeneral.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import testerGeneral.domain.UsuarioExamen;
import testerGeneral.persistence.DAOObject;
import testerGeneral.persistence.UsuarioExamenDao;


/**
 * Home object for domain model class UsuarioExamen.
 * @see testerGeneral.persistence.impl.UsuarioExamen
 * @author Hibernate Tools
 */
public class UsuarioExamenHome extends DAOObject implements UsuarioExamenDao {

    private static final Log log = LogFactory.getLog(UsuarioExamenHome.class);
    
    public void insert(UsuarioExamen transientInstance) throws Exception {
        log.debug("persisting UsuarioExamen instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(UsuarioExamen transientInstance) throws Exception {
        log.debug("persisting UsuarioExamen instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(UsuarioExamen persistentInstance) throws Exception {
        log.debug("deleting UsuarioExamen instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
        	//log.error("delete failed", re);
           throw re;
        }
    }
    
    public UsuarioExamen get(Long p_Id) throws Exception {
        log.debug("getting UsuarioExamen instance with id: " + p_Id);
        try {
            UsuarioExamen instance = (UsuarioExamen) getHibernateTemplate()
                    .get(UsuarioExamen.class, p_Id);
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
    
    
    public List getAll(UsuarioExamen p_example) throws Exception {
        log.debug("finding UsuarioExamen instance by example");
        try {
            Criteria cri = getSession().createCriteria(UsuarioExamen.class);
            
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