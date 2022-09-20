package org.clintonhealthaccess.vca.movil.controller;

import java.io.Serializable;
import java.util.List;

import org.clintonhealthaccess.vca.domain.Caso;
import org.clintonhealthaccess.vca.domain.Muestra;



public class DatosMapeo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Caso> casos;
	List<Muestra> muestras;

	

	
	public DatosMapeo() {
		super();
	}




	public List<Caso> getCasos() {
		return casos;
	}




	public void setCasos(List<Caso> casos) {
		this.casos = casos;
	}




	public List<Muestra> getMuestras() {
		return muestras;
	}




	public void setMuestras(List<Muestra> muestras) {
		this.muestras = muestras;
	}

	


}
