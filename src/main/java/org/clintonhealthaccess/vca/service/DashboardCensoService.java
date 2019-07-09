package org.clintonhealthaccess.vca.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para el dashboard de censo
 * 
 * @author William Aviles
 * 
 **/

@Service("dashboardCensoService")
@Transactional
public class DashboardCensoService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa datos de vivienda por fecha
	 * 
	 * @return lista de objetos
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getDatosHouseholdxFecha() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("SELECT (viv.censusDate) as fecha, COUNT(viv.ident) AS Total, "
				+ "SUM( CASE viv.pasive WHEN '1' THEN 1 ELSE 0 END ) AS Invalidas, "
				+ "SUM( CASE viv.pasive WHEN '0' THEN 1 ELSE 0 END ) AS Validas FROM Household viv GROUP BY (viv.censusDate) order by (viv.censusDate)");
		// Retrieve all
		return  query.list();
	}
	
}
