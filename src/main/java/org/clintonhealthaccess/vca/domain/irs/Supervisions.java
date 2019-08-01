package org.clintonhealthaccess.vca.domain.irs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.clintonhealthaccess.vca.domain.BaseMetaData;
import org.clintonhealthaccess.vca.domain.audit.Auditable;
import org.hibernate.annotations.ForeignKey;



/**
 * 
 * Supervisions es la clase que representa las supervisiones realizadas en campo.
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "supervisions", catalog = "vca")
public class Supervisions extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ident;
	private Target target;
	private Date supervisionDate;
	private Supervisor supervisor;
	private Rociador rociador;
	private String usoEqProt;
	private String eqProtBien;
	private String numIden;
	private String aguaOp;
	private String propViv;
	private String mezcla;
	private String aguaAdec;
	private String mezclaPrep;
	private String agitaBomba;
	private String bombaCerrada;
	private String bombaPresion;
	private String compruebaBomba;
	private String colocApropiada;
	private String distApropiada;
	private String distBoquilla;
	private String pasoFrente;
	private String mantRitmo;
	private String metConteo;
	private String velocSuperficies;
	private String supFajas;
	private String pasosLaterales;
	private String salvarObstaculos;
	private String bienRociado;
	private String supInvertidas;
	private String objPiso;
	private String reportaConsumoAprop;
	private String transEqAprop;
	private String eqCompleto;
	private String cuidaMatEq;
	private String buenAspPersonal;
	private String cumpleInstrucciones;
	private String aceptaSuperv;
	private String respetuoso;
	
	
	

	public Supervisions() {
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
	@JoinColumn(name="target")
    @ForeignKey(name = "FK_SUP_TAR")
	public Target getTarget() {
		return target;
	}



	public void setTarget(Target target) {
		this.target = target;
	}


	@Column(name = "supDate", nullable = false)
	public Date getSupervisionDate() {
		return supervisionDate;
	}



	public void setSupervisionDate(Date supervisionDate) {
		this.supervisionDate = supervisionDate;
	}


	@ManyToOne(optional=false)
	@JoinColumn(name="supervisor")
    @ForeignKey(name = "FK_SUP_SUPER")
	public Supervisor getSupervisor() {
		return supervisor;
	}



	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}


	@ManyToOne(optional=false)
	@JoinColumn(name="sprayer")
    @ForeignKey(name = "FK_SUP_ROC")
	public Rociador getRociador() {
		return rociador;
	}



	public void setRociador(Rociador rociador) {
		this.rociador = rociador;
	}




	@Column(name = "usoEqProt", nullable = false, length = 5)
	public String getUsoEqProt() {
		return usoEqProt;
	}



	public void setUsoEqProt(String usoEqProt) {
		this.usoEqProt = usoEqProt;
	}


	@Column(name = "eqProtBien", nullable = false, length = 5)
	public String getEqProtBien() {
		return eqProtBien;
	}



	public void setEqProtBien(String eqProtBien) {
		this.eqProtBien = eqProtBien;
	}


	@Column(name = "numIden", nullable = false, length = 5)
	public String getNumIden() {
		return numIden;
	}



	public void setNumIden(String numIden) {
		this.numIden = numIden;
	}


	@Column(name = "aguaOp", nullable = false, length = 5)
	public String getAguaOp() {
		return aguaOp;
	}



	public void setAguaOp(String aguaOp) {
		this.aguaOp = aguaOp;
	}


	@Column(name = "propViv", nullable = false, length = 5)
	public String getPropViv() {
		return propViv;
	}



	public void setPropViv(String propViv) {
		this.propViv = propViv;
	}


	@Column(name = "mezcla", nullable = false, length = 5)
	public String getMezcla() {
		return mezcla;
	}



	public void setMezcla(String mezcla) {
		this.mezcla = mezcla;
	}


	@Column(name = "aguaAdec", nullable = false, length = 5)
	public String getAguaAdec() {
		return aguaAdec;
	}



	public void setAguaAdec(String aguaAdec) {
		this.aguaAdec = aguaAdec;
	}


	@Column(name = "mezclaPrep", nullable = false, length = 5)
	public String getMezclaPrep() {
		return mezclaPrep;
	}



	public void setMezclaPrep(String mezclaPrep) {
		this.mezclaPrep = mezclaPrep;
	}


	@Column(name = "agitaBomba", nullable = false, length = 5)
	public String getAgitaBomba() {
		return agitaBomba;
	}



	public void setAgitaBomba(String agitaBomba) {
		this.agitaBomba = agitaBomba;
	}


	@Column(name = "bombaCerrada", nullable = false, length = 5)
	public String getBombaCerrada() {
		return bombaCerrada;
	}



	public void setBombaCerrada(String bombaCerrada) {
		this.bombaCerrada = bombaCerrada;
	}


	@Column(name = "bombaPresion", nullable = false, length = 5)
	public String getBombaPresion() {
		return bombaPresion;
	}



	public void setBombaPresion(String bombaPresion) {
		this.bombaPresion = bombaPresion;
	}


	@Column(name = "compruebaBomba", nullable = false, length = 5)
	public String getCompruebaBomba() {
		return compruebaBomba;
	}



	public void setCompruebaBomba(String compruebaBomba) {
		this.compruebaBomba = compruebaBomba;
	}


	@Column(name = "colocApropiada", nullable = false, length = 5)
	public String getColocApropiada() {
		return colocApropiada;
	}



	public void setColocApropiada(String colocApropiada) {
		this.colocApropiada = colocApropiada;
	}


	@Column(name = "distApropiada", nullable = false, length = 5)
	public String getDistApropiada() {
		return distApropiada;
	}



	public void setDistApropiada(String distApropiada) {
		this.distApropiada = distApropiada;
	}


	@Column(name = "distBoquilla", nullable = false, length = 5)
	public String getDistBoquilla() {
		return distBoquilla;
	}



	public void setDistBoquilla(String distBoquilla) {
		this.distBoquilla = distBoquilla;
	}


	@Column(name = "pasoFrente", nullable = false, length = 5)
	public String getPasoFrente() {
		return pasoFrente;
	}



	public void setPasoFrente(String pasoFrente) {
		this.pasoFrente = pasoFrente;
	}


	@Column(name = "mantRitmo", nullable = false, length = 5)
	public String getMantRitmo() {
		return mantRitmo;
	}



	public void setMantRitmo(String mantRitmo) {
		this.mantRitmo = mantRitmo;
	}


	@Column(name = "metConteo", nullable = false, length = 5)
	public String getMetConteo() {
		return metConteo;
	}



	public void setMetConteo(String metConteo) {
		this.metConteo = metConteo;
	}


	@Column(name = "velocSuperficies", nullable = false, length = 5)
	public String getVelocSuperficies() {
		return velocSuperficies;
	}



	public void setVelocSuperficies(String velocSuperficies) {
		this.velocSuperficies = velocSuperficies;
	}


	@Column(name = "supFajas", nullable = false, length = 5)
	public String getSupFajas() {
		return supFajas;
	}



	public void setSupFajas(String supFajas) {
		this.supFajas = supFajas;
	}


	@Column(name = "pasosLaterales", nullable = false, length = 5)
	public String getPasosLaterales() {
		return pasosLaterales;
	}



	public void setPasosLaterales(String pasosLaterales) {
		this.pasosLaterales = pasosLaterales;
	}


	@Column(name = "salvarObstaculos", nullable = false, length = 5)
	public String getSalvarObstaculos() {
		return salvarObstaculos;
	}



	public void setSalvarObstaculos(String salvarObstaculos) {
		this.salvarObstaculos = salvarObstaculos;
	}


	@Column(name = "bienRociado", nullable = false, length = 5)
	public String getBienRociado() {
		return bienRociado;
	}



	public void setBienRociado(String bienRociado) {
		this.bienRociado = bienRociado;
	}


	@Column(name = "supInvertidas", nullable = false, length = 5)
	public String getSupInvertidas() {
		return supInvertidas;
	}



	public void setSupInvertidas(String supInvertidas) {
		this.supInvertidas = supInvertidas;
	}


	@Column(name = "objPiso", nullable = false, length = 5)
	public String getObjPiso() {
		return objPiso;
	}



	public void setObjPiso(String objPiso) {
		this.objPiso = objPiso;
	}


	@Column(name = "reportaConsumoAprop", nullable = false, length = 5)
	public String getReportaConsumoAprop() {
		return reportaConsumoAprop;
	}



	public void setReportaConsumoAprop(String reportaConsumoAprop) {
		this.reportaConsumoAprop = reportaConsumoAprop;
	}


	@Column(name = "transEqAprop", nullable = false, length = 5)
	public String getTransEqAprop() {
		return transEqAprop;
	}



	public void setTransEqAprop(String transEqAprop) {
		this.transEqAprop = transEqAprop;
	}


	@Column(name = "eqCompleto", nullable = false, length = 5)
	public String getEqCompleto() {
		return eqCompleto;
	}



	public void setEqCompleto(String eqCompleto) {
		this.eqCompleto = eqCompleto;
	}


	@Column(name = "cuidaMatEq", nullable = false, length = 5)
	public String getCuidaMatEq() {
		return cuidaMatEq;
	}



	public void setCuidaMatEq(String cuidaMatEq) {
		this.cuidaMatEq = cuidaMatEq;
	}


	@Column(name = "buenAspPersonal", nullable = false, length = 5)
	public String getBuenAspPersonal() {
		return buenAspPersonal;
	}



	public void setBuenAspPersonal(String buenAspPersonal) {
		this.buenAspPersonal = buenAspPersonal;
	}


	@Column(name = "cumpleInstrucciones", nullable = false, length = 5)
	public String getCumpleInstrucciones() {
		return cumpleInstrucciones;
	}



	public void setCumpleInstrucciones(String cumpleInstrucciones) {
		this.cumpleInstrucciones = cumpleInstrucciones;
	}


	@Column(name = "aceptaSuperv", nullable = false, length = 5)
	public String getAceptaSuperv() {
		return aceptaSuperv;
	}



	public void setAceptaSuperv(String aceptaSuperv) {
		this.aceptaSuperv = aceptaSuperv;
	}


	@Column(name = "respetuoso", nullable = false, length = 5)
	public String getRespetuoso() {
		return respetuoso;
	}



	public void setRespetuoso(String respetuoso) {
		this.respetuoso = respetuoso;
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
		if (!(other instanceof Supervisions))
			return false;
		
		Supervisions castOther = (Supervisions) other;

		return (this.getIdent().equals(castOther.getIdent()));
	}
	

}
