package org.clintonhealthaccess.vca.domain.irs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.clintonhealthaccess.vca.domain.BaseMetaData;
import org.clintonhealthaccess.vca.domain.Localidad;
import org.clintonhealthaccess.vca.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;



/**
 * 
 * Target es la clase que representa las viviendas objetivos en una temporada.
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "targetloc", catalog = "vca", uniqueConstraints={@UniqueConstraint(columnNames = {"season","localidad"})})
public class TargetLocalidad extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ident;
	private IrsSeason irsSeason;
	private Localidad localidad;
	
	
	public TargetLocalidad() {
		super();
	}
	
	public TargetLocalidad(String ident, IrsSeason irsSeason, Localidad localidad, String username) {
		super();
		this.ident = ident;
		this.irsSeason = irsSeason;
		this.localidad = localidad;
		this.setRecordDate(new Date());
		this.setRecordUser(username);
	}






	@Id
    @Column(name = "id", nullable = false, length = 50)
	public String getIdent() {
		return ident;
	}


	public void setIdent(String ident) {
		this.ident = ident;
	}

	@ManyToOne(optional=false)
	@JoinColumn(name="season")
    @ForeignKey(name = "FK_TARLOC_TEMP")
	public IrsSeason getIrsSeason() {
		return irsSeason;
	}



	public void setIrsSeason(IrsSeason irsSeason) {
		this.irsSeason = irsSeason;
	}


	@ManyToOne(optional=false)
	@JoinColumn(name="localidad")
    @ForeignKey(name = "FK_TARLOC_CASA")
	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}
	
	
	@Override
	public String toString(){
		return this.getIdent();
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TargetLocalidad))
			return false;
		
		TargetLocalidad castOther = (TargetLocalidad) other;

		return (this.getIdent().equals(castOther.getIdent()));
	}
	

}
