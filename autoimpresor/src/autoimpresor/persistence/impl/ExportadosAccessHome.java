package autoimpresor.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import testerGeneral.persistence.DAOObject;
import autoimpresor.domain.access.Exportados;
import autoimpresor.persistence.ExportadosAccessDao;


/**
 * Home object for domain model class ExportadosAccess.
 * @see autoimpresor.persistence.impl.ExportadosAccess
 * @author Hibernate Tools
 */
public class ExportadosAccessHome extends DAOObject implements ExportadosAccessDao {

    private static final Log log = LogFactory.getLog(ExportadosAccessHome.class);
    
    public void insert(Exportados transientInstance) throws Exception {
        log.debug("persisting ExportadosAccess instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Exportados transientInstance) throws Exception {
        log.debug("persisting ExportadosAccess instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Exportados persistentInstance) throws Exception {
        log.debug("deleting ExportadosAccess instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
           //log.error("delete failed", re);
           throw re;
        }
    }
    
    public Exportados get(Long p_Id) throws Exception {
        log.debug("getting ExportadosAccess instance with id: " + p_Id);
        try {
        	Exportados instance = (Exportados) getHibernateTemplate()
                    .get(Exportados.class, p_Id);
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
    
    
    public List getAll(Exportados p_example) throws Exception {
        log.debug("finding ExportadosAccess instance by example");
        try {
            Criteria cri = getSession().createCriteria(Exportados.class);
            
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