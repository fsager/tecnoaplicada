package opcionesmultiples.persistence.impl;

import java.util.List;

import opcionesmultiples.domain.DetalleExamenMultipleChoice;
import opcionesmultiples.persistence.DetalleExamenMultipleChoiceDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Example;

import testerGeneral.persistence.DAOObject;


/**
 * Home object for domain model class DetalleExamenMultipleChoice.
 * @see opcionesmultiples.persistence.impl.DetalleExamenMultipleChoice
 * @author Hibernate Tools
 */
public class DetalleExamenMultipleChoiceHome extends DAOObject implements DetalleExamenMultipleChoiceDao {

    private static final Log log = LogFactory.getLog(DetalleExamenMultipleChoiceHome.class);
    
    public void insert(DetalleExamenMultipleChoice transientInstance) throws Exception {
        log.debug("persisting DetalleExamenMultipleChoice instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(DetalleExamenMultipleChoice transientInstance) throws Exception {
        log.debug("persisting DetalleExamenMultipleChoice instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(DetalleExamenMultipleChoice persistentInstance) throws Exception {
        log.debug("deleting DetalleExamenMultipleChoice instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DetalleExamenMultipleChoice get(Long p_Id) throws Exception {
        log.debug("getting DetalleExamenMultipleChoice instance with id: " + p_Id);
        try {
            DetalleExamenMultipleChoice instance = (DetalleExamenMultipleChoice) getHibernateTemplate()
                    .get(DetalleExamenMultipleChoice.class, p_Id);
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
    
    
    public List getAll(DetalleExamenMultipleChoice p_example) throws Exception {
        log.debug("finding DetalleExamenMultipleChoice instance by example");
        try {
            List results = getSession()
                    .createCriteria(DetalleExamenMultipleChoice.class)
                    .add(Example.create(p_example)
                    			.enableLike()
                    			.ignoreCase())
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}