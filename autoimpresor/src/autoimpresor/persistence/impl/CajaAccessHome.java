package autoimpresor.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import testerGeneral.persistence.DAOObject;
import autoimpresor.domain.access.Caja;
import autoimpresor.persistence.CajaAccessDao;


/**
 * Home object for domain model class CajaAccess.
 * @see autoimpresor.persistence.impl.CajaAccess
 * @author Hibernate Tools
 */
public class CajaAccessHome extends DAOObject implements CajaAccessDao {

    private static final Log log = LogFactory.getLog(CajaAccessHome.class);
    
    public void insert(Caja transientInstance) throws Exception {
        log.debug("persisting CajaAccess instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Caja transientInstance) throws Exception {
        log.debug("persisting CajaAccess instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Caja persistentInstance) throws Exception {
        log.debug("deleting CajaAccess instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
           //log.error("delete failed", re);
           throw re;
        }
    }
    
    public Caja get(Long p_Id) throws Exception {
        log.debug("getting CajaAccess instance with id: " + p_Id);
        try {
        	Caja instance = (Caja) getHibernateTemplate()
                    .get(Caja.class, p_Id);
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
    
    
    public List getAll(Caja p_example) throws Exception {
        log.debug("finding CajaAccess instance by example");
        try {
            Criteria cri = getSession().createCriteria(Caja.class);
            
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