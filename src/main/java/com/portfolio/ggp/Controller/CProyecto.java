/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.ggp.Controller;

import com.portfolio.ggp.Dto.dtoProyecto;
import com.portfolio.ggp.Entity.Proyecto;
import com.portfolio.ggp.Security.Controller.Mensaje;
import com.portfolio.ggp.Service.SProyecto;
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
@RequestMapping("/proyecto")
@CrossOrigin(origins = {"https://frontendggp.web.app", "http://localhost:4200"})
public class CProyecto {
    @Autowired
    SProyecto sProyecto;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list = sProyecto.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id){
        if(!sProyecto.existsById(id)){
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Proyecto proyecto = sProyecto.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyecto dtoproyecto){
        if(StringUtils.isBlank(dtoproyecto.getNombreP())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoproyecto.getDescripcionP())){
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoproyecto.getImgP())) {
            return new ResponseEntity(new Mensaje("La imagen es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if(sProyecto.existsByNombreP(dtoproyecto.getNombreP())){
            return new ResponseEntity(new Mensaje("Ese proyecto existe"), HttpStatus.BAD_REQUEST);
        }
        
        Proyecto proyecto = new Proyecto (dtoproyecto.getNombreP(), dtoproyecto.getDescripcionP(), dtoproyecto.getImgP());
        sProyecto.save(proyecto);
        
        return new ResponseEntity(new Mensaje("Proyecto agregado"), HttpStatus.OK);
        
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyecto dtoproyecto){
        //Validamos si existe el ID
        if(!sProyecto.existsById(id)){
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        if(sProyecto.existsByNombreP(dtoproyecto.getNombreP()) && sProyecto.getByNombreP(dtoproyecto.getNombreP()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoproyecto.getNombreP())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoproyecto.getDescripcionP())){
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoproyecto.getImgP())) {
            return new ResponseEntity(new Mensaje("La imagen es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        Proyecto proyecto = sProyecto.getOne(id).get();
        proyecto.setNombreP(dtoproyecto.getNombreP());
        proyecto.setDescripcionP((dtoproyecto.getDescripcionP()));
        proyecto.setImgP(dtoproyecto.getImgP());
        sProyecto.save(proyecto);
        
        return new ResponseEntity(new Mensaje("Proyecto actualizado"), HttpStatus.OK);
        
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        //Validamos si existe el ID
        if(!sProyecto.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        sProyecto.delete(id);
        
        return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }
}
