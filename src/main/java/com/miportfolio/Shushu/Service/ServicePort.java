package com.miportfolio.Shushu.Service;

import com.miportfolio.Shushu.Entity.Portfolio;
import com.miportfolio.Shushu.Repository.RepoPort;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServicePort {
    @Autowired
    RepoPort repoPort;
            
    public List<Portfolio> list(){
        return repoPort.findAll();
    }
    
    public Optional<Portfolio> getOne(int id){
        return repoPort.findById(id);
    }
    
    public Optional<Portfolio> getByNombre(String nombre){
        return repoPort.findByNombre(nombre);
    }
    
    public void save(Portfolio portfolio){
        repoPort.save(portfolio);
    }
    
    public void delete(int id){
        repoPort.deleteById(id);
    }
    
    public boolean existsById(int id){
        return repoPort.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return repoPort.existsByNombre(nombre);
    }
}
