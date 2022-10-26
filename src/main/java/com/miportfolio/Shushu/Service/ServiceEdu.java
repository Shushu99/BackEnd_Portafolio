/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miportfolio.Shushu.Service;

import com.miportfolio.Shushu.Entity.Educacion;
import com.miportfolio.Shushu.Repository.RepoEdu;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServiceEdu {
    @Autowired
    RepoEdu repoEdu;
    
    public List<Educacion> list(){
        return repoEdu.findAll();
    }
    
    public Optional<Educacion> getOne(int id){
        return repoEdu.findById(id);
    }
    
    public Optional<Educacion> getByNombreEdu(String nombreEdu){
        return repoEdu.findByNombreEdu(nombreEdu);
    }
    
    public void save(Educacion educacion){
        repoEdu.save(educacion);
    }
    
    public void delete(int id){
        repoEdu.deleteById(id);
    }
    
    public boolean existsById(int id){
        return repoEdu.existsById(id);
    }
    
    public boolean existsByNombreEdu(String nombreEdu){
        return repoEdu.existsByNombreEdu(nombreEdu);
    }
}
