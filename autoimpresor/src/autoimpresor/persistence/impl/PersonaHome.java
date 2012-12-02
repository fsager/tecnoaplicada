package autoimpresor.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import testerGeneral.persistence.DAOObject;
import testerGeneral.persistence.impl.Util;
import autoimpresor.domain.Persona;
import autoimpresor.persistence.PersonaDao;


/**
 * Home object for domain model class Persona.
 * @see autoimpresor.persistence.impl.PersonaImp
 * @author Hibernate Tools
 */
public class PersonaHome extends DAOObject implements PersonaDao {

    private static final Log log = LogFactory.getLog(PersonaHome.class);
    
    public void insert(Persona transientInstance) throws Exception {
        log.debug("persisting Persona instance");
        try {
            saveObject(transientInstance);
            Util.insertAudit(Util.ACTION_INSERT,(Object)transientInstance,(Object)transientInstance.getPerId()+ "-" +transientInstance.getPerNombreCompleto());
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Persona transientInstance) throws Exception {
        log.debug("persisting Persona instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Persona persistentInstance) throws Exception {
        log.debug("deleting Persona instance");
        try {
            deleteObject(persistentInstance);
            Util.insertAudit(Util.ACTION_INSERT,(Object)persistentInstance,(Object)persistentInstance.getPerId()+ "-" +persistentInstance.getPerNombreCompleto());
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
           //log.error("delete failed", re);
           throw re;
        }
    }
    
    public Persona get(Long p_Id) throws Exception {
        log.debug("getting Persona instance with id: " + p_Id);
        try {
            Persona instance = (Persona) getHibernateTemplate()
                    .get(Persona.class, p_Id);
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
    
    public boolean migracionNecesaria() throws Exception 
    {
        log.debug("finding Persona instance by migracionNecesaria");
        try {
        	Persona p_example=new Persona();
            p_example.setPerFirma(null);
            p_example.setPerFoto(null);
            p_example.setPerSexo("H");
            
            Criteria cri = getSession().createCriteria(Persona.class);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            cri.setMaxResults(1);
            List results = cri.list();
            
            return !results.isEmpty();
        }
        catch (RuntimeException re) {
            //log.error("find by example failed", re);
            throw re;
        }
    }
    
    public List getAll(Persona p_example) throws Exception {
        log.debug("finding Persona instance by example");
        try {
            Criteria cri = getSession().createCriteria(Persona.class);
            p_example.setPerFirma(null);
            p_example.setPerFoto(null);     
            
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            cri.addOrder(Order.asc("perApellido"));
            cri.addOrder(Order.asc("perNombre"));
            cri.setMaxResults(200);
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