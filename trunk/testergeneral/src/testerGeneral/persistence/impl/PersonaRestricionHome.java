package testerGeneral.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import testerGeneral.domain.PersonaRestricion;
import testerGeneral.persistence.DAOObject;
import testerGeneral.persistence.PersonaRestricionDao;


/**
 * Home object for domain model class PersonaRestricion.
 * @see testerGeneral.persistence.impl.PersonaRestricion
 * @author Hibernate Tools
 */
public class PersonaRestricionHome extends DAOObject implements PersonaRestricionDao {

    private static final Log log = LogFactory.getLog(PersonaRestricionHome.class);
    
    public void insert(PersonaRestricion transientInstance) throws Exception {
        log.debug("persisting PersonaRestricion instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(PersonaRestricion transientInstance) throws Exception {
        log.debug("persisting PersonaRestricion instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(PersonaRestricion persistentInstance) throws Exception {
        log.debug("deleting PersonaRestricion instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
        	//log.error("delete failed", re);
           throw re;
        }
    }
    
    public PersonaRestricion get(Long p_Id) throws Exception {
        log.debug("getting PersonaRestricion instance with id: " + p_Id);
        try {
            PersonaRestricion instance = (PersonaRestricion) getHibernateTemplate()
                    .get(PersonaRestricion.class, p_Id);
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
    
    
    public List getAll(PersonaRestricion p_example) throws Exception {
        log.debug("finding PersonaRestricion instance by example");
        try {
            Criteria cri = getSession().createCriteria(PersonaRestricion.class);
            
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