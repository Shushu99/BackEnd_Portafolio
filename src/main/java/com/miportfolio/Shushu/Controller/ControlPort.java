/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miportfolio.Shushu.Controller;

import com.miportfolio.Shushu.Dto.dtoPort;
import com.miportfolio.Shushu.Entity.Portfolio;
import com.miportfolio.Shushu.Security.Controller.Mensaje;
import com.miportfolio.Shushu.Service.ServicePort;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio")
@CrossOrigin(origins = "http://localhost:4200")
public class ControlPort {
    @Autowired
    ServicePort servicePort;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Portfolio>> list(){
        List<Portfolio> list = servicePort.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Portfolio> getById(@PathVariable("id") int id){
        if(!servicePort.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Portfolio portfolio = servicePort.getOne(id).get();
        return new ResponseEntity(portfolio, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!servicePort.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        servicePort.delete(id);
        return new ResponseEntity(new Mensaje("Trabajo Eliminado"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPort dtoport){
        if(StringUtils.isBlank(dtoport.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(servicePort.existsByNombre(dtoport.getNombre())){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Portfolio portfolio = new Portfolio(dtoport.getNombre(), dtoport.getDescripcion(), dtoport.getImagen());
        servicePort.save(portfolio);
        return new ResponseEntity(new Mensaje("Trabajo Creado"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPort dtoport){
        if(!servicePort.existsById(id)){
        return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);
        }
        if(servicePort.existsByNombre(dtoport.getNombre()) && servicePort.getByNombre(dtoport.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoport.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        Portfolio portfolio = servicePort.getOne(id).get();
        
        portfolio.setNombre(dtoport.getNombre());
        portfolio.setDescripcion(dtoport.getDescripcion());
        portfolio.setImagen(dtoport.getImagen());
        
        servicePort.save(portfolio);
        
        return new ResponseEntity(new Mensaje("Trabajo Actualizado"), HttpStatus.OK);
    }
}
