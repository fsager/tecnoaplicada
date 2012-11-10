package autoimpresor.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import testerGeneral.persistence.DAOObject;
import autoimpresor.domain.access.Licencias;
import autoimpresor.persistence.LicenciasAccessDao;


/**
 * Home object for domain model class LicenciasAccess.
 * @see autoimpresor.persistence.impl.LicenciasAccess
 * @author Hibernate Tools
 */
public class LicenciasAccessHome extends DAOObject implements LicenciasAccessDao {

    private static final Log log = LogFactory.getLog(LicenciasAccessHome.class);
    
    public void insert(Licencias transientInstance) throws Exception {
        log.debug("persisting LicenciasAccess instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Licencias transientInstance) throws Exception {
        log.debug("persisting LicenciasAccess instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Licencias persistentInstance) throws Exception {
        log.debug("deleting LicenciasAccess instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
           //log.error("delete failed", re);
           throw re;
        }
    }
    
    public Licencias get(Long p_Id) throws Exception {
        log.debug("getting LicenciasAccess instance with id: " + p_Id);
        try {
        	Licencias instance = (Licencias) getHibernateTemplate()
                    .get(Licencias.class, p_Id);
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
    
    
    public List getAll(Licencias p_example) throws Exception {
        log.debug("finding LicenciasAccess instance by example");
        try {
            Criteria cri = getSession().createCriteria(Licencias.class);
            
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