package YadosFitness.entidad.services;

import YadosFitness.entidad.repositories.DietaRepository;
import YadosFitness.entidad.security.SecurityConfguration;
import YadosFitness.entidad.controllers.Mapper;
import YadosFitness.entidad.dtos.ClienteDTO;
import YadosFitness.entidad.dtos.DietaNuevaDTO;
import YadosFitness.entidad.dtos.EntrenadorDTO;
import YadosFitness.entidad.entities.Dieta;
import YadosFitness.entidad.exceptions.DietaExistException;
import YadosFitness.entidad.exceptions.DietaNoExisteException;
import YadosFitness.entidad.exceptions.AcessoNoAutorizadoException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LogicaDieta {
    private DietaRepository repo;
    @Autowired
    private RestTemplate restTemplate;
    private static final String URL_cliente = "http://localhost:8080/cliente";
    private static final String URL_entrenador = "http://localhost:8080/entrenador";

    public LogicaDieta(DietaRepository repo){
        this.repo = repo;
    }

    public List<Dieta> dietasDeCliente(Long idCliente) {
        ResponseEntity resp = restTemplate.getForEntity(URL_cliente + "/" + idCliente, ClienteDTO.class);
        if(SecurityConfguration.getAuthenticatedUser().get().getUsername().equals(resp.getBody().toString())) {
            return repo.findByClienteId(idCliente);
        }else{
            throw new AcessoNoAutorizadoException("No tienes permisos para ver las dietas de otro cliente");
        }
    }

    public List<Dieta> dietasDeEntrenador(Long idEntrenador) {
        ResponseEntity resp = restTemplate.getForEntity(URL_entrenador + "/" + idEntrenador, ClienteDTO.class);
        if(SecurityConfguration.getAuthenticatedUser().get().getUsername().equals(resp.getBody().toString())) {
            return repo.findByEntrenadorId(idEntrenador);
        }else{
            throw new AcessoNoAutorizadoException("No tienes permisos para ver las dietas de otro entrenador");
        }
    }

    public void asignarDieta(Long idDieta, Long idCliente) {
        Long idEntrenador = repo.findById(idDieta).get().getEntrenador();
        ResponseEntity resp = restTemplate.getForEntity(URL_entrenador + "/" + idEntrenador, EntrenadorDTO.class);
        if(SecurityConfguration.getAuthenticatedUser().get().getUsername().equals(resp.getBody().toString())){
            Optional<Dieta> opt = getDietaById(idDieta);
            if(opt.isPresent()){
                Dieta dieta = opt.get();
                dieta.getCliente().add(idCliente);
                repo.save(dieta);
            }else{
                throw new DietaNoExisteException("Dieta no existe");
            }
        }else{
            throw new AcessoNoAutorizadoException("No tienes permisos para asignar dietas");
        }
        
    }

    public Dieta addDieta(Dieta dieta){
        ResponseEntity resp = restTemplate.getForEntity(URL_entrenador + "/" + dieta.getEntrenador(), EntrenadorDTO.class);
        if(SecurityConfguration.getAuthenticatedUser().get().getUsername().equals(resp.getBody().toString())){
            if(repo.findByNombre(dieta.getNombre()).isPresent() ){
                throw new DietaExistException("Dieta ya existente");
            }
            return (Dieta)repo.save(dieta);
        }else{
            throw new AcessoNoAutorizadoException("No tienes permisos para añadir dietas");
        }

    }

    public Optional<Dieta> getDietaById(Long id){
        Dieta dieta = repo.findById(id).get();
        Long idEntrenador = repo.findById(id).get().getEntrenador();
        //Set<Long> clientes = repo.findById(id).get().getCliente();
        ResponseEntity resp = restTemplate.getForEntity(URL_entrenador + "/" + idEntrenador, EntrenadorDTO.class);
        //ResponseEntity resp2 = restTemplate.getForEntity(URL_cliente + "/" + repo.findById(id).get().getCliente(), ClienteDTO.class);
        if(SecurityConfguration.getAuthenticatedUser().get().getUsername().equals(resp.getBody().toString())){
            Optional<Dieta> optional= repo.findById(id);

            if(optional.isEmpty()){
                throw new DietaNoExisteException("Dieta no existente");
            }else{
                return optional;
            }

        }else{
            throw new AcessoNoAutorizadoException("No tienes permisos para ver las dietas de otro entrenador");
        }

    }

    public Dieta updateDieta(Dieta dieta){
        Long idEntrenador = repo.findById(dieta.getId()).get().getEntrenador();
        ResponseEntity resp = restTemplate.getForEntity(URL_entrenador + "/" + idEntrenador, EntrenadorDTO.class);
        if(SecurityConfguration.getAuthenticatedUser().get().getUsername().equals(resp.getBody().toString())){
            if(repo.existsById(dieta.getId())){
                return repo.save(dieta);
            }else{
                throw new DietaNoExisteException("Dieta no existe");
            }
        }else{
            throw new AcessoNoAutorizadoException("No tienes permisos para modificar dietas");
        }
       
    }

    public void deleteById(Long id){
        Long idEntrenador = repo.findById(id).get().getEntrenador();
        ResponseEntity resp = restTemplate.getForEntity(URL_entrenador + "/" +idEntrenador, EntrenadorDTO.class);
        if(SecurityConfguration.getAuthenticatedUser().get().getUsername().equals(resp.getBody().toString())){
            var dieta = repo.findById(id);
            if(!dieta.isPresent()){
                throw new DietaNoExisteException("Dieta no existe");
            }else{   
                repo.deleteById(id);
            }
        }else{
            throw new AcessoNoAutorizadoException("No tienes permisos para eliminar dietas");
        }
        
    }
    
}
