package testerGeneral.persistence.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import testerGeneral.domain.Auditoria;
import testerGeneral.domain.Persona;
import testerGeneral.persistence.DAOObject;
import testerGeneral.persistence.PersonaDao;


/**
 * Home object for domain model class Persona.
 * @see testerGeneral.persistence.impl.Persona
 * @author Hibernate Tools
 */
public class PersonaHome extends DAOObject implements PersonaDao {

    private static final Log log = LogFactory.getLog(PersonaHome.class);
    
    public void insert(Persona transientInstance) throws Exception {
        log.debug("persisting Persona instance");
        try {
            saveObject(transientInstance);
            Util.insertAudit(Util.ACTION_INSERT,(Object)transientInstance,(Object)transientInstance.getPerNumeroDoc()+ "-" +transientInstance.getPerNombreCompleto());
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
            Util.insertAudit(Util.ACTION_DELETE,(Object)persistentInstance,(Object)persistentInstance.getPerNumeroDoc()+ "-" +persistentInstance.getPerNombreCompleto());
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
    
    
    public List getAll(Persona p_example) throws Exception {
        log.debug("finding Persona instance by example");
        try {
            Criteria cri = getSession().createCriteria(Persona.class);
            
            p_example.setPerFirma(null);
            p_example.setPerFoto(null);            
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            cri.addOrder(Order.asc("perApellido"));
            cri.addOrder(Order.asc("perNombre"));
            List results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
    
    public List getAll(Persona p_example,Date desde,Date hasta) throws Exception {
        log.debug("finding Persona instance by example");
        try {
            Criteria cri = getSession().createCriteria(Persona.class);
            
            p_example.setPerFirma(null);
            p_example.setPerFoto(null); 
            
            
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            if(p_example.getPerEstudios().equals("SI"))
            {
            	cri.add(Restrictions.isNotNull("perEstudios"));	
            }
            else if(p_example.getPerEstudios().equals("NO"))
            {
            	cri.add(Restrictions.isNull("perEstudios"));	
            }
            	
            
            if(desde!=null)
            {
            	frontend.utils.Util.redondearFecha(desde);
            	cri.add(Restrictions.ge("perFechaNacimiento",desde));
            }
            if(hasta!=null)
            {
                Calendar cal=Calendar.getInstance();
                cal.setTime(hasta);
                cal.add(Calendar.DAY_OF_YEAR,1);
            	cri.add(Restrictions.le("perFechaNacimiento",cal.getTime()));
            }
            
            cri.addOrder(Order.asc("perFechaNacimiento"));
            
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