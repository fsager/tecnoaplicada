package testerGeneral.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import testerGeneral.domain.Examen;
import testerGeneral.persistence.DAOObject;
import testerGeneral.persistence.ExamenDao;


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
            Util.insertAudit(Util.ACTION_INSERT,(Object)transientInstance,(Object)transientInstance.getExaCodigo()+ "-" +transientInstance.getExaNombre());
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
            Util.insertAudit(Util.ACTION_DELETE,(Object)persistentInstance,(Object)persistentInstance.getExaCodigo()+ "-" +persistentInstance.getExaNombre());
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
        	//log.error("delete failed", re);
           throw re;
        }
    }
    
    public Examen get(Long p_Id) throws Exception {
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
            
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            cri.addOrder(Order.asc("exaNombre"));
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