package ar.com.tecnologiaaplicada.persistence.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import ar.com.tecnologiaaplicada.domain.Propiedad;
import ar.com.tecnologiaaplicada.persistence.PropiedadDao;
import ar.com.tecnologiaaplicada.persistence.util.DAOObject;


/**
 * Home object for domain model class Propiedad.
 * @see testerGeneral.persistence.impl.Propiedad
 * @author Hibernate Tools
 */
public class PropiedadHome extends DAOObject implements PropiedadDao {

    private static final Log log = LogFactory.getLog(PropiedadHome.class);
    
    public void insert(Propiedad transientInstance) throws Exception {
        log.debug("persisting Propiedad instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Propiedad transientInstance) throws Exception {
        log.debug("persisting Propiedad instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Propiedad persistentInstance) throws Exception {
        log.debug("deleting Propiedad instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
        	//log.error("delete failed", re);
           throw re;
        }
    }
    
    public Propiedad get(Serializable p_Id) throws Exception {
        log.debug("getting Propiedad instance with id: " + p_Id);
        try {
            Propiedad instance = (Propiedad) getHibernateTemplate()
                    .get(Propiedad.class, p_Id);
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
    
    
    public List getAll(Propiedad p_example) throws Exception {
        log.debug("finding Propiedad instance by example");
        try {
            Criteria cri = getSession().createCriteria(Propiedad.class);
            
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