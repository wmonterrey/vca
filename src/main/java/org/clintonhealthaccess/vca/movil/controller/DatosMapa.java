package org.clintonhealthaccess.vca.movil.controller;

import java.io.Serializable;
import java.util.List;

import org.clintonhealthaccess.vca.domain.Caso;
import org.clintonhealthaccess.vca.domain.Criadero;
import org.clintonhealthaccess.vca.domain.PuntoDiagnostico;



public class DatosMapa implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Caso> casos;
	List<PuntoDiagnostico> puntoDiagnosticos;
	List<Criadero> criaderos;

	

	
	public DatosMapa() {
		super();
	}



	public List<Caso> getCasos() {
		return casos;
	}





	public void setCasos(List<Caso> casos) {
		this.casos = casos;
	}



	public List<PuntoDiagnostico> getPuntoDiagnosticos() {
		return puntoDiagnosticos;
	}



	public void setPuntoDiagnosticos(List<PuntoDiagnostico> puntoDiagnosticos) {
		this.puntoDiagnosticos = puntoDiagnosticos;
	}



	public List<Criadero> getCriaderos() {
		return criaderos;
	}



	public void setCriaderos(List<Criadero> criaderos) {
		this.criaderos = criaderos;
	}
	
	
	


}
