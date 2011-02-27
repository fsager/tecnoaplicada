package ar.com.tecnologiaaplicada.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import ar.com.tecnologiaaplicada.domain.Activacion;
import ar.com.tecnologiaaplicada.persistence.ActivacionDao;
import ar.com.tecnologiaaplicada.persistence.util.DAOObject;


/**
 * Home object for domain model class Activacion.
 * @see ar.com.tecnologiaaplicada.persistence.impl.Activacion
 * @author Hibernate Tools
 */
public class ActivacionHome extends DAOObject implements ActivacionDao {

    private static final Log log = LogFactory.getLog(ActivacionHome.class);
    
    public void insert(Activacion transientInstance) throws Exception {
        log.debug("persisting Activacion instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Activacion transientInstance) throws Exception {
        log.debug("persisting Activacion instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Activacion persistentInstance) throws Exception {
        log.debug("deleting Activacion instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Activacion get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting Activacion instance with id: " + p_Id);
        try {
            Activacion instance = (Activacion) getHibernateTemplate()
                    .get(Activacion.class, p_Id);
            if (instance==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List getAll(Activacion p_example) throws Exception {
        log.debug("finding Activacion instance by example");
        try {
            Criteria cri = getSession().createCriteria(Activacion.class);
            
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            cri.addOrder(Order.desc("actId"));
            List results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}