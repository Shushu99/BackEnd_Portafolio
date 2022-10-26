/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.miportfolio.Shushu.Repository;

import com.miportfolio.Shushu.Entity.Portfolio;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoPort extends JpaRepository<Portfolio, Integer>{
    public Optional<Portfolio> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
