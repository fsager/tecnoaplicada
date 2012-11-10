package autoimpresor.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import testerGeneral.persistence.DAOObject;
import autoimpresor.domain.access.Antecedentes;
import autoimpresor.persistence.AntecedentesAccessDao;


/**
 * Home object for domain model class AntecedentesAccess.
 * @see autoimpresor.persistence.impl.AntecedentesAccess
 * @author Hibernate Tools
 */
public class AntecedentesAccessHome extends DAOObject implements AntecedentesAccessDao {

    private static final Log log = LogFactory.getLog(AntecedentesAccessHome.class);
    
    public void insert(Antecedentes transientInstance) throws Exception {
        log.debug("persisting AntecedentesAccess instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Antecedentes transientInstance) throws Exception {
        log.debug("persisting AntecedentesAccess instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Antecedentes persistentInstance) throws Exception {
        log.debug("deleting AntecedentesAccess instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
           //log.error("delete failed", re);
           throw re;
        }
    }
    
    public Antecedentes get(Long p_Id) throws Exception {
        log.debug("getting AntecedentesAccess instance with id: " + p_Id);
        try {
        	Antecedentes instance = (Antecedentes) getHibernateTemplate()
                    .get(Antecedentes.class, p_Id);
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
    
    
    public List getAll(Antecedentes p_example) throws Exception {
        log.debug("finding AntecedentesAccess instance by example");
        try {
            Criteria cri = getSession().createCriteria(Antecedentes.class);
            
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