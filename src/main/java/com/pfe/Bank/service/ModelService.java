package com.pfe.Bank.service;

import com.pfe.Bank.exception.MissingEntity;
import com.pfe.Bank.form.ModelForm;
import com.pfe.Bank.model.Model;

import java.util.List;

public interface ModelService {
    List<Model> getAllModele();

    public Model addModele(ModelForm form) throws MissingEntity;
    Model getModeleById(long id) throws MissingEntity;
    public Model updateModele(Long id, ModelForm form) throws MissingEntity;
    void deleteModele(Long id) throws MissingEntity;
    public List<Model> getModelesToBeSoftDisabled();
    public List<Model> getModelesSoftDisabled();

    public List<Model> getModelesUsed();

    public List<Model> getModelesNotUsed();
    public void restoreModele(Long id) throws MissingEntity;

}
