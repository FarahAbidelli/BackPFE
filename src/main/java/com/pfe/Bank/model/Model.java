package com.pfe.Bank.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="model")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @Temporal(TemporalType.DATE)
    private Date dateUpdate;

    private boolean used;
    private boolean updatebale;

    @Temporal(TemporalType.DATE)
    private Date nextUpdateDate;

    @Temporal(TemporalType.DATE)
    private Date lastUsedDate;

    private boolean disabled =false;

    private double minCaValue;
    private double maxCaValue;

    private boolean withFinancialData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isUpdatebale() {
        return updatebale;
    }

    public void setUpdatebale(boolean updatebale) {
        this.updatebale = updatebale;
    }

    public Date getNextUpdateDate() {
        return nextUpdateDate;
    }

    public void setNextUpdateDate(Date nextUpdateDate) {
        this.nextUpdateDate = nextUpdateDate;
    }

    public Date getLastUsedDate() {
        return lastUsedDate;
    }

    public void setLastUsedDate(Date lastUsedDate) {
        this.lastUsedDate = lastUsedDate;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public double getMinCaValue() {
        return minCaValue;
    }

    public void setMinCaValue(double minCaValue) {
        this.minCaValue = minCaValue;
    }

    public double getMaxCaValue() {
        return maxCaValue;
    }

    public void setMaxCaValue(double maxCaValue) {
        this.maxCaValue = maxCaValue;
    }

    public boolean isWithFinancialData() {
        return withFinancialData;
    }

    public void setWithFinancialData(boolean withFinancialData) {
        this.withFinancialData = withFinancialData;
    }
}
