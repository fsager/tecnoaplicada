package autoimpresor.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import testerGeneral.persistence.DAOObject;
import autoimpresor.domain.Caja;
import autoimpresor.persistence.CajaDao;


/**
 * Home object for domain model class Caja.
 * @see autoimpresor.persistence.impl.CajaImp
 * @author Hibernate Tools
 */
public class CajaHome extends DAOObject implements CajaDao {

    private static final Log log = LogFactory.getLog(CajaHome.class);
    
    public void insert(Caja transientInstance) throws Exception {
        log.debug("persisting Caja instance");
        try {
            saveObject(transientInstance);
            //Util.insertAudit(Util.ACTION_INSERT,(Object)transientInstance,(Object)transientInstance.getId()+ "-" +transientInstance.getExaNombre());
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Caja transientInstance) throws Exception {
        log.debug("persisting Caja instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Caja persistentInstance) throws Exception {
        log.debug("deleting Caja instance");
        try {
            deleteObject(persistentInstance);
            //Util.insertAudit(Util.ACTION_DELETE,(Object)persistentInstance,(Object)persistentInstance.getId()+ "-" +persistentInstance.getExaNombre());
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
           //log.error("delete failed", re);
           throw re;
        }
    }
    
    public Caja get(Long p_Id) throws Exception {
        log.debug("getting Caja instance with id: " + p_Id);
        try {
            Caja instance = (Caja) getHibernateTemplate()
                    .get(Caja.class, p_Id);
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
    
    
    public List getAll(Caja p_example) throws Exception {
        log.debug("finding Caja instance by example");
        try {
            Criteria cri = getSession().createCriteria(Caja.class);
            
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            cri.addOrder(Order.asc("cajId"));
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