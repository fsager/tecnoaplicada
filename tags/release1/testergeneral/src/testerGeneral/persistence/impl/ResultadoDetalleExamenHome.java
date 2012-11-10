package testerGeneral.persistence.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import testerGeneral.domain.ResultadoDetalleExamen;
import testerGeneral.persistence.DAOObject;
import testerGeneral.persistence.ResultadoDetalleExamenDao;


/**
 * Home object for domain model class ResultadoDetalleExamen.
 * @see testerGeneral.persistence.impl.ResultadoDetalleExamen
 * @author Hibernate Tools
 */
public class ResultadoDetalleExamenHome extends DAOObject implements ResultadoDetalleExamenDao {

    private static final Log log = LogFactory.getLog(ResultadoDetalleExamenHome.class);
    
    public void insert(ResultadoDetalleExamen transientInstance) throws Exception {
        log.debug("persisting ResultadoDetalleExamen instance");
        try {
        	if(transientInstance.getRdeImagen()==null)
        		transientInstance.setRdeImagen(new byte[1]);
        		
            saveObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            //log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(ResultadoDetalleExamen transientInstance) throws Exception {
        log.debug("persisting ResultadoDetalleExamen instance");
        try {
        	if(transientInstance.getRdeImagen()==null)
        		transientInstance.setRdeImagen(new byte[1]);
        	
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(ResultadoDetalleExamen persistentInstance) throws Exception {
        log.debug("deleting ResultadoDetalleExamen instance");
        try {
            deleteObject(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
        	//log.error("delete failed", re);
           throw re;
        }
    }
    
    public ResultadoDetalleExamen get(Long p_Id) throws Exception {
        log.debug("getting ResultadoDetalleExamen instance with id: " + p_Id);
        try {
            ResultadoDetalleExamen instance = (ResultadoDetalleExamen) getHibernateTemplate()
                    .get(ResultadoDetalleExamen.class, p_Id);
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
    
    
    public List getAll(ResultadoDetalleExamen p_example) throws Exception {
        log.debug("finding ResultadoDetalleExamen instance by example");
        try {
            Criteria cri = getSession().createCriteria(ResultadoDetalleExamen.class);
            p_example.setRdeImagen(null);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            if(p_example.getPersonaExamen()!=null)
            	cri.createCriteria("personaExamen").add(Restrictions.idEq(p_example.getPersonaExamen().getPexaId()));
            if(p_example.getExamenDetalle()!=null)
            	cri.createCriteria("examenDetalle").add(Restrictions.idEq(p_example.getExamenDetalle().getExadId()));

            cri.addOrder(Order.asc("rdeId"));
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