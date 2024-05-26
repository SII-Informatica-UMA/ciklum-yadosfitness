package YadosFitness.entidad.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import YadosFitness.entidad.entities.Usuario;

@Service
public class UsuarioService {
    private RestTemplate restTemplate;

    public Usuario getUsuario(Long id){
        ResponseEntity resp = restTemplate.getForEntity("http://localhost:9001/usuario/"+id, Usuario.class);
        return resp.getStatusCode() == HttpStatus.OK ? (Usuario) resp.getBody() : null;
    }
}
