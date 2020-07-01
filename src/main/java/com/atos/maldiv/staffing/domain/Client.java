package com.atos.maldiv.staffing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "jhi_client")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Client extends AbstractAuditingEntity implements Serializable  {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
private Long id;
private String nameClient;
private String namClientIris;    
private String codeClientIris;
private String marche;
private boolean isActive;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getNameClient() {
	return nameClient;
}
public void setNameClient(String nameClient) {
	this.nameClient = nameClient;
}
public String getNamClientIris() {
	return namClientIris;
}
public void setNamClientIris(String namClientIris) {
	this.namClientIris = namClientIris;
}
public String getCodeClientIris() {
	return codeClientIris;
}
public void setCodeClientIris(String codeClientIris) {
	this.codeClientIris = codeClientIris;
}
public String getMarche() {
	return marche;
}
public void setMarche(String marche) {
	this.marche = marche;
}
public boolean isActive() {
	return isActive;
}
public void setActive(boolean isActive) {
	this.isActive = isActive;
}


}
