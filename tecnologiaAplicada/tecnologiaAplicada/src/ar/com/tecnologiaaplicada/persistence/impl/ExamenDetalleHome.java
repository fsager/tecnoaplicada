package ar.com.tecnologiaaplicada.persistence.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import ar.com.tecnologiaaplicada.domain.ExamenDetalle;
import ar.com.tecnologiaaplicada.persistence.ExamenDetalleDao;
import ar.com.tecnologiaaplicada.persistence.util.DAOObject;


/**
 * Home object for domain model class ExamenDetalle.
 * @see testerGeneral.persistence.impl.ExamenDetalle
 * @author Hibernate Tools
 */
public class ExamenDetalleHome extends DAOObject implements ExamenDetalleDao {

    private static final Log log = LogFactory.getLog(ExamenDetalleHome.class);
    
    public void insert(ExamenDetalle transientInstance) throws Exception {
        log.debug("persisting ExamenDetalle instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(ExamenDetalle transientInstance) throws Exception {
        log.debug("persisting ExamenDetalle instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(ExamenDetalle persistentInstance) throws Exception {
        log.debug("deleting ExamenDetalle instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
        	// log.error("delete failed", re);
           throw re;
        }
    }
    
    public ExamenDetalle get(Serializable p_Id) throws Exception {
        log.debug("getting ExamenDetalle instance with id: " + p_Id);
        try {
            ExamenDetalle instance = (ExamenDetalle) getHibernateTemplate().get(ExamenDetalle.class, p_Id);
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
    
    
    public List getAll(ExamenDetalle p_example) throws Exception {
        log.debug("finding ExamenDetalle instance by example");
        try {
            Criteria cri = getSession().createCriteria(ExamenDetalle.class);
            
            cri.add(Example.create(p_example).enableLike().ignoreCase().excludeZeroes());
            if(p_example.getExamen()!=null)
            	cri.createCriteria("examen").add(Restrictions.idEq(p_example.getExamen().getExaId()));
            
            //cri.addOrder(Order.asc("exadOrden"));
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