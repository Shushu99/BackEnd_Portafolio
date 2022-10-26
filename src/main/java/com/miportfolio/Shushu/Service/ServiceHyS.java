/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miportfolio.Shushu.Service;

import com.miportfolio.Shushu.Entity.HyS;
import com.miportfolio.Shushu.Repository.RepoHyS;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ServiceHyS {
    @Autowired
    RepoHyS repoHyS;
    
    public List<HyS> list(){
        return repoHyS.findAll();
    }
    
    public Optional<HyS> getOne(int id){
        return repoHyS.findById(id);
    }
    
    public Optional<HyS> getByNombre(String nombre){
        return repoHyS.findByNombre(nombre);
    }
    
    public void save(HyS skill){
        repoHyS.save(skill);
    }
    
    public void delete(int id){
        repoHyS.deleteById(id);
    }
    
    public boolean existsById(int id){
        return repoHyS.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return repoHyS.existsByNombre(nombre);
    }
}
