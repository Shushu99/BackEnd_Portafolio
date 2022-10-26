/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miportfolio.Shushu.Service;

import com.miportfolio.Shushu.Entity.Experiencia_Labo;
import com.miportfolio.Shushu.Repository.RepoExpe;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServiceExpe {
    @Autowired
    RepoExpe repoExpe;
    
    public List<Experiencia_Labo> list(){
        return repoExpe.findAll();
    }
    
    public Optional<Experiencia_Labo> getOne(int id){
        return repoExpe.findById(id);
    }
    
    public Optional<Experiencia_Labo> getByNombreE(String nombreE){
        return repoExpe.findByNombreE(nombreE);
    }
    
    public void save(Experiencia_Labo expe){
        repoExpe.save(expe);
    }
    
    public void delete(int id){
        repoExpe.deleteById(id);
    }
    
    public boolean existsById(int id){
        return repoExpe.existsById(id);
    }
    
    public boolean existsByNombreE(String nombreE){
        return repoExpe.existsByNombreE(nombreE);
    }
    
}
