package org.clintonhealthaccess.vca.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.clintonhealthaccess.vca.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;



/**
 * Household es la clase que representa la vivienda donde se registra la información.
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "households", catalog = "vca", uniqueConstraints={@UniqueConstraint(columnNames = {"code","pasive"})})
public class Household extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ident;
	private String code;
	private Localidad local;
	private Censador censusTaker;
	private Date censusDate;
	private String inhabited;
	private String ownerName;
	private Integer habitants;
	private String material;
	private Integer rooms;
	private Integer sprRooms;
	private Integer noSprooms;
	private String noSproomsReasons;
	private Double latitude;
	private Double longitude;
	private String obs;
	
	public Household() {
		super();
	}



	@Id
    @Column(name = "id", nullable = false, length = 50)
	public String getIdent() {
		return ident;
	}


	public void setIdent(String ident) {
		this.ident = ident;
	}
	
	@Column(name = "code", nullable = false, length = 100)
	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}

	
	@ManyToOne(optional=false)
	@JoinColumn(name="local")
    @ForeignKey(name = "FK_VIVIENDA_LOCALIDAD")
	public Localidad getLocal() {
		return local;
	}

	public void setLocal(Localidad local) {
		this.local = local;
	}

	
	@ManyToOne(optional=false)
	@JoinColumn(name="censustaker")
    @ForeignKey(name = "FK_VIVIENDA_CESADOR")
	public Censador getCensusTaker() {
		return censusTaker;
	}



	public void setCensusTaker(Censador censusTaker) {
		this.censusTaker = censusTaker;
	}



	@Column(name = "censusDate", nullable = false)
	public Date getCensusDate() {
		return censusDate;
	}



	public void setCensusDate(Date censusDate) {
		this.censusDate = censusDate;
	}


	@Column(name = "inhabited", nullable = false, length = 50)
	public String getInhabited() {
		return inhabited;
	}



	public void setInhabited(String inhabited) {
		this.inhabited = inhabited;
	}


	@Column(name = "ownerName", nullable = false, length = 250)
	public String getOwnerName() {
		return ownerName;
	}



	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	@Column(name = "habitants", nullable = true)
	public Integer getHabitants() {
		return habitants;
	}



	public void setHabitants(Integer habitants) {
		this.habitants = habitants;
	}


	@Column(name = "material", nullable = false, length = 50)
	public String getMaterial() {
		return material;
	}



	public void setMaterial(String material) {
		this.material = material;
	}


	@Column(name = "rooms", nullable = true)
	public Integer getRooms() {
		return rooms;
	}



	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}


	@Column(name = "sprRooms", nullable = true)
	public Integer getSprRooms() {
		return sprRooms;
	}



	public void setSprRooms(Integer sprRooms) {
		this.sprRooms = sprRooms;
	}


	@Column(name = "noSprooms", nullable = true)
	public Integer getNoSprooms() {
		return noSprooms;
	}



	public void setNoSprooms(Integer noSprooms) {
		this.noSprooms = noSprooms;
	}


	@Column(name = "noSproomsReasons", nullable = true, length = 250)
	public String getNoSproomsReasons() {
		return noSproomsReasons;
	}



	public void setNoSproomsReasons(String noSproomsReasons) {
		this.noSproomsReasons = noSproomsReasons;
	}


	@Column(name = "latitude", nullable = true)
	public Double getLatitude() {
		return latitude;
	}



	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	@Column(name = "longitude", nullable = true)
	public Double getLongitude() {
		return longitude;
	}



	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	@Column(name = "obs", nullable = true, length = 750)
	public String getObs() {
		return obs;
	}



	public void setObs(String obs) {
		this.obs = obs;
	}



	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}
	
	
	@Override
	public String toString(){
		return this.getCode();
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Household))
			return false;
		
		Household castOther = (Household) other;

		return (this.getIdent().equals(castOther.getIdent()));
	}
	

}
