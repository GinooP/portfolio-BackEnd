
package com.portfolio.ggp.Interface;

import com.portfolio.ggp.Entity.Persona;
import java.util.List;


public interface IPersonaService {
    //traer una persona
    public List<Persona> getPersona();
    
    //guardar un objeto de tipo Persona
    public void savePersona(Persona persona);
    
    //eliminar un objeto
    public void deletePersona(Long id);
    
    //buscar una persona
    public Persona findPersona(Long id);
}
