package com.portfolio.marce.Interface;

import com.portfolio.marce.Entity.Persona;
import java.util.List;
import java.util.Optional;


public interface IPersonaService {
    
    //traer lista personas
    public List<Persona> getPersona();
    
    //guardar una persona
    public void savePersona(Persona persona);
    
    
    //eliminar persona por id
    public void deletePersona(int id);
    
    //buscar persona por id
    public Persona findPersona(int id);
    
    public Optional<Persona> getOne(int id);
    
    public boolean existsById(int id);
    
    
}
