package ar.com.tecnologiaaplicada.persistence.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ar.com.tecnologiaaplicada.domain.Cliente;
import ar.com.tecnologiaaplicada.domain.Licencia;
import ar.com.tecnologiaaplicada.persistence.ClienteDao;
import ar.com.tecnologiaaplicada.persistence.util.DAOObject;


/**
 * Home object for domain model class Cliente.
 * @see ar.com.tecnologiaaplicada.persistence.impl.Cliente
 * @author Hibernate Tools
 */
public class ClienteHome extends DAOObject implements ClienteDao {

    private static final Log log = LogFactory.getLog(ClienteHome.class);
    
    public void insert(Cliente transientInstance) throws Exception {
        log.debug("persisting Cliente instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Cliente transientInstance) throws Exception {
        log.debug("persisting Cliente instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Cliente persistentInstance) throws Exception {
        log.debug("deleting Cliente instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Cliente get(java.io.Serializable p_Id) throws Exception {
        log.debug("getting Cliente instance with id: " + p_Id);
        try {
            Cliente instance = (Cliente) getHibernateTemplate()
                    .get(Cliente.class, p_Id);
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
    
    
    public List getAll(Cliente p_example) throws Exception {
        log.debug("finding Cliente instance by example");
        try {
            Criteria cri = getSession().createCriteria(Cliente.class);
            
            
            if(p_example.getCliRazonSocial()!=null)
            {
            	cri.add(Restrictions.ilike("cliRazonSocial", p_example.getCliRazonSocial().toLowerCase(),MatchMode.START));
            }

            if(p_example.getCliCuitCuil()!=null)
            {
            	cri.add(Restrictions.ilike("cliCuitCuil", p_example.getCliCuitCuil().toLowerCase(),MatchMode.START));
            }
            
            p_example.setCliRazonSocial(null);
            p_example.setCliCuitCuil(null);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            cri.addOrder(Order.desc("cliRazonSocial"));
            cri.addOrder(Order.desc("cliId"));
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