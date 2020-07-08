package com.atos.maldiv.staffing.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Forfait.
 */
@Entity
@Table(name = "forfait")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Forfait implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "projet")
    private String projet;

    @Column(name = "responsable")
    private String responsable;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "ressource")
    private String ressource;

    @Column(name = "status")
    private String status;

    @Column(name = "lieu")
    private String lieu;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjet() {
        return projet;
    }

    public Forfait projet(String projet) {
        this.projet = projet;
        return this;
    }

    public void setProjet(String projet) {
        this.projet = projet;
    }

    public String getResponsable() {
        return responsable;
    }

    public Forfait responsable(String responsable) {
        this.responsable = responsable;
        return this;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Forfait startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Forfait endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getRessource() {
        return ressource;
    }

    public Forfait ressource(String ressource) {
        this.ressource = ressource;
        return this;
    }

    public void setRessource(String ressource) {
        this.ressource = ressource;
    }

    public String getStatus() {
        return status;
    }

    public Forfait status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLieu() {
        return lieu;
    }

    public Forfait lieu(String lieu) {
        this.lieu = lieu;
        return this;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Forfait)) {
            return false;
        }
        return id != null && id.equals(((Forfait) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Forfait{" +
            "id=" + getId() +
            ", projet='" + getProjet() + "'" +
            ", responsable='" + getResponsable() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", ressource='" + getRessource() + "'" +
            ", status='" + getStatus() + "'" +
            ", lieu='" + getLieu() + "'" +
            "}";
    }
}
