package YadosFitness.entidad.services;

import YadosFitness.entidad.repositories.DietaRepository;
import YadosFitness.entidad.controllers.Mapper;
import YadosFitness.entidad.dtos.ClienteDTO;
import YadosFitness.entidad.dtos.DietaNuevaDTO;
import YadosFitness.entidad.entities.Dieta;
import YadosFitness.entidad.exceptions.DietaExistException;
import YadosFitness.entidad.exceptions.DietaNoExisteException;

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

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LogicaDieta {
    private DietaRepository repo;
    private static final String URL = "http://localhost:8080/cliente";

    public LogicaDieta(DietaRepository repo){
        this.repo = repo;
    }

    public List<Dieta> dietasDeCliente(Long idCliente) {
       /* try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(URL+"/"+idCliente))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                ClienteDTO cliente = mapper.readValue(response.body(), ClienteDTO.class);
                System.out.println(cliente);
            } else {
                System.out.println("Error: " + response.statusCode());
            }

        } catch (Exception e) {
            throw new DietaNoExisteException();
        } */       
        return repo.findByClienteId(idCliente);
    }

    public List<Dieta> dietasDeEntrenador(Long idEntrenador) {
        return repo.findAllByEntrenadorId(idEntrenador);
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
        if(repo.findByNombre(dieta.getNombre()).isPresent() ){
            throw new DietaExistException("Dieta ya existente");
        }
      
        return (Dieta)repo.save(dieta);
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
            return repo.save(dieta);
        }else{
            throw new DietaNoExisteException("Dieta no existe");
        }
    }

    public void deleteById(Long id){
        var dieta = repo.findById(id);
        if(!dieta.isPresent()){
            throw new DietaNoExisteException("Dieta no existe");
        }else{   
            repo.deleteById(id);
        }
    }
    
}
