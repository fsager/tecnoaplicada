package autoimpresor.persistence.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import testerGeneral.domain.Propiedad;
import testerGeneral.persistence.DAOObject;
import testerGeneral.persistence.impl.Util;
import autoimpresor.business.ContextManager;
import autoimpresor.domain.Licencia;
import autoimpresor.persistence.LicenciaDao;


/**
 * Home object for domain model class Licencia.
 * @see autoimpresor.persistence.impl.LicenciaImp
 * @author Hibernate Tools
 */
public class LicenciaHome extends DAOObject implements LicenciaDao {

    private static final Log log = LogFactory.getLog(LicenciaHome.class);
    
    public void insert(Licencia transientInstance) throws Exception {
        log.debug("persisting Licencia instance");
        try {
            saveObject(transientInstance);
            Util.insertAudit(Util.ACTION_INSERT,(Object)transientInstance,(Object)transientInstance.getLicId()+ "-" +transientInstance.getPersona().getPerNombreCompleto());
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
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
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(Licencia persistentInstance) throws Exception {
        log.debug("deleting Licencia instance");
        try {
            deleteObject(persistentInstance);
            Util.insertAudit(Util.ACTION_INSERT,(Object)persistentInstance,(Object)persistentInstance.getLicId()+ "-" +persistentInstance.getPersona().getPerNombreCompleto());
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
           //log.error("delete failed", re);
           throw re;
        }
    }
    
    public Licencia get(Long p_Id) throws Exception {
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
            //log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List getAll(Licencia p_example) throws Exception {
        log.debug("finding Licencia instance by example");
        try {
            Criteria cri = getSession().createCriteria(Licencia.class);
            
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            if(p_example.getPersona()!=null && p_example.getPersona().getPerId()!=null)
            	cri.createCriteria("persona").add(Restrictions.idEq(p_example.getPersona().getPerId()));
            else if(p_example.getPersona()!=null)
            {
            	 p_example.getPersona().setPerFirma(null);
            	 p_example.getPersona().setPerFoto(null);     
                 
            	 cri.createCriteria("persona").add(Example.create(p_example.getPersona()).enableLike().ignoreCase());
            }
            
            cri.addOrder(Order.desc("licFechaOtorgada"));
            cri.addOrder(Order.asc("licId"));
            
            cri.setMaxResults(1000);
            
            List results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            //log.error("find by example failed", re);
            throw re;
        }
    }
    
    public List getAll(Licencia p_example,Date desde, Date hasta) throws Exception {
    	
        log.debug("finding Licencia instance by example");
        try {
            Criteria cri = getSession().createCriteria(Licencia.class);
            
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
            	cri.add(Restrictions.ge("licFechaOtorgada",desde));
            }
            if(hasta!=null)
            {
            	cri.add(Restrictions.le("licFechaOtorgada",hasta));
            }

            
            cri.addOrder(Order.desc("licFechaOtorgada"));
            cri.addOrder(Order.asc("licId"));
            
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
    
    public List<Object[]> getAllEstadisticas(Licencia p_example,Date desdeOtor, Date hastaOtor,Date desdeEdad, Date hastaEdad,String agrupador) throws Exception {
    	
        log.debug("finding Licencia instance by example");
        try {
            Criteria cri = getSession().createCriteria(Licencia.class);
            Criteria criPersona=null;
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            if(p_example.getPersona()!=null)
            {
            	 p_example.getPersona().setPerFirma(null);
            	 p_example.getPersona().setPerFoto(null);     
                 
            	 criPersona=cri.createCriteria("persona","persona");
            	 criPersona.add(Example.create(p_example.getPersona()).enableLike().ignoreCase());
            	 
                 if(desdeEdad!=null)
                 {
                	criPersona.add(Restrictions.le("perFechaNacimiento",desdeEdad));
                 }
                 if(hastaEdad!=null)
                 {
                	criPersona.add(Restrictions.ge("perFechaNacimiento",hastaEdad));
                 }
            }
            
            
            if(desdeOtor!=null)
            {
            	cri.add(Restrictions.ge("licFechaOtorgada",desdeOtor));
            }
            if(hastaOtor!=null)
            {
            	cri.add(Restrictions.le("licFechaOtorgada",hastaOtor));
            }

            ProjectionList projList = Projections.projectionList();
            /*projList.add(Projections.property(criPersona.getAlias()+"."+agrupador));
            projList.add(Projections.count(criPersona.getAlias()+"."+agrupador),"count");
            projList.add(Projections.groupProperty(criPersona.getAlias()+"."+agrupador));*/
            projList.add(Projections.groupProperty(agrupador));
            //projList.add(Projections.property(agrupador));
            if(agrupador.startsWith("persona"))
            	projList.add(Projections.countDistinct("persona.perId"),"count");            
            else
            	projList.add(Projections.count(agrupador),"count");
            	
            
            
            //criPersona.
            cri.setProjection(projList);
            
            cri.addOrder(Order.desc("count"));

         
            List results = cri.list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re) {
            //log.error("find by example failed", re);
            throw re;
        }
    }
    
    public Long getMaxNumeroLicencia() throws Exception {
        log.debug("finding Licencia instance by getMaxNumeroLicencia");
        try {

    		Propiedad propiedadRangoDesde = ContextManager.getPropertyObj("MUNICIPIO.RANGO.LICENCIAS.DESDE");    		
    		Propiedad propiedadRangoHasta = ContextManager.getPropertyObj("MUNICIPIO.RANGO.LICENCIAS.HASTA");
        	
        	Query query = getSession().createQuery("select max(licNumero) from Licencia where licNumero > ? and licNumero < ?");
        	
        	query.setString(0,propiedadRangoDesde.getPropValor());
        	query.setString(1,propiedadRangoHasta.getPropValor());
        	
        	if(query.uniqueResult()==null)
        	{
        		return new Long(propiedadRangoDesde.getPropValor());
        	}
        	
            return (Long)query.uniqueResult();
            
        }
        catch (RuntimeException re) {
            //log.error("find by example failed", re);
            throw re;
        }
    }
    
    public Long getMaxNumeroRecibo() throws Exception {
        log.debug("finding Licencia instance by getMaxNumeroRecibo");
        try {
       	
        	Query query = getSession().createQuery("select max(licRecibo) from Licencia");
        	
        	Long maxNroReciboMasUno=1l;
        	if(query.uniqueResult()!=null)
        	{
        		Long maxNroRecibo=(Long)query.uniqueResult();
        		maxNroReciboMasUno=maxNroRecibo+1;
        	}
        	
            return maxNroReciboMasUno;
            
        }
        catch (RuntimeException re) {
            //log.error("find by example failed", re);
            throw re;
        }
    }
}