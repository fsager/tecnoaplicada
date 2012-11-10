
package testerGeneral.persistence.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import testerGeneral.domain.UsuarioCommon;
import testerGeneral.persistence.DAOObject;
import testerGeneral.persistence.UsuarioDao;


/**
 * Home object for domain model class Usuario.
 * @see testerGeneral.persistence.impl.Usuario
 * @author Hibernate Tools
 */
public class UsuarioHome extends DAOObject implements UsuarioDao {

    private static final Log log = LogFactory.getLog(UsuarioHome.class);
    
    public void insert(UsuarioCommon transientInstance) throws Exception {
        log.debug("persisting Usuario instance");
        try {
            saveObject(transientInstance);            
            Util.insertAudit(Util.ACTION_INSERT,(Object)transientInstance,(Object)transientInstance.getUsrNombre());
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void update(UsuarioCommon transientInstance) throws Exception {
        log.debug("persisting Usuario instance");
        try {
        	if(transientInstance.getUsrFirma()!=null && transientInstance.getUsrFirma().length>1)
        	{
        		
        	}
        	else
        	{
        		transientInstance.setUsrFirma(new byte[1]);
        	}
            updateObject(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
        	//log.error("persist failed", re);
            throw re;
        }
    }
    
    public void delete(UsuarioCommon persistentInstance) throws Exception {
        log.debug("deleting Usuario instance");
        try {
            deleteObject(persistentInstance);
            Util.insertAudit(Util.ACTION_DELETE,(Object)persistentInstance,(Object)persistentInstance.getUsrNombre());
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
        	//log.error("delete failed", re);
            throw re;
        }
    }
    
    public UsuarioCommon get(Serializable p_Id,Class classs) throws Exception {
        log.debug("getting Persona instance with id: " + p_Id);
        try {
        	UsuarioCommon instance = (UsuarioCommon) getHibernateTemplate()
                    .get(classs, p_Id);
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

	
    public UsuarioCommon getUsrName(String p_Id,Class classs) throws Exception {
        log.debug("getting Usuario instance with id: " + p_Id);
        try {
        	UsuarioCommon usr=(UsuarioCommon)classs.newInstance();
        	usr.setUsrNombre(p_Id);
        	usr.setUsrFirma(null);
            Criteria cri = getSession().createCriteria(usr.getClass());
            
            if(usr instanceof testerGeneral.domain.Usuario)
            {
            	cri.add(Example.create(usr).enableLike().ignoreCase());
            	cri.add(Restrictions.ilike("usrNombre", p_Id, MatchMode.ANYWHERE));
            }
            else
            {
            	cri.add(Example.create(usr).enableLike().ignoreCase());
            }
            
            usr = (UsuarioCommon)cri.uniqueResult();
            
            return usr;
        }
        catch (RuntimeException re) {
        	//log.error("get failed", re);
            throw re;
        }
    }
    
    public List getAll(UsuarioCommon p_example) throws Exception {
        log.debug("finding Usuario instance by example");
        try {
            Criteria cri = getSession().createCriteria(p_example.getClass());
            p_example.setUsrFirma(null);
            if(p_example.getUsrNombre()!=null)
            {
            	cri.add(Restrictions.ilike("usrNombre", p_example.getUsrNombre().toLowerCase(),MatchMode.START));
            	//cri.add(Restrictions.sqlRestriction("usr_nombre like '"+p_example.getUsrNombre().toLowerCase()+"%'"));
            }
            
            p_example.setUsrNombre(null);
            cri.add(Example.create(p_example).enableLike().ignoreCase());
            
            
            cri.addOrder(Order.asc("usrNombre"));
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