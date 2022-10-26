/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miportfolio.Shushu.Security.Service;

import com.miportfolio.Shushu.Security.Entity.User;
import com.miportfolio.Shushu.Security.Repository.intUserRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    @Autowired
    intUserRepository intuserRepository;
    
    public Optional<User> getByNombreUser(String nombreUser){
        return intuserRepository.findByNombreUser(nombreUser);
    }
    
    public boolean existsByNombreUser(String nombreUser){
        return intuserRepository.existsByNombreUser(nombreUser);
    }
    
    public boolean existsByEmail(String email){
        return intuserRepository.existsByEmail(email);
    }
    
    public void save(User user){
        intuserRepository.save(user);
    }
}
