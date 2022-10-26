/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.miportfolio.Shushu.Repository;

import com.miportfolio.Shushu.Entity.Experiencia_Labo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoExpe extends JpaRepository<Experiencia_Labo, Integer> {
    public Optional<Experiencia_Labo> findByNombreE(String nombreE);
    public boolean existsByNombreE(String nombreE);
}
