package autoimpresor.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import testerGeneral.persistence.DAOObject;
import autoimpresor.domain.access.Personas;
import autoimpresor.persistence.PersonasAccessDao;


/**
 * Home object for domain model class PersonasAccess.
 * @see autoimpresor.persistence.impl.PersonasAccess
 * @author Hibernate Tools
 */
public class PersonasAccessHome extends DAOObject implements PersonasAccessDao {

    private static final Log log = LogFactory.getLog(PersonasAccessHome.class);
    
    public void insert(Personas transientInstance) throws Exception {
        log.debug("persisting PersonasAccess instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Personas transientInstance) throws Exception {
        log.debug("persisting PersonasAccess instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Personas persistentInstance) throws Exception {
        log.debug("deleting PersonasAccess instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
           //log.error("delete failed", re);
           throw re;
        }
    }
    
    public Personas get(Long p_Id) throws Exception {
        log.debug("getting PersonasAccess instance with id: " + p_Id);
        try {
        	Personas instance = (Personas) getHibernateTemplate()
                    .get(Personas.class, p_Id);
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
    
    
    public List getAll(Personas p_example) throws Exception {
        log.debug("finding PersonasAccess instance by example");
        try {
            Criteria cri = getSession().createCriteria(Personas.class);
            
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