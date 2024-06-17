package com.pfe.Bank.controller;
import com.pfe.Bank.dto.ModelDto;
import com.pfe.Bank.exception.MissingEntity;
import com.pfe.Bank.form.ModelForm;
import com.pfe.Bank.model.Model;
import com.pfe.Bank.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequiredArgsConstructor

public class ModelController {

    @Autowired
    ModelService modeleService;

    @PostMapping("/addmodels")
    public ModelDto addModele(@RequestBody ModelForm form) throws MissingEntity {
        Model modele = modeleService.addModele(form);
        return ModelDto.of(modele);
    }

    @GetMapping("/models")
    List<ModelDto> getAllModeles() {
        List<Model> modele = modeleService.getAllModele();
        return ModelDto.of(modele);
    }

    @GetMapping("/models/{id}")
    public ModelDto getModeleById(@PathVariable Long id) throws MissingEntity {
        Model modele = modeleService.getModeleById(id);
        return ModelDto.of(modele);
    }

    @PutMapping("/modelsput/{id}")
    public ModelDto updateModele(@PathVariable Long id, @RequestBody ModelForm form) throws MissingEntity {
        Model modele = modeleService.getModeleById(id);

        if (modele == null) {
            throw new MissingEntity("Modèle introuvable avec l'ID : " + id);
        }
        form.setDateCreation(modele.getDateCreation());
        modele.setUpdatebale(true);
        modele = modeleService.updateModele(id, form);

        return ModelDto.of(modele);
    }

    @DeleteMapping("/deletemodels/{id}")
    public ResponseEntity<Void> deleteModele(@PathVariable Long id) {
        try {
            modeleService.deleteModele(id);
            return ResponseEntity.noContent().build();
        } catch (MissingEntity e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ModelsSoftDeleted")
    public List<Model> getModelesToBeSoftDeleted() {
        return modeleService.getModelesToBeSoftDisabled();
    }

    @GetMapping("/ModelsSoftDeletedTrue")
    public List<Model> getModelesSoftDeleted() {
        return modeleService.getModelesSoftDisabled();
    }

    @GetMapping("/ModelUsed")
    public ResponseEntity<List<Model>> getModelUsed() {
        List<Model> modelesUtilises = modeleService.getModelesUsed();
        return new ResponseEntity<>(modelesUtilises, HttpStatus.OK);
    }

    @GetMapping("/ModelNotUsed")
    public ResponseEntity<List<Model>> getModelNotUsed() {
        List<Model> modelesNonUtilises = modeleService.getModelesNotUsed();
        return new ResponseEntity<>(modelesNonUtilises, HttpStatus.OK);
    }

    @PutMapping("/restoreModele/{id}")
    public ResponseEntity<Void> restoreModele(@PathVariable Long id) {
        try {
            modeleService.restoreModele(id);
            return ResponseEntity.noContent().build();
        } catch (MissingEntity e) {
            return ResponseEntity.notFound().build();
        }
    }


}
