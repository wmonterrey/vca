package org.clintonhealthaccess.vca.movil.controller;

import java.io.Serializable;
import java.util.List;

import org.clintonhealthaccess.vca.domain.irs.IrsSeason;
import org.clintonhealthaccess.vca.domain.irs.Supervision;
import org.clintonhealthaccess.vca.domain.irs.Target;
import org.clintonhealthaccess.vca.domain.irs.TargetLocalidad;
import org.clintonhealthaccess.vca.domain.irs.Visit;




public class DatosIrs implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<IrsSeason> temporadas = null;
	List<TargetLocalidad> targetlocals;
	List<Target> targets;
	List<Visit> visits;
	List<Supervision> supervisions;
	

	public DatosIrs() {
		super();
	}

	public List<IrsSeason> getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(List<IrsSeason> temporadas) {
		this.temporadas = temporadas;
	}
	
	public List<TargetLocalidad> getTargetlocals() {
		return targetlocals;
	}

	public void setTargetlocals(List<TargetLocalidad> targetlocals) {
		this.targetlocals = targetlocals;
	}

	public List<Target> getTargets() {
		return targets;
	}

	public void setTargets(List<Target> targets) {
		this.targets = targets;
	}

	public List<Visit> getVisits() {
		return visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

	public List<Supervision> getSupervisions() {
		return supervisions;
	}

	public void setSupervisions(List<Supervision> supervisions) {
		this.supervisions = supervisions;
	}
	
	

}
