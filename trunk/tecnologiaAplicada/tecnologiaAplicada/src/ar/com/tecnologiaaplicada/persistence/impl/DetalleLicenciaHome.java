package ar.com.tecnologiaaplicada.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ar.com.tecnologiaaplicada.domain.DetalleLicencia;
import ar.com.tecnologiaaplicada.persistence.DetalleLicenciaDao;
import ar.com.tecnologiaaplicada.persistence.util.DAOObject;


/**
 * Home object for domain model class DetalleLicencia.
 * @see ar.com.tecnologiaaplicada.persistence.impl.DetalleLicencia
 * @author Hibernate Tools
 */
public class DetalleLicenciaHome extends DAOObject implements DetalleLicenciaDao {

    private static final Log log = LogFactory.getLog(DetalleLicenciaHome.class);
    
    public void insert(DetalleLicencia transientInstance) throws Exception {
        log.debug("persisting DetalleLicencia instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(DetalleLicencia transientInstance) throws Exception {
        log.debug("persisting DetalleLicencia instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(DetalleLicencia persistentInstance) throws Exception {
        log.debug("deleting DetalleLicencia instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DetalleLicencia get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting DetalleLicencia instance with id: " + p_Id);
        try {
            DetalleLicencia instance = (DetalleLicencia) getHibernateTemplate()
                    .get(DetalleLicencia.class, p_Id);
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
    
    
    public List getAll(DetalleLicencia p_example) throws Exception {
        log.debug("finding DetalleLicencia instance by example");
        try {
            Criteria cri = getSession().createCriteria(DetalleLicencia.class);
            cri.add(Example.create(p_example).enableLike().ignoreCase().excludeZeroes());
            cri.setFetchMode("licencia",FetchMode.JOIN);
            
            if(p_example.getLicencia()!=null && p_example.getLicencia().getLicId()!=null)
            {
            	cri.createCriteria("licencia").add(Restrictions.idEq(p_example.getLicencia().getLicId()));
            }
            
            cri.addOrder(Order.desc("dlicId"));
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