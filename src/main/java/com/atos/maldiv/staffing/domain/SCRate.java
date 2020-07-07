package com.atos.maldiv.staffing.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A SCRate.
 */
@Entity
@Table(name = "sc_rate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SCRate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "niveau")
    private String niveau;

    @Column(name = "localisation")
    private String localisation;

    @Column(name = "montant")
    private String montant;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public SCRate code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNiveau() {
        return niveau;
    }

    public SCRate niveau(String niveau) {
        this.niveau = niveau;
        return this;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getLocalisation() {
        return localisation;
    }

    public SCRate localisation(String localisation) {
        this.localisation = localisation;
        return this;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getMontant() {
        return montant;
    }

    public SCRate montant(String montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SCRate)) {
            return false;
        }
        return id != null && id.equals(((SCRate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SCRate{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", niveau='" + getNiveau() + "'" +
            ", localisation='" + getLocalisation() + "'" +
            ", montant='" + getMontant() + "'" +
            "}";
    }
}
