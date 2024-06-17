package com.pfe.Bank.dto;

import com.pfe.Bank.form.ModelForm;
import com.pfe.Bank.model.Model;

import java.util.List;
import java.util.stream.Collectors;

public class ModelDto extends ModelForm {

    public ModelDto(Model modele) {
        super(modele);
    }

    public static ModelDto of(Model modele){
        return new ModelDto(modele);
    }

    public static List<ModelDto> of(List<Model> modeles){
        return modeles.stream().map(ModelDto::of).collect(Collectors.toList());
    }
}
