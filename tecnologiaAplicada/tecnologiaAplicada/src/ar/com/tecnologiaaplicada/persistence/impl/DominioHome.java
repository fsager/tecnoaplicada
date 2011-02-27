package ar.com.tecnologiaaplicada.persistence.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import ar.com.tecnologiaaplicada.domain.Dominio;
import ar.com.tecnologiaaplicada.persistence.DominioDao;
import ar.com.tecnologiaaplicada.persistence.util.DAOObject;


/**
 * Home object for domain model class Dominio.
 * @see testerGeneral.persistence.impl.Dominio
 * @author Hibernate Tools
 */
public class DominioHome extends DAOObject implements DominioDao {

    private static final Log log = LogFactory.getLog(DominioHome.class);
    
    public void insert(Dominio transientInstance) throws Exception {
        log.debug("persisting Dominio instance");
        try {
            saveObject(transientInstance);
            
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Dominio transientInstance) throws Exception {
        log.debug("persisting Dominio instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Dominio persistentInstance) throws Exception {
        log.debug("deleting Dominio instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
        	//log.error("delete failed", re);
           throw re;
        }
    }
    
    public Dominio get(Serializable p_Id) throws Exception {
        log.debug("getting Dominio instance with id: " + p_Id);
        try {
            Dominio instance = (Dominio) getHibernateTemplate()
                    .get(Dominio.class, p_Id);
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
    
    
    public List getAll(Dominio p_example) throws Exception {
        log.debug("finding Dominio instance by example");
        try {
            Criteria cri = getSession().createCriteria(Dominio.class);
            
            cri.add(Example.create(p_example));
            
            cri.addOrder(Order.asc("domValorMostrar"));
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