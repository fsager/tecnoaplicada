package autoimpresor.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import testerGeneral.persistence.DAOObject;
import autoimpresor.domain.access.Localidades;
import autoimpresor.persistence.LocalidadesAccessDao;


/**
 * Home object for domain model class LocalidadesAccess.
 * @see autoimpresor.persistence.impl.LocalidadesAccess
 * @author Hibernate Tools
 */
public class LocalidadesAccessHome extends DAOObject implements LocalidadesAccessDao {

    private static final Log log = LogFactory.getLog(LocalidadesAccessHome.class);
    
    public void insert(Localidades transientInstance) throws Exception {
        log.debug("persisting LocalidadesAccess instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Localidades transientInstance) throws Exception {
        log.debug("persisting LocalidadesAccess instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Localidades persistentInstance) throws Exception {
        log.debug("deleting LocalidadesAccess instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
           //log.error("delete failed", re);
           throw re;
        }
    }
    
    public Localidades get(Long p_Id) throws Exception {
        log.debug("getting LocalidadesAccess instance with id: " + p_Id);
        try {
        	Localidades instance = (Localidades) getHibernateTemplate()
                    .get(Localidades.class, p_Id);
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
    
    
    public List getAll(Localidades p_example) throws Exception {
        log.debug("finding LocalidadesAccess instance by example");
        try {
            Criteria cri = getSession().createCriteria(Localidades.class);
            
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