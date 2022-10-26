/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.miportfolio.Shushu.Repository;

import com.miportfolio.Shushu.Entity.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoEdu extends JpaRepository<Educacion, Integer>{
    public Optional<Educacion> findByNombreEdu(String nombreEdu);
    public boolean existsByNombreEdu(String nombreEdu);
}
