package autoimpresor.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import testerGeneral.persistence.DAOObject;
import testerGeneral.persistence.impl.Util;
import autoimpresor.domain.Antecedente;
import autoimpresor.persistence.AntecedenteDao;


/**
 * Home object for domain model class Antecedente.
 * @see autoimpresor.persistence.impl.AntecedenteImp
 * @author Hibernate Tools
 */
public class AntecedenteHome extends DAOObject implements AntecedenteDao {

    private static final Log log = LogFactory.getLog(AntecedenteHome.class);
    
    public void insert(Antecedente transientInstance) throws Exception {
        log.debug("persisting Antecedente instance");
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
    
    public void update(Antecedente transientInstance) throws Exception {
        log.debug("persisting Antecedente instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Antecedente persistentInstance) throws Exception {
        log.debug("deleting Antecedente instance");
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
    
    public Antecedente get(Long p_Id) throws Exception {
        log.debug("getting Antecedente instance with id: " + p_Id);
        try {
            Antecedente instance = (Antecedente) getHibernateTemplate()
                    .get(Antecedente.class, p_Id);
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
    
    
    public List getAll(Antecedente p_example) throws Exception {
        log.debug("finding Antecedente instance by example");
        try {
            Criteria cri = getSession().createCriteria(Antecedente.class);
            
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            cri.addOrder(Order.asc("antId"));
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