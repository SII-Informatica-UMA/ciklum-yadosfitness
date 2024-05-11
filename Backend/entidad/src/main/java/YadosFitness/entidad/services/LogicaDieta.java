package YadosFitness.entidad.services;

import YadosFitness.entidad.repositories.DietaRepository;
import YadosFitness.entidad.entities.Dieta;
import YadosFitness.entidad.exceptions.DietaExistException;
import YadosFitness.entidad.exceptions.DietaNoExisteException;
import YadosFitness.entidad.exceptions.DietaVaciaException;

import java.util.ArrayList;
import java.util.HashSet;
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

    public List<Dieta> dietasDeCliente(Long idCliente) {
        Set<Long> ids = new HashSet<>();
        ids.add(idCliente);
        
        return repo.findByClienteId(ids);
    }

    public List<Dieta> dietasDeEntrenador(Long idEntrenador) {
        return repo.findByEntrenadorId(idEntrenador);
    }

    public void asignarDieta(Long idDieta, Long idCliente) {
        Optional<Dieta> opt = getDietaById(idDieta);
        opt.ifPresent(dieta -> {
            dieta.getCliente().add(idCliente);
            repo.save(dieta);
            });
        opt.orElseThrow(DietaNoExisteException::new);
    }

    public Dieta addDieta(Dieta dieta){
        if(repo.findById(dieta.getId()).isPresent()){
            throw new DietaExistException("Dieta ya existente");
        }
        return repo.save(dieta);
    }

    public Optional<Dieta> getDietaById(Long id){
        Optional<Dieta> optional= repo.findById(id);

        if(optional.isEmpty()){
            throw new DietaNoExisteException("Dieta no existente");
        }else{
            return optional;
        }
    }

    public Dieta updateDieta(Dieta dieta){
        if(repo.existsById(dieta.getId())){
            var opDieta = repo.findById(dieta.getId());
            opDieta.ifPresent(n -> {
                n.setNombre(dieta.getNombre());
                n.setDescripcion(dieta.getDescripcion());
            });
            return repo.save(opDieta.get());
        }else{
            throw new DietaNoExisteException("Dieta no existe");
        }
    }

    public void deleteById(Long id){
        var dieta = repo.findById(id);

        if(dieta.isPresent()){
            repo.deleteById(id);
        }else{
            throw new DietaNoExisteException("Dieta no existente");
        }
    
    }
    
}
