package org.clintonhealthaccess.vca.service;

import java.util.List;

import javax.annotation.Resource;

import org.clintonhealthaccess.vca.domain.Foco;
import org.clintonhealthaccess.vca.domain.Localidad;
import org.clintonhealthaccess.vca.domain.relationships.FocoLocalidad;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Servicio para el objeto Foco
 * 
 * @author William Aviles
 * 
 **/

@Service("focoService")
@Transactional
public class FocoService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todas las focos
	 * 
	 * @return una lista de <code>Foco</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Foco> getFocos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Foco");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todas las focos activas
	 * 
	 * @return una lista de <code>Foco</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Foco> getActiveFocos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Foco foco where foco.pasive ='0'");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa una Foco
	 * @param id Identificador del foco 
	 * @return un <code>Foco</code>
	 */

	public Foco getFoco(String ident) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Foco foco where " +
				"foco.ident =:ident");
		query.setParameter("ident",ident);
		Foco foco = (Foco) query.uniqueResult();
		return foco;
	}
	
	
	/**
	 * Guarda una foco
	 * @param foco El foco a guardar
	 * 
	 */
	public void saveFoco(Foco foco) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(foco);
	}
	
	
	/**
	 * Guarda
	 * @param focoLocalidad
	 * 
	 */
	public void saveFocoLocalidad(FocoLocalidad focoLocalidad) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(focoLocalidad);
	}
	
	@SuppressWarnings("unchecked")
	public List<Localidad> getLocalidadesFoco(String foco) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Localidad loc " +
				"where (loc.pasive ='0' and loc.ident in (Select floc.focoLocalidadId.localidad from FocoLocalidad floc "
				+ "where floc.focoLocalidadId.foco =:foco and floc.pasive ='0'))");
		query.setParameter("foco",foco);
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa una FocoLocalidad
	 * @param id Identificador del FocoLocalidad 
	 * @return un <code>FocoLocalidad</code>
	 */

	public FocoLocalidad getFocoLocalidad(String foco, String localidad) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM FocoLocalidad fl where " +
				"fl.focoLocalidadId.foco =:foco and fl.focoLocalidadId.localidad =:localidad");
		query.setParameter("foco",foco);
		query.setParameter("localidad",localidad);
		FocoLocalidad fl = (FocoLocalidad) query.uniqueResult();
		return fl;
	}
	

}
