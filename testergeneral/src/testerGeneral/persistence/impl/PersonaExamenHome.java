package testerGeneral.persistence.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import testerGeneral.domain.PersonaExamen;
import testerGeneral.persistence.DAOObject;
import testerGeneral.persistence.PersonaExamenDao;


/**
 * Home object for domain model class PersonaExamen.
 * @see testerGeneral.persistence.impl.PersonaExamen
 * @author Hibernate Tools
 */
public class PersonaExamenHome extends DAOObject implements PersonaExamenDao {

    private static final Log log = LogFactory.getLog(PersonaExamenHome.class);
    
    public void insert(PersonaExamen transientInstance) throws Exception {
        log.debug("persisting PersonaExamen instance");
        try {
            saveObject(transientInstance);
            Util.insertAudit(Util.ACTION_INSERT,"Examen",(Object)transientInstance.getExamen().getExaNombre()+ "-" +transientInstance.getPersona().getPerNumeroDoc()+" - " +transientInstance.getPersona().getPerNombreCompleto());
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(PersonaExamen transientInstance) throws Exception {
        log.debug("persisting PersonaExamen instance");
        try {
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(PersonaExamen persistentInstance) throws Exception {
        log.debug("deleting PersonaExamen instance");
        try {
            deleteObject(persistentInstance);
            Util.insertAudit(Util.ACTION_DELETE,"Examen",(Object)persistentInstance.getExamen().getExaNombre()+ "-" +persistentInstance.getPersona().getPerNumeroDoc()+" - " +persistentInstance.getPersona().getPerNombreCompleto());
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
        	//log.error("delete failed", re);
           throw re;
        }
    }
    
    public PersonaExamen get(Long p_Id) throws Exception {
        log.debug("getting PersonaExamen instance with id: " + p_Id);
        try {
            PersonaExamen instance = (PersonaExamen) getHibernateTemplate()
                    .get(PersonaExamen.class, p_Id);
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
     
    public List getAll(PersonaExamen p_example) throws Exception {
        log.debug("finding PersonaExamen instance by example");
        try {
            Criteria cri = getSession().createCriteria(PersonaExamen.class);
            
            p_example.setPexaAdj(null);   
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            if(p_example.getPersona()!=null  && p_example.getPersona().getPerId()!=null)
            	cri.createCriteria("persona").add(Restrictions.idEq(p_example.getPersona().getPerId()));
            else if(p_example.getPersona()!=null)
            {
             	 p_example.getPersona().setPerFirma(null);
            	 p_example.getPersona().setPerFoto(null);     
                 
            	 cri.createCriteria("persona").add(Example.create(p_example.getPersona()).enableLike().ignoreCase());
            }
            if(p_example.getExamen()!=null  && p_example.getExamen().getExaId()!=null)
            	cri.createCriteria("examen").add(Restrictions.idEq(p_example.getExamen().getExaId()));

            cri.addOrder(Order.desc("pexaFecha"));
            cri.addOrder(Order.asc("examen"));
            List results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;            
          
        }
        catch (RuntimeException re) {
        	//log.error("find by example failed", re);
            throw re;
        }
    }
    
    public List getAll(PersonaExamen p_example,Date desde, Date hasta) throws Exception {
    	
        log.debug("finding PersonaExamen instance by example");
        try {
            Criteria cri = getSession().createCriteria(PersonaExamen.class);
            p_example.setPexaAdj(null);   
            
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            if(p_example.getPersona()!=null && p_example.getPersona().getPerId()!=null)
            	cri.createCriteria("persona").add(Restrictions.idEq(p_example.getPersona().getPerId()));
            else if(p_example.getPersona()!=null)
            {
            	 p_example.getPersona().setPerFirma(null);
            	 p_example.getPersona().setPerFoto(null);     
                 
            	 cri.createCriteria("persona").add(Example.create(p_example.getPersona()).enableLike().ignoreCase());
            }
            
            
            if(desde!=null)
            {
            	cri.add(Restrictions.ge("pexaFecha",desde));
            }
            if(hasta!=null)
            {
            	cri.add(Restrictions.le("pexaFecha",hasta));
            }

            
            cri.addOrder(Order.desc("pexaFecha"));
            cri.addOrder(Order.asc("examen"));
            
            //cri.setMaxResults(1000);
            
            List results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            //log.error("find by example failed", re);
            throw re;
        }
    }
    
    public Long getCantidadExamenes(Date lastDate) throws Exception {
        log.debug("finding PersonaExamen instance by getCantidadExamenes");
        try {
        	
        	String sql="select count(*) from app.persona_examen";
        	
        	if(lastDate!=null)
        	{
        		sql+=" where pexa_fecha > ?";
        	}
        	
            SQLQuery sqlQuery=getSession().createSQLQuery(sql);
        	
            if(lastDate!=null)
        		sqlQuery.setDate(0,lastDate);

            Integer cantidadExamenes=(Integer)sqlQuery.uniqueResult();
            
            return new Long(cantidadExamenes);
        }
        catch (RuntimeException re) {
        	//log.error("find by getCantidadExamenes failed", re);
            throw re;
        }
    }
}