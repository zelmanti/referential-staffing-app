package com.atos.maldiv.staffing.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name_client")
    private String nameClient;

    @Column(name = "nam_client_iris")
    private String namClientIris;

    @Column(name = "code_client_iris")
    private String codeClientIris;

    @Column(name = "marche")
    private String marche;

    @Column(name = "is_active")
    private Boolean isActive;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameClient() {
        return nameClient;
    }

    public Client nameClient(String nameClient) {
        this.nameClient = nameClient;
        return this;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getNamClientIris() {
        return namClientIris;
    }

    public Client namClientIris(String namClientIris) {
        this.namClientIris = namClientIris;
        return this;
    }

    public void setNamClientIris(String namClientIris) {
        this.namClientIris = namClientIris;
    }

    public String getCodeClientIris() {
        return codeClientIris;
    }

    public Client codeClientIris(String codeClientIris) {
        this.codeClientIris = codeClientIris;
        return this;
    }

    public void setCodeClientIris(String codeClientIris) {
        this.codeClientIris = codeClientIris;
    }

    public String getMarche() {
        return marche;
    }

    public Client marche(String marche) {
        this.marche = marche;
        return this;
    }

    public void setMarche(String marche) {
        this.marche = marche;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Client isActive(Boolean isActive) {
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
        if (!(o instanceof Client)) {
            return false;
        }
        return id != null && id.equals(((Client) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", nameClient='" + getNameClient() + "'" +
            ", namClientIris='" + getNamClientIris() + "'" +
            ", codeClientIris='" + getCodeClientIris() + "'" +
            ", marche='" + getMarche() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
