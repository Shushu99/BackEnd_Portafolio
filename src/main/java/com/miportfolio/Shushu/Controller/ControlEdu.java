/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miportfolio.Shushu.Controller;

import com.miportfolio.Shushu.Dto.dtoEdu;
import com.miportfolio.Shushu.Entity.Educacion;
import com.miportfolio.Shushu.Security.Controller.Mensaje;
import com.miportfolio.Shushu.Service.ServiceEdu;
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
@RequestMapping("/educacion")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ControlEdu {
    @Autowired
    ServiceEdu serviceEdu;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = serviceEdu.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
        if(!serviceEdu.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = serviceEdu.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!serviceEdu.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        serviceEdu.delete(id);
        return new ResponseEntity(new Mensaje("Educación eliminada"), HttpStatus.OK);
    }
    
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEdu dtoedu){
        if(StringUtils.isBlank(dtoedu.getNombreEdu())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(serviceEdu.existsByNombreEdu(dtoedu.getNombreEdu())){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = new Educacion(dtoedu.getNombreEdu(), dtoedu.getDescripcionEdu());
        serviceEdu.save(educacion);
        return new ResponseEntity(new Mensaje("Educación Creada"), HttpStatus.OK);
    }
    
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEdu dtoedu){
        if(!serviceEdu.existsById(id)){
        return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);
        }
        if(serviceEdu.existsByNombreEdu(dtoedu.getNombreEdu()) && serviceEdu.getByNombreEdu(dtoedu.getNombreEdu()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoedu.getNombreEdu())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = serviceEdu.getOne(id).get();
        
        educacion.setNombreEdu(dtoedu.getNombreEdu());
        educacion.setDescripcionEdu(dtoedu.getDescripcionEdu());
        
        serviceEdu.save(educacion);
        
        return new ResponseEntity(new Mensaje("Educación Actualizada"), HttpStatus.OK);
    }
}
