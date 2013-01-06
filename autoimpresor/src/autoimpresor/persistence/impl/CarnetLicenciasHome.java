package autoimpresor.persistence.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;

import testerGeneral.persistence.DAOObject;
import autoimpresor.domain.CarnetLicencias;
import autoimpresor.domain.CarnetLicenciasQR;
import autoimpresor.persistence.CarnetLicenciasDao;

/**
 * Home object for domain model class CarnetLicencias.
 * 
 * @see autoimpresor.persistence.impl.CarnetLicencias
 * @author Hibernate Tools
 */
public class CarnetLicenciasHome extends DAOObject implements
		CarnetLicenciasDao {

	private static final Log log = LogFactory.getLog(CarnetLicenciasHome.class);

	public void insert(CarnetLicencias transientInstance) throws Exception {
		log.debug("persisting CarnetLicencias instance");
		try {
			if(transientInstance instanceof CarnetLicenciasQR)
			{
				if (!(((CarnetLicenciasQR)transientInstance).getQr() != null && ((CarnetLicenciasQR)transientInstance).getQr().length > 1))
					((CarnetLicenciasQR)transientInstance).setQr(new byte[1]);
			}
			
			saveObject(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			// log.error("persist failed", re);
			throw re;
		}
	}

	public void update(CarnetLicencias transientInstance) throws Exception {
		log.debug("persisting CarnetLicencias instance");
		try {
			
			if(transientInstance instanceof CarnetLicenciasQR)
			{
				if (!(((CarnetLicenciasQR)transientInstance).getQr() != null && ((CarnetLicenciasQR)transientInstance).getQr().length > 1))
					((CarnetLicenciasQR)transientInstance).setQr(new byte[1]);
			}
			
			updateObject(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			// log.error("persist failed", re);
			throw re;
		}
	}

	public void delete(CarnetLicencias persistentInstance) throws Exception {
		log.debug("deleting CarnetLicencias instance");
		try {
			deleteObject(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			// log.error("delete failed", re);
			throw re;
		}
	}

	public CarnetLicencias get(Long p_Id) throws Exception {
		log.debug("getting CarnetLicencias instance with id: " + p_Id);
		try {
			CarnetLicencias instance = (CarnetLicencias) getHibernateTemplate()
					.get(CarnetLicencias.class, p_Id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			// log.error("get failed", re);
			throw re;
		}
	}

	public List getAll(CarnetLicencias p_example) throws Exception {
		log.debug("finding CarnetLicencias instance by example");
		try {
			Criteria cri = getSession().createCriteria(p_example.getClass());

			p_example.setPerFirma(null);
			p_example.setPerFoto(null);
			p_example.setUsrFirma(null);
			p_example.setMncEscudo(null);
			if(p_example instanceof CarnetLicenciasQR)
			{
				((CarnetLicenciasQR)p_example).setQr(null);
			}

			cri.add(Example.create(p_example).enableLike().ignoreCase());
			cri.addOrder(Order.asc("CAMBIAR"));
			List results = cri.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			// log.error("find by example failed", re);
			throw re;
		}
	}

	public List getAll(CarnetLicencias p_example, Date desde, Date hasta)
			throws Exception {
		log.debug("finding CarnetLicencias instance by example");
		try {

			Criteria cri = getSession().createCriteria(p_example.getClass());

			CriteriaImpl cImpl = (CriteriaImpl) cri;

			p_example.setPerFirma(null);
			p_example.setPerFoto(null);
			p_example.setUsrFirma(null);
			p_example.setMncEscudo(null);
			if(p_example instanceof CarnetLicenciasQR)
			{
				((CarnetLicenciasQR)p_example).setQr(null);
			}

			Calendar cal = Calendar.getInstance();
			cal.setTime(hasta);
			cal.add(Calendar.DAY_OF_YEAR, 1);
			hasta = cal.getTime();

			if (p_example.getCliFechaImpresion() != null) {
				if (desde != null) {
					cri.add(Restrictions.ge("cliFechaImpresion", desde));
				}
				if (hasta != null) {
					cri.add(Restrictions.le("cliFechaImpresion", hasta));
				}
			} else {
				if (desde != null) {
					cri.add(Restrictions.ge("cliFechaImport", desde));
				}
				if (hasta != null) {
					cri.add(Restrictions.le("cliFechaImport", hasta));
				}
			}

			p_example.setCliFechaImpresion(null);

			/**
			 * Ver licencias pendientes, es decir con cantidad de impresiones diferentes de valor nulo.
			 */
			if (p_example.getCliCantImpresiones() != null
					&& p_example.getCliCantImpresiones().equals(new Long(0))) {

				cri.add(Restrictions.isNull("cliFechaImpresion"));
				p_example.setCliCantImpresiones(null);

			}

			cri.add(Example.create(p_example).enableLike().ignoreCase());

			cri.addOrder(Order.asc("cliId"));
			//cri.setMaxResults(1000);
			List results = cri.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			// log.error("find by example failed", re);
			throw re;
		}
	}

	public List getPendientes() throws Exception {
		log.debug("finding Licencia instance by getPendientes");
		try {
			Criteria cri = getSession().createCriteria(CarnetLicenciasQR.class);
			cri.add(Restrictions.isNull("cliFechaImpresion"));
			cri.add(Restrictions.isNull("cliCantImpresiones"));

			List results = cri.list();
			log.debug("find by getPendientes successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			// log.error("find by example failed", re);
			throw re;
		}
	}
}