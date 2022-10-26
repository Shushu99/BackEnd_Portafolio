/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miportfolio.Shushu.Security.Service;

import com.miportfolio.Shushu.Security.Entity.User;
import com.miportfolio.Shushu.Security.Entity.UserPrincipal;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDetailsImplements implements UserDetailsService{
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String nombreUser) throws UsernameNotFoundException {
        User user = userService.getByNombreUser(nombreUser).get();
        return UserPrincipal.build(user);
    }
    
    
}
