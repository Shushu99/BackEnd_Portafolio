package com.miportfolio.Shushu.Security.Service;

import com.miportfolio.Shushu.Security.Entity.Rol;
import com.miportfolio.Shushu.Security.Enums.RolNombre;
import com.miportfolio.Shushu.Security.Repository.intRolRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {
    @Autowired
    intRolRepository introlRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return introlRepository.findByRolNombre(rolNombre);
    }
    
    public void save(Rol rol){
        introlRepository.save(rol);
    }
}
