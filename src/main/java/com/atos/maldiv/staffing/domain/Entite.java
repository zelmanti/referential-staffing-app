package com.atos.maldiv.staffing.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Entite.
 */
@Entity
@Table(name = "entite")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Entite implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code_entity")
    private String codeEntity;

    @Column(name = "libele_entity")
    private String libeleEntity;

    @Column(name = "type_entitee")
    private String typeEntitee;

    @Column(name = "manager")
    private String manager;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeEntity() {
        return codeEntity;
    }

    public Entite codeEntity(String codeEntity) {
        this.codeEntity = codeEntity;
        return this;
    }

    public void setCodeEntity(String codeEntity) {
        this.codeEntity = codeEntity;
    }

    public String getLibeleEntity() {
        return libeleEntity;
    }

    public Entite libeleEntity(String libeleEntity) {
        this.libeleEntity = libeleEntity;
        return this;
    }

    public void setLibeleEntity(String libeleEntity) {
        this.libeleEntity = libeleEntity;
    }

    public String getTypeEntitee() {
        return typeEntitee;
    }

    public Entite typeEntitee(String typeEntitee) {
        this.typeEntitee = typeEntitee;
        return this;
    }

    public void setTypeEntitee(String typeEntitee) {
        this.typeEntitee = typeEntitee;
    }

    public String getManager() {
        return manager;
    }

    public Entite manager(String manager) {
        this.manager = manager;
        return this;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Entite)) {
            return false;
        }
        return id != null && id.equals(((Entite) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Entite{" +
            "id=" + getId() +
            ", codeEntity='" + getCodeEntity() + "'" +
            ", libeleEntity='" + getLibeleEntity() + "'" +
            ", typeEntitee='" + getTypeEntitee() + "'" +
            ", manager='" + getManager() + "'" +
            "}";
    }
}
