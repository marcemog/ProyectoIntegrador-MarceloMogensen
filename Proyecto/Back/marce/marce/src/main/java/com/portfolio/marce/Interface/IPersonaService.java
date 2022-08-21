package com.portfolio.marce.Interface;

import com.portfolio.marce.Entity.Persona;
import java.util.List;


public interface IPersonaService {
    
    //traer lista personas
    public List<Persona> getPersona();
    
    //guardar una persona
    public void savePersona(Persona persona);
    
    
    //eliminar persona por id
    public void deletePersona(Long id);
    
    //buscar persona por id
    public Persona findPersona(Long id);
    
    
}
