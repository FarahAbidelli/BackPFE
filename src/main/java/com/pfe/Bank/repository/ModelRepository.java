package com.pfe.Bank.repository;

import com.pfe.Bank.model.Model;
import com.pfe.Bank.model.Modul;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model,Long> {

    List<Model> findByDisabledFalse();
    @Query("SELECT m FROM Model m WHERE m.disabled = true")
    List<Model> findModelesToBeSoftDisabled();

    @Query("SELECT m FROM Model m WHERE m.disabled = false")
    List<Model> findModelesSoftDisabled();

    List<Model> findByUsed(boolean used);
}
