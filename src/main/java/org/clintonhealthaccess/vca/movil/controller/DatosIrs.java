package org.clintonhealthaccess.vca.movil.controller;

import java.io.Serializable;
import java.util.List;

import org.clintonhealthaccess.vca.domain.irs.IrsSeason;
import org.clintonhealthaccess.vca.domain.irs.Target;




public class DatosIrs implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<IrsSeason> temporadas = null;
	List<Target> targets;

	public DatosIrs() {
		super();
	}

	public List<IrsSeason> getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(List<IrsSeason> temporadas) {
		this.temporadas = temporadas;
	}

	public List<Target> getTargets() {
		return targets;
	}

	public void setTargets(List<Target> targets) {
		this.targets = targets;
	}

}
