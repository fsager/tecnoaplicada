package testerGeneral.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import testerGeneral.domain.Resultado;
import testerGeneral.persistence.DAOObject;
import testerGeneral.persistence.ResultadoDao;


/**
 * Home object for domain model class Resultado.
 * @see testerGeneral.persistence.impl.Resultado
 * @author Hibernate Tools
 */
public class ResultadoHome extends DAOObject implements ResultadoDao {

    private static final Log log = LogFactory.getLog(ResultadoHome.class);
    
    public void insert(Resultado transientInstance) throws Exception {
        log.debug("persisting Resultado instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Resultado transientInstance) throws Exception {
        log.debug("persisting Resultado instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Resultado persistentInstance) throws Exception {
        log.debug("deleting Resultado instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
        	//log.error("delete failed", re);
           throw re;
        }
    }
    
    public Resultado get(Long p_Id) throws Exception {
        log.debug("getting Resultado instance with id: " + p_Id);
        try {
            Resultado instance = (Resultado) getHibernateTemplate()
                    .get(Resultado.class, p_Id);
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
    
    
    public List getAll(Resultado p_example) throws Exception {
        log.debug("finding Resultado instance by example");
        try {
            Criteria cri = getSession().createCriteria(Resultado.class);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            if(p_example.getResultadoDetalleExamen()!=null)
            	cri.createCriteria("resultadoDetalleExamen").add(Restrictions.idEq(p_example.getResultadoDetalleExamen().getRdeId()));
            
            cri.addOrder(Order.asc("resEtapa"));
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