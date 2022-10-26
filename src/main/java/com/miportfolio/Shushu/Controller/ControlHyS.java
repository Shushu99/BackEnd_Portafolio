package com.miportfolio.Shushu.Controller;

import com.miportfolio.Shushu.Dto.dtoHyS;
import com.miportfolio.Shushu.Entity.HyS;
import com.miportfolio.Shushu.Security.Controller.Mensaje;
import com.miportfolio.Shushu.Service.ServiceHyS;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/hys")
public class ControlHyS {
    @Autowired 
    ServiceHyS serviceHyS;
    
    @GetMapping("/lista")
    public ResponseEntity<List<HyS>> list(){
        List<HyS> list = serviceHyS.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<HyS> getById(@PathVariable("id") int id){
        if(!serviceHyS.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        HyS hys = serviceHyS.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }
    
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!serviceHyS.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        serviceHyS.delete(id);
        return new ResponseEntity(new Mensaje("Skill eliminada"), HttpStatus.OK);
    }
  
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHyS dtohys){      
        if(StringUtils.isBlank(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(serviceHyS.existsByNombre(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje("Esa skill existe"), HttpStatus.BAD_REQUEST);
        
        HyS hys = new HyS(dtohys.getNombre(), dtohys.getPorcentaje());
        serviceHyS.save(hys);
        
        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }
    
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHyS dtohys){
        //Validamos si existe el ID
        if(!serviceHyS.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        //Compara nombre de skills
        if(serviceHyS.existsByNombre(dtohys.getNombre()) && serviceHyS.getByNombre(dtohys.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        //No puede estar vacio
        if(StringUtils.isBlank(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        
        HyS hys= serviceHyS.getOne(id).get();
        hys.setNombre(dtohys.getNombre());
        hys.setPorcentaje((dtohys.getPorcentaje()));
        
        serviceHyS.save(hys);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);
             
    }
}
