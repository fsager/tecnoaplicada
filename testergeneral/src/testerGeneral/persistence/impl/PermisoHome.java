package testerGeneral.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import testerGeneral.domain.Permiso;
import testerGeneral.persistence.DAOObject;
import testerGeneral.persistence.PermisoDao;


/**
 * Home object for domain model class Permiso.
 * @see testerGeneral.persistence.impl.Permiso
 * @author Hibernate Tools
 */
public class PermisoHome extends DAOObject implements PermisoDao {

    private static final Log log = LogFactory.getLog(PermisoHome.class);
    
    public void insert(Permiso transientInstance) throws Exception {
        log.debug("persisting Permiso instance");
        try {

            saveObject(transientInstance);
            Util.insertAudit(Util.ACTION_INSERT,(Object)transientInstance,(Object)transientInstance.getPerCodigo()+ "-" +transientInstance.getPerDescripcion());
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Permiso transientInstance) throws Exception {
        log.debug("persisting Permiso instance");
        try {
            updateObject(transientInstance);            
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Permiso persistentInstance) throws Exception {
        log.debug("deleting Permiso instance");
        try {
            deleteObject(persistentInstance);
            Util.insertAudit(Util.ACTION_DELETE,(Object)persistentInstance,(Object)persistentInstance.getPerCodigo()+ "-" +persistentInstance.getPerDescripcion());
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
        	//log.error("delete failed", re);
           throw re;
        }
    }
    
    public Permiso get(Long p_Id) throws Exception {
        log.debug("getting Permiso instance with id: " + p_Id);
        try {
            Permiso instance = (Permiso) getHibernateTemplate()
                    .get(Permiso.class, p_Id);
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
    
    
    public List getAll(Permiso p_example) throws Exception {
        log.debug("finding Permiso instance by example");
        try {
            Criteria cri = getSession().createCriteria(Permiso.class);
            
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            cri.addOrder(Order.asc("perDescripcion"));
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