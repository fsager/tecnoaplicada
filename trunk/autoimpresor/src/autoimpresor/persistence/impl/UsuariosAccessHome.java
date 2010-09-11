package autoimpresor.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import testerGeneral.persistence.DAOObject;
import autoimpresor.domain.access.Usuarios;
import autoimpresor.persistence.UsuariosAccessDao;


/**
 * Home object for domain model class UsuariosAccess.
 * @see autoimpresor.persistence.impl.UsuariosAccess
 * @author Hibernate Tools
 */
public class UsuariosAccessHome extends DAOObject implements UsuariosAccessDao {

    private static final Log log = LogFactory.getLog(UsuariosAccessHome.class);
    
    public void insert(Usuarios transientInstance) throws Exception {
        log.debug("persisting UsuariosAccess instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Usuarios transientInstance) throws Exception {
        log.debug("persisting UsuariosAccess instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Usuarios persistentInstance) throws Exception {
        log.debug("deleting UsuariosAccess instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
           //log.error("delete failed", re);
           throw re;
        }
    }
    
    public Usuarios get(Long p_Id) throws Exception {
        log.debug("getting UsuariosAccess instance with id: " + p_Id);
        try {
        	Usuarios instance = (Usuarios) getHibernateTemplate()
                    .get(Usuarios.class, p_Id);
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
    
    
    public List getAll(Usuarios p_example) throws Exception {
        log.debug("finding UsuariosAccess instance by example");
        try {
            Criteria cri = getSession().createCriteria(Usuarios.class);
            
            cri.add(Example.create(p_example).enableLike().ignoreCase());
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