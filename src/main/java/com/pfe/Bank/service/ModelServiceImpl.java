package com.pfe.Bank.service;

import com.pfe.Bank.exception.MissingEntity;
import com.pfe.Bank.form.ModelForm;
import com.pfe.Bank.model.Model;
import com.pfe.Bank.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ModelServiceImpl implements ModelService{

    @Autowired
    ModelRepository modeleRepository;

    @Override
    public List<Model> getAllModele() {

        return modeleRepository.findAll();
    }

    @Override
    public Model addModele(ModelForm form) throws MissingEntity {
        Model modele = new Model();
        modele.setId(form.getId());
        modele.setName(form.getName());
        modele.setDescription(form.getDescription());
        modele.setDisabled(form.isDisabled());
        modele.setDateUpdate(form.getDateUpdate());
        modele.setUsed(form.isUsed());
        modele.setDateCreation(form.getDateCreation());
        modele.setUpdatebale(form.isUpdatebale());
        modele.setMinCaValue(form.getMinCaValue());
        modele.setMaxCaValue(form.getMaxCaValue());
        if (modele.isUsed()) {
            modele.setLastUsedDate(new Date());
        }
        if (modele.isUpdatebale()) {
            modele.setNextUpdateDate(new Date());
        }
        modele.setDisabled(form.isDisabled());
        return modeleRepository.save(modele);
    }

    @Override
    public Model getModeleById(long id) throws MissingEntity {
        Optional<Model> optional = modeleRepository.findById(id);
        if (!optional.isPresent()) {
            throw new MissingEntity("Modele not found with code Menu : " + id);
        }
        return optional.get();
    }

    @Override
    public Model updateModele(Long id, ModelForm form) throws MissingEntity {
        Model modele = getModeleById(id);
        modele.setName(form.getName());
        modele.setDescription(form.getDescription());
        modele.setDateUpdate(form.getDateUpdate());
        modele.setUsed(form.isUsed());
        modele.setDateCreation(form.getDateCreation());
        modele.setUpdatebale(form.isUpdatebale());
        modele.setUpdatebale(true);
        //modele.setLastUsedDate(new Date());
        modele.setNextUpdateDate(new Date());
        modele.setDisabled(form.isDisabled());
        modele.setMinCaValue(form.getMinCaValue());
        modele.setMaxCaValue(form.getMaxCaValue());
        return modeleRepository.save(modele);
    }

    @Override
    public void deleteModele(Long id) throws MissingEntity {
        Model modele = getModeleById(id);
        modele.setDisabled(true);
        modeleRepository.delete(modele);
    }

    public List<Model> getModelesToBeSoftDisabled() {
        return modeleRepository.findModelesToBeSoftDisabled();
    }

    @Override
    public List<Model> getModelesSoftDisabled() {
        return modeleRepository.findModelesSoftDisabled();
    }

    @Override
    public List<Model> getModelesUsed() {

        return modeleRepository.findByUsed(true);
    }

    @Override
    public List<Model> getModelesNotUsed() {

        return modeleRepository.findByUsed(false);
    }

    @Override
    public void restoreModele(Long id) throws MissingEntity {
        Model modele = modeleRepository.findById(id).orElseThrow(() -> new MissingEntity("Modele not found"));
        modele.setDisabled(false);
        modeleRepository.save(modele);
    }
}
