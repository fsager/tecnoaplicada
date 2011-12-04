package opcionesmultiples.persistence.impl;

import java.util.List;

import opcionesmultiples.domain.Respuesta;
import opcionesmultiples.persistence.RespuestaDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import testerGeneral.persistence.DAOObject;


/**
 * Home object for domain model class Respuesta.
 * @see opcionesmultiples.persistence.impl.Respuesta
 * @author Hibernate Tools
 */
public class RespuestaHome extends DAOObject implements RespuestaDao {

    private static final Log log = LogFactory.getLog(RespuestaHome.class);
    
    public void insert(Respuesta transientInstance) throws Exception {
        log.debug("persisting Respuesta instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Respuesta transientInstance) throws Exception {
        log.debug("persisting Respuesta instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Respuesta persistentInstance) throws Exception {
        log.debug("deleting Respuesta instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Respuesta get(Long p_Id) throws Exception {
        log.debug("getting Respuesta instance with id: " + p_Id);
        try {
            Respuesta instance = (Respuesta) getHibernateTemplate()
                    .get(Respuesta.class, p_Id);
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
    
    
    public List getAll(Respuesta p_example) throws Exception {
        log.debug("finding Respuesta instance by example");
        try {
        	
        	Criteria cri=getSession().createCriteria(Respuesta.class).add(Example.create(p_example)
        			.enableLike()
        			.ignoreCase());
        	
        	if(p_example.getPregunta()!=null)
        		cri.createCriteria("pregunta").add(Restrictions.idEq(p_example.getPregunta().getId()));
        	
        	//Primero la opcion correcta. S
        	cri.addOrder(Order.desc("resCorrecta"));
        	
            List results =cri.list();
            
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}