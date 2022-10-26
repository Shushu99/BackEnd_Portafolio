package com.miportfolio.Shushu.Service;

import com.miportfolio.Shushu.Entity.Persona;
import com.miportfolio.Shushu.Repository.IntPersonaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ImpPersonaService {

    @Autowired
    IntPersonaRepository intpersonaRepository;
    
    public List<Persona> list(){
        return intpersonaRepository.findAll();
    }
    
    public Optional<Persona> getOne(int id){
        return intpersonaRepository.findById(id);
    }
    
    public Optional<Persona> getByNombre(String nombre){
        return intpersonaRepository.findByNombre(nombre);
    }
    
    public void save(Persona persona){
        intpersonaRepository.save(persona);
    }
    
    public void delete(int id){
        intpersonaRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return intpersonaRepository.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return intpersonaRepository.existsByNombre(nombre);
    }
}
