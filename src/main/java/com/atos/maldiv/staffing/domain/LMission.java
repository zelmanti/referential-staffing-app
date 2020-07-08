package com.atos.maldiv.staffing.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A LMission.
 */
@Entity
@Table(name = "l_mission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LMission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "description_mission")
    private String descriptionMission;

    @Column(name = "indicateur_renouvelement")
    private String indicateurRenouvelement;

    @Column(name = "is_active")
    private String isActive;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LMission startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LMission endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescriptionMission() {
        return descriptionMission;
    }

    public LMission descriptionMission(String descriptionMission) {
        this.descriptionMission = descriptionMission;
        return this;
    }

    public void setDescriptionMission(String descriptionMission) {
        this.descriptionMission = descriptionMission;
    }

    public String getIndicateurRenouvelement() {
        return indicateurRenouvelement;
    }

    public LMission indicateurRenouvelement(String indicateurRenouvelement) {
        this.indicateurRenouvelement = indicateurRenouvelement;
        return this;
    }

    public void setIndicateurRenouvelement(String indicateurRenouvelement) {
        this.indicateurRenouvelement = indicateurRenouvelement;
    }

    public String getIsActive() {
        return isActive;
    }

    public LMission isActive(String isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LMission)) {
            return false;
        }
        return id != null && id.equals(((LMission) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LMission{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", descriptionMission='" + getDescriptionMission() + "'" +
            ", indicateurRenouvelement='" + getIndicateurRenouvelement() + "'" +
            ", isActive='" + getIsActive() + "'" +
            "}";
    }
}
