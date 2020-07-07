package com.atos.maldiv.staffing.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A EntityType.
 */
@Entity
@Table(name = "entity_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EntityType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code_type_entitee")
    private String codeTypeEntitee;

    @Column(name = "libelle_type_entitee")
    private String libelleTypeEntitee;

    @Column(name = "is_active")
    private Boolean isActive;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeTypeEntitee() {
        return codeTypeEntitee;
    }

    public EntityType codeTypeEntitee(String codeTypeEntitee) {
        this.codeTypeEntitee = codeTypeEntitee;
        return this;
    }

    public void setCodeTypeEntitee(String codeTypeEntitee) {
        this.codeTypeEntitee = codeTypeEntitee;
    }

    public String getLibelleTypeEntitee() {
        return libelleTypeEntitee;
    }

    public EntityType libelleTypeEntitee(String libelleTypeEntitee) {
        this.libelleTypeEntitee = libelleTypeEntitee;
        return this;
    }

    public void setLibelleTypeEntitee(String libelleTypeEntitee) {
        this.libelleTypeEntitee = libelleTypeEntitee;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public EntityType isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityType)) {
            return false;
        }
        return id != null && id.equals(((EntityType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntityType{" +
            "id=" + getId() +
            ", codeTypeEntitee='" + getCodeTypeEntitee() + "'" +
            ", libelleTypeEntitee='" + getLibelleTypeEntitee() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
