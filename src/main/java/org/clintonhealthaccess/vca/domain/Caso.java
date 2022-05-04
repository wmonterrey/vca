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
import org.springframework.format.annotation.DateTimeFormat;



/**
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "casos", catalog = "vca", uniqueConstraints={@UniqueConstraint(columnNames = {"codigo","pasive"})})
public class Caso extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ident;
	private Localidad local;
	private String codigo;
	private String cui;
	private String casa;
	private String nombre;
	private Date fisDate;
	private Date mxDate;
	private String inv="0";
	private Date invDate;
	private String tx="0";
	private String txSup="No";
	private Date txDate;
	private String txComp="0";
	private Date txCompDate;
	private String sx="0";
	private Date sxDate;
	private String sxResult;
	private String sxComp="0";
	private Date sxCompDate;
	private String sxCompResult;
	private String lostFollowUp="0";
	private String lostFollowUpReason;
	private String estadocaso="CONF";
	private String info;
	private Double latitude;
	private Double longitude;
	private Integer zoom=10;
	
	
	public Caso() {
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

	
	@ManyToOne(optional=false)
	@JoinColumn(name="local")
    @ForeignKey(name = "FK_CASO_LOCALIDAD")
	public Localidad getLocal() {
		return local;
	}

	public void setLocal(Localidad local) {
		this.local = local;
	}

	@Column(name = "codigo", nullable = false, length = 100)
	public String getCodigo() {
		return codigo;
	}


	@Column(name = "cui", nullable = true, length = 100)
	public String getCui() {
		return cui;
	}



	public void setCui(String cui) {
		this.cui = cui;
	}


	@Column(name = "casa", nullable = true, length = 100)
	public String getCasa() {
		return casa;
	}



	public void setCasa(String casa) {
		this.casa = casa;
	}


	@Column(name = "nombre", nullable = true, length = 500)
	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	

	
	@Column(name = "fisDate", nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	public Date getFisDate() {
		return fisDate;
	}



	public void setFisDate(Date fisDate) {
		this.fisDate = fisDate;
	}


	@Column(name = "mxDate", nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	public Date getMxDate() {
		return mxDate;
	}



	public void setMxDate(Date mxDate) {
		this.mxDate = mxDate;
	}

	
	
	

	@Column(name = "inv", nullable = false, length = 1)
	public String getInv() {
		return inv;
	}



	public void setInv(String inv) {
		this.inv = inv;
	}


	@Column(name = "invDate", nullable = true)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	public Date getInvDate() {
		return invDate;
	}



	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}


	@Column(name = "tx", nullable = false, length = 1)
	public String getTx() {
		return tx;
	}



	public void setTx(String tx) {
		this.tx = tx;
	}


	@Column(name = "txSup", nullable = false, length = 100)
	public String getTxSup() {
		return txSup;
	}



	public void setTxSup(String txSup) {
		this.txSup = txSup;
	}


	@Column(name = "txDate", nullable = true)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	public Date getTxDate() {
		return txDate;
	}



	public void setTxDate(Date txDate) {
		this.txDate = txDate;
	}


	@Column(name = "txComp", nullable = false, length = 1)
	public String getTxComp() {
		return txComp;
	}



	public void setTxComp(String txComp) {
		this.txComp = txComp;
	}


	@Column(name = "txCompDate", nullable = true)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	public Date getTxCompDate() {
		return txCompDate;
	}



	public void setTxCompDate(Date txCompDate) {
		this.txCompDate = txCompDate;
	}


	@Column(name = "sx", nullable = false, length = 1)
	public String getSx() {
		return sx;
	}



	public void setSx(String sx) {
		this.sx = sx;
	}


	@Column(name = "sxDate", nullable = true)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	public Date getSxDate() {
		return sxDate;
	}



	public void setSxDate(Date sxDate) {
		this.sxDate = sxDate;
	}


	@Column(name = "lostFollowUp", nullable = false, length = 1)
	public String getLostFollowUp() {
		return lostFollowUp;
	}



	public void setLostFollowUp(String lostFollowUp) {
		this.lostFollowUp = lostFollowUp;
	}


	@Column(name = "sxCompDate", nullable = true)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	public Date getSxCompDate() {
		return sxCompDate;
	}



	public void setSxCompDate(Date sxCompDate) {
		this.sxCompDate = sxCompDate;
	}

	@Column(name = "sxComp", nullable = false, length = 1)
	public String getSxComp() {
		return sxComp;
	}



	public void setSxComp(String sxComp) {
		this.sxComp = sxComp;
	}
	
	@Column(name = "lostFollowUpReason", nullable = true, length = 750)
	public String getLostFollowUpReason() {
		return lostFollowUpReason;
	}



	public void setLostFollowUpReason(String lostFollowUpReason) {
		this.lostFollowUpReason = lostFollowUpReason;
	}


	@Column(name = "info", nullable = true, length = 750)
	public String getInfo() {
		return info;
	}



	public void setInfo(String info) {
		this.info = info;
	}
	
	
	
	@Column(name = "estadocaso", nullable = false, length = 50)
	public String getEstadocaso() {
		return estadocaso;
	}



	public void setEstadocaso(String estadocaso) {
		this.estadocaso = estadocaso;
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
	
	
	
	@Column(name = "zoom", nullable = true)
	public Integer getZoom() {
		return zoom;
	}



	public void setZoom(Integer zoom) {
		this.zoom = zoom;
	}
	
	
	@Column(name = "sxResult", nullable = true, length = 50)
	public String getSxResult() {
		return sxResult;
	}



	public void setSxResult(String sxResult) {
		this.sxResult = sxResult;
	}


	@Column(name = "sxCompResult", nullable = true, length = 50)
	public String getSxCompResult() {
		return sxCompResult;
	}



	public void setSxCompResult(String sxCompResult) {
		this.sxCompResult = sxCompResult;
	}



	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}
	
	
	@Override
	public String toString(){
		return this.getCodigo();
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Caso))
			return false;
		
		Caso castOther = (Caso) other;

		return (this.getIdent().equals(castOther.getIdent()));
	}
	

}
