package com.atos.maldiv.staffing.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "jhi_affectation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Affectation extends AbstractAuditingEntity implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id; 
    private String ressource;
    private String rattachementRessource;
    private String prestation;   
    private String codeProjet;
    private String status;
    private String client;
    private String rattachementClient;
    private Date startDate;
    private Date endDate;
    
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRessource() {
		return ressource;
	}
	public void setRessource(String ressource) {
		this.ressource = ressource;
	}
	public String getRattachementRessource() {
		return rattachementRessource;
	}
	public void setRattachementRessource(String rattachementRessource) {
		this.rattachementRessource = rattachementRessource;
	}
	public String getPrestation() {
		return prestation;
	}
	public void setPrestation(String prestation) {
		this.prestation = prestation;
	}
	public String getCodeProjet() {
		return codeProjet;
	}
	public void setCodeProjet(String codeProjet) {
		this.codeProjet = codeProjet;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getRattachementClient() {
		return rattachementClient;
	}
	public void setRattachementClient(String rattachementClient) {
		this.rattachementClient = rattachementClient;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getTaux() {
		return taux;
	}
	public void setTaux(String taux) {
		this.taux = taux;
	}
	private String  taux;
   
}
