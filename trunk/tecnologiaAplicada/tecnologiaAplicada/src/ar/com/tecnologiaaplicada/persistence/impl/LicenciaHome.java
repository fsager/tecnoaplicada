package ar.com.tecnologiaaplicada.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ar.com.tecnologiaaplicada.domain.Licencia;
import ar.com.tecnologiaaplicada.persistence.LicenciaDao;
import ar.com.tecnologiaaplicada.persistence.util.DAOObject;


/**
 * Home object for domain model class Licencia.
 * @see ar.com.tecnologiaaplicada.persistence.impl.Licencia
 * @author Hibernate Tools
 */
public class LicenciaHome extends DAOObject implements LicenciaDao {

    private static final Log log = LogFactory.getLog(LicenciaHome.class);
    
    public void insert(Licencia transientInstance) throws Exception {
        log.debug("persisting Licencia instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Licencia transientInstance) throws Exception {
        log.debug("persisting Licencia instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Licencia persistentInstance) throws Exception {
        log.debug("deleting Licencia instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Licencia get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting Licencia instance with id: " + p_Id);
        try {
            Licencia instance = (Licencia) getHibernateTemplate()
                    .get(Licencia.class, p_Id);
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
    
    
    public List getAll(Licencia p_example) throws Exception {
        log.debug("finding Licencia instance by example");
        try {
            Criteria cri = getSession().createCriteria(Licencia.class);
            
            cri.add(Example.create(p_example).enableLike().ignoreCase().excludeZeroes());
            cri.setFetchMode("cliente", FetchMode.JOIN);
            //cri.setFetchMode("cliente", FetchMode.JOIN);
            
            if(p_example.getCliente()!=null)
            {
            	cri.createCriteria("cliente").add(Restrictions.idEq(p_example.getCliente().getCliId()));
            }
            
            cri.addOrder(Order.desc("licId"));
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