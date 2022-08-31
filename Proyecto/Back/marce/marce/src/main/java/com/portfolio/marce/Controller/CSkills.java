package com.portfolio.marce.Controller;

import com.portfolio.marce.Dto.dtoSkills;
import com.portfolio.marce.Entity.Skills;
import com.portfolio.marce.Security.Controller.Mensaje;
import com.portfolio.marce.Service.SkillsServ;
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
@RequestMapping("skills")
@CrossOrigin(origins = "http://localhost:4200")
public class CSkills {
    
    @Autowired
    SkillsServ skillsServ;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Skills>> list() {
        List<Skills> list = skillsServ.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoSkills dtoski) {
        if (StringUtils.isBlank(dtoski.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (skillsServ.existsByNombre(dtoski.getNombre())) {
            return new ResponseEntity(new Mensaje("Habilidad ya existente"), HttpStatus.BAD_REQUEST);
        }

       Skills skill = new Skills(dtoski.getNombre(), dtoski.getPorcentaje(), dtoski.getUrlLogo());
        skillsServ.save(skill);

        return new ResponseEntity(new Mensaje("Habilidad agregada"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoSkills dtoski) {
        //validacion existencia de ID
        if (!skillsServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID no existente"), HttpStatus.BAD_REQUEST);
        }
        //comparacion nombres de exp
        if (skillsServ.existsByNombre(dtoski.getNombre()) && skillsServ.getByNombre(dtoski.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Habilidad ya existente"), HttpStatus.BAD_REQUEST);
        }
        //validacion de campo no vacio
        if (StringUtils.isBlank(dtoski.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es olbigatorio"), HttpStatus.BAD_REQUEST);
        }

        Skills skill = skillsServ.getOne(id).get();
        skill.setNombre(dtoski.getNombre());
        skill.setPorcentaje(dtoski.getPorcentaje());
        skill.setUrlLogo(dtoski.getUrlLogo());

        skillsServ.save(skill);
        return new ResponseEntity(new Mensaje("Habilidades actualizadas"), HttpStatus.OK);

    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //validacion existencia de ID
        if (!skillsServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID no existente"), HttpStatus.BAD_REQUEST);
        }

        skillsServ.delete(id);
        return new ResponseEntity(new Mensaje("Habilidad eliminada"), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") int id) {
        if (!skillsServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Skills skill = skillsServ.getOne(id).get();
        return new ResponseEntity(skill, HttpStatus.OK);
    }
}
