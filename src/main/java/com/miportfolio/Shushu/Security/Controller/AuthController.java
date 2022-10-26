package com.miportfolio.Shushu.Security.Controller;

import com.miportfolio.Shushu.Security.Dto.JwtDto;
import com.miportfolio.Shushu.Security.Dto.LoginUser;
import com.miportfolio.Shushu.Security.Dto.NuevoUser;
import com.miportfolio.Shushu.Security.Entity.Rol;
import com.miportfolio.Shushu.Security.Entity.User;
import com.miportfolio.Shushu.Security.Enums.RolNombre;
import com.miportfolio.Shushu.Security.Service.RolService;
import com.miportfolio.Shushu.Security.Service.UserService;
import com.miportfolio.Shushu.Security.jwt.JwtProvider;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://giuliana-massanet.web.app")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUser nuevoUser, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) 
            return new ResponseEntity(new Mensaje("Campos o email incorrectos"), HttpStatus.BAD_REQUEST);
        
        if(userService.existsByNombreUser(nuevoUser.getNombreUser())) 
            return new ResponseEntity(new Mensaje("Ese usuario ya existe"), HttpStatus.BAD_REQUEST);
        

        if(userService.existsByEmail(nuevoUser.getEmail())) 
            return new ResponseEntity(new Mensaje("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        

        User user = new User(nuevoUser.getNombre(), nuevoUser.getNombreUser(), nuevoUser.getEmail(), passwordEncoder.encode(nuevoUser.getPassword()));

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());

        if(nuevoUser.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);

        return new ResponseEntity(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos incorrectos"), HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getNombreUser(), loginUser.getPassword()));

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return new ResponseEntity(jwtDto, HttpStatus.OK);
    };

}
