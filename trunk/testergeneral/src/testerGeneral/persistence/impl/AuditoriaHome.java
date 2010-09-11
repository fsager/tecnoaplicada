package testerGeneral.persistence.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import testerGeneral.domain.Auditoria;
import testerGeneral.persistence.AuditoriaDao;
import testerGeneral.persistence.DAOObject;


/**
 * Home object for domain model class Auditoria.
 * @see testerGeneral.persistence.impl.Auditoria
 * @author Hibernate Tools
 */
public class AuditoriaHome extends DAOObject implements AuditoriaDao {

    private static final Log log = LogFactory.getLog(AuditoriaHome.class);
    
    public void insert(Auditoria transientInstance) throws Exception {
        log.debug("persisting Auditoria instance");
        try {
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(Auditoria transientInstance) throws Exception {
        log.debug("persisting Auditoria instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Auditoria persistentInstance) throws Exception {
        log.debug("deleting Auditoria instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
        	//log.error("delete failed", re);
            throw re;
        }
    }
    
    public Auditoria get(Long p_Id) throws Exception {
        log.debug("getting Auditoria instance with id: " + p_Id);
        try {
            Auditoria instance = (Auditoria) getHibernateTemplate()
                    .get(Auditoria.class, p_Id);
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
    
    
    public List getAll(Auditoria p_example) throws Exception {
        log.debug("finding Auditoria instance by example");
        try {
            Criteria cri = getSession().createCriteria(Auditoria.class);
            
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            cri.addOrder(Order.asc("audFecha"));
            List results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
        	//log.error("find by example failed");
            throw re;
        }
    }
    
    public List getAll(Auditoria p_example,Date desde,Date hasta) throws Exception {
        log.debug("finding Auditoria instance by example");
        try {
            Criteria cri = getSession().createCriteria(Auditoria.class);
            
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            if(desde!=null)
            {
            	frontend.utils.Util.redondearFecha(desde);
            	cri.add(Restrictions.ge("audFecha",desde));
            }
            if(hasta!=null)
            {
                Calendar cal=Calendar.getInstance();
                cal.setTime(hasta);
                cal.add(Calendar.DAY_OF_YEAR,1);
            	cri.add(Restrictions.le("audFecha",cal.getTime()));
            }
            
            cri.addOrder(Order.asc("audFecha"));
            
            List results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
        	//log.error("find by example failed", re);
            throw re;
        }
    }
    
    public int deleteAll(Auditoria p_example) throws Exception {
        log.debug("deleteAll Auditoria instance by example");
        try {
        	
        	String sql="delete from app.auditoria where AUD_FECHA <= ?";
        	SQLQuery sqlQuery= getSession().createSQLQuery(sql);
        	sqlQuery.setDate(0,p_example.getAudFecha());
            
        	int i=sqlQuery.executeUpdate();
           
            
            log.debug("deleteAll example successful, result size: " + i);
            return i;
        }
        catch (RuntimeException re) {
        	//log.error("find by example failed", re);
            throw re;
        }
    }

}