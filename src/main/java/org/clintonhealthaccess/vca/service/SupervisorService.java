package org.clintonhealthaccess.vca.service;

import java.util.List;

import javax.annotation.Resource;

import org.clintonhealthaccess.vca.domain.irs.Supervisor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Servicio para el objeto Supervisor
 * 
 * @author William Aviles
 * 
 **/

@Service("supervisorService")
@Transactional
public class SupervisorService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todas las supervisores
	 * 
	 * @return una lista de <code>Supervisor</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Supervisor> getSupervisores() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Supervisor");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todas las supervisores activas
	 * 
	 * @return una lista de <code>Supervisor</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Supervisor> getActiveSupervisores() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Supervisor supervisor where supervisor.pasive ='0'");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa una Supervisor
	 * @param id Identificador del supervisor 
	 * @return un <code>Supervisor</code>
	 */

	public Supervisor getSupervisor(String ident) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Supervisor supervisor where " +
				"supervisor.ident =:ident");
		query.setParameter("ident",ident);
		Supervisor supervisor = (Supervisor) query.uniqueResult();
		return supervisor;
	}
	
	
	/**
	 * Guarda una supervisor
	 * @param supervisor El supervisor a guardar
	 * 
	 */
	public void saveSupervisor(Supervisor supervisor) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(supervisor);
	}
	

}
