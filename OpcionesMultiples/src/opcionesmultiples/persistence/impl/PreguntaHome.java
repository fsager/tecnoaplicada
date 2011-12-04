package opcionesmultiples.persistence.impl;

import java.util.List;

import opcionesmultiples.domain.Pregunta;
import opcionesmultiples.persistence.PreguntaDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import testerGeneral.persistence.DAOObject;


/**
 * Home object for domain model class Pregunta.
 * @see opcionesmultiples.persistence.impl.PreguntaInterfaz
 * @author Hibernate Tools
 */
public class PreguntaHome extends DAOObject implements PreguntaDao {

    private static final Log log = LogFactory.getLog(PreguntaHome.class);
    
    public void insert(Pregunta transientInstance) throws Exception {
        log.debug("persisting Pregunta instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Pregunta transientInstance) throws Exception {
        log.debug("persisting Pregunta instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Pregunta persistentInstance) throws Exception {
        log.debug("deleting Pregunta instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Pregunta get(Long p_Id) throws Exception {
        log.debug("getting Pregunta instance with id: " + p_Id);
        try {
            Pregunta instance = (Pregunta) getHibernateTemplate()
                    .get(Pregunta.class, p_Id);
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
    
    
    public List getAll(Pregunta p_example) throws Exception {
        log.debug("finding Pregunta instance by example");
        try {
        	p_example.setPreImagen(null);
            Criteria cri= getSession()
                    .createCriteria(Pregunta.class)
                    .add(Example.create(p_example)
                    			.enableLike()
                    			.ignoreCase());
            
            
            
            
            List results=cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
}