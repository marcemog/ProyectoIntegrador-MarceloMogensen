package com.portfolio.marce.Controller;

import com.portfolio.marce.Dto.dtoEducacion;
import com.portfolio.marce.Entity.Educacion;
import com.portfolio.marce.Security.Controller.Mensaje;
import com.portfolio.marce.Service.EducacionServ;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/educ")
@CrossOrigin(origins = "https://marcelo-mogensen-dev.web.app")
public class CEducacion {
    
    @Autowired
    EducacionServ educacionServ;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = educacionServ.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoedu) {
        if (StringUtils.isBlank(dtoedu.getNombreEdu())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (educacionServ.existsByNombreEdu(dtoedu.getNombreEdu())) {
            return new ResponseEntity(new Mensaje("Educación ya existente"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = new Educacion(dtoedu.getNombreEdu(), dtoedu.getDescripcionEdu());
        educacionServ.save(educacion);

        return new ResponseEntity(new Mensaje("Educación agregada"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoedu) {
        //validacion existencia de ID
        if (!educacionServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID no existente"), HttpStatus.NOT_FOUND);
        }
        //comparacion nombres de educacion
        if (educacionServ.existsByNombreEdu(dtoedu.getNombreEdu()) && educacionServ.getByNombreEdu(dtoedu.getNombreEdu()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Educación ya existente"), HttpStatus.BAD_REQUEST);
        }
        //validacion de campo no vacio
        if (StringUtils.isBlank(dtoedu.getNombreEdu())) {
            return new ResponseEntity(new Mensaje("El nombre es olbigatorio"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = educacionServ.getOne(id).get();
        educacion.setNombreEdu(dtoedu.getNombreEdu());
        educacion.setDescripcionEdu(dtoedu.getDescripcionEdu());

        educacionServ.save(educacion);
        return new ResponseEntity(new Mensaje("Educación actualizada"), HttpStatus.OK);

    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //validacion existencia de ID
        if (!educacionServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID no existente"), HttpStatus.NOT_FOUND);
        }

        educacionServ.delete(id);
        return new ResponseEntity(new Mensaje("Educación eliminada"), HttpStatus.OK);

    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id) {
        if (!educacionServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Educacion educacion = educacionServ.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
}
