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
import org.clintonhealthaccess.vca.domain.Household;
import org.clintonhealthaccess.vca.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;
import org.springframework.format.annotation.DateTimeFormat;



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
@Table(name = "targets", catalog = "vca", uniqueConstraints={@UniqueConstraint(columnNames = {"season","house"})})
public class Target extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ident;
	private IrsSeason irsSeason;
	private Household household;
	private Integer sprRooms;
	private Integer habitants;
	private Integer masculinos;
	private Integer femeninos;
	private Integer menores5;
	private Integer menores5masc;
	private Integer menores5fem;
	private Integer embarazadas;
	private String sprayStatus;
	private Date lastModified;
	private Personal assignedTo;
	
	
	public Target() {
		super();
	}
	
	public Target(String ident, IrsSeason irsSeason, Household household, String sprayStatus,
			Date lastModified, String username) {
		super();
		this.ident = ident;
		this.irsSeason = irsSeason;
		this.household = household;
		this.sprayStatus = sprayStatus;
		this.lastModified = lastModified;
		this.setRecordDate(lastModified);
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
    @ForeignKey(name = "FK_TAR_TEMP")
	public IrsSeason getIrsSeason() {
		return irsSeason;
	}



	public void setIrsSeason(IrsSeason irsSeason) {
		this.irsSeason = irsSeason;
	}


	@ManyToOne(optional=false)
	@JoinColumn(name="house")
    @ForeignKey(name = "FK_TAR_CASA")
	public Household getHousehold() {
		return household;
	}



	public void setHousehold(Household household) {
		this.household = household;
	}
	
	
	@Column(name = "sprayStatus", nullable = false, length = 50)
	public String getSprayStatus() {
		return sprayStatus;
	}



	public void setSprayStatus(String sprayStatus) {
		this.sprayStatus = sprayStatus;
	}


	@Column(name = "lastModified", nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	public Date getLastModified() {
		return lastModified;
	}



	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	

	@Column(name = "sprRooms", nullable = true)
	public Integer getSprRooms() {
		return sprRooms;
	}

	public void setSprRooms(Integer sprRooms) {
		this.sprRooms = sprRooms;
	}

	@Column(name = "habitants", nullable = true)
	public Integer getHabitants() {
		return habitants;
	}

	public void setHabitants(Integer habitants) {
		this.habitants = habitants;
	}
	
	
	@Column(name = "masculinos", nullable = true)
	public Integer getMasculinos() {
		return masculinos;
	}

	public void setMasculinos(Integer masculinos) {
		this.masculinos = masculinos;
	}
	
	@Column(name = "femeninos", nullable = true)
	public Integer getFemeninos() {
		return femeninos;
	}

	public void setFemeninos(Integer femeninos) {
		this.femeninos = femeninos;
	}

	@Column(name = "menores5", nullable = true)
	public Integer getMenores5() {
		return menores5;
	}

	public void setMenores5(Integer menores5) {
		this.menores5 = menores5;
	}

	@Column(name = "menores5masc", nullable = true)
	public Integer getMenores5masc() {
		return menores5masc;
	}

	public void setMenores5masc(Integer menores5masc) {
		this.menores5masc = menores5masc;
	}

	@Column(name = "menores5fem", nullable = true)
	public Integer getMenores5fem() {
		return menores5fem;
	}

	public void setMenores5fem(Integer menores5fem) {
		this.menores5fem = menores5fem;
	}

	@Column(name = "embarazadas", nullable = true)
	public Integer getEmbarazadas() {
		return embarazadas;
	}

	public void setEmbarazadas(Integer embarazadas) {
		this.embarazadas = embarazadas;
	}

	@ManyToOne(optional=true)
	@JoinColumn(name="assignedto")
    @ForeignKey(name = "FK_TAR_PER")
	public Personal getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Personal assignedTo) {
		this.assignedTo = assignedTo;
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
		if (!(other instanceof Target))
			return false;
		
		Target castOther = (Target) other;

		return (this.getIdent().equals(castOther.getIdent()));
	}
	

}
