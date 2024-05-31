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
import org.springframework.boot.autoconfigure.jersey.JerseyProperties.Servlet;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
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
        HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<ClienteDTO> resp = restTemplate.exchange(URL_cliente + "/" + idCliente, HttpMethod.GET,entity,ClienteDTO.class);
        if(SecurityConfguration.getAuthenticatedUser().get().getUsername().equals(resp.getBody().getIdUsuario().toString())) {
            return repo.findByClienteId(idCliente);
        }else{
            throw new AcessoNoAutorizadoException("No tienes permisos para ver las dietas de otro cliente");
        }
    }

    public List<Dieta> dietasDeEntrenador(Long idEntrenador) {
        HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<EntrenadorDTO> resp = restTemplate.exchange(URL_entrenador + "/" + idEntrenador, HttpMethod.GET,entity,EntrenadorDTO.class);
        
        if(SecurityConfguration.getAuthenticatedUser().get().getUsername().equals(resp.getBody().getIdUsuario().toString())) {
            return repo.findAllByEntrenadorId(idEntrenador);
        }else{
            throw new AcessoNoAutorizadoException("No tienes permisos para ver las dietas de otro entrenador");
        }
    }

    public void asignarDieta(Long idDieta, Long idCliente) {
        
        Optional<Dieta> opt = repo.findById(idDieta);
            if(!opt.isPresent()){
                throw new DietaNoExisteException("Dieta no existe");
            }
        HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());

        Long idEntrenador = repo.findById(idDieta).get().getEntrenador();
        ResponseEntity<EntrenadorDTO> resp = restTemplate.exchange(URL_entrenador + "/" + idEntrenador, HttpMethod.GET,entity,EntrenadorDTO.class);;
        
        if(SecurityConfguration.getAuthenticatedUser().get().getUsername().equals(resp.getBody().getIdUsuario().toString())){   
            Dieta dieta = opt.get();
            dieta.getCliente().add(idCliente);
            repo.save(dieta);
        }else{
            throw new AcessoNoAutorizadoException("No tienes permisos para asignar dietas");
        }
        
    }

    public Dieta addDieta(Dieta dieta){
        HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<EntrenadorDTO> resp = restTemplate.exchange(URL_entrenador + "/" + dieta.getEntrenador(), HttpMethod.GET,entity,EntrenadorDTO.class);
        if(SecurityConfguration.getAuthenticatedUser().get().getUsername().equals(resp.getBody().getIdUsuario().toString())){
            if(repo.findByNombre(dieta.getNombre()).isPresent() ){
                throw new DietaExistException("Dieta ya existente");
            }
            return (Dieta)repo.save(dieta);
        }else{
            throw new AcessoNoAutorizadoException("No tienes permisos para añadir dietas");
        }

    }

    public Optional<Dieta> getDietaById(Long id){
        Optional<Dieta> optional= repo.findById(id);

        if(optional.isEmpty()){
            throw new DietaNoExisteException("Dieta no existente");
        }

        HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
        Long idEntrenador = repo.findById(id).get().getEntrenador();
        Set<Long> clientes = repo.findById(id).get().getCliente();
        ResponseEntity<EntrenadorDTO> resp = restTemplate.exchange(URL_entrenador + "/" + idEntrenador, HttpMethod.GET,entity,EntrenadorDTO.class);
        if(SecurityConfguration.getAuthenticatedUser().get().getUsername().equals(resp.getBody().getIdUsuario().toString())){
            return optional;
        }else if(clientes.contains(Long.parseLong(SecurityConfguration.getAuthenticatedUser().get().getUsername()))){
            return optional;
        }else{
            throw new AcessoNoAutorizadoException("No tienes permisos para ver las dietas de otro entrenador");
        }

    }

    public Dieta updateDieta(Dieta dieta){
        if(!repo.existsById(dieta.getId())){
            throw new DietaNoExisteException("Dieta no existe");
        }
        HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
        Long idEntrenador = repo.findById(dieta.getId()).get().getEntrenador();
        ResponseEntity<EntrenadorDTO> resp = restTemplate.exchange(URL_entrenador + "/" + idEntrenador, HttpMethod.GET,entity,EntrenadorDTO.class);
        if(SecurityConfguration.getAuthenticatedUser().get().getUsername().equals(resp.getBody().getIdUsuario().toString())){
            
            return repo.save(dieta);
            
        }else{
            throw new AcessoNoAutorizadoException("No tienes permisos para modificar dietas");
        }
       
    }

    public void deleteById(Long id){
        var dieta = repo.findById(id);
        if(!dieta.isPresent()){
            throw new DietaNoExisteException("Dieta no existe");
        }
        HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
        Long idEntrenador = repo.findById(id).get().getEntrenador();
        ResponseEntity<EntrenadorDTO> resp = restTemplate.exchange(URL_entrenador + "/" + idEntrenador, HttpMethod.GET,entity,EntrenadorDTO.class);
        if(SecurityConfguration.getAuthenticatedUser().get().getUsername().equals(resp.getBody().getIdUsuario().toString())){
            repo.deleteById(id);
        }else{
            throw new AcessoNoAutorizadoException("No tienes permisos para eliminar dietas");
        }
        
    }
    
}
