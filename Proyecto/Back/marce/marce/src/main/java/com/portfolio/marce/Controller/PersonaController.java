package com.portfolio.marce.Controller;

import com.portfolio.marce.Dto.dtoPersona;
import com.portfolio.marce.Entity.Persona;
import com.portfolio.marce.Interface.IPersonaService;
import com.portfolio.marce.Security.Controller.Mensaje;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("personas")
public class PersonaController {
    @Autowired IPersonaService ipersonaservice;
    
    @GetMapping("/traer")
    public List<Persona> getPersona(){
        return ipersonaservice.getPersona();
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/crear")
    public String createPersona(@RequestBody Persona persona){
        ipersonaservice.savePersona(persona);
        return "La persona ha sido creada correctamente";
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/borrar/{id}")
    public String deletePersona(@PathVariable int id){
       ipersonaservice.deletePersona(id);
       return "La persona ha sido eliminada correctamente";
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtoper) {
          //validacion de campo no vacio
        if (StringUtils.isBlank(dtoper.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        } 

        Persona persona = ipersonaservice.getOne(id).get();
        persona.setNombre(dtoper.getNombre());
        persona.setApellido(dtoper.getApellido());
        persona.setImg(dtoper.getImg());
        persona.setFormacion(dtoper.getFormacion());
        persona.setDescripcion(dtoper.getDescripcion());

        ipersonaservice.savePersona(persona);
        return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);

    }
    
    
    @GetMapping("/traer/perfil")
    public Persona findPersona(){
        return ipersonaservice.findPersona(1);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id) {
        if (!ipersonaservice.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Persona persona = ipersonaservice.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

  
  
    }
    
    
    
    
    

