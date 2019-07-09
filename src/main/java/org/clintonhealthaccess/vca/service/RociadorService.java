package org.clintonhealthaccess.vca.service;

import java.util.List;

import javax.annotation.Resource;

import org.clintonhealthaccess.vca.domain.Rociador;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Servicio para el objeto Rociador
 * 
 * @author William Aviles
 * 
 **/

@Service("rociadorService")
@Transactional
public class RociadorService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todas las rociadores
	 * 
	 * @return una lista de <code>Rociador</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Rociador> getRociadores() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Rociador");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todas las rociadores activas
	 * 
	 * @return una lista de <code>Rociador</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Rociador> getActiveRociadores() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Rociador rociador where rociador.pasive ='0'");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa una Rociador
	 * @param id Identificador del rociador 
	 * @return un <code>Rociador</code>
	 */

	public Rociador getRociador(String ident) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Rociador rociador where " +
				"rociador.ident =:ident");
		query.setParameter("ident",ident);
		Rociador rociador = (Rociador) query.uniqueResult();
		return rociador;
	}
	
	
	/**
	 * Guarda una rociador
	 * @param rociador El rociador a guardar
	 * 
	 */
	public void saveRociador(Rociador rociador) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(rociador);
	}
	

}
