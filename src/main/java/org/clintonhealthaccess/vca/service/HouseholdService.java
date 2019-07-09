package org.clintonhealthaccess.vca.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.clintonhealthaccess.vca.domain.Household;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Servicio para el objeto Household
 * 
 * @author William Aviles
 * 
 **/

@Service("householdService")
@Transactional
public class HouseholdService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos los viviendas
	 * 
	 * @return una lista de <code>Household</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Household> getViviendas() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Household");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos los viviendas activos
	 * 
	 * @return una lista de <code>Household</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Household> getActiveViviendas() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Household house where house.pasive ='0'");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa una Household
	 * @param id Identificador del household 
	 * @return un <code>Household</code>
	 */

	public Household getVivienda(String ident) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Household house where " +
				"house.ident =:ident");
		query.setParameter("ident",ident);
		Household district = (Household) query.uniqueResult();
		return district;
	}
	
	
	/**
	 * Guarda un household
	 * @param household 
	 * 
	 */
	public void saveVivienda(Household household) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(household);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Household> getHousesFiltro(String codeHouse, String ownerName,
			Long desde, Long hasta, String local, String censusTaker, String recordUser) {
		//Set the SQL Query initially
		String sqlQuery = "from Household viv where 1 = 1";
		// if not null set time parameters
		if(!(desde==null)) {
			sqlQuery = sqlQuery + " and viv.censusDate between :fechaInicio and :fechaFinal";
		}
		if (!(codeHouse==null)) {
			sqlQuery = sqlQuery + " and viv.code like:codeHouse";
		}
		if (!(ownerName==null)) {
			sqlQuery = sqlQuery + " and viv.ownerName like:ownerName";
		}
		if(!local.equals("ALL")) {
			sqlQuery = sqlQuery + " and viv.local.ident=:local";
		}
		if(!censusTaker.equals("ALL")) {
			sqlQuery = sqlQuery + " and viv.censusTaker.ident=:censusTaker";
		}
		if(!recordUser.equals("ALL")) {
			sqlQuery = sqlQuery + " and viv.recordUser=:recordUser";
		}
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery(sqlQuery);
		if(!(desde==null)) {
			Timestamp timeStampInicio = new Timestamp(desde);
			Timestamp timeStampFinal = new Timestamp(hasta);
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
		}
		if (!(codeHouse==null)) {
			query.setParameter("codeHouse", "%" + codeHouse + "%");
		}
		if (!(ownerName==null)) {
			query.setParameter("ownerName", "%" + ownerName + "%");
		}
		if(!local.equals("ALL")) {
			query.setParameter("local", local);
		}
		if(!censusTaker.equals("ALL")) {
			query.setParameter("censusTaker", censusTaker);
		}
		if(!recordUser.equals("ALL")) {
			query.setParameter("recordUser", recordUser);
		}
		
		// Retrieve all
		return  query.list();
	}
	

}
