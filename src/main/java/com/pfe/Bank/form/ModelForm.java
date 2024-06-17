package com.pfe.Bank.form;


import com.pfe.Bank.model.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModelForm {

    private long id;
    private String name;
    private String description;
    private Date dateCreation;
    private Date dateUpdate;
    private boolean used;
    private boolean updatebale;
    private Date nextUpdateDate;
    private Date lastUsedDate;
    private boolean disabled;

    private double minCaValue;
    private double maxCaValue;

    private boolean withFinancialData;



    public ModelForm(Model modele){
        this.id=modele.getId();
        this.name=modele.getName();
        this.description= modele.getDescription();
        this.dateCreation=modele.getDateCreation();
        this.dateUpdate=modele.getDateUpdate();
        this.used=modele.isUsed();
        this.updatebale= modele.isUpdatebale();
        this.nextUpdateDate=modele.getNextUpdateDate();
        this.lastUsedDate=modele.getLastUsedDate();
        this.disabled= modele.isDisabled();
        this.minCaValue =modele.getMinCaValue();
        this.maxCaValue = modele.getMaxCaValue();



    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
