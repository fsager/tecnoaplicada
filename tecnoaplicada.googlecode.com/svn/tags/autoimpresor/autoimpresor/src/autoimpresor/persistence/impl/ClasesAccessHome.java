package autoimpresor.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import testerGeneral.persistence.DAOObject;
import autoimpresor.domain.access.Clases;
import autoimpresor.persistence.ClasesAccessDao;


/**
 * Home object for domain model class ClasesAccess.
 * @see autoimpresor.persistence.impl.ClasesAccess
 * @author Hibernate Tools
 */
public class ClasesAccessHome extends DAOObject implements ClasesAccessDao {

    private static final Log log = LogFactory.getLog(ClasesAccessHome.class);
    
    public void insert(Clases transientInstance) throws Exception {
        log.debug("persisting ClasesAccess instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Clases transientInstance) throws Exception {
        log.debug("persisting ClasesAccess instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Clases persistentInstance) throws Exception {
        log.debug("deleting ClasesAccess instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
           //log.error("delete failed", re);
           throw re;
        }
    }
    
    public Clases get(Long p_Id) throws Exception {
        log.debug("getting ClasesAccess instance with id: " + p_Id);
        try {
        	Clases instance = (Clases) getHibernateTemplate()
                    .get(Clases.class, p_Id);
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
    
    
    public List getAll(Clases p_example) throws Exception {
        log.debug("finding ClasesAccess instance by example");
        try {
            Criteria cri = getSession().createCriteria(Clases.class);
            
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