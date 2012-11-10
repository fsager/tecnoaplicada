package autoimpresor.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import testerGeneral.persistence.DAOObject;
import testerGeneral.persistence.impl.Util;
import autoimpresor.domain.ClaseLicencia;
import autoimpresor.persistence.ClaseLicenciaDao;


/**
 * Home object for domain model class ClaseLicencia.
 * @see autoimpresor.persistence.impl.ClaseLicenciaImp
 * @author Hibernate Tools
 */
public class ClaseLicenciaHome extends DAOObject implements ClaseLicenciaDao {

    private static final Log log = LogFactory.getLog(ClaseLicenciaHome.class);
    
    public void insert(ClaseLicencia transientInstance) throws Exception {
        log.debug("persisting ClaseLicencia instance");
        try {
            saveObject(transientInstance);
            Util.insertAudit(Util.ACTION_INSERT,(Object)transientInstance,(Object)transientInstance.getCllId()+ "-" +transientInstance.getCllNombreClase());
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(ClaseLicencia transientInstance) throws Exception {
        log.debug("persisting ClaseLicencia instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(ClaseLicencia persistentInstance) throws Exception {
        log.debug("deleting ClaseLicencia instance");
        try {
            deleteObject(persistentInstance);
            Util.insertAudit(Util.ACTION_INSERT,(Object)persistentInstance,(Object)persistentInstance.getCllId()+ "-" +persistentInstance.getCllNombreClase());
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
           //log.error("delete failed", re);
           throw re;
        }
    }
    
    public ClaseLicencia get(Long p_Id) throws Exception {
        log.debug("getting ClaseLicencia instance with id: " + p_Id);
        try {
            ClaseLicencia instance = (ClaseLicencia) getHibernateTemplate()
                    .get(ClaseLicencia.class, p_Id);
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
    
    
    public List getAll(ClaseLicencia p_example) throws Exception {
        log.debug("finding ClaseLicencia instance by example");
        try {
            Criteria cri = getSession().createCriteria(ClaseLicencia.class);
            
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            cri.addOrder(Order.asc("cllNombreClase"));
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