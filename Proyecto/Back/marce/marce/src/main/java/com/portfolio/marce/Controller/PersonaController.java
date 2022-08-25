package com.portfolio.marce.Controller;

import com.portfolio.marce.Entity.Persona;
import com.portfolio.marce.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PersonaController {
    @Autowired IPersonaService ipersonaservice;
    
    @GetMapping("/personas/traer")
    public List<Persona> getPersona(){
        return ipersonaservice.getPersona();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/personas/crear")
    public String createPersona(@RequestBody Persona persona){
        ipersonaservice.savePersona(persona);
        return "La persona ha sido creada correctamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
       ipersonaservice.deletePersona(id);
       return "La persona ha sido eliminada correctamente";
    }
    
    //url: puerto/personas/editar/id?nombre & apellido & img
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
                              @RequestParam("nombre") String nuevoNombre,
                              @RequestParam("apellido") String nuevoApellido,
                              @RequestParam("img") String nuevaImg){
       Persona persona =  ipersonaservice.findPersona(id);
       
       persona.setNombre(nuevoNombre);
       persona.setApellido(nuevoApellido);
       persona.setImg(nuevaImg);
       
       ipersonaservice.savePersona(persona);
       return persona;
    }
    
    
    @GetMapping("personas/traer/perfil")
    public Persona findPersona(){
        return ipersonaservice.findPersona((long)1);
    }

  
  
    }
    
    
    
    
    

