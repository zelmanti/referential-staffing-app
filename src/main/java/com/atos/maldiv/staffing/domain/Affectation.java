package com.atos.maldiv.staffing.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Affectation.
 */
@Entity
@Table(name = "affectation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Affectation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ressource")
    private String ressource;

    @Column(name = "rattachement_ressource")
    private String rattachementRessource;

    @Column(name = "prestation")
    private String prestation;

    @Column(name = "code_projet")
    private String codeProjet;

    @Column(name = "status")
    private String status;

    @Column(name = "client")
    private String client;

    @Column(name = "rattachement_client")
    private String rattachementClient;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRessource() {
        return ressource;
    }

    public Affectation ressource(String ressource) {
        this.ressource = ressource;
        return this;
    }

    public void setRessource(String ressource) {
        this.ressource = ressource;
    }

    public String getRattachementRessource() {
        return rattachementRessource;
    }

    public Affectation rattachementRessource(String rattachementRessource) {
        this.rattachementRessource = rattachementRessource;
        return this;
    }

    public void setRattachementRessource(String rattachementRessource) {
        this.rattachementRessource = rattachementRessource;
    }

    public String getPrestation() {
        return prestation;
    }

    public Affectation prestation(String prestation) {
        this.prestation = prestation;
        return this;
    }

    public void setPrestation(String prestation) {
        this.prestation = prestation;
    }

    public String getCodeProjet() {
        return codeProjet;
    }

    public Affectation codeProjet(String codeProjet) {
        this.codeProjet = codeProjet;
        return this;
    }

    public void setCodeProjet(String codeProjet) {
        this.codeProjet = codeProjet;
    }

    public String getStatus() {
        return status;
    }

    public Affectation status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClient() {
        return client;
    }

    public Affectation client(String client) {
        this.client = client;
        return this;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getRattachementClient() {
        return rattachementClient;
    }

    public Affectation rattachementClient(String rattachementClient) {
        this.rattachementClient = rattachementClient;
        return this;
    }

    public void setRattachementClient(String rattachementClient) {
        this.rattachementClient = rattachementClient;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Affectation startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Affectation endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Affectation)) {
            return false;
        }
        return id != null && id.equals(((Affectation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Affectation{" +
            "id=" + getId() +
            ", ressource='" + getRessource() + "'" +
            ", rattachementRessource='" + getRattachementRessource() + "'" +
            ", prestation='" + getPrestation() + "'" +
            ", codeProjet='" + getCodeProjet() + "'" +
            ", status='" + getStatus() + "'" +
            ", client='" + getClient() + "'" +
            ", rattachementClient='" + getRattachementClient() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
}
