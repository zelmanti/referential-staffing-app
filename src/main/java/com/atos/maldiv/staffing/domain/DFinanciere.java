package com.atos.maldiv.staffing.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DFinanciere.
 */
@Entity
@Table(name = "d_financiere")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DFinanciere implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "tjm")
    private Double tjm;

    @Column(name = "rfa")
    private Double rfa;

    @Column(name = "frais_mentuels")
    private Double fraisMentuels;

    @Column(name = "frais_journaliers")
    private Double fraisJournaliers;

    @Column(name = "marge_calculee")
    private Double margeCalculee;

    @Column(name = "indicateur_t_revenue")
    private String indicateurTRevenue;

    @Column(name = "jours_travailee")
    private LocalDate joursTravailee;

    @Column(name = "mois_travailee")
    private LocalDate moisTravailee;

    @Column(name = "annees_travailee")
    private LocalDate anneesTravailee;

    @Column(name = "scr")
    private String scr;

    @Column(name = "chiffre_affaire_calculee")
    private Double chiffreAffaireCalculee;

    @Column(name = "couts_calculee")
    private Double coutsCalculee;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public DFinanciere startDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public DFinanciere endDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getTjm() {
        return tjm;
    }

    public DFinanciere tjm(Double tjm) {
        this.tjm = tjm;
        return this;
    }

    public void setTjm(Double tjm) {
        this.tjm = tjm;
    }

    public Double getRfa() {
        return rfa;
    }

    public DFinanciere rfa(Double rfa) {
        this.rfa = rfa;
        return this;
    }

    public void setRfa(Double rfa) {
        this.rfa = rfa;
    }

    public Double getFraisMentuels() {
        return fraisMentuels;
    }

    public DFinanciere fraisMentuels(Double fraisMentuels) {
        this.fraisMentuels = fraisMentuels;
        return this;
    }

    public void setFraisMentuels(Double fraisMentuels) {
        this.fraisMentuels = fraisMentuels;
    }

    public Double getFraisJournaliers() {
        return fraisJournaliers;
    }

    public DFinanciere fraisJournaliers(Double fraisJournaliers) {
        this.fraisJournaliers = fraisJournaliers;
        return this;
    }

    public void setFraisJournaliers(Double fraisJournaliers) {
        this.fraisJournaliers = fraisJournaliers;
    }

    public Double getMargeCalculee() {
        return margeCalculee;
    }

    public DFinanciere margeCalculee(Double margeCalculee) {
        this.margeCalculee = margeCalculee;
        return this;
    }

    public void setMargeCalculee(Double margeCalculee) {
        this.margeCalculee = margeCalculee;
    }

    public String getIndicateurTRevenue() {
        return indicateurTRevenue;
    }

    public DFinanciere indicateurTRevenue(String indicateurTRevenue) {
        this.indicateurTRevenue = indicateurTRevenue;
        return this;
    }

    public void setIndicateurTRevenue(String indicateurTRevenue) {
        this.indicateurTRevenue = indicateurTRevenue;
    }

    public LocalDate getJoursTravailee() {
        return joursTravailee;
    }

    public DFinanciere joursTravailee(LocalDate joursTravailee) {
        this.joursTravailee = joursTravailee;
        return this;
    }

    public void setJoursTravailee(LocalDate joursTravailee) {
        this.joursTravailee = joursTravailee;
    }

    public LocalDate getMoisTravailee() {
        return moisTravailee;
    }

    public DFinanciere moisTravailee(LocalDate moisTravailee) {
        this.moisTravailee = moisTravailee;
        return this;
    }

    public void setMoisTravailee(LocalDate moisTravailee) {
        this.moisTravailee = moisTravailee;
    }

    public LocalDate getAnneesTravailee() {
        return anneesTravailee;
    }

    public DFinanciere anneesTravailee(LocalDate anneesTravailee) {
        this.anneesTravailee = anneesTravailee;
        return this;
    }

    public void setAnneesTravailee(LocalDate anneesTravailee) {
        this.anneesTravailee = anneesTravailee;
    }

    public String getScr() {
        return scr;
    }

    public DFinanciere scr(String scr) {
        this.scr = scr;
        return this;
    }

    public void setScr(String scr) {
        this.scr = scr;
    }

    public Double getChiffreAffaireCalculee() {
        return chiffreAffaireCalculee;
    }

    public DFinanciere chiffreAffaireCalculee(Double chiffreAffaireCalculee) {
        this.chiffreAffaireCalculee = chiffreAffaireCalculee;
        return this;
    }

    public void setChiffreAffaireCalculee(Double chiffreAffaireCalculee) {
        this.chiffreAffaireCalculee = chiffreAffaireCalculee;
    }

    public Double getCoutsCalculee() {
        return coutsCalculee;
    }

    public DFinanciere coutsCalculee(Double coutsCalculee) {
        this.coutsCalculee = coutsCalculee;
        return this;
    }

    public void setCoutsCalculee(Double coutsCalculee) {
        this.coutsCalculee = coutsCalculee;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DFinanciere)) {
            return false;
        }
        return id != null && id.equals(((DFinanciere) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DFinanciere{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", tjm=" + getTjm() +
            ", rfa=" + getRfa() +
            ", fraisMentuels=" + getFraisMentuels() +
            ", fraisJournaliers=" + getFraisJournaliers() +
            ", margeCalculee=" + getMargeCalculee() +
            ", indicateurTRevenue='" + getIndicateurTRevenue() + "'" +
            ", joursTravailee='" + getJoursTravailee() + "'" +
            ", moisTravailee='" + getMoisTravailee() + "'" +
            ", anneesTravailee='" + getAnneesTravailee() + "'" +
            ", scr='" + getScr() + "'" +
            ", chiffreAffaireCalculee=" + getChiffreAffaireCalculee() +
            ", coutsCalculee=" + getCoutsCalculee() +
            "}";
    }
}
