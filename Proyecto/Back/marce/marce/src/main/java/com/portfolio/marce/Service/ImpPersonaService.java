package com.portfolio.marce.Service;

import com.portfolio.marce.Entity.Persona;
import com.portfolio.marce.Interface.IPersonaService;
import com.portfolio.marce.Repository.IPersonaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpPersonaService implements IPersonaService {
    @Autowired 
    IPersonaRepository ipersonarepository;
   
    
    @Override
    public List<Persona> getPersona() {
        List<Persona> persona = ipersonarepository.findAll();
        return persona;
    }

    @Override
    public void savePersona(Persona persona) {
        ipersonarepository.save(persona);
    }

    @Override
    public void deletePersona(int id) {
        ipersonarepository.deleteById(id);
    }

    @Override
    public Persona findPersona(int id) {
        Persona persona = ipersonarepository.findById(id).orElse(null);
        return persona;
    }
    
    
    public boolean existsById(int id){
        return ipersonarepository.existsById(id);
    }
    
     public Optional<Persona> getOne(int id){
        return ipersonarepository.findById(id);
    }
}
