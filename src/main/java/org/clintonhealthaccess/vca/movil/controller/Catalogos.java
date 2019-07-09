package org.clintonhealthaccess.vca.movil.controller;

import java.io.Serializable;
import java.util.List;

import org.clintonhealthaccess.vca.domain.Brigada;
import org.clintonhealthaccess.vca.domain.Censador;
import org.clintonhealthaccess.vca.domain.Rociador;
import org.clintonhealthaccess.vca.domain.Supervisor;
import org.clintonhealthaccess.vca.language.MessageResource;

public class Catalogos implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<MessageResource> catalogos;
	List<Censador> censadores;
	List<Rociador> rociadores;
	List<Supervisor> supervisores;
	List<Brigada> brigadas;
	
	public Catalogos() {
		super();
	}





	public Catalogos(List<MessageResource> catalogos, List<Censador> censadores, List<Rociador> rociadores,
			List<Supervisor> supervisores, List<Brigada> brigadas) {
		super();
		this.catalogos = catalogos;
		this.censadores = censadores;
		this.rociadores = rociadores;
		this.supervisores = supervisores;
		this.brigadas = brigadas;
	}





	public List<MessageResource> getCatalogos() {
		return catalogos;
	}


	public void setCatalogos(List<MessageResource> catalogos) {
		this.catalogos = catalogos;
	}


	public List<Censador> getCensadores() {
		return censadores;
	}


	public void setCensadores(List<Censador> censadores) {
		this.censadores = censadores;
	}


	public List<Rociador> getRociadores() {
		return rociadores;
	}


	public void setRociadores(List<Rociador> rociadores) {
		this.rociadores = rociadores;
	}





	public List<Supervisor> getSupervisores() {
		return supervisores;
	}





	public void setSupervisores(List<Supervisor> supervisores) {
		this.supervisores = supervisores;
	}





	public List<Brigada> getBrigadas() {
		return brigadas;
	}





	public void setBrigadas(List<Brigada> brigadas) {
		this.brigadas = brigadas;
	}
	
	
	
	

}
