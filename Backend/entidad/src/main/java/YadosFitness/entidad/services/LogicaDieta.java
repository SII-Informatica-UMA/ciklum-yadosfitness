package YadosFitness.entidad.services;

import YadosFitness.entidad.repositories.DietaRepository;
import YadosFitness.entidad.entities.Dieta;
import YadosFitness.entidad.exceptions.DietaNoExisteException;
import YadosFitness.entidad.exceptions.DietaVaciaException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LogicaDieta {
    private DietaRepository repo;


    public LogicaDieta(DietaRepository repo){
        this.repo = repo;
    }

    public List<Dieta> getDietas(){
        return repo.findAll();
    }

    public Dieta getDietaById(Long id){
        Optional<Dieta> optional= repo.findById(id);

        if(optional.isEmpty()){
            throw new DietaNoExisteException("Dieta no existente");
        }else{
            return optional.get();
        }
    }

    /* 
    public List<Dieta> getDietaByClienteId(Set<Long> id){
        return repo.findByClienteId(id);
    }

    public List<Dieta> getDietaByEntrenadorId(Long id){
        Optional<Dieta> optional= repo.findById(id);

        if(optional.isEmpty()){
            throw new DietaNoExisteException("Dieta no existente");
        }else{
            return optional.get();
        }
    }
    */

    public void deleteById(Long id){
        repo.deleteById(id);
    }
    
}
