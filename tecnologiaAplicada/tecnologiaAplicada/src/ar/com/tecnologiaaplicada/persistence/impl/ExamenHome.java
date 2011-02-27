package ar.com.tecnologiaaplicada.persistence.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import ar.com.tecnologiaaplicada.domain.Examen;
import ar.com.tecnologiaaplicada.persistence.ExamenDao;
import ar.com.tecnologiaaplicada.persistence.util.DAOObject;


/**
 * Home object for domain model class Examen.
 * @see testerGeneral.persistence.impl.Examen
 * @author Hibernate Tools
 */
public class ExamenHome extends DAOObject implements ExamenDao {

    private static final Log log = LogFactory.getLog(ExamenHome.class);
    
    public void insert(Examen transientInstance) throws Exception {
        log.debug("persisting Examen instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Examen transientInstance) throws Exception {
        log.debug("persisting Examen instance");
        try {
            updateObject(transientInstance);            
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Examen persistentInstance) throws Exception {
        log.debug("deleting Examen instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
        	//log.error("delete failed", re);
           throw re;
        }
    }
    
    public Examen get(Serializable p_Id) throws Exception {
        log.debug("getting Examen instance with id: " + p_Id);
        try {
            Examen instance = (Examen) getHibernateTemplate()
                    .get(Examen.class, p_Id);
            
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
     
    public List getAll(Examen p_example) throws Exception {
        log.debug("finding Examen instance by example");
        try {
            Criteria cri = getSession().createCriteria(Examen.class);
            
            cri.add(Example.create(p_example).enableLike().ignoreCase().excludeZeroes());
            cri.addOrder(Order.asc("exaOrden"));
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